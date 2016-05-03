package bomberman;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JComponent;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import java.awt.geom.*;
import java.awt.image.*;
import java.util.Random;


/**
 * Klasa Plansza przygotowuje parametry planszy
*/
public class Plansza extends JComponent implements Runnable {

	/** obiekt klasy Graphics 2D */
	Graphics2D g2d; 
	/** liczba elementów labiryntu, które niszczy fala uderzeniowa */
	private int mur_liczba;
	/** liczba elementów labiryntów, które s¹ niezniszczalne */
	private int krzaki_liczba;
	/** wspolrzedna x-owa stawianej bomby */
	private int Bomba_x;
	/** wspolrzedna y-owa stawianej bomby */
	private int Bomba_y;
	/** ilosc bonusow znajdujacych sie na konkretnej planszy*/
	private int ILOSC_BONUSOW;
	/** wspolrzedna x-owa Bombermana po parsowaniu */
	private int WSP_B_X;
	/** wspolrzedna y-owa Bombermana po parsowaniu */
	private int WSP_B_Y;
	/** zasieg bomby */
	private int sila_razenia;
	/** zasieg bomby (zmienna pomocnicza)*/
	private int sila_razeniaa;
	/** czas do wybuchu bomby */
	private int CZAS;
	/** wspolrzedna x-owa elementu mur */
	private int[] x;
	/** wspolrzedna y-owa elementu mur */
	private int[] y;
	/** wspolrzedna x-owa elementu krzak*/
	private int[] s;
	/** wspolrzedna y-owa elementu krzak */
	private int[] r;
	/** wspolrzedna x-owa portalu*/
	private int[] PX;
	/** wspolrzedna y-owa portalu*/
	private int[] PY;
	/** wspolrzedna x-owa Bombermana*/
	private int[] BX;
	/** wspolrzedna y-owa Bombermana*/
	private int[] BY;
	/** wspolrzedna x-owa bonusu*/
	private int[] BONUSY_X;
	/** wspolrzedna y-owa bonusu*/
	private int[] BONUSY_Y;
	/** wspolrzedna x-owa przeciwnika*/
	private int[] PRZECIWNIK_X;
	/** wspolrzedna y-owa przeciwnika*/
	private int[] PRZECIWNIK_Y;
	/** zmienna pomocnicza, przed parsowaniem */
	private String[] krzaki_liczba1;
	/** zmienna pomocnicza, przed parsowaniem */
	private String[] mur_liczba1;
	/** zmienna pomocnicza, przed parsowaniem */
	private String[] x1;
	/** zmienna pomocnicza, przed parsowaniem */
	private String[] y1;
	/** zmienna pomocnicza, przed parsowaniem */
	private String[] s1;
	/** zmienna pomocnicza, przed parsowaniem */
	private String[] r1;
	/** zmienna pomocnicza, przed parsowaniem */
	private String[] px;
	/** zmienna pomocnicza, przed parsowaniem */
	private String[] py;
	/** zmienna pomocnicza, przed parsowaniem */
	private String[] bx;
	/** zmienna pomocnicza, przed parsowaniem */
	private String[] by;
	/** zmienna pomocnicza, przed parsowaniem */
	private String[] bonusy_x;
	/** zmienna pomocnicza, przed parsowaniem */
	private String[] bonusy_y;
	/** zmienna pomocnicza, przed parsowaniem */
	private String[] ilosc_bonusow;
	/** zmienna pomocnicza, przed parsowaniem */
	private String[] przeciwnik_x;
	/** zmienna pomocnicza, przed parsowaniem */
	private String[] przeciwnik_y;
	/** dwuwymiarowa tablica odwzorowuj¹ca plansze */
	private int plansz[][];
	/** zmienna pomocnicza przed parsowaniem */
	private String[] czas;
	/** obiekt klasy Bomberman */
	public Bomberman bombaa;
	/** obiekt klasy Postac (przeciwnik) */
	public Postac W1;
	/** obiekt klasy Postac (przeciwnik) */
	public Postac W2;
	/** obiekt klasy Postac (przeciwnik) */
	public Postac W3;
	/** obiekt klasy Thread */
	public Thread watek;
	/** Obiekt klasy Thread wykorzytsywany do ruchu przeciwnika */
	private Thread przeciwnicy;
	/** flaga umozliwiajaca/blokujaca dostep do wszystkich metod run() */
	public boolean flaga_dostepu_p = false;
	/** pomocnicza flaga */
	private boolean flaga_dostepu = true;
	/** Wspolczynnik do sklaowania wysokosci */
	private int mnoznik_x;
	/** Wspolczynnik do skalowania szerokosci */
	private int mnoznik_y;
	
	
	/**
	 * Konstruktor klasy Plansza, tworzy obiekt klasy Wczytaj, nastepnie korzystajac z metod tej klasy sa wczytywane wspolrzedne wszystkich elementow planszy,
	 * parsowane i umieszczane w odpowiednich tablicach zmiennych, na koniec jest tworzony i uruchamiany watek odpowiedzialny za ruch przeciwnikow
	 * @param file_name nazwa pliku konfiguracyjnego zawierajacego wspolrzedne wszystkich elemntow planszy
	 * @param w szerokosc okna
	 * @param h wysokosc okna
	 */
	public Plansza(String file_name,int w,int h,int g)
	{
		Dimension d = new Dimension(w, h);
	    setMinimumSize(d);
	    setPreferredSize(new Dimension(d));
	    setMaximumSize(d);
	
		Wczytaj file = new Wczytaj(file_name);
		
		plansz = new int[22][22];
		krzaki_liczba1 = file.FindSection("KRZAKI");
		mur_liczba1 = file.FindSection("MUR");
		krzaki_liczba = Integer.parseInt(krzaki_liczba1[1]);
		mur_liczba = Integer.parseInt(mur_liczba1[1]);
		
		x = new int[mur_liczba];	 
		y = new int[mur_liczba];	  
		s = new int[krzaki_liczba];   
		r = new int[krzaki_liczba];
		PX = new int[2];
		PY = new int[2];
		BX = new int[2];
		BY = new int[2];
		
			
		this.x1 = (file.FindSection("X"));
		this.y1 = (file.FindSection("Y"));
		this.s1 = (file.FindSection("X1"));
		this.r1 = (file.FindSection("Y1"));
		this.px = (file.FindSection("PX"));//prtal x
		this.py = (file.FindSection("PY"));//poral y
		this.bx = (file.FindSection("BX"));//bomberman x
		this.by = (file.FindSection("BY"));// bomberman y
		
		ilosc_bonusow = file.FindSection("BONUSY");
		ILOSC_BONUSOW = Integer.parseInt(ilosc_bonusow[1]);
		this.bonusy_x = (file.FindSection("X4"));
		this.bonusy_y = (file.FindSection("Y4"));
		this.przeciwnik_x = (file.FindSection("X3"));
		this.przeciwnik_y = (file.FindSection("Y3"));
		this.czas = (file.FindSection("CZAS"));
		
		CZAS = Integer.parseInt(czas[1]); 
		BONUSY_X = new int[ILOSC_BONUSOW];
		BONUSY_Y = new int[ILOSC_BONUSOW];
		PRZECIWNIK_X = new int[3];
		PRZECIWNIK_Y = new int[3];
		
		for(int i = 0;i<ILOSC_BONUSOW; i++)
		{
			BONUSY_X[i]=Integer.parseInt(bonusy_x[i+1]); 
			BONUSY_Y[i]=Integer.parseInt(bonusy_y[i+1]);
		}
		
		for(int i = 0;i<=2; i++)
		{
			PRZECIWNIK_X[i]=Integer.parseInt(przeciwnik_x[i+1]); 
			PRZECIWNIK_Y[i]=Integer.parseInt(przeciwnik_y[i+1]);
		}
		
		for(int i=0;i<2;i++)
		{
		plansz[PRZECIWNIK_X[i]][PRZECIWNIK_Y[i]] = 11;
		}
		
		W1 = new Postac(PRZECIWNIK_X[0], PRZECIWNIK_Y[0]);
		W2 = new Postac(PRZECIWNIK_X[1], PRZECIWNIK_Y[1]);
		W3 = new Postac(PRZECIWNIK_X[2], PRZECIWNIK_Y[2]);
		
		PX[0]=Integer.parseInt(px[1]);
		PY[0]=Integer.parseInt(py[1]);
		BX[0]=Integer.parseInt(bx[1]);
		BY[0]=Integer.parseInt(by[1]);
		
		bombaa = new Bomberman(BX[0],BY[0],g);
		
		plansz[PX[0]] [PY[0]]= 3;
		plansz[BX[0]] [BY[0]]= 5;
		
		for(int i = 0; i<mur_liczba;i++)
		{
			x[i]=Integer.parseInt(x1[i+1]); //wspolrzedna muru x-owa
			y[i]=Integer.parseInt(y1[i+1]);//wspolrzedna muru x-owa
		}
		for(int i = 0; i<krzaki_liczba;i++)
		{
			s[i]=Integer.parseInt(s1[i+1]);//wspolrzedna krzaka x-owa
			r[i]=Integer.parseInt(r1[i+1]);//wspolrzedna krzaja y-kowa
		}
		
			for(int i=0;i<krzaki_liczba;i++)
			{
			plansz[s[i]][r[i]]=2;
			}
		for(int i=0;i<mur_liczba;i++)
			{
			plansz[x[i]][y[i]]=1;
			}
	
	watek = new Thread(this);
	watek.start();
	
	}
	
	
	/** nadpisanie metody paintComponent, odpowiedzialnej za rysowanie planszy
	 * @param g kontekst graficzny
	 */
	 public void paintComponent(Graphics g) 
	 {
		    super.paintComponent(g);
		    g2d = (Graphics2D) g;
		    int w = getWidth();
		    int h = getHeight();
		    g2d.setColor(Color.blue);
		    g2d.drawRect(0,0,w-1,h-1);
		    
		    mnoznik_x = h/23;
		    mnoznik_y = w/23;
		    
		    for(int i=0;i<this.plansz.length;i++)
			{
				for(int j=0;j<this.plansz[0].length;j++)
				{
					switch(this.plansz[i][j])
					{
						case 1:	g2d.setColor(Color.RED);
								g2d.fillRect(mnoznik_y*j, mnoznik_x*i, mnoznik_y, mnoznik_x);
								break;
						case 3:	g2d.setColor(Color.GREEN);
								g2d.fillRect(mnoznik_y*j, mnoznik_x*i, mnoznik_y, mnoznik_x);
								break;
						case 7: g2d.setColor(Color.RED);
								g2d.drawOval(mnoznik_y*j, mnoznik_x*i, mnoznik_y, mnoznik_x);
								break;
						case 8: g2d.setColor(Color.ORANGE);
								g2d.drawOval(mnoznik_y*j, mnoznik_x*i, mnoznik_y, mnoznik_x);
								break;
						case 9: g2d.setColor(Color.GREEN);
								g2d.drawOval(mnoznik_y*j, mnoznik_x*i, mnoznik_y, mnoznik_x);
								break;
						case 10: g2d.setColor(Color.BLUE);
						         g2d.drawOval(mnoznik_y*j, mnoznik_x*i, mnoznik_y, mnoznik_x);
								 break;
						case 2: g2d.setColor(Color.BLUE);
								g2d.fillRect(mnoznik_y*j, mnoznik_x*i, mnoznik_y, mnoznik_x);
								break;
						case 5: 
								g2d.setColor(Color.YELLOW);
								g2d.fillOval(mnoznik_y*j, mnoznik_x*i, mnoznik_y, mnoznik_x);
								break;
						case 4:
								g2d.setColor(Color.BLACK);
								g2d.fillOval(mnoznik_y*j, mnoznik_x*i, mnoznik_y, mnoznik_x);
								break;
						case 6:
								g2d.setColor(Color.ORANGE);
								g2d.fillRect(mnoznik_y*j, mnoznik_x*i, mnoznik_y, mnoznik_x);
								break;
									
						case 11:
								g2d.setColor(Color.GREEN);
								g2d.fillOval(mnoznik_y*j, mnoznik_x*i, mnoznik_y, mnoznik_x);
								break;
					}
				}
			}
	}
	 
