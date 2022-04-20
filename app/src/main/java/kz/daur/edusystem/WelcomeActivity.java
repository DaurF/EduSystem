package kz.daur.edusystem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

public class WelcomeActivity extends AppCompatActivity {

    Button sign_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        Window w = getWindow();
        View decorView = w.getDecorView();
        w.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        w.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        w.setStatusBarColor(ContextCompat.getColor(this, R.color.blue));
        decorView.setSystemUiVisibility(decorView.getSystemUiVisibility() | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);

        sign_btn = (Button) findViewById(R.id.sign_btn);

        sign_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(WelcomeActivity.this,
                        LoginActivity.class);
                startActivity(intent);
            }
        });

    }
}