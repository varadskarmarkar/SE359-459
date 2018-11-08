import controllers.components.Card;
import controllers.components.CardsMngr;
import controllers.components.GameHandler;
import controllers.window.GameWindow;
import javafx.geometry.Point2D;
import javafx.scene.paint.Color;
import org.junit.*;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class checkSequenceTest {

    List<Card> mockCards;
    CardsMngr cardsMngr;
    GameHandler gameHandler;
    @Before
    public void setUp() throws Exception {
        mockCards = mock(List.class);
        cardsMngr = mock(CardsMngr.class);
        gameHandler = new GameHandler();

        gameHandler.setCardsMngr(cardsMngr);

        when(cardsMngr.getBoardCards()).thenReturn(mockCards);
    }

    @Test
    public void testVerticalSeq() {

        // the checkSequence function is supposed to check all neighbor cards and see if there is a sequence of color got formed.
        // so let's make mock card that
        Card mockCard0 = mock(Card.class);
        when(mockCard0.getColor()).thenReturn(Color.GREEN);

        Card mockCard1 = mock(Card.class);
        when(mockCard1.getColor()).thenReturn(Color.BLUE);

        when(mockCards.get(0)).thenReturn(mockCard0);
        when(mockCards.get(1)).thenReturn(mockCard0);
        when(mockCards.get(2)).thenReturn(mockCard0);
        when(mockCards.get(3)).thenReturn(mockCard0);
        when(mockCards.get(4)).thenReturn(mockCard0);
        when(mockCards.get(anyInt())).thenReturn(mockCard1);


        Card mockPlacedCard = mock(Card.class);
        when(mockPlacedCard.getID()).thenReturn(0);
        when(mockPlacedCard.getColor()).thenReturn(Color.GREEN);


        assertThat(gameHandler.checkSequence(mockPlacedCard), is(true));

    }


    @Test
    public void testHorizontalSeq() {

        // the checkSequence function is supposed to check all neighbor cards and see if there is a sequence of color got formed.
        Card mockCard0 = mock(Card.class);
        when(mockCard0.getColor()).thenReturn(Color.GREEN);

        Card mockCard1 = mock(Card.class);
        when(mockCard1.getColor()).thenReturn(Color.BLUE);

        when(mockCards.get(0)).thenReturn(mockCard0);
        when(mockCards.get(10)).thenReturn(mockCard0);
        when(mockCards.get(20)).thenReturn(mockCard0);
        when(mockCards.get(30)).thenReturn(mockCard0);
        when(mockCards.get(40)).thenReturn(mockCard0);
        when(mockCards.get(anyInt())).thenReturn(mockCard1);




        Card mockPlacedCard = mock(Card.class);
        when(mockPlacedCard.getID()).thenReturn(0);
        when(mockPlacedCard.getColor()).thenReturn(Color.GREEN);


        assertThat(gameHandler.checkSequence(mockPlacedCard), is(true));

    }


    @Test
    public void testDiagonalSeq() {

        // the checkSequence function is supposed to check all neighbor cards and see if there is a sequence of color got formed.
        // so let's make mock card that
        Card mockCard0 = mock(Card.class);
        when(mockCard0.getColor()).thenReturn(Color.GREEN);

        Card mockCard1 = mock(Card.class);
        when(mockCard1.getColor()).thenReturn(Color.BLUE);

        when(mockCards.get(0)).thenReturn(mockCard0);
        when(mockCards.get(11)).thenReturn(mockCard0);
        when(mockCards.get(22)).thenReturn(mockCard0);
        when(mockCards.get(33)).thenReturn(mockCard0);
        when(mockCards.get(44)).thenReturn(mockCard0);
        when(mockCards.get(anyInt())).thenReturn(mockCard1);


        Card mockPlacedCard = mock(Card.class);
        when(mockPlacedCard.getID()).thenReturn(0);
        when(mockPlacedCard.getColor()).thenReturn(Color.GREEN);


        assertThat(gameHandler.checkSequence(mockPlacedCard), is(true));

    }
}