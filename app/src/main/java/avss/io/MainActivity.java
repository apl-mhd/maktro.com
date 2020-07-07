package avss.io;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener,
MainFragment.onFragmentBtnSelected, SetPhoneNumber.saveNumber{


    DrawerLayout drawerLayout;
    ActionBarDrawerToggle actionBarDrawerToggle;
    Toolbar toolbar;
    NavigationView navigationView;

    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;


    /*shared*/

    public  static final  String SHARED_PREFS = "sharedPrefs";
    public  static  final String ADMIN_PHONE = "admin_phone";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawerLayout = findViewById(R.id.drawer);
        navigationView = findViewById(R.id.navigationView);
        navigationView.setNavigationItemSelectedListener(this);

        actionBarDrawerToggle  = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.oepn, R.string.close);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.setDrawerIndicatorEnabled(true);
        actionBarDrawerToggle.syncState();


        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.container_fragment, new MainFragment());
        fragmentTransaction.commit();


    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {


        drawerLayout.closeDrawer(GravityCompat.START);

        if (menuItem.getItemId() == R.id.setting){
            changeFragment(new SetPhoneNumber());

        }

        if (menuItem.getItemId() == R.id.acdc){
            changeFragment(new MainFragment());

        }


        return false;
    }


    public  void changeFragment(Fragment fragment){

        fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.container_fragment, fragment).addToBackStack(null).commit();


    }

    @Override
    public void onButtonSelected(String name) {

        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();


      String  text = sharedPreferences.getString(ADMIN_PHONE, "");

        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("sms:" + text));
        intent.putExtra("sms_body", "Lock");
        startActivity(intent);

//        Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void saveAdmin(String phoneNumber) {

        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putString(ADMIN_PHONE, phoneNumber);
        editor.apply();

        Toast.makeText(this, "Device Number Saved", Toast.LENGTH_SHORT).show();
    }
}
