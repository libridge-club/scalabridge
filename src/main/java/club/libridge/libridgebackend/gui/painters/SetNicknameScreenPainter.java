package club.libridge.libridgebackend.gui.painters;

import java.awt.Component;
import java.awt.Container;

import javax.swing.JTextField;

import club.libridge.libridgebackend.gui.elements.ConnectToServerElement;
import club.libridge.libridgebackend.gui.jelements.SBKingButton;
import club.libridge.libridgebackend.gui.screens.WelcomeScreen;

public class SetNicknameScreenPainter implements Painter {

    private WelcomeScreen welcomeScreen;

    public SetNicknameScreenPainter(WelcomeScreen welcomeScreen) {
        this.welcomeScreen = welcomeScreen;
    }

    @Override
    public void paint(Container contentPane) {
        ConnectToServerElement connectToServerElement = new ConnectToServerElement();
        connectToServerElement.add(contentPane, new ConnectToScreenActionListener());

        contentPane.validate();
        contentPane.repaint();
    }

    class ConnectToScreenActionListener implements java.awt.event.ActionListener {
        public void actionPerformed(java.awt.event.ActionEvent event) {
            SBKingButton button = (SBKingButton) event.getSource();
            Component[] components = button.getParent().getComponents();

            String nickname = "";
            for (Component component : components) {
                if ("nicknameTextField".equals(component.getName())) {
                    JTextField nicknameTextField = (JTextField) component;
                    nickname = nicknameTextField.getText();
                }
            }
            welcomeScreen.setAndSendNickname(nickname);
        }
    }

}
