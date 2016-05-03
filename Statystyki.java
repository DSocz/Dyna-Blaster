package bomberman;


import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
/** Klasa odpowiedzialna za stworzenie okna ze satystykami gracza */
public class Statystyki extends JFrame implements Runnable {
	
	/** Imie gracza  */
	String Imie;
	/** Punkty zdobyte przez gracza w calej grze */
	int Punkty;
	/** Biezaca ilosc zyc bombermana */
	int Ilosc_zyc;
	/** Dostepne bomby bombermana*/
	int Liczba_bomb;
	/** Biezace punkty gracza */
	int pkt_tymczasowe;
	/** Zasieg bomby */
	int Zasieg_bomby;
	/** Obiekt klasy JFrame */
	public JFrame frame;
	/** Obiekt klasy Thread */
	private Thread th;
	/** Obiekt klasy JLabel wykorzystywany przy wyswietlaniu zmniennej Imie*/
	private JLabel LImie;
	/** Obiekt klasy JLabel wykorzystywany przy wyswietlaniu zmniennej Punkty*/
	private JLabel LPunkty;
	/** Obiekt klasy JLabel wykorzystywany przy wyswietlaniu zmniennej pkt_tymczasowe*/
	private JLabel LPunkty1;
	/** Obiekt klasy JLabel wykorzystywany przy wyswietlaniu zmniennej Ilosc_zyc*/
	private JLabel LIlosc_zyc;
	/** Obiekt klasy JLabel wykorzystywany przy wyswietlaniu zmniennej Liczba_bomb*/
	private JLabel LLiczba_bomb;
	/** Obiekt klasy JLabel wykorzystywany do sygnalizowania czy flaga jest w³aczona*/
	private JLabel LPauza;
	/** Obiekt klasy JLabel wykorzystywany przy wyswietlaniu zmiennej zasiegu bomby*/
	private JLabel LZasieg;
	/** Flaga, ustawiona na true umozliwia wyswietlenie zaktualizowanych danych dotyczacych bombermana w odpowiednim oknie */
	public Boolean fala_dostepu_s;
	/** flaga pauzy */
	Boolean pauza = false;
	
/** Konstruktor klasy Statystyki, stworzenie nowego okna oraz dodanie etykiet  
 * @param a imie gracza*/	
public Statystyki(String a)
{
	frame = new JFrame();
	frame.setSize(300,300);
	
	frame.setLocation(50,20);
	frame.setLayout(new GridLayout(7, 1));
	frame.setVisible(true);
	
	Imie = a;
	Ilosc_zyc = 3;
	Liczba_bomb = 1;
	Zasieg_bomby = 1;
	
	
	 LImie = new JLabel("GRACZ: " + Imie);
     LImie.setSize(100, 50);
     frame.add(LImie);
     
     LPunkty = new JLabel("ZDOBYTE PUNKTY: " + Punkty);
     LPunkty.setSize(100, 50);
     frame.add(LPunkty);
     
     LPunkty1 = new JLabel("PUNKTY W TEJ RUNDZIE: "+ pkt_tymczasowe);
     LPunkty1.setSize(100, 50);
     frame.add(LPunkty1);
     
     LIlosc_zyc = new JLabel("ILOSC ZYC: "+ Ilosc_zyc);
     LIlosc_zyc.setSize(100, 50);
     frame.add(LIlosc_zyc);
     
     LLiczba_bomb = new JLabel("ILOSC BOMB: "+ Liczba_bomb);
     LLiczba_bomb.setSize(100, 50);
     frame.add(LLiczba_bomb);
     
     LZasieg = new JLabel("ZASIEG BOMBY: "+ Zasieg_bomby);
     LZasieg.setSize(100, 50);
     frame.add(LZasieg);
     
     LPauza = new JLabel("");
     LPauza.setSize(100, 50);
     frame.add(LPauza);
     
     fala_dostepu_s = true;
	 th = new Thread(this);
	 th.start();
}

/** Metoda zliczajaca punkty zdobyte w grze do aktualnego momentu */
public void zlicz_pkt()
{
	this.Punkty = this.Punkty + pkt_tymczasowe+1000;
}
/** Metoda aktualizujaca dane dotyczace bombermana */
public void update()
{
	LImie.setText("GRACZ: " + Imie);
	LPunkty.setText("ZDOBYTE PUNKTY: " + Punkty);
	LPunkty1.setText("PUNKTY W TEJ RUNDZIE: "+ pkt_tymczasowe);
	LIlosc_zyc.setText("ILOSC ZYC: "+ Ilosc_zyc);
	LLiczba_bomb.setText("ILOSC BOMB: "+ Liczba_bomb);
	LZasieg.setText("ZASIEG BOMBY: "+ Zasieg_bomby);
	if(pauza)
	{
		LPauza.setText("PAUZA!");
	}
	if(!pauza)
	{
		LPauza.setText("");
	}
}

/** Przeciazenie metody run */
@Override
public void run() {
	
	try {
		while(fala_dostepu_s)
		{	
			Thread.sleep(100);
			update();
		}
	
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}

public void zmien_flage()
{
	this.fala_dostepu_s = false;
	this.frame.dispose();
}
}