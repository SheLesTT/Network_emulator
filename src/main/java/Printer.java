import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.MutableTreeNode;
import java.util.ArrayList;

public class Printer implements IpTable, Nodeble{

    @JsonIgnore
    Network net;
    @JsonProperty
    Boolean sleep = true;
    @JsonProperty
    private String ip;
    @JsonProperty
    private String type = "Printer";
    @JsonProperty
    int number;
    @JsonProperty
    ArrayList<String> linked_nodes = new ArrayList<>();

    public Printer(){};

    public Printer(String id, String con_id, Network net){
        this.ip = id;
        linked_nodes.add(con_id);
        this.net = net;
        this.number = net.getNum_nodes();
    }

    public MutableTreeNode addTreeNode(String ip){
        DefaultMutableTreeNode node = new DefaultMutableTreeNode("printer " + number);
        DefaultMutableTreeNode node_ip = new DefaultMutableTreeNode("Ip " + this.ip);
        node.add(node_ip);
        return node;
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

    public void printNodesConnections() {
        for (String node_ip: linked_nodes){
            IpTable node= net.getNodeByID(node_ip);
            System.out.println(node.getType() + node.getIp());

        }
    }
    public ArrayList<String> getLinkedNodes() {
        return linked_nodes;
    }

    @Override
    public String toString(){
        return id;
    }
    @JsonIgnore
    public String getName(){
        return "Printer " +number;
    }
    public void setNet(Network net) {
        this.net = net;
    }
    public void setSleep(Boolean is_sleeping){sleep = is_sleeping;}
}