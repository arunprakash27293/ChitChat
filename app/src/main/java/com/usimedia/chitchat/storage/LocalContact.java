package com.usimedia.chitchat.storage;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.usimedia.chitchat.model.ChatContact;
import com.usimedia.chitchat.model.Coloumn;

import java.text.SimpleDateFormat;

/**
 * Created by USI IT on 6/4/2016.
 */
public class LocalContact extends  SQLiteOpenHelper {
    private  static  final String  DATABASE_NAME="chitchat";
    private  static  final String TABLE_NAME="contacts";
    private  static  final  int VERSION=1;

    private  static  final Coloumn ID;
    private  static  final Coloumn EMAIL;
    private  static  final Coloumn NAME;
    private  static  final Coloumn STATUS;
    private  static  final Coloumn LASTSEEN;
    private  static  final String SPACE = " ";

    static
    {
        ID=new Coloumn("_id","INTEGER_PRIMARY_KEY AUTOINCREMENT");
        EMAIL=new Coloumn("email","TEXT");
        NAME=new Coloumn("name","TEXT");
        STATUS=new Coloumn("status","TEXT");
        LASTSEEN=new Coloumn("lastseen","TEXT");
    }
    private void createtabel(SQLiteDatabase db){
        //db.execSQL("CREATE_TABLE_IF_NOT_EXISTS".concat(SPACE).concat(ID.getName()).concat(SPACE).concat(ID.getType()));
        db.execSQL("CREATE_TABLE_IF_NOT_EXISTS"
                .concat(SPACE).concat("TABLE_NAME").concat(SPACE)
                .concat("(").concat(SPACE).concat(ID.getName()).concat(SPACE).concat(ID.getType())
                .concat(",").concat(SPACE).concat(EMAIL.getName()).concat(SPACE).concat(EMAIL.getType())
                .concat(",").concat(SPACE).concat(NAME.getName()).concat(SPACE).concat(NAME.getType())
                .concat(",").concat(SPACE).concat(STATUS.getName()).concat(SPACE).concat(STATUS.getType())
                .concat(",").concat(SPACE).concat(LASTSEEN.getName()).concat(SPACE).concat(LASTSEEN.getType())
                .concat(")")
                .concat(";"));
    }
    private void droptable(SQLiteDatabase db)
    {
       db.execSQL
               ("DROP_TABLE_IF_EXISTS"
               .concat("(")
               .concat(SPACE)
               .concat(TABLE_NAME)
               .concat(SPACE)
               .concat(");")
                );

    }
    private void insertchatcontact(ChatContact contact){

        final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

        ContentValues cv= new ContentValues();
        SQLiteDatabase db= getWritableDatabase();
        cv.put(EMAIL.getName(),contact.getEmail());
        cv.put(STATUS.getName(),contact.getStatus());

        cv.put(LASTSEEN.getName(),dateFormat.format(contact.getLastseen()));
        cv.put(NAME.getName(),contact.getName());
        db.insert(TABLE_NAME,null,cv);

    }
    private Cursor getallContacts()

    {
        SQLiteDatabase db=getReadableDatabase();
    String query="SELECT"+ "*" + "FROM"+ TABLE_NAME+ ";";
        return db.rawQuery(query,null);
    }

    public LocalContact(Context context) {
        super(context,DATABASE_NAME, null, VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        createtabel(sqLiteDatabase);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        droptable(sqLiteDatabase);
        createtabel(sqLiteDatabase);
    }
}
