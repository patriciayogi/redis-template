package com.search.job.indeed.models.response;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
public class Results implements Serializable {

        private static final long serialVersionUID = 1L;

        private String jobtitle;
        private String company;
        private String city;
        private String state;
        private String country;
        private String language;
        private String formattedLocation;
        private String source;
        private String date;
        private String snippet;
        private String url;
        private String onmousedown;
        private String latitude;
        private String longitude;
        private String jobkey;
        private boolean sponsored;
        private boolean expired;
        private boolean indeedApply;
        private String formattedLocationFull;
        private String formattedRelativeTime;
        private String stations;

    public Results(String jobtitle, String company, String city, String state, String country, String language, String formattedLocation, String source, String date, String snippet, String url, String onmousedown, String latitude, String longitude, String jobkey, boolean sponsored, boolean expired, boolean indeedApply, String formattedLocationFull, String formattedRelativeTime, String stations) {
        this.jobtitle = jobtitle;
        this.company = company;
        this.city = city;
        this.state = state;
        this.country = country;
        this.language = language;
        this.formattedLocation = formattedLocation;
        this.source = source;
        this.date = date;
        this.snippet = snippet;
        this.url = url;
        this.onmousedown = onmousedown;
        this.latitude = latitude;
        this.longitude = longitude;
        this.jobkey = jobkey;
        this.sponsored = sponsored;
        this.expired = expired;
        this.indeedApply = indeedApply;
        this.formattedLocationFull = formattedLocationFull;
        this.formattedRelativeTime = formattedRelativeTime;
        this.stations = stations;
    }
}
