package com.dhruv.controller;


import com.dhruv.service.SellerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/sellers") // all the sellers with with this mapping
public class SellerController {
    private final SellerService sellerService;



}
