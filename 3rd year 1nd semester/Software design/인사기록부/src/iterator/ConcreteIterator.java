package iterator;


/**
 * 이터레이터 기능 구현 클래스.<p>
 * 
 * @param EmployeeList 데이터 집합 객체
 * @param index 데이터 집합의 현재 인덱스
 * @author 이태용
 *
 *	
 */
class ConcreteIterator implements Iterator {
	private EmployeeList employeeList;
	int index;

	/**
	 * 
	 * 
	 * @param employeeList
	 */
	public ConcreteIterator(EmployeeList employeeList) {
		this.employeeList = employeeList;
		this.index = 0;
	}
	

	
	/**
	 * 데이터 집합의 다음 인덱스 요소를 받아오는 메소드
	 * 
	 * @return Object
	 * 
	 * @see iterator.Iterator#next()
	 */
	public Object next() {
		Employee data  = employeeList.getData(index);
		index = index + 1;
		return data;

	}
	
	
	
	/** 
	 * 데이터 집합의 다음 요소가 있다면 true, 없다면 false 반환
	 * 
	 * @return boolean
	 * @see iterator.Iterator#hasNext()
	 */
	public boolean hasNext() {
		if (index >= employeeList.getLength()) {
			return false;
		}else {
			return true;
		}
		
	}


}
