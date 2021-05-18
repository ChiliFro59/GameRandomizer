/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package apex;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.net.URL;
import java.util.ResourceBundle;
import java.net.MalformedURLException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.NumberAxis;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author pekin
 */
public class ApexController implements Initializable
{

    private URL kingsCanyonURL;
    private InputStream is = null;
    private BufferedReader reader = null;
    private String line;
    private String locationName = "";
    private int x = 0;
    private int y = 0;
    private Circle circle = new Circle();
    private List<Location> mapOne = new ArrayList<>();
    private List<Location> mapTwo = new ArrayList<>();
    private List<Location> mapThree = new ArrayList<>();
    private List<Location> mapFour = new ArrayList<>();
    private List<Location> mapFive = new ArrayList<>();
    private List<String> characters = new ArrayList<>();
    private List<String> weapons = new ArrayList<>();
    private List<String> challenges = new ArrayList<>();
    private List<String> duoPlayers = new ArrayList<>();
    private List<String> trioPlayers = new ArrayList<>();
    private String teamChallenge = "";
    //@FXML
    //private ScatterChart<Number,Number> chartGrid;
    private NumberAxis xAxis = new NumberAxis(0, 100, 1);
    private NumberAxis yAxis = new NumberAxis(0, 100, 1);
    @FXML
    private RadioButton radioPlayersOne;
    @FXML
    private ToggleGroup Mode;
    @FXML
    private RadioButton radioPlayersTwo;
    @FXML
    private TextField fieldDuosPlayerOne;
    @FXML
    private TextField fieldDuosPlayerTwo;
    @FXML
    private RadioButton radioPlayersThree;
    @FXML
    private TextField fieldTriosPlayerOne;
    @FXML
    private TextField fieldTriosPlayerTwo;
    @FXML
    private TextField fieldTriosPlayerThree;
    @FXML
    private CheckBox checkboxChallenge;
    @FXML
    private TextField textJumpmaster;
    @FXML
    private TextField textJumpLocation;
    @FXML
    private Text textPlayerOneOutput;
    @FXML
    private Text textPlayerTwoOutput;
    @FXML
    private Text textPlayerThreeOutput;
    @FXML
    private Button buttonRandomize;
    @FXML
    private Tooltip toolTipGeneral;
    @FXML
    private RadioButton RadioMapFive;
    @FXML
    private ToggleGroup Map;
    @FXML
    private RadioButton RadioMapFour;
    @FXML
    private RadioButton RadioMapThree;
    @FXML
    private RadioButton RadioMapTwo;
    @FXML
    private RadioButton RadioMapOne;
    @FXML
    private Button buttonRerollJumpMaster;
    @FXML
    private Button buttonRerollJumpLocation;
    @FXML
    private Button buttonPlayerOneRerollCharacter;
    @FXML
    private Button buttonPlayerOneRerollWeaponOne;
    @FXML
    private Button buttonPlayerOneRerollWeaponTwo;
    @FXML
    private Button buttonPlayerTwoRerollCharacter;
    @FXML
    private Button buttonPlayerTwoRerollWeaponOne;
    @FXML
    private Button buttonPlayerTwoRerollWeaponTwo;
    @FXML
    private Button buttonPlayerThreeRerollCharacter;
    @FXML
    private Button buttonPlayerThreeRerollWeaponOne;
    @FXML
    private Button buttonPlayerThreeRerollWeaponTwo;
    @FXML
    private Button buttonPlayerOneRerollChallenge;
    @FXML
    private Button buttonPlayerTwoRerollChallenge;
    @FXML
    private Button buttonPlayerThreeRerollChallenge;
    @FXML
    private TextField fieldPlayerTwoCharacterOutput;
    @FXML
    private TextField fieldPlayerTwoWeaponOneOutput;
    @FXML
    private TextField fieldPlayerTwoWeaponTwoOutput;
    @FXML
    private TextField fieldPlayerTwoChallengeOutput;
    @FXML
    private TextField fieldPlayerThreeWeaponOneOutput;
    @FXML
    private TextField fieldPlayerThreeWeaponTwoOutput;
    @FXML
    private TextField fieldPlayerThreeChallengeOutput;
    private TextField fieldPlayarOneCharacterOutput;
    @FXML
    private TextField fieldPlayerOneWeaponOneOutput;
    @FXML
    private TextField fieldPlayerOneWeaponTwoOutput;
    @FXML
    private TextField fieldPlayerOneChallengeOutput;
    @FXML
    private RadioButton radioTeamChallenge;
    @FXML
    private ToggleGroup Challenge;
    @FXML
    private RadioButton radioIndividualChallenge;
    Random rand = new Random();
    private int random = 0;
    @FXML
    private TextField fieldPlayerOneCharacterOutput;
    @FXML
    private CheckBox checkboxDuplicateWeapons;
    @FXML
    private TextField fieldPlayerThreeCharacterOutput;
    @FXML
    private GridPane gridPane;
    @FXML
    private Text textErrorText;
    @FXML
    private Pane paneInstruction;
    @FXML
    private Text textGoodLuck;
    @FXML
    private Text season;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize( URL url, ResourceBundle rb )
    {
        try
        {
            loadSeason();
            loadMapLocations();
            loadCharacters();
            loadWeapons();
            loadChallenges();
            loadMapImages();
        }
        catch ( MalformedURLException e )
        {
            System.out.println(e);
        }
        catch ( IOException e )
        {
            System.out.println(e);
        }
        catch ( Exception e )
        {
            System.out.println(e);
        }

        //just below and as center as possible following grid lines
         //used to find location to make plotting circles easier
        //unncomment and bring grid pane to front in scene builder
        /*
        gridPane.addEventHandler(MouseEvent.MOUSE_CLICKED, e ->
        {
            System.out.println("X: " + (e.getX() / 18 - 1));
            System.out.println("Y: " + (e.getY() / 18 - 1));
        });
        */
    }

    
    private void loadMapLocations() throws Exception
    {

        URL mapOneURL = new URL(
          "https://raw.githubusercontent.com/ChiliFro59/GameRandomizer/main/src/apex/loadableResources/mapOne.txt");
        URL mapTwoURL = new URL(
          "https://raw.githubusercontent.com/ChiliFro59/GameRandomizer/main/src/apex/loadableResources/mapTwo.txt");
        URL mapThreeURL = new URL(
          "https://raw.githubusercontent.com/ChiliFro59/GameRandomizer/main/src/apex/loadableResources/mapThree.txt");
        URL mapFourURL = new URL(
          "https://raw.githubusercontent.com/ChiliFro59/GameRandomizer/main/src/apex/loadableResources/mapFour.txt");
        URL mapFiveURL = new URL(
          "https://raw.githubusercontent.com/ChiliFro59/GameRandomizer/main/src/apex/loadableResources/mapFive.txt");
        mapReader(mapOneURL);
        mapReader(mapTwoURL);
        mapReader(mapThreeURL);
        mapReader(mapFourURL);
        mapReader(mapFiveURL);

    }

