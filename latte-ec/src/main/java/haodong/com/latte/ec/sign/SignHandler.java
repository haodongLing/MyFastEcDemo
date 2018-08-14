package haodong.com.latte.ec.sign;

import android.accounts.AccountManager;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import haodong.com.latte.ec.database.DatabaseManager;
import haodong.com.latte.ec.database.UserProfile;
import haodong.com.latte_core.app.AccuntManager;



public class SignHandler {

    public static void onSignIn(String response, ISignListener signListener) {
        final JSONObject profileJson = JSON.parseObject(response).getJSONObject("data");
        final long userId = profileJson.getLong("userId");
        final String name = profileJson.getString("name");
        final String avatar = profileJson.getString("avatar");
        final String gender = profileJson.getString("gender");
        final String address = profileJson.getString("address");

        final UserProfile profile = new UserProfile(userId, name, avatar, gender, address);
        DatabaseManager.getInstance().getmDao().insert(profile);

        //已经注册并登录成功了
        AccuntManager.setSignState(true);
        signListener.onSignInSuccess();
    }


    public static void onSignUp(String response, ISignListener signListener) {
        final JSONObject profileJson = JSON.parseObject(response).getJSONObject("data");
        final long userId = profileJson.getLong("userId");
        final String name = profileJson.getString("name");
        final String avatar = profileJson.getString("avatar");
        final String gender = profileJson.getString("gender");
        final String address = profileJson.getString("address");

        final UserProfile profile = new UserProfile(userId, name, avatar, gender, address);
        DatabaseManager.getInstance().getmDao().insert(profile);

        //已经注册并登录成功了
        AccuntManager.setSignState(true);
        signListener.onSignUpSuccess();
    }
}
