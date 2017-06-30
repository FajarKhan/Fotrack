package timer.fotrack.com;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.achartengine.GraphicalView;

import java.util.concurrent.TimeUnit;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.T;
import static android.icu.util.HebrewCalendar.AV;

public class DynamicGraphActivity extends AppCompatActivity {
    private static GraphicalView view;

    private LineGraph line = new LineGraph();

    private static Thread thread;
    public static int TotelWork,TotelBreak;
    private TextView TodayWork,AvgWork,MonthWork,MonthBreak,Ratio;
    private ImageView BackButton;

    /** Called when the activity is first created. */


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dynamic_graph);

        BackButton=(ImageView)findViewById(R.id.Backbutton2);
        BackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DynamicGraphActivity.super.onBackPressed();

            }
        });

        float Long=MainActivity.LongStoreSec/60;
        float Short=MainActivity.ShortStoreSec/60;

        TotelWork=MainActivity.flag/60;
        TotelBreak= (int) (Long+Short);

        TodayWork=(TextView)findViewById(R.id.TodayWork);
        AvgWork=(TextView)findViewById(R.id.AvgWork);
        MonthBreak=(TextView)findViewById(R.id.Monthbreak);
        MonthWork=(TextView)findViewById(R.id.MonthWork);
        Ratio=(TextView)findViewById(R.id.Ratio);

        long TodayworkTime=MainActivity.PomoStoreSec;

        long m = (TodayworkTime / 60) % 60;
        long h = (TodayworkTime / (60 * 60)) % 24;
        TodayWork.setText(String.format("%d:%02d", h,m));

        int TotelWork=MainActivity.flag/3600;
        float Avg=TotelWork/MainActivity.Days;
        AvgWork.setText(String.valueOf(Avg));

        MonthWork.setText(String.valueOf(TotelWork));

        float Break=Long+Short;
        MonthBreak.setText(String.valueOf(Break));









        thread = new Thread() {

            public void run()

            {

                for (int i = 0; i < 30; i++)

                {

                    try {

                        Thread.sleep(10000);

                    } catch (InterruptedException e) {

// TODO Auto-generated catch block
                        e.printStackTrace();

                    }

                    Point p = MockData.getDataFromReceiver(i); // We got new data!
                    point2 p1 = Mockdata2.getDataFromReceiver2(i);
                    line.addNewPoints(p,p1); // Add it to our graph
                    view.repaint();

                }

            }

        };

        thread.start();

    }


    @Override

    protected void onStart() {

        super.onStart();

        view = line.getView(this);
        LinearLayout l1 = (LinearLayout)findViewById(R.id.chart_container);
        l1.addView(view);
    }

}

