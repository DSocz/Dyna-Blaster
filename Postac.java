package bomberman;


/**
 *Klasa, ktorej obiekty reprezentuja przeciwnikow 
 */
public class Postac
{
	/** zycie przeciwnika */
	boolean life;
	/** maksymalna ilosc bomb przeciwnika, ktra moze pozostawaæ jednoczeœnie na planszy */
	int ilosc_bomb;
	/** zasieg bomby przeciwnika */
	int sila_razenia_p;
	/** wspolrzedna x-owa przeciwnika */
	int WX;
	/** wspolrzedna y-kowa przeciwnika */
	int WY;

	/** 
	 * Konstruktor klasy postac
	 * @param a wspolrzedna x-owa przeciwnika wczytywana z pliku konfiguracyjnego
	 * @param b wspolrzedna y-owa przeciwnika wczytywana z pliku konfiguracyjnego
	 */
	public Postac(int a, int b )
	{
		WX = a;
		WY = b;
		life = true;
		ilosc_bomb = 1;
		sila_razenia_p = 1;
		
	}

}
