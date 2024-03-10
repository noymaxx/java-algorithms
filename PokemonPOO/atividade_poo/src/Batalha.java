public class Batalha {
    /*
        Defina 4 atributos privados:
        - treinador1, treinador2 do tipo Treinador (isto é, objetos já instanciados da classe Treinador);
        - vencedor, do tipo int;
        - iniciada, do tipo boolean.
    */
    private Treinador treinador1, treinador2;
    private int vencedor;
    private boolean iniciada;

    public Batalha(Treinador treinador1, Treinador treinador2) {
        /*
        Construtor da classe
        Inicialize os atributos privados com:
        - treinador1 recebe o mesmo endereço do parâmetro treinador1 (dica: é só fazer uma atribuição simples);
        - treinador2 recebe o mesmo endereço do parâmetro treinador2
        - vencedor deve ser zero (indica quem venceu a partida: 0 = ninguém, 1 = o treinador1, 2 = o treinador2);
        - iniciada deve ser false (indica se a batalha já ocorreu ou não).
        */
        this.treinador1 = treinador1;
        this.treinador2 = treinador2;

        vencedor = 0;
        iniciada = false;
    }

    public Treinador getTreinador1() {
        /*
        Retorna o objeto (atributo privado) treinador1.
        */
        return treinador1;
    }

    public Treinador getTreinador2() {
        /*
        Retorna o objeto (atributo privado) treinador2.
        */
        return treinador2;
    }

    public int getVencedor() {
        /*
        Retorna o valor do atributo privado vencedor.
        */
        return vencedor;
    }

    public int batalhar(int i1, int i2, int c, int k) {
        /*
        Recebe 4 parâmetros:
        - i1, um índice de listaPokemons do treinador1, indicando qual Pokémon será usado por este treinador;
        - i2, um índice de listaPokemons do treinador2, indicando qual Pokémon será usado por este treinador;
        - c: pode ser 1 ou 2, indicando qual Pokémon começará atacando (se o do treinador1, ou o do treinador2);
        - k: inteiro >= 1, indica de quantas em quantas iterações devem ser usados os ataques com habilidade, ao invés de ataques simples.

        Dica 1: ao iniciar a batalha, restaure os hit points de ambos os Pokémons. A batalha só termina quando os hit points de um
        dos Pokémons for igual a zero. A partir deste momento, não deve haver mais ataques. Ou seja, a cada chamada do método atacar(),
        independente do Pokémon que o chamou, esta condição deve ser checada.

        Dica 2: crie uma variável contadora (por exemplo, iter), começando de 1 para contar quantas iterações foram necessárias para
        encerrar o jogo. Toda vez que (iter % k == 0), ou seja, iter for múltiplo de k, ambos os Pokémons devem atacar usando suas
        habilidades especiais.

        Dica 3: a cada iteração, cada um dos Pokémons se ataca. Exemplo:
        Quando c == 2, primeiro o Pokémon do treinador2 ataca o Pokémon do treinador1.
        Em seguida, o Pokémon do treinador1 ataca o Pokémon do treinador2. Só então a variável contadora (iter) é incrementada.

        Ao final da batalha:
        - incremente as vitórias e derrotas de cada treinador;
        - retorne o valor da variável contadora (iter), indicando quantas iterações foram necessárias para finalizar a batalha.

        Uma batalha não pode ocorrer quando:
        - o atributo privado iniciada já for true;
        - i1 estiver fora do intervalo válido da listaPokemons do treinador1;
        - i2 estiver fora do intervalo válido da listaPokemons do treinador2;
        Neste caso, o método deve retornar 0.

        */
        if (!iniciada) {
            iniciada = true;
        } else {
            return 0;
        }

        if (i1 < 0 || i1 >= treinador1.totalPokemons()) {
            return 0;
        }

        if (i2 < 0 || i2 >= treinador2.totalPokemons()) {
            return 0;
        }

        Pokemon pokemon1 = treinador1.getListaPokemons().get(i1);
        pokemon1.restaurarHp();

        Pokemon pokemon2 = treinador2.getListaPokemons().get(i2);
        pokemon2.restaurarHp();

        int iter;

        for (iter = 1; pokemon1.getHpAtual() > 0 && pokemon2.getHpAtual() > 0; iter++) {
            if (iter % k == 0) {
                if (c % 2 == 0) {
                    pokemon2.atacarComHabilidade(pokemon1);
                    if (pokemon1.getHpAtual() > 0) {
                        pokemon1.atacarComHabilidade((pokemon2));
                    }
                } else {
                    pokemon1.atacarComHabilidade(pokemon2);
                    if (pokemon2.getHpAtual() > 0) {
                        pokemon2.atacarComHabilidade(pokemon1);
                    }
                }
            } else {
                if (c % 2 == 0) {
                    pokemon2.atacar(pokemon1);
                    if (pokemon1.getHpAtual() > 0) {
                        pokemon1.atacar(pokemon2);
                    }
                } else {
                    pokemon1.atacar(pokemon2);
                    if (pokemon2.getHpAtual() > 0) {
                        pokemon2.atacar(pokemon1);
                    }
                }
            }
            if (c == 1) {
                c = 2;
            } else {
                c = 1;
            }

            if (pokemon1.getHpAtual() <= 0 || pokemon2.getHpAtual() <= 0) break;
        }

        if (pokemon1.getHpAtual() > 0) {
            vencedor = 1;
            treinador1.incVitorias();
            treinador2.incDerrotas();
        } else if (pokemon2.getHpAtual() > 0) {
            vencedor = 2;
            treinador2.incVitorias();
            treinador1.incDerrotas();
        }

        return iter;
    }

    public static void main(String[] args) {
        /* Método main para testes
           Não é necessário implementá-lo, mas você pode testar as funcionalidades da classe Batalha aqui.
        */
    }

}
