package com.example.mobdeve.s17.tamayo.francisemmanuel.wanderers;

public class MyJournalData {
    private long _id;
    private String title;
    private String content;
    private long date_made;
    private long date_modified;
    private String location;
    //private Integer entryImage;

    public MyJournalData(long _id, String title, String content, long date_made, long date_modified, String location) {
        this._id = _id;
        this.title = title;
        this.content = content;
        this.date_made = date_made;
        this.date_modified = date_modified;
        this.location = location;
    }

    public long getId () { return _id; }
    public String getTitle () { return title; }
    public String getContent () { return content; }
    public long getDateMade () { return date_made; }
    public long getDateModified () { return date_modified; }
    public String getLocation () { return location; }

    public  void setTitle (String title) { this.title = title; }
    public void setContent (String content) { this.title = content; }
    public void setDateModified (long dateModified) { this.date_modified = dateModified; }
    public void setLocation (String location) { this.location = location; }
}
