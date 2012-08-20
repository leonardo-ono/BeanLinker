package jbean2.core;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import jbean2.annotation.BindProperty;
import jbean2.annotation.Controller;
import jbean2.annotation.Conversor;
import jbean2.annotation.Model;
import jbean2.annotation.View;

/**
 * Obtem todas as classes do projeto que correspondem a @View, @ControIller e @Model.
 * 
 * @author Leonardo Ono (ono.leo@gmail.com)
 * @since 1.00.00
 */
public class BeanFinder {

    private static Map<String, Class> viewClasses = new HashMap<String, Class>();
    private static Map<String, Class> modelClasses = new HashMap<String, Class>();
    private static Map<String, Class> controllerClasses = new HashMap<String, Class>();
    private static Map<String, Class> conversorClasses = new HashMap<String, Class>();
    
    private static List<Method> methods = new ArrayList<Method>();

    public static Map<String, Class> getControllerClasses() {
        return controllerClasses;
    }

    public static Map<String, Class> getConversorClasses() {
        return conversorClasses;
    }

    public static Map<String, Class> getModelClasses() {
        return modelClasses;
    }

    public static Map<String, Class> getViewClasses() {
        return viewClasses;
    }
    
    public static void main(String[] args) throws IOException {

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
        // createViewModelBinders();
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
                    //System.out.println("  class: " + className);
                    getAllBeanClasses(className);
                }
            }
        }
    }
    
    private static void getAllBeanClasses(String className) {
        try {
            Class beanClass = Class.forName(className);
            if (beanClass.isAnnotationPresent(View.class)) {
                View view = (View) beanClass.getAnnotation(View.class);
                viewClasses.put(view.value(), beanClass);
                //System.out.println(view);
            }
            else if (beanClass.isAnnotationPresent(Controller.class)) {
                Controller controller = (Controller) beanClass.getAnnotation(Controller.class);
                controllerClasses.put(controller.value(), beanClass);
                //System.out.println(controller);
            }
            else if (beanClass.isAnnotationPresent(Model.class)) {
                Model model = (Model) beanClass.getAnnotation(Model.class);
                modelClasses.put(model.value(), beanClass);
                //System.out.println(model);
            }
            else if (beanClass.isAnnotationPresent(Conversor.class)) {
                Conversor conversor = (Conversor) beanClass.getAnnotation(Conversor.class);
                conversorClasses.put(conversor.value(), beanClass);
                //System.out.println(conversor);
            }
        } catch (Exception ex) {
            System.out.println(ex);
            System.exit(-1);
        }
    }
    
    private static void createViewModelBinders() {
        for (String key : viewClasses.keySet()) {
            Class viewClass = viewClasses.get(key);
            System.out.println("key=" + key + " / class=" + viewClass);
            for (Method m : viewClass.getMethods()) {
                if (m.isAnnotationPresent(BindProperty.class)) {
                    BindProperty bind = (BindProperty) m.getAnnotation(BindProperty.class);
                    System.out.println("method=" + m.getName() + " annotation=" + bind);
                    methods.add(m);
                }
            }
        }
    }
    
}
