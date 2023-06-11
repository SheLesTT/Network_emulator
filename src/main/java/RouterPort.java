public class RouterPort implements IdTable {
    String id ;
    String max_subnet_id ;
    String min_subnet_id;

    public RouterPort(String id, String max_subnet_id, String min_subnet_id){
        this.id = id;
        this.max_subnet_id = max_subnet_id;
        this.min_subnet_id = min_subnet_id;
    }

}

