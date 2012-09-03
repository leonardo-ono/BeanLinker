package compra.pedido.modelo;

import compra.pedido.entidades.Pedcab;
import compra.pedido.entidades.Peddet;
import compra.pedido.entidades.Pedgradcor;
import compra.pedido.entidades.Pedgradqtde;
import compra.pedido.entidades.Pedgradtam;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 * Classe ComprasPedidoImpl.
 * 
 * @author Leonardo Ono (ono.leo@gmail.com)
 * @since 1.00.00
 */
public class ComprasPedidoImpl implements ComprasPedido, Serializable {

    private EntityManagerFactory emf;
    private EntityManager em;
    private List<Pedcab> pedidosAbertos;
    private Pedcab pedidoSelecionado = new Pedcab();
    private Peddet itemSelecionado;
    private Pedgradqtde[][][] quantidade;
    private ProdutoDao produtoDao = new ProdutoDao();;
    
    public ComprasPedidoImpl() {
    }

    @Override
    public List<Pedcab> getPedidosAbertos() {
        return pedidosAbertos;
    }

    @Override
    public Pedcab getPedidoSelecionado() {
        return pedidoSelecionado;
    }

    @Override
    public Peddet getItemSelecionado() {
        return itemSelecionado;
    }

    @Override
    public Pedgradqtde[][][] getQuantidades() throws Exception {
        if (pedidosAbertos == null || pedidoSelecionado == null) {
            return new Pedgradqtde[0][0][0];
        }
        //if (quantidade != null) {
        //    return quantidade;
        //}
        em.getTransaction().begin();
        int qtdeDeItens = pedidoSelecionado.getPeddetList().size();
        for (int i = 0; i < pedidoSelecionado.getPeddetList().size(); i++) {
            Peddet itemQtde = pedidoSelecionado.getPeddetList().get(i);
            int qtdeDeCores = itemQtde.getPedgradcorList().size();
            int qtdeDeTamanhos = itemQtde.getPedgradtamList().size();
            if (quantidade == null) {
                quantidade = new Pedgradqtde
                        [qtdeDeItens][qtdeDeTamanhos][qtdeDeCores];
            }
            for (int c = 0; c < qtdeDeCores; c++) {
                Pedgradcor cor = itemQtde.getPedgradcorList().get(c);
                for (int t = 0; t < qtdeDeTamanhos; t++) {
                    Pedgradtam tam = itemQtde.getPedgradtamList().get(t);
                    int corCod = cor.getPedgradcorPK().getCorCod();
                    int tamCod = tam.getPedgradtamPK().getTamCod();
                    for (Pedgradqtde q : itemQtde.getPedgradqtdeList()) {
                        int corCodDoQ = q.getPedgradcor()
                                .getPedgradcorPK().getCorCod();
                        
                        int tamCodDoQ = q.getPedgradtam()
                                .getPedgradtamPK().getTamCod();
                        
                        if (tamCod == tamCodDoQ && corCod == corCodDoQ) {
                            quantidade[i][t][c] = q;
                        }
                    }
                    if (quantidade[i][t][c] == null) {
                        int pedCod = itemQtde.getPeddetPK().getPedCod();
                        String proRef = itemQtde.getPeddetPK().getProRef();
                        quantidade[i][t][c] = new Pedgradqtde(
                                pedCod, proRef, tamCod, corCod);
                        
                        quantidade[i][t][c].setPeddet(itemQtde);
                        quantidade[i][t][c].setPedgradcor(cor);
                        quantidade[i][t][c].setPedgradtam(tam);
                        quantidade[i][t][c].setProQtde(0);
                        itemQtde.getPedgradqtdeList().add(quantidade[i][t][c]);
                        em.persist(quantidade[i][t][c]);
                    }
                }
            }
        }
        em.getTransaction().commit();
        return quantidade;
    }

