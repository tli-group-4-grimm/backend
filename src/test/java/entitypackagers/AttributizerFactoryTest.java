package entitypackagers;

import entities.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

public class AttributizerFactoryTest {

    @Test
    public void testGetCarAttributizer() {
        Entity car = new Car(5, "Test", "Car", 2021);
        assertTrue(AttributizerFactory.getAttributizer(car) instanceof AttributizeCarUseCase);
    }

    @Test
    public void testGetBuyerAttributizer() {
        Entity buyer = new CarBuyer(50000, 750);
        assertTrue(AttributizerFactory.getAttributizer(buyer) instanceof AttributizeCarBuyerUseCase);
    }

    @Test
    public void testGetAddOnAttributizer() {
        Entity addOn = new AddOn("Marshmallows");
        assertTrue(AttributizerFactory.getAttributizer(addOn) instanceof AttributizeAddOnUseCase);
    }

    @Test
    public void testGetLoanDataAttributizer() {
        Entity loanData = new LoanData();
        assertTrue(AttributizerFactory.getAttributizer(loanData) instanceof AttributizeLoanDataUseCase);
    }

    @Test
    public void testGetFakeEntityAttributizer() {
        Entity faker = new FakeEntity();
        try {
            AttributizerFactory.getAttributizer(faker);
        } catch (ClassCastException e) {
            return;
        }
        fail();
    }
}
