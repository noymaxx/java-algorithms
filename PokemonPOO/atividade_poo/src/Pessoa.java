public class Pessoa {
    /*
        Defina 2 atributos privados:
        - nome, do tipo String;
        - idade, do tipo int.
    */

    private String nome;
    private int idade;


    public Pessoa(String nome, int idade) {
        /*
        Construtor da classe
        Recebe os 2 parâmetros indicados acima e copia seus valores nos respectivos
        atributos privados.

        OBS: Nesta classe não será necessário implementar nenhum tipo de validação.
        */
        this.nome = nome;
        this.idade = Math.abs(idade);
    }

    public String getNome() {
        /*
        Retorna o valor do atributo privado nome.
        */
        return nome;
    }

    public void setNome(String nome) {
        /*
        Atualiza o atributo privado nome.
        */
        this.nome = nome;
    }

    public int getIdade() {
        /*
        Retorna o valor do atributo privado idade.
        */
        return idade;
    }

    public void setIdade(int idade) {
        /*
        Atualiza o atributo privado idade.
        */
        this.idade = Math.abs(idade);
    }

    public static void main(String[] args) {
        /* Método main para testes
           Não é necessário implementá-lo, mas você pode testar as funcionalidades da classe Pessoa aqui.
        */
        Pessoa pessoa1 = new Pessoa("Junin", -20);
        System.out.println(pessoa1.getNome());
        System.out.println(pessoa1.getIdade());

        pessoa1.setNome("Gabigol");
        pessoa1.setIdade(-27);

        System.out.println(pessoa1.getNome());
        System.out.println(pessoa1.getIdade());

        Pessoa pessoa2 = new Pessoa ("T", 244);
        System.out.println(pessoa2.getNome());
        System.out.println(pessoa2.getIdade());
    }
}
