package me.yipzale.water.article.controller;

import me.yipzale.water.article.entity.Archive;
import me.yipzale.water.article.mapper.ArchiveMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/archives")
public class ArchiveController {
    @Autowired
    private ArchiveMapper archiveMapper;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public ApiResponse archives(
            @RequestParam(name = "sortby", defaultValue = "created_at") String sortby,
            @RequestParam(name = "order", defaultValue = "asc") String order,
            @RequestParam(name = "_open", defaultValue = "0") Integer open,
            @RequestParam(name = "_with", defaultValue = "") String with
    ) {
        List<Archive> archiveList;
        if (open == 1) {
            archiveList = archiveMapper.selectAllWithOpenData(sortby, order);
        } else {
            archiveList = archiveMapper.selectAll(sortby, order);
        }
        if (!with.isEmpty()) {
            String[] withs = with.split(",");
            List<String> withList = Arrays.asList(withs);
            if (withList.contains("articles")) {
                for (Archive archive:archiveList) {
                    if (archive.getArchiveNodes() != null) {
                        for (Archive childArchive:archive.getArchiveNodes()) {
                            childArchive.getArticles();
                        }
                    }
                }
            }
        }
        return new ApiResponse(0, "success", archiveList);
    }
}
