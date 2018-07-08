import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

final class Greeting {

    private static final String HELLO = "Hello, ";
    private static final String PERIOD = ".";

    String greet(final String... names) {
        if (names == null || names[0].isEmpty()) {
            return HELLO + "my friend.";
        }
        List<String> normalGreetNames = getNormalGreetNames(names);

        StringBuilder greetingBuilder = new StringBuilder();
        if (!normalGreetNames.isEmpty()) {
            buildNormalGreeting(normalGreetNames, greetingBuilder);
        }

        String shoutingName = getShoutingName(names);
        if (!normalGreetNames.isEmpty() && !shoutingName.isEmpty()) {
            greetingBuilder.append(" AND ");
        }

        if (!shoutingName.isEmpty()) {
            greetingBuilder.append("HELLO ")
                    .append(shoutingName)
                    .append("!");
        }

        return greetingBuilder.toString();
    }

    private void buildNormalGreeting(List<String> normalGreetNames, StringBuilder greetingBuilder) {
        greetingBuilder.append(HELLO);

        final int greetNameSize = normalGreetNames.size();
        if (greetNameSize == 1) {
            greetingBuilder.append(normalGreetNames.get(0));
        } else if (greetNameSize == 2) {
            greetingBuilder.append(normalGreetNames.get(0))
                    .append(" and ")
                    .append(normalGreetNames.get(1));
        } else {
            for (int i = 0; i < greetNameSize - 1; i++) {
                greetingBuilder.append(normalGreetNames.get(i)).append(", ");
            }

            greetingBuilder.append("and ")
                    .append(getLastName(normalGreetNames));
        }

        greetingBuilder.append(PERIOD);
    }

    private List<String> getNormalGreetNames(String[] names) {
        return Arrays.stream(names)
                .filter(name -> !StringUtils.isAllUpperCase(name))
                .collect(Collectors.toList());
    }

    private String getShoutingName(String[] names) {
        return Arrays.stream(names)
                .filter(StringUtils::isAllUpperCase)
                .findFirst()
                .orElse("");
    }

    private String getLastName(List<String> normalGreetNames) {
        return normalGreetNames.get(normalGreetNames.size() - 1);
    }
}
