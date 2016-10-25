package br.com.seartv.service;

import java.util.List;

import br.com.seartv.model.Dates;
import br.com.seartv.model.Movie;

public class UpComingMoviesResponseModel {

    private long page;
    private List<Movie> results;
    private Dates dates;
    private long total_pages;
    private long total_results;

    public Dates getDates() {
        return dates;
    }

    public void setDates(Dates dates) {
        this.dates = dates;
    }

    public long getPage() {
        return page;
    }

    public void setPage(long page) {
        this.page = page;
    }

    public List<Movie> getResults() {
        return results;
    }

    public void setResults(List<Movie> results) {
        this.results = results;
    }

    public long getTotal_pages() {
        return total_pages;
    }

    public void setTotal_pages(long total_pages) {
        this.total_pages = total_pages;
    }

    public long getTotal_results() {
        return total_results;
    }

    public void setTotal_results(long total_results) {
        this.total_results = total_results;
    }
}