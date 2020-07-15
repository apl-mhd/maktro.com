package maktroo.maktroshopsecurity;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class SetPhoneNumber extends Fragment {




    private saveNumber listener;


    private Button deviceBtn, adminBtn1, adminBtn2, adminBtn3;
    private EditText deviceEdit, editTextAdmin1, editTextAdmin2, editTextAdmin3;




    public  static final  String SHARED_PREFS = "sharedPrefs";
    public  static  final String ADMIN_PHONE = "admin_phone";



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.set_number, container, false);


        deviceBtn = view.findViewById(R.id.deviceBtn);
        deviceEdit = view.findViewById(R.id.deviceEditText);

        adminBtn1 = view.findViewById(R.id.adminBtn1);
        editTextAdmin1 = view.findViewById(R.id.adminEdit1);

        adminBtn2 = view.findViewById(R.id.adminBtn2);
        editTextAdmin2 = view.findViewById(R.id.adminEdit2);

        adminBtn3 = view.findViewById(R.id.adminBtn3);
        editTextAdmin3 = view.findViewById(R.id.adminEdit3);



        deviceBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String phone = deviceEdit.getText().toString();

                listener.saveAdmin(phone);




            }
        });

        adminBtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {




                String phone = editTextAdmin1.getText().toString();
                listener.sendAdmin(phone, "123431");
                //Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("sms:" + phone));
                //intent.putExtra("sms_body", "123431"+phone+"#");
                //startActivity(intent);



            }
        });


        adminBtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String phone = editTextAdmin2.getText().toString();
                listener.sendAdmin(phone, "123432");
//                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("sms:" + phone));
//                intent.putExtra("sms_body", "123432"+phone+"#");
//                startActivity(intent);

            }
        });


        adminBtn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String phone = editTextAdmin3.getText().toString();
                listener.sendAdmin(phone, "123433");
//
//                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("sms:" + phone));
//                intent.putExtra("sms_body", "123433"+phone+"#");
//                startActivity(intent);

            }
        });


        return  view;
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

        public  void  saveAdmin(String phoneNumber);
        public  void  sendAdmin(String phoneNumber, String code);
    }
}
