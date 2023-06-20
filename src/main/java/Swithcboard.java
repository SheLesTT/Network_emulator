import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.MutableTreeNode;
import java.util.ArrayList;

public class Swithcboard extends NetworkComponent implements IpTable {
    Network net;
    String ip = null;
    int number  ;
    String type  = "Switchboard";
    int linked_nodes_counter = 1;
    ArrayList<String> linked_nodes = new ArrayList<>();

    @Override
    public String getType() {
        return type;
    }
    public void setType(String str){
        type = str;
    }

    public  Swithcboard(String ip, String con_id, Network net){
        this.ip =  ip;
        this.net = net;
        this.number = net.num_switchboards;

        linked_nodes.add(con_id);
    }

    public MutableTreeNode addTreeNode(String backlink_connection){
        DefaultMutableTreeNode switch_board_node = new DefaultMutableTreeNode("switchboard " + number);
        DefaultMutableTreeNode char_node  = new DefaultMutableTreeNode("Network characteristics");
        DefaultMutableTreeNode id_switch_node = new DefaultMutableTreeNode("Ip " +ip);
        char_node.add(id_switch_node);
        switch_board_node.add(char_node);
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
    public void printNodesConnections() {
        for (String node_ip: linked_nodes){
            IpTable node= net.getNodeByID(node_ip);
            System.out.println(node.getType() + node.getIp());

        }
    }

    @Override
    public ArrayList<String> getLinkedNodes() {
        return linked_nodes;
    }

//    @Override
//    public int getNumber() {
//        return number;
//    }
}