    private Image mapOneImage;
    private Image mapTwoImage;
    private Image mapThreeImage;
    private Image mapFourImage;
    private Image mapFiveImage;

    private void loadMapImages() throws Exception
    {
        String path;

        path = "https://raw.githubusercontent.com/ChiliFro59/GameRandomizer/main/src/apex/loadableResources/mapOne.jpg";
        mapOneImage = new Image(path);
        if ( mapOneImage.isError() )
        {
            RadioMapOne.setVisible(false);
        }
        path = "https://raw.githubusercontent.com/ChiliFro59/GameRandomizer/main/src/apex/loadableResources/mapTwo.jpg";
        mapTwoImage = new Image(path);
        if ( mapTwoImage.isError() )
        {
            RadioMapTwo.setVisible(false);
        }
        path = "https://raw.githubusercontent.com/ChiliFro59/GameRandomizer/main/src/apex/loadableResources/mapThree.jpg";
        mapThreeImage = new Image(path);
        if ( mapThreeImage.isError() )
        {
            RadioMapThree.setVisible(false);
        }

        path = "https://raw.githubusercontent.com/ChiliFro59/GameRandomizer/main/src/apex/loadableResources/mapFour.jpg";
        mapFourImage = new Image(path);

        if ( mapFourImage.isError() )
        {
            RadioMapFour.setVisible(false);
        }
        path = "https://raw.githubusercontent.com/ChiliFro59/GameRandomizer/main/src/apex/loadableResources/mapFive.jpg";
        mapFiveImage = new Image(path);
        if ( mapFiveImage.isError() )
        {
            RadioMapFive.setVisible(false);
        }

        setBackGroundImage();

    }

