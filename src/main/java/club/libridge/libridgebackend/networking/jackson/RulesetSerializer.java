package club.libridge.libridgebackend.networking.jackson;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import club.libridge.libridgebackend.core.rulesets.abstractrulesets.Ruleset;
import club.libridge.libridgebackend.core.rulesets.concrete.PositiveWithTrumpsRuleset;

public class RulesetSerializer extends StdSerializer<Ruleset> {

    public RulesetSerializer() {
        this(null);
    }

    public RulesetSerializer(Class<Ruleset> t) {
        super(t);
    }

    @Override
    public void serialize(Ruleset value, JsonGenerator jgen, SerializerProvider provider) throws IOException, JsonProcessingException {
        String trumpSuit;
        if (value.getClass() == PositiveWithTrumpsRuleset.class) {
            PositiveWithTrumpsRuleset positiveWithTrumpsRuleset = (PositiveWithTrumpsRuleset) value;
            trumpSuit = positiveWithTrumpsRuleset.getTrumpSuit().getName();
        } else {
            trumpSuit = "No Trumps";
        }
        jgen.writeStartObject();
        jgen.writeStringField("rulesetName", value.getClass().getSimpleName());
        jgen.writeStringField("trumpSuit", trumpSuit);

        jgen.writeEndObject();
    }
}
