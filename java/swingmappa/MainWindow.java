/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package swingmappa;

import com.pityubak.liberator.data.RuntimeObject;
import com.pityubak.swinglibrary.annotations.Action;
import com.pityubak.swinglibrary.annotations.Actions;
import com.pityubak.swinglibrary.annotations.Binding;
import com.pityubak.swinglibrary.annotations.Bindings;
import com.pityubak.swinglibrary.annotations.Entity;
import com.pityubak.swinglibrary.annotations.Label;
import com.pityubak.swinglibrary.annotations.Observeable;
import com.pityubak.swinglibrary.annotations.Panel;

import com.pityubak.swinglibrary.annotations.Window;
import com.pityubak.swinglibrary.components.RunnableComponent;
import com.pityubak.swinglibrary.components.SwingEntity;
import com.pityubak.swinglibrary.components.SwingFrame;
import com.pityubak.swinglibrary.components.SwingPanel;
import com.pityubak.swinglibrary.misc.ColorType;
import com.pityubak.swinglibrary.misc.DrawingType;
import com.pityubak.swinglibrary.misc.PredefinedAction;
import com.pityubak.swinglibrary.service.ColorConvertService;
import java.awt.event.KeyEvent;
import javax.swing.BorderFactory;

import javax.swing.JLabel;
import javax.swing.border.Border;

/**
 *
 * @author Pityubak
 */
public class MainWindow implements RunnableComponent {

    @Window(title = "Fuck yeah", defaultCloseOperation = javax.swing.WindowConstants.EXIT_ON_CLOSE,
            undecorated = true, width = 800, height = 640)
    private SwingFrame frame;
    @Bindings({
        @Binding(actionType = PredefinedAction.UP_MOVE, speed = 20, actionName = "moveUpY"),
        @Binding(actionType = PredefinedAction.DOWN_MOVE, speed = 20, actionName = "moveDownY")})
    @Entity(x = 10, y = 350, color = ColorType.DARK_SLATE_GRAY, width = 20, height = 200, draw = DrawingType.FILL_RECT)

    private SwingEntity paddleY;
    @Entity(x = 730, y = 100, color = ColorType.DARK_SLATE_GRAY, width = 20, height = 200,
            xDir = 1, yDir = 1, draw = DrawingType.FILL_RECT)
    @Bindings({
        @Binding(actionType = PredefinedAction.UP_MOVE, speed = 20, actionName = "moveUpX"),
        @Binding(actionType = PredefinedAction.DOWN_MOVE, speed = 20, actionName = "moveDownX")})
    private SwingEntity paddleX;

    @Panel(fgColor = ColorType.STEEL_BLUE, bgColor = ColorType.STEEL_BLUE, draggable = true)
    @Bindings({
        @Binding(actionType = PredefinedAction.LEVEL, parent = "frame", targetName = "popUp",
                actionName = "pause", targetVar = "isPause")})
    @Actions({
        @Action(key = KeyEvent.VK_W, actionName = "simpleXUp", action = "moveUpY"),
        @Action(key = KeyEvent.VK_S, actionName = "simpleXDown", action = "moveDownY"),
        @Action(key = KeyEvent.VK_UP, actionName = "simpleYUp", action = "moveUpX"),
        @Action(key = KeyEvent.VK_DOWN, actionName = "simpleYDown", action = "moveDownX"),
        @Action(key = KeyEvent.VK_ESCAPE, actionName = "showPanel", action = "pause")
    }
    )
    private SwingPanel panel;

    @Label(width = 50, height = 20, text = "0",
            x = 200, y = 10, fgColor = ColorType.BLACK, fontSize = 23)
    private JLabel label1;
    @Label(width = 50, height = 20, text = "0",
            x = 550, y = 10, fgColor = ColorType.BLACK, fontSize = 23)
    private JLabel label2;

    @Entity(width = 8, height = 600, x = 396, y = 20,
            color = ColorType.WHITE, draw = DrawingType.FILL_RECT)
    private SwingEntity centerLine;

    @Entity(x = 350, y = 350, color = ColorType.ORANGE_RED, width = 40, height = 40,
            draw = DrawingType.FILL_OVAL, xDir = 1, yDir = 1)

    private SwingEntity ball;

    @Observeable("isPause")
    private final RuntimeObject isPause = new RuntimeObject(true);

    private boolean collisionX() {
        return ball.getBounds().intersects(paddleX.getBounds());
    }

    private boolean collisionY() {
        return ball.getBounds().intersects(paddleY.getBounds());
    }

    @Override
    public void run() {

        if (!(boolean) this.isPause.get()) {
            move();
            panel.repaint();

        }

    }

    private void move() {

        if (ball.getX() + ball.getxDir() < 0) {
            ball.setxDir(1);
            int score = Integer.parseInt(label2.getText()) + 1;
            label2.setText(String.valueOf(score));

        } else if (ball.getX() + ball.getxDir() > frame.getWidth() - 60) {
            ball.setxDir(-1);
            int score = Integer.parseInt(label1.getText()) + 1;
            label1.setText(String.valueOf(score));

        }
        if (ball.getY() + ball.getyDir() < 0) {
            ball.setyDir(1);
        } else if (ball.getY() + ball.getyDir() > frame.getHeight() - 60) {
            ball.setyDir(-1);
        }

        if (collisionX()) {
            ball.setxDir(-1);
            ball.setX(paddleX.getX() - ball.getHeight());

        }
        if (collisionY()) {
            ball.setxDir(1);
            ball.setX(paddleY.getX() - paddleY.getWidth() + ball.getHeight());

        }

        ball.setX(ball.getX() + ball.getxDir());
        ball.setY(ball.getY() + ball.getyDir());

    }

    @Override
    public SwingFrame getFrame() {

        return frame;
    }

    @Override
    public void init() {

        panel.addEntity(centerLine);
        panel.addEntity(paddleX);
        panel.addEntity(paddleY);
        panel.addEntity(ball);
        panel.add(label1);
        panel.add(label2);

        Border border = BorderFactory.createLineBorder(ColorConvertService.convert(ColorType.BLACK), 3);
        panel.setBorder(border);

        frame.addSwingPanel("popUp");
    }

}
