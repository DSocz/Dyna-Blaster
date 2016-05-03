package bomberman;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;

/**
 *Klasa odpowiedzialna za stworzenie nowego okna, w ktorym jest rysowana plansza oraz obslugujaca interface KeyListener
 */
public class RysujPlansze extends JFrame implements KeyListener {
	    /** obiekt klasy plansza */
		Plansza b;
		/** flaga pauzy */
		Boolean pauza = false;
		
		
		/** 
		 * Konstruktor klasy RysujPlansze
		 * @param a plik konfiguracyjny
		 */
		public RysujPlansze(String a, int c) {
	    super("BOMBERMAN");
	    b =new Plansza(a,650, 650,c); 
	    add(b);
	    setLocation(500,20);
	    addKeyListener(this);
	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    pack();
	    setLocationRelativeTo(null);
	    setVisible(true);
	    
	  }
	
	/** Metoda zamyka okno gry */
	public void zamknij_okno_gry()
	{
		this.dispose();
	}
	
	@Override
	public void keyPressed(KeyEvent e) {}
	
	/**
	 * obsluga zdarzen przy zwolnieniu klawisza strzalki
	 * @param e zdarzenie
	 */
	@Override
	public void keyReleased(KeyEvent e) {
		switch(e.getKeyCode())
		{
			case 37: if(!pauza){b.ruch_bomberman('l');}
				break;
			case 38:if(!pauza){b.ruch_bomberman('g');}
				break;
			case 39:if(!pauza){b.ruch_bomberman('p');}
				break;
			case 40:if(!pauza){b.ruch_bomberman('d');}
				break;	
			case 32:if(!pauza){b.ruch_bomberman('s');}
				break;
			case 80:
				if(!pauza)
					{pauza = true;}
				else 
					{pauza = false;}
				break;
		}		
	}

	@Override
	public void keyTyped(KeyEvent e) {}
	  
}
