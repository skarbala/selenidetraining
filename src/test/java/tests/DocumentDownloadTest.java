package tests;

import base.TestBase;
import org.junit.Test;
import utils.DataUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class DocumentDownloadTest extends TestBase {

    @Test
    public void itShouldDownloadBusinessRules() throws IOException {
        open("https://www.tatrabanka.sk/sk/o-banke/dolezite-dokumenty/obchodne-podmienky/");

        File file =$(byText("Všeobecné obchodné podmienky Tatra banky, a.s.")).download();
        DataUtils.printPdfContent(file);
    }
}
