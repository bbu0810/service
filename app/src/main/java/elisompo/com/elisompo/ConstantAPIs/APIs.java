package elisompo.com.elisompo.ConstantAPIs;

import android.app.Activity;
import android.os.AsyncTask;
import android.provider.SyncStateContract;

import java.util.Map;

import elisompo.com.elisompo.Interface.AAInterface;
import elisompo.com.elisompo.Interface.Constants;

public class APIs implements Constants{
    public static void getOrderList(AAInterface aaInterface){
        ConnectToServer connectToServer = new ConnectToServer();
        connectToServer.execute(BASIC_URL);
    }

}