	 /** 
	  * metoda odpowiedzialna za ruch Bombermana, stawianie przez niego bomby oraz sprawdza czy Bomberman dotarl do portalu, w zaleznosci od wcisnietej strzalki 
	  * wykonuje sie konkretny przypadek 
	  * @param a zmienna odpowiedzialna za wybor case
	  */
	 public void ruch_bomberman(char a)
	 {
		 
		 switch(a)
		 {	case 'l':
			 if (plansz[BX[0]][BY[0]-1] == 0 || plansz[BX[0]][BY[0]-1] == 3 || plansz[BX[0]][BY[0]-1] == 10 || plansz[BX[0]][BY[0]-1] == 9 || plansz[BX[0]][BY[0]-1] == 8 || plansz[BX[0]][BY[0]-1] == 7)
			 {   
				 if(plansz[BX[0]][BY[0]-1] == 10 || plansz[BX[0]][BY[0]-1] == 9 || plansz[BX[0]][BY[0]-1] == 8 || plansz[BX[0]][BY[0]-1] == 7)
				 {
					 int x = czy_bonus(BX[0],BY[0]-1);
					 bombaa.bomberman_bonusy(x);
				 }
				 plansz[BX[0]][BY[0]-1] = 5;
				 if(plansz[BX[0]][BY[0]] == 5) {plansz[BX[0]][BY[0]] = 0;}
				 BY[0]--;
				 repaint();
			 }
			 	break;
		 	case 'g':
		 		if ((plansz[BX[0]-1][BY[0]] == 0) || plansz[BX[0]-1][BY[0]] == 3 || (plansz[BX[0]-1][BY[0]] == 10) || (plansz[BX[0]-1][BY[0]] == 9)|| (plansz[BX[0]-1][BY[0]] == 8)||(plansz[BX[0]-1][BY[0]] == 7))
				 { 
		 			 if((plansz[BX[0]-1][BY[0]] == 10) || (plansz[BX[0]-1][BY[0]] == 9)|| (plansz[BX[0]-1][BY[0]] == 8)||(plansz[BX[0]-1][BY[0]] == 7))
					 {
						 int x = czy_bonus(BX[0]-1,BY[0]);
						 bombaa.bomberman_bonusy(x);
					 }
		 			plansz[BX[0]-1][BY[0]] = 5;
				 	if(plansz[BX[0]][BY[0]] == 5) {plansz[BX[0]][BY[0]] = 0;}
					 BX[0]--;
					 repaint();
				 }
		 		break;
		 	case 'p':
		 		 if ( plansz[BX[0]][BY[0]+1] == 0 || plansz[BX[0]][BY[0]+1] == 3 || plansz[BX[0]][BY[0]+1] == 10 || plansz[BX[0]][BY[0]+1] == 9 || plansz[BX[0]][BY[0]+1] == 8 || plansz[BX[0]][BY[0]+1] == 7)
				 {   
		 			 if(plansz[BX[0]][BY[0]+1] == 10 || plansz[BX[0]][BY[0]+1] == 9 || plansz[BX[0]][BY[0]+1] == 8 || plansz[BX[0]][BY[0]+1] == 7)
					 {
						 int x = czy_bonus(BX[0],BY[0]+1);
						 bombaa.bomberman_bonusy(x);
					 }
		 			 plansz[BX[0]][BY[0]+1] = 5;
		 			 if(plansz[BX[0]][BY[0]] == 5) {plansz[BX[0]][BY[0]] = 0;}
					 BY[0]++;
					 repaint();
				 }
				 break;
		 	case 'd':
		 		if ((plansz[BX[0]+1][BY[0]] == 0)|| plansz[BX[0]+1][BY[0]] == 3 || (plansz[BX[0]+1][BY[0]] == 10)|| (plansz[BX[0]+1][BY[0]] == 9)|| (plansz[BX[0]+1][BY[0]] == 8)||(plansz[BX[0]+1][BY[0]] == 7))
				 {   
		 			 if((plansz[BX[0]+1][BY[0]] == 10)|| (plansz[BX[0]+1][BY[0]] == 9) || (plansz[BX[0]+1][BY[0]] == 8)||(plansz[BX[0]+1][BY[0]] == 7))
					 {
						 int x = czy_bonus(BX[0]+1,BY[0]);
						 bombaa.bomberman_bonusy(x);
					 }
		 			plansz[BX[0]+1][BY[0]] = 5;
		 			if(plansz[BX[0]][BY[0]] == 5) {plansz[BX[0]][BY[0]] = 0;}
					 BX[0]++;
					 repaint();
				 }
				 break;
		 	case 's':
		 		if(!(bombaa.ilosc_bombb == 0))
		 		{
		 			bombaa.ilosc_bombb--;
		 			zmien_wsp_b(BX[0],BY[0]);	
		 			sila_razenia(bombaa.sila_razenia);
		 			(new Thread(new Tylko_Run())).start();
		 			(new Thread(new przywroc_bombe_B())).start();
		 			repaint();
		 		}
		 		
		 		break;
		 }
		 
	 }
	 
