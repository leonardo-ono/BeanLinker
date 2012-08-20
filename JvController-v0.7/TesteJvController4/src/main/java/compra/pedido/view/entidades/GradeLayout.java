package compra.pedido.view.entidades;

import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.LayoutManager;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author leo
 */
public class GradeLayout implements LayoutManager {
        
    private int xOffset = 50;
    private int yOffset = 20;
    private int width = 50;
    private int height = 20;

    @Override
    public void addLayoutComponent(String string, Component cmpnt) {
    }

    @Override
    public void removeLayoutComponent(Component cmpnt) {
    }

    @Override
    public Dimension preferredLayoutSize(Container cntnr) {
        Dimension dim = new Dimension(0, 0);

        int linPosMax = 0;
        int colPosMax = 0;
        
        for (Component c : cntnr.getComponents()) {
            if (c instanceof PedgradtamView) {
                PedgradtamView tam = (PedgradtamView) c;
                if (tam.getColPos() > colPosMax) {
                    colPosMax = tam.getColPos();
                }
            }
            if (c instanceof PedgradcorView) {
                PedgradcorView cor = (PedgradcorView) c;
                if (cor.getLinPos() > linPosMax) {
                    linPosMax = cor.getLinPos();
                }
            }
        }

        dim.width = (colPosMax + 2) * width +xOffset;
        dim.height = (linPosMax + 2) * height +yOffset;
        return dim;
    }

    @Override
    public Dimension minimumLayoutSize(Container cntnr) {
        return preferredLayoutSize(cntnr);
    }

    @Override
    public void layoutContainer(Container cntnr) {
        Map<Integer, Integer> tamPosMap = new HashMap<Integer, Integer>();
        Map<Integer, Integer> corPosMap = new HashMap<Integer, Integer>();
        for (Component c : cntnr.getComponents()) {
            if (c instanceof PedgradtamView) {
                PedgradtamView tam = (PedgradtamView) c;
                int x = tam.getColPos() * width + xOffset;
                int y = yOffset;
                tam.setBounds(x, y, width, height);
                tamPosMap.put(tam.getTamCod(), tam.getColPos());
            }
            if (c instanceof PedgradcorView) {
                PedgradcorView cor = (PedgradcorView) c;
                int x = xOffset;
                int y = cor.getLinPos() * height + yOffset;
                cor.setBounds(x - 30, y, width + 30, height);
                corPosMap.put(cor.getCorCod(), cor.getLinPos());
            }
        }
        for (Component c : cntnr.getComponents()) {
            if (c instanceof PedgradqtdeView) {
                PedgradqtdeView qtde = (PedgradqtdeView) c;
                if (tamPosMap.get(qtde.getTamCod()) == null
                        || corPosMap.get(qtde.getCorCod()) == null) {
                    break;
                }
                int colPos = tamPosMap.get(qtde.getTamCod());
                int linPos = corPosMap.get(qtde.getCorCod());
                int x = colPos * width + xOffset;
                int y = linPos * height + yOffset;
                qtde.setBounds(x, y, width+1, height+1);
            }
        }
    }
    
}
