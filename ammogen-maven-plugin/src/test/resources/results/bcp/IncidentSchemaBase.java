// THIS IS GENERATED CODE, MAKE SURE ANY CHANGES MADE HERE ARE PROPAGATED INTO THE GENERATOR TEMPLATES
package edu.vu.isis.ammo.dash.provider;

import android.net.Uri;
import android.provider.BaseColumns;
import android.content.ContentResolver;
import android.database.Cursor;

public abstract class IncidentSchemaBase {
  public final static String VERSION = "1.7.0";
public static final String AUTHORITY = "edu.vu.isis.ammo.dash.provider.incidentprovider";

public static final String DATABASE_NAME = "incident.db";

/**
 see AmmoProviderSchema for details
*/
public enum Disposition {
   REMOTE(0), LOCAL(1);

   private final int code;

   private Disposition(int code) {
      this.code = code;
   }

   public int toCode() {
      return this.code;
   }

   public static Disposition fromCode(final int code) {
      switch (code) {
      case 0: return REMOTE;
      case 1: return LOCAL;
      }
      return LOCAL;
   }

   @Override
   public String toString() {
      return this.name();
   }

   public static Disposition fromString(final String value) {
      try {
         return (value == null) ? Disposition.LOCAL 
               : (value.startsWith( "REMOTE" )) ? Disposition.REMOTE
                     : Disposition.LOCAL;
      } catch (Exception ex) {
         return Disposition.LOCAL;
      }
   }
}

protected IncidentSchemaBase() {}

public static final int FIELD_TYPE_NULL = 0;
public static final int FIELD_TYPE_BOOL = 1;
public static final int FIELD_TYPE_BLOB = 2;
public static final int FIELD_TYPE_FLOAT = 3;
public static final int FIELD_TYPE_INTEGER = 4;
public static final int FIELD_TYPE_LONG = 5;
public static final int FIELD_TYPE_TEXT = 6;
public static final int FIELD_TYPE_REAL = 7;
public static final int FIELD_TYPE_FK = 8;
public static final int FIELD_TYPE_GUID = 9;
public static final int FIELD_TYPE_EXCLUSIVE = 10;
public static final int FIELD_TYPE_INCLUSIVE = 11;
public static final int FIELD_TYPE_TIMESTAMP = 12;
public static final int FIELD_TYPE_SHORT = 13;
public static final int FIELD_TYPE_FILE = 14;

// BEGIN CUSTOM Incident CONSTANTS
// END   CUSTOM Incident CONSTANTS

public static class Meta {
   final public String name;
   final public int type;
   public Meta(String name, int type) { this.name = name; this.type = type; }
}

public static final Meta[] MEDIA_CURSOR_COLUMNS = new Meta[] {
new Meta(MediaTableSchemaBase.EVENT_ID, FIELD_TYPE_TEXT)  ,
   new Meta(MediaTableSchemaBase.DATA_TYPE, FIELD_TYPE_TEXT), 
new Meta(MediaTableSchemaBase.DATA, FIELD_TYPE_FILE)  ,
   new Meta(MediaTableSchemaBase.CREATED_DATE, FIELD_TYPE_TIMESTAMP)  ,
   new Meta(MediaTableSchemaBase.MODIFIED_DATE, FIELD_TYPE_TIMESTAMP)  
};

public static final String[] MEDIA_KEY_COLUMNS = new String[] {
  MediaTableSchemaBase.EVENT_ID 
};

public static class MediaTableSchemaBase implements BaseColumns {
   protected MediaTableSchemaBase() {} // No instantiation.

   /**
    * The content:// style URL for this table
    */
   public static final Uri CONTENT_URI =
      Uri.parse("content://"+AUTHORITY+"/media");

   public static Uri getUri(Cursor cursor) {
     final Integer id = cursor.getInt(cursor.getColumnIndex(BaseColumns._ID));
     return  Uri.withAppendedPath(MediaTableSchemaBase.CONTENT_URI, id.toString());
   }

   /**
    * The MIME type of {@link #CONTENT_URI} providing a directory
    */
   public static final String CONTENT_TYPE =
      ContentResolver.CURSOR_DIR_BASE_TYPE+"/vnd.edu.vu.isis.ammo.dash.media";

   /**
    * A mime type used for publisher subscriber.
    */
   public static final String CONTENT_TOPIC =
      "ammo/edu.vu.isis.ammo.dash.media";

   /**
    * The MIME type of a {@link #CONTENT_URI} sub-directory of a single media entry.
    */
   public static final String CONTENT_ITEM_TYPE = 
      ContentResolver.CURSOR_ITEM_BASE_TYPE+"/vnd.edu.vu.isis.ammo.dash.media";


