package com.nestor;

import antlr4.mysql.MySqlParser;
import antlr4.mysql.MySqlParserBaseListener;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author : Nestor.Bian
 * @version : V 1.0
 * @date : 2023/2/16
 */
public class GetTableNamesListener extends MySqlParserBaseListener {
    private final Set<String> tableNameSet = new HashSet<String>();

    @Override
    public void enterTableSources(MySqlParser.TableSourcesContext ctx) {
        List<MySqlParser.TableSourceContext> tableSourceContexts = ctx.getRuleContexts(MySqlParser.TableSourceContext.class);
        for (MySqlParser.TableSourceContext tableSource : tableSourceContexts) {
            //通过tableSourceItems获取表名
            getTableNameByTableSourceItems(tableSource.getRuleContexts(MySqlParser.TableSourceItemContext.class));
            //获取join部分
            List<MySqlParser.OuterJoinContext> joinContexts = tableSource.getRuleContexts(MySqlParser.OuterJoinContext.class);
            for (MySqlParser.OuterJoinContext joinContext : joinContexts) {
                List<MySqlParser.TableSourceItemContext> tableSourceItemContexts = joinContext.getRuleContexts(MySqlParser.TableSourceItemContext.class);
                getTableNameByTableSourceItems(tableSourceItemContexts);
            }
        }
    }

    private void getTableNameByTableSourceItems(List<MySqlParser.TableSourceItemContext> tableSourceItems) {
        for (MySqlParser.TableSourceItemContext tableSourceItem : tableSourceItems) {
            List<MySqlParser.TableNameContext> tableNameContexts = tableSourceItem.getRuleContexts(MySqlParser.TableNameContext.class);
            for (MySqlParser.TableNameContext tableNameContext : tableNameContexts) {
                tableNameSet.add(tableNameContext.getText());
            }
        }
    }

    public Set<String> getTableNameSet() {
        return tableNameSet;
    }
}

