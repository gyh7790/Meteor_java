package com.gyh.modules.app.web;

import com.gyh.common.persistence.web.BaseController;
import com.gyh.common.utils.R;
import com.gyh.modules.app.entity.Banner;
import com.gyh.modules.app.entity.Video;
import com.gyh.modules.app.entity.VideoItem;
import com.gyh.modules.app.service.VideoItemService;
import com.gyh.modules.app.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author gyh
 * @Date 2021/4/15 16:09
 */
@RestController
@RequestMapping("app/videoItem")
public class VideoItemController extends BaseController {

    @Autowired
    private VideoItemService videoItemService;

    @GetMapping("list")
    public R getList(VideoItem videoItem){
        List<VideoItem> result = videoItemService.findList(videoItem);
        return R.ok(result);
    }

    /**
     *
     * @param videoItem
     * @return
     */
    @GetMapping("page")
    public R getPage(VideoItem videoItem){

        setPage();
        List<VideoItem> result = videoItemService.findList(videoItem);

        return R.page(result);
    }

    @PostMapping("save")
    public R save(@RequestBody VideoItem videoItem){

        int row = videoItemService.save(videoItem);
        if (row > 0) {
            return R.ok("添加成功");
        } else {
            return R.error("添加失败");
        }
    }

    @DeleteMapping("{id}")
    public R deleteById(@PathVariable String id){
        int row = videoItemService.deleteById(id);
        if (row > 0) {
            return R.ok("删除成功");
        } else {
            return R.error("删除失败");
        }
    }
}
