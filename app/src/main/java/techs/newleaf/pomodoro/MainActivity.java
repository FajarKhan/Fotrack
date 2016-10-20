package techs.newleaf.pomodoro;

import android.animation.Animator;

import android.animation.AnimatorSet;
import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;

import android.app.ActivityManager;
import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Context;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.os.CountDownTimer;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.NotificationCompat;
import android.util.Base64;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.widget.CheckBox;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.concurrent.TimeUnit;

import static android.graphics.Color.parseColor;
import static android.os.Build.VERSION_CODES.N;
import static techs.newleaf.pomodoro.R.id.MainLayout;
import static techs.newleaf.pomodoro.R.id.noti;


@TargetApi(Build.VERSION_CODES.GINGERBREAD)
@SuppressLint("NewApi")

public class MainActivity extends FragmentActivity {

    private ImageButton play;
    private TextView CountText;
    SectionsPagerAdapter mSectionsPagerAdapter;
    public static ViewPager mViewPager;
    Integer[] colors = null;
    ArgbEvaluator argbEvaluator = new ArgbEvaluator();
    CounterClass timer;
    PodobreakClass2 podotimer2;
    PodobreakClass3 podotimer3;
     ShortbreakClass shortBreaks;
     ShortbreakClass2 shortBreaks2;
    ShortbreakClass3  shortBreaks3;
    LongbreakClass longBreaks;
    finishLongbreakClass finishlongBreaks;
    long millis;
    private ImageButton settings;
    Animation fadeInAnimation;
    private TextView textView;
    int count=0;
    int countL=0;
    private LinearLayout linearLayout;
    private TextView loops;
    private View myView,myView2,myView3,myView4,myView5,myView6,myView7;
    private LinearLayout re2,re3,re4,re5,re6,re7;
    private LinearLayout re1;
    private  AlphaAnimation blinkanimation1,blinkanimation2,blinkanimation3,blinkanimation4,blinkanimation5,blinkanimation6,blinkanimation7;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fadeInAnimation = AnimationUtils.loadAnimation(this, R.anim.fadein);

