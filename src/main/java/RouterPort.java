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

    @JsonIgnore
    Network net;
    @JsonProperty
    Subnetwork subnet;

    public void setNet(Network net) {
        this.net = net;
    }


    @JsonProperty
    Router router;

    @JsonProperty
    ArrayList<String>  linked_nodes = new ArrayList<>();

    public RouterPort(){}

    public RouterPort(Network net,Subnetwork subnet, String ip,  Router router){
        this.ip = ip;

        this.router = router;
        this.net = net;
        this.subnet = subnet;

    }

    @Override
    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
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


        if (num_total_nodes < 30){
            attachSwitchboard(creator, num_total_nodes,num_printers,this);
        }else{
            String  ip_switch = subnet.giveIp();
            Swithcboard connecting_switch = creator.createSwitchboard(ip_switch, ip, subnet); // Switchboard to connect another switches

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
        if(subnet.isIpAvailable(ip_to_replace)){
            ip = ip_to_replace;
            result = true;
        }
        return result;

    }



    public void attachSwitchboard(Creator creator, int num_nodes, int num_printers, IpTable node_to_connect){

        Swithcboard swithcboard = creator.createSwitchboard(subnet.giveIp(), node_to_connect.getIp(),subnet);
        node_to_connect.addConnection(swithcboard.getIp());
        while (num_printers>0){

            String ip_given =  subnet.giveIp();
            swithcboard.linked_nodes.add(ip_given);
            creator.createPrinter(ip_given,swithcboard.getIp(),subnet);
            num_printers--;
            num_nodes--;
        }
        for(int i =0; i < num_nodes; i++){
            String ip_given =  subnet.giveIp();
            if(ip_given ==""){
                i =num_nodes;
            }else {
                swithcboard.linked_nodes.add(ip_given);
                creator.createNode(ip_given, swithcboard.getIp(), subnet);
            }
        }

    }
    public MutableTreeNode addTreeNode(String ip) {
        MutableTreeNode treeNode = null;

        for (String node_ip : linked_nodes) {
            IpTable node = net.getNodeByID(node_ip);
            if (node.getType().equals("Switchboard")) {
                treeNode =node.addTreeNode(getIp());
            }
        }
        return treeNode;
    }

    @Override
    public String getType() {
        return type;
    }




    public ArrayList<String> getLinkedNodes() {
        return linked_nodes;
    }
@JsonIgnore
    @Override
    public String getName() {
        return "Port of Router " + router.getNumber() ;
    }
@JsonIgnore
    @Override
    public Boolean getSleep() {
        return false;
    }


}

