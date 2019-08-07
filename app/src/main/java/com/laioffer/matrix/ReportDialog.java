package com.laioffer.matrix;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.Window;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class ReportDialog extends Dialog {
    private int cx;
    private int cy;
    private RecyclerView mRecyclerView;
    private ReportRecyclerViewAdapter mRecyclerViewAdapter;


    public ReportDialog(@NonNull Context context) {
        this(context, R.style.MyAlertDialogStyle);
    }

    public ReportDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
    }

    public static ReportDialog newInstance(Context context, int cx, int cy) {
        ReportDialog dialog = new ReportDialog(context, R.style.MyAlertDialogStyle);  /** */
        dialog.cx = cx;
        dialog.cy = cy;
        return dialog;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final View dialogView = View.inflate(getContext(), R.layout.dialog, null);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(dialogView);
        getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        //set up animation
        setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface dialogInterface) {
                animateDialog(dialogView, true);      /** Open it with animation */
            }
        });

        setOnKeyListener(new DialogInterface.OnKeyListener() {
            @Override
            public boolean onKey(DialogInterface dialogInterface, int i, KeyEvent keyEvent) {
                if (i == KeyEvent.KEYCODE_BACK) {
                    animateDialog(dialogView, false);
                    return true;              /** Close it with animation */
                }
                return false;
            }
        });
        setupRecyclerView(dialogView);
    }
    private void animateDialog(View dialogView, boolean open) {
        final View view = dialogView.findViewById(R.id.dialog);
        int w = view.getWidth();
        int h = view.getHeight();

        int endRadius = (int) Math.hypot(w, h);

        if (open) {
            Animator revealAnimator = ViewAnimationUtils.createCircularReveal(view, cx, cy, 0, endRadius);
            view.setVisibility(View.VISIBLE);
            revealAnimator.setDuration(500);
            revealAnimator.start();
        } else {
            Animator anim = ViewAnimationUtils.createCircularReveal(view, cx, cy, endRadius, 0);

            anim.addListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    super.onAnimationEnd(animation);
                    view.setVisibility(View.INVISIBLE);
                    dismiss();

                }
            });
            anim.setDuration(500);
            anim.start();
        }
    }
    private void setupRecyclerView(View dialogView) {
        mRecyclerView = dialogView.findViewById(R.id.recycler_view);
        mRecyclerView.setLayoutManager(new GridLayoutManager(getContext(), 3));
        mRecyclerViewAdapter = new ReportRecyclerViewAdapter(getContext(), Config.listItems);
        mRecyclerViewAdapter.setClickListener(new ReportRecyclerViewAdapter
                .OnClickListener() {
            @Override
            public void setItem(String item) {
                // for switch item
            }
        });

        mRecyclerView.setAdapter(mRecyclerViewAdapter);
    }

}
