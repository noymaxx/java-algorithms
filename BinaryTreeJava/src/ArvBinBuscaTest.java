import org.junit.Test;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class ArvBinBuscaTest {

    /* BEGIN: classe Aluno */
    private class Aluno implements Comparable<Aluno> {
        private int matricula;
        private String nome;

        public Aluno(int matricula, String nome) {
            this.matricula = matricula;
            this.nome = nome;
        }

        public int getMatricula() {
            return matricula;
        }

        public String getNome() {
            return nome;
        }

        @Override
        public int compareTo(Aluno outroAluno) {
            // compara os alunos por número de matrícula
            return Integer.compare(this.matricula, outroAluno.matricula);
        }
    }
    /* END: classe Aluno */

    public static int[] arrayListToPrimitive(ArrayList<Integer> integers) {
        int[] result = new int[integers.size()];
        for (int i = 0; i < result.length; i++)
            result[i] = integers.get(i);
        return result;
    }

    @Test
    public void testeNoArvBinAtributos() {
        Class<NoArvBin> targetClass = NoArvBin.class;
        Field[] atributos = targetClass.getDeclaredFields();
        // verifica se NoArvBin possui exatamente 4 atributos
        assertEquals("NoArvBin deve ter exatamente 4 atributos!", 4, atributos.length);

        boolean todosSaoPrivados = true;
        int i = 0;
        while (todosSaoPrivados && i < atributos.length) {
            if (!Modifier.isPrivate(atributos[i].getModifiers()))
                todosSaoPrivados = false;
            i++;
        }
        // verifica se todos os atributos de NoArvBin são privados
        assertTrue("Todos os atributos de NoArvBin devem ser privados!", todosSaoPrivados);
    }

    @Test
    public void testeNoArvBinSetGetChave() {
        NoArvBin<Integer> no = new NoArvBin<>(10);
        no.setChave(15);
        assertEquals((Object) 15, (Object) no.getChave());
    }

    @Test
    public void testeNoArvBinIncDecGetOcorrencias() {
        NoArvBin<Integer> no = new NoArvBin<>(10);
        no.incOcorrencias();
        no.incOcorrencias();
        no.decOcorrencias();
        no.incOcorrencias();
        no.decOcorrencias();
        no.incOcorrencias();
        assertEquals(3, no.getOcorrencias());
    }

    @Test
    public void testeNoArvBinDecNaoNegativo() {
        NoArvBin<Integer> no = new NoArvBin<>(10);
        no.incOcorrencias();
        no.incOcorrencias();
        no.decOcorrencias();
        no.decOcorrencias();
        no.decOcorrencias();
        no.decOcorrencias();
        assertEquals(1, no.getOcorrencias());
    }

    @Test
    public void testeNoArvBinSetGetEsq() {
        NoArvBin<Integer> pai = new NoArvBin<>(30);
        NoArvBin<Integer> filho = new NoArvBin<>(35);
        pai.setEsq(filho);
        assertEquals(filho, pai.getEsq());
    }

    @Test
    public void testeNoArvBinSetGetDir() {
        NoArvBin<Integer> pai = new NoArvBin<>(50);
        NoArvBin<Integer> filho = new NoArvBin<>(55);
        pai.setDir(filho);
        assertEquals(filho, pai.getDir());
    }

    @Test
    public void testeArvBinBuscaAtributos() {
        Class<ArvBinBusca> targetClass = ArvBinBusca.class;
        Field[] atributos = targetClass.getDeclaredFields();
        // verifica se ArvBinBusca possui exatamente 1 atributo
        assertEquals("ArvBinBusca deve ter exatamente 1 atributo!", 1, atributos.length);

        boolean todosSaoPrivados = true;
        int i = 0;
        while (todosSaoPrivados && i < atributos.length) {
            if (!Modifier.isPrivate(atributos[i].getModifiers()))
                todosSaoPrivados = false;
            i++;
        }
        // verifica se todos os atributos de NoArvBin são privados
        assertTrue("Todos os atributos de ArvBinBusca devem ser privados!", todosSaoPrivados);
    }

    @Test
    public void testeArvBinBuscaInserir1() {
        ArvBinBusca<Aluno> arv = new ArvBinBusca<>();
        Aluno alu1 = new Aluno(1234, "Fulano");
        arv.inserir(alu1);
        assertEquals(0, alu1.compareTo(arv.getRaiz().getChave()));
        // testa apontadores dos nós que não possuem 2 filhos:
        assertNull(arv.getRaiz().getEsq());
        assertNull(arv.getRaiz().getDir());
        // ocorrência dos registros:
        assertEquals(1, arv.getRaiz().getOcorrencias());
    }

    @Test
    public void testeArvBinBuscaInserir2() {
        ArvBinBusca<Aluno> arv = new ArvBinBusca<>();
        Aluno alu1 = new Aluno(6, "Fulano");
        Aluno alu2 = new Aluno(9, "Ciclano");
        Aluno alu3 = new Aluno(5, "Beltrano");
        arv.inserir(alu1);
        arv.inserir(alu2);
        arv.inserir(alu3);
        assertEquals(0, alu1.compareTo(arv.getRaiz().getChave()));
        assertEquals(0, alu2.compareTo(arv.getRaiz().getDir().getChave()));
        assertEquals(0, alu3.compareTo(arv.getRaiz().getEsq().getChave()));
        // testa apontadores dos nós que não possuem 2 filhos:
        assertNull(arv.getRaiz().getEsq().getEsq());
        assertNull(arv.getRaiz().getEsq().getDir());
        assertNull(arv.getRaiz().getDir().getEsq());
        assertNull(arv.getRaiz().getDir().getDir());
        // ocorrência dos registros:
        assertEquals(1, arv.getRaiz().getOcorrencias());
        assertEquals(1, arv.getRaiz().getDir().getOcorrencias());
        assertEquals(1, arv.getRaiz().getEsq().getOcorrencias());
    }

    @Test
    public void testeArvBinBuscaInserir3() {
        /*
                         fulano(2x)
                   /                   \
              beltrano(4x)             wilma
                     \                 /    \
                      ciclano      julia    zelia
                                               \
                                               zilma(3x)
         */
        ArvBinBusca<String> arv = new ArvBinBusca<>();
        arv.inserir("fulano");
        arv.inserir("beltrano");
        arv.inserir("ciclano");
        arv.inserir("wilma");
        arv.inserir("beltrano");
        arv.inserir("julia");
        arv.inserir("zelia");
        arv.inserir("zilma");
        arv.inserir("zilma");
        arv.inserir("fulano"); // 2x
        arv.inserir("beltrano");
        arv.inserir("zilma"); // 3x
        arv.inserir("beltrano"); // 4x
        assertEquals("fulano", arv.getRaiz().getChave());
        assertEquals("beltrano", arv.getRaiz().getEsq().getChave());
        assertEquals("ciclano", arv.getRaiz().getEsq().getDir().getChave());
        assertEquals("wilma", arv.getRaiz().getDir().getChave());
        assertEquals("julia", arv.getRaiz().getDir().getEsq().getChave());
        assertEquals("zelia", arv.getRaiz().getDir().getDir().getChave());
        assertEquals("zilma", arv.getRaiz().getDir().getDir().getDir().getChave());
        // testa apontadores dos nós que não possuem 2 filhos:
        assertNull(arv.getRaiz().getEsq().getEsq()); // beltrano
        assertNull(arv.getRaiz().getEsq().getDir().getEsq()); // ciclano
        assertNull(arv.getRaiz().getEsq().getDir().getDir()); // ciclano
        assertNull(arv.getRaiz().getDir().getEsq().getEsq()); // julia
        assertNull(arv.getRaiz().getDir().getEsq().getDir()); // julia
        assertNull(arv.getRaiz().getDir().getDir().getEsq()); // zelia
        assertNull(arv.getRaiz().getDir().getDir().getDir().getEsq()); // zilma
        assertNull(arv.getRaiz().getDir().getDir().getDir().getDir()); // zilma
        // ocorrência dos registros:
        assertEquals(2, arv.getRaiz().getOcorrencias());
        assertEquals(4, arv.getRaiz().getEsq().getOcorrencias());
        assertEquals(1, arv.getRaiz().getEsq().getDir().getOcorrencias());
        assertEquals(1, arv.getRaiz().getDir().getOcorrencias());
        assertEquals(1, arv.getRaiz().getDir().getEsq().getOcorrencias());
        assertEquals(1, arv.getRaiz().getDir().getDir().getOcorrencias());
        assertEquals(3, arv.getRaiz().getDir().getDir().getDir().getOcorrencias());
    }

    @Test
    public void testeArvBinBuscaBuscar1() {
        /*
                   100  <-- buscar 100
                 /     \
               70       120
             /  \      /   \
            60  80   110   150
                    /
                   102
        */
        NoArvBin<Integer> el100 = new NoArvBin<>(100);
        NoArvBin<Integer> el70 = new NoArvBin<>(70);
        NoArvBin<Integer> el120 = new NoArvBin<>(120);
        el100.setEsq(el70);
        el100.setDir(el120);
        NoArvBin<Integer> el60 = new NoArvBin<>(60);
        NoArvBin<Integer> el80 = new NoArvBin<>(80);
        NoArvBin<Integer> el110 = new NoArvBin<>(110);
        NoArvBin<Integer> el150 = new NoArvBin<>(150);
        el70.setEsq(el60);
        el70.setDir(el80);
        el120.setEsq(el110);
        el120.setDir(el150);
        NoArvBin<Integer> el102 = new NoArvBin<>(102);
        el110.setEsq(el102);
        ArvBinBusca<Integer> arv = new ArvBinBusca<>(el100);
        assertEquals("1", arv.buscar(100).toUpperCase());
    }

    @Test
    public void testeArvBinBuscaBuscar2() {
        /*
                   100
                 /     \
               70       120
             /  \      /   \
            60  80   110   150
                       \
                       112(3x)  <-- buscar 112 (com 3 ocorrências)
        */
        NoArvBin<Integer> el100 = new NoArvBin<>(100);
        NoArvBin<Integer> el70 = new NoArvBin<>(70);
        NoArvBin<Integer> el120 = new NoArvBin<>(120);
        el100.setEsq(el70);
        el100.setDir(el120);
        NoArvBin<Integer> el60 = new NoArvBin<>(60);
        NoArvBin<Integer> el80 = new NoArvBin<>(80);
        NoArvBin<Integer> el110 = new NoArvBin<>(110);
        NoArvBin<Integer> el150 = new NoArvBin<>(150);
        el70.setEsq(el60);
        el70.setDir(el80);
        el120.setEsq(el110);
        el120.setDir(el150);
        NoArvBin<Integer> el112 = new NoArvBin<>(112);
        el110.setDir(el112);
        el112.incOcorrencias(); // 2x
        el112.incOcorrencias(); // 3x
        ArvBinBusca<Integer> arv = new ArvBinBusca<>(el100);
        assertEquals("DED3", arv.buscar(112).toUpperCase());
    }

    @Test
    public void testeArvBinBuscaBuscar3() {
        /*
           Buscar 65 (não existe).

                   100
                 /     \
               70       120
             /  \      /   \
            60  80   110   150
                    /
                   102
        */
        NoArvBin<Integer> el100 = new NoArvBin<>(100);
        NoArvBin<Integer> el70 = new NoArvBin<>(70);
        NoArvBin<Integer> el120 = new NoArvBin<>(120);
        el100.setEsq(el70);
        el100.setDir(el120);
        NoArvBin<Integer> el60 = new NoArvBin<>(60);
        NoArvBin<Integer> el80 = new NoArvBin<>(80);
        NoArvBin<Integer> el110 = new NoArvBin<>(110);
        NoArvBin<Integer> el150 = new NoArvBin<>(150);
        el70.setEsq(el60);
        el70.setDir(el80);
        el120.setEsq(el110);
        el120.setDir(el150);
        NoArvBin<Integer> el102 = new NoArvBin<>(102);
        el110.setEsq(el102);
        ArvBinBusca<Integer> arv = new ArvBinBusca<>(el100);
        assertEquals("EED0", arv.buscar(65).toUpperCase());
    }

    @Test
    public void testeArvBinBuscaRemover1() {
        /*
                   100
                 /     \
               70       120
             /  \      /   \
            60  80   110   150
                    /
                   102 <-- remover 102
        */
        NoArvBin<Integer> el100 = new NoArvBin<>(100);
        NoArvBin<Integer> el70 = new NoArvBin<>(70);
        NoArvBin<Integer> el120 = new NoArvBin<>(120);
        el100.setEsq(el70);
        el100.setDir(el120);
        NoArvBin<Integer> el60 = new NoArvBin<>(60);
        NoArvBin<Integer> el80 = new NoArvBin<>(80);
        NoArvBin<Integer> el110 = new NoArvBin<>(110);
        NoArvBin<Integer> el150 = new NoArvBin<>(150);
        el70.setEsq(el60);
        el70.setDir(el80);
        el120.setEsq(el110);
        el120.setDir(el150);
        NoArvBin<Integer> el102 = new NoArvBin<>(102);
        el110.setEsq(el102);
        ArvBinBusca<Integer> arv = new ArvBinBusca<>(el100);
        arv.remover(102);
        assertEquals(100, (int) arv.getRaiz().getChave());
        assertEquals(70, (int) arv.getRaiz().getEsq().getChave());
        assertEquals(120, (int) arv.getRaiz().getDir().getChave());
        assertEquals(60, (int) arv.getRaiz().getEsq().getEsq().getChave());
        assertEquals(80, (int) arv.getRaiz().getEsq().getDir().getChave());
        assertEquals(110, (int) arv.getRaiz().getDir().getEsq().getChave());
        assertEquals(150, (int) arv.getRaiz().getDir().getDir().getChave());
        assertEquals(null, arv.getRaiz().getDir().getEsq().getEsq());
        // ocorrência dos registros:
        assertEquals(1, arv.getRaiz().getOcorrencias());
        assertEquals(1, arv.getRaiz().getEsq().getOcorrencias());
        assertEquals(1, (int) arv.getRaiz().getDir().getOcorrencias());
        assertEquals(1, (int) arv.getRaiz().getEsq().getEsq().getOcorrencias());
        assertEquals(1, (int) arv.getRaiz().getEsq().getDir().getOcorrencias());
        assertEquals(1, (int) arv.getRaiz().getDir().getEsq().getOcorrencias());
        assertEquals(1, (int) arv.getRaiz().getDir().getDir().getOcorrencias());
    }

    @Test
    public void testeArvBinBuscaRemover2() {
        /*
                   100
                 /     \
               70       120
             /  \      /   \
            60  80   110   150
                    /
                   102(3x) <-- remover 1 ocorrencia de 102
        */
        NoArvBin<Integer> el100 = new NoArvBin<>(100);
        NoArvBin<Integer> el70 = new NoArvBin<>(70);
        NoArvBin<Integer> el120 = new NoArvBin<>(120);
        el100.setEsq(el70);
        el100.setDir(el120);
        NoArvBin<Integer> el60 = new NoArvBin<>(60);
        NoArvBin<Integer> el80 = new NoArvBin<>(80);
        NoArvBin<Integer> el110 = new NoArvBin<>(110);
        NoArvBin<Integer> el150 = new NoArvBin<>(150);
        el70.setEsq(el60);
        el70.setDir(el80);
        el120.setEsq(el110);
        el120.setDir(el150);
        NoArvBin<Integer> el102 = new NoArvBin<>(102);
        el110.setEsq(el102);
        el102.incOcorrencias(); // 2x
        el102.incOcorrencias(); // 3x
        ArvBinBusca<Integer> arv = new ArvBinBusca<>(el100);
        arv.remover(102);
        assertEquals(100, (int) arv.getRaiz().getChave());
        assertEquals(70, (int) arv.getRaiz().getEsq().getChave());
        assertEquals(120, (int) arv.getRaiz().getDir().getChave());
        assertEquals(60, (int) arv.getRaiz().getEsq().getEsq().getChave());
        assertEquals(80, (int) arv.getRaiz().getEsq().getDir().getChave());
        assertEquals(110, (int) arv.getRaiz().getDir().getEsq().getChave());
        assertEquals(150, (int) arv.getRaiz().getDir().getDir().getChave());
        assertEquals(102, (int) arv.getRaiz().getDir().getEsq().getEsq().getChave());
        // ocorrência dos registros:
        assertEquals(1, arv.getRaiz().getOcorrencias());
        assertEquals(1, arv.getRaiz().getEsq().getOcorrencias());
        assertEquals(1, (int) arv.getRaiz().getDir().getOcorrencias());
        assertEquals(1, (int) arv.getRaiz().getEsq().getEsq().getOcorrencias());
        assertEquals(1, (int) arv.getRaiz().getEsq().getDir().getOcorrencias());
        assertEquals(1, (int) arv.getRaiz().getDir().getEsq().getOcorrencias());
        assertEquals(1, (int) arv.getRaiz().getDir().getDir().getOcorrencias());
        assertEquals(2, (int) arv.getRaiz().getDir().getEsq().getEsq().getOcorrencias());
    }

    @Test
    public void testeArvBinBuscaRemover3() {
        /*
                   100
                 /     \
               70       120
              /  \      /
            60   80   110    <-- remover 80
                /
              75
        */
        NoArvBin<Integer> el100 = new NoArvBin<>(100);
        NoArvBin<Integer> el70 = new NoArvBin<>(70);
        NoArvBin<Integer> el120 = new NoArvBin<>(120);
        el100.setEsq(el70);
        el100.setDir(el120);
        NoArvBin<Integer> el60 = new NoArvBin<>(60);
        NoArvBin<Integer> el80 = new NoArvBin<>(80);
        NoArvBin<Integer> el110 = new NoArvBin<>(110);
        el70.setEsq(el60);
        el70.setDir(el80);
        el120.setEsq(el110);
        NoArvBin<Integer> el75 = new NoArvBin<>(75);
        el80.setEsq(el75);
        ArvBinBusca<Integer> arv = new ArvBinBusca<>(el100);
        arv.remover(80);
        assertEquals(100, (int) arv.getRaiz().getChave());
        assertEquals(70, (int) arv.getRaiz().getEsq().getChave());
        assertEquals(120, (int) arv.getRaiz().getDir().getChave());
        assertEquals(60, (int) arv.getRaiz().getEsq().getEsq().getChave());
        assertEquals(75, (int) arv.getRaiz().getEsq().getDir().getChave());
        assertEquals(110, (int) arv.getRaiz().getDir().getEsq().getChave());
        // ocorrência dos registros:
        assertEquals(1, arv.getRaiz().getOcorrencias());
        assertEquals(1, arv.getRaiz().getEsq().getOcorrencias());
        assertEquals(1, (int) arv.getRaiz().getDir().getOcorrencias());
        assertEquals(1, (int) arv.getRaiz().getEsq().getEsq().getOcorrencias());
        assertEquals(1, (int) arv.getRaiz().getEsq().getDir().getOcorrencias());
        assertEquals(1, (int) arv.getRaiz().getDir().getEsq().getOcorrencias());
    }

    @Test
    public void testeArvBinBuscaRemover4() {
        /*
                    100
                 /       \
               70        120
              /  \       /
            60  80(5x) 110    <-- remover 2 ocorrências de 80
                /
              75
        */
        NoArvBin<Integer> el100 = new NoArvBin<>(100);
        NoArvBin<Integer> el70 = new NoArvBin<>(70);
        NoArvBin<Integer> el120 = new NoArvBin<>(120);
        el100.setEsq(el70);
        el100.setDir(el120);
        NoArvBin<Integer> el60 = new NoArvBin<>(60);
        NoArvBin<Integer> el80 = new NoArvBin<>(80);
        NoArvBin<Integer> el110 = new NoArvBin<>(110);
        el70.setEsq(el60);
        el70.setDir(el80);
        el120.setEsq(el110);
        NoArvBin<Integer> el75 = new NoArvBin<>(75);
        el80.setEsq(el75);
        el80.incOcorrencias(); // 2x
        el80.incOcorrencias(); // 3x
        el80.incOcorrencias(); // 4x
        el80.incOcorrencias(); // 5x
        ArvBinBusca<Integer> arv = new ArvBinBusca<>(el100);
        arv.remover(80); // remove a 1a ocorrência
        arv.remover(80); // remove a 2a ocorrência
        assertEquals(100, (int) arv.getRaiz().getChave());
        assertEquals(70, (int) arv.getRaiz().getEsq().getChave());
        assertEquals(120, (int) arv.getRaiz().getDir().getChave());
        assertEquals(60, (int) arv.getRaiz().getEsq().getEsq().getChave());
        assertEquals(80, (int) arv.getRaiz().getEsq().getDir().getChave());
        assertEquals(75, (int) arv.getRaiz().getEsq().getDir().getEsq().getChave());
        assertEquals(110, (int) arv.getRaiz().getDir().getEsq().getChave());
        // ocorrência dos registros:
        assertEquals(1, arv.getRaiz().getOcorrencias());
        assertEquals(1, arv.getRaiz().getEsq().getOcorrencias());
        assertEquals(1, (int) arv.getRaiz().getDir().getOcorrencias());
        assertEquals(1, (int) arv.getRaiz().getEsq().getEsq().getOcorrencias());
        assertEquals(3, (int) arv.getRaiz().getEsq().getDir().getOcorrencias());
        assertEquals(1, (int) arv.getRaiz().getEsq().getDir().getEsq().getOcorrencias());
        assertEquals(1, (int) arv.getRaiz().getDir().getEsq().getOcorrencias());
    }

    @Test
    public void testeArvBinBuscaRemover5() {
        /*
                   100    <-- remover 100
                 /     \
               70       120
             /  \      /   \
            60  80   110   150
                    /
                   102
        */
        NoArvBin<Integer> el100 = new NoArvBin<>(100);
        NoArvBin<Integer> el70 = new NoArvBin<>(70);
        NoArvBin<Integer> el120 = new NoArvBin<>(120);
        el100.setEsq(el70);
        el100.setDir(el120);
        NoArvBin<Integer> el60 = new NoArvBin<>(60);
        NoArvBin<Integer> el80 = new NoArvBin<>(80);
        NoArvBin<Integer> el110 = new NoArvBin<>(110);
        NoArvBin<Integer> el150 = new NoArvBin<>(150);
        el70.setEsq(el60);
        el70.setDir(el80);
        el120.setEsq(el110);
        el120.setDir(el150);
        NoArvBin<Integer> el102 = new NoArvBin<>(102);
        el110.setEsq(el102);
        ArvBinBusca<Integer> arv = new ArvBinBusca<>(el100);
        arv.remover(100);
        assertEquals(102, (int) arv.getRaiz().getChave());
        assertEquals(70, (int) arv.getRaiz().getEsq().getChave());
        assertEquals(120, (int) arv.getRaiz().getDir().getChave());
        assertEquals(60, (int) arv.getRaiz().getEsq().getEsq().getChave());
        assertEquals(80, (int) arv.getRaiz().getEsq().getDir().getChave());
        assertEquals(110, (int) arv.getRaiz().getDir().getEsq().getChave());
        assertEquals(150, (int) arv.getRaiz().getDir().getDir().getChave());
        assertEquals(null, arv.getRaiz().getDir().getEsq().getEsq());
        // ocorrência dos registros:
        assertEquals(1, arv.getRaiz().getOcorrencias());
        assertEquals(1, arv.getRaiz().getEsq().getOcorrencias());
        assertEquals(1, (int) arv.getRaiz().getDir().getOcorrencias());
        assertEquals(1, (int) arv.getRaiz().getEsq().getEsq().getOcorrencias());
        assertEquals(1, (int) arv.getRaiz().getEsq().getDir().getOcorrencias());
        assertEquals(1, (int) arv.getRaiz().getDir().getEsq().getOcorrencias());
        assertEquals(1, (int) arv.getRaiz().getDir().getDir().getOcorrencias());
    }

    @Test
    public void testeArvBinBuscaRemover6() {
        /*
                   100(5x)    <-- remover 3 ocorrências de 100
                 /     \
               70       120
             /  \      /   \
            60  80   110   150
                    /
                   102
        */
        NoArvBin<Integer> el100 = new NoArvBin<>(100);
        NoArvBin<Integer> el70 = new NoArvBin<>(70);
        NoArvBin<Integer> el120 = new NoArvBin<>(120);
        el100.setEsq(el70);
        el100.setDir(el120);
        NoArvBin<Integer> el60 = new NoArvBin<>(60);
        NoArvBin<Integer> el80 = new NoArvBin<>(80);
        NoArvBin<Integer> el110 = new NoArvBin<>(110);
        NoArvBin<Integer> el150 = new NoArvBin<>(150);
        el70.setEsq(el60);
        el70.setDir(el80);
        el120.setEsq(el110);
        el120.setDir(el150);
        NoArvBin<Integer> el102 = new NoArvBin<>(102);
        el110.setEsq(el102);
        el100.incOcorrencias(); // 2x
        el100.incOcorrencias(); // 3x
        el100.incOcorrencias(); // 4x
        el100.incOcorrencias(); // 5x
        ArvBinBusca<Integer> arv = new ArvBinBusca<>(el100);
        arv.remover(100);
        arv.remover(100);
        arv.remover(100);
        assertEquals(100, (int) arv.getRaiz().getChave());
        assertEquals(70, (int) arv.getRaiz().getEsq().getChave());
        assertEquals(120, (int) arv.getRaiz().getDir().getChave());
        assertEquals(60, (int) arv.getRaiz().getEsq().getEsq().getChave());
        assertEquals(80, (int) arv.getRaiz().getEsq().getDir().getChave());
        assertEquals(110, (int) arv.getRaiz().getDir().getEsq().getChave());
        assertEquals(150, (int) arv.getRaiz().getDir().getDir().getChave());
        assertEquals(102, (int) arv.getRaiz().getDir().getEsq().getEsq().getChave());
        // ocorrência dos registros:
        assertEquals(2, arv.getRaiz().getOcorrencias());
        assertEquals(1, arv.getRaiz().getEsq().getOcorrencias());
        assertEquals(1, (int) arv.getRaiz().getDir().getOcorrencias());
        assertEquals(1, (int) arv.getRaiz().getEsq().getEsq().getOcorrencias());
        assertEquals(1, (int) arv.getRaiz().getEsq().getDir().getOcorrencias());
        assertEquals(1, (int) arv.getRaiz().getDir().getEsq().getOcorrencias());
        assertEquals(1, (int) arv.getRaiz().getDir().getDir().getOcorrencias());
        assertEquals(1, (int) arv.getRaiz().getDir().getEsq().getEsq().getOcorrencias());
    }

    @Test
    public void testeArvBinGetProfundidade() {
        /*
                  100
                /     \
              70       120
             /  \     /  \
            60  80  110   150
                   /
                  102
                  /
                101
        */
        NoArvBin<Integer> el100 = new NoArvBin<>(100);
        NoArvBin<Integer> el70 = new NoArvBin<>(70);
        NoArvBin<Integer> el120 = new NoArvBin<>(120);
        el100.setEsq(el70);
        el100.setDir(el120);
        NoArvBin<Integer> el60 = new NoArvBin<>(60);
        NoArvBin<Integer> el80 = new NoArvBin<>(80);
        NoArvBin<Integer> el110 = new NoArvBin<>(110);
        NoArvBin<Integer> el150 = new NoArvBin<>(150);
        el70.setEsq(el60);
        el70.setDir(el80);
        el120.setEsq(el110);
        el120.setDir(el150);
        NoArvBin<Integer> el102 = new NoArvBin<>(102);
        el110.setEsq(el102);
        NoArvBin<Integer> el101 = new NoArvBin<>(101);
        el102.setEsq(el101);
        ArvBinBusca<Integer> arv = new ArvBinBusca<>(el100);
        assertEquals(5, arv.getProfundidade());
    }

    @Test
    public void testeArvBinQtdRegistros1() {
        /*
                  100
                /     \
              70       120
             /  \     /  \
            60  80  110   150
                   /
                  102
        */
        NoArvBin<Integer> el100 = new NoArvBin<>(100);
        NoArvBin<Integer> el70 = new NoArvBin<>(70);
        NoArvBin<Integer> el120 = new NoArvBin<>(120);
        el100.setEsq(el70);
        el100.setDir(el120);
        NoArvBin<Integer> el60 = new NoArvBin<>(60);
        NoArvBin<Integer> el80 = new NoArvBin<>(80);
        NoArvBin<Integer> el110 = new NoArvBin<>(110);
        NoArvBin<Integer> el150 = new NoArvBin<>(150);
        el70.setEsq(el60);
        el70.setDir(el80);
        el120.setEsq(el110);
        el120.setDir(el150);
        NoArvBin<Integer> el102 = new NoArvBin<>(102);
        el110.setEsq(el102);
        ArvBinBusca<Integer> arv = new ArvBinBusca<>(el100);
        assertEquals(8, arv.qtdRegistros());
    }

    @Test
    public void testeArvBinQtdRegistros2() {
        /*
                      100(2x)
                   /          \
                70           120
             /     \      /      \
            60(3x) 80   110(5x)  150
                         /
                         102
        */
        NoArvBin<Integer> el100 = new NoArvBin<>(100);
        NoArvBin<Integer> el70 = new NoArvBin<>(70);
        NoArvBin<Integer> el120 = new NoArvBin<>(120);
        el100.setEsq(el70);
        el100.setDir(el120);
        NoArvBin<Integer> el60 = new NoArvBin<>(60);
        NoArvBin<Integer> el80 = new NoArvBin<>(80);
        NoArvBin<Integer> el110 = new NoArvBin<>(110);
        NoArvBin<Integer> el150 = new NoArvBin<>(150);
        el70.setEsq(el60);
        el70.setDir(el80);
        el120.setEsq(el110);
        el120.setDir(el150);
        NoArvBin<Integer> el102 = new NoArvBin<>(102);
        el110.setEsq(el102);
        el100.incOcorrencias();
        el60.incOcorrencias();
        el60.incOcorrencias();
        el110.incOcorrencias();
        el110.incOcorrencias();
        el110.incOcorrencias();
        el110.incOcorrencias();
        ArvBinBusca<Integer> arv = new ArvBinBusca<>(el100);
        assertEquals(15, arv.qtdRegistros());
    }

    @Test
    public void testeArvBinBuscaEmOrdem() {
        /*
                  100
              /         \
            70         120(2x)
           /  \        /   \
          60  80(3x) 110   150
                        \
                        112
        */
        NoArvBin<Integer> el100 = new NoArvBin<>(100);
        NoArvBin<Integer> el70 = new NoArvBin<>(70);
        NoArvBin<Integer> el120 = new NoArvBin<>(120);
        el100.setEsq(el70);
        el100.setDir(el120);
        NoArvBin<Integer> el60 = new NoArvBin<>(60);
        NoArvBin<Integer> el80 = new NoArvBin<>(80);
        NoArvBin<Integer> el110 = new NoArvBin<>(110);
        NoArvBin<Integer> el150 = new NoArvBin<>(150);
        el70.setEsq(el60);
        el70.setDir(el80);
        el120.setEsq(el110);
        el120.setDir(el150);
        NoArvBin<Integer> el112 = new NoArvBin<>(112);
        el110.setDir(el112);
        el80.incOcorrencias();
        el80.incOcorrencias(); // 3x
        el120.incOcorrencias(); // 2x
        ArvBinBusca<Integer> arv = new ArvBinBusca<>(el100);
        ArrayList<Integer> resultado = arv.emOrdem();
        assertArrayEquals(new int[]{60, 70, 80, 80, 80, 100, 110, 112, 120, 120, 150}, arrayListToPrimitive(resultado));
    }

    @Test
    public void testeArvBinBuscaPreOrdem() {
        /*
                  100
              /         \
            70         120(2x)
           /  \        /   \
          60  80(3x) 110   150
                        \
                        112
        */
        NoArvBin<Integer> el100 = new NoArvBin<>(100);
        NoArvBin<Integer> el70 = new NoArvBin<>(70);
        NoArvBin<Integer> el120 = new NoArvBin<>(120);
        el100.setEsq(el70);
        el100.setDir(el120);
        NoArvBin<Integer> el60 = new NoArvBin<>(60);
        NoArvBin<Integer> el80 = new NoArvBin<>(80);
        NoArvBin<Integer> el110 = new NoArvBin<>(110);
        NoArvBin<Integer> el150 = new NoArvBin<>(150);
        el70.setEsq(el60);
        el70.setDir(el80);
        el120.setEsq(el110);
        el120.setDir(el150);
        NoArvBin<Integer> el112 = new NoArvBin<>(112);
        el110.setDir(el112);
        el80.incOcorrencias();
        el80.incOcorrencias(); // 3x
        el120.incOcorrencias(); // 2x
        ArvBinBusca<Integer> arv = new ArvBinBusca<>(el100);
        ArrayList<Integer> resultado = arv.preOrdem();
        assertArrayEquals(new int[]{100, 70, 60, 80, 80, 80, 120, 120, 110, 112, 150}, arrayListToPrimitive(resultado));
    }

    @Test
    public void testeArvBinBuscaPosOrdem() {
        /*
                  100
              /         \
            70         120(2x)
           /  \        /   \
          60  80(3x) 110   150
                        \
                        112
        */
        NoArvBin<Integer> el100 = new NoArvBin<>(100);
        NoArvBin<Integer> el70 = new NoArvBin<>(70);
        NoArvBin<Integer> el120 = new NoArvBin<>(120);
        el100.setEsq(el70);
        el100.setDir(el120);
        NoArvBin<Integer> el60 = new NoArvBin<>(60);
        NoArvBin<Integer> el80 = new NoArvBin<>(80);
        NoArvBin<Integer> el110 = new NoArvBin<>(110);
        NoArvBin<Integer> el150 = new NoArvBin<>(150);
        el70.setEsq(el60);
        el70.setDir(el80);
        el120.setEsq(el110);
        el120.setDir(el150);
        NoArvBin<Integer> el112 = new NoArvBin<>(112);
        el110.setDir(el112);
        el80.incOcorrencias();
        el80.incOcorrencias(); // 3x
        el120.incOcorrencias(); // 2x
        ArvBinBusca<Integer> arv = new ArvBinBusca<>(el100);
        ArrayList<Integer> resultado = arv.posOrdem();
        assertArrayEquals(new int[]{60, 80, 80, 80, 70, 112, 110, 150, 120, 120, 100}, arrayListToPrimitive(resultado));
    }
}
