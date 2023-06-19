import javax.swing.tree.DefaultMutableTreeNode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonIgnore;
public class Network {
    ArrayList<Router> routers = new ArrayList<>();
    int num_routers =0;
    int num_nodes = 0;
    @JsonIgnore
    int num_switchboards =0;
    @JsonIgnore
    HashMap<String, IdTable> all_nodes = new HashMap<>();
    @JsonProperty
    HashMap<String, RouterPort> rout = new HashMap<>();
    @JsonIgnore
    HashMap<String, IdTableWrapper> modif_all_nodes = new HashMap<>();


    public Network() {
        // Default constructor
    }

    public void prepareForWriting(){
        for (String key: all_nodes.keySet()){
            RouterPort port = (RouterPort) all_nodes.get(key);
            rout.put(key, (RouterPort) port);
        }
    }

    public void finishReading(){

       for (String key: rout.keySet()){
            RouterPort port =  rout.get(key);
            all_nodes.put(key,  port);
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
