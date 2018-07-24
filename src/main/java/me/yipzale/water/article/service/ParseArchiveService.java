package me.yipzale.water.article.service;

import me.yipzale.water.article.entity.Archive;
import me.yipzale.water.article.mapper.ArchiveMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class ParseArchiveService {
    @Autowired
    ArchiveMapper archiveMapper;

    /**
     * 日期转归档列表
     * @param date
     * @return
     */
    public List<Archive> date2Archives(Date date) {
        if (date == null) {
            date = new Date();
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1;

        List<Archive> archiveList = new ArrayList<Archive>();
        // 年份
        String yearStr = String.valueOf(year);
        String yearPath = Archive.SEPARATOR + yearStr;
        Archive yearArchive = archiveMapper.selectByPath(yearPath);
        if (yearArchive == null) {
            yearArchive = buildArchive(yearStr + "年", yearStr, yearPath, 0);
        }
        archiveList.add(yearArchive);

        // 月
        String monthStr = String.valueOf(month);
        if (monthStr.length() == 1) {
            monthStr = "0" + monthStr;
        }
        String monthPath = yearPath + Archive.SEPARATOR + monthStr;
        Archive monthArchive = archiveMapper.selectByPath(monthPath);
        if (monthArchive == null) {
            monthArchive = buildArchive(yearStr + "年" + monthStr + "月", monthStr, monthPath, yearArchive.getId());
        }
        archiveList.add(monthArchive);

        return archiveList;
    }

    /**
     * 构建归档对象
     * @param title
     * @param alias
     * @param path
     * @param parentId
     * @return
     */
    private Archive buildArchive(String title, String alias, String path, int parentId) {
        Archive archive = new Archive();
        archive.setTitle(title);
        archive.setAlias(alias);
        archive.setPath(path);
        archive.setParentId(parentId);
        archive.setCreatedAt(new Date());
        return archive;
    }
}
