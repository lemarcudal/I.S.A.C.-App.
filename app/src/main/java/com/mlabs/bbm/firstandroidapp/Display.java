package com.mlabs.bbm.firstandroidapp;

import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

/**
 * Created by Guitarista on 9/10/2016.
 */
public class Display extends Activity{

    public ImageView ontouch;
    public EditText first,second,diff,mot,quadr;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.display);

        ontouch = (ImageView) findViewById(R.id.ontouch);
        first = (EditText) findViewById(R.id.first);
        second = (EditText) findViewById(R.id.second);
        diff = (EditText) findViewById(R.id.diff);
        mot = (EditText) findViewById(R.id.mot);
        quadr = (EditText) findViewById(R.id.quadr);
        ontouch.setOnTouchListener(new View.OnTouchListener(){
            float x1, x2;
            float y1, y2;

            @Override
            public boolean onTouch(View view, MotionEvent motionevent){

                switch ( motionevent.getAction() ) {
                    case MotionEvent.ACTION_DOWN:
                        x1 = motionevent.getX();
                        y1 = motionevent.getY();
                        first.setText(x1 +","+y1);
                        //Toast.makeText(getBaseContext(), "X1= " + x1 + " Y1= " + y1 ,Toast.LENGTH_SHORT).show();
                        break;
                    case MotionEvent.ACTION_UP:
                        x2 = motionevent.getX();
                        y2 = motionevent.getY();



                        if (x1==x2 && y1==y2)
                        {
                            second.setText(x2 + "," + y2);
                            mot.setText("");
                            quadr.setText("");
                            diff.setText((x1-x2)+ "," +(y1-y2));
                        }

                        if (x1 > x2 && y1 > y2)
                        {
                            diff.setText((x1-x2)+ "," +(y1-y2));
                            second.setText(x2 + "," + y2);
                            mot.setText("SWIPE LEFT ; SWIPE UP");
                            quadr.setText("QUADRANT 2");
                        }
                        if (x1 > x2 && y1 < y2)
                        {
                            diff.setText((x1-x2)+ "," +(y2-y1));
                            second.setText(x2 + "," + y2);
                            mot.setText("SWIPE LEFT ; SWIPE DOWN");
                            quadr.setText("QUADRANT 3");
                        }
                        if (x1 < x2 && y1 > y2)
                        {
                            diff.setText((x2-x1)+ "," +(y1-y2));
                            second.setText(x2 + "," + y2);
                            mot.setText("SWIPE RIGHT ; SWIPE UP");
                            quadr.setText("QUADRANT 1");
                        }
                        if (x1 < x2 && y1 < y2)
                        {
                            diff.setText((x2-x1)+ ";" +(y2-y1));
                            second.setText(x2 + "," + y2);
                            mot.setText("SWIPE RIGHT ; SWIPE DOWN");
                            quadr.setText("QUADRANT 4");
                        }

                        break;
                }
                return true;
            }
        });
    }

}
