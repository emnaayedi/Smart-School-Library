package com.slensky.focussis.fragments;

import android.content.DialogInterface;
import android.graphics.Rect;
import android.os.Bundle;
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

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.slensky.focussis.R;
import com.slensky.focussis.data.Referral;
import com.slensky.focussis.data.Referrals;
import com.slensky.focussis.network.FocusApi;
import com.slensky.focussis.network.FocusApiSingleton;
import com.slensky.focussis.util.DateUtil;
import com.slensky.focussis.util.TableRowAnimationController;

import java.util.Collections;
import java.util.List;

/**
 * Created by slensky on 5/23/17.
 */

public class ReferralsFragment extends NetworkTabAwareFragment {

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
                entryDate.setText(r.getEntryDate().monthOfYear().get() + "/" + r.getEntryDate().dayOfMonth().getAsText() + '/' + r.getEntryDate().year().getAsShortText().substring(2));
                reporter.setText(r.getTeacher());
                if (r.getViolation() != null) {
                    violation.setText(r.getViolation());
                } else {
                    violation.setText(r.getOtherViolation());
                }

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
        String html = "<b>Titre: </b>" + r.getViolation() + "<br><br>" +
                "<b>Domaine: </b>" + r.getName() + "<br><br>" +
                "<b>Collection: </b>" + r.getSchool() + "<br><br>" +
                "<b>Nombre Page: </b>" + r.getGrade() + "<br><br>" +
                "<b>Date Parution: </b>" + DateUtil.dateTimeToLongString(r.getEntryDate()) + "<br><br>" +
                "<b>Date Importation: </b>" + DateUtil.dateTimeToLongString(r.getLastUpdated()) + "<br><br>" +
                "<b>Editeur:</b>" + r.getTeacher();

        if (r.getOtherViolation() != null) {
            html += "<br><br><b>Résumé: </b>" + r.getOtherViolation();
        }
        if (r.isProcessed()) {
            html += "<br><br><b>Possibilité d'Achat: </b>Yes";
        } else {
            html += "<br><br><b>Possibilité d'Achat: </b>No";
        }

        float dpi = getContext().getResources().getDisplayMetrics().density;
        TextView messageView = new TextView(getContext());
        messageView.setText(Html.fromHtml(html));
        messageView.setTextIsSelectable(true);
        messageView.setTextColor(getResources().getColor(R.color.textPrimary));
        messageView.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimension(R.dimen.subheadingText));
        messageView.setPadding(16, 0, 16, 0);

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
