package src;

import java.util.HashMap;

public class Name {
String common;
String official;
HashMap<String,Language> nativeName ;
}

class Language{
	String official;
	String common;
}

class Language2{
	String name;
	String symbol;
}

class Idd{
	String root;
	String suffixes[];
}


class languagefm{
	String f;
	String m;
}

class Maps{
	String googleMaps;
	String openStreetMaps;
}
class Car{
	String signs[];
	String side;
}
class Flags{
	String png;
	String svg;
	String alt;

}
class CoatOfArms{
	String png;
	String svg;
}
class CapitalInfo{
	double latlng[];
}
class PostalCode{
	String format;
	String regex;
	
}