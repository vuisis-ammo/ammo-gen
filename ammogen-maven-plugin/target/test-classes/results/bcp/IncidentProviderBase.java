// THIS IS GENERATED CODE, MAKE SURE ANY CHANGES MADE HERE ARE PROPAGATED INTO THE GENERATOR TEMPLATES
package edu.vu.isis.ammo.dash.provider;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;
import android.util.Log;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.database.sqlite.SQLiteCursor;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.os.Environment;
import android.os.ParcelFileDescriptor;
import android.provider.BaseColumns;
import android.text.TextUtils;

import edu.vu.isis.ammo.dash.provider.IncidentSchemaBase.Meta;
import edu.vu.isis.ammo.dash.provider.IncidentSchemaBase.MediaTableSchemaBase;
import edu.vu.isis.ammo.dash.provider.IncidentSchemaBase.EventTableSchemaBase;
import edu.vu.isis.ammo.dash.provider.IncidentSchemaBase.StatusTableSchemaBase;
import edu.vu.isis.ammo.dash.provider.IncidentSchemaBase.CategoryTableSchemaBase;



// BEGIN CUSTOM Incident IMPORTS
// END   CUSTOM  Incident IMPORTS

public abstract class IncidentProviderBase extends ContentProvider {
  public final static String VERSION = "1.7.0";
// Table definitions 
public interface Tables {
   public static final String MEDIA_TBL = "media";
   public static final String EVENT_TBL = "event";
   public static final String STATUS_TBL = "status";
   public static final String CATEGORY_TBL = "category";

}


private static final String MEDIA_KEY_CLAUSE;
static {
  MEDIA_KEY_CLAUSE = new StringBuilder()
  .append(MediaTableSchemaBase.EVENT_ID).append("=?") 
    .toString();
}; 

private static final String EVENT_KEY_CLAUSE;
static {
  EVENT_KEY_CLAUSE = new StringBuilder()
  .append(EventTableSchemaBase.UUID).append("=?") 
    .toString();
}; 

private static final String STATUS_KEY_CLAUSE;
static {
  STATUS_KEY_CLAUSE = new StringBuilder()
    .toString();
}; 

private static final String CATEGORY_KEY_CLAUSE;
static {
  CATEGORY_KEY_CLAUSE = new StringBuilder()
    .toString();
}; 


// Views.
public interface Views {
   // Nothing to put here yet.
}

protected class IncidentDatabaseHelper extends SQLiteOpenHelper {
   // ===========================================================
   // Constants
   // ===========================================================
   private final Logger logger = LoggerFactory.getLogger(IncidentDatabaseHelper.class);

   // ===========================================================
   // Fields
   // ===========================================================

   /** Nothing to put here */


   // ===========================================================
   // Constructors
   // ===========================================================
   public IncidentDatabaseHelper(Context context, String name, int version) {
      super(context, name, null, version);
   }

   /**
    * Pass through to grand parent.
    */
   public IncidentDatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
      super(context, name, factory, version);
   }


   // ===========================================================
   // SQLiteOpenHelper Methods
   // ===========================================================

   @Override
   public synchronized void onCreate(SQLiteDatabase db) {
      logger.info( "Bootstrapping database");
      try {
        /** 
         * Table Name: media <P>
         */
        db.execSQL("CREATE TABLE \"" + Tables.MEDIA_TBL + "\" (" 
          + "\""+MediaTableSchemaBase.EVENT_ID + "\" TEXT, " 
          + "\""+MediaTableSchemaBase.DATA_TYPE + "\" TEXT, "
          + "\""+MediaTableSchemaBase.DATA + "\" TEXT, " 
          + "\""+MediaTableSchemaBase.CREATED_DATE + "\" INTEGER, " 
          + "\""+MediaTableSchemaBase.MODIFIED_DATE + "\" INTEGER, " 
          + "\""+MediaTableSchemaBase._ID + "\" INTEGER PRIMARY KEY AUTOINCREMENT, "
          + "\""+MediaTableSchemaBase._RECEIVED_DATE + "\" LONG, "
          + "\""+MediaTableSchemaBase._DISPOSITION + "\" TEXT );" ); 
        /** 
         * Table Name: event <P>
         */
        db.execSQL("CREATE TABLE \"" + Tables.EVENT_TBL + "\" (" 
          + "\""+EventTableSchemaBase.UUID + "\" TEXT, " 
          + "\""+EventTableSchemaBase.MEDIA_COUNT + "\" INTEGER, " 
          + "\""+EventTableSchemaBase.ORIGINATOR + "\" TEXT, " 
          + "\""+EventTableSchemaBase.DISPLAY_NAME + "\" TEXT, " 
          + "\""+EventTableSchemaBase.CATEGORY_ID + "\" TEXT, " 
          + "\""+EventTableSchemaBase.TITLE + "\" TEXT, " 
          + "\""+EventTableSchemaBase.DESCRIPTION + "\" TEXT, " 
          + "\""+EventTableSchemaBase.LONGITUDE + "\" REAL, " 
          + "\""+EventTableSchemaBase.LATITUDE + "\" REAL, " 
          + "\""+EventTableSchemaBase.CREATED_DATE + "\" INTEGER, " 
          + "\""+EventTableSchemaBase.MODIFIED_DATE + "\" INTEGER, " 
          + "\""+EventTableSchemaBase.CID + "\" TEXT, " 
          + "\""+EventTableSchemaBase.CATEGORY + "\" TEXT, " 
          + "\""+EventTableSchemaBase.UNIT + "\" TEXT, " 
          + "\""+EventTableSchemaBase.SIZE + "\" INTEGER, " 
          + "\""+EventTableSchemaBase.DEST_GROUP_TYPE + "\" TEXT, " 
          + "\""+EventTableSchemaBase.DEST_GROUP_NAME + "\" TEXT, " 
          + "\""+EventTableSchemaBase.STATUS + "\" INTEGER, " 
          + "\""+EventTableSchemaBase._ID + "\" INTEGER PRIMARY KEY AUTOINCREMENT, "
          + "\""+EventTableSchemaBase._RECEIVED_DATE + "\" LONG, "
          + "\""+EventTableSchemaBase._DISPOSITION + "\" TEXT );" ); 
        /** 
         * Table Name: status <P>
         */
        db.execSQL("CREATE TABLE \"" + Tables.STATUS_TBL + "\" (" 
          + "\""+StatusTableSchemaBase.EVENT_UUID + "\" TEXT, " 
          + "\""+StatusTableSchemaBase.STATUS + "\" TEXT, " 
          + "\""+StatusTableSchemaBase.ACTOR + "\" TEXT, " 
          + "\""+StatusTableSchemaBase.MODIFIED_DATE + "\" INTEGER, " 
          + "\""+StatusTableSchemaBase._ID + "\" INTEGER PRIMARY KEY AUTOINCREMENT, "
          + "\""+StatusTableSchemaBase._RECEIVED_DATE + "\" LONG, "
          + "\""+StatusTableSchemaBase._DISPOSITION + "\" TEXT );" ); 
        /** 
         * Table Name: category <P>
         */
        db.execSQL("CREATE TABLE \"" + Tables.CATEGORY_TBL + "\" (" 
          + "\""+CategoryTableSchemaBase.MAIN_CATEGORY + "\" TEXT, " 
          + "\""+CategoryTableSchemaBase.SUB_CATEGORY + "\" TEXT, " 
          + "\""+CategoryTableSchemaBase.TIGR_ID + "\" TEXT, " 
          + "\""+CategoryTableSchemaBase.ICON_TYPE + "\" TEXT, "
          + "\""+CategoryTableSchemaBase.ICON + "\" TEXT, " 
          + "\""+CategoryTableSchemaBase._ID + "\" INTEGER PRIMARY KEY AUTOINCREMENT, "
          + "\""+CategoryTableSchemaBase._RECEIVED_DATE + "\" LONG, "
          + "\""+CategoryTableSchemaBase._DISPOSITION + "\" TEXT );" ); 

        preloadTables(db);
        createViews(db);
        createTriggers(db);

        } catch (SQLiteException ex) {
           ex.printStackTrace();
        }
   }

   @Override
   public synchronized void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
      logger.warn( "Upgrading database from version {} to {} which will destroy all old data",
             oldVersion, newVersion);
         db.execSQL("DROP TABLE IF EXISTS \"" + Tables.MEDIA_TBL + "\";");
         db.execSQL("DROP TABLE IF EXISTS \"" + Tables.EVENT_TBL + "\";");
         db.execSQL("DROP TABLE IF EXISTS \"" + Tables.STATUS_TBL + "\";");
         db.execSQL("DROP TABLE IF EXISTS \"" + Tables.CATEGORY_TBL + "\";");

      onCreate(db);
   }

   // ===========================================================
   // Database Creation Helper Methods
   // ===========================================================

   /**
    * Can be overriden to cause tables to be loaded
    */
   protected void preloadTables(SQLiteDatabase db) { }

   /** View creation */
   protected void createViews(SQLiteDatabase db) { }

   /** Trigger creation */
   protected void createTriggers(SQLiteDatabase db) { }
}

// ===========================================================
// Constants
// ===========================================================
private final static Logger logger = LoggerFactory.getLogger(IncidentProviderBase.class);

// ===========================================================
// Fields
// ===========================================================
 /** Projection Maps */
protected static String[] mediaProjectionKey;
protected static HashMap<String, String> mediaProjectionMap;

protected static String[] eventProjectionKey;
protected static HashMap<String, String> eventProjectionMap;

protected static String[] statusProjectionKey;
protected static HashMap<String, String> statusProjectionMap;

protected static String[] categoryProjectionKey;
protected static HashMap<String, String> categoryProjectionMap;


/** Uri Matcher tags */
protected static final int MEDIA_BLOB = 10;
protected static final int MEDIA_SET = 11;
protected static final int MEDIA_ID = 12;
protected static final int MEDIA_SERIAL = 13;
protected static final int MEDIA_DESERIAL = 14;
protected static final int MEDIA_META = 15;

private static final MatrixCursor mediaFieldTypeCursor;

protected static final int EVENT_BLOB = 20;
protected static final int EVENT_SET = 21;
protected static final int EVENT_ID = 22;
protected static final int EVENT_SERIAL = 23;
protected static final int EVENT_DESERIAL = 24;
protected static final int EVENT_META = 25;

private static final MatrixCursor eventFieldTypeCursor;

protected static final int STATUS_BLOB = 30;
protected static final int STATUS_SET = 31;
protected static final int STATUS_ID = 32;
protected static final int STATUS_SERIAL = 33;
protected static final int STATUS_DESERIAL = 34;
protected static final int STATUS_META = 35;

private static final MatrixCursor statusFieldTypeCursor;

protected static final int CATEGORY_BLOB = 40;
protected static final int CATEGORY_SET = 41;
protected static final int CATEGORY_ID = 42;
protected static final int CATEGORY_SERIAL = 43;
protected static final int CATEGORY_DESERIAL = 44;
protected static final int CATEGORY_META = 45;

private static final MatrixCursor categoryFieldTypeCursor;


/** Uri matcher */
protected static final UriMatcher uriMatcher;

/** Database helper */
protected IncidentDatabaseHelper openHelper;
protected abstract boolean createDatabaseHelper();

/**
 * In support of cr.openInputStream
 */
private static final UriMatcher blobUriMatcher;
static {
  blobUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
    blobUriMatcher.addURI(IncidentSchemaBase.AUTHORITY, Tables.MEDIA_TBL+"/#/*", MEDIA_BLOB);

    blobUriMatcher.addURI(IncidentSchemaBase.AUTHORITY, Tables.EVENT_TBL+"/#/*", EVENT_BLOB);

    blobUriMatcher.addURI(IncidentSchemaBase.AUTHORITY, Tables.STATUS_TBL+"/#/*", STATUS_BLOB);

    blobUriMatcher.addURI(IncidentSchemaBase.AUTHORITY, Tables.CATEGORY_TBL+"/#/*", CATEGORY_BLOB);

}

