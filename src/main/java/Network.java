import java.util.ArrayList;

public class Network {
    ArrayList<Router> routers = new ArrayList<>();
    int num_routers =0;
    int num_nodes = 0;
    int num_switchboards =0;

    public int getNum_routers() {
        return num_routers;
    }

    public void addRouter() {
        this.num_routers += 1;
    }

    public int getNum_nodes() {
        return num_nodes;
    }

    public void addNode() {
        this.num_nodes +=1;
    }
    public void addSwithcboard(){
        this.num_switchboards +=1;
    }
}
