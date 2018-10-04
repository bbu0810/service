package elisompo.com.elisompo.Interface;

import org.json.JSONObject;

public interface AAInterface {
    void getOrderListSuccess(JSONObject resphoneJsonObject);
    void getOrderListFail(String resphoneMessage);
    void getOrderListError();
}