/**
 * Examines uri's from clients:
 *  long fkId = cursor.getLong(cursor.getColumnIndex(Table.FK));
 *    Drawable icon = null;
 *    Uri fkUri = ContentUris.withAppendedId(TableSchema.CONTENT_URI, fkId);
 *  // then the fkUri can be used to get a tuple using a query.
 *    Cursor categoryCursor = this.managedQuery(categoryUri, null, null, null, null);
 *  // ...or the fkUri can be used to get a file descriptor.
 *    Uri iconUri = Uri.withAppendedPath(categoryUri, CategoryTableSchema.ICON);
 *  InputStream is = this.getContentResolver().openInputStream(iconUri);
 *  Drawableicon = Drawable.createFromStream(is, null);
 *  
 *  It is expected that the uri passed in will be of the form <content_uri>/<table>/<id>/<column>
 *  This is simple enough that a UriMatcher is not needed and 
 *  a simple uri.getPathSegments will suffice to identify the file.
 */

// ===========================================================
// Content Provider Overrides
// ===========================================================

/**
 * This is used to get fields which are too large to store in the
 * database or would exceed the Binder data size limit of 1MiB.
 * The blob matcher expects a URI post-pended with 
 */

@Override
public synchronized ParcelFileDescriptor openFile (Uri uri, String mode) {
    int imode = 0;
    if (mode.contains("w")) imode |= ParcelFileDescriptor.MODE_WRITE_ONLY;
    if (mode.contains("r")) imode |= ParcelFileDescriptor.MODE_READ_ONLY;
    if (mode.contains("+")) imode |= ParcelFileDescriptor.MODE_APPEND;

    final List<String> pseg = uri.getPathSegments();
    final SQLiteDatabase db = this.openHelper.getReadableDatabase();

    final int match = blobUriMatcher.match(uri);
    switch (match) {
     case MEDIA_BLOB:
        if (pseg.size() < 3)
            return null;

        try {
            final String tuple = pseg.get(1);
            final String field = pseg.get(2);
            logger.trace("open file error tuple=[{}] field=[{}]", tuple, field);
            logger.trace("pseg=[{}]", pseg);
            final String selection = new StringBuilder()
            .append(MediaTableSchemaBase._ID).append("=?")
            .toString();
            final String[] selectArgs = new String[]{tuple}; 

            Cursor blobCursor = null;
            final File filePath;
            try {
               blobCursor = db
                    .query(Tables.MEDIA_TBL, new String[]{field}, 
                            selection, selectArgs, 
                            null, null, null);
               if (blobCursor.getCount() < 1) return null;
               blobCursor.moveToFirst();

               filePath = new File(blobCursor.getString(0));
            } catch (SQLiteException ex) {
               logger.error("files are not implemented for large blobs {}", field);
               return null;

            } finally {
               if (blobCursor != null) blobCursor.close();
            }

            if (0 != (imode & ParcelFileDescriptor.MODE_WRITE_ONLY)) {
               logger.trace("candidate blob file {}", filePath);
               try {
                   final File newFile = receiveFile(Tables.MEDIA_TBL, tuple, filePath);
                   logger.trace("new blob file {}", newFile);

                   final ContentValues cv = new ContentValues();
                   cv.put(field, newFile.getCanonicalPath());
                   db.update(Tables.MEDIA_TBL, cv, selection, selectArgs);

                   return ParcelFileDescriptor.open(newFile, imode | ParcelFileDescriptor.MODE_CREATE);

               } catch (FileNotFoundException ex) {
                   logger.error("could not open file {}\n {}", 
                           ex.getLocalizedMessage(), ex.getStackTrace());
                   return null;
               } catch (IOException ex) {
                   return null;
               }
            }
            return ParcelFileDescriptor.open(filePath, imode);

        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } 
     break;
      

     case EVENT_BLOB:
        if (pseg.size() < 3)
            return null;

        try {
            final String tuple = pseg.get(1);
            final String field = pseg.get(2);
            logger.trace("open file error tuple=[{}] field=[{}]", tuple, field);
            logger.trace("pseg=[{}]", pseg);
            final String selection = new StringBuilder()
            .append(EventTableSchemaBase._ID).append("=?")
            .toString();
            final String[] selectArgs = new String[]{tuple}; 

            Cursor blobCursor = null;
            final File filePath;
            try {
               blobCursor = db
                    .query(Tables.EVENT_TBL, new String[]{field}, 
                            selection, selectArgs, 
                            null, null, null);
               if (blobCursor.getCount() < 1) return null;
               blobCursor.moveToFirst();

               filePath = new File(blobCursor.getString(0));
            } catch (SQLiteException ex) {
               logger.error("files are not implemented for large blobs {}", field);
               return null;

            } finally {
               if (blobCursor != null) blobCursor.close();
            }

            if (0 != (imode & ParcelFileDescriptor.MODE_WRITE_ONLY)) {
               logger.trace("candidate blob file {}", filePath);
               try {
                   final File newFile = receiveFile(Tables.EVENT_TBL, tuple, filePath);
                   logger.trace("new blob file {}", newFile);

                   final ContentValues cv = new ContentValues();
                   cv.put(field, newFile.getCanonicalPath());
                   db.update(Tables.EVENT_TBL, cv, selection, selectArgs);

                   return ParcelFileDescriptor.open(newFile, imode | ParcelFileDescriptor.MODE_CREATE);

               } catch (FileNotFoundException ex) {
                   logger.error("could not open file {}\n {}", 
                           ex.getLocalizedMessage(), ex.getStackTrace());
                   return null;
               } catch (IOException ex) {
                   return null;
               }
            }
            return ParcelFileDescriptor.open(filePath, imode);

        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } 
     break;
      

     case STATUS_BLOB:
        if (pseg.size() < 3)
            return null;

        try {
            final String tuple = pseg.get(1);
            final String field = pseg.get(2);
            logger.trace("open file error tuple=[{}] field=[{}]", tuple, field);
            logger.trace("pseg=[{}]", pseg);
            final String selection = new StringBuilder()
            .append(StatusTableSchemaBase._ID).append("=?")
            .toString();
            final String[] selectArgs = new String[]{tuple}; 

            Cursor blobCursor = null;
            final File filePath;
            try {
               blobCursor = db
                    .query(Tables.STATUS_TBL, new String[]{field}, 
                            selection, selectArgs, 
                            null, null, null);
               if (blobCursor.getCount() < 1) return null;
               blobCursor.moveToFirst();

               filePath = new File(blobCursor.getString(0));
            } catch (SQLiteException ex) {
               logger.error("files are not implemented for large blobs {}", field);
               return null;

            } finally {
               if (blobCursor != null) blobCursor.close();
            }

            if (0 != (imode & ParcelFileDescriptor.MODE_WRITE_ONLY)) {
               logger.trace("candidate blob file {}", filePath);
               try {
                   final File newFile = receiveFile(Tables.STATUS_TBL, tuple, filePath);
                   logger.trace("new blob file {}", newFile);

                   final ContentValues cv = new ContentValues();
                   cv.put(field, newFile.getCanonicalPath());
                   db.update(Tables.STATUS_TBL, cv, selection, selectArgs);

                   return ParcelFileDescriptor.open(newFile, imode | ParcelFileDescriptor.MODE_CREATE);

               } catch (FileNotFoundException ex) {
                   logger.error("could not open file {}\n {}", 
                           ex.getLocalizedMessage(), ex.getStackTrace());
                   return null;
               } catch (IOException ex) {
                   return null;
               }
            }
            return ParcelFileDescriptor.open(filePath, imode);

        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } 
     break;
      

     case CATEGORY_BLOB:
        if (pseg.size() < 3)
            return null;

        try {
            final String tuple = pseg.get(1);
            final String field = pseg.get(2);
            logger.trace("open file error tuple=[{}] field=[{}]", tuple, field);
            logger.trace("pseg=[{}]", pseg);
            final String selection = new StringBuilder()
            .append(CategoryTableSchemaBase._ID).append("=?")
            .toString();
            final String[] selectArgs = new String[]{tuple}; 

            Cursor blobCursor = null;
            final File filePath;
            try {
               blobCursor = db
                    .query(Tables.CATEGORY_TBL, new String[]{field}, 
                            selection, selectArgs, 
                            null, null, null);
               if (blobCursor.getCount() < 1) return null;
               blobCursor.moveToFirst();

               filePath = new File(blobCursor.getString(0));
            } catch (SQLiteException ex) {
               logger.error("files are not implemented for large blobs {}", field);
               return null;

            } finally {
               if (blobCursor != null) blobCursor.close();
            }

            if (0 != (imode & ParcelFileDescriptor.MODE_WRITE_ONLY)) {
               logger.trace("candidate blob file {}", filePath);
               try {
                   final File newFile = receiveFile(Tables.CATEGORY_TBL, tuple, filePath);
                   logger.trace("new blob file {}", newFile);

                   final ContentValues cv = new ContentValues();
                   cv.put(field, newFile.getCanonicalPath());
                   db.update(Tables.CATEGORY_TBL, cv, selection, selectArgs);

                   return ParcelFileDescriptor.open(newFile, imode | ParcelFileDescriptor.MODE_CREATE);

               } catch (FileNotFoundException ex) {
                   logger.error("could not open file {}\n {}", 
                           ex.getLocalizedMessage(), ex.getStackTrace());
                   return null;
               } catch (IOException ex) {
                   return null;
               }
            }
            return ParcelFileDescriptor.open(filePath, imode);

        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } 
     break;
      
    default:
        throw new IllegalArgumentException("Unknown URI " + uri);
    }
    return null;
}

@Override
public synchronized boolean onCreate() {
   this.createDatabaseHelper();
   return true;
}

