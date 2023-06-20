import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

public class PathFinder {
    Network net;

    public  PathFinder(Network net){
        this.net = net;
    }

    public void BFS(String starting_node, String node_to_serach){

        HashMap<String ,String> prev = Solve(starting_node);
        for (String key: prev.keySet()){
//            System.out.println("Previous for node "+ key + " is " + prev.get(key));
        }
        reconstructPath(node_to_serach,prev);

    }

    public HashMap<String,String> Solve(String starting_node){

        LinkedList<String> queue = new LinkedList<>();
        queue.add(starting_node);
        HashMap<String,Boolean> visited = ConstructVisitedHashMap();
        visited.put(starting_node, true);
        HashMap<String, String> prev = ConstructPrevHashMap();
        while (queue.size()>0){
            String node_id = queue.poll();
            IpTable node = net.getNodeByID(node_id);
            ArrayList<String> neigbours = node.getLinkedNodes();

            for(String next_id : neigbours){
                if(!visited.get(next_id)){

                    queue.add(next_id);
                    visited.put(next_id, true);
                    prev.put(next_id, node_id);

                }
            }

        }
       return prev;
    }
    public void reconstructPath( String node_to_serach, HashMap<String,String> prev){
       ArrayList<String> path = new ArrayList<>();
       path.add(node_to_serach);
       for(String at = node_to_serach; at !=null; at=prev.get(at)){
//           System.out.println("Adding node" + at + " With previous" + prev.get(at));

           path.add(at);
       }
       for(String node_id: path){
           IpTable  node = net.getNodeByID(node_id);
           System.out.println("Node "+node.getType()+" With ip " +node.getIp());
       }

    }

    public HashMap<String,Boolean> ConstructVisitedHashMap(){
        HashMap<String, Boolean> visited = new HashMap<>();
        for(String key: net.all_nodes.keySet()){
            visited.put(key, false);
        }
        return visited;
    }
    public HashMap<String,String> ConstructPrevHashMap() {
        HashMap<String, String> prev = new HashMap<>();
        for (String key : net.all_nodes.keySet()){
            prev.put(key, null);
        }
        return prev;
    }
}
