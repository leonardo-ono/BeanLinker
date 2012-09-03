package compra.pedido.modelo;

import compra.pedido.entidades.Produtos;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Classe ProdutoDao.
 * 
 * @author Leonardo Ono (ono.leo@gmail.com)
 * @since 1.0 (24/08/2012 13:26)
 */
public class ProdutoDao {

    private EntityManagerFactory emf;
    private EntityManager em;

    public ProdutoDao() {
        emf = Persistence.createEntityManagerFactory(
                "cadastro-yacimaPU");
        
        em = emf.createEntityManager();
    }

    public Produtos carregar(Integer id) throws Exception {
        return em.find(Produtos.class, id);
    }
    
}
