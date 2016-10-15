package id.sch.smktelkom_mlg.tugas01.xiirpl1018.unoicecreamshop;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener {

    Spinner spFlavour;
    EditText etcustomer, etamount;
    RadioGroup rgGelas;
    RadioButton conee, cupp;
    TextView tvHasil;
    CheckBox cbKoko, cbOreo, cbOvaltine;
    int nTopping;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spFlavour = (Spinner) findViewById(R.id.spinnerFlavour);
        etcustomer = (EditText) findViewById(R.id.editTextCustomer);
        etamount = (EditText) findViewById(R.id.editTextamount);
        rgGelas = (RadioGroup) findViewById(R.id.radioGroupGelas);
        conee = (RadioButton) findViewById(R.id.cone);
        cupp = (RadioButton) findViewById(R.id.cup);
        cbKoko = (CheckBox) findViewById(R.id.koko);
        cbOreo = (CheckBox) findViewById(R.id.oreo);
        cbOvaltine = (CheckBox) findViewById(R.id.ovaltine);
        tvHasil = (TextView) findViewById(R.id.tvHasil);

        cbKoko.setOnCheckedChangeListener(this);
        cbOreo.setOnCheckedChangeListener(this);
        cbOvaltine.setOnCheckedChangeListener(this);

        findViewById(R.id.buttonOrder).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                doClick();
            }
        });

        findViewById(R.id.koko).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cbKoko.setError(null);
                cbOreo.setError(null);
                cbOvaltine.setError(null);
            }
        });
        findViewById(R.id.oreo).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cbKoko.setError(null);
                cbOreo.setError(null);
                cbOvaltine.setError(null);
            }
        });
        findViewById(R.id.ovaltine).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cbKoko.setError(null);
                cbOreo.setError(null);
                cbOvaltine.setError(null);
            }
        });
        findViewById(R.id.cone).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                conee.setError(null);
                cupp.setError(null);
            }
        });
        findViewById(R.id.cup).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                conee.setError(null);
                cupp.setError(null);
            }
        });

    }

    private void doClick() {

        if (isValid()) {
            String nama, category = null, glass1 = null;
            int flavour, topping, glass, jumlah, total;
            flavour = 0;
            topping = 0;
            glass = 0;


            nama = etcustomer.getText().toString();
            jumlah = Integer.parseInt(etamount.getText().toString());

            if (spFlavour.getSelectedItem().toString().equals("Cappucino")) {
                flavour = 3000;
                category = "beneficial";
            } else if (spFlavour.getSelectedItem().toString().equals("Chocolate")) {
                flavour = 2000;
                category = "amazing";
            } else if (spFlavour.getSelectedItem().toString().equals("Durian")) {
                flavour = 5000;
                category = "attractive";
            } else if (spFlavour.getSelectedItem().toString().equals("Oreo")) {
                flavour = 3000;
                category = "awesome";
            } else if (spFlavour.getSelectedItem().toString().equals("Strawberry")) {
                flavour = 2000;
                category = "colorful";
            } else if (spFlavour.getSelectedItem().toString().equals("Vanilla")) {
                flavour = 2500;
                category = "beautiful";
            }

            if (cbKoko.isChecked()) topping += 1000;
            if (cbOreo.isChecked()) topping += 1000;
            if (cbOvaltine.isChecked()) topping += 2000;

            if (rgGelas.getCheckedRadioButtonId() != -1) {
                RadioButton rb = (RadioButton)
                        findViewById(rgGelas.getCheckedRadioButtonId());

                if (rb.getText().toString().equals("Cone")) {
                    glass = 1500;
                    glass1 = "Cone";
                } else if (rb.getText().toString().equals("Cup")) {
                    glass = 3000;
                    glass1 = "Cup";
                }
            }

            total = (flavour + topping + glass) * jumlah;

            //tvHasil.setText("Hello " + nama + "\n\n. Your order is " + jumlah + " ice cream " + glass1 + " flavour " + spFlavour.getSelectedItem().toString() + " with " + nTopping + " topping that's can make your day feel so " + category + ". \n\nPrice    : " + total);

            final String fNama = nama;
            final String fCategory = category;
            DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    switch (which) {
                        case DialogInterface.BUTTON_POSITIVE:
                            tvHasil.setTextColor(Color.RED);
                            tvHasil.setText("Your order have been canceled!");
                            break;

                        case DialogInterface.BUTTON_NEGATIVE:
                            etcustomer.setText("");
                            etamount.setText("");
                            cbKoko.setChecked(false);
                            cbOreo.setChecked(false);
                            cbOvaltine.setChecked(false);
                            spFlavour.setSelection(1);
                            conee.setChecked(false);
                            cupp.setChecked(false);
                            tvHasil.setTextColor(Color.GREEN);
                            tvHasil.setText("Thank you for your order, " + fNama + "!\n\nHope this " + spFlavour.getSelectedItem().toString() + " flavour can make your day feel so " + fCategory + "!");
                            break;
                    }
                }
            };

            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("Hello " + nama + "!\n\nYour order is " + jumlah + " " + spFlavour.getSelectedItem().toString() + " Ice Cream " + glass1 + " with " + nTopping + " topping.\n\nAre you sure want to get your order with price IDR " + total + "?").setNegativeButton("Yes", dialogClickListener)
                    .setPositiveButton("No", dialogClickListener).show();


            //Testing


        }
    }

    private boolean isValid() {

        boolean valid = true;
        String customer = etcustomer.getText().toString();
        String amount = etamount.getText().toString();

        if (customer.isEmpty()) {
            etcustomer.setError("What's your name?");
            valid = false;
        } else if (customer.length() < 3) {
            etcustomer.setError("Please insert minimum 3 character from your name!");
            valid = false;
        } else {
            etcustomer.setError(null);
        }


        if (amount.isEmpty()) {
            etamount.setError("Insert your amount of your GoMood ice cream order!");
            valid = false;
        } else {
            etamount.setError(null);
        }

        if (!cbOreo.isChecked() && !cbKoko.isChecked() && !cbOvaltine.isChecked()) {
            cbKoko.setError("");
            cbOvaltine.setError("");
            cbOreo.setError("");
            valid = false;
        } else {
            cbKoko.setError(null);
            cbOvaltine.setError(null);
            cbOreo.setError(null);
        }

        if (rgGelas.getCheckedRadioButtonId() == -1) {
            conee.setError("");
            cupp.setError("");
            valid = false;
        } else {
            conee.setError(null);
            cupp.setError(null);

        }


        return valid;

    }


    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if (isChecked) {
            nTopping += 1;
        } else {
            nTopping -= 1;
        }

    }
}
