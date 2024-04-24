package questions;

import doNotModify.Song;
import doNotModify.SongNode;

public class PlayQueue {
    public SongNode start; // DO NOT MODIFY
    public SongNode end;   // DO NOT MODIFY
    public int size; //Custom class parameter. Only feasible way without O(n) to calculate size
    public int mid; //Custom class parameter for optimisation purposes.
    // You may add extra attributes here

    /**
     * Adds a Song to the end of the PlayQueue.
     * <p>
     * Note: This must be completed before moving onto any other method.
     * @param song - The Song to add
     */
    public void addSong(Song song) { //Passed
       if(start==null && end == null) { //No SongNodes
    	   start = new SongNode(song, null, null);
    	   end = start;
    	   size++; //custom
       }
       else if(start!=null && end!=null) { //must be else if in order to ensure that if the previous part executes, this part doesn't
    	   if(end.equals(start)) { //Single SongNode
    		   end = new SongNode(song, null, start);
    		   start.next = end;
    		   size++; //custom
    	   }
    	   else { //Adding to the end of the list
    		   SongNode temp = new SongNode(song, null, end);
    		   end.next = temp; //set previous node's next to the 
    		   end = temp;
    		   size++; //custom
    	   }
       }
    }

    /**
     * Remove the first SongNode with the parameter Song from the PlayQueue.
     * <p>
     * Return true if a SongNode was removed, false otherwise.
     * @param song
     * @return - true if a SongNode was removed, false otherwise.
     */
    public boolean removeSong(Song song) { //passed
        if(start==null&&end==null) {
        	return false;
        }
        if(start!=null&&end!=null) {
        	if (start!=null&& end.equals(start) && start.song.equals(song)) {//single node in list
        		start=null;
        		end=null;
        		size--; //custom
        	}
        	else { //multiple songnodes and middle of list O(n) where n is number of nodes till song is found
        		SongNode temp = start.next;
        		SongNode temp2; //Substitute for temp where needed
        		while (temp!=null) {//need to do start as well
        			if(start.song.equals(song)) {//if at the start
        				temp.previous = null;
        				start = start.next;
        				size--; //custom
        				return true;
        			}
        			else if(temp.song.equals(song) && !temp.equals(end)) { //if explicitly in the middle of the list
        				temp2 = temp; //temp2 is assigned node temp
        				temp = temp.next; //temp is now next node
        				temp.previous = temp2.previous; //now being the next node, temp's previous is the before nodes previous
        				temp2.previous.next = temp; //the before nodes previous now has its next assigned to temp;
        				size--; //custom
        				return true;
        			}
        			else if(temp.song.equals(song) && temp.equals(end)) { //delete node with parameter song at end of list
        				temp2 = temp;
        				temp = temp.next; //in this case null
        				end = temp2.previous;
        				end.next = null;
        				size--; //custom
        				return true;
        			}
        			else {
        				temp = temp.next;
        			}
        		}
        	}
        }
        return false;
    }

    /**
     * Removes the SongNode at the specified index from the PlayQueue, returning
     * the Song that was removed.
     * <p>
     * Return null if `index` is invalid.
     * @param index
     */
    public Song removeSong(int index) { //passed
    	if(index<0) { //Exception
    		return null;
    	}
    	SongNode returnable = null; //A copy node store for the song that was removed from the list
    	
    	 if(start==null&&end==null) {
         	return null;
         }
         if(start!=null&&end!=null) {
         	if (start!=null&& end.equals(start) && index==0) {//single node in list
         		returnable = start; //Copy node to delete to returnable
         		start=null;
         		end=null;
         		this.size--; //custom
         		return returnable.song; //return deleted nodes song
         	}
         	else { //multiple songnodes and middle of list O(n) where n is number of nodes till song is found
         		int indexCompare = 1; //Note: NEEDS TO START AT 1
         		SongNode temp = start.next;
         		SongNode temp2; //Substitute for temp where needed
         		while (temp!=null) { //should prevent it from exceeding the intended index
         			if(index==0) {//if at the start
         				
         				
         				returnable = temp.previous; //because start is being removed
         				temp.previous = null;
         				start = start.next;
         				this.size--; //custom
         				
         				
         				
         				return returnable.song;
         			}
         			else if(index>=1 && index==indexCompare && !temp.equals(end)) { //if explicitly in the middle of the list
         				
         				
         				returnable = temp; //store object being removed from list in temporary store returnable
         				temp2 = temp; //temp2 is assigned node temp
         				temp = temp.next; //temp is now next node
         				temp.previous = temp2.previous; //now being the next node, temp's previous is the before nodes previous
         				temp2.previous.next = temp; //the before nodes previous now has its next assigned to temp;
         				this.size--; //custom
         				
         				
         				
         				return temp2.song;
         			}
         			else if(index>=1 && index==indexCompare && temp.equals(end)) { //delete node at end of list
         				
         				
         				returnable = temp; //store object being removed from list in temporary store returnable
         				temp2 = temp;
         				temp = temp.next; //in this case null
         				end = temp2.previous;
         				end.next = null;
         				this.size--; //custom
         				
         				
         				
         				return returnable.song;
         			}
         			else { //NOTE: Appears to be activating 
         				System.out.println(indexCompare + " " + index);
         				indexCompare = indexCompare+1;
         				temp = temp.next;
         				
         				
         				
         				
         			}
         		}
         	}
         }
         return null;
    }

