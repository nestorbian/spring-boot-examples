package com.nestor;

import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

import antlr4.mysql.MySqlLexer;
import antlr4.mysql.MySqlParser;

/**
 * @author : Nestor.Bian
 * @version : V 1.0
 * @date : 2023/2/15
 */
public class TestSql {
    public static void main(String args[]) {
        /** antlr4 格式化SQL **/
        // 词法分析器
        MySqlLexer lexer = new MySqlLexer(CharStreams.fromString("create table newtable select * from table1"));
        // 词法符号的缓冲区,用于存储词法分析器生成的词法符号
        CommonTokenStream commonTokenStream = new CommonTokenStream(lexer);
        // 新建一个语法分析器，处理词法符号缓冲区内容
        MySqlParser parser = new MySqlParser(commonTokenStream);
        // 定义CreateTableListener
        CreateTableListener listener = new CreateTableListener();
        ParseTreeWalker.DEFAULT.walk(listener, parser.sqlStatements());
        /** 提取关键参数-表名 */
        String tableName = listener.getTableName();
        /** 测试打印方法 */
        System.out.println(tableName);
    }
}
