package com.slensky.focussis.fragments;

import android.annotation.SuppressLint;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.os.Build;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;
import androidx.core.content.ContextCompat;
import androidx.appcompat.app.AlertDialog;

import android.preference.PreferenceManager;
import android.text.Html;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Switch;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.slensky.focussis.R;
import com.slensky.focussis.activities.MainActivity;
import com.slensky.focussis.data.Absences;
import com.slensky.focussis.network.FocusApi;
import com.slensky.focussis.network.FocusApiSingleton;
import com.slensky.focussis.util.TableRowAnimationController;
import com.slensky.focussis.views.AbsenceLabelView;

import org.apache.commons.lang.WordUtils;
import org.apache.commons.lang.math.NumberUtils;

import com.slensky.focussis.data.AbsenceDay;
import com.slensky.focussis.data.AbsencePeriod;
import com.slensky.focussis.util.DateUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import butterknife.BindView;

/**
 * Created by slensky on 5/24/17.
 */

public class AbsencesFragment extends NetworkTabAwareFragment {
    private static final String TAG = "AbsencesFragment";
    private static final String CHANNEL_ID ="my_channel_01" ;

  // @BindView(R.id.notif)
    //CheckBox _savenotif;

    private SharedPreferences loginPrefs;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view = getView();


        api = FocusApiSingleton.getApi();
        title = getString(R.string.absences_label);

        refresh();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment


