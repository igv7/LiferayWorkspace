package obj;

public class Worker {
	private int id;
	private String name;
	private String birthdate;

	public Worker(int id, String name, String birthdate) {
		super();
		this.setId(id);
		this.setName(name);
		this.setBirthdate(birthdate);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(String birthdate) {
		this.birthdate = birthdate;
	}

}
