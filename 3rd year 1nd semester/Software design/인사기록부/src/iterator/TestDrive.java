package iterator;

public class TestDrive {
	public static void main(String args[]) {
		EmployeeList employeeList = new EmployeeList(3);
		employeeList.addStaff(new Employee("마","asd","asd","asd","asd","asd","asd","집","asd"));
		employeeList.addStaff(new Employee("카","asd","asd","asd","asd","asd","asd","학교","asd"));
		employeeList.addStaff(new Employee("오","asd","asd","asd","asd","asd","asd","회사","asd"));
		
		ConcreteIterator iterator = (ConcreteIterator) employeeList.iterator();
		
		while(iterator.hasNext()) {
			Employee data = (Employee)iterator.next();
			System.out.println("이름 :" + data.getName() );
			System.out.println("주소 :" + data.getAddress() );

		}
	}
	
}