	 /**  przeciazenie metody run(), implementujacej iterfejs runable klasy Plansza (poruszanie sie przeciwnikow */
	
	 private class obsługa_postaci implements Runnable
	 {
		 @Override
		 public void run()
		 {
			 try 
				{
					while(W1.life || W2.life || W3.life)
						{
							if(!flaga_dostepu_p)
							{
							if(W1.life)
								{
									int a=0;
									boolean dostep1 = true;
									while(dostep1)
									{
										a = ruch_przciwnika_losuj();
										dostep1 = ruch_przeciwnika_okolice(0,a);
									}
									ruch_przeciwnika(0,a);
									dostep1=true;
									if(W1.ilosc_bomb == 1 && pozwolenie_na_bombe(PRZECIWNIK_X[0], PRZECIWNIK_Y[0]))
									{ 
										W1.ilosc_bomb --;
										zmien_wsp_b(PRZECIWNIK_X[0], PRZECIWNIK_Y[0]);
										sila_razenia(W1.sila_razenia_p);
									    (new Thread(new Tylko_Run())).start();
									    (new Thread(new przywroc_bombe_W1())).start();
									}
								}
							if(W2.life)
								{
									int a=0;
									boolean dostep2 = true;
									while(dostep2)
									{
									a = ruch_przciwnika_losuj();
									dostep2 = ruch_przeciwnika_okolice(1,a);
									}
									ruch_przeciwnika(1,a);
									dostep2=true;
									if(W2.ilosc_bomb == 1 && pozwolenie_na_bombe(PRZECIWNIK_X[1], PRZECIWNIK_Y[1]))
									{ 
										W2.ilosc_bomb --;
										zmien_wsp_b(PRZECIWNIK_X[1], PRZECIWNIK_Y[1]);
										sila_razenia(W2.sila_razenia_p);
									    (new Thread(new Tylko_Run())).start();
									    (new Thread(new przywroc_bombe_W2())).start();
									}
								}
							if(W3.life)
								{
									int a=0;
									boolean dostep3 = true;
									while(dostep3)
									{
										a = ruch_przciwnika_losuj();
										dostep3 = ruch_przeciwnika_okolice(2,a);
									}
									ruch_przeciwnika(2,a);
									dostep3=true;
									if(W3.ilosc_bomb == 1 && pozwolenie_na_bombe(PRZECIWNIK_X[2], PRZECIWNIK_Y[2]))
									{ 
										W3.ilosc_bomb --;
										zmien_wsp_b(PRZECIWNIK_X[2], PRZECIWNIK_Y[2]);
										sila_razenia(W3.sila_razenia_p);
									    (new Thread(new Tylko_Run())).start();
									    (new Thread(new przywroc_bombe_W3())).start();
									}
								}
							repaint();
							Thread.sleep(250);
						}
					}
				}
			catch (InterruptedException e) 
				{
					e.printStackTrace();
				}
		 }
		
		 
	 }
	 /** klasa abstrakcyjna odpowiedzialna za obsluge w¹tku (odliczanie czasu do przywrocenia maksymalnej liczby bomb przeciwnika nr3 po wybuchu bomby */
	 private class przywroc_bombe_W1 implements Runnable
	 {