   public static final String DEFAULT_SORT_ORDER = ""; //"modified_date DESC";

   // ========= Field Name and Type Constants ================
      /** 
      * Description: Points to the event to which this belongs.
      * <P>Type: TEXT</P> 
      */
          public static final String EVENT_ID = "event_id";
      /** 
      * Description: The file where the media is stored (and its mime type)
      * <P>Type: FILE</P> 
      */
          public static final String DATA_TYPE = "data_type";
          public static final String DATA = "data";
      /** 
      * Description: 
      * <P>Type: TIMESTAMP</P> 
      */
          public static final String CREATED_DATE = "created_date";
      /** 
      * Description: 
      * <P>Type: TIMESTAMP</P> 
      */
          public static final String MODIFIED_DATE = "modified_date";


   public static final String _DISPOSITION = "_disp"; 

   public static final String _RECEIVED_DATE = "_received_date";

// BEGIN CUSTOM MEDIA_SCHEMA PROPERTIES
// END   CUSTOM MEDIA_SCHEMA PROPERTIES
} 
public static final Meta[] EVENT_CURSOR_COLUMNS = new Meta[] {
new Meta(EventTableSchemaBase.UUID, FIELD_TYPE_TEXT)  ,
   new Meta(EventTableSchemaBase.MEDIA_COUNT, FIELD_TYPE_INTEGER)  ,
   new Meta(EventTableSchemaBase.ORIGINATOR, FIELD_TYPE_TEXT)  ,
   new Meta(EventTableSchemaBase.DISPLAY_NAME, FIELD_TYPE_TEXT)  ,
   new Meta(EventTableSchemaBase.CATEGORY_ID, FIELD_TYPE_TEXT)  ,
   new Meta(EventTableSchemaBase.TITLE, FIELD_TYPE_TEXT)  ,
   new Meta(EventTableSchemaBase.DESCRIPTION, FIELD_TYPE_TEXT)  ,
   new Meta(EventTableSchemaBase.LONGITUDE, FIELD_TYPE_REAL)  ,
   new Meta(EventTableSchemaBase.LATITUDE, FIELD_TYPE_REAL)  ,
   new Meta(EventTableSchemaBase.CREATED_DATE, FIELD_TYPE_TIMESTAMP)  ,
   new Meta(EventTableSchemaBase.MODIFIED_DATE, FIELD_TYPE_TIMESTAMP)  ,
   new Meta(EventTableSchemaBase.CID, FIELD_TYPE_TEXT)  ,
   new Meta(EventTableSchemaBase.CATEGORY, FIELD_TYPE_TEXT)  ,
   new Meta(EventTableSchemaBase.UNIT, FIELD_TYPE_TEXT)  ,
   new Meta(EventTableSchemaBase.SIZE, FIELD_TYPE_LONG)  ,
   new Meta(EventTableSchemaBase.DEST_GROUP_TYPE, FIELD_TYPE_TEXT)  ,
   new Meta(EventTableSchemaBase.DEST_GROUP_NAME, FIELD_TYPE_TEXT)  ,
   new Meta(EventTableSchemaBase.STATUS, FIELD_TYPE_INTEGER)  
};

public static final String[] EVENT_KEY_COLUMNS = new String[] {
  EventTableSchemaBase.UUID 
};

public static class EventTableSchemaBase implements BaseColumns {
   protected EventTableSchemaBase() {} // No instantiation.

   /**
    * The content:// style URL for this table
    */
   public static final Uri CONTENT_URI =
      Uri.parse("content://"+AUTHORITY+"/event");

   public static Uri getUri(Cursor cursor) {
     final Integer id = cursor.getInt(cursor.getColumnIndex(BaseColumns._ID));
     return  Uri.withAppendedPath(EventTableSchemaBase.CONTENT_URI, id.toString());
   }

   /**
    * The MIME type of {@link #CONTENT_URI} providing a directory
    */
   public static final String CONTENT_TYPE =
      ContentResolver.CURSOR_DIR_BASE_TYPE+"/vnd.edu.vu.isis.ammo.dash.event";

   /**
    * A mime type used for publisher subscriber.
    */
   public static final String CONTENT_TOPIC =
      "ammo/edu.vu.isis.ammo.dash.event";

   /**
    * The MIME type of a {@link #CONTENT_URI} sub-directory of a single event entry.
    */
   public static final String CONTENT_ITEM_TYPE = 
      ContentResolver.CURSOR_ITEM_BASE_TYPE+"/vnd.edu.vu.isis.ammo.dash.event";


