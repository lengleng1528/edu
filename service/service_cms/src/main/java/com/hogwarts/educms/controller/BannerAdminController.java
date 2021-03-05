package com.hogwarts.educms.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hogwarts.commonutils.Res;
import com.hogwarts.educms.entity.CrmBanner;
import com.hogwarts.educms.service.CrmBannerService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/educms/banneradmin")
@CrossOrigin
public class BannerAdminController {

    @Autowired
    private CrmBannerService bannerService;

    //1 分页查询banner
    @GetMapping("pageBanner/{page}/{limit}")
    public Res pageBanner(@PathVariable long page, @PathVariable long limit) {
        Page<CrmBanner> pageBanner = new Page<>(page,limit);
        bannerService.page(pageBanner,null);
        return Res.ok().data("items",pageBanner.getRecords()).data("total",pageBanner.getTotal());
    }

    //2 添加banner
    @PostMapping("addBanner")
    public Res addBanner(@RequestBody CrmBanner crmBanner) {
        bannerService.save(crmBanner);
        return Res.ok();
    }

    @ApiOperation(value = "获取Banner")
    @GetMapping("get/{id}")
    public Res get(@PathVariable String id) {
        CrmBanner banner = bannerService.getById(id);
        return Res.ok().data("item", banner);
    }

    @ApiOperation(value = "修改Banner")
    @PutMapping("update")
    public Res updateById(@RequestBody CrmBanner banner) {
        bannerService.updateById(banner);
        return Res.ok();
    }

    @ApiOperation(value = "删除Banner")
    @DeleteMapping("remove/{id}")
    public Res remove(@PathVariable String id) {
        bannerService.removeById(id);
        return Res.ok();
    }
}

