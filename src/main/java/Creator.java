public class Creator {
    Network net = null;
    IpGenerator generator = null;

    public Creator(Network net, IpGenerator generator){
        this.net =  net;
        this.generator = generator;
    }


    public Router createRouter(int MaskLength){
        net.addRouter();
        return new Router(MaskLength,net.getNum_routers(),net);
    }
    public Swithcboard createSwitchboard(String ip, String con_id,Subnetwork subnetwork){
        net.addSwitchboard();
        Swithcboard swithcboard = new Swithcboard(ip, con_id, net,subnetwork);
        net.putNode(swithcboard.getIp(),swithcboard);
        return swithcboard;
    }

    public Node createNode(String ip, String con_id, Subnetwork subnetwork){
        net.addNode();
        Node node =  new Node(ip, con_id, net,subnetwork );
        net.putNode(node.getIp(), node);
        return node;

    }

    public Printer createPrinter(String ip, String con_id, Subnetwork subnetwork){
        net.addNode();
        Printer printer =  new Printer(ip, con_id, net, subnetwork);
        net.putNode(printer.getIp(), printer);
        return printer;

    }


    public RouterPort createPort( Router router , int subnetwork_size) {
        String max_id = generator.generateMaxSubnetId(subnetwork_size);
        String min_id = generator.generateMinSubnetId();
        Subnetwork subnet = new Subnetwork(max_id,min_id);

        RouterPort port = new RouterPort(net, subnet, generator.generateFirstAvailableId(), router);

        net.putNode(generator.generateFirstAvailableId(), port);
        generator.updateBits(subnetwork_size);

        return port;
    }
//    public Subnetwork createSubnetwork(String max_ip, String min_ip){
//        Subnetwork subnet = new
//    }


    }
