package iterator;


/**
 *
 *	인사기록부 데이터 형식 지정을 위한 클래스<p>
 *
 *	사용 방법 :
 *	<pre>
 *		Employee data = new Employee(String name,	String department, String employeeYear, String tel, String presureOfBusiness, String grade, String salary, String address, String performanceEvaluation)
 *		data.getName(); // 데이터 내부의 필요한 요소를 얻어올 때 getElement() 메소드 사용
 *	</pre>
 *	
 *	@param name	이름
 *	@param department	부서
 *	@param employeeYear	호봉
 *	@param tel	전화번호
 *	@param presureOfBusiness	사번
 *	@param grade	직급
 *	@param salary	연봉
 *	@param address	주소
 *	@param performanceEvaluation	인사고과
 *	<p>
 * 
 *
 *	@author 이태용
 */

public class Employee {
	String name;
	String department;
	String employeeYear;
	String tel;
	String presureOfBusiness; //사번
	String grade;
	String salary;
	String address;
	String performanceEvaluation; // 인사고과
	
	public Employee(String name,	String department, String employeeYear, String tel, String presureOfBusiness, String grade, String salary, String address, String performanceEvaluation) {
		this.name = name;
		this.department = department;
		this.employeeYear = employeeYear;
		this.tel = tel;
		this.presureOfBusiness = presureOfBusiness;
		this.grade = grade;
		this.salary = salary;
		this.address = address;
		this.performanceEvaluation = performanceEvaluation;
	}
	
	public String getName() {
		return name;
	}
	
	public String getDepartment() {
		return department;
	}
	
	public String getEmployeeYear() {
		return employeeYear;
	}
	
	public String getTel() {
		return tel;
	}
	
	public String getPresureOfBusiness() {
		return presureOfBusiness;
	}
	
	public String getGrade() {
		return grade;
	}
	
	public String getSalary() {
		return salary;
	}
	
	public String getAddress() {
		return address;
	}
	
	public String getPerformanceEvaluation() {
		return performanceEvaluation;
	}
	
	
	
	
}
