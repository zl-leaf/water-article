package me.yipzale.water.article.mybatis.sql;

import org.apache.commons.lang.text.StrBuilder;
import org.apache.ibatis.type.JdbcType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class QuerySQL {
    private QuerySQL.QuerySQLStatement sql = new QuerySQL.QuerySQLStatement();

    public QuerySQL select(String columns) {
        sql.select = columns;
        return this;
    }

    public QuerySQL from(String table) {
        sql.table = table;
        return this;
    }

    public QuerySQL join(String table, String on, String joinType) {
        sql.join.add(new JoinTable(table, on, joinType));
        return this;
    }

    public QuerySQL where(Column column, String op, JdbcType jdbcType) {
        Condition condition = new Condition(column, op, jdbcType);
        sql.where.add(condition);
        return this;
    }

    public QuerySQL and(Column column, String op, JdbcType jdbcType) {
        Condition condition = new Condition(column, op, jdbcType);
        condition.setConnectOp("AND");
        sql.where.add(condition);
        return this;
    }

    public QuerySQL or(Column column, String op, JdbcType jdbcType) {
        Condition condition = new Condition(column, op, jdbcType);
        condition.setConnectOp("OR");
        sql.where.add(condition);
        return this;
    }

    public QuerySQL orderBy(String orderBy) {
        sql.orderBy.add(orderBy);
        return this;
    }

    public QuerySQL limit(int offset, int limit) {
        sql.offset = offset;
        sql.limit = limit;
        return this;
    }

    @Override
    public String toString() {
        return sql.toSQL();
    }

    private class QuerySQLStatement {
        String select = "*";
        String table;
        List<JoinTable> join = new ArrayList<>();
        List<Condition> where = new ArrayList<>();
        List<String> orderBy = new ArrayList<>();
        int offset = 0;
        int limit = 0;

        public String toSQL() {
            StringBuilder builder = new StringBuilder();
            builder.append(String.format("SELECT %s FROM %s", select, table));

            // join
            if (!join.isEmpty()) {
                for (JoinTable joinTable: join) {
                    builder.append(
                            String.format(" %s join %s on %s",
                                    joinTable.getJoinType(),
                                    joinTable.getTable(),
                                    joinTable.getOn()
                            )
                    );
                }
            }

            // where
            if (!where.isEmpty()) {
                StringBuilder whereCondition = new StringBuilder();
                for (int i = 0; i < where.size(); i++) {
                    Condition condition = where.get(i);
                    if (i > 0) {
                        whereCondition.insert(0, "(");
                        whereCondition.append(
                                String.format(" %s %s.%s %s #{%s, jdbcType=%s}",
                                        condition.getConnectOp(),
                                        table,
                                        condition.getColumn().getColumn(),
                                        condition.getOp(),
                                        condition.getColumn().getValueKey(),
                                        condition.getJdbcType().toString()
                                )
                        );
                        whereCondition.append(")");
                    } else {
                        whereCondition.append(
                                String.format("%s.%s %s #{%s, jdbcType=%s}",
                                        table,
                                        condition.getColumn().getColumn(),
                                        condition.getOp(),
                                        condition.getColumn().getValueKey(),
                                        condition.getJdbcType().toString()
                                )
                        );
                    }
                }
                builder.append(" WHERE ");
                builder.append(whereCondition);
            }

            // orderby
            if (orderBy.size() > 0) {
                builder.append(" ORDER BY ");
                for (int i = 0; i < orderBy.size(); i++) {
                    if (i > 0) {
                        builder.append(",");
                    }
                    builder.append(orderBy.get(i));
                }
            }

            // limit
            if (limit > 0) {
                builder.append(String.format(" LIMIT %d,%d", offset, limit));
            }

            return builder.toString();
        }
    }
}
