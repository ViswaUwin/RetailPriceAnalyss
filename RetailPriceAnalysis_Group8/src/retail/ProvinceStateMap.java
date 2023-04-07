package retail;

import java.util.*;

public class ProvinceStateMap {
	
	static Map<String, List<String>> provinceStateMap = new HashMap<>();

	public static Map<String, List<String>> getProvinceStateMap() {
		return provinceStateMap;
	}

	private static String[] ALBERTA_STATES = {"Calgary", "Edmonton", "Fort McMurray",
			"Grande Prairie", "Jasper", "Lake Louise", "Lethbridge", "Medicine Hat", "Red Deer", "Saint Albert" };
	private static String[] BRITISH_COLUMBIA_STATES = { "Barkerville", "Burnaby", "Campbell River", "Chilliwack",
			"Courtenay", "Cranbrook", "Dawson Creek", "Delta", "Esquimalt", "Fort Saint James", "Fort Saint John",
			"Hope", "Kamloops", "Kelowna", "Kimberley", "Kitimat", "Langley", "Nanaimo", "Nelson", "New Westminster",
			"North Vancouver", "Oak Bay", "Penticton", "Powell River", "Prince George", "Prince Rupert", "Quesnel",
			"Revelstoke", "Rossland", "Trail", "Vancouver", "Vernon", "Victoria", "West Vancouver", "White Rock" };
	private static String[] MANITOBA_STATES = { "Brandon", "Churchill", "Dauphin", "Flin Flon", "Kildonan",
			"Saint Boniface", "Swan River", "Thompson", "Winnipeg", "York Factory" };
	private static String[] NEW_BRUNSWICK_STATES = { "Bathurst", "Caraquet", "Dalhousie", "Fredericton", "Miramichi",
			"Moncton", "Saint John" };
	private static String[] NEW_FOUND_LANDAND_LABRADOR_STATES = { "Argentia", "Bonavista", "Channel-Port aux Basques",
			"Corner Brook", "Ferryland", "Gander", "Grand Falls�Windsor", "Happy Valley�Goose Bay", "Harbour Grace",
			"Labrador City", "Placentia", "Saint Anthony", "St. John�s", "Wabana" };
	private static String[] NORTHWEST_TERRITORIES_STATES = { "Fort Smith", "Hay River", "Inuvik", "Tuktoyaktuk",
			"Yellowknife" };
	private static String[] NOVA_SCOTIA_STATES = { "Baddeck", "Digby", "Glace Bay", "Halifax", "Liverpool",
			"Louisbourg", "Lunenburg", "Pictou", "Port Hawkesbury", "Springhill", "Sydney", "Yarmouth" };
	private static String[] NUNAVUT_STATES = { "Iqaluit" };
	private static String[] ONTARIO_STATES = { "Bancroft", "Barrie", "Belleville", "Brampton", "Brantford",
			"Brockville", "Burlington", "Cambridge", "Chatham", "Chatham-Kent", "Cornwall", "Elliot Lake", "Etobicoke",
			"Fort Erie", "Fort Frances", "Gananoque", "Guelph", "Hamilton", "Iroquois Falls", "Kapuskasing",
			"Kawartha Lakes", "Kenora", "Kingston", "Kirkland Lake", "Kitchener", "Laurentian Hills", "London",
			"Midland", "Mississauga", "Moose Factory", "Moosonee", "Niagara Falls", "Niagara-on-the-Lake", "North Bay",
			"North York", "Oakville", "Orillia", "Oshawa", "Ottawa", "Parry Sound", "Perth", "Peterborough", "Picton",
			"Port Colborne", "Saint Catharines", "Saint Thomas", "Sarnia-Clearwater", "Sault Sainte Marie",
			"Scarborough", "Simcoe", "Stratford", "Sudbury", "Temiskaming Shores", "Thorold", "Thunder Bay", "Timmins",
			"Toronto", "Trenton", "Waterloo", "Welland", "West Nipissing", "Windsor", "Woodstock", "York" };
	private static String[] PRINCE_EDWARD_ISLAND_STATES = { "Borden", "Cavendish", "Charlottetown", "Souris",
			"Summerside" };
	private static String[] QUEBEC_STATES = { "Asbestos", "Baie-Comeau", "Beloeil", "Cap-de-la-Madeleine", "Chambly",
			"Charlesbourg", "Ch�teauguay", "Chibougamau", "C�te-Saint-Luc", "Dorval", "Gasp�", "Gatineau", "Granby",
			"Havre-Saint-Pierre", "Hull", "Jonqui�re", "Kuujjuaq", "La Salle", "La Tuque", "Lachine", "Laval", "L�vis",
			"Longueuil", "Magog", "Matane", "Montreal", "Montr�al-Nord", "Perc�", "Port-Cartier", "Quebec", "Rimouski",
			"Rouyn-Noranda", "Saguenay", "Saint-Eustache", "Saint-Hubert", "Sainte-Anne-de-Beaupr�", "Sainte-Foy",
			"Sainte-Th�r�se", "Sept-�les", "Sherbrooke", "Sorel-Tracy", "Trois-Rivi�res", "Val-d�Or", "Waskaganish" };
	private static String[] SASKATCHEWAN_STATES = { "Batoche", "Cumberland House", "Estevan", "Flin Flon", "Moose Jaw",
			"Prince Albert", "Regina", "Saskatoon", "Uranium City" };
	private static String[] YUKON_STATES = { "Dawson", "Watson Lake", "Whitehorse", "county", "Maine", "Quebec",
			"Canada", "Kennebec", "Appalachian National Scenic Trail" };

