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
    public String EndomondoAPIUrl;

    public Endomondo () {
    	Email = new String();
    	Password = new String();
    	DisplayName = new String();
    	AuthToken = new String();
    	UserId = new String();
    	EndomondoAPIUrl = "https://api.mobile.endomondo.com/mobile";
    }

    public Endomondo (String ApiUrl) {
        this();
    	EndomondoAPIUrl = ApiUrl;
    }

    public boolean Authenticate()
	{

		boolean result = false;
        Email = "arnipol@poczta.onet.pl";
        Password = "sp4xko#1";
        Password = UrlReader.HtmEncoder(Password);
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


}
