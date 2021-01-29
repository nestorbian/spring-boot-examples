package com.nestor.springabstractcacheredis.common.plugin;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.commons.lang3.ObjectUtils;
import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.PluginAdapter;
import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.api.dom.java.Interface;
import org.mybatis.generator.api.dom.java.JavaVisibility;
import org.mybatis.generator.api.dom.java.Method;
import org.mybatis.generator.api.dom.java.Parameter;
import org.mybatis.generator.config.ModelType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;

/**
 * 支持XMLMAPPER生成对应的mapper接口，如有其他需求可以在此基础上拓展
 * 
 * @author : Nestor.Bian
 * @version : V 1.0
 * @date : 2020/6/1
 */
public class BaseMapperGeneratorPlugin extends PluginAdapter {

	private static final Logger log = LoggerFactory.getLogger(BaseMapperGeneratorPlugin.class);

	public boolean validate(List<String> warnings) {
		return true;
	}

	/**
	 * 生成mapper接口
	 *
	 * @param interfaze
	 * @param introspectedTable
	 * @return boolean
	 * @date : 2020/6/7 16:05
	 * @author : Nestor.Bian
	 * @since : 1.0
	 */
	@Override
	public boolean clientGenerated(Interface interfaze, IntrospectedTable introspectedTable) {
		String baseMapper = (String) properties.get("baseMapper");
		String baseMapperClassName = baseMapper.substring(baseMapper.lastIndexOf(".") + 1);

		// 不支持flat模式
		String primaryKeyType;
		if (introspectedTable.getContext().getDefaultModelType() == ModelType.CONDITIONAL) {
			List<IntrospectedColumn> primaryKeyColumns = introspectedTable.getPrimaryKeyColumns();
			if (primaryKeyColumns.size() > 1) {
				primaryKeyType = introspectedTable.getPrimaryKeyType();
			} else {
				primaryKeyType = introspectedTable.getPrimaryKeyColumns().get(0).getFullyQualifiedJavaType().toString();
			}
		} else if (introspectedTable.getContext().getDefaultModelType() == ModelType.HIERARCHICAL) {
			primaryKeyType = introspectedTable.getPrimaryKeyType();
		} else {
			List<IntrospectedColumn> primaryKeyColumns = introspectedTable.getPrimaryKeyColumns();
			if (primaryKeyColumns.size() > 1) {
				throw new RuntimeException("flat模式下暂不支持组合主键");
			}
			primaryKeyType = introspectedTable.getPrimaryKeyColumns().get(0).getFullyQualifiedJavaType().toString();
		}

		FullyQualifiedJavaType fqjt = new FullyQualifiedJavaType(
				baseMapperClassName + "<" + introspectedTable.getBaseRecordType() + ","
						+ introspectedTable.getExampleType() + "," + primaryKeyType + ">");
		FullyQualifiedJavaType imp = new FullyQualifiedJavaType(baseMapper);

		// 添加 extends BaseMapper
		interfaze.addSuperInterface(fqjt);

		interfaze.getImportedTypes().clear();
		interfaze.addImportedType(new FullyQualifiedJavaType(introspectedTable.getBaseRecordType()));
		interfaze.addImportedType(new FullyQualifiedJavaType(introspectedTable.getExampleType()));
		// 添加 import BaseMapper;
		interfaze.addImportedType(imp);

		// 方法不需要
		interfaze.getMethods().clear();

		// 保留原mapper接口上自定义的方法
		try {
			Class<?> mapperClass = Class.forName(introspectedTable.getMyBatis3JavaMapperType());
			interfaze.getMethods().addAll(buildMybatisMethod(mapperClass, interfaze));
		} catch (ClassNotFoundException e) {
			log.info(introspectedTable.getMyBatis3JavaMapperType() + "不存在, 无需保留mapper中自定义方法");
		} catch (Exception e) {
			log.error("BaseMapperGeneratorPlugin发生错误：\n", e);
		}
		interfaze.getAnnotations().clear();

		return true;
	}

