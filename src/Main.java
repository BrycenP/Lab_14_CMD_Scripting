import javax.swing.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;


public class Main
{
    public static void main(String[] args)
    {
        JFileChooser chooser = new JFileChooser("src");
        File selectedFile;
        String rec = "";
        if (args.length > 0) {
            selectedFile = new File(args[0]);
            if (!selectedFile.exists()) {
                System.out.println("The file specified does not exist.");
                return;
            }
        } else {
            chooser.setCurrentDirectory(new File(System.getProperty("user.dir") + "/src"));
            if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
                selectedFile = chooser.getSelectedFile();
            } else {
                System.out.println("No file selected!!! ... exiting.\nRun the program again and select a file.");
                return;
            }
        }
        try {
            Path file = selectedFile.toPath();
            InputStream in = new BufferedInputStream(Files.newInputStream(file));
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            int lines = 0;
            int wordsNum = 0;
            int characters = 0;
            while (reader.ready()) {
                rec = reader.readLine();
                lines++;
                String [] words = rec.split(",\\s");
                wordsNum += words.length;
                characters += rec.length();
                System.out.printf("\nLine %4d %-60s ", lines, rec);
            }
            reader.close();
            System.out.println("\n\nData file read!");
            System.out.println("Name: " + selectedFile.getName());
            System.out.println("Word count: " +wordsNum);
            System.out.println("Line count: " +lines);
            System.out.println("Character count: " + characters);
        }
        catch (FileNotFoundException e)
        {
            System.out.println("File not found!!!");
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

}