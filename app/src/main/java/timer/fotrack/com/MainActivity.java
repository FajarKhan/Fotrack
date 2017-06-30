package timer.fotrack.com;

import android.animation.Animator;

import android.animation.AnimatorSet;
import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.CountDownTimer;

import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import android.os.Bundle;
import android.support.v7.app.NotificationCompat;

import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;

import android.view.View;

import android.view.ViewGroup;

import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;

import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.hanks.htextview.HTextView;
import com.hanks.htextview.HTextViewType;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import static android.R.attr.x;
import static android.R.id.list;
import static android.content.Context.NOTIFICATION_SERVICE;
import static android.graphics.Color.parseColor;
import static android.graphics.PorterDuff.Mode.ADD;
import static timer.fotrack.com.R.id.vibr;


@TargetApi(Build.VERSION_CODES.GINGERBREAD)
@SuppressLint("NewApi")

public class MainActivity extends FragmentActivity {

    private ImageButton play;
    private View CirAnimation,CirAnimation2,CirAnimation3;
    private HTextView CountText;
    SectionsPagerAdapter mSectionsPagerAdapter;
    public static ViewPager mViewPager;
    Integer[] colors = null;
  SharedPreferences pref,shortTime,longTime,TotelPomo,TotelShort,TotelLong,TotelWork,TotelPomod,Vibrstate,Screenstate,Product;
    ArgbEvaluator argbEvaluator = new ArgbEvaluator();
    CounterClass timer;
    PodobreakClass2 podotimer2;
    PodobreakClass3 podotimer3;
     ShortbreakClass shortBreaks;
     ShortbreakClass2 shortBreaks2;
    ShortbreakClass3  shortBreaks3;
    public TextView Reset,Skip;
     public  static int PomoStoreSec,PomoStoreMin,ShortStoreSec,ShortStoreMin,LongStoreSec,LongStoreMin,Breaktime,flag=0,Days=1,worksmonth=0;
    LongbreakClass longBreaks;
    finishLongbreakClass finishlongBreaks;
    long millis;
    private ImageButton settings,graph;
    Animation fadeInAnimation;
    private TextView textView;
    int count=0;
    EditText Keynote;
    private LinearLayout linearLayout;
    private View myView,myView2,myView3,myView4,myView5,myView6,myView7;
    private LinearLayout re2,re3,re4,re5,re6,re7;
    private LinearLayout re1;
    String[] str = new String[30];
    String[] str2 = new String[30];
    String Note;
    int flag2;

    int i=0,j=0;
    private  AlphaAnimation blinkanimation1,blinkanimation2,blinkanimation3,blinkanimation4,blinkanimation5,blinkanimation6,blinkanimation7;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Product = getApplicationContext().getSharedPreferences("MyPref12", 0);
        flag2=Product.getInt("product", 0);

        if(flag2==0)
        {
            Intent intent=new Intent(MainActivity.this,ProductTour.class);
            startActivity(intent);
            flag2=1;
        }



        str[0] = "The only way to do great work is to love what you do.\n" +
                "-Steve Jobs";
        str[1] = "The way to get started is to quit talking and begin doing.\n" +
                " –Walt ";
        str[2] = "Disney  Real integrity is doing the right thing, knowing that nobody’s going to know whether you did it or not. \n" +
                "-Oprah Winfrey  Infinite";
        str[3] = "“Simplicity boils down to two steps: Identify the essential. Eliminate the rest.”\n" +
                "–Leo Babauta";
        str[4] = "striving to be the best is man’s duty; It is its own reward. Everything else is in god’s hands.\n" +
                "-Mahatma Gandhi";
        str[5] = " It’s not that I’m so smart, it’s just that I stay with problems longer.\n" +
                "-Albert Einstein";
        str[6] = "Work gives you meaning and purpose and life is empty without it. \n" +
                "-Stephen hawking";
        str[7] = " When you combine ignorance and leverage, you get some pretty interesting results.\n" +
                "-Warren Buffett";
        str[8] = " You were born to win, but to be a winner, you must plan to win, prepare to win, and expect to win.\n" +
                "-Zig Ziglar";
        str[9] = "Sometimes, things may not go your way, but the effort should be there every single night.\n" +
                "-Michael Jordan";
        str[10] = " How poor are they that have not patience! What wound did ever heal but by degrees?\n" +
                "-William Shakespeare";
        str[11] = "The only thing to do with good advice is to pass it on. It is never of any use to oneself. \n" +
                "-Oscar Wilde";
        str[12] = " He who is not courageous enough to take risks will accomplish nothing in life.\n" +
                "-Muhammad Ali";
        str[13] = "Plans are nothing; planning is everything.\n" +
                "-Dwight D. Eisenhower";
        str[14] = "There are risks and costs to action. But they are far less than the long range risks of comfortable inaction.\n" +
                "-John F. Kennedy ";
        str[15] = "If you’re going through hell, keep going.\n" +
                "-Winston Churchill";
        str[16] = " It is better to keep your mouth closed and let people think you are a fool than to open it and remove all doubt.\n" +
                "-Mark Twain";
        str[17] = "You can have everything in life you want, if you will just help other people get what they want.\n" +
                "-Zig Ziglar";
        str[18] = " Learning never exhausts the mind. \n" +
                "-Leonardo da Vinci";
        str[19] = " Good leadership consists of showing average people how to do the work of superior people.\n" +
                "-John D. Rockefeller ";
        str[20] = "Our incomes are like our shoes; if too small, they gall and pinch us; but if too large, they cause us to stumble and to trip. \n" +
                "-John Locke";
        str[21] = " Love and work are the cornerstones of our humanness.\n" +
                "-Sigmund Freud ";
        str[22] = "Obstacles are those frightful things you see when you take your eyes off your goal.\n" +
                "-Henry Ford";
        str[23] = "Gentleness doesn’t get work done unless you happen to be a hen laying eggs.\n" +
                "-Coco Chanel";
        str[24] = "Never neglect details. When everyone’s mind is dulled or distracted the leader must be doubly vigilant.\n" +
                "-Colin Powell ";
        str[25] = "Remember that failure is an event, not a person.\n" +
                "-Zig Ziglar";
        str[26] = "Discussion is an exchange of knowledge an argument an exchange of ignorance.\n" +
                "-Robert Quillen";
        str[27] = "Luck is not chance, it’s toil; Fortune’s expensive smile is earned.\n" +
                "-Emily Dickinson";
        str[28] = "“Create with the heart; build with the mind.”\n" +
                "-Criss Jami, Killosophy";
        str[29] = "“Concentrate all your thoughts upon the work in hand. The Sun's rays do not burn until brought to a focus”\n" +
                "-Alexander Graham Bell";

