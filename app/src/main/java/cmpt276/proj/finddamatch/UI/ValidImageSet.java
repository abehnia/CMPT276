package cmpt276.proj.finddamatch.UI;

/**
 * Enum that contains the valid image set
 */
public enum ValidImageSet implements ImageSetOption {
    NBA(0),
    College(1),
    Custom(2);

    private int ID;

    private ValidImageSet(int ID) {
        this.ID = ID;
    }

    @Override
    public int getID() {
        return ID;
    }

    @Override
    public boolean isEquivalent(ImageSetOption imageSetOption) {
        return imageSetOption.getID() == ID;
    }
}
