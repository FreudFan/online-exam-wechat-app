package edu.sandau.entity;

import java.util.Date;
import java.util.List;

public class WorryTopic {
    private Integer id;
    private String description;
    private String correctkey;
    private String worryanswer;
    private Integer difficult;
    private String difficultName;
    private Double topicmark;
    private String analysis;
    private Integer subject_id;
    private String createtime;

    /***
     * 选项
     */
    private List<Option> optionList;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCorrectkey() {
        return correctkey;
    }

    public void setCorrectkey(String correctkey) {
        this.correctkey = correctkey;
    }

    public String getWorryanswer() {
        return worryanswer;
    }

    public void setWorryanswer(String worryanswer) {
        this.worryanswer = worryanswer;
    }

    public Integer getDifficult() {
        return difficult;
    }

    public void setDifficult(Integer difficult) {
        this.difficult = difficult;
    }

    public String getDifficultName() {
        return difficultName;
    }

    public void setDifficultName(String difficultName) {
        this.difficultName = difficultName;
    }

    public Double getTopicmark() {
        return topicmark;
    }

    public void setTopicmark(Double topicmark) {
        this.topicmark = topicmark;
    }

    public String getAnalysis() {
        return analysis;
    }

    public void setAnalysis(String analysis) {
        this.analysis = analysis;
    }

    public Integer getSubject_id() {
        return subject_id;
    }

    public void setSubject_id(Integer subject_id) {
        this.subject_id = subject_id;
    }

    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }

    public List<Option> getOptionList() {
        return optionList;
    }

    public void setOptionList(List<Option> optionList) {
        this.optionList = optionList;
    }
}
