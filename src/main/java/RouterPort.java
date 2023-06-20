import javax.swing.tree.MutableTreeNode;
import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class RouterPort implements IpTable {
    @JsonProperty
    private String ip;
@JsonProperty
    private int ip_counter=2 ;  // two because first ip is unavailable and second is port's ip
    @JsonProperty
    private String type = "RouterPort";
    @JsonProperty
    private String max_subnet_ip;
    @JsonProperty
    private String min_subnet_ip;
    @JsonIgnore
    Network net;

    public void setNet(Network net) {
        this.net = net;
    }

    @JsonProperty
    ArrayList<String> ids_used = new ArrayList<>();
    @JsonProperty
    Router router;
//    @JsonProperty
//    Router routerLink;
    @JsonProperty
    ArrayList<String>  linked_nodes = new ArrayList<>();

    public RouterPort(){}

    public RouterPort(Network net,String ip, String max_subnet_id, String min_subnet_id, Router router){
        this.ip = ip;
        this.max_subnet_ip = max_subnet_id;
        this.min_subnet_ip = min_subnet_id;
        this.router = router;
        this.net = net;

    }

    @Override
    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getMax_subnet_ip() {
        return max_subnet_ip;
    }

    public void setMax_subnet_ip(String max_subnet_ip) {
        this.max_subnet_ip = max_subnet_ip;
    }

    public String getMin_subnet_ip() {
        return min_subnet_ip;
    }

    public void setMin_subnet_ip(String min_subnet_id) {
        this.min_subnet_ip = min_subnet_id;
    }

    public void addConnection(String ip){
        linked_nodes.add(ip);
    }

    public void attachSubNet(Creator creator){

        int num_nodes = (int)Math.floor(Math.random() * (router.subnetwork_size -4 ) +1);
        if (num_nodes < 30){
            attachSwitchboard(creator, num_nodes,this);
        }else{
//            System.out.println("got here");
            String  ip_switch = giveIp();
//            System.out.println(ip_switch);
            Swithcboard connecting_switch = creator.createSwitchboard(ip_switch, ip); // Switchboard to connect another switches
//            connecting_switch.setType( "connecting ");

            linked_nodes.add(connecting_switch.getIp());
            int count_switch = num_nodes / 29 +1;
            for(int i = 0; i < count_switch; i++){
                if (num_nodes>30) {
                    attachSwitchboard(creator, 29, connecting_switch);
                    num_nodes -= 29;
                }else {attachSwitchboard(creator, num_nodes, connecting_switch); }
            }
        }

    }

    public void attachSwitchboard(Creator creator, int num_nodes, IpTable node_to_connect){

        Swithcboard swithcboard = creator.createSwitchboard(giveIp(), node_to_connect.getIp());
        node_to_connect.addConnection(swithcboard.getIp());
        for(int i =0; i < num_nodes; i++){
            String ip =  giveIp();
            swithcboard.linked_nodes.add(ip);
            creator.createNode(ip,swithcboard.getIp() );
        }

    }
    public MutableTreeNode addTreeNode(String ip) {
        MutableTreeNode treeNode = null;
//        System.out.println("length linked nodes " + linked_nodes.size());
        for (String node_ip : linked_nodes) {

            IpTable node = net.getNodeByID(node_ip);
            if (node.getType().equals("Switchboard")) {
                treeNode =node.addTreeNode(getIp());
            }
//        System.out.println(treeNode);

        }
        return treeNode;
    }

    @Override
    public String getType() {
        return type;
    }

    public void Print(){
        System.out.println("ip " + ip + " max ip " + max_subnet_ip +" min ip " + min_subnet_ip);
        for (String node_id: linked_nodes){
            IpTable node = net.all_nodes.get(node_id);
            System.out.println(node.getType() + node.getIp());
//            int len_con = node.li
        }
    }
    public int stringToIntIP(String str_ip){
        int fouth_bit;
        int third_bit;
        int lastIndex = str_ip.lastIndexOf('.');
        String str_fouth_bit = str_ip.substring(lastIndex + 1);
        fouth_bit = Integer.parseInt(str_fouth_bit);

        int secondToLastIndex = str_ip.lastIndexOf('.', str_ip.lastIndexOf('.') - 1);
        String str_third_bit = str_ip.substring(secondToLastIndex+1,lastIndex);
        third_bit = Integer.parseInt(str_third_bit);
        int int_id  = third_bit*256 +fouth_bit;
        return int_id;

    }

    public String intToStringIp(int int_id ){
        int fourth_bit;
        int third_bit;
        fourth_bit = int_id % 256;
        third_bit = int_id / 256;
        String str_id  = "192.168." + Integer.toString(third_bit) + "." + Integer.toString(fourth_bit);
        return str_id;
    }
    public String giveIp(){
       int ip = stringToIntIP(min_subnet_ip)+ ip_counter;
       String str_ip;
//       System.out.println(ip);
//       System.out.println(stringToIntIP(max_subnet_ip));
       if (ip < stringToIntIP(max_subnet_ip)){
           ip_counter+=1;
           str_ip = intToStringIp(ip);
       }else {
           System.out.println("ip is out of bounds for his subnework");
           str_ip =null;
       }

    return  str_ip;
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
@JsonIgnore
    @Override
    public String getName() {
        return "Port of Router " + router.getNumber() ;
    }
}

