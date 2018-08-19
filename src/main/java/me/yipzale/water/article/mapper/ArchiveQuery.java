package me.yipzale.water.article.mapper;

import me.yipzale.water.article.mybatis.sql.QueryBuilder;

public class ArchiveQuery extends QueryBuilder {
    public final static String WITH_ARCHIVE_NODES = "archiveNodes";

    private int parentId;
    private String sortby;
    private String order;

    public ArchiveQuery() {
        select("archives.*");
        from("archives");
    }

    public ArchiveQuery with(String table) {
        if (table == "archiveNodes") {
            String[] archiveNodesSelect = new String[]{
                    "archiveNodes.id as archiveNodes_id",
                    "archiveNodes.title as archiveNodes_title",
                    "archiveNodes.alias as archiveNodes_alias",
                    "archiveNodes.path as archiveNodes_path",
                    "archiveNodes.parent_id as archiveNodes_parent_id",
                    "archiveNodes.created_at as archiveNodes_created_at",
            };
            select("archives.*, " + String.join(", ", archiveNodesSelect));
            leftJoin("archives as archiveNodes", "archives.id=archiveNodes.parent_id");
        }
        return this;
    }

    public int getParentId() {
        return parentId;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
    }

    public String getSortby() {
        return sortby;
    }

    public void setSortby(String sortby) {
        this.sortby = sortby;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }
}
