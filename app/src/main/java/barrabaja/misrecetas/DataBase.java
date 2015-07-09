package barrabaja.misrecetas;

/*
* Esta clase contiene la base de datos local
* para gestionar la aplicacion. Mas tarde
* sera sustituida por otra clase de base de
* datos que se conecte a una externa.
* */

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.sql.SQLException;

/**
 * Created by Alberto on 06/07/2015.
 */
public class DataBase {

    private Context mCtx = null;
    private DataBaseInternal mDbInternal = null;
    private SQLiteDatabase mDb = null;
    private static final byte DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "mis_recetas";
    private static final String PUREBA = "blablabla";

    //Campos, tablas y creates de la base de datos
    //Campos comunes
    private static final String ID = "id";
    private static final String NOMBRE = "nombre";

    //Tabla nevera
    private static final String TABLE_NEVERA = "nevera";
    private static final String TN_ID_USUARIO = "id_usuario";

    //Create tabla nevera
    private static final String DATABASE_CREATE_NEVERA = "create table "+ TABLE_NEVERA + " (" + ID + " integer primary key, "
            + NOMBRE +" text not null, " + TN_ID_USUARIO + " integer not null)";

    //Tabla alimento
    private static final String TABLE_ALIMENTO = "alimento";
    private static final String TA_ID_TEMPORADA = "id_temporada";
    private static final String TA_ID_TIPO_ALI = "id_tipo_alimento";

    //Create tabla alimento
    private static final String DATABASE_CREATE_ALIMENTO = "create table "+ TABLE_ALIMENTO + " (" + ID + " integer primary key, "
            + NOMBRE +" text not null, " + TA_ID_TEMPORADA + " integer not null, " + TA_ID_TIPO_ALI + " integer not null)";

    //Tabla temporada
    private static final String TABLE_TEMPORADA = "temporada";

    //Create tabla temporada
    private static final String DATABASE_CREATE_TEMPORADA = "create table "+ TABLE_TEMPORADA + " (" + ID + " integer primary key, "
            + NOMBRE +" text not null)";

    //Tabla usuarios
    private static final String TABLE_USUARIOS = "usuarios";
    private static final String TU_APELLIDOS = "apellidos";
    private static final String TU_NICK = "nick";
    private static final String TU_PASS = "pass";
    private static final String TU_EMAIL = "email";
    private static final String TU_SEXO = "sexo";

    //Create tabla usuarios
    private static final String DATABASE_CREATE_USUARIOS = "create table "+ TABLE_USUARIOS + " (" + ID + " integer primary key, "
            + NOMBRE +" text not null, " + TU_APELLIDOS + " text null, " + TU_NICK + " text not null, " + TU_PASS + " text not null, "
            + TU_EMAIL + " text not null, " + TU_SEXO + " text null)";

    //Tabla alimento_nevera
    private static final String TABLE_ALI_NEV = "alimento_nevera";
    private static final String TAN_ID_NEV = "id_nevera";
    private static final String TAN_ID_USU = "id_usuario";
    private static final String TAN_ID_ALI = "id_alimento";

    //Create tabla alimento_nevera
    private static final String DATABASE_CREATE_ALI_NEV = "create table "+ TABLE_ALI_NEV + " (" + ID + " integer primary key, "
            + TAN_ID_NEV +" integer not null, " + TAN_ID_USU + " integer not null)";

    //Tabla recetas
    private static final String TABLE_RECETAS = "recetas";
    private static final String TR_ID_TIPO = "id_tipo";
    private static final String TR_DESC = "descripcion";
    private static final String TR_FOTO = "foto";
    private static final String TR_VIDEO = "video";
    private static final String TR_COMPARTIR = "compartir";
    private static final String TR_ID_USU = "id_usuario";
    private static final String TR_FECHA = "fecha";

    //Create tabla recetas
    private static final String DATABASE_CREATE_RECETAS = "create table "+ TABLE_RECETAS + " (" + ID + " integer primary key, "
            + NOMBRE +" text not null, " + TR_ID_TIPO + " integer not null, " + TR_DESC + " text null, " + TR_FOTO + " text null, "
            + TR_VIDEO + " text null, " + TR_COMPARTIR + " integer not null, " + TR_ID_USU + " integer not null, "
            + TR_FECHA + " integer not null)";

    //Tabla tipo_receta
    private static final String TABLE_TIPO_REC = "tipo_receta";

