import java.io.*;
import java.net.*;
import javax.swing.JOptionPane;

public class Get {
	public static void main(String[] args) {
		String userStockName = "";
		userStockName = JOptionPane.showInputDialog("Enter your stock's symbol: ");
		try {
			GetRequest(userStockName);
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Error Setting Up GET Request");
		}

	}
	public static void GetRequest(String stockName) throws IOException{
		URL urlForGetRequest = new URL("https://www.alphavantage.co/query?function=GLOBAL_QUOTE&symbol="+stockName+"&apikey=GXASNYI920XFTKNS");
		String readLine = null;
		HttpURLConnection connection = (HttpURLConnection) urlForGetRequest.openConnection();
		connection.setRequestMethod("GET");
		int responseCode = connection.getResponseCode();
		//Checks if the code of the response is valid
		if (responseCode == HttpURLConnection.HTTP_OK) {
			//Following code formats the response so it is outputable
			BufferedReader in = new BufferedReader(
					new InputStreamReader(connection.getInputStream()));
			StringBuffer response = new StringBuffer();
			while((readLine = in .readLine()) != null) {
				response.append(readLine);
				response.append("\r\n");
			}in .close();
			//Output Response
			System.out.println("String Result "+ response.toString());
		}
		else {
			System.out.println("GET ERROR | Response Code = "+responseCode);
		}
	}

}
