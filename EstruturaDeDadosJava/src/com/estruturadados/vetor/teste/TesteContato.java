package com.estruturadados.vetor.teste;

import com.estruturadados.vetor.VetorObjetos;

public class TesteContato {
    public static void main(String[] args) {

        VetorObjetos vetor = new VetorObjetos(3);

        Contato c1 = new Contato("Junin", "32 988652622", "junin@gmail.com");
        Contato c2 = new Contato("Vtrem", "32 988777666", "vtrem@gmail.com");
        Contato c3 = new Contato("HB", "32 123456789", "hb@gmail.com");

        Contato c4 = new Contato("teste", "11 11111111", "teste@gmail.com");

        vetor.adiciona(c1);
        vetor.adiciona(c2);
        vetor.adiciona(c3);

        int pos = vetor.busca(c4);
        if (pos > -1) {
            System.out.println("Elemento existe no vetor");
        } else {
            System.out.println("Elemento nao existe no vetor");
        }

    }
}