    private void loadCharacters() throws Exception
    {
        URL charactersURL = new URL(
          "https://raw.githubusercontent.com/ChiliFro59/GameRandomizer/main/src/apex/loadableResources/characters.txt");
        BufferedReader in = new BufferedReader(
          new InputStreamReader(charactersURL.openStream()));
        String inputLine;
        while ( ( inputLine = in.readLine() ) != null )
        {
            characters.add(inputLine);
        }
    }

    private void loadWeapons() throws Exception
    {
        URL weaponsURL = new URL(
          "https://raw.githubusercontent.com/ChiliFro59/GameRandomizer/main/src/apex/loadableResources/weapons.txt");
        BufferedReader in = new BufferedReader(
          new InputStreamReader(weaponsURL.openStream()));
        String inputLine;
        while ( ( inputLine = in.readLine() ) != null )
        {
            weapons.add(inputLine);
        }
    }

    private void loadChallenges() throws Exception
    {
        URL challengesURL = new URL(
          "https://raw.githubusercontent.com/ChiliFro59/GameRandomizer/main/src/apex/loadableResources/challenges.txt");
        BufferedReader in = new BufferedReader(new InputStreamReader(challengesURL.openStream()));
        String inputLine;
        while ( ( inputLine = in.readLine() ) != null )
        {
            challenges.add(inputLine);
        }
    }

    private void mapReader( URL mapNumber ) throws Exception
    {

        BufferedReader in = new BufferedReader(
          new InputStreamReader(mapNumber.openStream()));
        String inputLine = in.readLine();
        if ( inputLine.trim().equals("True") )
        {
            int locationCase = Integer.valueOf(in.readLine());
            switch ( locationCase )
            {
                case 1:
                {
                    while ( ( inputLine = in.readLine() ) != null )
                    {

                        String[] tempMapLocations = inputLine.split(" ");
                        mapOne.add(Location.verifyLocationValues(tempMapLocations));

                    }
                    RadioMapOne.setText(mapOne.get(0).getLocationName());
                    break;
                }
                case 2:
                {
                    while ( ( inputLine = in.readLine() ) != null )
                    {
                        String[] tempMapLocations = inputLine.split(" ");
                        mapTwo.add(Location.verifyLocationValues(tempMapLocations));
                    }
                    RadioMapTwo.setText(mapTwo.get(0).getLocationName());
                    break;
                }
                case 3:
                {
                    while ( ( inputLine = in.readLine() ) != null )
                    {
                        String[] tempMapLocations = inputLine.split(" ");
                        mapThree.add(Location.verifyLocationValues(tempMapLocations));
                    }
                    RadioMapThree.setText(mapThree.get(0).getLocationName());
                    break;
                }
                case 4:
                {
                    while ( ( inputLine = in.readLine() ) != null )
                    {
                        String[] tempMapLocations = inputLine.split(" ");
                        mapFour.add(Location.verifyLocationValues(tempMapLocations));
                    }
                    RadioMapFour.setText(mapFour.get(0).getLocationName());
                    break;
                }
                case 5:
                {
                    while ( ( inputLine = in.readLine() ) != null )
                    {
                        String[] tempMapLocations = inputLine.split(" ");
                        mapFive.add(Location.verifyLocationValues(tempMapLocations));
                    }
                    RadioMapFive.setText(mapFive.get(0).getLocationName());
                    break;
                }
                default:
                    System.out.println("we failed");
            }

        }
        else
        {

            int locationCase = Integer.valueOf(in.readLine());
            switch ( locationCase )
            {
                case 1:
                {
                    RadioMapOne.setVisible(false);
                    break;
                }
                case 2:
                {
                    RadioMapTwo.setVisible(false);
                    break;
                }
                case 3:
                {
                    RadioMapThree.setVisible(false);
                    break;
                }
                case 4:
                {
                    RadioMapFour.setVisible(false);
                    break;
                }
                case 5:
                {
                    RadioMapFive.setVisible(false);
                    break;
                }
                default:
                    System.out.println("Error");
            }
        }
    }

