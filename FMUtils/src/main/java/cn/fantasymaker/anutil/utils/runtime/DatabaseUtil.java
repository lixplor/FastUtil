/*
 *     Copyright © 2016 Fantasymaker
 *
 *     Permission is hereby granted, free of charge, to any person obtaining a copy
 *     of this software and associated documentation files (the "Software"), to deal
 *     in the Software without restriction, including without limitation the rights
 *     to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 *     copies of the Software, and to permit persons to whom the Software is
 *     furnished to do so, subject to the following conditions:
 *
 *     The above copyright notice and this permission notice shall be included in all
 *     copies or substantial portions of the Software.
 *
 *     THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 *     IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 *     FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 *     AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 *     LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 *     OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 *     SOFTWARE.
 */

package cn.fantasymaker.anutil.utils.runtime;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.lang.reflect.Field;

import cn.fantasymaker.anutil.utils.FMUtils;

/**
 * Created :  2016-08-11
 * Author  :  Fantasymaker
 * Web     :  http://blog.fantasymaker.cn
 * Email   :  me@fantasymaker.cn
 */
public class DatabaseUtil {

    /*
    todo
    数据库连接, 关闭连接
    库, 表, field 增删改查
    修改表结构
    SQL语句生成
    转换: 对象 -> 表, 表 -> 对象 方式: 反射根据类型建立col; 不能直接序列化text这样无法查找
    事务
     */

    public static final String DEFAULT_DB_NAME = "default.db";
    public static final int DEFAULT_DB_VERSION = 1;

    public static String sCreateSql = "";


    private static Context sContext = FMUtils.getContext();
    public static SQLiteDatabase sSQLiteDatabase;


    public static SQLiteOpenHelper getHelper(String name, int version) {
        return new MySqliteOpenHelper(name, version);
    }

    public static void openDb(boolean writeble, String createTableSql) {
        SQLiteOpenHelper sqLiteOpenHelper = getHelper(DEFAULT_DB_NAME, DEFAULT_DB_VERSION);
        if (writeble) {
            sSQLiteDatabase = sqLiteOpenHelper.getWritableDatabase();
        } else {
            sSQLiteDatabase = sqLiteOpenHelper.getReadableDatabase();
        }
    }

    public static SQLiteDatabase getOrCreateReadableDb(String name, int version, String sql) {
        sCreateSql = sql;
        SQLiteOpenHelper sqLiteOpenHelper = getHelper(name, version);
        return sqLiteOpenHelper.getReadableDatabase();
    }

    public static void closeDb() {
        if (sSQLiteDatabase.isOpen()) {
            sSQLiteDatabase.close();
        }
    }

    public static void drop(String tableName) {
        // TODO: 16/8/14
    }

    public static long replace(String tableName, ContentValues values) {
        long rowId;
        openDb(true, sCreateSql);
        rowId = sSQLiteDatabase.replace(tableName, null, values);
        closeDb();
        return rowId;
    }

    public static long insert(String tableName, ContentValues values) {
        long rowId;
        openDb(true, sCreateSql);
        rowId = sSQLiteDatabase.insert(tableName, null, values);
        closeDb();
        return rowId;
    }

    public static int delete(String tableName, String whereSql, String[] whereArgs) {
        int affectRows;
        openDb(true, sCreateSql);
        affectRows = sSQLiteDatabase.delete(tableName, whereSql, whereArgs);
        closeDb();
        return affectRows;
    }

    public static int deleteAll(String tableName) {
        return delete(tableName, null, null);
    }

    public static int update(String tableName, ContentValues values, String whereSql, String[] whereArgs) {
        int affectRows;
        openDb(true, sCreateSql);
        affectRows = sSQLiteDatabase.update(tableName, values, whereSql, whereArgs);
        closeDb();
        return affectRows;
    }

    public static Cursor query(String tableName, String[] cols, String select, String[] selectArgs, String groupBy, String having, String orderBy) {
        Cursor result;
        openDb(true, sCreateSql);
        result = sSQLiteDatabase.query(tableName, cols, select, selectArgs, groupBy, having, orderBy);
        closeDb();
        return result;
    }

    public static Cursor query(String tableName, String[] cols, String select, String[] selectArgs, String groupBy, String having, String orderBy, String limit) {
        Cursor result;
        openDb(true, sCreateSql);
        result = sSQLiteDatabase.query(tableName, cols, select, selectArgs, groupBy, having, orderBy, limit);
        closeDb();
        return result;
    }

//    public static List<T> queryT(String tableName, String[] cols, String select, String[] selectArgs, String groupBy, String having, String orderBy, String limit){
//        Cursor cursor = query(tableName, cols, select, selectArgs, groupBy, having, orderBy, limit);
//        while(cursor.moveToNext()){
//            cursor.getType()
//        }
//    }

    public static void exec(SQLiteDatabase db, String sql) {
        db.execSQL(sql);
    }

    public static int getVersion() {
        return sSQLiteDatabase.getVersion();
    }

    public static void setVersion(int version) {
        sSQLiteDatabase.setVersion(version);
    }

    public static boolean needUpgrade(int newVersion) {
        return sSQLiteDatabase.needUpgrade(newVersion);
    }

    public static String getPath() {
        return sSQLiteDatabase.getPath();
    }

    public static boolean isReadOnly() {
        return sSQLiteDatabase.isReadOnly();
    }

    public static boolean runTransaction(SQLiteDatabase db, TransactionTask transactionTask) {
        boolean isSuccess = false;
        db.beginTransaction();
        try {
            if (transactionTask != null) {
                transactionTask.onTransactionBegin();
                db.setTransactionSuccessful();
                isSuccess = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
            isSuccess = false;
        } finally {
            db.endTransaction();
        }
        return isSuccess;
    }

    public interface TransactionTask {
        void onTransactionBegin();
    }

    private static class MySqliteOpenHelper extends SQLiteOpenHelper {

        public MySqliteOpenHelper(String name, int version) {
            super(sContext, name, null, version);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(sCreateSql);
        }

        @Override
        public void onOpen(SQLiteDatabase db) {

        }

        @Override
        public void onConfigure(SQLiteDatabase db) {

        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        }

        @Override
        public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        }
    }


    //----------

    /**
     * 生成建表sql语句
     */
    public static String generateSql(Class klass) throws ClassNotFoundException {

        StringBuffer sql = new StringBuffer("CREATE TABLE ");
        // 获取类名， getSimpleName获取类名, getName()获取包名+类名
        sql.append(klass.getSimpleName());
        sql.append("(");
        // 自动创建一个_id
        sql.append("_id integer PRIMARY KEY AUTOINCREMENT,");
        // 获取所有的字段
        Field[] fields = klass.getDeclaredFields();
        for (Field field : fields) {
            // 如果是public的， 则表示不是一个表的字段
            if (field.isAccessible()) {
                continue;
            }
            // 获取字段名
            String name = field.getName();
            sql.append(name).append(" ");
            // 获取字段类型
            Class<?> fieldType = field.getType();
            if (fieldType == String.class) {  // 如果是String
                sql.append("text,");
            } else if (fieldType == Integer.class
                    || fieldType == int.class
                    || fieldType == Long.class
                    || fieldType == long.class) {
                sql.append("integer,");
            } else if (fieldType == Boolean.class
                    || fieldType == boolean.class) {
                sql.append("boolean,");
            } else if (fieldType == Float.class
                    || fieldType == float.class) {
                sql.append("float,");
            }
        }
        sql.replace(sql.length() - 1, sql.length(), "");
        sql.append(");");

        return sql.toString();
    }
}
