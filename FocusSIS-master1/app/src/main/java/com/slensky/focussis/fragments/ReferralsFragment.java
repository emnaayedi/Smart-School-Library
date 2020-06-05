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
import com.slensky.focussis.util.TableRowAnimationController;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import android.util.Log;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;


/**
 * Created by slensky on 5/23/17.
 */

public class ReferralsFragment extends NetworkTabAwareFragment {

    private static final String TAG = "";
    String html2,html_date,html_nom_doc,html_nom_dom, possachat, resume, nbpage, collection, editeur, datePar, nomDoc, nomDomaine, dateentree, key, ref_nom_doc, ref_nom_domaine, ref_date_parution, ref_qte, ref_nb_page, ref_collection, ref_date_entree, ref_editeur, ref_resume, ref_possAchat;
    boolean b;
    TextView reporter, violation, entryDate;
    private ArrayList<String> mKeys = new ArrayList<>();
TextView messageView;

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

    DatabaseReference database = FirebaseDatabase.getInstance().getReference().child("stockage");


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
                database.addValueEventListener(new ValueEventListener() {
                    private static final String TAG = "";

                    @Override
                    public void onDataChange(@NonNull DataSnapshot data) {

                        for (DataSnapshot postSnapshot : data.getChildren()) {
                            if (mKeys.contains(postSnapshot.getKey() )) {

                                final TableRow referralRow = (TableRow) inflater.inflate(R.layout.view_referral, table, false);
                                ref_nom_doc = postSnapshot.child("nomDoc").getValue(String.class);
                                if( ref_nom_doc!=null) {

                                    ref_nom_domaine = postSnapshot.child("nomDomain").getValue(String.class);
                                    ref_date_parution = postSnapshot.child("dateParution").getValue(String.class);
                                    ref_qte = postSnapshot.child("Qte").getValue(String.class);
                                    ref_collection = postSnapshot.child("Collection").getValue(String.class);
                                    ref_nb_page = postSnapshot.child("nbrePage").getValue(String.class);
                                    ref_date_entree = postSnapshot.child("dateEntree").getValue(String.class);
                                    ref_editeur = postSnapshot.child("Editeur").getValue(String.class);
                                    ref_resume = postSnapshot.child("Resume").getValue(String.class);
                                    ref_possAchat = postSnapshot.child("PossAchat").getValue(String.class);
                                    b = (ref_qte.compareTo("2") >= 1);

                                    html_date = "<b>" + ref_date_parution;
                                    entryDate.setText(Html.fromHtml(html_date));
                                    html_nom_doc = "<b>" + ref_nom_doc;
                                    reporter.setText(Html.fromHtml(html_nom_doc));
                                    html_nom_dom = "<b>" + ref_nom_domaine;
                                    violation.setText(Html.fromHtml(html_nom_dom));
                                    html2 = "<b>Titre: </b>" + ref_nom_doc + "<br><br>" +
                                            "<b>Domaine: </b>" + ref_nom_domaine + "<br><br>" +
                                            "<b>Collection: </b>" + ref_collection + "<br><br>" +
                                            "<b>Nombre Page: </b>" + ref_nb_page + "<br><br>" +
                                            "<b>Date Parution: </b>" + ref_date_parution + "<br><br>" +
                                            "<b>Date Importation: </b>" + ref_date_entree + "<br><br>" +
                                            "<b>Editeur:</b>" + ref_editeur + "<br><br><b>Résumé: </b>" + ref_resume + "<br><br><b>Possibilité d'Achat: </b>" + ref_possAchat;

                                    AlertDialog alertDialog = new AlertDialog.Builder(getActivity()).create();
                                    //alertDialog.setTitle();
                                    TextView messageView = new TextView(getContext());


                                    messageView.setText(Html.fromHtml(html2));
                                    messageView.setTextIsSelectable(true);
                                    messageView.setTextColor(getResources().getColor(R.color.textPrimary));
                                    messageView.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimension(R.dimen.subheadingText));
                                    messageView.setPadding(16, 0, 16, 0);
                                    float dpi = getContext().getResources().getDisplayMetrics().density;

                                    alertDialog.setView(messageView, (int) (19 * dpi), (int) (19 * dpi), (int) (14 * dpi), (int) (5 * dpi));
                                    alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "OK",
                                            (dialog, which) -> dialog.dismiss());
                                    referralRow.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {


                                            alertDialog.show();
                                        }
                                    });
                                    final Animation animation = animationController.nextAnimation();

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

                                    //divider.setAnimation(animation);


                                }
                            } else {
                                mKeys.add(postSnapshot.getKey());
                                final TableRow referralRow = (TableRow) inflater.inflate(R.layout.view_referral, table, false);
                                violation = (TextView) referralRow.findViewById(R.id.text_violation);
                                reporter = (TextView) referralRow.findViewById(R.id.text_reporter_name);
                                entryDate = (TextView) referralRow.findViewById(R.id.text_entry_date);
                                ref_nom_doc = postSnapshot.child("nomDoc").getValue(String.class);
                                ref_nom_domaine = postSnapshot.child("nomDomain").getValue(String.class);
                                ref_date_parution = postSnapshot.child("dateParution").getValue(String.class);
                                ref_qte = postSnapshot.child("Qte").getValue(String.class);
                                ref_collection = postSnapshot.child("Collection").getValue(String.class);
                                ref_nb_page = postSnapshot.child("nbrePage").getValue(String.class);
                                ref_date_entree = postSnapshot.child("dateEntree").getValue(String.class);
                                ref_editeur = postSnapshot.child("Editeur").getValue(String.class);
                                ref_resume = postSnapshot.child("Resume").getValue(String.class);
                                ref_possAchat = postSnapshot.child("PossAchat").getValue(String.class);
                                b = (ref_qte.compareTo("2") >= 1);

                                html_date = "<b>" + ref_date_parution;
                                entryDate.setText(Html.fromHtml(html_date));
                                html_nom_doc = "<b>" + ref_nom_doc;
                                reporter.setText(Html.fromHtml(html_nom_doc));
                                html_nom_dom = "<b>" + ref_nom_domaine;
                                violation.setText(Html.fromHtml(html_nom_dom));
                                html2 = "<b>Titre: </b>" + ref_nom_doc + "<br><br>" +
                                        "<b>Domaine: </b>" + ref_nom_domaine + "<br><br>" +
                                        "<b>Collection: </b>" + ref_collection + "<br><br>" +
                                        "<b>Nombre Page: </b>" + ref_nb_page + "<br><br>" +
                                        "<b>Date Parution: </b>" + ref_date_parution + "<br><br>" +
                                        "<b>Date Importation: </b>" + ref_date_entree + "<br><br>" +
                                        "<b>Editeur:</b>" + ref_editeur + "<br><br><b>Résumé: </b>" + ref_resume + "<br><br><b>Possibilité d'Achat: </b>" + ref_possAchat;

                                AlertDialog alertDialog = new AlertDialog.Builder(getActivity()).create();
                                //alertDialog.setTitle();
                                TextView messageView = new TextView(getContext());


                                messageView.setText(Html.fromHtml(html2));
                                ref_nom_doc=null;
                                messageView.setTextIsSelectable(true);
                                messageView.setTextColor(getResources().getColor(R.color.textPrimary));
                                messageView.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimension(R.dimen.subheadingText));
                                messageView.setPadding(16, 0, 16, 0);
                                float dpi = getContext().getResources().getDisplayMetrics().density;

                                alertDialog.setView(messageView, (int) (19 * dpi), (int) (19 * dpi), (int) (14 * dpi), (int) (5 * dpi));
                                alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "OK",
                                        (dialog, which) -> dialog.dismiss());
                                referralRow.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {


                                        alertDialog.show();

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


                            requestFinished = true;
                        }
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });


            }
        }}






    @Override
    protected void makeRequest () {
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



    }}