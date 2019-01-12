package app.knapp.udacity.builditbigger;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import app.knapp.udacity.builditbigger.R;

import app.knapp.jokes.JokeActivity;


public class MainActivity extends AppCompatActivity implements GoogleCloudEndpointsAsyncTask.JokeReceivedInterface {

    private static final String TAG = "MainActivity";

    Button btnTellJoke;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnTellJoke = findViewById(R.id.btnTellJoke);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onJokeReceived(String joke) {
        btnTellJoke.setEnabled(true);
        Log.d(TAG, "onJokeReceived: joke " + joke);
        Intent intent = new Intent(this, JokeActivity.class);
        intent.putExtra("joke", joke);
        startActivity(intent);
    }

    public void tellJoke(View view) {
        Log.d(TAG, "tellJoke: start async task");
        GoogleCloudEndpointsAsyncTask asyncTask = new GoogleCloudEndpointsAsyncTask(this);
        btnTellJoke.setEnabled(false);
        asyncTask.execute();
    }


}
