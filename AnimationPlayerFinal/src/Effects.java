/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */



public class Effects {
    
    public class AnimationElement {
        
        public int frameNumber;
        public EffectType effect;
    }
    public int numberEffects = 0;
    public AnimationElement[] effects = new AnimationElement[100];
    
    public void addEffect(int frameNumber, EffectType effect) {
        System.out.println("AddEffect ");
        AnimationElement anim = new AnimationElement();
        anim.frameNumber = frameNumber;
        anim.effect = effect;
        effects[numberEffects] = anim;
        numberEffects += 1;
    }
    
    
}
