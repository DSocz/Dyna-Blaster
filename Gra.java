package bomberman;
//import java.awt.Canvas;
import java.awt.Button;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

/**
 * @author DS KS
 *Klasa Gra 
 */
public class Gra extends JFrame implements Runnable{
	/** Zmienna odczytana z pliku konfiguracyjnego przechowujaca liczbe plansz */
	private int level_number;
	/** Obiekt klasy RysujPlansz*/
	RysujPlansze plansza1;
	/** Zmienna przechowujaca nazwe pierwszego, niezmiennego pliku konfiguracyjnego*/
	private String file_name = "conf.txt";
	/** Utworzenie listy przechowujacej kolejno rundy oraz ich tla*/
	private ArrayList levels = new ArrayList();
	/** Obiekt klasy TabelaWynikow*/
	private TabelaWynikow tabela;
	/** Obiekt klasy Thread */
	public Thread th ;
	/** Flaga, ktora ustawiona na true oznacza, ze gracz nie chce powtarzaæ poziomu*/
	private boolean stayLevel=false;
	/** Flaga, która ustawiona na true oznacza koniec gry*/
	private boolean gameEnded=false;
	/** Flaga swiadczaca o rezultacie gry */
	private boolean result;
	/** Obiekt klasy Statystyki */
	public Statystyki stat;
	/** Obiekt klasy Button - przycisk TAK*/
	private Button bTak;
	/** Obiekt klasy Button - przycisk NIE*/
	private Button bNie;
	/** Obiekt klasy JLabel */
	private JLabel  Label;
	/** Zmienna pomocnicza*/
	Boolean flaga_gry;
	/**Tablica String-ow przechowujaca dane do zapisu do pliku Wyniki.txt */
	String[] linie; 
	/** Obiekt klasy FileWriter wykorzystywana przy zapisie danych do pliku*/
	FileWriter fw = null;
	/** Zmienna klasy BufferedWriter wykorzystywana przy zapisie danych do pliku */
	BufferedWriter bw;
	/** Zmienna pomocnicza przechowujaca imie gracza */
	private String Imiea;
	/** Zmienna przechowujaca napis wyswietlany w etykiecie */
	String napis;
	/** Szerokosc okna planszy */
	int szerokosc;
	/** Wysokosc okna planszy */ 
	int wysokosc;
	
	
	/** Konstruktor klasy Gra, uworzenie dodatkowego okna oraz odczytanie i dodanie do listy kolejnych planszy gry*/
 	public Gra(String a)
	{
		super("GRA");
		setSize(300,300);
		setVisible(false);
		
		 Label = new JLabel("");
	     setSize(300, 300);
	     add(Label);
	     setLocation(500,200);
	     
		Imiea = a;
		Wczytaj file = new Wczytaj(file_name);
		flaga_gry = false;
		level_number = Integer.parseInt(file.FindSection("LEVEL_NUMBER")[1]);
		String[] names =file.FindSection("NAMES");
		for (int i = 0; i< level_number; i++)
			{
			Level l = new Level("tlo.jpg", names[i+1]);
	        levels.add(l);
			}
		}
	
