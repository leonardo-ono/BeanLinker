package br.beanlinker.core;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Property linking uni tests.
 * 
 * @author Leonardo Ono (ono.leo@gmail.com)
 * @since 1.00.00 (05/01/2013 22:28)
 */
public class PropertyLinkingTest {

    // ---------------------------------------------------------------------------
    // Test classes to simulate:
    //
    // Model.name <---> View.textName.text
    // ---------------------------------------------------------------------------
    
    public static class Model {
        
        private String name = "Model.name";

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getObject(Object object) {
            return object.toString();
        }

        public String getObjectTwo(Object object1, Object object2) {
            return object1.toString() + "_" + object2.toString();
        }
        
        @Override
        public String toString() {
            return "Model{" + "name=" + name + '}';
        }

    }
    
    public static class View {

        private TextView textName = new TextView();
        private AView aView = new AView();
        
        public TextView getTextName() {
            return textName;
        }

        public void setTextName(TextView textName) {
            this.textName = textName;
        }

        public AView getaView() {
            return aView;
        }

        public void setaView(AView aView) {
            this.aView = aView;
        }

        @Override
        public String toString() {
            return "View{" + "textName=" + textName + '}';
        }
        
    }

    public static class AView {

        private TextView textName = new TextView();

        public TextView getTextName() {
            return textName;
        }

        public void setTextName(TextView textName) {
            this.textName = textName;
        }

        @Override
        public String toString() {
            return "AView{" + "textName=" + textName + '}';
        }
         
    }
    
    public static class TextView {

        private String text = "Text.text";

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }

