public class Minesweeper {
    public static void main(String[] args) {
        int m = Integer.parseInt(args[0]);
        int n = Integer.parseInt(args[1]);
        int k = Integer.parseInt(args[2]);

        char[][] mines = new char[m][n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                mines[i][j] = '0';
            }
        }


        int bomb = 0;

        while (bomb < k) {
            int random1 = (int) (Math.random() * m);
            int random2 = (int) (Math.random() * n);
            if (mines[random1][random2] != '*') {
                mines[random1][random2] = '*';
            } else {
                k++;
            }
            bomb++;
        }

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (mines[i][j] == '*') {
                    continue;
                }

                if (i < m - 1 && mines[i + 1][j] == '*') {
                    mines[i][j] += 1;
                }
                if (j < n - 1 && mines[i][j + 1] == '*') {
                    mines[i][j] += 1;
                }
                if (j < n - 1 && i < m - 1 && mines[i + 1][j + 1] == '*') {
                    mines[i][j] += 1;
                }
                if (j > 0 && mines[i][j - 1] == '*') {
                    mines[i][j] += 1;
                }
                if (i > 0 && mines[i - 1][j] == '*') {
                    mines[i][j] += 1;
                }
                if (i > 0 && j > 0 && mines[i - 1][j - 1] == '*') {
                    mines[i][j] += 1;
                }
                if (i > 0 && j < n - 1 && mines[i - 1][j + 1] == '*') {
                    mines[i][j] += 1;
                }
                if (i < m - 1 && j > 0 && mines[i + 1][j - 1] == '*') {
                    mines[i][j] += 1;
                }
            }
        }

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(mines[i][j] + "  ");
            }
            System.out.println();
        }
    }
}
