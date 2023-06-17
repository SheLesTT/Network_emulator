public class Swithcboard extends NetworkComponent implements IdTable{
    @Override
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    String id = null;
}
