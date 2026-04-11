package com.eazybytes.eazystore.repository;

import com.eazybytes.eazystore.entity.Customer;
import com.eazybytes.eazystore.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    /**
     * Fetch orders for a customer, sorted by creation date in descending order.
     * @param customer
     * @return
     */
    List<Order> findByCustomerOrderByCreatedAtDesc(Customer customer);

    List<Order> findByOrderStatus(String orderStatus);

    @Query("SELECT o FROM Order o WHERE o.customer=:customer ORDER BY o.createdAt DESC")
    List<Order> findOrdersByCustomer(@Param("customer") Customer customer);

    @Query(value = "SELECT * FROM orders o WHERE o.customer_id=:customerId ORDER BY o.created_at DESC",
    nativeQuery = true)
    List<Order> findOrdersByCustomerWithNativeQuery(@Param("customerId") Long customerId);

    //Whatever is the first input parameter to my method, please consider the same as
    // a value against to these orderStatus, or param String orderStatus is the orderStatus
    @Query("SELECT o FROM Order o WHERE o.orderStatus=?1")
    List<Order> findOrdersByStatus(String orderStatus);

    @Query(value = "SELECT * FROM orders o WHERE o.order_status=?1", nativeQuery = true)
    List<Order> findOrdersByStatusWithNativeQuery(String orderStatus);

    // combined findById and then update orderStatus two commands into one command
    // need to update updatedAt and updatedBy, not loaded any Entity but executed the update query
    // the auditing functionality supported by the framework is not working
    @Transactional // start a new session as this query will modify the database
    @Modifying // The @Query is to modify the data, not just query
    @Query("UPDATE Order o SET o.orderStatus=:orderStatus, o.updatedAt=CURRENT_TIMESTAMP, o.updatedBy=:updatedBy WHERE o.orderId=:orderId")
    int updateOrderStatus(@Param("orderId") Long orderId, @Param("orderStatus") String orderStatus,
                          @Param("updatedBy") String updatedBy);
}
