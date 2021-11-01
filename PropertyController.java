// IPPO Assignment 1, Version 20.3, 21/12/2020
package ippo.assignment1.controller;

import ippo.assignment1.library.utils.HashMap;
import ippo.assignment1.library.Picture;
import ippo.assignment1.library.controller.Controller;
import ippo.assignment1.library.service.Service;
import ippo.assignment1.library.service.ServiceFromProperties;
import ippo.assignment1.library.utils.Properties;
import ippo.assignment1.library.view.View;
import ippo.assignment1.library.view.ViewFromProperties;

/**
 * A simple controller for the PictureViewer application.
 *
 * @author Paul Anderson &lt;dcspaul@ed.ac.uk&gt;
 * @version 20.3, 21/12/2020
 */
public class PropertyController implements Controller {


    private Service service;


    /**
     *  declare a HashMap with parameters Integer and String  and initialize it
     *  this will hold the name of the munro along with an index as key value pair
      */
    private static HashMap<Integer, String> munroMap = new HashMap();
    private static View view = new ViewFromProperties();

    /**
     * addSubject to take a name of a munro and adds it to the view and the hashmap

     */


    public void addSubject(String MunroName) {

        int selectionID = view.addSelection(MunroName);

        munroMap.put(selectionID,MunroName);

    }

    /**
     * Start the controller.
     */
    public void start() {

        /**
         * create the view and the service objects
         */

        view = new ViewFromProperties(this);
        service = new ServiceFromProperties();

        /**
         * get the list of Munro from the property file , create a List (array) by splitting
         * at the comma ,remove trailing spaces by using the trim() method and adjust the
         * indexing for an array
         */

        String ListOfMunros = Properties.get("controller.subjects");

        String []MunroNames = ListOfMunros.split(",");

        for (int i=0; i<MunroNames.length;i++)
        {
            addSubject(MunroNames[i].trim());
        }

        /**
         * start the interface
          */

        view.start();
    }

    /**
     * Handle the specified selection from the interface.
     *
     * @param selectionID the id of the selected item
     */
    public void select(int selectionID) {

        /**
         * a picture corresponding to the selectionID
         * by default, this is an empty picture
         * (this is used if the selectionID does not match)
         * gets the picture thru the sectionID and the HashMap
          */


        Picture picture = new Picture();

        picture=service.getPicture(munroMap.get(selectionID),1);

        /**
         * show the picture in the interface
        */

        view.showPicture(picture);
    }

}

