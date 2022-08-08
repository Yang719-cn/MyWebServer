package cn.yang719.SpringWeb.entity;

import java.util.Date;

public class ImagePlus extends Picture{
    private String Base64;

    private String artist;

    private Date time;

    public ImagePlus(Picture f){
        super();
        this.setId(f.getId());
        this.setFilesize(f.getFilesize());
        this.setPicheight(f.getPicheight());
        this.setPicwidth(f.getPicwidth());
        this.setPicurl(f.getPicurl());
    }

    public ImagePlus(Picture f,String base64,String artist){
        super();
        setId(f.getId());
        setFilesize(f.getFilesize());
        setPicheight(f.getPicheight());
        setPicwidth(f.getPicwidth());
        setPicurl(f.getPicurl());
        this.artist = artist;
        this.Base64 = base64;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getBase64() {
        return Base64;
    }

    public void setBase64(String base64) {
        Base64 = base64;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }
}
