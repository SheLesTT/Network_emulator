import javax.swing.tree.DefaultMutableTreeNode;
import java.util.ArrayList;

public class Network {
    ArrayList<Router> routers = new ArrayList<>();
    int num_routers =0;
    int num_nodes = 0;
    int num_switchboards =0;

    public Network() {
        // Default constructor
    }
    public void addRouter(Router router){
        routers.add(router);
    }

    public ArrayList<Router> getRouters() {
        return routers;
    }

    public void setRouters(ArrayList<Router> routers) {
        this.routers = routers;
    }

    public int getNum_routers() {
        return num_routers;
    }

    public void setNum_routers(int num_routers) {
        this.num_routers = num_routers;
    }

    public int getNum_nodes() {
        return num_nodes;
    }

    public void setNum_nodes(int num_nodes) {
        this.num_nodes = num_nodes;
    }

    public int getNum_switchboards() {
        return num_switchboards;
    }

    public void setNum_switchboards(int num_switchboards) {
        this.num_switchboards = num_switchboards;
    }



    public void addRouter() {
        this.num_routers += 1;
    }

    public void addNode() {
        this.num_nodes += 1;
    }

    public void addSwitchboard() {
        this.num_switchboards += 1;
    }
    public DefaultMutableTreeNode addInfoToGui(){
        DefaultMutableTreeNode main = new DefaultMutableTreeNode("network");
        for(Router router: routers){
            main.add(router.addNode());
        }
        return main;
    }
}
