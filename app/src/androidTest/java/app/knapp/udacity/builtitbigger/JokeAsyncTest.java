package app.knapp.udacity.builtitbigger;


import app.knapp.udacity.builditbigger.GoogleCloudEndpointsAsyncTask;
import app.knapp.udacity.builditbigger.MainActivity;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.text.TextUtils;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import static junit.framework.TestCase.assertFalse;


@RunWith(AndroidJUnit4.class)
public class JokeAsyncTest {

    @Rule
    public ActivityTestRule<MainActivity> activityActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void testAsyncTaskJoke() throws Exception {

        GoogleCloudEndpointsAsyncTask asyncTaskJoke = new GoogleCloudEndpointsAsyncTask(activityActivityTestRule.getActivity());

        asyncTaskJoke.execute();
        String taskResult = asyncTaskJoke.get();

        assertFalse(TextUtils.isEmpty(taskResult));
    }
}
