package com.michaelfahmy.udacity.moviz;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by michael on 4/9/16.
 */
public class Movies {
    private Integer page;
    public List<Movie> results = new ArrayList<Movie>();
    private Integer totalResults;
    private Integer totalPages;

    /**
     * No args constructor for use in serialization
     */
    public Movies() {
    }

    /**
     *
     * @param results
     * @param totalResults
     * @param page
     * @param totalPages
     */
    public Movies(Integer page, List<Movie> results, Integer totalResults, Integer totalPages) {
        this.page = page;
        this.results = results;
        this.totalResults = totalResults;
        this.totalPages = totalPages;
    }

    public Integer getPage() {
        return page;
    }

    public Integer getTotalResults() {
        return totalResults;
    }

    public Integer getTotalPages() {
        return totalPages;
    }
}