    /**
     * Return the size (number of SongNodes) in the PlayQueue.
     * @return the size of the PlayQueue
     */
    public int size() { //Note: Requires removeSong Index version in order to pass tests. This is O(1).
        return size; //Relies on updated size values whenever list is updated. Dependent on custom class parameter size.
    }

    /**
     * Reverse the calling object PlayQueues Song ordering.
     */
    public void reverseQueue() { //just swap objects that prev and next are pointing to. O(n) as it is for every node in the list (passed)
    	
    	if(start == null) {
        	return;
        }
    	
    	
        SongNode temp = start; //temp is where we will operate on the node's properties
        SongNode current = temp; //copy pointer to ensure we can go further in the list after we swap the next and prev pointers' objects.
        
        
        while(current!=null) {
        	
        	if(temp.equals(start)) { //start of the list, so swap the next node to be the previous one, and leave the next as null, as it is now end of the list.
        		current = current.next; //assign copy pointer to the next one in the list, as this will be lost after we change the order of objects assigned to the next and prev pointers.
        		temp.previous = temp.next; //assign object at next to be the one at previous
        		temp.next = null;
        		temp = current;
        	}
        	else if(temp.equals(end)) { //make it so that it is start of the list
        		current=null; //current should finally equal null to indicate that the list has been reversed
        		temp.next = temp.previous;
        		temp.previous = null;
        	}
        	else { //middle of list
        		current = current.next;
        		SongNode copyPrev = temp.previous;
        		temp.previous = temp.next;
        		temp.next = copyPrev;
        		temp = current;
        	}
        	
        }
        SongNode copyStart = start;
        start = end;
        end = copyStart;
    }

