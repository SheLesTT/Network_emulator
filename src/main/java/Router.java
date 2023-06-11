import java.util.ArrayList;
import static java.lang.Math.pow;
public class Router extends  NetworkComponent{
    int mask_length;
    int number;
    int subnetwork_size = (int) pow(2,(32-mask_length));
    IdGenerator generator = new IdGenerator();
    ArrayList<RouterPort> ports = null;
    public Router (int mask_length, int number){
        this.mask_length = mask_length;
        this.number = number;

    }

    public RouterPort createPort(IdGenerator generator){

        RouterPort port = new RouterPort(generator.generateMinSubnetId(),
                                generator.generateMaxSubnetId(subnetwork_size),
                                generator.generateMinSubnetId());
        generator.updateBits(subnetwork_size);
        return port;

    }
}
