package com.chen.blogbackend.entities;

import com.datastax.oss.driver.api.mapper.annotations.Entity;

import java.util.List;
import java.util.Map;

@Entity
public class Video {

    private String resourceId;
    private String poster;
    private String score;
    private String introduction;
    private String movieName;
    private String tags;
    private List<String> actorList;
    private String releaseYear ;
    private String level;
    private List<String> pictureList;
    private Map<String, String> makerList;
    private List<String> genreList;
    private Integer position;
    private String type;
    private Integer totalSeason;

    public Video(String resourceId, String poster, String score, String introduction, String movieName, String tags,
                 List<String> actorList, String releaseYear, String level, List<String> pictureList, Map<String,
            String> makerList,int position, List<String> genreList, String type, Integer totalSeason) {
        this.resourceId = resourceId;
        this.poster = poster;
        this.score = score;
        this.introduction = introduction;
        this.movieName = movieName;
        this.tags = tags;
        this.actorList = actorList;
        this.releaseYear = releaseYear;
        this.level = level;
        this.pictureList = pictureList;
        this.makerList = makerList;
        this.genreList = genreList;
        this.position = position;
        this.type = type;
        this.totalSeason = totalSeason;
    }

    public Video() {
    }

    public Integer getTotalSeason() {
        return totalSeason;
    }

    public void setTotalSeason(Integer totalSeason) {
        this.totalSeason = totalSeason;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }




    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public List<String> getActorList() {
        return actorList;
    }

    public void setActorList(List<String> actorList) {
        this.actorList = actorList;
    }

    public String getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(String releaseYear) {
        this.releaseYear = releaseYear;
    }

    public List<String> getPictureList() {
        return pictureList;
    }

    public void setPictureList(List<String> pictureList) {
        this.pictureList = pictureList;
    }

    public Map<String, String> getMakerList() {
        return makerList;
    }

    public void setMakerList(Map<String, String> makerList) {
        this.makerList = makerList;
    }

    public List<String> getGenreList() {
        return genreList;
    }

    public void setGenreList(List<String> genreList) {
        this.genreList = genreList;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }


    public String getResourceId() {
        return resourceId;
    }

    public void setResourceId(String resourceId) {
        this.resourceId = resourceId;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }


    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }


    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

}
