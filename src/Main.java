
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.io.BufferedReader;
import java.util.List;
import java.util.ArrayList;
import java.time.temporal.ChronoUnit;

import java.time.format.DateTimeFormatter;

public class Main {
	 
	public static void main(String[] args) {
		
		DateTimeFormatter dateFormat=DateTimeFormatter.ofPattern("yyyy-MM-dd");
		List<Employee> emps= new ArrayList<Employee>();
		
		try {
			
		BufferedReader br = new BufferedReader( new FileReader( "employees.txt" ) );
		String line =br.readLine();
		
		int tempID,tempProjectID;
		LocalDate start,end;
		
		while(line!=null) {
			
			
			String lines[] = line.split(", ");
			tempID = Integer.parseInt(lines[0]);
			tempProjectID = Integer.parseInt(lines[1]);
			start = LocalDate.parse(lines[2], dateFormat);
			
			if(lines[3].trim().equals("NULL")) {
				end=LocalDate.now(); 
				
			}
			else {
				
				end = LocalDate.parse(lines[3], dateFormat);
			}
			
			if(emps.contains(new Employee(tempID))) {
				
				emps.get(emps.indexOf(new Employee(tempID))).addProject(new Project(tempProjectID,start, end));
			}
			else {
				emps.add(new Employee(tempID));
				emps.get(emps.size()-1).addProject(new Project(tempProjectID,start, end));
			}
			
			
			line=br.readLine();
			}
		br.close();
		}
		catch(FileNotFoundException e) {
			System.out.println("employees.txt not found");
		}
		catch(java.util.InputMismatchException e) {
			System.out.println("File not formated properly");
			
		}
		catch(IOException e) {
			System.out.println("Buffered reader had problems");
			
		}
		finally {
			int empID1 = 0, empID2 = 0;
			long tempSum = 0 ,maxSum = 0;
			//iterating over all employees without being redundant
			for(int i=0;i<emps.size()-1;i++) {
				for(int j=i+1;j<emps.size();j++){
					//iterating over all their projects to find matching
					for(int x=0;x<emps.get(i).getProjectCount();x++) {
						for(int y=0;y<emps.get(j).getProjectCount();y++) {
							//if there is the same project?
							if(emps.get(i).getProject(x).equals(emps.get(j).getProject(y))) {
								//are the times of the projects overlapping?
								if(!emps.get(i).getProject(x).getStartDate().isAfter(emps.get(j).getProject(y).getEndDate()) 
									|| !emps.get(i).getProject(x).getEndDate().isBefore(emps.get(j).getProject(y).getStartDate())) {
									
								    //comparing starting dates of the 2 projects,
									//if start date of first is after start date of second
									if(emps.get(i).getProject(x).getStartDate().isAfter(emps.get(j).getProject(y).getStartDate())){
										//if end date1 is before end date2
										if(emps.get(i).getProject(x).getEndDate().isBefore(emps.get(j).getProject(y).getEndDate())) {
											tempSum+=ChronoUnit.DAYS.between(emps.get(i).getProject(x).getStartDate(),emps.get(i).getProject(x).getEndDate());
										}
										//if end date1 is after end date2 or equal
										else {
											//start1 to end2 or end1 as end2 is before end1 or end1 and end2 are equal
											tempSum+=ChronoUnit.DAYS.between(emps.get(i).getProject(x).getStartDate(),emps.get(j).getProject(y).getEndDate());
										}
									}
									// if start date of first is before or equal to start date of second
									else {
										//if end date1 is before end date2
										if(emps.get(i).getProject(x).getEndDate().isBefore(emps.get(j).getProject(y).getEndDate())) {
											//start1 to end1
											tempSum+=ChronoUnit.DAYS.between(emps.get(j).getProject(y).getStartDate(),emps.get(i).getProject(x).getEndDate());
										}
										//start2 to end2 or end1 as end2 is before end1 or end1 and end2 are equal
										else {
											tempSum+=ChronoUnit.DAYS.between(emps.get(j).getProject(y).getStartDate(),emps.get(j).getProject(y).getEndDate());
										}
										
									}
							 
								}
								
							}
							
							
						}
					}
					
					if(tempSum>maxSum) {
						maxSum=tempSum;
						empID1=emps.get(i).getEmpID();
						empID2=emps.get(j).getEmpID();
					}
					
					tempSum=0;
				}
			}
			if(empID1!=0 && empID2!=0) {
			System.out.printf("Employee 1 : %d \nEmployee 2 : %d \nDays worked together : %d \n~~~~~~~~~~~~~~~~~~~~~~~~~\n",empID1,empID2,maxSum);
			System.out.printf("Number of employees : %d \n",emps.size());
			//test
			for(Employee e : emps) {
				System.out.println(e);
			}
			}
			else {
				System.out.println("There are no employees that worked together");
			}
			
		}
		
	}

}