@Override
public synchronized Cursor query(Uri uri, String[] projection, String selection,
      String[] selectionArgs, String sortOrder) {
   final SQLiteQueryBuilder qb = new SQLiteQueryBuilder();
   final SQLiteDatabase db = this.openHelper.getReadableDatabase();

   // Switch on the path in the uri for what we want to query.
   final SQLiteCursor cursor;
   final List<String> psegs = uri.getPathSegments();

   switch (uriMatcher.match(uri)) {
   case MEDIA_META:
      logger.debug("meta","provide MEDIA meta data {}",uri);
      return mediaFieldTypeCursor;

   case MEDIA_SET:
      qb.setTables(Tables.MEDIA_TBL);
      qb.setProjectionMap(mediaProjectionMap);

      cursor = (SQLiteCursor) qb.query(db, projection, selection, selectionArgs, null, null, 
     (! TextUtils.isEmpty(sortOrder)) ? sortOrder
     : MediaTableSchemaBase.DEFAULT_SORT_ORDER);

      cursor.setNotificationUri(getContext().getContentResolver(), uri);
      return cursor;

   case MEDIA_ID:
      qb.setTables(Tables.MEDIA_TBL);
      qb.setProjectionMap(mediaProjectionMap);
      qb.appendWhere(MediaTableSchemaBase._ID + "="
        + uri.getPathSegments().get(1));

      cursor = (SQLiteCursor) qb.query(db, projection, selection, selectionArgs, null, null, 
        (! TextUtils.isEmpty(sortOrder)) ? sortOrder
     : MediaTableSchemaBase.DEFAULT_SORT_ORDER);

      cursor.setNotificationUri(getContext().getContentResolver(), uri);
      return cursor;

   case MEDIA_SERIAL:
   {
      qb.setTables(Tables.MEDIA_TBL);
      qb.setProjectionMap(mediaProjectionMap);

      qb.appendWhere(MediaTableSchemaBase._ID + " = " + uri.getPathSegments().get(1));

      final List<String> projectionList = new ArrayList<String>();
      for (final Meta columnMeta : IncidentSchemaBase.MEDIA_CURSOR_COLUMNS) {
          switch (columnMeta.type) {
          case IncidentSchemaBase.FIELD_TYPE_BLOB: break;
          default: 
              projectionList.add(columnMeta.name);
          }
      }
      final String[] projectionArray = projectionList.toArray(new String[projectionList.size()]);
      cursor = (SQLiteCursor) qb.query(db, projectionArray, null, null, null, null, null);
      if (1 > cursor.getCount()) {
       logger.info("no data of type MEDIA_ID"); 
       cursor.close();
       return null;
      }
      if (psegs.size() < 2) {
          return cursor;
      }
      return this.customQueryMediaTableSchema(psegs, cursor);
   }
   case MEDIA_BLOB:
   {
      final List<String> fieldNameList = new ArrayList<String>();

      for (Meta columnMeta : IncidentSchemaBase.MEDIA_CURSOR_COLUMNS) {
          switch (columnMeta.type) {
          case IncidentSchemaBase.FIELD_TYPE_BLOB:
          case IncidentSchemaBase.FIELD_TYPE_FILE:
              fieldNameList.add(columnMeta.name);
              break;
          default:
          }
      }
      if (fieldNameList.size() < 1) return null;

      final String[] fieldNameArray = fieldNameList.toArray(new String[fieldNameList.size()]);
      final String bselect = MediaTableSchemaBase._ID + "=?";
      final String[] bselectArgs = new String[]{ uri.getPathSegments().get(1) };
      return db.query(Tables.MEDIA_TBL, fieldNameArray, 
                 bselect, bselectArgs, null, null, null);
   }

   case EVENT_META:
      logger.debug("meta","provide EVENT meta data {}",uri);
      return eventFieldTypeCursor;

   case EVENT_SET:
      qb.setTables(Tables.EVENT_TBL);
      qb.setProjectionMap(eventProjectionMap);

      cursor = (SQLiteCursor) qb.query(db, projection, selection, selectionArgs, null, null, 
     (! TextUtils.isEmpty(sortOrder)) ? sortOrder
     : EventTableSchemaBase.DEFAULT_SORT_ORDER);

      cursor.setNotificationUri(getContext().getContentResolver(), uri);
      return cursor;

   case EVENT_ID:
      qb.setTables(Tables.EVENT_TBL);
      qb.setProjectionMap(eventProjectionMap);
      qb.appendWhere(EventTableSchemaBase._ID + "="
        + uri.getPathSegments().get(1));

      cursor = (SQLiteCursor) qb.query(db, projection, selection, selectionArgs, null, null, 
        (! TextUtils.isEmpty(sortOrder)) ? sortOrder
     : EventTableSchemaBase.DEFAULT_SORT_ORDER);

      cursor.setNotificationUri(getContext().getContentResolver(), uri);
      return cursor;

   case EVENT_SERIAL:
   {
      qb.setTables(Tables.EVENT_TBL);
      qb.setProjectionMap(eventProjectionMap);

      qb.appendWhere(EventTableSchemaBase._ID + " = " + uri.getPathSegments().get(1));

      final List<String> projectionList = new ArrayList<String>();
      for (final Meta columnMeta : IncidentSchemaBase.EVENT_CURSOR_COLUMNS) {
          switch (columnMeta.type) {
          case IncidentSchemaBase.FIELD_TYPE_BLOB: break;
          default: 
              projectionList.add(columnMeta.name);
          }
      }
      final String[] projectionArray = projectionList.toArray(new String[projectionList.size()]);
      cursor = (SQLiteCursor) qb.query(db, projectionArray, null, null, null, null, null);
      if (1 > cursor.getCount()) {
       logger.info("no data of type EVENT_ID"); 
       cursor.close();
       return null;
      }
      if (psegs.size() < 2) {
          return cursor;
      }
      return this.customQueryEventTableSchema(psegs, cursor);
   }
   case EVENT_BLOB:
   {
      final List<String> fieldNameList = new ArrayList<String>();

      for (Meta columnMeta : IncidentSchemaBase.EVENT_CURSOR_COLUMNS) {
          switch (columnMeta.type) {
          case IncidentSchemaBase.FIELD_TYPE_BLOB:
          case IncidentSchemaBase.FIELD_TYPE_FILE:
              fieldNameList.add(columnMeta.name);
              break;
          default:
          }
      }
      if (fieldNameList.size() < 1) return null;

      final String[] fieldNameArray = fieldNameList.toArray(new String[fieldNameList.size()]);
      final String bselect = EventTableSchemaBase._ID + "=?";
      final String[] bselectArgs = new String[]{ uri.getPathSegments().get(1) };
      return db.query(Tables.EVENT_TBL, fieldNameArray, 
                 bselect, bselectArgs, null, null, null);
   }

   case STATUS_META:
      logger.debug("meta","provide STATUS meta data {}",uri);
      return statusFieldTypeCursor;

   case STATUS_SET:
      qb.setTables(Tables.STATUS_TBL);
      qb.setProjectionMap(statusProjectionMap);

      cursor = (SQLiteCursor) qb.query(db, projection, selection, selectionArgs, null, null, 
     (! TextUtils.isEmpty(sortOrder)) ? sortOrder
     : StatusTableSchemaBase.DEFAULT_SORT_ORDER);

      cursor.setNotificationUri(getContext().getContentResolver(), uri);
      return cursor;

   case STATUS_ID:
      qb.setTables(Tables.STATUS_TBL);
      qb.setProjectionMap(statusProjectionMap);
      qb.appendWhere(StatusTableSchemaBase._ID + "="
        + uri.getPathSegments().get(1));

      cursor = (SQLiteCursor) qb.query(db, projection, selection, selectionArgs, null, null, 
        (! TextUtils.isEmpty(sortOrder)) ? sortOrder
     : StatusTableSchemaBase.DEFAULT_SORT_ORDER);

      cursor.setNotificationUri(getContext().getContentResolver(), uri);
      return cursor;

   case STATUS_SERIAL:
   {
      qb.setTables(Tables.STATUS_TBL);
      qb.setProjectionMap(statusProjectionMap);

      qb.appendWhere(StatusTableSchemaBase._ID + " = " + uri.getPathSegments().get(1));

      final List<String> projectionList = new ArrayList<String>();
      for (final Meta columnMeta : IncidentSchemaBase.STATUS_CURSOR_COLUMNS) {
          switch (columnMeta.type) {
          case IncidentSchemaBase.FIELD_TYPE_BLOB: break;
          default: 
              projectionList.add(columnMeta.name);
          }
      }
      final String[] projectionArray = projectionList.toArray(new String[projectionList.size()]);
      cursor = (SQLiteCursor) qb.query(db, projectionArray, null, null, null, null, null);
      if (1 > cursor.getCount()) {
       logger.info("no data of type STATUS_ID"); 
       cursor.close();
       return null;
      }
      if (psegs.size() < 2) {
          return cursor;
      }
      return this.customQueryStatusTableSchema(psegs, cursor);
   }
   case STATUS_BLOB:
   {
      final List<String> fieldNameList = new ArrayList<String>();

      for (Meta columnMeta : IncidentSchemaBase.STATUS_CURSOR_COLUMNS) {
          switch (columnMeta.type) {
          case IncidentSchemaBase.FIELD_TYPE_BLOB:
          case IncidentSchemaBase.FIELD_TYPE_FILE:
              fieldNameList.add(columnMeta.name);
              break;
          default:
          }
      }
      if (fieldNameList.size() < 1) return null;

      final String[] fieldNameArray = fieldNameList.toArray(new String[fieldNameList.size()]);
      final String bselect = StatusTableSchemaBase._ID + "=?";
      final String[] bselectArgs = new String[]{ uri.getPathSegments().get(1) };
      return db.query(Tables.STATUS_TBL, fieldNameArray, 
                 bselect, bselectArgs, null, null, null);
   }

   case CATEGORY_META:
      logger.debug("meta","provide CATEGORY meta data {}",uri);
      return categoryFieldTypeCursor;

   case CATEGORY_SET:
      qb.setTables(Tables.CATEGORY_TBL);
      qb.setProjectionMap(categoryProjectionMap);

      cursor = (SQLiteCursor) qb.query(db, projection, selection, selectionArgs, null, null, 
     (! TextUtils.isEmpty(sortOrder)) ? sortOrder
     : CategoryTableSchemaBase.DEFAULT_SORT_ORDER);

      cursor.setNotificationUri(getContext().getContentResolver(), uri);
      return cursor;

   case CATEGORY_ID:
      qb.setTables(Tables.CATEGORY_TBL);
      qb.setProjectionMap(categoryProjectionMap);
      qb.appendWhere(CategoryTableSchemaBase._ID + "="
        + uri.getPathSegments().get(1));

      cursor = (SQLiteCursor) qb.query(db, projection, selection, selectionArgs, null, null, 
        (! TextUtils.isEmpty(sortOrder)) ? sortOrder
     : CategoryTableSchemaBase.DEFAULT_SORT_ORDER);

      cursor.setNotificationUri(getContext().getContentResolver(), uri);
      return cursor;

   case CATEGORY_SERIAL:
   {
      qb.setTables(Tables.CATEGORY_TBL);
      qb.setProjectionMap(categoryProjectionMap);

      qb.appendWhere(CategoryTableSchemaBase._ID + " = " + uri.getPathSegments().get(1));

      final List<String> projectionList = new ArrayList<String>();
      for (final Meta columnMeta : IncidentSchemaBase.CATEGORY_CURSOR_COLUMNS) {
          switch (columnMeta.type) {
          case IncidentSchemaBase.FIELD_TYPE_BLOB: break;
          default: 
              projectionList.add(columnMeta.name);
          }
      }
      final String[] projectionArray = projectionList.toArray(new String[projectionList.size()]);
      cursor = (SQLiteCursor) qb.query(db, projectionArray, null, null, null, null, null);
      if (1 > cursor.getCount()) {
       logger.info("no data of type CATEGORY_ID"); 
       cursor.close();
       return null;
      }
      if (psegs.size() < 2) {
          return cursor;
      }
      return this.customQueryCategoryTableSchema(psegs, cursor);
   }
   case CATEGORY_BLOB:
   {
      final List<String> fieldNameList = new ArrayList<String>();

      for (Meta columnMeta : IncidentSchemaBase.CATEGORY_CURSOR_COLUMNS) {
          switch (columnMeta.type) {
          case IncidentSchemaBase.FIELD_TYPE_BLOB:
          case IncidentSchemaBase.FIELD_TYPE_FILE:
              fieldNameList.add(columnMeta.name);
              break;
          default:
          }
      }
      if (fieldNameList.size() < 1) return null;

      final String[] fieldNameArray = fieldNameList.toArray(new String[fieldNameList.size()]);
      final String bselect = CategoryTableSchemaBase._ID + "=?";
      final String[] bselectArgs = new String[]{ uri.getPathSegments().get(1) };
      return db.query(Tables.CATEGORY_TBL, fieldNameArray, 
                 bselect, bselectArgs, null, null, null);
   }

   default: 
      throw new IllegalArgumentException("Unknown URI " + uri);
   }
}

private Cursor customQueryMediaTableSchema(final List<String> psegs, final SQLiteCursor cursor) {
    logger.info("no custom cursor MediaTableSchema {}", psegs); 
    return cursor;
} 
private Cursor customQueryEventTableSchema(final List<String> psegs, final SQLiteCursor cursor) {
    logger.info("no custom cursor EventTableSchema {}", psegs); 
    return cursor;
} 
private Cursor customQueryStatusTableSchema(final List<String> psegs, final SQLiteCursor cursor) {
    logger.info("no custom cursor StatusTableSchema {}", psegs); 
    return cursor;
} 
private Cursor customQueryCategoryTableSchema(final List<String> psegs, final SQLiteCursor cursor) {
    logger.info("no custom cursor CategoryTableSchema {}", psegs); 
    return cursor;
} 

