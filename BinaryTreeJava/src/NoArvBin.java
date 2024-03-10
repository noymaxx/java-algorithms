/*
    A classe NoArvBin deve aceitar o tipo genérico T, que será o tipo da
    chave guardada no nó. Este tipo, por sua vez, deve ser Comparable
    ("comparável"). Ou seja, independente do tipo usado na chave, ela sempre
    terá disponível o método compareTo() que retorna -1, 0 ou 1, dependendo
    se os valores comparados são menores, iguais ou maiores, respectivamente.

    Dica: não se preocupe com isso. Você não precisa implementar este método,
    ele já foi implementado nas classes que podemos utilizar. Entretanto,
    ele será necessário para comparar valores durante a implementação das
    operações na árvore.

    OBS: não modifique a assinatura da classe abaixo!
*/
public class NoArvBin<T extends Comparable<T>> {

    /* Atributos: crie quatro atributos privados:
        - uma chave do tipo genérico T, que será o valor útil armazenado no nó;
        - um atributo do tipo int para guardar a quantidade de ocorrências da
    chave guardada neste nó;
        - dois atributos do tipo NoArvBin<T>, que guardam os apontadores para os
    filhos esquerdo e direito deste nó. */

    private T chave;
    private int ocorrencias;
    private NoArvBin<T> esq;
    private NoArvBin<T> dir;

    public NoArvBin(T chave) {
        /* Primeiro construtor: inicializa o atributo que representa a chave com
        o parâmetro passado, a quantidade de ocorrências com o valor 1, e os
        apontadores esquerdo e direito com valores null. */
        this.chave = chave;
        this.ocorrencias = 1;
        this.esq = null;
        this.dir = null;
    }

    public T getChave() {
        /* Retorna o valor armazenado no atributo privado que representa a chave. */
        return chave; // substituir
    }

    public void setChave(T chave) {
        /* Recebe uma nova chave por parâmetro e modifica o atributo privado que
        representa a chave. */
        this.chave = chave;
    }

    public int getOcorrencias() {
        /* Retorna o valor armazenado no atributo privado que guarda as ocorrências
        da chave. */
        return ocorrencias; // substituir
    }

    public void incOcorrencias() {
        /* Incrementa em 1 unidade o valor do atributo privado que guarda as
        ocorrências da chave. */
        ocorrencias++;
    }

    public void decOcorrencias() {
        /* Decrementa em 1 unidade o valor do atributo privado que guarda as
        ocorrências da chave.
        O número de ocorrências não pode ser abaixo de 1, independente de
        quantas vezes este método seja chamado. */
        if (ocorrencias > 1) {
            ocorrencias--;
        }
    }

    public NoArvBin<T> getEsq() {
        /* Retorna o valor armazenado no atributo privado que representa o
        apontador esquerdo. */
        return esq; // substituir
    }

    public void setEsq(NoArvBin<T> esq) {
        /* Recebe um novo apontador por parâmetro e modifica o atributo privado
        que representa o apontador esquerdo. */
        this.esq = esq;
    }

    public NoArvBin<T> getDir() {
        /* Retorna o valor armazenado no atributo privado que representa o
        apontador direito. */
        return dir; // substituir
    }

    public void setDir(NoArvBin<T> dir) {
        /* Recebe um novo apontador por parâmetro e modifica o atributo privado
        que representa o apontador direito. */
        this.dir = dir;
    }

    public static void main(String[] args) {
        // Método para testes. Algumas saídas esperadas estão listadas abaixo:
        NoArvBin<Integer> no = new NoArvBin<>(5); // cria um nó do tipo Integer (T será substituído por este tipo), com a chave 5
        no.setChave(60);
        System.out.println(no.getChave()); // imprime 60
        System.out.println(no.getOcorrencias()); // imprime 1
        no.incOcorrencias();
        no.incOcorrencias();
        System.out.println(no.getOcorrencias()); // imprime 3
        no.decOcorrencias();
        System.out.println(no.getOcorrencias()); // imprime 2
        NoArvBin<Integer> no2 = new NoArvBin<>(4);
        NoArvBin<Integer> no3 = new NoArvBin<>(100);
        no.setEsq(no2);
        no.setDir(no3);
        System.out.println(no.getEsq() == no2); // imprime true
        System.out.println(no.getDir() == no2); // imprime false
        System.out.println(no.getEsq() == no3); // imprime false
        System.out.println(no.getDir() == no3); // imprime true
    }
}
