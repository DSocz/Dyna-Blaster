package bomberman;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileNotFoundException;

/**
 *Klasa wczytujaca plik tekstowy Conf
 */
public class Wczytaj {
	/** przechowuje zawartosc pliku konfiguracyjnego */
	BufferedReader txt;
	
	/**
	 * Konstruktor klasy Wczytaj, wczytuje zawartosc pliku konfiguracyjnego, sprawdza czy jest pusty, jezli tak rzuca wyjatkiem
	 * @param Conf nazwa wczytywanego pliku konfiguracyjnego
	 */
	public Wczytaj(String Conf)
	{
		try
		{
			txt=new BufferedReader(new FileReader(Conf));
		}
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}
	}
	/**
	 * Metoda szukajaca zadanej frazy w pliku konfiguracyjnym, wczytuje ja, dzieli na pojedyncze wyrazy i wpisuje do tablicy stringow
	 * @param a szukana fraza 
	 * @return tablica stringow
	 */
	public String[] FindSection(String a)
	{
		/** linijka tekstu z pliku konfiguracyjnego */
		String str;
		while(true)
		{
			try{
				str= txt.readLine();
				if(str == null)
					throw new Exception("Blad");
				if (str == "")
					continue;
				if (str == "$")
					continue;
				str = str.replace(" = ", ":");
				str = str.replace(" ", ":");
				/** przechowuje oddzielone wyrazy wczytanej wczesniej linijki */
				String[] b = str.split(":", -2);
	            if (b[0].equals(a))
	            { return b;}
	                
	        	}   
			catch(Exception e)
        	{
        		e.printStackTrace();
        		System.out.println("Blad odczytu pliku");
   	
        	}
		}
	}
	
	
}
