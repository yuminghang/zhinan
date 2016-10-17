package com.project.zhinan.bean;

import java.util.List;

/**
 * Created by ymh on 2016/10/10.
 */
public class Quesition {
    /**
     * success : true
     * title : 问卷一
     * questions : [{"_id":"57fb2ee17f151d7f7c94fcb0","content":"教师上课准时，教学态度认真负责，热情高。","type":1,"options":[{"_id":"57fb2f0f7f151d7f7c94fcb1","content":"非常同意"},{"_id":"57fb2f0f7f151d7f7c94fcb2","content":"比较同意"},{"_id":"57fb2f0f7f151d7f7c94fcb3","content":"不好说"},{"_id":"57fb2f0f7f151d7f7c94fcb5","content":"非常不同意"},{"_id":"57fb2f0f7f151d7f7c94fcb4","content":"比较不同意"}]},{"_id":"57fb2f9c7f151d7f7c94fcbb","content":"你觉得本课程可以在哪些方面改进？","type":3,"options":[]},{"_id":"57fb2fe07f151d7f7c94fcbd","content":"这个系统哪里还可以改进的？","type":3,"options":[]}]
     */
    private boolean success;
    private String title;
    /**
     * _id : 57fb2ee17f151d7f7c94fcb0
     * content : 教师上课准时，教学态度认真负责，热情高。
     * type : 1
     * options : [{"_id":"57fb2f0f7f151d7f7c94fcb1","content":"非常同意"},{"_id":"57fb2f0f7f151d7f7c94fcb2","content":"比较同意"},{"_id":"57fb2f0f7f151d7f7c94fcb3","content":"不好说"},{"_id":"57fb2f0f7f151d7f7c94fcb5","content":"非常不同意"},{"_id":"57fb2f0f7f151d7f7c94fcb4","content":"比较不同意"}]
     */

    private List<QuestionsEntity> questions;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<QuestionsEntity> getQuestions() {
        return questions;
    }

    public void setQuestions(List<QuestionsEntity> questions) {
        this.questions = questions;
    }

    public static class QuestionsEntity {
        private String _id;
        private String content;
        private String type;
        private int que_state;
        private List<OptionsEntity> options;

        public int getQue_state() {
            return que_state;
        }

        public void setQue_state(int que_state) {
            this.que_state = que_state;
        }

        public String get_id() {
            return _id;
        }

        public void set_id(String _id) {
            this._id = _id;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public List<OptionsEntity> getOptions() {
            return options;
        }

        public void setOptions(List<OptionsEntity> options) {
            this.options = options;
        }

        public static class OptionsEntity {
            private String _id;
            private String content;
            private int ans_state;

            public int getAns_state() {
                return ans_state;
            }

            public void setAns_state(int ans_state) {
                this.ans_state = ans_state;
            }

            public String get_id() {
                return _id;
            }

            public void set_id(String _id) {
                this._id = _id;
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