    private void resetarAtributosPrivadosDoPedido() {
        pedidosAbertos = new ArrayList<Pedcab>();
        pedidoSelecionado = new Pedcab();
        itemSelecionado = null;
        quantidade = null;
    }

    @Override
    public void iniciarPedidoNovo(int forCod, int opeCod, char tipo) 
            throws Exception {
        
        if (pedidoSelecionado != null) {
            throw new Exception("Um pedido esta em aberto !");
        }
        emf = Persistence.createEntityManagerFactory(
                "compra-pedido-entidadesPU");
        
        em = emf.createEntityManager();

        resetarAtributosPrivadosDoPedido();
        Pedcab pedidoNovo = new Pedcab();
        pedidoNovo.setPedDataCad(new Date());
        pedidoNovo.setPedDataEmis(null);
        pedidoNovo.setForCod(forCod);
        pedidoNovo.setOpeCod(opeCod);
        pedidoNovo.setPedSitCod(0);
        pedidoNovo.setPedObs("");
        pedidoNovo.setPedDataPrg(null);
        pedidoNovo.setPedTipo(tipo);
        pedidoNovo.setPedRegTipo("NRM");
        pedidoNovo.setLojaCod(0);
        pedidoNovo.setPedForObs("");
        pedidoNovo.setPedDataEntr(new Date());
        pedidoNovo.setConfFil(false);
        pedidoNovo.setAbertoPor(opeCod);
        pedidoNovo.setPeddetList(new ArrayList<Peddet>());

        // Cria um pedNum livre
        Pedcab p = null;
        int pedNum = 0;
        for (int seq = 1; seq <= 9999; seq++) {
            String seqStr = "0000" + seq;
            seqStr = seqStr.substring(seqStr.length() - 4, seqStr.length());
            String procodStr = forCod + seqStr;
            pedNum = Integer.parseInt(procodStr);
            Query q = em.createQuery(
                    "select p from Pedcab p where p.pedNum=:pedNum");
            
            q.setParameter("pedNum", pedNum);
            q.setMaxResults(1);
            try {
                p = (Pedcab) q.getSingleResult();
            } catch (NoResultException e) {
                p = null;
            }
            if (p == null) {
                break;
            }
            continue;
        }
        if (p != null) {
            throw new Exception(
                    "Nao ha mais sequencia livre para este fornecedor !");
        }
        pedidoNovo.setPedNum(pedNum);

        em.getTransaction().begin();
        em.persist(pedidoNovo);
        em.getTransaction().commit();
        pedidoSelecionado = pedidoNovo;
        pedidosAbertos.add(pedidoNovo);
    }

    @Override
    public void abrirPedido(int pedNum) throws Exception {
        //if (pedidoSelecionado != null) {
        //    throw new Exception("Um pedido esta em aberto !");
        //}
        emf = Persistence.createEntityManagerFactory(
                "compra-pedido-entidadesPU");
        
        em = emf.createEntityManager();

        resetarAtributosPrivadosDoPedido();
        Query q = em.createQuery(
                "select p from Pedcab p where p.pedNum=:pedNum");
        
        q.setParameter("pedNum", pedNum);
        pedidosAbertos = q.getResultList();
        if (pedidosAbertos.isEmpty()) {
            throw new Exception("Pedido invalido !");
        }
        for (Pedcab pedcab : pedidosAbertos) {
            if (pedcab.getLojaCod() == 0) {
                pedidoSelecionado = pedcab;
            }
            
            // Seta os produtos
            for (Peddet peddet : pedcab.getPeddetList()) {
                peddet.setProduto(produtoDao.carregar(peddet.getProCod()));
            }
        }
    }

    @Override
    public void fecharPedido() throws Exception {
        resetarAtributosPrivadosDoPedido();
        if (em != null && em.isOpen()) {
            em.close();
        }
        if (emf != null && emf.isOpen()) {
            emf.close();
        }
    }

