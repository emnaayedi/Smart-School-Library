package com.slensky.focussis.fragments;


import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.util.LogPrinter;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.animation.Animation;
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

public class ScheduleCoursesTabFragment extends Fragment {
    private static final String TAGn = "ScheduleCoursesTabFragment";
    private static final String CHANNEL_ID ="my_channel_01" ;
    private static String livre;
    private Schedule schedule;
    String html12,html11,html10,html13,html14,nom,nom1,nom2,nom4,nom5,nom6,html15;

    public ScheduleCoursesTabFragment() {
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


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_schedule_courses_tab, container, false);



        TableLayout table = (TableLayout) view.findViewById(R.id.table_courses);
        table.removeAllViews();
        TableRow headerRow = (TableRow) inflater.inflate(R.layout.view_schedule_course_header, table, false);
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
                                       final TableRow courseRow = (TableRow) inflater.inflate(R.layout.view_schedule_course, table, false);


                                       TextView period = (TextView) courseRow.findViewById(R.id.text_course_period);


                                       DatabaseReference ref_nom = database.child("emprunte/" + b[j] + "/nom_livre");
                                       ref_nom.addListenerForSingleValueEvent(new ValueEventListener() {
                                           @Override
                                           public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                               nom4 = dataSnapshot.getValue().toString();
                                               livre=nom4;
                                               period.setText(nom4);
                                               html14 = "<b>Nom Livre: </b>" + nom4 + "<br><br>";

                                           }

                                           @Override
                                           public void onCancelled(@NonNull DatabaseError databaseError) {

                                           }
                                       });



                                       DatabaseReference ref_id = database.child("emprunte/" + b[j] + "/id_livre");
                                       ref_id.addListenerForSingleValueEvent(new ValueEventListener() {
                                           @Override
                                           public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                               nom = dataSnapshot.getValue().toString();
                                               html10="<b>Id-livre: </b>" + nom + "<br><br>";



                                           }

                                           @Override
                                           public void onCancelled(@NonNull DatabaseError databaseError) {

                                           }
                                       });



                                       DatabaseReference ref_datee = database.child("emprunte/" + b[j] + "/date_emp");
                                       ref_datee.addListenerForSingleValueEvent(new ValueEventListener() {
                                           @Override
                                           public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                                nom1 = dataSnapshot.getValue().toString();
                                               html11="<b>Date Emprunte: </b>" + nom1+ "<br><br>";


                                           }

                                           @Override
                                           public void onCancelled(@NonNull DatabaseError databaseError) {

                                           }
                                       });
                                       TextView days = (TextView) courseRow.findViewById(R.id.text_course_days);

                                       DatabaseReference ref_retour = database.child("emprunte/" + b[j] + "/date_retour");
                                       ref_retour.addListenerForSingleValueEvent(new ValueEventListener() {
                                           @Override
                                           public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                               nom2 = dataSnapshot.getValue().toString();

                                               days.setText(nom2);
                                               html12=  "<b>Date Retour: </b>" + nom2 + "<br><br>";

                                           }

                                           @Override
                                           public void onCancelled(@NonNull DatabaseError databaseError) {

                                           }
                                       });
                                       DatabaseReference avis = database.child("emprunte/" + b[j] + "/Avis");
                                       avis.addListenerForSingleValueEvent(new ValueEventListener() {
                                           @Override
                                           public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                               nom6 = dataSnapshot.getValue().toString();
                                               html15="<b>Avis: </b>" + nom6 + "<br><br>";



                                           }

                                           @Override
                                           public void onCancelled(@NonNull DatabaseError databaseError) {

                                           }
                                       });

                                       //  days.setText(c.getDays());
                                       TextView room = (TextView) courseRow.findViewById(R.id.text_course_room);

                                       DatabaseReference ref_etat = database.child("emprunte/" + b[j] + "/etat");
                                       ref_etat.addListenerForSingleValueEvent(new ValueEventListener() {
                                           @Override
                                           public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                              nom5 = dataSnapshot.getValue().toString();
                                              html13= "<b>Etat: </b>" + nom5 + "<br><br>";
                                               room.setText(nom5);
                                               AlertDialog alertDialog = new AlertDialog.Builder(getActivity()).create();
                                               //alertDialog.setTitle();
                                               TextView messageView= new TextView(getContext());

                                               messageView.setText(Html.fromHtml(html10+html11+html12+html13+html14+html15));

                                               messageView.setTextIsSelectable(true);
                                               messageView.setTextColor(getResources().getColor(R.color.textPrimary));
                                               messageView.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimension(R.dimen.subheadingText));
                                               messageView.setPadding(16, 0, 16, 0);
                                               float dpi = getContext().getResources().getDisplayMetrics().density;

                                               alertDialog.setView(messageView, (int) (19 * dpi), (int) (19 * dpi), (int) (14 * dpi), (int) (5 * dpi));
                                               alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "OK",
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