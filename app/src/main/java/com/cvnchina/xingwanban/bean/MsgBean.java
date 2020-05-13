package com.cvnchina.xingwanban.bean;

import java.util.List;

/**
 * Created by hecuncun on 2020-5-13
 */
public class MsgBean {

    /**
     * dataList : [{"headPic":"用户头像","nickName":"用户昵称","type":"类型","videoId":"相关视频ID","videoPic":"视频封面","content":"内容"}]
     * total : 12
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
         * headPic : 用户头像
         * nickName : 用户昵称
         * type : 类型
         * videoId : 相关视频ID
         * videoPic : 视频封面
         * content : 内容
         */

        private String headPic;
        private String nickName;
        private String type;
        private String videoId;
        private String videoPic;
        private String content;

        public String getHeadPic() {
            return headPic;
        }

        public void setHeadPic(String headPic) {
            this.headPic = headPic;
        }

        public String getNickName() {
            return nickName;
        }

        public void setNickName(String nickName) {
            this.nickName = nickName;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getVideoId() {
            return videoId;
        }

        public void setVideoId(String videoId) {
            this.videoId = videoId;
        }

        public String getVideoPic() {
            return videoPic;
        }

        public void setVideoPic(String videoPic) {
            this.videoPic = videoPic;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }
    }
}