   public static final String DEFAULT_SORT_ORDER = ""; //"modified_date DESC";

   // ========= Field Name and Type Constants ================
      /** 
      * Description: 
      * <P>Type: TEXT</P> 
      */
          public static final String UUID = "uuid";
      /** 
      * Description: A count of the number of media children (i.e. from the media table.
      * <P>Type: INTEGER</P> 
      */
          public static final String MEDIA_COUNT = "media_count";
      /** 
      * Description: TigrUID of the author of the report.
      * <P>Type: TEXT</P> 
      */
          public static final String ORIGINATOR = "originator";
      /** 
      * Description: 
      * <P>Type: TEXT</P> 
      */
          public static final String DISPLAY_NAME = "display_name";
      /** 
      * Description: Previously this was directed toward the primary key of the
            category table now it points to the tigr id.
            This is also a perfectly good key field.
      * <P>Type: TEXT</P> 
      */
          public static final String CATEGORY_ID = "category_id";
      /** 
      * Description: 
      * <P>Type: TEXT</P> 
      */
          public static final String TITLE = "title";
      /** 
      * Description: 
      * <P>Type: TEXT</P> 
      */
          public static final String DESCRIPTION = "description";
      /** 
      * Description: 
      * <P>Type: REAL</P> 
      */
          public static final String LONGITUDE = "longitude";
      /** 
      * Description: 
      * <P>Type: REAL</P> 
      */
          public static final String LATITUDE = "latitude";
      /** 
      * Description: 
      * <P>Type: TIMESTAMP</P> 
      */
          public static final String CREATED_DATE = "created_date";
      /** 
      * Description: 
      * <P>Type: TIMESTAMP</P> 
      */
          public static final String MODIFIED_DATE = "modified_date";
      /** 
      * Description: 
      * <P>Type: TEXT</P> 
      */
          public static final String CID = "cid";
      /** 
      * Description: 
      * <P>Type: TEXT</P> 
      */
          public static final String CATEGORY = "category";
      /** 
      * Description: 
      * <P>Type: TEXT</P> 
      */
          public static final String UNIT = "unit";
      /** 
      * Description: The total size of the incident in kibibytes.
            Although this is the pre-serialized size it should
            be sufficient for determining message size. 
            In particular the meta-data is presumed constant.
            Note that the image size is not the display resolution size.
            (jpeg files are compressed)
      * <P>Type: LONG</P> 
      */
          public static final String SIZE = "size";
      /** 
      * Description: Type of group report will be broadcasted (Broadcast, Callsign, Unit).
      * <P>Type: TEXT</P> 
      */
          public static final String DEST_GROUP_TYPE = "dest_group_type";
      /** 
      * Description: Destination group identifier to send quick report.
      * <P>Type: TEXT</P> 
      */
          public static final String DEST_GROUP_NAME = "dest_group_name";
      /** 
      * Description: The status of the report with respect to submission to
               the gateway plugin.
      * <P>Type: INTEGER</P> 
      */
          public static final String STATUS = "status";


   public static final String _DISPOSITION = "_disp"; 

   public static final String _RECEIVED_DATE = "_received_date";

// BEGIN CUSTOM EVENT_SCHEMA PROPERTIES
// END   CUSTOM EVENT_SCHEMA PROPERTIES
} 
public static final Meta[] STATUS_CURSOR_COLUMNS = new Meta[] {
new Meta(StatusTableSchemaBase.EVENT_UUID, FIELD_TYPE_TEXT)  ,
   new Meta(StatusTableSchemaBase.STATUS, FIELD_TYPE_TEXT)  ,
   new Meta(StatusTableSchemaBase.ACTOR, FIELD_TYPE_TEXT)  ,
   new Meta(StatusTableSchemaBase.MODIFIED_DATE, FIELD_TYPE_TIMESTAMP)  
};

public static final String[] STATUS_KEY_COLUMNS = new String[] {
};

public static class StatusTableSchemaBase implements BaseColumns {
   protected StatusTableSchemaBase() {} // No instantiation.

   /**
    * The content:// style URL for this table
    */
   public static final Uri CONTENT_URI =
      Uri.parse("content://"+AUTHORITY+"/status");

   public static Uri getUri(Cursor cursor) {
     final Integer id = cursor.getInt(cursor.getColumnIndex(BaseColumns._ID));
     return  Uri.withAppendedPath(StatusTableSchemaBase.CONTENT_URI, id.toString());
   }