    @FXML
    private void handleClickRandomize()
    {
        textErrorText.setText("");
        duoPlayers.clear();
        trioPlayers.clear();
        getPlayers();
        teamChallenge = challenges.get(rand.nextInt(challenges.size()));
        setPlayerValues();
        textGoodLuck.setVisible(true);

    }

    private void getPlayers()
    {
        if ( radioPlayersOne.isSelected() )
        {
            duoPlayers.clear();
            trioPlayers.clear();
        }
        if ( radioPlayersTwo.isSelected() )
        {
            String playerOne = fieldDuosPlayerOne.getText();
            if ( playerOne.length() == 0 )
            {
                playerOne = "Player 1";
            }
            duoPlayers.add(playerOne);
            String playerTwo = fieldDuosPlayerTwo.getText();
            if ( playerTwo.length() == 0 )
            {
                playerTwo = "Player 2";
            }
            duoPlayers.add(playerTwo);
            trioPlayers.clear();
        }
        if ( radioPlayersThree.isSelected() )
        {
            String playerOne = fieldTriosPlayerOne.getText();
            if ( playerOne.length() == 0 )
            {
                playerOne = "Player 1";
            }
            trioPlayers.add(playerOne);
            String playerTwo = fieldTriosPlayerTwo.getText();
            if ( playerTwo.length() == 0 )
            {
                playerTwo = "Player 2";
            }
            trioPlayers.add(playerTwo);
            String playerThree = fieldTriosPlayerThree.getText();
            if ( playerThree.length() == 0 )
            {
                playerThree = "Player 3";
            }
            trioPlayers.add(playerThree);
            duoPlayers.clear();
        }
    }

    private void setPlayerValues()
    {
        if ( radioPlayersOne.isSelected() )
        {
            textJumpmaster.setText("YOU!!");
            textJumpLocation.setText(getRandomLocation());
            textPlayerOneOutput.setText("You");
            fieldPlayerOneCharacterOutput.setText(getRandomCharacter());
            String playerOneWeaponOne = getRandomWeaponOne();
            fieldPlayerOneWeaponOneOutput.setText(playerOneWeaponOne);
            fieldPlayerOneWeaponTwoOutput.setText(getRandomWeaponTwo(playerOneWeaponOne));
            fieldPlayerOneChallengeOutput.setText(getRandomChallenge());

            resetPlayerTwo();
            resetPlayerThree();
        }
        if ( radioPlayersTwo.isSelected() )
        {
            textJumpmaster.setText(getRandomDuoJumpMaster());
            textJumpLocation.setText(getRandomLocation());

            textPlayerOneOutput.setText(duoPlayers.get(0));
            String playerOneCharacter = getRandomCharacter();
            fieldPlayerOneCharacterOutput.setText(playerOneCharacter);
            String playerOneWeaponOne = getRandomWeaponOne();
            fieldPlayerOneWeaponOneOutput.setText(playerOneWeaponOne);
            fieldPlayerOneWeaponTwoOutput.setText(getRandomWeaponTwo(playerOneWeaponOne));
            fieldPlayerOneChallengeOutput.setText(getRandomChallenge());

            textPlayerTwoOutput.setText(duoPlayers.get(1));
            String playerTwoCharacter = checkDuplicateDuosCharacters(playerOneCharacter,
              getRandomCharacter());
            fieldPlayerTwoCharacterOutput.setText(playerTwoCharacter);
            String playerTwoWeaponOne = getRandomWeaponOne();
            fieldPlayerTwoWeaponOneOutput.setText(playerTwoWeaponOne);
            fieldPlayerTwoWeaponTwoOutput.setText(getRandomWeaponTwo(playerTwoWeaponOne));
            fieldPlayerTwoChallengeOutput.setText(getRandomChallenge());

            resetPlayerThree();

        }
        if ( radioPlayersThree.isSelected() )
        {
            textJumpmaster.setText(getRandomTrioJumpMaster());
            textJumpLocation.setText(getRandomLocation());

            textPlayerOneOutput.setText(trioPlayers.get(0));
            String playerOneCharacter = getRandomCharacter();
            fieldPlayerOneCharacterOutput.setText(playerOneCharacter);
            String playerOneWeaponOne = getRandomWeaponOne();
            fieldPlayerOneWeaponOneOutput.setText(playerOneWeaponOne);
            fieldPlayerOneWeaponTwoOutput.setText(getRandomWeaponTwo(playerOneWeaponOne));
            fieldPlayerOneChallengeOutput.setText(getRandomChallenge());

            textPlayerTwoOutput.setText(trioPlayers.get(1));
            String playerTwoCharacter = checkDuplicateDuosCharacters(playerOneCharacter,
              getRandomCharacter());
            fieldPlayerTwoCharacterOutput.setText(playerTwoCharacter);
            String playerTwoWeaponOne = getRandomWeaponOne();
            fieldPlayerTwoWeaponOneOutput.setText(playerTwoWeaponOne);
            fieldPlayerTwoWeaponTwoOutput.setText(getRandomWeaponTwo(playerTwoWeaponOne));
            fieldPlayerTwoChallengeOutput.setText(getRandomChallenge());

            textPlayerThreeOutput.setText(trioPlayers.get(2));
            String playerThreeCharacter = checkDuplicateTriosCharacter(playerOneCharacter,
              playerTwoCharacter, getRandomCharacter());
            fieldPlayerThreeCharacterOutput.setText(playerThreeCharacter);
            String playerThreeWeaponOne = getRandomWeaponOne();
            fieldPlayerThreeWeaponOneOutput.setText(playerThreeWeaponOne);
            fieldPlayerThreeWeaponTwoOutput.setText(getRandomWeaponTwo(playerThreeWeaponOne));
            fieldPlayerThreeChallengeOutput.setText(getRandomChallenge());

        }

    }

