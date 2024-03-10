import org.junit.Test;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

public class AtividadePokemonTest {

    private boolean checkTodosAtributosPrivados(Field[] atributos) {
        boolean todosSaoPrivados = true;
        int i = 0;
        while (todosSaoPrivados && i < atributos.length) {
            if (!Modifier.isPrivate(atributos[i].getModifiers()))
                todosSaoPrivados = false;
            i++;
        }
        return todosSaoPrivados;
    }

    /* ----------------------------------------------
     Testes da classe Pokemon
     ---------------------------------------------- */

    @Test
    public void testPokemonAtributos() {
        Class<Pokemon> targetClass = Pokemon.class;
        Field[] atributos = targetClass.getDeclaredFields();
        assertEquals("A classe Pokemon deve ter exatamente 6 atributos", 6, atributos.length);
        assertTrue("Todos os atributos de Pokemon devem ser privados", checkTodosAtributosPrivados(atributos));
    }

    @Test
    public void testPokemonConstrutorGetters() {
        Pokemon poke = new Pokemon("Charmander", 100, 50, 30);
        assertEquals("Pokemon: getNome() retornou um valor diferente ao atribuído no construtor", "Charmander", poke.getNome());
        assertEquals("Pokemon: getHp() retornou um valor diferente ao atribuído no construtor", 100, poke.getHpAtual());
        assertEquals("Pokemon: getAtaque() retornou um valor diferente ao atribuído no construtor", 50, poke.getAtaque());
        assertEquals("Pokemon: getDefesa() retornou um valor diferente ao atribuído no construtor", 30, poke.getDefesa());
    }

    @Test
    public void testPokemonInexistente() {
        Pokemon poke = new Pokemon("Bulbasaur", 100, 50, 30);
        assertEquals("Pokemon: getNome() retornou um valor diferente do esperado", "Bulbasaur", poke.getNome());
        assertEquals("Pokemon: getHabilidade() retornou um valor diferente do esperado", "grama", poke.getHabilidade());
        poke.setNome("Squirtle");
        assertEquals("Pokemon: após usar setNome(), getNome() retornou um valor diferente do esperado", "Squirtle", poke.getNome());
        assertEquals("Pokemon: getHabilidade() retornou um valor diferente do esperado", "água", poke.getHabilidade());
        poke.setNome("Caterpie");
        assertEquals("Pokemon: após usar setNome(), getNome() retornou um valor diferente do esperado", "Pikachu", poke.getNome());
        assertEquals("Pokemon: após usar setNome(), getHabilidade() retornou um valor diferente do esperado", "elétrico", poke.getHabilidade());
        poke = new Pokemon("Butterfree", 120, 60, 40);
        assertEquals("Pokemon: getNome() retornou um valor diferente do esperado", "Pikachu", poke.getNome());
        assertEquals("Pokemon: getHabilidade() retornou um valor diferente do esperado", "elétrico", poke.getHabilidade());
    }

    @Test
    public void testPokemonLimites() {
        Pokemon poke = new Pokemon("Pikachu", 501, 101, 101);
        assertEquals("Pokemon: getHpAtual() retornou um valor diferente do esperado", 500, poke.getHpAtual());
        assertEquals("Pokemon: getAtaque() retornou um valor diferente do esperado", 100, poke.getAtaque());
        assertEquals("Pokemon: getDefesa() retornou um valor diferente do esperado", 100, poke.getDefesa());
        poke.setHpOriginal(1);
        assertEquals("Pokemon: após usar setHpOriginal(), getHpAtual() retornou um valor diferente do esperado", 1, poke.getHpAtual());
        poke.setAtaque(10);
        assertEquals("Pokemon: após usar setAtaque(), getAtaque() retornou um valor diferente do esperado", 10, poke.getAtaque());
        poke.setDefesa(10);
        assertEquals("Pokemon: após usar setDefesa(), getDefesa() retornou um valor diferente do esperado", 10, poke.getDefesa());
        poke.setHpOriginal(0);
        assertEquals("Pokemon: após usar setHpOriginal(), getHpAtual() retornou um valor diferente do esperado", 1, poke.getHpAtual());
        poke.setAtaque(9);
        assertEquals("Pokemon: após usar setAtaque(), getAtaque() retornou um valor diferente do esperado", 10, poke.getAtaque());
        poke.setDefesa(9);
        assertEquals("Pokemon: após usar setDefesa(), getDefesa() retornou um valor diferente do esperado", 10, poke.getDefesa());
        poke.setHpOriginal(40);
        for(int i = 0; i < 10; i++)
            poke.decHpAtual(5);
        assertEquals("Pokemon: após chamar decHpAtual() sucessivas vezes, getHpAtual() retornou um valor diferente do esperado", 0, poke.getHpAtual());
    }

