package timer.fotrack.com;

import java.util.Random;

/**
 * Created by fajar on 03-Nov-16.
 */

public class MockData {
    // x is the day number, 0, 1, 2, 3
    public static Point getDataFromReceiver(int x)
    {
        return new Point(x, (int) (DynamicGraphActivity.TotelWork));
    }

}
