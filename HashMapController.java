// IPPO Assignment 1, Version 20.3, 21/12/2020
package ippo.assignment1.controller;

import ippo.assignment1.library.utils.HashMap;
import ippo.assignment1.library.Picture;
import ippo.assignment1.library.controller.Controller;
import ippo.assignment1.library.service.Service;
import ippo.assignment1.library.service.ServiceFromProperties;
import ippo.assignment1.library.view.View;
import ippo.assignment1.library.view.ViewFromProperties;

/**
 * A simple controller for the PictureViewer application.
 *
 * @author Paul Anderson &lt;dcspaul@ed.ac.uk&gt;
 * @version 20.3, 21/12/2020
 */
public class HashMapController implements Controller {


    private Service service;


    /**
     * declare a HashMap with parameters Integer and String and initialize it
     *
     */
    private static HashMap<Integer, String> munroMap = new HashMap();
    private static View view = new ViewFromProperties();

    /**
     * AddSubject  to add the munro to the view and the hashmap
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
         * populate the hashmap
          */
        addSubject("Stob Binnein");
        addSubject("Gairich");
        addSubject("Ben Lomond");
        addSubject("Ben Vorlich");


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

        /** a picture corresponding to the selectionID
         by default, this is an empty picture
         (this is used if the selectionID does not match)
         the picture is accessed form the hashmap thru the selectionID
         */

        Picture picture = new Picture();

        picture=service.getPicture(munroMap.get(selectionID),1);

        /**
         * show the picture in the interface
         */

        view.showPicture(picture);
    }

}




