package Entidades;


public class Produto {
    private String foto;
    private String nome;
    private String valor;
    private String descricao;
    private String arquivo;

    /**
     * @return the foto
     */
    public String getFoto() {
        return foto;
    }

    /**
     * @return the nome
     */
    public String getNome() {
        return nome;
    }

    /**
     * @return the valor
     */
    public String getValor() {
        return valor;
    }

    /**
     * @return the descricao
     */
    public String getDescricao() {
        return descricao;
    }

    /**
     * @param foto the foto to set
     */
    public void setFoto(String foto) {
        this.foto = foto;
    }

    /**
     * @param nome the nome to set
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * @param valor the valor to set
     */
    public void setValor(String valor) {
        this.valor = valor;
    }

    /**
     * @param descricao the descricao to set
     */
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    /**
     * @return the pasta
     */
    public String getArquivo() {
        return arquivo;
    }

    /**
     * @param pasta the pasta to set
     */
    public void setArquivo(String arquivo) {
        this.arquivo = arquivo;
    }

}
