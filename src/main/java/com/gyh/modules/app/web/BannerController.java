package com.gyh.modules.app.web;

import com.gyh.common.persistence.web.BaseController;
import com.gyh.common.utils.R;
import com.gyh.modules.app.entity.Banner;
import com.gyh.modules.app.service.BannerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author gyh
 * @Date 2021/4/15 16:06
 */
@RestController
@RequestMapping("app/banner")
public class BannerController extends BaseController {

    @Autowired
    private BannerService bannerService;

    @GetMapping("list")
    public R getList(Banner banner){
        List<Banner> result = bannerService.findList(banner);
        return R.ok(result);
    }

    /**
     * 轮播图片
     * @param banner
     * @return
     */
    @GetMapping("page")
    public R getPage(Banner banner){

        setPage();
        List<Banner> result = bannerService.findList(banner);

        return R.page(result);
    }

    @PostMapping("save")
    public R save(@RequestBody Banner banner){

        int row = bannerService.save(banner);
        if (row > 0) {
            return R.ok("添加成功");
        } else {
            return R.error("添加失败");
        }
    }

    @DeleteMapping("{id}")
    public R deleteById(@PathVariable String id){
        int row = bannerService.deleteById(id);
        if (row > 0) {
            return R.ok("删除成功");
        } else {
            return R.error("删除失败");
        }
    }

}