        @Override
        public String toString() {
            return "Text{" + "text=" + text + '}';
        }
        
    }
    
    // ---------------------------------------------------------------------------
    
    private BeanLinker beanLinker;
    
    @Before
    public void init() {
        beanLinker = new BeanLinkerImpl();
    }
    
    @Test
    public void testLinkClassPropertyToClassProperty() throws Exception {
        beanLinker.registerClass("Model", "br.beanlinker.core.PropertyLinkingTest$Model");
        beanLinker.registerClass("View", "br.beanlinker.core.PropertyLinkingTest$View");
        beanLinker.createAllNewInstances();
        beanLinker.linkProperty("Model.name", "View.textName.text", "", "", "", "");
        beanLinker.eval("model.name = 'abcde12345'; ");
        beanLinker.update("model", "view");
        assertEquals(beanLinker.eval("model.name"), "abcde12345");
        assertEquals(beanLinker.eval("view.textName.text"), "abcde12345");
    }

    @Test
    public void testLinkClassPropertyToClassMethod() throws Exception {
        beanLinker.registerClass("Model", "br.beanlinker.core.PropertyLinkingTest$Model");
        beanLinker.registerClass("View", "br.beanlinker.core.PropertyLinkingTest$View");
        beanLinker.createAllNewInstances();
        beanLinker.linkProperty("Model.getName()", "View.textName.text", "", "", "", "");
        beanLinker.update("model", "view");
        assertEquals(beanLinker.eval("model.name"), "Model.name");
        assertEquals(beanLinker.eval("view.textName.text"), "Model.name");
    }

    @Test
    public void testLinkClassPropertyToClassMethod2() throws Exception {
        // linker.linkProperty("Telefone.getNumero()", "TelefoneView.textNumero.text", "", "", "", "");
        beanLinker.registerClass("Model", "br.beanlinker.core.PropertyLinkingTest$Model");
        beanLinker.registerClass("View", "br.beanlinker.core.PropertyLinkingTest$View");
        beanLinker.createAllNewInstances();
        beanLinker.eval("model.name = 'aaaa123'; ");
        beanLinker.eval("view.textName.text = 'bbbb456'; ");
        // Class.method() <--- expression 
        // portanto atualizacao so ocorre do model para view (unidirecional)
        beanLinker.linkProperty("Model.getName()", "View.textName.text", "", "", "", "");
        beanLinker.update("view", "model");
        // portanto se atualizar do view para model, sera apenas ignorado 
        // e model.name continua 'aaaa123'
        assertEquals("aaaa123", beanLinker.eval("model.name").toString());
    }    
    
    @Test
    public void testLinkClassPropertyToInstanceMethod() throws Exception {
        beanLinker.registerClass("Model", "br.beanlinker.core.PropertyLinkingTest$Model");
        beanLinker.registerClass("View", "br.beanlinker.core.PropertyLinkingTest$View");
        beanLinker.createAllNewInstances();
        beanLinker.linkProperty("model.getObject('XYZK12345')", "View.textName.text", "", "", "", "");
        beanLinker.update("model", "view");
        assertEquals("XYZK12345", beanLinker.eval("view.textName.text"));
    }    

    @Test
    public void testLinkClassPropertyToInstanceMethod2() throws Exception {
        //        linker.linkProperty("model.getWhiteColor()", "ProdutoView.textNumero.background", "", "", "", "");
        beanLinker.registerClass("Model", "br.beanlinker.core.PropertyLinkingTest$Model");
        beanLinker.registerClass("View", "br.beanlinker.core.PropertyLinkingTest$View");
        beanLinker.createAllNewInstances();
        
        beanLinker.linkProperty("model.getObject('xxx1234xyz')", "View.aView.textName.text", "", "", "", "");
        beanLinker.update("model", "view");
        assertEquals("xxx1234xyz", beanLinker.eval("view.aView.textName.text").toString());
    }    

    @Test
    public void testLinkClassPropertyToInstanceMethodWithClassProperty1() throws Exception {
        beanLinker.registerClass("Model", "br.beanlinker.core.PropertyLinkingTest$Model");
        beanLinker.registerClass("View", "br.beanlinker.core.PropertyLinkingTest$View");
        beanLinker.createAllNewInstances();
        beanLinker.eval("view.textName.text = 'ALTERADO1234'; ");
        beanLinker.linkProperty("model.getObject(View.textName.text)", "View.textName.text", "", "", "", "");
        beanLinker.update("model", "view");
        assertEquals("ALTERADO1234", beanLinker.eval("view.textName.text").toString());
    }    

    @Test
    public void testLinkClassPropertyToInstanceMethodWithClassProperty2() throws Exception {
        beanLinker.registerClass("Model", "br.beanlinker.core.PropertyLinkingTest$Model");
        beanLinker.registerClass("View", "br.beanlinker.core.PropertyLinkingTest$View");
        beanLinker.createAllNewInstances();
        beanLinker.eval("model.name = 'sdlfjlsdfij43543_435ufld'; ");
        beanLinker.linkProperty("model.getObject(Model)", "View.textName.text", "", "", "", "");
        beanLinker.update("model", "view");
        assertEquals("Model{name=sdlfjlsdfij43543_435ufld}", beanLinker.eval("view.textName.text").toString());
    }    

    @Test
    public void testLinkClassPropertyToInstanceMethodWithClassProperty3() throws Exception {
        beanLinker.registerClass("Model", "br.beanlinker.core.PropertyLinkingTest$Model");
        beanLinker.registerClass("View", "br.beanlinker.core.PropertyLinkingTest$View");
        beanLinker.createAllNewInstances();
        beanLinker.eval("model.name = 'aaaa123'; ");
        beanLinker.eval("view.textName.text = 'bbbb456'; ");
        beanLinker.linkProperty("model.getObjectTwo(Model.name, View.textName.text)", "View.textName.text", "", "", "", "");
        beanLinker.update("model", "view");
        assertEquals("aaaa123_bbbb456", beanLinker.eval("view.textName.text").toString());
    }    

    @Test
    public void testLinkClassPropertyToInstanceMethodWithClassProperty4() throws Exception {
        beanLinker.registerClass("Model", "br.beanlinker.core.PropertyLinkingTest$Model");
        beanLinker.registerClass("View", "br.beanlinker.core.PropertyLinkingTest$View");
        beanLinker.createAllNewInstances();
        beanLinker.eval("model.name = 'aaaa123'; ");
        beanLinker.eval("view.textName.text = 'bbbb456'; ");
        // S? inverti a ordem left para right do teste anterior
        beanLinker.linkProperty("View.textName.text", "model.getObjectTwo(Model.name, View.textName.text)", "", "", "", "");
        beanLinker.update("model", "view");
        assertEquals("aaaa123_bbbb456", beanLinker.eval("view.textName.text").toString());
    }    

    @Test
    public void testLinkClassPropertyToExpression() throws Exception {
        beanLinker.registerClass("Model", "br.beanlinker.core.PropertyLinkingTest$Model");
        beanLinker.registerClass("View", "br.beanlinker.core.PropertyLinkingTest$View");
        beanLinker.createAllNewInstances();
        beanLinker.eval("model.name = 'aaaa123'; ");
        beanLinker.eval("view.textName.text = 'bbbb456'; ");
        // Class.property <--- expression 
        // portanto atualizacao so ocorre do view para model (unidirecional)
        beanLinker.linkProperty("Model.name", "View.textName.text+'_expr'", "", "", "", "");
        beanLinker.update("model", "view");
        // se tentar update de model para view, o link e ignorado
        // portanto view.textName.text deve permanecer com valor anterior 'bbbb456'
        assertEquals("bbbb456", beanLinker.eval("view.textName.text").toString());
    }    

    @Test
    public void testLinkClassPropertyToExpression2() throws Exception {
        beanLinker.registerClass("Model", "br.beanlinker.core.PropertyLinkingTest$Model");
        beanLinker.registerClass("View", "br.beanlinker.core.PropertyLinkingTest$View");
        beanLinker.createAllNewInstances();
        beanLinker.eval("model.name = 'aaaa123'; ");
        beanLinker.eval("view.textName.text = 'bbbb456'; ");
        // Class.property <--- expression 
        // portanto atualizacao so ocorre do view para model (unidirecional)
        beanLinker.linkProperty("Model.name", "View.textName.text+'_expr'", "", "", "", "");
        beanLinker.update("view", "model");
        // portanto view.textName.text deve ser atualizado para "bbbb456_expr"
        assertEquals("bbbb456_expr", beanLinker.eval("model.name").toString());
    }    
    
    @Test
    public void testLinkClassAliasToClassAlias() throws Exception {
        beanLinker.registerClass("Model", "br.beanlinker.core.PropertyLinkingTest$Model");
        beanLinker.registerClass("View", "br.beanlinker.core.PropertyLinkingTest$View");
        beanLinker.createAllNewInstances();
        beanLinker.eval("model.name = 'aaaa123'; ");
        beanLinker.eval("view.textName.text = 'bbbb456'; ");
        // Class.property <--- expression 
        // portanto atualizacao so ocorre do view para model (unidirecional)
        beanLinker.linkProperty("Model", "View", "", "", "", "");
        try {
            beanLinker.update("view", "model");
            fail("must throw \"One of the expressions must be a class property !\" exception !");
        } catch (Exception ex) {
            assertEquals("One of the expressions must be a class property !", ex.getMessage());
        }
    }    
    
    // um dos dois precisa ser ClassProperty (example: Model.name)
    // o outro pode ser:
    //         - Class property (example: View.textName.text)
    //         - Class method (example: View.getTextName().getText(); )
    //         - Instance method (example: model.method(); )
    //         - Instance method misturado com instance property misturado com 
    //           Class method com Class property 
    //          (example: model.getObjectTwo(model.name, Model.getName(), Model.name, View.getTextName(), View.textName.text); )
    
    // dado entrada, vai ser feita um eval, mas antes
    
    // precisa extrair todos ClassAlias da expressao

    // s? pode ser encontrado no maximo 2 classAlias (o da esquerda e outro da direita) se tiver mais ? porque a expressao esta errada
    // Se tiver por exemplo linkProperty("model.method(A.x, B.y)", "C.z") ---> tamb?m est? errado pois encontrou 3 ClassAlias ("A", "B" e "C")
    
    // linkProperty("A.x", "B.y") ---> OK (tanto A.x quanto B.y precisam ter getters e setters, sera que tem algum jeito de verificar se a propriedade possui um metodo ?)
    // linkProperty("A.getX()", "B.y") ---> OK (unidirectional linking)
    // linkProperty("model.method(A.x)", "B.y") ---> OK
    // linkProperty("model.method(A.x, B.y)", "B.y") ---> OK
    
    // linkProperty("A", "B") ---> ERRO! (precisa tratar ?)
    // linkProperty("model.method(A.x, B.y)", "B.method(A.x)") ---> ERRO! (precisa tratar ?) um dos dois precisa ser uma propriedade que possui um setter 
    
    // substituir esses ClassAlias pelo seu respectivo varName ativo do source e dest
    
    // TODO updateId generator
    
    // Class alias inside "" must not be replaced
    // example: "Model.links.get('Model').iterator()" "sourceObject.links.get('Model').iterator()" --> correct
    // example: "Model.links.get('Model').iterator()" "sourceObject.links.get('sourceObject').iterator()" --> error
    
    // Bean link (classes pai, detectar tamb?m ?)
    // List<ParentClass>  <----------> List<ViewParentClass> error
    // InheritedClassA
    // InheritedClassB
    // InheritedClassC
    
}
