package cmpt276.proj.finddamatch.UI;

public enum VALID_IMAGE_SET implements ImageSetOption {
    WESTERN(0),
    EASTERN(1),
    FLICKR(2);

    private int ID;

    private VALID_IMAGE_SET(int ID) {
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
