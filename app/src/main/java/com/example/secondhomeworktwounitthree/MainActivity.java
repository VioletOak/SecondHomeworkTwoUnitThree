package com.example.secondhomeworktwounitthree;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private TextView coordinatesOut;
    private float x;
    private float y;
    private String sDown;
    private String sMove;
    private String sUp;
    private final float xCat = 500;
    private final float yCat = 500;
    private final float deltaCat = 50;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        coordinatesOut = findViewById(R.id.coordinatesOut);
        coordinatesOut.setOnTouchListener(listener);
    }
    private View.OnTouchListener listener = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View view, MotionEvent motionEvent) {
            x = motionEvent.getX();
            y = motionEvent.getY();

            switch (motionEvent.getAction()) {
                case   MotionEvent.ACTION_DOWN:
                    sDown = "Нажатие: коордната Х = " + x + ", координата у = " + y;
                    sMove = " ";
                    sUp = " ";
                    break;
                case MotionEvent.ACTION_MOVE:
                    sMove = "Движение: коордната Х = " + x + ", координата у = " + y;
                    if (x < (xCat + deltaCat) && x > (xCat - deltaCat) && y < (yCat + deltaCat)
                            && y > (yCat - deltaCat)) {
                        Toast toast = Toast.makeText(getApplicationContext(),
                                R.string.successful_search, Toast.LENGTH_SHORT);

                        LinearLayout toastContainer = (LinearLayout) toast.getView();
                        ImageView cat = new ImageView(getApplicationContext());
                        cat.setImageResource(R.drawable.catone);
                        toastContainer.addView(cat, 1);
                        toast.setGravity(Gravity.LEFT|Gravity.TOP, (int)(x-100), (int) (y-50));
                        toast.show();
                    }
                    break;
                case MotionEvent.ACTION_UP:
                case MotionEvent.ACTION_CANCEL:
                    sMove = " ";
                    sUp = " ";
                    break;
            }
            coordinatesOut.setText(sDown + "\n" + sMove + "\n" + sUp);
            return true;
        }
    };
}