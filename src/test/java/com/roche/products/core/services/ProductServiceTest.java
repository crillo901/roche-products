package com.roche.products.core.services;

import com.roche.RocheProductsApplicationTestBase;
import com.roche.products.common.request.CreateProductRequest;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolationException;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class ProductServiceTest extends RocheProductsApplicationTestBase {

    @Test
    public void validAnnotationShouldValidateParam(){
        assertThrows(ConstraintViolationException.class, () -> productService.createProduct(new CreateProductRequest()));
    }


    /*
    @Ignore
    public void simpleTestForOptimisticVersioningLocking() {

        final AtomicBoolean depositsDone1 = new AtomicBoolean(false);
        final AtomicBoolean withdrawsDone1 = new AtomicBoolean(false);

        final AtomicBoolean depositsDone2 = new AtomicBoolean(false);
        final AtomicBoolean withdrawsDone2 = new AtomicBoolean(false);

        Order storedOrder = orderService.createUser(new CreateOrderRequest("tester",
                Collections.singletonList(new CreateProductRequest(new BigDecimal(0), null))));

        final Order order = orderService.getById(storedOrder.getId());
        Optional<Product> accountOptional = order.getAccounts().stream().findFirst();

        assertThat(accountOptional.isPresent()).isTrue();

        final Product product = accountOptional.get();

        new Thread(() -> {
            for (int i = 0; i < 100; ++i) {
                accountsService.deposit(new DepositRequest(product.getId(), order.getId(), BigDecimal.TEN));
            }
            depositsDone1.getAndSet(true);
        }).start();

        new Thread(() -> {
            for (int i = 0; i < 100; ++i) {
                accountsService.withdraw(new WithdrawRequest(product.getId(), order.getId(), BigDecimal.TEN));
            }
            withdrawsDone1.getAndSet(true);
        }).start();

        new Thread(() -> {
            for (int i = 0; i < 100; ++i) {
                accountsService.deposit(new DepositRequest(product.getId(), order.getId(), BigDecimal.TEN));
            }
            depositsDone2.getAndSet(true);
        }).start();

        new Thread(() -> {
            for (int i = 0; i < 100; ++i) {
                accountsService.withdraw(new WithdrawRequest(product.getId(), order.getId(), BigDecimal.TEN));
            }
            withdrawsDone2.getAndSet(true);
        }).start();

        while (!depositsDone1.get() && !withdrawsDone1.get() && !depositsDone2.get() && !withdrawsDone2.get()) {
            try {
                sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        Product productAfter = accountsService.getById(product.getId());
        assertThat(productAfter).isNotNull();
        assertThat(productAfter.getBalance()).isEqualByComparingTo(BigDecimal.ZERO);
    }

     */
}
