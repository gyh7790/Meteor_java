package com.gyh.modules.app.web;

import com.gyh.common.persistence.web.BaseController;
import com.gyh.common.utils.R;
import com.gyh.modules.app.entity.Banner;
import com.gyh.modules.app.entity.Video;
import com.gyh.modules.app.service.BannerService;
import com.gyh.modules.app.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author gyh
 * @Date 2021/4/15 16:08
 */
@RestController
@RequestMapping("app/video")
public class VideoController extends BaseController {

    @Autowired
    private VideoService videoService;

    @GetMapping("list")
    public R getList(Video video){
        List<Video> result = videoService.findList(video);
        return R.ok(result);
    }

    /**
     *
     * @param video
     * @return
     */
    @GetMapping("page")
    public R getPage(Video video){

        setPage();
        List<Video> result = videoService.findList(video);

        return R.page(result);
    }

    @PostMapping("save")
    public R save(@RequestBody Video video){

        int row = videoService.save(video);
        if (row > 0) {
            return R.ok("添加成功");
        } else {
            return R.error("添加失败");
        }
    }

    @DeleteMapping("{id}")
    public R deleteById(@PathVariable String id){
        int row = videoService.deleteById(id);
        if (row > 0) {
            return R.ok("删除成功");
        } else {
            return R.error("删除失败");
        }
    }

}
