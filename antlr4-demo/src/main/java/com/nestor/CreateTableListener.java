package com.nestor;

import java.util.List;

import antlr4.mysql.MySqlParser;
import antlr4.mysql.MySqlParserBaseListener;

/**
 * @author : Nestor.Bian
 * @version : V 1.0
 * @date : 2023/2/15
 */
public class CreateTableListener extends MySqlParserBaseListener {
    private String tableName = null;

    @Override
    public void enterQueryCreateTable(MySqlParser.QueryCreateTableContext ctx) {
        List<MySqlParser.TableNameContext> tableSourceContexts = ctx.getRuleContexts(
                MySqlParser.TableNameContext.class);
        for (MySqlParser.TableNameContext tableSource : tableSourceContexts) {
            // 通过tableSourceItems获取表名
            tableName = tableSource.getText();
        }
    }



    public String getTableName() {
        return tableName;
    }
}
