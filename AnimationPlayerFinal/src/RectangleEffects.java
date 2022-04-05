/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 *
 * @author Philipe Turcanu
 */
public class RectangleEffects extends Rectangle {

    /**
     *
     */
    public Effects myEffects = new Effects();
    private Color col;

    /**
     *
     */
    public int jumpX,

    /**
     *
     */
    jumpY;

    /**
     *
     */
    public Color newColor;
    
    /**
     *
     */
    public void ShowEffect() {
        this.setFill(col);
    }
    
    /**
     *
     */
    public void HideEffect() {
        col = (Color) this.getFill();
        this.setFill(Color.TRANSPARENT);
    }
    
    /**
     *
     */
    public void JumpEffect() {
        double coordX = this.getX();
        double coordY = this.getY();
        this.setX(jumpX + coordX);
        this.setY(jumpY + coordY);
    }
    
    /**
     *
     */
    public void ChangeColorEffect() {
        this.setFill(newColor);
    }
    
    /**
     *
     * @param frameNumber
     */
    public void ApplyEffect(int frameNumber) {
        for (int i = 0; i < myEffects.numberEffects; i++) {
            if (myEffects.effects[i].frameNumber == frameNumber) {
                switch (myEffects.effects[i].effect) {
                    case HIDE :
                        HideEffect();
                        break;
                    case SHOW :
                        ShowEffect();
                        break;
                    case JUMP :
                        JumpEffect();
                        break;
                    case CHANGECOLOR :
                        ChangeColorEffect();
                        break;
                    default :
                        break;
                }
            }
        }
    }

    
}
