package com.example.mysketchbook;

import android.content.Context;
import android.graphics.Color;
import android.text.InputType;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.EditText;
import android.widget.LinearLayout;

import androidx.appcompat.widget.AppCompatEditText;

public class TextTool extends AppCompatEditText {

    public TextTool(Context context, AttributeSet attrs) {
        super(context, attrs);
        initialize();
    }

    private void initialize() {
        setTextColor(Color.BLACK);
        setInputType(InputType.TYPE_CLASS_TEXT);
        setTextSize(20);
    }

    // Добавьте метод для активации TextTool
    public void activateTextMode(float x, float y, LinearLayout parentLayout) {
        // Создаем новое поле для ввода текста
        TextTool editText = new TextTool(getContext(), null);

        // Устанавливаем координаты
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );
        layoutParams.leftMargin = (int) x;
        layoutParams.topMargin = (int) y;

        editText.setLayoutParams(layoutParams);

        // Устанавливаем прозрачный фон
        editText.setBackgroundColor(Color.TRANSPARENT);

        // Добавляем поле для ввода текста на холст
        parentLayout.addView(editText);

        // Устанавливаем фокус и отображаем клавиатуру для ввода текста
        editText.requestFocus();

        // Устанавливаем фон программно
    }

    // Добавьте метод для деактивации TextTool
    public void deactivateTextMode() {
        setVisibility(INVISIBLE);
        clearFocus();
    }

    // Добавьте метод для получения текста из EditText
    public String getEnteredText() {
        return getText().toString();
    }

    @Override
    public boolean performClick() {
        // Обработайте событие щелчка при необходимости
        return super.performClick();
    }
}