	public ProvinceStateMap() {
		// Create a map to store province and its corresponding states
		
		// Add some sample data to the map
		List<String> albertaStates = new ArrayList<>(Arrays.asList(ALBERTA_STATES));
		provinceStateMap.put("AB", albertaStates);

		List<String> britishColumbiaStates = new ArrayList<>(Arrays.asList(BRITISH_COLUMBIA_STATES));
		provinceStateMap.put("BC", britishColumbiaStates);

		List<String> manitobaStates = new ArrayList<>(Arrays.asList(MANITOBA_STATES));
		provinceStateMap.put("MB", manitobaStates);

		List<String> newBrunswickStates = new ArrayList<>(Arrays.asList(NEW_BRUNSWICK_STATES));
		provinceStateMap.put("NB", newBrunswickStates);

		List<String> newfoundlandAndLabradorStates = new ArrayList<>(Arrays.asList(NEW_FOUND_LANDAND_LABRADOR_STATES));
		provinceStateMap.put("NL", newfoundlandAndLabradorStates);

		List<String> northwestTerritoriesStates = new ArrayList<>(Arrays.asList(NORTHWEST_TERRITORIES_STATES));
		provinceStateMap.put("NT", northwestTerritoriesStates);

		List<String> novaScotiaStates = new ArrayList<>(Arrays.asList(NOVA_SCOTIA_STATES));
		provinceStateMap.put("NS", novaScotiaStates);

		List<String> nunavutStates = new ArrayList<>(Arrays.asList(NUNAVUT_STATES));
		provinceStateMap.put("NU", nunavutStates);

		List<String> ontarioStates = new ArrayList<>(Arrays.asList(ONTARIO_STATES));
		provinceStateMap.put("ON", ontarioStates);

		List<String> princeEdwardIslandStates = new ArrayList<>(Arrays.asList(PRINCE_EDWARD_ISLAND_STATES));
		provinceStateMap.put("PE", princeEdwardIslandStates);

		List<String> quebecStates = new ArrayList<>(Arrays.asList(QUEBEC_STATES));
		provinceStateMap.put("QC", quebecStates);

		List<String> saskatchewanStates = new ArrayList<>(Arrays.asList(SASKATCHEWAN_STATES));
		provinceStateMap.put("SK", saskatchewanStates);

		List<String> yukonStates = new ArrayList<>(Arrays.asList(YUKON_STATES));
		provinceStateMap.put("YT", yukonStates);

	}
}