    @Test
    public void testPokemonAtaque() {
        Pokemon poke1 = new Pokemon("Pikachu", 100, 50, 40); // elétrico
        Pokemon poke2 = new Pokemon("Charmander", 100, 55, 45); // fogo
        Pokemon poke3 = new Pokemon("Bulbasaur", 100, 40, 20); // grama
        Pokemon poke4 = new Pokemon("Squirtle", 100, 60, 30); // água
        Pokemon poke5 = new Pokemon("Eevee", 100, 45, 35); // normal
        poke1.atacar(poke2); // dano: 50-45 = 5
        assertEquals("Pokemon: após chamar atacar(), getHpAtual() retornou um valor diferente do esperado", 95, poke2.getHpAtual());
        poke2.atacar(poke3); // dano: 55-20=35
        assertEquals("Pokemon: após chamar atacar(), getHpAtual() retornou um valor diferente do esperado", 65, poke3.getHpAtual());
        poke1.atacarComHabilidade(poke2);
        assertEquals("Pokemon: após chamar atacar(), getHpAtual() retornou um valor diferente do esperado", 99, poke1.getHpAtual());
        assertEquals("Pokemon: após chamar atacar(), getHpAtual() retornou um valor diferente do esperado", 85, poke2.getHpAtual());
        poke2.atacarComHabilidade(poke3);
        assertEquals("Pokemon: após chamar atacar(), getHpAtual() retornou um valor diferente do esperado", 84, poke2.getHpAtual());
        assertEquals("Pokemon: após chamar atacar(), getHpAtual() retornou um valor diferente do esperado", 50, poke3.getHpAtual());
        poke3.atacarComHabilidade(poke4);
        assertEquals("Pokemon: após chamar atacar(), getHpAtual() retornou um valor diferente do esperado", 49, poke3.getHpAtual());
        assertEquals("Pokemon: após chamar atacar(), getHpAtual() retornou um valor diferente do esperado", 96, poke4.getHpAtual());
        poke4.atacarComHabilidade(poke5);
        assertEquals("Pokemon: após chamar atacar(), getHpAtual() retornou um valor diferente do esperado", 95, poke4.getHpAtual());
        assertEquals("Pokemon: após chamar atacar(), getHpAtual() retornou um valor diferente do esperado", 95, poke5.getHpAtual());
        poke5.atacarComHabilidade(poke1);
        assertEquals("Pokemon: após chamar atacar(), getHpAtual() retornou um valor diferente do esperado", 94, poke5.getHpAtual());
        assertEquals("Pokemon: após chamar atacar(), getHpAtual() retornou um valor diferente do esperado", 97, poke1.getHpAtual());
    }

    /* ----------------------------------------------
     Testes da classe Pessoa
     ---------------------------------------------- */

    @Test
    public void testPessoaAtributos() {
        Class<Pessoa> targetClass = Pessoa.class;
        Field[] atributos = targetClass.getDeclaredFields();
        assertEquals("A classe Pessoa deve ter exatamente 3 atributos", 2, atributos.length);
        assertTrue("Todos os atributos de Treinador devem ser privados", checkTodosAtributosPrivados(atributos));
    }

