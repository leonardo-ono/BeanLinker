package classFinder;

import actionImpl.ActionHandler;
import java.io.File;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.Enumeration;
import java.util.logging.Level;
import java.util.logging.Logger;
import jbean.BeanELAcess;
import jbean.annotation.Controller;
import jbean.annotation.Conversor;
import jbean.annotation.Model;
import jbean.annotation.StartApp;
import jbean.annotation.View;

/**
 * Obtem todos as classes do projeto e adiciona automaticamente
 * os objetos marcados com @View, @Controller e @Model no
 * contexto do JController.
 * 
 * @author leonardo
 */
public class ClassFinder {

    private static Method startMethod;
    private static Object startBean;

    public static void invokeStartMethod() {
        if (startMethod != null) {
            try {
                startMethod.invoke(startBean);
            } catch (Exception ex) {
                Logger.getLogger(ClassFinder.class.getName()).log(Level.SEVERE, null, ex);
                System.exit(-1);
            }
        }
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        ClassLoader classLoader = ClassFinder.class.getClassLoader();
        Enumeration<URL> urls = classLoader.getResources("*");
        //for (Object key : System.getProperties().keySet()) {
        //    System.out.println(key + " = " + System.getProperties().get(key));
        //}
        String classPath = System.getProperties().get("java.class.path").toString();
        String pathSeparator = System.getProperties().get("path.separator").toString();
        String fileSeparator = System.getProperties().getProperty("file.separator").toString();
        
        String paths[] = classPath.split(pathSeparator);
        for (String path : paths) {
            path = path.trim();
            if (!path.endsWith(fileSeparator)) {
                path = path + fileSeparator;
            }
            //System.out.println("   path: " + path);
            listAllClassesOfThisProject(path, path);
        }
        
        // Verifica as acoes
        for (Object bean : BeanELAcess.getInstance().getBeans().values()) {
            ActionHandler.createAction(bean);
        }
    }
    
    private static void listAllClassesOfThisProject(String path, String originalPath) {
        String fileSeparator = System.getProperties().getProperty("file.separator").toString();

        File filePath = new File(path);
        for (File f : filePath.listFiles()) {
            if (f.isDirectory()) {
                //System.out.println("    dir: " + f.getAbsolutePath());
                listAllClassesOfThisProject(f.getAbsolutePath(), originalPath);
            }
            else {
                //System.out.println("   file: " + f.getAbsolutePath());
                if (f.getAbsolutePath().endsWith(".class")) {
                    String className = "";
                    className = f.getAbsolutePath().replace(originalPath, "");
                    className = className.replace(".class", "");
                    className = className.replace(fileSeparator, ".");
                    System.out.println("  class: " + className);
                    addBeanToContext(className);
                }
            }
        }
    }
    
    private synchronized static void addBeanToContext(String className) {
        try {
            Class c = Class.forName(className);
            
            
            for (Annotation a : c.getAnnotations()) {
                System.out.println("annotation: " + a);
            }
            Object sb = null;
            
            if (c.isAnnotationPresent(View.class)) {
                View view = (View) c.getAnnotation(View.class);
                sb = c.newInstance();
                BeanELAcess.getInstance().addBean(view.value(), sb);
                System.out.println("adicionando instancia de " + className + " no contexto como " + view.value() + " ...");
            }
            if (c.isAnnotationPresent(Controller.class)) {
                Controller controller = (Controller) c.getAnnotation(Controller.class);
                sb = c.newInstance();
                BeanELAcess.getInstance().addBean(controller.value(), sb);
                System.out.println("adicionando instancia de " + className + " no contexto como " + controller.value() + " ...");
            }
            if (c.isAnnotationPresent(Model.class)) {
                Model model = (Model) c.getAnnotation(Model.class);
                sb = c.newInstance();
                BeanELAcess.getInstance().addBean(model.value(), sb);
                System.out.println("adicionando instancia de " + className + " no contexto como " + model.value() + " ...");
            }
            if (c.isAnnotationPresent(Conversor.class)) {
                Conversor conversor = (Conversor) c.getAnnotation(Conversor.class);
                sb = c.newInstance();
                BeanELAcess.getInstance().addBean(conversor.value(), sb);
                System.out.println("adicionando instancia de " + className + " no contexto como " + conversor.value() + " ...");
            }
            
            for (Method method : c.getMethods()) {
                if (method.isAnnotationPresent(StartApp.class)) {
                    startMethod = method;
                    startBean = sb;
                    break;
                }
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }

    }
}
