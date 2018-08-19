package me.yipzale.water.article.mybatis.sql;

import org.apache.ibatis.type.JdbcType;

/**
 * SQL的where条件
 */
public class Condition {
    private Column column;
    private String op;
    private JdbcType jdbcType;
    private String connectOp;

    public Condition(Column column, String op, JdbcType jdbcType) {
        this.column = column;
        this.op = op;
        this.jdbcType = jdbcType;
    }

    public void setConnectOp(String connectOp) {
        this.connectOp = connectOp;
    }

    public Column getColumn() {
        return column;
    }

    public String getOp() {
        return op;
    }

    public JdbcType getJdbcType() {
        return jdbcType;
    }

    public String getConnectOp() {
        return connectOp;
    }
}
