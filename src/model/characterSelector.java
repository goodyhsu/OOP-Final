package model;

import controller.Game;
import controller.GameLoop;
import player.Cat;
import player.Dog;
import player.Player;
import views.GameView;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

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
            File f = new File(dir, player_names.get(idx) + ".png");
            if (f == null) {
                System.out.println("Image not found :((");
            }
            Image image;
            try {
                image = ImageIO.read(f);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            int periphery = 100;
            int img_size = 150;
            int x = (GameView.WIDTH + periphery - (i * (periphery*2+img_size)))%GameView.WIDTH;
            int y = (int)(GameView.HEIGHT/2);
            g.drawImage(image, x, y, img_size, img_size, null);

            g.setColor(Color.BLACK);
            g.setFont(new Font("TimesRoman", Font.PLAIN, 64));
            g.drawString("Round " + Integer.toString(round), (int) (GameView.WIDTH / 2 - 120), periphery);

            g.setColor(Color.darkGray);
            g.setFont(new Font("TimesRoman", Font.PLAIN, 32));
            g.drawString("Choose your favorite character :))", periphery*3, periphery*2);

            g.setColor(Color.pink);
            g.fillRoundRect(280, GameView.HEIGHT-135, 480, 50, 20, 20);
            g.setFont(new Font("TimesRoman", Font.PLAIN, 32));
            g.setColor(Color.BLACK);
            g.drawString("Press \"Enter\" to start the game!", 300, GameView.HEIGHT-periphery);
        }
    }

    public void setPlayers(Game game, World world){
        world.getSprites().clear();
        Player p1, p2;
        if (img_idx.get(0) == 0) {
            p1 = new Dog(new Point(75, 75));
        } else {
            p1 = new Cat(new Point(75, 75));
        }

        if (img_idx.get(1) == 0) {
            p2 = new Dog(new Point(900, 525));
        } else {
            p2 = new Cat(new Point(900, 525));
        }
        game.setPlayer(p1, p2);
        world.addSprites(p1, p2);
    }
}
