import javax.swing.tree.MutableTreeNode;
import java.util.ArrayList;

//@JsonDeserialize(using = IdTableDeserializer.class)
public interface IpTable {

     public String getIp();
     public String getType();
     public String getName();
     public Boolean changeIp(String ip_to_replace);
     public void setNet(Network net);
//     public int getNumber();
     public void addConnection(String ip);
     public MutableTreeNode addTreeNode(String ip);

     public ArrayList<String> getLinkedNodes();
     public Boolean getSleep();
}
