package com.bin.refusedemo.utils;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class ContactInjfoDao {
    private MyDBHelper mMyDBHelper;

    /**
     * dao类需要实例化数据库Help类,只有得到帮助类的对象我们才可以实例化 SQLiteDatabase
     *
     * @param context
     */
    public ContactInjfoDao(Context context) {
        mMyDBHelper = new MyDBHelper(context);
    }

    // 将数据库打开帮帮助类实例化，然后利用这个对象
    // 调用谷歌的api去进行增删改查

    // 增加的方法吗，返回的的是一个long值
    public long addDate(String name, String phone, String sex, String gexing) {
        // 增删改查每一个方法都要得到数据库，然后操作完成后一定要关闭
        // getWritableDatabase(); 执行后数据库文件才会生成
        // 数据库文件利用DDMS可以查看，在 data/data/包名/databases 目录下即可查看
        SQLiteDatabase sqLiteDatabase = mMyDBHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put("name", name);
        contentValues.put("phone", phone);
        contentValues.put("sex", sex);
        contentValues.put("gexing", gexing);
        // 返回,显示数据添加在第几行
        // 加了现在连续添加了3行数据,突然删掉第三行,然后再添加一条数据返回的是4不是3
        // 因为自增长
        long rowid = sqLiteDatabase.insert("contactinfo", null, contentValues);

        sqLiteDatabase.close();
        return rowid;
    }


    // 删除的方法，返回值是int
    public int deleteDate(String name) {
        SQLiteDatabase sqLiteDatabase = mMyDBHelper.getWritableDatabase();
        int deleteResult = sqLiteDatabase.delete("contactinfo", "name=?", new String[]{name});
        sqLiteDatabase.close();
        return deleteResult;
    }

    /**
     * 修改的方法
     *
     * @param name
     * @param newPhone
     * @return
     */
    public int updateData(String name, String newPhone, String sex, String gexing) {
        SQLiteDatabase sqLiteDatabase = mMyDBHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("phone", newPhone);
        contentValues.put("sex", sex);
        contentValues.put("gexing", gexing);
        int updateResult = sqLiteDatabase.update("contactinfo", contentValues, "name=?", new String[]{name});
        sqLiteDatabase.close();
        return updateResult;
    }

    /**
     * 查询的方法（查找电话）
     *
     * @param name
     * @return
     */
    public String alterDate(String name) {
        String phone = null;
        String sex = null;
        String gexing = null;
        SQLiteDatabase readableDatabase = mMyDBHelper.getReadableDatabase();
        // 查询比较特别,涉及到 cursor
        Cursor cursor = readableDatabase.query("contactinfo", new String[]{"phone", "sex", "gexing"}, "name=?", new String[]{name}, null, null, null);
        while (cursor.moveToNext()) {
            phone = cursor.getString(cursor.getColumnIndex("phone"));
            sex = cursor.getString(cursor.getColumnIndex("sex"));
            gexing = cursor.getString(cursor.getColumnIndex("gexing"));
        }
        cursor.close(); // 记得关闭 corsor
        readableDatabase.close(); // 关闭数据库
        return phone + "-" + sex + "-" + gexing;
    }
}
