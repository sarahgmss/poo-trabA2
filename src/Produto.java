public class Produto {
    private String nome;
    private double precoCusto;
    private double precoVenda;
    private double lucro;
    private String status;

    public Produto(String nome, double precoCusto, double precoVenda) {
        this.nome = nome;
        this.precoCusto = precoCusto;
        this.precoVenda = precoVenda;
        calcularLucro(); 
    }

    public void calcularLucro() {
        this.lucro = this.precoVenda - this.precoCusto;
        

        if (this.lucro < 5.0) {
            this.status = "Margem Baixa";
        } else {
            this.status = "Margem Boa";
        }
    }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public double getPrecoCusto() { return precoCusto; }
    public void setPrecoCusto(double precoCusto) { this.precoCusto = precoCusto; calcularLucro(); }
    public double getPrecoVenda() { return precoVenda; }
    public void setPrecoVenda(double precoVenda) { this.precoVenda = precoVenda; calcularLucro(); }
    public double getLucro() { return lucro; }
    public String getStatus() { return status; }
}