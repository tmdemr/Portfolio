package iterator;



/**
 * 집합 객체(list, vector 등)에 접근하여 필요한 데이터를 얻는 기능 구현을 위한 인터페이스<p>
 * 
 * 집합 객체들의 내부 구조를 노출시키지 않고 원소들에 접근할 수 있도록
 * Iterator 패턴을 사용하여 구현하였음.<p>
 * 
 * @author 이태용
 *
 */

public interface Iterator {
	
	public abstract boolean hasNext() ;
	public abstract Object next() ;

}
