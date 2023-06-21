import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;

public class Subnetwork {

    @JsonProperty
    private String max_subnet_ip;
    @JsonProperty
    private String min_subnet_ip;
    @JsonProperty
    ArrayList<String> ips_used = new ArrayList<>();
    @JsonIgnore
    private IpTransormator ipTransormator = new IpTransormator();

    @JsonProperty
    private int ip_counter=2 ;  // two because first ip is unavailable and second is port's ip

    public Subnetwork(){};
    public Subnetwork(String max_subnet_ip, String min_subnet_ip){
        this.max_subnet_ip= max_subnet_ip;
        this.min_subnet_ip = min_subnet_ip;
    }
    public String giveIp(){
        int ip = ipTransormator.stringToIntIP(min_subnet_ip)+ ip_counter;
        String str_ip;

        if (ip < ipTransormator.stringToIntIP(max_subnet_ip)){
            ip_counter+=1;
            str_ip = ipTransormator.intToStringIp(ip);
            ips_used.add(str_ip);
        }else {
            System.out.println("ip is out of bounds for his subnework");
            str_ip =null;
        }

        return  str_ip;
    }

    public Boolean isIpAvailable(String ip){

        boolean result =true;
        int int_ip = ipTransormator.stringToIntIP(ip);
        int max_ip = ipTransormator.stringToIntIP(max_subnet_ip);
        int min_ip = ipTransormator.stringToIntIP(min_subnet_ip);
        if(ips_used.contains(ip)){
            result = false;
        }
        if (int_ip>max_ip | int_ip<min_ip){
            result = false;
        }
        return result;
    }
}