		@Override
		public void run() {
			try {
				
					Thread.sleep(2500);
					W1.ilosc_bomb =1;
					
				
			} catch (InterruptedException e) {
				e.printStackTrace();
			}}}
	 


	 /** klasa abstrakcyjna odpowiedzialna za obsluge watku (odliczanie czasu do przywrocenia maksymalnej liczby bomb przeciwnika nr3 po wybuchu bomby */
	 private class przywroc_bombe_B implements Runnable
	 {

		@Override
		public void run() {
			try {
				
					Thread.sleep(2500);
					bombaa.ilosc_bombb ++;
				
				
			} catch (InterruptedException e) {
				e.printStackTrace();
			}}}
	
	 

	 /** klasa abstrakcyjna odpowiedzialna za obsluge watku (odliczanie czasu do przywrocenia maksymalnej liczby bomb przeciwnika nr3 po wybuchu bomby */
	 private class przywroc_bombe_W2 implements Runnable
	 {

		@Override
		public void run() {
			try {
				
					Thread.sleep(2500);
					W2.ilosc_bomb =1;
				
				
			} catch (InterruptedException e) {
				e.printStackTrace();
			}}}
	 
	 

	 /** klasa abstrakcyjna odpowiedzialna za obsluge w¹tku (odliczanie czasu do przywrocenia maksymalnej liczby bomb przeciwnika nr3 po wybuchu bomby */
	 private class przywroc_bombe_W3 implements Runnable
	 {

