package com.example.lab3userdata;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputLayout;

public class Address extends AppCompatActivity
implements ClickListener{

    private EditText userCountryEditText;
    private EditText userCityEditText;
    private EditText userAddressEditText;
    private TextInputLayout userCountryInputLayout;
    private TextInputLayout userCityInputLayout;
    private TextInputLayout userAddressInputLayout;
    private Button save;
    private Button cancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address_form);
        this.userCountryEditText = (EditText)this.findViewById(R.id.userCountryEditText);
        this.userCityEditText = (EditText)this.findViewById(R.id.userCityEditText);
        this.userAddressEditText = (EditText)this.findViewById(R.id.userAddressEditText);
        this.userCountryInputLayout = (TextInputLayout) this.findViewById(R.id.userCountryInputLayout);
        this.userCityInputLayout = (TextInputLayout) this.findViewById(R.id.userCityInputLayout);
        this.userAddressInputLayout = (TextInputLayout) this.findViewById(R.id.userAddressInputLayout);

        this.save = (Button)this.findViewById(R.id.saveButton);
        this.cancel = (Button)this.findViewById(R.id.cancelButton);

        this.save.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent returnIntent = new Intent();
                returnIntent.putExtra(Constants.FIELD_TYPE_KEY, Types.ADDRESS);
                returnIntent.putExtra(Constants.RESULT_KEY, Address.this.userCountryEditText.getText().toString()+", "+Address.this.userCityEditText.getText().toString()+ ", "+ Address.this.userAddressEditText.getText().toString());
                Address.this.setResult(Activity.RESULT_OK, returnIntent);
                Address.this.finish();
            }
        });

        this.cancel.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Address.this.setResult(Activity.RESULT_CANCELED);
                Address.this.finish();
            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_cancel, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.menuCancel) {
            CancelDialog cancelDialog = new CancelDialog();
            cancelDialog.show(getSupportFragmentManager(),Constants.CANCEL_DIALOG_KEY);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void click() {
        finish();
    }
}