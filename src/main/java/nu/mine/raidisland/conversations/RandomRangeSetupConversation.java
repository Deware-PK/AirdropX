package nu.mine.raidisland.conversations;

import nu.mine.raidisland.Core;
import nu.mine.raidisland.airdrop.Airdrop;
import org.bukkit.conversations.ConversationContext;
import org.bukkit.conversations.Prompt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.mineacademy.fo.Valid;
import org.mineacademy.fo.conversation.SimplePrompt;

public class RandomRangeSetupConversation extends SimplePrompt {

	private final Airdrop airdrop;

	public RandomRangeSetupConversation(Airdrop airdrop) {
		super(true);

		this.airdrop = airdrop;
	}

	@Override
	protected String getPrompt(ConversationContext context) {
		return "&eWrite the range between 20 - 10,000. &cType \"Exit\" to end conversation.";
	}

	@Override
	protected boolean isInputValid(final ConversationContext context, final String input) {
		if (!Valid.isInteger(input))
			return false;

		final int range = Integer.parseInt(input);

		return range >= 20 && range <= 10000;
	}

	@Override
	protected String getFailedValidationText(final ConversationContext context, final String invalidInput) {
		return "Invalid range: '" + invalidInput + "'. Only enter whole numbers";
	}

	@Nullable
	@Override
	protected Prompt acceptValidatedInput(@NotNull ConversationContext conversationContext, @NotNull String s) {

		final int range = Integer.parseInt(s);

		airdrop.setRandomSpawnRange(range);

		tell(Core.PREFIX + " &aYou're changing auto spawn range for &f" + airdrop.getName() + "&a to " + s);

		return END_OF_CONVERSATION;
	}

}