@Override
public synchronized Uri insert(Uri uri, ContentValues assignedValues) {
   /** Validate the requested uri and do default initialization. */
   switch (uriMatcher.match(uri)) {
   case MEDIA_SET:
      try {
         final ContentValues values = this.initializeMediaWithDefaults(assignedValues);
         if ( IncidentSchemaBase.MEDIA_KEY_COLUMNS.length < 1 ) {
            final SQLiteDatabase db = openHelper.getWritableDatabase();
            final long rowID = db.insert(Tables.MEDIA_TBL, MediaTableSchemaBase.EVENT_ID, values);
            if (rowID < 1) {
               throw new SQLiteException("Failed to insert row into " + uri);
            }
            final Uri playerURI = ContentUris.withAppendedId(MediaTableSchemaBase.CONTENT_URI, rowID);
            getContext().getContentResolver().notifyChange(uri, null);
            return playerURI;
         }

         final List<String> selectArgsList = new ArrayList<String>();
         for (String item : IncidentSchemaBase.MEDIA_KEY_COLUMNS) {
            selectArgsList.add(values.getAsString(item));
         } 
         final String[] selectArgs = selectArgsList.toArray(new String[0]);
         final SQLiteDatabase db = openHelper.getWritableDatabase();

         final long rowID;
         final int count = db.update(Tables.MEDIA_TBL, values, 
               IncidentProviderBase.MEDIA_KEY_CLAUSE, selectArgs);
         if ( count < 1 ) {
            rowID = db.insert(Tables.MEDIA_TBL, MediaTableSchemaBase.EVENT_ID, values);
            if (rowID < 1) {
               throw new SQLiteException("Failed to insert row into " + uri);
            }
         }
         else {
            final Cursor cursor = db.query(Tables.MEDIA_TBL, null, 
                IncidentProviderBase.MEDIA_KEY_CLAUSE, selectArgs, 
                null, null, null);
            cursor.moveToFirst();
            rowID = cursor.getInt(cursor.getColumnIndex(BaseColumns._ID));
            cursor.close();
         }
         final Uri playerURI = ContentUris.withAppendedId(MediaTableSchemaBase.CONTENT_URI, rowID);
         getContext().getContentResolver().notifyChange(uri, null);
         return playerURI;
      } catch (SQLiteException ex) {
         logger.warn("bad column set {}", ex.getLocalizedMessage());
      }
   return null;

   /**
     Receive messages from the distributor and deserialize into the content provider.
   */
   case MEDIA_DESERIAL:
      try {
         final ContentValues values = this.initializeMediaWithDefaults(assignedValues);
         final String json = assignedValues.getAsString("_serial");
         final JSONObject input = (JSONObject) new JSONTokener(json).nextValue();

         final ContentValues cv = new ContentValues();
         for (int ix=0; ix <  IncidentSchemaBase.MEDIA_CURSOR_COLUMNS.length; ix++) {
             switch (IncidentSchemaBase.MEDIA_CURSOR_COLUMNS[ix].type) {
             case IncidentSchemaBase.FIELD_TYPE_BLOB:
               continue;
             }
             final String key = IncidentSchemaBase.MEDIA_CURSOR_COLUMNS[ix].name;
             try {
               final String value = input.getString(key);
               cv.put(key, value);
             } catch (JSONException ex) {
               logger.error("could not extract from json {} {}", 
                   ex.getLocalizedMessage(), ex.getStackTrace());
             }
         }

         final List<String> selectArgsList = new ArrayList<String>();
         for (String item : IncidentSchemaBase.MEDIA_KEY_COLUMNS) {
            selectArgsList.add(values.getAsString(item));
         } 
         final String[] selectArgs = selectArgsList.toArray(new String[0]);
         final SQLiteDatabase db = this.openHelper.getWritableDatabase();
         final long rowID;
         final int count = db.update(Tables.MEDIA_TBL, values, 
                 IncidentProviderBase.MEDIA_KEY_CLAUSE, selectArgs);
         if ( count < 1 ) {
            rowID = db.insert(Tables.MEDIA_TBL, MediaTableSchemaBase.EVENT_ID, values);
            if (rowID < 1) {
               throw new SQLiteException("Failed to insert row into " + uri);
            }
         }
         else {
            final Cursor cursor = db.query(Tables.MEDIA_TBL, null, 
                 IncidentProviderBase.MEDIA_KEY_CLAUSE, selectArgs, 
                 null, null, null);
            cursor.moveToFirst();
            rowID = cursor.getInt(cursor.getColumnIndex(BaseColumns._ID));
            cursor.close();
         }
         final Uri playerURI = ContentUris.withAppendedId(MediaTableSchemaBase.CONTENT_URI, rowID);
         getContext().getContentResolver().notifyChange(uri, null);
         return playerURI;
      } catch (JSONException ex) {
         logger.error("could not parse json {} {}", 
              ex.getLocalizedMessage(), ex.getStackTrace());
      }
      return null;

   case EVENT_SET:
      try {
         final ContentValues values = this.initializeEventWithDefaults(assignedValues);
         if ( IncidentSchemaBase.EVENT_KEY_COLUMNS.length < 1 ) {
            final SQLiteDatabase db = openHelper.getWritableDatabase();
            final long rowID = db.insert(Tables.EVENT_TBL, EventTableSchemaBase.UUID, values);
            if (rowID < 1) {
               throw new SQLiteException("Failed to insert row into " + uri);
            }
            final Uri playerURI = ContentUris.withAppendedId(EventTableSchemaBase.CONTENT_URI, rowID);
            getContext().getContentResolver().notifyChange(uri, null);
            return playerURI;
         }

         final List<String> selectArgsList = new ArrayList<String>();
         for (String item : IncidentSchemaBase.EVENT_KEY_COLUMNS) {
            selectArgsList.add(values.getAsString(item));
         } 
         final String[] selectArgs = selectArgsList.toArray(new String[0]);
         final SQLiteDatabase db = openHelper.getWritableDatabase();

         final long rowID;
         final int count = db.update(Tables.EVENT_TBL, values, 
               IncidentProviderBase.EVENT_KEY_CLAUSE, selectArgs);
         if ( count < 1 ) {
            rowID = db.insert(Tables.EVENT_TBL, EventTableSchemaBase.UUID, values);
            if (rowID < 1) {
               throw new SQLiteException("Failed to insert row into " + uri);
            }
         }
         else {
            final Cursor cursor = db.query(Tables.EVENT_TBL, null, 
                IncidentProviderBase.EVENT_KEY_CLAUSE, selectArgs, 
                null, null, null);
            cursor.moveToFirst();
            rowID = cursor.getInt(cursor.getColumnIndex(BaseColumns._ID));
            cursor.close();
         }
         final Uri playerURI = ContentUris.withAppendedId(EventTableSchemaBase.CONTENT_URI, rowID);
         getContext().getContentResolver().notifyChange(uri, null);
         return playerURI;
      } catch (SQLiteException ex) {
         logger.warn("bad column set {}", ex.getLocalizedMessage());
      }
   return null;

   /**
     Receive messages from the distributor and deserialize into the content provider.
   */
   case EVENT_DESERIAL:
      try {
         final ContentValues values = this.initializeEventWithDefaults(assignedValues);
         final String json = assignedValues.getAsString("_serial");
         final JSONObject input = (JSONObject) new JSONTokener(json).nextValue();

         final ContentValues cv = new ContentValues();
         for (int ix=0; ix <  IncidentSchemaBase.EVENT_CURSOR_COLUMNS.length; ix++) {
             switch (IncidentSchemaBase.EVENT_CURSOR_COLUMNS[ix].type) {
             case IncidentSchemaBase.FIELD_TYPE_BLOB:
               continue;
             }
             final String key = IncidentSchemaBase.EVENT_CURSOR_COLUMNS[ix].name;
             try {
               final String value = input.getString(key);
               cv.put(key, value);
             } catch (JSONException ex) {
               logger.error("could not extract from json {} {}", 
                   ex.getLocalizedMessage(), ex.getStackTrace());
             }
         }

         final List<String> selectArgsList = new ArrayList<String>();
         for (String item : IncidentSchemaBase.EVENT_KEY_COLUMNS) {
            selectArgsList.add(values.getAsString(item));
         } 
         final String[] selectArgs = selectArgsList.toArray(new String[0]);
         final SQLiteDatabase db = this.openHelper.getWritableDatabase();
         final long rowID;
         final int count = db.update(Tables.EVENT_TBL, values, 
                 IncidentProviderBase.EVENT_KEY_CLAUSE, selectArgs);
         if ( count < 1 ) {
            rowID = db.insert(Tables.EVENT_TBL, EventTableSchemaBase.UUID, values);
            if (rowID < 1) {
               throw new SQLiteException("Failed to insert row into " + uri);
            }
         }
         else {
            final Cursor cursor = db.query(Tables.EVENT_TBL, null, 
                 IncidentProviderBase.EVENT_KEY_CLAUSE, selectArgs, 
                 null, null, null);
            cursor.moveToFirst();
            rowID = cursor.getInt(cursor.getColumnIndex(BaseColumns._ID));
            cursor.close();
         }
         final Uri playerURI = ContentUris.withAppendedId(EventTableSchemaBase.CONTENT_URI, rowID);
         getContext().getContentResolver().notifyChange(uri, null);
         return playerURI;
      } catch (JSONException ex) {
         logger.error("could not parse json {} {}", 
              ex.getLocalizedMessage(), ex.getStackTrace());
      }
      return null;

   case STATUS_SET:
      try {
         final ContentValues values = this.initializeStatusWithDefaults(assignedValues);
         if ( IncidentSchemaBase.STATUS_KEY_COLUMNS.length < 1 ) {
            final SQLiteDatabase db = openHelper.getWritableDatabase();
            final long rowID = db.insert(Tables.STATUS_TBL, StatusTableSchemaBase.EVENT_UUID, values);
            if (rowID < 1) {
               throw new SQLiteException("Failed to insert row into " + uri);
            }
            final Uri playerURI = ContentUris.withAppendedId(StatusTableSchemaBase.CONTENT_URI, rowID);
            getContext().getContentResolver().notifyChange(uri, null);
            return playerURI;
         }

         final List<String> selectArgsList = new ArrayList<String>();
         for (String item : IncidentSchemaBase.STATUS_KEY_COLUMNS) {
            selectArgsList.add(values.getAsString(item));
         } 
         final String[] selectArgs = selectArgsList.toArray(new String[0]);
         final SQLiteDatabase db = openHelper.getWritableDatabase();

         final long rowID;
         final int count = db.update(Tables.STATUS_TBL, values, 
               IncidentProviderBase.STATUS_KEY_CLAUSE, selectArgs);
         if ( count < 1 ) {
            rowID = db.insert(Tables.STATUS_TBL, StatusTableSchemaBase.EVENT_UUID, values);
            if (rowID < 1) {
               throw new SQLiteException("Failed to insert row into " + uri);
            }
         }
         else {
            final Cursor cursor = db.query(Tables.STATUS_TBL, null, 
                IncidentProviderBase.STATUS_KEY_CLAUSE, selectArgs, 
                null, null, null);
            cursor.moveToFirst();
            rowID = cursor.getInt(cursor.getColumnIndex(BaseColumns._ID));
            cursor.close();
         }
         final Uri playerURI = ContentUris.withAppendedId(StatusTableSchemaBase.CONTENT_URI, rowID);
         getContext().getContentResolver().notifyChange(uri, null);
         return playerURI;
      } catch (SQLiteException ex) {
         logger.warn("bad column set {}", ex.getLocalizedMessage());
      }
   return null;

   /**
     Receive messages from the distributor and deserialize into the content provider.
   */
   case STATUS_DESERIAL:
      try {
         final ContentValues values = this.initializeStatusWithDefaults(assignedValues);
         final String json = assignedValues.getAsString("_serial");
         final JSONObject input = (JSONObject) new JSONTokener(json).nextValue();

         final ContentValues cv = new ContentValues();
         for (int ix=0; ix <  IncidentSchemaBase.STATUS_CURSOR_COLUMNS.length; ix++) {
             switch (IncidentSchemaBase.STATUS_CURSOR_COLUMNS[ix].type) {
             case IncidentSchemaBase.FIELD_TYPE_BLOB:
               continue;
             }
             final String key = IncidentSchemaBase.STATUS_CURSOR_COLUMNS[ix].name;
             try {
               final String value = input.getString(key);
               cv.put(key, value);
             } catch (JSONException ex) {
               logger.error("could not extract from json {} {}", 
                   ex.getLocalizedMessage(), ex.getStackTrace());
             }
         }

         final List<String> selectArgsList = new ArrayList<String>();
         for (String item : IncidentSchemaBase.STATUS_KEY_COLUMNS) {
            selectArgsList.add(values.getAsString(item));
         } 
         final String[] selectArgs = selectArgsList.toArray(new String[0]);
         final SQLiteDatabase db = this.openHelper.getWritableDatabase();
         final long rowID;
         final int count = db.update(Tables.STATUS_TBL, values, 
                 IncidentProviderBase.STATUS_KEY_CLAUSE, selectArgs);
         if ( count < 1 ) {
            rowID = db.insert(Tables.STATUS_TBL, StatusTableSchemaBase.EVENT_UUID, values);
            if (rowID < 1) {
               throw new SQLiteException("Failed to insert row into " + uri);
            }
         }
         else {
            final Cursor cursor = db.query(Tables.STATUS_TBL, null, 
                 IncidentProviderBase.STATUS_KEY_CLAUSE, selectArgs, 
                 null, null, null);
            cursor.moveToFirst();
            rowID = cursor.getInt(cursor.getColumnIndex(BaseColumns._ID));
            cursor.close();
         }
         final Uri playerURI = ContentUris.withAppendedId(StatusTableSchemaBase.CONTENT_URI, rowID);
         getContext().getContentResolver().notifyChange(uri, null);
         return playerURI;
      } catch (JSONException ex) {
         logger.error("could not parse json {} {}", 
              ex.getLocalizedMessage(), ex.getStackTrace());
      }
      return null;

   case CATEGORY_SET:
      try {
         final ContentValues values = this.initializeCategoryWithDefaults(assignedValues);
         if ( IncidentSchemaBase.CATEGORY_KEY_COLUMNS.length < 1 ) {
            final SQLiteDatabase db = openHelper.getWritableDatabase();
            final long rowID = db.insert(Tables.CATEGORY_TBL, CategoryTableSchemaBase.MAIN_CATEGORY, values);
            if (rowID < 1) {
               throw new SQLiteException("Failed to insert row into " + uri);
            }
            final Uri playerURI = ContentUris.withAppendedId(CategoryTableSchemaBase.CONTENT_URI, rowID);
            getContext().getContentResolver().notifyChange(uri, null);
            return playerURI;
         }

         final List<String> selectArgsList = new ArrayList<String>();
         for (String item : IncidentSchemaBase.CATEGORY_KEY_COLUMNS) {
            selectArgsList.add(values.getAsString(item));
         } 
         final String[] selectArgs = selectArgsList.toArray(new String[0]);
         final SQLiteDatabase db = openHelper.getWritableDatabase();

         final long rowID;
         final int count = db.update(Tables.CATEGORY_TBL, values, 
               IncidentProviderBase.CATEGORY_KEY_CLAUSE, selectArgs);
         if ( count < 1 ) {
            rowID = db.insert(Tables.CATEGORY_TBL, CategoryTableSchemaBase.MAIN_CATEGORY, values);
            if (rowID < 1) {
               throw new SQLiteException("Failed to insert row into " + uri);
            }
         }
         else {
            final Cursor cursor = db.query(Tables.CATEGORY_TBL, null, 
                IncidentProviderBase.CATEGORY_KEY_CLAUSE, selectArgs, 
                null, null, null);
            cursor.moveToFirst();
            rowID = cursor.getInt(cursor.getColumnIndex(BaseColumns._ID));
            cursor.close();
         }
         final Uri playerURI = ContentUris.withAppendedId(CategoryTableSchemaBase.CONTENT_URI, rowID);
         getContext().getContentResolver().notifyChange(uri, null);
         return playerURI;
      } catch (SQLiteException ex) {
         logger.warn("bad column set {}", ex.getLocalizedMessage());
      }
   return null;

   /**
     Receive messages from the distributor and deserialize into the content provider.
   */
   case CATEGORY_DESERIAL:
      try {
         final ContentValues values = this.initializeCategoryWithDefaults(assignedValues);
         final String json = assignedValues.getAsString("_serial");
         final JSONObject input = (JSONObject) new JSONTokener(json).nextValue();

         final ContentValues cv = new ContentValues();
         for (int ix=0; ix <  IncidentSchemaBase.CATEGORY_CURSOR_COLUMNS.length; ix++) {
             switch (IncidentSchemaBase.CATEGORY_CURSOR_COLUMNS[ix].type) {
             case IncidentSchemaBase.FIELD_TYPE_BLOB:
               continue;
             }
             final String key = IncidentSchemaBase.CATEGORY_CURSOR_COLUMNS[ix].name;
             try {
               final String value = input.getString(key);
               cv.put(key, value);
             } catch (JSONException ex) {
               logger.error("could not extract from json {} {}", 
                   ex.getLocalizedMessage(), ex.getStackTrace());
             }
         }

         final List<String> selectArgsList = new ArrayList<String>();
         for (String item : IncidentSchemaBase.CATEGORY_KEY_COLUMNS) {
            selectArgsList.add(values.getAsString(item));
         } 
         final String[] selectArgs = selectArgsList.toArray(new String[0]);
         final SQLiteDatabase db = this.openHelper.getWritableDatabase();
         final long rowID;
         final int count = db.update(Tables.CATEGORY_TBL, values, 
                 IncidentProviderBase.CATEGORY_KEY_CLAUSE, selectArgs);
         if ( count < 1 ) {
            rowID = db.insert(Tables.CATEGORY_TBL, CategoryTableSchemaBase.MAIN_CATEGORY, values);
            if (rowID < 1) {
               throw new SQLiteException("Failed to insert row into " + uri);
            }
         }
         else {
            final Cursor cursor = db.query(Tables.CATEGORY_TBL, null, 
                 IncidentProviderBase.CATEGORY_KEY_CLAUSE, selectArgs, 
                 null, null, null);
            cursor.moveToFirst();
            rowID = cursor.getInt(cursor.getColumnIndex(BaseColumns._ID));
            cursor.close();
         }
         final Uri playerURI = ContentUris.withAppendedId(CategoryTableSchemaBase.CONTENT_URI, rowID);
         getContext().getContentResolver().notifyChange(uri, null);
         return playerURI;
      } catch (JSONException ex) {
         logger.error("could not parse json {} {}", 
              ex.getLocalizedMessage(), ex.getStackTrace());
      }
      return null;


   default:
      throw new IllegalArgumentException("Unknown URI " + uri);
   }
}

