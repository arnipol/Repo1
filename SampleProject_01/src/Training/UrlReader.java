package Training;
import java.net.*;
import java.io.*;

public class UrlReader {

	public static String readFromUrl(String url) throws IOException {
        URL oracle = new URL(url);;
        String output = new String();
        BufferedReader in = new BufferedReader(new InputStreamReader(oracle.openStream()));
        String inputLine = new String();
	    while ((inputLine = in.readLine()) != null)
	            output += inputLine + "\n";
	    in.close();
        return output;
	}
}
