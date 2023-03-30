package ui;

import model.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static model.GameModel.STARTING_POKEBALLS;

public class GameGraphics extends JFrame implements ActionListener {
    public static final int FRAME_WIDTH = 1000;
    public static final int FRAME_HEIGHT = 700;
    public static final Font NORMAL_TEXT_FONT = new Font("Times New Roman", Font.PLAIN, 15);
    public static final BorderLayout LAYOUT = new BorderLayout();
    public static final String JSON_STORE = "./data/pokemasters.json";

    private final GameModel gameModel;
    private BattleModel battleModel;
    private Player player;

    // EFFECTS: creates new GameGraphics
    public GameGraphics() {
        super("PokéMasters");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        ImageIcon icon = new ImageIcon("./data/pokeMastersIcon.png");
        setIconImage(icon.getImage());

        player = new Player();
        gameModel = new GameModel(player, JSON_STORE);
    }

    // EFFECTS: creates the first frame when the game runs
    public void welcomeFrame() {
        setSize(FRAME_WIDTH, FRAME_HEIGHT);
        setLayout(null);
        centerOnScreen();

        add(welcomeTitle());
        add(newGameButton());
        add(loadGameButton());

        setVisible(true);
    }

    // EFFECTS: creates the frame for the prologue of the game
    private void newGameFrame() {
        setPreferredSize(new Dimension(FRAME_WIDTH, FRAME_HEIGHT));
        JTextArea prologue = prologue();
        add(prologue, BorderLayout.CENTER);
        pack();
        prologue.requestFocusInWindow();
    }


    // EFFECTS: creates frame where user can choose their starter pokemon
    private void chooseStarterFrame() {
        setPreferredSize(new Dimension(FRAME_WIDTH, FRAME_HEIGHT));
        starterMessagePanel();
        pack();
    }


    // EFFECTS: creates the final frame before running the main game
    private void introEndFrame(String pokemonName) {
        gameModel.readyPlayer();
        setPreferredSize(new Dimension(FRAME_WIDTH, FRAME_HEIGHT));

        JTextArea textArea = introEnd(pokemonName);
        add(textArea, BorderLayout.CENTER);
        pack();
        textArea.requestFocusInWindow();
    }

    // EFFECTS: creates a JLabel for the title for the welcomeFrame
    private JLabel welcomeTitle() {
        JLabel title = new JLabel("Welcome to PokéMasters: The Unreal World of Pokémon");
        title.setFont(new Font("Times New Roman", Font.BOLD, 30));
        title.setBounds(0, 50, FRAME_WIDTH, 50);
        title.setHorizontalAlignment(JLabel.CENTER);
        return title;
    }

    // EFFECTS: returns a JButton to start a new game
    private JButton newGameButton() {
        JButton newGameButton = new JButton("New Game");
        newGameButton.setFont(NORMAL_TEXT_FONT);
        newGameButton.setFocusable(false);
        newGameButton.setBounds(400, 300, 200, 50);
        newGameButton.addActionListener(this);
        newGameButton.setActionCommand("new game");
        return newGameButton;
    }

    // EFFECTS: returns a JButton to load previous game and run the main game
    private JButton loadGameButton() {
        JButton loadGameButton = new JButton("Load Previous Game");
        loadGameButton.setFont(NORMAL_TEXT_FONT);
        loadGameButton.setFocusable(false);
        loadGameButton.setBounds(400, 400, 200, 50);
        loadGameButton.addActionListener(this);
        loadGameButton.setActionCommand("load game");
        return loadGameButton;
    }

    // EFFECTS: returns a JTextArea with prologue of the game printed
    private JTextArea prologue() {
        JTextArea prologue = new JTextArea(prologueString());
        prologue.setFont(NORMAL_TEXT_FONT);
        prologue.setEditable(false);
        prologue.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                if (e.getKeyChar() == '\n') {
                    getContentPane().removeAll();
                    repaint();
                    setLayout(LAYOUT);
                    chooseStarterFrame();
                }
            }

            @Override
            public void keyPressed(KeyEvent e) {}