    /**
     * Move the SongNode from the `fromIndex` index the specified `amount`.
     * 
     * Let the queue be:
     *       start              end
     *         |                 |
     * null <- a <-> b <-> c <-> d -> null
     * 
     * Let fromIndex be 1.
     * The expected queue should be as follows for:
     * amount := 0
     *       start              end
     *         |                 |
     * null <- a <-> b <-> c <-> d -> null
     * 
     * amount := 1
     *       start              end
     *         |                 |
     * null <- a <-> c <-> b <-> d -> null
     * 
     * amount := -1
     *       start              end
     *         |                 |
     * null <- b <-> a <-> c <-> d -> null
     * 
     * amount := 2
     *       start              end
     *         |                 |
     * null <- a <-> c <-> d <-> b -> null
     * <p>
     * Do nothing if either `fromIndex` is invalid, or `amount` is invalid for
     * the given `fromIndex`.
     * <p>
     * Do not create any new SongNode instances.
     * @param fromIndex
     * @param amount
     */
    public void moveSong(int fromIndex, int amount) { //store pointer objects and reassign (passed)
    	int amountCopy = amount; //To count down from to ensure we are moving correct number of spaces.
    	
    	//Exception list
        if(fromIndex<0) {
        	return;
        }
        if(amount>size) {
        	return;
        }
        if(amount< size*-1) {
        	return;
        }
        if(amount> (size-1-fromIndex) ) {
        	return;
        }
        if(amount==0) {
        	return;
        }
        
        //Actual start of code
        
        SongNode current = start;
        int currentIndex = 0; //The index of the current node in the list.
        if(current.next!=null) { //to ensure that null exception doesn't occur
        	if(currentIndex<fromIndex) { //currentIndex is less than fromIndex (fromIndex is positive and current needs to be moved up)
        		
        		
        		while(current!=null && currentIndex!=fromIndex) { // loop to move current to the correct node where fromIndex is located
        			//Pre: current is start. fromIndex is greater than 0
        			//Post: (Intended) current is the node linked to fromIndex in the linked list
        			current = current.next;
        			currentIndex = currentIndex +1;
        		}
        		
        		
        	}
        	
        	
        	if(currentIndex==fromIndex) { //currentIndex is now equal to fromIndex (precondition for inner loop)
        		
        		while(amountCopy!=0) { //Need to put references to start and end pointers (mostly done here, but start.next needs to be changed)
        			//Pre: current is at the specified node. currentIndex is indeed at fromIndex, and this is the index of the node of current.
        			//Post: current is moved the specified amount of places. currentIndex == 0; 
        			if(amountCopy>0) { //we need to move it up
        				
        				SongNode temp = current.next; //pointer to the next node
        				
        				//Reverses order of two nodes in list (moves current up by 1)
        				current.next = temp.next;
        				temp.previous = current.previous;
        				current.previous = temp;
        				temp.next = current;
        				//End pointing (TODO: Make node after/before order reversal is done point to new nodes if possible)
        				if(current.next!=null) {
        					current.next.previous = current;
        				}
        				if(temp.previous!=null) {
        					temp.previous.next = temp;
        				}
        				
        				currentIndex = currentIndex+1;
        				amountCopy = amountCopy-1;
        				
        				if(temp.previous == null) {
        					start = temp;
        				}
        				
        				if(current.next == null) {
        					end = current;
        				}
        				
        				//Reassignment of start and end's next and previous pointers
        				if(current.previous!=null) { //Reassignment of start.next based on current
        					if(current.previous.previous!=null) {
        						if(current.previous.previous.equals(start)) {
                					start.next = current.previous;
                				}
        					}
        				}
        				
        				if(temp.previous!=null) { //Reassignment of start.next based on temp
        					if(temp.previous.previous!=null) {
        						if(temp.previous.previous.equals(start)) {
                					start.next = temp.previous;
                				}
        					}
        				}
        				
        				if(current.next!=null) { //reassignment of end.previous based on current
        					if(current.next.next!=null) {
        						if(current.next.next.equals(end)) {
                					end.previous = current.next;
                				}
        					}
        				}
        				
        				if(temp.next!=null) { //reassignment of end.previous based on temp
        					if(temp.next.next!=null) {
        						if(temp.next.next.equals(end)) {
                					end.previous = temp.next;
                				}
        					}
        				}
        				
        				
        				
        				
        			}
        			
        			else if(amountCopy<0) {
        				
        				SongNode temp = current.previous; //Pointer to previous node
        				
        				//Reverses order of two nodes in list (moves current down by 1)
        				temp.next = current.next;
        				current.previous = temp.previous;
        				temp.previous = current;
        				current.next = temp;
        				
        				
        				
        				//End pointing
        				
        				if(temp.next!=null) {
        					temp.next.previous = temp;
        				}
        				if(current.previous!=null) {
        					current.previous.next = current;
        				}
        				
        				
        				currentIndex = currentIndex-1;
        				amountCopy = amountCopy+1;
        				
        				
        				if(temp.next ==null) {
        					end = temp;
        					
        				}
        				
        				if(current.previous == null) {
        					start = current;
        					
        				}
        				
        				//Reassignment of start and end's next and previous pointers
        				
        				if(current.previous!=null) { //Reassignment of start.next based on current
        					if(current.previous.previous!=null) {
        						if(current.previous.previous.equals(start)) {
                					start.next = current.previous;
                				}
        					}
        				}
        				if(temp.previous!=null) { //Reassignment of start.next based on temp
        					if(temp.previous.previous!=null) {
        						if(temp.previous.previous.equals(start)) {
                					start.next = temp.previous;
                				}
        					}
        				}
        				
        				if(current.next!=null) { //reassignment of end.previous based on current
        					if(current.next.next!=null) {
        						if(current.next.next.equals(end)) {
                					end.previous = current.next;
                				}
        					}
        				}
        				
        				if(temp.next!=null) { //reassignment of end.previous based on temp
        					if(temp.next.next!=null) {
        						if(temp.next.next.equals(end)) {
                					end.previous = temp.next;
                				}
        					}
        				}
        				
        				
        				
        				
        				
        			}
        		}
        	}
        }
        
    }

    /**
     * Swap the SongNodes at parameter indices.
     * Do nothing if either parameters are invalid.
     * @param firstIndex
     * @param secondIndex
     */
    public void swapSongs(int firstIndex, int secondIndex) { //Move the song back or forward by the difference using move song (passed)
        if((firstIndex<0)||(secondIndex<0)){
        	return;
        }
        if(firstIndex<secondIndex) {
        	moveSong(firstIndex, secondIndex-firstIndex);
        	moveSong(secondIndex-1, (secondIndex-1-firstIndex)*-1);
        }
        else if(firstIndex>secondIndex) {
        	moveSong(secondIndex, firstIndex-secondIndex);
        	moveSong(firstIndex-1, (firstIndex-1-secondIndex)*-1);
        }
    }

