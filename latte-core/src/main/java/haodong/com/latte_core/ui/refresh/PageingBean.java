package haodong.com.latte_core.ui.refresh;

/**
 * Created by linghaoDo on 2018/8/6
 */
public class PageingBean {
    // 当前是第几页
    private int mPageIndex = 0;
    // 总数据条数
    private int mTotal = 0;
    // 一页显示几条数据
    private int mPageSize = 0;
    // 当前已经显示了几条数据
    private int mCurrentCount = 0;
    // 加载延时
    private int mDelayed = 0;

    public int getPageIndex() {
        return mPageIndex;
    }

    // 链式调用  所以使用本身
    public PageingBean setPageIndex(int mPageIndex) {
        this.mPageIndex = mPageIndex;
        return this;
    }

    public int getTotal() {
        return mTotal;
    }

    public PageingBean setTotal(int mTotal) {
        this.mTotal = mTotal;
        return this;
    }

    public int getPageSize() {
        return mPageSize;
    }

    public PageingBean setPageSize(int mPageSize) {
        this.mPageSize = mPageSize;
        return this;
    }

    public int getCurrentCount() {
        return mCurrentCount;
    }

    public PageingBean setCurrentCount(int mCurrentCount) {
        this.mCurrentCount = mCurrentCount;
        return this;
    }

    public int getDelayed() {
        return mDelayed;
    }

    public PageingBean setDelayed(int mDelayed) {
        this.mDelayed = mDelayed;
        return this;
    }

    //第一次加载第一页时，
    PageingBean addIndex() {
        mPageIndex++;
        return this;
    }

}
