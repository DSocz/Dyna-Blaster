package bomberman;

import java.awt.Color;
import java.awt.Graphics;

/**
 *Klasa, ktorej obiekt reprezentuje Bomberman
 */
public class Bomberman {
	/** punkty zdobyte przez gracza */
	int pkt;
	/** maksymalna ilosc bomb Bombermana, ktora moze pozostawac jednoczesnie na planszy */
	int ilosc_bombb;
	/** przejscie rundy, jej wartosc zmienia sie na 'true' kiedy Bomberman dociera do portalu */
	boolean flaga_zwyciestwa;
	/** zasieg bomby Bombermana */
	int sila_razenia;
	/** imie gracza */
	String imie;
	/** zycie Bombermana, zmienia sie na 'false' gdy Bomberman ginie */
	boolean life;
	/** ilosc zyc Bombermana */
	int ilosc_zyc;
	/** wspolrzedna x-owa Bombermana */
	int WX;
	/** wspolrzedna y-owa Bombermana*/
	int WY;
	
	
	/** 
	 * Konstruktor klasy postc
	 * @param a wspolrzedna poczatkowa x-owa Bombermana wczytywana z pliku konfiguracyjnego
	 * @param b wspolrzedna poczatkowa y-owa Bombermana wczytywana z pliku konfiguracyjnego
	 */
	public Bomberman(int a, int b, int c)
	{
		imie = "brak";
		pkt = 0;
		ilosc_zyc = c;
		ilosc_bombb = 1;
		sila_razenia = 1;
		flaga_zwyciestwa = false;
		life = true;
		WX = a;
		WY = b;
	}
	 
	/** Metoda, ktora dodaje Bombermanowi dodatkowa bombe (Bonus) */
	public void wiecej_bomb()
	{ilosc_bombb ++;}
	/**Metoda, ktora dodaje Bombermanowi punky(Bonus) 
	 * @param a ilosc punktow*/
	public void dodaj_pkt(int a)
	{this.pkt =+ a;}
	/**Metoda zwiekszajaca zasieg bomby Bombermana(Bonus) */
	public void zwieksz_zasieg()
	{
		if(sila_razenia < 2)
		{sila_razenia ++ ;}
	}

	/** 
	 * Metoda, ktora wywoluje odpowiednie metody w zaleznosci od tego jaki bonus zbierze Bomberman
	 * @param a wartosc komorki w tablicy plansz, ktora symbolizuje bonus, ktory zebral Bomberman
	 */
	public void bomberman_bonusy(int a)
	{
		switch(a)
		 {	
		 	case 7:
		 		dodaj_pkt(200);
		 		break;
		 	case 8:
		 		dodaj_zycie();
		 		dodaj_pkt(50);
		 		break;
		 	case 9:
		 		wiecej_bomb();
		 		dodaj_pkt(50);
		 		break;
		 	case 10:
		 		zwieksz_zasieg();
		 		dodaj_pkt(50);
		 		break;
		
		 }
	}
	/** Metoda odejmujaca Bombermanowi zycie, jesli znajdzie sie on w zasiegu fali razenia oraz resetuje niekótre bonusy  */
	public void odejmij_zycie()
	{
		this.ilosc_zyc --;
	}
	/** Metoda dodaje zycie po zebrani odpowiedniego bonusa */
	public void dodaj_zycie()
	{this.ilosc_zyc ++;}
	
	/** Metoda resetuje bonosu po utacie zycia przez bombermana*/
	public void reset_bonusow()
	{
		if(this.ilosc_bombb > 1){this.ilosc_bombb = 1;}
		this.sila_razenia = 1;
	}
}
