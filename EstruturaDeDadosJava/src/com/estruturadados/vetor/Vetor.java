package com.estruturadados.vetor;

public class Vetor {

    private String[] elementos;
    private int tamanho;

    // cria construtor
    public Vetor(int capacidade){
        this.elementos = new  String[capacidade];
        this.tamanho = 0;
    }

    /* public void adiciona(String elemento){
        for(int i=0; i<this.elementos.length; i++){
            if(this.elementos[i] == null){
                this.elementos[i] = elemento;
                break;
            }
        }
    } */

    /*public void adiciona(String elemento) throws Exception{

        if(tamanho <  this.elementos.length){
            this.elementos[this.tamanho] = elemento;
            this.tamanho++;
        } else {
            throw new Exception("Vetor já está cheio");
        }
    }*/

    // adiciona elemento no final do vetor
    public boolean adiciona(String elemento){

        this.aumentaCapacidade();

        if(this.tamanho < this.elementos.length){
            this.elementos[this.tamanho] = elemento;
            this.tamanho++;
            return true;
        }
        return false;
    }


    public boolean adiciona(int posicao, String elemento){
        if (!(posicao >= 0 && posicao < tamanho)){
            throw new IllegalArgumentException("Posição Inválida");
        }

        this.aumentaCapacidade();

        for (int i=this.tamanho-1; i>=posicao; i--){
            this.elementos[i+1] = this.elementos[i];
        }
        this.elementos[posicao] = elemento;
        this.tamanho ++;

        return true;
    }

    private void aumentaCapacidade() {
        if(this.tamanho == this.elementos.length){
            String[] elementosNovos = new String[this.elementos.length * 2];
            for (int i=0; i<this.elementos.length; i++){
                this.elementos = elementosNovos;
            }
        }
    }

    // verifca o tamanho e imprime os elementos do vetor
    public String busca(int posicao){
        if (!(posicao >= 0 && posicao < tamanho)){
            throw new IllegalArgumentException("Posição Inválida");
        }
        return this.elementos[posicao];
    }

    // verifica se o elemento existe no vetor
    public int busca(String elemento){
        for (int i=0; i<this.tamanho; i++){
            if (this.elementos[i].equals(elemento)){
                return i;
            }
        }
        return -1;
    }

    public void remove(int posicao){
        if(!(posicao >= 0 && posicao < tamanho)){
            throw new IllegalArgumentException("Posição inválida");
        }
        for (int i = posicao; i<this.tamanho-1; i++){
            this.elementos[i] = this.elementos[i+1];
        }
        tamanho--;
    }

    public int tamanho(){
        return this.tamanho;
    }

    @Override
    public String toString() {

        StringBuilder s = new StringBuilder();
        s.append("[");

        for (int i=0; i<this.tamanho-1; i++){
            s.append(this.elementos[i]);
            s.append(",");
        }

        if(this.tamanho>0){
            s.append(this.elementos[this.tamanho-1]);
        }

        s.append("]");

        return s.toString();
    }

}
