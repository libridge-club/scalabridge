package br.com.sbk.sbking.networking.jackson;

import static br.com.sbk.sbking.logging.SBKingLogger.LOGGER;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import br.com.sbk.sbking.core.Suit;
import br.com.sbk.sbking.core.rulesets.abstractrulesets.Ruleset;
import br.com.sbk.sbking.core.rulesets.concrete.PositiveNoTrumpsRuleset;
import br.com.sbk.sbking.core.rulesets.concrete.PositiveRuleset;
import br.com.sbk.sbking.core.rulesets.concrete.PositiveWithTrumpsRuleset;

public class RulesetDeserializer extends StdDeserializer<Ruleset> {

    private static Map<String, Class<? extends Ruleset>> simpleNameToClass = new HashMap<>();
    private static Map<String, Suit> suitNameToSuit = new HashMap<>();

    // Static initialization block to avoid doing this calculation every
    // deserialization
    static {
        simpleNameToClass.put("NoRuleset", PositiveRuleset.class);
        simpleNameToClass.put("PositiveNoTrumpsRuleset", PositiveNoTrumpsRuleset.class);
        simpleNameToClass.put("PositiveWithTrumpsRuleset", PositiveWithTrumpsRuleset.class);

        suitNameToSuit.put("Diamonds", Suit.DIAMONDS);
        suitNameToSuit.put("Clubs", Suit.CLUBS);
        suitNameToSuit.put("Hearts", Suit.HEARTS);
        suitNameToSuit.put("Spades", Suit.SPADES);
    }

    public RulesetDeserializer() {
        this(null);
    }

    public RulesetDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public Ruleset deserialize(JsonParser jp, DeserializationContext ctxt)
            throws IOException, JsonProcessingException {
        JsonNode node = jp.getCodec().readTree(jp);
        String rulesetName = node.get("rulesetName").asText();
        String trumpSuit = node.get("trumpSuit").asText();
        Class<? extends Ruleset> class1 = simpleNameToClass.get(rulesetName);
        Ruleset response = new PositiveRuleset();
        if (PositiveWithTrumpsRuleset.class.equals(class1)) {
            response = new PositiveWithTrumpsRuleset(suitNameToSuit.get(trumpSuit));
        } else {
            try {
                response = class1.getDeclaredConstructor().newInstance();
            } catch (Exception e) {
                LOGGER.error(e);
            }
        }
        return response;
    }
}
