package ph.gardenia.com.callback;

/**
 * Created by otomatik on 8/10/15.
 */
public interface VolleyCallback {

    /*This object creates callback which notify the application layer
    * the state of */

    /*Volley Response*/
    int ON_RESPONSE_SUCCESS = 10;
    int ON_RESPONSE_JSON_ERROR = 11;
    int ON_RESPONSE_FAILED = 12;
    int ON_RESPONSE_BLANK = 13;

    /*Feeder Response*/
    int ON_FEEDER_STATE_MDSC = 14;
    int ON_FEEDER_STATE_MDSC_ITEMS = 15;
    int ON_FEEDER_STATE_PRODUCT_PRICE = 16;
    int ON_FEEDER_STATE_PRODUCT = 17;
    int ON_FEEDER_STATE_CHILD_ROUTE = 18;
    int ON_FEEDER_STATE_CHILD_USER = 19;
    int ON_FEEDER_STATE_CHILD_REQUIRED_DOCS = 20;
    int ON_FEEDER_STATE_CHILD_CUSTOMER = 21;
    int ON_FEEDER_STATE_CHILD_DSC = 22;
    int ON_FEEDER_STATE_CHILD_DSC_ITEMS = 23;
    int ON_FEEDER_STATE_CHILD_FORECAST = 24;
    int ON_FEEDER_STATE_CHILD_FORECAST_ITEMS = 25;
    int ON_FEEDER_STATE_CHILD_REFERENCE_NO = 26;

    /*Sfa Response*/

    /*PreInvoice Response*/

    /*Sfa-Cindy Response*/

    void onSuccess(int result);

    void onFailed(int result);

    void onStringResponse(String result);

}
