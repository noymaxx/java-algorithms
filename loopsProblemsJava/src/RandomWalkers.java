public class RandomWalkers {
    public static void main(String[] args) {

        int r = Integer.parseInt(args[0]);
        int trials = Integer.parseInt(args[1]);

        double steps = 0;

        for (int j = 0; j < trials; j++) {

            int x = 0;
            int y = 0;
            int i = 0;

            while ((Math.abs(x) + Math.abs(y)) < r) {
                int walk = (int) (Math.random()*4) + 1;

                if (walk == 1) {
                    x += 1;
                }
                if (walk == 2) {
                    y += 1;
                }
                if (walk == 3) {
                    x -= 1;
                }
                if (walk == 4) {
                    y -= 1;
                }
                i++;
            }
            steps += i;
        }

        double average = steps/trials;
        System.out.println("average number of steps = " + average);
    }
}
