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
    String subnetwork;

public Swithcboard(){};

    public  Swithcboard(String ip, String con_id, Network net, String subnetwork){
        this.ip =  ip;
        this.net = net;
        this.number = net.num_switchboards;
        this.subnetwork =subnetwork;
        linked_nodes.add(con_id);
    }

    public Boolean changeIp(String id_to_replace){
        boolean result = false;
        RouterPort port = (RouterPort) net.getNodeByID(subnetwork);
        if(port.isIpAvailable(id_to_replace)){
            ip = id_to_replace;
            result = true;
        }
        return  result;
    }
    public MutableTreeNode addTreeNode(String backlink_connection){
        DefaultMutableTreeNode switch_board_node = new DefaultMutableTreeNode(this);
//        DefaultMutableTreeNode char_node  = new DefaultMutableTreeNode("Network characteristics");
//        DefaultMutableTreeNode id_switch_node = new DefaultMutableTreeNode("Ip " +ip);
//        char_node.add(id_switch_node);
//        switch_board_node.add(char_node);
        for(String id_node: linked_nodes){
            if(!(id_node.equals(backlink_connection))) {
                IpTable node = net.getNodeByID(id_node);
//                System.out.println("Connections of a node" + node.getType()+  node.getIp());
//                node.printNodesConnections();
//                System.out.println("Added node with ip " + id_node + " Of type " + node.getType() +" with backlink "+ backlink_connection+ " Current node "+ getIp());
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
    @Override
    public void printNodesConnections() {
        for (String node_ip: linked_nodes){
            IpTable node= net.getNodeByID(node_ip);
            System.out.println(node.getType() + node.getIp());

        }
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

    public Boolean getSleep(){
        return false;
    }
//    @Override
//    public int getNumber() {
//        return number;
//    }
}