    private String getRandomLocation()
    {
        if ( RadioMapOne.isSelected() )
        {
            Location tempLocation = mapOne.get(1 + rand.nextInt(mapOne.size() - 1));
            setMap(tempLocation);
            return tempLocation.getLocationName();
        }
        if ( RadioMapTwo.isSelected() )
        {
            Location tempLocation = mapTwo.get(1 + rand.nextInt(mapTwo.size() - 1));
            setMap(tempLocation);
            return tempLocation.getLocationName();
        }
        if ( RadioMapThree.isSelected() )
        {
            Location tempLocation = mapThree.get(1 + rand.nextInt(mapThree.size() - 1));
            setMap(tempLocation);
            return tempLocation.getLocationName();
        }
        if ( RadioMapFour.isSelected() )
        {
            Location tempLocation = mapFour.get(1 + rand.nextInt(mapFour.size() - 1));
            setMap(tempLocation);
            return tempLocation.getLocationName();
        }
        if ( RadioMapFive.isSelected() )
        {
            Location tempLocation = mapFive.get(1 + rand.nextInt(mapFive.size() - 1));
            setMap(tempLocation);
            return tempLocation.getLocationName();
        }
        return "";
    }

    private String getRandomWeaponOne()
    {
        return weapons.get(rand.nextInt(weapons.size()));
    }

    private String getRandomWeaponTwo( String weaponOne )
    {
        String weaponTwo = weapons.get(rand.nextInt(weapons.size()));
        if ( !checkboxDuplicateWeapons.isSelected() )
        {
            while ( weaponTwo.equals(weaponOne) )
            {
                weaponTwo = weapons.get(rand.nextInt(weapons.size()));
            }
        }
        return weaponTwo;
    }

    private String getRandomChallenge()
    {
        String challenge;
        if ( checkboxChallenge.isSelected() )
        {
            challenge = challenges.get(rand.nextInt(challenges.size()));
        }
        else
        {
            challenge = "";
        }
        if ( radioTeamChallenge.isSelected() && checkboxChallenge.isSelected() )
        {
            challenge = teamChallenge;
        }

        return challenge;
    }

    private String getRandomCharacter()
    {
        return characters.get(rand.nextInt(characters.size()));
    }

