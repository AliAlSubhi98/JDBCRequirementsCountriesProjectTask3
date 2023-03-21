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