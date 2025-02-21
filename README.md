# HTML Analyzer

## Overview
HTML Analyzer is a Java-based tool that fetches and processes an HTML page from a given URL. It identifies and extracts the deepest nested text from the HTML structure.

## Features
- Fetches HTML content from a specified URL.
- Parses the HTML structure to determine the deepest text node.
- Handles malformed HTML structures.
- Uses only Java standard libraries (Java 17).

## Prerequisites
- **Java 17** or later must be installed.
- An active internet connection to fetch HTML content.

## Installation
1. Clone the repository:
   ```sh
   git clone https://github.com/your-username/html-analyzer.git
   ```
2. Navigate to the project directory:
   ```sh
   cd html-analyzer
   ```
3. Compile the Java file:
   ```sh
   javac HtmlAnalyzer.java
   ```

## Usage
Run the program with a URL as an argument:
```sh
java HtmlAnalyzer "https://example.com"
```

Example Output:
```
Deepest text found: "This is the deepest content"
```

## Code Structure
- **HtmlAnalyzer.java**: Main class that handles fetching and processing HTML.
- **fetchHtmlContent()**: Retrieves the raw HTML content from the given URL.
- **findDeepestText()**: Parses the HTML structure and identifies the deepest text node.

## Known Issues
- Does not handle JavaScript-rendered pages (client-side rendering).
- Might not handle all edge cases in malformed HTML.

## License
This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.


