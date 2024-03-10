import java.util.ArrayList;

/*
   A classe que representa a árvore binária de busca deve ter a assinatura abaixo,
   portanto mantenha da maneira que está.
   O valor T é um tipo genérico, portanto ao instanciar uma nova árvore, você
   deverá fazê-lo da seguinte maneira:
        ArvBinBusca<Tipo> arvore = new ArvBinBusca<>(), onde Tipo é uma classe
   qualquer que implementa a interface Comparable, ou seja, esta classe
   deve implementar o método compareTo().

   Caso precise de mais informações sobre objetos que implementam Comparable,
   visite: https://docs.oracle.com/javase/8/docs/api/java/lang/Comparable.html

   OBS: não modifique a assinatura da classe abaixo!
*/
public class ArvBinBusca<T extends Comparable<T>> {

    /* Atributos: crie um atributo privado do tipo NoArvBin<T> que armazena a raiz
    da árvore. A classe só deve ter este único atributo. */
    private NoArvBin<T> raiz;

    public ArvBinBusca() {
        /* Primeiro construtor: inicializa a raiz da árvore com o valor nulo. */
        this.raiz = null;
    }

    public ArvBinBusca(NoArvBin<T> novaRaiz) {
        /* Segundo construtor: inicializa a raiz da árvore com o valor passado
        por parâmetro, do tipo NoArvBin<T>.

        Este método é muito importante, pois sem ele não será possível rodar
        parte dos testes. */
        this.raiz = novaRaiz;
    }

    public NoArvBin<T> getRaiz() {
        /* Retorna a raiz da árvore. Este método é muito importante, pois sem ele
        não será possível rodar os demais testes. */
        return raiz; // substituir
    }

    public void inserir(T novaChave) {
        /* Insere uma novaChave do tipo genérico T na árvore binária de busca.
        Se a chave não existir, cria um novo nó e inicializa o contador de
        ocorrências deste nó em 1 unidade.

        Se a chave já existir na árvore, o método não deve criar um novo nó, e
        ao invés disso, deve incrementar o contador de ocorrências desta chave.

        Caso julgue necessário, crie outro método privado para auxiliar
        na inserção. */
        if (raiz == null) {
            raiz = new NoArvBin<T>(novaChave);
        } else {
            NoArvBin<T> noAtual = raiz;
            NoArvBin<T> noPai;
            while (true) {
                noPai = noAtual;
                if (novaChave.compareTo(noAtual.getChave()) < 0) {
                    if (noAtual.getEsq() != null) {
                        noAtual = noAtual.getEsq();
                    } else {
                        noPai.setEsq(new NoArvBin<T>(novaChave));
                        break;
                    }
                } else if (novaChave.compareTo(noAtual.getChave()) > 0) {
                    if (noAtual.getDir() != null) {
                        noAtual = noAtual.getDir();
                    } else {
                        noPai.setDir(new NoArvBin<T>(novaChave));
                        break;
                    }
                } else {
                    noAtual.incOcorrencias();
                    return;
                }
            }
        }
    }

    public void remover(T chave) {
        /* Remove uma chave do tipo genérico T na árvore binária de busca.

        Se existir mais de uma ocorrência da chave na árvore, o método não deve
        remover o novo nó, e ao invés disso, deve decrementar o contador de
        ocorrências desta chave.

        Dica: utilize a técnica que substitui um nó com 2 filhos pelo menor nó
        da subárvore direita, caso contrário seu método não passará nos casos
        de teste programados.

        Caso julgue necessário, crie outros métodos privados para auxiliar
        na remoção. */
        if (raiz == null) {
            return;
        }

        NoArvBin<T> noAtual = raiz;
        NoArvBin<T> noPai = null;
        boolean esq = true;

        while (noAtual != null && !noAtual.getChave().equals(chave)) {
            noPai = noAtual;

            if (chave.compareTo(noAtual.getChave()) < 0) {
                esq = true;
                noAtual = noAtual.getEsq();
            } else if (chave.compareTo(noAtual.getChave()) > 0) {
                esq = false;
                noAtual = noAtual.getDir();
            }
        }

        if (noAtual.getOcorrencias() > 1) {
            noAtual.decOcorrencias();
            return;
        }

        if (noAtual.getEsq() == null && noAtual.getDir() == null) {
            if (noAtual == raiz) {
                raiz = null;
            } else if (esq) {
                noPai.setEsq(null);
            } else {
                noPai.setDir(null);
            }
        }
        else if (noAtual.getEsq() == null) {
            if (noAtual == raiz) {
                raiz = noAtual.getDir();
            } else if (esq) {
                noPai.setEsq(noAtual.getDir());
            } else {
                noPai.setDir(noAtual.getDir());
            }
        }
        else if (noAtual.getDir() == null) {
            if (noAtual == raiz) {
                raiz = noAtual.getEsq();
            } else if (esq) {
                noPai.setEsq(noAtual.getEsq());
            } else {
                noPai.setDir(noAtual.getEsq());
            }
        }
        else {
            NoArvBin<T> substituto = getMenorNo(noAtual.getDir());
            if (noPai == null) {
                raiz = substituto;
            } else if (esq) {
                noPai.setEsq(substituto);
            } else {
                noPai.setDir(substituto);
            }
            substituto.setEsq(noAtual.getEsq());
            if (substituto != noAtual.getDir()) {
                paiDoMenor(substituto).setEsq(substituto.getDir());
                substituto.setDir(noAtual.getDir());
            }
        }
    }

