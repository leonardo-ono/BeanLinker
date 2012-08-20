package autocompletar;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class _Autocompleter {

  private final static JPopupMenu textPopupMenu
      = new JPopupMenu("MENU") {

    {
      add(new JMenuItem("item 1"));
      add(new JMenuItem("item 2"));
      setFocusable(false);
    }

  };

  private final static KeyListener textInputListener
      = new KeyAdapter() {

    @Override
    public void keyTyped(KeyEvent e) {
      Point p = textInput.getCaret().getMagicCaretPosition();
      if (textPopupMenu.isVisible()) {
        SwingUtilities.convertPointToScreen(p, textInput);
        textPopupMenu.setLocation(p.x, p.y + 20);
      } else {
        textPopupMenu.show(textInput, p.x, p.y + 20);
      }
    }

  };

  private final static JTextArea textInput
      = new JTextArea("type something") {

    {
      addKeyListener(textInputListener);
      setCaretPosition(getText().length());
    }

  };

  private final static JFrame f = new JFrame("TEST") {

    {
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

      add(textInput);

      setSize(400, 60);
      setLocationRelativeTo(null);
      setVisible(true);
    }

  };

  public static void main(String[] args)
      throws Exception {
        // YES, IT'S EMPTY !!!
        // It'll start anyway because of static initializers
  }

}
