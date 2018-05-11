package camt.se234.project;

import camt.se234.project.dao.ProductDao;
import camt.se234.project.entity.Product;
import camt.se234.project.service.ProductServiceImpl;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsCollectionContaining.hasItems;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ProductServiceImplTest {
    ProductDao productDao;
    ProductServiceImpl productService;

    @Before
    public void setup() {
        productDao = mock(ProductDao.class);
        productService = new ProductServiceImpl();
        productService.setProductDao(productDao);
    }

    @Test
    public void testProductGetAllWithMock(){
       List<Product> mockProduct = new ArrayList<>();
       mockProduct.add(new Product("123","Apple","It's a apple","hi",50));
        mockProduct.add(new Product("456","Water Melon","It's a Water Melon","hi",70));
        mockProduct.add(new Product("789","Cherry","It's a Cherry","hi",100));
        when(productDao.getProducts()).thenReturn(mockProduct);
        assertThat(productService.getAllProducts(),hasItems(new Product("123","Apple","It's a apple","hi",50),
                new Product("456","Water Melon","It's a Water Melon","hi",70),
                new Product("789","Cherry","It's a Cherry","hi",100)));
    }

    @Test
    public void testProductGetAvalibleWithMock(){
        List<Product> mockProduct = new ArrayList<>();
        mockProduct.add(new Product("123","Apple","It's a apple","hi",50));
        mockProduct.add(new Product("456","Water Melon","It's a Water Melon","hi",70));
        mockProduct.add(new Product("789","Cherry","It's a Cherry","hi",0));
        when(productDao.getProducts()).thenReturn(mockProduct);
        assertThat(productService.getAvailableProducts(),hasItems(new Product("123","Apple","It's a apple","hi",50),
                new Product("456","Water Melon","It's a Water Melon","hi",70)));
    }

    @Test
    public void testProductGetUnavalibleWithMock(){
        List<Product> mockProduct = new ArrayList<>();
        mockProduct.add(new Product("123","Apple","It's a apple","hi",50));
        mockProduct.add(new Product("456","Water Melon","It's a Water Melon","hi",0));
        mockProduct.add(new Product("789","Cherry","It's a Cherry","hi",100));
        when(productDao.getProducts()).thenReturn(mockProduct);
        assertThat(productService.getUnavailableProductSize(),is(1));
    }
}
