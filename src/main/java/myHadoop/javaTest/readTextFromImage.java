package myHadoop.javaTest;
import org.bytedeco.javacpp.*;
import static org.bytedeco.javacpp.lept.*;
import static org.bytedeco.javacpp.tesseract.*;

public class readTextFromImage {
	public static void main(String[] args) {
        BytePointer outText;
     
        TessBaseAPI api = new TessBaseAPI();
        // Initialize tesseract-ocr with English, without specifying tessdata path
        
        
        if (api.Init(null, "eng") != 0) {
            System.err.println("Could not initialize tesseract.");
            System.exit(1);
        }
		
        // Open input image with leptonica library
        PIX image = pixRead(args.length > 0 ? args[0] : "/home/rajendra/Pictures/IMAGES/amazon.png");
        api.SetImage(image);
        // Get OCR result
        outText = api.GetUTF8Text();
        System.out.println("OCR output:\n") ;
        System.out.print("\n"+outText.getString());

        // Destroy used object and release memory
        api.End();
        outText.deallocate();
        pixDestroy(image);
    }

}
