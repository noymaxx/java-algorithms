import java.util.Arrays;
import java.util.List;

public class Pokemon {
    /*
        Defina 6 atributos privados:
        - nome, habilidade, do tipo String;
        - hpOriginal, hpAtual, ataque, defesa, todos do tipo int.
    */
    private String nome;
    private String habilidade;
    private int hpOriginal;
    private int hpAtual;
    private int ataque;
    private int defesa;

    public Pokemon(String nome, int hp, int ataque, int defesa) {
        /*
        Construtor da classe
        Recebe os 4 parâmetros indicados acima, e copia seus valores nos respectivos atributos privados
        usando os métodos: setNome(), setHpOriginal(), setAtaque(), setDefesa().

        É importante usar os setters, porque cada um desses valores precisa ser validado por eles.
        */

        setNome(nome);
        setHpOriginal(hp);
        setAtaque(ataque);
        setDefesa(defesa);
    }

    public static Pokemon gerarPokemonAleatorio() {
        /* Método estático já implementado que retorna um pokemon aleatório.
        Pode ser usado para os seus testes. Fique à vontade para modificá-lo de acordo
         com suas necessidades.

         OBS: este método não é utilizado em nenhum outro lugar, e não será testado durante a avaliação da atividade.
         */
        List<Pokemon> listaPokemons = Arrays.asList(
                new Pokemon("Bulbasaur", 100, 49, 49),
                new Pokemon("Charmander", 100, 65, 58),
                new Pokemon("Pikachu", 100, 55, 40),
                new Pokemon("Squirtle", 100, 59, 63)
        );
        int indiceAleatorio = (int) (Math.random() * listaPokemons.size());
        return listaPokemons.get(indiceAleatorio);
    }

    public void setNome(String nome) {
        /*
        Recebe o nome do Pokémon por parâmetro e verifica se ele encontra-se na seguinte lista:
        "Pikachu", "Charmander", "Bulbasaur", "Squirtle", "Jigglypuff", "Meowth", "Psyduck", "Eevee", "Vulpix", "Growlithe"

        Se o nome existe na lista, o valor do parâmetro deve ser copiado no atributo privado nome. Além disso,
        atualize o valor do atributo privado habilidade, de acordo com a habilidade deste Pokémon. As respectivas habilidades
        dos Pokémons da lista acima são as seguintes:
        "elétrico", "fogo", "grama", "água", "normal", "normal", "água", "normal", "fogo", "fogo"

        Caso o nome não exista na lista, o atributo privado nome deve ser atualizado para "Pikachu", e o atributo privado
        habilidade deve ser atualizado para "elétrico".
        */

        String[] pokemons = {
                "Pikachu", "Charmander", "Bulbasaur", "Squirtle",
                "Jigglypuff", "Meowth", "Psyduck", "Eevee", "Vulpix", "Growlithe"
        };
        String[] habilidades = {"elétrico", "fogo", "grama", "água", "normal",
                "normal", "água", "normal", "fogo", "fogo"
        };

        boolean pokemonEncontrado = false;
        for (int i = 0; i < pokemons.length; i++) {
            if (pokemons[i].equalsIgnoreCase(nome)) {
               this.nome = nome;
               this.habilidade = habilidades[i];
               pokemonEncontrado = true;
               break;
            }
        }

        if(!pokemonEncontrado) {
            this.nome = "Pikachu";
            this.habilidade = "elétrico";
        }
    }

    public String getNome() {
        /*
        Retorna o valor do atributo privado nome.
        */
        return nome;
    }

    public String getHabilidade() {
        /*
        Retorna o valor do atributo privado habilidade.
        */
        return habilidade;
    }

    public int getHpAtual() {
        /*
        Retorna o valor do atributo privado hpAtual.
        */
        return hpAtual;
    }

    public void setHpOriginal(int hp) {
        /*
        Atualiza o atributo privado hpOriginal. Se for menor que 1, o hpOriginal deve ser 1.
        Já se for maior que 500, o hpOriginal deve ser 500.
        Ou seja, o hpOriginal sempre é um valor entre 1 e 500.
        Sempre que hpOriginal for atualizado, faça com que o atributo hpAtual seja igual a hpOriginal.
        */
        if (hp < 1) {
            hp = 1;
        }
        else if (hp > 500) {
            hp = 500;
        }
        this.hpOriginal = hp;
        this.hpAtual = this.hpOriginal;
    }

