package guigui;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.util.*;

public class CreatJTable {

	public CreatJTable(List<String[]> arrayList) {
		JFrame jFrame = new JFrame("탐색 결과");

		String columnNames[] = // 열 이름
				{"병원이름", "병원 분류", "도로명 주소", "전화번호"};

		JTable jTable = new JTable(arrayList.toArray(new Object[][]{}), columnNames); // 테이블을 생성하는 부분. 리스트를 오브젝트 2차원 배열로 바꾸어줌
		JScrollPane jScollPane = new JScrollPane(jTable);

		jFrame.pack();
		jFrame.add(jScollPane);

		jFrame.setSize(1000, 300);
		jFrame.setVisible(true);
		//jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}
}