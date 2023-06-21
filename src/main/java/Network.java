import javax.swing.tree.DefaultMutableTreeNode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonIgnore;
public class Network {
    ArrayList<Router> routers = new ArrayList<>();
    int num_routers =0;
    int num_nodes = 0;
    @JsonIgnore
    int num_switchboards =0;
    @JsonIgnore
    HashMap<String, IpTable> all_nodes = new HashMap<>();
    @JsonProperty
    HashMap<String, RouterPort> rout_port = new HashMap<>();
    @JsonProperty
    HashMap<String, Swithcboard> switchboard = new HashMap<>();

    @JsonProperty
    HashMap<String,Node > node_map = new HashMap<>();
    @JsonProperty
    HashMap<String,Printer> prnter_map = new HashMap<>();

//    @JsonIgnore
//    HashMap<String, IdTableWrapper> modif_all_nodes = new HashMap<>();


    public Network() {
        // Default constructor
    }

    public void setSleep(){
        for(IpTable node: all_nodes.values()){
            if (node instanceof Nodeble){
                ((Nodeble) node).setSleep(false);
                Random random = new Random();
                double randomNumber = random.nextDouble();
                if(randomNumber < 0.3){
                    ((Nodeble) node).setSleep(true);
                }
            }
        }
    }
    public void connectNetwork(){
        for(int i=0; i< routers.size()-1;i ++){
            Router rout1 = routers.get(i);
            Router rout2 = routers.get(i+1);
            connectRouters(rout1, rout2);
            connectRouters(rout2, rout1);
        }
        if(routers.size()>2) {
            Router last = routers.get(routers.size() - 1);
            Router first = routers.get(0);
            connectRouters(first, last);
            connectRouters(last, first);
        }
    }

    public void connectRouters(Router rout1, Router rout2){

        for(String port_ip: rout1.ports){
            RouterPort port = (RouterPort) all_nodes.get(port_ip);
            for (String port_to_add: rout2.ports){
                port.addConnection(port_to_add);
            }
        }
    }
    public void setNetToNodes(){
        for(Router router: routers){
            router.setNet(this);
        }
        for (IpTable node: all_nodes.values()){
            node.setNet(this);
        }
    }
    public  void putNode(String ip, IpTable node){
        all_nodes.put(ip, node);
    }
    public IpTable getNodeByID(String key){
        return all_nodes.get(key);
    }

    public void prepareForWriting(){
        for (String node_id: all_nodes.keySet()){
            IpTable node_stored = all_nodes.get(node_id);
            String type = node_stored.getType();
            switch (type){
                case "Switchboard":
                    switchboard.put(node_id,(Swithcboard) node_stored);
                    break;
                case "Node":
                    node_map.put(node_id, (Node) node_stored);
                    break;
                case "RouterPort":
                    rout_port.put(node_id, (RouterPort) node_stored);
                    break;
                case "Printer":
                    prnter_map.put(node_id,(Printer) node_stored);
            }

        }
    }

    public void finishReading(){

       for (String key: rout_port.keySet()){
            RouterPort port =  rout_port.get(key);
            all_nodes.put(key,  port);
        }

        for (String key: switchboard.keySet()){
            Swithcboard swithcboard =  switchboard.get(key);
            all_nodes.put(key,  swithcboard);
        }

        for (String key: node_map.keySet()){
            Node node =  node_map.get(key);
            all_nodes.put(key,  node);
        }

        for (String key: prnter_map.keySet()){
            Printer node =  prnter_map.get(key);
            all_nodes.put(key,  node);
        }
    }

    // Methods needed for custom deserialization

//    public void printModifiedMap(){
//        for(String key: modif_all_nodes.keySet()){
//            System.out.println("Value " + modif_all_nodes.get(key).getData());
//            System.out.println("Key " + key);
//        }
//
//
//    }
//    public void modifHashMap(){
//        for (Map.Entry<String, IdTable> entry : all_nodes.entrySet()) {
//            String key = entry.getKey();
//            IdTable idTable = entry.getValue();
//            if(idTable instanceof RouterPort){
//                IdTableWrapper wrapper = new IdTableWrapper("RouterPort", idTable);
//
//                modif_all_nodes.put(key, wrapper);
//            }
//            else {
//
//                IdTableWrapper wrapper = new IdTableWrapper("Something", idTable);
//                modif_all_nodes.put(key, wrapper);
//            }
//
//        }
//
//    }

//    public void unpackModifiedHashmap(){
//        for (Map.Entry<String, IdTableWrapper> entry : modif_all_nodes.entrySet()){
//            String key = entry.getKey();
//            IdTable data = entry.getValue().getData();
//            all_nodes.put(key, data);
//        }
//    }
    public void addRouter(Router router){
        routers.add(router);
    }

    public ArrayList<Router> getRouters() {
        return routers;
    }

    public void setRouters(ArrayList<Router> routers) {
        this.routers = routers;
    }

    public int getNum_routers() {
        return num_routers;
    }

    public void setNum_routers(int num_routers) {
        this.num_routers = num_routers;
    }

    public int getNum_nodes() {
        return num_nodes;
    }

    public void setNum_nodes(int num_nodes) {
        this.num_nodes = num_nodes;
    }

    public int getNum_switchboards() {
        return num_switchboards;
    }

    public void setNum_switchboards(int num_switchboards) {
        this.num_switchboards = num_switchboards;
    }



    public void addRouter() {
        this.num_routers += 1;
    }

    public void addNode() {
        this.num_nodes += 1;
    }

    public void addSwitchboard() {
        this.num_switchboards += 1;
    }
    public DefaultMutableTreeNode addInfoToGui(){
        DefaultMutableTreeNode main = new DefaultMutableTreeNode("network");
        for(Router router: routers){
            main.add(router.addTreeNode());
        }
        return main;
    }
}
