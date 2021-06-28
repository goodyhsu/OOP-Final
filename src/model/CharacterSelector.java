package model;

import controller.Game;
import controller.Game;
import player.Player;
import views.GameView;

import java.awt.*;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

import static utils.CreateInstanceUtils.createPlayerTypeByName;
import static utils.ImageStateUtils.readImage;
import static utils.LocationUtils.coordinateToLocation;
import static utils.renderUtils.drawString;
import static utils.renderUtils.drawImage;

public class CharacterSelector {
    private final Game game;
    private final String dir = "sprites/characters/";
    private final ArrayList<String> class_names = new ArrayList<>();
    private final ArrayList<Integer> img_idx = new ArrayList<>();
    private ArrayList<Integer> img_num;
    private final int player_num = 2;
    private int round;

    public CharacterSelector(Game game) {
        this.class_names.addAll(Arrays.asList("Dog", "Dog", "Cat", "Cat", "UglyTom"));
        this.img_idx.addAll(Arrays.asList(0, 1));
        this.game = game;
        this.img_num = new ArrayList<>(Arrays.asList(4, 4));
        round = 1;
    }
    public void changeCharacter(int player, int idx_change) {
        int idx = img_idx.get(player) + idx_change;
        idx = (idx < 0)? idx + img_num.get(player) : idx % img_num.get(player);
        img_idx.set(player, idx);
    }

    public void reset(int round) {
        for (int i = 0; i < player_num; i++)
            img_idx.set(i, i*2);
        this.round = round;
    }

    public void unlockTom(int player) {
        img_num.set(player, 5);
    }

    public void render(Graphics g) {
        if (game.getStatus() == Game.Status.selecting) {
            Image image = readImage(new File("img/0.png"));
            g.drawImage(image, 0, 0, GameView.WIDTH, GameView.HEIGHT, null);

            int periphery = 100;
            drawPlayers(g, 100);

            String string;

            // title
            if (round == 1)
                string = "狗狗貓貓";
            else
                string = "狗狗貓貓醜湯姆";
            drawString(g, string, Color.BLACK,
                    new Font("TimesRoman", Font.PLAIN, 64),
                    (int) (GameView.WIDTH/2), periphery, true);

            // round
            string = "Round " + (round);
            drawString(g, string, Color.BLACK,
                    new Font("TimesRoman", Font.PLAIN, 64),
                    (int) (GameView.WIDTH / 2), periphery*2, true);

            // score
            ArrayList<Integer> scores = game.getScores();
            string = scores.get(0) + " : " + scores.get(1);
            drawString(g, string, Color.BLACK,
                    new Font("TimesRoman", Font.PLAIN, 64),
                    (int) (GameView.WIDTH / 2), (int) (GameView.WIDTH/2)-100, true);

//            // choose
//            drawString(g, "Choose your favorite character :))", Color.darkGray,
//                    new Font("TimesRoman", Font.PLAIN, 24), (int) (GameView.WIDTH/2), periphery*3, true);

            // player 1 & player 2
            drawString(g, "Player 1", Color.darkGray,
                    new Font("TimesRoman", Font.PLAIN, 32), 130, 220, false);

            drawString(g, "Player 2", Color.darkGray,
                    new Font("TimesRoman", Font.PLAIN, 32), 820, 220, false);

            // StartTheGame
            drawStartTheGame(g, 100);

            // instructions key
            drawInstructionKey(g, 5);
        }
        else {
            drawInstructions(g);
        }
    }

    public void drawInstructions(Graphics g) {
        Image image = readImage(new File("img/Instruction_2.png"));
        drawImage(g, image, 0, 0, GameView.WIDTH, GameView.HEIGHT);
    }

    public void setPlayers(Game game, World world, ArrayList<SpriteCoordinate> player_coordinates){
        ArrayList<Player> players = new ArrayList<Player>();

        for (int i = 0; i < player_num; i++) {
            Point point = coordinateToLocation(player_coordinates.get(i));
            Player p = createPlayerTypeByName("player." + class_names.get(img_idx.get(i)), point, i, img_idx.get(i)%2);
            players.add(p);
        }
        game.setPlayer(players.get(0), players.get(1));
        world.addSprites(players.get(0), players.get(1));
    }

    private void drawPlayers(Graphics g, int periphery) {
        Image image;
        // draw players
        for (int i = 0; i < player_num; i++) {
            int idx = img_idx.get(i);
            image = readImage(new File(dir, class_names.get(idx) + (idx % 2) + ".png"));

            int img_size = 150;
            int x = (GameView.WIDTH + periphery - (i * (periphery*2+img_size)))%GameView.WIDTH;
            int y = (int)(GameView.HEIGHT/5*3);
            int img_w = img_size;
            int img_h = (int) ((float)img_size * ((float) image.getHeight(null)) / image.getWidth(null));
            drawImage(g, image, x, y, img_w, img_h);
            drawADJL(g, i, x, img_w);
        }
    }

    private void drawADJL(Graphics g, int i, int x, int img_w) {
        Image left = readImage(new File("img", "Left_" + (i) + ".png"));
        Image right = readImage(new File("img", "Right_" + (i) + ".png"));
        int img_size = 70;
        int periphery = 5;
        int y = 430;
        drawImage(g, left, x-img_size-periphery, y, img_size, img_size);
        drawImage(g, right, x + img_w + periphery, y, img_size, img_size);
    }

    private void drawStartTheGame(Graphics g, int periphery) {
        Image image = readImage(new File("img", "StartTheGame.png"));
        drawImage(g, image, (int) (GameView.WIDTH/2) - image.getWidth(null)/3,
                GameView.HEIGHT-periphery, image.getWidth(null)/3*2, image.getHeight(null)/3*2);
    }

    private void drawInstructionKey(Graphics g, int periphery) {
        Image image;
        image = readImage(new File("img/Instruction_key.png"));
        int img_w = (int) image.getWidth(null)/3*2;
        int img_h = (int) image.getHeight(null)/3*2;
        g.drawImage(image, GameView.WIDTH-img_w-periphery, GameView.HEIGHT-img_h-periphery, img_w, img_h, null);
    }
}
