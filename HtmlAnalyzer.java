import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HtmlAnalyzer {

    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Uso: java HtmlAnalyzer <URL>");
            return;
        }

        String url = args[0];
        try {
            String htmlContent = fetchHtmlContent(url);
            String deepestText = findDeepestText(htmlContent);
            System.out.println(deepestText);
        } catch (IOException e) {
            System.out.println("URL connection error");
        } catch (IllegalArgumentException e) {
            System.out.println("malformed HTML");
        }
    }

    private static String fetchHtmlContent(String url) throws IOException {
        StringBuilder content = new StringBuilder();
        HttpURLConnection connection = null;

        try {
            URL urlObj = new URL(url);
            connection = (HttpURLConnection) urlObj.openConnection();
            connection.setRequestMethod("GET");

            try (BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    content.append(line);
                }
            }
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }

        return content.toString();
    }

    private static String findDeepestText(String htmlContent) {
        // Expressões regulares para capturar tags e textos
        Pattern tagPattern = Pattern.compile("<(/?)(\\w+)[^>]*>"); // Captura tags de abertura e fechamento
        Pattern textPattern = Pattern.compile(">([^<]+)<"); // Captura texto entre tags

        int maxDepth = 0, currentDepth = 0;
        String deepestText = null;

        Matcher tagMatcher = tagPattern.matcher(htmlContent);
        Matcher textMatcher = textPattern.matcher(htmlContent);

        while (tagMatcher.find()) {
            boolean isClosingTag = tagMatcher.group(1).equals("/");

            if (!isClosingTag) {
                // Tag de abertura -> aumenta profundidade
                currentDepth++;
            } else {
                // Tag de fechamento -> reduz profundidade
                currentDepth--;
            }

            // Buscar o texto após a tag aberta
            if (textMatcher.find(tagMatcher.end())) {
                String text = textMatcher.group(1).trim();
                if (!text.isEmpty() && currentDepth > maxDepth) {
                    maxDepth = currentDepth;
                    deepestText = text;
                }
            }
        }

        if (deepestText == null) {
            throw new IllegalArgumentException("HTML malformado");
        }

        return deepestText;
    }
}
