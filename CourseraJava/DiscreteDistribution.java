public class DiscreteDistribution {
    public static void main(String[] args) {

        int m = Integer.parseInt(args[0]);
        int[] array = new int[args.length-1];
        int[] sum = new int[args.length];
        int r;

        sum[0] = 0;

        for (int i = 1; i <  args.length; i++) {
            array[i - 1] = Integer.parseInt(args[i]);
            sum[i] = sum[i - 1] + array[i - 1];
        }

        for (int j = 0; j < m; j++) {
            r = (int) (Math.random() * sum[sum.length-1]);

            for (int k = 1; k < sum.length; k++) {
                if ((r < sum[k]) && (r >= sum[k - 1])) {
                    System.out.print(k + " ");
                    break;
                }
            }
            if ((j+1) % 25 == 0) {
                System.out.println();
            }
        }
        System.out.println();
    }
}
