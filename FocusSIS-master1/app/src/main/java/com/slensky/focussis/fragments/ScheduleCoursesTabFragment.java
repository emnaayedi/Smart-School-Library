package com.slensky.focussis.fragments;


import android.os.Bundle;
import android.util.Log;
import android.util.LogPrinter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.gson.Gson;
import com.slensky.focussis.FocusApplication;
import com.slensky.focussis.R;
import com.slensky.focussis.data.Schedule;
import com.slensky.focussis.data.ScheduleCourse;
import com.slensky.focussis.util.GsonSingleton;
import com.slensky.focussis.util.TableRowAnimationController;

import java.util.List;

/**
 * Created by slensky on 5/9/18.
 */

public class ScheduleCoursesTabFragment extends Fragment {
    private static final String TAGn = "ScheduleCoursesTabFragment";
    private Schedule schedule;

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
        TextView header = (TextView) view.findViewById(R.id.text_schedule_header);
        header.setText(schedule.getCurrentMarkingPeriod().getName());

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
                                               String nom = dataSnapshot.getValue().toString();
                                               System.out.println(nom);
                                               period.setText(nom);

                                           }

                                           @Override
                                           public void onCancelled(@NonNull DatabaseError databaseError) {

                                           }
                                       });

                                       TextView name = (TextView) courseRow.findViewById(R.id.text_course_name);

                                       DatabaseReference ref_id = database.child("emprunte/" + b[j] + "/id_livre");
                                       ref_id.addListenerForSingleValueEvent(new ValueEventListener() {
                                           @Override
                                           public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                               String nom = dataSnapshot.getValue().toString();
                                               System.out.println(nom);
                                               name.setText(nom);

                                           }

                                           @Override
                                           public void onCancelled(@NonNull DatabaseError databaseError) {

                                           }
                                       });

                                       TextView teacher = (TextView) courseRow.findViewById(R.id.text_course_teacher);

                                       DatabaseReference ref_datee = database.child("emprunte/" + b[j] + "/date_emp");
                                       ref_datee.addListenerForSingleValueEvent(new ValueEventListener() {
                                           @Override
                                           public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                               String nom = dataSnapshot.getValue().toString();
                                               System.out.println(nom);
                                               teacher.setText(nom);

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
                                               String nom = dataSnapshot.getValue().toString();
                                               System.out.println(nom);
                                               days.setText(nom);

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
                                               String nom = dataSnapshot.getValue().toString();
                                               System.out.println(nom);
                                               room.setText(nom);

                                           }

                                           @Override
                                           public void onCancelled(@NonNull DatabaseError databaseError) {

                                           }
                                       });

                                       // room.setText(c.getRoom().split(" ")[0]); // changes jr/sr area into just jr/sr for brevity


                                       final View divider = inflater.inflate(R.layout.view_divider, table, false);

                                       Animation animation = animationController.nextAnimation();
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