package model;

import controller.Game;
import player.Cat;
import player.Dog;
import player.Tom;
import player.Player;
import views.GameView;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;

import static utils.ImageIO.getImage;
import static utils.CreateInstance.createPlayerTypeByName;

public class characterSelector {
    private String dir = "sprites/characters/";
    private ArrayList<String> class_names = new ArrayList<String>();
    private ArrayList<Integer> img_idx = new ArrayList<Integer>();
    private final int img_num = 5;
    private final int player_num = 2;
    private int round;
    public characterSelector() {
        this.class_names.addAll(Arrays.asList("Dog", "Dog", "Cat", "Cat", "Tom"));
        this.img_idx.addAll(Arrays.asList(0, 1));
    }
    public void changeCharacter(int player, int idx_change) {
        Integer idx = img_idx.get(player) + idx_change;
        if (idx < 0) {
            idx += img_num;
        }
        idx %= img_num;
        img_idx.set(player, idx);
    }

    public void reset(int round) {
        for (int i = 0; i < player_num; i++)
            img_idx.set(i, i*2);
        this.round = round;
    }

    public void render(Graphics g) {
        for (int i = 0; i < player_num; i++) {
            int idx = img_idx.get(i);
//            System.out.printf("name = %s\n", class_names.get(idx) + Integer.toString(idx%2) + ".png");
            Image image = getImage(dir, class_names.get(idx) + Integer.toString(idx%2) + ".png");

            int periphery = 100;
            int img_size = 150;
            int x = (GameView.WIDTH + periphery - (i * (periphery*2+img_size)))%GameView.WIDTH;
            int y = (int)(GameView.HEIGHT/2);
//            g.drawImage(image, x, y, img_size, img_size, null);
            drawImage(g, image, x, y, img_size, (int) (img_size * ((float) image.getHeight(null)) / image.getWidth(null)));

            drawString(g, "Round " + Integer.toString(round), Color.BLACK, 64, (int) (GameView.WIDTH / 2 - 120), periphery);
            drawString(g, "Choose your favorite character :))", Color.darkGray, 32, periphery*3, periphery*2);

            g.setColor(Color.pink);
            g.fillRoundRect(280, GameView.HEIGHT-135, 480, 50, 20, 20);
            drawString(g, "Press \"Enter\" to start the game!", Color.BLACK, 32, 300, GameView.HEIGHT-periphery);

            // instructions
            image = getImage("img", "Instruction_key.png");
            int img_w = (int) image.getWidth(null)/3*2;
            int img_h = (int) image.getHeight(null)/3*2;
            g.drawImage(image, GameView.WIDTH-img_w-5, GameView.HEIGHT-img_h-5, img_w, img_h, null);
        }
    }

    public void renderInstructions(Graphics g) {
        Image image = getImage("img", "Instruction.png");
        drawImage(g, image, 0, 0, GameView.WIDTH, GameView.HEIGHT);
    }

    private void drawImage(Graphics g, Image image, int x, int y, int width, int height) {
        float scale = 1;
        int img_w = (int) image.getWidth(null);
        scale = (float) width / img_w;

        int img_h = (int) image.getHeight(null);
        scale = Math.min(scale, (float) height / img_h);

        img_w = (int) (img_w * scale);
        img_h = (int) (img_h * scale);

        if (y != 0) {
            y = (int) (y - ((float)img_h - width) / 2);
        }
        g.drawImage(image, x + (int) ((width-img_w) / 2), y + (int) ((height-img_h)/2), img_w, img_h, null);
    }

    private void drawString(Graphics g, String string, Color color, int font_size, int x, int y) {
        g.setColor(color);
        g.setFont(new Font("TimesRoman", Font.PLAIN, font_size));
        g.drawString(string, x, y);
    }



    public void setPlayers(Game game, World world){
        world.getSprites().clear();
        ArrayList<Player> players = new ArrayList<Player>();

        for (int i = 0; i < player_num; i++) {
            int x = 50 + 900 * i;
            int y = 50 + 550 * i;
            Player p = createPlayerTypeByName("player." + class_names.get(img_idx.get(i)), new Point(x, y), i, img_idx.get(i)%2);
            players.add(p);
        }
        game.setPlayer(players.get(0), players.get(1));
        world.addSprites(players.get(0), players.get(1));
    }
}