        str2[0] = "“Your calm mind is the ultimate weapon against your challenges. So relax.”\n" +
                "-Bryant McGill";
        str2[1] = "Peace cannot be kept by force; it can only be achieved by understanding\n" +
                " –Albert Einstein ";
        str2[2] = " “You can discover more about a person in an hour of play than in a year of conversation.” \n" +
                "-Plato";
        str2[3] = "For every minute you remain angry, you give up sixty seconds of peace of mind.\n" +
                "– Ralph Waldo Emerson";
        str2[4] = "Success is peace of mind which is a direct result of self-satisfaction in knowing you did your best to become the best you are capable of becoming. \n" +
                "-John Wooden";
        str2[5] = " You'll never find peace of mind until you listen to your heart.\n" +
                "-George Michael";
        str2[6] = " Peace of mind for five minutes, that's what I crave. \n" +
                "-Alanis Morissette ";
        str2[7] = " Do not overrate what you have received, nor envy others. He who envies others does not obtain peace of mind.\n" +
                "-Buddha";
        str2[8] = "Work and live to serve others, to leave the world a little better than you found it and garner for yourself as much peace of mind as you can. This is happiness.\n" +
                "-David Sarnoff";
        str2[9] = "I can have peace of mind only when I forgive rather than judge. \n" +
                "-Gerald Jampolsky";
        str2[10] = " Money cannot buy peace of mind. It cannot heal ruptured relationships, or build meaning into a life that has none. \n" +
                "-Richard M. DeVos";
        str2[11] = "Peace of mind comes from not wanting to change others. \n" +
                "-Gerald Jampolsky";
        str2[12] = " When you've seen beyond yourself, then you may find, peace of mind is waiting there.\n" +
                "-George Harrison";
        str2[13] = "You should feel beautiful and you should feel safe. What you surround yourself with should bring you peace of mind and peace of spirit. \n" +
                "-Stacy London";
        str2[14] = "“There is virtue in work and there is virtue in rest. Use both and overlook neither.”\n" +
                "-Alan Cohen ";
        str2[15] = "“Whenever you find yourself on the side of the majority, it is time to pause and reflect.”\n" +
                "-Mark Twain";
        str2[16] = " Rest and be thankful.\n" +
                "-William Wadsworth";
        str2[17] = "The measure of success is happiness and peace of mind. \n" +
                "-Bobby Davro";
        str2[18] = " Negative emotions like hatred destroy our peace of mind. \n" +
                "-Matthieu Ricard ";
        str2[19] = " To discover your mission and put it into action - instead of worrying on the sidelines - is to find peace of mind and a heart full of love.\n" +
                "-Scilla Elworthy ";
        str2[20] = " Piety is not a goal but a means to attain through the purest peace of mind the highest culture. \n" +
                "-Johann Wolfgang von Goethe";
        str2[21] = " Other people do not have to change for us to experience peace of mind.\n" +
                "- Gerald Jampolsky ";
        str2[22] = "“All that is important comes in quietness and waiting.” \n" +
                "-Patrick Lindsay";
        str2[23] = "“A little nonsense now and then, is cherished by the wisest men.”\n" +
                "-Roald Dahl";
        str2[24] = "Good, better, best. Never let it rest. Till your good is better and your better is best.\n" +
                "- St. Jerome ";
        str2[25] = "Do the right thing. It will gratify some people and astonish the rest.\n" +
                "-Mark Twain";
        str2[26] = "Discussion is an exchange of knowledge an argument an exchange of ignorance.\n" +
                "-Robert Quillen";
        str2[27] = "“Find a place you trust and then try trusting it for a while.”\n" +
                "-Corita Kent";
        str2[28] = "“Nothing is a mistake. There’s no win and no fail. There’s only make.”\n" +
                "-Corita Kent";
        str2[29] = "“Concentrate all your thoughts upon the work in hand. The Sun's rays do not burn until brought to a focus”\n" +
                "-Alexander Graham Bell";


        Reset=(TextView)findViewById(R.id.reset);
        Skip=(TextView)findViewById(R.id.skip);

        Vibrstate = getApplicationContext().getSharedPreferences("MyPref10", 0);
        Settings.vibrationState=Vibrstate.getInt("Vib", 1);

        Screenstate = getApplicationContext().getSharedPreferences("MyPref11", 0);
        Settings.ScreenState=Screenstate.getInt("scr", 0);

        fadeInAnimation = AnimationUtils.loadAnimation(this, R.anim.fadein);
        mViewPager = (ViewPager) findViewById(R.id.pager);

        pref = getApplicationContext().getSharedPreferences("MyPref", 0);
        Settings.PomoProgress=pref.getInt("pomo", 25);

        shortTime = getApplicationContext().getSharedPreferences("MyPref2", 0);
        Settings.ShortProgress=shortTime.getInt("short", 5);

        longTime = getApplicationContext().getSharedPreferences("MyPref3", 0);
        Settings.LongProgress=longTime.getInt("long", 15);


        int currentDay = Calendar.getInstance().get(Calendar.DAY_OF_YEAR);

        TotelPomod = getApplicationContext().getSharedPreferences("MyPref8", 0);
        flag=flag+TotelPomod.getInt("TotemPomod", 0);

        TotelPomo = getApplicationContext().getSharedPreferences("MyPref4", 0);
        PomoStoreSec=PomoStoreSec+TotelPomo.getInt("pomoTotel", 0);

        TotelWork = getApplicationContext().getSharedPreferences("MyPref7", 0);
        worksmonth = worksmonth+TotelWork.getInt("Status_size", 0);


        int lastRecordedDay = TotelPomo.getInt("lastRecordedDay", 0);
        if (currentDay != lastRecordedDay) {
            Days=Days+1;
            worksmonth=worksmonth+PomoStoreSec;
            PomoStoreSec=0;
        }







        TotelShort = getApplicationContext().getSharedPreferences("MyPref5", 0);
        ShortStoreSec=ShortStoreSec+TotelShort.getInt("shortTotel", 0);

        TotelLong = getApplicationContext().getSharedPreferences("MyPref6", 0);
        LongStoreSec=LongStoreSec+TotelLong.getInt("longTotel", 0);


        timer = new CounterClass(Settings.PomoProgress*60 * 1000, 1000);
        podotimer2 = new PodobreakClass2(Settings.PomoProgress*60*1000,1000);
        podotimer3 = new PodobreakClass3(Settings.PomoProgress*60*1000,1000);
        shortBreaks= new ShortbreakClass(Settings.ShortProgress*60*1000,1000);
        shortBreaks2= new ShortbreakClass2(Settings.ShortProgress*60*1000,1000);
        shortBreaks3= new ShortbreakClass3(Settings.ShortProgress*60*1000,1000);
        longBreaks= new LongbreakClass(Settings.PomoProgress*60*1000,1000);
        finishlongBreaks= new finishLongbreakClass(Settings.LongProgress*60*1000,1000);

        CirAnimation=(View)findViewById(R.id.ciranimation);
        CirAnimation2=(View)findViewById(R.id.ciranimation2);
        CirAnimation3=(View)findViewById(R.id.ciranimation3);


        Animation animation = AnimationUtils.loadAnimation(getApplication(), R.anim.ciranim);
        CirAnimation.startAnimation(animation);

        Animation animation2 = AnimationUtils.loadAnimation(getApplication(), R.anim.ciranim2);
        CirAnimation2.startAnimation(animation2);

