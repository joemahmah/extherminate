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
trait Interactable {
    String name
    String description
    def actions = null
    
    def hasAction(){
        return actions != null
    }
    
    def listActions(){
        def actionNames = new ArrayList<String>()
        
        for(action in actions){
            actionNames.add(action.name)
        }
        
        return actionNames
    }
    
    def doAction(action){
        for(act in actions){
            if(act.name.equals(action)){
                act.action.call()
            }
        }
    }
    
    String toString(){
        return name
    }
}

