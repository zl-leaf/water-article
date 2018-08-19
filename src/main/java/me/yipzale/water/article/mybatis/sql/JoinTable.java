package me.yipzale.water.article.mybatis.sql;

public class JoinTable {
    private String table;
    private String on;
    private String joinType;

    public JoinTable(String table, String on, String joinType) {
        this.table = table;
        this.on = on;
        this.joinType = joinType;
    }

    public String getTable() {
        return table;
    }

    public String getOn() {
        return on;
    }

    public String getJoinType() {
        return joinType;
    }
}
