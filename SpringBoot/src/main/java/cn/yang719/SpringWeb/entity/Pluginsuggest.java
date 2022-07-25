package cn.yang719.SpringWeb.entity;

import java.util.Date;

public class Pluginsuggest {
    private Integer id;

    private Integer uid;

    private String suggest;

    private String pluginurl;

    private Date time;

    private Integer aid;

    private Integer isfinish;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getSuggest() {
        return suggest;
    }

    public void setSuggest(String suggest) {
        this.suggest = suggest;
    }

    public String getPluginurl() {
        return pluginurl;
    }

    public void setPluginurl(String pluginurl) {
        this.pluginurl = pluginurl;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public Integer getAid() {
        return aid;
    }

    public void setAid(Integer aid) {
        this.aid = aid;
    }

    public Integer getIsfinish() {
        return isfinish;
    }

    public void setIsfinish(Integer isfinish) {
        this.isfinish = isfinish;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", uid=").append(uid);
        sb.append(", suggest=").append(suggest);
        sb.append(", pluginurl=").append(pluginurl);
        sb.append(", time=").append(time);
        sb.append(", aid=").append(aid);
        sb.append(", isfinish=").append(isfinish);
        sb.append("]");
        return sb.toString();
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        Pluginsuggest other = (Pluginsuggest) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
                && (this.getUid() == null ? other.getUid() == null : this.getUid().equals(other.getUid()))
                && (this.getSuggest() == null ? other.getSuggest() == null : this.getSuggest().equals(other.getSuggest()))
                && (this.getPluginurl() == null ? other.getPluginurl() == null : this.getPluginurl().equals(other.getPluginurl()))
                && (this.getTime() == null ? other.getTime() == null : this.getTime().equals(other.getTime()))
                && (this.getAid() == null ? other.getAid() == null : this.getAid().equals(other.getAid()))
                && (this.getIsfinish() == null ? other.getIsfinish() == null : this.getIsfinish().equals(other.getIsfinish()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getUid() == null) ? 0 : getUid().hashCode());
        result = prime * result + ((getSuggest() == null) ? 0 : getSuggest().hashCode());
        result = prime * result + ((getPluginurl() == null) ? 0 : getPluginurl().hashCode());
        result = prime * result + ((getTime() == null) ? 0 : getTime().hashCode());
        result = prime * result + ((getAid() == null) ? 0 : getAid().hashCode());
        result = prime * result + ((getIsfinish() == null) ? 0 : getIsfinish().hashCode());
        return result;
    }
}