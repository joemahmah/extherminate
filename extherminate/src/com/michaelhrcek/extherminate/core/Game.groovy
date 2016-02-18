/*
 * Copyright 2016 mhrcek.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.michaelhrcek.extherminate.core

/**
 *
 * @author mhrcek
 */
class Game {
    
    static Random random = new Random()
    
    static void main(String[] args){
        Location l1 = new Location("COSI")
        Location l2 = new Location("ITL")
        Location l3 = new Location("HALLWAY NEAR COSI")
        Location l4 = new Location("4")
        Location l5 = new Location("5")
        Location l6 = new Location("6")
    
        l1.setConnections([l2,l3])
        l2.setConnections([l1,l3])
        l3.setConnections([l1,l2,l4])
        l4.setConnections([l3,l5,l6])
        l5.setConnections([l4])
        l6.setConnections([14])
        
        l1.addInteractable(new Person(name:"DOBBY",description:"Dobby is in the corner."))
        l1.addInteractable(new Person(name:"GRM",description:"GRM is sitting at the table."))
        l1.addInteractable(new Grabable(name:"GRM'S COMPUTER",description:"GRM's Computer seems to have ponies on the screen."))
        l1.addInteractable(new Person(name:"FATMAN",description:"In a large chair sits Fatman."))
        l1.addInteractable(new Grabable(name:"PAINTING",description:"A wet oil painting of mountains is in the corner. It may not be a good idea to touch it..."))
        l1.addInteractable(new Grabable(name:"THERMOSTAT",description:"The thermostat sits on the wall. It mocks you. Maybe...",actions:[new Action(name:"Grab",description:"You rip the thermostat off the wall.",action:{-> println "rip"}),new Action(name:"Toggle",description:"You toggle the thermostat.", action:{-> println "gg"})]))
        l1.addInteractable(new Grabable(name:"RADIO",description:"An old radio sits on the window."))
        l1.addInteractable(new Nongrabable(name:"WINDOW",description:""))
        l1.addInteractable(new Grabable(name:"BRUSH",description:"A dirty brush sits on the table."))
        
        l2.addInteractable(new Person(name:"BEN",description:"You can see Ben. He appears to be triggered..."))
        l2.addInteractable(new Person(name:"ALAN",description:"Alan is sitting near Ben. He is rambles on about the GPL."))
        l2.addInteractable(new Grabable(name:"WINDOW",description:""))
        
        ArrayList<Location> locations = [l1,l2,l3,l4,l5,l6]
        
        def lm = new LocationMap(locations,l1)
        
        while(true){
            String input = System.in.newReader().readLine()
            def currentRoom = lm.currentLocation
            
            if(input.toUpperCase().startsWith("EXIT")){
                break;
            } else if(input.toUpperCase().startsWith("LOOK")){
                println currentRoom.genDescription()
            } else if(input.toUpperCase().startsWith("INTERACT")){
                if(input.size() > 9){
                    String object = input.toUpperCase().substring(9)
                    def newObject = currentRoom.hasObject(object)
                    
                    if(newObject){
                        //lm.setCurrentLocation(newObject)
                        if(newObject.hasAction()){
                            println "What would you like to do with ${newObject.name}"
                            println newObject.listActions()
                            
                            input = System.in.newReader().readLine()
                            newObject.doAction(input)
                        } else {
                            println "You beat the everliving fuck out of ${newObject.name}"
                        }
                    } else{
                        println "Can't fuck up an object that doesn't exist..."
                    }
                } else{
                    println "Can't fuck up a null pointer..."
                }
            } else if(input.toUpperCase().startsWith("MOVE")){
                if(input.size() > 5){
                    String location = input.toUpperCase().substring(5)
                    def newLocation = currentRoom.hasConnection(location)
                    
                    if(newLocation){
                        lm.setCurrentLocation(newLocation)
                        println "You move to ${newLocation.name}"
                    } else{
                        println "Can't move to a place that doesn't exist..."
                    }
                } else{
                    println "Can't move to a null pointer..."
                }
            } else if(input.toUpperCase().startsWith("HELP")){
                println """
                    EXIT\n\
                    HELP\n\
                    LOOK\n\
                    INTERACT\n\
                    MOVE\n\
                    GRAB
                    """
            }else{
                println "Does not compute!"
            }
        }
        
    }
}

