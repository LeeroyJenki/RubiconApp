package ru.rubiconepro.study.Modules.PdfViewer.Layout;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.github.barteksc.pdfviewer.PDFView;

import java.io.File;

import ru.rubiconepro.study.R;

public class PdfViewerMainLayout extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pdf_viewer_main_layout);

        PDFView view = findViewById(R.id.pdfView);
     //   view.fromUri(Uri.parse("http://www.ihst.ru/projects/sohist/books/bronstein.pdf"));



     //   File file = new File("src/tmp/assets/icon.png");
     //   file.
     //   Image image = ImageIO.read(file);

        File f = new File("C:\\FilesRubic\\bronstein.pdf");
        System.out.println(f.getName());

        view.fromFile(f);

 //       File file = new File(filePath);
 //       if (file.exists()) {
 //           // открываем файл для чтения
 //       }

    }
}
