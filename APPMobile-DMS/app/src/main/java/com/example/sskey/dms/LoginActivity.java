package com.example.sskey.dms;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sskey.dms.Utils.UtilBasic;
import com.sskey.dms.data.remote.APIUtils;
import com.sskey.dms.data.remote.SOService;
import com.sskey.dms.model.UserInFoDTO;
import com.sskey.dms.utils.PreferenceUtils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    private static final String TAG = "LoginActivity";
    private static final int REQUEST_SIGNUP = 0;

    EditText userUserNameText;
    EditText passwordText;
    Button loginButton;
    TextView signupLink;
    SOService mService;
    UserInFoDTO nguoiDung;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //init API service
        mService = APIUtils.getSOService();
        nguoiDung = new UserInFoDTO();

        userUserNameText = (EditText) findViewById(R.id.input_userUserName);
        passwordText = (EditText) findViewById(R.id.input_password);
        loginButton = (Button) findViewById(R.id.btn_login);
        signupLink = (TextView) findViewById(R.id.link_signup);

        //check login exits
        if (PreferenceUtils.getPhone(this) != null && PreferenceUtils.getPassword(this) != null) {
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            intent.putExtra("SDT", PreferenceUtils.getPhone(this));
            startActivity(intent);
        } else {

            login();
        }

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();

            }
        });

        signupLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, SignUpActivity.class);
                startActivityForResult(intent, REQUEST_SIGNUP);
            }
        });

    }

    private void login() {
        Log.d(TAG, "Login");

        if (!validate()) {
            onLoginFailed();
            return;
        }

        loginButton.setEnabled(false);
        final ProgressDialog progressDialog = new ProgressDialog(LoginActivity.this, R.style.AppTheme_Dark_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Wait....");
        progressDialog.show();

        final String username = userUserNameText.getText().toString();

        final String matKhau = passwordText.getText().toString();

        mService.login(username, matKhau).enqueue(new Callback<UserInFoDTO>() {
            @Override
            public void onResponse(Call<UserInFoDTO> call, Response<UserInFoDTO> response) {
                nguoiDung = response.body();
//                progressDialog.cancel();
                try {
                    if (nguoiDung != null) {
                        // save in Preference
                        PreferenceUtils.saveMaNguoiDung(nguoiDung.getUserID(), LoginActivity.this);
                        PreferenceUtils.savePhone(nguoiDung.getUserName(), LoginActivity.this);
                        PreferenceUtils.saveLoaiNguoiDung(nguoiDung.getEmployeeId(), LoginActivity.this);
                        PreferenceUtils.savePassword(nguoiDung.getPassWord(), LoginActivity.this);
                        PreferenceUtils.saveTenNguoiDung(username, LoginActivity.this);

                        //save object pars json
                        String obj = UtilBasic.ObjectToJson(nguoiDung);
                        PreferenceUtils.saveObj(obj, LoginActivity.this);
                        Intent accountsIntent = new Intent(LoginActivity.this, MainActivity.class);
                        progressDialog.cancel();
                        startActivity(accountsIntent);

                        Toast.makeText(LoginActivity.this, "Đăng nhập thành công!!!", Toast.LENGTH_SHORT).show();

                    } else {
                        progressDialog.cancel();
                        Toast.makeText(LoginActivity.this, "Đang nhập không thành công, Vui lòng đang nhập lại", Toast.LENGTH_SHORT).show();

                    }

                } catch (Exception e) {
                    progressDialog.cancel();
                    Toast.makeText(LoginActivity.this, "Không có tín hiệu từ API", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<UserInFoDTO> call, Throwable t) {
                Toast.makeText(LoginActivity.this, t.getMessage()+"Đang nhập không thành công, Vui lòng kiểm tra lại số điện thoại và mật khẩu", Toast.LENGTH_SHORT).show();

            }
        });
        new android.os.Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                onLoginSuccess();
//                progressDialog.show();
            }
        }, 1000);
    }

    private void onLoginSuccess() {
        loginButton.setEnabled(true);
    }

    private void onLoginFailed() {
        Toast.makeText(getBaseContext(), "Vui lòng kiểm tra đăng nhập", Toast.LENGTH_SHORT).show();


        loginButton.setEnabled(true);
    }

    private boolean validate() {

        boolean valid = true;

        String username = userUserNameText.getText().toString();
        String password = passwordText.getText().toString();

        if (username.isEmpty()) {
            userUserNameText.setError("Bạn chưa nhập số điện thoại! Vui lòng kiểm tra lại");
            valid = false;
        } else {
            userUserNameText.setError(null);
        }

        if (password.isEmpty()) {
            passwordText.setError("Bạn chưa nhập mật khẩu! Vui lòng kiểm tra lại");
            valid = false;
        } else {
            passwordText.setError(null);
        }

        return valid;
    }

    @Override
    public void onBackPressed() {
        moveTaskToBack(true);
    }
}
