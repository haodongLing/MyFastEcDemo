package haodong.com.latte_core.delegates;

public abstract class LatteDelegate extends PermissionCheckerDelegate {
    @SuppressWarnings("unchecked")
    public <T extends LatteDelegate> T getParentDelegate() {
        return (T) getParentFragment();
    }

}
