package com.slensky.focussis.fragments;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.slensky.focussis.R;
import com.slensky.focussis.activities.MainActivity;
import com.slensky.focussis.network.FocusApi;
import com.slensky.focussis.network.FocusApiSingleton;
import com.slensky.focussis.util.CardViewAnimationController;
import com.slensky.focussis.util.SchoolSingleton;
import com.slensky.focussis.views.IconWithTextView;

import org.apache.commons.lang.math.NumberUtils;

import com.slensky.focussis.data.Demographic;
import com.slensky.focussis.util.DateUtil;

import java.util.List;
/**
 * Created by slensky on 5/14/17.
 */

public class DemographicFragment extends NetworkTabAwareFragment {
    private static final String TAG = "DemographicFragment";

    // for formatting the student's grade/level
    private static String ordinal(int i) {
        String[] sufixes = new String[]{"th", "st", "nd", "rd", "th", "th", "th", "th", "th", "th"};
        switch (i % 100) {
            case 11:
            case 12:
            case 13:
                return i + "th";
            default:
                return i + sufixes[i % 10];

        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        api = FocusApiSingleton.getApi();
        title = getString(com.slensky.focussis.R.string.demographic_label);
        refresh();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(com.slensky.focussis.R.layout.fragment_demographic, container, false);
        return view;
    }

    protected void onSuccess(Demographic demographic) {
        View view = getView();
        if (view != null) {
            DatabaseReference database = FirebaseDatabase.getInstance().getReference();
            DatabaseReference ref_etud = database.child("etudiants");
            ref_etud.addValueEventListener(new ValueEventListener() {

                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    for (DataSnapshot ds : dataSnapshot.getChildren()) {
                        IconWithTextView name = (IconWithTextView) view.findViewById(com.slensky.focussis.R.id.view_name);

                        String nameT = ds.child("name").getValue(String.class);
                        IconWithTextView dob = (IconWithTextView) view.findViewById(com.slensky.focussis.R.id.view_dob);
                        String emailT = ds.child("email").getValue(String.class);
                        String filT = ds.child("fil").getValue(String.class);
                        String idT = ds.child("login").getValue(String.class);
                        Integer telT = ds.child("nb_emp").getValue(int.class);
                        Integer nbempT = ds.child("tel").getValue(int.class);

                        IconWithTextView studentID = (IconWithTextView) view.findViewById(com.slensky.focussis.R.id.view_student_id);
                        studentID.setText(idT);
                        studentID.setText(filT);
                        name.setText(nameT);
                        dob.setText(emailT);
                        LinearLayout llDetailed = view.findViewById(R.id.ll_detailed);


                        View v2 = LayoutInflater.from(getContext()).inflate(R.layout.view_icon_with_text, llDetailed, false);
                        ImageView ic2 = v2.findViewById(R.id.row_icon);
                        TextView hint2 = v2.findViewById(R.id.text_hint);
                        TextView main2 = v2.findViewById(R.id.text_main);
                        hint2.setText(title);
                        main2.setText(telT.toString());
                        int drawableId2;
                        drawableId2 = R.drawable.ic_phone_black_24px;
                        Drawable d2 = getResources().getDrawable(drawableId2);
                        ic2.setImageDrawable(d2);

                        llDetailed.addView(v2);
                        LinearLayout llDetailed3 = view.findViewById(R.id.ll_detailed);
                        View v3 = LayoutInflater.from(getContext()).inflate(R.layout.view_icon_with_text, llDetailed, false);
                        ImageView ic3 = v3.findViewById(R.id.row_icon);
                        TextView hint3 = v3.findViewById(R.id.text_hint);
                        TextView main3 = v3.findViewById(R.id.text_main);
                        hint3.setText(title);
                        main3.setText(nbempT.toString());
                        int drawableId3;
                        drawableId3 = R.drawable.ic_account_card_details_black_24px;
                        Drawable d3 = getResources().getDrawable(drawableId3);
                        ic3.setImageDrawable(d3);

                        llDetailed3.addView(v3);

                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }

            });


            // attempt to find email in custom fields
            String emailStr = null;
            for (String title : demographic.getCustomFields().keySet()) {
                if (title.toLowerCase().equals("email") || title.toLowerCase().equals("e-mail")) {
                    emailStr = demographic.getCustomFields().get(title);
                    demographic.getCustomFields().remove(title);
                    break;
                }
            }
            // fall back to known email pattern if email cannot be found
            if (emailStr == null) {
                emailStr = ((MainActivity) getActivity()).getUsername() + SchoolSingleton.getInstance().getSchool().getDomainName();
            }
            IconWithTextView email = (IconWithTextView) view.findViewById(com.slensky.focussis.R.id.view_email);
            email.setText(emailStr);
            final String finalEmailStr = emailStr;
            email.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(Intent.ACTION_SENDTO);
                    intent.putExtra(Intent.EXTRA_EMAIL, new String[]{finalEmailStr});
                    intent.setData(Uri.parse("mailto:")); // only email apps should handle this
                    if (getContext() != null && intent.resolveActivity(getContext().getPackageManager()) != null) {
                        Log.i(TAG, "Emailing " + finalEmailStr);
                        startActivity(intent);
                    }
                }
            });

            // attempt to find the "level" custom field if it's present
            int level = 0;
            for (String title : demographic.getCustomFields().keySet()) {
                if (title.toLowerCase().equals("level (year)") && NumberUtils.isDigits(demographic.getCustomFields().get(title))) {
                    level = Integer.parseInt(demographic.getCustomFields().get(title));
                    demographic.getCustomFields().remove(title);
                    break;
                }
            }

            IconWithTextView grade = (IconWithTextView) view.findViewById(com.slensky.focussis.R.id.view_grade);
            if (level != 0) {
                grade.setText(ordinal(demographic.getStudent().getGrade()) + ", " + ordinal(level) + " year");
            } else {
                grade.setText(ordinal(demographic.getStudent().getGrade()));
            }




            CardViewAnimationController animationController = new CardViewAnimationController(getContext());
            CardView basic = view.findViewById(R.id.card_basic);
            CardView detailed = view.findViewById(R.id.card_detailed);
            basic.setAnimation(animationController.nextAnimation());
            detailed.setAnimation(animationController.nextAnimation());

        }

        requestFinished = true;
    }



    @Override
    protected void makeRequest() {
        api.getDemographic(new FocusApi.Listener<Demographic>() {
            @Override
            public void onResponse(Demographic response) {
                onSuccess(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                onError(error);
            }
        });
    }

    @Override
    public boolean hasTabs() {
        return false;
    }

    @Override
    public List<String> getTabNames() {
        return null;
    }

    private String boolToYesNo(boolean b) {
        if (b) {
            return getString(com.slensky.focussis.R.string.yes);
        }
        return getString(com.slensky.focussis.R.string.no);
    }

}
