import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;

final class MdBuilder {
    private final String problemsGroupName;
    private final DataPayload data;

    MdBuilder(final String problemsGroupName, final String inputFileName) throws FileNotFoundException {
        this.problemsGroupName = problemsGroupName;

        final File inputFile = new File(inputFileName);
        final Scanner inputFileReader = new Scanner(inputFile);

        final String problemTitle = inputFileReader.nextLine().replaceAll("^\\d+. ", "");
        final String problemLink = inputFileReader.nextLine();

        inputFileReader.useDelimiter("\\Z");
        final String problemSourceCode = inputFileReader.next();

        data = new DataPayload(problemTitle, problemLink, problemSourceCode);
        inputFileReader.close();
    }

    MdBuilder(final String problemsGroupName) throws FileNotFoundException {
        this(problemsGroupName, "source_leetcode_data.txt");
    }

    void write(final String outputFileName) throws IOException, URISyntaxException {
        final Path outputFilePath = Paths.get(outputFileName);
        final List<String> lines = Files.readAllLines(outputFilePath, StandardCharsets.UTF_8);
        if (lines.isEmpty()) {
            Files.write(outputFilePath, String.format("# %s\n\n%s\n\n%s", problemsGroupName, generateProblemEntry(), generateProblemSection()).getBytes());
        } else {
            for (int i = 0; i < lines.size(); ++i) {
                final String line = lines.get(i);
                if (line.startsWith("##")) {
                    lines.add(i - 1, generateProblemEntry());
                    break;
                }
            }
            lines.add("\n" + generateProblemSection());
            Files.write(outputFilePath, lines, StandardCharsets.UTF_8);
        }
    }

    private String generateProblemEntry() throws URISyntaxException {
        // final URI problemUri = new URI(data.getLink());
        // return String.format("+ [%s](#%s)", data.getTitle(), new File(problemUri.getPath()).getName());
        return String.format("+ [%s](#%s)", data.getTitle(), data.getTitle());
    }

    private String generateProblemSection() {
        return String.format("## %s\n\n%s\n\n```\n%s\n```", data.getTitle(), data.getLink(), data.getSourceCode());
    }
}
