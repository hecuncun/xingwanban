package com.cvnchina.xingwanban.bean;

import java.util.List;

/**
 * Created by hecuncun on 2020-5-17
 */
public class EvaluateListBean {

    /**
     * items : [{"id":1,"userHeadPic":"http://10.1.17.75:8018/vms//headImage/15892877193573568.jpg","userNickName":"测试账号","content":"测试评论","createDate":"2020-04-17 14:35:22","childComment":[{"id":41189,"userHeadPic":null,"userNickName":null,"content":"好的","createDate":"2020-04-17 14:35:22","childComment":null},{"id":41188,"userHeadPic":null,"userNickName":null,"content":"qweqweqw","createDate":"2020-04-17 14:35:22","childComment":null}]}]
     * total : 1
     */

    private int total;
    private List<ItemsBean> items;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<ItemsBean> getItems() {
        return items;
    }

    public void setItems(List<ItemsBean> items) {
        this.items = items;
    }

    public static class ItemsBean {
        /**
         * id : 1
         * userHeadPic : http://10.1.17.75:8018/vms//headImage/15892877193573568.jpg
         * userNickName : 测试账号
         * content : 测试评论
         * createDate : 2020-04-17 14:35:22
         * childComment : [{"id":41189,"userHeadPic":null,"userNickName":null,"content":"好的","createDate":"2020-04-17 14:35:22","childComment":null},{"id":41188,"userHeadPic":null,"userNickName":null,"content":"qweqweqw","createDate":"2020-04-17 14:35:22","childComment":null}]
         */

        private int id;
        private String userHeadPic;
        private String userNickName;
        private String content;
        private String createDate;
        private List<ChildCommentBean> childComment;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

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

        public String getCreateDate() {
            return createDate;
        }

        public void setCreateDate(String createDate) {
            this.createDate = createDate;
        }

        public List<ChildCommentBean> getChildComment() {
            return childComment;
        }

        public void setChildComment(List<ChildCommentBean> childComment) {
            this.childComment = childComment;
        }

        public static class ChildCommentBean {
            /**
             * id : 41189
             * userHeadPic : null
             * userNickName : null
             * content : 好的
             * createDate : 2020-04-17 14:35:22
             * childComment : null
             */

            private int id;
            private Object userHeadPic;
            private Object userNickName;
            private String content;
            private String createDate;
            private Object childComment;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public Object getUserHeadPic() {
                return userHeadPic;
            }

            public void setUserHeadPic(Object userHeadPic) {
                this.userHeadPic = userHeadPic;
            }

            public Object getUserNickName() {
                return userNickName;
            }

            public void setUserNickName(Object userNickName) {
                this.userNickName = userNickName;
            }

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public String getCreateDate() {
                return createDate;
            }

            public void setCreateDate(String createDate) {
                this.createDate = createDate;
            }

            public Object getChildComment() {
                return childComment;
            }

            public void setChildComment(Object childComment) {
                this.childComment = childComment;
            }
        }
    }
}