    /**
     * Check the PlayQueue for cycles.
     * <p>
     * There is at most one cycle in the PlayQueue. This may be bi-directional.
     * @return - true if a cycle is detected, false otherwise.
     */
    public boolean hasCycle() { //requires discrete maths? (requires floyd's cycle detection algorithm, if two pointers meet at same point, a cycle exists)
    	//Maybe use slow or fast depending on whether the size is even or odd? May make it O(n) by accounting for the different results.
    	//Floyd's cycle detection algorithm
        SongNode slow = start;
        SongNode fast = start;
        SongNode bslow = end;
        SongNode bfast = end;
        
        //Simple Cycle
        if(slow!=null && fast!=null &&slow==fast) {
        	if(slow.next!=null) {
        		if(slow.next.equals(fast)) {
        			return true;
        		}
        		if(slow.next.equals(start)) {
        			return true;
        		}
        	}
        	if(slow.previous!=null) {
        		if(slow.previous.equals(fast)) {
        			return true;
        		}
        		if(slow.previous.equals(start)) {
        			return true;
        		}
        	}
        }
        while(slow!=null && fast!=null && fast.next!=null || bslow!=null && bfast!=null&& bfast.previous!=null) { //Still O(n^2)
        	
        	if(slow!=null && fast!=null && fast.next!=null) {
        		slow = slow.next;
        		fast = fast.next.next; //increased speed
        	}
        	
        	
        	if(bslow!=null && bfast!=null&& bfast.previous!=null) {
        		bslow = bslow.previous;
        		bfast = bfast.previous.previous;
        	}
        	
        	
        	if(slow.equals(fast)) { //note in java documentation, since both slow and fast are pointers, it will compare equality of references
        		return true;
        	}
        	if(bslow!=null&&bfast!=null && bfast.previous!=null) {
        		if(bslow.equals(bfast)) {
        			return true;
        		}
        	}
        	
        }
        
        /*
        slow=end;
        fast=end;
        
        while(slow!=null && fast!=null && fast.previous!=null) { //Backward (warning causes O(n^2) complexity)
        	slow = slow.previous;
        	fast = fast.previous.previous; //increased speed
        	if(slow.equals(fast)) { //note in java documentation, since both slow and fast are pointers, it will compare equality of references
        		return true;
        	}
        	
        }
        */
        
        
        
        return false;
    }

    /**
     * Create and return a (semi) randomly shuffled PlayQueue from the calling object.
     * <p>
     * A shuffled PlayQueue begins with the same Song as the calling object.
     * For all other Songs in the resulting PlayQueue the following formula is used:
     * <p>
     * (x^2 + 1) % p * s % n
     * <p>
     * where x is the index previously taken from,
     * <p>
     * where p is a prime number,
     * <p>
     * where s is seed number.
     * <p>
     * and n is the length of the PlayQueue
     * <p>
     * You must ensure that you do not go out of bounds, and that when the provided formula
     * creates a cycle that it is no longer used. Then the Songs in all uncovered SongNodes
     * are added in their original order to the resulting PlayQueue.
     * 
     * @param p - prime number
     * @param s - seed number
     * @return the shuffled queue
     */
    public PlayQueue shuffledQueue(int p, int s) { //TODO: Build lookup list to ensure duplicates are not created of nodes that already exist in the list after a cycle is detected.
    	if(isAPrime(p)==false) {
    		return null;
    	}
    	
    	PlayQueue shuffled = new PlayQueue();
    	if(this.start!=null) {
    		shuffled.addSong(this.start.song); // The start of the new queue which will be the same song as the one at the start of the original queue
    	}
    	int x = 0; //Starting from zero, the index of the last node (so for first node, zero is used, for second, one is used)
    	
    	
    	
    	while(shuffled.size()<this.size()) {
    		int shuffleAlgo = (x*x + 1) % p * s % this.size; //shuffle algo (assuming PlayQueue size is this.size)
    		Song newSong = getSongNodeAtIndex(shuffleAlgo).song;
    		shuffled.addSong(newSong);
    		System.out.println("Node "+shuffled.getSongNodeAtIndex(x).song.title+" was added"); //The removal part only seems to be useful for getting rid of a duplicate that shows up right after I detect the cycle. BUT HOW DOES THAT EVEN HAPPEN I HAVE NO IDEA HOW THIS IS OCCURING. IT SHOULDN'T DO THAT
    		//If a cycle is found, do not apply the algo any longer and just add every other song after that one in its original order (because high likelihood of more cycles)
    		if(shuffled.hasCycleByTitle()==true) { //This now works by using a helper, using original results in a memory error due to copies of objects not equaling each other
    			
    			Song removed = shuffled.removeSong(x); //What the heck, it doesn't want me to remove the one causing a cycle, what is this? EDIT: Now has an unintended but useful use, deleting a duplicate that somehow shows up when I detect the cycle? I really hope this doesn't screw me as I'm building on a bug I have no idea how to fix
    			
    			System.out.println("Size of shuffled is "+shuffled.size);
    			shuffled.removeSong(x);
    			System.out.println("Node "+removed.title+" was removed");
    			System.out.println("Size of shuffled is "+shuffled.size);
    			break; //Poor practice, but designed to just get out of the loop
    		}
    		System.out.println("x was " + x + " for this iteration");
    		x=x+1;
    		
    		
    		
        		
        }
    	x=x-1;
    	
    	
    	while(shuffled.size<this.size()) {
    		
    		//PRECONDITION: The size of shuffled is not the same as the size of this list (PlayQueue) minus one as we are not including the SongNode with a cycle (deleted in last loop if found)
    		//POSTCONDITION: The size of shuffled is the same as the size of the list (PlayQueue) minus one with every node after the node deleted being in the same order as the original list
    		Song newerSong = getSongNodeAtIndex(x).song;
    		shuffled.addSong(newerSong);
    		System.out.println("Node "+shuffled.getSongNodeAtIndex(x).song.title+" was added");
    		System.out.println("x was " + x + " for this iteration");
    		x = x + 1;
    		
    	}
    	
    	
    	
    	
    	return shuffled;
    	
    }
    
