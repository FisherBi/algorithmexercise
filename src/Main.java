import java.io.*;
import java.text.ParseException;

public class Main {

    private static void formatFile() {
        File csvFile = new File("csv.txt");
        File outFile = new File("output.txt");
        String inString;

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(csvFile));
             BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(outFile))) {
            while ((inString = bufferedReader.readLine()) != null) {
                String formatStr = convertColumn(inString);
                bufferedWriter.write(formatStr);
                bufferedWriter.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    private static String convertColumn(String inString) throws ParseException {
        StringBuffer output = new StringBuffer();
        String col[] = inString.trim().split(",(?=([^\\\"]*\\\"[^\\\"]*\\\")*[^\\\"]*$)", -1);
        output.append(Integer.parseInt(removeRedundantQuotation(col[0]))).append("\t");
        output.append("'"+removeRedundantQuotation(col[1])+"'").append("\t");
        output.append("'"+removeRedundantQuotation(col[2])+"'").append("\t");
        output.append(Float.parseFloat(removeRedundantQuotation(col[3]))).append("\t");
        output.append(removeRedundantQuotation(col[4]).replace("-","/"));
        return output.toString();
    }

    private static String removeRedundantQuotation(String str) {
        if (str != null && str.length() > 2) {
            if (str.indexOf("\"") == 0) {
                str = str.substring(1);
            }
            if (str.lastIndexOf("\"") == (str.length() - 1)) {
                str = str.substring(0, str.length() - 1);
            }
            str = str.replaceAll("\"\"", "\"");
        }
        return str;
    }

    public static void main(String[] args) {
        formatFile();
    }
}