        Animation animation3 = AnimationUtils.loadAnimation(getApplication(), R.anim.ciranim3);
        CirAnimation3.startAnimation(animation3);


        CirAnimation.setVisibility(View.INVISIBLE);
        CirAnimation2.setVisibility(View.INVISIBLE);
        CirAnimation3.setVisibility(View.INVISIBLE);

        Keynote=(EditText)findViewById(R.id.note);
        Note=Keynote.getText().toString();
        Keynote.setText(Note);




        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());
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
        CountText=(HTextView)findViewById(R.id.countText);
        CountText.setAnimateType(HTextViewType.EVAPORATE);
        settings=(ImageButton)findViewById(R.id.settings);
        settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplication(),Settings.class);
                startActivity(intent);
            }
        });
        graph=(ImageButton)findViewById(R.id.graph);
        graph.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplication(),DynamicGraphActivity.class);
                startActivity(intent);
            }
        });


        linearLayout=(LinearLayout)findViewById(R.id.linear);
        play=(ImageButton)findViewById(R.id.play);

        play.setOnClickListener( startListener );

    }

    @Override
    protected void onPause() {

        flag = worksmonth+PomoStoreSec;

        Product= getApplicationContext().getSharedPreferences("MyPref12", 0);
        SharedPreferences.Editor editor12 = Product.edit();
        editor12.putInt("product", flag2);
        editor12.apply();


        int currentDay = Calendar.getInstance().get(Calendar.DAY_OF_YEAR);

        Vibrstate= getApplicationContext().getSharedPreferences("MyPref10", 0);
        SharedPreferences.Editor editor10 = Vibrstate.edit();
        editor10.putInt("Vib", Settings.vibrationState);
        editor10.apply();

        Screenstate= getApplicationContext().getSharedPreferences("MyPref11", 0);
        SharedPreferences.Editor editor11 = Screenstate.edit();
        editor11.putInt("scr", Settings.ScreenState);
        editor11.apply();


        TotelPomo= getApplicationContext().getSharedPreferences("MyPref4", 0);
        SharedPreferences.Editor editor = TotelPomo.edit();
        editor.putInt("pomoTotel", PomoStoreSec);
        editor.putInt("lastRecordedDay", currentDay);
        editor.apply();

        TotelShort= getApplicationContext().getSharedPreferences("MyPref5", 0);
        SharedPreferences.Editor editor2 = TotelShort.edit();
        editor2.putInt("shortTotel", ShortStoreSec);
        editor2.apply();

        TotelLong= getApplicationContext().getSharedPreferences("MyPref6", 0);
        SharedPreferences.Editor editor3 = TotelLong.edit();
        editor3.putInt("longTotel", LongStoreSec);
        editor3.apply();

        TotelPomod= getApplicationContext().getSharedPreferences("MyPref8", 0);
        SharedPreferences.Editor editor5 = TotelPomod.edit();
        editor5.putInt("TotemPomod", flag);
        editor5.apply();

        TotelWork= getApplicationContext().getSharedPreferences("MyPref7", 0);
        SharedPreferences.Editor editor4 = TotelWork.edit();
        editor4.putInt("Status_size", worksmonth);
        editor4.apply();

        super.onPause();
    }

    @Override
    protected void onResume() {
        pref = getApplicationContext().getSharedPreferences("MyPref", 0);
        SharedPreferences.Editor editor = pref.edit();
        editor.putInt("pomo", Settings.PomoProgress);
        editor.apply();

        shortTime = getApplicationContext().getSharedPreferences("MyPref2", 0);
        SharedPreferences.Editor editor2 = shortTime.edit();
        editor2.putInt("short", Settings.ShortProgress);
        editor2.apply();

        longTime = getApplicationContext().getSharedPreferences("MyPref3", 0);
        SharedPreferences.Editor editor3 = longTime.edit();
        editor3.putInt("long", Settings.LongProgress);
        editor3.apply();

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

            Skip.setVisibility(View.VISIBLE);
            Reset.setVisibility(View.VISIBLE);



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
            Skip.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    timer.cancel();
                    blinkanimation1.cancel();
                    initiatePopupWindow();
                }
            });

           Reset.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View view1) {
                   timer.cancel();
                   blinkanimation1.cancel();

                   ((GradientDrawable)re2.getBackground()).setStroke(1,Color.parseColor("#00000000"));
                   ((GradientDrawable)myView2.getBackground()).setColor(Color.parseColor("#32ffffff"));
                   ((GradientDrawable)re3.getBackground()).setStroke(1,Color.parseColor("#00000000"));
                   myView3.setBackgroundColor(Color.parseColor("#32ffffff"));
                   ((GradientDrawable)re4.getBackground()).setStroke(1,Color.parseColor("#00000000"));
                   ((GradientDrawable)myView4.getBackground()).setColor(Color.parseColor("#32ffffff"));
                   ((GradientDrawable)re5.getBackground()).setStroke(1,Color.parseColor("#00000000"));
                   myView5.setBackgroundColor(Color.parseColor("#32ffffff"));
                   ((GradientDrawable)re6.getBackground()).setStroke(1,Color.parseColor("#00000000"));
                   ((GradientDrawable)myView6.getBackground()).setColor(Color.parseColor("#32ffffff"));
                   ((GradientDrawable)re7.getBackground()).setStroke(1,Color.parseColor("#00000000"));
                   myView7.setBackgroundColor(Color.parseColor("#32ffffff"));

                   myView.setBackgroundColor(Color.WHITE);
                   ((GradientDrawable)re1.getBackground()).setStroke(1,Color.WHITE);
                   blinkanimation1= new AlphaAnimation(1, 0); // Change alpha from fully visible to invisible
                   blinkanimation1.setDuration(500); // duration - half a second
                   blinkanimation1.setInterpolator(new LinearInterpolator()); // do not alter animation rate
                   blinkanimation1.setRepeatCount(Settings.PomoProgress*60*2); // Repeat animation infinitely
                   blinkanimation1.setRepeatMode(Animation.REVERSE);

                   re1.startAnimation(blinkanimation1);
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

                   Skip.setOnClickListener(new View.OnClickListener() {
                       @Override
                       public void onClick(View view) {
                           timer.cancel();
                           blinkanimation1.cancel();
                           initiatePopupWindow();
                       }
                   });
                   Reset.setOnClickListener(this);

                   CountText.setOnClickListener( stopListener );

               }
           });
            CountText.setOnClickListener( stopListener );
        }
    };


    View.OnClickListener stopListener = new View.OnClickListener() {

        public void onClick( View v ){

            Reset.setVisibility(View.INVISIBLE);
            Skip.setVisibility(View.INVISIBLE);

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
            Reset.setVisibility(View.VISIBLE);
            Skip.setVisibility(View.VISIBLE);

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

                    Reset.setVisibility(View.INVISIBLE);
                    Skip.setVisibility(View.INVISIBLE);

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
            Reset.setVisibility(View.VISIBLE);
            Skip.setVisibility(View.VISIBLE);
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

                    Reset.setVisibility(View.INVISIBLE);
                    Skip.setVisibility(View.INVISIBLE);

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
            Reset.setVisibility(View.VISIBLE);
            Skip.setVisibility(View.VISIBLE);
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

                    Reset.setVisibility(View.INVISIBLE);
                    Skip.setVisibility(View.INVISIBLE);
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
            Reset.setVisibility(View.VISIBLE);
            Skip.setVisibility(View.VISIBLE);
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

                    Reset.setVisibility(View.INVISIBLE);
                    Skip.setVisibility(View.INVISIBLE);

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
            Reset.setVisibility(View.VISIBLE);
            Skip.setVisibility(View.VISIBLE);
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
                    Reset.setVisibility(View.INVISIBLE);
                    Skip.setVisibility(View.INVISIBLE);
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
            Reset.setVisibility(View.VISIBLE);
            Skip.setVisibility(View.VISIBLE);
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

                    Reset.setVisibility(View.INVISIBLE);
                    Skip.setVisibility(View.INVISIBLE);


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
            Reset.setVisibility(View.VISIBLE);
            Skip.setVisibility(View.VISIBLE);
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

                    Reset.setVisibility(View.INVISIBLE);
                    Skip.setVisibility(View.INVISIBLE);


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
            Reset.setVisibility(View.VISIBLE);
            Skip.setVisibility(View.VISIBLE);
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

                    Reset.setVisibility(View.INVISIBLE);
                    Skip.setVisibility(View.INVISIBLE);



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
            PomoStoreSec=PomoStoreSec+1;
            millis = millisUntilFinished;
            String hms = String.format("%02d:%02d",
                    TimeUnit.MILLISECONDS.toMinutes(millis) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(millis)),
                    TimeUnit.MILLISECONDS.toSeconds(millis) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millis)));
            System.out.println(hms);

            CountText.animateText(hms);
        }

        @Override
        public void onFinish() {
            PomoStoreSec=PomoStoreSec+1;
            initiatePopupWindow();
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
                    .setStyle(new NotificationCompat.BigTextStyle().bigText(str[i]))
                    .build();
            i++;
            if (i==29)
            {
                i=0;
            }

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
            ShortStoreSec=ShortStoreSec+1;
            millis = millisUntilFinished;
           String hms = String.format("%02d:%02d",
                    TimeUnit.MILLISECONDS.toMinutes(millis) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(millis)),
                    TimeUnit.MILLISECONDS.toSeconds(millis) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millis)));
            System.out.println(hms);

            CountText.animateText(hms);
        }

        @Override
        public void onFinish() {
            ShortStoreSec=ShortStoreSec+1;
            shortPopupWindow();
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
                    .setStyle(new NotificationCompat.BigTextStyle().bigText(str2[j]))
                    .build();

            j++;
            if (j==29)
            {
                j=0;
            }

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
            PomoStoreSec=PomoStoreSec+1;
            millis = millisUntilFinished;
            String hms = String.format("%02d:%02d",
                    TimeUnit.MILLISECONDS.toMinutes(millis) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(millis)),
                    TimeUnit.MILLISECONDS.toSeconds(millis) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millis)));
            System.out.println(hms);

            CountText.animateText(hms);
        }

        @Override
        public void onFinish() {
            PomoStoreSec=PomoStoreSec+1;
            initiatePopupWindow2();
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
                    .setStyle(new NotificationCompat.BigTextStyle().bigText(str[i]))
                    .build();
            i++;
            if (i==29)
            {
                i=0;
            }

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
            ShortStoreSec=ShortStoreSec+1;
            millis = millisUntilFinished;
            String hms = String.format("%02d:%02d",
                    TimeUnit.MILLISECONDS.toMinutes(millis) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(millis)),
                    TimeUnit.MILLISECONDS.toSeconds(millis) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millis)));
            System.out.println(hms);

            CountText.animateText(hms);
        }

        @Override
        public void onFinish() {
            ShortStoreSec=ShortStoreSec+1;
            shortPopupWindow2();
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
                    .setStyle(new NotificationCompat.BigTextStyle().bigText(str2[j]))
                    .build();

            j++;
            if (j==29)
            {
                j=0;
            }

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
            PomoStoreSec=PomoStoreSec+1;
            millis = millisUntilFinished;
            String hms = String.format("%02d:%02d",
                    TimeUnit.MILLISECONDS.toMinutes(millis) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(millis)),
                    TimeUnit.MILLISECONDS.toSeconds(millis) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millis)));
            System.out.println(hms);

           CountText.animateText(hms);
        }

        @Override
        public void onFinish() {
            PomoStoreSec=PomoStoreSec+1;
            initiatePopupWindow3();
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
                    .setStyle(new NotificationCompat.BigTextStyle().bigText(str[i]))
                    .build();

            i++;
            if (i==29)
            {
                i=0;
            }

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
            ShortStoreSec=ShortStoreSec+1;
            millis = millisUntilFinished;
            String hms = String.format("%02d:%02d",
                    TimeUnit.MILLISECONDS.toMinutes(millis) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(millis)),
                    TimeUnit.MILLISECONDS.toSeconds(millis) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millis)));
            System.out.println(hms);

            CountText.animateText(hms);
        }

        @Override
        public void onFinish() {
            ShortStoreSec=ShortStoreSec+1;
            shortPopupWindow3();
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
                    .setStyle(new NotificationCompat.BigTextStyle().bigText(str2[j]))
                    .build();
            j++;
            if (j==29)
            {
                j=0;
            }

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
            PomoStoreSec=PomoStoreSec+1;
            millis = millisUntilFinished;
            String hms = String.format("%02d:%02d",
                    TimeUnit.MILLISECONDS.toMinutes(millis) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(millis)),
                    TimeUnit.MILLISECONDS.toSeconds(millis) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millis)));
            System.out.println(hms);

            CountText.animateText(hms);
        }

        @Override
        public void onFinish() {
            PomoStoreSec=PomoStoreSec+1;
            PodofinshPopupWindow();

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
                    .setStyle(new NotificationCompat.BigTextStyle().bigText(str[i]))
                    .build();
            i++;
            if (i==29)
            {
                i=0;
            }

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
            LongStoreSec=LongStoreSec+1;
            millis = millisUntilFinished;
            String hms = String.format("%02d:%02d",
                    TimeUnit.MILLISECONDS.toMinutes(millis) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(millis)),
                    TimeUnit.MILLISECONDS.toSeconds(millis) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millis)));
            System.out.println(hms);

            CountText.animateText(hms);
        }

        @Override
        public void onFinish() {
            LongStoreSec=LongStoreSec+1;
            longPopupWindow();
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
                    .setStyle(new NotificationCompat.BigTextStyle().bigText(str2[j]))
                    .build();

            j++;
            if (j==29)
            {
                j=0;
            }

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
                TextView Pomo=(TextView)layout.findViewById(R.id.PomoText);
                Pomo.setText(str[i]);
                i++;
                if (i==29)
                {
                    i=0;
                }
                pwindo = new PopupWindow(layout, 800, 650, true);
                pwindo.showAtLocation(layout, Gravity.CENTER, 0, 0);

                TextView breaktime = (TextView) layout.findViewById(R.id.breakTime);
                breaktime.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        pwindo.dismiss();
                        play.setVisibility(View.INVISIBLE);
                        ((GradientDrawable) re1.getBackground()).setStroke(1, Color.parseColor("#00000000"));
                        myView.setBackgroundColor(Color.WHITE);
                        ((GradientDrawable) myView2.getBackground()).setColor(Color.WHITE);
                        ((GradientDrawable) re2.getBackground()).setStroke(2, Color.WHITE);
                        blinkanimation2 = new AlphaAnimation(1, 0); // Change alpha from fully visible to invisible
                        blinkanimation2.setDuration(500); // duration - half a second
                        blinkanimation2.setInterpolator(new LinearInterpolator()); // do not alter animation rate
                        blinkanimation2.setRepeatCount(Settings.ShortProgress * 60 * 2); // Repeat animation infinitely
                        blinkanimation2.setRepeatMode(Animation.REVERSE);
                        re2.startAnimation(blinkanimation2);
                        CountText.setVisibility(View.VISIBLE);
                        CountText.startAnimation(fadeInAnimation);
                        if (Settings.ShortProgress == 5) {
                            shortBreaks.start();
                        } else {
                            shortBreaks.cancel();
                            shortBreaks = new ShortbreakClass(Settings.ShortProgress * 60 * 1000, 1000);
                            shortBreaks.start();
                        }
                        textView.setText("[Short Break]");
                        Skip.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                shortBreaks.cancel();
                                blinkanimation2.cancel();
                                shortPopupWindow();
                            }
                        });

                        Reset.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view1) {
                                shortBreaks.cancel();
                                blinkanimation2.cancel();

                                ((GradientDrawable)re2.getBackground()).setStroke(1,Color.parseColor("#00000000"));
                                ((GradientDrawable)myView2.getBackground()).setColor(Color.parseColor("#32ffffff"));
                                ((GradientDrawable)re3.getBackground()).setStroke(1,Color.parseColor("#00000000"));
                                myView3.setBackgroundColor(Color.parseColor("#32ffffff"));
                                ((GradientDrawable)re4.getBackground()).setStroke(1,Color.parseColor("#00000000"));
                                ((GradientDrawable)myView4.getBackground()).setColor(Color.parseColor("#32ffffff"));
                                ((GradientDrawable)re5.getBackground()).setStroke(1,Color.parseColor("#00000000"));
                                myView5.setBackgroundColor(Color.parseColor("#32ffffff"));
                                ((GradientDrawable)re6.getBackground()).setStroke(1,Color.parseColor("#00000000"));
                                ((GradientDrawable)myView6.getBackground()).setColor(Color.parseColor("#32ffffff"));
                                ((GradientDrawable)re7.getBackground()).setStroke(1,Color.parseColor("#00000000"));
                                myView7.setBackgroundColor(Color.parseColor("#32ffffff"));


                                myView.setBackgroundColor(Color.WHITE);
                                ((GradientDrawable)re1.getBackground()).setStroke(1,Color.WHITE);
                                blinkanimation1= new AlphaAnimation(1, 0); // Change alpha from fully visible to invisible
                                blinkanimation1.setDuration(500); // duration - half a second
                                blinkanimation1.setInterpolator(new LinearInterpolator()); // do not alter animation rate
                                blinkanimation1.setRepeatCount(Settings.PomoProgress*60*2); // Repeat animation infinitely
                                blinkanimation1.setRepeatMode(Animation.REVERSE);

                                re1.startAnimation(blinkanimation1);
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

                                Skip.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        timer.cancel();
                                        blinkanimation1.cancel();
                                        initiatePopupWindow();
                                    }
                                });
                                Reset.setOnClickListener(this);

                                CountText.setOnClickListener( stopListener );

                            }
                        });
                        CountText.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Reset.setVisibility(View.INVISIBLE);
                                Skip.setVisibility(View.INVISIBLE);
                                blinkanimation2.cancel();
                                shortBreaks.cancel();

                                play.setImageResource(R.mipmap.puse);
                                int marginTop = (int) getResources().getDimensionPixelSize(R.dimen.left_border_distance);
                                int marginRight = (int) getResources().getDimensionPixelSize(R.dimen.right_border_distance);
                                FrameLayout.LayoutParams toastTextLp = (FrameLayout.LayoutParams) play.getLayoutParams();
                                toastTextLp.setMargins(marginRight, marginTop, 0, 0);
                                play.setLayoutParams(toastTextLp);
                                play.setVisibility(View.VISIBLE);


                                ObjectAnimator slideDownAnimPlayButton = ObjectAnimator.ofFloat(play, "translationY", -(play.getTop()), 0);
                                ObjectAnimator slideDownAnimPlayButtonX = ObjectAnimator.ofFloat(play, "scaleX", 0.5f, 1f);
                                ObjectAnimator slideDownAnimPlayButtonY = ObjectAnimator.ofFloat(play, "scaleY", 0.5f, 1f);
                                AnimatorSet animatorSet1 = new AnimatorSet();
                                animatorSet1.setDuration(300);
                                animatorSet1.playTogether(slideDownAnimPlayButton, slideDownAnimPlayButtonX, slideDownAnimPlayButtonY);
                                animatorSet1.start();

                                ObjectAnimator slideDownAnimTextView = ObjectAnimator.ofFloat(CountText, "translationY", 0, (CountText.getTop()));
                                ObjectAnimator scaleDownAnimTextViewX = ObjectAnimator.ofFloat(CountText, "scaleX", 1f, 0.5f);
                                ObjectAnimator scaleDownAnimTextViewY = ObjectAnimator.ofFloat(CountText, "scaleY", 1f, 0.5f);
                                AnimatorSet animatorSet = new AnimatorSet();
                                animatorSet.setDuration(300);
                                animatorSet.playTogether(slideDownAnimTextView, scaleDownAnimTextViewX, scaleDownAnimTextViewY);
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
                                play.setOnClickListener(resumeListener2);
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
            TextView Short=(TextView) layout.findViewById(R.id.BreakText);
            Short.setText(str2[j]);
            j++;
            if (j==29)
            {
                j=0;
            }
            shortbreakpop = new PopupWindow(layout, 800, 590, true);
            shortbreakpop.showAtLocation(layout, Gravity.CENTER, 0, 0);

            TextView breaktime = (TextView) layout.findViewById(R.id.startpodo);
            breaktime.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    shortbreakpop.dismiss();
                    play.setVisibility(View.INVISIBLE);
                    ((GradientDrawable)re2.getBackground()).setStroke(1,Color.parseColor("#00000000"));
                    ((GradientDrawable)myView2.getBackground()).setColor(Color.WHITE);
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
                    Skip.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            blinkanimation3.cancel();
                            podotimer2.cancel();
                            initiatePopupWindow2();
                        }
                    });

                    Reset.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view1) {
                            blinkanimation3.cancel();
                            podotimer2.cancel();

                            ((GradientDrawable)re2.getBackground()).setStroke(1,Color.parseColor("#00000000"));
                            ((GradientDrawable)myView2.getBackground()).setColor(Color.parseColor("#32ffffff"));
                            ((GradientDrawable)re3.getBackground()).setStroke(1,Color.parseColor("#00000000"));
                            myView3.setBackgroundColor(Color.parseColor("#32ffffff"));
                            ((GradientDrawable)re4.getBackground()).setStroke(1,Color.parseColor("#00000000"));
                            ((GradientDrawable)myView4.getBackground()).setColor(Color.parseColor("#32ffffff"));
                            ((GradientDrawable)re5.getBackground()).setStroke(1,Color.parseColor("#00000000"));
                            myView5.setBackgroundColor(Color.parseColor("#32ffffff"));
                            ((GradientDrawable)re6.getBackground()).setStroke(1,Color.parseColor("#00000000"));
                            ((GradientDrawable)myView6.getBackground()).setColor(Color.parseColor("#32ffffff"));
                            ((GradientDrawable)re7.getBackground()).setStroke(1,Color.parseColor("#00000000"));
                            myView7.setBackgroundColor(Color.parseColor("#32ffffff"));

                            myView.setBackgroundColor(Color.WHITE);
                            ((GradientDrawable)re1.getBackground()).setStroke(1,Color.WHITE);
                            blinkanimation1= new AlphaAnimation(1, 0); // Change alpha from fully visible to invisible
                            blinkanimation1.setDuration(500); // duration - half a second
                            blinkanimation1.setInterpolator(new LinearInterpolator()); // do not alter animation rate
                            blinkanimation1.setRepeatCount(Settings.PomoProgress*60*2); // Repeat animation infinitely
                            blinkanimation1.setRepeatMode(Animation.REVERSE);

                            re1.startAnimation(blinkanimation1);
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

                            Skip.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    timer.cancel();
                                    blinkanimation1.cancel();
                                    initiatePopupWindow();
                                }
                            });
                            Reset.setOnClickListener(this);

                            CountText.setOnClickListener( stopListener );

                        }
                    });
                    CountText.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Reset.setVisibility(View.INVISIBLE);
                            Skip.setVisibility(View.INVISIBLE);
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
            TextView Pomo=(TextView)layout.findViewById(R.id.PomoText);
            Pomo.setText(str[i]);
            i++;
            if (i==29)
            {
                i=0;
            }
            pwindo = new PopupWindow(layout, 800, 650, true);
            pwindo.showAtLocation(layout, Gravity.CENTER, 0, 0);

            TextView breaktime = (TextView) layout.findViewById(R.id.breakTime);
            breaktime.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    pwindo.dismiss();
                    play.setVisibility(View.INVISIBLE);
                    ((GradientDrawable)re3.getBackground()).setStroke(1,Color.parseColor("#00000000"));
                    myView3.setBackgroundColor(Color.WHITE);
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
                    Skip.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            blinkanimation4.cancel();
                            shortBreaks2.cancel();
                            shortPopupWindow2();
                        }
                    });

                    Reset.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view1) {
                            blinkanimation4.cancel();
                            shortBreaks2.cancel();

                            ((GradientDrawable)re2.getBackground()).setStroke(1,Color.parseColor("#00000000"));
                            ((GradientDrawable)myView2.getBackground()).setColor(Color.parseColor("#32ffffff"));
                            ((GradientDrawable)re3.getBackground()).setStroke(1,Color.parseColor("#00000000"));
                            myView3.setBackgroundColor(Color.parseColor("#32ffffff"));
                            ((GradientDrawable)re4.getBackground()).setStroke(1,Color.parseColor("#00000000"));
                            ((GradientDrawable)myView4.getBackground()).setColor(Color.parseColor("#32ffffff"));
                            ((GradientDrawable)re5.getBackground()).setStroke(1,Color.parseColor("#00000000"));
                            myView5.setBackgroundColor(Color.parseColor("#32ffffff"));
                            ((GradientDrawable)re6.getBackground()).setStroke(1,Color.parseColor("#00000000"));
                            ((GradientDrawable)myView6.getBackground()).setColor(Color.parseColor("#32ffffff"));
                            ((GradientDrawable)re7.getBackground()).setStroke(1,Color.parseColor("#00000000"));
                            myView7.setBackgroundColor(Color.parseColor("#32ffffff"));

                            myView.setBackgroundColor(Color.WHITE);
                            ((GradientDrawable)re1.getBackground()).setStroke(1,Color.WHITE);
                            blinkanimation1= new AlphaAnimation(1, 0); // Change alpha from fully visible to invisible
                            blinkanimation1.setDuration(500); // duration - half a second
                            blinkanimation1.setInterpolator(new LinearInterpolator()); // do not alter animation rate
                            blinkanimation1.setRepeatCount(Settings.PomoProgress*60*2); // Repeat animation infinitely
                            blinkanimation1.setRepeatMode(Animation.REVERSE);

                            re1.startAnimation(blinkanimation1);
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

                            Skip.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    timer.cancel();
                                    blinkanimation1.cancel();
                                    initiatePopupWindow();
                                }
                            });
                            Reset.setOnClickListener(this);

                            CountText.setOnClickListener( stopListener );

                        }
                    });
                    CountText.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            blinkanimation4.cancel();

                            shortBreaks2.cancel();

                            Reset.setVisibility(View.INVISIBLE);
                            Skip.setVisibility(View.INVISIBLE);
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
            TextView Short=(TextView) layout.findViewById(R.id.BreakText);
            Short.setText(str2[j]);
            j++;
            if (j==29)
            {
                j=0;
            }
            shortbreakpop = new PopupWindow(layout, 800, 590, true);
            shortbreakpop.showAtLocation(layout, Gravity.CENTER, 0, 0);

            TextView breaktime = (TextView) layout.findViewById(R.id.startpodo);
            breaktime.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    play.setVisibility(View.INVISIBLE);
                    shortbreakpop.dismiss();
                    ((GradientDrawable)re4.getBackground()).setStroke(1,Color.parseColor("#00000000"));
                    ((GradientDrawable)myView4.getBackground()).setColor(Color.WHITE);
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
                    Skip.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            blinkanimation5.cancel();
                            podotimer3.cancel();
                            initiatePopupWindow3();
                        }
                    });

                    Reset.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view1) {
                            blinkanimation5.cancel();
                            podotimer3.cancel();

                            ((GradientDrawable)re2.getBackground()).setStroke(1,Color.parseColor("#00000000"));
                            ((GradientDrawable)myView2.getBackground()).setColor(Color.parseColor("#32ffffff"));
                            ((GradientDrawable)re3.getBackground()).setStroke(1,Color.parseColor("#00000000"));
                            myView3.setBackgroundColor(Color.parseColor("#32ffffff"));
                            ((GradientDrawable)re4.getBackground()).setStroke(1,Color.parseColor("#00000000"));
                            ((GradientDrawable)myView4.getBackground()).setColor(Color.parseColor("#32ffffff"));
                            ((GradientDrawable)re5.getBackground()).setStroke(1,Color.parseColor("#00000000"));
                            myView5.setBackgroundColor(Color.parseColor("#32ffffff"));
                            ((GradientDrawable)re6.getBackground()).setStroke(1,Color.parseColor("#00000000"));
                            ((GradientDrawable)myView6.getBackground()).setColor(Color.parseColor("#32ffffff"));
                            ((GradientDrawable)re7.getBackground()).setStroke(1,Color.parseColor("#00000000"));
                            myView7.setBackgroundColor(Color.parseColor("#32ffffff"));

                            myView.setBackgroundColor(Color.WHITE);
                            ((GradientDrawable)re1.getBackground()).setStroke(1,Color.WHITE);
                            blinkanimation1= new AlphaAnimation(1, 0); // Change alpha from fully visible to invisible
                            blinkanimation1.setDuration(500); // duration - half a second
                            blinkanimation1.setInterpolator(new LinearInterpolator()); // do not alter animation rate
                            blinkanimation1.setRepeatCount(Settings.PomoProgress*60*2); // Repeat animation infinitely
                            blinkanimation1.setRepeatMode(Animation.REVERSE);

                            re1.startAnimation(blinkanimation1);
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

                            Skip.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    timer.cancel();
                                    blinkanimation1.cancel();
                                    initiatePopupWindow();
                                }
                            });
                            Reset.setOnClickListener(this);

                            CountText.setOnClickListener( stopListener );

                        }
                    });
                    CountText.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            blinkanimation5.cancel();

                            podotimer3.cancel();

                            Reset.setVisibility(View.INVISIBLE);
                            Skip.setVisibility(View.INVISIBLE);
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
            TextView Pomo=(TextView)layout.findViewById(R.id.PomoText);
            Pomo.setText(str[i]);
            i++;
            if (i==29)
            {
                i=0;
            }
            pwindo = new PopupWindow(layout, 800, 650, true);
            pwindo.showAtLocation(layout, Gravity.CENTER, 0, 0);

            TextView breaktime = (TextView) layout.findViewById(R.id.breakTime);
            breaktime.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    pwindo.dismiss();
                    play.setVisibility(View.INVISIBLE);
                    ((GradientDrawable)re5.getBackground()).setStroke(1,Color.parseColor("#00000000"));
                    myView5.setBackgroundColor(Color.WHITE);
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
                    Skip.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            blinkanimation6.cancel();
                            shortBreaks3.cancel();
                            shortPopupWindow3();

                        }
                    });

                    Reset.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view1) {
                            blinkanimation6.cancel();
                            shortBreaks3.cancel();

                            ((GradientDrawable)re2.getBackground()).setStroke(1,Color.parseColor("#00000000"));
                            ((GradientDrawable)myView2.getBackground()).setColor(Color.parseColor("#32ffffff"));
                            ((GradientDrawable)re3.getBackground()).setStroke(1,Color.parseColor("#00000000"));
                            myView3.setBackgroundColor(Color.parseColor("#32ffffff"));
                            ((GradientDrawable)re4.getBackground()).setStroke(1,Color.parseColor("#00000000"));
                            ((GradientDrawable)myView4.getBackground()).setColor(Color.parseColor("#32ffffff"));
                            ((GradientDrawable)re5.getBackground()).setStroke(1,Color.parseColor("#00000000"));
                            myView5.setBackgroundColor(Color.parseColor("#32ffffff"));
                            ((GradientDrawable)re6.getBackground()).setStroke(1,Color.parseColor("#00000000"));
                            ((GradientDrawable)myView6.getBackground()).setColor(Color.parseColor("#32ffffff"));
                            ((GradientDrawable)re7.getBackground()).setStroke(1,Color.parseColor("#00000000"));
                            myView7.setBackgroundColor(Color.parseColor("#32ffffff"));

                            myView.setBackgroundColor(Color.WHITE);
                            ((GradientDrawable)re1.getBackground()).setStroke(1,Color.WHITE);
                            blinkanimation1= new AlphaAnimation(1, 0); // Change alpha from fully visible to invisible
                            blinkanimation1.setDuration(500); // duration - half a second
                            blinkanimation1.setInterpolator(new LinearInterpolator()); // do not alter animation rate
                            blinkanimation1.setRepeatCount(Settings.PomoProgress*60*2); // Repeat animation infinitely
                            blinkanimation1.setRepeatMode(Animation.REVERSE);

                            re1.startAnimation(blinkanimation1);
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

                            Skip.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    timer.cancel();
                                    blinkanimation1.cancel();
                                    initiatePopupWindow();
                                }
                            });
                            Reset.setOnClickListener(this);

                            CountText.setOnClickListener( stopListener );

                        }
                    });
                    CountText.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            blinkanimation6.cancel();

                            shortBreaks3.cancel();

                            Reset.setVisibility(View.INVISIBLE);
                            Skip.setVisibility(View.INVISIBLE);
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
            TextView Short=(TextView) layout.findViewById(R.id.BreakText);
            Short.setText(str2[j]);
            j++;
            if (j==29)
            {
                j=0;
            }
            shortbreakpop = new PopupWindow(layout, 800, 590, true);
            shortbreakpop.showAtLocation(layout, Gravity.CENTER, 0, 0);

            TextView breaktime = (TextView) layout.findViewById(R.id.startpodo);
            breaktime.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    shortbreakpop.dismiss();
                    play.setVisibility(View.INVISIBLE);
                    ((GradientDrawable)re6.getBackground()).setStroke(1,Color.parseColor("#00000000"));
                    ((GradientDrawable)myView6.getBackground()).setColor(Color.WHITE);
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
                    Skip.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            blinkanimation7.cancel();
                            longBreaks.cancel();
                            PodofinshPopupWindow();
                        }
                    });

                    Reset.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view1) {
                            longBreaks.cancel();
                            PodofinshPopupWindow();

                            ((GradientDrawable)re2.getBackground()).setStroke(1,Color.parseColor("#00000000"));
                            ((GradientDrawable)myView2.getBackground()).setColor(Color.parseColor("#32ffffff"));
                            ((GradientDrawable)re3.getBackground()).setStroke(1,Color.parseColor("#00000000"));
                            myView3.setBackgroundColor(Color.parseColor("#32ffffff"));
                            ((GradientDrawable)re4.getBackground()).setStroke(1,Color.parseColor("#00000000"));
                            ((GradientDrawable)myView4.getBackground()).setColor(Color.parseColor("#32ffffff"));
                            ((GradientDrawable)re5.getBackground()).setStroke(1,Color.parseColor("#00000000"));
                            myView5.setBackgroundColor(Color.parseColor("#32ffffff"));
                            ((GradientDrawable)re6.getBackground()).setStroke(1,Color.parseColor("#00000000"));
                            ((GradientDrawable)myView6.getBackground()).setColor(Color.parseColor("#32ffffff"));
                            ((GradientDrawable)re7.getBackground()).setStroke(1,Color.parseColor("#00000000"));
                            myView7.setBackgroundColor(Color.parseColor("#32ffffff"));

                            myView.setBackgroundColor(Color.WHITE);
                            ((GradientDrawable)re1.getBackground()).setStroke(1,Color.WHITE);
                            blinkanimation1= new AlphaAnimation(1, 0); // Change alpha from fully visible to invisible
                            blinkanimation1.setDuration(500); // duration - half a second
                            blinkanimation1.setInterpolator(new LinearInterpolator()); // do not alter animation rate
                            blinkanimation1.setRepeatCount(Settings.PomoProgress*60*2); // Repeat animation infinitely
                            blinkanimation1.setRepeatMode(Animation.REVERSE);

                            re1.startAnimation(blinkanimation1);
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

                            Skip.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    timer.cancel();
                                    blinkanimation1.cancel();
                                    initiatePopupWindow();
                                }
                            });
                            Reset.setOnClickListener(this);

                            CountText.setOnClickListener( stopListener );

                        }
                    });
                    CountText.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            blinkanimation7.cancel();
                            longBreaks.cancel();

                            Reset.setVisibility(View.INVISIBLE);
                            Skip.setVisibility(View.INVISIBLE);
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
            TextView Pomos=(TextView)layout.findViewById(R.id.PomosText);
            Pomos.setText(str[i]);
            i++;
            if (i==29)
            {
                i=0;
            }
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
                    myView7.setBackgroundColor(Color.WHITE);
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
                    Skip.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            finishlongBreaks.cancel();
                            longPopupWindow();
                        }
                    });

                    Reset.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view1) {
                            finishlongBreaks.cancel();

                            ((GradientDrawable)re2.getBackground()).setStroke(1,Color.parseColor("#00000000"));
                            ((GradientDrawable)myView2.getBackground()).setColor(Color.parseColor("#32ffffff"));
                            ((GradientDrawable)re3.getBackground()).setStroke(1,Color.parseColor("#00000000"));
                            myView3.setBackgroundColor(Color.parseColor("#32ffffff"));
                            ((GradientDrawable)re4.getBackground()).setStroke(1,Color.parseColor("#00000000"));
                            ((GradientDrawable)myView4.getBackground()).setColor(Color.parseColor("#32ffffff"));
                            ((GradientDrawable)re5.getBackground()).setStroke(1,Color.parseColor("#00000000"));
                            myView5.setBackgroundColor(Color.parseColor("#32ffffff"));
                            ((GradientDrawable)re6.getBackground()).setStroke(1,Color.parseColor("#00000000"));
                            ((GradientDrawable)myView6.getBackground()).setColor(Color.parseColor("#32ffffff"));
                            ((GradientDrawable)re7.getBackground()).setStroke(1,Color.parseColor("#00000000"));
                            myView7.setBackgroundColor(Color.parseColor("#32ffffff"));

                            myView.setBackgroundColor(Color.WHITE);
                            ((GradientDrawable)re1.getBackground()).setStroke(1,Color.WHITE);
                            blinkanimation1= new AlphaAnimation(1, 0); // Change alpha from fully visible to invisible
                            blinkanimation1.setDuration(500); // duration - half a second
                            blinkanimation1.setInterpolator(new LinearInterpolator()); // do not alter animation rate
                            blinkanimation1.setRepeatCount(Settings.PomoProgress*60*2); // Repeat animation infinitely
                            blinkanimation1.setRepeatMode(Animation.REVERSE);

                            re1.startAnimation(blinkanimation1);
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

                            Skip.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    timer.cancel();
                                    blinkanimation1.cancel();
                                    initiatePopupWindow();
                                }
                            });
                            Reset.setOnClickListener(this);

                            CountText.setOnClickListener( stopListener );

                        }
                    });
                    CountText.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {

                           finishlongBreaks.cancel();
                            Reset.setVisibility(View.INVISIBLE);
                            Skip.setVisibility(View.INVISIBLE);
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
            TextView Pomo=(TextView)layout.findViewById(R.id.LongText);
            Pomo.setText(str[j]);
            j++;
            if (j==29)
            {
                j=0;
            }
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
                     myView.setBackgroundColor(Color.argb(20,255,255,255));
                    ((GradientDrawable)myView2.getBackground()).setColor(Color.argb(20,255,255,255));
                    myView3.setBackgroundColor(Color.argb(20,255,255,255));
                    ((GradientDrawable)myView4.getBackground()).setColor(Color.argb(20,255,255,255));
                    myView5.setBackgroundColor(Color.argb(20,255,255,255));
                    ((GradientDrawable)myView6.getBackground()).setColor(Color.argb(20,255,255,255));
                    myView7.setBackgroundColor(Color.argb(20,255,255,255));
                    textView.setText("[Pomodoro]");
                    linearLayout.setVisibility(View.VISIBLE);
                    count++;
                    String s=String.valueOf(count);
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
                    Skip.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            blinkanimation.cancel();
                            timer.cancel();

                            initiatePopupWindow();
                        }
                    });

                    Reset.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view1) {
                            timer.cancel();
                            blinkanimation1.cancel();

                            ((GradientDrawable)re2.getBackground()).setStroke(1,Color.parseColor("#00000000"));
                            ((GradientDrawable)myView2.getBackground()).setColor(Color.parseColor("#32ffffff"));
                            ((GradientDrawable)re3.getBackground()).setStroke(1,Color.parseColor("#00000000"));
                            myView3.setBackgroundColor(Color.parseColor("#32ffffff"));
                            ((GradientDrawable)re4.getBackground()).setStroke(1,Color.parseColor("#00000000"));
                            ((GradientDrawable)myView4.getBackground()).setColor(Color.parseColor("#32ffffff"));
                            ((GradientDrawable)re5.getBackground()).setStroke(1,Color.parseColor("#00000000"));
                            myView5.setBackgroundColor(Color.parseColor("#32ffffff"));
                            ((GradientDrawable)re6.getBackground()).setStroke(1,Color.parseColor("#00000000"));
                            ((GradientDrawable)myView6.getBackground()).setColor(Color.parseColor("#32ffffff"));
                            ((GradientDrawable)re7.getBackground()).setStroke(1,Color.parseColor("#00000000"));
                            myView7.setBackgroundColor(Color.parseColor("#32ffffff"));

                            myView.setBackgroundColor(Color.WHITE);
                            ((GradientDrawable)re1.getBackground()).setStroke(1,Color.WHITE);
                            blinkanimation1= new AlphaAnimation(1, 0); // Change alpha from fully visible to invisible
                            blinkanimation1.setDuration(500); // duration - half a second
                            blinkanimation1.setInterpolator(new LinearInterpolator()); // do not alter animation rate
                            blinkanimation1.setRepeatCount(Settings.PomoProgress*60*2); // Repeat animation infinitely
                            blinkanimation1.setRepeatMode(Animation.REVERSE);

                            re1.startAnimation(blinkanimation1);
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

                            Skip.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    timer.cancel();
                                    blinkanimation1.cancel();
                                    initiatePopupWindow();
                                }
                            });
                            Reset.setOnClickListener(this);

                            CountText.setOnClickListener( stopListener );

                        }
                    });
                    CountText.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            blinkanimation.cancel();

                            timer.cancel();

                            Reset.setVisibility(View.INVISIBLE);
                            Skip.setVisibility(View.INVISIBLE);

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
