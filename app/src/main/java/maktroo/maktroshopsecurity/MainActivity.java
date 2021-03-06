package maktroo.maktroshopsecurity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
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

        if (menuItem.getItemId() == R.id.userManual){

            changeFragment(new UserManual());
        }

        if (menuItem.getItemId() ==R.id.helpLine){


            Intent intent = new Intent(Intent.ACTION_DIAL);
            intent.setData(Uri.parse("tel:01714178875"));
            startActivity(intent);

//            Intent callIntent = new Intent(Intent.ACTION_CALL);
//            callIntent.setData(Uri.parse("tel:01714178875"));

        }

        if (menuItem.getItemId()==R.id.website){

            String url = "http://www.maktro.com";
            Intent i = new Intent(Intent.ACTION_VIEW);
            i.setData(Uri.parse(url));
            startActivity(i);
        }


        if (menuItem.getItemId()==R.id.facebook){

            String url = "http://www.fb.com/maktrocom";
            Intent i = new Intent(Intent.ACTION_VIEW);
            i.setData(Uri.parse(url));
            startActivity(i);
        }


        if (menuItem.getItemId()==R.id.playstore){


            Uri uri = Uri.parse("market://details?id=" + this.getPackageName());

            Intent goToMarket = new Intent(Intent.ACTION_VIEW, uri);
            // To count with Play market back stack, After pressing back button,
            // to taken back to our application, we need to add following flags to intent.
            goToMarket.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY |
                    Intent.FLAG_ACTIVITY_NEW_DOCUMENT |
                    Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
            try {
                startActivity(goToMarket);
            } catch (ActivityNotFoundException e) {
                startActivity(new Intent(Intent.ACTION_VIEW,
                        Uri.parse("http://play.google.com/store/apps/details?id=" + this.getPackageName())));
            }


//            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=PackageName")));
        }







        return false;
    }


    public  void changeFragment(Fragment fragment){

        fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.container_fragment, fragment).addToBackStack(null).commit();


    }

    @Override
    public void onButtonSelected(String acdc) {

        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();




      String  text = sharedPreferences.getString(ADMIN_PHONE, "");

      if (text.length() ==0){
          Toast.makeText(this, "Please Set Device Number First", Toast.LENGTH_LONG).show();
          changeFragment(new SetPhoneNumber());
      }
      else {


          Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("sms:" + text));
          intent.putExtra("sms_body", acdc);
          startActivity(intent);
      }

//        Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void saveAdmin(String phoneNumber) {

        if (phoneNumber.length() <11){

            Toast.makeText(this, "Please Enter Device Number Correctly", Toast.LENGTH_SHORT).show();

        }
        else {


            SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();

            editor.putString(ADMIN_PHONE, phoneNumber);
            editor.apply();
            Toast.makeText(this, "Device Number Saved", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void sendAdmin(String phoneNumber, String code) {

        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();




        String  text = sharedPreferences.getString(ADMIN_PHONE, "");


        if (text.length() == 0){
            Toast.makeText(this, "Set Device Number First", Toast.LENGTH_SHORT).show();
        }
        else  if (phoneNumber.length() <11 ){
            Toast.makeText(this, "Please Enter Device Number Correctly", Toast.LENGTH_SHORT).show();
        }
        else{

            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("sms:" + text));
            intent.putExtra("sms_body", code+phoneNumber+"#");
            startActivity(intent);
        }


    }


}
