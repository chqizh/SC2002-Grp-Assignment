package Accounts;

public class Employee extends Account {
    private String name;
    private char role = 'S';
    private char gender;
    private int age;
    
    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setRole(char role) {
        this.role = role;        
    }

    public char getRole() {
        return this.role;
    }
    public void setGender(char gender){
        this.gender = gender;
    }

	public char getGender() {
		return this.gender;
	}

	public void setAge(int age) {
        this.age = age;
        throw new UnsupportedOperationException();
	}

	public int getAge() {
		return this.age;
	}

}
