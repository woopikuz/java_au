import java.io.IOException;
import java.net.URISyntaxException;

public final class Main {
    public static void main(final String[] args) throws IOException, URISyntaxException {
        final String problemsGroupName = args[0].substring(0, 1).toUpperCase() + args[0].substring(1);
        final MdBuilder mdBuilder = new MdBuilder(problemsGroupName);
        mdBuilder.write(String.format("%s.md", args[0]));
    }
}
