
public class Virus {
	private int bpCount;
	//creates bp field
	private String gene;
	//creates gene field
	private int year;
	//creates year field
	private String organism;
	//creates organism field
	private String origin;
	//creates origin field

	public Virus(int bp, int date, String virusGene, String virusOrganism, String originInput) {
		//constructor that creates a virus object, filling in each individual field
		bpCount = bp;
		year = date;
		gene = virusGene;
		organism = virusOrganism;
		origin = originInput;

	}

	public String toString() {
		//returns all fields
		String representation = "bpCount: " + bpCount + "\nYear: " + year + "\nGene: " + gene + "\nOrganism: " + organism + "\nOrigin:\n" + origin;
		//creates a string with every field that is returned
		return representation;
	}

	public String getOrganism() {
		//returns organism
		return organism;
	}
	
	public String getOrigin() {
		//returns origin
		return origin;
	}

	public int getReference() {
		//returns reference
		return bpCount;
	}

	public int getYear() {
		//returns yaer
		return year;
	}

	public String getGene() {
		//returns gene
		return gene;
	}

}