		@Override
		public void run() {
			try {
				
					Thread.sleep(2500);
					W3.ilosc_bomb=1;
					
				} 
			catch (InterruptedException e) {
				e.printStackTrace();
			}}}
	 
	 /** klasa abstrakcyjna, odpowiedzialna za obsluge watku rzuconego po postawieniu bomby*/
	 public  class Tylko_Run implements Runnable
	 {
		@Override
		public void run() {
			int x = WSP_B_X;
			int y = WSP_B_Y;
			int z = sila_razenia;
			try 
			{
				
					plansz[x][y] = 4;
					repaint();
					Thread.sleep(CZAS);
					postaw_fale_uderzeniowa(x,y,z);
					repaint();
					Thread.sleep(500);
					usun_fale_uderzeniowa(x,y,z);
					repaint();		
					
			}
		catch (InterruptedException e) 
			{
				e.printStackTrace();
			}
		}
	 }
	
	 /** Metoda stawia fale uderzeniowa w dozwolonym miesjcu */
	 public void postaw_fale_uderzeniowa(int x, int y, int z)
	 {
		 	plansz[x][y] = 6;
		 	if(x == PRZECIWNIK_X[0] && y == PRZECIWNIK_Y[0]){ W1.life = false;}
			if(x == PRZECIWNIK_X[1] && y == PRZECIWNIK_Y[1]){ W2.life = false;}
			if(x == PRZECIWNIK_X[2] && y == PRZECIWNIK_Y[2]){ W3.life = false;}
			
			if(x == BX[0] && y == BY[0]) 
			{
				bombaa.odejmij_zycie();
				bombaa.reset_bonusow();
				plansz[BX[0]][ BY[0]] = 0;
				BX[0]=bombaa.WX; 
				BY[0]=bombaa.WY ;
				plansz[BX[0]][ BY[0]] = 5;	
			}
		 	
			for(int zas=1;zas<=z;zas++)
				{
					if(!(plansz[x+zas][y] == 1))
						{
							plansz[x+zas][y] = 6;
							if(x+zas == PRZECIWNIK_X[0] && y == PRZECIWNIK_Y[0]){ W1.life = false;}
							if(x+zas == PRZECIWNIK_X[1] && y == PRZECIWNIK_Y[1]){ W2.life = false;}
							if(x+zas == PRZECIWNIK_X[2] && y == PRZECIWNIK_Y[2]){ W3.life = false;}
				
							if(x+zas == BX[0] && y == BY[0]) 
								{
								bombaa.odejmij_zycie();
								bombaa.reset_bonusow();
								plansz[BX[0]][ BY[0]] = 0;
								BX[0]=bombaa.WX; 
								BY[0]=bombaa.WY ;
								plansz[BX[0]][ BY[0]] = 5;	
								}
						}
					if(x+zas == PX[0] && y==PY[0])
						{
							plansz[PX[0]][PY[0]] = 3;
							zas = z;
						}
					if(plansz[x+zas][y] == 1){zas=z;}
				}
			
			for(int zas=1;zas<=z;zas++)
				{
					if(!(plansz[x-zas][y] == 1))
						{
							plansz[x-zas][y] = 6;
							if(x-zas == PRZECIWNIK_X[0] && y == PRZECIWNIK_Y[0]){ W1.life = false;}
							if(x-zas == PRZECIWNIK_X[1] && y == PRZECIWNIK_Y[1]){ W2.life = false;}
							if(x-zas == PRZECIWNIK_X[2] && y == PRZECIWNIK_Y[2]){ W3.life = false;}
				
							if(x-zas == BX[0] && y == BY[0]) 
							{
								bombaa.odejmij_zycie();
								bombaa.reset_bonusow();
								plansz[BX[0]][ BY[0]] = 0;
								BX[0]=bombaa.WX; 
								BY[0]=bombaa.WY ;
								plansz[BX[0]][ BY[0]] = 5;	
							}
						}
					if(x-zas == PX[0] && y==PY[0])
						{
							plansz[PX[0]][PY[0]] = 3;
							zas = z;
						}
					if(plansz[x-zas][y] == 1){zas = z;}
				}
			for(int zas=1;zas<=z;zas++)
				{
					if(!(plansz[x][y+zas] == 1))
						{
							plansz[x][y+zas] = 6;
							if(x == PRZECIWNIK_X[0] && y+zas == PRZECIWNIK_Y[0]){ W1.life = false;}
							if(x == PRZECIWNIK_X[1] && y+zas == PRZECIWNIK_Y[1]){ W2.life = false;}
							if(x == PRZECIWNIK_X[2] && y+zas == PRZECIWNIK_Y[2]){ W3.life = false;}
				
							if(x == BX[0] && y+zas == BY[0]) 
								{
									bombaa.odejmij_zycie();
									bombaa.reset_bonusow();
									plansz[BX[0]][ BY[0]] = 0;
									BX[0]=bombaa.WX; 
									BY[0]=bombaa.WY ;
									plansz[BX[0]][ BY[0]] = 5;	
								}
						}
					if(x == PX[0] && y+zas==PY[0])
						{
							plansz[PX[0]][PY[0]] = 3;
							zas=z;
						}
					if(plansz[x][y+zas] == 1){zas = z;}
				}
			
			for(int zas=1;zas<=z;zas++)
				{
					if(!(plansz[x][y-zas] == 1))
						{
							plansz[x][y-zas] = 6;
							if(x == PRZECIWNIK_X[0] && y-1 == PRZECIWNIK_Y[0]){ W1.life = false;}
							if(x == PRZECIWNIK_X[1] && y-1 == PRZECIWNIK_Y[1]){ W2.life = false;}
							if(x == PRZECIWNIK_X[2] && y-1 == PRZECIWNIK_Y[2]){ W3.life = false;}
				
							if(x == BX[0] && y-zas == BY[0]) 
								{
								bombaa.odejmij_zycie();
								bombaa.reset_bonusow();
								plansz[BX[0]][ BY[0]] = 0;
								BX[0]=bombaa.WX; 
								BY[0]=bombaa.WY ;
								plansz[BX[0]][ BY[0]] = 5;	
								}
						}
					if(x == PX[0] && y-zas==PY[0])
						{
							plansz[PX[0]][PY[0]] = 3;
							zas = z;
						}
					if(plansz[x][y-zas] == 1){zas=z;}
				}
			repaint();
	 }	
	 /** Metoda usuwa fale uderzeniowa z planszy */
	 public void usun_fale_uderzeniowa(int x,int y, int z)
	 {
		 plansz[x][y] = 0;
		 
		 if(this.sprawdz_bonusy(x,y))
			{
				losuj_bonusy(x,y);
			}
		 
		 for(int zas=1;zas<=z;zas++)
		{
		 if(plansz[x+zas][y] == 6)
		 {
			 if(this.sprawdz_bonusy(x+zas,y))
				{
					losuj_bonusy(x+zas,y);
				}
			 else  plansz[x+zas][y] = 0;
		 }
		 
		 if(plansz[x-zas][y] == 6)
		 {
			 if(this.sprawdz_bonusy(x-zas,y))
				{
					losuj_bonusy(x-zas,y);
				}
			 else plansz[x-zas][y] = 0;
		 }
		 
		 if(plansz[x][y+zas] == 6)
		 {
			 if(this.sprawdz_bonusy(x,y+zas))
				{
					losuj_bonusy(x,y+zas);
				}
			 else  plansz[x][y+zas] = 0;
		 }
		 
		 if(plansz[x][y-zas] == 6)
		 {
			 if(this.sprawdz_bonusy(x,y-zas))
				{
					losuj_bonusy(x,y-zas);
				}
			 else plansz[x][y-zas] = 0;
		 }
		 }
	 }
	 
