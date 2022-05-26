package kz.daur.edusystem;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class FaqActivity extends AppCompatActivity {

    RelativeLayout que_ans1, que_ans2;
    RelativeLayout que1, que2;
    TextView ans1, ans2;
    View line1, line2;
    ImageView show1, hide1, show2, hide2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faq);

        que1 = (RelativeLayout) findViewById(R.id.que1);
        ans1 = (TextView) findViewById(R.id.ans1);
        line1 = findViewById(R.id.line1);
        que_ans1 = (RelativeLayout) findViewById(R.id.que_ans1);
        show1 = (ImageView) findViewById(R.id.show1);
        hide1 = (ImageView) findViewById(R.id.hide1);
        que2 = (RelativeLayout) findViewById(R.id.que2);
        ans2 = (TextView) findViewById(R.id.ans2);
        line2 = findViewById(R.id.line2);
        que_ans2 = (RelativeLayout) findViewById(R.id.que_ans2);
        show2 = (ImageView) findViewById(R.id.show2);
        hide2 = (ImageView) findViewById(R.id.hide2);

        que1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ans1.setVisibility(View.VISIBLE);
                line1.setVisibility(View.INVISIBLE);
                que_ans1.setBackgroundColor(Color.parseColor("#80ADD8E6"));
                que1.setClickable(false);
                show1.setVisibility(View.INVISIBLE);
                hide1.setVisibility(View.VISIBLE);
            }
        });

        hide1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ans1.setVisibility(View.INVISIBLE);
                line1.setVisibility(View.VISIBLE);
                que_ans1.setBackgroundColor(Color.WHITE);
                que1.setClickable(true);
                show1.setVisibility(View.VISIBLE);
                hide1.setVisibility(View.INVISIBLE);
            }
        });

        que2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ans2.setVisibility(View.VISIBLE);
                line2.setVisibility(View.INVISIBLE);
                que_ans2.setBackgroundColor(Color.parseColor("#80ADD8E6"));
                que2.setClickable(false);
                show2.setVisibility(View.INVISIBLE);
                hide2.setVisibility(View.VISIBLE);
            }
        });

        hide2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ans2.setVisibility(View.INVISIBLE);
                line2.setVisibility(View.VISIBLE);
                que_ans2.setBackgroundColor(Color.WHITE);
                que2.setClickable(true);
                show2.setVisibility(View.VISIBLE);
                hide2.setVisibility(View.INVISIBLE);
            }
        });
    }

}