    public boolean isAPrime(int f) { //Helper for shuffledQueue
    	if(f<=1) {
    		return false;
    	}
    	for(int i = 2; i<f/2; i++) { //O(log n)
    		if(f%i==0) {
    			return false;
    		}
    	}
    	
    	return true;
    }
    
    public SongNode getSongNodeAtIndex(int x) { //Helper for shuffledQueue
    	if(x<0) {
    		return null;
    	}
    	if(x>this.size()) {
    		return null;
    	}
    	int count = 0;
    	SongNode current = start;
    	while (count<x) {
    		current=current.next;
    		count=count+1;
    	}
    	return current;
    }
    
    public boolean hasCycleByTitle() { //helper for shuffle (possibly because created copies of objects are not the same in memory, so try by title instead) EDIT: This works
    	//Floyd's cycle detection algorithm
        SongNode slow = start;
        SongNode fast = start;
        SongNode bslow = end;
        SongNode bfast = end;
        
        //Simple Cycle
        if(slow!=null && fast!=null &&slow==fast) {
        	if(slow.next!=null) {
        		if(slow.next.song.title==fast.song.title) {
        			return true;
        		}
        		if(slow.next.song.title==start.song.title) {
        			return true;
        		}
        	}
        	if(slow.previous!=null) {
        		if(slow.previous.song.title==fast.song.title) {
        			return true;
        		}
        		if(slow.previous.song.title==start.song.title) {
        			return true;
        		}
        	}
        }
        while(slow!=null && fast!=null && fast.next!=null || bslow!=null && bfast!=null&& bfast.previous!=null) { //Still O(n^2)
        	
        	if(slow!=null && fast!=null && fast.next!=null) {
        		slow = slow.next;
        		fast = fast.next.next; //increased speed
        	}
        	
        	
        	if(bslow!=null && bfast!=null&& bfast.previous!=null) {
        		bslow = bslow.previous;
        		bfast = bfast.previous.previous;
        	}
        	
        	if(slow!=null && fast!=null) {
        		if(slow.song.title == fast.song.title) {
        			return true;
        		}
        	}
        	if(bslow!=null&&bfast!=null && bfast.previous!=null) {
        		if(bslow.song.title == bfast.song.title) {
        			return true;
        		}
        	}
        	
        }
        return false;
    }


    @Override
    public String toString() {
        if (start == null) {
            return "null";
        }
        String forward = " forwards :         ";
        SongNode temp = start;
        while (temp.next != null) {
            forward += temp.song.title + " -> ";
            temp = temp.next;
        }
        forward += temp.song.title + " -> null";

        temp = end;
        String backward = "";
        while (temp.previous != null) {
            backward = " <- " + temp.song.title + backward;
            temp = temp.previous;
        }
        backward = "backwards : null <- " + temp.song.title + backward;
        return forward + "\n" + backward;
    }
}
