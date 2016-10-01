package com.mlabs.bbm.firstandroidapp;

import android.app.Activity;
import android.content.Intent;
import android.media.Image;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

/**
 * Created by benjarmanalili on 16/07/2016.
 * edited by lemarcudal on 21/07/2016
 */
public class SplashScreen extends Activity{

    MediaPlayer mySound;    //variable below for sound

    @Override
    protected void onPause() {
        super.onPause();
        mySound.release();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);
        //------------------------------------------------------------------------------------------
        final ImageView iv = (ImageView) findViewById(R.id.imageView2);
        final ImageView iv2 = (ImageView) findViewById(R.id.imageView3);
        final ImageView iv3 = (ImageView) findViewById(R.id.imageView4);
        final Animation an = AnimationUtils.loadAnimation(getBaseContext(),R.anim.rotate);
        final Animation an1 = AnimationUtils.loadAnimation(getBaseContext(),R.anim.rotate1);
        final Animation an2 = AnimationUtils.loadAnimation(getBaseContext(),R.anim.abc_fade_out);

        mySound = MediaPlayer.create(this, R.raw.isac_boot);
        mySound.start();
        iv.startAnimation(an);
        iv3.startAnimation(an1);
        iv2.startAnimation(an1);
        an.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }
            @Override
            public void onAnimationEnd(Animation animation) {
                iv.startAnimation(an2);
                iv2.startAnimation(an2);
                iv3.startAnimation(an2);
                finish();
                Intent i = new Intent(SplashScreen.this,MainActivity.class);
                startActivity(i);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }


}
