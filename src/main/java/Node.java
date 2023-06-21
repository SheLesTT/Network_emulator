import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.MutableTreeNode;
import java.util.ArrayList;

public class Node implements IpTable, Nodeble {

    @JsonIgnore
    Network net;
    @JsonProperty
    Boolean sleep = false;
    @JsonProperty
    private String ip;
    @JsonProperty
    private String type = "Node";
    @JsonProperty
    int number;
    @JsonProperty
    Subnetwork subnetwork;
    @JsonProperty
    ArrayList<String> linked_nodes = new ArrayList<>();

    public Node(){};

    public Node(String id, String con_id, Network net, Subnetwork subnetwork){
        this.ip = id;
        linked_nodes.add(con_id);
        this.net = net;
        this.number = net.getNum_nodes();
        this.subnetwork =subnetwork;
    }

    public MutableTreeNode addTreeNode(String ip){
        DefaultMutableTreeNode node = new DefaultMutableTreeNode(this);
        return node;
    }
    public Boolean changeIp(String id_to_replace){
        boolean result = false;
//        System.out.println(subnetwork);
//        RouterPort port = (RouterPort) net.getNodeByID(subnetwork);
        if(subnetwork.isIpAvailable(id_to_replace)){
            ip = id_to_replace;
            result = true;
        }
        return  result;
    }

    public void addConnection(String ip){
        linked_nodes.add(ip);
    }
    @Override
    public String getIp() {
        return ip;
    }

    public String getType() {
        return type;
    }


    public ArrayList<String> getLinkedNodes() {
        return linked_nodes;
    }


    @JsonIgnore
    public String getName(){
        return "Node " +number;
    }
    public void setNet(Network net) {
        this.net = net;
    }

    @Override
    public Boolean getSleep() {
        return sleep;
    }

    public void setSleep(Boolean is_sleeping){sleep = is_sleeping;}
}

