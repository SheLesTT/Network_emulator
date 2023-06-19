import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonDeserialize(using = IdTableDeserializer.class)
public interface IdTable {
     String id = null;
     String node_name = null;
     String type = null;
     public String getId();
}
