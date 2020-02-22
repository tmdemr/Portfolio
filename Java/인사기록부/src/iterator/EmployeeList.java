package iterator;


/**
 * 	DataSet의 집합체 역할을 하는 클래스.
 * 	DataSet의 Size를 정하는 기능과 Size를 반환하는 기능, 집합체 내부의 특정 인덱스 값에 대한 Element 반환에 대한 기능을 가지고 있음<p>
 * 	
 * 
 * 	@param employee DataSet의 집합(배열)
 * 	@param index 데이터 집합의 현재 인덱스
 *	<p>
 * 	@author 이태용
 *
 *	@see iterator.EmployeeList#ConcreteAggregate(int)
 *	@see iterator.EmployeeList#getLength()
 *	@see iterator.EmployeeList#getData(int)
 *
 */
public class EmployeeList implements Aggregate{
	private Employee[] employee; //Employee의 리스트
	private int index = 0;
	
	/**
	 * 데이터 집합의 사이즈를 지정하는 생성자
	 * 
	 * 사용 방법 :
	 * <pre>
	 * 	ConcreteAggregate example = new ConcreteAggregete(maxSize);
	 * </pre>
	 * 
	 * @param maxSize 데이터 집합의 Size를 의미하는 변수
	 * 
	 */
	public EmployeeList(int maxSize) {
		this.employee = new Employee[maxSize];
	}
	
	
	
	public Iterator iterator() {
		return new ConcreteIterator(this);
	}
	
	
	public Employee getData(int index) {
		return employee[index];
	}
	
	
	/**
	 * @return 현재 데이터 집합의 사이즈를 반환함
	 */
	public int getLength() {
		return index;
	}
	
	public void addStaff(Employee data) {
		//이 부분은 인사 기록부의 insert 기능으로 대체될 것으로 전망됨.
		this.employee[index] = data;
		index++;
	}


}
