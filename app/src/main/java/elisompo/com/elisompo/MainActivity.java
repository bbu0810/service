package elisompo.com.elisompo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import org.json.JSONObject;

import elisompo.com.elisompo.ConstantAPIs.APIs;
import elisompo.com.elisompo.Interface.AAInterface;
import elisompo.com.elisompo.Interface.Constants;
import elisompo.com.elisompo.Interface.GlobalConstants;

public class MainActivity extends AppCompatActivity implements Constants{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AAInterface aaInterface = new AAInterface() {
            @Override
            public void getOrderListSuccess(String resphoneMessage) {
                try{
                    JSONObject jsonObject = new JSONObject(resphoneMessage);
                    GlobalConstants.sPHONE = jsonObject.getString(PHONE);
                    GlobalConstants.sPHONE = jsonObject.getString(PHONE);
                    GlobalConstants.sPHONE = jsonObject.getString(PHONE);
                    GlobalConstants.sPHONE = jsonObject.getString(PHONE);
                    GlobalConstants.sPHONE = jsonObject.getString(PHONE);
                    GlobalConstants.sPHONE = jsonObject.getString(PHONE);

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
                Toast.makeText(MainActivity.this, "ERROR", Toast.LENGTH_LONG).show();
            }
        };
        APIs.getOrderList(aaInterface);
    }
}
