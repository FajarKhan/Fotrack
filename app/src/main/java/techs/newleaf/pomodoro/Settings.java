package techs.newleaf.pomodoro;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.media.RingtoneManager;
import android.net.Uri;
import android.opengl.Matrix;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.RotateAnimation;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.CompoundButton;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.support.v7.widget.SwitchCompat;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.share.model.AppInviteContent;
import com.facebook.share.widget.AppInviteDialog;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

import static android.R.attr.angle;
import static android.R.attr.button;
import static android.R.attr.value;
import static techs.newleaf.pomodoro.R.id.MainLayout;
import static techs.newleaf.pomodoro.R.id.ShortText;
import static techs.newleaf.pomodoro.R.id.play;


public class Settings extends AppCompatActivity {
    private LinearLayout notification,Invite;
    static public int PomoProgress=25,ShortProgress=5,LongProgress=15;
    private ImageView r1,r2,r3,r4,r5,r6,r7,r8,r9,r10,r11,r12,r13,r14,r15,r16,r17,r18,r19,r20,r21,r22,r23,r24,r25,r26,r27,r28,r29,r30,r31,r32;
   static public String chosenRingtone;
    static public int vibrationState=1;
    static public int ScreenState;
    private ScrollView settings_bg;
    private SeekBar pomoTimer,ShortTimer,LongTimer;
    private ImageView BackButton;
    private SwitchCompat vibration,ScreenOn;
    private TextView PomoText,ShortText,LongText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        notification=(LinearLayout)findViewById(R.id.noti);
        notification.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View view) {
                                                Intent intent = new Intent(RingtoneManager.ACTION_RINGTONE_PICKER);
                                                intent.putExtra(RingtoneManager.EXTRA_RINGTONE_TYPE, RingtoneManager.TYPE_NOTIFICATION);
                                                intent.putExtra(RingtoneManager.EXTRA_RINGTONE_TITLE, "Select Tone");
                                                intent.putExtra(RingtoneManager.EXTRA_RINGTONE_EXISTING_URI, (Uri) null);
                                                Settings.this.startActivityForResult(intent, 5);
                                            }
                                        });

        FacebookSdk.sdkInitialize(getApplicationContext());
        AppEventsLogger.activateApp(this);
        Invite=(LinearLayout)findViewById(R.id.invite1);
        Invite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String appLinkUrl,previewImageUrl;

                appLinkUrl = "https://fb.me/1030159673763369";
                previewImageUrl = "https://scontent-sit4-1.xx.fbcdn.net/t31.0-8/14753724_1630153697277538_5910569469678470699_o.png";

                if (AppInviteDialog.canShow()) {
                    AppInviteContent content = new AppInviteContent.Builder()
                            .setApplinkUrl(appLinkUrl)
                            .setPreviewImageUrl(previewImageUrl)
                            .build();
                    AppInviteDialog.show(Settings.this, content);
                }

            }
        });

        vibration=(SwitchCompat)findViewById(R.id.vibration);
        vibration.setChecked(false);
        if (vibrationState==1)
        {
            vibration.setChecked(true);
        }
        vibration.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b){
                    vibrationState=1;
                }
                else {
                    vibrationState=0;
                }
            }
        });

        ScreenOn=(SwitchCompat)findViewById(R.id.ScreenOn);
        ScreenOn.setChecked(true);
        if(ScreenState==0){
            ScreenOn.setChecked(false);
        }
        ScreenOn.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b){
                    ScreenState=1;
                }
                else {
                    ScreenState=0;
                }
            }
        });

        BackButton=(ImageView)findViewById(R.id.Backbutton);
        BackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Settings.super.onBackPressed();

            }
        });

        pomoTimer=(SeekBar)findViewById(R.id.pomoTimer);
        PomoText=(TextView)findViewById(R.id.PomoText);
        String min = String.valueOf(PomoProgress);
       PomoText.setText(min+" Minutes");
        pomoTimer.setMax(60);
        pomoTimer.setProgress(PomoProgress);
        pomoTimer.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                String min = String.valueOf(i);
                PomoText.setText(min+" Minutes");
                PomoProgress=i;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                final Snackbar snackBar = Snackbar.make(findViewById(android.R.id.content), "Time change effect in next run", Snackbar.LENGTH_LONG);
                snackBar.setActionTextColor(Color.parseColor("#80cbc4"));
                snackBar.setAction("Dismiss", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        snackBar.dismiss();
                    }
                });
                snackBar.show();

            }
        });
        ShortTimer=(SeekBar)findViewById(R.id.ShortTimer);
        ShortText=(TextView)findViewById(R.id.ShortText);
        String minn = String.valueOf(ShortProgress);
        ShortText.setText(minn+" Minutes");
        ShortTimer.setProgress(ShortProgress);
        ShortTimer.setMax(15);
        ShortTimer.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                String min = String.valueOf(i);
                ShortText.setText(min+" Minutes");
                ShortProgress=i;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                final Snackbar snackBar = Snackbar.make(findViewById(android.R.id.content), "Time change effect in next run", Snackbar.LENGTH_LONG);
                snackBar.setActionTextColor(Color.parseColor("#80cbc4"));
                snackBar.setAction("Dismiss", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        snackBar.dismiss();
                    }
                });
                snackBar.show();

            }
        });
        LongTimer=(SeekBar)findViewById(R.id.LongTimer);
        LongText=(TextView)findViewById(R.id.LongText);
        String minnn = String.valueOf(LongProgress);
        LongText.setText(minnn+" Minutes");
        LongTimer.setMax(25);
       LongTimer.setProgress(LongProgress);
        LongTimer.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                String min = String.valueOf(i);
                LongText.setText(min+" Minutes");
                LongProgress=i;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                final Snackbar snackBar = Snackbar.make(findViewById(android.R.id.content), "Time change effect in next run", Snackbar.LENGTH_LONG);
                snackBar.setActionTextColor(Color.parseColor("#80cbc4"));
                snackBar.setAction("Dismiss", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        snackBar.dismiss();
                    }
                });
                snackBar.show();

            }
        });


        settings_bg=(ScrollView) findViewById(R.id.settings_bg);

        r1 = (ImageView) findViewById(R.id.c1);
        r2 = (ImageView) findViewById(R.id.c2);
        r3 = (ImageView) findViewById(R.id.c3);
        r4 = (ImageView) findViewById(R.id.c4);
        r5 = (ImageView) findViewById(R.id.c5);
        r6 = (ImageView) findViewById(R.id.c6);
        r7 = (ImageView) findViewById(R.id.c7);
        r8 = (ImageView) findViewById(R.id.c8);
        r9 = (ImageView) findViewById(R.id.c9);
        r10 = (ImageView) findViewById(R.id.c10);
        r11 = (ImageView) findViewById(R.id.c11);
        r12 = (ImageView) findViewById(R.id.c12);
        r13 = (ImageView) findViewById(R.id.c13);
        r14 = (ImageView) findViewById(R.id.c14);
        r15 = (ImageView) findViewById(R.id.c15);
        r16 = (ImageView) findViewById(R.id.c16);
        r17 = (ImageView) findViewById(R.id.c17);
        r18 = (ImageView) findViewById(R.id.c18);
        r19 = (ImageView) findViewById(R.id.c19);
        r20 = (ImageView) findViewById(R.id.c20);
        r21 = (ImageView) findViewById(R.id.c21);
        r22 = (ImageView) findViewById(R.id.c22);
        r23 = (ImageView) findViewById(R.id.c23);
        r24 = (ImageView) findViewById(R.id.c24);
        r25 = (ImageView) findViewById(R.id.c25);
        r26 = (ImageView) findViewById(R.id.c26);
        r27 = (ImageView) findViewById(R.id.c27);
        r28 = (ImageView) findViewById(R.id.c28);
        r29 = (ImageView) findViewById(R.id.c29);
        r30 = (ImageView) findViewById(R.id.c30);
        r31 = (ImageView) findViewById(R.id.c31);
        r32 = (ImageView) findViewById(R.id.c32);

        r1.setOnClickListener(press);
        r2.setOnClickListener(press2);
        r3.setOnClickListener(press3);
        r4.setOnClickListener(press4);
        r5.setOnClickListener(press5);
        r6.setOnClickListener(press6);
        r7.setOnClickListener(press7);
        r8.setOnClickListener(press8);
        r9.setOnClickListener(press9);
        r10.setOnClickListener(press10);
        r11.setOnClickListener(press11);
        r12.setOnClickListener(press12);
        r13.setOnClickListener(press13);
        r14.setOnClickListener(press14);
        r15.setOnClickListener(press15);
        r16.setOnClickListener(press16);
        r17.setOnClickListener(press17);
        r18.setOnClickListener(press18);
        r19.setOnClickListener(press19);
        r20.setOnClickListener(press20);
        r21.setOnClickListener(press21);
        r22.setOnClickListener(press22);
        r23.setOnClickListener(press23);
        r24.setOnClickListener(press24);
        r25.setOnClickListener(press25);
        r26.setOnClickListener(press26);
        r27.setOnClickListener(press27);
        r28.setOnClickListener(press28);
        r29.setOnClickListener(press29);
        r30.setOnClickListener(press30);
        r31.setOnClickListener(press31);
        r32.setOnClickListener(press32);


        ColorDrawable buttonColor = (ColorDrawable) MainActivity.mViewPager.getBackground();
        int colorId = buttonColor.getColor();
        if (colorId == Color.parseColor("#f44336")) {
            r1.setImageResource(R.drawable.tick);
            r1.setBackgroundResource(R.drawable.border);

        }
        if (colorId == Color.parseColor("#4caf50")) {
            r2.setImageResource(R.drawable.tick);
            r2.setBackgroundResource(R.drawable.bor2);

        }
        if (colorId == Color.parseColor("#c62828")) {
            r3.setImageResource(R.drawable.tick);
            r3.setBackgroundResource(R.drawable.bor3);

        }
        if (colorId == Color.parseColor("#2f7d32")) {
            r4.setImageResource(R.drawable.tick);
            r4.setBackgroundResource(R.drawable.bor4);

        }
        if (colorId == Color.parseColor("#e91f63")) {
            r5.setImageResource(R.drawable.tick);
            r5.setBackgroundResource(R.drawable.bor5);

        }
        if (colorId == Color.parseColor("#8bc34a")) {
            r6.setImageResource(R.drawable.tick);
            r6.setBackgroundResource(R.drawable.bor6);

        }
        if (colorId == Color.parseColor("#ad1457")) {
            r7.setImageResource(R.drawable.tick);
            r7.setBackgroundResource(R.drawable.bor7);

        }
        if (colorId == Color.parseColor("#558b2f")) {
            r8.setImageResource(R.drawable.tick);
            r8.setBackgroundResource(R.drawable.bor8);

        }
        if (colorId == Color.parseColor("#9c26b0")) {
            r9.setImageResource(R.drawable.tick);
            r9.setBackgroundResource(R.drawable.bor9);

        }
        if (colorId == Color.parseColor("#cddc39")) {
            r10.setImageResource(R.drawable.tick);
            r10.setBackgroundResource(R.drawable.bor10);

        }
        if (colorId == Color.parseColor("#6a1b9a")) {
            r11.setImageResource(R.drawable.tick);
            r11.setBackgroundResource(R.drawable.bor11);

        }
        if (colorId == Color.parseColor("#9e9d25")) {
            r12.setImageResource(R.drawable.tick);
            r12.setBackgroundResource(R.drawable.bor12);

        }
        if (colorId == Color.parseColor("#673ab7")) {
            r13.setImageResource(R.drawable.tick);
            r13.setBackgroundResource(R.drawable.bore13);

        }
        if (colorId == Color.parseColor("#ffeb3b")) {
            r14.setImageResource(R.drawable.tick);
            r14.setBackgroundResource(R.drawable.bor14);

        }
        if (colorId == Color.parseColor("#4527a0")) {
            r15.setImageResource(R.drawable.tick);
            r15.setBackgroundResource(R.drawable.bor15);

        }
        if (colorId == Color.parseColor("#f9a825")) {
            r16.setImageResource(R.drawable.tick);
            r16.setBackgroundResource(R.drawable.bor16);

        }
        if (colorId == Color.parseColor("#3f51b5")) {
            r17.setImageResource(R.drawable.tick);
            r17.setBackgroundResource(R.drawable.bor17);

        }
        if (colorId == Color.parseColor("#ffc108")) {
            r18.setImageResource(R.drawable.tick);
            r18.setBackgroundResource(R.drawable.bor18);

        }
        if (colorId == Color.parseColor("#283593")) {
            r19.setImageResource(R.drawable.tick);
            r19.setBackgroundResource(R.drawable.bor19);

        }
        if (colorId == Color.parseColor("#ff8f00")) {
            r20.setImageResource(R.drawable.tick);
            r20.setBackgroundResource(R.drawable.bor20);

        }
        if (colorId == Color.parseColor("#2096f3")) {
            r21.setImageResource(R.drawable.tick);
            r21.setBackgroundResource(R.drawable.bor21);

        }if (colorId == Color.parseColor("#ff9802")) {
            r22.setImageResource(R.drawable.tick);
            r22.setBackgroundResource(R.drawable.bor22);

        }
        if (colorId == Color.parseColor("#0277bd")) {
            r23.setImageResource(R.drawable.tick);
            r23.setBackgroundResource(R.drawable.bor23);

        }
        if (colorId == Color.parseColor("#ef6c00")) {
            r24.setImageResource(R.drawable.tick);
            r24.setBackgroundResource(R.drawable.bor24);

        }
        if (colorId == Color.parseColor("#01bcd4")) {
            r25.setImageResource(R.drawable.tick);
            r25.setBackgroundResource(R.drawable.bor25);

        }
        if (colorId == Color.parseColor("#ff5722")) {
            r26.setImageResource(R.drawable.tick);
            r26.setBackgroundResource(R.drawable.bor26);

        }
        if (colorId == Color.parseColor("#00838f")) {
            r27.setImageResource(R.drawable.tick);
            r27.setBackgroundResource(R.drawable.bor27);

        }
        if (colorId == Color.parseColor("#d84316")) {
            r28.setImageResource(R.drawable.tick);
            r28.setBackgroundResource(R.drawable.bor28);

        }
        if (colorId == Color.parseColor("#009588")) {
            r29.setImageResource(R.drawable.tick);
            r29.setBackgroundResource(R.drawable.bor29);

        }
        if (colorId == Color.parseColor("#000000")) {
            r30.setImageResource(R.drawable.tick);
            r30.setBackgroundResource(R.drawable.bor30);

        }
        if (colorId == Color.parseColor("#00695c")) {
            r31.setImageResource(R.drawable.tick);
            r31.setBackgroundResource(R.drawable.bor31);

        }
        if (colorId == Color.parseColor("#4e342e")) {
            r32.setImageResource(R.drawable.tick);
            r32.setBackgroundResource(R.drawable.bor32);

        }



    }
    @Override
    protected void onActivityResult(final int requestCode, final int resultCode, final Intent intent)
    {
        if (resultCode == Activity.RESULT_OK && requestCode == 5)
        {
            Uri uri = intent.getParcelableExtra(RingtoneManager.EXTRA_RINGTONE_PICKED_URI);

            if (uri != null)
            {
                this.chosenRingtone = uri.toString();
            }
            else
            {
                this.chosenRingtone = null;
            }
        }
    }

    View.OnClickListener press=new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            MainActivity.mViewPager.setBackgroundColor(Color.parseColor("#f44336"));
            settings_bg.setBackgroundColor(Color.parseColor("#f44336"));
            r1.setBackgroundResource(R.drawable.griditem);
            r1.setImageResource(android.R.color.transparent);

            if (r1.getDrawable().getConstantState() == getResources().getDrawable(R.drawable.tick).getConstantState()) {
                r1.setBackgroundResource(R.drawable.griditem);
                r1.setImageResource(android.R.color.transparent);
            } else {
                r2.setBackgroundResource(R.drawable.non2);
                r3.setBackgroundResource(R.drawable.non3);
                r4.setBackgroundResource(R.drawable.non4);
                r5.setBackgroundResource(R.drawable.non5);
                r6.setBackgroundResource(R.drawable.non6);
                r7.setBackgroundResource(R.drawable.non7);
                r8.setBackgroundResource(R.drawable.non8);
                r9.setBackgroundResource(R.drawable.non9);
                r10.setBackgroundResource(R.drawable.non10);
                r11.setBackgroundResource(R.drawable.non11);
                r12.setBackgroundResource(R.drawable.non12);
                r13.setBackgroundResource(R.drawable.non13);
                r14.setBackgroundResource(R.drawable.non14);
                r15.setBackgroundResource(R.drawable.non15);
                r16.setBackgroundResource(R.drawable.non16);
                r17.setBackgroundResource(R.drawable.non17);
                r18.setBackgroundResource(R.drawable.non18);
                r19.setBackgroundResource(R.drawable.non19);
                r20.setBackgroundResource(R.drawable.non20);
                r21.setBackgroundResource(R.drawable.non21);
                r22.setBackgroundResource(R.drawable.non22);
                r23.setBackgroundResource(R.drawable.non23);
                r24.setBackgroundResource(R.drawable.non24);
                r25.setBackgroundResource(R.drawable.non25);
                r26.setBackgroundResource(R.drawable.non26);
                r27.setBackgroundResource(R.drawable.non27);
                r28.setBackgroundResource(R.drawable.non28);
                r29.setBackgroundResource(R.drawable.non29);
                r30.setBackgroundResource(R.drawable.non30);
                r31.setBackgroundResource(R.drawable.non31);
                r32.setBackgroundResource(R.drawable.non32);


                r1.setImageResource(R.drawable.tick);
                r1.setBackgroundResource(R.drawable.border);

                r2.setImageResource(android.R.color.transparent);
                r3.setImageResource(android.R.color.transparent);
                r4.setImageResource(android.R.color.transparent);
                r5.setImageResource(android.R.color.transparent);
                r6.setImageResource(android.R.color.transparent);
                r7.setImageResource(android.R.color.transparent);
                r8.setImageResource(android.R.color.transparent);
                r9.setImageResource(android.R.color.transparent);
                r10.setImageResource(android.R.color.transparent);
                r11.setImageResource(android.R.color.transparent);
                r12.setImageResource(android.R.color.transparent);
                r13.setImageResource(android.R.color.transparent);
                r14.setImageResource(android.R.color.transparent);
                r15.setImageResource(android.R.color.transparent);
                r16.setImageResource(android.R.color.transparent);
                r17.setImageResource(android.R.color.transparent);
                r18.setImageResource(android.R.color.transparent);
                r19.setImageResource(android.R.color.transparent);
                r20.setImageResource(android.R.color.transparent);
                r21.setImageResource(android.R.color.transparent);
                r22.setImageResource(android.R.color.transparent);
                r23.setImageResource(android.R.color.transparent);
                r24.setImageResource(android.R.color.transparent);
                r25.setImageResource(android.R.color.transparent);
                r26.setImageResource(android.R.color.transparent);
                r27.setImageResource(android.R.color.transparent);
                r28.setImageResource(android.R.color.transparent);
                r29.setImageResource(android.R.color.transparent);
                r30.setImageResource(android.R.color.transparent);
                r31.setImageResource(android.R.color.transparent);
                r32.setImageResource(android.R.color.transparent);
            }
        }
    };
    View.OnClickListener press2=new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            MainActivity.mViewPager.setBackgroundColor(Color.parseColor("#4caf50"));
            settings_bg.setBackgroundColor(Color.parseColor("#4caf50"));

            r2.setBackgroundResource(R.drawable.non2);
            r2.setImageResource(android.R.color.transparent);

            if (r2.getDrawable().getConstantState() == getResources().getDrawable(R.drawable.tick).getConstantState()) {
                r2.setBackgroundResource(R.drawable.non2);
                r2.setImageResource(android.R.color.transparent);
            } else {
                r1.setBackgroundResource(R.drawable.griditem);
                r3.setBackgroundResource(R.drawable.non3);
                r4.setBackgroundResource(R.drawable.non4);
                r5.setBackgroundResource(R.drawable.non5);
                r6.setBackgroundResource(R.drawable.non6);
                r7.setBackgroundResource(R.drawable.non7);
                r8.setBackgroundResource(R.drawable.non8);
                r9.setBackgroundResource(R.drawable.non9);
                r10.setBackgroundResource(R.drawable.non10);
                r11.setBackgroundResource(R.drawable.non11);
                r12.setBackgroundResource(R.drawable.non12);
                r13.setBackgroundResource(R.drawable.non13);
                r14.setBackgroundResource(R.drawable.non14);
                r15.setBackgroundResource(R.drawable.non15);
                r16.setBackgroundResource(R.drawable.non16);
                r17.setBackgroundResource(R.drawable.non17);
                r18.setBackgroundResource(R.drawable.non18);
                r19.setBackgroundResource(R.drawable.non19);
                r20.setBackgroundResource(R.drawable.non20);
                r21.setBackgroundResource(R.drawable.non21);
                r22.setBackgroundResource(R.drawable.non22);
                r23.setBackgroundResource(R.drawable.non23);
                r24.setBackgroundResource(R.drawable.non24);
                r25.setBackgroundResource(R.drawable.non25);
                r26.setBackgroundResource(R.drawable.non26);
                r27.setBackgroundResource(R.drawable.non27);
                r28.setBackgroundResource(R.drawable.non28);
                r29.setBackgroundResource(R.drawable.non29);
                r30.setBackgroundResource(R.drawable.non30);
                r31.setBackgroundResource(R.drawable.non31);
                r32.setBackgroundResource(R.drawable.non32);

                r2.setImageResource(R.drawable.tick);
                r2.setBackgroundResource(R.drawable.bor2);

                r1.setImageResource(android.R.color.transparent);
                r3.setImageResource(android.R.color.transparent);
                r4.setImageResource(android.R.color.transparent);
                r5.setImageResource(android.R.color.transparent);
                r6.setImageResource(android.R.color.transparent);
                r7.setImageResource(android.R.color.transparent);
                r8.setImageResource(android.R.color.transparent);
                r9.setImageResource(android.R.color.transparent);
                r10.setImageResource(android.R.color.transparent);
                r11.setImageResource(android.R.color.transparent);
                r12.setImageResource(android.R.color.transparent);
                r13.setImageResource(android.R.color.transparent);
                r14.setImageResource(android.R.color.transparent);
                r15.setImageResource(android.R.color.transparent);
                r16.setImageResource(android.R.color.transparent);
                r17.setImageResource(android.R.color.transparent);
                r18.setImageResource(android.R.color.transparent);
                r19.setImageResource(android.R.color.transparent);
                r20.setImageResource(android.R.color.transparent);
                r21.setImageResource(android.R.color.transparent);
                r22.setImageResource(android.R.color.transparent);
                r23.setImageResource(android.R.color.transparent);
                r24.setImageResource(android.R.color.transparent);
                r25.setImageResource(android.R.color.transparent);
                r26.setImageResource(android.R.color.transparent);
                r27.setImageResource(android.R.color.transparent);
                r28.setImageResource(android.R.color.transparent);
                r29.setImageResource(android.R.color.transparent);
                r30.setImageResource(android.R.color.transparent);
                r31.setImageResource(android.R.color.transparent);
                r32.setImageResource(android.R.color.transparent);
            }
        }
    };
    View.OnClickListener press3=new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            MainActivity.mViewPager.setBackgroundColor(Color.parseColor("#c62828"));
            settings_bg.setBackgroundColor(Color.parseColor("#c62828"));

            r3.setBackgroundResource(R.drawable.non3);
            r3.setImageResource(android.R.color.transparent);

            if (r3.getDrawable().getConstantState() == getResources().getDrawable(R.drawable.tick).getConstantState()) {
                r3.setBackgroundResource(R.drawable.non3);
                r3.setImageResource(android.R.color.transparent);
            } else {
                r1.setBackgroundResource(R.drawable.griditem);
                r2.setBackgroundResource(R.drawable.non2);
                r4.setBackgroundResource(R.drawable.non4);
                r5.setBackgroundResource(R.drawable.non5);
                r6.setBackgroundResource(R.drawable.non6);
                r7.setBackgroundResource(R.drawable.non7);
                r8.setBackgroundResource(R.drawable.non8);
                r9.setBackgroundResource(R.drawable.non9);
                r10.setBackgroundResource(R.drawable.non10);
                r11.setBackgroundResource(R.drawable.non11);
                r12.setBackgroundResource(R.drawable.non12);
                r13.setBackgroundResource(R.drawable.non13);
                r14.setBackgroundResource(R.drawable.non14);
                r15.setBackgroundResource(R.drawable.non15);
                r16.setBackgroundResource(R.drawable.non16);
                r17.setBackgroundResource(R.drawable.non17);
                r18.setBackgroundResource(R.drawable.non18);
                r19.setBackgroundResource(R.drawable.non19);
                r20.setBackgroundResource(R.drawable.non20);
                r21.setBackgroundResource(R.drawable.non21);
                r22.setBackgroundResource(R.drawable.non22);
                r23.setBackgroundResource(R.drawable.non23);
                r24.setBackgroundResource(R.drawable.non24);
                r25.setBackgroundResource(R.drawable.non25);
                r26.setBackgroundResource(R.drawable.non26);
                r27.setBackgroundResource(R.drawable.non27);
                r28.setBackgroundResource(R.drawable.non28);
                r29.setBackgroundResource(R.drawable.non29);
                r30.setBackgroundResource(R.drawable.non30);
                r31.setBackgroundResource(R.drawable.non31);
                r32.setBackgroundResource(R.drawable.non32);

                r3.setImageResource(R.drawable.tick);
                r3.setBackgroundResource(R.drawable.bor3);

                r1.setImageResource(android.R.color.transparent);
                r2.setImageResource(android.R.color.transparent);
                r4.setImageResource(android.R.color.transparent);
                r5.setImageResource(android.R.color.transparent);
                r6.setImageResource(android.R.color.transparent);
                r7.setImageResource(android.R.color.transparent);
                r8.setImageResource(android.R.color.transparent);
                r9.setImageResource(android.R.color.transparent);
                r10.setImageResource(android.R.color.transparent);
                r11.setImageResource(android.R.color.transparent);
                r12.setImageResource(android.R.color.transparent);
                r13.setImageResource(android.R.color.transparent);
                r14.setImageResource(android.R.color.transparent);
                r15.setImageResource(android.R.color.transparent);
                r16.setImageResource(android.R.color.transparent);
                r17.setImageResource(android.R.color.transparent);
                r18.setImageResource(android.R.color.transparent);
                r19.setImageResource(android.R.color.transparent);
                r20.setImageResource(android.R.color.transparent);
                r21.setImageResource(android.R.color.transparent);
                r22.setImageResource(android.R.color.transparent);
                r23.setImageResource(android.R.color.transparent);
                r24.setImageResource(android.R.color.transparent);
                r25.setImageResource(android.R.color.transparent);
                r26.setImageResource(android.R.color.transparent);
                r27.setImageResource(android.R.color.transparent);
                r28.setImageResource(android.R.color.transparent);
                r29.setImageResource(android.R.color.transparent);
                r30.setImageResource(android.R.color.transparent);
                r31.setImageResource(android.R.color.transparent);
                r32.setImageResource(android.R.color.transparent);

            }
        }
    };
    View.OnClickListener press4=new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            MainActivity.mViewPager.setBackgroundColor(Color.parseColor("#2f7d32"));
            settings_bg.setBackgroundColor(Color.parseColor("#2f7d32"));


            r4.setBackgroundResource(R.drawable.non4);
            r4.setImageResource(android.R.color.transparent);

            if (r4.getDrawable().getConstantState() == getResources().getDrawable(R.drawable.tick).getConstantState()) {
                r4.setBackgroundResource(R.drawable.non4);
                r4.setImageResource(android.R.color.transparent);
            } else {
                r1.setBackgroundResource(R.drawable.griditem);
                r2.setBackgroundResource(R.drawable.non2);
                r3.setBackgroundResource(R.drawable.non3);
                r5.setBackgroundResource(R.drawable.non5);
                r6.setBackgroundResource(R.drawable.non6);
                r7.setBackgroundResource(R.drawable.non7);
                r8.setBackgroundResource(R.drawable.non8);
                r9.setBackgroundResource(R.drawable.non9);
                r10.setBackgroundResource(R.drawable.non10);
                r11.setBackgroundResource(R.drawable.non11);
                r12.setBackgroundResource(R.drawable.non12);
                r13.setBackgroundResource(R.drawable.non13);
                r14.setBackgroundResource(R.drawable.non14);
                r15.setBackgroundResource(R.drawable.non15);
                r16.setBackgroundResource(R.drawable.non16);
                r17.setBackgroundResource(R.drawable.non17);
                r18.setBackgroundResource(R.drawable.non18);
                r19.setBackgroundResource(R.drawable.non19);
                r20.setBackgroundResource(R.drawable.non20);
                r21.setBackgroundResource(R.drawable.non21);
                r22.setBackgroundResource(R.drawable.non22);
                r23.setBackgroundResource(R.drawable.non23);
                r24.setBackgroundResource(R.drawable.non24);
                r25.setBackgroundResource(R.drawable.non25);
                r26.setBackgroundResource(R.drawable.non26);
                r27.setBackgroundResource(R.drawable.non27);
                r28.setBackgroundResource(R.drawable.non28);
                r29.setBackgroundResource(R.drawable.non29);
                r30.setBackgroundResource(R.drawable.non30);
                r31.setBackgroundResource(R.drawable.non31);
                r32.setBackgroundResource(R.drawable.non32);

                r4.setImageResource(R.drawable.tick);
                r4.setBackgroundResource(R.drawable.bor4);

                r1.setImageResource(android.R.color.transparent);
                r2.setImageResource(android.R.color.transparent);
                r3.setImageResource(android.R.color.transparent);
                r5.setImageResource(android.R.color.transparent);
                r6.setImageResource(android.R.color.transparent);
                r7.setImageResource(android.R.color.transparent);
                r8.setImageResource(android.R.color.transparent);
                r9.setImageResource(android.R.color.transparent);
                r10.setImageResource(android.R.color.transparent);
                r11.setImageResource(android.R.color.transparent);
                r12.setImageResource(android.R.color.transparent);
                r13.setImageResource(android.R.color.transparent);
                r14.setImageResource(android.R.color.transparent);
                r15.setImageResource(android.R.color.transparent);
                r16.setImageResource(android.R.color.transparent);
                r17.setImageResource(android.R.color.transparent);
                r18.setImageResource(android.R.color.transparent);
                r19.setImageResource(android.R.color.transparent);
                r20.setImageResource(android.R.color.transparent);
                r21.setImageResource(android.R.color.transparent);
                r22.setImageResource(android.R.color.transparent);
                r23.setImageResource(android.R.color.transparent);
                r24.setImageResource(android.R.color.transparent);
                r25.setImageResource(android.R.color.transparent);
                r26.setImageResource(android.R.color.transparent);
                r27.setImageResource(android.R.color.transparent);
                r28.setImageResource(android.R.color.transparent);
                r29.setImageResource(android.R.color.transparent);
                r30.setImageResource(android.R.color.transparent);
                r31.setImageResource(android.R.color.transparent);
                r32.setImageResource(android.R.color.transparent);

            }
        }
    };
    View.OnClickListener press5=new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            MainActivity.mViewPager.setBackgroundColor(Color.parseColor("#e91f63"));
            settings_bg.setBackgroundColor(Color.parseColor("#e91f63"));

            r5.setBackgroundResource(R.drawable.non5);
            r5.setImageResource(android.R.color.transparent);

            if (r5.getDrawable().getConstantState() == getResources().getDrawable(R.drawable.tick).getConstantState()) {
                r5.setBackgroundResource(R.drawable.non5);
                r5.setImageResource(android.R.color.transparent);
            } else {
                r1.setBackgroundResource(R.drawable.griditem);
                r2.setBackgroundResource(R.drawable.non2);
                r3.setBackgroundResource(R.drawable.non3);
                r4.setBackgroundResource(R.drawable.non4);
                r6.setBackgroundResource(R.drawable.non6);
                r7.setBackgroundResource(R.drawable.non7);
                r8.setBackgroundResource(R.drawable.non8);
                r9.setBackgroundResource(R.drawable.non9);
                r10.setBackgroundResource(R.drawable.non10);
                r11.setBackgroundResource(R.drawable.non11);
                r12.setBackgroundResource(R.drawable.non12);
                r13.setBackgroundResource(R.drawable.non13);
                r14.setBackgroundResource(R.drawable.non14);
                r15.setBackgroundResource(R.drawable.non15);
                r16.setBackgroundResource(R.drawable.non16);
                r17.setBackgroundResource(R.drawable.non17);
                r18.setBackgroundResource(R.drawable.non18);
                r19.setBackgroundResource(R.drawable.non19);
                r20.setBackgroundResource(R.drawable.non20);
                r21.setBackgroundResource(R.drawable.non21);
                r22.setBackgroundResource(R.drawable.non22);
                r23.setBackgroundResource(R.drawable.non23);
                r24.setBackgroundResource(R.drawable.non24);
                r25.setBackgroundResource(R.drawable.non25);
                r26.setBackgroundResource(R.drawable.non26);
                r27.setBackgroundResource(R.drawable.non27);
                r28.setBackgroundResource(R.drawable.non28);
                r29.setBackgroundResource(R.drawable.non29);
                r30.setBackgroundResource(R.drawable.non30);
                r31.setBackgroundResource(R.drawable.non31);
                r32.setBackgroundResource(R.drawable.non32);

                r5.setImageResource(R.drawable.tick);
                r5.setBackgroundResource(R.drawable.bor5);

                r1.setImageResource(android.R.color.transparent);
                r2.setImageResource(android.R.color.transparent);
                r3.setImageResource(android.R.color.transparent);
                r4.setImageResource(android.R.color.transparent);
                r6.setImageResource(android.R.color.transparent);
                r7.setImageResource(android.R.color.transparent);
                r8.setImageResource(android.R.color.transparent);
                r9.setImageResource(android.R.color.transparent);
                r10.setImageResource(android.R.color.transparent);
                r11.setImageResource(android.R.color.transparent);
                r12.setImageResource(android.R.color.transparent);
                r13.setImageResource(android.R.color.transparent);
                r14.setImageResource(android.R.color.transparent);
                r15.setImageResource(android.R.color.transparent);
                r16.setImageResource(android.R.color.transparent);
                r17.setImageResource(android.R.color.transparent);
                r18.setImageResource(android.R.color.transparent);
                r19.setImageResource(android.R.color.transparent);
                r20.setImageResource(android.R.color.transparent);
                r21.setImageResource(android.R.color.transparent);
                r22.setImageResource(android.R.color.transparent);
                r23.setImageResource(android.R.color.transparent);
                r24.setImageResource(android.R.color.transparent);
                r25.setImageResource(android.R.color.transparent);
                r26.setImageResource(android.R.color.transparent);
                r27.setImageResource(android.R.color.transparent);
                r28.setImageResource(android.R.color.transparent);
                r29.setImageResource(android.R.color.transparent);
                r30.setImageResource(android.R.color.transparent);
                r31.setImageResource(android.R.color.transparent);
                r32.setImageResource(android.R.color.transparent);

            }
        }
    };
    View.OnClickListener press6=new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            MainActivity.mViewPager.setBackgroundColor(Color.parseColor("#8bc34a"));
            settings_bg.setBackgroundColor(Color.parseColor("#8bc34a"));

            r6.setBackgroundResource(R.drawable.non6);
            r6.setImageResource(android.R.color.transparent);

            if (r6.getDrawable().getConstantState() == getResources().getDrawable(R.drawable.tick).getConstantState()) {
                r6.setBackgroundResource(R.drawable.non6);
                r6.setImageResource(android.R.color.transparent);
            } else {
                r1.setBackgroundResource(R.drawable.griditem);
                r2.setBackgroundResource(R.drawable.non2);
                r3.setBackgroundResource(R.drawable.non3);
                r5.setBackgroundResource(R.drawable.non5);
                r4.setBackgroundResource(R.drawable.non4);
                r7.setBackgroundResource(R.drawable.non7);
                r8.setBackgroundResource(R.drawable.non8);
                r9.setBackgroundResource(R.drawable.non9);
                r10.setBackgroundResource(R.drawable.non10);
                r11.setBackgroundResource(R.drawable.non11);
                r12.setBackgroundResource(R.drawable.non12);
                r13.setBackgroundResource(R.drawable.non13);
                r14.setBackgroundResource(R.drawable.non14);
                r15.setBackgroundResource(R.drawable.non15);
                r16.setBackgroundResource(R.drawable.non16);
                r17.setBackgroundResource(R.drawable.non17);
                r18.setBackgroundResource(R.drawable.non18);
                r19.setBackgroundResource(R.drawable.non19);
                r20.setBackgroundResource(R.drawable.non20);
                r21.setBackgroundResource(R.drawable.non21);
                r22.setBackgroundResource(R.drawable.non22);
                r23.setBackgroundResource(R.drawable.non23);
                r24.setBackgroundResource(R.drawable.non24);
                r25.setBackgroundResource(R.drawable.non25);
                r26.setBackgroundResource(R.drawable.non26);
                r27.setBackgroundResource(R.drawable.non27);
                r28.setBackgroundResource(R.drawable.non28);
                r29.setBackgroundResource(R.drawable.non29);
                r30.setBackgroundResource(R.drawable.non30);
                r31.setBackgroundResource(R.drawable.non31);
                r32.setBackgroundResource(R.drawable.non32);

                r6.setImageResource(R.drawable.tick);
                r6.setBackgroundResource(R.drawable.bor6);

                r1.setImageResource(android.R.color.transparent);
                r2.setImageResource(android.R.color.transparent);
                r3.setImageResource(android.R.color.transparent);
                r4.setImageResource(android.R.color.transparent);
                r5.setImageResource(android.R.color.transparent);
                r7.setImageResource(android.R.color.transparent);
                r8.setImageResource(android.R.color.transparent);
                r9.setImageResource(android.R.color.transparent);
                r10.setImageResource(android.R.color.transparent);
                r11.setImageResource(android.R.color.transparent);
                r12.setImageResource(android.R.color.transparent);
                r13.setImageResource(android.R.color.transparent);
                r14.setImageResource(android.R.color.transparent);
                r15.setImageResource(android.R.color.transparent);
                r16.setImageResource(android.R.color.transparent);
                r17.setImageResource(android.R.color.transparent);
                r18.setImageResource(android.R.color.transparent);
                r19.setImageResource(android.R.color.transparent);
                r20.setImageResource(android.R.color.transparent);
                r21.setImageResource(android.R.color.transparent);
                r22.setImageResource(android.R.color.transparent);
                r23.setImageResource(android.R.color.transparent);
                r24.setImageResource(android.R.color.transparent);
                r25.setImageResource(android.R.color.transparent);
                r26.setImageResource(android.R.color.transparent);
                r27.setImageResource(android.R.color.transparent);
                r28.setImageResource(android.R.color.transparent);
                r29.setImageResource(android.R.color.transparent);
                r30.setImageResource(android.R.color.transparent);
                r31.setImageResource(android.R.color.transparent);
                r32.setImageResource(android.R.color.transparent);

            }
        }
    };
    View.OnClickListener press7=new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            MainActivity.mViewPager.setBackgroundColor(Color.parseColor("#ad1457"));
            settings_bg.setBackgroundColor(Color.parseColor("#ad1457"));

            r7.setBackgroundResource(R.drawable.non7);
            r7.setImageResource(android.R.color.transparent);

            if (r7.getDrawable().getConstantState() == getResources().getDrawable(R.drawable.tick).getConstantState()) {
                r7.setBackgroundResource(R.drawable.non7);
                r7.setImageResource(android.R.color.transparent);
            } else {
                r1.setBackgroundResource(R.drawable.griditem);
                r2.setBackgroundResource(R.drawable.non2);
                r3.setBackgroundResource(R.drawable.non3);
                r5.setBackgroundResource(R.drawable.non5);
                r6.setBackgroundResource(R.drawable.non6);
                r4.setBackgroundResource(R.drawable.non4);
                r8.setBackgroundResource(R.drawable.non8);
                r9.setBackgroundResource(R.drawable.non9);
                r10.setBackgroundResource(R.drawable.non10);
                r11.setBackgroundResource(R.drawable.non11);
                r12.setBackgroundResource(R.drawable.non12);
                r13.setBackgroundResource(R.drawable.non13);
                r14.setBackgroundResource(R.drawable.non14);
                r15.setBackgroundResource(R.drawable.non15);
                r16.setBackgroundResource(R.drawable.non16);
                r17.setBackgroundResource(R.drawable.non17);
                r18.setBackgroundResource(R.drawable.non18);
                r19.setBackgroundResource(R.drawable.non19);
                r20.setBackgroundResource(R.drawable.non20);
                r21.setBackgroundResource(R.drawable.non21);
                r22.setBackgroundResource(R.drawable.non22);
                r23.setBackgroundResource(R.drawable.non23);
                r24.setBackgroundResource(R.drawable.non24);
                r25.setBackgroundResource(R.drawable.non25);
                r26.setBackgroundResource(R.drawable.non26);
                r27.setBackgroundResource(R.drawable.non27);
                r28.setBackgroundResource(R.drawable.non28);
                r29.setBackgroundResource(R.drawable.non29);
                r30.setBackgroundResource(R.drawable.non30);
                r31.setBackgroundResource(R.drawable.non31);
                r32.setBackgroundResource(R.drawable.non32);

                r7.setImageResource(R.drawable.tick);
                r7.setBackgroundResource(R.drawable.bor7);

                r1.setImageResource(android.R.color.transparent);
                r2.setImageResource(android.R.color.transparent);
                r3.setImageResource(android.R.color.transparent);
                r4.setImageResource(android.R.color.transparent);
                r5.setImageResource(android.R.color.transparent);
                r6.setImageResource(android.R.color.transparent);
                r8.setImageResource(android.R.color.transparent);
                r9.setImageResource(android.R.color.transparent);
                r10.setImageResource(android.R.color.transparent);
                r11.setImageResource(android.R.color.transparent);
                r12.setImageResource(android.R.color.transparent);
                r13.setImageResource(android.R.color.transparent);
                r14.setImageResource(android.R.color.transparent);
                r15.setImageResource(android.R.color.transparent);
                r16.setImageResource(android.R.color.transparent);
                r17.setImageResource(android.R.color.transparent);
                r18.setImageResource(android.R.color.transparent);
                r19.setImageResource(android.R.color.transparent);
                r20.setImageResource(android.R.color.transparent);
                r21.setImageResource(android.R.color.transparent);
                r22.setImageResource(android.R.color.transparent);
                r23.setImageResource(android.R.color.transparent);
                r24.setImageResource(android.R.color.transparent);
                r25.setImageResource(android.R.color.transparent);
                r26.setImageResource(android.R.color.transparent);
                r27.setImageResource(android.R.color.transparent);
                r28.setImageResource(android.R.color.transparent);
                r29.setImageResource(android.R.color.transparent);
                r30.setImageResource(android.R.color.transparent);
                r31.setImageResource(android.R.color.transparent);
                r32.setImageResource(android.R.color.transparent);

            }
        }
    };
    View.OnClickListener press8=new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            MainActivity.mViewPager.setBackgroundColor(Color.parseColor("#558b2f"));
            settings_bg.setBackgroundColor(Color.parseColor("#558b2f"));

            r8.setBackgroundResource(R.drawable.non8);
            r8.setImageResource(android.R.color.transparent);

            if (r8.getDrawable().getConstantState() == getResources().getDrawable(R.drawable.tick).getConstantState()) {
                r8.setBackgroundResource(R.drawable.non8);
                r8.setImageResource(android.R.color.transparent);
            } else {
                r1.setBackgroundResource(R.drawable.griditem);
                r2.setBackgroundResource(R.drawable.non2);
                r3.setBackgroundResource(R.drawable.non3);
                r5.setBackgroundResource(R.drawable.non5);
                r6.setBackgroundResource(R.drawable.non6);
                r4.setBackgroundResource(R.drawable.non4);
                r7.setBackgroundResource(R.drawable.non7);
                r9.setBackgroundResource(R.drawable.non9);
                r10.setBackgroundResource(R.drawable.non10);
                r11.setBackgroundResource(R.drawable.non11);
                r12.setBackgroundResource(R.drawable.non12);
                r13.setBackgroundResource(R.drawable.non13);
                r14.setBackgroundResource(R.drawable.non14);
                r15.setBackgroundResource(R.drawable.non15);
                r16.setBackgroundResource(R.drawable.non16);
                r17.setBackgroundResource(R.drawable.non17);
                r18.setBackgroundResource(R.drawable.non18);
                r19.setBackgroundResource(R.drawable.non19);
                r20.setBackgroundResource(R.drawable.non20);
                r21.setBackgroundResource(R.drawable.non21);
                r22.setBackgroundResource(R.drawable.non22);
                r23.setBackgroundResource(R.drawable.non23);
                r24.setBackgroundResource(R.drawable.non24);
                r25.setBackgroundResource(R.drawable.non25);
                r26.setBackgroundResource(R.drawable.non26);
                r27.setBackgroundResource(R.drawable.non27);
                r28.setBackgroundResource(R.drawable.non28);
                r29.setBackgroundResource(R.drawable.non29);
                r30.setBackgroundResource(R.drawable.non30);
                r31.setBackgroundResource(R.drawable.non31);
                r32.setBackgroundResource(R.drawable.non32);

                r8.setImageResource(R.drawable.tick);
                r8.setBackgroundResource(R.drawable.bor8);

                r1.setImageResource(android.R.color.transparent);
                r2.setImageResource(android.R.color.transparent);
                r3.setImageResource(android.R.color.transparent);
                r4.setImageResource(android.R.color.transparent);
                r5.setImageResource(android.R.color.transparent);
                r6.setImageResource(android.R.color.transparent);
                r7.setImageResource(android.R.color.transparent);
                r9.setImageResource(android.R.color.transparent);
                r10.setImageResource(android.R.color.transparent);
                r11.setImageResource(android.R.color.transparent);
                r12.setImageResource(android.R.color.transparent);
                r13.setImageResource(android.R.color.transparent);
                r14.setImageResource(android.R.color.transparent);
                r15.setImageResource(android.R.color.transparent);
                r16.setImageResource(android.R.color.transparent);
                r17.setImageResource(android.R.color.transparent);
                r18.setImageResource(android.R.color.transparent);
                r19.setImageResource(android.R.color.transparent);
                r20.setImageResource(android.R.color.transparent);
                r21.setImageResource(android.R.color.transparent);
                r22.setImageResource(android.R.color.transparent);
                r23.setImageResource(android.R.color.transparent);
                r24.setImageResource(android.R.color.transparent);
                r25.setImageResource(android.R.color.transparent);
                r26.setImageResource(android.R.color.transparent);
                r27.setImageResource(android.R.color.transparent);
                r28.setImageResource(android.R.color.transparent);
                r29.setImageResource(android.R.color.transparent);
                r30.setImageResource(android.R.color.transparent);
                r31.setImageResource(android.R.color.transparent);
                r32.setImageResource(android.R.color.transparent);

            }
        }
    };
    View.OnClickListener press9=new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            MainActivity.mViewPager.setBackgroundColor(Color.parseColor("#9c26b0"));
            settings_bg.setBackgroundColor(Color.parseColor("#9c26b0"));

            r9.setBackgroundResource(R.drawable.non9);
            r9.setImageResource(android.R.color.transparent);

            if (r9.getDrawable().getConstantState() == getResources().getDrawable(R.drawable.tick).getConstantState()) {
                r9.setBackgroundResource(R.drawable.non9);
                r9.setImageResource(android.R.color.transparent);
            } else {
                r1.setBackgroundResource(R.drawable.griditem);
                r2.setBackgroundResource(R.drawable.non2);
                r3.setBackgroundResource(R.drawable.non3);
                r5.setBackgroundResource(R.drawable.non5);
                r6.setBackgroundResource(R.drawable.non6);
                r4.setBackgroundResource(R.drawable.non4);
                r8.setBackgroundResource(R.drawable.non8);
                r7.setBackgroundResource(R.drawable.non7);
                r10.setBackgroundResource(R.drawable.non10);
                r11.setBackgroundResource(R.drawable.non11);
                r12.setBackgroundResource(R.drawable.non12);
                r13.setBackgroundResource(R.drawable.non13);
                r14.setBackgroundResource(R.drawable.non14);
                r15.setBackgroundResource(R.drawable.non15);
                r16.setBackgroundResource(R.drawable.non16);
                r17.setBackgroundResource(R.drawable.non17);
                r18.setBackgroundResource(R.drawable.non18);
                r19.setBackgroundResource(R.drawable.non19);
                r20.setBackgroundResource(R.drawable.non20);
                r21.setBackgroundResource(R.drawable.non21);
                r22.setBackgroundResource(R.drawable.non22);
                r23.setBackgroundResource(R.drawable.non23);
                r24.setBackgroundResource(R.drawable.non24);
                r25.setBackgroundResource(R.drawable.non25);
                r26.setBackgroundResource(R.drawable.non26);
                r27.setBackgroundResource(R.drawable.non27);
                r28.setBackgroundResource(R.drawable.non28);
                r29.setBackgroundResource(R.drawable.non29);
                r30.setBackgroundResource(R.drawable.non30);
                r31.setBackgroundResource(R.drawable.non31);
                r32.setBackgroundResource(R.drawable.non32);

                r9.setImageResource(R.drawable.tick);
                r9.setBackgroundResource(R.drawable.bor9);

                r1.setImageResource(android.R.color.transparent);
                r2.setImageResource(android.R.color.transparent);
                r3.setImageResource(android.R.color.transparent);
                r4.setImageResource(android.R.color.transparent);
                r5.setImageResource(android.R.color.transparent);
                r6.setImageResource(android.R.color.transparent);
                r7.setImageResource(android.R.color.transparent);
                r8.setImageResource(android.R.color.transparent);
                r10.setImageResource(android.R.color.transparent);
                r11.setImageResource(android.R.color.transparent);
                r12.setImageResource(android.R.color.transparent);
                r13.setImageResource(android.R.color.transparent);
                r14.setImageResource(android.R.color.transparent);
                r15.setImageResource(android.R.color.transparent);
                r16.setImageResource(android.R.color.transparent);
                r17.setImageResource(android.R.color.transparent);
                r18.setImageResource(android.R.color.transparent);
                r19.setImageResource(android.R.color.transparent);
                r20.setImageResource(android.R.color.transparent);
                r21.setImageResource(android.R.color.transparent);
                r22.setImageResource(android.R.color.transparent);
                r23.setImageResource(android.R.color.transparent);
                r24.setImageResource(android.R.color.transparent);
                r25.setImageResource(android.R.color.transparent);
                r26.setImageResource(android.R.color.transparent);
                r27.setImageResource(android.R.color.transparent);
                r28.setImageResource(android.R.color.transparent);
                r29.setImageResource(android.R.color.transparent);
                r30.setImageResource(android.R.color.transparent);
                r31.setImageResource(android.R.color.transparent);
                r32.setImageResource(android.R.color.transparent);

            }
        }
    };
    View.OnClickListener press10=new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            MainActivity.mViewPager.setBackgroundColor(Color.parseColor("#cddc39"));
            settings_bg.setBackgroundColor(Color.parseColor("#cddc39"));

            r10.setBackgroundResource(R.drawable.non10);
            r10.setImageResource(android.R.color.transparent);

            if (r10.getDrawable().getConstantState() == getResources().getDrawable(R.drawable.tick).getConstantState()) {
                r10.setBackgroundResource(R.drawable.non10);
                r10.setImageResource(android.R.color.transparent);
            } else {
                r1.setBackgroundResource(R.drawable.griditem);
                r2.setBackgroundResource(R.drawable.non2);
                r3.setBackgroundResource(R.drawable.non3);
                r5.setBackgroundResource(R.drawable.non5);
                r6.setBackgroundResource(R.drawable.non6);
                r4.setBackgroundResource(R.drawable.non4);
                r8.setBackgroundResource(R.drawable.non8);
                r9.setBackgroundResource(R.drawable.non9);
                r7.setBackgroundResource(R.drawable.non7);
                r11.setBackgroundResource(R.drawable.non11);
                r12.setBackgroundResource(R.drawable.non12);
                r13.setBackgroundResource(R.drawable.non13);
                r14.setBackgroundResource(R.drawable.non14);
                r15.setBackgroundResource(R.drawable.non15);
                r16.setBackgroundResource(R.drawable.non16);
                r17.setBackgroundResource(R.drawable.non17);
                r18.setBackgroundResource(R.drawable.non18);
                r19.setBackgroundResource(R.drawable.non19);
                r20.setBackgroundResource(R.drawable.non20);
                r21.setBackgroundResource(R.drawable.non21);
                r22.setBackgroundResource(R.drawable.non22);
                r23.setBackgroundResource(R.drawable.non23);
                r24.setBackgroundResource(R.drawable.non24);
                r25.setBackgroundResource(R.drawable.non25);
                r26.setBackgroundResource(R.drawable.non26);
                r27.setBackgroundResource(R.drawable.non27);
                r28.setBackgroundResource(R.drawable.non28);
                r29.setBackgroundResource(R.drawable.non29);
                r30.setBackgroundResource(R.drawable.non30);
                r31.setBackgroundResource(R.drawable.non31);
                r32.setBackgroundResource(R.drawable.non32);

                r10.setImageResource(R.drawable.tick);
                r10.setBackgroundResource(R.drawable.bor10);

                r1.setImageResource(android.R.color.transparent);
                r2.setImageResource(android.R.color.transparent);
                r3.setImageResource(android.R.color.transparent);
                r4.setImageResource(android.R.color.transparent);
                r5.setImageResource(android.R.color.transparent);
                r6.setImageResource(android.R.color.transparent);
                r7.setImageResource(android.R.color.transparent);
                r8.setImageResource(android.R.color.transparent);
                r9.setImageResource(android.R.color.transparent);
                r11.setImageResource(android.R.color.transparent);
                r12.setImageResource(android.R.color.transparent);
                r13.setImageResource(android.R.color.transparent);
                r14.setImageResource(android.R.color.transparent);
                r15.setImageResource(android.R.color.transparent);
                r16.setImageResource(android.R.color.transparent);
                r17.setImageResource(android.R.color.transparent);
                r18.setImageResource(android.R.color.transparent);
                r19.setImageResource(android.R.color.transparent);
                r20.setImageResource(android.R.color.transparent);
                r21.setImageResource(android.R.color.transparent);
                r22.setImageResource(android.R.color.transparent);
                r23.setImageResource(android.R.color.transparent);
                r24.setImageResource(android.R.color.transparent);
                r25.setImageResource(android.R.color.transparent);
                r26.setImageResource(android.R.color.transparent);
                r27.setImageResource(android.R.color.transparent);
                r28.setImageResource(android.R.color.transparent);
                r29.setImageResource(android.R.color.transparent);
                r30.setImageResource(android.R.color.transparent);
                r31.setImageResource(android.R.color.transparent);
                r32.setImageResource(android.R.color.transparent);

            }
        }
    };
    View.OnClickListener press11=new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            MainActivity.mViewPager.setBackgroundColor(Color.parseColor("#6a1b9a"));
            settings_bg.setBackgroundColor(Color.parseColor("#6a1b9a"));

            r11.setBackgroundResource(R.drawable.non11);
            r11.setImageResource(android.R.color.transparent);

            if (r11.getDrawable().getConstantState() == getResources().getDrawable(R.drawable.tick).getConstantState()) {
                r11.setBackgroundResource(R.drawable.non11);
                r11.setImageResource(android.R.color.transparent);
            } else {
                r1.setBackgroundResource(R.drawable.griditem);
                r2.setBackgroundResource(R.drawable.non2);
                r3.setBackgroundResource(R.drawable.non3);
                r5.setBackgroundResource(R.drawable.non5);
                r6.setBackgroundResource(R.drawable.non6);
                r4.setBackgroundResource(R.drawable.non4);
                r8.setBackgroundResource(R.drawable.non8);
                r9.setBackgroundResource(R.drawable.non9);
                r10.setBackgroundResource(R.drawable.non10);
                r7.setBackgroundResource(R.drawable.non7);
                r12.setBackgroundResource(R.drawable.non12);
                r13.setBackgroundResource(R.drawable.non13);
                r14.setBackgroundResource(R.drawable.non14);
                r15.setBackgroundResource(R.drawable.non15);
                r16.setBackgroundResource(R.drawable.non16);
                r17.setBackgroundResource(R.drawable.non17);
                r18.setBackgroundResource(R.drawable.non18);
                r19.setBackgroundResource(R.drawable.non19);
                r20.setBackgroundResource(R.drawable.non20);
                r21.setBackgroundResource(R.drawable.non21);
                r22.setBackgroundResource(R.drawable.non22);
                r23.setBackgroundResource(R.drawable.non23);
                r24.setBackgroundResource(R.drawable.non24);
                r25.setBackgroundResource(R.drawable.non25);
                r26.setBackgroundResource(R.drawable.non26);
                r27.setBackgroundResource(R.drawable.non27);
                r28.setBackgroundResource(R.drawable.non28);
                r29.setBackgroundResource(R.drawable.non29);
                r30.setBackgroundResource(R.drawable.non30);
                r31.setBackgroundResource(R.drawable.non31);
                r32.setBackgroundResource(R.drawable.non32);

                r11.setImageResource(R.drawable.tick);
                r11.setBackgroundResource(R.drawable.bor11);

                r1.setImageResource(android.R.color.transparent);
                r2.setImageResource(android.R.color.transparent);
                r3.setImageResource(android.R.color.transparent);
                r4.setImageResource(android.R.color.transparent);
                r5.setImageResource(android.R.color.transparent);
                r6.setImageResource(android.R.color.transparent);
                r7.setImageResource(android.R.color.transparent);
                r8.setImageResource(android.R.color.transparent);
                r9.setImageResource(android.R.color.transparent);
                r10.setImageResource(android.R.color.transparent);
                r12.setImageResource(android.R.color.transparent);
                r13.setImageResource(android.R.color.transparent);
                r14.setImageResource(android.R.color.transparent);
                r15.setImageResource(android.R.color.transparent);
                r16.setImageResource(android.R.color.transparent);
                r17.setImageResource(android.R.color.transparent);
                r18.setImageResource(android.R.color.transparent);
                r19.setImageResource(android.R.color.transparent);
                r20.setImageResource(android.R.color.transparent);
                r21.setImageResource(android.R.color.transparent);
                r22.setImageResource(android.R.color.transparent);
                r23.setImageResource(android.R.color.transparent);
                r24.setImageResource(android.R.color.transparent);
                r25.setImageResource(android.R.color.transparent);
                r26.setImageResource(android.R.color.transparent);
                r27.setImageResource(android.R.color.transparent);
                r28.setImageResource(android.R.color.transparent);
                r29.setImageResource(android.R.color.transparent);
                r30.setImageResource(android.R.color.transparent);
                r31.setImageResource(android.R.color.transparent);
                r32.setImageResource(android.R.color.transparent);
            }
        }
    };
    View.OnClickListener press12=new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            MainActivity.mViewPager.setBackgroundColor(Color.parseColor("#9e9d25"));
            settings_bg.setBackgroundColor(Color.parseColor("#9e9d25"));

            r12.setBackgroundResource(R.drawable.non12);
            r12.setImageResource(android.R.color.transparent);

            if (r12.getDrawable().getConstantState() == getResources().getDrawable(R.drawable.tick).getConstantState()) {
                r12.setBackgroundResource(R.drawable.non12);
                r12.setImageResource(android.R.color.transparent);
            } else {
                r1.setBackgroundResource(R.drawable.griditem);
                r2.setBackgroundResource(R.drawable.non2);
                r3.setBackgroundResource(R.drawable.non3);
                r5.setBackgroundResource(R.drawable.non5);
                r6.setBackgroundResource(R.drawable.non6);
                r4.setBackgroundResource(R.drawable.non4);
                r8.setBackgroundResource(R.drawable.non8);
                r9.setBackgroundResource(R.drawable.non9);
                r10.setBackgroundResource(R.drawable.non10);
                r11.setBackgroundResource(R.drawable.non11);
                r7.setBackgroundResource(R.drawable.non7);
                r13.setBackgroundResource(R.drawable.non13);
                r14.setBackgroundResource(R.drawable.non14);
                r15.setBackgroundResource(R.drawable.non15);
                r16.setBackgroundResource(R.drawable.non16);
                r17.setBackgroundResource(R.drawable.non17);
                r18.setBackgroundResource(R.drawable.non18);
                r19.setBackgroundResource(R.drawable.non19);
                r20.setBackgroundResource(R.drawable.non20);
                r21.setBackgroundResource(R.drawable.non21);
                r22.setBackgroundResource(R.drawable.non22);
                r23.setBackgroundResource(R.drawable.non23);
                r24.setBackgroundResource(R.drawable.non24);
                r25.setBackgroundResource(R.drawable.non25);
                r26.setBackgroundResource(R.drawable.non26);
                r27.setBackgroundResource(R.drawable.non27);
                r28.setBackgroundResource(R.drawable.non28);
                r29.setBackgroundResource(R.drawable.non29);
                r30.setBackgroundResource(R.drawable.non30);
                r31.setBackgroundResource(R.drawable.non31);
                r32.setBackgroundResource(R.drawable.non32);

                r12.setImageResource(R.drawable.tick);
                r12.setBackgroundResource(R.drawable.bor12);

                r1.setImageResource(android.R.color.transparent);
                r2.setImageResource(android.R.color.transparent);
                r3.setImageResource(android.R.color.transparent);
                r4.setImageResource(android.R.color.transparent);
                r5.setImageResource(android.R.color.transparent);
                r6.setImageResource(android.R.color.transparent);
                r7.setImageResource(android.R.color.transparent);
                r8.setImageResource(android.R.color.transparent);
                r9.setImageResource(android.R.color.transparent);
                r10.setImageResource(android.R.color.transparent);
                r11.setImageResource(android.R.color.transparent);
                r13.setImageResource(android.R.color.transparent);
                r14.setImageResource(android.R.color.transparent);
                r15.setImageResource(android.R.color.transparent);
                r16.setImageResource(android.R.color.transparent);
                r17.setImageResource(android.R.color.transparent);
                r18.setImageResource(android.R.color.transparent);
                r19.setImageResource(android.R.color.transparent);
                r20.setImageResource(android.R.color.transparent);
                r21.setImageResource(android.R.color.transparent);
                r22.setImageResource(android.R.color.transparent);
                r23.setImageResource(android.R.color.transparent);
                r24.setImageResource(android.R.color.transparent);
                r25.setImageResource(android.R.color.transparent);
                r26.setImageResource(android.R.color.transparent);
                r27.setImageResource(android.R.color.transparent);
                r28.setImageResource(android.R.color.transparent);
                r29.setImageResource(android.R.color.transparent);
                r30.setImageResource(android.R.color.transparent);
                r31.setImageResource(android.R.color.transparent);
                r32.setImageResource(android.R.color.transparent);
            }
        }
    };
    View.OnClickListener press13=new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            MainActivity.mViewPager.setBackgroundColor(Color.parseColor("#673ab7"));
            settings_bg.setBackgroundColor(Color.parseColor("#673ab7"));

            r13.setBackgroundResource(R.drawable.non13);
            r13.setImageResource(android.R.color.transparent);

            if (r13.getDrawable().getConstantState() == getResources().getDrawable(R.drawable.tick).getConstantState()) {
                r13.setBackgroundResource(R.drawable.non13);
                r13.setImageResource(android.R.color.transparent);
            } else {
                r1.setBackgroundResource(R.drawable.griditem);
                r2.setBackgroundResource(R.drawable.non2);
                r3.setBackgroundResource(R.drawable.non3);
                r5.setBackgroundResource(R.drawable.non5);
                r6.setBackgroundResource(R.drawable.non6);
                r4.setBackgroundResource(R.drawable.non4);
                r8.setBackgroundResource(R.drawable.non8);
                r9.setBackgroundResource(R.drawable.non9);
                r10.setBackgroundResource(R.drawable.non10);
                r11.setBackgroundResource(R.drawable.non11);
                r12.setBackgroundResource(R.drawable.non12);
                r7.setBackgroundResource(R.drawable.non7);
                r14.setBackgroundResource(R.drawable.non14);
                r15.setBackgroundResource(R.drawable.non15);
                r16.setBackgroundResource(R.drawable.non16);
                r17.setBackgroundResource(R.drawable.non17);
                r18.setBackgroundResource(R.drawable.non18);
                r19.setBackgroundResource(R.drawable.non19);
                r20.setBackgroundResource(R.drawable.non20);
                r21.setBackgroundResource(R.drawable.non21);
                r22.setBackgroundResource(R.drawable.non22);
                r23.setBackgroundResource(R.drawable.non23);
                r24.setBackgroundResource(R.drawable.non24);
                r25.setBackgroundResource(R.drawable.non25);
                r26.setBackgroundResource(R.drawable.non26);
                r27.setBackgroundResource(R.drawable.non27);
                r28.setBackgroundResource(R.drawable.non28);
                r29.setBackgroundResource(R.drawable.non29);
                r30.setBackgroundResource(R.drawable.non30);
                r31.setBackgroundResource(R.drawable.non31);
                r32.setBackgroundResource(R.drawable.non32);

                r13.setImageResource(R.drawable.tick);
                r13.setBackgroundResource(R.drawable.bore13);

                r1.setImageResource(android.R.color.transparent);
                r2.setImageResource(android.R.color.transparent);
                r3.setImageResource(android.R.color.transparent);
                r4.setImageResource(android.R.color.transparent);
                r5.setImageResource(android.R.color.transparent);
                r6.setImageResource(android.R.color.transparent);
                r7.setImageResource(android.R.color.transparent);
                r8.setImageResource(android.R.color.transparent);
                r9.setImageResource(android.R.color.transparent);
                r10.setImageResource(android.R.color.transparent);
                r11.setImageResource(android.R.color.transparent);
                r12.setImageResource(android.R.color.transparent);
                r14.setImageResource(android.R.color.transparent);
                r15.setImageResource(android.R.color.transparent);
                r16.setImageResource(android.R.color.transparent);
                r17.setImageResource(android.R.color.transparent);
                r18.setImageResource(android.R.color.transparent);
                r19.setImageResource(android.R.color.transparent);
                r20.setImageResource(android.R.color.transparent);
                r21.setImageResource(android.R.color.transparent);
                r22.setImageResource(android.R.color.transparent);
                r23.setImageResource(android.R.color.transparent);
                r24.setImageResource(android.R.color.transparent);
                r25.setImageResource(android.R.color.transparent);
                r26.setImageResource(android.R.color.transparent);
                r27.setImageResource(android.R.color.transparent);
                r28.setImageResource(android.R.color.transparent);
                r29.setImageResource(android.R.color.transparent);
                r30.setImageResource(android.R.color.transparent);
                r31.setImageResource(android.R.color.transparent);
                r32.setImageResource(android.R.color.transparent);

            }
        }
    };
    View.OnClickListener press14=new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            MainActivity.mViewPager.setBackgroundColor(Color.parseColor("#ffeb3b"));
            settings_bg.setBackgroundColor(Color.parseColor("#ffeb3b"));

            r14.setBackgroundResource(R.drawable.non14);
            r14.setImageResource(android.R.color.transparent);

            if (r14.getDrawable().getConstantState() == getResources().getDrawable(R.drawable.tick).getConstantState()) {
                r14.setBackgroundResource(R.drawable.non14);
                r14.setImageResource(android.R.color.transparent);
            } else {
                r1.setBackgroundResource(R.drawable.griditem);
                r2.setBackgroundResource(R.drawable.non2);
                r3.setBackgroundResource(R.drawable.non3);
                r5.setBackgroundResource(R.drawable.non5);
                r6.setBackgroundResource(R.drawable.non6);
                r4.setBackgroundResource(R.drawable.non4);
                r8.setBackgroundResource(R.drawable.non8);
                r9.setBackgroundResource(R.drawable.non9);
                r10.setBackgroundResource(R.drawable.non10);
                r11.setBackgroundResource(R.drawable.non11);
                r12.setBackgroundResource(R.drawable.non12);
                r13.setBackgroundResource(R.drawable.non13);
                r7.setBackgroundResource(R.drawable.non7);
                r15.setBackgroundResource(R.drawable.non15);
                r16.setBackgroundResource(R.drawable.non16);
                r17.setBackgroundResource(R.drawable.non17);
                r18.setBackgroundResource(R.drawable.non18);
                r19.setBackgroundResource(R.drawable.non19);
                r20.setBackgroundResource(R.drawable.non20);
                r21.setBackgroundResource(R.drawable.non21);
                r22.setBackgroundResource(R.drawable.non22);
                r23.setBackgroundResource(R.drawable.non23);
                r24.setBackgroundResource(R.drawable.non24);
                r25.setBackgroundResource(R.drawable.non25);
                r26.setBackgroundResource(R.drawable.non26);
                r27.setBackgroundResource(R.drawable.non27);
                r28.setBackgroundResource(R.drawable.non28);
                r29.setBackgroundResource(R.drawable.non29);
                r30.setBackgroundResource(R.drawable.non30);
                r31.setBackgroundResource(R.drawable.non31);
                r32.setBackgroundResource(R.drawable.non32);

                r14.setImageResource(R.drawable.tick);
                r14.setBackgroundResource(R.drawable.bor14);

                r1.setImageResource(android.R.color.transparent);
                r2.setImageResource(android.R.color.transparent);
                r3.setImageResource(android.R.color.transparent);
                r4.setImageResource(android.R.color.transparent);
                r5.setImageResource(android.R.color.transparent);
                r6.setImageResource(android.R.color.transparent);
                r7.setImageResource(android.R.color.transparent);
                r8.setImageResource(android.R.color.transparent);
                r9.setImageResource(android.R.color.transparent);
                r10.setImageResource(android.R.color.transparent);
                r11.setImageResource(android.R.color.transparent);
                r12.setImageResource(android.R.color.transparent);
                r13.setImageResource(android.R.color.transparent);
                r15.setImageResource(android.R.color.transparent);
                r16.setImageResource(android.R.color.transparent);
                r17.setImageResource(android.R.color.transparent);
                r18.setImageResource(android.R.color.transparent);
                r19.setImageResource(android.R.color.transparent);
                r20.setImageResource(android.R.color.transparent);
                r21.setImageResource(android.R.color.transparent);
                r22.setImageResource(android.R.color.transparent);
                r23.setImageResource(android.R.color.transparent);
                r24.setImageResource(android.R.color.transparent);
                r25.setImageResource(android.R.color.transparent);
                r26.setImageResource(android.R.color.transparent);
                r27.setImageResource(android.R.color.transparent);
                r28.setImageResource(android.R.color.transparent);
                r29.setImageResource(android.R.color.transparent);
                r30.setImageResource(android.R.color.transparent);
                r31.setImageResource(android.R.color.transparent);
                r32.setImageResource(android.R.color.transparent);
            }
        }
    };
    View.OnClickListener press15=new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            MainActivity.mViewPager.setBackgroundColor(Color.parseColor("#4527a0"));
            settings_bg.setBackgroundColor(Color.parseColor("#4527a0"));

            r15.setBackgroundResource(R.drawable.non15);
            r15.setImageResource(android.R.color.transparent);

            if (r15.getDrawable().getConstantState() == getResources().getDrawable(R.drawable.tick).getConstantState()) {
                r15.setBackgroundResource(R.drawable.non15);
                r15.setImageResource(android.R.color.transparent);
            } else {
                r1.setBackgroundResource(R.drawable.griditem);
                r2.setBackgroundResource(R.drawable.non2);
                r3.setBackgroundResource(R.drawable.non3);
                r5.setBackgroundResource(R.drawable.non5);
                r6.setBackgroundResource(R.drawable.non6);
                r4.setBackgroundResource(R.drawable.non4);
                r8.setBackgroundResource(R.drawable.non8);
                r9.setBackgroundResource(R.drawable.non9);
                r10.setBackgroundResource(R.drawable.non10);
                r11.setBackgroundResource(R.drawable.non11);
                r12.setBackgroundResource(R.drawable.non12);
                r13.setBackgroundResource(R.drawable.non13);
                r14.setBackgroundResource(R.drawable.non14);
                r7.setBackgroundResource(R.drawable.non7);
                r16.setBackgroundResource(R.drawable.non16);
                r17.setBackgroundResource(R.drawable.non17);
                r18.setBackgroundResource(R.drawable.non18);
                r19.setBackgroundResource(R.drawable.non19);
                r20.setBackgroundResource(R.drawable.non20);
                r21.setBackgroundResource(R.drawable.non21);
                r22.setBackgroundResource(R.drawable.non22);
                r23.setBackgroundResource(R.drawable.non23);
                r24.setBackgroundResource(R.drawable.non24);
                r25.setBackgroundResource(R.drawable.non25);
                r26.setBackgroundResource(R.drawable.non26);
                r27.setBackgroundResource(R.drawable.non27);
                r28.setBackgroundResource(R.drawable.non28);
                r29.setBackgroundResource(R.drawable.non29);
                r30.setBackgroundResource(R.drawable.non30);
                r31.setBackgroundResource(R.drawable.non31);
                r32.setBackgroundResource(R.drawable.non32);

                r15.setImageResource(R.drawable.tick);
                r15.setBackgroundResource(R.drawable.bor15);
                r1.setImageResource(android.R.color.transparent);
                r2.setImageResource(android.R.color.transparent);
                r3.setImageResource(android.R.color.transparent);
                r4.setImageResource(android.R.color.transparent);
                r5.setImageResource(android.R.color.transparent);
                r6.setImageResource(android.R.color.transparent);
                r7.setImageResource(android.R.color.transparent);
                r8.setImageResource(android.R.color.transparent);
                r9.setImageResource(android.R.color.transparent);
                r10.setImageResource(android.R.color.transparent);
                r11.setImageResource(android.R.color.transparent);
                r12.setImageResource(android.R.color.transparent);
                r13.setImageResource(android.R.color.transparent);
                r14.setImageResource(android.R.color.transparent);
                r16.setImageResource(android.R.color.transparent);
                r17.setImageResource(android.R.color.transparent);
                r18.setImageResource(android.R.color.transparent);
                r19.setImageResource(android.R.color.transparent);
                r20.setImageResource(android.R.color.transparent);
                r21.setImageResource(android.R.color.transparent);
                r22.setImageResource(android.R.color.transparent);
                r23.setImageResource(android.R.color.transparent);
                r24.setImageResource(android.R.color.transparent);
                r25.setImageResource(android.R.color.transparent);
                r26.setImageResource(android.R.color.transparent);
                r27.setImageResource(android.R.color.transparent);
                r28.setImageResource(android.R.color.transparent);
                r29.setImageResource(android.R.color.transparent);
                r30.setImageResource(android.R.color.transparent);
                r31.setImageResource(android.R.color.transparent);
                r32.setImageResource(android.R.color.transparent);

            }
        }
    };
    View.OnClickListener press16=new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            MainActivity.mViewPager.setBackgroundColor(Color.parseColor("#f9a825"));
            settings_bg.setBackgroundColor(Color.parseColor("#f9a825"));

            r16.setBackgroundResource(R.drawable.non16);
            r16.setImageResource(android.R.color.transparent);

            if (r16.getDrawable().getConstantState() == getResources().getDrawable(R.drawable.tick).getConstantState()) {
                r16.setBackgroundResource(R.drawable.non16);
                r16.setImageResource(android.R.color.transparent);
            } else {
                r1.setBackgroundResource(R.drawable.griditem);
                r2.setBackgroundResource(R.drawable.non2);
                r3.setBackgroundResource(R.drawable.non3);
                r5.setBackgroundResource(R.drawable.non5);
                r6.setBackgroundResource(R.drawable.non6);
                r4.setBackgroundResource(R.drawable.non4);
                r8.setBackgroundResource(R.drawable.non8);
                r9.setBackgroundResource(R.drawable.non9);
                r10.setBackgroundResource(R.drawable.non10);
                r11.setBackgroundResource(R.drawable.non11);
                r12.setBackgroundResource(R.drawable.non12);
                r13.setBackgroundResource(R.drawable.non13);
                r14.setBackgroundResource(R.drawable.non14);
                r15.setBackgroundResource(R.drawable.non15);
                r7.setBackgroundResource(R.drawable.non7);
                r17.setBackgroundResource(R.drawable.non17);
                r18.setBackgroundResource(R.drawable.non18);
                r19.setBackgroundResource(R.drawable.non19);
                r20.setBackgroundResource(R.drawable.non20);
                r21.setBackgroundResource(R.drawable.non21);
                r22.setBackgroundResource(R.drawable.non22);
                r23.setBackgroundResource(R.drawable.non23);
                r24.setBackgroundResource(R.drawable.non24);
                r25.setBackgroundResource(R.drawable.non25);
                r26.setBackgroundResource(R.drawable.non26);
                r27.setBackgroundResource(R.drawable.non27);
                r28.setBackgroundResource(R.drawable.non28);
                r29.setBackgroundResource(R.drawable.non29);
                r30.setBackgroundResource(R.drawable.non30);
                r31.setBackgroundResource(R.drawable.non31);
                r32.setBackgroundResource(R.drawable.non32);

                r16.setImageResource(R.drawable.tick);
                r16.setBackgroundResource(R.drawable.bor16);

                r1.setImageResource(android.R.color.transparent);
                r2.setImageResource(android.R.color.transparent);
                r3.setImageResource(android.R.color.transparent);
                r4.setImageResource(android.R.color.transparent);
                r5.setImageResource(android.R.color.transparent);
                r6.setImageResource(android.R.color.transparent);
                r7.setImageResource(android.R.color.transparent);
                r8.setImageResource(android.R.color.transparent);
                r9.setImageResource(android.R.color.transparent);
                r10.setImageResource(android.R.color.transparent);
                r11.setImageResource(android.R.color.transparent);
                r12.setImageResource(android.R.color.transparent);
                r13.setImageResource(android.R.color.transparent);
                r14.setImageResource(android.R.color.transparent);
                r15.setImageResource(android.R.color.transparent);
                r17.setImageResource(android.R.color.transparent);
                r18.setImageResource(android.R.color.transparent);
                r19.setImageResource(android.R.color.transparent);
                r20.setImageResource(android.R.color.transparent);
                r21.setImageResource(android.R.color.transparent);
                r22.setImageResource(android.R.color.transparent);
                r23.setImageResource(android.R.color.transparent);
                r24.setImageResource(android.R.color.transparent);
                r25.setImageResource(android.R.color.transparent);
                r26.setImageResource(android.R.color.transparent);
                r27.setImageResource(android.R.color.transparent);
                r28.setImageResource(android.R.color.transparent);
                r29.setImageResource(android.R.color.transparent);
                r30.setImageResource(android.R.color.transparent);
                r31.setImageResource(android.R.color.transparent);
                r32.setImageResource(android.R.color.transparent);

            }
        }
    };
    View.OnClickListener press17=new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            MainActivity.mViewPager.setBackgroundColor(Color.parseColor("#3f51b5"));
            settings_bg.setBackgroundColor(Color.parseColor("#3f51b5"));

            r17.setBackgroundResource(R.drawable.non17);
            r17.setImageResource(android.R.color.transparent);

            if (r17.getDrawable().getConstantState() == getResources().getDrawable(R.drawable.tick).getConstantState()) {
                r17.setBackgroundResource(R.drawable.non17);
                r17.setImageResource(android.R.color.transparent);
            } else {
                r1.setBackgroundResource(R.drawable.griditem);
                r2.setBackgroundResource(R.drawable.non2);
                r3.setBackgroundResource(R.drawable.non3);
                r5.setBackgroundResource(R.drawable.non5);
                r6.setBackgroundResource(R.drawable.non6);
                r4.setBackgroundResource(R.drawable.non4);
                r8.setBackgroundResource(R.drawable.non8);
                r9.setBackgroundResource(R.drawable.non9);
                r10.setBackgroundResource(R.drawable.non10);
                r11.setBackgroundResource(R.drawable.non11);
                r12.setBackgroundResource(R.drawable.non12);
                r13.setBackgroundResource(R.drawable.non13);
                r14.setBackgroundResource(R.drawable.non14);
                r15.setBackgroundResource(R.drawable.non15);
                r16.setBackgroundResource(R.drawable.non16);
                r7.setBackgroundResource(R.drawable.non7);
                r18.setBackgroundResource(R.drawable.non18);
                r19.setBackgroundResource(R.drawable.non19);
                r20.setBackgroundResource(R.drawable.non20);
                r21.setBackgroundResource(R.drawable.non21);
                r22.setBackgroundResource(R.drawable.non22);
                r23.setBackgroundResource(R.drawable.non23);
                r24.setBackgroundResource(R.drawable.non24);
                r25.setBackgroundResource(R.drawable.non25);
                r26.setBackgroundResource(R.drawable.non26);
                r27.setBackgroundResource(R.drawable.non27);
                r28.setBackgroundResource(R.drawable.non28);
                r29.setBackgroundResource(R.drawable.non29);
                r30.setBackgroundResource(R.drawable.non30);
                r31.setBackgroundResource(R.drawable.non31);
                r32.setBackgroundResource(R.drawable.non32);

                r17.setImageResource(R.drawable.tick);
                r17.setBackgroundResource(R.drawable.bor17);

                r1.setImageResource(android.R.color.transparent);
                r2.setImageResource(android.R.color.transparent);
                r3.setImageResource(android.R.color.transparent);
                r4.setImageResource(android.R.color.transparent);
                r5.setImageResource(android.R.color.transparent);
                r6.setImageResource(android.R.color.transparent);
                r7.setImageResource(android.R.color.transparent);
                r8.setImageResource(android.R.color.transparent);
                r9.setImageResource(android.R.color.transparent);
                r10.setImageResource(android.R.color.transparent);
                r11.setImageResource(android.R.color.transparent);
                r12.setImageResource(android.R.color.transparent);
                r13.setImageResource(android.R.color.transparent);
                r14.setImageResource(android.R.color.transparent);
                r15.setImageResource(android.R.color.transparent);
                r16.setImageResource(android.R.color.transparent);
                r18.setImageResource(android.R.color.transparent);
                r19.setImageResource(android.R.color.transparent);
                r20.setImageResource(android.R.color.transparent);
                r21.setImageResource(android.R.color.transparent);
                r22.setImageResource(android.R.color.transparent);
                r23.setImageResource(android.R.color.transparent);
                r24.setImageResource(android.R.color.transparent);
                r25.setImageResource(android.R.color.transparent);
                r26.setImageResource(android.R.color.transparent);
                r27.setImageResource(android.R.color.transparent);
                r28.setImageResource(android.R.color.transparent);
                r29.setImageResource(android.R.color.transparent);
                r30.setImageResource(android.R.color.transparent);
                r31.setImageResource(android.R.color.transparent);
                r32.setImageResource(android.R.color.transparent);

            }
        }
    };
    View.OnClickListener press18=new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            MainActivity.mViewPager.setBackgroundColor(Color.parseColor("#ffc108"));
            settings_bg.setBackgroundColor(Color.parseColor("#ffc108"));
            r18.setBackgroundResource(R.drawable.non18);
            r18.setImageResource(android.R.color.transparent);

            if (r18.getDrawable().getConstantState() == getResources().getDrawable(R.drawable.tick).getConstantState()) {
                r18.setBackgroundResource(R.drawable.non18);
                r18.setImageResource(android.R.color.transparent);
            } else {
                r1.setBackgroundResource(R.drawable.griditem);
                r2.setBackgroundResource(R.drawable.non2);
                r3.setBackgroundResource(R.drawable.non3);
                r5.setBackgroundResource(R.drawable.non5);
                r6.setBackgroundResource(R.drawable.non6);
                r4.setBackgroundResource(R.drawable.non4);
                r8.setBackgroundResource(R.drawable.non8);
                r9.setBackgroundResource(R.drawable.non9);
                r10.setBackgroundResource(R.drawable.non10);
                r11.setBackgroundResource(R.drawable.non11);
                r12.setBackgroundResource(R.drawable.non12);
                r13.setBackgroundResource(R.drawable.non13);
                r14.setBackgroundResource(R.drawable.non14);
                r15.setBackgroundResource(R.drawable.non15);
                r16.setBackgroundResource(R.drawable.non16);
                r17.setBackgroundResource(R.drawable.non17);
                r7.setBackgroundResource(R.drawable.non7);
                r19.setBackgroundResource(R.drawable.non19);
                r20.setBackgroundResource(R.drawable.non20);
                r21.setBackgroundResource(R.drawable.non21);
                r22.setBackgroundResource(R.drawable.non22);
                r23.setBackgroundResource(R.drawable.non23);
                r24.setBackgroundResource(R.drawable.non24);
                r25.setBackgroundResource(R.drawable.non25);
                r26.setBackgroundResource(R.drawable.non26);
                r27.setBackgroundResource(R.drawable.non27);
                r28.setBackgroundResource(R.drawable.non28);
                r29.setBackgroundResource(R.drawable.non29);
                r30.setBackgroundResource(R.drawable.non30);
                r31.setBackgroundResource(R.drawable.non31);
                r32.setBackgroundResource(R.drawable.non32);

                r18.setImageResource(R.drawable.tick);
                r18.setBackgroundResource(R.drawable.bor18);

                r1.setImageResource(android.R.color.transparent);
                r2.setImageResource(android.R.color.transparent);
                r3.setImageResource(android.R.color.transparent);
                r4.setImageResource(android.R.color.transparent);
                r5.setImageResource(android.R.color.transparent);
                r6.setImageResource(android.R.color.transparent);
                r7.setImageResource(android.R.color.transparent);
                r8.setImageResource(android.R.color.transparent);
                r9.setImageResource(android.R.color.transparent);
                r10.setImageResource(android.R.color.transparent);
                r11.setImageResource(android.R.color.transparent);
                r12.setImageResource(android.R.color.transparent);
                r13.setImageResource(android.R.color.transparent);
                r14.setImageResource(android.R.color.transparent);
                r15.setImageResource(android.R.color.transparent);
                r16.setImageResource(android.R.color.transparent);
                r17.setImageResource(android.R.color.transparent);
                r19.setImageResource(android.R.color.transparent);
                r20.setImageResource(android.R.color.transparent);
                r21.setImageResource(android.R.color.transparent);
                r22.setImageResource(android.R.color.transparent);
                r23.setImageResource(android.R.color.transparent);
                r24.setImageResource(android.R.color.transparent);
                r25.setImageResource(android.R.color.transparent);
                r26.setImageResource(android.R.color.transparent);
                r27.setImageResource(android.R.color.transparent);
                r28.setImageResource(android.R.color.transparent);
                r29.setImageResource(android.R.color.transparent);
                r30.setImageResource(android.R.color.transparent);
                r31.setImageResource(android.R.color.transparent);
                r32.setImageResource(android.R.color.transparent);

            }
        }
    };
    View.OnClickListener press19=new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            MainActivity.mViewPager.setBackgroundColor(Color.parseColor("#283593"));
            settings_bg.setBackgroundColor(Color.parseColor("#283593"));
            r19.setBackgroundResource(R.drawable.non19);
            r19.setImageResource(android.R.color.transparent);

            if (r19.getDrawable().getConstantState() == getResources().getDrawable(R.drawable.tick).getConstantState()) {
                r19.setBackgroundResource(R.drawable.non19);
                r19.setImageResource(android.R.color.transparent);
            } else {
                r1.setBackgroundResource(R.drawable.griditem);
                r2.setBackgroundResource(R.drawable.non2);
                r3.setBackgroundResource(R.drawable.non3);
                r5.setBackgroundResource(R.drawable.non5);
                r6.setBackgroundResource(R.drawable.non6);
                r4.setBackgroundResource(R.drawable.non4);
                r8.setBackgroundResource(R.drawable.non8);
                r9.setBackgroundResource(R.drawable.non9);
                r10.setBackgroundResource(R.drawable.non10);
                r11.setBackgroundResource(R.drawable.non11);
                r12.setBackgroundResource(R.drawable.non12);
                r13.setBackgroundResource(R.drawable.non13);
                r14.setBackgroundResource(R.drawable.non14);
                r15.setBackgroundResource(R.drawable.non15);
                r16.setBackgroundResource(R.drawable.non16);
                r17.setBackgroundResource(R.drawable.non17);
                r18.setBackgroundResource(R.drawable.non18);
                r7.setBackgroundResource(R.drawable.non7);
                r20.setBackgroundResource(R.drawable.non20);
                r21.setBackgroundResource(R.drawable.non21);
                r22.setBackgroundResource(R.drawable.non22);
                r23.setBackgroundResource(R.drawable.non23);
                r24.setBackgroundResource(R.drawable.non24);
                r25.setBackgroundResource(R.drawable.non25);
                r26.setBackgroundResource(R.drawable.non26);
                r27.setBackgroundResource(R.drawable.non27);
                r28.setBackgroundResource(R.drawable.non28);
                r29.setBackgroundResource(R.drawable.non29);
                r30.setBackgroundResource(R.drawable.non30);
                r31.setBackgroundResource(R.drawable.non31);
                r32.setBackgroundResource(R.drawable.non32);

                r19.setImageResource(R.drawable.tick);
                r19.setBackgroundResource(R.drawable.bor19);

                r1.setImageResource(android.R.color.transparent);
                r2.setImageResource(android.R.color.transparent);
                r3.setImageResource(android.R.color.transparent);
                r4.setImageResource(android.R.color.transparent);
                r5.setImageResource(android.R.color.transparent);
                r6.setImageResource(android.R.color.transparent);
                r7.setImageResource(android.R.color.transparent);
                r8.setImageResource(android.R.color.transparent);
                r9.setImageResource(android.R.color.transparent);
                r10.setImageResource(android.R.color.transparent);
                r11.setImageResource(android.R.color.transparent);
                r12.setImageResource(android.R.color.transparent);
                r13.setImageResource(android.R.color.transparent);
                r14.setImageResource(android.R.color.transparent);
                r15.setImageResource(android.R.color.transparent);
                r16.setImageResource(android.R.color.transparent);
                r17.setImageResource(android.R.color.transparent);
                r18.setImageResource(android.R.color.transparent);
                r20.setImageResource(android.R.color.transparent);
                r21.setImageResource(android.R.color.transparent);
                r22.setImageResource(android.R.color.transparent);
                r23.setImageResource(android.R.color.transparent);
                r24.setImageResource(android.R.color.transparent);
                r25.setImageResource(android.R.color.transparent);
                r26.setImageResource(android.R.color.transparent);
                r27.setImageResource(android.R.color.transparent);
                r28.setImageResource(android.R.color.transparent);
                r29.setImageResource(android.R.color.transparent);
                r30.setImageResource(android.R.color.transparent);
                r31.setImageResource(android.R.color.transparent);
                r32.setImageResource(android.R.color.transparent);

            }

        }
    };
    View.OnClickListener press20=new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            MainActivity.mViewPager.setBackgroundColor(Color.parseColor("#ff8f00"));
            settings_bg.setBackgroundColor(Color.parseColor("#ff8f00"));
            r20.setBackgroundResource(R.drawable.non20);
            r20.setImageResource(android.R.color.transparent);

            if (r20.getDrawable().getConstantState() == getResources().getDrawable(R.drawable.tick).getConstantState()) {
                r20.setBackgroundResource(R.drawable.non20);
                r20.setImageResource(android.R.color.transparent);
            } else {
                r1.setBackgroundResource(R.drawable.griditem);
                r2.setBackgroundResource(R.drawable.non2);
                r3.setBackgroundResource(R.drawable.non3);
                r5.setBackgroundResource(R.drawable.non5);
                r6.setBackgroundResource(R.drawable.non6);
                r4.setBackgroundResource(R.drawable.non4);
                r8.setBackgroundResource(R.drawable.non8);
                r9.setBackgroundResource(R.drawable.non9);
                r10.setBackgroundResource(R.drawable.non10);
                r11.setBackgroundResource(R.drawable.non11);
                r12.setBackgroundResource(R.drawable.non12);
                r13.setBackgroundResource(R.drawable.non13);
                r14.setBackgroundResource(R.drawable.non14);
                r15.setBackgroundResource(R.drawable.non15);
                r16.setBackgroundResource(R.drawable.non16);
                r17.setBackgroundResource(R.drawable.non17);
                r18.setBackgroundResource(R.drawable.non18);
                r19.setBackgroundResource(R.drawable.non19);
                r7.setBackgroundResource(R.drawable.non7);
                r21.setBackgroundResource(R.drawable.non21);
                r22.setBackgroundResource(R.drawable.non22);
                r23.setBackgroundResource(R.drawable.non23);
                r24.setBackgroundResource(R.drawable.non24);
                r25.setBackgroundResource(R.drawable.non25);
                r26.setBackgroundResource(R.drawable.non26);
                r27.setBackgroundResource(R.drawable.non27);
                r28.setBackgroundResource(R.drawable.non28);
                r29.setBackgroundResource(R.drawable.non29);
                r30.setBackgroundResource(R.drawable.non30);
                r31.setBackgroundResource(R.drawable.non31);
                r32.setBackgroundResource(R.drawable.non32);

                r20.setImageResource(R.drawable.tick);
                r20.setBackgroundResource(R.drawable.bor20);

                r1.setImageResource(android.R.color.transparent);
                r2.setImageResource(android.R.color.transparent);
                r3.setImageResource(android.R.color.transparent);
                r4.setImageResource(android.R.color.transparent);
                r5.setImageResource(android.R.color.transparent);
                r6.setImageResource(android.R.color.transparent);
                r7.setImageResource(android.R.color.transparent);
                r8.setImageResource(android.R.color.transparent);
                r9.setImageResource(android.R.color.transparent);
                r10.setImageResource(android.R.color.transparent);
                r11.setImageResource(android.R.color.transparent);
                r12.setImageResource(android.R.color.transparent);
                r13.setImageResource(android.R.color.transparent);
                r14.setImageResource(android.R.color.transparent);
                r15.setImageResource(android.R.color.transparent);
                r16.setImageResource(android.R.color.transparent);
                r17.setImageResource(android.R.color.transparent);
                r18.setImageResource(android.R.color.transparent);
                r19.setImageResource(android.R.color.transparent);
                r21.setImageResource(android.R.color.transparent);
                r22.setImageResource(android.R.color.transparent);
                r23.setImageResource(android.R.color.transparent);
                r24.setImageResource(android.R.color.transparent);
                r25.setImageResource(android.R.color.transparent);
                r26.setImageResource(android.R.color.transparent);
                r27.setImageResource(android.R.color.transparent);
                r28.setImageResource(android.R.color.transparent);
                r29.setImageResource(android.R.color.transparent);
                r30.setImageResource(android.R.color.transparent);
                r31.setImageResource(android.R.color.transparent);
                r32.setImageResource(android.R.color.transparent);

            }
        }
    };
    View.OnClickListener press21=new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            MainActivity.mViewPager.setBackgroundColor(Color.parseColor("#2096f3"));
            settings_bg.setBackgroundColor(Color.parseColor("#2096f3"));
            r21.setBackgroundResource(R.drawable.non21);
            r21.setImageResource(android.R.color.transparent);

            if (r21.getDrawable().getConstantState() == getResources().getDrawable(R.drawable.tick).getConstantState()) {
                r21.setBackgroundResource(R.drawable.non21);
                r21.setImageResource(android.R.color.transparent);
            } else {
                r1.setBackgroundResource(R.drawable.griditem);
                r2.setBackgroundResource(R.drawable.non2);
                r3.setBackgroundResource(R.drawable.non3);
                r5.setBackgroundResource(R.drawable.non5);
                r6.setBackgroundResource(R.drawable.non6);
                r4.setBackgroundResource(R.drawable.non4);
                r8.setBackgroundResource(R.drawable.non8);
                r9.setBackgroundResource(R.drawable.non9);
                r10.setBackgroundResource(R.drawable.non10);
                r11.setBackgroundResource(R.drawable.non11);
                r12.setBackgroundResource(R.drawable.non12);
                r13.setBackgroundResource(R.drawable.non13);
                r14.setBackgroundResource(R.drawable.non14);
                r15.setBackgroundResource(R.drawable.non15);
                r16.setBackgroundResource(R.drawable.non16);
                r17.setBackgroundResource(R.drawable.non17);
                r18.setBackgroundResource(R.drawable.non18);
                r19.setBackgroundResource(R.drawable.non19);
                r20.setBackgroundResource(R.drawable.non20);
                r7.setBackgroundResource(R.drawable.non7);
                r22.setBackgroundResource(R.drawable.non22);
                r23.setBackgroundResource(R.drawable.non23);
                r24.setBackgroundResource(R.drawable.non24);
                r25.setBackgroundResource(R.drawable.non25);
                r26.setBackgroundResource(R.drawable.non26);
                r27.setBackgroundResource(R.drawable.non27);
                r28.setBackgroundResource(R.drawable.non28);
                r29.setBackgroundResource(R.drawable.non29);
                r30.setBackgroundResource(R.drawable.non30);
                r31.setBackgroundResource(R.drawable.non31);
                r32.setBackgroundResource(R.drawable.non32);

                r21.setImageResource(R.drawable.tick);
                r21.setBackgroundResource(R.drawable.bor21);

                r1.setImageResource(android.R.color.transparent);
                r2.setImageResource(android.R.color.transparent);
                r3.setImageResource(android.R.color.transparent);
                r4.setImageResource(android.R.color.transparent);
                r5.setImageResource(android.R.color.transparent);
                r6.setImageResource(android.R.color.transparent);
                r7.setImageResource(android.R.color.transparent);
                r8.setImageResource(android.R.color.transparent);
                r9.setImageResource(android.R.color.transparent);
                r10.setImageResource(android.R.color.transparent);
                r11.setImageResource(android.R.color.transparent);
                r12.setImageResource(android.R.color.transparent);
                r13.setImageResource(android.R.color.transparent);
                r14.setImageResource(android.R.color.transparent);
                r15.setImageResource(android.R.color.transparent);
                r16.setImageResource(android.R.color.transparent);
                r17.setImageResource(android.R.color.transparent);
                r18.setImageResource(android.R.color.transparent);
                r19.setImageResource(android.R.color.transparent);
                r20.setImageResource(android.R.color.transparent);
                r22.setImageResource(android.R.color.transparent);
                r23.setImageResource(android.R.color.transparent);
                r24.setImageResource(android.R.color.transparent);
                r25.setImageResource(android.R.color.transparent);
                r26.setImageResource(android.R.color.transparent);
                r27.setImageResource(android.R.color.transparent);
                r28.setImageResource(android.R.color.transparent);
                r29.setImageResource(android.R.color.transparent);
                r30.setImageResource(android.R.color.transparent);
                r31.setImageResource(android.R.color.transparent);
                r32.setImageResource(android.R.color.transparent);

            }
        }
    };
    View.OnClickListener press22=new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            MainActivity.mViewPager.setBackgroundColor(Color.parseColor("#ff9802"));
            settings_bg.setBackgroundColor(Color.parseColor("#ff9802"));
            r22.setBackgroundResource(R.drawable.non22);
            r22.setImageResource(android.R.color.transparent);

            if (r22.getDrawable().getConstantState() == getResources().getDrawable(R.drawable.tick).getConstantState()) {
                r22.setBackgroundResource(R.drawable.non22);
                r22.setImageResource(android.R.color.transparent);
            } else {
                r1.setBackgroundResource(R.drawable.griditem);
                r2.setBackgroundResource(R.drawable.non2);
                r3.setBackgroundResource(R.drawable.non3);
                r5.setBackgroundResource(R.drawable.non5);
                r6.setBackgroundResource(R.drawable.non6);
                r4.setBackgroundResource(R.drawable.non4);
                r8.setBackgroundResource(R.drawable.non8);
                r9.setBackgroundResource(R.drawable.non9);
                r10.setBackgroundResource(R.drawable.non10);
                r11.setBackgroundResource(R.drawable.non11);
                r12.setBackgroundResource(R.drawable.non12);
                r13.setBackgroundResource(R.drawable.non13);
                r14.setBackgroundResource(R.drawable.non14);
                r15.setBackgroundResource(R.drawable.non15);
                r16.setBackgroundResource(R.drawable.non16);
                r17.setBackgroundResource(R.drawable.non17);
                r18.setBackgroundResource(R.drawable.non18);
                r19.setBackgroundResource(R.drawable.non19);
                r20.setBackgroundResource(R.drawable.non20);
                r21.setBackgroundResource(R.drawable.non21);
                r7.setBackgroundResource(R.drawable.non7);
                r23.setBackgroundResource(R.drawable.non23);
                r24.setBackgroundResource(R.drawable.non24);
                r25.setBackgroundResource(R.drawable.non25);
                r26.setBackgroundResource(R.drawable.non26);
                r27.setBackgroundResource(R.drawable.non27);
                r28.setBackgroundResource(R.drawable.non28);
                r29.setBackgroundResource(R.drawable.non29);
                r30.setBackgroundResource(R.drawable.non30);
                r31.setBackgroundResource(R.drawable.non31);
                r32.setBackgroundResource(R.drawable.non32);

                r22.setImageResource(R.drawable.tick);
                r22.setBackgroundResource(R.drawable.bor22);

                r1.setImageResource(android.R.color.transparent);
                r2.setImageResource(android.R.color.transparent);
                r3.setImageResource(android.R.color.transparent);
                r4.setImageResource(android.R.color.transparent);
                r5.setImageResource(android.R.color.transparent);
                r6.setImageResource(android.R.color.transparent);
                r7.setImageResource(android.R.color.transparent);
                r8.setImageResource(android.R.color.transparent);
                r9.setImageResource(android.R.color.transparent);
                r10.setImageResource(android.R.color.transparent);
                r11.setImageResource(android.R.color.transparent);
                r12.setImageResource(android.R.color.transparent);
                r13.setImageResource(android.R.color.transparent);
                r14.setImageResource(android.R.color.transparent);
                r15.setImageResource(android.R.color.transparent);
                r16.setImageResource(android.R.color.transparent);
                r17.setImageResource(android.R.color.transparent);
                r18.setImageResource(android.R.color.transparent);
                r19.setImageResource(android.R.color.transparent);
                r20.setImageResource(android.R.color.transparent);
                r21.setImageResource(android.R.color.transparent);
                r23.setImageResource(android.R.color.transparent);
                r24.setImageResource(android.R.color.transparent);
                r25.setImageResource(android.R.color.transparent);
                r26.setImageResource(android.R.color.transparent);
                r27.setImageResource(android.R.color.transparent);
                r28.setImageResource(android.R.color.transparent);
                r29.setImageResource(android.R.color.transparent);
                r30.setImageResource(android.R.color.transparent);
                r31.setImageResource(android.R.color.transparent);
                r32.setImageResource(android.R.color.transparent);

            }
        }
    };
    View.OnClickListener press23=new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            MainActivity.mViewPager.setBackgroundColor(Color.parseColor("#0277bd"));
            settings_bg.setBackgroundColor(Color.parseColor("#0277bd"));
            r23.setBackgroundResource(R.drawable.non23);
            r23.setImageResource(android.R.color.transparent);

            if (r23.getDrawable().getConstantState() == getResources().getDrawable(R.drawable.tick).getConstantState()) {
                r23.setBackgroundResource(R.drawable.non23);
                r23.setImageResource(android.R.color.transparent);
            } else {
                r1.setBackgroundResource(R.drawable.griditem);
                r2.setBackgroundResource(R.drawable.non2);
                r3.setBackgroundResource(R.drawable.non3);
                r5.setBackgroundResource(R.drawable.non5);
                r6.setBackgroundResource(R.drawable.non6);
                r4.setBackgroundResource(R.drawable.non4);
                r8.setBackgroundResource(R.drawable.non8);
                r9.setBackgroundResource(R.drawable.non9);
                r10.setBackgroundResource(R.drawable.non10);
                r11.setBackgroundResource(R.drawable.non11);
                r12.setBackgroundResource(R.drawable.non12);
                r13.setBackgroundResource(R.drawable.non13);
                r14.setBackgroundResource(R.drawable.non14);
                r15.setBackgroundResource(R.drawable.non15);
                r16.setBackgroundResource(R.drawable.non16);
                r17.setBackgroundResource(R.drawable.non17);
                r18.setBackgroundResource(R.drawable.non18);
                r19.setBackgroundResource(R.drawable.non19);
                r20.setBackgroundResource(R.drawable.non20);
                r21.setBackgroundResource(R.drawable.non21);
                r22.setBackgroundResource(R.drawable.non22);
                r7.setBackgroundResource(R.drawable.non7);
                r24.setBackgroundResource(R.drawable.non24);
                r25.setBackgroundResource(R.drawable.non25);
                r26.setBackgroundResource(R.drawable.non26);
                r27.setBackgroundResource(R.drawable.non27);
                r28.setBackgroundResource(R.drawable.non28);
                r29.setBackgroundResource(R.drawable.non29);
                r30.setBackgroundResource(R.drawable.non30);
                r31.setBackgroundResource(R.drawable.non31);
                r32.setBackgroundResource(R.drawable.non32);

                r23.setImageResource(R.drawable.tick);
                r23.setBackgroundResource(R.drawable.bor23);

                r1.setImageResource(android.R.color.transparent);
                r2.setImageResource(android.R.color.transparent);
                r3.setImageResource(android.R.color.transparent);
                r4.setImageResource(android.R.color.transparent);
                r5.setImageResource(android.R.color.transparent);
                r6.setImageResource(android.R.color.transparent);
                r7.setImageResource(android.R.color.transparent);
                r8.setImageResource(android.R.color.transparent);
                r9.setImageResource(android.R.color.transparent);
                r10.setImageResource(android.R.color.transparent);
                r11.setImageResource(android.R.color.transparent);
                r12.setImageResource(android.R.color.transparent);
                r13.setImageResource(android.R.color.transparent);
                r14.setImageResource(android.R.color.transparent);
                r15.setImageResource(android.R.color.transparent);
                r16.setImageResource(android.R.color.transparent);
                r17.setImageResource(android.R.color.transparent);
                r18.setImageResource(android.R.color.transparent);
                r19.setImageResource(android.R.color.transparent);
                r20.setImageResource(android.R.color.transparent);
                r21.setImageResource(android.R.color.transparent);
                r22.setImageResource(android.R.color.transparent);
                r24.setImageResource(android.R.color.transparent);
                r25.setImageResource(android.R.color.transparent);
                r26.setImageResource(android.R.color.transparent);
                r27.setImageResource(android.R.color.transparent);
                r28.setImageResource(android.R.color.transparent);
                r29.setImageResource(android.R.color.transparent);
                r30.setImageResource(android.R.color.transparent);
                r31.setImageResource(android.R.color.transparent);
                r32.setImageResource(android.R.color.transparent);

            }
        }
    };
    View.OnClickListener press24=new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            MainActivity.mViewPager.setBackgroundColor(Color.parseColor("#ef6c00"));
            settings_bg.setBackgroundColor(Color.parseColor("#ef6c00"));
            r24.setBackgroundResource(R.drawable.non24);
            r24.setImageResource(android.R.color.transparent);

            if (r24.getDrawable().getConstantState() == getResources().getDrawable(R.drawable.tick).getConstantState()) {
                r24.setBackgroundResource(R.drawable.non24);
                r24.setImageResource(android.R.color.transparent);
            } else {
                r1.setBackgroundResource(R.drawable.griditem);
                r2.setBackgroundResource(R.drawable.non2);
                r3.setBackgroundResource(R.drawable.non3);
                r5.setBackgroundResource(R.drawable.non5);
                r6.setBackgroundResource(R.drawable.non6);
                r4.setBackgroundResource(R.drawable.non4);
                r8.setBackgroundResource(R.drawable.non8);
                r9.setBackgroundResource(R.drawable.non9);
                r10.setBackgroundResource(R.drawable.non10);
                r11.setBackgroundResource(R.drawable.non11);
                r12.setBackgroundResource(R.drawable.non12);
                r13.setBackgroundResource(R.drawable.non13);
                r14.setBackgroundResource(R.drawable.non14);
                r15.setBackgroundResource(R.drawable.non15);
                r16.setBackgroundResource(R.drawable.non16);
                r17.setBackgroundResource(R.drawable.non17);
                r18.setBackgroundResource(R.drawable.non18);
                r19.setBackgroundResource(R.drawable.non19);
                r20.setBackgroundResource(R.drawable.non20);
                r21.setBackgroundResource(R.drawable.non21);
                r22.setBackgroundResource(R.drawable.non22);
                r23.setBackgroundResource(R.drawable.non23);
                r7.setBackgroundResource(R.drawable.non7);
                r25.setBackgroundResource(R.drawable.non25);
                r26.setBackgroundResource(R.drawable.non26);
                r27.setBackgroundResource(R.drawable.non27);
                r28.setBackgroundResource(R.drawable.non28);
                r29.setBackgroundResource(R.drawable.non29);
                r30.setBackgroundResource(R.drawable.non30);
                r31.setBackgroundResource(R.drawable.non31);
                r32.setBackgroundResource(R.drawable.non32);

                r24.setImageResource(R.drawable.tick);
                r24.setBackgroundResource(R.drawable.bor24);

                r1.setImageResource(android.R.color.transparent);
                r2.setImageResource(android.R.color.transparent);
                r3.setImageResource(android.R.color.transparent);
                r4.setImageResource(android.R.color.transparent);
                r5.setImageResource(android.R.color.transparent);
                r6.setImageResource(android.R.color.transparent);
                r7.setImageResource(android.R.color.transparent);
                r8.setImageResource(android.R.color.transparent);
                r9.setImageResource(android.R.color.transparent);
                r10.setImageResource(android.R.color.transparent);
                r11.setImageResource(android.R.color.transparent);
                r12.setImageResource(android.R.color.transparent);
                r13.setImageResource(android.R.color.transparent);
                r14.setImageResource(android.R.color.transparent);
                r15.setImageResource(android.R.color.transparent);
                r16.setImageResource(android.R.color.transparent);
                r17.setImageResource(android.R.color.transparent);
                r18.setImageResource(android.R.color.transparent);
                r19.setImageResource(android.R.color.transparent);
                r20.setImageResource(android.R.color.transparent);
                r21.setImageResource(android.R.color.transparent);
                r22.setImageResource(android.R.color.transparent);
                r23.setImageResource(android.R.color.transparent);
                r25.setImageResource(android.R.color.transparent);
                r26.setImageResource(android.R.color.transparent);
                r27.setImageResource(android.R.color.transparent);
                r28.setImageResource(android.R.color.transparent);
                r29.setImageResource(android.R.color.transparent);
                r30.setImageResource(android.R.color.transparent);
                r31.setImageResource(android.R.color.transparent);
                r32.setImageResource(android.R.color.transparent);

            }
        }
    };
    View.OnClickListener press25=new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            MainActivity.mViewPager.setBackgroundColor(Color.parseColor("#01bcd4"));
            settings_bg.setBackgroundColor(Color.parseColor("#01bcd4"));
            r25.setBackgroundResource(R.drawable.non25);
            r25.setImageResource(android.R.color.transparent);

            if (r25.getDrawable().getConstantState() == getResources().getDrawable(R.drawable.tick).getConstantState()) {
                r25.setBackgroundResource(R.drawable.non25);
                r25.setImageResource(android.R.color.transparent);
            } else {
                r1.setBackgroundResource(R.drawable.griditem);
                r2.setBackgroundResource(R.drawable.non2);
                r3.setBackgroundResource(R.drawable.non3);
                r5.setBackgroundResource(R.drawable.non5);
                r6.setBackgroundResource(R.drawable.non6);
                r4.setBackgroundResource(R.drawable.non4);
                r8.setBackgroundResource(R.drawable.non8);
                r9.setBackgroundResource(R.drawable.non9);
                r10.setBackgroundResource(R.drawable.non10);
                r11.setBackgroundResource(R.drawable.non11);
                r12.setBackgroundResource(R.drawable.non12);
                r13.setBackgroundResource(R.drawable.non13);
                r14.setBackgroundResource(R.drawable.non14);
                r15.setBackgroundResource(R.drawable.non15);
                r16.setBackgroundResource(R.drawable.non16);
                r17.setBackgroundResource(R.drawable.non17);
                r18.setBackgroundResource(R.drawable.non18);
                r19.setBackgroundResource(R.drawable.non19);
                r20.setBackgroundResource(R.drawable.non20);
                r21.setBackgroundResource(R.drawable.non21);
                r22.setBackgroundResource(R.drawable.non22);
                r23.setBackgroundResource(R.drawable.non23);
                r24.setBackgroundResource(R.drawable.non24);
                r7.setBackgroundResource(R.drawable.non7);
                r26.setBackgroundResource(R.drawable.non26);
                r27.setBackgroundResource(R.drawable.non27);
                r28.setBackgroundResource(R.drawable.non28);
                r29.setBackgroundResource(R.drawable.non29);
                r30.setBackgroundResource(R.drawable.non30);
                r31.setBackgroundResource(R.drawable.non31);
                r32.setBackgroundResource(R.drawable.non32);

                r25.setImageResource(R.drawable.tick);
                r25.setBackgroundResource(R.drawable.bor25);

                r1.setImageResource(android.R.color.transparent);
                r2.setImageResource(android.R.color.transparent);
                r3.setImageResource(android.R.color.transparent);
                r4.setImageResource(android.R.color.transparent);
                r5.setImageResource(android.R.color.transparent);
                r6.setImageResource(android.R.color.transparent);
                r7.setImageResource(android.R.color.transparent);
                r8.setImageResource(android.R.color.transparent);
                r9.setImageResource(android.R.color.transparent);
                r10.setImageResource(android.R.color.transparent);
                r11.setImageResource(android.R.color.transparent);
                r12.setImageResource(android.R.color.transparent);
                r13.setImageResource(android.R.color.transparent);
                r14.setImageResource(android.R.color.transparent);
                r15.setImageResource(android.R.color.transparent);
                r16.setImageResource(android.R.color.transparent);
                r17.setImageResource(android.R.color.transparent);
                r18.setImageResource(android.R.color.transparent);
                r19.setImageResource(android.R.color.transparent);
                r20.setImageResource(android.R.color.transparent);
                r21.setImageResource(android.R.color.transparent);
                r22.setImageResource(android.R.color.transparent);
                r23.setImageResource(android.R.color.transparent);
                r24.setImageResource(android.R.color.transparent);
                r26.setImageResource(android.R.color.transparent);
                r27.setImageResource(android.R.color.transparent);
                r28.setImageResource(android.R.color.transparent);
                r29.setImageResource(android.R.color.transparent);
                r30.setImageResource(android.R.color.transparent);
                r31.setImageResource(android.R.color.transparent);
                r32.setImageResource(android.R.color.transparent);
            }
        }
    };
    View.OnClickListener press26=new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            MainActivity.mViewPager.setBackgroundColor(Color.parseColor("#ff5722"));
            settings_bg.setBackgroundColor(Color.parseColor("#ff5722"));
            r26.setBackgroundResource(R.drawable.non26);
            r26.setImageResource(android.R.color.transparent);

            if (r26.getDrawable().getConstantState() == getResources().getDrawable(R.drawable.tick).getConstantState()) {
                r26.setBackgroundResource(R.drawable.non26);
                r26.setImageResource(android.R.color.transparent);
            } else {
                r1.setBackgroundResource(R.drawable.griditem);
                r2.setBackgroundResource(R.drawable.non2);
                r3.setBackgroundResource(R.drawable.non3);
                r5.setBackgroundResource(R.drawable.non5);
                r6.setBackgroundResource(R.drawable.non6);
                r4.setBackgroundResource(R.drawable.non4);
                r8.setBackgroundResource(R.drawable.non8);
                r9.setBackgroundResource(R.drawable.non9);
                r10.setBackgroundResource(R.drawable.non10);
                r11.setBackgroundResource(R.drawable.non11);
                r12.setBackgroundResource(R.drawable.non12);
                r13.setBackgroundResource(R.drawable.non13);
                r14.setBackgroundResource(R.drawable.non14);
                r15.setBackgroundResource(R.drawable.non15);
                r16.setBackgroundResource(R.drawable.non16);
                r17.setBackgroundResource(R.drawable.non17);
                r18.setBackgroundResource(R.drawable.non18);
                r19.setBackgroundResource(R.drawable.non19);
                r20.setBackgroundResource(R.drawable.non20);
                r21.setBackgroundResource(R.drawable.non21);
                r22.setBackgroundResource(R.drawable.non22);
                r23.setBackgroundResource(R.drawable.non23);
                r24.setBackgroundResource(R.drawable.non24);
                r25.setBackgroundResource(R.drawable.non25);
                r7.setBackgroundResource(R.drawable.non7);
                r27.setBackgroundResource(R.drawable.non27);
                r28.setBackgroundResource(R.drawable.non28);
                r29.setBackgroundResource(R.drawable.non29);
                r30.setBackgroundResource(R.drawable.non30);
                r31.setBackgroundResource(R.drawable.non31);
                r32.setBackgroundResource(R.drawable.non32);

                r26.setImageResource(R.drawable.tick);
                r26.setBackgroundResource(R.drawable.bor26);

                r1.setImageResource(android.R.color.transparent);
                r2.setImageResource(android.R.color.transparent);
                r3.setImageResource(android.R.color.transparent);
                r4.setImageResource(android.R.color.transparent);
                r5.setImageResource(android.R.color.transparent);
                r6.setImageResource(android.R.color.transparent);
                r7.setImageResource(android.R.color.transparent);
                r8.setImageResource(android.R.color.transparent);
                r9.setImageResource(android.R.color.transparent);
                r10.setImageResource(android.R.color.transparent);
                r11.setImageResource(android.R.color.transparent);
                r12.setImageResource(android.R.color.transparent);
                r13.setImageResource(android.R.color.transparent);
                r14.setImageResource(android.R.color.transparent);
                r15.setImageResource(android.R.color.transparent);
                r16.setImageResource(android.R.color.transparent);
                r17.setImageResource(android.R.color.transparent);
                r18.setImageResource(android.R.color.transparent);
                r19.setImageResource(android.R.color.transparent);
                r20.setImageResource(android.R.color.transparent);
                r21.setImageResource(android.R.color.transparent);
                r22.setImageResource(android.R.color.transparent);
                r23.setImageResource(android.R.color.transparent);
                r24.setImageResource(android.R.color.transparent);
                r25.setImageResource(android.R.color.transparent);
                r27.setImageResource(android.R.color.transparent);
                r28.setImageResource(android.R.color.transparent);
                r29.setImageResource(android.R.color.transparent);
                r30.setImageResource(android.R.color.transparent);
                r31.setImageResource(android.R.color.transparent);
                r32.setImageResource(android.R.color.transparent);
            }
        }
    };
    View.OnClickListener press27=new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            MainActivity.mViewPager.setBackgroundColor(Color.parseColor("#00838f"));
            settings_bg.setBackgroundColor(Color.parseColor("#00838f"));
            r27.setBackgroundResource(R.drawable.non27);
            r27.setImageResource(android.R.color.transparent);

            if (r27.getDrawable().getConstantState() == getResources().getDrawable(R.drawable.tick).getConstantState()) {
                r27.setBackgroundResource(R.drawable.non27);
                r27.setImageResource(android.R.color.transparent);
            } else {
                r1.setBackgroundResource(R.drawable.griditem);
                r2.setBackgroundResource(R.drawable.non2);
                r3.setBackgroundResource(R.drawable.non3);
                r5.setBackgroundResource(R.drawable.non5);
                r6.setBackgroundResource(R.drawable.non6);
                r4.setBackgroundResource(R.drawable.non4);
                r8.setBackgroundResource(R.drawable.non8);
                r9.setBackgroundResource(R.drawable.non9);
                r10.setBackgroundResource(R.drawable.non10);
                r11.setBackgroundResource(R.drawable.non11);
                r12.setBackgroundResource(R.drawable.non12);
                r13.setBackgroundResource(R.drawable.non13);
                r14.setBackgroundResource(R.drawable.non14);
                r15.setBackgroundResource(R.drawable.non15);
                r16.setBackgroundResource(R.drawable.non16);
                r17.setBackgroundResource(R.drawable.non17);
                r18.setBackgroundResource(R.drawable.non18);
                r19.setBackgroundResource(R.drawable.non19);
                r20.setBackgroundResource(R.drawable.non20);
                r21.setBackgroundResource(R.drawable.non21);
                r22.setBackgroundResource(R.drawable.non22);
                r23.setBackgroundResource(R.drawable.non23);
                r24.setBackgroundResource(R.drawable.non24);
                r25.setBackgroundResource(R.drawable.non25);
                r26.setBackgroundResource(R.drawable.non26);
                r7.setBackgroundResource(R.drawable.non7);
                r28.setBackgroundResource(R.drawable.non28);
                r29.setBackgroundResource(R.drawable.non29);
                r30.setBackgroundResource(R.drawable.non30);
                r31.setBackgroundResource(R.drawable.non31);
                r32.setBackgroundResource(R.drawable.non32);

                r27.setImageResource(R.drawable.tick);
                r27.setBackgroundResource(R.drawable.bor27);

                r1.setImageResource(android.R.color.transparent);
                r2.setImageResource(android.R.color.transparent);
                r3.setImageResource(android.R.color.transparent);
                r4.setImageResource(android.R.color.transparent);
                r5.setImageResource(android.R.color.transparent);
                r6.setImageResource(android.R.color.transparent);
                r7.setImageResource(android.R.color.transparent);
                r8.setImageResource(android.R.color.transparent);
                r9.setImageResource(android.R.color.transparent);
                r10.setImageResource(android.R.color.transparent);
                r11.setImageResource(android.R.color.transparent);
                r12.setImageResource(android.R.color.transparent);
                r13.setImageResource(android.R.color.transparent);
                r14.setImageResource(android.R.color.transparent);
                r15.setImageResource(android.R.color.transparent);
                r16.setImageResource(android.R.color.transparent);
                r17.setImageResource(android.R.color.transparent);
                r18.setImageResource(android.R.color.transparent);
                r19.setImageResource(android.R.color.transparent);
                r20.setImageResource(android.R.color.transparent);
                r21.setImageResource(android.R.color.transparent);
                r22.setImageResource(android.R.color.transparent);
                r23.setImageResource(android.R.color.transparent);
                r24.setImageResource(android.R.color.transparent);
                r25.setImageResource(android.R.color.transparent);
                r26.setImageResource(android.R.color.transparent);
                r28.setImageResource(android.R.color.transparent);
                r29.setImageResource(android.R.color.transparent);
                r30.setImageResource(android.R.color.transparent);
                r31.setImageResource(android.R.color.transparent);
                r32.setImageResource(android.R.color.transparent);

            }
        }
    };
    View.OnClickListener press28=new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            MainActivity.mViewPager.setBackgroundColor(Color.parseColor("#d84316"));
            settings_bg.setBackgroundColor(Color.parseColor("#d84316"));
            r28.setBackgroundResource(R.drawable.non28);
            r28.setImageResource(android.R.color.transparent);

            if (r28.getDrawable().getConstantState() == getResources().getDrawable(R.drawable.tick).getConstantState()) {
                r28.setBackgroundResource(R.drawable.non28);
                r28.setImageResource(android.R.color.transparent);
            } else {
                r1.setBackgroundResource(R.drawable.griditem);
                r2.setBackgroundResource(R.drawable.non2);
                r3.setBackgroundResource(R.drawable.non3);
                r5.setBackgroundResource(R.drawable.non5);
                r6.setBackgroundResource(R.drawable.non6);
                r4.setBackgroundResource(R.drawable.non4);
                r8.setBackgroundResource(R.drawable.non8);
                r9.setBackgroundResource(R.drawable.non9);
                r10.setBackgroundResource(R.drawable.non10);
                r11.setBackgroundResource(R.drawable.non11);
                r12.setBackgroundResource(R.drawable.non12);
                r13.setBackgroundResource(R.drawable.non13);
                r14.setBackgroundResource(R.drawable.non14);
                r15.setBackgroundResource(R.drawable.non15);
                r16.setBackgroundResource(R.drawable.non16);
                r17.setBackgroundResource(R.drawable.non17);
                r18.setBackgroundResource(R.drawable.non18);
                r19.setBackgroundResource(R.drawable.non19);
                r20.setBackgroundResource(R.drawable.non20);
                r21.setBackgroundResource(R.drawable.non21);
                r22.setBackgroundResource(R.drawable.non22);
                r23.setBackgroundResource(R.drawable.non23);
                r24.setBackgroundResource(R.drawable.non24);
                r25.setBackgroundResource(R.drawable.non25);
                r26.setBackgroundResource(R.drawable.non26);
                r27.setBackgroundResource(R.drawable.non27);
                r7.setBackgroundResource(R.drawable.non7);
                r29.setBackgroundResource(R.drawable.non29);
                r30.setBackgroundResource(R.drawable.non30);
                r31.setBackgroundResource(R.drawable.non31);
                r32.setBackgroundResource(R.drawable.non32);

                r28.setImageResource(R.drawable.tick);
                r28.setBackgroundResource(R.drawable.bor28);

                r1.setImageResource(android.R.color.transparent);
                r2.setImageResource(android.R.color.transparent);
                r3.setImageResource(android.R.color.transparent);
                r4.setImageResource(android.R.color.transparent);
                r5.setImageResource(android.R.color.transparent);
                r6.setImageResource(android.R.color.transparent);
                r7.setImageResource(android.R.color.transparent);
                r8.setImageResource(android.R.color.transparent);
                r9.setImageResource(android.R.color.transparent);
                r10.setImageResource(android.R.color.transparent);
                r11.setImageResource(android.R.color.transparent);
                r12.setImageResource(android.R.color.transparent);
                r13.setImageResource(android.R.color.transparent);
                r14.setImageResource(android.R.color.transparent);
                r15.setImageResource(android.R.color.transparent);
                r16.setImageResource(android.R.color.transparent);
                r17.setImageResource(android.R.color.transparent);
                r18.setImageResource(android.R.color.transparent);
                r19.setImageResource(android.R.color.transparent);
                r20.setImageResource(android.R.color.transparent);
                r21.setImageResource(android.R.color.transparent);
                r22.setImageResource(android.R.color.transparent);
                r23.setImageResource(android.R.color.transparent);
                r24.setImageResource(android.R.color.transparent);
                r25.setImageResource(android.R.color.transparent);
                r26.setImageResource(android.R.color.transparent);
                r27.setImageResource(android.R.color.transparent);
                r29.setImageResource(android.R.color.transparent);
                r30.setImageResource(android.R.color.transparent);
                r31.setImageResource(android.R.color.transparent);
                r32.setImageResource(android.R.color.transparent);

            }
        }
    };
    View.OnClickListener press29=new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            MainActivity.mViewPager.setBackgroundColor(Color.parseColor("#009588"));
            settings_bg.setBackgroundColor(Color.parseColor("#009588"));
            r29.setBackgroundResource(R.drawable.non29);
            r29.setImageResource(android.R.color.transparent);

            if (r29.getDrawable().getConstantState() == getResources().getDrawable(R.drawable.tick).getConstantState()) {
                r29.setBackgroundResource(R.drawable.non29);
                r29.setImageResource(android.R.color.transparent);
            } else {
                r1.setBackgroundResource(R.drawable.griditem);
                r2.setBackgroundResource(R.drawable.non2);
                r3.setBackgroundResource(R.drawable.non3);
                r5.setBackgroundResource(R.drawable.non5);
                r6.setBackgroundResource(R.drawable.non6);
                r4.setBackgroundResource(R.drawable.non4);
                r8.setBackgroundResource(R.drawable.non8);
                r9.setBackgroundResource(R.drawable.non9);
                r10.setBackgroundResource(R.drawable.non10);
                r11.setBackgroundResource(R.drawable.non11);
                r12.setBackgroundResource(R.drawable.non12);
                r13.setBackgroundResource(R.drawable.non13);
                r14.setBackgroundResource(R.drawable.non14);
                r15.setBackgroundResource(R.drawable.non15);
                r16.setBackgroundResource(R.drawable.non16);
                r17.setBackgroundResource(R.drawable.non17);
                r18.setBackgroundResource(R.drawable.non18);
                r19.setBackgroundResource(R.drawable.non19);
                r20.setBackgroundResource(R.drawable.non20);
                r21.setBackgroundResource(R.drawable.non21);
                r22.setBackgroundResource(R.drawable.non22);
                r23.setBackgroundResource(R.drawable.non23);
                r24.setBackgroundResource(R.drawable.non24);
                r25.setBackgroundResource(R.drawable.non25);
                r26.setBackgroundResource(R.drawable.non26);
                r27.setBackgroundResource(R.drawable.non27);
                r7.setBackgroundResource(R.drawable.non7);
                r28.setBackgroundResource(R.drawable.non28);
                r30.setBackgroundResource(R.drawable.non30);
                r31.setBackgroundResource(R.drawable.non31);
                r32.setBackgroundResource(R.drawable.non32);

                r29.setImageResource(R.drawable.tick);
                r29.setBackgroundResource(R.drawable.bor29);

                r1.setImageResource(android.R.color.transparent);
                r2.setImageResource(android.R.color.transparent);
                r3.setImageResource(android.R.color.transparent);
                r4.setImageResource(android.R.color.transparent);
                r5.setImageResource(android.R.color.transparent);
                r6.setImageResource(android.R.color.transparent);
                r7.setImageResource(android.R.color.transparent);
                r8.setImageResource(android.R.color.transparent);
                r9.setImageResource(android.R.color.transparent);
                r10.setImageResource(android.R.color.transparent);
                r11.setImageResource(android.R.color.transparent);
                r12.setImageResource(android.R.color.transparent);
                r13.setImageResource(android.R.color.transparent);
                r14.setImageResource(android.R.color.transparent);
                r15.setImageResource(android.R.color.transparent);
                r16.setImageResource(android.R.color.transparent);
                r17.setImageResource(android.R.color.transparent);
                r18.setImageResource(android.R.color.transparent);
                r19.setImageResource(android.R.color.transparent);
                r20.setImageResource(android.R.color.transparent);
                r21.setImageResource(android.R.color.transparent);
                r22.setImageResource(android.R.color.transparent);
                r23.setImageResource(android.R.color.transparent);
                r24.setImageResource(android.R.color.transparent);
                r25.setImageResource(android.R.color.transparent);
                r26.setImageResource(android.R.color.transparent);
                r27.setImageResource(android.R.color.transparent);
                r28.setImageResource(android.R.color.transparent);
                r30.setImageResource(android.R.color.transparent);
                r31.setImageResource(android.R.color.transparent);
                r32.setImageResource(android.R.color.transparent);


            }
        }
    };
    View.OnClickListener press30=new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            MainActivity.mViewPager.setBackgroundColor(Color.parseColor("#000000"));
            settings_bg.setBackgroundColor(Color.parseColor("#000000"));
            r30.setBackgroundResource(R.drawable.non30);
            r30.setImageResource(android.R.color.transparent);

            if (r30.getDrawable().getConstantState() == getResources().getDrawable(R.drawable.tick).getConstantState()) {
                r30.setBackgroundResource(R.drawable.non30);
                r30.setImageResource(android.R.color.transparent);
            } else {
                r1.setBackgroundResource(R.drawable.griditem);
                r2.setBackgroundResource(R.drawable.non2);
                r3.setBackgroundResource(R.drawable.non3);
                r5.setBackgroundResource(R.drawable.non5);
                r6.setBackgroundResource(R.drawable.non6);
                r4.setBackgroundResource(R.drawable.non4);
                r8.setBackgroundResource(R.drawable.non8);
                r9.setBackgroundResource(R.drawable.non9);
                r10.setBackgroundResource(R.drawable.non10);
                r11.setBackgroundResource(R.drawable.non11);
                r12.setBackgroundResource(R.drawable.non12);
                r13.setBackgroundResource(R.drawable.non13);
                r14.setBackgroundResource(R.drawable.non14);
                r15.setBackgroundResource(R.drawable.non15);
                r16.setBackgroundResource(R.drawable.non16);
                r17.setBackgroundResource(R.drawable.non17);
                r18.setBackgroundResource(R.drawable.non18);
                r19.setBackgroundResource(R.drawable.non19);
                r20.setBackgroundResource(R.drawable.non20);
                r21.setBackgroundResource(R.drawable.non21);
                r22.setBackgroundResource(R.drawable.non22);
                r23.setBackgroundResource(R.drawable.non23);
                r24.setBackgroundResource(R.drawable.non24);
                r25.setBackgroundResource(R.drawable.non25);
                r26.setBackgroundResource(R.drawable.non26);
                r27.setBackgroundResource(R.drawable.non27);
                r7.setBackgroundResource(R.drawable.non7);
                r28.setBackgroundResource(R.drawable.non28);
                r29.setBackgroundResource(R.drawable.non29);
                r31.setBackgroundResource(R.drawable.non31);
                r32.setBackgroundResource(R.drawable.non32);

                r30.setImageResource(R.drawable.tick);
                r30.setBackgroundResource(R.drawable.bor30);

                r1.setImageResource(android.R.color.transparent);
                r2.setImageResource(android.R.color.transparent);
                r3.setImageResource(android.R.color.transparent);
                r4.setImageResource(android.R.color.transparent);
                r5.setImageResource(android.R.color.transparent);
                r6.setImageResource(android.R.color.transparent);
                r7.setImageResource(android.R.color.transparent);
                r8.setImageResource(android.R.color.transparent);
                r9.setImageResource(android.R.color.transparent);
                r10.setImageResource(android.R.color.transparent);
                r11.setImageResource(android.R.color.transparent);
                r12.setImageResource(android.R.color.transparent);
                r13.setImageResource(android.R.color.transparent);
                r14.setImageResource(android.R.color.transparent);
                r15.setImageResource(android.R.color.transparent);
                r16.setImageResource(android.R.color.transparent);
                r17.setImageResource(android.R.color.transparent);
                r18.setImageResource(android.R.color.transparent);
                r19.setImageResource(android.R.color.transparent);
                r20.setImageResource(android.R.color.transparent);
                r21.setImageResource(android.R.color.transparent);
                r22.setImageResource(android.R.color.transparent);
                r23.setImageResource(android.R.color.transparent);
                r24.setImageResource(android.R.color.transparent);
                r25.setImageResource(android.R.color.transparent);
                r26.setImageResource(android.R.color.transparent);
                r27.setImageResource(android.R.color.transparent);
                r28.setImageResource(android.R.color.transparent);
                r29.setImageResource(android.R.color.transparent);
                r31.setImageResource(android.R.color.transparent);
                r32.setImageResource(android.R.color.transparent);


            }
        }
    };
    View.OnClickListener press31=new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            MainActivity.mViewPager.setBackgroundColor(Color.parseColor("#00695c"));
            settings_bg.setBackgroundColor(Color.parseColor("#00695c"));
            r31.setBackgroundResource(R.drawable.non31);
            r31.setImageResource(android.R.color.transparent);

            if (r31.getDrawable().getConstantState() == getResources().getDrawable(R.drawable.tick).getConstantState()) {
                r31.setBackgroundResource(R.drawable.non31);
                r31.setImageResource(android.R.color.transparent);
            } else {
                r1.setBackgroundResource(R.drawable.griditem);
                r2.setBackgroundResource(R.drawable.non2);
                r3.setBackgroundResource(R.drawable.non3);
                r5.setBackgroundResource(R.drawable.non5);
                r6.setBackgroundResource(R.drawable.non6);
                r4.setBackgroundResource(R.drawable.non4);
                r8.setBackgroundResource(R.drawable.non8);
                r9.setBackgroundResource(R.drawable.non9);
                r10.setBackgroundResource(R.drawable.non10);
                r11.setBackgroundResource(R.drawable.non11);
                r12.setBackgroundResource(R.drawable.non12);
                r13.setBackgroundResource(R.drawable.non13);
                r14.setBackgroundResource(R.drawable.non14);
                r15.setBackgroundResource(R.drawable.non15);
                r16.setBackgroundResource(R.drawable.non16);
                r17.setBackgroundResource(R.drawable.non17);
                r18.setBackgroundResource(R.drawable.non18);
                r19.setBackgroundResource(R.drawable.non19);
                r20.setBackgroundResource(R.drawable.non20);
                r21.setBackgroundResource(R.drawable.non21);
                r22.setBackgroundResource(R.drawable.non22);
                r23.setBackgroundResource(R.drawable.non23);
                r24.setBackgroundResource(R.drawable.non24);
                r25.setBackgroundResource(R.drawable.non25);
                r26.setBackgroundResource(R.drawable.non26);
                r27.setBackgroundResource(R.drawable.non27);
                r7.setBackgroundResource(R.drawable.non7);
                r28.setBackgroundResource(R.drawable.non28);
                r30.setBackgroundResource(R.drawable.non30);
                r29.setBackgroundResource(R.drawable.non29);
                r32.setBackgroundResource(R.drawable.non32);

                r31.setImageResource(R.drawable.tick);
                r31.setBackgroundResource(R.drawable.bor31);

                r1.setImageResource(android.R.color.transparent);
                r2.setImageResource(android.R.color.transparent);
                r3.setImageResource(android.R.color.transparent);
                r4.setImageResource(android.R.color.transparent);
                r5.setImageResource(android.R.color.transparent);
                r6.setImageResource(android.R.color.transparent);
                r7.setImageResource(android.R.color.transparent);
                r8.setImageResource(android.R.color.transparent);
                r9.setImageResource(android.R.color.transparent);
                r10.setImageResource(android.R.color.transparent);
                r11.setImageResource(android.R.color.transparent);
                r12.setImageResource(android.R.color.transparent);
                r13.setImageResource(android.R.color.transparent);
                r14.setImageResource(android.R.color.transparent);
                r15.setImageResource(android.R.color.transparent);
                r16.setImageResource(android.R.color.transparent);
                r17.setImageResource(android.R.color.transparent);
                r18.setImageResource(android.R.color.transparent);
                r19.setImageResource(android.R.color.transparent);
                r20.setImageResource(android.R.color.transparent);
                r21.setImageResource(android.R.color.transparent);
                r22.setImageResource(android.R.color.transparent);
                r23.setImageResource(android.R.color.transparent);
                r24.setImageResource(android.R.color.transparent);
                r25.setImageResource(android.R.color.transparent);
                r26.setImageResource(android.R.color.transparent);
                r27.setImageResource(android.R.color.transparent);
                r28.setImageResource(android.R.color.transparent);
                r30.setImageResource(android.R.color.transparent);
                r29.setImageResource(android.R.color.transparent);
                r32.setImageResource(android.R.color.transparent);


            }
        }
    };
    View.OnClickListener press32=new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            MainActivity.mViewPager.setBackgroundColor(Color.parseColor("#4e342e"));
            settings_bg.setBackgroundColor(Color.parseColor("#4e342e"));
            r32.setBackgroundResource(R.drawable.non32);
            r32.setImageResource(android.R.color.transparent);

            if (r32.getDrawable().getConstantState() == getResources().getDrawable(R.drawable.tick).getConstantState()) {
                r32.setBackgroundResource(R.drawable.non32);
                r32.setImageResource(android.R.color.transparent);
            } else {
                r1.setBackgroundResource(R.drawable.griditem);
                r2.setBackgroundResource(R.drawable.non2);
                r3.setBackgroundResource(R.drawable.non3);
                r5.setBackgroundResource(R.drawable.non5);
                r6.setBackgroundResource(R.drawable.non6);
                r4.setBackgroundResource(R.drawable.non4);
                r8.setBackgroundResource(R.drawable.non8);
                r9.setBackgroundResource(R.drawable.non9);
                r10.setBackgroundResource(R.drawable.non10);
                r11.setBackgroundResource(R.drawable.non11);
                r12.setBackgroundResource(R.drawable.non12);
                r13.setBackgroundResource(R.drawable.non13);
                r14.setBackgroundResource(R.drawable.non14);
                r15.setBackgroundResource(R.drawable.non15);
                r16.setBackgroundResource(R.drawable.non16);
                r17.setBackgroundResource(R.drawable.non17);
                r18.setBackgroundResource(R.drawable.non18);
                r19.setBackgroundResource(R.drawable.non19);
                r20.setBackgroundResource(R.drawable.non20);
                r21.setBackgroundResource(R.drawable.non21);
                r22.setBackgroundResource(R.drawable.non22);
                r23.setBackgroundResource(R.drawable.non23);
                r24.setBackgroundResource(R.drawable.non24);
                r25.setBackgroundResource(R.drawable.non25);
                r26.setBackgroundResource(R.drawable.non26);
                r27.setBackgroundResource(R.drawable.non27);
                r7.setBackgroundResource(R.drawable.non7);
                r28.setBackgroundResource(R.drawable.non28);
                r30.setBackgroundResource(R.drawable.non30);
                r31.setBackgroundResource(R.drawable.non31);
                r29.setBackgroundResource(R.drawable.non29);

                r32.setImageResource(R.drawable.tick);
                r32.setBackgroundResource(R.drawable.bor32);

                r1.setImageResource(android.R.color.transparent);
                r2.setImageResource(android.R.color.transparent);
                r3.setImageResource(android.R.color.transparent);
                r4.setImageResource(android.R.color.transparent);
                r5.setImageResource(android.R.color.transparent);
                r6.setImageResource(android.R.color.transparent);
                r7.setImageResource(android.R.color.transparent);
                r8.setImageResource(android.R.color.transparent);
                r9.setImageResource(android.R.color.transparent);
                r10.setImageResource(android.R.color.transparent);
                r11.setImageResource(android.R.color.transparent);
                r12.setImageResource(android.R.color.transparent);
                r13.setImageResource(android.R.color.transparent);
                r14.setImageResource(android.R.color.transparent);
                r15.setImageResource(android.R.color.transparent);
                r16.setImageResource(android.R.color.transparent);
                r17.setImageResource(android.R.color.transparent);
                r18.setImageResource(android.R.color.transparent);
                r19.setImageResource(android.R.color.transparent);
                r20.setImageResource(android.R.color.transparent);
                r21.setImageResource(android.R.color.transparent);
                r22.setImageResource(android.R.color.transparent);
                r23.setImageResource(android.R.color.transparent);
                r24.setImageResource(android.R.color.transparent);
                r25.setImageResource(android.R.color.transparent);
                r26.setImageResource(android.R.color.transparent);
                r27.setImageResource(android.R.color.transparent);
                r28.setImageResource(android.R.color.transparent);
                r30.setImageResource(android.R.color.transparent);
                r31.setImageResource(android.R.color.transparent);
                r29.setImageResource(android.R.color.transparent);


            }
        }
    };
}