	/** Przeciazenie metody run() klasy Plansza, obsługa postaci */
	public void run() {
		przeciwnicy = new Thread(new obsługa_postaci());
		przeciwnicy.start();
		
		while(flaga_dostepu)
		{
			System.out.println(" sprawdza");
			if(BX[0]==PX[0] && BY[0]==PX[0])
				{
					bombaa.flaga_zwyciestwa = true;
					flaga_dostepu = false;
					System.out.println(" portal");
				}
			if(bombaa.ilosc_zyc == 0)
		 		{
			 		bombaa.life = false;
			 		flaga_dostepu = false;
		 		}
			
		} 
	}
	
	
	/** 
	 * metoda, ktora sprawdza czy pod krzakiem, ktory jest w polu razenia bomby, jest bonus
	 * @param a wspolrzedna x-owa miejsca, w ktorym jest fala uderzeniowa
	 * @param b  wspolrzedna y-owa miejsca, w ktorym jest fala uderzeniowa
	 * @return true je¿eli jest, false je¿eli nie ma
	 */
	public boolean sprawdz_bonusy(int a,int b)
	{
		for(int i=0;i<this.ILOSC_BONUSOW;i++)
		{
			if(BONUSY_X[i] == a && BONUSY_Y[i] == b)
			{
				return true;
			}
			
		}
		return false;
	}
	
