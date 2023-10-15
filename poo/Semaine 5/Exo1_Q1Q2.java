import java.util.ArrayList;
import java.util.Arrays;

public class Exo1_Q1Q2
{
	public static void main (String [] args)
	{
		ArrayList < String > liste = new ArrayList < String > ();
		liste.addAll ( Arrays.asList ( "Bonjour", "Comment tu vas ?" ) );
		liste.add ( 0, "Link réveille toi" );
		
		System.out.println (liste);
		
		liste.remove ("Bonjour");

		
		for ( int index = 0; index < liste.size (); index ++ )
			if ( liste.get (index).length () % 2 == 0 ) liste.set ( index, "b" );

		System.out.println (liste);

		return;
	}
}

