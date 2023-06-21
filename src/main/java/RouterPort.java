import javax.swing.tree.MutableTreeNode;
import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class RouterPort implements IpTable {
    @JsonProperty
    private String ip;
    private IpTransormator ipTransormator = new IpTransormator();
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
    ArrayList<String> ips_used = new ArrayList<>();
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

        int num_total_nodes = (int)Math.floor(Math.random() * (router.subnetwork_size -4 ) +1);
        int num_printers = (int)Math.floor(Math.random()*3)+1;
        if (num_printers >= num_total_nodes){
            num_printers =1;
        }
//        int num_nodes = num_total_nodes - num_printers;

        if (num_total_nodes < 30){
            attachSwitchboard(creator, num_total_nodes,num_printers,this);
        }else{
            String  ip_switch = giveIp();
            Swithcboard connecting_switch = creator.createSwitchboard(ip_switch, ip, ip); // Switchboard to connect another switches

            linked_nodes.add(connecting_switch.getIp());
            int count_switch = num_total_nodes / 29 +1;
            for(int i = 0; i < count_switch; i++){
                if (num_total_nodes>30) {
                    attachSwitchboard(creator, 29,num_printers, connecting_switch);
                    num_printers=0;
                    num_total_nodes -= 29;
                }else {attachSwitchboard(creator, num_total_nodes,num_printers, connecting_switch); }
            }
        }

    }
    public Boolean changeIp(String ip_to_replace){
        boolean result = false;
        if(isIpAvailable(ip_to_replace)){
            ip = ip_to_replace;
            result = true;
        }
        return result;

    }
    public Boolean isIpAvailable(String ip){

        boolean result =true;
        int int_ip = ipTransormator.stringToIntIP(ip);
        int max_ip = ipTransormator.stringToIntIP(max_subnet_ip);
        int min_ip = ipTransormator.stringToIntIP(min_subnet_ip);
        if(ips_used.contains(ip)){
            result = false;
        }
        if (int_ip>max_ip | int_ip<min_ip){
            result = false;
        }
        return result;
    }


    public void attachSwitchboard(Creator creator, int num_nodes, int num_printers, IpTable node_to_connect){

        Swithcboard swithcboard = creator.createSwitchboard(giveIp(), node_to_connect.getIp(), ip);
        node_to_connect.addConnection(swithcboard.getIp());
        while (num_printers>0){

            String ip_given =  giveIp();
            swithcboard.linked_nodes.add(ip_given);
            creator.createPrinter(ip_given,swithcboard.getIp(),getIp());
            num_printers--;
            num_nodes--;
        }
        for(int i =0; i < num_nodes; i++){
            String ip_given =  giveIp();
            swithcboard.linked_nodes.add(ip_given);
            creator.createNode(ip_given,swithcboard.getIp(), getIp() );
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

    public String giveIp(){
       int ip = ipTransormator.stringToIntIP(min_subnet_ip)+ ip_counter;
       String str_ip;
//       System.out.println(ip);
//       System.out.println(stringToIntIP(max_subnet_ip));
       if (ip < ipTransormator.stringToIntIP(max_subnet_ip)){
           ip_counter+=1;
           str_ip = ipTransormator.intToStringIp(ip);
           ips_used.add(str_ip);
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

    @Override
    public Boolean getSleep() {
        return false;
    }


}

