import java.util.ArrayList;
import java.util.Arrays;
import java.util.ListIterator;
import java.util.Scanner;

public class VirusCollection {

	ArrayList<Virus> collection = new ArrayList<Virus>();
	//creates arraylist

	public VirusCollection(ArrayList<Virus> virus) {
		//sets the inputed arraylist as the virus collection
		collection = virus;
	}

	public int returnLength() {
		//returns the virus collection size
		return collection.size();
	}
	public void filterDefinition(String s) {
		String sParsed = s.substring((s.length() + 1) - s.length(), s.length() - 1);
		//parses definition by taking in substring and parsing it in a simple manner
		Scanner removeCommas = new Scanner(sParsed).useDelimiter(",");
		//removes commas in the already parsed input
		ArrayList<Virus> temp = new ArrayList<>();
		//creates a temporary virus list for reference
		while(removeCommas.hasNext()) {
			//while there exist more keywords to filter through keep running while loop
			String parsedKeyword = removeCommas.next();
			//store each keyword through each while loop
			parsedKeyword = parsedKeyword.trim();
			//removes white space from the stored string
		for (int i = collection.size() - 1; i >= 0; i--) {
			//runs through the virus collection checking for the keyword
			if (collection.get(i).getOrganism().toLowerCase().contains(parsedKeyword.toLowerCase()) == true) {
				//if the keyword (case insensitive) does show up then add it to the temporary arraylist
				temp.add(collection.get(i));
			}
		}
		}
		for (int b = temp.size() - 1; b >= 0; b--) {
			//checks through the temporary virus list for repeated virus files
			for (int i = b - 1; i >= 0; i--) {
				if (temp.get(b).equals(temp.get(i))) {
					//if the temporary virus list has a repeated virus file remove it 
					temp.remove(i);
				}
			}
		}
		collection.clear();
		//clear the virus collection data
		for(int i = 0; i < temp.size(); i++) {
			//loops through the temp virus list and adds to the original virus collection
		collection.add(temp.get(i));
		}

	}

	public void filterOrigin(String s) {
		String lowerCase = s.toLowerCase();
		//turns the keyword to lower case for case insensitivity 
		ArrayList<Virus> newVirusList = new ArrayList<Virus>();
		//creates a new virus collection for the new filtered virus list
		for (int i = 0; i < collection.size(); i++) {
			//loops through the virus collection
			if (collection.get(i).getOrigin().toLowerCase().contains(lowerCase)) {
				//filters through the virus collection and if a file contains the keyword in its origin then add to new virus collection
				newVirusList.add(collection.get(i));
			} else {
				//if does not contain keyword remove it from the collection
				collection.remove(i);
				i--;
				//compensates for the length of the virus collection 
			}
			collection.equals(newVirusList);
			//sets original virus collection equal to the new filtered virus collection
		}
	}

	public void filterGene(String s) {
		String lowerCase = s.toLowerCase();
		//turns keyword to lower case for case insensitivity 
		for (int i = 0; i < collection.size(); i++) {
			//loops through the virus collection
			if (!(collection.get(i).getGene().toLowerCase().contains(lowerCase))) {
				//if file does not contain the inputed gene then remove it from the original virus list
				collection.remove(i);
				i--;
				//compensates for the length of the virus collection 
			}
		}

	}

	public void filterYear(Range r) {
		//takes in range in order to filer through the year
		for (int i = 0; i < collection.size(); i++) {
			//loops through the virus collection
			if ((r.contains(collection.get(i).getYear()))) {
				//if the virus object is in the range or equal to the single range value then do nothing
			} else {
				collection.remove(i);
				//otherwise if does not contain or equal range remove virus object from the virus collection otherwise
				i--;
				//compensates for the length of the virus collection
			}
		}
	}

	public void filterReference(Range r) {
		//takes in range in order to filer through the bp count/reference
		for (int i = 0; i < collection.size(); i++) {
			//loops through the virus collection
			if ((r.contains(collection.get(i).getReference()))) {
				//if the virus object is in the range or equal to the single range value then do nothing
			} else {
				collection.remove(i);
				//otherwise if does not contain or equal range remove virus object from the virus collection otherwise
				i--;
				//compensates for the length of the virus collection
			}

		}

	}

