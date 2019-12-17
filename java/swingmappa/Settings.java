/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package swingmappa;

import com.pityubak.swinglibrary.annotations.Button;
import com.pityubak.swinglibrary.annotations.ComboBox;
import com.pityubak.swinglibrary.annotations.Label;
import com.pityubak.swinglibrary.annotations.Panel;
import com.pityubak.swinglibrary.components.SwingPanel;
import com.pityubak.swinglibrary.components.SwingComponent;
import com.pityubak.swinglibrary.misc.ColorType;
import com.pityubak.swinglibrary.misc.PredefinedAction;
import com.pityubak.swinglibrary.service.ColorConvertService;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import com.pityubak.swinglibrary.annotations.Binding;
import com.pityubak.swinglibrary.annotations.Bindings;
import com.pityubak.swinglibrary.annotations.Fill;
import com.pityubak.swinglibrary.misc.FillType;
import java.util.List;
import javax.swing.DefaultComboBoxModel;

/**
 *
 * @author Pityubak
 */
public class Settings implements SwingComponent {

    @Panel(fgColor = ColorType.PERU, bgColor = ColorType.BEIGE, draggable = true)
    private SwingPanel settingPanel;

    @Button(width = 150, height = 50, text = "Cancel", x = 150, y = 540,bgColor = ColorType.CORN_FLOWER_BLUE)

    @Bindings(
            @Binding(actionType = PredefinedAction.LEVEL, parent = "frame", targetName = "popUp"))
    private JButton backBtn;

    @Button(width = 150, height = 50, text = "OK", x = 500, y = 540,bgColor = ColorType.CORN_FLOWER_BLUE)
    @Bindings({
        @Binding(actionType = PredefinedAction.KEY_REBINDER, targetName = "panel", parent = "settingPanel", filter = JComboBox.class),
        @Binding(actionType = PredefinedAction.LEVEL, parent = "frame", targetName = "popUp",
                actionName = "pause")
    })
    private JButton okBtn;

    @Label(text = "Pause:", width = 200, height = 50, x = 150, y = 100, fontSize = 15, horizontal = SwingConstants.LEFT)
    private JLabel pause;

    @ComboBox(width = 150, height = 25, x = 500, y = 100)
    private JComboBox showPanel;
    @Label(text = "Paddle1 Up Key:", width = 200, height = 50, x = 150, y = 175, fontSize = 15, horizontal = SwingConstants.LEFT)
    private JLabel fPlayerUp;
    @ComboBox(width = 150, height = 25, x = 500, y = 175)
    private JComboBox simpleXUp;
    @Label(text = "Paddle1 Down Key:", width = 200, height = 50, x = 150, y = 250, fontSize = 15, horizontal = SwingConstants.LEFT)
    private JLabel fPlayerDown;
    @ComboBox(width = 150, height = 25, x = 500, y = 250)
    private JComboBox simpleXDown;

    @Label(text = "Paddle2 Up Key:", width = 200, height = 50, x = 150, y = 325, fontSize = 15, horizontal = SwingConstants.LEFT)
    private JLabel sPlayerUp;
    @ComboBox(width = 150, height = 25, x = 500, y = 325)
    private JComboBox simpleYUp;
    @Label(text = "Paddle2 Down Key:", width = 200, height = 50, x = 150, y = 400, fontSize = 15, horizontal = SwingConstants.LEFT)
    private JLabel sPlayerDown;
    @ComboBox(width = 150, height = 25, x = 500, y = 400)
    private JComboBox simpleYDown;

    @Fill(FillType.KEY)
    private List<String> allKey;

    @Override
    public void init() {

        this.simpleXDown.setModel(new DefaultComboBoxModel(this.allKey.toArray()));
        this.simpleYDown.setModel(new DefaultComboBoxModel(this.allKey.toArray()));
        this.simpleXUp.setModel(new DefaultComboBoxModel(this.allKey.toArray()));
        this.simpleYUp.setModel(new DefaultComboBoxModel(this.allKey.toArray()));
        this.showPanel.setModel(new DefaultComboBoxModel(this.allKey.toArray()));

        settingPanel.add(showPanel);
        settingPanel.add(backBtn);
        settingPanel.add(okBtn);
        settingPanel.add(fPlayerUp);
        settingPanel.add(fPlayerDown);
        settingPanel.add(sPlayerUp);
        settingPanel.add(sPlayerDown);
        settingPanel.add(pause);
        settingPanel.add(this.simpleXUp);
        settingPanel.add(this.simpleXDown);
        settingPanel.add(this.simpleYDown);
        settingPanel.add(this.simpleYUp);
        Border border = BorderFactory.createLineBorder(ColorConvertService.convert(ColorType.BLACK), 3);
        settingPanel.setBorder(border);
    }
}
