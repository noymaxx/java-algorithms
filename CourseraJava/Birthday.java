public class Birthday {
    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        int trials = Integer.parseInt(args[1]);

        int trial = 0;
        int[] count = new int [n];

        while (trial < trials) {
            int peoples = 0;
            boolean[] checkBirthday = new boolean[n];
            boolean duplicateBirthday = false;

            for (int people = 0; (people < n) && (!duplicateBirthday); people++) {
                int newBirthday = (int) (Math.random() * n);
                count[people]++;

                if (checkBirthday[newBirthday]) {
                    duplicateBirthday = true;
                } else {
                    checkBirthday[newBirthday] = true;
                }
                count[people]++;
            }
            trial++;
        }

        double fraction = 0.0;
        int sum = 0;

        for (int i = 0; i < n; i++) {
            fraction = (double) sum / trials;

            if (fraction >= 0.5) {
                count[i] = 0;
            }
        }

        for (int i = 0; i <= n; i++) {
            if (count[i] > 0) {
                System.out.println((i + 1) + "  " + count[i] + "  " + fraction);
            }
        }
    }
}

// obs: desisti desse, tentei, tentei e tentei e não consegui
