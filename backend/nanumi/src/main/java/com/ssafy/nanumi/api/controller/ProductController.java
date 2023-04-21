package com.ssafy.nanumi.api.controller;

import com.ssafy.nanumi.api.response.ProductAllResponse;
import com.ssafy.nanumi.api.response.ProductDetailResponse;
import com.ssafy.nanumi.api.service.ProductService;
import com.ssafy.nanumi.db.entity.User;
import com.ssafy.nanumi.db.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/products")
public class ProductController {
    public final ProductService productService;
    public final UserRepository userRepository;


    // 전체 조회
    @GetMapping("")
    public ResponseEntity<List<ProductAllResponse>> getProductAll(){
        return new ResponseEntity<>(productService.findProductAll(), HttpStatus.OK);
    }
    // 상세 페이지 조회
    @GetMapping("/{product_id}")
    public ResponseEntity<ProductDetailResponse> getProductOne(@PathVariable("product_id") Long id) {
        return new ResponseEntity<>(productService.findByProductId(id), HttpStatus.OK);
    }

//    @GetMapping("/categories/{categorie_id}")
//    public ResponseEntity<List<ProductAllResponse>> getCateProductAll(@PathVariable("product_id") Long id){
//        return new ResponseEntity<>(productService.findCateProductAll(id), HttpStatus.OK);
//    }


    // 상품 등록
//    @PostMapping("")
//    public ResponseEntity<?> createProduct(@ResponseBody) {
//        return new ResponseEntity<>(productService.
//    }
//
//    @PatchMapping("")
//    public ResponseEntity<> updateProduct() {
//
//    }


}