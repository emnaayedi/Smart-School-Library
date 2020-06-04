package com.slensky.focussis.fragments;

import android.content.DialogInterface;
import android.graphics.Rect;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import android.text.Html;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.animation.Animation;
import android.widget.ScrollView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.android.volley.Response;
import com.android.volley.VolleyError;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.slensky.focussis.R;
import com.slensky.focussis.data.Referral;
import com.slensky.focussis.data.Referrals;
import com.slensky.focussis.network.FocusApi;
import com.slensky.focussis.network.FocusApiSingleton;
import com.slensky.focussis.util.DateUtil;
import com.slensky.focussis.util.TableRowAnimationController;

import java.util.Collections;
import java.util.List;
import android.util.Log;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import java.util.Map;


/**
 * Created by slensky on 5/23/17.
 */

public class ReferralsFragment extends NetworkTabAwareFragment {
    String html2,possachat,resume,nbpage,collection,editeur,datePar,nomDoc,nomDomaine,dateentree;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        api = FocusApiSingleton.getApi();
        title = getString(R.string.referrals_label);
        refresh();
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_referrals, container, false);
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

    DatabaseReference database = FirebaseDatabase.getInstance().getReference();
    DatabaseReference ref_editeur = database.child("stockage/-M3C1loXpQdSFD3GwqHF/Editeur");
    DatabaseReference ref_collection  = database.child("stockage/-M3C1loXpQdSFD3GwqHF/Collection");
    DatabaseReference ref_date_entree =database.child("stockage/-M3C1loXpQdSFD3GwqHF/dateEntree");
    DatabaseReference ref_date_parution =database.child("stockage/-M3C1loXpQdSFD3GwqHF/dateParution");
    DatabaseReference id  = database.child("stockage/-M3C1loXpQdSFD3GwqHF/id");
    DatabaseReference ref_nom_doc  = database.child("stockage/-M3C1loXpQdSFD3GwqHF/nomDoc");
    DatabaseReference ref_nom_domaine  = database.child("stockage/-M3C1loXpQdSFD3GwqHF/nomDomain");
    DatabaseReference ref_resume =database.child("stockage/-M3C1loXpQdSFD3GwqHF/Resume");
    DatabaseReference ref_possAchat =database.child("stockage/-M3C1loXpQdSFD3GwqHF/PossAchat");
    DatabaseReference ref_nb_page =database.child("stockage/-M3C1loXpQdSFD3GwqHF/nbrePage");

    protected void onSuccess(Referrals referrals) {
        List<Referral> refList = referrals.getReferrals();
        Collections.reverse(refList);
        View view = getView();
        if (view != null) {
            LayoutInflater inflater = LayoutInflater.from(getContext());

            TableLayout table = (TableLayout) view.findViewById(R.id.table_referrals);
            table.removeAllViews();
            TableRow headerRow = (TableRow) inflater.inflate(R.layout.view_referral_header, table, false);
            table.addView(headerRow);

            final ScrollView scrollView = view.findViewById(R.id.scrollview_referrals);

            TableRowAnimationController animationController = new TableRowAnimationController(getContext());

            for (final Referral r : refList) {
                final TableRow referralRow = (TableRow) inflater.inflate(R.layout.view_referral, table, false);
                TextView violation = (TextView) referralRow.findViewById(R.id.text_violation);
                TextView reporter = (TextView) referralRow.findViewById(R.id.text_reporter_name);
                TextView entryDate = (TextView) referralRow.findViewById(R.id.text_entry_date);
                ref_date_parution.addValueEventListener(new ValueEventListener() {
                    private static final String TAG ="" ;

                    @Override
                    public void onDataChange(@NonNull DataSnapshot data) {
                        datePar = data.getValue(String.class);
                        String html_date="<b>"+String.valueOf(datePar) ;
                        entryDate.setText(Html.fromHtml(html_date));
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                        Log.e(TAG, "onCancelled", databaseError.toException());

                    }
                });
                ref_nom_doc.addValueEventListener(new ValueEventListener() {
                    private static final String TAG ="" ;

                    @Override
                    public void onDataChange(@NonNull DataSnapshot data) {
                        nomDoc = data.getValue(String.class);
                        String html_nom_doc="<b>"+String.valueOf(nomDoc) ;
                        reporter.setText(Html.fromHtml(html_nom_doc));
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                        Log.e(TAG, "onCancelled", databaseError.toException());

                    }
                });
                ref_nom_domaine.addValueEventListener(new ValueEventListener() {
                    private static final String TAG ="" ;

                    @Override
                    public void onDataChange(@NonNull DataSnapshot data) {
                        nomDomaine = data.getValue(String.class);
                        String html_nom_dom="<b>"+String.valueOf(nomDomaine) ;
                        violation.setText(Html.fromHtml(html_nom_dom));
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                        Log.e(TAG, "onCancelled", databaseError.toException());

                    }
                });
                referralRow.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        showReferralDialog(r);
                    }
                });

                final View divider = inflater.inflate(R.layout.view_divider, table, false);

                final Animation animation = animationController.nextAnimation();
                //referralRow.setAnimation(animation);
                //divider.setAnimation(animation);

                divider.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                    @Override
                    public void onGlobalLayout() {
                        Rect scrollBounds = new Rect();
                        scrollView.getHitRect(scrollBounds);
                        if (divider.getLocalVisibleRect(scrollBounds)) {
                            divider.setAnimation(animation);
                        }
                        divider.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                    }
                });

                referralRow.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                    @Override
                    public void onGlobalLayout() {
                        Rect scrollBounds = new Rect();
                        scrollView.getHitRect(scrollBounds);
                        if (referralRow.getLocalVisibleRect(scrollBounds)) {
                            referralRow.setAnimation(animation);
                        }
                        referralRow.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                    }
                });

                divider.setLayerType(View.LAYER_TYPE_HARDWARE, null);
                referralRow.setLayerType(View.LAYER_TYPE_HARDWARE, null);

                animation.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        if (divider.getLayerType() != View.LAYER_TYPE_NONE) {
                            divider.setLayerType(View.LAYER_TYPE_NONE, null);
                        }
                        if (referralRow.getLayerType() != View.LAYER_TYPE_NONE) {
                            referralRow.setLayerType(View.LAYER_TYPE_NONE, null);
                        }
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });

                table.addView(divider);
                table.addView(referralRow);
            }

            if (refList.size() == 0) {
                table.addView(inflater.inflate(R.layout.view_no_records_row, table, false));
            }

        }

        requestFinished = true;
    }

    @Override
    protected void makeRequest() {
        api.getReferrals(new FocusApi.Listener<Referrals>() {
            @Override
            public void onResponse(Referrals response) {
                onSuccess(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                onError(error);
            }
        });
    }



    public void showReferralDialog(Referral r) {
        AlertDialog alertDialog = new AlertDialog.Builder(getActivity()).create();
        //alertDialog.setTitle();
        TextView messageView = new TextView(getContext());

        ref_collection.addValueEventListener(new ValueEventListener() {
            private static final String TAG ="" ;

            @Override
            public void onDataChange(@NonNull DataSnapshot data) {
                collection = data.getValue(String.class);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.e(TAG, "onCancelled", databaseError.toException());

            }
        });
        ref_nb_page.addValueEventListener(new ValueEventListener() {
            private static final String TAG ="" ;

            @Override
            public void onDataChange(@NonNull DataSnapshot data) {
                nbpage = data.getValue(String.class);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.e(TAG, "onCancelled", databaseError.toException());

            }
        });
        ref_date_entree.addValueEventListener(new ValueEventListener() {
            private static final String TAG ="" ;

            @Override
            public void onDataChange(@NonNull DataSnapshot data) {
                dateentree= data.getValue(String.class);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.e(TAG, "onCancelled", databaseError.toException());

            }
        });
        ref_editeur.addValueEventListener(new ValueEventListener() {
            private static final String TAG ="" ;

            @Override
            public void onDataChange(@NonNull DataSnapshot data) {
                editeur = data.getValue(String.class);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.e(TAG, "onCancelled", databaseError.toException());

            }
        });
        ref_resume.addValueEventListener(new ValueEventListener() {
            private static final String TAG ="" ;

            @Override
            public void onDataChange(@NonNull DataSnapshot data) {
                resume = data.getValue(String.class);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.e(TAG, "onCancelled", databaseError.toException());

            }
        });
        ref_possAchat.addValueEventListener(new ValueEventListener() {
            private static final String TAG ="" ;


            @Override
            public void onDataChange(@NonNull DataSnapshot data) {
                possachat = data.getValue(String.class);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.e(TAG, "onCancelled", databaseError.toException());

            }
        });
         html2 = "<b>Titre: </b>" + nomDoc + "<br><br>" +
                "<b>Domaine: </b>" +nomDomaine + "<br><br>" +
                "<b>Collection: </b>" + collection + "<br><br>" +
                "<b>Nombre Page: </b>" + nbpage + "<br><br>" +
                "<b>Date Parution: </b>" + datePar + "<br><br>" +
                "<b>Date Importation: </b>" +dateentree + "<br><br>" +
                "<b>Editeur:</b>" + editeur;

        if (r.getOtherViolation() != null) {
            html2 += "<br><br><b>Résumé: </b>" + resume;
        }
        if (r.isProcessed()) {
            html2 += "<br><br><b>Possibilité d'Achat: </b>"+possachat;
        }


        messageView.setText(Html.fromHtml(html2));
        messageView.setTextIsSelectable(true);
        messageView.setTextColor(getResources().getColor(R.color.textPrimary));
        messageView.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimension(R.dimen.subheadingText));
        messageView.setPadding(16, 0, 16, 0);
        float dpi = getContext().getResources().getDisplayMetrics().density;

        alertDialog.setView(messageView, (int) (19 * dpi), (int) (19 * dpi), (int) (14 * dpi), (int) (5 * dpi));
        alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        alertDialog.show();
    }

}
