package club.libridge.libridgebackend.gui.main;

import static club.libridge.libridgebackend.logging.SBKingLogger.LOGGER;

import club.libridge.libridgebackend.gui.constants.FrameConstants;

public final class ClientApplicationState {

    private static boolean guiHasChanged = false;
    private static boolean guiScaleValid = true;

    private ClientApplicationState() {
        throw new IllegalStateException("Utility class");
    }

    public static void setGUIHasChanged(boolean changed) {
        guiHasChanged = changed;
    }

    public static boolean getGUIHasChanged() {
        return guiHasChanged;
    }

    public static void invalidateGUIScale() {
        guiScaleValid = false;
    }

    public static void checkWindowResize(int newWidth, int newHeight) {
        if (!guiScaleValid) {
            resizeWindow(newWidth, newHeight);
        }

        guiScaleValid = true;
    }

    public static void startAppState() {
        LOGGER.debug("Initializing Frame Constants");

        FrameConstants.initFrameConstants();

        // AssetLoader must be initialized after FrameConstants.
        AssetLoader.initAssetLoader();
    }

    public static void resizeWindow(int width, int height) {
        LOGGER.debug("Resizing Window");

        FrameConstants.computeConstants(width, height);

        AssetLoader.invalidateAssetLoaderCache();

        ClientApplicationState.setGUIHasChanged(true);
    }

}
