/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package swingmappa;

import com.pityubak.swinglibrary.SwingBuilder;

/**
 *
 * @author Pityubak
 */
public class SwingMappa {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        
        SwingBuilder builder = new SwingBuilder(new Class<?>[]{MainWindow.class, MainMenu.class, Settings.class});
   
      

        builder.run(1);

    }

}
