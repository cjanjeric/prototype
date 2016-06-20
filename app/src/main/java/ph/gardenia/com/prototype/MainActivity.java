package ph.gardenia.com.prototype;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.mikepenz.materialdrawer.AccountHeader;
import com.mikepenz.materialdrawer.AccountHeaderBuilder;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.ProfileDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IProfile;
import com.mikepenz.materialdrawer.model.interfaces.Nameable;

import java.util.List;

import ph.gardenia.com.fragment.DscFragment;
import ph.gardenia.com.fragment.RouteFragment;
import ph.gardenia.com.fragment.TransactionFragment;
import ph.gardenia.com.helper.RouteHelper;
import ph.gardenia.com.helper.UserHelper;
import ph.gardenia.com.utils.Constant;

public class MainActivity extends AppCompatActivity {

    private String TAG = getClass().getSimpleName();
    private List<RouteHelper> routeHelpers;
    private List<UserHelper> userHelpers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        /*Set Transition in activity*/
        overridePendingTransition(R.anim.fragment_slide_left_enter, R.anim.fragment_slide_right_exit);

        setContentView(R.layout.activity_main);

        routeHelpers = RouteHelper.listAll(RouteHelper.class);
        userHelpers = UserHelper.listAll(UserHelper.class);

        /*Set the Home Fragment*/
        Fragment fragment = new Fragment();
        if (fragment != null) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragment = new DscFragment();
            fragmentTransaction.replace(R.id.main, fragment);
            fragmentTransaction.commit();
        }

        //Instantiate toolbar to be used
        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //build account header of the drawer
        AccountHeader headerResult = new AccountHeaderBuilder()
                .withActivity(this)
                .withHeaderBackground(R.drawable.header)
                .addProfiles(
                        new ProfileDrawerItem()
                                .withName(userHelpers.get(Constant.BASE_COLUMN).getCompleteName())
                                .withEmail(routeHelpers.get(Constant.BASE_COLUMN).getDescript())
                                .withIcon(R.drawable.profile)
                )
                .withOnAccountHeaderListener(new AccountHeader.OnAccountHeaderListener() {
                    @Override
                    public boolean onProfileChanged(View view, IProfile profile, boolean currentProfile) {
                        return false;
                    }
                })
                .build();

        //build drawer
        new DrawerBuilder().withActivity(this).build();

        //Preparing items for drawer
        PrimaryDrawerItem dsc = new PrimaryDrawerItem().withName("DAILY STOCK CONTROL").withIcon(R.drawable.ic_assignment_white_36dp);
        PrimaryDrawerItem transaction = new PrimaryDrawerItem().withName("TRANSACTION").withIcon(R.drawable.ic_shopping_cart_white_36dp);
        PrimaryDrawerItem remittance = new PrimaryDrawerItem().withName("REMITTANCES").withIcon(R.drawable.ic_attach_money_white_36dp);
        PrimaryDrawerItem drr = new PrimaryDrawerItem().withName("DAILY REMITTANCE REPORT").withIcon(R.drawable.ic_insert_chart_white_36dp);

        //Apply items to the drawer and implement an onclick event
        Drawer result = new DrawerBuilder()
                .withActivity(this)
                .withToolbar(toolbar)
                .addDrawerItems(dsc, transaction, remittance, drr)
                .withAccountHeader(headerResult)
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {

                        Log.d(TAG, "POSITION " + position);

                        //Instantiate fragment to be used
                        Fragment fragment = new Fragment();
                        FragmentManager fragmentManager = getSupportFragmentManager();
                        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

                        switch (position) {

                            case 1:
                                Log.d(TAG, "DSCFRAGMENT");
                                if (fragment != null) {
                                    fragment = new DscFragment();
                                }
                                break;
                            case 2:
                                Log.d(TAG, "TRANSACTIONFRAGMENT");
                                if (fragment != null) {
                                    fragment = new TransactionFragment();
                                }
                                break;
                            case 3:
                                Log.d(TAG, "REMITTANCEFRAGMENT");
                                break;
                            case 4:
                                Log.d(TAG, "DAILYREMITTANCEREPORTFRAGMENT");
                                break;


                        }

                        fragmentTransaction.replace(R.id.main, fragment);
                        fragmentTransaction.commit();

                        return false;
                    }
                }).build();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.send) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
