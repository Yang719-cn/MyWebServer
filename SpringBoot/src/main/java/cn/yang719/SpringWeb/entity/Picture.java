package cn.yang719.SpringWeb.entity;

public class Picture {
    private Integer id;

    private String picurl;

    private String filesize;

    private Integer picheight;

    private Integer picwidth;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPicurl() {
        return picurl;
    }

    public void setPicurl(String picurl) {
        this.picurl = picurl;
    }

    public String getFilesize() {
        return filesize;
    }

    public void setFilesize(String filesize) {
        this.filesize = filesize;
    }

    public Integer getPicheight() {
        return picheight;
    }

    public void setPicheight(Integer picheight) {
        this.picheight = picheight;
    }

    public Integer getPicwidth() {
        return picwidth;
    }

    public void setPicwidth(Integer picwidth) {
        this.picwidth = picwidth;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", picurl=").append(picurl);
        sb.append(", filesize=").append(filesize);
        sb.append(", picheight=").append(picheight);
        sb.append(", picwidth=").append(picwidth);
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
        Picture other = (Picture) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getPicurl() == null ? other.getPicurl() == null : this.getPicurl().equals(other.getPicurl()))
            && (this.getFilesize() == null ? other.getFilesize() == null : this.getFilesize().equals(other.getFilesize()))
            && (this.getPicheight() == null ? other.getPicheight() == null : this.getPicheight().equals(other.getPicheight()))
            && (this.getPicwidth() == null ? other.getPicwidth() == null : this.getPicwidth().equals(other.getPicwidth()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getPicurl() == null) ? 0 : getPicurl().hashCode());
        result = prime * result + ((getFilesize() == null) ? 0 : getFilesize().hashCode());
        result = prime * result + ((getPicheight() == null) ? 0 : getPicheight().hashCode());
        result = prime * result + ((getPicwidth() == null) ? 0 : getPicwidth().hashCode());
        return result;
    }
}