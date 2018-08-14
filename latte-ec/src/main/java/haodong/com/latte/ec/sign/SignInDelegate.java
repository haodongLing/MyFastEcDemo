package haodong.com.latte.ec.sign;

import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.util.Patterns;
import android.view.View;

import butterknife.BindView;
import butterknife.OnClick;
import haodong.com.latte.ec.R;
import haodong.com.latte.ec.R2;
import haodong.com.latte_core.delegates.LatteDelegate;
import haodong.com.latte_core.net.RestClient;
import haodong.com.latte_core.net.callback.ISuccess;
import haodong.com.latte_core.util.log.LatteLogger;

public class SignInDelegate extends LatteDelegate {
    @BindView(R2.id.edit_sign_in_email)
    TextInputEditText mEmail = null;
    @BindView(R2.id.edit_sign_in_password)
    TextInputEditText mPassword = null;

    private ISignListener mISignListener=null;
    @OnClick(R2.id.btn_sign_in)

    void onClickSignIn(){
        if(checkForm()){
            RestClient.builder()
                    .url("http://192.168.56.1:8080/RestDataServer/api/user_profile.php")
                    .params("email", mEmail.getText().toString())
                    .params("password", mPassword.getText().toString())
                    .success(new ISuccess() {
                        @Override
                        public void onSuccess(String response) {
                            LatteLogger.json("USER_PROFILE", response);
                            SignHandler.onSignIn(response, mISignListener);
                        }
                    })
                    .build()
                    .post();
        }
    }
    @OnClick(R2.id.icon_sign_in_wechat)
    void onClickSignWechat(){

    }
    @OnClick(R2.id.tv_link_sign_up)
    void onClickLink(){
        start(new SignUpDelegate());
    }


    @Override
    public Object setLayout() {
        return R.layout.delegate_sign_in;
    }

    private boolean checkForm() {

        final String email = mEmail.getText().toString();

        final String password = mPassword.getText().toString();

        Boolean isPass = true;

        if (email.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            mEmail.setError("错误的邮箱格式");
            isPass = false;
        } else {
            mEmail.setError(null);
        }
        if (password.isEmpty() || password.length() < 6) {
            mPassword.setError("请填写至少6位的密码");
            isPass = false;
        } else {
            mPassword.setError(null);
        }
        return isPass;

    }


    @Override
    public void onBindView(Bundle savedInstanceState, View rootView) {

    }
}
