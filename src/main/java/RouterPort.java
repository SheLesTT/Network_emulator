import javax.swing.tree.DefaultMutableTreeNode;
import java.util.ArrayList;

public class RouterPort implements IdTable {
    private String id ;
    private String max_subnet_id ;
    private String min_subnet_id;

    ArrayList<IdTable>  linked_nodes = null;

    public RouterPort(){}

    public RouterPort(String id, String max_subnet_id, String min_subnet_id){
        this.id = id;
        this.max_subnet_id = max_subnet_id;
        this.min_subnet_id = min_subnet_id;
    }

    @Override
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMax_subnet_id() {
        return max_subnet_id;
    }

    public void setMax_subnet_id(String max_subnet_id) {
        this.max_subnet_id = max_subnet_id;
    }

    public String getMin_subnet_id() {
        return min_subnet_id;
    }

    public void setMin_subnet_id(String min_subnet_id) {
        this.min_subnet_id = min_subnet_id;
    }

    public void addNode(){
        DefaultMutableTreeNode port_node = new DefaultMutableTreeNode("port + " + id );
    }
    public void Print(){
        System.out.println("id " + id + " max id " + max_subnet_id +" min id " + min_subnet_id);
//        for (IdTable node: linked_nodes){
//            System.out.println(node.node_name + node.getId());
//        }
    }

}

