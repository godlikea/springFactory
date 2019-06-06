package com.kai.common.datasource;

/**
 * @author ggk
 * @date 2019/5/28 0028 上午 9:10
 */
public class DataSourceContextHolder {

    private static final ThreadLocal<DatabaseType> thread=new ThreadLocal<>();

    public static void setDatabaseType(DatabaseType databaseType){
        thread.set(databaseType);
    }

    public static DatabaseType getDatabaseType(){
        return thread.get();
    }

    public static void clearDataBase(){
        thread.remove();
    }
}
