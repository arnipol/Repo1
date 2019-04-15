package Training;
import java.net.*;
import java.util.ArrayList;
import java.util.List;
import java.io.*;

public class UrlReader {

	public static String readFromUrl(String url) throws IOException {
		//url = HtmEncoder(url);		
        URL oracle = new URL(url);
        String output = new String();
        BufferedReader in = new BufferedReader(new InputStreamReader(oracle.openStream()));
        String inputLine = new String();
	    while ((inputLine = in.readLine()) != null)
	            output += inputLine + "\n";
	    in.close();
        return output;
	}



	static String HtmEncoder(String txt)
    {
          char[] _chars = new char[] { '!', '#', '$', '%', '&', '\'', '(', ')', '*', '+', ',', '-', '.', '/', ':', ';', '<', '=', '>', '?', '@', '{', '|', '}', '~' };
          List<Character> chars = new ArrayList<>();
          for (char c : _chars) chars.add(c);

          String[] charstr = new String[] { "%21", "%23", "%24", "%25", "%26", "%27", "%28", "%29", "%2A", "%2B", "%2C", "%2D", "%2E", "%2F", "%3A", "%3B", "%3C", "%3D", "%3E", "%3F", "0.4", "%7B", "%7C", "%7D", "%7E" };
          String etxt = new String();
          for (char c : txt.toCharArray())
          {
              int idx =  chars.indexOf(c);
              if (idx >= 0) etxt += charstr[idx];
              else etxt += c;
          }
          return etxt;
    }
}
