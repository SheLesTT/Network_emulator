import java.util.ArrayList;
import static java.lang.Math.pow;
public class Router extends  NetworkComponent{
    int mask_length;
    int number;
    int subnetwork_size = (int) pow(2,(32-mask_length));
    int subnetwok_count = (int)pow(2,8-(32-mask_length));
//    IdGenerator generator = new IdGenerator();
    ArrayList<RouterPort> ports = null;
    public Router (int mask_length, int number){
        this.mask_length = mask_length;
        this.number = number;
    }

    public void addPorts(){

    }


    public void Print(){
        System.out.println("router number" + number);
        System.out.println("mask length" + mask_length);

        for(RouterPort port: ports){
            port.Print();
        }
    }
}
