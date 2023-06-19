public class Creator {
    Network net = null;
    IdGenerator generator = null;

    public Creator(Network net, IdGenerator generator){
        this.net =  net;
        this.generator = generator;
    }


    public Router createRouter(int MaskLength){
        net.addRouter();
        return new Router(MaskLength,net.getNum_routers(),net);
    }
    public Swithcboard createSwitchboard(){
        net.addSwitchboard();
        return new Swithcboard();
    }

    public Node createNode(){
        net.addNode();
        return  new Node();
    }

    public RouterPort createPort(int subnetwork_size) {

        RouterPort port = new RouterPort(generator.generateMinSubnetId(),
                generator.generateMaxSubnetId(subnetwork_size),
                generator.generateMinSubnetId());
        net.all_nodes.put(generator.generateMinSubnetId(), port);
        generator.updateBits(subnetwork_size);

        return port;
    }


    }
