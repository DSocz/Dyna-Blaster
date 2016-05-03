package bomberman;

import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;

import java.awt.*;

import javax.swing.*;
/**Klasa tworzaca okno z tablica Najlepszych Wynikow */
public class TabelaWynikow extends JFrame { 
	
			/** Obiekt klasy Wczytaj umozliwiajacy odczytanie danych z pliku Wyniki.txt */
			Wczytaj Wyniki_plik = new Wczytaj("Wyniki.txt");
			/** Tablica int-ow przechowujaca odczytane z pliku punkty poprzednich graczy */
	  		int[] punkty;
	  		/** Tablica String-ow przechowujaca odczytane z pliku niki poprzednich graczy */
	  		String[] Login;
	  		/** Ilosc linijek odczytywanych danych */
	  		int ilosc;
	  		
	  		/** Konstruktor klasy TabelaWynikow, stworzenie okna z Najlepszymi Wynikami */
			TabelaWynikow(String a) 
			{
		    super("Najlepsze Wyniki");
		    setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		    pack();
		    setSize(150, 50);
			setLocation(50,50);
			setLayout(new GridLayout(10, 1));
			ilosc = 10;			
		    setSize(450,600);
		  } 
			/** Metoda dodajaca do okna Najwyzszych wynikow etykiety z odczytanymi danymi */
	  		public void Wyswietl_wyniki()
	  		{

				for(int i=0;i<=ilosc-1; i++)
				{
				    add(new JLabel((i+1)+". " + Login[i] + "  -  " + punkty[i] + "pkt" ));
				}
	  		}
	  		/** Metoda odczytujaca dane z pliku Wyniki.txt i zapisujaca je w odpowiednich tablicach */
			public void Odzczytaj_wyniki()
	  		{
				String xyz;
	  			String[] dane_string,ilosc1;
	  			punkty = new int[11];
	  			Login = new String[11];
	  			
	  			for(int i = 1;i<=ilosc;i++)
	  			{
	  				xyz = String.valueOf(i);
	  				dane_string = Wyniki_plik.FindSection(xyz);
	  				punkty[i-1] =Integer.parseInt(dane_string[2]);
	  				Login[i-1]=dane_string[1];		
	  			}
	  		}
			/** Metoda sortujace dane wczesniej wczytane z pliku Wyniki.txt wlaczajaca nowe dane z odbytej gry
			 * @param ilosc punktow zdobytych przez gracza
			 * @param imie gracza  */
			public void sortowanie(int a, String b)
			{
					punkty[ilosc] = a;
					Login[ilosc] = b;
					int temp;
					String nazwa1;
					for(int i=0; i<=ilosc;i++)
					{
						for(int j=0;j<=ilosc-1;j++)
						{
							if(punkty[j]<punkty[j+1])
							{
								temp = punkty[j];
								nazwa1 = Login[j];
								punkty[j] = punkty[j+1];
								Login[j] = Login[j+1];
								punkty[j+1]=temp;
								Login[j+1] = nazwa1;
							}
						}
					}
				
				
			}
}