 	/** Metoda odpowiedzialana za zapisanie wynikow gry(ilosc punktow gracza oraz jego nick) do pliku Wyniki.txt
 	 * @param a punkty zdobyte przez gracza
 	 * @param b nick gracza*/
	public void zapisz_do_pliku(int a, String b)
	{
		tabela = new TabelaWynikow("Wyniki.txt");
		tabela.Odzczytaj_wyniki();
		tabela.sortowanie(a, b);
		
		String[] wyniki1;
		wyniki1 = new String[11];
		linie = new String[11];
		;
		for(int i =0;i<=10;i++)
		{
			wyniki1[i]=String.valueOf(tabela.punkty[i]);
		}
	    
	    
	    for(int j = 1;j<=10;j++)
	    {
	    	linie[j]= j+":"+tabela.Login[j-1]+":"+wyniki1[j-1];
	    } 
		    try {
		       fw = new FileWriter("Wyniki.txt");
		          } catch (IOException e) { 
		        e.printStackTrace();
		     }
		 
		    bw = new BufferedWriter(fw);
		   try {
		       for (int i = 1; i < linie.length; i++) {
		         bw.write(linie[i]);
		       bw.newLine();
		       }
		     } catch (IOException e) {
		        e.printStackTrace();
		     }

		     try {
		        bw.close();
		        fw.close();
		     } catch (IOException e) {
		           e.printStackTrace();
		     }
		  }
	/** Zmiana wartosci odpowiednich flag w przypadku przegrania gry przez gracza*/
	public void gameOver() { 
     	result = false;
     	gameEnded = true;
     	
     }
	/** Zmiana wartosci odpowiednich flag w przypadku wygrania gry przez gracza*/
    public void Win()
     {
     	result = true;
     	gameEnded = true;
     }
    	 public void run()  
    	 		{
    		 
        	    	stat = new Statystyki(Imiea);
        	    	for (int i = 0; i < levels.size(); i++) {
        			stat.pkt_tymczasowe = 0;
        			Level l = (Level)levels.get(i);
        			
        			game(l);
        			
        			if(!result)
        			{
        				break;
        			}
        			stat.zlicz_pkt();
        		}
        		koniec_gry();
        }
        /** Metoda sprawdzajaca czy gracz ukonczyl poziom i przeszedl do nastepnego*/
    	 public void checkLevel()
    	 {
    		 if(plansza1.b.bombaa.flaga_zwyciestwa && plansza1.b.bombaa.life)//wygrana runda
    		 {
    			 Win();
    			 System.out.println("win");
    			
    		 }
    		 if(!plansza1.b.bombaa.flaga_zwyciestwa && !plansza1.b.bombaa.life)//zabicie bombermana
    		 {
    			 gameOver();
    		 }
    	 }
     
    	 /**
          * Metoda odpowiadajaca za przebieg gry
          * @param l poziom ktory ma zostac uruchomiony
          */
          public void game(Level l) {
        	stayLevel=false;
        	gameEnded = false;
            plansza1 =new RysujPlansze(l.plikkonfiguracja,stat.Ilosc_zyc);
          
            while (!gameEnded) 
            	{
            	  updateStats(plansza1.b.bombaa.pkt);
                  checkLevel();
                //  System.out.println(plansza1.b.bombaa.ilosc_zyc);
             
                  
                  if(plansza1.pauza)
                  {
                	 plansza1.b.flaga_dostepu_p = true;
                	 stat.pauza = true;
                  }
                 if(!plansza1.pauza) 
                 {
                	 plansza1.b.flaga_dostepu_p = false;
                	 stat.pauza = false;
                 }
                }
            
            if(gameEnded && result)
            	{
            		Label.setText("YEEEEE NASTEPNY POZIOM!!!");
            		setVisible(true);
            		try {
						th.sleep(1000);
						} catch (InterruptedException e) {e.printStackTrace();}
            		setVisible(false);
            		plansza1.dispose();
            	}

            if(gameEnded && !(result) )
            	{
            		Label.setText("SROMOTNA PORAZKA!!!");
            		setVisible(true);
            		try {
            			th.sleep(1000);
            			} catch (InterruptedException e) {e.printStackTrace();}
        			setVisible(false);
        			plansza1.dispose();
            	}
          
        }
	
		/** Metoda odpowiedzialna za zaktualizowanie dancyh obiektu klasy Statystyki 
		 * @param a zdobute przez gracza punkty*/
		public void updateStats(int a)
		{
				stat.pkt_tymczasowe = stat.pkt_tymczasowe + a;
				stat.Ilosc_zyc = plansza1.b.bombaa.ilosc_zyc;
				stat.Liczba_bomb = plansza1.b.bombaa.ilosc_bombb;
				stat.Zasieg_bomby = plansza1.b.bombaa.sila_razenia;
				plansza1.b.bombaa.pkt = 0;
		}
		
		/** Czynnosci wykonywane po zakonczeniu gry */
		public void koniec_gry()
		{
			zapisz_do_pliku(stat.Punkty, stat.Imie);
    		plansza1.dispose();
    		stat.zmien_flage();
    		Label.setText("KONIEC GRY!!!  ZDOBYLES  " + stat.Punkty+"  PUNKTOW");
    		setVisible(true);
    		try {
				th.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
    		dispose();
    		
		}
		

	}

