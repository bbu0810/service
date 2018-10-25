package elisompo.com.elisompo.Interface;

import org.json.JSONObject;

public interface AAInterface {
    void getOrderListSuccess(String resphoneMessage);
    void getOrderListFail(String resphoneMessage);
    void getOrderListError();
}
