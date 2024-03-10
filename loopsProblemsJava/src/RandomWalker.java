public class RandomWalker {
    public static void main(String[] args) {

        int r = Integer.parseInt(args[0]);

        int x = 0;
        int y = 0;
        int i = 0;

        System.out.println("("+ x + ", " + y + ")");

        for (i = 0; (Math.abs(x) + Math.abs(y)) < r; i++) {
            int walk = (int) (Math.random()*4) + 1;

            if (walk == 1) {
                x += 1;
                System.out.println("("+ x + ", " + y + ")");
            }
            if (walk == 2) {
                y += 1;
                System.out.println("("+ x + ", " + y + ")");
            }
            if (walk == 3) {
                x -= 1;
                System.out.println("("+ x + ", " + y + ")");
            }
            if (walk == 4) {
                y -= 1;
                System.out.println("("+ x + ", " + y + ")");
            }
        }
        System.out.println("steps = " + i);
    }
}
