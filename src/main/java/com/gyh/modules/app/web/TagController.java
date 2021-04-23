package com.gyh.modules.app.web;

import com.gyh.common.persistence.web.BaseController;
import com.gyh.common.utils.R;
import com.gyh.modules.app.entity.Tag;
import com.gyh.modules.app.entity.Video;
import com.gyh.modules.app.service.TagService;
import com.gyh.modules.app.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author gyh
 * @Date 2021/4/15 16:07
 */
@RestController
@RequestMapping("app/tag")
public class TagController extends BaseController {

    @Autowired
    private TagService tagService;

    @GetMapping("list")
    public R getList(Tag tag){
        List<Tag> result = tagService.findList(tag);
        return R.ok(result);
    }

    /**
     *
     * @param tag
     * @return
     */
    @GetMapping("page")
    public R getPage(Tag tag){

        setPage();
        List<Tag> result = tagService.findList(tag);

        return R.page(result);
    }

    @PostMapping("save")
    public R save(@RequestBody Tag tag){

        int row = tagService.save(tag);
        if (row > 0) {
            return R.ok("添加成功");
        } else {
            return R.error("添加失败");
        }
    }

    @DeleteMapping("{id}")
    public R deleteById(@PathVariable String id){
        int row = tagService.deleteById(id);
        if (row > 0) {
            return R.ok("删除成功");
        } else {
            return R.error("删除失败");
        }
    }

}
