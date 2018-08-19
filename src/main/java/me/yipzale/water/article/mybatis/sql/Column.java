package me.yipzale.water.article.mybatis.sql;

public class Column {
    private String column;
    private String valueKey;

    public Column(String column, String valueKey) {
        this.column = column;
        this.valueKey = valueKey;
    }

    public String getColumn() {
        return column;
    }

    public String getValueKey() {
        return valueKey;
    }
}
