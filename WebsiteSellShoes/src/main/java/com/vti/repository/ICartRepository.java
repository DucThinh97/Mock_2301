package com.vti.repository;

import com.vti.dto.oderDetail.ProductDTO;
import com.vti.entity.Cart;
import com.vti.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ICartRepository extends JpaRepository<Cart, Cart.ShoppingCartKey>, JpaSpecificationExecutor<Cart> {

    //getProductByUserId
    @Query(value = "SELECT * FROM CART WHERE userId = :idParameter", nativeQuery = true)
    Page<Cart> findAllByUserId(Pageable pageable, @Param("idParameter") int userId);

    //getProductByProductIdAndUserId
    @Query(value = "SELECT * FROM CART WHERE productId = :proIdParameter AND userId = :useIdParameter", nativeQuery = true)
    Cart findProductByProductIdAndUserId(@Param("proIdParameter") int productId, @Param("useIdParameter") int userId);

    //  delete cart
    @Modifying
    @Query(value = "DELETE FROM CART WHERE userId = :idParameter", nativeQuery = true)
    void deleteCartByUserId(@Param("idParameter") int userId);

    // delete product in cart
    @Modifying
    @Query(value = "DELETE FROM CART WHERE productId = :idParameter", nativeQuery = true)
    void deleteProductInCartByProductId(@Param("idParameter") int productId);

    // tính tổng
    @Query(value = "SELECT Sum(p.salePrice * c.quantity) as total FROM PRODUCT p\n" +
            "JOIN CART c\n" +
            "USING (productId)\n" +
            "WHERE c.userId = :useIdParameter", nativeQuery = true)
    int total(@Param("useIdParameter") int userId);

    // lấy soluong
    @Query(value = "SELECT c.quantity  as total\n" +
            "FROM cart c\n" +
            "JOIN product p\n" +
            "USING (productId)\n" +
            "JOIN oderlist od\n" +
            "USING (userId)\n" +
            "WHERE od.oderId = :oderIdParameter", nativeQuery = true)
    List<Integer> getQuantityByOderId(@Param("oderIdParameter") int oderId);

//    // lấy tên và giá
//
//    @Query(value = "SELECT  p.productName, p.salePrice\n" +
//            "FROM PRODUCT p\n" +
//            "JOIN CART c\n" +
//            "USING (productId)\n" +
//            "JOIN oderlist od\n" +
//            "USING (userId)\n" +
//            "WHERE od.oderId = :oderIdParameter", nativeQuery = true)
//    List<ProductDTO> getNameAndPriceByOderId(@Param("oderIdParameter") int oderId);
}
