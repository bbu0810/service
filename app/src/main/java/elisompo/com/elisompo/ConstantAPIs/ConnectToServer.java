package elisompo.com.elisompo.ConstantAPIs;

import android.os.AsyncTask;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MIME;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

import elisompo.com.elisompo.Interface.AAInterface;
import elisompo.com.elisompo.Interface.Constants;

public class ConnectToServer extends AsyncTask<String, Void, String> implements Constants {

    HttpClient httpClient;
    HashMap<String, String> mParamMap;
    AAInterface aaInterface;

    public ConnectToServer(HashMap<String, String> mParamMap, AAInterface aaInterface) {
        this.mParamMap = mParamMap;
        this.aaInterface = aaInterface;
    }

    public ConnectToServer(AAInterface aaInterface){
        mParamMap = new HashMap<String, String>();
        this.aaInterface = aaInterface;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        httpClient = new DefaultHttpClient();
    }

    @Override
    protected String doInBackground(String... strings) {
        try{
            HttpConnectionParams.setConnectionTimeout(httpClient.getParams(), 10000);
            MultipartEntityBuilder builder = MultipartEntityBuilder.create();
            for (String key : mParamMap.keySet()) {
                builder.addTextBody(key, mParamMap.get(key), ContentType.create("text/plain", MIME.UTF8_CHARSET));
            }
            HttpPost httpPost = new HttpPost(strings[0]);   // strings[0] is url sent from function that call this function
            httpPost.setEntity(builder.build());
            HttpResponse httpResponse = httpClient.execute(httpPost);
            String responseBody = EntityUtils.toString(httpResponse.getEntity());
            return responseBody;
        } catch(Exception error){
            return null;
        }
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        aaInterface.getOrderListSuccess(s);
    }

}
