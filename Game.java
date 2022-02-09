package com;

import java.awt.*;
import java.awt.image.BufferStrategy;
import java.util.Random;

public class Game extends Canvas implements Runnable {

    public static final int WIDTH = 650, HEIGHT = WIDTH / 12 *9;

    public Game(){
        handler = new Handler();
        this.addKeyListener(new KeyInput(handler));
        new Window(HEIGHT, WIDTH, "Bouncing Lights", this);
        Random r = new Random();
        handler.AddObject(new Player(WIDTH/2-32, HEIGHT/2-32, ID.PLAYER));
        for (int i = 0; i < 10; i++) {
            handler.AddObject(new BasicEnemy(r.nextInt(WIDTH), r.nextInt(HEIGHT), ID.BASIC_ENEMY));
        }

    }

    private Thread thread;
    boolean running = false;

    private final Handler handler;

    public synchronized void start(){
        thread = new Thread(this);
        thread.start();
        running = true;
    }
    public synchronized void stop() {
        try {
            thread.join();
            running = false;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public void run() {
        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        long timer = System.currentTimeMillis();
        int frames = 0;
        while (running){
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            while (delta >= 1){
                tick();
                delta--;
            }
            if (running)
                render();
            frames++;

            if(System.currentTimeMillis() - timer > 1000){
                timer += 1000;
                //System.out.println("FPS: " + frames);
                frames = 0;
            }
        }
        stop();
    }
    private void render() {
        BufferStrategy bs = this.getBufferStrategy();
        if(bs == null){
            this.createBufferStrategy(3);
            return;
        }

        Graphics g = bs.getDrawGraphics();
        g.setColor(Color.gray);
        g.fillRect(0,0,WIDTH,HEIGHT);

        handler.render(g);
        g.dispose();
        bs.show();
    }
    public void tick(){
        handler.tick();
    }

    public static void main(String[] args) {
        new Game();
    }
}