    private NoArvBin<T> getMenorNo(NoArvBin<T> no) {
        while (no.getEsq() != null) {
            no = no.getEsq();
        }
        return no;
    }

    private NoArvBin<T> paiDoMenor(NoArvBin<T> no) {
        NoArvBin<T> pai = no;
        while (pai.getEsq() != null && pai.getEsq().getEsq() != null) {
            pai = pai.getEsq();
        }
        return pai;
    }

    public String buscar(T chave) {
        /* Busca uma chave do tipo genérico T na árvore binária de busca.

        O método deve retornar uma String, sem espaços em branco, contendo todo
        o caminho percorrido ("E": esquerda, "D": direita) para buscar a chave
        na árvore, e o número de ocorrências desta chave (caso ela não exista,
        este valor será 0).

        Exemplo: suponha que desejamos buscar a chave 102 na seguinte árvore
        binária de busca, e existam 5 ocorrências desta chave:
                    90
                 /     \
               70       120
             /  \      /   \
            60  80   110   150
                    /
                   102(5x)

        O método deverá retornar a String: "DEE5";

        Se na árvore do exemplo acima desejarmos procurar por "90", retorna: "1";
        Por sua vez, ao procurar a chave 89, o método deve retornar: "EDD0".

        Caso julgue necessário, crie outro método privado para auxiliar
        na busca. */
         // substituir
        NoArvBin<T> noAtual = raiz;
        NoArvBin<T> paiAtual = null;
        String caminho = "";

        while (noAtual != null) {
            if (chave.compareTo(noAtual.getChave()) == 0) {
                return caminho + noAtual.getOcorrencias();
            }
            if (chave.compareTo(noAtual.getChave()) < 0) {
                caminho += "E";
                paiAtual = noAtual;
                noAtual = noAtual.getEsq();
            } else {
                caminho += "D";
                paiAtual = noAtual;
                noAtual = noAtual.getDir();
            }
        }
        return caminho + "0";
    }

    public int getProfundidade() {
        /* Retorna a profundidade (isto é, a quantidade de níveis) da árvore.

        Ideia principal: vasculhe a subárvore esquerda e a subárvore direita,
        até que a raiz da respectiva subárvore seja null. Uma subárvore cuja
        raiz seja null não possui nenhum nó, portanto sua profundidade é zero.
        A cada etapa, calcule qual das duas subárvores possui a maior profundidade.

        Caso julgue necessário, crie outro método privado para auxiliar
        no cálculo da profundidade da árvore. */
        return calculoProfundidade(raiz); // substituir
    }

    private int calculoProfundidade(NoArvBin<T> noAtual) {
        if (noAtual == null) {
            return 0;
        }
        int esq = calculoProfundidade(noAtual.getEsq());
        int dir = calculoProfundidade(noAtual.getDir());
        if (esq > dir) {
            return esq + 1;
        } else {
            return dir + 1;
        }
    }

    public int qtdRegistros() {
        /* Retorna a quantidade de chaves inseridas na árvore. Isto é, ocorrências
        repetidas devem ser consideradas.

        Ideia principal: vasculhe a subárvore esquerda e a subárvore direita,
        até que a raiz da respectiva subárvore seja null. Uma subárvore cuja
        raiz seja null não possui nenhum nó, a quantidade de registros nela
        é zero.
        A cada etapa, some a quantidade de registros das subárvores esquerda,
        direita, e também a quantidade de ocorrências da chave contida na raiz
        da respectiva subárvore.

        Caso julgue necessário, crie outro método privado para auxiliar
        no cálculo da quantidade de registros da árvore. */
        return calculoRegistros(raiz); // substituir
    }

