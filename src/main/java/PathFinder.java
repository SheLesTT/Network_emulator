import java.util.*;

public class PathFinder {
    Network net;

    public  PathFinder(Network net){
        this.net = net;
    }

    public String BFS(String starting_node, String node_to_serach){

        HashMap<String ,String> prev = Solve(starting_node);
        for (String key: prev.keySet()){
//            System.out.println("Previous for node "+ key + " is " + prev.get(key));
        }
        return reconstructPath(node_to_serach,prev);

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
    public String reconstructPath( String node_to_serach, HashMap<String,String> prev){
       ArrayList<String> path = new ArrayList<>();
       path.add(node_to_serach);
       for(String at = node_to_serach; at !=null; at=prev.get(at)){
//           System.out.println("Adding node" + at + " With previous" + prev.get(at));

           path.add(at);
       }
        Collections.reverse(path);
       path.remove(path.size()-1);
       String str_path = "";
       for(String node_id: path){
           IpTable  node = net.getNodeByID(node_id);
           if(path.indexOf(node_id) != path.size()-1) {
               str_path += node.getName() + " with ip " + node.getIp() + "->\n";
           }else {
               str_path += node.getName() + " with ip " + node.getIp() + "\n";
           }
//           System.out.println(str_path);
//           System.out.println("Node "+node.getType()+" With ip " +node.getIp());
       }
       return str_path;

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