    @Test
    public void testPessoaConstrutorGetters() {
        Pessoa pes = new Pessoa("Ash", 20);
        assertEquals("Pessoa: getNome() retornou um valor diferente ao atribuído no construtor", "Ash", pes.getNome());
        assertEquals("Pessoa: getIdade() retornou um valor diferente ao atribuído no construtor", 20, pes.getIdade());
        pes = new Pessoa("Misty", 19);
        assertEquals("Pessoa: getNome() retornou um valor diferente ao atribuído no construtor", "Misty", pes.getNome());
        assertEquals("Pessoa: getIdade() retornou um valor diferente ao atribuído no construtor", 19, pes.getIdade());
    }

    @Test
    public void testPessoaSetters() {
        Pessoa pes = new Pessoa("James", 21);
        pes.setNome("Jessie");
        assertEquals("Pessoa: getNome() retornou um valor diferente do esperado", "Jessie", pes.getNome());
        pes.setIdade(22);
        assertEquals("Pessoa: getIdade() retornou um valor diferente do esperado", 22, pes.getIdade());
    }


    /* ----------------------------------------------
     Testes da classe Treinador
     ---------------------------------------------- */

    @Test
    public void testTreinadorAtributos() {
        Class<Treinador> targetClass = Treinador.class;
        Field[] atributos = targetClass.getDeclaredFields();
        assertEquals("A classe Treinador deve ter exatamente 3 atributos", 3, atributos.length);
        assertTrue("Todos os atributos de Treinador devem ser privados", checkTodosAtributosPrivados(atributos));
    }

    @Test
    public void testTreinadorHeranca() {
        Treinador tre = new Treinador("Ash", 20);
        Pessoa pes = new Pessoa("Misty", 19);
        assertTrue("A classe Treinador deve herdar da classe Pessoa", pes.getClass().isAssignableFrom(tre.getClass()));
    }

    @Test
    public void testTreinadorConstrutorGetters() {
        Treinador tre = new Treinador("Ash Ketchum", 22);
        assertEquals("Treinador: getNome() retornou um valor diferente ao atribuído no construtor", "Ash Ketchum", tre.getNome());
        assertEquals("Treinador: getIdade() retornou um valor diferente ao atribuído no construtor", 22, tre.getIdade());
        assertEquals("Treinador: getVitorias() retornou um valor diferente ao atribuído no construtor", 0, tre.getVitorias());
        assertEquals("Treinador: getDerrotas() retornou um valor diferente ao atribuído no construtor", 0, tre.getDerrotas());
    }

    @Test
    public void testTreinadorVitoriasDerrotas() {
        Treinador tre = new Treinador("Ash Ketchum", 22);
        for(int i = 0; i < 17; i++)
            tre.incVitorias();
        for(int i = 0; i < 18; i++)
            tre.incDerrotas();
        assertEquals("Treinador: getVitorias() retornou um valor diferente do esperado", 17, tre.getVitorias());
        assertEquals("Treinador: getDerrotas() retornou um valor diferente do esperado", 18, tre.getDerrotas());
    }

