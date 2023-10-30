package moviebookingsystem.contract.request;

public class BookingRequest {
    private long movieId;
    private long seatNumber;
    private long showTimeId;
    private long memberId;

    public BookingRequest(){}

    public BookingRequest(long movieId, long seatNumber, long showTimeId, long memberId) {
        this.movieId = movieId;
        this.seatNumber = seatNumber;
        this.showTimeId = showTimeId;
        this.memberId = memberId;
    }

    public long getMovieId() {
        return movieId;
    }

    public void setMovieId(long movieId) {
        this.movieId = movieId;
    }

    public long getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(long seatNumber) {
        this.seatNumber = seatNumber;
    }

    public long getShowTimeId() {
        return showTimeId;
    }

    public void setShowTimeId(long showTimeId) {
        this.showTimeId = showTimeId;
    }

    public long getMemberId() {
        return memberId;
    }

    public void setMemberId(long memberId) {
        this.memberId = memberId;
    }
}
