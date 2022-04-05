/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */




import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

/**
 *
 * @author Philipe Turcanu
 */
public class CircleEffects extends Circle {
    
    private Color col;

    /**
     *
     */
    public Effects myEffects = new Effects();

    /**
     *
     */
    public double jumpX,

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
    public void ShowEffect() { //For all of the effects, if it has important information, it is saved in a global variable, if not, the original is in local variables
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
        double centerX = this.getCenterX();
        double centerY = this.getCenterY();
        this.setCenterX(jumpX + centerX);
        this.setCenterY(jumpY + centerY);
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
        for (int i = 0; i < myEffects.numberEffects; i++) { //This loop runs through the effects array for the object. Only if the frame number matches the current
            //frame in the animation will it play the effect
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
