package com.slensky.focussis.activities;

import android.app.AlertDialog;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.preference.PreferenceManager;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.slensky.focussis.FocusApplication;
import com.slensky.focussis.R;
import com.slensky.focussis.data.FocusPreferences;
import com.slensky.focussis.data.Schedule;
import com.slensky.focussis.data.ScheduleCourse;
import com.slensky.focussis.fragments.PageFragment;
import com.slensky.focussis.fragments.PortalFragment;
import com.slensky.focussis.fragments.ScheduleCoursesTabFragment;
import com.slensky.focussis.network.FocusApi;
import com.slensky.focussis.network.FocusApiSingleton;
import com.slensky.focussis.network.FocusDebugApi;
import com.slensky.focussis.util.TableRowAnimationController;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;

import static java.security.AccessController.getContext;

public class LoginActivity extends AppCompatActivity {
    private static final String TAG = "LoginActivity";

    @BindView(com.slensky.focussis.R.id.input_username)
    EditText _usernameText;
    @BindView(com.slensky.focussis.R.id.input_password)
    EditText _passwordText;
    @BindView(com.slensky.focussis.R.id.text_layout_email)
    TextInputLayout _usernameLayout;
    @BindView(com.slensky.focussis.R.id.text_layout_password)
    TextInputLayout _passwordLayout;
    @BindView(com.slensky.focussis.R.id.btn_login)
    Button _loginButton;
    @BindView(com.slensky.focussis.R.id.check_remember)
    CheckBox _saveLoginCheckBox;

    private SharedPreferences loginPrefs;
    private SharedPreferences sharedpreferences;

    private AlertDialog languageErrorDialog;
    private FocusPreferences focusPreferences;

    private FocusApi api;

    private SharedPreferences defaultSharedPrefs;

    // count the number of times the login has failed due to invalid credentials
    // if it has failed too many times, show a different message ensuring the student is from ASD
    private int authErrors;

    DatabaseReference database = FirebaseDatabase.getInstance().getReference();
    DatabaseReference ref_emp = database.child("emprunte");
    DatabaseReference ref_place = database.child("biblio/place_total");
    DatabaseReference ref_etud_existe = database.child("biblio/nb_etud_existe");


    private String livre;
    private String etat;
    private String e="notReturned";


    private static final String CHANNEL_ID1 ="my_channel_01" ;
    private static final String CHANNEL_ID2 ="my_channel_02" ;
    public static int getDay() {
        SimpleDateFormat day = new SimpleDateFormat("dd", Locale.getDefault());
        Date date = new Date();
        String d=day.format(date);
        int d1 =Integer.valueOf(d);
        // int d1=01;
        return d1;

    }
    public static int getMonth() {
        SimpleDateFormat month = new SimpleDateFormat("MM", Locale.getDefault());
        Date date = new Date();
        String m=month.format(date);
        int m1 =Integer.valueOf(m);
        //int m1=05;
        return m1;
    }
    public static int getYear() {
        SimpleDateFormat year = new SimpleDateFormat("yyyy", Locale.getDefault());
        Date date = new Date();
        String y=year.format(date);
        int y1 =Integer.valueOf(y);
        return  y1;}

    public void addNotification(String nom) {
        Intent notifyIntent = new Intent(this, ScheduleCoursesTabFragment.class) ;
        Random r = new Random();

        PendingIntent notifyPendingIntent = PendingIntent.getActivity(
                this, 0, notifyIntent, PendingIntent.FLAG_UPDATE_CURRENT
        );
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANNEL_ID1)
                .setSmallIcon(R.drawable.notification)
                .setContentTitle("Smart Library")
                .setContentText("N'oubliez pas de rendre le livre "+nom+" au bibliotheque")
                .setStyle(new NotificationCompat.BigTextStyle()
                        .bigText("N'oubliez pas de rendre le livre "+ nom+" au bibliotheque"))
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setAutoCancel(true)
                .setContentIntent(notifyPendingIntent);


        NotificationManager notificationManager = (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(15,builder.build());
    }

