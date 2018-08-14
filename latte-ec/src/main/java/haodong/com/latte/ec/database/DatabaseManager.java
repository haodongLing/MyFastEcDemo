package haodong.com.latte.ec.database;

import android.content.Context;

import org.greenrobot.greendao.database.Database;

public class DatabaseManager {
    private DaoSession mDaoSession = null;
    private UserProfileDao mDao = null;

    private static final class Holder {
        // 实例化
        private static final DatabaseManager INSTANCE = new DatabaseManager();
    }

    public static DatabaseManager getInstance() {
        return Holder.INSTANCE;
    }

    private DatabaseManager() {

    }

    public DatabaseManager init(Context context) {
        initDao(context);
        return this;
    }

    public final UserProfileDao getmDao() {
        return mDao;
    }

    private void initDao(Context context) {
        final ReleaseOpenHelper openHelper = new ReleaseOpenHelper(context, "fast_ec.db");
        final Database db = openHelper.getWritableDb();
        mDaoSession = new DaoMaster(db).newSession();
        mDao = mDaoSession.getUserProfileDao();
    }
}
