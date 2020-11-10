package app.options;

import org.junit.jupiter.api.Test;

public class PathOptionTests extends OptionsTests {
	@Test
	void notPassingPath_ShouldThrowException() {
		String[] args = givenOptionsNotPassing(PATH_OPTION);

		assertInvalidOptionIsThrown(args);
	}
}
