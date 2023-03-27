package src;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

import com.google.gson.Gson;

public class APIConsumer {
	static ArrayList<MyObject> countries = new ArrayList<MyObject>(); 
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
					System.out.println("cioc: " + ObjList.cioc);
					System.out.println("independent: " + ObjList.independent);
					System.out.println("status: " + ObjList.status);
					System.out.println("unMember: " + ObjList.unMember);
					if (ObjList.currencies != null) {
						for (String key : ObjList.currencies.keySet()) {
							System.out.println(key + " name: " + ObjList.currencies.get(key).name + " symbol: "
									+ ObjList.currencies.get(key).symbol);
						}
					}
					System.out.println("root: " + ObjList.idd.root);
					if (ObjList.idd.suffixes != null) {
						for (String idd : ObjList.idd.suffixes) {
							System.out.println("suffixes: " + idd);
						}
					}
					if (ObjList.capital != null) {
						for (String capital : ObjList.capital) {
							System.out.println("capital: " + capital);
						}
					}
					if (ObjList.altSpellings != null) {
						for (String altSpellings : ObjList.altSpellings) {
							System.out.println("altSpellings: " + altSpellings);
						}
					}
					System.out.println("region: " + ObjList.region);
					System.out.println("subregion: " + ObjList.subregion);
					for (String key : ObjList.languages.keySet()) {
						System.out.println(key + " : " + ObjList.languages.get(key));
					}

					for (String key : ObjList.translations.keySet()) {
						System.out.println(key + "\nofficial: " + ObjList.translations.get(key).official + ",\ncommon: "
								+ ObjList.translations.get(key).common);
					}
					if (ObjList.latlng != null) {
						System.out.println("latlng: ");
						for (double latlng : ObjList.latlng) {
							System.out.println(latlng);
						}
					}
					System.out.println("landlocked: " + ObjList.landlocked);
					if (ObjList.borders != null) {
						System.out.println("borders: ");
						for (String borders : ObjList.borders) {
							System.out.println(borders);
						}
					}
					System.out.println("area: " + ObjList.area);

					if (ObjList.demonyms != null) {
						for (String key : ObjList.demonyms.keySet()) {
							System.out.println(
									key + " f: " + ObjList.demonyms.get(key).f + " m: " + ObjList.demonyms.get(key).f);
						}
					}
					System.out.println(ObjList.flag);
					System.out.println(ObjList.maps.googleMaps + "\n" + ObjList.maps.openStreetMaps);
					System.out.println("population: " + ObjList.population);

					if (ObjList.gini != null) {
						for (String key : ObjList.gini.keySet()) {
							System.out.println("gini: " + key + " " + ObjList.gini.get(key));
						}
					}

					System.out.println("fifa: " + ObjList.fifa);

					if (ObjList.car.signs != null) {
						for (String car : ObjList.car.signs) {
							System.out.println("car signs: " + car);
						}
					}
					System.out.println(ObjList.car.side);

					System.out.println("timezones: ");
					for (String timezones : ObjList.timezones) {
						System.out.println(timezones);
					}

					for (String continents : ObjList.continents) {
						System.out.println("continents: " + continents);
					}

					System.out.println("flags");
					System.out.println(ObjList.flags.png);
					System.out.println(ObjList.flags.svg);
					System.out.println(ObjList.flags.alt);

					System.out.println("coatOfArms:");
					System.out.println(ObjList.coatOfArms.png);
					System.out.println(ObjList.coatOfArms.svg);

					System.out.println("startOfWeek: " + ObjList.startOfWeek);

					if (ObjList.capitalInfo.latlng != null) {
						for (double latlng : ObjList.capitalInfo.latlng) {
							System.out.println(latlng);
						}
					}
					if(ObjList.postalCode != null) {
					System.out.println(ObjList.postalCode.format);
					System.out.println(ObjList.postalCode.regex);
					}
					
					countries.add(ObjList);
					System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------");
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
	String cioc;
	boolean independent;
	String status;
	boolean unMember;
	HashMap<String, Language2> currencies;
	Idd idd;
	String capital[];
	String altSpellings[];
	String region;
	String subregion;
	HashMap<String, String> languages;
	HashMap<String, Language> translations;
	double latlng[];
	boolean landlocked;
	String borders[];
	double area;
	HashMap<String, languagefm> demonyms;
	String flag;
	Maps maps;
	int population;
	HashMap<String, Float> gini;
	String fifa;
	Car car;
	String timezones[];
	String continents[];
	Flags flags;
	CoatOfArms coatOfArms;
	String startOfWeek;
	CapitalInfo capitalInfo;
	PostalCode postalCode;
}