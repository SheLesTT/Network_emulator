public class IdTableWrapper {
    private String implementationType;
    private IdTable data;

    public IdTableWrapper() {
    }

    public IdTableWrapper(String implementationType, IdTable data) {
        this.implementationType = implementationType;
        this.data = data;
    }

    public String getImplementationType() {
        return implementationType;
    }

    public void setImplementationType(String implementationType) {
        this.implementationType = implementationType;
    }

    public IdTable getData() {
        return data;
    }

    public void setData(IdTable data) {
        this.data = data;
    }
}