	/**
	 * 不覆盖，保留原mapper接口上自定义的方法
	 *
	 * @param interfaceClass
	 * @return java.util.List<org.mybatis.generator.api.dom.java.Method>
	 * @date : 2020/6/7 15:35
	 * @author : Nestor.Bian
	 * @since : 1.0
	 */
	private List<Method> buildMybatisMethod(Class<?> interfaceClass, Interface interfaze) throws Exception {
		List<Method> mybatisMethodList = new ArrayList<>();
		boolean anInterface = interfaceClass.isInterface();

		// 非接口类直接返回
		if (!anInterface) {
			return mybatisMethodList;
		}

		List<java.lang.reflect.Method> sortedList = Arrays.stream(interfaceClass.getDeclaredMethods()).sorted(
				Comparator.comparing(java.lang.reflect.Method::getName)).collect(Collectors.toList());
		for (java.lang.reflect.Method method : sortedList) {
			int modifiers = method.getModifiers();
			if (Modifier.isPublic(modifiers) && Modifier.isAbstract(modifiers)) {
				Method mybatisMethod = new Method(method.getName());
				// 添加注解
				Annotation[] methodAnnotation = method.getDeclaredAnnotations();
				if (methodAnnotation.length != 0) {
					listAnnotationStr(methodAnnotation, interfaze).forEach(mybatisMethod::addAnnotation);
				}

				// 设置访问权限
				mybatisMethod.setConstructor(false);
				mybatisMethod.setSynchronized(Modifier.isSynchronized(modifiers));
				mybatisMethod.setNative(Modifier.isNative(modifiers));
				mybatisMethod.setDefault(false);
				mybatisMethod.setAbstract(Modifier.isAbstract(modifiers));
				mybatisMethod.setFinal(Modifier.isFinal(modifiers));
				mybatisMethod.setVisibility(JavaVisibility.PUBLIC);
				mybatisMethod.setStatic(Modifier.isStatic(modifiers));

				// 添加异常
				Class<?>[] exceptionTypes = method.getExceptionTypes();
				Arrays.stream(exceptionTypes).forEach(item -> {
					mybatisMethod.addException(new FullyQualifiedJavaType(item.getCanonicalName()));
					// 添加import
					interfaze.addImportedType(new FullyQualifiedJavaType(item.getCanonicalName()));
				});

				// 添加参数以及参数注解
				java.lang.reflect.Parameter[] parameters = method.getParameters();
				for (int i = 0; i < parameters.length; i++) {
					Parameter parameter = new Parameter(
							new FullyQualifiedJavaType(parameters[i].getParameterizedType().getTypeName()),
							parameters[i].getName());
					interfaze.addImportedType(new FullyQualifiedJavaType(parameters[i].getType().getCanonicalName()));
					Annotation[] annotations = parameters[i].getAnnotations();
					if (annotations.length != 0) {

						// 添加参数的注解，暂时仅支持@Transactional@Param，如有其他需要可以自己拓展
						listAnnotationStr(annotations, interfaze).forEach(parameter::addAnnotation);

					}
					mybatisMethod.addParameter(parameter);
				}

				// 设置返回类型
				String reutrnTypeName = method.getGenericReturnType().getTypeName();
				mybatisMethod.setReturnType(new FullyQualifiedJavaType(reutrnTypeName));
				// 添加import
				interfaze.addImportedType(new FullyQualifiedJavaType(method.getReturnType().getCanonicalName()));

				mybatisMethodList.add(mybatisMethod);
			}
		}

		return mybatisMethodList;
	}

