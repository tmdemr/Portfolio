package observer;



public class TestDrive {

	public static void main(String[] args) {
		PRD prd = new PRD();
		
		ChangedDisplay changeddisplay = new ChangedDisplay(prd);
		
		prd.setMeasurements(1, "Lee", "School", 22);

	}

}
