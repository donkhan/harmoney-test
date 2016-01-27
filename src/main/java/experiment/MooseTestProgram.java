package experiment;



public class MooseTestProgram {
	
	public static void main(String args[]){
		String[] userNames = new String[]{"vteial"};
		String[] passWords = new String[]{"1"};
		
	
		for(int i = 0;i<userNames.length;i++){
			MooseTestThread mt = new MooseTestThread(userNames[i],passWords[i]);
			mt.start();
		}
	}

}