    private void setMap( Location tempLocation )
    {
        Color color = new Color(1, 1, 1, 1);

        circle.setRadius(20.0f);

        //Setting color to the circle 
        circle.setFill(Color.color(0, 0, 0, 0));

        //Setting the stroke width 
        circle.setStrokeWidth(3);

        //Setting color to the stroke  
        circle.setStroke(Color.RED);
        int xValue = tempLocation.getxValue();
        int yValue = tempLocation.getyValue();
        //int tempX = 14;
        //int tempY = 18;
        
       
        

        try
        {
            gridPane.add(circle, xValue, yValue);
            //gridPane.add(circle,tempX,tempY);
        }
        catch ( Exception e )
        {

        }
        //gridPane.
    }

    @FXML
    private void handleRerollJumpmaster()
    {
        try
        {
            if ( radioPlayersTwo.isSelected() )
            {
                textJumpmaster.setText(getRandomDuoJumpMaster());
            }
            if ( radioPlayersThree.isSelected() )
            {
                textJumpmaster.setText(getRandomTrioJumpMaster());
            }
        }
        catch ( Exception e )
        {
            textErrorText.setText("Please click randomize first");
        }
    }

    private void resetPlayerTwo()
    {
        textPlayerTwoOutput.setText("");
        fieldPlayerTwoCharacterOutput.setText("");
        fieldPlayerTwoWeaponOneOutput.setText("");
        fieldPlayerTwoWeaponTwoOutput.setText("");
        fieldPlayerTwoChallengeOutput.setText("");
    }

    private void resetPlayerThree()
    {
        textPlayerThreeOutput.setText("");
        fieldPlayerThreeCharacterOutput.setText("");
        fieldPlayerThreeWeaponOneOutput.setText("");
        fieldPlayerThreeWeaponTwoOutput.setText("");
        fieldPlayerThreeChallengeOutput.setText("");
    }

    @FXML
    private void handleRadioMaps() throws Exception
    {
        setBackGroundImage();
    }

    private String getRandomDuoJumpMaster()
    {
        return duoPlayers.get(rand.nextInt(2));
    }

    private String getRandomTrioJumpMaster()
    {
        return trioPlayers.get(rand.nextInt(3));
    }

    private void setBackGroundImage()
    {
        Image image = null;
        if ( RadioMapOne.isSelected() )
        {
            image = mapOneImage;
        }
        if ( RadioMapTwo.isSelected() )
        {
            image = mapTwoImage;
        }
        if ( RadioMapThree.isSelected() )
        {
            image = mapThreeImage;
        }
        if ( RadioMapFour.isSelected() )
        {
            image = mapFourImage;
        }
        if ( RadioMapFive.isSelected() )
        {
            image = mapFiveImage;
        }
        BackgroundImage backgroundimage = new BackgroundImage(image,
          BackgroundRepeat.NO_REPEAT,
          BackgroundRepeat.NO_REPEAT,
          BackgroundPosition.DEFAULT,
          BackgroundSize.DEFAULT);

        // create Background 
        Background background = new Background(backgroundimage);
        gridPane.setBackground(background);
    }

    @FXML
    private void handleRerollJumpLocation( ActionEvent event )
    {
        try
        {
            textJumpLocation.setText(getRandomLocation());
        }
        catch ( Exception e )
        {
            textErrorText.setText("Please click randomize first");
        }
    }

    private String checkDuplicateDuosCharacters( String playerOneCharacter,
      String playerTwoCharacter )
    {
        String p2C = playerTwoCharacter;
        while ( playerOneCharacter.equals(p2C) )
        {
            p2C = getRandomCharacter();
        }
        return p2C;

    }

    private String checkDuplicateTriosCharacter( String playerOneCharacter,
      String playerTwoCharacter, String playerThreeCharacter )
    {
        String p3C = playerThreeCharacter;
        while ( p3C.equals(playerTwoCharacter) || p3C.equals(
          playerOneCharacter) )
        {
            p3C = getRandomCharacter();
        }
        return p3C;
    }

    private String checkDuplicateWeapons()
    {
        return null;

    }

