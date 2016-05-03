package bomberman;

import java.awt.Button;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import bomberman.Plansza.Tylko_Run;


/**
 * Klasa Menu tworzy menu gry*/
public class Menu extends JFrame implements ActionListener {
	  
	/** Obiekt klasy Button - START */
	Button bStart;
	/** Obiekt klasy Button - WYJSCIE */
	Button bWyjscie; 
	/** Obiekt klasy Button - DODAJ IMIE */
	Button bDodajImie;
	/** Obiekt klasy Button - TABELA WYNIKOW */
	Button bTabelaWynikow;
	/** Obiekt klasy Button - INSTRUKCJA */
	Button bIstrukcja;
	/** Obiekt klasy TabelaWynikow sluzacy do wyswietlenia Najlepszych Wynikow */
	TabelaWynikow nowy;
	/** Zmienna przechowujaca imie wpisane przez gracza*/
	String imie;
	/** Zmienna blokujaza pewne opjce menu w zaleznosci czy gra jest wlaczona/wylaczona */
	private int Flaga_gry = 0;
	/** Obiekt klasy Thread*/
	public Thread th ;
	/** Konstrukto klasy Menu, utorzenie okna wraz z menu gry, dodanie wszystkich Button-ow*/
	public Gra gra1;
	
	public Menu()
	 {
	     setTitle("Gra");
	     setSize(700,200);
	     setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	     setLayout(null);
	     
	     bStart = new Button("START");
	     bStart.setBounds(20,50,120,30);
	     add(bStart);
	     bStart.addActionListener(this);
	     
	     bDodajImie = new Button("DODAJ IMIE");
	     bDodajImie.setBounds(150,50,120,30);
	     add(bDodajImie);
	     bDodajImie.addActionListener(this);
	     
	     bTabelaWynikow = new Button("TABELA WYNIKOW");
	     bTabelaWynikow.setBounds(280,50,120,30);
	     add(bTabelaWynikow);
	     bTabelaWynikow.addActionListener(this);
	     
	     bIstrukcja = new Button("INSTRUKCJA");
	     bIstrukcja.setBounds(410,50,120,30);
	     add(bIstrukcja);
	     bIstrukcja.addActionListener(this);
	     
	     bWyjscie = new Button("WYJSCIE");
	     bWyjscie.setBounds(540,50,120,30);
	     add(bWyjscie);
	     bWyjscie.addActionListener(this);
	     imie = "brak";
	 }
	
	
	 public static void main(String[] args) 
	 { 
		 Menu okienko = new Menu();
		 okienko.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		 okienko.setVisible(true); 
	 }

	 /** obsluga zdarzen po wykryciu sygnalu (nacisniecie Button-a)
	  * @param zdarzenie*/
	public void actionPerformed(ActionEvent e) 
	{
		Object zrodlo = e.getSource();
		if(zrodlo == bStart)
			{
			{
			setVisible(true);
			
			if(Flaga_gry >0)
			{
				if(th.isAlive()){}
				if(!th.isAlive()){Flaga_gry = 0;}
			}
			if(Flaga_gry == 0)
			{
				gra1 = new Gra(imie);
				th =  new Thread(gra1);
				th.start();
				Flaga_gry ++;
			}
			}}
		if(zrodlo == bDodajImie)
			{
				if(Flaga_gry == 0)
				{
					imie = JOptionPane.showInputDialog("Podaj imie:");
				}
				if(Flaga_gry>0)
				{
					if(th.isAlive()){}
					if(!th.isAlive()){imie = JOptionPane.showInputDialog("Podaj imie:");}
				}
			}
		if(zrodlo == bTabelaWynikow)
			
			{
			nowy = new TabelaWynikow("Wyniki.txt");
			nowy.Odzczytaj_wyniki();
			nowy.Wyswietl_wyniki();
			nowy.setVisible(true);
			}
		if(zrodlo == bIstrukcja)
		{
		
		  JOptionPane.showMessageDialog(this,"Celem gry jest niszczenie obiektów poruszających się na planszy i odnajdowanie przejść do \n" +
				  "kolejnych poziomów.\n" +
				  "Plansza gry przedstawia labirynt z przeszkodami, po którym porusza się bohater gry (Bomber) \n"+
				  "oraz próbujące go zniszczyć potwory. Bomber oczyszcza przejścia za pomocą bomb. \n"+
				  "Wybuchająca bomba może zniszczyć obiekty w pewnym otoczeniu – potwory, przeszkody, \n"+
				  "a także samego Bombera. Niektóre obiekty, np. ściany labiryntu, są niezniszczalne, \n"+
				  "zatrzymują również falę uderzeniową. Zadaniem Bombera jest odnalezienie, początkowo \n"+
				  "ukrytego, np. pod jedną z przeszkód, przejścia do kolejnego poziomu. \n"+
				  "Gra składa się z poziomów – plansz, przedstawiających różne układy labiryntu, przeszkód \n"+
				  "i potworów. Przejście na kolejny poziom następuje po pomyślnym ukończeniu aktualnego. ", "INSTRUCJA", JOptionPane.INFORMATION_MESSAGE);
				}
		   else if(zrodlo == bWyjscie)
		   {
			   if(Flaga_gry>0)
			   {
				   if(th.isAlive())
				   {
					   gra1.plansza1.zamknij_okno_gry();
				   }
				  
			   }
			   dispose();
			   
			   
		   }
	}
	 
	}


