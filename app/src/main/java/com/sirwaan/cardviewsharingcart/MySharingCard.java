package com.sirwaan.cardviewsharingcart;

import android.animation.Animator;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatImageView;

import de.hdodenhof.circleimageview.CircleImageView;

public class MySharingCard extends RelativeLayout implements View.OnClickListener {
    private static final long REVEAL_DURATION = 700;  //ms


    View rootView;
    private AppCompatImageView socalIcon, coverImage;
    private CircleImageView profileImageView;
    private RelativeLayout layoutReveal;
    private LinearLayout layoutButtons;
    private AppCompatButton socialbtn1, socialbtn2, socialbtn3;

    public MySharingCard(Context context) {
        super(context);
        init(context);
    }

    public MySharingCard(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);

    }

    private void init(Context context) {

        rootView = inflate(context, R.layout.layout_sharing_card, this);
        //imageViews
        profileImageView = rootView.findViewById(R.id.cicle_image_profile);
        socalIcon = rootView.findViewById(R.id.imageView_socal_icon);
        coverImage = rootView.findViewById(R.id.imageView_coverImage);
        //btn s
        socialbtn1 = rootView.findViewById(R.id.btn_1);
        socialbtn2 = rootView.findViewById(R.id.btn_2);
        socialbtn3 = rootView.findViewById(R.id.btn_3);

        //relative and linearLayout
        layoutButtons = rootView.findViewById(R.id.linear_layout_btns);
        layoutReveal = rootView.findViewById(R.id.relative_layout_reveal);

        socalIcon.setOnClickListener(this);

    }

    public AppCompatButton getSocialbtn1() {
        return socialbtn1;
    }

    public AppCompatButton getSocialbtn2() {
        return socialbtn2;
    }

    public AppCompatButton getSocialbtn3() {
        return socialbtn3;
    }

    public AppCompatImageView getCoverImage() {
        return coverImage;
    }

    public CircleImageView getProfileImageView() {
        return profileImageView;
    }

    @Override
    public void onClick(View v) {

        //چطوری مختصات اون گوشه از X و Y رو باید بدست بیاریم ؟ ؟
        // جواب ازش میخواهیم از مختصات عکسمون این مشخصات رو بگیریم
        // چون ما میخواهیم از گوشه سمت راست
        // و پاییین عکس مون این انیمیشن شروع بشه
        // و تا بالا سمت چپ ادامه پیدا کنه
        ////
        //برای شعاع هم همینطور redius
        // قطرشو به دست میاریم یعنی رادیکال طول به توان دو ضرب در عرض به توان دو

        int centerX = coverImage.getRight()+socalIcon.getLeft()/2;
        int centerY = coverImage.getBottom()+socalIcon.getTop()/2;

        float redius = (float) Math.hypot(centerX - coverImage.getLeft(),coverImage.getHeight());
        if (layoutReveal.getVisibility() == VISIBLE) {
            hide(centerX, centerY, redius);
        } else {
            show(centerX, centerY, redius);
        }
    }

    private void show(int centerX, int centerY, float redius) {

        socalIcon.setImageResource(R.drawable.ic_close_black_24dp);
        socalIcon.setBackgroundResource(R.drawable.social_icon_cancle_backgound);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Animator animator = ViewAnimationUtils.createCircularReveal(layoutReveal, centerX, centerY, 0, redius);

            animator.setDuration(REVEAL_DURATION);
            layoutReveal.setVisibility(VISIBLE);
            animator.addListener(new Animator.AnimatorListener() {
                @Override
                public void onAnimationStart(Animator animation) {


                    layoutReveal.setVisibility(VISIBLE);

                }

                @Override
                public void onAnimationEnd(Animator animation) {
                    Animation faildin = AnimationUtils.loadAnimation(rootView.getContext(), R.anim.faildin);

                    layoutButtons.startAnimation(faildin);

                    layoutButtons.setVisibility(VISIBLE);

                    socalIcon.setImageResource(R.drawable.ic_close_black_24dp);
                    socalIcon.setBackgroundResource(R.drawable.social_icon_cancle_backgound);
                }

                @Override
                public void onAnimationCancel(Animator animation) {

                }

                @Override
                public void onAnimationRepeat(Animator animation) {

                }
            });
            animator.start();

        } else {
        //برای ورژن های پایین
        Animation animation = AnimationUtils.loadAnimation(getContext(), R.anim.faildin);
        animation.setDuration(REVEAL_DURATION * 3 / 4);
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                layoutReveal.setVisibility(VISIBLE);
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                Animation faildin = AnimationUtils.loadAnimation(rootView.getContext(), R.anim.faildin);
                faildin.setDuration(REVEAL_DURATION * 3 / 8);
                layoutButtons.startAnimation(faildin);

                layoutButtons.setVisibility(VISIBLE);



            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        layoutReveal.startAnimation(animation);
          }

    }

    private void hide(int centerX, int centerY, float redius) {
        socalIcon.setImageResource(R.drawable.ic_email_black_24dp);
        socalIcon.setBackgroundResource(R.drawable.social_icon_normal_backgound);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Animator animator = ViewAnimationUtils.createCircularReveal(layoutReveal, centerX, centerY, redius, 0);

            animator.setDuration(REVEAL_DURATION);

            animator.addListener(new Animator.AnimatorListener() {
                @Override
                public void onAnimationStart(Animator animation) {


                }

                @Override
                public void onAnimationEnd(Animator animation) {
                    layoutReveal.setVisibility(GONE);
                    layoutButtons.setVisibility(GONE);



                }

                @Override
                public void onAnimationCancel(Animator animation) {

                }

                @Override
                public void onAnimationRepeat(Animator animation) {

                }
            });
            animator.start();

        } else {

            Animation animation = AnimationUtils.loadAnimation(getContext(), R.anim.faildout);
            animation.setDuration(REVEAL_DURATION / 2);
            animation.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {

                }

                @Override
                public void onAnimationEnd(Animation animation) {
                    layoutReveal.setVisibility(GONE);
                    layoutButtons.setVisibility(GONE);
                }

                @Override
                public void onAnimationRepeat(Animation animation) {

                }
            });
            layoutReveal.startAnimation(animation);
        }
    }
}