/** Insert method helper */
protected ContentValues initializeMediaWithDefaults(ContentValues assignedValues) {
   final Long now = Long.valueOf(System.currentTimeMillis());
   final ContentValues values = (assignedValues == null) 
      ? new ContentValues() : assignedValues;

     if (!values.containsKey(MediaTableSchemaBase.EVENT_ID)) {
        values.put(MediaTableSchemaBase.EVENT_ID, "");
     }  
     if (!values.containsKey(MediaTableSchemaBase.DATA_TYPE)) {
        values.put(MediaTableSchemaBase.DATA_TYPE, "text/plain");
     }
       if (!values.containsKey(MediaTableSchemaBase.DATA)) {
          values.put(MediaTableSchemaBase.DATA, "");
       }  
     if (!values.containsKey(MediaTableSchemaBase.CREATED_DATE)) {
        values.put(MediaTableSchemaBase.CREATED_DATE, now);
     }  
     if (!values.containsKey(MediaTableSchemaBase.MODIFIED_DATE)) {
        values.put(MediaTableSchemaBase.MODIFIED_DATE, now);
     }  
     if (!values.containsKey(MediaTableSchemaBase._RECEIVED_DATE)) {
         values.put(MediaTableSchemaBase._RECEIVED_DATE, now);
     }
     if (!values.containsKey(MediaTableSchemaBase._DISPOSITION)) {
        values.put(MediaTableSchemaBase._DISPOSITION, IncidentSchemaBase.Disposition.LOCAL.name());
     }
   return values;
} 
/** Insert method helper */
protected ContentValues initializeEventWithDefaults(ContentValues assignedValues) {
   final Long now = Long.valueOf(System.currentTimeMillis());
   final ContentValues values = (assignedValues == null) 
      ? new ContentValues() : assignedValues;

     if (!values.containsKey(EventTableSchemaBase.UUID)) {
        values.put(EventTableSchemaBase.UUID, "");
     }  
     if (!values.containsKey(EventTableSchemaBase.MEDIA_COUNT)) {
        values.put(EventTableSchemaBase.MEDIA_COUNT, 0);
     }  
     if (!values.containsKey(EventTableSchemaBase.ORIGINATOR)) {
        values.put(EventTableSchemaBase.ORIGINATOR, "");
     }  
     if (!values.containsKey(EventTableSchemaBase.DISPLAY_NAME)) {
        values.put(EventTableSchemaBase.DISPLAY_NAME, "");
     }  
     if (!values.containsKey(EventTableSchemaBase.CATEGORY_ID)) {
        values.put(EventTableSchemaBase.CATEGORY_ID, "");
     }  
     if (!values.containsKey(EventTableSchemaBase.TITLE)) {
        values.put(EventTableSchemaBase.TITLE, "<no title>");
     }  
     if (!values.containsKey(EventTableSchemaBase.DESCRIPTION)) {
        values.put(EventTableSchemaBase.DESCRIPTION, "");
     }  
     if (!values.containsKey(EventTableSchemaBase.LONGITUDE)) {
        values.put(EventTableSchemaBase.LONGITUDE, 0.0);
     }  
     if (!values.containsKey(EventTableSchemaBase.LATITUDE)) {
        values.put(EventTableSchemaBase.LATITUDE, 0.0);
     }  
     if (!values.containsKey(EventTableSchemaBase.CREATED_DATE)) {
        values.put(EventTableSchemaBase.CREATED_DATE, now);
     }  
     if (!values.containsKey(EventTableSchemaBase.MODIFIED_DATE)) {
        values.put(EventTableSchemaBase.MODIFIED_DATE, now);
     }  
     if (!values.containsKey(EventTableSchemaBase.CID)) {
        values.put(EventTableSchemaBase.CID, "null");
     }  
     if (!values.containsKey(EventTableSchemaBase.CATEGORY)) {
        values.put(EventTableSchemaBase.CATEGORY, "null");
     }  
     if (!values.containsKey(EventTableSchemaBase.UNIT)) {
        values.put(EventTableSchemaBase.UNIT, "null");
     }  
     if (!values.containsKey(EventTableSchemaBase.SIZE)) {
        values.put(EventTableSchemaBase.SIZE, 0);
     }  
     if (!values.containsKey(EventTableSchemaBase.DEST_GROUP_TYPE)) {
        values.put(EventTableSchemaBase.DEST_GROUP_TYPE, "");
     }  
     if (!values.containsKey(EventTableSchemaBase.DEST_GROUP_NAME)) {
        values.put(EventTableSchemaBase.DEST_GROUP_NAME, "");
     }  
     if (!values.containsKey(EventTableSchemaBase.STATUS)) {
        values.put(EventTableSchemaBase.STATUS, -1);
     }  
     if (!values.containsKey(EventTableSchemaBase._RECEIVED_DATE)) {
         values.put(EventTableSchemaBase._RECEIVED_DATE, now);
     }
     if (!values.containsKey(EventTableSchemaBase._DISPOSITION)) {
        values.put(EventTableSchemaBase._DISPOSITION, IncidentSchemaBase.Disposition.LOCAL.name());
     }
   return values;
} 
/** Insert method helper */
protected ContentValues initializeStatusWithDefaults(ContentValues assignedValues) {
   final Long now = Long.valueOf(System.currentTimeMillis());
   final ContentValues values = (assignedValues == null) 
      ? new ContentValues() : assignedValues;

     if (!values.containsKey(StatusTableSchemaBase.EVENT_UUID)) {
        values.put(StatusTableSchemaBase.EVENT_UUID, "");
     }  
     if (!values.containsKey(StatusTableSchemaBase.STATUS)) {
        values.put(StatusTableSchemaBase.STATUS, "");
     }  
     if (!values.containsKey(StatusTableSchemaBase.ACTOR)) {
        values.put(StatusTableSchemaBase.ACTOR, "");
     }  
     if (!values.containsKey(StatusTableSchemaBase.MODIFIED_DATE)) {
        values.put(StatusTableSchemaBase.MODIFIED_DATE, now);
     }  
     if (!values.containsKey(StatusTableSchemaBase._RECEIVED_DATE)) {
         values.put(StatusTableSchemaBase._RECEIVED_DATE, now);
     }
     if (!values.containsKey(StatusTableSchemaBase._DISPOSITION)) {
        values.put(StatusTableSchemaBase._DISPOSITION, IncidentSchemaBase.Disposition.LOCAL.name());
     }
   return values;
} 
/** Insert method helper */
protected ContentValues initializeCategoryWithDefaults(ContentValues assignedValues) {
   final Long now = Long.valueOf(System.currentTimeMillis());
   final ContentValues values = (assignedValues == null) 
      ? new ContentValues() : assignedValues;

     if (!values.containsKey(CategoryTableSchemaBase.MAIN_CATEGORY)) {
        values.put(CategoryTableSchemaBase.MAIN_CATEGORY, "");
     }  
     if (!values.containsKey(CategoryTableSchemaBase.SUB_CATEGORY)) {
        values.put(CategoryTableSchemaBase.SUB_CATEGORY, "");
     }  
     if (!values.containsKey(CategoryTableSchemaBase.TIGR_ID)) {
        values.put(CategoryTableSchemaBase.TIGR_ID, "");
     }  
     if (!values.containsKey(CategoryTableSchemaBase.ICON_TYPE)) {
        values.put(CategoryTableSchemaBase.ICON_TYPE, "text/plain");
     }
       if (!values.containsKey(CategoryTableSchemaBase.ICON)) {
          values.put(CategoryTableSchemaBase.ICON, "");
       }  
     if (!values.containsKey(CategoryTableSchemaBase._RECEIVED_DATE)) {
         values.put(CategoryTableSchemaBase._RECEIVED_DATE, now);
     }
     if (!values.containsKey(CategoryTableSchemaBase._DISPOSITION)) {
        values.put(CategoryTableSchemaBase._DISPOSITION, IncidentSchemaBase.Disposition.LOCAL.name());
     }
   return values;
} 


