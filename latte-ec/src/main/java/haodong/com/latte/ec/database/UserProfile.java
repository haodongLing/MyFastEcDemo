package haodong.com.latte.ec.database;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.annotation.Generated;

@Entity(nameInDb = "user_profile")
public class UserProfile {
    @Id
    private long userId=0;
    private String name=null;
    private String avator=null;
    private String gender=null;
    private String adress=null;
    @Generated(hash = 207532133)
    public UserProfile(long userId, String name, String avator, String gender,
            String adress) {
        this.userId = userId;
        this.name = name;
        this.avator = avator;
        this.gender = gender;
        this.adress = adress;
    }
    @Generated(hash = 968487393)
    public UserProfile() {
    }
    public long getUserId() {
        return this.userId;
    }
    public void setUserId(long userId) {
        this.userId = userId;
    }
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getAvator() {
        return this.avator;
    }
    public void setAvator(String avator) {
        this.avator = avator;
    }
    public String getGender() {
        return this.gender;
    }
    public void setGender(String gender) {
        this.gender = gender;
    }
    public String getAdress() {
        return this.adress;
    }
    public void setAdress(String adress) {
        this.adress = adress;
    }

}