    @Override
    public void selecionarLoja(int lojaCod) throws Exception {
        if (pedidosAbertos.isEmpty()) {
            throw new Exception("Nenhum pedido aberto !");
        }
        pedidoSelecionado = null;
        for (Pedcab pedcab : pedidosAbertos) {
            if (pedcab.getLojaCod() == lojaCod) {
                pedidoSelecionado = pedcab;
                break;
            }
        }
        if (pedidoSelecionado != null) {
            return;
        }
        Pedcab pedidoNovo = clonarPedido(pedidosAbertos.get(0));
        pedidoNovo.setLojaCod(lojaCod);
        em.getTransaction().begin();
        List<Peddet> itens = pedidoNovo.getPeddetList();
        pedidoNovo.setPeddetList(null);
        em.persist(pedidoNovo);
        em.flush();
        int pedCod = pedidoNovo.getPedCod();
        for (Peddet pe : itens) {
            pe.getPeddetPK().setPedCod(pedCod);
            for (Pedgradcor cor : pe.getPedgradcorList()) {
                cor.getPedgradcorPK().setPedCod(pedCod);
            }
            for (Pedgradtam tam : pe.getPedgradtamList()) {
                tam.getPedgradtamPK().setPedCod(pedCod);
            }
            List<Pedgradqtde> qtdes = pe.getPedgradqtdeList();
            for (Pedgradqtde qtde : qtdes) {
                qtde.getPedgradqtdePK().setPedCod(pedCod);
                qtde.setProQtde(0);
                qtde.setPedgradtam(null);
                qtde.setPedgradcor(null);
            }
            if (itemSelecionado != null 
                    && itemSelecionado.getProCod() == pe.getProCod()) {
                
                itemSelecionado = pe;
            }
        }
        pedidoNovo.setPeddetList(itens);
        em.flush();
        em.refresh(pedidoNovo);
        em.getTransaction().commit();
        pedidosAbertos.add(pedidoNovo);
        pedidoSelecionado = pedidoNovo;
        quantidade = null;
    }

    @Override
    public void selecionarItem(int proCod) throws Exception {
        verificarHaPedidoSelecionado();
        itemSelecionado = null;
        for (Peddet item : pedidoSelecionado.getPeddetList()) {
            if (item.getProCod() == proCod) {
                itemSelecionado = item;
                break;
            }
        }
        if (itemSelecionado == null) {
            throw new Exception("Item do pedido nao encontrado !");
        }
    }

    @Override
    public void adicionarItem(int proCod, String proRef) throws Exception {
        verificarHaPedidoSelecionado();
        for (Peddet item : pedidoSelecionado.getPeddetList()) {
            if (item.getProCod() == proCod) {
                throw new Exception("Item ja adicionado !");
            }
        }
        em.getTransaction().begin();
        for (Pedcab ped : pedidosAbertos) {
            Peddet itemNovo = new Peddet(ped.getPedCod(), proRef);
            itemNovo.setProCod(proCod);
            itemNovo.setProFator(0);
            itemNovo.setProObs("");
            itemNovo.setProInd(ped.getPeddetList().size() + 1);
            itemNovo.setPedgradtamList(new ArrayList<Pedgradtam>());
            itemNovo.setPedgradcorList(new ArrayList<Pedgradcor>());
            itemNovo.setPedgradqtdeList(new ArrayList<Pedgradqtde>());
            ped.getPeddetList().add(itemNovo);
            em.persist(itemNovo);
            if (pedidoSelecionado.getPedCod() == ped.getPedCod()) {
                itemSelecionado = itemNovo;
                quantidade = null;
            }
        }
        em.getTransaction().commit();
    }

