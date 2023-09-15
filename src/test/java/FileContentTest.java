import cosmic.lang.EntryPoint;
import cosmic.lang.entry.FileContentsContentAdapter;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.Buffer;
import java.nio.charset.StandardCharsets;

public class FileContentTest {

    public static void main(String[] args) {

        InputStream streamIn = FileContentsContentAdapter.class.getResourceAsStream("/Program.cosmic");
        assert streamIn != null;

        StringBuilder inputString = new StringBuilder();

        try {
            InputStreamReader reader = new InputStreamReader(streamIn, StandardCharsets.UTF_8);
            BufferedReader bufferedReader = new BufferedReader(reader);

            String line;

            while ((line = bufferedReader.readLine()) != null) {
                inputString.append(line).append("\n");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }

        FileContentsContentAdapter contentAdapter = new FileContentsContentAdapter(inputString.toString());
        EntryPoint.Interpret(contentAdapter);

    }

}
