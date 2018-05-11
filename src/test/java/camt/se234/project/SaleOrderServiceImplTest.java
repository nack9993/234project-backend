package camt.se234.project;

import camt.se234.project.dao.OrderDao;
import camt.se234.project.entity.Product;
import camt.se234.project.entity.SaleOrder;
import camt.se234.project.entity.SaleTransaction;
import camt.se234.project.service.SaleOrderServiceImpl;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsCollectionContaining.hasItems;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.hamcrest.core.Is.is;

public class SaleOrderServiceImplTest {
    OrderDao orderDao;
    SaleOrderServiceImpl saleOrderServiceImpl;

    @Before
    public void setup() {
        orderDao = mock(OrderDao.class);
        saleOrderServiceImpl = new SaleOrderServiceImpl();
        saleOrderServiceImpl.setOrderDao(orderDao);
    }

    @Test
    public void testGetSaleOrderWithMock(){
        List<SaleTransaction> transactions = new ArrayList<>();
        transactions.add(new SaleTransaction("1234",new SaleOrder("555",transactions),
                new Product("123","Apple","It's a apple","hi",50),10));
        List<SaleOrder> saleOrders = new ArrayList<>();
        saleOrders.add(new SaleOrder("555",transactions));
        when(orderDao.getOrders()).thenReturn(saleOrders);
        assertThat(saleOrderServiceImpl.getSaleOrders(),hasItems(new SaleOrder("555",transactions)));
    }

    @Test
    public void testAverageSaleOrderWithMock(){
        List<SaleTransaction> transactions = new ArrayList<>();
        List<SaleTransaction> transactions2 = new ArrayList<>();
        transactions.add(new SaleTransaction("1234",new SaleOrder("555",transactions),
                new Product("123","Apple","It's a apple","hi",5),10));
        transactions2.add(new SaleTransaction("5678",new SaleOrder("666",transactions),
                new Product("666","Coconut","It's a coconut","hi",5),10));
        List<SaleOrder> saleOrders = new ArrayList<>();
        saleOrders.add(new SaleOrder("555",transactions));
        saleOrders.add(new SaleOrder("666",transactions2));
        when(orderDao.getOrders()).thenReturn(saleOrders);
        assertThat(saleOrderServiceImpl.getAverageSaleOrderPrice(),is(50.0));
    }
}
