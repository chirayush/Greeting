import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

final class Greeting {

    private static final String HELLO = "Hello, ";
    private static final String PERIOD = ".";

    private StringBuilder greetingBuilder = new StringBuilder();

    String greet(final String... names) {
        if (names == null || names[0].isEmpty()) {
            return HELLO + "my friend.";
        }
        List<String> normalGreetNames = getNormalGreetNames(names);

        if (!normalGreetNames.isEmpty()) {
            buildNormalGreeting(normalGreetNames);
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

    private void buildNormalGreeting(List<String> normalGreetNames) {
        greetingBuilder.append(HELLO);

        final int greetNameSize = normalGreetNames.size();
        if (greetNameSize == 1) {
            greet1Name(normalGreetNames);
        } else if (greetNameSize == 2) {
            greet2Names(normalGreetNames);
        } else {
            greetMultipleNames(normalGreetNames, greetNameSize);
        }

        greetingBuilder.append(PERIOD);
    }

    private void greetMultipleNames(List<String> normalGreetNames, int greetNameSize) {
        for (int i = 0; i < greetNameSize - 1; i++) {
            greetingBuilder.append(normalGreetNames.get(i)).append(", ");
        }

        greetingBuilder.append("and ")
                .append(getLastName(normalGreetNames));
    }

    private void greet2Names(List<String> normalGreetNames) {
        greetingBuilder.append(normalGreetNames.get(0))
                .append(" and ")
                .append(normalGreetNames.get(1));
    }

    private void greet1Name(List<String> normalGreetNames) {
        greetingBuilder.append(normalGreetNames.get(0));
    }

    private List<String> getNormalGreetNames(String[] names) {
        List<String> greetNameList = Arrays.asList(names);
        if (Arrays.stream(names).anyMatch(name -> name.contains("\""))) {
            greetNameList = Arrays.stream(names)
                    .map(name -> name
                            .replace(",", ":")
                            .replace("\"", ""))
                    .collect(Collectors.toList());
        }
        return greetNameList.stream()
                .filter(name -> !StringUtils.isAllUpperCase(name))
                .flatMap(name -> Arrays.stream(name.split(",")))
                .map(String::trim)
                .map(name -> name.replace(":", ","))
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