    @Override
    public void removerItem(int proCod) throws Exception {
        verificarHaPedidoSelecionado();
        boolean itemExiste = false;
        for (Peddet item : pedidoSelecionado.getPeddetList()) {
            if (item.getProCod() == proCod) {
                itemExiste = true;
                break;
            }
        }
        if (!itemExiste) {
            throw new Exception("Item nao encontrado !");
        }
        em.getTransaction().begin();
        for (Pedcab ped : pedidosAbertos) {
            for (int i = 0; i < ped.getPeddetList().size(); i++) {
                Peddet item = ped.getPeddetList().get(i);
                item.setProInd(i + 1);
                if (item.getProCod() == proCod) {
                    ped.getPeddetList().remove(item);
                    em.remove(item);
                    i--;
                }
            }
        }
        itemSelecionado = null;
        quantidade = null;
        em.getTransaction().commit();
    }

    @Override
    public void adicionarCor(int corCod) throws Exception {
        verificarHaPedidoSelecionado();
        verificarHaItemSelecionado();
        for (Pedgradcor cor : itemSelecionado.getPedgradcorList()) {
            if (cor.getPedgradcorPK().getCorCod() == corCod) {
                throw new Exception("Cor ja adicionada !");
            }
        }
        em.getTransaction().begin();
        for (Pedcab ped : pedidosAbertos) {
            for (Peddet item : ped.getPeddetList()) {
                if (item.getProCod() != itemSelecionado.getProCod()) {
                    continue;
                }
                int pedCod = ped.getPedCod();
                String proRef = itemSelecionado.getPeddetPK().getProRef();
                Pedgradcor corNovo = new Pedgradcor(pedCod, proRef, corCod);
                corNovo.setPeddet(item);
                corNovo.setLinPos(
                        itemSelecionado.getPedgradcorList().size() + 1);
                
                item.getPedgradcorList().add(corNovo);

                // Cria as quantidades
                for (int q=0; q<item.getPedgradtamList().size(); q++) {
                    Pedgradtam pedgradtam = item.getPedgradtamList().get(q);
                    Pedgradqtde qtde = new Pedgradqtde(pedCod, proRef
                            , pedgradtam.getPedgradtamPK().getTamCod(), corCod);
                    qtde.setPeddet(item);
                    qtde.setPedgradcor(corNovo);
                    qtde.setPedgradtam(pedgradtam);
                    item.getPedgradqtdeList().add(qtde);
                }
                
                em.persist(corNovo);
            }
        }
        em.getTransaction().commit();
        quantidade = null;
    }

    @Override
    public void removerCor(int corCod) throws Exception {
        verificarHaPedidoSelecionado();
        verificarHaItemSelecionado();
        em.getTransaction().begin();
        for (Pedcab ped : pedidosAbertos) {
            for (Peddet item : ped.getPeddetList()) {
                if (item.getProCod() != itemSelecionado.getProCod()) {
                    continue;
                }
                for (int c = 0; c < item.getPedgradcorList().size(); c++) {
                    Pedgradcor cor = item.getPedgradcorList().get(c);
                    cor.setLinPos(c + 1);
                    if (cor.getPedgradcorPK().getCorCod() == corCod) {
                        item.getPedgradcorList().remove(c);
                        em.remove(cor);
                        c--;
                    }
                }
                for (int q = 0; q < item.getPedgradqtdeList().size(); q++) {
                    Pedgradqtde qtde = item.getPedgradqtdeList().get(q);
                    if (qtde.getPedgradqtdePK().getCorCod() == corCod) {
                        item.getPedgradqtdeList().remove(q);
                        em.remove(qtde);
                        q--;
                    }
                }
                break;
            }
        }
        em.getTransaction().commit();
        quantidade = null;
    }

