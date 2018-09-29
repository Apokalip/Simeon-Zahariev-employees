
import java.time.*;


public class Project {
	private int ID;
	private LocalDate start;
	private LocalDate end;
	
	Project(int ID, LocalDate start){
		this.ID=ID;
		this.start=start;
		this.end=LocalDate.now();
	}
	Project(int ID, LocalDate start, LocalDate end){
		this.ID=ID;
		this.start=start;
		this.end=end;
	}
	
	public int getID() {
		return ID;
	}
	public void setID(int ID) {
		this.ID=ID;
	}
	
	public LocalDate getStartDate() {
		return start;
	}
	public void setStartDate(LocalDate start) {
		this.start=start;
	}
	
	public LocalDate getEndDate() {
		return end;
	}
	public void setEndDate(LocalDate end) {
		this.end=end;
	}
	@Override
	public String toString() { 
	    return "projectID:"+ID+", start:"+start+", end:"+end;
	} 
	@Override
	public boolean equals(Object obj) {
		if(obj==null) {
			return false;
		}
		
		 if (!(obj instanceof Project)) {
		        return false;
		 }
		 
		 if(obj==this) {
			 return true;
		 }
		 
		 return this.getID() == ((Project) obj).getID();
	
	}
}
