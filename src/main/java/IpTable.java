import javax.swing.tree.MutableTreeNode;
import java.util.ArrayList;

//@JsonDeserialize(using = IdTableDeserializer.class)
public interface IpTable {
     String id = null;
     String node_name = null;
     String type = null;
     public String getIp();
     public String getType();
     public String getName();
     public Boolean changeIp(String ip_to_replace);
     public void setNet(Network net);
//     public int getNumber();
     public void addConnection(String ip);
     public MutableTreeNode addTreeNode(String ip);
     public void printNodesConnections();
     public ArrayList<String> getLinkedNodes();
     public Boolean getSleep();
}