    public void decHpAtual(int qtd) {
        /*
        Decrementa o atributo privado hpAtual de acordo com o valor de qtd.
        Jamais pode ser menor que zero.
        */
        this.hpAtual -= qtd;

        if (this.hpAtual < 0) {
            this.hpAtual = 0;
        }
    }

    public void restaurarHp() {
        /*
        Copia o valor do atributo hpOriginal no atributo hpAtual.
        */
        this.hpAtual = this.hpOriginal;

    }

    public int getAtaque() {
        /*
        Retorna o valor do atributo privado ataque.
        */
        return ataque;
    }

    public void setAtaque(int ataque) {
        /*
        Atualiza o atributo privado ataque. Se for menor que 10, o ataque deve ser 10.
        Já se for maior que 100, o ataque deve ser 100.
        Ou seja, o ataque sempre é um valor entre 10 e 100.
        */
        if (ataque < 10) {
            this.ataque = 10;
        }
        else if (ataque > 100) {
            this.ataque = 100;
        } else {
            this.ataque = ataque;
        }
    }

    public int getDefesa() {
        /*
        Retorna o valor do atributo privado defesa.
        */
        return defesa;
    }

    public void setDefesa(int defesa) {
        /*
        Atualiza o atributo privado defesa. Se for menor que 10, a defesa deve ser 10.
        Já se for maior que 100, a defesa deve ser 100.
        Ou seja, a defesa sempre é um valor entre 10 e 100.
        */
        if (defesa < 10) {
            this.defesa = 10;
        }
        else if (defesa > 100) {
            this.defesa = 100;
        } else {
            this.defesa = defesa;
        }
    }

    public void atacar(Pokemon outroPokemon) {
        /*
        Ataca outro Pokémon, causando um dano que é o ataque do Pokémon atacante menos a defesa do seu inimigo.

         Se o dano for maior que zero, decrementa dano hit points do seu inimigo. Caso contrário, não faz nada.
        */
        int dano = this.getAtaque() - outroPokemon.getDefesa();

        if (dano > 0) {
            outroPokemon.decHpAtual(dano);
        }
    }

    public void atacarComHabilidade(Pokemon outroPokemon) {
        /*
         Ataca outro Pokémon com uma habilidade específica, causando um dano pré-definido com base na habilidade.
         Se o Pokémon é de água, causa um dano de 5 no inimigo;
         Se o Pokémon é de elétrico, causa um dano de 10 no inimigo;
         Se o Pokémon é de fogo, causa um dano de 15 no inimigo;
         Se o Pokémon é de grama, causa um dano de 4 no inimigo;
         Se o Pokémon é possui outra habilidade (normal, por exemplo), causa um dano padrão de 2 no inimigo.
         Após calcular o dano, decrementa este valor dos hit points do Pokémon inimigo.

         O Pokémon que está atacando (this) também sobre um dano fixo de 1, devido ao seu esforço com a habilidade especial.
         Ou seja, decrementa 1 hit point do Pokémon atacante.
        */
        int ataqueEspecial;
        if (this.habilidade.equals("água")) {
            ataqueEspecial = 5;
        }
        else if (this.habilidade.equals("elétrico")) {
            ataqueEspecial = 10;
        }
        else if (this.habilidade.equals("fogo")) {
            ataqueEspecial = 15;
        }
        else if (this.habilidade.equals("grama")) {
            ataqueEspecial = 4;
        }
        else {
            ataqueEspecial = 2;
        }

        outroPokemon.decHpAtual(ataqueEspecial);

        this.decHpAtual(1);
    }

    public static void main(String[] args) {
        /* Método main para testes
           Não é necessário implementá-lo, mas você pode testar as funcionalidades da classe Pokemon aqui.
        */
        Pokemon pikachu = new Pokemon("Junin", 100, 50, 30);
        Pokemon squirtle = new Pokemon("Squirtle", 100, 40, 50);

        pikachu.atacar(squirtle);

        System.out.println(squirtle.getNome() + " HP squirtle apos ataque: " + squirtle.getHpAtual());

        squirtle.atacarComHabilidade(pikachu);

        System.out.println(pikachu.getNome() + " HP pikachu apos ataque especial: " + pikachu.getHpAtual());
        System.out.println(squirtle.getNome() + " HP squirtle apos ataque especial: " + squirtle.getHpAtual());
    }

}
