
import java.util.List;
import java.util.ArrayList;

public class Employee {
	private int empID;
	private List<Project> projects= new ArrayList<Project>();
	
	public Employee(int ID){
		empID=ID;
	}
	
	public Employee(int ID, List<Project> projects){
		empID=ID;
		this.projects=projects;
	}	
	
	public int getEmpID() {
		return empID;
	}
	public void setEmpID(int ID) {
		empID=ID;
	}
	
	public List<Project> getProjects(){
		return projects;
	}
	public Project getProject(int index) {
		if(index>projects.size()-1) {
			return null;
		}
		return projects.get(index);
	}
	public int getProjectCount() {
		return projects.size();
	}
	public void setProjects(List<Project> projects) {
		this.projects=projects;
	}
	public void addProject(Project p) {
		projects.add(p);
	}
	@Override
	public String toString() {
		return "employeeID: " + empID + " " + projects.toString();
	}
	@Override
	public int hashCode() {
		return empID;
	}
	@Override
	public boolean equals(Object obj) {
		if(obj==null) {
			return false;
		}
		
		 if (!(obj instanceof Employee)) {
		        return false;
		 }
		 
		 if(obj==this) {
			 return true;
		 }
		 
		 return this.getEmpID() == ((Employee) obj).getEmpID();
	}
}
