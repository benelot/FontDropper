package ch.liviocasanova.swing;

import java.awt.Font;
import java.io.File;

public class OTFFont {
	Font font;
	//the method
    public Font grabFont(String fontName,float size){ 
        try{
            font = Font.createFont(Font.TRUETYPE_FONT, new File(
					fontName));
        }catch(Exception e){;
        e.printStackTrace();
        }
        return font.deriveFont(size);
    }

}
