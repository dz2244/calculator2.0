package com.example.calculator20;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity
{
    TextView res;
    EditText fnum,snum;

    String sNum1,sNum2;
    Double dNum1,dNum2 ,dRes = 0.0;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        res = findViewById(R.id.tvRes);
        fnum = findViewById(R.id.etFn);
        snum = findViewById(R.id.etSn);
        }
    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }
    public String beautifulNum(double result)
    {
        String scientificNotation = String.format("%.4e", result);
        String[] parts = scientificNotation.split("e");
        double base = Double.parseDouble(parts[0]) / 10.0;
        int exponent = Integer.parseInt(parts[1]) + 1;
        return String.format("%.4f * 10^%d", base, exponent);
    }
    public boolean onOptionsItemSelected(MenuItem menu)
    {

        sNum1 = fnum.getText().toString();
        sNum2 = snum.getText().toString();

        if(sNum1.isEmpty() || sNum2.isEmpty() )
        {
            Toast.makeText(this, "enter the two numbers before pressing. ", Toast.LENGTH_SHORT).show();
            return super.onOptionsItemSelected(menu);
        }

        else if(sNum1.equals("-") || sNum1.equals(".") || sNum1.equals("+") || sNum1.equals("+-") || sNum1.equals("-.") ||
                sNum2.equals("-") || sNum2.equals(".") || sNum2.equals("+") || sNum2.equals("+-") || sNum2.equals("-."))
        {
            Toast.makeText(this, "wrong input,enter numbers only", Toast.LENGTH_SHORT).show();
            return super.onOptionsItemSelected(menu);
        }

        dNum1 = Double.parseDouble(sNum1);
        dNum2 = Double.parseDouble(sNum2);

        int itemId = menu.getItemId();

        if(itemId == R.id.menuPlus)
        {
            dRes = dNum1 + dNum2;
        }
        else if(itemId == R.id.menuMinus)
        {
            dRes = dNum1 - dNum2;
        }
        else if(itemId == R.id.menuKefel)
        {
            dRes = dNum1 * dNum2;
        }
        else if(itemId == R.id.menuHiluk)
        {
            if (dNum2 != 0)
            {
                dRes = dNum1 / dNum2;
            }
            else
            {
                Toast.makeText(this, "you cannot divide by zero(im better)", Toast.LENGTH_SHORT).show();
                return super.onOptionsItemSelected(menu);
            }
        }
        else if(itemId == R.id.menuclear)
        {
            fnum.setText("");
            snum.setText("");
            res.setText("");
            return true;
        }

        if(dRes >= 1000000 )
        {
            res.setText("the result is: "+beautifulNum(dRes));
        }
        else
        {
            res.setText("the result is: "+dRes);
        }

        return super.onOptionsItemSelected(menu);
    }
}