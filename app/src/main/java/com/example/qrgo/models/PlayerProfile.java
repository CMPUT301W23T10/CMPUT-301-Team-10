package com.example.qrgo.models;

import java.util.Collection;
import java.util.List;

public class PlayerProfile {
    protected String username;
    protected String firstName;
    protected String lastName;
    protected String playerLocation;
    protected boolean locationEnabled;
    protected String contactPhone;
    protected String contactEmail;
    protected Collection<QRCode> qrCodes;
    protected Collection<Comment> comments;
    protected List<String> qrCodeIds; // A list of QR Code IDs specific to the user
    protected List<String> commentIds; // A list of Comment IDs specific to the user
    protected int totalScore;
    protected int highestScore;
    protected int lowestScore;
    protected int totalScans;

    protected List<BasicQRCode> qrCodeBasicProfiles;

    public PlayerProfile(String username, String firstName, String lastName, String contactPhone, String contactEmail,
                       int totalScore, int highestScore, int lowestScore, int totalScans,
                       List<String> qrScans, List<BasicQRCode> qrCodeBasicProfiles,
                         List<Comment> comments){
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.contactPhone = contactPhone;
        this.contactEmail = contactEmail;
        this.totalScore = totalScore;
        this.highestScore = highestScore;
        this.lowestScore = lowestScore;
        this.totalScans = totalScans;
        this.qrCodeIds = qrScans;
        this.qrCodeBasicProfiles = qrCodeBasicProfiles;
        this.comments = comments;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPlayerLocation() {
        return playerLocation;
    }

    public void setPlayerLocation(String playerLocation) {
        this.playerLocation = playerLocation;
    }

    public boolean isLocationEnabled() {
        return locationEnabled;
    }

    public void setLocationEnabled(boolean locationEnabled) {
        this.locationEnabled = locationEnabled;
    }

    public String getContactPhone() {
        return contactPhone;
    }

    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
    }

    public String getContactEmail() {
        return contactEmail;
    }

    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }

    public Collection<QRCode> getQrCodes() {
        return qrCodes;
    }

    public void setQrCodes(Collection<QRCode> qrCodes) {
        this.qrCodes = qrCodes;
    }

    public Collection<Comment> getComments() {
        return comments;
    }

    public void setComments(Collection<Comment> comments) {
        this.comments = comments;
    }

    public List<String> getQrCodeIds() {
        return qrCodeIds;
    }

    public void setQrCodeIds(List<String> qrCodeIds) {
        this.qrCodeIds = qrCodeIds;
    }

    public List<String> getCommentIds() {
        return commentIds;
    }

    public void setCommentIds(List<String> commentIds) {
        this.commentIds = commentIds;
    }

    public int getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(int totalScore) {
        this.totalScore = totalScore;
    }

    public int getHighestScore() {
        return highestScore;
    }

    public void setHighestScore(int highestScore) {
        this.highestScore = highestScore;
    }

    public int getLowestScore() {
        return lowestScore;
    }

    public void setLowestScore(int lowestScore) {
        this.lowestScore = lowestScore;
    }

    public int getTotalScans() {
        return totalScans;
    }

    public void setTotalScans(int totalScans) {
        this.totalScans = totalScans;
    }

    public List<BasicQRCode> getQrCodeBasicProfiles() {
        return qrCodeBasicProfiles;
    }

    public void setQrCodeBasicProfiles(List<BasicQRCode> qrCodeBasicProfiles) {
        this.qrCodeBasicProfiles = qrCodeBasicProfiles;
    }

    public interface Scoring{
        public Collection<PlayerProfile> viewLeaderBoard();
        public Integer viewHighestQrRanking();
    }

    public interface PlayerSearching{
        public Collection<PlayerProfile> searchByUsername();
    }

}

