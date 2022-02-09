package com;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyInput extends KeyAdapter {
    Handler handler;

    public KeyInput(Handler handler){
        this.handler = handler;
    }
    @Override
    public void keyPressed(KeyEvent e){
        int key = e.getKeyCode();

        for (int i = 0; i < handler.objects.size(); i++) {
            GameObject tempObject = handler.objects.get(i);

            if(tempObject.getId() == ID.PLAYER){
                if(key == KeyEvent.VK_W) tempObject.setVelY(-2);
                if(key == KeyEvent.VK_S) tempObject.setVelY(2);
                if(key == KeyEvent.VK_A) tempObject.setVelX(-2);
                if(key == KeyEvent.VK_D) tempObject.setVelX(2);
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();

        for (int i = 0; i < handler.objects.size(); i++) {
            GameObject tempObject = handler.objects.get(i);

            if(tempObject.getId() == ID.PLAYER){
                if(key == KeyEvent.VK_W) tempObject.setVelY(0);
                if(key == KeyEvent.VK_S) tempObject.setVelY(0);
                if(key == KeyEvent.VK_A) tempObject.setVelX(0);
                if(key == KeyEvent.VK_D) tempObject.setVelX(0);
            }
        }
    }
}
