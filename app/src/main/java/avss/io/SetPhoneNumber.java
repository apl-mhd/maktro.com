package avss.io;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import static android.content.Context.MODE_PRIVATE;
import static android.provider.Telephony.Mms.Part.TEXT;

public class SetPhoneNumber extends Fragment {




    private saveNumber listener;


    Button button;
    private EditText editText;
    private TextView textView;

    private TextView showNumber;
    private Button click_adimn;


    public  static final  String SHARED_PREFS = "sharedPrefs";
    public  static  final String ADMIN_PHONE = "admin_phone";



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.set_number, container, false);


        button = view.findViewById(R.id.save);
        editText = view.findViewById(R.id.admin);


        click_adimn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                textView.setText("okor");




            }
        });


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                listener.save(editText.getText().toString());





            }
        });




        return  view;
    }


    public  void a(){


    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);


        if (context instanceof saveNumber){
            listener = (saveNumber) context;
        }
        else {

            throw new ClassCastException(context.toString()+"Must implemented listener");
        }
    }

    public  interface  saveNumber{

        public  void  save(String phoneNumber);
    }
}
