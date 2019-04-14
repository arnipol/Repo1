package Training;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;

public class Endomondo {

	public String Email;
	public String Password;
	public String DisplayName;
	public String AuthToken;
	public String UserId;

    final String EndomondoAPIUrl = "https://api.mobile.endomondo.com/mobile";
	
    public boolean Authenticate() 
	{

		boolean result = false;
        Email = "arnipol@poczta.onet.pl";
        Password = HtmEncoder("sp4xko#1");

		String authUrl = String.format("%s/auth?deviceId=dummy&email=%s&password=%s&country=US&action=PAIR",
			 EndomondoAPIUrl, this.Email, this.Password);
		
		String str = new String();
		try {
			str = UrlReader.readFromUrl(authUrl);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			return false;
		}

		if (str != null)
		{
			String[] lines = str.split("\n");
			if (lines[0].equals("OK") == false)
			{				
				// TODO: Error
			}
			else
			{
				for (String line: lines)
				{			
					String[] values = line.split("=");
					if (values.length == 2)
					{
						switch (values[0])
						{
							case "authToken":
								this.AuthToken = values[1];
								result = true;
								break;
							case "displayName":
								this.DisplayName = values[1];
								break;
							case "userId":
								this.UserId = values[1];
								break;
							default:
								// Nothing
								break;
						}
					}
				}
			}
		}
		return result;
	}

    
    public WorkoutList ListWorkouts(int maxResults)
	{
		String listUrl = String.format("%s/api/workout/list?authToken=%s&maxResults=%s",
			 EndomondoAPIUrl, this.AuthToken, maxResults);
		String str;
		try {
			str = UrlReader.readFromUrl(listUrl);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			return null;
		}
    	Jsonb jsonb = JsonbBuilder.create();
    	WorkoutList workoutlist = jsonb.fromJson(str, WorkoutList.class);
        return workoutlist;
	}
    
    
    
    
    
    
	String HtmEncoder(String txt)
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
