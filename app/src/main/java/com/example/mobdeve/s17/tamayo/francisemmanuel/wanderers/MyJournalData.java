package com.example.mobdeve.s17.tamayo.francisemmanuel.wanderers;

public class MyJournalData {
    private String entryName;
    private String entryDescription;
    //private Integer entryImage;

    public MyJournalData(String entryName, String entryDescription) {
        this.entryName = entryName;
        this.entryDescription = entryDescription;
    }

    public String getEntryName() {
        return entryName;
    }

    public String getEntryDescription() {
        return entryDescription;
    }

    public void setEntryName(String entryName) {
        this.entryName = entryName;
    }

    public void setEntryDescription(String entryDescription) {
        this.entryDescription = entryDescription;
    }
}