        timer = new CounterClass(25*60 * 1000, 1000);
        podotimer2 = new PodobreakClass2(25*60*1000,1000);
        podotimer3 = new PodobreakClass3(25*60*1000,1000);
        shortBreaks= new ShortbreakClass(5*60*1000,1000);
        shortBreaks2= new ShortbreakClass2(5*60*1000,1000);
        shortBreaks3= new ShortbreakClass3(5*60*1000,1000);
        longBreaks= new LongbreakClass(25*60*1000,1000);
        finishlongBreaks= new finishLongbreakClass(15*60*1000,1000);


        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());
        mViewPager = (ViewPager) findViewById(R.id.pager);
        mViewPager.setAdapter(mSectionsPagerAdapter);
        mViewPager.setOnPageChangeListener(new CustomOnPageChangeListener());
        setUpColors();


        re1=(LinearLayout) findViewById(R.id.rect1);
        re2=(LinearLayout)findViewById(R.id.rect2);
        re3=(LinearLayout)findViewById(R.id.rect3);
        re4=(LinearLayout)findViewById(R.id.rect4);
        re5=(LinearLayout)findViewById(R.id.rect5);
        re6=(LinearLayout)findViewById(R.id.rect6);
        re7=(LinearLayout)findViewById(R.id.rect7);

        myView=findViewById(R.id.inrect1);
        myView2= findViewById(R.id.inrect2);
        myView3= findViewById(R.id.inrect3);
        myView4= findViewById(R.id.inrect4);
        myView5= findViewById(R.id.inrect5);
        myView6= findViewById(R.id.inrect6);
        myView7= findViewById(R.id.inrect7);
        textView=(TextView) findViewById(R.id.text);
        CountText=(TextView)findViewById(R.id.countText);
        settings=(ImageButton)findViewById(R.id.settings);
        settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplication(),Settings.class);
                startActivity(intent);
            }
        });


        linearLayout=(LinearLayout)findViewById(R.id.linear);
        loops=(TextView)findViewById(R.id.loop);
        play=(ImageButton)findViewById(R.id.play);

        play.setOnClickListener( startListener );

    }

    @Override
    protected void onResume() {
        if(Settings.ScreenState==1){
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        }
        else if(Settings.ScreenState==0) {
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        }
        super.onResume();
    }

    private void setUpColors(){

        Integer color1 = Color.parseColor("#000000");
        Integer color2 = Color.parseColor("#4e342e");
        Integer color3 = Color.parseColor("#00695c");
        Integer color4 = Color.parseColor("#d84316");
        Integer color5 = Color.parseColor("#00838f");
        Integer color6 = Color.parseColor("#ff5722");
        Integer color7 = Color.parseColor("#01bcd4");
        Integer color8 = Color.parseColor("#ef6c00");
        Integer color9 = Color.parseColor("#0277bd");
        Integer color10 = Color.parseColor("#ff9802");
        Integer color11 = Color.parseColor("#2096f3");
        Integer color12 = Color.parseColor("#283593");
        Integer color13 = Color.parseColor("#ffc108");
        Integer color14 = Color.parseColor("#3f51b5");
        Integer color15 = Color.parseColor("#f9a825");
        Integer color16 = Color.parseColor("#4527a0");
        Integer color17 = Color.parseColor("#ffeb3b");
        Integer color18 = Color.parseColor("#673ab7");
        Integer color19 = Color.parseColor("#9e9d25");
        Integer color20 = Color.parseColor("#6a1b9a");
        Integer color21 = Color.parseColor("#cddc39");
        Integer color22 = Color.parseColor("#9c26b0");
        Integer color23 = Color.parseColor("#558b2f");
        Integer color24 = Color.parseColor("#ad1457");
        Integer color25 = Color.parseColor("#8bc34a");
        Integer color26 = Color.parseColor("#e91f63");
        Integer color27 = Color.parseColor("#2f7d32");
        Integer color28 = Color.parseColor("#f44336");

        Integer[] colors_temp = {color1, color2, color3,color4,color5,color6,color7,color8,color9,color10,color11,color12,color13,color14,color15,color16,color17,color18,color19,color20,color21,color22,color23,color24,color25,color26,color27,color28};
        colors = colors_temp;

    }
    @Override
    public void onBackPressed() {
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        startActivity(intent);
    }


    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return PlaceholderFragment.newInstance(position + 1);
        }

        @Override
        public int getCount() {
            return 27;
        }
    }

    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        public PlaceholderFragment() {
        }
    }

    public class CustomOnPageChangeListener implements ViewPager.OnPageChangeListener {

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            if(position < (mSectionsPagerAdapter.getCount() -1) && position < (colors.length - 1)) {

                mViewPager.setBackgroundColor((Integer) argbEvaluator.evaluate(positionOffset, colors[position], colors[position + 1]));

            } else {

                // the last page color
                mViewPager.setBackgroundColor(colors[colors.length - 1]);

            }

        }

        @Override
        public void onPageSelected(int position) {
        }

        @Override
        public void onPageScrollStateChanged(int i) {
        }

    }

    View.OnClickListener startListener = new View.OnClickListener() {

        public void onClick( View v ){
            myView.setBackgroundColor(Color.WHITE);
            ((GradientDrawable)re1.getBackground()).setStroke(1,Color.WHITE);
            blinkanimation1= new AlphaAnimation(1, 0); // Change alpha from fully visible to invisible
            blinkanimation1.setDuration(500); // duration - half a second
            blinkanimation1.setInterpolator(new LinearInterpolator()); // do not alter animation rate
            blinkanimation1.setRepeatCount(Settings.PomoProgress*60*2); // Repeat animation infinitely
            blinkanimation1.setRepeatMode(Animation.REVERSE);

            re1.startAnimation(blinkanimation1);


            play.setVisibility(View.INVISIBLE);
            CountText.setVisibility(View.VISIBLE);


           CountText.startAnimation(fadeInAnimation);
            if(Settings.PomoProgress==25) {
                timer.start();
            }
            else {
                timer.cancel();
                timer = new CounterClass(Settings.PomoProgress*60 * 1000, 1000);
                timer.start();
            }
            textView.setText("[Pomodoro]");
            CountText.setOnClickListener( stopListener );
        }
    };


    View.OnClickListener stopListener = new View.OnClickListener() {

        public void onClick( View v ){

           blinkanimation1.cancel();
            timer.cancel();

            play.setImageResource(R.mipmap.puse);
            int marginTop = (int) getResources().getDimensionPixelSize(R.dimen.left_border_distance);
            int marginRight = (int) getResources().getDimensionPixelSize(R.dimen.right_border_distance);
           FrameLayout.LayoutParams toastTextLp = ( FrameLayout.LayoutParams)play.getLayoutParams();
            toastTextLp.setMargins(marginRight, marginTop,0, 0);
            play.setLayoutParams(toastTextLp);

            play.setVisibility(View.VISIBLE);



            ObjectAnimator slideDownAnimPlayButton = ObjectAnimator.ofFloat(play, "translationY", -(play.getTop()), 0);
            ObjectAnimator slideDownAnimPlayButtonX = ObjectAnimator.ofFloat(play, "scaleX", 0.5f, 1f);
            ObjectAnimator slideDownAnimPlayButtonY = ObjectAnimator.ofFloat(play, "scaleY", 0.5f, 1f);
            AnimatorSet animatorSet1 = new AnimatorSet();
            animatorSet1.setDuration(300);
            animatorSet1.playTogether(slideDownAnimPlayButton,slideDownAnimPlayButtonX,slideDownAnimPlayButtonY);
            animatorSet1.start();

            ObjectAnimator slideDownAnimTextView = ObjectAnimator.ofFloat(CountText, "translationY",0, (CountText.getTop() ));
            ObjectAnimator scaleDownAnimTextViewX = ObjectAnimator.ofFloat(CountText, "scaleX", 1f, 0.5f);
            ObjectAnimator scaleDownAnimTextViewY = ObjectAnimator.ofFloat(CountText, "scaleY", 1f, 0.5f);
            AnimatorSet animatorSet = new AnimatorSet();
            animatorSet.setDuration(300);
            animatorSet.playTogether(slideDownAnimTextView,scaleDownAnimTextViewX,scaleDownAnimTextViewY);
            animatorSet.start();
            slideDownAnimTextView.addListener(new Animator.AnimatorListener() {
                @Override
                public void onAnimationStart(Animator animation) {

                }

                @Override
                public void onAnimationEnd(Animator animation) {
                    CountText.setTextColor(parseColor("#32FFFFFF"));
                }

                @Override
                public void onAnimationCancel(Animator animator) {

                }

                @Override
                public void onAnimationRepeat(Animator animator) {

                }
            });
            play.setOnClickListener( resumeListener );

        }
    };

    View.OnClickListener resumeListener=new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            int a=(int)(millis/1000)*2;
            blinkanimation1.setRepeatCount(a);
            re1.startAnimation(blinkanimation1);
            ObjectAnimator slideDownAnimPlayButton = ObjectAnimator.ofFloat(play, "translationY", 0,-(play.getTop()));
            ObjectAnimator slideDownAnimPlayButtonX = ObjectAnimator.ofFloat(play, "scaleX", 1f, 0.5f);
            ObjectAnimator slideDownAnimPlayButtonY = ObjectAnimator.ofFloat(play, "scaleY", 1f, 0.5f);
            AnimatorSet animatorSet2 = new AnimatorSet();
            animatorSet2.setDuration(300);
            animatorSet2.playTogether(slideDownAnimPlayButton,slideDownAnimPlayButtonX,slideDownAnimPlayButtonY);
            animatorSet2.start();

            ObjectAnimator slideDownAnimTextView = ObjectAnimator.ofFloat(CountText, "translationY", (CountText.getTop()),0);
            ObjectAnimator scaleDownAnimTextViewX = ObjectAnimator.ofFloat(CountText, "scaleX", 0.5f, 1f);
            ObjectAnimator scaleDownAnimTextViewY = ObjectAnimator.ofFloat(CountText, "scaleY", 0.5f, 1f);
            AnimatorSet animatorSet = new AnimatorSet();
            animatorSet.setDuration(300);
            animatorSet.playTogether(slideDownAnimTextView,scaleDownAnimTextViewX,scaleDownAnimTextViewY);
            animatorSet.start();
            CountText.setTextColor(Color.WHITE);

            slideDownAnimPlayButton.addListener(new Animator.AnimatorListener() {
                @Override
                public void onAnimationStart(Animator animation) {

                }

                @Override
                public void onAnimationEnd(Animator animation) {
                    play.setVisibility(View.INVISIBLE);
                }

                @Override
                public void onAnimationCancel(Animator animator) {

                }

                @Override
                public void onAnimationRepeat(Animator animator) {

                }
            });

            final CounterClass timer2= new CounterClass(millis,1000);
            timer2.start();

            CountText.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    timer2.cancel();
                    blinkanimation1.cancel();


                    play.setImageResource(R.mipmap.puse);
                    int marginTop = (int) getResources().getDimensionPixelSize(R.dimen.left_border_distance);
                    int marginRight = (int) getResources().getDimensionPixelSize(R.dimen.right_border_distance);
                    FrameLayout.LayoutParams toastTextLp = ( FrameLayout.LayoutParams)play.getLayoutParams();
                    toastTextLp.setMargins(marginRight, marginTop,0, 0);
                    play.setLayoutParams(toastTextLp);
                    play.setVisibility(View.VISIBLE);


                    ObjectAnimator slideDownAnimPlayButton = ObjectAnimator.ofFloat(play, "translationY", -(play.getTop()), 0);
                    ObjectAnimator slideDownAnimPlayButtonX = ObjectAnimator.ofFloat(play, "scaleX", 0.5f, 1f);
                    ObjectAnimator slideDownAnimPlayButtonY = ObjectAnimator.ofFloat(play, "scaleY", 0.5f, 1f);
                    AnimatorSet animatorSet1 = new AnimatorSet();
                    animatorSet1.setDuration(300);
                    animatorSet1.playTogether(slideDownAnimPlayButton,slideDownAnimPlayButtonX,slideDownAnimPlayButtonY);
                    animatorSet1.start();

                    ObjectAnimator slideDownAnimTextView = ObjectAnimator.ofFloat(CountText, "translationY",0, (CountText.getTop() ));
                    ObjectAnimator scaleDownAnimTextViewX = ObjectAnimator.ofFloat(CountText, "scaleX", 1f, 0.5f);
                    ObjectAnimator scaleDownAnimTextViewY = ObjectAnimator.ofFloat(CountText, "scaleY", 1f, 0.5f);
                    AnimatorSet animatorSet = new AnimatorSet();
                    animatorSet.setDuration(300);
                    animatorSet.playTogether(slideDownAnimTextView,scaleDownAnimTextViewX,scaleDownAnimTextViewY);
                    animatorSet.start();
                    slideDownAnimTextView.addListener(new Animator.AnimatorListener() {
                        @Override
                        public void onAnimationStart(Animator animation) {

                        }

                        @Override
                        public void onAnimationEnd(Animator animation) {
                            CountText.setTextColor(parseColor("#32FFFFFF"));
                        }

                        @Override
                        public void onAnimationCancel(Animator animator) {

                        }

                        @Override
                        public void onAnimationRepeat(Animator animator) {

                        }
                    });
                    play.setOnClickListener( resumeListener );
                }
            });

        }
    };

    View.OnClickListener resumeListener2=new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            int a=(int)(millis/1000)*2;
            blinkanimation2.setRepeatCount(a);
            re2.startAnimation(blinkanimation2);
            ObjectAnimator slideDownAnimPlayButton = ObjectAnimator.ofFloat(play, "translationY", 0,-(play.getTop()));
            ObjectAnimator slideDownAnimPlayButtonX = ObjectAnimator.ofFloat(play, "scaleX", 1f, 0.5f);
            ObjectAnimator slideDownAnimPlayButtonY = ObjectAnimator.ofFloat(play, "scaleY", 1f, 0.5f);
            AnimatorSet animatorSet2 = new AnimatorSet();
            animatorSet2.setDuration(300);
            animatorSet2.playTogether(slideDownAnimPlayButton,slideDownAnimPlayButtonX,slideDownAnimPlayButtonY);
            animatorSet2.start();

            ObjectAnimator slideDownAnimTextView = ObjectAnimator.ofFloat(CountText, "translationY", (CountText.getTop()),0);
            ObjectAnimator scaleDownAnimTextViewX = ObjectAnimator.ofFloat(CountText, "scaleX", 0.5f, 1f);
            ObjectAnimator scaleDownAnimTextViewY = ObjectAnimator.ofFloat(CountText, "scaleY", 0.5f, 1f);
            AnimatorSet animatorSet = new AnimatorSet();
            animatorSet.setDuration(300);
            animatorSet.playTogether(slideDownAnimTextView,scaleDownAnimTextViewX,scaleDownAnimTextViewY);
            animatorSet.start();
            CountText.setTextColor(Color.WHITE);

            slideDownAnimPlayButton.addListener(new Animator.AnimatorListener() {
                @Override
                public void onAnimationStart(Animator animation) {

                }

                @Override
                public void onAnimationEnd(Animator animation) {
                    play.setVisibility(View.INVISIBLE);
                }

                @Override
                public void onAnimationCancel(Animator animator) {

                }

                @Override
                public void onAnimationRepeat(Animator animator) {

                }
            });
           final ShortbreakClass shortBreaks= new ShortbreakClass(millis,1000);
            shortBreaks.start();


            CountText.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    shortBreaks.cancel();
                    blinkanimation2.cancel();

                    play.setImageResource(R.mipmap.puse);
                    int marginTop = (int) getResources().getDimensionPixelSize(R.dimen.left_border_distance);
                    int marginRight = (int) getResources().getDimensionPixelSize(R.dimen.right_border_distance);
                    FrameLayout.LayoutParams toastTextLp = ( FrameLayout.LayoutParams)play.getLayoutParams();
                    toastTextLp.setMargins(marginRight, marginTop,0, 0);
                    play.setLayoutParams(toastTextLp);
                    play.setVisibility(View.VISIBLE);


                    ObjectAnimator slideDownAnimPlayButton = ObjectAnimator.ofFloat(play, "translationY", -(play.getTop()), 0);
                    ObjectAnimator slideDownAnimPlayButtonX = ObjectAnimator.ofFloat(play, "scaleX", 0.5f, 1f);
                    ObjectAnimator slideDownAnimPlayButtonY = ObjectAnimator.ofFloat(play, "scaleY", 0.5f, 1f);
                    AnimatorSet animatorSet1 = new AnimatorSet();
                    animatorSet1.setDuration(300);
                    animatorSet1.playTogether(slideDownAnimPlayButton,slideDownAnimPlayButtonX,slideDownAnimPlayButtonY);
                    animatorSet1.start();

                    ObjectAnimator slideDownAnimTextView = ObjectAnimator.ofFloat(CountText, "translationY",0, (CountText.getTop() ));
                    ObjectAnimator scaleDownAnimTextViewX = ObjectAnimator.ofFloat(CountText, "scaleX", 1f, 0.5f);
                    ObjectAnimator scaleDownAnimTextViewY = ObjectAnimator.ofFloat(CountText, "scaleY", 1f, 0.5f);
                    AnimatorSet animatorSet = new AnimatorSet();
                    animatorSet.setDuration(300);
                    animatorSet.playTogether(slideDownAnimTextView,scaleDownAnimTextViewX,scaleDownAnimTextViewY);
                    animatorSet.start();
                    slideDownAnimTextView.addListener(new Animator.AnimatorListener() {
                        @Override
                        public void onAnimationStart(Animator animation) {

                        }

                        @Override
                        public void onAnimationEnd(Animator animation) {
                            CountText.setTextColor(parseColor("#32FFFFFF"));
                        }

                        @Override
                        public void onAnimationCancel(Animator animator) {

                        }

                        @Override
                        public void onAnimationRepeat(Animator animator) {

                        }
                    });
                    play.setOnClickListener( resumeListener2 );
                }
            });

        }
    };

    View.OnClickListener resumeListener3=new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            int a=(int)(millis/1000)*2;
            blinkanimation3.setRepeatCount(a);
            re3.startAnimation(blinkanimation3);
            ObjectAnimator slideDownAnimPlayButton = ObjectAnimator.ofFloat(play, "translationY", 0,-(play.getTop()));
            ObjectAnimator slideDownAnimPlayButtonX = ObjectAnimator.ofFloat(play, "scaleX", 1f, 0.5f);
            ObjectAnimator slideDownAnimPlayButtonY = ObjectAnimator.ofFloat(play, "scaleY", 1f, 0.5f);
            AnimatorSet animatorSet2 = new AnimatorSet();
            animatorSet2.setDuration(300);
            animatorSet2.playTogether(slideDownAnimPlayButton,slideDownAnimPlayButtonX,slideDownAnimPlayButtonY);
            animatorSet2.start();

            ObjectAnimator slideDownAnimTextView = ObjectAnimator.ofFloat(CountText, "translationY", (CountText.getTop()),0);
            ObjectAnimator scaleDownAnimTextViewX = ObjectAnimator.ofFloat(CountText, "scaleX", 0.5f, 1f);
            ObjectAnimator scaleDownAnimTextViewY = ObjectAnimator.ofFloat(CountText, "scaleY", 0.5f, 1f);
            AnimatorSet animatorSet = new AnimatorSet();
            animatorSet.setDuration(300);
            animatorSet.playTogether(slideDownAnimTextView,scaleDownAnimTextViewX,scaleDownAnimTextViewY);
            animatorSet.start();
            CountText.setTextColor(Color.WHITE);

            slideDownAnimPlayButton.addListener(new Animator.AnimatorListener() {
                @Override
                public void onAnimationStart(Animator animation) {

                }

                @Override
                public void onAnimationEnd(Animator animation) {
                    play.setVisibility(View.INVISIBLE);
                }

                @Override
                public void onAnimationCancel(Animator animator) {

                }

                @Override
                public void onAnimationRepeat(Animator animator) {

                }
            });
            final PodobreakClass2 shortBreaks= new PodobreakClass2(millis,1000);
            shortBreaks.start();

            CountText.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    shortBreaks.cancel();
                    blinkanimation3.cancel();


                    play.setImageResource(R.mipmap.puse);
                    int marginTop = (int) getResources().getDimensionPixelSize(R.dimen.left_border_distance);
                    int marginRight = (int) getResources().getDimensionPixelSize(R.dimen.right_border_distance);
                    FrameLayout.LayoutParams toastTextLp = ( FrameLayout.LayoutParams)play.getLayoutParams();
                    toastTextLp.setMargins(marginRight, marginTop,0, 0);
                    play.setLayoutParams(toastTextLp);
                    play.setVisibility(View.VISIBLE);


                    ObjectAnimator slideDownAnimPlayButton = ObjectAnimator.ofFloat(play, "translationY", -(play.getTop()), 0);
                    ObjectAnimator slideDownAnimPlayButtonX = ObjectAnimator.ofFloat(play, "scaleX", 0.5f, 1f);
                    ObjectAnimator slideDownAnimPlayButtonY = ObjectAnimator.ofFloat(play, "scaleY", 0.5f, 1f);
                    AnimatorSet animatorSet1 = new AnimatorSet();
                    animatorSet1.setDuration(300);
                    animatorSet1.playTogether(slideDownAnimPlayButton,slideDownAnimPlayButtonX,slideDownAnimPlayButtonY);
                    animatorSet1.start();

                    ObjectAnimator slideDownAnimTextView = ObjectAnimator.ofFloat(CountText, "translationY",0, (CountText.getTop() ));
                    ObjectAnimator scaleDownAnimTextViewX = ObjectAnimator.ofFloat(CountText, "scaleX", 1f, 0.5f);
                    ObjectAnimator scaleDownAnimTextViewY = ObjectAnimator.ofFloat(CountText, "scaleY", 1f, 0.5f);
                    AnimatorSet animatorSet = new AnimatorSet();
                    animatorSet.setDuration(300);
                    animatorSet.playTogether(slideDownAnimTextView,scaleDownAnimTextViewX,scaleDownAnimTextViewY);
                    animatorSet.start();
                    slideDownAnimTextView.addListener(new Animator.AnimatorListener() {
                        @Override
                        public void onAnimationStart(Animator animation) {

                        }

                        @Override
                        public void onAnimationEnd(Animator animation) {
                            CountText.setTextColor(parseColor("#32FFFFFF"));
                        }

                        @Override
                        public void onAnimationCancel(Animator animator) {

                        }

                        @Override
                        public void onAnimationRepeat(Animator animator) {

                        }
                    });
                    play.setOnClickListener( resumeListener3 );
                }
            });

        }
    };
    View.OnClickListener resumeListener4=new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            int a=(int)(millis/1000)*2;
            blinkanimation4.setRepeatCount(a);
            re4.startAnimation(blinkanimation4);
            ObjectAnimator slideDownAnimPlayButton = ObjectAnimator.ofFloat(play, "translationY", 0,-(play.getTop()));
            ObjectAnimator slideDownAnimPlayButtonX = ObjectAnimator.ofFloat(play, "scaleX", 1f, 0.5f);
            ObjectAnimator slideDownAnimPlayButtonY = ObjectAnimator.ofFloat(play, "scaleY", 1f, 0.5f);
            AnimatorSet animatorSet2 = new AnimatorSet();
            animatorSet2.setDuration(300);
            animatorSet2.playTogether(slideDownAnimPlayButton,slideDownAnimPlayButtonX,slideDownAnimPlayButtonY);
            animatorSet2.start();

            ObjectAnimator slideDownAnimTextView = ObjectAnimator.ofFloat(CountText, "translationY", (CountText.getTop()),0);
            ObjectAnimator scaleDownAnimTextViewX = ObjectAnimator.ofFloat(CountText, "scaleX", 0.5f, 1f);
            ObjectAnimator scaleDownAnimTextViewY = ObjectAnimator.ofFloat(CountText, "scaleY", 0.5f, 1f);
            AnimatorSet animatorSet = new AnimatorSet();
            animatorSet.setDuration(300);
            animatorSet.playTogether(slideDownAnimTextView,scaleDownAnimTextViewX,scaleDownAnimTextViewY);
            animatorSet.start();
            CountText.setTextColor(Color.WHITE);

            slideDownAnimPlayButton.addListener(new Animator.AnimatorListener() {
                @Override
                public void onAnimationStart(Animator animation) {

                }

                @Override
                public void onAnimationEnd(Animator animation) {
                    play.setVisibility(View.INVISIBLE);
                }

                @Override
                public void onAnimationCancel(Animator animator) {

                }

                @Override
                public void onAnimationRepeat(Animator animator) {

                }
            });
            final ShortbreakClass2 shortBreaks= new ShortbreakClass2(millis,1000);
            shortBreaks.start();

            CountText.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    shortBreaks.cancel();

                    blinkanimation4.cancel();

                    play.setImageResource(R.mipmap.puse);
                    int marginTop = (int) getResources().getDimensionPixelSize(R.dimen.left_border_distance);
                    int marginRight = (int) getResources().getDimensionPixelSize(R.dimen.right_border_distance);
                    FrameLayout.LayoutParams toastTextLp = ( FrameLayout.LayoutParams)play.getLayoutParams();
                    toastTextLp.setMargins(marginRight, marginTop,0, 0);
                    play.setLayoutParams(toastTextLp);
                    play.setVisibility(View.VISIBLE);


                    ObjectAnimator slideDownAnimPlayButton = ObjectAnimator.ofFloat(play, "translationY", -(play.getTop()), 0);
                    ObjectAnimator slideDownAnimPlayButtonX = ObjectAnimator.ofFloat(play, "scaleX", 0.5f, 1f);
                    ObjectAnimator slideDownAnimPlayButtonY = ObjectAnimator.ofFloat(play, "scaleY", 0.5f, 1f);
                    AnimatorSet animatorSet1 = new AnimatorSet();
                    animatorSet1.setDuration(300);
                    animatorSet1.playTogether(slideDownAnimPlayButton,slideDownAnimPlayButtonX,slideDownAnimPlayButtonY);
                    animatorSet1.start();

                    ObjectAnimator slideDownAnimTextView = ObjectAnimator.ofFloat(CountText, "translationY",0, (CountText.getTop() ));
                    ObjectAnimator scaleDownAnimTextViewX = ObjectAnimator.ofFloat(CountText, "scaleX", 1f, 0.5f);
                    ObjectAnimator scaleDownAnimTextViewY = ObjectAnimator.ofFloat(CountText, "scaleY", 1f, 0.5f);
                    AnimatorSet animatorSet = new AnimatorSet();
                    animatorSet.setDuration(300);
                    animatorSet.playTogether(slideDownAnimTextView,scaleDownAnimTextViewX,scaleDownAnimTextViewY);
                    animatorSet.start();
                    slideDownAnimTextView.addListener(new Animator.AnimatorListener() {
                        @Override
                        public void onAnimationStart(Animator animation) {

                        }

                        @Override
                        public void onAnimationEnd(Animator animation) {
                            CountText.setTextColor(parseColor("#32FFFFFF"));
                        }

                        @Override
                        public void onAnimationCancel(Animator animator) {

                        }

                        @Override
                        public void onAnimationRepeat(Animator animator) {

                        }
                    });
                    play.setOnClickListener( resumeListener4 );
                }
            });

        }
    };
    View.OnClickListener resumeListener5=new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            int a=(int)(millis/1000)*2;
            blinkanimation5.setRepeatCount(a);
            re5.startAnimation(blinkanimation5);
            ObjectAnimator slideDownAnimPlayButton = ObjectAnimator.ofFloat(play, "translationY", 0,-(play.getTop()));
            ObjectAnimator slideDownAnimPlayButtonX = ObjectAnimator.ofFloat(play, "scaleX", 1f, 0.5f);
            ObjectAnimator slideDownAnimPlayButtonY = ObjectAnimator.ofFloat(play, "scaleY", 1f, 0.5f);
            AnimatorSet animatorSet2 = new AnimatorSet();
            animatorSet2.setDuration(300);
            animatorSet2.playTogether(slideDownAnimPlayButton,slideDownAnimPlayButtonX,slideDownAnimPlayButtonY);
            animatorSet2.start();

            ObjectAnimator slideDownAnimTextView = ObjectAnimator.ofFloat(CountText, "translationY", (CountText.getTop()),0);
            ObjectAnimator scaleDownAnimTextViewX = ObjectAnimator.ofFloat(CountText, "scaleX", 0.5f, 1f);
            ObjectAnimator scaleDownAnimTextViewY = ObjectAnimator.ofFloat(CountText, "scaleY", 0.5f, 1f);
            AnimatorSet animatorSet = new AnimatorSet();
            animatorSet.setDuration(300);
            animatorSet.playTogether(slideDownAnimTextView,scaleDownAnimTextViewX,scaleDownAnimTextViewY);
            animatorSet.start();
            CountText.setTextColor(Color.WHITE);

            slideDownAnimPlayButton.addListener(new Animator.AnimatorListener() {
                @Override
                public void onAnimationStart(Animator animation) {

                }

                @Override
                public void onAnimationEnd(Animator animation) {
                    play.setVisibility(View.INVISIBLE);
                }

                @Override
                public void onAnimationCancel(Animator animator) {

                }

                @Override
                public void onAnimationRepeat(Animator animator) {

                }
            });
            final PodobreakClass3 shortBreaks= new PodobreakClass3(millis,1000);
            shortBreaks.start();
           CountText.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    shortBreaks.cancel();
                    blinkanimation5.cancel();


                    play.setImageResource(R.mipmap.puse);
                    int marginTop = (int) getResources().getDimensionPixelSize(R.dimen.left_border_distance);
                    int marginRight = (int) getResources().getDimensionPixelSize(R.dimen.right_border_distance);
                    FrameLayout.LayoutParams toastTextLp = ( FrameLayout.LayoutParams)play.getLayoutParams();
                    toastTextLp.setMargins(marginRight, marginTop,0, 0);
                    play.setLayoutParams(toastTextLp);
                    play.setVisibility(View.VISIBLE);


                    ObjectAnimator slideDownAnimPlayButton = ObjectAnimator.ofFloat(play, "translationY", -(play.getTop()), 0);
                    ObjectAnimator slideDownAnimPlayButtonX = ObjectAnimator.ofFloat(play, "scaleX", 0.5f, 1f);
                    ObjectAnimator slideDownAnimPlayButtonY = ObjectAnimator.ofFloat(play, "scaleY", 0.5f, 1f);
                    AnimatorSet animatorSet1 = new AnimatorSet();
                    animatorSet1.setDuration(300);
                    animatorSet1.playTogether(slideDownAnimPlayButton,slideDownAnimPlayButtonX,slideDownAnimPlayButtonY);
                    animatorSet1.start();

                    ObjectAnimator slideDownAnimTextView = ObjectAnimator.ofFloat(CountText, "translationY",0, (CountText.getTop() ));
                    ObjectAnimator scaleDownAnimTextViewX = ObjectAnimator.ofFloat(CountText, "scaleX", 1f, 0.5f);
                    ObjectAnimator scaleDownAnimTextViewY = ObjectAnimator.ofFloat(CountText, "scaleY", 1f, 0.5f);
                    AnimatorSet animatorSet = new AnimatorSet();
                    animatorSet.setDuration(300);
                    animatorSet.playTogether(slideDownAnimTextView,scaleDownAnimTextViewX,scaleDownAnimTextViewY);
                    animatorSet.start();
                    slideDownAnimTextView.addListener(new Animator.AnimatorListener() {
                        @Override
                        public void onAnimationStart(Animator animation) {

                        }

                        @Override
                        public void onAnimationEnd(Animator animation) {
                            CountText.setTextColor(parseColor("#32FFFFFF"));
                        }

                        @Override
                        public void onAnimationCancel(Animator animator) {

                        }

                        @Override
                        public void onAnimationRepeat(Animator animator) {

                        }
                    });
                    play.setOnClickListener( resumeListener5 );
                }
            });

        }
    };
    View.OnClickListener resumeListener6=new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            int a=(int)(millis/1000)*2;
            blinkanimation6.setRepeatCount(a);
            re6.startAnimation(blinkanimation6);
            ObjectAnimator slideDownAnimPlayButton = ObjectAnimator.ofFloat(play, "translationY", 0,-(play.getTop()));
            ObjectAnimator slideDownAnimPlayButtonX = ObjectAnimator.ofFloat(play, "scaleX", 1f, 0.5f);
            ObjectAnimator slideDownAnimPlayButtonY = ObjectAnimator.ofFloat(play, "scaleY", 1f, 0.5f);
            AnimatorSet animatorSet2 = new AnimatorSet();
            animatorSet2.setDuration(300);
            animatorSet2.playTogether(slideDownAnimPlayButton,slideDownAnimPlayButtonX,slideDownAnimPlayButtonY);
            animatorSet2.start();

            ObjectAnimator slideDownAnimTextView = ObjectAnimator.ofFloat(CountText, "translationY", (CountText.getTop()),0);
            ObjectAnimator scaleDownAnimTextViewX = ObjectAnimator.ofFloat(CountText, "scaleX", 0.5f, 1f);
            ObjectAnimator scaleDownAnimTextViewY = ObjectAnimator.ofFloat(CountText, "scaleY", 0.5f, 1f);
            AnimatorSet animatorSet = new AnimatorSet();
            animatorSet.setDuration(300);
            animatorSet.playTogether(slideDownAnimTextView,scaleDownAnimTextViewX,scaleDownAnimTextViewY);
            animatorSet.start();
            CountText.setTextColor(Color.WHITE);

            slideDownAnimPlayButton.addListener(new Animator.AnimatorListener() {
                @Override
                public void onAnimationStart(Animator animation) {

                }

                @Override
                public void onAnimationEnd(Animator animation) {
                    play.setVisibility(View.INVISIBLE);
                }

                @Override
                public void onAnimationCancel(Animator animator) {

                }

                @Override
                public void onAnimationRepeat(Animator animator) {

                }
            });
            final ShortbreakClass3 shortBreaks= new ShortbreakClass3(millis,1000);
            shortBreaks.start();

           CountText.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    shortBreaks.cancel();

                    blinkanimation6.cancel();


                    play.setImageResource(R.mipmap.puse);
                    int marginTop = (int) getResources().getDimensionPixelSize(R.dimen.left_border_distance);
                    int marginRight = (int) getResources().getDimensionPixelSize(R.dimen.right_border_distance);
                    FrameLayout.LayoutParams toastTextLp = ( FrameLayout.LayoutParams)play.getLayoutParams();
                    toastTextLp.setMargins(marginRight, marginTop,0, 0);
                    play.setLayoutParams(toastTextLp);
                    play.setVisibility(View.VISIBLE);


                    ObjectAnimator slideDownAnimPlayButton = ObjectAnimator.ofFloat(play, "translationY", -(play.getTop()), 0);
                    ObjectAnimator slideDownAnimPlayButtonX = ObjectAnimator.ofFloat(play, "scaleX", 0.5f, 1f);
                    ObjectAnimator slideDownAnimPlayButtonY = ObjectAnimator.ofFloat(play, "scaleY", 0.5f, 1f);
                    AnimatorSet animatorSet1 = new AnimatorSet();
                    animatorSet1.setDuration(300);
                    animatorSet1.playTogether(slideDownAnimPlayButton,slideDownAnimPlayButtonX,slideDownAnimPlayButtonY);
                    animatorSet1.start();

                    ObjectAnimator slideDownAnimTextView = ObjectAnimator.ofFloat(CountText, "translationY",0, (CountText.getTop() ));
                    ObjectAnimator scaleDownAnimTextViewX = ObjectAnimator.ofFloat(CountText, "scaleX", 1f, 0.5f);
                    ObjectAnimator scaleDownAnimTextViewY = ObjectAnimator.ofFloat(CountText, "scaleY", 1f, 0.5f);
                    AnimatorSet animatorSet = new AnimatorSet();
                    animatorSet.setDuration(300);
                    animatorSet.playTogether(slideDownAnimTextView,scaleDownAnimTextViewX,scaleDownAnimTextViewY);
                    animatorSet.start();
                    slideDownAnimTextView.addListener(new Animator.AnimatorListener() {
                        @Override
                        public void onAnimationStart(Animator animation) {

                        }

                        @Override
                        public void onAnimationEnd(Animator animation) {
                            CountText.setTextColor(parseColor("#32FFFFFF"));
                        }

                        @Override
                        public void onAnimationCancel(Animator animator) {

                        }

                        @Override
                        public void onAnimationRepeat(Animator animator) {

                        }
                    });
                    play.setOnClickListener( resumeListener6 );
                }
            });

        }
    };
    View.OnClickListener resumeListener7=new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            int a=(int)(millis/1000)*2;
            blinkanimation7.setRepeatCount(a);
            re7.startAnimation(blinkanimation7);
            ObjectAnimator slideDownAnimPlayButton = ObjectAnimator.ofFloat(play, "translationY", 0,-(play.getTop()));
            ObjectAnimator slideDownAnimPlayButtonX = ObjectAnimator.ofFloat(play, "scaleX", 1f, 0.5f);
            ObjectAnimator slideDownAnimPlayButtonY = ObjectAnimator.ofFloat(play, "scaleY", 1f, 0.5f);
            AnimatorSet animatorSet2 = new AnimatorSet();
            animatorSet2.setDuration(300);
            animatorSet2.playTogether(slideDownAnimPlayButton,slideDownAnimPlayButtonX,slideDownAnimPlayButtonY);
            animatorSet2.start();

            ObjectAnimator slideDownAnimTextView = ObjectAnimator.ofFloat(CountText, "translationY", (CountText.getTop()),0);
            ObjectAnimator scaleDownAnimTextViewX = ObjectAnimator.ofFloat(CountText, "scaleX", 0.5f, 1f);
            ObjectAnimator scaleDownAnimTextViewY = ObjectAnimator.ofFloat(CountText, "scaleY", 0.5f, 1f);
            AnimatorSet animatorSet = new AnimatorSet();
            animatorSet.setDuration(300);
            animatorSet.playTogether(slideDownAnimTextView,scaleDownAnimTextViewX,scaleDownAnimTextViewY);
            animatorSet.start();
            CountText.setTextColor(Color.WHITE);

            slideDownAnimPlayButton.addListener(new Animator.AnimatorListener() {
                @Override
                public void onAnimationStart(Animator animation) {

                }

                @Override
                public void onAnimationEnd(Animator animation) {
                    play.setVisibility(View.INVISIBLE);
                }

                @Override
                public void onAnimationCancel(Animator animator) {

                }

                @Override
                public void onAnimationRepeat(Animator animator) {

                }
            });
            final LongbreakClass shortBreaks= new LongbreakClass(millis,1000);
            shortBreaks.start();

            CountText.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    shortBreaks.cancel();
                    blinkanimation7.cancel();


                    play.setImageResource(R.mipmap.puse);
                    int marginTop = (int) getResources().getDimensionPixelSize(R.dimen.left_border_distance);
                    int marginRight = (int) getResources().getDimensionPixelSize(R.dimen.right_border_distance);
                    FrameLayout.LayoutParams toastTextLp = ( FrameLayout.LayoutParams)play.getLayoutParams();
                    toastTextLp.setMargins(marginRight, marginTop,0, 0);
                    play.setLayoutParams(toastTextLp);
                    play.setVisibility(View.VISIBLE);


                    ObjectAnimator slideDownAnimPlayButton = ObjectAnimator.ofFloat(play, "translationY", -(play.getTop()), 0);
                    ObjectAnimator slideDownAnimPlayButtonX = ObjectAnimator.ofFloat(play, "scaleX", 0.5f, 1f);
                    ObjectAnimator slideDownAnimPlayButtonY = ObjectAnimator.ofFloat(play, "scaleY", 0.5f, 1f);
                    AnimatorSet animatorSet1 = new AnimatorSet();
                    animatorSet1.setDuration(300);
                    animatorSet1.playTogether(slideDownAnimPlayButton,slideDownAnimPlayButtonX,slideDownAnimPlayButtonY);
                    animatorSet1.start();

                    ObjectAnimator slideDownAnimTextView = ObjectAnimator.ofFloat(CountText, "translationY",0, (CountText.getTop() ));
                    ObjectAnimator scaleDownAnimTextViewX = ObjectAnimator.ofFloat(CountText, "scaleX", 1f, 0.5f);
                    ObjectAnimator scaleDownAnimTextViewY = ObjectAnimator.ofFloat(CountText, "scaleY", 1f, 0.5f);
                    AnimatorSet animatorSet = new AnimatorSet();
                    animatorSet.setDuration(300);
                    animatorSet.playTogether(slideDownAnimTextView,scaleDownAnimTextViewX,scaleDownAnimTextViewY);
                    animatorSet.start();
                    slideDownAnimTextView.addListener(new Animator.AnimatorListener() {
                        @Override
                        public void onAnimationStart(Animator animation) {

                        }

                        @Override
                        public void onAnimationEnd(Animator animation) {
                            CountText.setTextColor(parseColor("#32FFFFFF"));
                        }

                        @Override
                        public void onAnimationCancel(Animator animator) {

                        }

                        @Override
                        public void onAnimationRepeat(Animator animator) {

                        }
                    });
                    play.setOnClickListener( resumeListener7 );
                }
            });

        }
    };
    View.OnClickListener resumeListener8=new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            ObjectAnimator slideDownAnimPlayButton = ObjectAnimator.ofFloat(play, "translationY", 0,-(play.getTop()));
            ObjectAnimator slideDownAnimPlayButtonX = ObjectAnimator.ofFloat(play, "scaleX", 1f, 0.5f);
            ObjectAnimator slideDownAnimPlayButtonY = ObjectAnimator.ofFloat(play, "scaleY", 1f, 0.5f);
            AnimatorSet animatorSet2 = new AnimatorSet();
            animatorSet2.setDuration(300);
            animatorSet2.playTogether(slideDownAnimPlayButton,slideDownAnimPlayButtonX,slideDownAnimPlayButtonY);
            animatorSet2.start();

            ObjectAnimator slideDownAnimTextView = ObjectAnimator.ofFloat(CountText, "translationY", (CountText.getTop()),0);
            ObjectAnimator scaleDownAnimTextViewX = ObjectAnimator.ofFloat(CountText, "scaleX", 0.5f, 1f);
            ObjectAnimator scaleDownAnimTextViewY = ObjectAnimator.ofFloat(CountText, "scaleY", 0.5f, 1f);
            AnimatorSet animatorSet = new AnimatorSet();
            animatorSet.setDuration(300);
            animatorSet.playTogether(slideDownAnimTextView,scaleDownAnimTextViewX,scaleDownAnimTextViewY);
            animatorSet.start();
            CountText.setTextColor(Color.WHITE);

            slideDownAnimPlayButton.addListener(new Animator.AnimatorListener() {
                @Override
                public void onAnimationStart(Animator animation) {

                }

                @Override
                public void onAnimationEnd(Animator animation) {
                    play.setVisibility(View.INVISIBLE);
                }

                @Override
                public void onAnimationCancel(Animator animator) {

                }

                @Override
                public void onAnimationRepeat(Animator animator) {

                }
            });
            final finishLongbreakClass shortBreaks= new finishLongbreakClass(millis,1000);
            shortBreaks.start();
            CountText.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    shortBreaks.cancel();



                    play.setImageResource(R.mipmap.puse);
                    int marginTop = (int) getResources().getDimensionPixelSize(R.dimen.left_border_distance);
                    int marginRight = (int) getResources().getDimensionPixelSize(R.dimen.right_border_distance);
                    FrameLayout.LayoutParams toastTextLp = ( FrameLayout.LayoutParams)play.getLayoutParams();
                    toastTextLp.setMargins(marginRight, marginTop,0, 0);
                    play.setLayoutParams(toastTextLp);
                    play.setVisibility(View.VISIBLE);


                    ObjectAnimator slideDownAnimPlayButton = ObjectAnimator.ofFloat(play, "translationY", -(play.getTop()), 0);
                    ObjectAnimator slideDownAnimPlayButtonX = ObjectAnimator.ofFloat(play, "scaleX", 0.5f, 1f);
                    ObjectAnimator slideDownAnimPlayButtonY = ObjectAnimator.ofFloat(play, "scaleY", 0.5f, 1f);
                    AnimatorSet animatorSet1 = new AnimatorSet();
                    animatorSet1.setDuration(300);
                    animatorSet1.playTogether(slideDownAnimPlayButton,slideDownAnimPlayButtonX,slideDownAnimPlayButtonY);
                    animatorSet1.start();

                    ObjectAnimator slideDownAnimTextView = ObjectAnimator.ofFloat(CountText, "translationY",0, (CountText.getTop() ));
                    ObjectAnimator scaleDownAnimTextViewX = ObjectAnimator.ofFloat(CountText, "scaleX", 1f, 0.5f);
                    ObjectAnimator scaleDownAnimTextViewY = ObjectAnimator.ofFloat(CountText, "scaleY", 1f, 0.5f);
                    AnimatorSet animatorSet = new AnimatorSet();
                    animatorSet.setDuration(300);
                    animatorSet.playTogether(slideDownAnimTextView,scaleDownAnimTextViewX,scaleDownAnimTextViewY);
                    animatorSet.start();
                    slideDownAnimTextView.addListener(new Animator.AnimatorListener() {
                        @Override
                        public void onAnimationStart(Animator animation) {

                        }

                        @Override
                        public void onAnimationEnd(Animator animation) {
                            CountText.setTextColor(parseColor("#32FFFFFF"));
                        }

                        @Override
                        public void onAnimationCancel(Animator animator) {

                        }

                        @Override
                        public void onAnimationRepeat(Animator animator) {

                        }
                    });
                    play.setOnClickListener( resumeListener8 );
                }
            });

        }
    };




    @TargetApi(Build.VERSION_CODES.GINGERBREAD)
    @SuppressLint("NewApi")
    public class CounterClass extends CountDownTimer {
        public CounterClass(long millisInFuture, long countDownInterval) {

            super(millisInFuture, countDownInterval);
        }
        @SuppressLint("NewApi")
        @TargetApi(Build.VERSION_CODES.GINGERBREAD)
        @Override
        public void onTick(long millisUntilFinished) {
            millis = millisUntilFinished;
            String hms = String.format("%02d:%02d",
                    TimeUnit.MILLISECONDS.toMinutes(millis) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(millis)),
                    TimeUnit.MILLISECONDS.toSeconds(millis) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millis)));
            System.out.println(hms);

            CountText.setText(hms);
        }

        @Override
        public void onFinish() {
            initiatePopupWindow();
            String longText = "Some times you have to take a break and breathe before you keep moving forward \n" +
                    "-Kayla Panchisin";
            Intent notifyIntent = new Intent(Intent.ACTION_MAIN);
            notifyIntent.setClass(getApplicationContext(), MainActivity.class);
            notifyIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
            PendingIntent pi = PendingIntent.getActivity(MainActivity.this,0,notifyIntent,0);
            Notification notification = new NotificationCompat.Builder(MainActivity.this)
                    .setTicker("this is ticker text")
                    .setSmallIcon(android.R.drawable.ic_lock_idle_alarm)
                    .setContentTitle("Pomodoro Finished")
                    .setContentText("Time is Up!")
                    .setContentIntent(pi)
                    .setAutoCancel(true)
                    .setStyle(new NotificationCompat.BigTextStyle().bigText(longText))
                    .build();

            NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
            if(Settings.chosenRingtone==null) {
                notification.defaults |= Notification.DEFAULT_SOUND;
            }
            else {
                Uri noti=Uri.parse(Settings.chosenRingtone);
                notification.sound=noti;
            }
            if(Settings.vibrationState==1) {
                notification.defaults |= Notification.DEFAULT_VIBRATE;
            }
            notificationManager.notify(0, notification);

        }

    }
    public class ShortbreakClass extends CountDownTimer {


        public ShortbreakClass(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }
        @SuppressLint("NewApi")
        @TargetApi(Build.VERSION_CODES.GINGERBREAD)
        @Override
        public void onTick(long millisUntilFinished) {

            millis = millisUntilFinished;
           String hms = String.format("%02d:%02d",
                    TimeUnit.MILLISECONDS.toMinutes(millis) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(millis)),
                    TimeUnit.MILLISECONDS.toSeconds(millis) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millis)));
            System.out.println(hms);

            CountText.setText(hms);
        }

        @Override
        public void onFinish() {
            shortPopupWindow();
            String longText = "The only way to do great work is to love what you do.\n" +
                    "-Steve Jobs";
            Intent notifyIntent = new Intent(Intent.ACTION_MAIN);
            notifyIntent.setClass(getApplicationContext(), MainActivity.class);
            notifyIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
            PendingIntent pi = PendingIntent.getActivity(MainActivity.this,0,notifyIntent,0);
            Notification notification = new NotificationCompat.Builder(MainActivity.this)
                    .setTicker("this is ticker text")
                    .setSmallIcon(android.R.drawable.ic_lock_idle_alarm)
                    .setContentTitle("Short Break Finished")
                    .setContentText("Time is Up!")
                    .setContentIntent(pi)
                    .setAutoCancel(true)
                    .setStyle(new NotificationCompat.BigTextStyle().bigText(longText))
                    .build();

            NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
            if(Settings.chosenRingtone==null) {
                notification.defaults |= Notification.DEFAULT_SOUND;
            }
            else {
                Uri noti=Uri.parse(Settings.chosenRingtone);
                notification.sound=noti;
            }
            if(Settings.vibrationState==1) {
                notification.defaults |= Notification.DEFAULT_VIBRATE;
            }
            notificationManager.notify(0, notification);


        }
    }
    public class PodobreakClass2 extends CountDownTimer {

        public PodobreakClass2(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }
        @SuppressLint("NewApi")
        @TargetApi(Build.VERSION_CODES.GINGERBREAD)
        @Override
        public void onTick(long millisUntilFinished) {

            millis = millisUntilFinished;
            String hms = String.format("%02d:%02d",
                    TimeUnit.MILLISECONDS.toMinutes(millis) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(millis)),
                    TimeUnit.MILLISECONDS.toSeconds(millis) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millis)));
            System.out.println(hms);

            CountText.setText(hms);
        }

        @Override
        public void onFinish() {
            initiatePopupWindow2();
            String longText = "Some times you have to take a break and breathe before you keep moving forward \n" +
                    "-Kayla Panchisin";
            Intent notifyIntent = new Intent(Intent.ACTION_MAIN);
            notifyIntent.setClass(getApplicationContext(), MainActivity.class);
            notifyIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
            PendingIntent pi = PendingIntent.getActivity(MainActivity.this,0,notifyIntent,0);
            Notification notification = new NotificationCompat.Builder(MainActivity.this)
                    .setTicker("this is ticker text")
                    .setSmallIcon(android.R.drawable.ic_lock_idle_alarm)
                    .setContentTitle("Pomodoro Finished")
                    .setContentText("Time is Up!")
                    .setContentIntent(pi)
                    .setAutoCancel(true)
                    .setStyle(new NotificationCompat.BigTextStyle().bigText(longText))
                    .build();

            NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
            if(Settings.chosenRingtone==null) {
                notification.defaults |= Notification.DEFAULT_SOUND;
            }
            else {
                Uri noti=Uri.parse(Settings.chosenRingtone);
                notification.sound=noti;
            }
            if(Settings.vibrationState==1) {
                notification.defaults |= Notification.DEFAULT_VIBRATE;
            }
            notificationManager.notify(0, notification);


        }
    }
    public class ShortbreakClass2 extends CountDownTimer {

        public ShortbreakClass2(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }
        @SuppressLint("NewApi")
        @TargetApi(Build.VERSION_CODES.GINGERBREAD)
        @Override
        public void onTick(long millisUntilFinished) {

            millis = millisUntilFinished;
            String hms = String.format("%02d:%02d",
                    TimeUnit.MILLISECONDS.toMinutes(millis) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(millis)),
                    TimeUnit.MILLISECONDS.toSeconds(millis) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millis)));
            System.out.println(hms);

            CountText.setText(hms);
        }

        @Override
        public void onFinish() {
            shortPopupWindow2();
            String longText = "The only way to do great work is to love what you do.\n" +
                    "-Steve Jobs";
            Intent notifyIntent = new Intent(Intent.ACTION_MAIN);
            notifyIntent.setClass(getApplicationContext(), MainActivity.class);
            notifyIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
            PendingIntent pi = PendingIntent.getActivity(MainActivity.this,0,notifyIntent,0);
            Notification notification = new NotificationCompat.Builder(MainActivity.this)
                    .setTicker("this is ticker text")
                    .setSmallIcon(android.R.drawable.ic_lock_idle_alarm)
                    .setContentTitle("Short Break Finished")
                    .setContentText("Time is Up!")
                    .setContentIntent(pi)
                    .setAutoCancel(true)
                    .setStyle(new NotificationCompat.BigTextStyle().bigText(longText))
                    .build();

            NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
            if(Settings.chosenRingtone==null) {
                notification.defaults |= Notification.DEFAULT_SOUND;
            }
            else {
                Uri noti=Uri.parse(Settings.chosenRingtone);
                notification.sound=noti;
            }
            if(Settings.vibrationState==1) {
                notification.defaults |= Notification.DEFAULT_VIBRATE;
            }
            notificationManager.notify(0, notification);

        }
    }
    public class PodobreakClass3 extends CountDownTimer {

        public PodobreakClass3(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }
        @SuppressLint("NewApi")
        @TargetApi(Build.VERSION_CODES.GINGERBREAD)
        @Override
        public void onTick(long millisUntilFinished) {

            millis = millisUntilFinished;
            String hms = String.format("%02d:%02d",
                    TimeUnit.MILLISECONDS.toMinutes(millis) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(millis)),
                    TimeUnit.MILLISECONDS.toSeconds(millis) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millis)));
            System.out.println(hms);

           CountText.setText(hms);
        }

        @Override
        public void onFinish() {
            initiatePopupWindow3();
            String longText = "Some times you have to take a break and breathe before you keep moving forward \n" +
                    "-Kayla Panchisin";
            Intent notifyIntent = new Intent(Intent.ACTION_MAIN);
            notifyIntent.setClass(getApplicationContext(), MainActivity.class);
            notifyIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
            PendingIntent pi = PendingIntent.getActivity(MainActivity.this,0,notifyIntent,0);
            Notification notification = new NotificationCompat.Builder(MainActivity.this)
                    .setTicker("this is ticker text")
                    .setSmallIcon(android.R.drawable.ic_lock_idle_alarm)
                    .setContentTitle("Pomodoro Finished")
                    .setContentText("Time is Up!")
                    .setContentIntent(pi)
                    .setAutoCancel(true)
                    .setStyle(new NotificationCompat.BigTextStyle().bigText(longText))
                    .build();

            NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
            if(Settings.chosenRingtone==null) {
                notification.defaults |= Notification.DEFAULT_SOUND;
            }
            else {
                Uri noti=Uri.parse(Settings.chosenRingtone);
                notification.sound=noti;
            }
            if(Settings.vibrationState==1) {
                notification.defaults |= Notification.DEFAULT_VIBRATE;
            }
            notificationManager.notify(0, notification);

        }
    }
    public class ShortbreakClass3 extends CountDownTimer {

        public ShortbreakClass3(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }
        @SuppressLint("NewApi")
        @TargetApi(Build.VERSION_CODES.GINGERBREAD)
        @Override
        public void onTick(long millisUntilFinished) {

            millis = millisUntilFinished;
            String hms = String.format("%02d:%02d",
                    TimeUnit.MILLISECONDS.toMinutes(millis) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(millis)),
                    TimeUnit.MILLISECONDS.toSeconds(millis) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millis)));
            System.out.println(hms);

            CountText.setText(hms);
        }

        @Override
        public void onFinish() {
            shortPopupWindow3();
            String longText = "The only way to do great work is to love what you do.\n" +
                    "-Steve Jobs";
            Intent notifyIntent = new Intent(Intent.ACTION_MAIN);
            notifyIntent.setClass(getApplicationContext(), MainActivity.class);
            notifyIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
            PendingIntent pi = PendingIntent.getActivity(MainActivity.this,0,notifyIntent,0);
            Notification notification = new NotificationCompat.Builder(MainActivity.this)
                    .setTicker("this is ticker text")
                    .setSmallIcon(android.R.drawable.ic_lock_idle_alarm)
                    .setContentTitle("Short Break Finished")
                    .setContentText("Time is Up!")
                    .setContentIntent(pi)
                    .setAutoCancel(true)
                    .setStyle(new NotificationCompat.BigTextStyle().bigText(longText))
                    .build();

            NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
            if(Settings.chosenRingtone==null) {
                notification.defaults |= Notification.DEFAULT_SOUND;
            }
            else {
                Uri noti=Uri.parse(Settings.chosenRingtone);
                notification.sound=noti;
            }
            if(Settings.vibrationState==1) {
                notification.defaults |= Notification.DEFAULT_VIBRATE;
            }
            notificationManager.notify(0, notification);

        }
    }

    public class LongbreakClass extends CountDownTimer {

        public LongbreakClass(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }
        @SuppressLint("NewApi")
        @TargetApi(Build.VERSION_CODES.GINGERBREAD)
        @Override
        public void onTick(long millisUntilFinished) {

            millis = millisUntilFinished;
            String hms = String.format("%02d:%02d",
                    TimeUnit.MILLISECONDS.toMinutes(millis) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(millis)),
                    TimeUnit.MILLISECONDS.toSeconds(millis) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millis)));
            System.out.println(hms);

            CountText.setText(hms);
        }

        @Override
        public void onFinish() {
            PodofinshPopupWindow();
            String longText = "Some times you have to take a break and breathe before you keep moving forward \n" +
                    "-Kayla Panchisin";
            Intent notifyIntent = new Intent(Intent.ACTION_MAIN);
            notifyIntent.setClass(getApplicationContext(), MainActivity.class);
            notifyIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
            PendingIntent pi = PendingIntent.getActivity(MainActivity.this,0,notifyIntent,0);
            Notification notification = new NotificationCompat.Builder(MainActivity.this)
                    .setTicker("this is ticker text")
                    .setSmallIcon(android.R.drawable.ic_lock_idle_alarm)
                    .setContentTitle("Pomodoro Finished")
                    .setContentText("Time is Up!")
                    .setContentIntent(pi)
                    .setAutoCancel(true)
                    .setStyle(new NotificationCompat.BigTextStyle().bigText(longText))
                    .build();

            NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
            if(Settings.chosenRingtone==null) {
                notification.defaults |= Notification.DEFAULT_SOUND;
            }
            else {
                Uri noti=Uri.parse(Settings.chosenRingtone);
                notification.sound=noti;
            }
            if(Settings.vibrationState==1) {
                notification.defaults |= Notification.DEFAULT_VIBRATE;
            }
            notificationManager.notify(0, notification);
        }
    }
    public class finishLongbreakClass extends CountDownTimer {

        public finishLongbreakClass(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }
        @SuppressLint("NewApi")
        @TargetApi(Build.VERSION_CODES.GINGERBREAD)
        @Override
        public void onTick(long millisUntilFinished) {

            millis = millisUntilFinished;
            String hms = String.format("%02d:%02d",
                    TimeUnit.MILLISECONDS.toMinutes(millis) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(millis)),
                    TimeUnit.MILLISECONDS.toSeconds(millis) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millis)));
            System.out.println(hms);

            CountText.setText(hms);
        }

        @Override
        public void onFinish() {
            longPopupWindow();
            String longText = "The only way to do great work is to love what you do.\n" +
                    "-Steve Jobs";
            Intent notifyIntent = new Intent(Intent.ACTION_MAIN);
            notifyIntent.setClass(getApplicationContext(), MainActivity.class);
            notifyIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
            PendingIntent pi = PendingIntent.getActivity(MainActivity.this,0,notifyIntent,0);
            Notification notification = new NotificationCompat.Builder(MainActivity.this)
                    .setTicker("this is ticker text")
                    .setSmallIcon(android.R.drawable.ic_lock_idle_alarm)
                    .setContentTitle("Long Break Finished")
                    .setContentText("Time is Up!")
                    .setContentIntent(pi)
                    .setAutoCancel(true)
                    .setStyle(new NotificationCompat.BigTextStyle().bigText(longText))
                    .build();

            NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
            if(Settings.chosenRingtone==null) {
                notification.defaults |= Notification.DEFAULT_SOUND;
            }
            else {
                Uri noti=Uri.parse(Settings.chosenRingtone);
                notification.sound=noti;
            }
            if(Settings.vibrationState==1) {
                notification.defaults |= Notification.DEFAULT_VIBRATE;
            }
            notificationManager.notify(0, notification);
        }
    }
    private PopupWindow pwindo;
    private PopupWindow shortbreakpop;
    private PopupWindow longbreakpop;
    private PopupWindow podostartpop;

    private void initiatePopupWindow() {
        try {
// We need to get the instance of the LayoutInflater
            LayoutInflater inflater = (LayoutInflater) MainActivity.this
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View layout = inflater.inflate(R.layout.popup,
                    (ViewGroup) findViewById(R.id.element));
            pwindo = new PopupWindow(layout, 800, 650, true);
            pwindo.showAtLocation(layout, Gravity.CENTER, 0, 0);

           TextView breaktime = (TextView) layout.findViewById(R.id.breakTime);
           breaktime.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View view) {
                   pwindo.dismiss();
                   play.setVisibility(View.INVISIBLE);
                   ((GradientDrawable)re1.getBackground()).setStroke(1, Color.parseColor("#00000000"));
                   myView.setBackgroundColor(Color.argb(20,255,255,255));
                   ((GradientDrawable) myView2.getBackground()).setColor(Color.WHITE);
                   ((GradientDrawable)re2.getBackground()).setStroke(2,Color.WHITE);
                   blinkanimation2= new AlphaAnimation(1, 0); // Change alpha from fully visible to invisible
                   blinkanimation2.setDuration(500); // duration - half a second
                   blinkanimation2.setInterpolator(new LinearInterpolator()); // do not alter animation rate
                   blinkanimation2.setRepeatCount(Settings.ShortProgress*60*2); // Repeat animation infinitely
                   blinkanimation2.setRepeatMode(Animation.REVERSE);
                   re2.startAnimation(blinkanimation2);
                    CountText.setVisibility(View.VISIBLE);
                   CountText.startAnimation(fadeInAnimation);
                   if(Settings.ShortProgress==5) {
                       shortBreaks.start();
                   }
                   else {
                       shortBreaks.cancel();
                       shortBreaks= new ShortbreakClass(Settings.ShortProgress*60*1000,1000);
                       shortBreaks.start();
                   }
                   textView.setText("[Short Break]");
                   CountText.setOnClickListener(new View.OnClickListener() {
                       @Override
                       public void onClick(View view) {
                           blinkanimation2.cancel();
                           shortBreaks.cancel();

                           play.setImageResource(R.mipmap.puse);
                           int marginTop = (int) getResources().getDimensionPixelSize(R.dimen.left_border_distance);
                           int marginRight = (int) getResources().getDimensionPixelSize(R.dimen.right_border_distance);
                           FrameLayout.LayoutParams toastTextLp = ( FrameLayout.LayoutParams)play.getLayoutParams();
                           toastTextLp.setMargins(marginRight, marginTop,0, 0);
                           play.setLayoutParams(toastTextLp);
                           play.setVisibility(View.VISIBLE);


                           ObjectAnimator slideDownAnimPlayButton = ObjectAnimator.ofFloat(play, "translationY", -(play.getTop()), 0);
                           ObjectAnimator slideDownAnimPlayButtonX = ObjectAnimator.ofFloat(play, "scaleX", 0.5f, 1f);
                           ObjectAnimator slideDownAnimPlayButtonY = ObjectAnimator.ofFloat(play, "scaleY", 0.5f, 1f);
                           AnimatorSet animatorSet1 = new AnimatorSet();
                           animatorSet1.setDuration(300);
                           animatorSet1.playTogether(slideDownAnimPlayButton,slideDownAnimPlayButtonX,slideDownAnimPlayButtonY);
                           animatorSet1.start();

                           ObjectAnimator slideDownAnimTextView = ObjectAnimator.ofFloat(CountText, "translationY",0, (CountText.getTop() ));
                           ObjectAnimator scaleDownAnimTextViewX = ObjectAnimator.ofFloat(CountText, "scaleX", 1f, 0.5f);
                           ObjectAnimator scaleDownAnimTextViewY = ObjectAnimator.ofFloat(CountText, "scaleY", 1f, 0.5f);
                           AnimatorSet animatorSet = new AnimatorSet();
                           animatorSet.setDuration(300);
                           animatorSet.playTogether(slideDownAnimTextView,scaleDownAnimTextViewX,scaleDownAnimTextViewY);
                           animatorSet.start();
                           slideDownAnimTextView.addListener(new Animator.AnimatorListener() {
                               @Override
                               public void onAnimationStart(Animator animation) {

                               }

                               @Override
                               public void onAnimationEnd(Animator animation) {
                                   CountText.setTextColor(parseColor("#32FFFFFF"));
                               }

                               @Override
                               public void onAnimationCancel(Animator animator) {

                               }

                               @Override
                               public void onAnimationRepeat(Animator animator) {

                               }
                           });
                           play.setOnClickListener( resumeListener2 );
                       }
                   });

               }
           });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void shortPopupWindow() {
        try {
// We need to get the instance of the LayoutInflater
            LayoutInflater inflater = (LayoutInflater) MainActivity.this
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View layout = inflater.inflate(R.layout.shortpop,
                    (ViewGroup) findViewById(R.id.element2));
            shortbreakpop = new PopupWindow(layout, 800, 590, true);
            shortbreakpop.showAtLocation(layout, Gravity.CENTER, 0, 0);

            TextView breaktime = (TextView) layout.findViewById(R.id.startpodo);
            breaktime.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    shortbreakpop.dismiss();
                    play.setVisibility(View.INVISIBLE);
                    ((GradientDrawable)re2.getBackground()).setStroke(1,Color.parseColor("#00000000"));
                    ((GradientDrawable)myView2.getBackground()).setColor(Color.argb(20,255,255,255));
                    myView3.setBackgroundColor(Color.WHITE);
                    ((GradientDrawable)re3.getBackground()).setStroke(1,Color.WHITE);
                   blinkanimation3= new AlphaAnimation(1, 0); // Change alpha from fully visible to invisible
                    blinkanimation3.setDuration(500); // duration - half a second
                    blinkanimation3.setInterpolator(new LinearInterpolator()); // do not alter animation rate
                    blinkanimation3.setRepeatCount(Settings.PomoProgress*60*2); // Repeat animation infinitely
                    blinkanimation3.setRepeatMode(Animation.REVERSE);
                    re3.startAnimation(blinkanimation3);
                    if(Settings.PomoProgress==25) {
                        podotimer2.start();
                    }
                    else {
                        podotimer2.cancel();
                        podotimer2 = new PodobreakClass2(Settings.PomoProgress*60*1000,1000);
                        podotimer2.start();
                    }
                    CountText.setVisibility(View.VISIBLE);
                    CountText.startAnimation(fadeInAnimation);
                    textView.setText("[Pomodoro]");
                    CountText.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            blinkanimation3.cancel();
                            podotimer2.cancel();

                            play.setImageResource(R.mipmap.puse);
                            int marginTop = (int) getResources().getDimensionPixelSize(R.dimen.left_border_distance);
                            int marginRight = (int) getResources().getDimensionPixelSize(R.dimen.right_border_distance);
                            FrameLayout.LayoutParams toastTextLp = ( FrameLayout.LayoutParams)play.getLayoutParams();
                            toastTextLp.setMargins(marginRight, marginTop,0, 0);
                            play.setLayoutParams(toastTextLp);
                            play.setVisibility(View.VISIBLE);


                            ObjectAnimator slideDownAnimPlayButton = ObjectAnimator.ofFloat(play, "translationY", -(play.getTop()), 0);
                            ObjectAnimator slideDownAnimPlayButtonX = ObjectAnimator.ofFloat(play, "scaleX", 0.5f, 1f);
                            ObjectAnimator slideDownAnimPlayButtonY = ObjectAnimator.ofFloat(play, "scaleY", 0.5f, 1f);
                            AnimatorSet animatorSet1 = new AnimatorSet();
                            animatorSet1.setDuration(300);
                            animatorSet1.playTogether(slideDownAnimPlayButton,slideDownAnimPlayButtonX,slideDownAnimPlayButtonY);
                            animatorSet1.start();

                            ObjectAnimator slideDownAnimTextView = ObjectAnimator.ofFloat(CountText, "translationY",0, (CountText.getTop() ));
                            ObjectAnimator scaleDownAnimTextViewX = ObjectAnimator.ofFloat(CountText, "scaleX", 1f, 0.5f);
                            ObjectAnimator scaleDownAnimTextViewY = ObjectAnimator.ofFloat(CountText, "scaleY", 1f, 0.5f);
                            AnimatorSet animatorSet = new AnimatorSet();
                            animatorSet.setDuration(300);
                            animatorSet.playTogether(slideDownAnimTextView,scaleDownAnimTextViewX,scaleDownAnimTextViewY);
                            animatorSet.start();
                            slideDownAnimTextView.addListener(new Animator.AnimatorListener() {
                                @Override
                                public void onAnimationStart(Animator animation) {

                                }

                                @Override
                                public void onAnimationEnd(Animator animation) {
                                    CountText.setTextColor(parseColor("#32FFFFFF"));
                                }

                                @Override
                                public void onAnimationCancel(Animator animator) {

                                }

                                @Override
                                public void onAnimationRepeat(Animator animator) {

                                }
                            });
                            play.setOnClickListener( resumeListener3 );
                        }
                    });

                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void initiatePopupWindow2() {
        try {
// We need to get the instance of the LayoutInflater
            LayoutInflater inflater = (LayoutInflater) MainActivity.this
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View layout = inflater.inflate(R.layout.popup,
                    (ViewGroup) findViewById(R.id.element));
            pwindo = new PopupWindow(layout, 800, 650, true);
            pwindo.showAtLocation(layout, Gravity.CENTER, 0, 0);

            TextView breaktime = (TextView) layout.findViewById(R.id.breakTime);
            breaktime.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    pwindo.dismiss();
                    play.setVisibility(View.INVISIBLE);
                    ((GradientDrawable)re3.getBackground()).setStroke(1,Color.parseColor("#00000000"));
                    myView3.setBackgroundColor(Color.argb(20,255,255,255));
                    ((GradientDrawable)myView4.getBackground()).setColor(Color.WHITE);
                    ((GradientDrawable)re4.getBackground()).setStroke(2,Color.WHITE);
                     blinkanimation4= new AlphaAnimation(1, 0); // Change alpha from fully visible to invisible
                    blinkanimation4.setDuration(500); // duration - half a second
                    blinkanimation4.setInterpolator(new LinearInterpolator()); // do not alter animation rate
                    blinkanimation4.setRepeatCount(Settings.ShortProgress*60*2); // Repeat animation infinitely
                    blinkanimation4.setRepeatMode(Animation.REVERSE);
                    re4.startAnimation(blinkanimation4);

                    if(Settings.ShortProgress==5) {
                        shortBreaks2.start();
                    }
                    else {
                        shortBreaks2.cancel();
                        shortBreaks2= new ShortbreakClass2(Settings.ShortProgress*60*1000,1000);
                        shortBreaks2.start();
                    }
                    CountText.setVisibility(View.VISIBLE);
                    CountText.startAnimation(fadeInAnimation);
                    textView.setText("[Short Break]");
                    CountText.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            blinkanimation4.cancel();

                            shortBreaks2.cancel();
                            play.setImageResource(R.mipmap.puse);
                            int marginTop = (int) getResources().getDimensionPixelSize(R.dimen.left_border_distance);
                            int marginRight = (int) getResources().getDimensionPixelSize(R.dimen.right_border_distance);
                            FrameLayout.LayoutParams toastTextLp = ( FrameLayout.LayoutParams)play.getLayoutParams();
                            toastTextLp.setMargins(marginRight, marginTop,0, 0);
                            play.setLayoutParams(toastTextLp);
                            play.setVisibility(View.VISIBLE);


                            ObjectAnimator slideDownAnimPlayButton = ObjectAnimator.ofFloat(play, "translationY", -(play.getTop()), 0);
                            ObjectAnimator slideDownAnimPlayButtonX = ObjectAnimator.ofFloat(play, "scaleX", 0.5f, 1f);
                            ObjectAnimator slideDownAnimPlayButtonY = ObjectAnimator.ofFloat(play, "scaleY", 0.5f, 1f);
                            AnimatorSet animatorSet1 = new AnimatorSet();
                            animatorSet1.setDuration(300);
                            animatorSet1.playTogether(slideDownAnimPlayButton,slideDownAnimPlayButtonX,slideDownAnimPlayButtonY);
                            animatorSet1.start();

                            ObjectAnimator slideDownAnimTextView = ObjectAnimator.ofFloat(CountText, "translationY",0, (CountText.getTop() ));
                            ObjectAnimator scaleDownAnimTextViewX = ObjectAnimator.ofFloat(CountText, "scaleX", 1f, 0.5f);
                            ObjectAnimator scaleDownAnimTextViewY = ObjectAnimator.ofFloat(CountText, "scaleY", 1f, 0.5f);
                            AnimatorSet animatorSet = new AnimatorSet();
                            animatorSet.setDuration(300);
                            animatorSet.playTogether(slideDownAnimTextView,scaleDownAnimTextViewX,scaleDownAnimTextViewY);
                            animatorSet.start();
                            slideDownAnimTextView.addListener(new Animator.AnimatorListener() {
                                @Override
                                public void onAnimationStart(Animator animation) {

                                }

                                @Override
                                public void onAnimationEnd(Animator animation) {
                                    CountText.setTextColor(parseColor("#32FFFFFF"));
                                }

                                @Override
                                public void onAnimationCancel(Animator animator) {

                                }

                                @Override
                                public void onAnimationRepeat(Animator animator) {

                                }
                            });
                            play.setOnClickListener( resumeListener4 );
                        }
                    });

                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void shortPopupWindow2() {
        try {
// We need to get the instance of the LayoutInflater
            LayoutInflater inflater = (LayoutInflater) MainActivity.this
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View layout = inflater.inflate(R.layout.shortpop,
                    (ViewGroup) findViewById(R.id.element2));
            shortbreakpop = new PopupWindow(layout, 800, 590, true);
            shortbreakpop.showAtLocation(layout, Gravity.CENTER, 0, 0);

            TextView breaktime = (TextView) layout.findViewById(R.id.startpodo);
            breaktime.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    play.setVisibility(View.INVISIBLE);
                    shortbreakpop.dismiss();
                    ((GradientDrawable)re4.getBackground()).setStroke(1,Color.parseColor("#00000000"));
                    ((GradientDrawable)myView4.getBackground()).setColor(Color.argb(20,255,255,255));
                    myView5.setBackgroundColor(Color.WHITE);
                    ((GradientDrawable)re5.getBackground()).setStroke(1,Color.WHITE);
                     blinkanimation5= new AlphaAnimation(1, 0); // Change alpha from fully visible to invisible
                    blinkanimation5.setDuration(500); // duration - half a second
                    blinkanimation5.setInterpolator(new LinearInterpolator()); // do not alter animation rate
                    blinkanimation5.setRepeatCount(Settings.PomoProgress*60*2); // Repeat animation infinitely
                    blinkanimation5.setRepeatMode(Animation.REVERSE);
                    re5.startAnimation(blinkanimation5);

                    if(Settings.PomoProgress==25) {
                        podotimer3.start();
                    }
                    else {
                        podotimer3.cancel();
                        podotimer3 = new PodobreakClass3(Settings.PomoProgress*60*1000,1000);
                        podotimer3.start();
                    }
                    CountText.setVisibility(View.VISIBLE);
                    CountText.startAnimation(fadeInAnimation);
                    textView.setText("[Pomodoro]");
                    CountText.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            blinkanimation5.cancel();

                            podotimer3.cancel();
                            play.setImageResource(R.mipmap.puse);
                            int marginTop = (int) getResources().getDimensionPixelSize(R.dimen.left_border_distance);
                            int marginRight = (int) getResources().getDimensionPixelSize(R.dimen.right_border_distance);
                            FrameLayout.LayoutParams toastTextLp = ( FrameLayout.LayoutParams)play.getLayoutParams();
                            toastTextLp.setMargins(marginRight, marginTop,0, 0);
                            play.setLayoutParams(toastTextLp);
                            play.setVisibility(View.VISIBLE);


                            ObjectAnimator slideDownAnimPlayButton = ObjectAnimator.ofFloat(play, "translationY", -(play.getTop()), 0);
                            ObjectAnimator slideDownAnimPlayButtonX = ObjectAnimator.ofFloat(play, "scaleX", 0.5f, 1f);
                            ObjectAnimator slideDownAnimPlayButtonY = ObjectAnimator.ofFloat(play, "scaleY", 0.5f, 1f);
                            AnimatorSet animatorSet1 = new AnimatorSet();
                            animatorSet1.setDuration(300);
                            animatorSet1.playTogether(slideDownAnimPlayButton,slideDownAnimPlayButtonX,slideDownAnimPlayButtonY);
                            animatorSet1.start();

                            ObjectAnimator slideDownAnimTextView = ObjectAnimator.ofFloat(CountText, "translationY",0, (CountText.getTop() ));
                            ObjectAnimator scaleDownAnimTextViewX = ObjectAnimator.ofFloat(CountText, "scaleX", 1f, 0.5f);
                            ObjectAnimator scaleDownAnimTextViewY = ObjectAnimator.ofFloat(CountText, "scaleY", 1f, 0.5f);
                            AnimatorSet animatorSet = new AnimatorSet();
                            animatorSet.setDuration(300);
                            animatorSet.playTogether(slideDownAnimTextView,scaleDownAnimTextViewX,scaleDownAnimTextViewY);
                            animatorSet.start();
                            slideDownAnimTextView.addListener(new Animator.AnimatorListener() {
                                @Override
                                public void onAnimationStart(Animator animation) {

                                }

                                @Override
                                public void onAnimationEnd(Animator animation) {
                                    CountText.setTextColor(parseColor("#32FFFFFF"));
                                }

                                @Override
                                public void onAnimationCancel(Animator animator) {

                                }

                                @Override
                                public void onAnimationRepeat(Animator animator) {

                                }
                            });
                            play.setOnClickListener( resumeListener5 );
                        }
                    });

                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void initiatePopupWindow3() {
        try {
// We need to get the instance of the LayoutInflater
            LayoutInflater inflater = (LayoutInflater) MainActivity.this
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View layout = inflater.inflate(R.layout.popup,
                    (ViewGroup) findViewById(R.id.element));
            pwindo = new PopupWindow(layout, 800, 650, true);
            pwindo.showAtLocation(layout, Gravity.CENTER, 0, 0);

            TextView breaktime = (TextView) layout.findViewById(R.id.breakTime);
            breaktime.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    pwindo.dismiss();
                    play.setVisibility(View.INVISIBLE);
                    ((GradientDrawable)re5.getBackground()).setStroke(1,Color.parseColor("#00000000"));
                    myView5.setBackgroundColor(Color.argb(20,255,255,255));
                    ((GradientDrawable)myView6.getBackground()).setColor(Color.WHITE);
                    ((GradientDrawable)re6.getBackground()).setStroke(2,Color.WHITE);
                     blinkanimation6= new AlphaAnimation(1, 0); // Change alpha from fully visible to invisible
                    blinkanimation6.setDuration(500); // duration - half a second
                    blinkanimation6.setInterpolator(new LinearInterpolator()); // do not alter animation rate
                    blinkanimation6.setRepeatCount(Settings.ShortProgress*60*2); // Repeat animation infinitely
                    blinkanimation6.setRepeatMode(Animation.REVERSE);
                    re6.startAnimation(blinkanimation6);
                    shortBreaks3.start();
                    if(Settings.ShortProgress==5) {
                        shortBreaks3.start();
                    }
                    else {
                        shortBreaks3.cancel();
                        shortBreaks3= new ShortbreakClass3(Settings.ShortProgress*60*1000,1000);
                        shortBreaks3.start();
                    }
                    CountText.setVisibility(View.VISIBLE);
                    CountText.startAnimation(fadeInAnimation);
                    textView.setText("[Short Break]");
                    CountText.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            blinkanimation6.cancel();

                            shortBreaks3.cancel();
                            play.setImageResource(R.mipmap.puse);
                            int marginTop = (int) getResources().getDimensionPixelSize(R.dimen.left_border_distance);
                            int marginRight = (int) getResources().getDimensionPixelSize(R.dimen.right_border_distance);
                            FrameLayout.LayoutParams toastTextLp = ( FrameLayout.LayoutParams)play.getLayoutParams();
                            toastTextLp.setMargins(marginRight, marginTop,0, 0);
                            play.setLayoutParams(toastTextLp);
                            play.setVisibility(View.VISIBLE);


                            ObjectAnimator slideDownAnimPlayButton = ObjectAnimator.ofFloat(play, "translationY", -(play.getTop()), 0);
                            ObjectAnimator slideDownAnimPlayButtonX = ObjectAnimator.ofFloat(play, "scaleX", 0.5f, 1f);
                            ObjectAnimator slideDownAnimPlayButtonY = ObjectAnimator.ofFloat(play, "scaleY", 0.5f, 1f);
                            AnimatorSet animatorSet1 = new AnimatorSet();
                            animatorSet1.setDuration(300);
                            animatorSet1.playTogether(slideDownAnimPlayButton,slideDownAnimPlayButtonX,slideDownAnimPlayButtonY);
                            animatorSet1.start();

                            ObjectAnimator slideDownAnimTextView = ObjectAnimator.ofFloat(CountText, "translationY",0, (CountText.getTop() ));
                            ObjectAnimator scaleDownAnimTextViewX = ObjectAnimator.ofFloat(CountText, "scaleX", 1f, 0.5f);
                            ObjectAnimator scaleDownAnimTextViewY = ObjectAnimator.ofFloat(CountText, "scaleY", 1f, 0.5f);
                            AnimatorSet animatorSet = new AnimatorSet();
                            animatorSet.setDuration(300);
                            animatorSet.playTogether(slideDownAnimTextView,scaleDownAnimTextViewX,scaleDownAnimTextViewY);
                            animatorSet.start();
                            slideDownAnimTextView.addListener(new Animator.AnimatorListener() {
                                @Override
                                public void onAnimationStart(Animator animation) {

                                }

                                @Override
                                public void onAnimationEnd(Animator animation) {
                                    CountText.setTextColor(parseColor("#32FFFFFF"));
                                }

                                @Override
                                public void onAnimationCancel(Animator animator) {

                                }

                                @Override
                                public void onAnimationRepeat(Animator animator) {

                                }
                            });
                            play.setOnClickListener( resumeListener6 );
                        }
                    });

                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void shortPopupWindow3() {
        try {
// We need to get the instance of the LayoutInflater
            LayoutInflater inflater = (LayoutInflater) MainActivity.this
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View layout = inflater.inflate(R.layout.shortpop,
                    (ViewGroup) findViewById(R.id.element2));
            shortbreakpop = new PopupWindow(layout, 800, 590, true);
            shortbreakpop.showAtLocation(layout, Gravity.CENTER, 0, 0);

            TextView breaktime = (TextView) layout.findViewById(R.id.startpodo);
            breaktime.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    shortbreakpop.dismiss();
                    play.setVisibility(View.INVISIBLE);
                    ((GradientDrawable)re6.getBackground()).setStroke(1,Color.parseColor("#00000000"));
                    ((GradientDrawable)myView6.getBackground()).setColor(Color.argb(20,255,255,255));
                    myView7.setBackgroundColor(Color.WHITE);
                    ((GradientDrawable)re7.getBackground()).setStroke(1,Color.WHITE);
                     blinkanimation7= new AlphaAnimation(1, 0); // Change alpha from fully visible to invisible
                    blinkanimation7.setDuration(500); // duration - half a second
                    blinkanimation7.setInterpolator(new LinearInterpolator()); // do not alter animation rate
                    blinkanimation7.setRepeatCount(Settings.PomoProgress*60*2); // Repeat animation infinitely
                    blinkanimation7.setRepeatMode(Animation.REVERSE);
                    re7.startAnimation(blinkanimation7);
                    longBreaks.start();
                    if(Settings.PomoProgress==25) {
                        longBreaks.start();
                    }
                    else {
                        longBreaks.cancel();
                        longBreaks= new LongbreakClass(Settings.PomoProgress*60*1000,1000);
                        longBreaks.start();
                    }
                    CountText.setVisibility(View.VISIBLE);
                    CountText.startAnimation(fadeInAnimation);
                    textView.setText("[Pomodoro]");
                    CountText.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            blinkanimation7.cancel();
                            longBreaks.cancel();
                            play.setImageResource(R.mipmap.puse);
                            int marginTop = (int) getResources().getDimensionPixelSize(R.dimen.left_border_distance);
                            int marginRight = (int) getResources().getDimensionPixelSize(R.dimen.right_border_distance);
                            FrameLayout.LayoutParams toastTextLp = ( FrameLayout.LayoutParams)play.getLayoutParams();
                            toastTextLp.setMargins(marginRight, marginTop,0, 0);
                            play.setLayoutParams(toastTextLp);
                            play.setVisibility(View.VISIBLE);


                            ObjectAnimator slideDownAnimPlayButton = ObjectAnimator.ofFloat(play, "translationY", -(play.getTop()), 0);
                            ObjectAnimator slideDownAnimPlayButtonX = ObjectAnimator.ofFloat(play, "scaleX", 0.5f, 1f);
                            ObjectAnimator slideDownAnimPlayButtonY = ObjectAnimator.ofFloat(play, "scaleY", 0.5f, 1f);
                            AnimatorSet animatorSet1 = new AnimatorSet();
                            animatorSet1.setDuration(300);
                            animatorSet1.playTogether(slideDownAnimPlayButton,slideDownAnimPlayButtonX,slideDownAnimPlayButtonY);
                            animatorSet1.start();

                            ObjectAnimator slideDownAnimTextView = ObjectAnimator.ofFloat(CountText, "translationY",0, (CountText.getTop() ));
                            ObjectAnimator scaleDownAnimTextViewX = ObjectAnimator.ofFloat(CountText, "scaleX", 1f, 0.5f);
                            ObjectAnimator scaleDownAnimTextViewY = ObjectAnimator.ofFloat(CountText, "scaleY", 1f, 0.5f);
                            AnimatorSet animatorSet = new AnimatorSet();
                            animatorSet.setDuration(300);
                            animatorSet.playTogether(slideDownAnimTextView,scaleDownAnimTextViewX,scaleDownAnimTextViewY);
                            animatorSet.start();
                            slideDownAnimTextView.addListener(new Animator.AnimatorListener() {
                                @Override
                                public void onAnimationStart(Animator animation) {

                                }

                                @Override
                                public void onAnimationEnd(Animator animation) {
                                    CountText.setTextColor(parseColor("#32FFFFFF"));
                                }

                                @Override
                                public void onAnimationCancel(Animator animator) {

                                }

                                @Override
                                public void onAnimationRepeat(Animator animator) {

                                }
                            });
                            play.setOnClickListener( resumeListener7 );
                        }
                    });

                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void PodofinshPopupWindow() {
        try {
// We need to get the instance of the LayoutInflater
            LayoutInflater inflater = (LayoutInflater) MainActivity.this
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View layout = inflater.inflate(R.layout.startlongbreak,
                    (ViewGroup) findViewById(R.id.element4));
            podostartpop = new PopupWindow(layout, 800, 650, true);
            podostartpop.showAtLocation(layout, Gravity.CENTER, 0, 0);

            TextView breaktime = (TextView) layout.findViewById(R.id.podofinishTime);
            breaktime.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    podostartpop.dismiss();
                    play.setVisibility(View.INVISIBLE);
                    myView.setVisibility(View.INVISIBLE);
                    myView2.setVisibility(View.INVISIBLE);
                    myView3.setVisibility(View.INVISIBLE);
                    myView4.setVisibility(View.INVISIBLE);
                    myView5.setVisibility(View.INVISIBLE);
                    myView6.setVisibility(View.INVISIBLE);
                    myView7.setVisibility(View.INVISIBLE);
                    textView.setText("[Long Break]");
                    ((GradientDrawable)re7.getBackground()).setStroke(1,Color.parseColor("#00000000"));
                    myView7.setBackgroundColor(Color.argb(20,255,255,255));
                    if(Settings.PomoProgress==15) {
                        finishlongBreaks.start();
                    }
                    else {
                        finishlongBreaks.cancel();
                        finishlongBreaks= new finishLongbreakClass(Settings.LongProgress*60*1000,1000);
                        finishlongBreaks.start();
                    }
                    CountText.setVisibility(View.VISIBLE);
                    CountText.startAnimation(fadeInAnimation);
                    CountText.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {

                           finishlongBreaks.cancel();
                            play.setImageResource(R.mipmap.puse);
                            int marginTop = (int) getResources().getDimensionPixelSize(R.dimen.left_border_distance);
                            int marginRight = (int) getResources().getDimensionPixelSize(R.dimen.right_border_distance);
                            FrameLayout.LayoutParams toastTextLp = ( FrameLayout.LayoutParams)play.getLayoutParams();
                            toastTextLp.setMargins(marginRight, marginTop,0, 0);
                            play.setLayoutParams(toastTextLp);
                            play.setVisibility(View.VISIBLE);


                            ObjectAnimator slideDownAnimPlayButton = ObjectAnimator.ofFloat(play, "translationY", -(play.getTop()), 0);
                            ObjectAnimator slideDownAnimPlayButtonX = ObjectAnimator.ofFloat(play, "scaleX", 0.5f, 1f);
                            ObjectAnimator slideDownAnimPlayButtonY = ObjectAnimator.ofFloat(play, "scaleY", 0.5f, 1f);
                            AnimatorSet animatorSet1 = new AnimatorSet();
                            animatorSet1.setDuration(300);
                            animatorSet1.playTogether(slideDownAnimPlayButton,slideDownAnimPlayButtonX,slideDownAnimPlayButtonY);
                            animatorSet1.start();

                            ObjectAnimator slideDownAnimTextView = ObjectAnimator.ofFloat(CountText, "translationY",0, (CountText.getTop() ));
                            ObjectAnimator scaleDownAnimTextViewX = ObjectAnimator.ofFloat(CountText, "scaleX", 1f, 0.5f);
                            ObjectAnimator scaleDownAnimTextViewY = ObjectAnimator.ofFloat(CountText, "scaleY", 1f, 0.5f);
                            AnimatorSet animatorSet = new AnimatorSet();
                            animatorSet.setDuration(300);
                            animatorSet.playTogether(slideDownAnimTextView,scaleDownAnimTextViewX,scaleDownAnimTextViewY);
                            animatorSet.start();
                            slideDownAnimTextView.addListener(new Animator.AnimatorListener() {
                                @Override
                                public void onAnimationStart(Animator animation) {

                                }

                                @Override
                                public void onAnimationEnd(Animator animation) {
                                    CountText.setTextColor(parseColor("#32FFFFFF"));
                                }

                                @Override
                                public void onAnimationCancel(Animator animator) {

                                }

                                @Override
                                public void onAnimationRepeat(Animator animator) {

                                }
                            });
                            play.setOnClickListener( resumeListener8 );
                        }
                    });

                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void longPopupWindow() {
        try {
// We need to get the instance of the LayoutInflater
            LayoutInflater inflater = (LayoutInflater) MainActivity.this
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View layout = inflater.inflate(R.layout.longpop,
                    (ViewGroup) findViewById(R.id.element3));
            longbreakpop = new PopupWindow(layout, 800, 590, true);
            longbreakpop.showAtLocation(layout, Gravity.CENTER, 0, 0);

            TextView breaktime = (TextView) layout.findViewById(R.id.podolongstart2);
            breaktime.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    longbreakpop.dismiss();
                    play.setVisibility(View.INVISIBLE);
                    myView.setVisibility(View.VISIBLE);
                    myView2.setVisibility(View.VISIBLE);
                    myView3.setVisibility(View.VISIBLE);
                    myView4.setVisibility(View.VISIBLE);
                    myView5.setVisibility(View.VISIBLE);
                    myView6.setVisibility(View.VISIBLE);
                    myView7.setVisibility(View.VISIBLE);
                    textView.setText("[Pomodoro]");
                    linearLayout.setVisibility(View.VISIBLE);
                    count++;
                    String s=String.valueOf(count);
                    loops.setText(s);
                    myView.setBackgroundColor(Color.WHITE);
                    ((GradientDrawable)re1.getBackground()).setStroke(1,Color.WHITE);
                    final AlphaAnimation blinkanimation= new AlphaAnimation(1, 0); // Change alpha from fully visible to invisible
                    blinkanimation.setDuration(500); // duration - half a second
                    blinkanimation.setInterpolator(new LinearInterpolator()); // do not alter animation rate
                    blinkanimation.setRepeatCount(Settings.PomoProgress*60*2); // Repeat animation infinitely
                    blinkanimation.setRepeatMode(Animation.REVERSE);
                    re1.startAnimation(blinkanimation);
                    if(Settings.PomoProgress==25) {
                        timer.start();
                    }
                    else {
                        timer.cancel();
                        timer = new CounterClass(Settings.PomoProgress*60 * 1000, 1000);
                        timer.start();
                    }
                    CountText.setVisibility(View.VISIBLE);
                    CountText.startAnimation(fadeInAnimation);
                    CountText.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            blinkanimation.cancel();

                            timer.cancel();

                            play.setImageResource(R.mipmap.puse);
                            int marginTop = (int) getResources().getDimensionPixelSize(R.dimen.left_border_distance);
                            int marginRight = (int) getResources().getDimensionPixelSize(R.dimen.right_border_distance);
                            FrameLayout.LayoutParams toastTextLp = ( FrameLayout.LayoutParams)play.getLayoutParams();
                            toastTextLp.setMargins(marginRight, marginTop,0, 0);
                            play.setLayoutParams(toastTextLp);
                            play.setVisibility(View.VISIBLE);


                            ObjectAnimator slideDownAnimPlayButton = ObjectAnimator.ofFloat(play, "translationY", -(play.getTop()), 0);
                            ObjectAnimator slideDownAnimPlayButtonX = ObjectAnimator.ofFloat(play, "scaleX", 0.5f, 1f);
                            ObjectAnimator slideDownAnimPlayButtonY = ObjectAnimator.ofFloat(play, "scaleY", 0.5f, 1f);
                            AnimatorSet animatorSet1 = new AnimatorSet();
                            animatorSet1.setDuration(300);
                            animatorSet1.playTogether(slideDownAnimPlayButton,slideDownAnimPlayButtonX,slideDownAnimPlayButtonY);
                            animatorSet1.start();

                            ObjectAnimator slideDownAnimTextView = ObjectAnimator.ofFloat(CountText, "translationY",0, (CountText.getTop() ));
                            ObjectAnimator scaleDownAnimTextViewX = ObjectAnimator.ofFloat(CountText, "scaleX", 1f, 0.5f);
                            ObjectAnimator scaleDownAnimTextViewY = ObjectAnimator.ofFloat(CountText, "scaleY", 1f, 0.5f);
                            AnimatorSet animatorSet = new AnimatorSet();
                            animatorSet.setDuration(300);
                            animatorSet.playTogether(slideDownAnimTextView,scaleDownAnimTextViewX,scaleDownAnimTextViewY);
                            animatorSet.start();
                            slideDownAnimTextView.addListener(new Animator.AnimatorListener() {
                                @Override
                                public void onAnimationStart(Animator animation) {

                                }

                                @Override
                                public void onAnimationEnd(Animator animation) {
                                    CountText.setTextColor(parseColor("#32FFFFFF"));
                                }

                                @Override
                                public void onAnimationCancel(Animator animator) {

                                }

                                @Override
                                public void onAnimationRepeat(Animator animator) {

                                }
                            });
                            play.setOnClickListener( resumeListener );
                        }
                    });

                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