	/**
	 * 获取注解字符串
	 *
	 * @param annotations
	 * @return java.util.List<java.lang.String>
	 * @date : 2020/6/8 20:57
	 * @author : Nestor.Bian
	 * @since : 1.0
	 */
	private List<String> listAnnotationStr(Annotation[] annotations, Interface interfaze)
			throws IllegalAccessException, InvocationTargetException {
		List<String> annotationStrList = new ArrayList<>();
		for (Annotation item : annotations) {
			// 添加import
			interfaze.addImportedType(new FullyQualifiedJavaType(item.annotationType().getCanonicalName()));

			// 过滤出public abstract的方法Interface interfaze
			java.lang.reflect.Method[] annotationDeclaredMethods = item.annotationType().getDeclaredMethods();
			Optional<java.lang.reflect.Method> valueMethod = Arrays.stream(annotationDeclaredMethods).filter(
					x -> "value".equals(x.getName())).findAny();
			List<java.lang.reflect.Method> methodListAfterFilter = Arrays.stream(annotationDeclaredMethods).filter(
					x -> !"value".equals(x.getName())).filter(
							x -> Modifier.isPublic(x.getModifiers()) && Modifier.isAbstract(x.getModifiers())).sorted(
									Comparator.comparing(java.lang.reflect.Method::getName)).collect(
											Collectors.toList());
			valueMethod.ifPresent(x -> methodListAfterFilter.add(0, x));

			StringBuilder annotationBuilder = new StringBuilder(
					String.format("@%s", item.annotationType().getSimpleName()));
			// 筛选出指定值的方法
			List<java.lang.reflect.Method> valueMethods = new ArrayList<>();

			for (java.lang.reflect.Method x : methodListAfterFilter) {
				if (!ObjectUtils.isEmpty(x.invoke(item)) && !x.invoke(item).equals(x.getDefaultValue())) {
					valueMethods.add(x);
				}
			}

			if (!CollectionUtils.isEmpty(valueMethods)) {
				annotationBuilder.append("(");

				// 添加注解属性
				for (java.lang.reflect.Method x : valueMethods) {
					annotationBuilder.append(String.format("%s = ", x.getName()));
					Object val = x.invoke(item);
					if (val.getClass().isArray()) {
						Object[] objects = (Object[]) val;
						if (objects.length > 1) {
							annotationBuilder.append("{");
						}
						for (Object singleItem : objects) {
							concatSingleItem(singleItem, annotationBuilder, interfaze);
							annotationBuilder.append(", ");
						}
						annotationBuilder.delete(annotationBuilder.length() - 2, annotationBuilder.length());
						if (objects.length > 1) {
							annotationBuilder.append("}");
						}
					} else {
						concatSingleItem(val, annotationBuilder, interfaze);
					}
					annotationBuilder.append(", ");
				}

				annotationBuilder.delete(annotationBuilder.length() - 2, annotationBuilder.length());
				annotationBuilder.append(")");
			}
			annotationStrList.add(annotationBuilder.toString());
		}

		return annotationStrList;
	}

	/**
	 * 拼接单个元素
	 *
	 * @param val
	 * @param annotationBuilder
	 * @param interfaze
	 * @return void
	 * @date : 2020/6/13 16:52
	 * @author : Nestor.Bian
	 * @since : 1.0
	 */
	private void concatSingleItem(Object val, StringBuilder annotationBuilder, Interface interfaze) {
		if (String.class.equals(val.getClass())) {
			annotationBuilder.append(String.format("\"%s\"", val));
		} else if (val.getClass().isEnum()) {
			// 支持枚举类型value
			annotationBuilder.append(val.getClass().getSimpleName()).append(".").append(((Enum) val).name());
			interfaze.addImportedType(new FullyQualifiedJavaType(val.getClass().getCanonicalName()));
		} else if (val instanceof Class) {
			annotationBuilder.append(((Class) val).getSimpleName()).append(".class");
			interfaze.addImportedType(new FullyQualifiedJavaType(((Class) val).getCanonicalName()));
		} else {
			annotationBuilder.append(val);
			interfaze.addImportedType(new FullyQualifiedJavaType(val.getClass().getCanonicalName()));
		}
	}

}