    private int calculoRegistros(NoArvBin<T> noAtual) {
        if (noAtual == null) {
            return 0;
        }
        return noAtual.getOcorrencias() + calculoRegistros(noAtual.getEsq()) + calculoRegistros(noAtual.getDir());
    }

    public ArrayList<T> emOrdem() {
        /* Retorna um ArrayList com todas chaves existentes na árvore.
        A ordem que as chaves aparecem no ArrayList deve ser a mesma obtida
        através do percurso em ordem (ou ordem simétrica). Chaves com mais de
        uma ocorrência devem aparecer repetidas no array.

        Caso julgue necessário, crie outro método privado para auxiliar no
        percurso em ordem. */

        ArrayList<T> lista = new ArrayList<>();
        emOrdemAux(raiz, lista);
        return lista;
    }

    private void emOrdemAux(NoArvBin<T> noAtual, ArrayList<T> lista) {
        if (noAtual != null) {
            emOrdemAux(noAtual.getEsq(), lista);
            for (int i = 0; i < noAtual.getOcorrencias(); i++) {
                lista.add(noAtual.getChave());
            }
            emOrdemAux(noAtual.getDir(), lista);
        }
    }

    public ArrayList<T> preOrdem() {
        /* Retorna um ArrayList com todas chaves existentes na árvore.
        A ordem que as chaves aparecem no ArrayList deve ser a mesma obtida
        através do percurso em pré-ordem. Chaves com mais de uma ocorrência
        devem aparecer repetidas no array.

        Caso julgue necessário, crie outro método privado para auxiliar no
        percurso em pré-ordem. */
        ArrayList<T> lista = new ArrayList<>();
        preOrdemAux(raiz, lista);
        return lista;
    }

    private void preOrdemAux(NoArvBin<T> noAtual, ArrayList<T> lista) {
        if (noAtual != null) {
            for (int i = 0; i < noAtual.getOcorrencias(); i++) {
                lista.add(noAtual.getChave());
            }
            preOrdemAux(noAtual.getEsq(), lista);
            preOrdemAux(noAtual.getDir(), lista);
        }
    }

    public ArrayList<T> posOrdem() {
        /* Retorna um ArrayList com todas chaves existentes na árvore.
        A ordem que as chaves aparecem no ArrayList deve ser a mesma obtida
        através do percurso em pós-ordem. Chaves com mais de uma ocorrência
        devem aparecer repetidas no array.

        Caso julgue necessário, crie outro método privado para auxiliar no
        percurso em pós-ordem. */
        ArrayList<T> lista = new ArrayList<>();
        posOrdemAux(raiz, lista);
        return lista;
    }

    private void posOrdemAux(NoArvBin<T> noAtual, ArrayList<T> lista) {
        if (noAtual != null) {
            posOrdemAux(noAtual.getEsq(), lista);
            posOrdemAux(noAtual.getDir(), lista);
            for (int i = 0; i < noAtual.getOcorrencias(); i++) {
                lista.add(noAtual.getChave());
            }
        }
    }

    public static void main(String[] args) {
        // Método para testes. Algumas saídas esperadas estão listadas abaixo:
        ArvBinBusca<Integer> arv = new ArvBinBusca<>();
        /*
                   90
                 /     \
               70       120
             /  \      /   \
            60  80   110   150
                    /
                   102(5x)
        */
        arv.inserir(90);
        arv.inserir(70);
        arv.inserir(120);
        arv.inserir(60);
        arv.inserir(80);
        arv.inserir(110);
        arv.inserir(102);
        arv.inserir(102);
        arv.inserir(150);
        arv.inserir(102);
        arv.inserir(102);
        arv.inserir(102);
        System.out.println(arv.buscar(90)); // imprime 1
        System.out.println(arv.buscar(102)); // imprime DEE1
        System.out.println(arv.buscar(89)); // imprime EDD0
        System.out.println(arv.getProfundidade()); // imprime 4
        System.out.println(arv.qtdRegistros()); // imprime 12
        System.out.println(arv.emOrdem().toString()); // imprime [60, 70, 80, 90, 102, 102, 102, 102, 102, 110, 120, 150]
        System.out.println(arv.preOrdem().toString()); // imprime [90, 70, 60, 80, 120, 110, 102, 102, 102, 102, 102, 150]
        System.out.println(arv.posOrdem().toString()); // imprime [60, 80, 70, 102, 102, 102, 102, 102, 110, 150, 120, 90]
    }
}