    //dont forget to check reroll for duplicate characters, weapons
    @FXML
    private void handleRerollPlayerOneCharacter( ActionEvent event )
    {
        try
        {
            if ( radioPlayersOne.isSelected() )
            {
                fieldPlayerOneCharacterOutput.setText(getRandomCharacter());
            }
            if ( radioPlayersTwo.isSelected() )
            {
                String playerTwoCharacter = fieldPlayerTwoCharacterOutput.getText();
                String p1C = checkDuplicateDuosCharacters(playerTwoCharacter, getRandomCharacter());
                fieldPlayerOneCharacterOutput.setText(p1C);
            }
            if ( radioPlayersThree.isSelected() )
            {
                String playerTwoCharacter = fieldPlayerTwoCharacterOutput.getText();
                String playerThreeCharacter = fieldPlayerThreeCharacterOutput.getText();
                String p1C = checkDuplicateTriosCharacter(playerTwoCharacter, playerThreeCharacter,
                  getRandomCharacter());
                fieldPlayerOneCharacterOutput.setText(p1C);
            }
        }
        catch ( Exception e )
        {
            textErrorText.setText("Please click randomize first");
        }
    }

    @FXML
    private void handleRerollPlayerOneWeaponOne( ActionEvent event )
    {
        try
        {
            String playerOneWeaponTwo = fieldPlayerOneWeaponTwoOutput.getText();
            String p1W1 = getRandomWeaponTwo(playerOneWeaponTwo);
            fieldPlayerOneWeaponOneOutput.setText(p1W1);
        }
        catch ( Exception e )
        {
            textErrorText.setText("Please click randomize first");
        }
    }

    @FXML
    private void handleRerollPlayerOneWeaponTwo( ActionEvent event )
    {
        try
        {
            String playerOneWeaponOne = fieldPlayerOneWeaponOneOutput.getText();
            String p1W2 = getRandomWeaponTwo(playerOneWeaponOne);
            fieldPlayerOneWeaponTwoOutput.setText(p1W2);
        }
        catch ( Exception e )
        {
            textErrorText.setText("Please click randomize first");
        }
    }

    @FXML
    private void handleRerollPlayerTwoCharacter( ActionEvent event )
    {
        try
        {
            if ( radioPlayersOne.isSelected() )
            {
                textErrorText.setText("Please click randomize or 2/3 players button first");
            }
            if ( radioPlayersTwo.isSelected() )
            {
                String playerOneCharacter = fieldPlayerOneCharacterOutput.getText();
                String p2C = checkDuplicateDuosCharacters(playerOneCharacter, getRandomCharacter());
                fieldPlayerTwoCharacterOutput.setText(p2C);
            }
            if ( radioPlayersThree.isSelected() )
            {
                String playerOneCharacter = fieldPlayerOneCharacterOutput.getText();
                String playerThreeCharacter = fieldPlayerThreeCharacterOutput.getText();
                String p2C = checkDuplicateTriosCharacter(playerOneCharacter, playerThreeCharacter,
                  getRandomCharacter());
                fieldPlayerTwoCharacterOutput.setText(p2C);
            }
        }
        catch ( Exception e )
        {
            textErrorText.setText("Please click randomize first");
        }
    }

    @FXML
    private void handleRerollPlayerTwoWeaponOne( ActionEvent event )
    {
        try
        {
            String playerTwoWeaponTwo = fieldPlayerTwoWeaponTwoOutput.getText();
            String p2W1 = getRandomWeaponTwo(playerTwoWeaponTwo);
            fieldPlayerTwoWeaponOneOutput.setText(p2W1);
        }
        catch ( Exception e )
        {
            textErrorText.setText("Please click randomize first");
        }
    }

    @FXML
    private void handleRerollPlayerTwoWeaponTwo( ActionEvent event )
    {
        try
        {
            String playerTwoWeaponOne = fieldPlayerTwoWeaponOneOutput.getText();
            String p2W2 = getRandomWeaponTwo(playerTwoWeaponOne);
            fieldPlayerTwoWeaponTwoOutput.setText(p2W2);
        }
        catch ( Exception e )
        {
            textErrorText.setText("Please click randomize first");
        }
    }

