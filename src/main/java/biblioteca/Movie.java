package biblioteca;

public class Movie {
    private String name;
    private String director;
    private int year;
    private double rating;
    private boolean checked = false;
    private User renter;

    public Movie(String name, String director, int year, double rating) {
        this.name = name;
        this.director = director;
        this.year = year;
        this.rating = rating;
    }

    public void setUnchecked() {
        this.checked = false;
        this.renter = null;
    }

    public void checkout(User user) {
        this.checked = true;
        this.renter = user;
    }

    public boolean getChecked() {
        return checked;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public User getRenter() {
        return renter;
    }

    public void setRenter(User renter) {
        this.renter = renter;
    }
}
