package ph.gardenia.com.request;

import android.content.Context;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

import ph.gardenia.com.callback.VolleyCallback;
import ph.gardenia.com.utils.Constant;
import ph.gardenia.com.utils.Singleton;

/**
 * Created by otomatik on 6/7/16.
 */
public class PullRequest {

    /*This Object Pull data from web API*/

    private String TAG = getClass().getSimpleName();
    private Context context;

    public PullRequest(Context context){
        this.context = context;
    }

    public void getRoute(final String routeCode, final VolleyCallback callback){

        /*This method requires route code and use it to request
         * data for specific route to the WebService */

        StringRequest request = new StringRequest(Request.Method.POST, Constant.BASE_URL +"routes/get_assigned_route", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Log.d(TAG, "RESPONSE " + response);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("token", Constant.KEY);
                params.put("route_code", routeCode);
                return super.getParams();
            }
        };

        /*Set timeout for 50 seconds and tell not to retry the request*/
        request.setRetryPolicy(new DefaultRetryPolicy(50000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        /*Singleton class to add request*/
        Singleton.getInstance(context.getApplicationContext()).addToRequestQueue(request);

    }

}
