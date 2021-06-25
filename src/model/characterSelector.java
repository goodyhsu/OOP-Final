package model;

import controller.Game;
import player.Cat;
import player.Dog;
import player.Tom;
import player.Player;
import views.GameView;

import java.awt.*;
import java.util.ArrayList;

import static utils.ImageIO.getImage;

public class characterSelector {
    private String dir = "sprites/characters/";
    private ArrayList<String> player_names = new ArrayList<String>();
    private ArrayList<Integer> img_idx = new ArrayList<Integer>();
    private final int player_num = 2;
    private int round;
    public characterSelector() {
        this.player_names.add("Dog");
        this.player_names.add("Cat");
        this.img_idx.add(0);
        this.img_idx.add(1);
    }
    public void changeCharacter(int player, int idx_change) {
        Integer idx = img_idx.get(player) + idx_change;
        if (idx < 0) {
            idx += player_num;
        }
        idx %= player_num;
        img_idx.set(player, idx);
    }

    public void reset(int round) {
        for (int i = 0; i < player_num; i++)
            img_idx.set(i, i);
        this.round = round;
    }

    public void render(Graphics g) {
        for (int i = 0; i < player_num; i++) {
            int idx = img_idx.get(i);
            Image image = getImage(dir, player_names.get(idx) + ".png");

            int periphery = 100;
            int img_size = 150;
            int x = (GameView.WIDTH + periphery - (i * (periphery*2+img_size)))%GameView.WIDTH;
            int y = (int)(GameView.HEIGHT/2);
            g.drawImage(image, x, y, img_size, img_size, null);

            drawString(g, "Round " + Integer.toString(round), Color.BLACK, 64, (int) (GameView.WIDTH / 2 - 120), periphery);
            drawString(g, "Choose your favorite character :))", Color.darkGray, 32, periphery*3, periphery*2);

            g.setColor(Color.pink);
            g.fillRoundRect(280, GameView.HEIGHT-135, 480, 50, 20, 20);
            drawString(g, "Press \"Enter\" to start the game!", Color.BLACK, 32, 300, GameView.HEIGHT-periphery);

            // instructions
            image = getImage("img", "Instruction_key.png");
            int img_w = (int) image.getWidth(null)/3*2;
            int img_h = (int) image.getHeight(null)/3*2;
            g.drawImage(image, GameView.WIDTH-img_w-20, GameView.HEIGHT-img_h-20, img_w, img_h, null);
        }
    }

    public void renderInstructions(Graphics g) {
        Image image = getImage("img", "Instruction.png");
        float scale = 1;
        int img_w = (int) image.getWidth(null);
        scale = (float) GameView.WIDTH / img_w;
        int img_h = (int) image.getHeight(null);
        scale = Math.min(scale, (float) GameView.HEIGHT / img_h);
        img_w = (int) (img_w * scale);
        img_h = (int) (img_h * scale);
        g.drawImage(image, (int) ((GameView.WIDTH-img_w) / 2), (int) ((GameView.HEIGHT-img_h)/2), img_w, img_h, null);
    }

    private void drawString(Graphics g, String string, Color color, int font_size, int x, int y) {
        g.setColor(color);
        g.setFont(new Font("TimesRoman", Font.PLAIN, font_size));
        g.drawString(string, x, y);
    }



    public void setPlayers(Game game, World world){
        world.getSprites().clear();
        Player p1, p2;
        if (img_idx.get(0) == 0) {
            p1 = new Dog(new Point(50, 50), 0);
        } else {
            p1 = new Cat(new Point(50, 50), 0);
        }

        if (img_idx.get(1) == 0) {
            p2 = new Dog(new Point(950, 600), 1);
        } else {
            p2 = new Cat(new Point(950, 600), 1);
        }
        game.setPlayer(p1, p2);
        world.addSprites(p1, p2);
    }
}