    public void notif_place() {
        Intent notifyIntent = new Intent(this, ScheduleCoursesTabFragment.class) ;

        PendingIntent notifyPendingIntent = PendingIntent.getActivity(
                this, 0, notifyIntent, PendingIntent.FLAG_UPDATE_CURRENT
        );
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANNEL_ID1)
                .setSmallIcon(R.drawable.notification)
                .setContentTitle("Smart Library")
                .setContentText("Existe une place disponible dans la bibliotheque")
                .setStyle(new NotificationCompat.BigTextStyle()
                        .bigText("Existe une place disponible" +
                                " dans la bibliotheque"))

                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setAutoCancel(true)

                .setContentIntent(notifyPendingIntent);


        NotificationManager notificationManager = (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(5,builder.build());
    }

    public void notif_retard(String nom) {
        Intent notifyIntent = new Intent(this,ScheduleCoursesTabFragment.class) ;
        Random r = new Random();

        PendingIntent notifyPendingIntent = PendingIntent.getActivity(
                this, 0, notifyIntent, PendingIntent.FLAG_UPDATE_CURRENT
        );
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANNEL_ID2)
                .setSmallIcon(R.drawable.notification)
                .setContentTitle("Smart Library")
                .setContentText("Vous avez depassez le temps limite pour rendre le livre "+nom)
                .setStyle(new NotificationCompat.BigTextStyle()
                        .bigText("Vous avez depassez le temps limite pour rendre le livre "+nom))
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setAutoCancel(true)
                .setContentIntent(notifyPendingIntent);
        NotificationManager notificationManager = (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(16,builder.build());
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        setTheme(com.slensky.focussis.R.style.AppTheme_Light);
        super.onCreate(savedInstanceState);

        // finish activity and resume MainActivity if the app was already open
        // https://stackoverflow.com/questions/19545889/app-restarts-rather-than-resumes
        if (!isTaskRoot()
                && getIntent().hasCategory(Intent.CATEGORY_LAUNCHER)
                && getIntent().getAction() != null
                && getIntent().getAction().equals(Intent.ACTION_MAIN)) {

            finish();
            return;
        }

        setContentView(com.slensky.focussis.R.layout.activity_login);
        ButterKnife.bind(this);
        loginPrefs = getSharedPreferences(getString(com.slensky.focussis.R.string.login_prefs), MODE_PRIVATE);
        authErrors = 0;
        Intent intent = getIntent();

        // show a dialog indicating that the app only works for ASD. Mandatory delay before allowing the user to close it
        if (!loginPrefs.getBoolean(getString(R.string.login_prefs_read_school_msg), false)) {
            final AlertDialog schoolMessage = new AlertDialog.Builder(this)
                    .setTitle(getString(R.string.login_asd_notice_title))
                    .setMessage(getString(R.string.login_asd_notice_message))
                    .setNegativeButton(R.string.login_asd_notice_negative, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            finish();
                        }
                    }).setPositiveButton(R.string.login_asd_notice_positive, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            final SharedPreferences.Editor loginPrefsEditor = loginPrefs.edit();
                            loginPrefsEditor.putBoolean(getString(R.string.login_prefs_read_school_msg), true);
                            loginPrefsEditor.apply();
                        }
                    }).setCancelable(false)
                    .create();

            schoolMessage.show();

            final Button positive = schoolMessage.getButton(AlertDialog.BUTTON_POSITIVE);
            positive.setEnabled(false);

            final CharSequence positiveMessage = positive.getText();
            final Thread waitToEnableButtonThread = new Thread(new Runnable() {
                @Override
                public void run() {
                    int waitTime = 6;
                    while (true) {
                        final int finalWaitTime = waitTime;
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                positive.setText(positiveMessage + " (" + finalWaitTime + ")");
                            }
                        });
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        waitTime--;
                        if (waitTime == 0) {
                            break;
                        }
                    }
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            positive.setText(positiveMessage);
                            positive.setEnabled(true);
                        }
                    });
                }
            });
            waitToEnableButtonThread.start();

        }

        RelativeLayout rl = (RelativeLayout) findViewById(com.slensky.focussis.R.id.login_layout);
        rl.setPadding(rl.getPaddingLeft(), getWindowManager().getDefaultDisplay().getHeight() / 6, rl.getPaddingRight(), rl.getPaddingBottom());

        _loginButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                login();
            }
        });

        Boolean saveLogin = loginPrefs.getBoolean(getString(R.string.login_prefs_save_login), false);
        if (saveLogin) {
            _usernameText.setText(loginPrefs.getString(getString(com.slensky.focussis.R.string.login_prefs_username), ""));
            _passwordText.setText(loginPrefs.getString(getString(com.slensky.focussis.R.string.login_prefs_password), ""));
            _saveLoginCheckBox.setChecked(true);
        }

        languageErrorDialog = new AlertDialog.Builder(this)
                .setTitle(com.slensky.focussis.R.string.language_alert_title)
                .setMessage(com.slensky.focussis.R.string.language_alert_message)
                .setPositiveButton(com.slensky.focussis.R.string.language_alert_positive, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                        final ProgressDialog progressDialog = ProgressDialog.show(LoginActivity.this, null, getString(com.slensky.focussis.R.string.language_change_progress), true);
                        focusPreferences.setEnglishLanguage(true);
                        api.setPreferences(focusPreferences, new FocusApi.Listener<FocusPreferences>() {
                            @Override
                            public void onResponse(FocusPreferences response) {
                                progressDialog.hide();
                                progressDialog.dismiss();
                                login();
                            }
                        }, new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                progressDialog.hide();
                                progressDialog.dismiss();
                                onLoginFailed(getString(com.slensky.focussis.R.string.network_error_timeout));
                            }
                        });
                    }
                })
                .setNegativeButton(com.slensky.focussis.R.string.language_alert_negative, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                })
                .create();

        defaultSharedPrefs = PreferenceManager.getDefaultSharedPreferences(this);
        if (saveLogin && defaultSharedPrefs.getBoolean("automatic_login", false)
                && !intent.getBooleanExtra(getString(com.slensky.focussis.R.string.EXTRA_DISABLE_AUTO_SIGN_IN), false)
                && loginPrefs.getString(getString(com.slensky.focussis.R.string.login_prefs_password), null) != null) { // occurs after password change
            login();
        }

    }


    public void login() {
        _loginButton.setEnabled(false);

        String tempUsername = _usernameText.getText().toString();
        if (tempUsername.contains("@")) {
            tempUsername = tempUsername.split("@")[0];
        }

        final String username = tempUsername;
        final String password = _passwordText.getText().toString();

        DatabaseReference database = FirebaseDatabase.getInstance().getReference();
        DatabaseReference ref_etud = database.child("etudiants");
        sharedpreferences = getSharedPreferences("chaima", Context.MODE_PRIVATE);

        ref_etud.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot ds : dataSnapshot.getChildren()) {

                    // Check if key 'title' exists and if title value is equal to value to save (title_val)
                    if (ds.hasChild("login") && (username.equals(ds.child("login").getValue()))) {
                        if (ds.hasChild( "passwd") && (password.equals(ds.child("passwd").getValue()))) {
                            Log.d(TAG, "Using debug API");
                            FocusApplication.USE_DEBUG_API = true;
                            FocusApplication.loginn = username;

                            SharedPreferences.Editor editor = sharedpreferences.edit();
                            editor.putInt("2", 1109695);
                            editor.apply();

                        }

                        else {
                            FocusApplication.USE_DEBUG_API = false;
                        }

                    }
                            /*else {
                            if (ds.hasChild("login") && (! username.equals(ds.child("login").getValue())) &&
                                    !(password.equals(ds.child("passwd").getValue()))) {
                                FocusApplication.USE_DEBUG_API = false;

                            }}*/

                    boolean attemptLogin = true;
                    if (username.isEmpty()) {
                        _usernameLayout.setError(getString(R.string.login_blank_username_error));
                        attemptLogin = false;
                    } else {
                        _usernameLayout.setErrorEnabled(false);
                    }
                    if (password.isEmpty()) {
                        _passwordLayout.setError(getString(R.string.login_blank_password_error));
                        attemptLogin = false;
                    } else {
                        _passwordLayout.setErrorEnabled(false);
                    }

                    if (!attemptLogin) {
                        _loginButton.setEnabled(true);
                        return;
                    }

                    final ProgressDialog progressDialog = ProgressDialog.show(LoginActivity.this, null, getString(R.string.auth_progress_dialog), true);
                    DatabaseReference ref_emp = database.child("emprunte");
                    if (FocusApplication.USE_DEBUG_API) {
                        api = new FocusDebugApi(username, password, getApplicationContext());
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

                                    DatabaseReference ref_nom = database.child("emprunte/" + b[j] + "/nom_livre");
                                    ref_nom.addListenerForSingleValueEvent(new ValueEventListener() {
                                        @Override
                                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                            String nom = dataSnapshot.getValue().toString();
                                            livre=nom;
                                            System.out.println(nom);

                                        }

                                        @Override
                                        public void onCancelled(@NonNull DatabaseError databaseError) {

                                        }
                                    });
                                    DatabaseReference ref_etat = database.child("emprunte/" + b[j] + "/etat");
                                    ref_etat.addListenerForSingleValueEvent(new ValueEventListener() {
                                        @Override
                                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                            String nom = dataSnapshot.getValue().toString();
                                            etat=nom;
                                        }

                                        @Override
                                        public void onCancelled(@NonNull DatabaseError databaseError) {

                                        }
                                    });

                                    DatabaseReference ref_retour = database.child("emprunte/" + b[j] + "/date_retour");
                                    ref_retour.addListenerForSingleValueEvent(new ValueEventListener() {
                                        @Override
                                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                            String nom = dataSnapshot.getValue().toString();
                                            String d=nom.substring(0,2); int d1 = Integer.valueOf(d);
                                            String m=nom.substring(3,5); int m1 = Integer.valueOf(m);
                                            String y=nom.substring(6,10); int y1 = Integer.valueOf(y);
                                            if (((m1==getDay()+1)||(m1==getDay()))&&(d1==getMonth())&&(y1==getYear())&&(etat.equals(e))){
                                                addNotification(livre);
                                            }//avec retard
                                            if ((((m1==30)&&((d1==4)||(d1==6)||(d1==9)|(d1==11)))||
                                                    ((m1==31)&&((d1==3)||(d1==5)||(d1==7)||(d1==8)||(d1==10)))||((m1==28)&&(d1==2))||
                                                    ((m1==29)&&(d1==2)))&&
                                                    (getDay()==1)&&
                                                    (d1==getMonth()-1)&&(y1==getYear())&&(etat.equals(e))){
                                                addNotification(livre);
                                            }

                                            if ((m1<getDay())&&(d1==getMonth())&&(y1==getYear())&&(etat.equals(e))){
                                                notif_retard(livre);
                                            }


                                        }

                                        @Override
                                        public void onCancelled(@NonNull DatabaseError databaseError) {

                                        }
                                    });

                                    ref_place.addValueEventListener(new ValueEventListener() {
                                        @Override
                                        public void onDataChange (DataSnapshot dataSnapshot1){

                                            ref_etud_existe.addValueEventListener(new ValueEventListener() {
                                                int dispo=0;
                                                @Override
                                                public void onDataChange(@NonNull DataSnapshot data) {
                                                    int etud = data.getValue(int.class);
                                                    int place = dataSnapshot1.getValue(int.class);
                                                    dispo=place-etud;
                                                    if (dispo==1){
                                                        notif_place();
                                                    }

                                                }
                                                @Override
                                                public void onCancelled (@NonNull DatabaseError databaseError){

                                                }
                                            });




                                        }

                                        @Override
                                        public void onCancelled(@NonNull DatabaseError databaseError) {

                                        }
                                    });

                                }//for
                            }
                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {}

                        });
                    }
                    else {
                        api = new FocusApi(username, password, getApplicationContext());
                    }
                    api.login(new FocusApi.Listener<Boolean>() {
                        @Override
                        public void onResponse(Boolean response) {
                            if (response) {
                                Log.d(TAG, "Login successful");
                                final SharedPreferences.Editor loginPrefsEditor = loginPrefs.edit();
                                if (_saveLoginCheckBox.isChecked()) {
                                    Log.d(TAG, "Remembering user " + username);
                                    loginPrefsEditor.putBoolean(getString(R.string.login_prefs_save_login), true);
                                    loginPrefsEditor.putString(getString(R.string.login_prefs_username), username);
                                    loginPrefsEditor.putString(getString(R.string.login_prefs_password), password);
                                    loginPrefsEditor.apply();
                                } else {
                                    loginPrefsEditor.putBoolean(getString(R.string.login_prefs_save_login), false);
                                    loginPrefsEditor.putString(getString(R.string.login_prefs_username), "");
                                    loginPrefsEditor.putString(getString(R.string.login_prefs_password), "");
                                    loginPrefsEditor.apply();
                                }
                                loginPrefsEditor.apply();

                                if (defaultSharedPrefs.getBoolean("always_check_preferences", true)) {
                                    api.getPreferences(new FocusApi.Listener<FocusPreferences>() {
                                        @Override
                                        public void onResponse(FocusPreferences response) {
                                            focusPreferences = response;
                                            if (focusPreferences.isEnglishLanguage()) {
                                                progressDialog.hide();
                                                progressDialog.dismiss();
                                                FocusApiSingleton.setApi(api);
                                                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                                intent.putExtra(getString(R.string.EXTRA_USERNAME), username);
                                                intent.putExtra(getString(R.string.EXTRA_PASSWORD), password);
                                                startActivity(intent);
                                                finish();
                                            } else {
                                                progressDialog.hide();
                                                progressDialog.dismiss();
                                                _loginButton.setEnabled(true);
                                                languageErrorDialog.show();
                                            }
                                        }
                                    }, new Response.ErrorListener() {
                                        @Override
                                        public void onErrorResponse(VolleyError error) {
                                            progressDialog.hide();
                                            Log.d(TAG, "Getting preferences failed");
                                            if (error.networkResponse != null) {
                                                if (error.networkResponse.statusCode == 500) {
                                                    onLoginFailed(getString(R.string.network_error_server));
                                                } else {
                                                    onLoginFailed(getString(R.string.network_error_timeout));
                                                }
                                            } else {
                                                onLoginFailed(getString(R.string.network_error_timeout));
                                            }
                                        }
                                    });
                                } else {
                                    progressDialog.hide();
                                    try {
                                        progressDialog.dismiss();
                                    } catch (IllegalArgumentException e) {
                                        Log.e(TAG, "Not attached to window manager, could not dismiss dialog");
                                        e.printStackTrace();
                                    }
                                    FocusApiSingleton.setApi(api);
                                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                    intent.putExtra(getString(R.string.EXTRA_USERNAME), username);
                                    intent.putExtra(getString(R.string.EXTRA_PASSWORD), password);
                                    startActivity(intent);
                                    finish();
                                }
                            } else {
                                progressDialog.hide();
                                Log.d(TAG, "Login unsuccessful");
                                onLoginFailed(getString(R.string.network_error_auth));
                                _passwordText.setText("");
                            }
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            progressDialog.hide();
                            Log.d(TAG, "Login failed");
                            if (error.networkResponse != null) {
                                if (error.networkResponse.statusCode == 500) {
                                    onLoginFailed(getString(R.string.network_error_server));
                                } else {
                                    onLoginFailed(getString(R.string.network_error_timeout));
                                }
                            } else {
                                onLoginFailed(getString(R.string.network_error_timeout));
                            }
                        }
                    });
                }//for


            }//ondatachange mté3 username
            @Override
            public void onCancelled(DatabaseError databaseError) { }
        });//ref mté3 username

    }//login

   /* @Override
    public void onBackPressed() {
        // disable going back to the MainActivity
        moveTaskToBack(true);
    }*/

    public void onLoginFailed(String error) {
        Log.e(TAG, error);
        /*if (error.equals(getString(R.string.network_error_auth))) {
            authErrors++;
            if (authErrors > 2) {
                error = getString(R.string.network_error_auth_expanded);
            }
        }*/
        final String finalError = error;
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(getBaseContext(), finalError, Toast.LENGTH_LONG).show();
            }
        });
        _loginButton.setEnabled(true);
    }




}