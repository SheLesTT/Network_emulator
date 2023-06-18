import javax.swing.tree.DefaultMutableTreeNode;
import java.io.Serializable;
import javax.swing.tree.MutableTreeNode;
import java.util.ArrayList;
import static java.lang.Math.pow;
public class Router extends  NetworkComponent implements Serializable{
    int mask_length;
    int number;
    int subnetwork_size = 0;
    double subnetwok_count = 0;
//    IdGenerator generator = new IdGenerator();
    ArrayList<RouterPort> ports = new ArrayList<>();
    public Router (int mask_length, int number){
        this.mask_length = mask_length;
        this.number = number;
        this.subnetwork_size = (int) pow(2,(32-mask_length));
        this.subnetwok_count = (int) pow(2,8-(32-mask_length));
    }
    public Router() {
        // Default constructor
    }

    public void addPorts(Creator creator){
        for(int i =0; i< subnetwok_count;i++){
            RouterPort port = creator.createPort(subnetwork_size+1);
            ports.add(port);
        }
    }

    public MutableTreeNode addNode(){
        DefaultMutableTreeNode router_node = new DefaultMutableTreeNode("router " + number );
        for(RouterPort port: ports){
            port.addNode();
        }
        return router_node;
    }


    public void Print(){
        System.out.println("router number" + number);
        System.out.println("mask length" + mask_length);
        System.out.println(subnetwork_size);
        for(RouterPort port: ports){
            port.Print();
        }
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

    public ArrayList<RouterPort> getPorts() {
        return ports;
    }

    public void setPorts(ArrayList<RouterPort> ports) {
        this.ports = ports;
    }

}
