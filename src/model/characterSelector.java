package model;

import controller.Game;
import player.Player;
import views.GameView;

import java.awt.*;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

import static utils.CreateInstanceUtils.createPlayerTypeByName;
import static utils.ImageStateUtils.readImage;
import static utils.renderUtils.drawString;
import static utils.renderUtils.drawImage;

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
        idx = (idx < 0)? idx + img_num : idx % img_num;
        img_idx.set(player, idx);
    }

    public void reset(int round) {
        for (int i = 0; i < player_num; i++)
            img_idx.set(i, i*2);
        this.round = round;
    }

    public void render(Graphics g) {
        int periphery = 100;
        Image image;

        // draw players
        for (int i = 0; i < player_num; i++) {
            int idx = img_idx.get(i);
            image = readImage(new File(dir, class_names.get(idx) + (idx % 2) + ".png"));

            int img_size = 150;
            int x = (GameView.WIDTH + periphery - (i * (periphery*2+img_size)))%GameView.WIDTH;
            int y = (int)(GameView.HEIGHT/2);
            drawImage(g, image, x, y, img_size, (int) (img_size * ((float) image.getHeight(null)) / image.getWidth(null)));

        }

        // print round and some information
        drawString(g, "Round " + Integer.toString(round), Color.BLACK,
                new Font("TimesRoman", Font.PLAIN, 64), (int) (GameView.WIDTH / 2 - 120), periphery);
        drawString(g, "Choose your favorite character :))", Color.darkGray,
                new Font("TimesRoman", Font.PLAIN, 32), periphery*3, periphery*2);

        g.setColor(Color.pink);
        g.fillRoundRect(280, GameView.HEIGHT-135, 480, 50, 20, 20);
        drawString(g, "Press \"Enter\" to start the game!", Color.BLACK,
                new Font("TimesRoman", Font.PLAIN, 32), 300, GameView.HEIGHT-periphery);

        // instructions
        image = readImage(new File("img/Instruction_key.png"));
        int img_w = (int) image.getWidth(null)/3*2;
        int img_h = (int) image.getHeight(null)/3*2;
        g.drawImage(image, GameView.WIDTH-img_w-5, GameView.HEIGHT-img_h-5, img_w, img_h, null);
    }

    public void renderInstructions(Graphics g) {
        Image image = readImage(new File("img/Instruction.png"));
        drawImage(g, image, 0, 0, GameView.WIDTH, GameView.HEIGHT);
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
