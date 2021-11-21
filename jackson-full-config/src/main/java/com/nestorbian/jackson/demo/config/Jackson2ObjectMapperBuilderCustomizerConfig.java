// package com.nestorbian.jackson.demo.config;
//
// import java.time.LocalDate;
// import java.time.LocalDateTime;
// import java.time.format.DateTimeFormatter;
//
// import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
// import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
//
// import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
// import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
//
// /**
//  * @author : Nestor.Bian
//  * @version : V 1.0
//  * @date : 2021/11/21
//  */
// @Configuration
// public class Jackson2ObjectMapperBuilderCustomizerConfig {
//
//     @Bean
//     public Jackson2ObjectMapperBuilderCustomizer customizer() {
//         return new Jackson2ObjectMapperBuilderCustomizer() {
//             /**
//              * Customize the JacksonObjectMapperBuilder.
//              *
//              * @param jacksonObjectMapperBuilder
//              *            the JacksonObjectMapperBuilder to customize
//              */
//             @Override
//             public void customize(Jackson2ObjectMapperBuilder jacksonObjectMapperBuilder) {
//                 jacksonObjectMapperBuilder.deserializerByType(LocalDateTime.class,
//                         new LocalDateTimeDeserializer(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
//                 jacksonObjectMapperBuilder.deserializerByType(LocalDate.class,
//                         new LocalDateDeserializer(DateTimeFormatter.ISO_DATE));
//             }
//         };
//     }
//
// }
