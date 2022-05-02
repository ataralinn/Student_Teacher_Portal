import java.util.ArrayList;
import java.util.Random;

public abstract class Person
{
	@Override
	public String toString() {
		return firstName + " " + lastName;
	}

	private String firstName;
	private String lastName;
	private Character[] password;
	private ArrayList<Character[]> listOfPasswords = new ArrayList<Character[]>();

	public Person(String firstName, String lastName)
	{
		this.firstName = firstName;
		this.lastName = lastName;
		this.password = setPassword();

		System.out.println("Your password is: " + getPassword());
	}


	public void setFirstName(String firstName)
	{

		this.firstName = firstName;
	}

	public void setLastName(String lastName)
	{

		this.lastName = lastName;
	}

	public String getFirstName()
	{

		return firstName;
	}

	public String getLastName()
	{

		return lastName;
	}


	public Character[] setPassword()
	{
		String capitalCaseLetters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		String lowerCaseLetters = "abcdefghijklmnopqrstuvwxyz";
		String specialCharacters = "!@#$";
		String numbers = "1234567890";
		String combinedChars = capitalCaseLetters + lowerCaseLetters + specialCharacters + numbers;
		Random random = new Random();
		password = new Character[10];

		password[0] = lowerCaseLetters.charAt(random.nextInt(lowerCaseLetters.length()));
		password[1] = capitalCaseLetters.charAt(random.nextInt(capitalCaseLetters.length()));
		password[2] = specialCharacters.charAt(random.nextInt(specialCharacters.length()));
		password[3] = numbers.charAt(random.nextInt(numbers.length()));

		for (int i = 4; i < password.length; i++)
		{
			password[i] = combinedChars.charAt(random.nextInt(combinedChars.length()));
		}

		return password;

	}

	public String getPassword()
	{
		StringBuilder str = new StringBuilder();
		// str.append("Your password is: ");
		for (Character c : password)
		{
			str.append(c);
		}

		return str.toString();
	}

}