    @FXML
    private void handleRerollPlayerThreeCharacter( ActionEvent event )
    {
        try
        {
            if ( radioPlayersOne.isSelected() )
            {
                textErrorText.setText("Please click randomize or 3 players button first");
            }
            if ( radioPlayersTwo.isSelected() )
            {
                textErrorText.setText("Please click randomize or 3 players button first");
            }
            if ( radioPlayersThree.isSelected() )
            {
                String playerOneCharacter = fieldPlayerOneCharacterOutput.getText();
                String playerTwoCharacter = fieldPlayerTwoCharacterOutput.getText();
                String p3C = checkDuplicateTriosCharacter(playerOneCharacter, playerTwoCharacter,
                  getRandomCharacter());
                fieldPlayerThreeCharacterOutput.setText(p3C);
            }
        }
        catch ( Exception e )
        {
            textErrorText.setText("Please click randomize first");
        }
    }

    @FXML
    private void handleRerollPlayerThreeWeaponOne( ActionEvent event )
    {
        try
        {
            String playerThreeWeaponTwo = fieldPlayerThreeWeaponTwoOutput.getText();
            String p3W1 = getRandomWeaponTwo(playerThreeWeaponTwo);
            fieldPlayerThreeWeaponOneOutput.setText(p3W1);
        }
        catch ( Exception e )
        {
            textErrorText.setText("Please click randomize first");
        }
    }

    @FXML
    private void handleRerollPlayerThreeWeaponTwo( ActionEvent event )
    {
        try
        {
            String playerThreeWeaponOne = fieldPlayerThreeWeaponOneOutput.getText();
            String p3W2 = getRandomWeaponTwo(playerThreeWeaponOne);
            fieldPlayerThreeWeaponTwoOutput.setText(p3W2);
        }
        catch ( Exception e )
        {
            textErrorText.setText("Please click randomize first");
        }
    }

    private void setTeamChallenge()
    {
        teamChallenge = challenges.get(rand.nextInt(challenges.size()));
        if ( radioPlayersOne.isSelected() )
        {
            fieldPlayerOneChallengeOutput.setText(teamChallenge);
        }
        if ( radioPlayersTwo.isSelected() )
        {
            fieldPlayerOneChallengeOutput.setText(teamChallenge);
            fieldPlayerTwoChallengeOutput.setText(teamChallenge);
        }
        if ( radioPlayersThree.isSelected() )
        {
            fieldPlayerOneChallengeOutput.setText(teamChallenge);
            fieldPlayerTwoChallengeOutput.setText(teamChallenge);
            fieldPlayerThreeChallengeOutput.setText(teamChallenge);
        }

    }

    @FXML
    private void handleRerollPlayerOneChallenge( ActionEvent event )
    {
        try
        {
            if ( radioTeamChallenge.isSelected() )
            {
                setTeamChallenge();

            }

            fieldPlayerOneChallengeOutput.setText(getRandomChallenge());

        }
        catch ( Exception e )
        {
            textErrorText.setText("Please click randomize first");
        }
    }

    @FXML
    private void handleRerollPlayerTwoChallenge( ActionEvent event )
    {
        try
        {
            if ( radioTeamChallenge.isSelected() )
            {
                setTeamChallenge();

            }

            fieldPlayerTwoChallengeOutput.setText(getRandomChallenge());

        }
        catch ( Exception e )
        {
            textErrorText.setText("Please click randomize first");
        }
    }

    @FXML
    private void handleRerollPlayerThreeChallenge( ActionEvent event )
    {
        try
        {
            if ( radioTeamChallenge.isSelected() )
            {
                setTeamChallenge();

            }

            fieldPlayerThreeChallengeOutput.setText(getRandomChallenge());

        }
        catch ( Exception e )
        {
            textErrorText.setText("Please click randomize first");
        }
    }

    @FXML
    private void handleOpenInstructions()
    {
        if ( paneInstruction.isVisible() == false )
        {
            paneInstruction.setVisible(true);
        }
        else
        {
            paneInstruction.setVisible(false);
        }
    }

    private void handleCloseInstructions()
    {
        paneInstruction.setVisible(false);
    }

    private void loadSeason() throws Exception
    {
        URL challengesURL = new URL(
          "https://raw.githubusercontent.com/ChiliFro59/GameRandomizer/main/src/apex/loadableResources/CurrentSeason.txt");
        BufferedReader in = new BufferedReader(new InputStreamReader(challengesURL.openStream()));
        String inputLine;
        while ( ( inputLine = in.readLine() ) != null )
        {
            season.setText("Season " + inputLine);
        }
    }
}
