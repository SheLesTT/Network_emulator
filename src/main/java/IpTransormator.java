import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.validator.routines.InetAddressValidator;
public class IpTransormator {

    public IpTransormator(){};
    public int stringToIntIP(String str_ip){
        int fouth_bit;
        int third_bit;
        int lastIndex = str_ip.lastIndexOf('.');
        String str_fouth_bit = str_ip.substring(lastIndex + 1);
        fouth_bit = Integer.parseInt(str_fouth_bit);

        int secondToLastIndex = str_ip.lastIndexOf('.', str_ip.lastIndexOf('.') - 1);
        String str_third_bit = str_ip.substring(secondToLastIndex+1,lastIndex);
        third_bit = Integer.parseInt(str_third_bit);
        int int_id  = third_bit*256 +fouth_bit;
        return int_id;

    }

    public String intToStringIp(int int_id ){
        int fourth_bit;
        int third_bit;
        fourth_bit = int_id % 256;
        third_bit = int_id / 256;
        String str_id  = "192.168." + Integer.toString(third_bit) + "." + Integer.toString(fourth_bit);
        return str_id;
    }
    public Boolean isIpValid(String ip){
        InetAddressValidator validator = InetAddressValidator.getInstance();
        return  validator.isValidInet4Address(ip);

    }
}
