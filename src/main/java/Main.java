public class Main {
        /**
         * @param args the command line arguments
         */
        public static void main(String[] args) {
            IdGenerator gen = new IdGenerator();
            gen.generateSubnetId();
            gen.generateSubnetId();
            System.out.println(gen.generateSubnetId());

        /*Human human = new Human (0,80,16,1);
        Soldier mage = new Soldier(0,60,20,0);
        Fight f = new Fight();
        //System.out.println("human "+human.getHealth()+"    enemy "+mage.getHealth());
        f.Round(human, mage);*/
        }

}
