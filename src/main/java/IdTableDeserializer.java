import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public class IdTableDeserializer extends JsonDeserializer<IpTable> {
    @Override
    public IpTable deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        JsonNode node = jsonParser.getCodec().readTree(jsonParser);
//        System.out.println(node);
        // Extract the necessary fields from the JSON node
        String implementationType = (node.get("type")).asText();
//        System.out.println(implementationType + " Here");
        JsonNode dataNode = node;
        System.out.println(node);

        // Create the appropriate implementation instance based on the implementationType
        IpTable idTable;

        System.out.println(implementationType.equals("RouterPort") );
        if ("RouterPort".equals(implementationType)) {
            System.out.println("i got here");
            ObjectMapper objectMapper = new ObjectMapper();
            RouterPort routerPort = objectMapper.treeToValue(dataNode, RouterPort.class);
            idTable = routerPort;
        } else {
            // Handle unknown implementation type or provide a default implementation
            // You can throw an exception, log a warning, or create a default implementation object
            idTable = null; // Replace with your desired behavior
        }
        System.out.println(idTable);
        return idTable;
    }
}
