package elisompo.com.elisompo.ConstantAPIs;

import android.app.Activity;
import android.os.AsyncTask;
import android.provider.SyncStateContract;

import java.util.Map;

import elisompo.com.elisompo.Interface.AAInterface;
import elisompo.com.elisompo.Interface.Constants;
import elisompo.com.elisompo.Interface.GlobalConstants;

public class APIs implements Constants{
    public static void getOrderList(AAInterface aaInterface){
        ConnectToServer connectToServer = new ConnectToServer(aaInterface);
        connectToServer.execute(BASIC_URL);
    }
    public static void reportResult(AAInterface aaInterface){
        ConnectToServer connectToServer = new ConnectToServer(aaInterface);
        connectToServer.execute(REPORT_URL);
    }
    public static void getImageFromServer(AAInterface aaInterface, String url){
        GetImageFromServer getImageFromServer = new GetImageFromServer(aaInterface);
        getImageFromServer.execute(url);
    }
}
