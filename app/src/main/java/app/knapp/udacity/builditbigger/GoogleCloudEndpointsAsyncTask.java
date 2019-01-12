package app.knapp.udacity.builditbigger;

import java.io.IOException;
import android.os.AsyncTask;
import android.util.Log;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;
import com.udacity.gradle.builditbigger.backend.myApi.MyApi;

import static android.content.ContentValues.TAG;

public class GoogleCloudEndpointsAsyncTask extends AsyncTask<Void, Void, String> {

    private static MyApi myApi = null;
    private JokeReceivedInterface jokeReceivedInterface;

    public interface JokeReceivedInterface {
        void onJokeReceived(String joke);
    }

    public GoogleCloudEndpointsAsyncTask(JokeReceivedInterface jokeReceivedInterface) {
        this.jokeReceivedInterface = jokeReceivedInterface;
    }

    @Override
    protected String doInBackground(Void... voids) {
        if (myApi == null) {
            MyApi.Builder myApiBuilder = new MyApi.Builder(
                AndroidHttp.newCompatibleTransport(),
                new AndroidJsonFactory(),
                null)

                //.setRootUrl("http://10.0.2.2:8080/_ah/api/")
                // local IP was no working from emulator, maybe because I use Genymotion on an AMD system
                // deployed to a Google Cloud Platform project works
                .setRootUrl("https://build-it-bigger-228210.appspot.com/_ah/api/")

                .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
                    @Override
                    public void initialize(AbstractGoogleClientRequest<?> request) throws IOException {
                        request.setDisableGZipContent(true);
                    }
                });

            myApi = myApiBuilder.build();
        }

        try {
            return myApi.getJoke().execute().getData();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    protected void onPostExecute(String joke) {
        Log.d(TAG, "onPostExecute: joke " + joke);
        jokeReceivedInterface.onJokeReceived(joke);
    }
}
