public class ThueMorse {
    public static void main(String[] args) {

        int n = Integer.parseInt(args[0]);
        int[] thueMorse = new int[n];
        int tam = 1;

        thueMorse[0] = 0;

        while (tam < n) {
            int tam2 = tam * 2;

            for (int i = 0; (i < tam) && (i + tam < n); i++) {
                if (thueMorse[i] == 0) {
                    thueMorse[i + tam] = 1;
                } else {
                    thueMorse[i + tam] = 0;
                }
            }
            tam = tam2;
        }

        char[][] thueMorseMatrix = new char[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (thueMorse[i] == thueMorse[j]) {
                    thueMorseMatrix[i][j] = '+';
                } else {
                    thueMorseMatrix[i][j] = '-';
                }
                System.out.print(thueMorseMatrix[i][j] + "  ");
            }
            System.out.println();
        }
    }
}
