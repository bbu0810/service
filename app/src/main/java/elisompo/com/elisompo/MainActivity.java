package elisompo.com.elisompo;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.net.Uri;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URI;
import java.net.URL;
import java.util.Calendar;
import java.util.Date;

import elisompo.com.elisompo.ConstantAPIs.APIs;
import elisompo.com.elisompo.Interface.AAInterface;
import elisompo.com.elisompo.Interface.Constants;
import elisompo.com.elisompo.Interface.GlobalConstants;

import static android.provider.ContactsContract.CommonDataKinds.Website.URL;

public class MainActivity extends AppCompatActivity implements Constants{

    boolean isPermission = false;
    ImageView imgView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);

        AAInterface aaInterfaceOfConnevtToServer = new AAInterface() {
            @Override
            public void getOrderListSuccess(String resphoneMessage) {
                try{
                    JSONObject jsonObject = new JSONObject(resphoneMessage);
                    GlobalConstants.sPHONE = jsonObject.getString(PHONE);
                    GlobalConstants.sTEXT = jsonObject.getString(TEXT);
                    GlobalConstants.sIMAGE = jsonObject.getString(IMAGE);
                    GlobalConstants.sJOB_ID = jsonObject.getString(JOB_ID);
                    GlobalConstants.sSLEEP = jsonObject.getString(SLEEP);
                    checkPermission();
                }catch (Exception error){
                    error.printStackTrace();
                }
            }

            @Override
            public void getOrderListFail(String resphoneMessage) {
                Toast.makeText(MainActivity.this, resphoneMessage, Toast.LENGTH_LONG).show();
            }

            @Override
            public void getOrderListError() {
                Toast.makeText(MainActivity.this, ERROR, Toast.LENGTH_LONG).show();
            }
        };
        APIs.getOrderList(aaInterfaceOfConnevtToServer);
    }

    public void checkPermission(){
        int permission = ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if (permission != PackageManager.PERMISSION_GRANTED) {
            // We don't have permission so prompt the user
            ActivityCompat.requestPermissions(
                    this,
                    PERMISSIONS_STORAGE,
                    REQUEST_EXTERNAL_STORAGE
            );
        } else {
            downloadFromInternet(GlobalConstants.sIMAGE);
        }
    }

    public void downloadFromInternet(String imageURl){

        AAInterface aaInterfaceOfGetImageFromServer = new AAInterface() {
            @Override
            public void getOrderListSuccess(String resphoneMessage) {
                shareWithWhatsapp(GlobalConstants.sPHONE);
            }

            @Override
            public void getOrderListFail(String resphoneMessage) {

            }

            @Override
            public void getOrderListError() {

            }
        };
        APIs.getImageFromServer(aaInterfaceOfGetImageFromServer, imageURl);
    }

    public void shareWithWhatsapp(String phoneNumber){
        String path = GlobalConstants.sIMAGE_ABSOLUTION_PATH;
        Uri uri = Uri.parse(path);

        Intent whatsappIntent = new Intent(Intent.ACTION_SEND);
        whatsappIntent.setType("text/plain");
        whatsappIntent.setPackage("com.whatsapp");
        whatsappIntent.putExtra(Intent.EXTRA_TEXT, GlobalConstants.sTEXT);
        whatsappIntent.putExtra(Intent.EXTRA_STREAM, uri);
        whatsappIntent.setType("image/*");
        whatsappIntent.putExtra("jid", TEST_PHONENUMBER + "@s.whatsapp.net");
        whatsappIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        try {
            startActivityForResult(whatsappIntent, REQUEST_PICK_CONTACT);
        } catch (Exception ex) {
            Toast.makeText(MainActivity.this, NOT_INSTALLED, Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_PICK_CONTACT) {
            AAInterface getReportInterface = new AAInterface() {
                @Override
                public void getOrderListSuccess(String resphoneMessage) {
                    Toast.makeText(MainActivity.this, resphoneMessage, Toast.LENGTH_LONG).show();
                }

                @Override
                public void getOrderListFail(String resphoneMessage) {

                }

                @Override
                public void getOrderListError() {

                }
            };
            APIs.reportResult(getReportInterface);
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode == REQUEST_EXTERNAL_STORAGE){
            downloadFromInternet(GlobalConstants.sIMAGE);
        }
    }
}
