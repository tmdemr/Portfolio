/**
 * Information의 상태정보를 저장하기 위한 Memento를 관리하기 위한 클래스
 * Stack -> ArrayList
 * final modify : 2019-05-26
 * author : Kim Kwang Ho
 */
package memento;

import java.util.Stack;

public class CareTaker {
	Stack<Memento> mementos = new Stack<>();
	public void push(Memento memento) {
		mementos.push(memento);
	}
	public Memento pop() {
		return mementos.pop();
	}
}
