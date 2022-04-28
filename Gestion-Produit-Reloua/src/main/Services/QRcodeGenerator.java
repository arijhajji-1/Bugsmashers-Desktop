package Services;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;

public class QRcodeGenerator {
    private static final String QR_CODE_IMAGE_PATH = "./MyQRCode.png";

    public static javafx.scene.image.Image generateQRCodeImage(String text, int width, int height, String filePath)
            throws WriterException, IOException {QRCodeWriter qrCodeWriter = new QRCodeWriter();
        String fileType = "png";

        BufferedImage bufferedImage = null;
        try {
            BitMatrix byteMatrix = qrCodeWriter.encode(text, BarcodeFormat.QR_CODE, width, height);
            bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
            bufferedImage.createGraphics();

            Graphics2D graphics = (Graphics2D) bufferedImage.getGraphics();
            graphics.setColor(Color.WHITE);
            graphics.fillRect(0, 0, width, height);
            graphics.setColor(Color.BLACK);

            for (int i = 0; i < height; i++) {
                for (int j = 0; j < width; j++) {
                    if (byteMatrix.get(i, j)) {
                        graphics.fillRect(i, j, 1, 1);
                    }
                }
            }

            System.out.println("Success...");

        } catch (WriterException ex) {
            System.out.println(ex.getCause());
        }


        ImageIO.write(bufferedImage, "png", new File("C:\\Users\\USER\\Downloads\\gestionProduitLast\\src\\main\\resources\\img\\"+filePath));
        try {
            javafx.scene.image.Image im = new Image(QRcodeGenerator.class.getResourceAsStream("/img/" + filePath));
            return im;
        }catch(Exception e) {
            System.out.println(e.getCause());
        }

        return new Image("foo.jpg");

    }
}
