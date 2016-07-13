package ph.gardenia.com.request;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Base64;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.UnknownServiceException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ph.gardenia.com.callback.VolleyCallback;
import ph.gardenia.com.fragment.LoginFragment;
import ph.gardenia.com.helper.DownlineCustomerHelper;
import ph.gardenia.com.helper.DownlineDscHelper;
import ph.gardenia.com.helper.DownlineDscItemHelper;
import ph.gardenia.com.helper.DownlineForecastHelper;
import ph.gardenia.com.helper.DownlineProductHelper;
import ph.gardenia.com.helper.DownlineProductPriceHelper;
import ph.gardenia.com.helper.RouteHelper;
import ph.gardenia.com.helper.UserHelper;
import ph.gardenia.com.model.DownlineCustomer;
import ph.gardenia.com.model.DownlineDsc;
import ph.gardenia.com.model.DownlineDscItem;
import ph.gardenia.com.model.DownlineForecast;
import ph.gardenia.com.model.DownlineProduct;
import ph.gardenia.com.model.DownlineProductPrice;
import ph.gardenia.com.model.Route;
import ph.gardenia.com.model.User;
import ph.gardenia.com.utils.Constant;
import ph.gardenia.com.utils.Singleton;

/**
 * Created by otomatik on 6/7/16.
 */
public class PullRequest {

    /*This Object Pull data from web API*/

    private String TAG = getClass().getSimpleName();
    private Context context;
    private Gson gson;

    public PullRequest(Context context) {
        this.context = context;
        gson = new Gson();
    }

