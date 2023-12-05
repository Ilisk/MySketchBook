package com.example.mysketchbook;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.graphics.drawable.ColorDrawable;

import androidx.appcompat.app.AppCompatActivity;

import android.widget.Button;
import android.widget.ImageButton;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.DialogFragment;
public class MainActivity extends AppCompatActivity {

    private ImageButton btnPaint, btnText, btnClear;
    private PaintArea paintArea;
    Button[] buttons;
    private int currentColor = Color.BLACK;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnPaint = (ImageButton) findViewById(R.id.buttonPaint);
        btnClear = (ImageButton) findViewById(R.id.buttonClear);
        btnText = (ImageButton) findViewById(R.id.buttonText);

        paintArea = (PaintArea) findViewById(R.id.paint);
        paintArea.setVisibility(View.INVISIBLE);

//        Button btnRed = findViewById(R.id.red);
//        Button btnGreen = findViewById(R.id.green);
//        Button btnBlue = findViewById(R.id.blue);

        buttons = new Button[]{
                findViewById(R.id.red),
                findViewById(R.id.green),
                findViewById(R.id.blue),
                findViewById(R.id.black),
                findViewById(R.id.purple),
                findViewById(R.id.yellow)
        };

        for (Button button : buttons) {
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onColorButtonClick((Button) view);
                }
            });
        }

    }

    public void onBtnPaintClick(View view) {
        // Показывает кнопки цветов
        showColorButtons();

        // Показывает область рисования
        paintArea.setVisibility(View.VISIBLE);
    }

//    public void onBtnRed(View view) {
//        // Устанавливает цвет в PaintArea
//        paintArea.setPaintColor(Color.RED);
//
//        // Скрывает кнопки цветов
//        hideColorButtons();
//    }
//
//    public void onBtnGreen(View view) {
//        // Устанавливает цвет в PaintArea
//        paintArea.setPaintColor(Color.GREEN);
//
//        // Скрывает кнопки цветов
//        hideColorButtons();
//    }
//
//    public void onBtnBlue(View view) {
//        // Устанавливает цвет в PaintArea
//        paintArea.setPaintColor(Color.BLUE);
//
//        // Скрывает кнопки цветов
//        hideColorButtons();
//    }

    public void onColorButtonClick(View view) {
        String tag = (String) view.getTag();

        if (tag != null) {
            int colorId = getResources().getIdentifier(tag, "color", getPackageName());
            int color = ContextCompat.getColor(this, colorId);
            paintArea.setPaintColor(color);
        }
        hideColorButtons();
    }


    private void showColorButtons() {
        for (Button btn : buttons) {
            btn.setVisibility(View.VISIBLE);
        }
    }

    private void hideColorButtons() {
        for (Button btn : buttons) {
            btn.setVisibility(View.INVISIBLE);
        }
    }


// Добавьте методы для других цветов при необходимости

    public void onBtnClearClick(View view) {
        paintArea.clearPaintArea();
    }

    public void onBtnTextClick(View view) {

    }




}
