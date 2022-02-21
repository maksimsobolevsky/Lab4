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

public class UserName extends AppCompatActivity
implements ClickListener{

    private EditText userNameEditText;
    private EditText userSurnameEditText;
    private TextInputLayout userNameInputLayout;
    private TextInputLayout userSurnameInputLayout;
    private Button save;
    private Button cancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_name_form);

        this.userNameEditText = (EditText)this.findViewById(R.id.userNameEditText);
        this.userSurnameEditText = (EditText)this.findViewById(R.id.userSurnameEditText);
        this.userNameInputLayout = (TextInputLayout) this.findViewById(R.id.userNameInputLayout);
        this.userSurnameInputLayout = (TextInputLayout) this.findViewById(R.id.userSurnameInputLayout);

        this.save = (Button)this.findViewById(R.id.saveButton);
        this.cancel = (Button)this.findViewById(R.id.cancelButton);

        this.save.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent returnIntent = new Intent();
                returnIntent.putExtra(Constants.FIELD_TYPE_KEY, Types.USER_NAME);
                returnIntent.putExtra(Constants.RESULT_KEY, UserName.this.userNameEditText.getText().toString() +" " + UserName.this.userSurnameEditText.getText().toString());
                UserName.this.setResult(Activity.RESULT_OK, returnIntent);
                UserName.this.finish();
            }
        });

        this.cancel.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                UserName.this.setResult(Activity.RESULT_CANCELED);
                UserName.this.finish();
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
