package biblioteca;

public class Movie {
    String name;
    String director;
    int year;
    double rating;
    boolean checked = false;

    public Movie(String name, String director, int year, double rating) {
        this.name = name;
        this.director = director;
        this.year = year;
        this.rating = rating;
    }

    public void setChecked() {
        this.checked = true;
    }
}
