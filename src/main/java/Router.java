import javax.swing.tree.DefaultMutableTreeNode;
import java.io.Serializable;
import javax.swing.tree.MutableTreeNode;
import java.util.ArrayList;
import static java.lang.Math.pow;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Router implements Serializable{


    @JsonIgnore
    Network net = null;
    @JsonProperty
    int mask_length;
    @JsonProperty
    int number;
    @JsonProperty
    int subnetwork_size = 0;
    @JsonProperty
    double subnetwok_count = 0;
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

    public void addPorts(Creator creator ){
        for(int i =0; i< subnetwok_count;i++){

            RouterPort port = creator.createPort(this,subnetwork_size);
            ports.add(port.getIp());
            port.attachSubNet(creator);
        }
        for(String port_ip: ports){
            IpTable port = net.getNodeByID(port_ip);
            for(String node_ip: ports){
                if (node_ip != port_ip){
                    port.addConnection(node_ip);
                }
            }
        }
    }



    public MutableTreeNode addTreeNode(){
        DefaultMutableTreeNode router_node = new DefaultMutableTreeNode("router " + number );
        for(String port: ports){
            RouterPort port_rout = (RouterPort) net.all_nodes.get(port);
//            System.out.println(port_rout);
            router_node.add(port_rout.addTreeNode(""));

        }
        return router_node;
    }


//    public void Print(){
//        System.out.println("router number" + number);
//        System.out.println("mask length" + mask_length);
//        System.out.println(subnetwork_size);
//        for(String port: ports){
//            System.out.println(port);
//            RouterPort port_rout = (RouterPort) net.all_nodes.get(port);
//            port_rout.Print();
//        }
//    }
    public void setNet(Network net) {
        this.net = net;
    }
    public int getNumber() {
        return number;
    }

    public ArrayList<String> getPorts() {
        return ports;
    }

    public void setPorts(ArrayList<String> ports) {
        this.ports = ports;
    }

}
