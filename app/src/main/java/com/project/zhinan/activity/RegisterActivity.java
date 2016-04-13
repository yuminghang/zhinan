package com.project.zhinan.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.project.zhinan.R;
import com.project.zhinan.utils.StringUtil;

public class RegisterActivity extends AppCompatActivity {

    private EditText mUsernameEditText;
    private EditText mPhoneEditText;
    private EditText mEmailEditText;
    private EditText mRePassEditText;
    private EditText mPassEditText;
    private Button mRegisterButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        initView();


    }

    private void initView() {
        mUsernameEditText = (EditText) findViewById(R.id.et_username);
        mPhoneEditText = (EditText) findViewById(R.id.et_phone);
        mEmailEditText = (EditText) findViewById(R.id.et_email);
        mPassEditText = (EditText) findViewById(R.id.et_pass);
        mRePassEditText = (EditText) findViewById(R.id.et_re_pass);
        mRegisterButton = (Button) findViewById(R.id.bt_register);
        mRegisterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (StringUtil.isNullOrEmpty(mUsernameEditText.getText().toString()) ||
                        StringUtil.isNullOrEmpty(mPhoneEditText.getText().toString())||
                        StringUtil.isNullOrEmpty(mEmailEditText.getText().toString())||
                        StringUtil.isNullOrEmpty(mPassEditText.getText().toString())||
                        StringUtil.isNullOrEmpty(mRePassEditText.getText().toString())) {
                    Toast.makeText(RegisterActivity.this, "请完善信息", Toast.LENGTH_SHORT).show();
                }else {

                }
            }
        });
    }
}
