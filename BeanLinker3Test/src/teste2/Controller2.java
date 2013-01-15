package teste2;

import br.beanlinker.core.BeanLinker;
import br.beanlinker.core.BeanLinkerImpl;
import br.beanlinker.updater.UpdateException;
import java.awt.event.ActionEvent;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.AbstractAction;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

/**
 *
 * @author leo
 */
public class Controller2 {

    private static BeanLinker linker = new BeanLinkerImpl();

    public static void main(String[] args) throws Exception {
        //UIManager.put("nimbusBase", Color.BLUE);
        //UIManager.put("nimbusBlueGrey", Color.RED);
        //UIManager.put("control", Color.GREEN);
        try {
            for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception e) {
            // If Nimbus is not available, you can set the GUI to another look and feel.
        }

        linker.registerClass("Controller", "teste2.Controller2");
        linker.registerClass("UpdateModelAction", "teste2.Controller2$UpdateModelAction");
        linker.registerClass("UpdateViewAction", "teste2.Controller2$UpdateViewAction");
        linker.registerClass("AdicionarTelefoneAction", "teste2.Controller2$AdicionarTelefoneAction");
        linker.registerClass("LimparTudoAction", "teste2.Controller2$LimparTudoAction");

        linker.registerClass("Model", "teste2.Model");
        linker.registerClass("View", "teste2.View");
        linker.registerClass("Telefone", "teste2.Telefone");
        linker.registerClass("TelefoneView", "teste2.TelefoneView");
        linker.registerClass("Produto", "teste2.Produto");
        linker.registerClass("ProdutoView", "teste2.ProdutoView");

        // Conversor Validator
        linker.registerClass("IntegerConversor", "teste2.IntegerConversor");
        linker.registerClass("MyValidator", "teste2.MyValidator");
        
        // Cria as instancias
        linker.createAllNewInstances();

        linker.eval("view.buttonUpdateModel.setAction(updateModelAction);");
        linker.eval("view.buttonUpdateView.setAction(updateViewAction);");
        linker.eval("view.buttonAdicionar.setAction(adicionarTelefoneAction);");
        linker.eval("view.buttonLimparTudo.setAction(limparTudoAction);");

        // Produto
        linker.linkProperty("Produto.codigo", "ProdutoView.textCodigo.text", "integerConversor", "myValidator", "ProdutoView.textCodigo.background = model.getYellowColor();", "ProdutoView.textCodigo.background = model.getYellowColor();");
        linker.linkProperty("Produto.descricao", "ProdutoView.textDescricao.text", "", "", "", "");
        linker.linkProperty("Produto.numero", "ProdutoView.textNumero.text", "integerConversor", "", "ProdutoView.textNumero.background = model.getYellowColor();", "");
        linker.linkProperty("model.getWhiteColor()", "ProdutoView.textCodigo.background", "", "", "", "");
        linker.linkProperty("model.getWhiteColor()", "ProdutoView.textNumero.background", "", "", "", "");

        linker.linkProperty("Telefone.ddd", "TelefoneView.textDdd.text", "", "", "", "");

        // Uma forma de deixar o link unidirecional Telefone.getNumero() ---> TelefoneView.textNumero.text
        linker.linkProperty("Telefone.getNumero()", "TelefoneView.textNumero.text", "", "", "", "");

        linker.linkProperty("Telefone.tipo", "TelefoneView.textTipo.text", "", "", "", "");
        linker.linkProperty("model.getNumeroRandomico()", "TelefoneView.textNumeroRandomico.text", "", "", "", "");

        // forma de deixar o link unidirecional Telefone.getProduto() ---> TelefoneView.produtoView
        // linker.linkBean("Telefone.getProduto()", "TelefoneView.produtoView");
        // linker.linkBean("TelefoneView.produtoView", "model.gerarProdutoAleatorio()");
        linker.linkBean("Telefone.produto", "TelefoneView.produtoView");

        // TODO transformar o update expression em um Updater também
        // linker.setOnUpdateExpression("TelefoneView", "destObj.textNumeroRandomico.setText(model.getNumeroRandomico());", "");

        linker.setOnCreateNewInstanceExpression("TelefoneView", "newInstance.buttonExcluir.setAction(controller.createClickAction(associatedInstance)); newInstance.buttonExcluir.enabled = true;");

        //linker.linkBean("Model.telefone", "View.telefoneView");
        //linker.linkBean("View.telefoneView", "Model.telefone");

        // linker.linkProperty("Model.telefone", "View.textModelContent.text", "", "", "");
        /* Exemplo:
        linker.linkCollection("Model.telefones", "View.telefoneListView", "TelefoneView"
        , "'view_' + sourceObj.tipo.toLowerCase()+'_'+sourceObj.ddd+'_'+((Math.random()*99999999) + '').substring(0,6)"
        , "'model_' + sourceObj.tipo.toLowerCase()+'_'+sourceObj.ddd+'_'+((Math.random()*99999999) + '').substring(0,6)");
         */
        linker.linkCollection("Model.telefones", "View.telefoneListView", "TelefoneView");
        linker.eval("view.visible = true;");
    }

