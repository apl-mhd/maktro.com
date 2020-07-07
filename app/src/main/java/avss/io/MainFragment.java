package avss.io;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class MainFragment extends Fragment  {

    private  onFragmentBtnSelected listener;


    private  Button button;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view =  inflater.inflate(R.layout.fragment_main, container, false);


        Button active = view.findViewById(R.id.lock);
        Button deActive = view.findViewById(R.id.unlock);

        active.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                button = view.findViewById(R.id.lock);
                String a = button.getText().toString();
                listener.onButtonSelected(a);


            }
        });

        deActive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                button = view.findViewById(R.id.unlock);
                String a = button.getText().toString();
                listener.onButtonSelected(a);

//                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("sms:" + a));
//                intent.putExtra("sms_body", "Unlock");
//                startActivity(intent);

            }
        });

        return view;
    }





    @Override
    public void onAttach(Context context) {

        super.onAttach(context);
        if (context instanceof  onFragmentBtnSelected){

            listener = (onFragmentBtnSelected) context;
        }
        else{
            throw  new  ClassCastException(context.toString()+ "Must implement listener") ;
        }

    }

    public  interface  onFragmentBtnSelected{

        public  void onButtonSelected(String name);

    }
}