    //Create tabla tipo_receta
    private static final String DATABASE_CREATE_TIPO_REC= "create table "+ TABLE_TIPO_REC + " (" + ID + " integer primary key, "
            + NOMBRE +" text not null)";

    //Tabla alimento_receta
    private static final String TABLE_ALI_REC = "alimento_receta";
    private static final String TAR_ID_ALI = "id_alimento";
    private static final String TAR_ID_REC = "id_receta";
    private static final String TAR_CANT = "cantidad";

    //Create tabla alimento_receta
    private static final String DATABASE_CREATE_ALI_REC = "create table "+ TABLE_ALI_REC + " (" + ID + " integer primary key, "
            + TAR_ID_ALI +" integer not null, " + TAR_ID_REC + " integer not null, " + TAR_CANT + " integer not null)";

    //Tabla tipo_alimento
    private static final String TABLE_TIPO_ALI = "tipo_alimento";

    //Create tabla tipo_alimento
    private static final String DATABASE_CREATE_TIPO_ALI = "create table "+ TABLE_TIPO_ALI + " (" + ID + " integer primary key, "
            + NOMBRE +" text not null)";

    private static class DataBaseInternal extends SQLiteOpenHelper{

        public DataBaseInternal(Context context){super(context, DATABASE_NAME, null, DATABASE_VERSION);}

        @Override
        public void onCreate(SQLiteDatabase db) {
            createTables(db);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            deleteTables(db);
            createTables(db);
        }

        private void createTables (SQLiteDatabase db, String table){
            db.execSQL(table);
        }
        private void createTables (SQLiteDatabase db){db.execSQL("");}
        private void deleteTables (SQLiteDatabase db){ db.execSQL("DROP TABLE IF EXISTS " + TABLE_NEVERA);}
    }

    public DataBase open() throws SQLException{
        mDbInternal = new DataBaseInternal(mCtx);
        mDb = mDbInternal.getWritableDatabase();
        return this;
    }

    public void close(){
        mDbInternal.close();
    }


    /*
    * Aquí se irán añadiendo las consultas que vayan siendo necesarias;
    * */
    public Cursor getItemsNevera(){
        return mDb.query(TABLE_NEVERA, new String[] {ID, NOMBRE, TN_ID_USUARIO}, null, null, null, null, null);
    }

    public Cursor getItemsAlimento(){
        return mDb.query(TABLE_ALIMENTO, new String[] {ID, NOMBRE, TA_ID_TEMPORADA, TA_ID_TIPO_ALI}, null, null, null, null, null);
    }

    public Cursor getItemsTemporada(){
        return mDb.query(TABLE_TEMPORADA, new String[] {ID, NOMBRE}, null, null, null, null, null);
    }

    public Cursor getItemsUsuarios(){
        return mDb.query(TABLE_USUARIOS, new String[] {ID, NOMBRE, TU_APELLIDOS,
                TU_NICK, TU_PASS, TU_EMAIL, TU_SEXO}, null, null, null, null, null);
    }

    //Aquí tengo que añadir el id de la nevera y el usuario para que me de el resultado;
    public Cursor getItemsAliNev(){
        return mDb.query(TABLE_ALI_NEV, new String[] {ID, TAN_ID_USU, TAN_ID_NEV, TAN_ID_ALI}, null, null, null, null, null);
    }

    public Cursor getItemsRecetas(){
        return mDb.query(TABLE_RECETAS, new String[] {ID, NOMBRE, TR_ID_TIPO, }, null, null, null, null, null);
    }

    public Cursor getItemsTipoRec(){
        return mDb.query(TABLE_TIPO_REC, new String[] {ID, NOMBRE}, null, null, null, null, null);
    }

    public Cursor getItemsTipoAli(){
        return mDb.query(TABLE_TIPO_ALI, new String[] {ID, NOMBRE}, null, null, null, null, null);
    }

    //A partir de aquí se pondrán los insert, de momento solo pongo el de Usuarios para probar.

    public long insertItemUsuario(String nombre, String apellidos, String nick, String pass, String email, String sexo){
        ContentValues initialValues = new ContentValues();
        initialValues.put(NOMBRE,nombre);
        initialValues.put(TU_APELLIDOS,apellidos);
        initialValues.put(TU_NICK,nick);
        initialValues.put(TU_PASS,pass);
        initialValues.put(TU_EMAIL,email);
        initialValues.put(TU_SEXO,sexo);

        return mDb.insert(TABLE_USUARIOS, null, initialValues);
    }

}
