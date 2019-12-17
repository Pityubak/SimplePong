/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package swingmappa;


import com.pityubak.swinglibrary.annotations.Button;
import com.pityubak.swinglibrary.annotations.Entity;
import com.pityubak.swinglibrary.annotations.Label;
import com.pityubak.swinglibrary.annotations.Panel;
import com.pityubak.swinglibrary.components.SwingEntity;
import com.pityubak.swinglibrary.components.SwingPanel;
import com.pityubak.swinglibrary.components.SwingComponent;
import com.pityubak.swinglibrary.misc.ColorType;
import com.pityubak.swinglibrary.misc.DrawingType;
import com.pityubak.swinglibrary.misc.PredefinedAction;
import com.pityubak.swinglibrary.service.ColorConvertService;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.border.Border;
import com.pityubak.swinglibrary.annotations.Binding;
import com.pityubak.swinglibrary.annotations.Bindings;
import com.pityubak.swinglibrary.annotations.Get;
import com.pityubak.liberator.data.RuntimeObject;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;

/**
 *
 * @author Pityubak
 */

public class MainMenu implements SwingComponent {

    @Panel(fgColor = ColorType.ORANGE, bgColor = ColorType.BEIGE, draggable = true)

    private SwingPanel popUp;

    @Label(text = "FANTASTIC PONG GAME", width = 300, height = 100, fontSize = 20.0F, fgColor = ColorType.MEDIUM_SLATE_BLUE, x = 250)
    private JLabel title;

    @Button(width = 150, height = 50, text = "Exit", x = 325, y = 350, bgColor = ColorType.CORN_FLOWER_BLUE)

    @Bindings({
        @Binding(actionType = PredefinedAction.EXIT, targetName="frame")})
    private JButton exitBtn;

    @Button(width = 150, height = 50, text = "Back to game",
            toolTipText = "Navigate back to game", x = 325, y = 150, bgColor = ColorType.CORN_FLOWER_BLUE)
    @Bindings({
        @Binding(actionType = PredefinedAction.LEVEL, parent = "frame", targetName = "panel")})
    private JButton backToGame;

    @Button(width = 150, height = 50, text = "Settings",
            toolTipText = "Settings", x = 325, y = 250, bgColor = ColorType.CORN_FLOWER_BLUE)

    @Bindings({
        @Binding(actionType = PredefinedAction.LEVEL, parent = "frame", targetName = "settingPanel")})
    private JButton settings;

    @Entity(draw = DrawingType.DRAW_IMAGE, x = 550, y = 50, height = 200, width = 200, image = "/test1.png")
    private SwingEntity menuIcon;

    @Get("isPause")
    private RuntimeObject isPause;

    @Override
    public void init() {
        
        backToGame.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
               isPause.set(false);
            }
        });
        popUp.add(exitBtn);
        popUp.add(backToGame);
        popUp.add(settings);
      
        popUp.add(title);
        popUp.addEntity(menuIcon);
        Border border = BorderFactory.createLineBorder(ColorConvertService.convert(ColorType.BLACK), 3);
        popUp.setBorder(border);

    }

}
