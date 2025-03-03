package de.vanclausen.date4u.shell;

import org.jline.utils.AttributedString;
import org.jline.utils.AttributedStyle;
import org.springframework.context.annotation.Configuration;
import org.springframework.shell.jline.PromptProvider;

@Configuration
public class PromptProviderConfig implements PromptProvider {

    private static final PromptProvider userPromptProvider = () -> new AttributedString(
            "date4u:>", AttributedStyle.DEFAULT.foreground(AttributedStyle.GREEN)
    );
    private static final PromptProvider adminPromptProvider = () -> new AttributedString(
            "date4u:admin>", AttributedStyle.DEFAULT.foreground(AttributedStyle.YELLOW)
    );

    @Override
    public AttributedString getPrompt() {
        boolean isAdmin = (Math.round(Math.random()) == 1);
        return isAdmin ? adminPromptProvider.getPrompt() : userPromptProvider.getPrompt();
    }
}