	/**
	 * losowanie bonusu
	 * @param c wspolrzedna x-owa miejsca, w ktorym ma sie znajdowac losowany bonus
	 * @param b wspolrzedna y-owa miejsca, w ktorym ma sie znajdowac losowany bonus
	 */
	  public void losuj_bonusy(int c, int b)
	 
	{
		Random r = new Random(); 
		int a = r.nextInt(4)+7;
		this.plansz[c][b] = a;
	}
	
	  /**
	   * sprawdza na jaki bonus wszedl Bomberman
	   * @param a wspolrzedna x-owa sprawdzanej komorki planszy
	   * @param b wspolrzedna y-owa sprawdzanej komorki planszy
	   * @return wartosc w w dwuwymiarowej tablicy (planszy) ktora odpowiada bonusowi
	   */
	public int czy_bonus(int a,int b)
	{
		for(int i=0;i<this.ILOSC_BONUSOW;i++)
		{
			if(BONUSY_X[i] == a && BONUSY_Y[i] == b)
			{
				BONUSY_X[i] = 0;
				BONUSY_Y[i] = 0;
				return plansz[a][b];
			}
		}
		return 0;
	}
	
	/** metoda odpowiedzialne za losowanie kierunku w którym porusz sie przeciwnik */
	public int ruch_przciwnika_losuj()
	{
		Random r = new Random(); 
		int a = r.nextInt(4)+1;
		return a;
	}
	
