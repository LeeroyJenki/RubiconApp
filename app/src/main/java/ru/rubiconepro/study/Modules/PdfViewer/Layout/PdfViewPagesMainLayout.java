package ru.rubiconepro.study.Modules.PdfViewer.Layout;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import es.voghdev.pdfviewpager.library.asset.CopyAsset;
import es.voghdev.pdfviewpager.library.asset.CopyAssetThreadImpl;
import ru.rubiconepro.study.R;

public class PdfViewPagesMainLayout extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pdf_view_pages_main_layout);
        // from this
        // https://github.com/voghDev/PdfViewPager

        CopyAsset copyAsset = new CopyAssetThreadImpl(context, new Handler());
        copyAsset.copy(asset, new File(getCacheDir(), "sample.pdf").getAbsolutePath());

        pdfViewPager = new PDFViewPager(this, "sample.pdf");

    }
}
