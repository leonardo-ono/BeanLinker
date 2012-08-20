package jbean2.model;

import java.math.BigDecimal;
import jbean2.annotation.Model;

/**
 *
 * @author leonardo
 */
@Model("ProdutoModel")
public class Produto {

    private int codigo = 123;
    private String descricao = "DESCRICAO DO PRODUTO";
    private BigDecimal preco = BigDecimal.TEN;
    private Endereco endereco = new Endereco();

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public void setPreco(BigDecimal preco) {
        this.preco = preco;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    @Override
    public String toString() {
        return "Produto{" + "codigo=" + codigo + ", descricao=" + descricao + ", preco=" + preco + ", endereco=" + endereco + '}';
    }

    
}
