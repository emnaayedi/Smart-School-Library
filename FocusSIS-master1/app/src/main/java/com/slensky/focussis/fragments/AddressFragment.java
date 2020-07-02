package com.slensky.focussis.fragments;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.slensky.focussis.R;
import com.slensky.focussis.data.Address;
import com.slensky.focussis.data.AddressContact;
import com.slensky.focussis.data.AddressContactDetail;
import com.slensky.focussis.network.FocusApi;
import com.slensky.focussis.network.FocusApiSingleton;
import com.slensky.focussis.util.CardViewAnimationController;
import com.slensky.focussis.views.IconWithTextView;

import java.util.List;

import butterknife.BindView;

/**
 * Created by slensky on 5/22/17.
 */

public class AddressFragment extends NetworkTabAwareFragment {
    private final static String TAG = "AddressFragment";
    EditText text;
    Button btn_reclamation;
    TextInputLayout layout_reclamation;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        api = FocusApiSingleton.getApi();
        title = getString(R.string.address_label);

        refresh();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_address, container, false);
        return view;
    }

    @Override
    public boolean hasTabs() {
        return false;
    }

    @Override
    public List<String> getTabNames() {
        return null;
    }

    Reclamation reclamation;
    DatabaseReference ref;

    protected void onSuccess(Address address) {
        View view = getView();
        if (view != null) {
            CardViewAnimationController animationController = new CardViewAnimationController(getContext());
            LinearLayout addressLayout = (LinearLayout) view.findViewById(R.id.address_layout);
            CardView cview = (CardView) LayoutInflater.from(getContext()).inflate(R.layout.view_address_contact, addressLayout, false);

            btn_reclamation = (Button) cview.findViewById(R.id.btn_reclamation);
            text = (EditText)cview.findViewById(R.id.reclamation);
            layout_reclamation=(TextInputLayout)cview.findViewById(R.id.layout_reclamation);
            reclamation = new Reclamation();
            ref = FirebaseDatabase.getInstance().getReference().child("reclamation");
            btn_reclamation.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(getContext(), "Reclamation envoyee avec succes", Toast.LENGTH_SHORT).show();
                    final String a = text.getText().toString();



                    if((!a.trim().isEmpty())&&((a.trim().length()>20))) {
                        reclamation.setMsg(a);
                        ref.push().setValue(reclamation);
                        text.setText("");

                    }
                    else
                        if((a.trim().length()<20)&&(!a.isEmpty())&&(!a.equals(""))){
                            layout_reclamation.setError("Message must contain at least 20 characters");
                        }
                    else if((a.isEmpty())){
                        layout_reclamation.setError("Message cannot be blank");

                    }

                }

            });

            // i have no idea why i have to reset the margins like this, but it won't work without it
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            );
            int margin = dpToPixel(16);
            params.setMargins(margin, margin, margin, margin);
            cview.setLayoutParams(params);

            cview.setAnimation(animationController.nextAnimation());

            addressLayout.addView(cview);
        }


    requestFinished =true;
}

    @Override
    protected void makeRequest() {
        api.getAddress(new FocusApi.Listener<Address>() {
            @Override
            public void onResponse(Address response) {
                onSuccess(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                onError(error);
            }
        });
    }

    private String formatPhone(String phone) {
        if (phone.length() < 4) {
            return phone;
        }
        phone = phone.substring(0, phone.length() - 4) + "-" + phone.substring(phone.length() - 4);
        if (phone.length() > 8) {
            phone = "(" + phone.substring(0, 3) + ") " + phone.substring(3);
        }
        return phone;
    }

    private int dpToPixel(int dp) {
        final float scale = getResources().getDisplayMetrics().density;
        return (int) (dp * scale + 0.5f);
    }

    private String boolToYesNo(boolean b) {
        if (b) {
            return getString(R.string.yes);
        }
        return getString(R.string.no);
    }

}