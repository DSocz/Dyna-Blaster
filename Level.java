package bomberman;
import java.util.ArrayList;
import java.util.List;


/** Obiekty klasy Level przechowuja nazwe pliku konfiguracyjnego oraz nazwe pliku przechowujacego tlo */
public class Level {
	/** nazwa pliku .jpg */
	public String pliktlo;
	/** zawiera dane okreslajace polozenie elementów planszy */
	public String plikkonfiguracja;
	
	/** 
	 * Konstruktor klasy Level
	 * @param pliktlo nazwa pliku .jpg
	 * @param zawiera dane okreslajace polozenie elementow planszy
	 */
	public Level(String pliktlo, String plikkonfiguracja)
	{
		this.pliktlo=pliktlo;
		this.plikkonfiguracja=plikkonfiguracja;
	}
}
