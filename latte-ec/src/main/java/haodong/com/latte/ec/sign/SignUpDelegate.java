package haodong.com.latte.ec.sign;

import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.widget.AppCompatButton;
import android.util.Patterns;
import android.view.View;
import android.widget.Toast;

import java.util.regex.Pattern;

import butterknife.BindView;
import butterknife.OnClick;
import haodong.com.latte.ec.R;
import haodong.com.latte.ec.R2;
import haodong.com.latte_core.delegates.LatteDelegate;
import haodong.com.latte_core.net.RestClient;
import haodong.com.latte_core.net.callback.ISuccess;
import haodong.com.latte_core.util.log.LatteLogger;

/**
 * created by linghailong
 */
public class SignUpDelegate extends LatteDelegate {
    @BindView(R2.id.edit_sign_up_name)
    TextInputEditText mName = null;
    @BindView(R2.id.edit_sign_up_email)
    TextInputEditText mEmail = null;
    @BindView(R2.id.edit_sign_up_phone)
    TextInputEditText mPhone = null;
    @BindView(R2.id.edit_sign_up_password)
    TextInputEditText mPassword = null;
    @BindView(R2.id.edit_sign_up_re_password)
    TextInputEditText mRePassword = null;
    private ISignListener mISignListener = null;

    //    @BindView(R2.id.btn_sign_up);
//    AppCompatButton mSignUp
    @OnClick(R2.id.btn_sign_up)
    void onClickSignUp() {
        if (checkForm()) {
            RestClient.builder().url("http://192.168.56.1:8080/RestDataServer/api/user_profile.php")
                    .params("name", mName.getText().toString())
                    .params("email", mEmail.getText().toString())
                    .params("phone", mPhone.getText().toString())
                    .params("password", mPassword.getText().toString())
                    .success(new ISuccess() {
                        @Override
                        public void onSuccess(String response) {
                            LatteLogger.json("USER_PROFILE", response);
                            SignHandler.onSignUp(response, mISignListener);
                        }
                    })
                    .build()
                    .post();
            Toast.makeText(getContext(), "验证通过", Toast.LENGTH_LONG).show();
        }
    }

    @OnClick(R2.id.tv_link_sign_in)
    void onClickLink() {
        start(new SignInDelegate());
    }


    /**
     * setError记得归零 否则信息将一直存在
     */
    private boolean checkForm() {
        final String name = mName.getText().toString();
        final String email = mEmail.getText().toString();
        final String phone = mPhone.getText().toString();
        final String password = mPassword.getText().toString();
        final String rePassword = mRePassword.getText().toString();
        Boolean isPass = true;
        if (name.isEmpty()) {
            mName.setError("请输入姓名");
            isPass = false;
        } else {
            mName.setError(null);
        }
        if (email.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            mEmail.setError("错误的邮箱格式");
            isPass = false;
        } else {
            mEmail.setError(null);
        }
        if (phone.isEmpty() || phone.length() != 11) {
            mPhone.setError("错误的手机号码！");
            isPass = false;
        } else {
            mPhone.setError(null);
        }
        if (password.isEmpty() || password.length() < 6) {
            mPassword.setError("请填写至少6位的密码");
            isPass = false;
        } else {
            mPassword.setError(null);
        }
        if (rePassword.isEmpty() || rePassword.length() < 6 || !(rePassword.equals(password))) {
            mRePassword.setError("密码验证错误");
            isPass = false;
        } else {
            mRePassword.setError(null);
        }
        return isPass;

    }


    @Override
    public Object setLayout() {
        return R.layout.delegate_sign_up;
    }

    @Override
    public void onBindView(Bundle savedInstanceState, View rootView) {

    }
}
