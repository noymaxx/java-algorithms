public class Recursao {

    public static int somatorio(int n) {
        RecursionChecker.register("somatorio"); // Não remova esta linha, pois ela é usada pelo corretor
        /*
        Função recursiva que recebe um valor inteiro n >= 0, e devolve o somatório de 0 até n.
        Todos os casos de teste serão aplicados para n >= 0, ou seja, não se preocupe em tratar valores negativos.

        Dica: faça o somatório de 0 até n, número por número. Não utilize a fórmula de soma de uma PA.

        Exemplos:
        ------------
        somatorio(0) deve retornar 0;
        somatorio(2) deve retornar 3, pois 0+1+2 = 3;
        somatorio(5) deve retornar 15, pois 0+1+2+3+4+5 = 15;
        */
        // Escreva a sua implementação abaixo:

        if (n == 0) {
            return 0;
        }

        return n + somatorio(n - 1);
    }


    public static int qtdDigitos(int n) {
        RecursionChecker.register("qtdDigitos"); // Não remova esta linha, pois ela é usada pelo corretor
        /*
        Função recursiva que recebe um valor inteiro n >= 0, e retorna um valor inteiro indicando o número de dígitos de n.

        Exemplos:
        ------------
        qtdDigitos(4) retorna 1, pois 4 contém somente 1 dígito;
        qtdDigitos(9) retorna 1, pois 9 contém somente 1 dígito;
        qtdDigitos(10) retorna 2, pois 10 contém 2 dígitos;
        qtdDigitos(99) retorna 2, pois 99 contém 2 dígitos;
        qtdDigitos(1071) retorna 4, pois 1071 contém 4 dígitos;
        */
        // Escreva a sua implementação abaixo:

        if (n < 10) {
            return 1;
        }
        return 1 + qtdDigitos(n / 10);
    }


    public static int somaDigitos(int n) {
        RecursionChecker.register("somaDigitos"); // Não remova esta linha, pois ela é usada pelo corretor
        /*
        Função recursiva que recebe um valor inteiro n >= 0, e devolve o somatório de todos os dígitos de n.

        Exemplo:
        ------------
        somaDigitos(4) retorna 4;
            somaDigitos(10) retorna 1, pois 1+0 = 1;
        somaDigitos(45) retorna 9, pois 4+5 = 9;
        somaDigitos(451) retorna 10, pois 4+5+1 = 10;
        somaDigitos(2345) retorna 14, pois 2+3+4+5 = 14;
        */
        // Escreva a sua implementação abaixo:
        if (n < 10) {
            return n;
        }
        return n % 10 + somaDigitos(n / 10);
    }


    public static double potencia(double b, int e) {
        RecursionChecker.register("potencia"); // Não remova esta linha, pois ela é usada pelo corretor
        /*
        Função recursiva que recebe uma base decimal b e um expoente inteiro e >= 0,
        e retorna o resultado de b^e.

        Você deve implementar a lógica da exponenciação. Não utilize métodos como Math.pow() ou similares.
        Dica: b^e = b * b * ... * b, e vezes.

        Exemplos:
        -----------
        potencia(2, 0) retorna 1, pois 1 = 2^0;
        potencia(2, 5) retorna 32, pois 32 = 2^5;
        potencia(3.5, 2) retorna 12.25, pois 12.25 = 3.5^2;
        */
        // Escreva a sua implementação abaixo:
        if (e == 0) {
            return 1;
        }
        if (e == 1) {
            return b;
        }
        return b * potencia(b, e - 1);
    }


    public static boolean potenciaDeDois(int n) {
        RecursionChecker.register("potenciaDeDois"); // Não remova esta linha, pois ela é usada pelo corretor
        /*
        Função recursiva que recebe um valor inteiro n >= 1, e retorna true se n é potência de 2 e false caso contrário.

        Para testar se um número é potência de 2, podemos usar o seguinte algoritmo:
            - Se n é 1, então n é potência de 2;
            - Caso contrário, se n é ímpar (e não é 1), então não é potência de 2;
            - Caso contrário, (se n é par), calcule a divisão inteira n por 2 e
            repita os dois testes acima.

        Exemplos:
        -----------
        potenciaDeDois(4) retorna true, pois 4 = 2^2;
        potenciaDeDois(6) retorna false, pois 6 não é potência de 2;
        potenciaDeDois(11) retorna false, pois 11 não é potência de 2;
        potenciaDeDois(32) retorna true, pois 32 = 2^5;
        */
        // Escreva a sua implementação abaixo:
        if (n == 1) {
            return true;
        }
        if (n % 2 != 0) {
            return false;
        }
        else {
            return potenciaDeDois(n / 2);
        }

    }


    public static boolean palindromo(String s) {
        RecursionChecker.register("palindromo"); // Não remova esta linha, pois ela é usada pelo corretor
        /*
        Função recursiva que recebe uma string s e retorna true quando s é um palíndromo e false caso contrário.

        OBS: se o tamanho de s for igual a 0 ou 1, considere que s é palíndromo.

        Dica: Teste se o primeiro e o último caracteres são iguais. Depois repita
        o processo para a substring de s, excluindo o primeiro e o último índices,
        e assim por diante.

        Exemplos:
        -----------
        palindromo("") retorna true, pois s.length() é 0, e a cadeia vazia é palíndromo;
        palindromo("a") retorna true, pois s.length() é 1, e qualquer cadeia de tamanho 1 é palíndromo;
        palindromo("radar") retorna true, pois "radar" é palíndromo;
        palindromo("amor") retorna false, pois "amor" não é palíndromo;
        */
        // Escreva a sua implementação abaixo:
        if (s.isEmpty()) {
            return true;
        }
        if (s.length() == 1) {
            return true;
        }
        else if (s.charAt(0) == s.charAt(s.length() - 1)) {
            return palindromo(s.substring(1, s.length() - 1));
        }
        else {
            return false;
        }
    }


    public static boolean buscaArray(int[] arr, int i, int alvo) {
        RecursionChecker.register("buscaArray"); // Não remova esta linha, pois ela é usada pelo corretor
        /*
        Função recursiva que recebe uma array de inteiros arr, um índice inicial i, e um inteiro alvo que desejamos saber
        se existe ou não em arr.

        OBS: utilize a estratégia de busca sequencial, ou seja, compare elemento a elemento até o fim do array.

        Exemplos (usamos a notação de arrays do Python para facilitar a explicação):
        -----------
        mediaArray([10, 20, 30, 40, 50], 0, 20), retorna true, pois 20 existe no array;
        mediaArray([10, 20, 30, 40, 50], 0, 21), retorna false, pois 21 não existe no array;
        */
        // Escreva a sua implementação abaixo:
            if (!(i >= 0 && i < arr.length)){
                return false;
            }
            if (arr[i] == alvo) {
                return true;
            }
            return buscaArray(arr, i + 1, alvo);
    }


    public static void inverteArray(int[] arr, int i) {
        RecursionChecker.register("inverteArray"); // Não remova esta linha, pois ela é usada pelo corretor
        /*
        Função recursiva que recebe uma array de inteiros arr e um índice inicial i, e inverte os elementos de arr a
        partir da posição i.

        Dica: para inverter um array (considerando i = 0), basta trocar o primeiro elemento com o último, em seguida
        troque o segundo elemento com o penúltimo, e assim por diante.

        OBS: a função não retorna nada, pois arr já é passado por referência!

        OBS 2: não utilize nenhum outro método auxiliar para resolver este problema. Utilize somente operações elementares
        (aritméticas, lógicas e relacionais), acesso a índices do array e o atributo length.

        Exemplos (usamos a notação de arrays do Python para facilitar a explicação):
        -----------
        inverteArray([10, 20], 0), faz com que arr seja [20, 10];
        inverteArray([10, 20, 30, 40], 0), faz com que arr seja [40, 30, 20, 10];
        inverteArray([10, 20, 30, 40], 1), faz com que arr seja [10, 30, 20, 40];
        inverteArray([10, 20, 30, 40, 50, 60, 70, 80], 2), faz com que arr seja [10, 20, 60, 50, 40, 30, 70, 80];
        */
        // Escreva a sua implementação abaixo:

        if ( i >= arr.length / 2) {
            return;
        }

        int lastIndex = arr.length - i - 1;

        int aux = arr[lastIndex];
        arr[lastIndex] = arr[i];
        arr[i] = aux;

        inverteArray(arr, i + 1);
    }

    public static double somaMatriz(double[][] mat, int i) {
        RecursionChecker.register("somaMatriz"); // Não remova esta linha, pois ela é usada pelo corretor
        /*
        Função que recebe uma matriz de double mat, um índice inicial i, e retorna a soma dos elementos de mat a partir de i.
        Considere que i representa os índices da matriz, começando da esquerda para a direita e de cima para baixo, e o
        índice inicial (linha 0, coluna 0) é dado quando i = 0.

        Dica: sabendo o número de linhas e colunas da matriz e o índice i, é possível calcular a linha e a coluna que i
        representa através das operações de divisão e módulo.

        OBS: não utilizar nenhum método adicional do Java. Resolva este problema usando somente operações elementares
        (aritméticas, lógicas e relacionais), acesso a índices da matriz e o atributo length.

        Exemplos (usamos a notação de matrizes do Python para facilitar a explicação):
        -----------
        somaMatriz([[10, 20, 30], [40, 50, 60]], 0), retorna 210.0, pois 10+20+30+40+50+60 = 210.0;
        somaMatriz([[10, 20, 30], [40, 50, 60], [70, 80, 90]], 5), retorna 300.0, pois 60+70+80+90 = 300.0;
        */
        // Escreva a sua implementação abaixo:
        int numRows = mat.length;
        int numColunms = mat[0].length;
        int total = numColunms * numRows;

        if (i >= total) {
            return 0;
        }
        int colunm = i % numColunms;
        int row = i / numColunms;

        return mat[row][colunm] + somaMatriz(mat, i + 1);
    }


    public static void main(String[] args) {
        // Caso seja necessário, utilize este método para fazer seus próprios testes, chamando as funções que você implementou.
        // Exemplo:
        System.out.println(somatorio(3));
        System.out.println(qtdDigitos(888));
        System.out.println(somaDigitos(888));
        System.out.println(potencia(2, 5));
        System.out.println(palindromo("radar"));
        System.out.println(buscaArray(new int[]{1,2,3,4,5}, 3, 2));
        System.out.println(somaMatriz(new double[][]{{1,2,3},{1,2,3}}, 1));
    }
}
