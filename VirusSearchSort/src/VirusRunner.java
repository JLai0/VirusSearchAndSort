import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ListIterator;
import java.util.Scanner;

public class VirusRunner {

	public static void main(String[] args) throws IOException {
		boolean startProgram = true;
		//starts the program in a while loop so it can be reset
		while (startProgram == true) {
			//while loop start
			ArrayList<File> fileList = new ArrayList<File>(VirusRunner.load());
			//loads the files into the java program
			System.out.println("Hello!");
			//says hello!
			System.out.println("Total Amount of Files: " + fileList.size());
			ArrayList<String> fileNames = new ArrayList<String>(VirusRunner.loadNames());
			//loads each and every file name into an arraylist
			//the file names are never used but in a practical sense they would be very useful
			ArrayList<Virus> virusList = new ArrayList<Virus>();
			//arraylist for each individual virus content into an array list
			VirusCollection virusCollectionList = new VirusCollection(virusList);
			//turns the arraylist of virus files in a viruscollection object
			System.out.println("Enter Input: ");
			//asks for input
			Scanner userInput = new Scanner(System.in);
			//takes in user input
			String userInputStored = userInput.nextLine();
			//stores user input for reference
			ArrayList<String> userInputList = new ArrayList<String>();
			//user input stored in array list for storage
			String[] tempHolder = userInputStored.split("\\s+");
			//uses split method on the stored string so that it can be stored as an array for easier parsing
			for (int i = 0; i < fileList.size(); i++) {
				//works to load the content of each viruslist in a for loop equal to the amount of files
				Scanner fileContentScanner = new Scanner(fileList.get(i));
				//takes in first file into a scanner so it can be parsed
				String storedLine1 = fileContentScanner.nextLine();
				//first line stored
				String[] storedInfo = storedLine1.split("\\s+");
				//parses the first line and stored in array for easier access
				String[] storedDate = storedInfo[7].split("-");
				//takes in part of the first parsed line to be used later
				String[] storedGene = storedInfo[4].split("-");
				//takes in part of the first parsed line to be used later
				String storedGeneString = null;
				//creates a string that will store the bit of information responsible for identifying the gene
				if (storedGene.length == 2) {
					//checks whether or not the length of the storedgene in the arraylist because there is a gene type consisting of more than one word
					storedGeneString = storedGene[1];
					//if the gene has two words than it will be stored differently than if it had one word
				} else {
					storedGeneString = storedGene[0];
					//if the gene has one word than it will be stored differently than if it had two words
				}
				String storedLine2 = fileContentScanner.nextLine();
				//takes in the second line of the virus file for parsing
				String[] storedFullDefinition = storedLine2.split("\\s+");
				//parses the second line of the virus file and stores into a string or easier parsing
				String definition = "";
				//creates definition string for reference
				for (int b = 1; b < storedFullDefinition.length; b++) {
					//for loop that works to take in the definition depending on the length of the array which stores each indiviudal word that makes up the definition
					definition += storedFullDefinition[b] + " ";
					//adds each individual word that makes up the definition to the definition string so it can be stored
				}
				Scanner originFinder = new Scanner(fileList.get(i));
				//takes in the file again to be parsed for the origin
				int l = 0;
				//creates an integer for a while loop
				String originSaver = "";
				//creates origin string for the origin to be saved
				while (1 != l) {
					//keeps looping to find the origin until the origin string has be satisfied
					if (originFinder.hasNextLine()) {
						//works to find the line with the origin
						String line = originFinder.nextLine();
						//creates a string so that the line  with the origin can be parsed
						if (!line.contains("ORIGIN")) {
							//if the line does not contain the keyword origin do nothing
						} else {
							//else I want to store the following line because it does contain the origin
							originSaver = originFinder.nextLine() + "\n" + originFinder.nextLine() + "\n"
									+ originFinder.nextLine() + "\n" + originFinder.nextLine();
							//stores origin in the origin string
							l++;
							//ends while loop
						}
					}
				}

				Virus virusCreated = new Virus(Integer.valueOf(storedInfo[2]), Integer.valueOf(storedDate[2]),
						storedGeneString, definition, originSaver);
				//creates each individual virus object using the information that has been parsed from each file
				virusList.add(virusCreated);
				//adds each virus object to the arraylist then loop is repeated to be done for each virus file
				fileContentScanner.close();
				originFinder.close();
				//closes scanners
			}
			for (int q = 0; q < tempHolder.length; q++) {
				//the tempholder holds the userinput
				userInputList.add(tempHolder[q]);
				//store that user input as an arraylist for easy parsing and finding of keywords
			}
			int start = 0;
			//creates an integer for the while loop that reads the user input so that it can input the correct information
			while (start == 0) {
				//starts while loop
				if (userInputList.contains("list")) {
					//if the user input contain list then print every virus list without modification
				}
				if (userInputList.contains("sort")) {
					//if sorting then check for any of these four keywords
					if (userInputList.contains("reference")) {
						virusCollectionList.sortReference();
						//runs sortReference method on the virus collection if contains the keyword reference
					}
					if (userInputList.contains("year")) {
						virusCollectionList.sortYear();
						//runs sortReference method on the virus collection if contains the keyword year
					}
					if (userInputList.contains("definition")) {
						virusCollectionList.sortDefinition();
						//runs sortReference method on the virus collection if contains the keyword definition
					}
					if (userInputList.contains("origin")) {
						virusCollectionList.sortOrigin();
						//runs sortReference method on the virus collection if contains the keyword origin
					}
					if (userInputList.contains("gene")) {
						virusCollectionList.sortGene();
						//runs sortReference method on the virus collection if contains the keyword nucleotide
					}
				}

				if (userInputList.contains("filter")) {
					ArrayList<String> filterValues = new ArrayList<String>();
					//if filter then check for any of these four keywords and create an arraylist that to indicate how many user keywords to be filtered
					if (userInputList.contains("definition")) {
						for (int l = 2; l < userInputList.size(); l++) {
							filterValues.add(userInputList.get(l));
							//checks how many keywords will be used to filter through definition
						}
						virusCollectionList.filterDefinition(filterValues.toString());
						//runs filterDefinition on virus collection passing the inputed keywords
					}
					if (userInputList.contains("origin")) {
						for (int l = 2; l < userInputList.size(); l++) {
							virusCollectionList.filterOrigin(userInputList.get(l));
							//runs filterOrigin on virus collection if contains keyword definition
						}
					}
					if (userInputList.contains("gene")) {
						for (int l = 2; l < userInputList.size(); l++) {
							virusCollectionList.filterGene(userInputList.get(l));
							//runs filterGene on virus collection if contains the keyword nucleotide
						}
					}
					if (userInputList.contains("year")) {
						if (userInputList.size() <= 2) {
							String filter = userInputList.get(2);
							virusCollectionList.filterYear(Range.parse(filter));
							//runs filterYear on virus collection if there is only one year 
						}
						if (userInputList.size() > 2) {
							//if the user input is more than two keywords then the output will be a range between two years
							String filter = "";
							//creates filter string as a placeholder
							for (int i = 2; i < userInputList.size(); i++) {
								filter += userInputList.get(i);
								//turns userinput into a single string to be taken in as a value
							}
							virusCollectionList.filterYear(Range.parse(filter));
							//runs filterYear method with the year range 
						}
					}
					if (userInputList.contains("reference")) {
						if (userInputList.size() <= 2) {
							//if the user input is more than two keywords then the output will be a range between two reference values
							String filter = userInputList.get(2);
							virusCollectionList.filterReference(Range.parse(filter));
							//runs filterReference on virus collection if there is only one reference value
						}
						if (userInputList.size() > 2) {
							String filter = "";
							//creates filter string as a place holder
							for (int i = 2; i < userInputList.size(); i++) {
								filter += userInputList.get(i);
								//turns userinput into a single string to be taken in as a value
							}
							virusCollectionList.filterReference(Range.parse(filter));
							//runs filterReference method with the reference range
						}

					}
				}
				start = 1;
				//ends the while loop
				if (virusCollectionList.returnLength() != 0) {
					//if the virus collection has at least one virus object then print it out
					virusCollectionList.list();
					System.out.println("Choices:");
					System.out.println("-Reset");
					System.out.println("-Continue");
					System.out.println("-Quit");
					//gives options to user
				} else {
					System.out.println("Nothing Matches Your Input");
					//if there are no virus objects in the virus collection print nothing and tell user
				}
			}
			userInputList.clear();
			//ends userInputList scanner
			start = 1;
			String userInputStoredEnd = userInput.nextLine();
			//takes in userinput to determine option
			if (userInputStoredEnd.equals("reset")) {
				//if contains the keyword reset then run this code
				for (int i = 0; i < 50; i++) {
					System.out.println();
					//if reset then create blank space
				}
				if (userInputStoredEnd.equals("continue")) {
					//if contains the keyword continue run this code
					for (int i = 0; i < 50; i++) {
						System.out.println();
						//if continue then create blank space
					}
					start = 0;
					//restarts the code while loop
				}
			}
			if (userInputStoredEnd.equals("quit")) {
				//if contains the keyword quit then run this code
				userInput.close();
				//end userinput scanner
				startProgram = false;
				//ends program
			}
		}
	}
	public static ArrayList<File> load() {
		//load method creates an arrayList of each virus file so that they can be parsed through for necessary information
		File folder = new File("C:\\Users\\jonathan.lai\\eclipse-workspace\\VirusSearchSort\\");
		File[] listOfFiles = folder.listFiles();
		//loads each and every file into a file array
		ArrayList<File> fileList = new ArrayList<File>();
		//creates arraylist for the files
		for(int i = 0; i < listOfFiles.length; i++) {
			//loop through the file array and in doing so create a new file object with each file in the folder
			File file = listOfFiles[i];
			if(file.isFile() && file.getName().endsWith(".txt")) {
				//if the file ends in .txt store it within the array
				File inputFile = new File(file.getName());
				fileList.add(inputFile);
				//adds each file created to the fileList arrayList
			}
		}
		return fileList;
	}
	
	public static ArrayList<String> loadNames(){
		//this code is not a part of the project but in a practical sense could be used to find the actual files containing specific information
		File folder = new File("C:\\Users\\jonathan.lai\\eclipse-workspace\\VirusSearchSort\\");
		File[] listOfFiles= folder.listFiles();
		//creates a file array and loads every file into a file array
		ArrayList<String> fileNames = new ArrayList<String>();
		//creates a string arraylist to store virus file names
		for(int i = 0; i < listOfFiles.length; i++) {
			//loops through all of the files in the file array creating a new file object each time
			File file = listOfFiles[i];
			if(file.isFile() && file.getName().endsWith(".txt")) {
				//if the file ends with .txt store the file name as a string and add that string to the arraylist for future usage or reference
				String inputFile = file.getName();
				fileNames.add(inputFile);
			}
		}
		return fileNames;
	}
}
