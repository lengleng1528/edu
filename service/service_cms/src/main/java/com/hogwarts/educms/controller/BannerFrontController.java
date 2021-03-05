package com.hogwarts.educms.controller;


import com.hogwarts.commonutils.Res;
import com.hogwarts.educms.entity.CrmBanner;
import com.hogwarts.educms.service.CrmBannerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/educms/bannerfront")
@CrossOrigin
public class BannerFrontController {

    @Autowired
    private CrmBannerService bannerService;

    //查询所有banner
    @GetMapping("getAllBanner")
    public Res getAllBanner() {
        System.out.println("查询所有banner");
        List<CrmBanner> list = bannerService.selectAllBanner();
        return Res.ok().data("list",list);
    }
}

