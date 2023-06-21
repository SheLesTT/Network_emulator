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
    public Swithcboard createSwitchboard(String ip, String con_id,String subnetwork){
        net.addSwitchboard();
        Swithcboard swithcboard = new Swithcboard(ip, con_id, net,subnetwork);
//        System.out.println("Put switchboard with ip " + ip );
        net.putNode(swithcboard.getIp(),swithcboard);
        return swithcboard;
    }

    public Node createNode(String ip, String con_id, String subnetwork){
        net.addNode();
        System.out.println("Subnetwork of a node"+ subnetwork);
        Node node =  new Node(ip, con_id, net,subnetwork );
        net.putNode(node.getIp(), node);
        return node;

    }

    public Printer createPrinter(String ip, String con_id, String subnetwork){
        net.addNode();
        Printer printer =  new Printer(ip, con_id, net, subnetwork);
        net.putNode(printer.getIp(), printer);
        return printer;

    }


    public RouterPort createPort( Router router , int subnetwork_size) {

        RouterPort port = new RouterPort(net,generator.generateFirstAvailableId(),
                generator.generateMaxSubnetId(subnetwork_size),
                generator.generateMinSubnetId(), router);
//       System.out.println("Put port with " + generator.generateFirstAvailableId());
        net.putNode(generator.generateFirstAvailableId(), port);
        generator.updateBits(subnetwork_size);

        return port;
    }


    }
