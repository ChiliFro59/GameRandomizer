/*
 * Copyleft: (c) 2021</p>
 *
 * @author Chili Fro
 * @version 1.0
 */
package main;


import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import static javafx.application.Application.launch;

/**
 *
 * @author pekin
 */
public class main extends Application
{
    /*
     * sets the stage to ksp home as the main page
     * has a button bar with all the links to different pages
     */
    @Override
    public void start( Stage stage ) throws Exception
    {
        stage.setTitle("Game Randomizer");
        Parent apex = null;

        //parent pages for each tab
        try
        {
            apex = FXMLLoader.load(getClass().getResource("apex.fxml"));
        }
        catch ( IOException e )
        {
            System.out.println(e);
        }
        catch ( Exception e )
        {
            System.out.println(e);
        }

        VBox layoutGameRandomizer = new VBox();

        layoutGameRandomizer.setId("bodybg");
        layoutGameRandomizer.getStylesheets().addAll(this.getClass().getResource("apex.css").
          toExternalForm());

        //initial page loading
        layoutGameRandomizer.getChildren().addAll(apex);

        //display main page
        Scene mainPage = new Scene(layoutGameRandomizer);

        stage.setResizable(false);
        stage.setScene(mainPage);
        stage.show();

    }

    public static void main( String[] args ) throws IOException
    {
        launch(args);
    }

    
}
