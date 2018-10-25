package elisompo.com.elisompo.ConstantAPIs;

import android.os.AsyncTask;
import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import elisompo.com.elisompo.Interface.AAInterface;
import elisompo.com.elisompo.Interface.Constants;
import elisompo.com.elisompo.Interface.GlobalConstants;

public class GetImageFromServer extends AsyncTask<String, Void, String> implements Constants {

    AAInterface aaInterface;

    public GetImageFromServer(AAInterface aaInterface) {
        this.aaInterface = aaInterface;
    }

    @Override
    protected String doInBackground(String... strings) {
        try{
            URL url = new URL(strings[0]);
            InputStream input = url.openStream();
            try{
                File storagePath = Environment.getExternalStorageDirectory();
                String timeStamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
                String filename = LOGO_NAME + timeStamp + ".png";
                File file = new File(Environment.getExternalStorageDirectory(), filename);
                OutputStream output = new FileOutputStream(file);
                try{
                    byte[] buffer = new byte[2048];
                    int bytesRead = 0;
                    while ((bytesRead = input.read(buffer, 0, buffer.length)) >= 0) {
                        output.write(buffer, 0, bytesRead);
                    }
                }catch (Exception error){
                    Log.e(ERROR, error.toString());
                }finally {
                    output.close();
                    GlobalConstants.sIMAGE_ABSOLUTION_PATH = file.getAbsolutePath();
                    return storagePath.getAbsolutePath();
                }
            }catch (FileNotFoundException error){
                Log.e(ERROR, error.toString());
            }finally {
                input.close();
            }
        }catch (IOException error){
            Log.e(ERROR, error.toString());
        }
        return null;
    }
    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        aaInterface.getOrderListSuccess(s);
    }
}
