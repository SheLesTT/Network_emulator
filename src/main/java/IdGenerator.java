public class IdGenerator {
    private static IdGenerator network_id_generator = new IdGenerator();
    int third_bit = 0;
    int fouth_bit = 1;

    private IdGenerator(){}

    public static IdGenerator genGenrator(){
        return network_id_generator;
    }

    public void  updateBits(int created_network_size){
        fouth_bit += created_network_size;
        if (fouth_bit > 254){
            fouth_bit = fouth_bit-254;
            third_bit +=1;
        }
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
