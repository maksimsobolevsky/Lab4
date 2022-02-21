package com.example.lab3userdata;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import com.google.android.material.textfield.TextInputLayout;

public class MainActivity extends AppCompatActivity
implements ClickListener{

    private EditText userDataEditText;
    private EditText userAddressEditText;
    private EditText userCommentEditText;
    private TextInputLayout userDataInputLayout;
    private TextInputLayout userAddressInputLayout;
    private TextInputLayout userCommentInputLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.userDataEditText = (EditText)this.findViewById(R.id.userDataEditText);
        this.userAddressEditText = (EditText)this.findViewById(R.id.userAddressEditText);
        this.userCommentEditText = (EditText)this.findViewById(R.id.userCommentEditText);
        this.userDataInputLayout = (TextInputLayout) this.findViewById(R.id.userDataInputLayout);
        this.userAddressInputLayout = (TextInputLayout) this.findViewById(R.id.userAddressInputLayout);
        this.userCommentInputLayout = (TextInputLayout) this.findViewById(R.id.userCommentInputLayout);

        ActivityResultLauncher<Intent> someActivityResultLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        switch (result.getResultCode()){
                            case Activity.RESULT_OK: {
                               Intent data = result.getData();
                               Types type = (Types) data.getSerializableExtra(Constants.FIELD_TYPE_KEY);
                               String fieldContent = data.getStringExtra(Constants.RESULT_KEY);
                               switch (type){
                                   case USER_NAME:
                                       MainActivity.this.userDataEditText.setText(fieldContent);
                                       break;
                                   case ADDRESS:
                                       MainActivity.this.userAddressEditText.setText(fieldContent);
                                       break;
                                   case COMMENT:
                                       MainActivity.this.userCommentEditText.setText(fieldContent);
                                       break;
                               }
                                break;
                            }
                            case Activity.RESULT_CANCELED: {

                                break;
                            }

                        }
                    }
                });
        userDataInputLayout.setEndIconOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, UserName.class);
                someActivityResultLauncher.launch(intent);
            }
        });
        userAddressInputLayout.setEndIconOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Address.class);
                someActivityResultLauncher.launch(intent);
            }
        });
        userCommentInputLayout.setEndIconOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    Intent intent = new Intent(MainActivity.this, Comment.class);
                someActivityResultLauncher.launch(intent);
            }
        });

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_exit, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.menuExit) {
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