        return inflater.inflate(R.layout.fragment_absences, container, false);

    }

    @Override
    public boolean hasTabs() {
        return false;
    }

    @Override
    public List<String> getTabNames() {
        return null;
    }

    DatabaseReference database = FirebaseDatabase.getInstance().getReference();
    DatabaseReference ref_place = database.child("biblio/place_total");
    DatabaseReference ref_etud = database.child("biblio/nb_etud_existe");
    DatabaseReference ref_temp = database.child("biblio/temp");
    DatabaseReference ref_hor  = database.child("horaires");
    DatabaseReference ref_clim  = database.child("climatiseur");
    private void addNotification() {

        NotificationCompat.Builder builder = new NotificationCompat.Builder(getContext(), CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_sms_notification)
                .setContentTitle("Smart Library")
                .setContentText("Much longer text that cannot fit one line...")
                .setStyle(new NotificationCompat.BigTextStyle()
                        .bigText("Much longer text that cannot fit one line..."))
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
        NotificationManager   notificationManager = (NotificationManager)getContext().getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(3,builder.build());}


    @SuppressLint("StringFormatInvalid")
    protected void onSuccess(Absences absences) {

        View view = getView();
        if (view != null) {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            final ScrollView scrollView = view.findViewById(R.id.scrollview_absences);
            TextView summary = view.findViewById(R.id.text_absences_summary);
            String html = "<b>" + getString(com.slensky.focussis.R.string.absences_days_possible) + ": </b>"
                    + "<br><br><b>" + getString(com.slensky.focussis.R.string.absences_days_attended) + ": </b>";
            html = html.replace(".0|", "").replace("|", "");

            summary.setText(Html.fromHtml(html));
            TextView summaryNB = view.findViewById(R.id.text_absences_summary_nb);
           /* String html1 = absences.getDaysPossible() + "|"
                    + "<br><br>" + absences.getDaysAttended() + "| <br>(" + absences.getDaysAttendedPercent() + "%)";
            html1 = html1.replace(".0|", "").replace("|", "");*/

            ref_place.addValueEventListener(new ValueEventListener() {
                @Override
                   public void onDataChange (DataSnapshot dataSnapshot) {

                    ref_etud.addValueEventListener(new ValueEventListener() {
                        int dispo = 0;

                        @Override
                        public void onDataChange(@NonNull DataSnapshot data) {
                            int etud = data.getValue(int.class);
                            int place = dataSnapshot.getValue(int.class);
                            dispo = place - etud;
                            String html3 = String.valueOf(place) + "<br><br>" + String.valueOf(dispo);
                            summaryNB.setText(Html.fromHtml(html3));

                            if (dispo == 0) {

                                AlertDialog alertDialog = new AlertDialog.Builder(getActivity()).create();
                                //alertDialog.setTitle();
                                TextView messageView = new TextView(getContext());
                                String html1 = "Pas de places disponibles !";
                                String html2 = " Vous serez notifié dès qu'une place sera disponible !";
                                String html = html1 + "<br><br>" + html2;
                                messageView.setText(Html.fromHtml(html));

                                messageView.setTextIsSelectable(true);
                                messageView.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
                                messageView.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimension(R.dimen.subheadingText));
                                messageView.setPadding(16, 0, 16, 0);
                                float dpi = getContext().getResources().getDisplayMetrics().density;

                                alertDialog.setView(messageView, (int) (19 * dpi), (int) (19 * dpi), (int) (14 * dpi), (int) (5 * dpi));
                                alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "OK",
                                        (dialog, which) -> dialog.dismiss());
                                alertDialog.show();

                            }


                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {
                            Log.e(TAG, "onCancelled", databaseError.toException());

                        }
                    });
                }



                @Override
                      public void onCancelled (DatabaseError databaseError){
                          Log.e(TAG, "onCancelled", databaseError.toException());
                    }
                          });



            TextView absentHeader = view.findViewById(R.id.text_absent_header);
            absentHeader.setText(Html.fromHtml(getString(com.slensky.focussis.R.string.absences_absent_header)));


            TextView absent = view.findViewById(R.id.text_absent);
            //absent.setText(Html.fromHtml(getString(com.slensky.focussis.R.string.absences_absent, absences.getPeriodsAbsentUnexcused())));
            ref_temp.addValueEventListener(new ValueEventListener() {

                @Override
                public void onDataChange(@NonNull DataSnapshot data) {
                    int temp = data.getValue(int.class);
                    String html_temp="<b>"+String.valueOf(temp)+" °c" ;
                    absent.setText(Html.fromHtml(html_temp));
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    Log.e(TAG, "onCancelled", databaseError.toException());

                }
            });
            TextView etathtml=(TextView) view.findViewById(R.id.text_other_mark);

String html13="Active";

                    etathtml.setText(Html.fromHtml(html13));

            TextView lund = view.findViewById(R.id.text_lun_vend);
            ref_hor.child("lundi/debut").addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot data) {
                    ref_hor.child("lundi/fin").addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataf) {
                            String debut = data.getValue(String.class);

                            String fin = dataf.getValue(String.class);

                            lund.setText(debut+" - "+fin);
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {
                            Log.e(TAG, "onCancelled", databaseError.toException());

                        }
                    });

                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    Log.e(TAG, "onCancelled", databaseError.toException());

                }
            });



            TextView mardi = view.findViewById(R.id.samedi);
            ref_hor.child("mardi/debut").addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot data) {
                    ref_hor.child("mardi/fin").addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataf) {
                            String debut = data.getValue(String.class);

                            String fin = dataf.getValue(String.class);

                            mardi.setText(debut+" - "+fin);
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {
                            Log.e(TAG, "onCancelled", databaseError.toException());

                        }
                    });

                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    Log.e(TAG, "onCancelled", databaseError.toException());

                }
            });

            TextView mercredi = view.findViewById(R.id.dimanche);
            ref_hor.child("mercredi/debut").addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot data) {
                    ref_hor.child("mercredi/fin").addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataf) {
                            String debut = data.getValue(String.class);

                            String fin = dataf.getValue(String.class);

                            mercredi.setText(debut+" - "+fin);
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {
                            Log.e(TAG, "onCancelled", databaseError.toException());

                        }
                    });

                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    Log.e(TAG, "onCancelled", databaseError.toException());

                }
            });

            TextView jeudi = view.findViewById(R.id.jeudi);
            ref_hor.child("jeudi/debut").addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot data) {
                    ref_hor.child("jeudi/fin").addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataf) {
                            String debut = data.getValue(String.class);

                            String fin = dataf.getValue(String.class);

                            jeudi.setText(debut+" - "+fin);
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {
                            Log.e(TAG, "onCancelled", databaseError.toException());

                        }
                    });

                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    Log.e(TAG, "onCancelled", databaseError.toException());

                }
            });

            TextView vendredi = view.findViewById(R.id.vendredi);
            ref_hor.child("vendredi/debut").addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot data) {
                    ref_hor.child("vendredi/fin").addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataf) {
                            String debut = data.getValue(String.class);

                            String fin = dataf.getValue(String.class);

                            vendredi.setText(debut+" - "+fin);
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {
                            Log.e(TAG, "onCancelled", databaseError.toException());

                        }
                    });

                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    Log.e(TAG, "onCancelled", databaseError.toException());

                }
            });

            TextView samedi = view.findViewById(R.id.sam);
            ref_hor.child("samedi/debut").addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot data) {
                    ref_hor.child("samedi/fin").addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataf) {
                            String debut = data.getValue(String.class);

                            String fin = dataf.getValue(String.class);

                            samedi.setText(debut+" - "+fin);

                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {
                            Log.e(TAG, "onCancelled", databaseError.toException());

                        }
                    });

                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    Log.e(TAG, "onCancelled", databaseError.toException());

                }
            });

            TextView dimanche = view.findViewById(R.id.dim);
            ref_hor.child("dimanche/debut").addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot data) {
                    ref_hor.child("dimanche/fin").addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataf) {
                            String debut = data.getValue(String.class);

                            String fin = dataf.getValue(String.class);

                            dimanche.setText(debut+" - "+fin);
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {
                            Log.e(TAG, "onCancelled", databaseError.toException());

                        }
                    });

                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    Log.e(TAG, "onCancelled", databaseError.toException());

                }
            });
            TextView otherMarksHeader = view.findViewById(R.id.text_other_marks_header);
            otherMarksHeader.setText(Html.fromHtml(getString(com.slensky.focussis.R.string.absences_other_marks)));







        }
        requestFinished = true;
    }

    @Override
    protected void makeRequest() {
        Request request = api.getAbsences(new FocusApi.Listener<Absences>() {
            @Override
            public void onResponse(Absences response) {
                onSuccess(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                onError(error);
            }
        });
        // absences takes quite a long time to load for some reason
        request.setRetryPolicy(new DefaultRetryPolicy(10000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
    }

    private void showAbsenceDialog(AbsenceDay d) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = LayoutInflater.from(getContext());
        FrameLayout fl = new FrameLayout(getContext());
        TableLayout table = new TableLayout(getContext());
        fl.addView(table);
        FrameLayout.LayoutParams lp = (FrameLayout.LayoutParams) table.getLayoutParams();
        lp.setMargins(dpToPixels(16), dpToPixels(16), dpToPixels(16), 0);

        for (AbsencePeriod p : d.getPeriods()) {
            TableRow row = (TableRow) inflater.inflate(com.slensky.focussis.R.layout.view_absence_dialog_row, table, false);
            TextView period = (TextView) row.findViewById(com.slensky.focussis.R.id.text_period);
            period.setText(unabbreviatePeriod(p.getPeriod()));
            AbsenceLabelView label = (AbsenceLabelView) row.findViewById(com.slensky.focussis.R.id.period_absence_label);
            label.setStatus(p.getStatus());
            TextView textLabel = (TextView) row.findViewById(com.slensky.focussis.R.id.text_absence_label);
            String status = statusToString(p.getStatus());
            textLabel.setText(status);

            table.addView(row);
        }
        builder.setView(fl)
                .setTitle(DateUtil.dateTimeToShortString(d.getDate()))
                .setPositiveButton("OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
        builder.create().show();
    }

    private int dpToPixels(int dp) {
        final float scale = getResources().getDisplayMetrics().density;
        return (int) (dp * scale + 0.5f);
    }

    private String statusToString(Absences.Status status) {
        switch (status) {
            case UNSET:
                return "Unset";
            case PRESENT:
                return "Present";
            case HALF_DAY:
                return "Half-day";
            case ABSENT:
                return "Absent";
            case EXCUSED:
                return "Excused";
            case LATE:
                return "Late";
            case TARDY:
                return "Tardy";
            case MISC:
                return "Misc.";
            case OFFSITE:
                return "Off Site";
        }
        return null;
    }

    private String unabbreviatePeriod(String period) {
        String p = period.toLowerCase(); // for more robust comparisons

        // if the period is an integer, return "Period i"
        if (NumberUtils.isDigits(p)) {
            return "Period " + p;
        }

        // if the period is in the form "P3", return "Period 3"
        if (p.charAt(0) == 'p' && NumberUtils.isDigits(p.substring(1))) {
            return "Period " + p.substring(1);
        }

        // these abbreviations can be found in the absences page of sixth graders
        if (p.equals("hr")) {
            return "Homeroom";
        }
        if (p.equals("sh")) {
            return "Study Hall";
        }

        // possible abbreviations for advisory
        if (p.equals("adv") || p.equals("adv.")) {
            return "Advisory";
        }

        // no abbreviation/unknown pattern, capitalize it just in case it isn't for some reason
        return WordUtils.capitalize(period);
    }

}
