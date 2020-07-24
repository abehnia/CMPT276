//package cmpt276.proj.finddamatch.model.gameLogic;
//
//import androidx.annotation.NonNull;
//
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.Iterator;
//import java.util.Stack;
//
//import cmpt276.proj.finddamatch.model.Card;
//import cmpt276.proj.finddamatch.model.Image;
//import cmpt276.proj.finddamatch.model.MutableImage;
//import cmpt276.proj.finddamatch.model.GameMode;
//
//import static cmpt276.proj.finddamatch.model.gameLogic.VALID_GAME_MODE.GAME1;
//
///**
// * Pre Generated cards that satisfy the constraints for
// * the game Spot-It
// */
//public enum PRE_GENERATED_CARDS implements Card {
//
//    public int ORDER = GAME1.getOrder();
//    public int NUM_OF_IMAGES_PER_CARD = ORDER - 1;
//    public int ALL = ORDER * ORDER + ORDER + 1;
//
//    Stack<Card> arrayOfCards = new Stack<>();
//    ArrayList<Image> images = new ArrayList<>();
//
//
//    private PRE_GENERATED_CARDS() {
//        // Generate the first card, then returns the stack with first card in it.
//        generateFirstCard();
//
//        // Generate the all cards except the first and last card(ALL - 2), then returns stack with cards added.
//        generateCardsMinusFirstLast();
//
//        // Generate the last card, returns stack with last card added to it.
//        generateLastCard();
//
////        this.images.addAll(Arrays.asList(images));
//    }
//
//    @Override
//    public Image get(int index) {
//        return images.get(index);
//    }
//
//    @Override
//    public boolean exists(Image image) {
//        for (Image savedImage : images) {
//            if (savedImage.isEquivalent(image)) {
//                return true;
//            }
//        }
//        return false;
//    }
//
//    @Override
//    public int size() {
//        return images.size();
//    }
//
//    @NonNull
//    @Override
//    public Iterator<Image> iterator() {
//        return images.iterator();
//    }
//
//    private void generateFirstCard(){
//        ArrayList images = new ArrayList<Card>();
//        for (int i= 0; i < ORDER; i++){
//            Card card = new CardImpl();
//            for (int j = 0; j < ORDER; j++){
//                Image image = new ImageImpl(i * ORDER + j);
//                images.add(image);
//            }
//            Image image = new ImageImpl(ORDER * ORDER);
//            images.add(image);
//            arrayOfCards.push(new CardImpl(images));
//        }
//    }
//
//    private void generateCardsMinusFirstLast(){
//        ArrayList images = new ArrayList<Card>();
//        for (int i = 0; i < ORDER; i++) {
//            for (int j = 0; j < ORDER; j++) {
//                Card card = new CardImpl();
//                for (int k = 0; k < ORDER; k++) {
//                    Image image = new ImageImpl(k * ORDER + ((j + i * k) % ORDER));
//                    images.add(image);
//                }
//                Image image = new ImageImpl(ORDER * ORDER + i + 1);
//                images.add(image);
//                arrayOfCards.push(new CardImpl(images));
//            }
//        }
//    }
//
//    private void generateLastCard(){
//        ArrayList images = new ArrayList<Card>();
//        for (int i = 0; i < ORDER + 1; i++){
//            Image image = new ImageImpl(ORDER * ORDER + i);
//            images.add(image);
//        }
//        arrayOfCards.push(new CardImpl(images));
//    }
//
////    private Stack<Card> enumerateCards(){
////        //Enumerate cards one by one out of the stack
////        Stack<Card> arrayOfCardsReverse = new Stack<>();
////
////        while(!arrayOfCards.isEmpty()){
////            arrayOfCardsReverse.push(arrayOfCards.pop());
////
////
////        }
////
////    }
//
//}
