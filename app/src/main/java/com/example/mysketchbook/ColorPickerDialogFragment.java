package com.example.mysketchbook;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import androidx.fragment.app.DialogFragment;

public class ColorPickerDialogFragment extends DialogFragment {

    private OnColorSelectedListener onColorSelectedListener;

    interface OnColorSelectedListener {
        void onColorSelected(int color);
    }

    public void setOnColorSelectedListener(OnColorSelectedListener listener) {
        this.onColorSelectedListener = listener;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final int[] selectedColor = {Color.BLACK};  // Цвет по умолчанию

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Выберите цвет")
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        // Вызывается при нажатии на кнопку OK
                        if (onColorSelectedListener != null) {
                            onColorSelectedListener.onColorSelected(selectedColor[0]);
                        }
                    }
                })
                .setNegativeButton("Отмена", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        // Вызывается при нажатии на кнопку Отмена
                        dialog.dismiss();
                    }
                });

        final ColorPickerView colorPickerView = new ColorPickerView(getActivity());
        builder.setView(colorPickerView);

        // Обработчик изменения цвета в colorPickerView
        colorPickerView.setOnColorChangedListener(new ColorPickerView.OnColorChangedListener() {
            @Override
            public void onColorChanged(int color) {
                selectedColor[0] = color;
            }
        });

        return builder.create();
    }
}