    @Test
    public void testTreinadorListaPokemons() {
        Treinador tre = new Treinador("Ash Ketchum", 22);
        Pokemon poke1 = new Pokemon("Jigglypuff", 100, 50, 40);
        Pokemon poke2 = new Pokemon("Meowth", 100, 55, 45);
        Pokemon poke3 = new Pokemon("Jigglypuff", 100, 40, 20);
        Pokemon poke4 = new Pokemon("Vulpix", 100, 60, 30);
        assertEquals("Treinador: totalPokemons() retornou um valor diferente do esperado", 0, tre.totalPokemons());
        assertEquals("Treinador: ataqueTotal() retornou um valor diferente do esperado", 0, tre.ataqueTotal());
        boolean resultado = tre.adicionarPokemon(poke1);
        assertTrue("Treinador: adicionarPokemon() retornou um valor diferente do esperado", resultado);
        assertEquals("Treinador: totalPokemons() retornou um valor diferente do esperado", 1, tre.totalPokemons());
        assertEquals("Treinador: ataqueTotal() retornou um valor diferente do esperado", 50, tre.ataqueTotal());
        resultado = tre.adicionarPokemon(poke2);
        assertTrue("Treinador: adicionarPokemon() retornou um valor diferente do esperado", resultado);
        assertEquals("Treinador: totalPokemons() retornou um valor diferente do esperado", 2, tre.totalPokemons());
        assertEquals("Treinador: ataqueTotal() retornou um valor diferente do esperado", 105, tre.ataqueTotal());
        resultado = tre.adicionarPokemon(poke3);
        assertFalse("Treinador: adicionarPokemon() retornou um valor diferente do esperado", resultado);
        assertEquals("Treinador: totalPokemons() retornou um valor diferente do esperado", 2, tre.totalPokemons());
        assertEquals("Treinador: ataqueTotal() retornou um valor diferente do esperado", 105, tre.ataqueTotal());
        resultado = tre.adicionarPokemon(poke4);
        assertTrue("Treinador: adicionarPokemon() retornou um valor diferente do esperado", resultado);
        assertEquals("Treinador: totalPokemons() retornou um valor diferente do esperado", 3, tre.totalPokemons());
        assertEquals("Treinador: ataqueTotal() retornou um valor diferente do esperado", 165, tre.ataqueTotal());
        ArrayList<Pokemon> listaPokemons = tre.getListaPokemons();
        assertEquals("Treinador: getListaPokemons() retornou uma lista diferente da esperada", listaPokemons.get(0), poke1);
        assertEquals("Treinador: getListaPokemons() retornou uma lista diferente da esperada", listaPokemons.get(1), poke2);
        assertEquals("Treinador: getListaPokemons() retornou uma lista diferente da esperada", listaPokemons.get(2), poke4);

    }

    /* ----------------------------------------------
     Testes da classe Batalha
     ---------------------------------------------- */

    @Test
    public void testBatalhaAtributos() {
        Class<Batalha> targetClass = Batalha.class;
        Field[] atributos = targetClass.getDeclaredFields();
        assertEquals("A classe Batalha deve ter exatamente 4 atributos", 4, atributos.length);
        assertTrue("Todos os atributos de Batalha devem ser privados", checkTodosAtributosPrivados(atributos));
    }

    @Test
    public void testBatalhaConstrutorGetters() {
        Treinador tre1 = new Treinador("Ash", 20);
        Treinador tre2 = new Treinador("Misty", 19);
        Batalha bat = new Batalha(tre1, tre2);
        assertEquals("Batalha: getTreinador1() retornou um objeto diferente ao atribuído no construtor", tre1, bat.getTreinador1());
        assertEquals("Batalha: getTreinador1() retornou um objeto diferente ao atribuído no construtor", tre2, bat.getTreinador2());
        assertEquals("Batalha: getVencedor() retornou um valor diferente ao atribuído no construtor", 0, bat.getVencedor());
    }

