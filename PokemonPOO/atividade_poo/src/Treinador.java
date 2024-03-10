import java.util.ArrayList;

public class Treinador extends Pessoa {
    /*
        Defina 3 atributos privados:
        - listaPokemons, do tipo ArrayList, que guarda objetos da classe Pokemon;
        - vitorias, derrotas, do tipo int.

        Além disso, Treinador herde da classe Pessoa. Ou seja, a classe Treinador
        guanhará os métodos getNome(), setNome(), getIdade() e setIdade() por herança.
    */
    private int vitorias;
    private int derrotas;
    private ArrayList<Pokemon> listaPokemons;


    public Treinador(String nome, int idade) {
        /*
        Construtor da classe
        Recebe os 2 parâmetros indicados acima. Como esses valores são manipulados pela
        classe Pessoa, da qual Treinador deve herdar, utilize a função super() para passar
        esses valores para que o construtor de Pessoa os inicialize.

        Além disso, faça com que:
        - o atributo privado listaPokemons seja um ArrayList vazio;
        - o atributo privado vitorias seja zero;
        - o atributo privado derrotas seja zero.
        */
        super(nome, idade);
        this.listaPokemons = new ArrayList<>();
        this.vitorias = 0;
        this.derrotas = 0;
    }

    public boolean adicionarPokemon(Pokemon pokemon) {
        /*
        Recebe um objeto Pokemon por parâmetro e o adiciona no fim da listaPokemons.

        OBS: o método não deve permitir que um treinador tenha mais de um Pokémon com
        o mesmo nome.

        Deve retornar true se inseriu o Pokémon com sucesso, e false caso contrário (caso
        já exista um Pokémon com o mesmo nome).
        */

        for (Pokemon p : listaPokemons) {
            if (p.getNome().equalsIgnoreCase(pokemon.getNome())) {
                return false;
            }
        }
        this.listaPokemons.add(pokemon);
        return true;
    }

    public ArrayList<Pokemon> getListaPokemons() {
        /*
        Retorna o objeto que representa um ArrayList de Pokémons (atributo privado listaPokemons).
        */
        return listaPokemons;
    }

    public int totalPokemons() {
        /*
        Retorna o número total de Pokémons na listaPokemons.
        */
        return listaPokemons.size();
    }

    public int ataqueTotal() {
        /*
        Retorna a soma dos ataques de todos os Pokémons na listaPokemons.
        */
        int somaAtaques = 0;

        for (Pokemon p : listaPokemons) {
            somaAtaques += p.getAtaque();
        }
        return somaAtaques;
    }

    public int getVitorias() {
        /*
        Retorna o valor do atributo privado vitorias.
        */
        return vitorias;
    }

    public void incVitorias() {
        /*
        Incrementa o atributo vitorias em 1 unidade.
        */
        vitorias += 1;
    }

    public int getDerrotas() {
        /*
        Retorna o valor do atributo privado derrotas.
        */
        return derrotas;
    }

    public void incDerrotas() {
        /*
        Incrementa o atributo derrotas em 1 unidade.
        */
        derrotas += 1;
    }

    public static void main(String[] args) {
        /* Método main para testes
           Não é necessário implementá-lo, mas você pode testar as funcionalidades da classe Treinador aqui.
        */
        Treinador ash = new Treinador("Ash", 10);

        ash.adicionarPokemon(new Pokemon("Pikachu", 100, 50, 30));
        ash.adicionarPokemon(new Pokemon("Bulbasaur", 100, 40, 20));
        ash.adicionarPokemon(new Pokemon("Charmander", 100, 40, 40));

        System.out.println("Total de Pokeemons: " + ash.totalPokemons());

        System.out.println("Ataque total: " + ash.ataqueTotal());

        ash.incVitorias();
        ash.incDerrotas();

        System.out.println("Vitorias: " + ash.getVitorias());
        System.out.println("Derrotas: " + ash.getDerrotas());
    }
}
