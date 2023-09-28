import java.awt.*;

/**
 * Un rectangle qui peut être manipulé et se dessine sur un fond.
 *
 * @author LemilCa
 * @version 1.0 (27 septembre 2023)
 */

public class RectangleShape
{
    private int height;
	private int width;
	private int xPosition;
	private int yPosition;
	private String color;
	private boolean isVisible;

    /**
     * Crée un nouveau rectangle à la position par défaut avec la couleur par défaut.
     */
    public RectangleShape()
    {
        height = 30;
		width = 40;
		xPosition = 50;
		yPosition = 15;
		color = "black";
		isVisible = false;
    }

    /**
	 * Affiche ce rectangle. S'il était déjà visible, ne fait rien.
	 */
	public void makeVisible ()
	{
		isVisible = true;
		
		draw ();
	}

	/**
     * Masque ce rectangle. S'il était déjà masqué, ne fait rien.
     */
	public void makeInvisible ()
	{
		erase ();
		
		isVisible = false;
	}

	/**
     * Déplace le rectangle de quelques pixels à droite.
     */
	public void moveRight ()
	{
		moveHorizontal (20);
	}

	/**
     * Déplace le rectangle de quelques pixels à gauche.
     */
	public void moveLeft ()
	{
		moveHorizontal (-20);
	}

	/**
     * Déplace le rectangle de quelques pixels vers le haut.
     */
	public void moveUp ()
	{
		moveVertical (-20);
	}

	/**
     * Déplace le rectangle de quelques pixels vers le bas.
     */
	public void moveDown ()
	{
		moveVertical (20);
	}

	/**
     * Déplace le rectangle à l'horizontale de la valeur de 'distance'.
     */
	public void moveHorizontal (int distance)
	{
		erase ();
		
		xPosition += distance;
		
		draw ();
	}

	/**
     * Déplace le rectangle à la verticale de la valeur de 'distance'.
     */
	public void moveVertical(int distance)
    {
        erase ();
        
		yPosition += distance;
        
		draw ();
    }

	/**
     * Déplace lentement le rectangle à l'horizontale de la valeur de 'distance'.
     */
	public void slowMoveHorizontal (int distance)
    {
        int delta;

        if( distance < 0 ) { delta = -1; distance = -distance; }
        else delta = 1;

        for( int i = 0; i < distance; i ++ ) { xPosition += delta; draw(); }
    }

	/**
     * Déplace lentement le rectangle à la verticale de la valeur de 'distance'.
     */
	public void slowMoveVertical (int distance)
    {
        int delta;

        if( distance < 0 ) { delta = -1; distance = -distance; }
        else delta = 1;

        for( int i = 0; i < distance; i ++ ) { yPosition += delta; draw(); }
    }

	/**
     * Passe à la nouvelle taille (en pixels). Elle doit être >= 0.
     */
	public void changeSize (int newHeight, int newWidth)
    {
        erase();
        
		height = newHeight;
		width = newWidth;

        draw();
    }

	/**
	 * Tourne le rectangle (ça échange juste la largeur et la longueur).
	 */
	public void turn ()
	{
		changeSize (width, height);
	}

	/**
     * Modifie la couleur. Les couleurs valables sont "red", "yellow", "blue", "green",
     * "magenta" et "black".
     */
	public void changeColor (String newColor)
	{
		color = newColor;

		draw ();
	}

	/*
     * Dessine le rectangle à l'écran avec les spécifications actuelles.
     */
	private void draw ()
	{
		if ( isVisible == false ) return;
		
		Canvas canvas = Canvas.getCanvas ();
			
		canvas.draw ( this, color, new Rectangle (xPosition, yPosition, height, width) );
		canvas.wait (10);
	}

	/*
     * Efface le rectangle de l'écran.
     */
	private void erase ()
	{
		if ( isVisible == false ) return;

		Canvas canvas = Canvas.getCanvas ();

		canvas.erase (this);
	}
}