    @Test
    public void testBatalhaBatalhar() {
        Treinador tre1 = new Treinador("Ash", 20);
        Treinador tre2 = new Treinador("Misty", 19);
        Pokemon poke1 = new Pokemon("Pikachu", 100, 50, 35);
        Pokemon poke2 = new Pokemon("Charmander", 100, 55, 25);
        tre1.adicionarPokemon(poke1);
        tre1.adicionarPokemon(poke2);
        Pokemon poke3 = new Pokemon("Bulbasaur", 100, 40, 20);
        Pokemon poke4 = new Pokemon("Squirtle", 100, 45, 30);
        Pokemon poke5 = new Pokemon("Eevee", 100, 55, 50);
        tre2.adicionarPokemon(poke3);
        tre2.adicionarPokemon(poke4);
        tre2.adicionarPokemon(poke5);
        // batalha 1
        Batalha bat1 = new Batalha(tre1, tre2);
        assertEquals("Batalha: getVencedor() retornou um valor diferente ao atribuído no construtor", 0, bat1.getVencedor());
        int iter = bat1.batalhar(0, 0, 1, 3);
        assertEquals("Batalha: batalhar() retornou um valor diferente do esperado", 4, iter);
        assertEquals("Batalha: getVencedor() retornou um valor diferente do esperado", 1, bat1.getVencedor());
        iter = bat1.batalhar(0, 0, 1, 3);
        assertEquals("Batalha: batalhar() retornou um valor diferente do esperado ao ser chamado pela segunda vez", 0, iter);
        assertEquals("Batalha: o treinador1 possui um número diferente de vitórias", 1, tre1.getVitorias());
        assertEquals("Batalha: o treinador1 possui um número diferente de derrotas", 0, tre1.getDerrotas());
        assertEquals("Batalha: o treinador2 possui um número diferente de vitórias", 0, tre2.getVitorias());
        assertEquals("Batalha: o treinador2 possui um número diferente de derrotas", 1, tre2.getDerrotas());
        // batalha 2
        Batalha bat2 = new Batalha(tre1, tre2);
        assertEquals("Batalha: getVencedor() retornou um valor diferente ao atribuído no construtor", 0, bat2.getVencedor());
        iter = bat2.batalhar(1, 2, 2, 2);
        assertEquals("Batalha: batalhar() retornou um valor diferente do esperado", 7, iter);
        assertEquals("Batalha: getVencedor() retornou um valor diferente do esperado", 2, bat2.getVencedor());
        iter = bat2.batalhar(1, 2, 2, 2);
        assertEquals("Batalha: batalhar() retornou um valor diferente do esperado ao ser chamado pela segunda vez", 0, iter);
        assertEquals("Batalha: o treinador1 possui um número diferente de vitórias", 1, tre1.getVitorias());
        assertEquals("Batalha: o treinador1 possui um número diferente de derrotas", 1, tre1.getDerrotas());
        assertEquals("Batalha: o treinador2 possui um número diferente de vitórias", 1, tre2.getVitorias());
        assertEquals("Batalha: o treinador2 possui um número diferente de derrotas", 1, tre2.getDerrotas());
    }

    @Test
    public void testBatalhaBatalharIndicesInvalidos() {
        Treinador tre1 = new Treinador("Ash", 20);
        Treinador tre2 = new Treinador("Misty", 19);
        Pokemon poke1 = new Pokemon("Pikachu", 100, 50, 35);
        Pokemon poke2 = new Pokemon("Charmander", 100, 55, 48);
        tre1.adicionarPokemon(poke1);
        tre1.adicionarPokemon(poke2);
        Pokemon poke3 = new Pokemon("Bulbasaur", 100, 40, 20);
        Pokemon poke4 = new Pokemon("Squirtle", 100, 45, 30);
        Pokemon poke5 = new Pokemon("Eevee", 100, 55, 50);
        tre2.adicionarPokemon(poke3);
        tre2.adicionarPokemon(poke4);
        tre2.adicionarPokemon(poke5);
        Batalha bat1 = new Batalha(tre1, tre2);
        int iter = bat1.batalhar(-1, 0, 1, 3);
        assertEquals("Batalha: batalhar() retornou um valor diferente do esperado", 0, iter);
        iter = bat1.batalhar(2, 0, 1, 3);
        assertEquals("Batalha: batalhar() retornou um valor diferente do esperado", 0, iter);
        iter = bat1.batalhar(0, -1, 1, 3);
        assertEquals("Batalha: batalhar() retornou um valor diferente do esperado", 0, iter);
        iter = bat1.batalhar(0, 3, 1, 3);
        assertEquals("Batalha: batalhar() retornou um valor diferente do esperado", 0, iter);
    }

}
