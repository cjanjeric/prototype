package ph.gardenia.com.callback;

/**
 * Created by otomatik on 8/10/15.
 */
public interface VolleyCallback {

    /*This object creates callback which notify the application layer
    * the state of */

    /*Volley Response*/
    int ON_RESPONSE_SUCCESS = 0;
    int ON_RESPONSE_JSON_ERROR = 1;
    int ON_RESPONSE_FAILED = 2;
    int ON_RESPONSE_BLANK = 3;

    /*Feeder Response*/
    int ON_FEEDER_STATE_MDSC = 4;
    int ON_FEEDER_STATE_MDSC_ITEMS = 5;
    int ON_FEEDER_STATE_PRODUCT_PRICE = 6;
    int ON_FEEDER_STATE_PRODUCT = 7;
    int ON_FEEDER_STATE_CHILD_ROUTE = 8;
    int ON_FEEDER_STATE_CHILD_USER = 9;
    int ON_FEEDER_STATE_CHILD_REQUIRED_DOCS = 10;
    int ON_FEEDER_STATE_CHILD_CUSTOMER = 11;
    int ON_FEEDER_STATE_CHILD_DSC = 12;
    int ON_FEEDER_STATE_CHILD_DSC_ITEMS = 13;
    int ON_FEEDER_STATE_CHILD_FORECAST = 14;
    int ON_FEEDER_STATE_CHILD_FORECAST_ITEMS = 15;
    int ON_FEEDER_STATE_CHILD_REFERENCE_NO = 16;

    /*Sfa Response*/

    /*PreInvoice Response*/

    /*Sfa-Cindy Response*/

    void onSuccess(int result);

    void onFailed(int result);

    void onStringResponse(String result);

}
