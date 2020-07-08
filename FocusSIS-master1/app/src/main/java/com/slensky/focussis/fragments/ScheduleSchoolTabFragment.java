package com.slensky.focussis.fragments;


import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.util.LogPrinter;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.core.app.NotificationCompat;
import androidx.fragment.app.Fragment;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.gson.Gson;
import com.slensky.focussis.FocusApplication;
import com.slensky.focussis.R;
import com.slensky.focussis.data.Calendar;
import com.slensky.focussis.data.Schedule;
import com.slensky.focussis.data.ScheduleCourse;
import com.slensky.focussis.util.GsonSingleton;
import com.slensky.focussis.util.TableRowAnimationController;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Random;

/**
 * Created by slensky on 5/9/18.
 */

public class ScheduleSchoolTabFragment extends Fragment {
    private static final String TAGn = "ScheduleCoursesTabFragment";
    private static final String CHANNEL_ID ="my_channel_01" ;
    private static String livre;
    private Schedule schedule;
    String html12,html11,html10,html13,html14,nom,nom1,nom2,nom4,nom5,nom6,html15;

    public ScheduleSchoolTabFragment() {
        // required empty constructor
    }




    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Gson gson = GsonSingleton.getInstance();
        schedule = gson.fromJson(getArguments().getString(getString(R.string.EXTRA_SCHEDULE)), Schedule.class);
    }
    DatabaseReference database = FirebaseDatabase.getInstance().getReference();
    DatabaseReference ref_emp = database.child("emprunte");
    DatabaseReference ref_livre = database.child("stockage");


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_schedule_school_tab, container, false);



        TableLayout table = (TableLayout) view.findViewById(R.id.table_courses);
        table.removeAllViews();
        TableRow headerRow = (TableRow) inflater.inflate(R.layout.view_schedule_school_header, table, false);
        table.addView(headerRow);

        List<ScheduleCourse> courses = schedule.getCourses();
        TableRowAnimationController animationController = new TableRowAnimationController(getContext());

        ref_emp.orderByChild("id_emprunteur").equalTo(FocusApplication.loginn).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                int i = 0;
                String[] b=new String[(int) dataSnapshot.getChildrenCount()];

                if (dataSnapshot.exists()) {
                    for (DataSnapshot d : dataSnapshot.getChildren()) {
                        b[i] = d.getKey();
                        i++;
                    }}

                for (int j = 0; j < i; j++) {
                    final TableRow courseRow = (TableRow) inflater.inflate(R.layout.view_schedule_school, table, false);


                    TextView period = (TextView) courseRow.findViewById(R.id.text_course_period);


                    DatabaseReference ref_nom = database.child("emprunte/" + b[j] + "/nom_livre");
                    ref_nom.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            int i = 0;
                            String[] b=new String[(int) dataSnapshot.getChildrenCount()];
                            nom4 = dataSnapshot.getValue().toString();
                            livre=nom4;
                            period.setText(nom4);
                            html14 = "<b>Nom Livre: </b>" + nom4 + "<br><br>";

                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });
                    TextView nomm = (TextView) courseRow.findViewById(R.id.text_course_name);

                    RatingBar ratingBar=new RatingBar(getContext());
                    ratingBar.setStepSize((float) 0.5);
                    ratingBar.setNumStars(5);

                      String x=b[j];

                    DatabaseReference avis = database.child("emprunte/" + b[j] + "/Avis");
                    avis.addListenerForSingleValueEvent(new ValueEventListener() {

                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            nom6 = dataSnapshot.getValue().toString();
                            html15="<b>Avis: </b>" + nom6 + "<br><br>";
                            RatingBar r=courseRow.findViewById(R.id.ratingBar);

                             r.setRating(Float.parseFloat(nom6));


                    //  days.setText(c.getDays());
                    TextView room = (TextView) courseRow.findViewById(R.id.text_course_room);

                            AlertDialog alertDialog = new AlertDialog.Builder(getActivity()).create();
                            //alertDialog.setTitle();
                            TextView messageView= new TextView(getContext());

                            ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {

                                @Override
                                public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {

                                    r.setRating(ratingBar.getRating());
                                    avis.setValue(ratingBar.getRating());



                                }
                            });

                            messageView.setText(Html.fromHtml(html15));


                            ratingBar.setPadding(16, 16, 16, 16);
                            float dpi = getContext().getResources().getDisplayMetrics().density;
                            alertDialog.setTitle("Livre :  "+nom4);
                            alertDialog.setView(ratingBar);

                            alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "Changer",
                                    (dialog, which) -> dialog.dismiss());

                            courseRow.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {


                                    alertDialog.show();
                                }
                            });
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });


                    // room.setText(c.getRoom().split(" ")[0]); // changes jr/sr area into just jr/sr for brevity


                    final View divider = inflater.inflate(R.layout.view_divider, table, false);




                    final Animation animation = animationController.nextAnimation();

                    courseRow.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                        @Override
                        public void onGlobalLayout() {
                            Rect scrollBounds = new Rect();
                            if (courseRow.getLocalVisibleRect(scrollBounds)) {
                                courseRow.setAnimation(animation);
                            }
                            courseRow.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                        }
                    });

                    courseRow.setAnimation(animation);
                    divider.setAnimation(animation);

                    divider.setLayerType(View.LAYER_TYPE_HARDWARE, null);
                    courseRow.setLayerType(View.LAYER_TYPE_HARDWARE, null);

                    animation.setAnimationListener(new Animation.AnimationListener() {
                        @Override
                        public void onAnimationStart(Animation animation) {

                        }

                        @Override
                        public void onAnimationEnd(Animation animation) {
                            if (divider.getLayerType() != View.LAYER_TYPE_NONE) {
                                divider.setLayerType(View.LAYER_TYPE_NONE, null);
                            }
                            if (courseRow.getLayerType() != View.LAYER_TYPE_NONE) {
                                courseRow.setLayerType(View.LAYER_TYPE_NONE, null);
                            }
                        }

                        @Override
                        public void onAnimationRepeat(Animation animation) {

                        }
                    });

                    table.addView(divider);
                    table.addView(courseRow);
                }//for
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {}

        });

        if (schedule.getCourses().size() == 0) {
            table.addView(inflater.inflate(R.layout.view_no_records_row, table, false));
        }

        return view;


    }}