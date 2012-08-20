/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jbean2.main;

import java.io.File;
import java.io.IOException;

/**
 *
 * @author leonardo
 */
public class Main {

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
                if (f.getAbsolutePath().endsWith("jcontroller.xml")) {
                    System.out.println("   achou arquivo jcontroller.xml: " + f.getAbsolutePath());
                }
                /*
                if (f.getAbsolutePath().endsWith(".class")) {
                    String className = "";
                    className = f.getAbsolutePath().replace(originalPath, "");
                    className = className.replace(".class", "");
                    className = className.replace(fileSeparator, ".");
                    System.out.println("  class: " + className);
                    // getAllBeanClasses(className);
                }
                 */
            }
        }
    }    
    
}
