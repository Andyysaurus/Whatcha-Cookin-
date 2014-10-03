package com.example.moletracker;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

public class Photos extends Activity {
	PhotoAdapter molephotos;

	public SQLiteDatabase db;
	private DataBaseHelper dbHelper;
	
	private static final int CAMERA_REQUEST = 1888;

    static String mole_ImagePath = "";
    private static File f;
    private static int Take_Photo = 2;
    static String mole_Image_Name = "";
    public static String saveFolderName;
    private static File molePhotoDirectory;
    Bitmap bitmap;
    int storeposition = 0;
    
    
    public final static String EXTRA_BACK_LABEL = "com.example.moletracker.BACK_LABEL";
    String part; //area
    String backlabel; //location
    
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyymmddhhmmss");
    final String date = dateFormat.format(new Date()); //kirsty woz ere
    
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.molephotos);
        
        Intent intent = getIntent();
        backlabel = intent.getStringExtra(Area.EXTRA_FORWARD_LABEL);
        part = intent.getStringExtra(Area.EXTRA_LABEL);
        setTitle(backlabel);
        
        molephotos = new PhotoAdapter(this);
        molephotos = molephotos.open();
        
        GridView gridView = (GridView)findViewById(R.id.moleview);
        gridView.setAdapter(new GridViewAdapter(this));;
        
        
        
        
        /*gridView.setAdapter(new ImageAdapter(this));

        gridView.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                Toast.makeText(Photos.this, "" + position, Toast.LENGTH_SHORT).show();
            }
        });*/


    }

    public void takePhoto(View v) {
    	molephotos = new PhotoAdapter(this);
        molephotos = molephotos.open();
        
        saveFolderName = Environment.getExternalStorageDirectory().getAbsolutePath() + "/moletracker";
        molePhotoDirectory = new File(saveFolderName);
        if (!molePhotoDirectory.exists()) {
                    molePhotoDirectory.mkdirs();
        }
        
        //SimpleDateFormat dateFormat = new SimpleDateFormat("yyyymmddhhmmss");
        //String date = dateFormat.format(new Date()); //kirsty woz ere
        
        mole_Image_Name = date + ".jpg";
        mole_ImagePath = saveFolderName + "/" + part + "_" + backlabel + "_" + date + ".jpg";
        
        System.err.println("mole_ImagePath = " + mole_ImagePath);
        
        molephotos.insertPhotoEntry(backlabel, part, mole_ImagePath);
        
        f = new File(mole_ImagePath);
        startActivityForResult(new Intent(MediaStore.ACTION_IMAGE_CAPTURE).putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(f)),Take_Photo);
        System.err.println("f  " + f);
    }
     
    


    // used to create random numbers
    /*public String nextSessionId() {
        SecureRandom random = new SecureRandom();
        return new BigInteger(130, random).toString(32);
    }*/


    protected void onActivityResult(int requestCode, int resultCode, Intent data) {  //recreate
        if (requestCode == Take_Photo) {
            String filePath = null;

            filePath = mole_ImagePath;
            if (filePath != null) {
                //Bitmap faceView = (new_decode(new File(filePath)));
                //imageView.setImageBitmap(faceView);
            	
            	GridView gridView = (GridView)findViewById(R.id.moleview);
            	gridView.setAdapter(new GridViewAdapter(this));;

            } else {
                bitmap = null;
            }
        }
    	
    }

    public static Bitmap new_decode(File f) {

        // decode image size

        BitmapFactory.Options o = new BitmapFactory.Options();
        o.inJustDecodeBounds = true;
        o.inDither = false; // Disable Dithering mode

        o.inPurgeable = true; // Tell to gc that whether it needs free memory,
                                // the Bitmap can be cleared

        o.inInputShareable = true; // Which kind of reference will be used to
                                    // recover the Bitmap data after being
                                    // clear, when it will be used in the future
        try {
            BitmapFactory.decodeStream(new FileInputStream(f), null, o);
        } catch (FileNotFoundException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }

        // Find the correct scale value. It should be the power of 2.
        final int REQUIRED_SIZE = 300;
        int width_tmp = o.outWidth, height_tmp = o.outHeight;
        int scale = 1;
        while (true) {
            if (width_tmp / 1.5 < REQUIRED_SIZE && height_tmp / 1.5 < REQUIRED_SIZE)
                break;
            width_tmp /= 1.5;
            height_tmp /= 1.5;
            scale *= 1.5;
        }

        // decode with inSampleSize
        BitmapFactory.Options o2 = new BitmapFactory.Options();
        // o2.inSampleSize=scale;
        o.inDither = false; // Disable Dithering mode

        o.inPurgeable = true; // Tell to gc that whether it needs free memory,
                                // the Bitmap can be cleared

        o.inInputShareable = true; // Which kind of reference will be used to
                                    // recover the Bitmap data after being
                                    // clear, when it will be used in the future
        // return BitmapFactory.decodeStream(new FileInputStream(f), null, o2);
        try {

//          return BitmapFactory.decodeStream(new FileInputStream(f), null,
//                  null);
            Bitmap bitmap= BitmapFactory.decodeStream(new FileInputStream(f), null, null);
            System.out.println(" IW " + width_tmp);
            System.out.println("IHH " + height_tmp);           
               int iW = width_tmp;
                int iH = height_tmp;

               return Bitmap.createScaledBitmap(bitmap, iW, iH, true);

        } catch (OutOfMemoryError e) {
            // TODO: handle exception
            e.printStackTrace();
            // clearCache();

            // System.out.println("bitmap creating success");
            System.gc();
            return null;
            // System.runFinalization();
            // Runtime.getRuntime().gc();
            // System.gc();
            // decodeFile(f);
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        }

    }
    
    /*public String getDateName(String date) { //must be a better way
    	String yyyymmdd = date.substring(0, 8);
    	String dd = yyyymmdd.substring(6);
    	String yyyy = yyyymmdd.substring(0, 4);
    	int month = Integer.parseInt(yyyymmdd.substring(4, 6));
    	String[] monthNames = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
    	String monthString = monthNames[month-1];
    	String dateName = dd + " " + monthString + " " + yyyy;
    	return dateName;
    }*/

    public void back(View view) {
        Intent intent = new Intent(this, Area.class);
        intent.putExtra(EXTRA_BACK_LABEL, part);
        startActivity(intent);
    }

    
    
    private class GridViewAdapter extends BaseAdapter {
        private List<Item> items = new ArrayList<Item>();
        private LayoutInflater inflater;

        public GridViewAdapter(Context context) {
        
        int pc = molephotos.countPhotos(part, backlabel);
        	
        if (pc>0) {
            inflater = LayoutInflater.from(context);
            for(int i=0; i<pc; i++) {
            	String name = molephotos.photoNames(part, backlabel) [i];
            	//String text = getDateName(date);
            	items.add(new Item(name, new_decode(new File(name))));
            }
        }
            
        }

        @Override
        public int getCount() {
            return items.size();
        }

        @Override
        public Object getItem(int i)
        {
            return items.get(i);
        }

        @Override
        public long getItemId(int i)
        {
            return 0;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup)
        {
            View v = view;
            ImageView picture;
            TextView name;

            if(v == null)
            {
               v = inflater.inflate(R.layout.gridview_item, viewGroup, false);
               v.setTag(R.id.picture, v.findViewById(R.id.picture));
               v.setTag(R.id.text, v.findViewById(R.id.text));
            }

            picture = (ImageView)v.getTag(R.id.picture);
            name = (TextView)v.getTag(R.id.text);

            Item item = (Item)getItem(i);

            picture.setImageBitmap(item.filename);
            name.setText(item.name);

            return v;
        }

        private class Item
        {
            final String name;
            final Bitmap filename;

            Item(String name, Bitmap filename)
            {
                this.name = name;
                this.filename = filename;
            }
        }
    }
}