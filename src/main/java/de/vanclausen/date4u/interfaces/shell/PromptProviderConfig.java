package de.vanclausen.date4u.interfaces.shell;

import org.jline.utils.AttributedString;
import org.jline.utils.AttributedStyle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.shell.jline.PromptProvider;

@Configuration
public class PromptProviderConfig implements PromptProvider {

    private final Logger log = LoggerFactory.getLogger(getClass());
    private final boolean isAdmin;

    private final PromptProvider userPromptProvider = () -> new AttributedString(
            "date4u:>", AttributedStyle.DEFAULT.foreground(AttributedStyle.GREEN)
    );
    private final PromptProvider adminPromptProvider = () -> new AttributedString(
            "date4u:admin>", AttributedStyle.DEFAULT.foreground(AttributedStyle.YELLOW)
    );

    public PromptProviderConfig() {
        isAdmin = (Math.random() > 0.5);
        log.info("User is admin: {}", isAdmin);
    }

    @Override
    public AttributedString getPrompt() {
        return isAdmin ? adminPromptProvider.getPrompt() : userPromptProvider.getPrompt();
    }
}
