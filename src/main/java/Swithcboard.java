import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.MutableTreeNode;
import java.util.ArrayList;

public class Swithcboard implements IpTable {


    @JsonIgnore
    Network net;

    @JsonProperty
    String ip = null;
    @JsonProperty
    int number  ;
    @JsonProperty
    String type  = "Switchboard";
    @JsonProperty
    int linked_nodes_counter = 1;
    @JsonProperty
    ArrayList<String> linked_nodes = new ArrayList<>();
    @JsonProperty
    Subnetwork subnetwork;

public Swithcboard(){};

    public  Swithcboard(String ip, String con_id, Network net, Subnetwork subnetwork){
        this.ip =  ip;
        this.net = net;
        this.number = net.num_switchboards;
        this.subnetwork =subnetwork;
        linked_nodes.add(con_id);
    }

    public Boolean changeIp(String id_to_replace){
        boolean result = false;
//        RouterPort port = (RouterPort) net.getNodeByID(subnetwork);
        if(subnetwork.isIpAvailable(id_to_replace)){
            ip = id_to_replace;
            result = true;
        }
        return  result;
    }
    public MutableTreeNode addTreeNode(String backlink_connection){
        DefaultMutableTreeNode switch_board_node = new DefaultMutableTreeNode(this);

        for(String id_node: linked_nodes){
            if(!(id_node.equals(backlink_connection))) {
                IpTable node = net.getNodeByID(id_node);

                switch_board_node.add(node.addTreeNode(getIp()));
            }

        }
        return switch_board_node;
    }

    public void addConnection(String ip){
        linked_nodes.add(ip);
//        printNodesConnections();
    }
    @Override
    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    @Override
    public String getType() {
        return type;
    }
    public void setType(String str){
        type = str;
    }

    @JsonIgnore
    public String getName(){
        return "Switchboard " + number;
    }
    public void setNet(Network net) {
        this.net = net;
    }


    @Override
    public ArrayList<String> getLinkedNodes() {
        return linked_nodes;
    }
@JsonIgnore
    public Boolean getSleep(){
        return false;
    }

}
