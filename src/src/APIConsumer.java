package src;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import com.google.gson.Gson;

public class APIConsumer {
	static void showApi() {
		String apiUrl = "https://restcountries.com/v3.1/all";
		try {
			URL url = new URL(apiUrl);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Accept", "application/json");

			if (conn.getResponseCode() != 200) {
				throw new RuntimeException("HTTP error code : " + conn.getResponseCode());
			}

			BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));
			String output;
			StringBuilder json = new StringBuilder();

			while ((output = br.readLine()) != null) {
				json.append(output);
			}

			conn.disconnect();

			Gson gson = new Gson();
			MyObject myObj[] = gson.fromJson(json.toString(), MyObject[].class);

			// Use myObj for further processing
			for (MyObject ObjList : myObj) {
				System.out.println(ObjList.name.common);
				System.out.println(ObjList.name.official);
				if (ObjList.name.nativeName != null) {
					for (String key : ObjList.name.nativeName.keySet()) {
						System.out.println(key + ": Official: " + ObjList.name.nativeName.get(key).official
								+ " Common: " + ObjList.name.nativeName.get(key).common);
					}
					if (ObjList.tld != null) {
						for (String tld : ObjList.tld) {
							System.out.println("tld: " + tld);
						}
					}
					System.out.println("cca2: " + ObjList.cca2);
					System.out.println("ccn3: " + ObjList.ccn3);
					System.out.println("cca3: " + ObjList.cca3);
					System.out.println("independent: " + ObjList.independent);
					System.out.println("status: " + ObjList.status);
					System.out.println("unMember: " + ObjList.unMember);

					System.out.println("--------------");

				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

class MyObject {
	Name name;
	String tld[];
	String cca2;
	String ccn3;
	String cca3;
	boolean independent;
	String status;
	boolean unMember;
	
}