   /**
    * The MIME type of {@link #CONTENT_URI} providing a directory
    */
   public static final String CONTENT_TYPE =
      ContentResolver.CURSOR_DIR_BASE_TYPE+"/vnd.edu.vu.isis.ammo.dash.status";

   /**
    * A mime type used for publisher subscriber.
    */
   public static final String CONTENT_TOPIC =
      "ammo/edu.vu.isis.ammo.dash.status";

   /**
    * The MIME type of a {@link #CONTENT_URI} sub-directory of a single status entry.
    */
   public static final String CONTENT_ITEM_TYPE = 
      ContentResolver.CURSOR_ITEM_BASE_TYPE+"/vnd.edu.vu.isis.ammo.dash.status";


   public static final String DEFAULT_SORT_ORDER = ""; //"modified_date DESC";

   // ========= Field Name and Type Constants ================
      /** 
      * Description: 
      * <P>Type: TEXT</P> 
      */
          public static final String EVENT_UUID = "event_uuid";
      /** 
      * Description: A record of an action on the event.
      * <P>Type: TEXT</P> 
      */
          public static final String STATUS = "status";
      /** 
      * Description: The application key indicating the last use.
      * <P>Type: TEXT</P> 
      */
          public static final String ACTOR = "actor";
      /** 
      * Description: 
      * <P>Type: TIMESTAMP</P> 
      */
          public static final String MODIFIED_DATE = "modified_date";


   public static final String _DISPOSITION = "_disp"; 

   public static final String _RECEIVED_DATE = "_received_date";

// BEGIN CUSTOM STATUS_SCHEMA PROPERTIES
// END   CUSTOM STATUS_SCHEMA PROPERTIES
} 
public static final Meta[] CATEGORY_CURSOR_COLUMNS = new Meta[] {
new Meta(CategoryTableSchemaBase.MAIN_CATEGORY, FIELD_TYPE_TEXT)  ,
   new Meta(CategoryTableSchemaBase.SUB_CATEGORY, FIELD_TYPE_TEXT)  ,
   new Meta(CategoryTableSchemaBase.TIGR_ID, FIELD_TYPE_TEXT)  ,
   new Meta(CategoryTableSchemaBase.ICON_TYPE, FIELD_TYPE_TEXT), 
new Meta(CategoryTableSchemaBase.ICON, FIELD_TYPE_FILE)  
};

public static final String[] CATEGORY_KEY_COLUMNS = new String[] {
};

public static class CategoryTableSchemaBase implements BaseColumns {
   protected CategoryTableSchemaBase() {} // No instantiation.

   /**
    * The content:// style URL for this table
    */
   public static final Uri CONTENT_URI =
      Uri.parse("content://"+AUTHORITY+"/category");

   public static Uri getUri(Cursor cursor) {
     final Integer id = cursor.getInt(cursor.getColumnIndex(BaseColumns._ID));
     return  Uri.withAppendedPath(CategoryTableSchemaBase.CONTENT_URI, id.toString());
   }

   /**
    * The MIME type of {@link #CONTENT_URI} providing a directory
    */
   public static final String CONTENT_TYPE =
      ContentResolver.CURSOR_DIR_BASE_TYPE+"/vnd.edu.vu.isis.ammo.dash.category";

   /**
    * A mime type used for publisher subscriber.
    */
   public static final String CONTENT_TOPIC =
      "ammo/edu.vu.isis.ammo.dash.category";

   /**
    * The MIME type of a {@link #CONTENT_URI} sub-directory of a single category entry.
    */
   public static final String CONTENT_ITEM_TYPE = 
      ContentResolver.CURSOR_ITEM_BASE_TYPE+"/vnd.edu.vu.isis.ammo.dash.category";


   public static final String DEFAULT_SORT_ORDER = ""; //"modified_date DESC";

   // ========= Field Name and Type Constants ================
      /** 
      * Description: 
      * <P>Type: TEXT</P> 
      */
          public static final String MAIN_CATEGORY = "main_category";
      /** 
      * Description: 
      * <P>Type: TEXT</P> 
      */
          public static final String SUB_CATEGORY = "sub_category";
      /** 
      * Description: 
      * <P>Type: TEXT</P> 
      */
          public static final String TIGR_ID = "tigr_id";
      /** 
      * Description: 
      * <P>Type: FILE</P> 
      */
          public static final String ICON_TYPE = "icon_type";
          public static final String ICON = "icon";


   public static final String _DISPOSITION = "_disp"; 

   public static final String _RECEIVED_DATE = "_received_date";

// BEGIN CUSTOM CATEGORY_SCHEMA PROPERTIES
// END   CUSTOM CATEGORY_SCHEMA PROPERTIES
} 

 
}
