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

public class Comment extends AppCompatActivity
implements ClickListener{

    private EditText userCommentEditText;
    private TextInputLayout userCommentInputLayout;
    private Button save;
    private Button cancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment_form);

        this.userCommentEditText = (EditText)this.findViewById(R.id.userCommentEditText);
        this.userCommentInputLayout = (TextInputLayout) this.findViewById(R.id.userCommentInputLayout);

        this.save = (Button)this.findViewById(R.id.saveButton);
        this.cancel = (Button)this.findViewById(R.id.cancelButton);

        this.save.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent returnIntent = new Intent();
                returnIntent.putExtra(Constants.FIELD_TYPE_KEY, Types.COMMENT);
                returnIntent.putExtra(Constants.RESULT_KEY, Comment.this.userCommentEditText.getText().toString());
                Comment.this.setResult(Activity.RESULT_OK, returnIntent);
                Comment.this.finish();
            }
        });


        this.cancel.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Comment.this.setResult(Activity.RESULT_CANCELED);
                Comment.this.finish();
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