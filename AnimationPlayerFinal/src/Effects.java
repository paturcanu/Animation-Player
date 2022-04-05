/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Philipe Turcanu
 */




public class Effects {
    
    /**
     *
     */
    public class AnimationElement {
        
        /**
         *
         */
        public int frameNumber;

        /**
         *
         */
        public EffectType effect;
    }

    /**
     *
     */
    public int numberEffects = 0;

    /**
     *
     */
    public AnimationElement[] effects = new AnimationElement[100];
    
    /**
     *
     * @param frameNumber
     * @param effect
     */
    public void addEffect(int frameNumber, EffectType effect) { //adds effects to the effects array
        //When an animation is saved, that aniumation has a corresponding frame number. Important for the ObjectEffects classes
        System.out.println("AddEffect ");
        AnimationElement anim = new AnimationElement();
        anim.frameNumber = frameNumber;
        anim.effect = effect;
        effects[numberEffects] = anim;
        numberEffects += 1;
    }
    
    
}
