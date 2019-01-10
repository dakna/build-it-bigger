package app.knapp.jokes;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class JokeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_joke);

        TextView tvJoke = findViewById(R.id.tv_joke);

        String joke = getIntent().getStringExtra("joke");
        tvJoke.setText(joke);
    }
}