            @Override
            public void keyReleased(KeyEvent e) {}
        });

        return prologue;
    }

    // EFFECTS: returns the prologue string
    private String prologueString() {
        return "Welcome to the world of Pokémon!\n"
                + "So what exactly are Pokémon, you ask?\n"
                + "They are the strange creatures that live in the forests and lakes.\n"
                + "Pokémon are creatures of all shapes and sizes who live in the wild or alongside their human partners"
                + " (called \"Trainers\").\n"
                + "During their adventures, Pokémon grow and become more experienced and even, on occasion, evolve into"
                + " stronger Pokémon.\n"
                + "Hundreds of known Pokémon inhabit the Pokémon universe, with untold numbers waiting to be"
                + " discovered!\n\nEnough of the introduction. Now, let's dive into that "
                + "world of Pokémon and show everyone that you are the PokéMaster! [Press Enter to continue]";
    }

    // EFFECTS: returns a starterPokemonButton with the given name
    private JButton makeStarterPokemonButton(String name) {
        List<String> names = new ArrayList<>(Arrays.asList("bulbasaur", "charmander", "squirtle"));
        List<Color> colours = new ArrayList<>(Arrays.asList(Color.GREEN, Color.RED, Color.BLUE));

        JButton pokemonButton = new JButton(new ImageIcon("./data/PokemonImages/" + name + ".png"));
        pokemonButton.setText(name.substring(0, 1).toUpperCase() + name.substring(1));
        pokemonButton.setVerticalTextPosition(JButton.BOTTOM);
        pokemonButton.setHorizontalTextPosition(JButton.CENTER);
        pokemonButton.setBackground(colours.get(names.indexOf(name)));
        pokemonButton.setFocusable(false);
        pokemonButton.addActionListener(this);
        pokemonButton.setActionCommand("choose " + name);

        return pokemonButton;
    }

    // EFFECTS: creates a starter pokemon message JPanel
    private void starterMessagePanel() {
        JPanel starterPanel = new JPanel(new GridLayout(1, 3));
        starterPanel.add(makeStarterPokemonButton("bulbasaur"));
        starterPanel.add(makeStarterPokemonButton("charmander"));
        starterPanel.add(makeStarterPokemonButton("squirtle"));

        JLabel optionMessageLabel = new JLabel("In order to become a Pokémon Trainer, you need to first have a "
                + "starter Pokémon.\nThere are three starter Pokémon:\n");
        optionMessageLabel.setHorizontalAlignment(SwingConstants.CENTER);
        optionMessageLabel.setFont(NORMAL_TEXT_FONT);
        JLabel starterQuestionLabel = new JLabel("Which of these do you want to be your partner in this quest to "
                + "become the PokéMaster?");
        starterQuestionLabel.setHorizontalAlignment(SwingConstants.CENTER);
        starterQuestionLabel.setFont(NORMAL_TEXT_FONT);

        add(optionMessageLabel, BorderLayout.NORTH);
        add(starterPanel, BorderLayout.CENTER);
        add(starterQuestionLabel, BorderLayout.SOUTH);
    }

    // EFFECTS: returns a JTextArea for the end of the introduction of the game
    private JTextArea introEnd(String pokemonName) {
        JTextArea textArea = new JTextArea("You chose " + pokemonName + "!\nNow now! You will need some PokéBalls to "
                + "catch the Pokémon around you.\nHere are 5 PokéBalls. You might need them. You can purchase more from"
                + " any PokéCenter.\nYou received " + STARTING_POKEBALLS + " PokéBalls.\nYou received 500 PokéDollars."
                + "\nNow, set off to travel this amazing world! [Press Enter to continue]");
        textArea.setFont(NORMAL_TEXT_FONT);
        textArea.setEditable(false);
        textArea.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                if (e.getKeyChar() == '\n') {
                    getContentPane().removeAll();
                    repaint();
                    mainFrame();
                }
            }

            @Override
            public void keyPressed(KeyEvent e) {}

            @Override
            public void keyReleased(KeyEvent e) {}
        });

        return textArea;
    }

    // NEW_GAME ENDS HERE

    // EFFECTS: creates the frame for the main game
    private void mainFrame() {
        setSize(FRAME_WIDTH, FRAME_HEIGHT);

        ImageIcon image = new ImageIcon("./data/pokemonImages/mewtwo.png");
        JLabel mainScreenImage = new JLabel();
        mainScreenImage.setIcon(image);
        mainScreenImage.setHorizontalAlignment(JLabel.CENTER);
        mainScreenImage.setBounds(100, 25, 800, 350);

        JPanel mainPanel = new JPanel(null);
        mainPanel.setBounds(100, 400, 800, 300);
        mainPanel.add(mainFrameButton("Move", 75, 25));
        mainPanel.add(mainFrameButton("PokéCenter", 475, 25));
        mainPanel.add(mainFrameButton("Your Pokémon", 75, 175));
        mainPanel.add(mainFrameButton("Quit", 475, 175));

        add(mainScreenImage);
        add(mainPanel);
    }

    // EFFECTS: creates a frame for when the player encounters a new pokemon
    private void encounterFrame() {
        battleModel = new BattleModel(player);

        JTextArea encounterText = new JTextArea("You encountered a wild " + battleModel.getWildPokemon().getName()
                + " of level " + battleModel.getWildPokemon().getLevel() + "!\n"
                + "Your " + battleModel.getActivePokemon().getName() + "'s HP: "
                + battleModel.getActivePokemon().getHP() + "\n" + battleModel.getWildPokemon().getName() + "'s HP: "
                + battleModel.getWildPokemon().getHP());
        encounterText.setBounds(100, 100, 400, 200);
        encounterText.setFont(NORMAL_TEXT_FONT);
        encounterText.setEditable(false);

        ImageIcon image = new ImageIcon("./data/pokemonImages/" + battleModel.getWildPokemon().getName() + ".png");
        JLabel wildPokemonImage = new JLabel();
        wildPokemonImage.setIcon(image);
        wildPokemonImage.setHorizontalAlignment(JLabel.CENTER);
        wildPokemonImage.setBounds(500, 0, 400, 400);

        JPanel encounterOptionsPanel = new JPanel(null);
        encounterOptionsPanel.setBounds(100, 400, 800, 300);
        encounterOptionsPanel.add(mainFrameButton("Attack", 75, 25));
        encounterOptionsPanel.add(mainFrameButton("Switch", 475, 25));
        encounterOptionsPanel.add(mainFrameButton("Bag", 75, 175));
        encounterOptionsPanel.add(mainFrameButton("Escape", 475, 175));

        add(encounterText);
        add(wildPokemonImage);
        add(encounterOptionsPanel);
    }

    // EFFECTS: creates the frame for when the user chooses to attack the wild pokemon
    private void attackFrame() {
        JTextArea textArea = new JTextArea("Your " + battleModel.getActivePokemon().getName() + "'s HP: "
                + battleModel.getActivePokemon().getHP() + "/" + battleModel.getActivePokemon().getMaxHP() + "\n"
                + battleModel.getWildPokemon().getName() + "'s HP: " + battleModel.getWildPokemon().getHP() + "/"
                + battleModel.getWildPokemon().getMaxHP());
        textArea.setBounds(100, 100, 800, 200);
        textArea.setFont(NORMAL_TEXT_FONT);
        textArea.setEditable(false);

        add(textArea);
        add(attackButtons(battleModel.getActivePokemon().getAttacks()));
    }

    // EFFECTS: returns a JPanel containing all the buttons for attacks of the active pokemon in battle
    private JPanel attackButtons(List<Attack> attacks) {
        JPanel attacksPanel = new JPanel(null);
        attacksPanel.setBounds(100, 400, 800, 300);

        int x = 75;
        int y = 25;
        for (int i = 0; i < attacks.size(); i++) {
            if (i == 0 || i == 2) {
                attacksPanel.add(attackButton(attacks.get(i).getName(), x, y, i + 1));
                x += 400;
            } else {
                attacksPanel.add(attackButton(attacks.get(i).getName(), x, y, i + 1));
                x -= 400;
                y += 150;
            }
        }

        return attacksPanel;
    }

    // EFFECTS: creates a frame for when the player loses the battle
    private void battleLostFrame(Attack attack) {
        JTextArea lostText = new JTextArea("Your " + battleModel.getActivePokemon().getName() + " used "
                + attack.getName() + "\nYour " + battleModel.getActivePokemon().getName() + " wad defeated.");
        activePokemonGainXP(attack, lostText);
        lostText.append(" [Press Enter to continue]");
        lostText.setBounds(100, 100, 800, 200);
        lostText.setFont(NORMAL_TEXT_FONT);
        lostText.setEditable(false);
        lostText.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                if (e.getKeyChar() == '\n') {
                    getContentPane().removeAll();
                    repaint();
                    mainFrame();
                }
            }

            @Override
            public void keyPressed(KeyEvent e) {}

            @Override
            public void keyReleased(KeyEvent e) {}
        });
        add(lostText);
        lostText.requestFocusInWindow();
    }

    // EFFECTS: creates the frame for continued battle
    private void battleContinueFrame(Attack attack) {
        JTextArea continueText = new JTextArea("Your " + battleModel.getActivePokemon().getName() + "'s HP: "
                + battleModel.getActivePokemon().getHP() + "/" + battleModel.getActivePokemon().getMaxHP() + "\n"
                + battleModel.getWildPokemon().getName() + "'s HP: " + battleModel.getWildPokemon().getHP() + "/"
                + battleModel.getWildPokemon().getMaxHP() + "\nYour " + battleModel.getActivePokemon().getName()
                + " used " + attack.getName() + "\nYour " + battleModel.getActivePokemon().getName() + " dealt "
                + attack.getPower() + " damage to " + battleModel.getWildPokemon().getName());
        activePokemonGainXP(attack, continueText);
        continueText.setBounds(100, 100, 800, 200);
        continueText.setFont(NORMAL_TEXT_FONT);
        continueText.setEditable(false);

        JPanel encounterOptionsPanel = new JPanel(null);
        encounterOptionsPanel.setBounds(100, 400, 800, 300);
        encounterOptionsPanel.add(mainFrameButton("Attack", 75, 25));
        encounterOptionsPanel.add(mainFrameButton("Switch", 475, 25));
        encounterOptionsPanel.add(mainFrameButton("Bag", 75, 175));
        encounterOptionsPanel.add(mainFrameButton("Escape", 475, 175));

        add(continueText);
        add(encounterOptionsPanel);
    }

    // EFFECTS: decides whether the battle is lost or not and call method accordingly
    private void battleContinue(Attack attack) {
        battleModel.wildPokemonAttack();

        if (battleModel.getActivePokemon().getHP() == 0) {
            battleLostFrame(attack);
        } else {
            battleContinueFrame(attack);
        }
    }

    // EFFECTS: creates the frame for when the player wins the battle
    private void battleWonFrame(Attack attack) {
        JTextArea end = new JTextArea("Your " + battleModel.getActivePokemon().getName() + " used " + attack.getName());
        activePokemonGainXP(attack, end);
        end.append(battleWonText());
        end.setBounds(100, 100, 800, 200);
        end.setFont(NORMAL_TEXT_FONT);
        end.setEditable(false);
        end.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                if (e.getKeyChar() == '\n') {
                    getContentPane().removeAll();
                    repaint();
                    mainFrame();
                }
            }

            @Override
            public void keyPressed(KeyEvent e) {}

            @Override
            public void keyReleased(KeyEvent e) {}
        });
        add(end);
        end.requestFocusInWindow();
    }

    // EFFECTS: returns the battle winning message string
    private String battleWonText() {
        return "\nYour " + battleModel.getActivePokemon().getName() + " defeated "
                + battleModel.getWildPokemon().getName() + "\nYou received "
                + (10 * battleModel.getWildPokemon().getLevel()) + " PokéDollars.\n" + "Your PokéDollars: "
                + battleModel.getPlayer().getPokeDollars() + "\n[Press Enter to continue]";
    }

    // EFFECTS: creates the frame for when the player uses the bag
    private void bagFrame() {
        JTextArea bagText = new JTextArea("1. PokéBalls x" + battleModel.getPlayer().getPokeballs());
        bagText.setBounds(100, 100, 800, 200);
        bagText.setFont(NORMAL_TEXT_FONT);
        bagText.setEditable(false);
        add(bagText);

        JPanel usePanel = new JPanel(null);
        usePanel.setBounds(100, 400, 800, 300);
        usePanel.add(mainFrameButton("Use", 75, 25));
        usePanel.add(mainFrameButton("Back", 475, 25));
        add(usePanel);
    }

    // EFFECTS: creates frame for when the player uses pokeball and calls method accordingly
    private void usePokeballFrame() {
        int caught = battleModel.usePokeball();
        JTextArea usePokeball = new JTextArea();
        usePokeball.setBounds(100, 100, 800, 200);
        usePokeball.setFont(NORMAL_TEXT_FONT);
        usePokeball.setEditable(false);
        if (caught == 1) {
            pokemonCaught(usePokeball);
        } else if (caught == 0) {
            catchFail(usePokeball);
        } else {
            usePokeball.setText("You cannot have more than 6 Pokémon in your team!");
        }
        add(usePokeball);
        usePokeball.requestFocusInWindow();
    }

    // EFFECTS: creates the frame for when the player successfully catches the wild pokemon
    private void pokemonCaught(JTextArea usePokeball) {
        usePokeball.setText("1. 2.. 3... Poof!\n" + battleModel.getWildPokemon().getName() + " was caught! "
                + "[Press Enter to continue]");
        usePokeball.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                if (e.getKeyChar() == '\n') {
                    getContentPane().removeAll();
                    repaint();
                    mainFrame();
                }
            }

            @Override
            public void keyPressed(KeyEvent e) {}

            @Override
            public void keyReleased(KeyEvent e) {}
        });
    }

    // EFFECTS: creates the frame for when the player fails the catch the wild pokemon
    private void catchFail(JTextArea usePokeball) {
        usePokeball.setText("1. 2.. 3... Bam!\nFailed to catch " + battleModel.getWildPokemon().getName() + "! "
                + "[Press Enter to continue]");
        usePokeball.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                if (e.getKeyChar() == '\n') {
                    getContentPane().removeAll();
                    repaint();
                    encounterFrame();
                }
            }

            @Override
            public void keyPressed(KeyEvent e) {}

            @Override
            public void keyReleased(KeyEvent e) {}
        });
    }

    // EFFECTS: creates the frame for when the player visits PokeCenter
    private void pokecenterFrame() {
        gameModel.restorePokemonHealth();
        JTextArea healed = new JTextArea("");
        for (Pokemon p : player.getPokemon()) {
            healed.append(p.getName() + "'s HP: " + p.getHP() + "\n");
        }
        healed.setFont(NORMAL_TEXT_FONT);
        healed.setBounds(100, 100, 800, 200);
        healed.setEditable(false);

        add(healed);
        add(mainFrameButton("Back", 375, 500));
    }

    // EFFECTS: creates frame representing player's pokemon's current stats
    private void yourPokemonFrame() {
        int i = 1;
        JTextArea printPokemon = new JTextArea();
        printPokemon.setBounds(100, 100, 800, 200);
        printPokemon.setFont(NORMAL_TEXT_FONT);
        printPokemon.setEditable(false);
        for (Pokemon p : player.getPokemon()) {
            printPokemon.append(i + ". " + p.getName() + ":\n\tLevel: " + p.getLevel() + "\n\tHP: " + p.getHP()
                    + "\n\tXP: " + p.getXP() + "\n\tAttacks: " + p.getAttackNames() + "\n");
            i++;
        }
        add(printPokemon);
        add(mainFrameButton("Back", 375, 500));
    }

    // EFFECTS: creates frame for quitting the game if the player wants to save their game and does so
    private void quitFrame() {
        JLabel question = new JLabel("Do you want to save your game?");
        question.setFont(NORMAL_TEXT_FONT);
        question.setBounds(100, 100, 800, 300);

        JPanel saveOrNot = new JPanel(null);
        saveOrNot.setBounds(100, 400, 800, 300);
        saveOrNot.add(mainFrameButton("Yes", 75, 100));
        saveOrNot.add(mainFrameButton("No", 475, 100));

        add(question);
        add(saveOrNot);
    }

    // EFFECTS: displays XP gained by active pokemon; level up if possible; learned attack if it learned a new attack
    private void activePokemonGainXP(Attack attack, JTextArea textArea) {
        boolean levelUp = battleModel.activePokemonGainXP(attack);
        textArea.append("\nYour " + battleModel.getActivePokemon().getName() + " gained " + attack.getPower()
                + " XP. (" + (battleModel.getActivePokemon().getXP() - attack.getPower()) + " → "
                + battleModel.getActivePokemon().getXP() + ")");
        if (levelUp) {
            textArea.append("\nYour " + battleModel.getActivePokemon().getName() + " leveled up! ("
                    + (battleModel.getActivePokemon().getLevel() - 1) + " → "
                    + battleModel.getActivePokemon().getLevel() + ")");

            boolean learnAttack = battleModel.activePokemonLearnAttack();
            if (learnAttack) {
                textArea.append("Your " + battleModel.getActivePokemon().getName() + " learned "
                        + battleModel.getActivePokemon().getAttacks().get(
                        battleModel.getActivePokemon().getAttacks().size() - 1
                ).getName() + "!");
            }
        }
    }

    // EFFECTS: returns a JButton that is common in many frames
    private JButton mainFrameButton(String name, int x, int y) {
        JButton mainButton = new JButton(name);
        mainButton.setBounds(x, y, 250, 50);
        mainButton.setHorizontalTextPosition(JButton.CENTER);
        mainButton.setFocusable(false);
        mainButton.addActionListener(this);
        mainButton.setActionCommand(name);
        return mainButton;
    }

    // EFFECTS: returns a JButton for an attack of active pokemon
    private JButton attackButton(String name, int x, int y, int attackNumber) {
        JButton attackButton = new JButton(name);
        attackButton.setBounds(x, y, 250, 50);
        attackButton.setHorizontalTextPosition(JButton.CENTER);
        attackButton.setFocusable(false);
        attackButton.addActionListener(this);
        attackButton.setActionCommand("Attack " + attackNumber);
        return attackButton;
    }

    @SuppressWarnings({"checkstyle:MethodLength", "checkstyle:SuppressWarnings"})
    @Override
    public void actionPerformed(ActionEvent e) {
        getContentPane().removeAll();
        repaint();
        switch (e.getActionCommand()) {
            case "new game": {
                setLayout(LAYOUT);
                newGameFrame();
                break;
            } case "load game": {
                try {
                    player = gameModel.loadGame();
                } catch (IOException ioe) {
                    throw new RuntimeException(ioe);
                }
                mainFrame();
                break;
            } case "choose bulbasaur": {
                gameModel.assignStarter(0);
                setLayout(LAYOUT);
                introEndFrame("Bulbasaur");
                break;
            } case "choose charmander": {
                gameModel.assignStarter(1);
                setLayout(LAYOUT);
                introEndFrame("Charmander");
                break;
            } case "choose squirtle": {
                gameModel.assignStarter(2);
                setLayout(LAYOUT);
                introEndFrame("Squirtle");
                break;
            } case "Move": {
                boolean willBattle = gameModel.encounter();
                if (willBattle) {
                    encounterFrame();
                }
                break;
            } case "PokéCenter": {
                pokecenterFrame();
                break;
            } case "Your Pokémon": {
                yourPokemonFrame();
                break;
            } case "Back": {
                mainFrame();
                break;
            } case "Quit": {
                quitFrame();
                break;
            } case "Attack": {
                attackFrame();
                break;
            } case "Attack 1": {
                boolean defeated = battleModel.playerAttack(battleModel.getActivePokemon().getAttacks().get(0));
                if (!defeated) {
                    battleContinue(battleModel.getActivePokemon().getAttacks().get(0));
                } else {
                    battleWonFrame(battleModel.getActivePokemon().getAttacks().get(0));
                }
                break;
            } case "Attack 2": {
                boolean defeated = battleModel.playerAttack(battleModel.getActivePokemon().getAttacks().get(1));
                if (!defeated) {
                    battleContinue(battleModel.getActivePokemon().getAttacks().get(1));
                } else {
                    battleWonFrame(battleModel.getActivePokemon().getAttacks().get(1));
                }
                break;
            } case "Attack 3": {
                boolean defeated = battleModel.playerAttack(battleModel.getActivePokemon().getAttacks().get(2));
                if (!defeated) {
                    battleContinue(battleModel.getActivePokemon().getAttacks().get(2));
                } else {
                    battleWonFrame(battleModel.getActivePokemon().getAttacks().get(2));
                }
                break;
            } case "Attack 4": {
                boolean defeated = battleModel.playerAttack(battleModel.getActivePokemon().getAttacks().get(3));
                if (!defeated) {
                    battleContinue(battleModel.getActivePokemon().getAttacks().get(3));
                } else {
                    battleWonFrame(battleModel.getActivePokemon().getAttacks().get(3));
                }
                break;
            } case "Switch": {
                //stub
                break;
            } case "Bag": {
                bagFrame();
                break;
            } case "Use": {
                usePokeballFrame();
                break;
            } case "Escape": {
                mainFrame();
                break;
            } case "Yes": {
                try {
                    gameModel.writeToFile();
                } catch (FileNotFoundException ex) {
                    System.err.println("Unable to save your game.");
                }
                System.exit(0);
            } case "No": {
                System.exit(0);
            }
        }
    }

    // EFFECTS: sets the frame in the center of the screen
    private void centerOnScreen() {
        Dimension scrn = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation((scrn.width - getWidth()) / 2, (scrn.height - getHeight()) / 2);
    }
}