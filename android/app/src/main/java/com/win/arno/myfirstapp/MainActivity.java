package com.win.arno.myfirstapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.justalk.cloud.juslogin.LoginDelegate;
import com.justalk.cloud.lemon.MtcCli;
import com.justalk.cloud.lemon.MtcCliConstants;

public class MainActivity extends AppCompatActivity implements LoginDelegate.Callback {

    private EditText etAccount;
    private EditText etPwd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etAccount = ((EditText) findViewById(R.id.activity_main_account_et));
        etPwd = ((EditText) findViewById(R.id.activity_main_pwd_et));

        LoginDelegate.setCallback(this);

        if (MtcCli.Mtc_CliGetState() == MtcCliConstants.EN_MTC_CLI_STATE_LOGINED) {
            Toast.makeText(this, "已登录", Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    protected void onResume() {
        super.onResume();
        LoginDelegate.enterForeground();
    }

    @Override
    protected void onStop() {
        LoginDelegate.enterBackground();
        super.onStop();
    }

    @Override
    public void mtcLoginOk() {
        Toast.makeText(this, "登录成功", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void mtcLoginDidFail() {
        Toast.makeText(this, "登录失败", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void mtcLogoutOk() {
        Toast.makeText(this, "登出成功", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void mtcLogouted() {
        Toast.makeText(this, "账号异地登录", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void mtcAuthRequire(String s, String s1) {

    }

    public void onLogin(View view){
        String account = etAccount.getText().toString();
        String pwd = etPwd.getText().toString();
        String network = "http:router.justalkcloud.com:8080";
        LoginDelegate.login(account, pwd, network);
    }

    public void onLogout(View view){
        LoginDelegate.logout();
    }
}