	public void list() {
		ListIterator<Virus> virusListIterator = collection.listIterator();
		//creates a listIterator object
		int fileCounter = 1;
		//sets i equal to one to keep count of total amount of inputed files
		while (virusListIterator.hasNext()) {
			//while the listIterator has an object then keep running this code and printing out each virus file
			System.out.println("-------------------------------------------------------------------------------");
			//prints borders for the virus list 
			System.out.println("Virus Number: " + "#" + (fileCounter) + "\n" + virusListIterator.next());
			fileCounter++;
			//adds to fileCounter
		}
		System.out.println("-------------------------------------------------------------------------------");
	//prints borders for the virus list using fence post method
	}

	public void sortReference() {
		int j, i;
		//creates integer i and j which which will help loop through the virus collection
		for (i = 1; i < collection.size(); i++) {
			//loops through the virus collection adding each individual virus to a temporary virus object holder
			Virus temp = collection.get(i);
			j = i;
			//set j = i 
			while ((j > 0) && (temp.getReference() < collection.get(j - 1).getReference())) {
				//while j is more than 0 and the reference/bp count value is less than the previous the reference/bp count value
				collection.set(j, collection.get(j - 1));
				//adds to the collection in the correct order (smallest to largest)
				j--;
				//compensate for the changing collection size
			}
			collection.set(j, temp);
			//fence post method to add the last virus file as it skips over the last file in the while loop

		}
	}

	public void sortYear() {
		int j, i;
		//creates the integer i and j which will help loop through the virus collection
		for (i = 1; i < collection.size(); i++) {
			//loops through the virus collection adding each individual virus to a temporary virus object holder
			Virus temp = collection.get(i);
			j = i;
			//sets j = i
			while ((j > 0) && (temp.getYear() < collection.get(j - 1).getYear())) {
				//while j is more than 0 and the year value that is gotten is less than the previous the year value
				collection.set(j, collection.get(j - 1));
				//adds to the collection in the correct order (smallest to largest)
				j--;
				//compensate for the changing collection size
			}
			collection.set(j, temp);
			//fence post method to add the last virus file as it skips over the last file in the while loop
		}
	}

	public void sortDefinition() {
		int j, i;
		//creates the integer i and j which will help loop through the virus collection
		for (i = 1; i < collection.size(); i++) {
			//loops through the virus collection adding each individual virus to a temporary virus object holder
			Virus temp = collection.get(i);
			j = i;
			//sets j = i
			while ((j > 0) && (temp.getOrganism().compareTo(collection.get(j - 1).getOrganism()) < 0)) {
				//while j is more than 0 and the organism value that is gotten is less than the previous the organism value (alphabetically)
				collection.set(j, collection.get(j - 1));
				//adds to the collection in the correct order (alphabetically)
				j--;
				//compensate for the changing collection size
			}
			collection.set(j, temp);
			//fence post method to add the last virus file as it skips over the last file in the while loop

		}
	}

	public void sortOrigin() {
		int j, i;
		//creates the integer i and j which will help loop through the virus collection
		for (i = 1; i < collection.size(); i++) {
			//loops through the virus collection adding each individual virus to a temporary virus object holder
			Virus temp = collection.get(i);
			j = i;
			//sets j = i
			while ((j > 0) && (temp.getOrigin().compareTo(collection.get(j - 1).getOrigin()) < 0)) {
				//while j is more than 0 and the origin value that is gotten is less than the previous the origin value (alphabetically)
				collection.set(j, collection.get(j - 1));
				//adds to the collection in the correct order (alphabetically)
				j--;
				//compensate for the changing collection size
			}
			collection.set(j, temp);
			//fence post method to add the last virus file as it skips over the last file in the while loop

		}
	}

	public void sortGene() {
		int j, i;
		//creates the integer i and j which will help loop through the virus collection
		for (i = 1; i < collection.size(); i++) {
			//loops through the virus collection adding each individual virus to a temporary virus object holder
			Virus temp = collection.get(i);
			j = i;
			//sets j = i
			while ((j > 0)
					&& (temp.getGene().toLowerCase().compareTo(collection.get(j - 1).getGene().toLowerCase()) < 0)) {
				//while j is more than 0 and the gene value that is gotten is less than the previous the gene value (alphabetically)
				collection.set(j, collection.get(j - 1));
				//adds to the collection in the correct order (alphabetically)
				j--;
				//compensate for the changing collection size
			}
			collection.set(j, temp);
			//fence post method to add the last virus file as it skips over the last file in the while loop

		}
	}

}
