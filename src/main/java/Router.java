import javax.swing.tree.DefaultMutableTreeNode;
import java.io.Serializable;
import javax.swing.tree.MutableTreeNode;
import java.util.ArrayList;
import static java.lang.Math.pow;
import com.fasterxml.jackson.annotation.JsonIgnore;
public class Router extends  NetworkComponent implements Serializable{


    @JsonIgnore
    Network net = null;
    int mask_length;
    int number;
    int subnetwork_size = 0;
    double subnetwok_count = 0;
//    IdGenerator generator = new IdGenerator();
    ArrayList<String> ports = new ArrayList<>();
    public Router (int mask_length, int number, Network net){
        this.mask_length = mask_length;
        this.number = number;
        this.subnetwork_size = (int) pow(2,(32-mask_length));
        this.subnetwok_count = (int) pow(2,8-(32-mask_length));
        this.net = net;
    }
    public Router() {
        // Default constructor
    }

    public void addPorts(Creator creator){
        for(int i =0; i< subnetwok_count;i++){
            RouterPort port = creator.createPort(subnetwork_size+1);
            ports.add(port.getId());
        }
    }

    public MutableTreeNode addTreeNode(){
        DefaultMutableTreeNode router_node = new DefaultMutableTreeNode("router " + number );
        for(String port: ports){
            RouterPort port_rout = (RouterPort) net.all_nodes.get(port);
            router_node.add(port_rout.addTreeNode());
        }
        return router_node;
    }


    public void Print(){
        System.out.println("router number" + number);
        System.out.println("mask length" + mask_length);
        System.out.println(subnetwork_size);
        for(String port: ports){
            System.out.println(port);
            RouterPort port_rout = (RouterPort) net.all_nodes.get(port);
            port_rout.Print();
        }
    }
    public void setNet(Network net) {
        this.net = net;
    }
    public int getMask_length() {
        return mask_length;
    }

    public void setMask_length(int mask_length) {
        this.mask_length = mask_length;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getSubnetwork_size() {
        return subnetwork_size;
    }

    public void setSubnetwork_size(int subnetwork_size) {
        this.subnetwork_size = subnetwork_size;
    }

    public double getSubnetwok_count() {
        return subnetwok_count;
    }

    public void setSubnetwok_count(double subnetwok_count) {
        this.subnetwok_count = subnetwok_count;
    }

    public ArrayList<String> getPorts() {
        return ports;
    }

    public void setPorts(ArrayList<String> ports) {
        this.ports = ports;
    }

}
