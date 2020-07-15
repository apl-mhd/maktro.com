package maktroo.maktroshopsecurity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class WelComeActivity extends AppCompatActivity {


    public  static  int SPLASH_TIME_OUT  = 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wel_come);



        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);


//        new Handler().postDelayed(new Runnable() {
//            @Override
//            public void run() {
//
//                Intent intent = new Intent(WelComeActivity.this, MainActivity.class);
//                startActivity(intent);
//            }
//        }, SPLASH_TIME_OUT);

    }
}
