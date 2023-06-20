public class IdTableWrapper {
    private String implementationType;
    private IpTable data;

    public IdTableWrapper() {
    }

    public IdTableWrapper(String implementationType, IpTable data) {
        this.implementationType = implementationType;
        this.data = data;
    }

    public String getImplementationType() {
        return implementationType;
    }

    public void setImplementationType(String implementationType) {
        this.implementationType = implementationType;
    }

    public IpTable getData() {
        return data;
    }

    public void setData(IpTable data) {
        this.data = data;
    }
}
