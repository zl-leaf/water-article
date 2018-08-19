package me.yipzale.water.article.mybatis.sql;

import org.apache.ibatis.type.JdbcType;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class QueryBuilder {
    private QuerySQL sql = new QuerySQL();

    public QueryBuilder select(String columns) {
        sql.select(columns);
        return this;
    }

    public QueryBuilder from(String table) {
        sql.from(table);
        return this;
    }

    public QueryBuilder innerJoin(String table, String on) {
        sql.join(table, on, "inner");
        return this;
    }

    public QueryBuilder leftJoin(String table, String on) {
        sql.join(table, on, "left");
        return this;
    }

    public QueryBuilder where(String property, String op, Object value, JdbcType jdbcType) {
        Column column = property2Column(property);
        sql.where(column, op, jdbcType);
        bindValue(property, value);
        return this;
    }

    public QueryBuilder where(String property, Object value, JdbcType jdbcType) {
        return where(property, "=", value, jdbcType);
    }

    public QueryBuilder and(String property, String op, Object value, JdbcType jdbcType) {
        Column column = property2Column(property);
        sql.and(column, op, jdbcType);
        bindValue(property, value);
        return this;
    }

    public QueryBuilder and(String property, Object value, JdbcType jdbcType) {
        return and(property, "=", value, jdbcType);
    }

    public QueryBuilder or(String property, String op, Object value, JdbcType jdbcType) {
        Column column = property2Column(property);
        sql.or(column, op, jdbcType);
        bindValue(property, value);
        return this;
    }

    public QueryBuilder or(String property, Object value, JdbcType jdbcType) {
        return or(property, "=", value, jdbcType);
    }

    public QueryBuilder orderBy(String property, String order) {
        Column column = property2Column(property);
        sql.orderBy(column.getColumn() + " " + order);
        return this;
    }

    public QueryBuilder orderBy(String property) {
        return orderBy(property, "ASC");
    }

    public QueryBuilder limit(int offset, int limit) {
        sql.limit(offset, limit);
        return this;
    }

    public QueryBuilder limit(int limit) {
        return limit(0, limit);
    }

    private Column property2Column(String property) {
        StringBuilder builder = new StringBuilder();
        for (char p:property.toCharArray()) {
            if (p >= 'A' && p <= 'Z') {
                builder.append('_');
                p += 32;
            }
            builder.append(p);
        }
        String column = builder.toString();
        return new Column(column, property);
    }

    private void bindValue(String property, Object value) {
        PropertyDescriptor descriptor = null;
        try {
            descriptor = new PropertyDescriptor(property, this.getClass());
            Method method = descriptor.getWriteMethod();
            method.invoke(this, value);
        } catch (IntrospectionException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

    }

    public String getSql() {
        return sql.toString();
    }
}
