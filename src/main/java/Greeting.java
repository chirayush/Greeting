import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

final class Greeting {
    private static final String HELLO_GREET = "Hello, ";
    private static final String HELLO_SHOUT = "HELLO ";
    private static final String PERIOD = ".";

    String greet(final String... names) {
        if (names == null || StringUtils.isEmpty(names[0])) {
            return HELLO_GREET + "my friend" + PERIOD;
        }

        List<String> normalNames = getNormalNamesToGreet(names);

        StringBuilder greeting = new StringBuilder();
        if (!normalNames.isEmpty()) {
            greeting.append(HELLO_GREET);
            if (normalNames.size() == 1) {
                greetFor1Name(normalNames, greeting);
            } else if (normalNames.size() == 2) {
                greetFor2Names(normalNames, greeting);
            } else {
                greetForMultipleNames(normalNames, greeting);
            }
        }

        String shoutingName = getShoutingNameToGreet(names);

        if (!shoutingName.isEmpty()) {
            if (!normalNames.isEmpty()) {
                greeting.append(" AND ");
            }
            shoutName(shoutingName, greeting);
        }
        return greeting.toString();

    }

    private String getShoutingNameToGreet(String[] names) {
        return Arrays.stream(names)
                .filter(StringUtils::isAllUpperCase)
                .findFirst()
                .orElse("");
    }

    private List<String> getNormalNamesToGreet(String[] names) {
        return Arrays.stream(names)
                .filter(name -> !StringUtils.isAllUpperCase(name))
                .flatMap(name -> Arrays.stream(name.split(",")))
                .map(String::trim)
                .collect(Collectors.toList());
    }

    private void shoutName(String shoutingName, StringBuilder greeting) {
        greeting.append(HELLO_SHOUT).append(shoutingName).append("!");
    }

    private void greetFor1Name(List<String> normalNames, StringBuilder greeting) {
        greeting.append(normalNames.get(0)).append(PERIOD);
    }

    private void greetFor2Names(List<String> normalNames, StringBuilder greeting) {
        greeting.append(normalNames.get(0))
                .append(" and ")
                .append(normalNames.get(1))
                .append(PERIOD);
    }

    private void greetForMultipleNames(List<String> normalNames, StringBuilder greeting) {
        for (int i = 0; i < normalNames.size() - 1; i++) {
            greeting.append(normalNames.get(i)).append(", ");
        }
        greeting.append("and ")
                .append(normalNames.get(normalNames.size() - 1))
                .append(PERIOD);
    }

}
