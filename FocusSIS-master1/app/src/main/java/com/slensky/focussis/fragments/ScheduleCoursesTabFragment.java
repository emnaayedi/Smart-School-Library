package com.slensky.focussis.fragments;


import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
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

/**
 * Created by slensky on 5/9/18.
 */

public class ScheduleCoursesTabFragment extends Fragment {
    private static final String TAGn = "ScheduleCoursesTabFragment";
    private static final String CHANNEL_ID ="my_channel_01" ;
    private static String livre;
    private Schedule schedule;

    public ScheduleCoursesTabFragment() {
        // required empty constructor
    }

    public static int getDay() {
        SimpleDateFormat day = new SimpleDateFormat("dd", Locale.getDefault());
        Date date = new Date();
        String d=day.format(date);
        int d1 =Integer.valueOf(d);
        return d1;

    }
    public static String getMonth() {
        SimpleDateFormat month = new SimpleDateFormat("MM", Locale.getDefault());
        Date date = new Date();
        String m=month.format(date);
        return m;
    }
    public static String getYear() {
        SimpleDateFormat year = new SimpleDateFormat("yyyy", Locale.getDefault());
        Date date = new Date();
        String y=year.format(date);
        return  y;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Gson gson = GsonSingleton.getInstance();
        schedule = gson.fromJson(getArguments().getString(getString(R.string.EXTRA_SCHEDULE)), Schedule.class);
    }
    DatabaseReference database = FirebaseDatabase.getInstance().getReference();
    DatabaseReference ref_emp = database.child("emprunte");

    public void addNotification(String nom) {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(getContext(), CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_sms_notification)
                .setContentTitle("Smart Library")
                .setContentText("Ne oubliez pas de rendre le livre "+nom+" au bibliotheque")
                .setStyle(new NotificationCompat.BigTextStyle()
                        .bigText("Ne oubliez pas de rendre le livre "+ nom+" au bibliotheque"))
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);

        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        Intent notifyIntent = new Intent(getActivity(),ScheduleCoursesTabFragment.class) ;
// Set the Activity to start in a new, empty task

// Create the PendingIntent
        PendingIntent notifyPendingIntent = PendingIntent.getActivity(
                getContext(), 0, notifyIntent, PendingIntent.FLAG_UPDATE_CURRENT
        );
        builder.setAutoCancel(true);
        builder.setContentIntent(notifyPendingIntent);
        NotificationManager notificationManager = (NotificationManager)getContext().getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(0,builder.build());
    }
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
                                               livre=nom;
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
                                               String d=nom.substring(0,2);
                                               String m=nom.substring(3,5);
                                               String y=nom.substring(6,7);
                                               int d1 = Integer.valueOf(d);
                                                if (d1==getDay()-1){
                                                    addNotification(livre);
                                                }
                                               System.out.println(d1);

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