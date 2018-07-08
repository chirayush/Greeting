import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class GreetingTest {
    private Greeting greeting = new Greeting();

    @Test
    public void greetShouldReturnNameForSimpleGreetingBob() {
        assertEquals("Hello, Bob.", greeting.greet("Bob"));
    }

    @Test
    public void greetShouldReturnNameForSimpleGreetingRam() {
        assertEquals("Hello, Ram.", greeting.greet("Ram"));
    }

    @Test
    public void greetShouldReturnMyFriendWhenNameIsEmpty() {
        assertEquals("Hello, my friend.", greeting.greet(""));
    }

    @Test
    public void greetShouldReturnMyFriendWhenNameIsNull() {
        assertEquals("Hello, my friend.", greeting.greet( null));
    }

    @Test
    public void greetShouldGreetInUppercaseWhenNameIsUppercaseJERRY() {
        assertEquals("HELLO JERRY!", greeting.greet("JERRY"));
    }

    @Test
    public void greetShouldGreetInUppercaseWhenNameIsUppercaseJOE() {
        assertEquals("HELLO JOE!", greeting.greet("JOE"));
    }

    @Test
    public void greetShouldGreet2Names() {
        assertEquals("Hello, Jill and Jane.", greeting.greet("Jill", "Jane"));
    }

    @Test
    public void greetShouldGreet3Names() {
        assertEquals("Hello, Amy, Brian, and Charlotte.", greeting.greet("Amy", "Brian", "Charlotte"));
    }

    @Test
    public void greetShouldGreet4Names() {
        assertEquals("Hello, Amy, Brian, Charlie, and Charlotte.", greeting.greet("Amy", "Brian", "Charlie", "Charlotte"));
    }

    @Test
    public void greetShouldMixShoutingAndNormalMixing() {
        assertEquals("Hello, Amy and Charlotte. AND HELLO BRIAN!", greeting.greet("Amy", "BRIAN", "Charlotte"));
    }

    @Test
    public void greetShouldSeparateOutCommaSeparatedNames() {
        assertEquals("Hello, Bob, Charlie, and Dianne.", greeting.greet("Bob", "Charlie, Dianne"));
    }

    @Test
    public void greetShouldSkipCommaSeparationWhenEscapedByDoubleQuotes() {
        assertEquals("Hello, Bob and Charlie, Dianne.", greeting.greet("Bob", "\"Charlie, Dianne\""));
    }

    @Test
    public void greetShouldSeparateOutCSVAndEscapeDoubleQuotes() {
        assertEquals("Hello, Bob, Amy, Brian, and Charlie, Dianne.", greeting.greet("Bob", "Amy, Brian", "\"Charlie, Dianne\""));
    }
}

