package de.feichhorn.booltr.booltr;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class LastInputs extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.lastinputs);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        TextView emptyInputs = findViewById(R.id.emptyInput);
        emptyInputs.setVisibility(View.VISIBLE);
        getWindow().setLayout((int) (width * .9), (int) (height * .15));

        for (int k=1; k<=10; k++) {

            String btnName = "lastInput" + k;
            int btnId = getResources().getIdentifier(btnName, "id", getPackageName());
            Button btnLastInput = findViewById(btnId);
            btnLastInput.setVisibility(View.GONE);

        }

        ArrayList inputExpression = getInputExpression();
        if (inputExpression!=null) {

            for (int i=0;i<inputExpression.size(); i++) {
                String inputExpressionString = inputExpression.get(i).toString();
                for (int j=0;j<=10;j++) {
                    int lastInputNum = j+1;
                    String buttonName = "lastInput" + lastInputNum;
                    int buttonId = getResources().getIdentifier(buttonName, "id", getPackageName());
                    Button btn = findViewById(buttonId);
                    int hightOutput = (((j+1)*200+(j*30))+100);
                    getWindow().setLayout((int) (width * .9), hightOutput);
                    if (btn!=null) {
                        if (btn.getText().toString().isEmpty()) {
                            btn.setText(inputExpressionString);
                            btn.setVisibility(View.VISIBLE);
                            emptyInputs.setVisibility(View.GONE);
                            break;
                        }
                    }
                }
            }
        }

        final Intent returnLastInput = new Intent();


        Button lastInput1 = findViewById(R.id.lastInput1);
        View.OnClickListener lastInput1Listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button lastInput1 = findViewById(R.id.lastInput1);
                setResult(RESULT_OK, returnLastInput.putExtra("input", lastInput1.getText().toString()));
                LastInputs.this.finish();
            }
        };
        lastInput1.setOnClickListener(lastInput1Listener);

        //
        //
        //

        Button lastInput2 = findViewById(R.id.lastInput2);
        View.OnClickListener lastInput2Listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button lastInput2 = findViewById(R.id.lastInput2);
                setResult(RESULT_OK, returnLastInput.putExtra("input", lastInput2.getText().toString()));
                LastInputs.this.finish();
            }
        };
        lastInput2.setOnClickListener(lastInput2Listener);

        //
        //
        //

        Button lastInput3 = findViewById(R.id.lastInput3);
        View.OnClickListener lastInput3Listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button lastInput3 = findViewById(R.id.lastInput3);
                setResult(RESULT_OK, returnLastInput.putExtra("input", lastInput3.getText().toString()));
                LastInputs.this.finish();
            }
        };
        lastInput3.setOnClickListener(lastInput3Listener);

        //
        //
        //

        Button lastInput4 = findViewById(R.id.lastInput4);
        View.OnClickListener lastInput4Listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button lastInput4 = findViewById(R.id.lastInput4);
                setResult(RESULT_OK, returnLastInput.putExtra("input", lastInput4.getText().toString()));
                LastInputs.this.finish();
            }
        };
        lastInput4.setOnClickListener(lastInput4Listener);

        //
        //
        //

        Button lastInput5 = findViewById(R.id.lastInput5);
        View.OnClickListener lastInput5Listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button lastInput5 = findViewById(R.id.lastInput5);
                setResult(RESULT_OK, returnLastInput.putExtra("input", lastInput5.getText().toString()));
                LastInputs.this.finish();
            }
        };
        lastInput5.setOnClickListener(lastInput5Listener);

        //
        //
        //

        Button lastInput6 = findViewById(R.id.lastInput6);
        View.OnClickListener lastInput6Listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button lastInput6 = findViewById(R.id.lastInput6);
                setResult(RESULT_OK, returnLastInput.putExtra("input", lastInput6.getText().toString()));
                LastInputs.this.finish();
            }
        };
        lastInput6.setOnClickListener(lastInput6Listener);

        //
        //
        //

        Button lastInput7 = findViewById(R.id.lastInput7);
        View.OnClickListener lastInput7Listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button lastInput7 = findViewById(R.id.lastInput7);
                setResult(RESULT_OK, returnLastInput.putExtra("input", lastInput7.getText().toString()));
                LastInputs.this.finish();
            }
        };
        lastInput7.setOnClickListener(lastInput7Listener);

        //
        //
        //

        Button lastInput8 = findViewById(R.id.lastInput8);
        View.OnClickListener lastInput8Listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button lastInput8 = findViewById(R.id.lastInput8);
                setResult(RESULT_OK, returnLastInput.putExtra("input", lastInput8.getText().toString()));
                LastInputs.this.finish();
            }
        };
        lastInput8.setOnClickListener(lastInput8Listener);

        //
        //
        //

        Button lastInput9 = findViewById(R.id.lastInput9);
        View.OnClickListener lastInput9Listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button lastInput9 = findViewById(R.id.lastInput9);
                setResult(RESULT_OK, returnLastInput.putExtra("input", lastInput9.getText().toString()));
                LastInputs.this.finish();
            }
        };
        lastInput9.setOnClickListener(lastInput9Listener);

        //
        //
        //

        Button lastInput10 = findViewById(R.id.lastInput10);
        View.OnClickListener lastInput10Listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button lastInput10 = findViewById(R.id.lastInput10);
                setResult(RESULT_OK, returnLastInput.putExtra("input", lastInput10.getText().toString()));
                LastInputs.this.finish();
            }
        };
        lastInput10.setOnClickListener(lastInput10Listener);

        //
        //
        //


    }
    public ArrayList getInputExpression() {
        Intent input = getIntent();
        if (input!=null) {
            ArrayList inputExpression = input.getStringArrayListExtra("inputs");
            return inputExpression;
        }
        else {
            return null;
        }
    }
}
