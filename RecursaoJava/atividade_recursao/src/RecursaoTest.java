import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class RecursaoTest {
    private static String msgResultadoErrado = "O resultado não corresponde com o esperado";
    private static String msgFalhaRecursao = "A implementação recursiva não parece estar implementada corretamente";

    @Test
    public void testSomatorio01() {
        RecursionChecker.unregister("somatorio");
        int resp = Recursao.somatorio(0);
        assertEquals(msgResultadoErrado, 0, resp);
    }

    @Test
    public void testSomatorio02() {
        RecursionChecker.unregister("somatorio");
        int resp = Recursao.somatorio(3);
        assertEquals(msgResultadoErrado, 6, resp);
        assertTrue(msgFalhaRecursao, RecursionChecker.check("somatorio", 3, 4));
    }

    @Test
    public void testSomatorio03() {
        RecursionChecker.unregister("somatorio");
        int resp = Recursao.somatorio(10);
        assertEquals(msgResultadoErrado, 55, resp);
        assertTrue(msgFalhaRecursao, RecursionChecker.check("somatorio", 9, 11));
    }

    @Test
    public void qtdDigitos01() {
        RecursionChecker.unregister("qtdDigitos");
        int resp = Recursao.qtdDigitos(0);
        assertEquals(msgResultadoErrado, 1, resp);
    }

    @Test
    public void qtdDigitos02() {
        RecursionChecker.unregister("qtdDigitos");
        int resp = Recursao.qtdDigitos(10);
        assertEquals(msgResultadoErrado, 2, resp);
        assertTrue(msgFalhaRecursao, RecursionChecker.check("qtdDigitos", 2, 3));
    }

    @Test
    public void qtdDigitos03() {
        RecursionChecker.unregister("qtdDigitos");
        int resp = Recursao.qtdDigitos(203040);
        assertEquals(msgResultadoErrado, 6, resp);
        assertTrue(msgFalhaRecursao, RecursionChecker.check("qtdDigitos", 5, 7));
    }

    @Test
    public void somaDigitos01() {
        RecursionChecker.unregister("somaDigitos");
        int resp = Recursao.somaDigitos(0);
        assertEquals(msgResultadoErrado, 0, resp);
    }

    @Test
    public void somaDigitos02() {
        RecursionChecker.unregister("somaDigitos");
        int resp = Recursao.somaDigitos(20);
        assertEquals(msgResultadoErrado, 2, resp);
        assertTrue(msgFalhaRecursao, RecursionChecker.check("somaDigitos", 2, 4));
    }

    @Test
    public void somaDigitos03() {
        RecursionChecker.unregister("somaDigitos");
        int resp = Recursao.somaDigitos(9581362);
        assertEquals(msgResultadoErrado, 34, resp);
        assertTrue(msgFalhaRecursao, RecursionChecker.check("somaDigitos", 5, 9));
    }

    @Test
    public void potencia01() {
        RecursionChecker.unregister("potencia");
        double resp = Recursao.potencia(10, 0);
        assertEquals(msgResultadoErrado, 1.0, resp, 0.01);
    }

    @Test
    public void potencia02() {
        RecursionChecker.unregister("potencia");
        double resp = Recursao.potencia(2, 16);
        assertEquals(msgResultadoErrado, 65536.0, resp, 0.01);
        assertTrue(msgFalhaRecursao, RecursionChecker.check("potencia", 4, 18));
    }

    @Test
    public void potencia03() {
        RecursionChecker.unregister("potencia");
        double resp = Recursao.potencia(20.2, 3);
        assertEquals(msgResultadoErrado, 8242.408, resp, 0.01);
        assertTrue(msgFalhaRecursao, RecursionChecker.check("potencia", 3, 5));
    }

    @Test
    public void potenciaDeDois01() {
        RecursionChecker.unregister("potenciaDeDois");
        boolean resp = Recursao.potenciaDeDois(1);
        assertEquals(msgResultadoErrado, true, resp);
    }

    @Test
    public void potenciaDeDois02() {
        RecursionChecker.unregister("potenciaDeDois");
        boolean resp = Recursao.potenciaDeDois(32);
        assertEquals(msgResultadoErrado, true, resp);
        assertTrue(msgFalhaRecursao, RecursionChecker.check("potenciaDeDois", 5, 7));
    }

    @Test
    public void potenciaDeDois03() {
        RecursionChecker.unregister("potenciaDeDois");
        boolean resp = Recursao.potenciaDeDois(160);
        assertEquals(msgResultadoErrado, false, resp);
        assertTrue(msgFalhaRecursao, RecursionChecker.check("potenciaDeDois", 5, 7));
    }

    @Test
    public void potenciaDeDois04() {
        RecursionChecker.unregister("potenciaDeDois");
        boolean resp = Recursao.potenciaDeDois(1073741824);
        assertEquals(msgResultadoErrado, true, resp);
        assertTrue(msgFalhaRecursao, RecursionChecker.check("potenciaDeDois", 30, 32));

        RecursionChecker.unregister("potenciaDeDois");
        resp = Recursao.potenciaDeDois(1073741624);
        assertEquals(msgResultadoErrado, false, resp);
        assertTrue(msgFalhaRecursao, RecursionChecker.check("potenciaDeDois", 3, 6));
    }

    @Test
    public void palindromo01() {
        RecursionChecker.unregister("palindromo");
        boolean resp = Recursao.palindromo("");
        assertEquals(msgResultadoErrado, true, resp);

        RecursionChecker.unregister("palindromo");
        resp = Recursao.palindromo("x");
        assertEquals(msgResultadoErrado, true, resp);
    }

    @Test
    public void palindromo02() {
        RecursionChecker.unregister("palindromo");
        boolean resp = Recursao.palindromo("abaa");
        assertEquals(msgResultadoErrado, false, resp);
        assertTrue(msgFalhaRecursao, RecursionChecker.check("palindromo", 2, 3));
    }

    @Test
    public void palindromo03() {
        RecursionChecker.unregister("palindromo");
        boolean resp = Recursao.palindromo("socorrammesubinoonibusemmarrocos");
        assertEquals(msgResultadoErrado, true, resp);
        assertTrue(msgFalhaRecursao, RecursionChecker.check("palindromo", 15, 18));
    }

    @Test
    public void palindromo04() {
        RecursionChecker.unregister("palindromo");
        boolean resp = Recursao.palindromo("abracadabraarbadacarba");
        assertEquals(msgResultadoErrado, true, resp);
        assertTrue(msgFalhaRecursao, RecursionChecker.check("palindromo", 10, 13));

        RecursionChecker.unregister("palindromo");
        resp = Recursao.palindromo("abracadabraaradacarba");
        assertEquals(msgResultadoErrado, false, resp);
        assertTrue(msgFalhaRecursao, RecursionChecker.check("palindromo", 6, 13));
    }

    @Test
    public void buscaArray01() {
        RecursionChecker.unregister("buscaArray");
        int[] numeros = new int[]{};
        boolean resp = Recursao.buscaArray(numeros, 0, 3);
        assertEquals(msgResultadoErrado, false, resp);

        RecursionChecker.unregister("buscaArray");
        numeros = new int[]{3, 9, 1, 2, 8};
        resp = Recursao.buscaArray(numeros, 0, 3);
        assertEquals(msgResultadoErrado, true, resp);

    }

    @Test
    public void buscaArray02() {
        RecursionChecker.unregister("buscaArray");
        int[] numeros = new int[]{51, 20, 42, 22, 3, 86, 3, 0, 1, 100, 87, 37, 79};
        boolean resp = Recursao.buscaArray(numeros, 0, 21);
        assertEquals(msgResultadoErrado, false, resp);
        assertTrue(msgFalhaRecursao, RecursionChecker.check("buscaArray", 12, 15));
    }

    @Test
    public void buscaArray03() {
        RecursionChecker.unregister("buscaArray");
        int[] numeros = new int[]{51, 20, 42, 22, 3, 86, 3, 0, 1, 100, 87, 37, 79};
        boolean resp = Recursao.buscaArray(numeros, 0, 37);
        assertEquals(msgResultadoErrado, true, resp);
        assertTrue(msgFalhaRecursao, RecursionChecker.check("buscaArray", 11, 14));
    }

    @Test
    public void inverteArray01() {
        RecursionChecker.unregister("inverteArray");
        int[] numeros = new int[]{10, 20};
        Recursao.inverteArray(numeros, 0);
        assertTrue(msgResultadoErrado, Arrays.equals(numeros, new int[]{20, 10}));
        assertTrue(msgFalhaRecursao, RecursionChecker.check("inverteArray", 1, 3));
    }

    @Test
    public void inverteArray02() {
        RecursionChecker.unregister("inverteArray");
        int[] numeros = new int[]{11, 21, 31, 41};
        Recursao.inverteArray(numeros, 0);
        assertTrue(msgResultadoErrado, Arrays.equals(numeros, new int[]{41, 31, 21, 11}));
        assertTrue(msgFalhaRecursao, RecursionChecker.check("inverteArray", 2, 4));
    }

    @Test
    public void inverteArray03() {
        RecursionChecker.unregister("inverteArray");
        int[] numeros = new int[]{11, 21, 31, 41};
        Recursao.inverteArray(numeros, 1);
        assertTrue(msgResultadoErrado, Arrays.equals(numeros, new int[]{11, 31, 21, 41}));
        assertTrue(msgFalhaRecursao, RecursionChecker.check("inverteArray", 1, 4));
    }

    @Test
    public void inverteArray04() {
        RecursionChecker.unregister("inverteArray");
        int[] numeros = new int[]{51, 20, 42, 22, 3, 86, 3, 0, 1, 100, 87, 37, 79};
        Recursao.inverteArray(numeros, 3);
        assertTrue(msgResultadoErrado, Arrays.equals(numeros, new int[]{51, 20, 42, 100, 1, 0, 3, 86, 3, 22, 87, 37, 79}));
        assertTrue(msgFalhaRecursao, RecursionChecker.check("inverteArray", 3, 5));
    }

    @Test
    public void somaMatriz01() {
        RecursionChecker.unregister("somaMatriz");
        double[][] matriz = new double[][]{{1}, {2}};
        double resp = Recursao.somaMatriz(matriz, 0);
        assertEquals(msgResultadoErrado, 3.0, resp, 0.01);
        assertTrue(msgFalhaRecursao, RecursionChecker.check("somaMatriz", 2, 4));
    }

    @Test
    public void somaMatriz02() {
        RecursionChecker.unregister("somaMatriz");
        double[][] matriz = new double[][]{{1, 2, 3}, {4, 5, 6}};
        double resp = Recursao.somaMatriz(matriz, 0);
        assertEquals(msgResultadoErrado, 21.0, resp, 0.01);
        assertTrue(msgFalhaRecursao, RecursionChecker.check("somaMatriz", 5, 8));
    }

    @Test
    public void somaMatriz03() {
        RecursionChecker.unregister("somaMatriz");
        double[][] matriz = new double[][]{{1, 2, 3}, {4, 5, 6}};
        double resp = Recursao.somaMatriz(matriz, 1);
        assertEquals(msgResultadoErrado, 20.0, resp, 0.01);
        assertTrue(msgFalhaRecursao, RecursionChecker.check("somaMatriz", 4, 7));
    }
}
