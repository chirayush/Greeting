import org.apache.commons.lang3.StringUtils;

final class Greeting {
    String greet(final String name) {
        if (StringUtils.isEmpty(name)) {
            return sayHello("my friend");
        }

        if (StringUtils.isAllUpperCase(name)) {
            return "HELLO " + name + "!";
        }
        return sayHello(name);
    }

    private String sayHello(String name) {
        return "Hello, " + name + ".";
    }
}