    public void getRoute(final String routeCode, final VolleyCallback callback) {

        /*This method requires route code and use it to request
         * data for specific route to the WebService */

        Log.d(TAG, "ROUTE CODE " + routeCode);

        StringRequest request = new StringRequest(Request.Method.POST, Constant.BASE_DOWNLINE_URL + "routes/get_assigned_route", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Log.d(TAG, "ROUTE RESPONSE " + response);

                try {

                    /*Get the array Json*/
                    JSONArray jsonArray = new JSONArray(response);

                    for (int i = 0; i < jsonArray.length(); i++) {

                       /*Get the index of array and put it in the object*/
                        JSONObject jsonObject = jsonArray.getJSONObject(i);

                       /*Get the object from the json array*/
                        JSONObject routeObject = jsonObject.getJSONObject("route");

                        Log.d(TAG, "JSON ROUTE CID " + routeObject.getInt("cid"));
                        Log.d(TAG, "JSON ROUTE CODE " + routeObject.get("code_no"));
                        Log.d(TAG, "JSON ROUTE DESCRIPTION " + routeObject.getString("descript"));
                        Log.d(TAG, "JSON ROUTE SENIOR " + routeObject.getInt("senior_personnel"));
                        Log.d(TAG, "JSON ROUTE TRUCKER " + routeObject.getInt("trucker"));
                        Log.d(TAG, "JSON ROUTE DISPATCHER ID " + routeObject.getInt("dispatcher_id"));
                        Log.d(TAG, "JSON ROUTE LOADING ASSIGNMENT " + routeObject.getInt("loading_assignment_id"));

                       /*Pass the Json data to object POJO*/
                        Route route = new Route();
                        route.cid = routeObject.getInt("cid");
                        route.codeNo = routeObject.getString("code_no");
                        route.descript = routeObject.getString("descript");
                        route.seniorPersonnel = routeObject.getInt("senior_personnel");
                        route.truckNo = routeObject.getInt("trucker");
                        route.dispatcherId = routeObject.getInt("dispatcher_id");
                        route.loadingAssignmentId = routeObject.getInt("loading_assignment_id");

                       /*Save the object to the db*/
                        RouteHelper routeHelper = new RouteHelper(route);
                        routeHelper.save();

                        Log.d(TAG, "FIN");

                        switch (route.loadingAssignmentId) {

                            case Constant.IS_REGULAR_DOWNLINE:
                                callback.onSuccess(Constant.IS_REGULAR_DOWNLINE);
                                break;
                            case Constant.IS_FEEDER_DOWNLINE:
                                callback.onSuccess(Constant.IS_FEEDER_DOWNLINE);
                                break;
                            case Constant.IS_PREINVOICE_DOWNLINE:
                                callback.onSuccess(Constant.IS_PREINVOICE_DOWNLINE);
                                break;
                            case Constant.IS_FEEDER:
                                callback.onSuccess(Constant.IS_FEEDER);
                                break;
                            case Constant.IS_BO:
                                callback.onSuccess(Constant.IS_BO);
                                break;
                        }

                    }

                } catch (JSONException e) {

                    Log.d(TAG, "JSONException " + e.toString());
                    callback.onFailed(VolleyCallback.ON_RESPONSE_JSON_ERROR);
                    e.printStackTrace();

                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Log.d(TAG, "ROUTE ERROR " + error.getMessage());
                error.printStackTrace();
                callback.onFailed(VolleyCallback.ON_RESPONSE_FAILED);

            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("token", Constant.KEY);
                params.put("route_code", routeCode);
                return params;
            }
        };

        /*Set timeout for 50 seconds and tell not to retry the request*/
        request.setRetryPolicy(new DefaultRetryPolicy(50000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        /*Singleton class to add request*/
        Singleton.getInstance(context.getApplicationContext()).addToRequestQueue(request);

    }

    public void getUser(final String userName, final String password, final String routeCode, final VolleyCallback callback) {

        /*This method requires username, password and route code and use it to request
         * data for specific user table in the WebService */

        Log.d(TAG, "ROUTE CODE " + routeCode);
        Log.d(TAG, "USERNAME " + userName);
        Log.d(TAG, "PASSWORD " + password);


        StringRequest request = new StringRequest(Request.Method.POST, Constant.BASE_DOWNLINE_URL + "users/auth/", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Log.d(TAG, "USER RESPONSE " + response);


                try {

                    JSONArray jsonArray = new JSONArray(response);

                    for (int i = 0; i < jsonArray.length(); i++) {

                        JSONObject jsonObject = jsonArray.getJSONObject(i);

                        JSONObject userObject = jsonObject.getJSONObject("user");

                        User user = new User();

                        user.empId = userObject.getInt("emp_id");
                        user.cid = userObject.getInt("cid");
                        user.empCode = userObject.getString("emp_code");
                        byte[] firstName = Base64.decode(userObject.getString("firstname"), Base64.DEFAULT);
                        user.firstName = new String(firstName, "UTF-8");
                        byte[] middleName = Base64.decode(userObject.getString("middlename"), Base64.DEFAULT);
                        user.middleName = new String(middleName, "UTF-8");
                        byte[] lastName = Base64.decode(userObject.getString("lastname"), Base64.DEFAULT);
                        user.lastName = new String(lastName, "UTF-8");
                        user.userName = userObject.getString("username");
                        user.password = userObject.getString("password");
                        user.userGroup = userObject.getInt("user_group");
                        user.canReallocate = userObject.getInt("can_reallocate");
                        user.completeName = user.lastName + " " + user.firstName;

                        Log.d(TAG, "JSON USER EMPID " + user.empId);
                        Log.d(TAG, "JSON USER CID " + user.cid);
                        Log.d(TAG, "JSON USER EMPCODE " + user.empCode);
                        Log.d(TAG, "JSON USER FIRSTNAME " + user.firstName);
                        Log.d(TAG, "JSON USER LASTNAME " + user.lastName);
                        Log.d(TAG, "JSON USER MIDDLENAME " + user.middleName);
                        Log.d(TAG, "JSON USER USERNAME " + user.userName);
                        Log.d(TAG, "JSON USER PASSWORD " + user.password);
                        Log.d(TAG, "JSON USER USERGROUP " + user.userGroup);
                        Log.d(TAG, "JSON USER CANREALLOCATE " + user.canReallocate);

                        UserHelper userHelper = new UserHelper(user);
                        userHelper.save();

                        Log.d(TAG, "FIN");
                        callback.onSuccess(VolleyCallback.ON_RESPONSE_SUCCESS);

                    }

                } catch (JSONException e) {

                    Log.d(TAG, "JSONException " + e.toString());
                    callback.onFailed(VolleyCallback.ON_RESPONSE_JSON_ERROR);
                    e.printStackTrace();

                } catch (UnsupportedEncodingException e) {

                    Log.d(TAG, "UnsupportedEncodingException " + e.toString());

                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Log.d(TAG, "USER ERROR " + error.getMessage());
                error.printStackTrace();
                callback.onFailed(VolleyCallback.ON_RESPONSE_FAILED);

            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("token", Constant.KEY);
                params.put("route_code", routeCode);
                params.put("username", userName);
                params.put("password", password);
                return params;
            }
        };

        /*Set timeout for 50 seconds and tell not to retry the request*/
        request.setRetryPolicy(new DefaultRetryPolicy(50000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        /*Singleton class to add request*/
        Singleton.getInstance(context.getApplicationContext()).addToRequestQueue(request);

    }

    public void getProduct(final VolleyCallback callback) {

        Log.d(TAG, "PRODUCT");

        StringRequest request = new StringRequest(Request.Method.POST, Constant.BASE_DOWNLINE_URL + "products/get/", new Response.Listener<String>() {
            @Override
            public void onResponse(final String response) {


                new GetProduct(response, callback).execute();


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("token", Constant.KEY);
                return params;
            }
        };

        /*Set timeout for 50 seconds and tell not to retry the request*/
        request.setRetryPolicy(new DefaultRetryPolicy(50000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        /*Singleton class to add request*/
        Singleton.getInstance(context.getApplicationContext()).addToRequestQueue(request);

    }

    public class GetProduct extends AsyncTask<Integer, Integer, Integer> {

        VolleyCallback callback;
        String response;

        public GetProduct(String response, VolleyCallback callback) {
            this.response = response;
            this.callback = callback;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);

            switch (values[0]) {
                case VolleyCallback.ON_DOWLINE_STATE_PRODUCT:
                    callback.onSuccess(VolleyCallback.ON_DOWLINE_STATE_PRODUCT);
                    break;
            }

        }

        @Override
        protected Integer doInBackground(Integer... integers) {


            try {
                JSONArray jsonArray = new JSONArray(response);

                for (int index = 0; index < jsonArray.length(); index++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(index);
                    JSONObject jsonProduct = jsonObject.getJSONObject("product");

                    DownlineProduct product = new DownlineProduct();
                    product.cid = jsonProduct.getInt("cid");
                    product.prodCode = jsonProduct.getString("prod_code");
                    product.alias = jsonProduct.getString("prod_code2");
                    product.description = jsonProduct.getString("descript");
                    product.srp = jsonProduct.getDouble("srp");
                    product.packsPerTray = jsonProduct.getInt("packs_per_tray");

                    // Decodes the barcode to make it more human readable
                    byte[] barcode = Base64.decode(jsonProduct.getString("barcode"), Base64.DEFAULT);
                    product.barcode = new String(barcode, "UTF-8");

                    product.isActive = (jsonProduct.getInt("isactive") == 1);
                    product.discountExcluded = (jsonProduct.getInt("discount_excluded") == 1);

                    DownlineProductHelper downlineProductHelper = new DownlineProductHelper(product);
                    downlineProductHelper.save();

                    Log.d(TAG, "PRODUCT NAME " + product.description);

                }

                publishProgress(VolleyCallback.ON_DOWLINE_STATE_PRODUCT);

            } catch (JSONException e) {

            } catch (UnsupportedEncodingException e) {

            }

            return null;
        }
    }

    public void getProductPrice(final VolleyCallback callback) {

        Log.d(TAG, "PRODUCT PRICE ");

        StringRequest request = new StringRequest(Request.Method.POST, Constant.BASE_DOWNLINE_URL + "list_price/get/", new Response.Listener<String>() {
            @Override
            public void onResponse(final String response) {

                new GetProductPrice(response, callback).execute();

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("token", Constant.KEY);
                return params;
            }
        };

        /*Set timeout for 50 seconds and tell not to retry the request*/
        request.setRetryPolicy(new DefaultRetryPolicy(50000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        /*Singleton class to add request*/
        Singleton.getInstance(context.getApplicationContext()).addToRequestQueue(request);

    }

    public class GetProductPrice extends AsyncTask<Integer, Integer, Integer> {

        String response;
        VolleyCallback callback;

        public GetProductPrice(String response, VolleyCallback callback) {
            this.response = response;
            this.callback = callback;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);

            switch (values[0]) {
                case VolleyCallback.ON_DOWLINE_STATE_PRODUCT_PRICE:
                    callback.onSuccess(VolleyCallback.ON_DOWLINE_STATE_PRODUCT_PRICE);
                    break;
            }

        }

        @Override
        protected Integer doInBackground(Integer... integers) {

            try {
                JSONArray jsonArray = new JSONArray(response);

                for (int index = 0; index < jsonArray.length(); index++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(index);
                    JSONObject jsonItem = jsonObject.getJSONObject("list_price");

                    DownlineProductPrice productPrice = new DownlineProductPrice();
                    productPrice.discountTypeId = jsonItem.getInt("discount_type_id");
                    productPrice.productCode = jsonItem.getInt("prod_code");
                    productPrice.amount = jsonItem.getDouble("amount");

                    DownlineProductPriceHelper downlineProductPriceHelper = new DownlineProductPriceHelper(productPrice);
                    downlineProductPriceHelper.save();

                    Log.d(TAG, "PRODUCT PRICE " + productPrice.productCode);

                }

                publishProgress(VolleyCallback.ON_DOWLINE_STATE_PRODUCT_PRICE);

            } catch (JSONException e) {

            }


            return null;
        }
    }

    public void getCustomer(final String routeCode, final VolleyCallback callback) {

        Log.d(TAG, "CUSTOMER");

        StringRequest request = new StringRequest(Request.Method.POST, Constant.BASE_DOWNLINE_URL + "customers/get_by_route", new Response.Listener<String>() {
            @Override
            public void onResponse(final String response) {

                new GetCustomer(response, callback).execute();

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("token", Constant.KEY);
                params.put("route_code", routeCode);
                return params;
            }
        };

        /*Set timeout for 50 seconds and tell not to retry the request*/
        request.setRetryPolicy(new DefaultRetryPolicy(50000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        /*Singleton class to add request*/
        Singleton.getInstance(context.getApplicationContext()).addToRequestQueue(request);

    }

    public class GetCustomer extends AsyncTask<Integer, Integer, Integer> {

        String response;
        VolleyCallback callback;

        public GetCustomer(String response, VolleyCallback callback) {
            this.response = response;
            this.callback = callback;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            switch (values[0]) {
                case VolleyCallback.ON_DOWLINE_STATE_CUSTOMER:
                    callback.onSuccess(VolleyCallback.ON_DOWLINE_STATE_CUSTOMER);
                    break;
            }
        }

        @Override
        protected Integer doInBackground(Integer... integers) {

            try {
                JSONArray jsonArray = new JSONArray(response);

                for (int index = 0; index < jsonArray.length(); index++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(index);
                    JSONObject jsonCustomer = jsonObject.getJSONObject("customer");

                    DownlineCustomer customer = new DownlineCustomer();
                    customer.cid = jsonCustomer.getInt("cid");
                    customer.branch = jsonCustomer.getInt("branch");
                    customer.ancestor = jsonCustomer.getString("ancestor");
                    customer.customerCode = jsonCustomer.getString("customer_code");

                    // Decodes all the encoded string
                    byte[] customerName = Base64.decode(jsonCustomer.getString("cust_name"), Base64.DEFAULT);
                    customer.customerName = new String(customerName, "UTF-8");
                    byte[] outletName = Base64.decode(jsonCustomer.getString("outlet_name"), Base64.DEFAULT);
                    customer.outletName = new String(outletName, "UTF-8");
                    byte[] businessAddress = Base64.decode(jsonCustomer.getString("business_address"), Base64.DEFAULT);
                    customer.businessAddress = new String(businessAddress, "UTF-8");

                    customer.tinNo = jsonCustomer.getString("tin_no");
                    customer.telephoneNum = jsonCustomer.getString("tel_no");
                    customer.faxNo = jsonCustomer.getString("fax_no");
                    customer.storeType = jsonCustomer.getInt("store_type");
                    customer.payType = jsonCustomer.getInt("pay_type");
                    customer.routeId = jsonCustomer.getInt("route_id");
                    customer.bankCode = jsonCustomer.getString("bank_code");
                    customer.tradeDiscount = jsonCustomer.getDouble("trade_discount");
                    customer.decimalPlaces = jsonCustomer.getInt("decimal_places");

                    customer.needDr = (jsonCustomer.getInt("need_dr") == 1);
                    customer.needSi = (jsonCustomer.getInt("need_si") == 1);
                    customer.showExchangeDr = (jsonCustomer.getInt("show_exchange_dr") == 1);
                    customer.showExchangeSi = (jsonCustomer.getInt("show_exchange_si") == 1);

                    customer.zeroRated = (jsonCustomer.getInt("zerorated") == 1);

                    customer.storeDescription = jsonCustomer.getString("sdesc");
                    customer.discountType = jsonCustomer.getInt("discount_type");
                    customer.maxPrintCopies = jsonCustomer.getInt("max_print_copies");
                    customer.prodCodeId = jsonCustomer.getInt("prod_code_id");
                    customer.isManualRs = jsonCustomer.getInt("is_manual_rs") == 1;
                    customer.hasEwt = jsonCustomer.getInt("with_ewt") == 1;
                    customer.needDpv = jsonCustomer.getInt("need_dpv") == 1;
                    customer.needGrv = jsonCustomer.getInt("need_grv") == 1;
                    customer.needPo = jsonCustomer.getInt("need_po") == 1;

                    Log.d(TAG, "CUSTOMER NAME " + customer.customerName);

                    DownlineCustomerHelper customerHelper = new DownlineCustomerHelper(customer);
                    customerHelper.save();

                }

                publishProgress(VolleyCallback.ON_DOWLINE_STATE_CUSTOMER);

            } catch (JSONException e) {

            } catch (UnsupportedEncodingException e) {

            }

            return null;
        }
    }

    public void getDsc(final int empId, final VolleyCallback callback) {

        /*This method requires employee id and use it to request
         * data for specific new dsc table in the WebService */

        Log.d(TAG, "EMPLOYEE ID " + empId);

        StringRequest request = new StringRequest(Request.Method.POST, Constant.BASE_DOWNLINE_URL + "dsc/get_dsc_today", new Response.Listener<String>() {
            @Override
            public void onResponse(final String response) {

                Log.d(TAG, "DSC RESPONSE " + response);

                new GetDsc(response, callback).execute();

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Log.d(TAG, "DSC ERROR " + error.getMessage());
                error.printStackTrace();
                callback.onFailed(VolleyCallback.ON_RESPONSE_FAILED);

            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("token", Constant.KEY);
                params.put("emp_id", String.valueOf(empId));
                return params;
            }
        };

        /*Set timeout for 50 seconds and tell not to retry the request*/
        request.setRetryPolicy(new DefaultRetryPolicy(50000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        /*Singleton class to add request*/
        Singleton.getInstance(context.getApplicationContext()).addToRequestQueue(request);

    }

    public class GetDsc extends AsyncTask<Integer, Integer, Integer> {

        String response;
        VolleyCallback callback;

        public GetDsc(String response, VolleyCallback callback) {
            this.response = response;
            this.callback = callback;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            switch (values[0]) {

                case VolleyCallback.ON_DOWLINE_STATE_DSC:
                    callback.onSuccess(VolleyCallback.ON_DOWLINE_STATE_DSC);
                    break;
                case VolleyCallback.ON_DOWLINE_STATE_DSC_ITEMS:
                    callback.onSuccess(VolleyCallback.ON_DOWLINE_STATE_DSC_ITEMS);
                    break;
                case VolleyCallback.ON_DOWLINE_STATE_FORECAST:
                    callback.onSuccess(VolleyCallback.ON_DOWLINE_STATE_FORECAST);
                    break;

            }
        }

        @Override
        protected Integer doInBackground(Integer... integers) {

            try {

                JSONObject jsonObject = new JSONObject(response);

                JSONArray dscJsonArray = jsonObject.getJSONArray("dsc");
                for (int i = 0; i < dscJsonArray.length(); i++) {

                    JSONObject dscObject = dscJsonArray.getJSONObject(i);

                    DownlineDsc dsc = new DownlineDsc();

                    dsc.cid = dscObject.getInt("cid");
                    dsc.branch = dscObject.getInt("branch");
                    dsc.dscNo = dscObject.getString("dsc_no");

                    dsc.routeId = dscObject.getInt("route_id");
                    dsc.guardId = dscObject.getInt("guard_id");
                    byte[] guardDateTime = Base64.decode(dscObject.getString("guard_datetime"), Base64.DEFAULT);
                    dsc.guardTime = new String(guardDateTime, "UTF-8");
                    dsc.trayIn = dscObject.getInt("trayIN");
                    dsc.trayOut = dscObject.getInt("trayOUT");
                    dsc.boTrayIn = dscObject.getInt("bo_tray_in");
                    dsc.isPreloaded = (dscObject.getInt("ispreloaded") == 1);
                    dsc.truckerId = dscObject.getInt("trucker_id");
                    dsc.boConfirmed = (dscObject.getInt("bo_confirmed") == 1);
                    dsc.cashierReceived = (dscObject.getInt("cashier_received") == 1);
                    byte[] cashierReceivedDate = Base64.decode(dscObject.getString("cashier_received_date"), Base64.DEFAULT);
                    dsc.cashierReceivedDate = new String(cashierReceivedDate, "UTF-8");
                    byte[] datePosted = Base64.decode(dscObject.getString("trans_date"), Base64.DEFAULT);
                    dsc.datePosted = new String(datePosted, "UTF-8");

                    Log.d(TAG, "DSC " + dsc.dscNo);

                    DownlineDscHelper dscHelper = new DownlineDscHelper(dsc);
                    dscHelper.save();

                }

                Log.d(TAG, "FIN DSC");
                publishProgress(VolleyCallback.ON_DOWLINE_STATE_DSC);


                JSONArray dscItemsJsonArray = jsonObject.getJSONArray("dsc_items");

                for (int i = 0; i < dscItemsJsonArray.length(); i++) {

                    JSONObject dscItemsObject = dscItemsJsonArray.getJSONObject(i);

                    DownlineDscItem dscItem = new DownlineDscItem();

                    dscItem.dscId = dscItemsObject.getInt("dsc_id");
                    dscItem.productCode = dscItemsObject.getInt("prod_code");
                    dscItem.issued = dscItemsObject.getInt("issued");
                    dscItem.trayCount = dscItemsObject.getInt("tray_count");
                    dscItem.excessPack = dscItemsObject.getInt("excess_pack");

                    DownlineDscItemHelper dscItemHelper = new DownlineDscItemHelper(dscItem);
                    dscItemHelper.save();

                }

                Log.d(TAG, "FIN DSC ITEM");
                publishProgress(VolleyCallback.ON_DOWLINE_STATE_DSC_ITEMS);

                JSONArray forecastJsonArray = jsonObject.getJSONArray("forecasts");

                for (int i = 0; i < forecastJsonArray.length(); i++){

                    JSONObject forecastObject = forecastJsonArray.getJSONObject(i);

                    DownlineForecast forecast = new DownlineForecast();

                    forecast.cid = forecastObject.getInt("cid");
                    forecast.routeCid = forecastObject.getInt("route_id");
                    forecast.customerCid = forecastObject.getInt("customer_id");
                    forecast.isDelivered = (forecastObject.getInt("isdelivered") == 1);
                    forecast.storeClose = (forecastObject.getInt("storeclose") == 1);
                    forecast.dscNo = forecastObject.getString("dsc_no");

                    Log.d(TAG, "FORECAST CID " + forecast.cid);
                    DownlineForecastHelper forecastHelper = new DownlineForecastHelper(forecast);
                    forecastHelper.save();

                }

                Log.d(TAG, "FIN FORECAST");
                publishProgress(VolleyCallback.ON_DOWLINE_STATE_FORECAST);



            } catch (JSONException e) {

                Log.d(TAG, "JSONException " + e.toString());
                callback.onFailed(VolleyCallback.ON_RESPONSE_JSON_ERROR);
                e.printStackTrace();

            } catch (UnsupportedEncodingException e) {

                Log.d(TAG, "UnsupportedEncodingException " + e.toString());

            }

            return null;
        }
    }

}