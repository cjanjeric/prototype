package ph.gardenia.com.request;

import android.content.Context;
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
import ph.gardenia.com.helper.RouteHelper;
import ph.gardenia.com.helper.UserHelper;
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

}
;