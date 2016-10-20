import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.nio.file.OpenOption;
import java.security.PublicKey;
import java.text.DecimalFormat;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * This is a class to define the necessary attributes and methods to
 * conceptualize a "Student" The spepcific tasks are: 1. Instiate
 * 
 * @author Md.Khairul Islam
 * @version (11 Oct,2016)
 */
public class Student {
	
	public int Id;
	public String Name;
	public String Department;
	public String University;
	public double[] GPA = new double[8];
	public String[] subject = new String[100];
	public double[][] creditGrades = new double[100][100];
	public double totalCgpa = 0.0;
	public double totalCredit = 0.0;
	public double totalCreditGpa=0.0;
	
	
	public int k = 0, l = 0;

	
	public Student(int id, String name, String university, String department) {
		Id = id;
		Name = name;
		University = university;
		Department = department;
		for (int i = 1; i <= 40; i++) {
			for (int j = 1; j <= 2; j++) {
				creditGrades[i][j] = 0.0;
			}
		}

	}


	public void studentDetailsById() {
		
		System.out.println("ID: "+ Id);
		System.out.println("Name: "+ Name);
		System.out.println("Department: "+ Department);
		System.out.println("University: "+ University);
		
		
	}

	
	public void updateStudentById(int id) {
		// Write your code here
		System.out.println("ID: " +id);
	}

	public double computeCGPAByID() {
		
		totalCgpa=totalCreditGpa/totalCredit;
		System.out.println("Final CGPA: " + totalCgpa);
		
		return 0;
	}

	public double computeGPAById() {
		double termGpa = 0.0;
		double termCredit = 0.0;
		int k=0;
		for (int i = 1; i <= 40; i++) {
				//System.out.println(creditGrades[i][1] + " " + creditGrades[i][2]);
				termGpa += creditGrades[i][1]*creditGrades[i][2];
				totalCreditGpa+=creditGrades[i][1]*creditGrades[i][2];
				termCredit += creditGrades[i][1];
				totalCredit+=creditGrades[i][1];
				if(i%5==0)
				{
					k++;
					 termGpa=termGpa/termCredit;
					 System.out.println("Term " + k + "-GPA: " + termGpa);
					 termGpa = 0.0;
					 termCredit = 0.0;
				}
		}
		return 0;
	}

	public void loadStudents() {
		int m=1,n=1;
		try {

			File inputFile = new File("input.txt");
			Scanner input = new Scanner(inputFile);
			File outputFile= new File("output.txt");
			PrintWriter writer = new PrintWriter(outputFile);
			for (int i = 1; input.hasNext(); i++) {

				String inputStudentId = input.nextLine();
				if (inputStudentId.startsWith("1")) {
					int StringToInt = Integer.parseInt(inputStudentId);
					Id = StringToInt;
					writer.println(StringToInt);

				} else if (inputStudentId.startsWith("2")
						|| inputStudentId.startsWith("3")
						|| inputStudentId.startsWith("4")) {
					n = 1;
					Matcher matcher = Pattern.compile("[-+]?\\d*\\.?\\d+([eE][-+]?\\d+)?").matcher(
							inputStudentId);

					while (matcher.find()) {
						double StringToDouble = Double.parseDouble(matcher
								.group());

						creditGrades[m][n] = StringToDouble;
						n++;
					}
					
					m++;

				}

				else {
					if(i==2)
					{
						Name = inputStudentId;
					}
					else if(i==3)
					{
						University = inputStudentId;
					}
					else if(i==4)
					{
						Department = inputStudentId;
					}
						
					writer.println(inputStudentId);
				}

			}
			writer.close();	
			input.close();

		} catch (Exception e) {

			System.out.println("File not found");
		}
	}

	
	public static void main(String[] args) {		
		Student student = new Student(0, "Null", "Null", "Null");
		student.loadStudents();
		student.studentDetailsById();
		student.computeGPAById();
		student.computeCGPAByID();
	}
}