@Override
public synchronized int delete(Uri uri, String selection, String[] selectionArgs) {
   final SQLiteDatabase db = this.openHelper.getWritableDatabase();
   final int count;
   Cursor cursor;
   final int match = uriMatcher.match(uri);

   logger.info("running delete with uri({}) selection({}) match({})",
       new Object[]{uri, selection, match});

   switch (match) {
   case MEDIA_SET:
      cursor = db.query(Tables.MEDIA_TBL, new String[] {MediaTableSchemaBase._ID}, selection, selectionArgs, null, null, null);
      logger.info("cursor rows: {}", cursor.getCount());
      if (cursor.moveToFirst()) {
          do {
              long rowid = cursor.getLong(cursor.getColumnIndex(MediaTableSchemaBase._ID));
              String tuple = Long.toString(rowid);
              logger.info("found rowid ({}) and tuple ({}) for deletion", rowid, tuple);
              final File file = blobDir(Tables.MEDIA_TBL, tuple);
              logger.info("deleting directory: {}", file.getAbsolutePath());
              recursiveDelete(file);
          }
          while (cursor.moveToNext());
      }
      cursor.close();
      count = db.delete(Tables.MEDIA_TBL, selection, selectionArgs);
      break;

   case MEDIA_ID:
      final String mediaID = uri.getPathSegments().get(1);
      cursor = db.query(Tables.MEDIA_TBL, new String[] {MediaTableSchemaBase._ID}, selection, selectionArgs, null, null, null);
      logger.info("cursor rows: {}", cursor.getCount());
      if (cursor.moveToFirst()) {
          do {
              final long rowid = cursor.getLong(cursor.getColumnIndex(MediaTableSchemaBase._ID));
              final String tuple = Long.toString(rowid);
              logger.info("found rowid ({}) and tuple ({}) for deletion", rowid, tuple);
              final File file = blobDir(Tables.MEDIA_TBL, tuple);
              logger.info("deleting directory: {}", file.getAbsolutePath());
              recursiveDelete(file);
          }
          while (cursor.moveToNext());
      }
      cursor.close();
      count = db.delete(Tables.MEDIA_TBL,
            MediaTableSchemaBase._ID
                  + "="
                  + mediaID
                  + (TextUtils.isEmpty(selection) ? "" 
                         : (" AND (" + selection + ')')),
                  selectionArgs);
      break;

   case EVENT_SET:
      cursor = db.query(Tables.EVENT_TBL, new String[] {EventTableSchemaBase._ID}, selection, selectionArgs, null, null, null);
      logger.info("cursor rows: {}", cursor.getCount());
      if (cursor.moveToFirst()) {
          do {
              long rowid = cursor.getLong(cursor.getColumnIndex(EventTableSchemaBase._ID));
              String tuple = Long.toString(rowid);
              logger.info("found rowid ({}) and tuple ({}) for deletion", rowid, tuple);
              final File file = blobDir(Tables.EVENT_TBL, tuple);
              logger.info("deleting directory: {}", file.getAbsolutePath());
              recursiveDelete(file);
          }
          while (cursor.moveToNext());
      }
      cursor.close();
      count = db.delete(Tables.EVENT_TBL, selection, selectionArgs);
      break;

   case EVENT_ID:
      final String eventID = uri.getPathSegments().get(1);
      cursor = db.query(Tables.EVENT_TBL, new String[] {EventTableSchemaBase._ID}, selection, selectionArgs, null, null, null);
      logger.info("cursor rows: {}", cursor.getCount());
      if (cursor.moveToFirst()) {
          do {
              final long rowid = cursor.getLong(cursor.getColumnIndex(EventTableSchemaBase._ID));
              final String tuple = Long.toString(rowid);
              logger.info("found rowid ({}) and tuple ({}) for deletion", rowid, tuple);
              final File file = blobDir(Tables.EVENT_TBL, tuple);
              logger.info("deleting directory: {}", file.getAbsolutePath());
              recursiveDelete(file);
          }
          while (cursor.moveToNext());
      }
      cursor.close();
      count = db.delete(Tables.EVENT_TBL,
            EventTableSchemaBase._ID
                  + "="
                  + eventID
                  + (TextUtils.isEmpty(selection) ? "" 
                         : (" AND (" + selection + ')')),
                  selectionArgs);
      break;

   case STATUS_SET:
      cursor = db.query(Tables.STATUS_TBL, new String[] {StatusTableSchemaBase._ID}, selection, selectionArgs, null, null, null);
      logger.info("cursor rows: {}", cursor.getCount());
      if (cursor.moveToFirst()) {
          do {
              long rowid = cursor.getLong(cursor.getColumnIndex(StatusTableSchemaBase._ID));
              String tuple = Long.toString(rowid);
              logger.info("found rowid ({}) and tuple ({}) for deletion", rowid, tuple);
              final File file = blobDir(Tables.STATUS_TBL, tuple);
              logger.info("deleting directory: {}", file.getAbsolutePath());
              recursiveDelete(file);
          }
          while (cursor.moveToNext());
      }
      cursor.close();
      count = db.delete(Tables.STATUS_TBL, selection, selectionArgs);
      break;

   case STATUS_ID:
      final String statusID = uri.getPathSegments().get(1);
      cursor = db.query(Tables.STATUS_TBL, new String[] {StatusTableSchemaBase._ID}, selection, selectionArgs, null, null, null);
      logger.info("cursor rows: {}", cursor.getCount());
      if (cursor.moveToFirst()) {
          do {
              final long rowid = cursor.getLong(cursor.getColumnIndex(StatusTableSchemaBase._ID));
              final String tuple = Long.toString(rowid);
              logger.info("found rowid ({}) and tuple ({}) for deletion", rowid, tuple);
              final File file = blobDir(Tables.STATUS_TBL, tuple);
              logger.info("deleting directory: {}", file.getAbsolutePath());
              recursiveDelete(file);
          }
          while (cursor.moveToNext());
      }
      cursor.close();
      count = db.delete(Tables.STATUS_TBL,
            StatusTableSchemaBase._ID
                  + "="
                  + statusID
                  + (TextUtils.isEmpty(selection) ? "" 
                         : (" AND (" + selection + ')')),
                  selectionArgs);
      break;

   case CATEGORY_SET:
      cursor = db.query(Tables.CATEGORY_TBL, new String[] {CategoryTableSchemaBase._ID}, selection, selectionArgs, null, null, null);
      logger.info("cursor rows: {}", cursor.getCount());
      if (cursor.moveToFirst()) {
          do {
              long rowid = cursor.getLong(cursor.getColumnIndex(CategoryTableSchemaBase._ID));
              String tuple = Long.toString(rowid);
              logger.info("found rowid ({}) and tuple ({}) for deletion", rowid, tuple);
              final File file = blobDir(Tables.CATEGORY_TBL, tuple);
              logger.info("deleting directory: {}", file.getAbsolutePath());
              recursiveDelete(file);
          }
          while (cursor.moveToNext());
      }
      cursor.close();
      count = db.delete(Tables.CATEGORY_TBL, selection, selectionArgs);
      break;

   case CATEGORY_ID:
      final String categoryID = uri.getPathSegments().get(1);
      cursor = db.query(Tables.CATEGORY_TBL, new String[] {CategoryTableSchemaBase._ID}, selection, selectionArgs, null, null, null);
      logger.info("cursor rows: {}", cursor.getCount());
      if (cursor.moveToFirst()) {
          do {
              final long rowid = cursor.getLong(cursor.getColumnIndex(CategoryTableSchemaBase._ID));
              final String tuple = Long.toString(rowid);
              logger.info("found rowid ({}) and tuple ({}) for deletion", rowid, tuple);
              final File file = blobDir(Tables.CATEGORY_TBL, tuple);
              logger.info("deleting directory: {}", file.getAbsolutePath());
              recursiveDelete(file);
          }
          while (cursor.moveToNext());
      }
      cursor.close();
      count = db.delete(Tables.CATEGORY_TBL,
            CategoryTableSchemaBase._ID
                  + "="
                  + categoryID
                  + (TextUtils.isEmpty(selection) ? "" 
                         : (" AND (" + selection + ')')),
                  selectionArgs);
      break;


   default:
      throw new IllegalArgumentException("Unknown URI " + uri);
   }

   if (count > 0) getContext().getContentResolver().notifyChange(uri, null);
   return count;
}

