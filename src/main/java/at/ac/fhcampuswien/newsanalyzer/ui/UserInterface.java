package at.ac.fhcampuswien.newsanalyzer.ui;


import at.ac.fhcampuswien.newsanalyzer.ctrl.Controller;
import at.ac.fhcampuswien.newsapi.NewsApi;
import at.ac.fhcampuswien.newsapi.NewsApiBuilder;
import at.ac.fhcampuswien.newsapi.beans.Article;
import at.ac.fhcampuswien.newsapi.beans.NewsResponse;
import at.ac.fhcampuswien.newsapi.enums.Category;
import at.ac.fhcampuswien.newsapi.enums.Country;
import at.ac.fhcampuswien.newsapi.enums.Endpoint;
import at.ac.fhcampuswien.newsapi.enums.Language;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Scanner;

public class UserInterface 
{
	private Controller ctrl = new Controller();

	public void getDataFromCtrl1(){
		System.out.println("ABC");
		NewsApi newsApi = new NewsApiBuilder()
				.setApiKey(Controller.APIKEY)
				.setQ("krebs")
				.setEndPoint(Endpoint.TOP_HEADLINES)
				.setSourceCountry(Country.at)
				.setSourceCategory(Category.health)
				.createNewsApi();
		ctrl.process(newsApi);
	}

	public void getDataFromCtrl2(){
		// TODO implement me
		System.out.println("DEF - Medizin");
		NewsApi newsApi = new NewsApiBuilder()
				.setApiKey(Controller.APIKEY)
				.setQ("corona")
				.setEndPoint(Endpoint.TOP_HEADLINES)
				.setSourceCountry(Country.at)
				.setSourceCategory(Category.health)
				.setLanguage(Language.de)
				.createNewsApi();
		ctrl.process(newsApi);
	}

	public void getDataFromCtrl3(){
		// TODO implement me
		System.out.println("3");
		NewsApi newsApi = new NewsApiBuilder()
				.setApiKey(Controller.APIKEY)
				.setQ("apple")
				.setEndPoint(Endpoint.TOP_HEADLINES)
				.setSourceCountry(Country.at)
				.setSourceCategory(Category.technology)
				.createNewsApi();
		ctrl.process(newsApi);
	}
	
	public void getDataForCustomInput() {
		String topic = "";
		String country = "";
		String category = "";
		int i = 0;
		Scanner x = new Scanner(System.in);
		System.out.println("Bitte gegeben Sie die gewünschte Category ein: ");
		for(Category cat : Category.values()){
			i++;
			System.out.println(i + " " + cat);
		}
		int variable = x.nextInt();
		switch (variable){
			case 1:
				category = "business";
				break;
			case 2:
				category = "entertainment";
				break;
			case 3:
				category = "health";
				break;
			case 4:
				category = "science";
				break;
			case 5:
				category = "sports";
				break;
			case 6:
				category = "technology";
				break;
			default:
				System.out.println("You messed it up");
		}
		System.out.println("Choose a Country: \n 1: at\n 2: de\n 3: us\n 4: cz");
		variable = x.nextInt();
		switch (variable){
			case 1:
				country = "at";
				break;
			case 2:
				country = "de";
				break;
			case 3:
				country = "us";
				break;
			case 4:
				country = "cz";
				break;
			default:
				System.out.println("You messed it up");
		}
		System.out.println("please enter Topic");
		topic = x.next();

		// TODO implement me
		NewsApi newsApi = null;
		try{
			newsApi = new NewsApiBuilder()
					.setApiKey(Controller.APIKEY)
					.setQ(""+topic)
					.setEndPoint(Endpoint.TOP_HEADLINES)
					.setSourceCountry(Country.valueOf(country))
					.setSourceCategory(Category.valueOf(category))
					.createNewsApi();
		}catch(Exception e){
			System.err.println("YOU MESSED IT UP AGAIN");
		}
		ctrl.process(newsApi);
	}


	public void start() {
		Menu<Runnable> menu = new Menu<>("User Interface");
		menu.setTitle("Wählen Sie aus:");
		menu.insert("a", "Choice ABC", this::getDataFromCtrl1);
		menu.insert("b", "Choice DEF", this::getDataFromCtrl2);
		menu.insert("c", "Choice 3", this::getDataFromCtrl3);
		menu.insert("d", "Choice User Input:",this::getDataForCustomInput);
		menu.insert("q", "Quit", null);
		Runnable choice;
		while ((choice = menu.exec()) != null) {
			 choice.run();
		}
		System.out.println("Program finished");
	}


    protected String readLine() {
		String value = "\0";
		BufferedReader inReader = new BufferedReader(new InputStreamReader(System.in));
		try {
			value = inReader.readLine();
        } catch (IOException ignored) {
		}
		return value.trim();
	}

	protected Double readDouble(int lowerlimit, int upperlimit) 	{
		Double number = null;
        while (number == null) {
			String str = this.readLine();
			try {
				number = Double.parseDouble(str);
            } catch (NumberFormatException e) {
                number = null;
				System.out.println("Please enter a valid number:");
				continue;
			}
            if (number < lowerlimit) {
				System.out.println("Please enter a higher number:");
                number = null;
            } else if (number > upperlimit) {
				System.out.println("Please enter a lower number:");
                number = null;
			}
		}
		return number;
	}
}
