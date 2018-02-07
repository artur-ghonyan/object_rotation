package com.objectrotation;

import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;

import com.objectrotation.utlis.BitmapUtils;

public class MainActivity extends AppCompatActivity {
    private static final Float[] OBJECT_SIZE_VALUES = {0.5F, 1F, 1.5F};
    private static final int DEFAULT_OBJECT_SIZE = 1;
    private static final int BITMAP_SIDE = 250;

    private ImageView mImageViewShape;
    private Spinner mSpinnerScale;
    private TextView mTextViewAngle;

    private Bitmap mBitmap;

    private float mSelectedSize = 1F;
    private float mCurrentAngle = 0F;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final SeekBar seekBarRotate = findViewById(R.id.sb_rotation);
        mImageViewShape = findViewById(R.id.iv_shape);
        mTextViewAngle = findViewById(R.id.tv_angle);
        mSpinnerScale = findViewById(R.id.sp_scale);

        final ArrayAdapter<String> adapterScale = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.object_size_display_texts));
        adapterScale.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSpinnerScale.setAdapter(adapterScale);
        mSpinnerScale.setSelection(DEFAULT_OBJECT_SIZE);
        mSelectedSize = OBJECT_SIZE_VALUES[DEFAULT_OBJECT_SIZE];

        mBitmap = BitmapUtils.drawSquare(BITMAP_SIDE);
        updateViews();

        mSpinnerScale.setOnItemSelectedListener(spinnerScaleOnItemSelectedListener);
        seekBarRotate.setOnSeekBarChangeListener(seekBarRotateSeekBarChangeListener);
    }

    private void updateViews() {
        final Matrix matrix = new Matrix();
        matrix.postScale(mSelectedSize, mSelectedSize);
        matrix.postRotate(mCurrentAngle);
        final Bitmap resizedBitmap = Bitmap.createBitmap(mBitmap, 0, 0, BITMAP_SIDE, BITMAP_SIDE,
                matrix, true);

        mImageViewShape.setImageBitmap(resizedBitmap);
        mTextViewAngle.setText(String.format("Rotation angle: %s", (int) mCurrentAngle));
    }


    private SeekBar.OnSeekBarChangeListener seekBarRotateSeekBarChangeListener
            = new SeekBar.OnSeekBarChangeListener() {
        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            mCurrentAngle = (float) progress;
            updateViews();
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {
        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {
        }
    };

    private Spinner.OnItemSelectedListener spinnerScaleOnItemSelectedListener
            = new Spinner.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
            mSelectedSize = OBJECT_SIZE_VALUES[arg2];
            updateViews();
        }

        @Override
        public void onNothingSelected(AdapterView<?> arg0) {
            mSpinnerScale.setSelection(DEFAULT_OBJECT_SIZE);
            mSelectedSize = OBJECT_SIZE_VALUES[DEFAULT_OBJECT_SIZE];
        }
    };

}