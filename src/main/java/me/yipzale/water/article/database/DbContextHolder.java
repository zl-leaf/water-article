package me.yipzale.water.article.database;

public class DbContextHolder {

    private static final ThreadLocal<DbType> contextHolder = new ThreadLocal<DbType>();

    public static void setDbType(DbType dbType){
        if(dbType==null)throw new NullPointerException();
        contextHolder.set(dbType);
    }

    public static DbType getDbType(){
        return contextHolder.get()==null?DbType.MASTER:contextHolder.get();
    }

    public static void clearDbType(){
        contextHolder.remove();
    }
}
