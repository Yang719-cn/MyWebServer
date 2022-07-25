package cn.yang719.SpringWeb.entity;

public class Mcplayer {
    private Integer id;

    private String mcid;

    private String name;

    private String skinurl;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMcid() {
        return mcid;
    }

    public void setMcid(String mcid) {
        this.mcid = mcid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSkinurl() {
        return skinurl;
    }

    public void setSkinurl(String skinurl) {
        this.skinurl = skinurl;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", mcid=").append(mcid);
        sb.append(", name=").append(name);
        sb.append(", skinurl=").append(skinurl);
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
        Mcplayer other = (Mcplayer) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getMcid() == null ? other.getMcid() == null : this.getMcid().equals(other.getMcid()))
            && (this.getName() == null ? other.getName() == null : this.getName().equals(other.getName()))
            && (this.getSkinurl() == null ? other.getSkinurl() == null : this.getSkinurl().equals(other.getSkinurl()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getMcid() == null) ? 0 : getMcid().hashCode());
        result = prime * result + ((getName() == null) ? 0 : getName().hashCode());
        result = prime * result + ((getSkinurl() == null) ? 0 : getSkinurl().hashCode());
        return result;
    }
}