    @Override
    public void adicionarTamanho(int tamCod) throws Exception {
        verificarHaPedidoSelecionado();
        verificarHaItemSelecionado();
        for (Pedgradtam tamanho : itemSelecionado.getPedgradtamList()) {
            if (tamanho.getPedgradtamPK().getTamCod() == tamCod) {
                throw new Exception("Tamanho ja adicionado !");
            }
        }
        em.getTransaction().begin();
        for (Pedcab ped : pedidosAbertos) {
            for (Peddet item : ped.getPeddetList()) {
                if (item.getProCod() != itemSelecionado.getProCod()) {
                    continue;
                }
                int pedCod = ped.getPedCod();
                String proRef = itemSelecionado.getPeddetPK().getProRef();
                Pedgradtam tamNovo = new Pedgradtam(pedCod, proRef, tamCod);
                tamNovo.setPeddet(item);
                tamNovo.setColPos(
                        itemSelecionado.getPedgradtamList().size() + 1);
                
                item.getPedgradtamList().add(tamNovo);
                
                // Cria as quantidades
                for (int q=0; q<item.getPedgradcorList().size(); q++) {
                    Pedgradcor pedgradcor = item.getPedgradcorList().get(q);
                    Pedgradqtde qtde = new Pedgradqtde(pedCod, proRef, tamCod
                            , pedgradcor.getPedgradcorPK().getCorCod());
                    qtde.setPedgradcor(pedgradcor);
                    qtde.setPedgradtam(tamNovo);
                    qtde.setPeddet(item);
                    item.getPedgradqtdeList().add(qtde);
                }
                
                em.persist(tamNovo);
            }
        }
        em.getTransaction().commit();
        quantidade = null;
    }

    @Override
    public void removerTamanho(int tamCod) throws Exception {
        verificarHaPedidoSelecionado();
        verificarHaItemSelecionado();
        em.getTransaction().begin();
        for (Pedcab ped : pedidosAbertos) {
            for (Peddet item : ped.getPeddetList()) {
                if (item.getProCod() != itemSelecionado.getProCod()) {
                    continue;
                }
                for (int t = 0; t < item.getPedgradtamList().size(); t++) {
                    Pedgradtam tam = item.getPedgradtamList().get(t);
                    tam.setColPos(t + 1);
                    if (tam.getPedgradtamPK().getTamCod() == tamCod) {
                        item.getPedgradtamList().remove(t);
                        em.remove(tam);
                        t--;
                    }
                }
                for (int t = 0; t < item.getPedgradqtdeList().size(); t++) {
                    Pedgradqtde qtde = item.getPedgradqtdeList().get(t);
                    if (qtde.getPedgradqtdePK().getTamCod() == tamCod) {
                        item.getPedgradqtdeList().remove(t);
                        em.remove(qtde);
                        t--;
                    }
                }
                break;
            }
        }
        em.getTransaction().commit();
        quantidade = null;
    }

    @Override
    public void atualizarQuantidades() throws Exception {
        verificarHaPedidoSelecionado();
        em.getTransaction().begin();
        // Nao precisa fazer nada
        em.getTransaction().commit();
    }

    @Override
    public void restaurarQuantidades() throws Exception {
        verificarHaPedidoSelecionado();
        verificarHaItemSelecionado();
        em.refresh(itemSelecionado);
    }

    private void verificarHaPedidoSelecionado() throws Exception {
        if (pedidoSelecionado == null) {
            throw new Exception("Nenhuma loja esta selecionada !");
        }
    }

    private void verificarHaItemSelecionado() throws Exception {
        if (itemSelecionado == null) {
            throw new Exception("Nenhum item do pedido esta selecionado !");
        }
    }

    // Precisa estar fetch=FetchType.EAGER em peddetList
    private Pedcab clonarPedido(Pedcab pedidoOriginal) throws Exception {
        Serializable obj = pedidoOriginal;
        ByteArrayOutputStream bout = new ByteArrayOutputStream();
        ObjectOutputStream out = new ObjectOutputStream(bout);
        out.writeObject(obj);
        out.close();
        ByteArrayInputStream bin = new ByteArrayInputStream(bout.toByteArray());
        ObjectInputStream in = new ObjectInputStream(bin);
        Pedcab copiaDoPedido = (Pedcab) in.readObject();
        in.close();
        out.close();
        return copiaDoPedido;
    }
    
}