@Override
public synchronized int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
   final SQLiteDatabase db = this.openHelper.getWritableDatabase();
   final Uri notifyUri;
   final int count;
   switch (uriMatcher.match(uri)) {
   case MEDIA_SET:
      logger.debug("MEDIA_SET");
      notifyUri = uri;
      count = db.update(Tables.MEDIA_TBL, values, selection,
            selectionArgs);
      break;

   case MEDIA_ID:
      logger.debug("MEDIA_ID");
      //  notify on the base URI - without the ID ?
      notifyUri = MediaTableSchemaBase.CONTENT_URI; 
      String mediaID = uri.getPathSegments().get(1);
      count = db.update(Tables.MEDIA_TBL, values, MediaTableSchemaBase._ID
            + "="
            + mediaID
            + (TextUtils.isEmpty(selection) ? "" 
                         : (" AND (" + selection + ')')),
            selectionArgs);
      break;

   case EVENT_SET:
      logger.debug("EVENT_SET");
      notifyUri = uri;
      count = db.update(Tables.EVENT_TBL, values, selection,
            selectionArgs);
      break;

   case EVENT_ID:
      logger.debug("EVENT_ID");
      //  notify on the base URI - without the ID ?
      notifyUri = EventTableSchemaBase.CONTENT_URI; 
      String eventID = uri.getPathSegments().get(1);
      count = db.update(Tables.EVENT_TBL, values, EventTableSchemaBase._ID
            + "="
            + eventID
            + (TextUtils.isEmpty(selection) ? "" 
                         : (" AND (" + selection + ')')),
            selectionArgs);
      break;

   case STATUS_SET:
      logger.debug("STATUS_SET");
      notifyUri = uri;
      count = db.update(Tables.STATUS_TBL, values, selection,
            selectionArgs);
      break;

   case STATUS_ID:
      logger.debug("STATUS_ID");
      //  notify on the base URI - without the ID ?
      notifyUri = StatusTableSchemaBase.CONTENT_URI; 
      String statusID = uri.getPathSegments().get(1);
      count = db.update(Tables.STATUS_TBL, values, StatusTableSchemaBase._ID
            + "="
            + statusID
            + (TextUtils.isEmpty(selection) ? "" 
                         : (" AND (" + selection + ')')),
            selectionArgs);
      break;

   case CATEGORY_SET:
      logger.debug("CATEGORY_SET");
      notifyUri = uri;
      count = db.update(Tables.CATEGORY_TBL, values, selection,
            selectionArgs);
      break;

   case CATEGORY_ID:
      logger.debug("CATEGORY_ID");
      //  notify on the base URI - without the ID ?
      notifyUri = CategoryTableSchemaBase.CONTENT_URI; 
      String categoryID = uri.getPathSegments().get(1);
      count = db.update(Tables.CATEGORY_TBL, values, CategoryTableSchemaBase._ID
            + "="
            + categoryID
            + (TextUtils.isEmpty(selection) ? "" 
                         : (" AND (" + selection + ')')),
            selectionArgs);
      break;


   default:
      throw new IllegalArgumentException("Unknown URI " + uri);
   }

   if (count > 0) 
      getContext().getContentResolver().notifyChange(notifyUri, null);
   return count;   
}

@Override
public synchronized String getType(Uri uri) {
   switch (uriMatcher.match(uri)) {
      case MEDIA_SET:
      case MEDIA_ID:
         return MediaTableSchemaBase.CONTENT_TOPIC;

      case EVENT_SET:
      case EVENT_ID:
         return EventTableSchemaBase.CONTENT_TOPIC;

      case STATUS_SET:
      case STATUS_ID:
         return StatusTableSchemaBase.CONTENT_TOPIC;

      case CATEGORY_SET:
      case CATEGORY_ID:
         return CategoryTableSchemaBase.CONTENT_TOPIC;


   default:
      throw new IllegalArgumentException("Unknown URI " + uri);
   }   
}

// ===========================================================
// Static declarations
// ===========================================================

static {
   uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
      uriMatcher.addURI(IncidentSchemaBase.AUTHORITY, Tables.MEDIA_TBL, MEDIA_SET);
      uriMatcher.addURI(IncidentSchemaBase.AUTHORITY, Tables.MEDIA_TBL + "/#", MEDIA_ID);
      uriMatcher.addURI(IncidentSchemaBase.AUTHORITY, Tables.MEDIA_TBL + "/#/_serial/*", MEDIA_SERIAL);
      uriMatcher.addURI(IncidentSchemaBase.AUTHORITY, Tables.MEDIA_TBL + "/_deserial/*", MEDIA_DESERIAL);
      uriMatcher.addURI(IncidentSchemaBase.AUTHORITY, Tables.MEDIA_TBL + "/#/_serial", MEDIA_SERIAL);
      uriMatcher.addURI(IncidentSchemaBase.AUTHORITY, Tables.MEDIA_TBL + "/_deserial", MEDIA_DESERIAL);
      uriMatcher.addURI(IncidentSchemaBase.AUTHORITY, Tables.MEDIA_TBL + "/#/_blob", MEDIA_BLOB);
      uriMatcher.addURI(IncidentSchemaBase.AUTHORITY, Tables.MEDIA_TBL + "/#/_data_type", MEDIA_META);
      uriMatcher.addURI(IncidentSchemaBase.AUTHORITY, Tables.MEDIA_TBL + "/_data_type", MEDIA_META);

      {
        mediaFieldTypeCursor = new MatrixCursor(new String[] {
         MediaTableSchemaBase.EVENT_ID, 
         MediaTableSchemaBase.DATA, 
         MediaTableSchemaBase.CREATED_DATE, 
         MediaTableSchemaBase.MODIFIED_DATE, 
        }, 1);

        final MatrixCursor.RowBuilder row = mediaFieldTypeCursor.newRow();
        row.add(IncidentSchemaBase.FIELD_TYPE_TEXT); // EVENT_ID 
        row.add(IncidentSchemaBase.FIELD_TYPE_FILE); // DATA 
        row.add(IncidentSchemaBase.FIELD_TYPE_TIMESTAMP); // CREATED_DATE 
        row.add(IncidentSchemaBase.FIELD_TYPE_TIMESTAMP); // MODIFIED_DATE 
      }

      uriMatcher.addURI(IncidentSchemaBase.AUTHORITY, Tables.EVENT_TBL, EVENT_SET);
      uriMatcher.addURI(IncidentSchemaBase.AUTHORITY, Tables.EVENT_TBL + "/#", EVENT_ID);
      uriMatcher.addURI(IncidentSchemaBase.AUTHORITY, Tables.EVENT_TBL + "/#/_serial/*", EVENT_SERIAL);
      uriMatcher.addURI(IncidentSchemaBase.AUTHORITY, Tables.EVENT_TBL + "/_deserial/*", EVENT_DESERIAL);
      uriMatcher.addURI(IncidentSchemaBase.AUTHORITY, Tables.EVENT_TBL + "/#/_serial", EVENT_SERIAL);
      uriMatcher.addURI(IncidentSchemaBase.AUTHORITY, Tables.EVENT_TBL + "/_deserial", EVENT_DESERIAL);
      uriMatcher.addURI(IncidentSchemaBase.AUTHORITY, Tables.EVENT_TBL + "/#/_blob", EVENT_BLOB);
      uriMatcher.addURI(IncidentSchemaBase.AUTHORITY, Tables.EVENT_TBL + "/#/_data_type", EVENT_META);
      uriMatcher.addURI(IncidentSchemaBase.AUTHORITY, Tables.EVENT_TBL + "/_data_type", EVENT_META);

      {
        eventFieldTypeCursor = new MatrixCursor(new String[] {
         EventTableSchemaBase.UUID, 
         EventTableSchemaBase.MEDIA_COUNT, 
         EventTableSchemaBase.ORIGINATOR, 
         EventTableSchemaBase.DISPLAY_NAME, 
         EventTableSchemaBase.CATEGORY_ID, 
         EventTableSchemaBase.TITLE, 
         EventTableSchemaBase.DESCRIPTION, 
         EventTableSchemaBase.LONGITUDE, 
         EventTableSchemaBase.LATITUDE, 
         EventTableSchemaBase.CREATED_DATE, 
         EventTableSchemaBase.MODIFIED_DATE, 
         EventTableSchemaBase.CID, 
         EventTableSchemaBase.CATEGORY, 
         EventTableSchemaBase.UNIT, 
         EventTableSchemaBase.SIZE, 
         EventTableSchemaBase.DEST_GROUP_TYPE, 
         EventTableSchemaBase.DEST_GROUP_NAME, 
         EventTableSchemaBase.STATUS, 
        }, 1);

        final MatrixCursor.RowBuilder row = eventFieldTypeCursor.newRow();
        row.add(IncidentSchemaBase.FIELD_TYPE_TEXT); // UUID 
        row.add(IncidentSchemaBase.FIELD_TYPE_INTEGER); // MEDIA_COUNT 
        row.add(IncidentSchemaBase.FIELD_TYPE_TEXT); // ORIGINATOR 
        row.add(IncidentSchemaBase.FIELD_TYPE_TEXT); // DISPLAY_NAME 
        row.add(IncidentSchemaBase.FIELD_TYPE_TEXT); // CATEGORY_ID 
        row.add(IncidentSchemaBase.FIELD_TYPE_TEXT); // TITLE 
        row.add(IncidentSchemaBase.FIELD_TYPE_TEXT); // DESCRIPTION 
        row.add(IncidentSchemaBase.FIELD_TYPE_REAL); // LONGITUDE 
        row.add(IncidentSchemaBase.FIELD_TYPE_REAL); // LATITUDE 
        row.add(IncidentSchemaBase.FIELD_TYPE_TIMESTAMP); // CREATED_DATE 
        row.add(IncidentSchemaBase.FIELD_TYPE_TIMESTAMP); // MODIFIED_DATE 
        row.add(IncidentSchemaBase.FIELD_TYPE_TEXT); // CID 
        row.add(IncidentSchemaBase.FIELD_TYPE_TEXT); // CATEGORY 
        row.add(IncidentSchemaBase.FIELD_TYPE_TEXT); // UNIT 
        row.add(IncidentSchemaBase.FIELD_TYPE_LONG); // SIZE 
        row.add(IncidentSchemaBase.FIELD_TYPE_TEXT); // DEST_GROUP_TYPE 
        row.add(IncidentSchemaBase.FIELD_TYPE_TEXT); // DEST_GROUP_NAME 
        row.add(IncidentSchemaBase.FIELD_TYPE_INTEGER); // STATUS 
      }

      uriMatcher.addURI(IncidentSchemaBase.AUTHORITY, Tables.STATUS_TBL, STATUS_SET);
      uriMatcher.addURI(IncidentSchemaBase.AUTHORITY, Tables.STATUS_TBL + "/#", STATUS_ID);
      uriMatcher.addURI(IncidentSchemaBase.AUTHORITY, Tables.STATUS_TBL + "/#/_serial/*", STATUS_SERIAL);
      uriMatcher.addURI(IncidentSchemaBase.AUTHORITY, Tables.STATUS_TBL + "/_deserial/*", STATUS_DESERIAL);
      uriMatcher.addURI(IncidentSchemaBase.AUTHORITY, Tables.STATUS_TBL + "/#/_serial", STATUS_SERIAL);
      uriMatcher.addURI(IncidentSchemaBase.AUTHORITY, Tables.STATUS_TBL + "/_deserial", STATUS_DESERIAL);
      uriMatcher.addURI(IncidentSchemaBase.AUTHORITY, Tables.STATUS_TBL + "/#/_blob", STATUS_BLOB);
      uriMatcher.addURI(IncidentSchemaBase.AUTHORITY, Tables.STATUS_TBL + "/#/_data_type", STATUS_META);
      uriMatcher.addURI(IncidentSchemaBase.AUTHORITY, Tables.STATUS_TBL + "/_data_type", STATUS_META);

      {
        statusFieldTypeCursor = new MatrixCursor(new String[] {
         StatusTableSchemaBase.EVENT_UUID, 
         StatusTableSchemaBase.STATUS, 
         StatusTableSchemaBase.ACTOR, 
         StatusTableSchemaBase.MODIFIED_DATE, 
        }, 1);

        final MatrixCursor.RowBuilder row = statusFieldTypeCursor.newRow();
        row.add(IncidentSchemaBase.FIELD_TYPE_TEXT); // EVENT_UUID 
        row.add(IncidentSchemaBase.FIELD_TYPE_TEXT); // STATUS 
        row.add(IncidentSchemaBase.FIELD_TYPE_TEXT); // ACTOR 
        row.add(IncidentSchemaBase.FIELD_TYPE_TIMESTAMP); // MODIFIED_DATE 
      }

      uriMatcher.addURI(IncidentSchemaBase.AUTHORITY, Tables.CATEGORY_TBL, CATEGORY_SET);
      uriMatcher.addURI(IncidentSchemaBase.AUTHORITY, Tables.CATEGORY_TBL + "/#", CATEGORY_ID);
      uriMatcher.addURI(IncidentSchemaBase.AUTHORITY, Tables.CATEGORY_TBL + "/#/_serial/*", CATEGORY_SERIAL);
      uriMatcher.addURI(IncidentSchemaBase.AUTHORITY, Tables.CATEGORY_TBL + "/_deserial/*", CATEGORY_DESERIAL);
      uriMatcher.addURI(IncidentSchemaBase.AUTHORITY, Tables.CATEGORY_TBL + "/#/_serial", CATEGORY_SERIAL);
      uriMatcher.addURI(IncidentSchemaBase.AUTHORITY, Tables.CATEGORY_TBL + "/_deserial", CATEGORY_DESERIAL);
      uriMatcher.addURI(IncidentSchemaBase.AUTHORITY, Tables.CATEGORY_TBL + "/#/_blob", CATEGORY_BLOB);
      uriMatcher.addURI(IncidentSchemaBase.AUTHORITY, Tables.CATEGORY_TBL + "/#/_data_type", CATEGORY_META);
      uriMatcher.addURI(IncidentSchemaBase.AUTHORITY, Tables.CATEGORY_TBL + "/_data_type", CATEGORY_META);

      {
        categoryFieldTypeCursor = new MatrixCursor(new String[] {
         CategoryTableSchemaBase.MAIN_CATEGORY, 
         CategoryTableSchemaBase.SUB_CATEGORY, 
         CategoryTableSchemaBase.TIGR_ID, 
         CategoryTableSchemaBase.ICON, 
        }, 1);

        final MatrixCursor.RowBuilder row = categoryFieldTypeCursor.newRow();
        row.add(IncidentSchemaBase.FIELD_TYPE_TEXT); // MAIN_CATEGORY 
        row.add(IncidentSchemaBase.FIELD_TYPE_TEXT); // SUB_CATEGORY 
        row.add(IncidentSchemaBase.FIELD_TYPE_TEXT); // TIGR_ID 
        row.add(IncidentSchemaBase.FIELD_TYPE_FILE); // ICON 
      }


      mediaProjectionKey = new String[1];
      mediaProjectionKey[0] = MediaTableSchemaBase._ID;

      {
         final HashMap<String, String> columns = new HashMap<String, String>();
         columns.put(MediaTableSchemaBase._ID, MediaTableSchemaBase._ID);
            columns.put(MediaTableSchemaBase.EVENT_ID, "\""+MediaTableSchemaBase.EVENT_ID+"\""); 
            columns.put(MediaTableSchemaBase.DATA_TYPE, "\""+MediaTableSchemaBase.DATA_TYPE+"\"");
              columns.put(MediaTableSchemaBase.DATA, "\""+MediaTableSchemaBase.DATA+"\""); 
            columns.put(MediaTableSchemaBase.CREATED_DATE, "\""+MediaTableSchemaBase.CREATED_DATE+"\""); 
            columns.put(MediaTableSchemaBase.MODIFIED_DATE, "\""+MediaTableSchemaBase.MODIFIED_DATE+"\""); 
            columns.put(MediaTableSchemaBase._RECEIVED_DATE, "\""+MediaTableSchemaBase._RECEIVED_DATE+"\"");
            columns.put(MediaTableSchemaBase._DISPOSITION, "\""+MediaTableSchemaBase._DISPOSITION+"\"");

         mediaProjectionMap = columns;
      }


      eventProjectionKey = new String[1];
      eventProjectionKey[0] = EventTableSchemaBase._ID;

      {
         final HashMap<String, String> columns = new HashMap<String, String>();
         columns.put(EventTableSchemaBase._ID, EventTableSchemaBase._ID);
            columns.put(EventTableSchemaBase.UUID, "\""+EventTableSchemaBase.UUID+"\""); 
            columns.put(EventTableSchemaBase.MEDIA_COUNT, "\""+EventTableSchemaBase.MEDIA_COUNT+"\""); 
            columns.put(EventTableSchemaBase.ORIGINATOR, "\""+EventTableSchemaBase.ORIGINATOR+"\""); 
            columns.put(EventTableSchemaBase.DISPLAY_NAME, "\""+EventTableSchemaBase.DISPLAY_NAME+"\""); 
            columns.put(EventTableSchemaBase.CATEGORY_ID, "\""+EventTableSchemaBase.CATEGORY_ID+"\""); 
            columns.put(EventTableSchemaBase.TITLE, "\""+EventTableSchemaBase.TITLE+"\""); 
            columns.put(EventTableSchemaBase.DESCRIPTION, "\""+EventTableSchemaBase.DESCRIPTION+"\""); 
            columns.put(EventTableSchemaBase.LONGITUDE, "\""+EventTableSchemaBase.LONGITUDE+"\""); 
            columns.put(EventTableSchemaBase.LATITUDE, "\""+EventTableSchemaBase.LATITUDE+"\""); 
            columns.put(EventTableSchemaBase.CREATED_DATE, "\""+EventTableSchemaBase.CREATED_DATE+"\""); 
            columns.put(EventTableSchemaBase.MODIFIED_DATE, "\""+EventTableSchemaBase.MODIFIED_DATE+"\""); 
            columns.put(EventTableSchemaBase.CID, "\""+EventTableSchemaBase.CID+"\""); 
            columns.put(EventTableSchemaBase.CATEGORY, "\""+EventTableSchemaBase.CATEGORY+"\""); 
            columns.put(EventTableSchemaBase.UNIT, "\""+EventTableSchemaBase.UNIT+"\""); 
            columns.put(EventTableSchemaBase.SIZE, "\""+EventTableSchemaBase.SIZE+"\""); 
            columns.put(EventTableSchemaBase.DEST_GROUP_TYPE, "\""+EventTableSchemaBase.DEST_GROUP_TYPE+"\""); 
            columns.put(EventTableSchemaBase.DEST_GROUP_NAME, "\""+EventTableSchemaBase.DEST_GROUP_NAME+"\""); 
            columns.put(EventTableSchemaBase.STATUS, "\""+EventTableSchemaBase.STATUS+"\""); 
            columns.put(EventTableSchemaBase._RECEIVED_DATE, "\""+EventTableSchemaBase._RECEIVED_DATE+"\"");
            columns.put(EventTableSchemaBase._DISPOSITION, "\""+EventTableSchemaBase._DISPOSITION+"\"");

         eventProjectionMap = columns;
      }


      statusProjectionKey = new String[1];
      statusProjectionKey[0] = StatusTableSchemaBase._ID;

      {
         final HashMap<String, String> columns = new HashMap<String, String>();
         columns.put(StatusTableSchemaBase._ID, StatusTableSchemaBase._ID);
            columns.put(StatusTableSchemaBase.EVENT_UUID, "\""+StatusTableSchemaBase.EVENT_UUID+"\""); 
            columns.put(StatusTableSchemaBase.STATUS, "\""+StatusTableSchemaBase.STATUS+"\""); 
            columns.put(StatusTableSchemaBase.ACTOR, "\""+StatusTableSchemaBase.ACTOR+"\""); 
            columns.put(StatusTableSchemaBase.MODIFIED_DATE, "\""+StatusTableSchemaBase.MODIFIED_DATE+"\""); 
            columns.put(StatusTableSchemaBase._RECEIVED_DATE, "\""+StatusTableSchemaBase._RECEIVED_DATE+"\"");
            columns.put(StatusTableSchemaBase._DISPOSITION, "\""+StatusTableSchemaBase._DISPOSITION+"\"");

         statusProjectionMap = columns;
      }


      categoryProjectionKey = new String[1];
      categoryProjectionKey[0] = CategoryTableSchemaBase._ID;

      {
         final HashMap<String, String> columns = new HashMap<String, String>();
         columns.put(CategoryTableSchemaBase._ID, CategoryTableSchemaBase._ID);
            columns.put(CategoryTableSchemaBase.MAIN_CATEGORY, "\""+CategoryTableSchemaBase.MAIN_CATEGORY+"\""); 
            columns.put(CategoryTableSchemaBase.SUB_CATEGORY, "\""+CategoryTableSchemaBase.SUB_CATEGORY+"\""); 
            columns.put(CategoryTableSchemaBase.TIGR_ID, "\""+CategoryTableSchemaBase.TIGR_ID+"\""); 
            columns.put(CategoryTableSchemaBase.ICON_TYPE, "\""+CategoryTableSchemaBase.ICON_TYPE+"\"");
              columns.put(CategoryTableSchemaBase.ICON, "\""+CategoryTableSchemaBase.ICON+"\""); 
            columns.put(CategoryTableSchemaBase._RECEIVED_DATE, "\""+CategoryTableSchemaBase._RECEIVED_DATE+"\"");
            columns.put(CategoryTableSchemaBase._DISPOSITION, "\""+CategoryTableSchemaBase._DISPOSITION+"\"");

         categoryProjectionMap = columns;
      }


}

