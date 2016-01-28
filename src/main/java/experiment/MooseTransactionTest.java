package experiment;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.client.ClientProtocolException;

public class MooseTransactionTest {

	public static void main(String args[]){
		String userName = "feroz";
		String passWord = "1";
		
		LoginTest loginTest = new LoginTest(userName,passWord);
		String sessionId = "";
		try {
			sessionId = loginTest.login();
			List<DealerTransaction> list = new ArrayList<DealerTransaction>();
			list.add(new DealerTransaction("USD",100,3));
			DealerTransactionTest dt = new DealerTransactionTest(list,userName,sessionId);
			dt.executeTransactions();
			
		} catch (Throwable e) {
			e.printStackTrace();
		} finally{
			LogoutTest logout = new LogoutTest(userName,sessionId);
			try {
				logout.execute();
			} catch (ClientProtocolException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
