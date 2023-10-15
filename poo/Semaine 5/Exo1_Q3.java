import java.util.ArrayList;

public class Exo1_Q3
{
	public static void main (String [] args)
	{
		ArrayList < String > liste = new ArrayList < String > ();
		
		String string = "";
		for ( int loop = 0; loop < 3; loop ++ )
			{ string += "a"; liste.add (string); }

		boolean trouve = false;

		int index = 0;
		while ( index < 3 && ! trouve )
			{ trouve = liste.get (index).equals ("aa"); index += 1; }

		if (trouve)
			System.out.println ( "Chaine \"aa\" présente" );

		return;
	}	
}



