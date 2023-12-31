public class IpGenerator {
    private static IpGenerator network_id_generator = new IpGenerator();
    int third_bit = 0;
    int fouth_bit = 0;

    private IpGenerator(){}

    public static IpGenerator genGenrator(){
        return network_id_generator;
    }
    public void resetBits(){
        third_bit = 0;
        fouth_bit =0;
    }

    public void  updateBits(int created_network_size){

        fouth_bit += created_network_size;
        if (fouth_bit >= 255){
            fouth_bit = fouth_bit-256;
            third_bit +=1;
        }

    }
    public String generateFirstAvailableId(){
        String id = "192.168." + Integer.toString(third_bit) + "." + Integer.toString(fouth_bit+1);
        return id;
    }

    public String generateMinSubnetId(){
        String id = "192.168." + Integer.toString(third_bit) + "." + Integer.toString(fouth_bit);
        return id;
    }
    public String generateMaxSubnetId(int network_size){
        String max_id = "192.168." + Integer.toString(third_bit) + "." + Integer.toString(fouth_bit+ network_size-1);
        return max_id;
    }


}
