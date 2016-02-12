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
class Location {
	
    def name
    def interactables
    def connections
    
    Location(name){
        this(name,null)
    }
    
    Location(name, connections){
        this.name = name
        this.interactables = new ArrayList<Interactable>()
        this.connections = connections
    }
    
    def setConnections(connections){
        this.connections = connections;
    }
    
    def addInteractable(interactable){
        this.interactables.add(interactable)
    }
    
    def genDescription(){
        String description = "You are in ${name}. "
        
        for(inter in interactables){
            description += inter.description + " "
        }
        
        for(location in connections){
            if(Game.random.nextInt(10) > 5){
                description += "You are able to get to ${location.name}. "
            } else{
                description += "You can see a door to ${location.name}. "
            }
        }
        
        return description
    }
    
    def hasConnection(String location){
        for(connection in connections){
            if(connection.name.toUpperCase().equals(location.toUpperCase())){
                return connection;
            }
        }
        return false;
    }
    
    def hasObject(String obj){
        for(inter in interactables){
            if(inter.name.toUpperCase().equals(obj.toUpperCase())){
                return inter;
            }
        }
        return false;
    }
    
    String toString(){
        String location = name + "\n"
        
        for(inter in interactables){
            location += "\t" + inter.toString() + "\n"
        }
        
        return location
    }
}