	/** metoda sprawdza czy przeciwnik nie wejdzie w fale uderzeniowa */
	public boolean ruch_przeciwnika_okolice(int h,int a)
	{
		int d = PRZECIWNIK_X[h];
		int e = PRZECIWNIK_Y[h];
		if((a==1 && (plansz[d][e+2]==4 || plansz[d-1][e+1] == 4 || plansz[d+1][e+1] == 4)))
		{return true;}
		if((a==2 && (plansz[d][e-2]==4 || plansz[d-1][e-1] == 4 || plansz[d+1][e-1] == 4)))
		{return true;}
		if((a==3 && (plansz[d-2][e]==4 || plansz[d-1][e+1] == 4 || plansz[d-1][e-1] == 4)))
		{return true;}
		if((a==4 && (plansz[d+2][e]==4 || plansz[d+1][e+1] == 4 || plansz[d+1][e-1] == 4)))
		{return true;}
		else return false;
	}
	/**
	 * metoda odpowiedzialna za ruch przeciwnika 
	 * @param h indeks uzywany do odczytania wspolrzednych odpowiedniego przeciwnika
	 */
	public void ruch_przeciwnika(int h, int a)
	{
		int d = PRZECIWNIK_X[h];
		int e = PRZECIWNIK_Y[h];
	
		if (a == 1)
		{
			if(plansz[d][e+1] == 0 || plansz[d][e+1] == 7 || plansz[d][e+1] == 8 || plansz[d][e+1] == 9 || plansz[d][e+1] == 10)
			{
				if (plansz[d][e] == 11){plansz[d][e] = 0;}
				if (plansz[d][e] == 4){plansz[d][e] = 4;}
				plansz[d][e+1] = 11;
				PRZECIWNIK_Y[h]++;
			}
		}
		if(a == 2)
		{
			if(plansz[d][e-1] == 0 || plansz[d][e-1] == 7 || plansz[d][e-1] == 8 || plansz[d][e-1] == 9 || plansz[d][e-1] == 10)
			{
				if (plansz[d][e] == 11)plansz[d][e] = 0;
				if (plansz[d][e] == 4)plansz[d][e] = 4;
				plansz[d][e-1] = 11;
				PRZECIWNIK_Y[h]--;
			}
		}
		if(a == 3)
		{
			if(plansz[d-1][e] == 0 || plansz[d-1][e] == 7 || plansz[d-1][e] == 8 || plansz[d-1][e] == 9 || plansz[d-1][e] == 10)
			{
				if (plansz[d][e] == 11)plansz[d][e] = 0;
				if (plansz[d][e] == 4)plansz[d][e] = 4;
				plansz[d-1][e] = 11;
				PRZECIWNIK_X[h]--;
			}
		}
		if(a ==4)
		{
			if(plansz[d+1][e] == 0 || plansz[d+1][e] == 7 || plansz[d+1][e] == 8 || plansz[d+1][e] == 9 || plansz[d+1][e] == 10)
			{
				if (plansz[d][e] == 11)plansz[d][e] = 0;
				if (plansz[d][e] == 4)plansz[d][e] = 4;
				plansz[d+1][e] = 11;
				PRZECIWNIK_X[h]++;
			}
		}
	}
	
	
	/**
	 * metoda dajaca pozwolenie przeciwnikowi na postawienie bomby (stawia tylko przy krzakach lub Bombermanie
	 * @param a wsporzedna x-owa w której znajduje sie przeciwnik
	 * @param b wsporzedna y-owa w której znajduje sie przeciwnik
	 * @return true jesli mo¿e postawic bombe, false jesli nie
	 */
	public boolean pozwolenie_na_bombe(int a, int b)
	{
		if(plansz[a+1][b] == 2 || plansz[a-1][b] == 2 || plansz[a][b+1] == 2 || plansz[a][b-1] == 2||
		plansz[a+1][b] == 5 || plansz[a-1][b] == 5 || plansz[a][b+1] == 5 || plansz[a][b-1] == 5)
			{return true;}
		else return false;
	}
	
	
	/**
	 * metoda pomocnicza przy stawianiu bomby
	 * @param a wspolrzedna x-owa Bombermana lub przeciwnika
	 * @param b wspolrzedna x-owa Bombermana lub przeciwnika
	 */
	public void zmien_wsp_b(int a,int b)
	{
		WSP_B_X = a;
	    WSP_B_Y = b;
	}
	
	/**
	 * metoda pomocnicza przy wysadzaniu bomby
	 * @param a zasieg bomby Bombermana lub przeciwnika
	 */
	public void sila_razenia(int a)
	{
		sila_razenia = a;
	}
	
	
}


