package br.com.sbk.sbking.gui.elements;

import static javax.swing.SwingConstants.LEFT;

import java.awt.Container;
import java.awt.Font;
import java.awt.Point;

import javax.swing.JLabel;

import br.com.sbk.sbking.core.rulesets.abstractrulesets.Ruleset;
import br.com.sbk.sbking.gui.constants.FontConstants;
import br.com.sbk.sbking.gui.constants.FrameConstants;
import br.com.sbk.sbking.gui.jelements.SBKingLabel;

public class StrainElement {

    private static final int DEFAULT_FONT_SIZE = 40;

    public StrainElement(Ruleset ruleset, Container container, Point point) {
        JLabel strainLabel = createLabel(ruleset);
        strainLabel.setLocation(point);
        container.add(strainLabel);
    }

    private JLabel createLabel(Ruleset ruleset) {
        SBKingLabel label;
        double scaleFactor = FrameConstants.getScreenScale();
        int fontSize = (int) (scaleFactor * DEFAULT_FONT_SIZE);
        label = new SBKingLabel("Choosing Strain.");
        label.setFont(new Font(FontConstants.FONT_NAME, Font.PLAIN, fontSize));
        label.setHorizontalAlignment(LEFT);
        label.setSize(300, 100);

        return label;
    }
}
