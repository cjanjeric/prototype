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

    /*Downline Response*/
    int ON_DOWLINE_STATE_DSC = 14;
    int ON_DOWLINE_STATE_DSC_ITEMS = 15;
    int ON_DOWLINE_STATE_FORECAST = 16;
    int ON_DOWLINE_STATE_FORECAST_ITEMS = 17;
    int ON_DOWLINE_STATE_PRODUCT = 18;
    int ON_DOWLINE_STATE_PRODUCT_PRICE = 19;
    int ON_DOWLINE_STATE_REQUIRED_DOCS = 20;
    int ON_DOWLINE_STATE_CUSTOMER = 21;
    int ON_DOWLINE_STATE_REFERENCE = 22;

    /*Feeder Response*/
    int ON_FEEDER_STATE_MDSC = 23;
    int ON_FEEDER_STATE_MDSC_ITEMS = 24;
    int ON_FEEDER_STATE_PRODUCT_PRICE = 25;
    int ON_FEEDER_STATE_PRODUCT = 26;
    int ON_FEEDER_STATE_CHILD_ROUTE = 27;
    int ON_FEEDER_STATE_CHILD_USER = 28;
    int ON_FEEDER_STATE_CHILD_REQUIRED_DOCS = 29;
    int ON_FEEDER_STATE_CHILD_CUSTOMER = 30;
    int ON_FEEDER_STATE_CHILD_DSC = 31;
    int ON_FEEDER_STATE_CHILD_DSC_ITEMS = 32;
    int ON_FEEDER_STATE_CHILD_FORECAST = 33;
    int ON_FEEDER_STATE_CHILD_FORECAST_ITEMS = 34;
    int ON_FEEDER_STATE_CHILD_REFERENCE_NO = 35;

    /*Sfa Response*/

    /*PreInvoice Response*/

    /*Sfa-Cindy Response*/

    void onSuccess(int result);

    void onFailed(int result);

    void onStringResponse(String result);

}