    public ClickAction createClickAction(Object associatedObj) {
        return new ClickAction(associatedObj);
    }

    public static class ClickAction extends AbstractAction {

        private Object associatedObj;

        public ClickAction(Object associatedObj) {
            super("Remover");
            this.associatedObj = associatedObj;
        }

        @Override
        public void actionPerformed(ActionEvent ae) {
            try {
                JFrame view = (JFrame) linker.eval("view");
                // JOptionPane.showMessageDialog(view, "Voce clicou em " + associatedObj + " !");
                linker.assign("telefoneExcluir", associatedObj);
                linker.eval("model.excluirTelefone(telefoneExcluir);");
                linker.assign("telefoneExcluir", null);
                linker.update("model", "view");
            } catch (Exception ex) {
                Logger.getLogger(Controller2.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public static class UpdateModelAction extends AbstractAction {

        public UpdateModelAction() {
            super("Update Model");
        }

        @Override
        public void actionPerformed(ActionEvent ae) {
            try {
                linker.update("model", "view", ".*.background.*");
                linker.update("view", "model");
            } catch (UpdateException ex) {
                /*
                String message = "Erro(s) de inconsistência:\n\n";
                for (ConversionException ce : ex.getConversionExceptions()) {
                    Object destObj = ce.getUpdater().getUpdateInfo().evalSourceExpression(NAME);
                    if (destObj instanceof JTextField) {
                        JTextField text = (JTextField) destObj;
                        text.setBackground(Color.yellow);
                    }
                }
                 * 
                 */
                ex.printStackTrace();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage());
            }
        }
    }

    private static Thread updateViewThread = null;

    public static class UpdateViewAction extends AbstractAction {

        public UpdateViewAction() {
            super("Update View");
        }

        @Override
        public synchronized void actionPerformed(ActionEvent ae) {
            try {
                linker.eval("view.buttonUpdateView.enabled = false");
                linker.update("model", "view");
                linker.eval("view.buttonUpdateView.enabled = true");
            } catch (Exception ex) {
                Logger.getLogger(Controller2.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    public static class AdicionarTelefoneAction extends AbstractAction {

        public AdicionarTelefoneAction() {
            super("Adicionar Telefone");
        }

        @Override
        public void actionPerformed(ActionEvent ae) {
            try {
                linker.eval("view.buttonAdicionar.enabled=false");
                Telefone telefone = new Telefone();
                telefone.setDdd("novo");
                telefone.setNumero("novo");
                telefone.setTipo("novo");

                linker.assign("telefoneAdicionar", telefone);
                linker.eval("model.adicionarTelefone(telefoneAdicionar);");
                linker.update("model", "view");
                linker.eval("view.buttonAdicionar.enabled=true");
            } catch (Exception ex) {
                Logger.getLogger(Controller2.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public static class LimparTudoAction extends AbstractAction {

        public LimparTudoAction() {
            super("Limpar Tudo");
        }

        @Override
        public void actionPerformed(ActionEvent ae) {
            try {
                linker.eval("model.limparTelefones()");
                linker.update("model", "view");
            } catch (Exception ex) {
                Logger.getLogger(Controller2.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
