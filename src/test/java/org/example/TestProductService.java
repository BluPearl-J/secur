package org.example;
import org.example.dtos.requests.ProductRequest;
import org.example.dtos.responses.ProductResponse;
import org.example.services.ProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TestProductService {

    @Autowired
    private ProductService productService;

    @Test
    void testAddAndGetProduct() {
        ProductRequest request = new ProductRequest();
        request.setName("Test Product");
        request.setPrice(99.99);
        request.setStock(10);

        ProductResponse response = productService.add(request);

        assertNotNull(response.getId());
        assertEquals("Test Product", response.getName());
        assertEquals(99.99, response.getPrice());
        assertEquals(10, response.getStock());

        ProductResponse fetched = productService.getById(response.getId());
        assertEquals("Test Product", fetched.getName());
    }

    @Test
    void testGetAllProducts() {
        List<ProductResponse> products = productService.getAll();
        assertNotNull(products);
        assertTrue(products.size() >= 0);
    }

    @Test
    void testImportFromFakeStore() {
        List<ProductResponse> imported = productService.importFromFakeStore();
        assertNotNull(imported);
        assertTrue(imported.size() > 0);
    }

    @Test
    void testUpdateProduct() {

        ProductRequest request = new ProductRequest();
        request.setName("Old Product");
        request.setPrice(50.0);
        request.setStock(5);

        ProductResponse added = productService.add(request);


        ProductRequest updateRequest = new ProductRequest();
        updateRequest.setName("Updated Product");
        updateRequest.setPrice(75.0);
        updateRequest.setStock(8);

        ProductResponse updated = productService.update(added.getId(), updateRequest);

        assertNotNull(updated);
        assertEquals("Updated Product", updated.getName());
        assertEquals(75.0, updated.getPrice());
        assertEquals(8, updated.getStock());
    }

    @Test
    void testDeleteProduct() {

        ProductRequest request = new ProductRequest();
        request.setName("Delete Me");
        request.setPrice(20.0);
        request.setStock(2);

        ProductResponse added = productService.add(request);


        ProductResponse deleted = productService.delete(added.getId());

        assertNotNull(deleted);
        assertEquals("Delete Me", deleted.getName());


        ProductResponse fetched = productService.getById(added.getId());
        assertNull(fetched);
    }
}