static public final File applDir;
static public final File applCacheDir;

static public final File applCacheMediaDir;
static public final File applCacheEventDir;
static public final File applCacheStatusDir;
static public final File applCacheCategoryDir;


static public final File applTempDir;
static {
    applDir = new File(Environment.getExternalStorageDirectory(), "support/edu.vu.isis.ammo.dash"); 
    applDir.mkdirs();
    if (! applDir.exists()) {
      logger.error("cannot create support files check permissions : {}", 
           applDir.toString());
    } else if (! applDir.isDirectory()) {
      logger.error("support directory is not a directory : {}", 
           applDir.toString());
    }

    applCacheDir = new File(applDir, "cache/incident"); 
    applCacheDir.mkdirs();

    applCacheMediaDir = new File(applCacheDir, "media"); 
    applCacheDir.mkdirs();

    applCacheEventDir = new File(applCacheDir, "event"); 
    applCacheDir.mkdirs();

    applCacheStatusDir = new File(applCacheDir, "status"); 
    applCacheDir.mkdirs();

    applCacheCategoryDir = new File(applCacheDir, "category"); 
    applCacheDir.mkdirs();


    applTempDir = new File(applDir, "tmp/incident"); 
    applTempDir.mkdirs();
}

protected static File blobFile(String table, String tuple, String field) throws IOException {
   final File tupleCacheDir = blobDir(table, tuple);
   final File cacheFile = new File(tupleCacheDir, field+".blob");
   if (cacheFile.exists()) return cacheFile;    

   cacheFile.createNewFile();
   return cacheFile;
}

protected static File blobDir(String table, String tuple) {
   final File tableCacheDir = new File(applCacheDir, table);
   final File tupleCacheDir = new File(tableCacheDir, tuple);
   if (!tupleCacheDir.exists()) tupleCacheDir.mkdirs();
   return tupleCacheDir;
}

protected static File receiveFile(String table, String tuple, File filePath) {
  final String baseName = new StringBuilder()
      .append("recv_")
      .append(tuple).append("_")
      .append(filePath.getName())
      .toString();

  final File dirPath = filePath.getParentFile();
  final File wipPath;
  if (dirPath == null) {
    wipPath = blobDir(table, tuple);
  } else if (! dirPath.exists()) {
    if (! dirPath.mkdirs()) {
      wipPath = blobDir(table, tuple);
    } else {
      wipPath = dirPath;
    }
  } else { 
    wipPath = dirPath;
  }
  return new File(wipPath, baseName);
}

protected static File tempFilePath(String table) throws IOException {
   return File.createTempFile(table, ".tmp", applTempDir);
}


protected static void clearBlobCache(String table, String tuple) {
   if (table == null) {
     if (applCacheDir.isDirectory()) {
        for (File child : applCacheDir.listFiles()) {
            recursiveDelete(child);
        }
        return;
     }
   }
   final File tableCacheDir = new File(applCacheDir, table);
   if (tuple == null) {
     if (tableCacheDir.isDirectory()) {
        for (File child : tableCacheDir.listFiles()) {
            recursiveDelete(child);
        }
        return;
     }
   }
   final File tupleCacheDir = new File(tableCacheDir, tuple);
   if (tupleCacheDir.isDirectory()) {
      for (File child : tupleCacheDir.listFiles()) {
         recursiveDelete(child);
      }
   }
}

/** 
 * Recursively delete all children of this directory and the directory itself.
 * 
 * @param dir
 */
protected static void recursiveDelete(File dir) {
    if (!dir.exists()) return;

    if (dir.isFile()) {
        dir.delete();
        return;
    }
    if (dir.isDirectory()) {
        for (File child : dir.listFiles()) {
            recursiveDelete(child);
        }
        dir.delete();
        return;
    }
} 
}
