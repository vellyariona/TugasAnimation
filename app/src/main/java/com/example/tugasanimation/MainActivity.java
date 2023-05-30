package com.example.tugasanimation;
import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.CycleInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private ImageView nemo;
    private ImageView dory;
    private ImageView aquarium;

    private ImageView darla;
    private Button startButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nemo = findViewById(R.id.nemo);
        dory = findViewById(R.id.dory);
        startButton = findViewById(R.id.startButton);
        aquarium = findViewById(R.id.aquarium);
        darla = findViewById(R.id.darla);

        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startAnimation();
            }
        });
    }

    private void startAnimation() {
        // Putar objek
        ObjectAnimator rotation = ObjectAnimator.ofFloat(nemo, "rotation", 0f, 360f);
        rotation.setDuration(800);
        rotation.setRepeatCount(1);
        rotation.setInterpolator(new AccelerateInterpolator());

        // Jatuhkan objek
        ObjectAnimator fall = ObjectAnimator.ofFloat(nemo, "translationY", 0f, 500f);
        fall.setDuration(3000);
        fall.setInterpolator(new DecelerateInterpolator());

        // naikkan objek
        ObjectAnimator fall2 = ObjectAnimator.ofFloat(dory, "translationY", 0f, -500f);
        fall2.setDuration(700);

        // naikkan objek
        ObjectAnimator fall3 = ObjectAnimator.ofFloat(darla, "translationX", 0f, -500f);
        fall3.setDuration(2000);

        // Animasi bergetar objek menggunakan TranslateAnimation
        Animation shakeAnimation = new TranslateAnimation(0, 10, 0, 10);
        shakeAnimation.setDuration(1000);
        shakeAnimation.setInterpolator(new CycleInterpolator(20)); // Jumlah getaran

        // Terapkan animasi ke objek
        aquarium.startAnimation(shakeAnimation);


        // Kombinasikan animasi putar dan jatuh
        Animator.AnimatorListener listener = new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
                // Tindakan saat animasi dimulai
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                // Tindakan saat animasi selesai
                nemo.setTranslationY(0f); // Setel posisi vertikal kembali ke awal
//                imageView2.setVisibility(View.GONE);
                dory.setTranslationY(0f);
            }

            @Override
            public void onAnimationCancel(Animator animation) {
                // saat animasi dibatalkan
            }

            @Override
            public void onAnimationRepeat(Animator animation) {
                // saat animasi diulang
            }
        };

        rotation.addListener(listener); // Tambahkan listener ke animasi putar
        rotation.start(); // Mulai animasi putar
        fall2.start(); // Mulai animasi geser
        fall3.start();
        fall.start();
    }
}
