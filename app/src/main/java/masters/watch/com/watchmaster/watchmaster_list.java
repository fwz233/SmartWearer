package masters.watch.com.watchmaster;

import android.media.Image;

public class watchmaster_list{
    private String title;
    private Image watchmaster_image;
    private int watchmaster_image_int,height;

    public watchmaster_list(String title, Image watchmaster_image,int watchmaster_image_int,int height)
    {this.height = height;
        this.title = title;
        this.watchmaster_image = watchmaster_image;
        this.watchmaster_image_int=watchmaster_image_int;

    }

    public String gettitle()
    {
        return title;
    }

    public void settitle(String title)
    {
        this.title = title;
    }

    public Image getwatchmaster_image()
    {
        return watchmaster_image;
    }

    public void setwatchmaster_image(Image watchmaster_image)
    {
        this.watchmaster_image = watchmaster_image;
    }
    public int getwatchmaster_image_int()
    {
        return watchmaster_image_int;
    }

    public void setwatchmaster_image_int(int watchmaster_image_int)
    {
        this.watchmaster_image_int = watchmaster_image_int;
    }
    public int getheight()
    {
        return height;
    }

    public void setheight(int height)
    {
        this.height = height;
    }
}