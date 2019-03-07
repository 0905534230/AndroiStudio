package com.example.calculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    EditText mEdtInput;
    ArrayList<String> arrayResults;
    ArrayAdapter<String> arrayAdapter;
    ListView lvResult;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mEdtInput = (EditText) findViewById(R.id.edt_input);
        lvResult = (ListView) findViewById(R.id.lvResult);

        arrayResults = new ArrayList<String>();
        arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, arrayResults);
        lvResult.setAdapter(arrayAdapter);

    }


    public void onClick(View v) {
        Toast toast = Toast.makeText(MainActivity.this , "Input again " , Toast.LENGTH_SHORT);
        try {
            switch (v.getId()) {
                case R.id.btn_1:
                    mEdtInput.append("1");
                    break;

                case R.id.btn_2:
                    mEdtInput.append("2");
                    break;
                case R.id.btn_3:
                    mEdtInput.append("3");
                    break;

                case R.id.btn_4:
                    mEdtInput.append("4");
                    break;

                case R.id.btn_5:
                    mEdtInput.append("5");
                    break;

                case R.id.btn_6:
                    mEdtInput.append("6");
                    break;

                case R.id.btn_7:
                    mEdtInput.append("7");
                    break;

                case R.id.btn_8:
                    mEdtInput.append("8");
                    break;

                case R.id.btn_9:
                    mEdtInput.append("9");
                    break;

                case R.id.btn_0:
                    mEdtInput.append("0");
                    break;

                case R.id.btn_dot:
                    mEdtInput.append(".");
                    break;



            }
        }catch (NumberFormatException e){
            toast.show();
        }
    }
    public void doClearC(View v){
        arrayResults.clear();
        arrayAdapter.notifyDataSetChanged();
        mEdtInput.setText("");
    }

    public void doClearCe(View v){
        mEdtInput.setText("");

    }

    public void doPercent(View v ){
        double a ;
        a = Float.parseFloat(mEdtInput.getText().toString());
        double result = a / 100;
        String stringResult = a + "%" + "=" + result;
        arrayResults.add(stringResult);
        arrayAdapter.notifyDataSetChanged();
        mEdtInput.setText(Double.toString(result));
    }

    public void doSqrt(View v){
        double a;
        a = Double.parseDouble(mEdtInput.getText().toString());
        double result = Math.sqrt(a);
        String stringResult = "√" + a + "=" + result;
        arrayResults.add(stringResult);
        arrayAdapter.notifyDataSetChanged();
        mEdtInput.setText(Double.toString(result));
    }

    public void doSquare(View v){
        double a;
        a = Double.parseDouble(mEdtInput.getText().toString());
        double result = a * a;
        String stringResult = a + "x^2" + "=" + result;
        arrayResults.add(stringResult);
        arrayAdapter.notifyDataSetChanged();
        mEdtInput.setText(Double.toString(result));
    }

    public void do1DivX(View v){
        double a;
        a = Double.parseDouble(mEdtInput.getText().toString());
        double result = 1 / a;
        String stringResult = "1 / " + a + "=" + result;
        arrayResults.add(stringResult);
        arrayAdapter.notifyDataSetChanged();
        mEdtInput.setText(Double.toString(result));
    }

    public void doAddSub(View v){
        double a;
        a = Double.parseDouble(mEdtInput.getText().toString());
        double result = a * -1;
        String stringResult = a + " = " + result ;
        arrayResults.add(stringResult);
        arrayAdapter.notifyDataSetChanged();
        mEdtInput.setText(Double.toString(result));

    }

    public void doReset(View v){
        arrayResults.removeAll(arrayResults);
        arrayAdapter.notifyDataSetChanged();

        if(arrayResults.isEmpty()){
            arrayResults.add("There's no history yet.");
        }
    }

    public void doDel(View v){
        String st = mEdtInput.getText().toString();
        if (st.length() > 0){
            st = st.substring(0 , st.length() - 1);
            mEdtInput.setText(st);
        }
    }


    public void doAdd(View v){
        double a , b;
        a = Double.parseDouble(mEdtInput.getText().toString());
        b = Double.parseDouble(mEdtInput.getText().toString());
    }

    public void doEqual(View v){

    }
}
