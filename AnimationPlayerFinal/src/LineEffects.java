/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

/**
 *
 * @author Philipe Turcanu
 */
public class LineEffects extends Line{

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
        double StartX = this.getStartX();
        double StartY = this.getStartY();
        double EndX = this.getEndX();
        double EndY = this.getEndY();
        this.setStartX(jumpX + StartX);
        this.setStartY(jumpY + StartY);
        this.setEndX(jumpX + EndX);
        this.setEndY(jumpY + EndY);
    }
    
    /**
     *
     */
    public void ChangeColorEffect() {
        this.setStroke(newColor);
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
