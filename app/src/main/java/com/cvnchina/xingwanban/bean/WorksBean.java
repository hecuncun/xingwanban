package com.cvnchina.xingwanban.bean;

import java.util.List;

/**
 * Created by hecuncun on 2020-5-14
 */
public class WorksBean {

    /**
     * dataList : [{"contId":"视频id","contSubTitle":"视频标题","contTags":[{"tagId":"标签ID","tagName":"标签"}],"ischeck":"审核状态n：不通过；y：通过","commentnums":"评论数","haszannums":"点赞数","overimageurl":"封面图URL","showType":"1竖屏2横屏","contDownUrl":"视频链接","createtime":"上传时间","hotComment":[{"userHeadPic":"用户头像url","userNickName":"用户昵称","content":"评论内容"}]}]
     * total : 100
     * pageNum : 1
     * pageSize : 10
     */

    private int total;
    private int pageNum;
    private int pageSize;
    private List<DataListBean> dataList;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public List<DataListBean> getDataList() {
        return dataList;
    }

    public void setDataList(List<DataListBean> dataList) {
        this.dataList = dataList;
    }

    public static class DataListBean {
        /**
         * contId : 视频id
         * contSubTitle : 视频标题
         * contTags : [{"tagId":"标签ID","tagName":"标签"}]
         * ischeck : 审核状态n：不通过；y：通过
         * commentnums : 评论数
         * haszannums : 点赞数
         * overimageurl : 封面图URL
         * showType : 1竖屏2横屏
         * contDownUrl : 视频链接
         * createtime : 上传时间
         * hotComment : [{"userHeadPic":"用户头像url","userNickName":"用户昵称","content":"评论内容"}]
         */

        private String contId;
        private String contSubTitle;
        private String ischeck;
        private String commentnums;
        private String haszannums;
        private String overimageurl;
        private String showType;
        private String contDownUrl;
        private String createtime;
        private List<ContTagsBean> contTags;
        private List<HotCommentBean> hotComment;

        public String getContId() {
            return contId;
        }

        public void setContId(String contId) {
            this.contId = contId;
        }

        public String getContSubTitle() {
            return contSubTitle;
        }

        public void setContSubTitle(String contSubTitle) {
            this.contSubTitle = contSubTitle;
        }

        public String getIscheck() {
            return ischeck;
        }

        public void setIscheck(String ischeck) {
            this.ischeck = ischeck;
        }

        public String getCommentnums() {
            return commentnums;
        }

        public void setCommentnums(String commentnums) {
            this.commentnums = commentnums;
        }

        public String getHaszannums() {
            return haszannums;
        }

        public void setHaszannums(String haszannums) {
            this.haszannums = haszannums;
        }

        public String getOverimageurl() {
            return overimageurl;
        }

        public void setOverimageurl(String overimageurl) {
            this.overimageurl = overimageurl;
        }

        public String getShowType() {
            return showType;
        }

        public void setShowType(String showType) {
            this.showType = showType;
        }

        public String getContDownUrl() {
            return contDownUrl;
        }

        public void setContDownUrl(String contDownUrl) {
            this.contDownUrl = contDownUrl;
        }

        public String getCreatetime() {
            return createtime;
        }

        public void setCreatetime(String createtime) {
            this.createtime = createtime;
        }

        public List<ContTagsBean> getContTags() {
            return contTags;
        }

        public void setContTags(List<ContTagsBean> contTags) {
            this.contTags = contTags;
        }

        public List<HotCommentBean> getHotComment() {
            return hotComment;
        }

        public void setHotComment(List<HotCommentBean> hotComment) {
            this.hotComment = hotComment;
        }

        public static class ContTagsBean {
            /**
             * tagId : 标签ID
             * tagName : 标签
             */

            private String tagId;
            private String tagName;

            public String getTagId() {
                return tagId;
            }

            public void setTagId(String tagId) {
                this.tagId = tagId;
            }

            public String getTagName() {
                return tagName;
            }

            public void setTagName(String tagName) {
                this.tagName = tagName;
            }
        }

        public static class HotCommentBean {
            /**
             * userHeadPic : 用户头像url
             * userNickName : 用户昵称
             * content : 评论内容
             */

            private String userHeadPic;
            private String userNickName;
            private String content;

            public String getUserHeadPic() {
                return userHeadPic;
            }

            public void setUserHeadPic(String userHeadPic) {
                this.userHeadPic = userHeadPic;
            }

            public String getUserNickName() {
                return userNickName;
            }

            public void setUserNickName(String userNickName) {
                this.userNickName = userNickName;
            }

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }
        }
    }
}
