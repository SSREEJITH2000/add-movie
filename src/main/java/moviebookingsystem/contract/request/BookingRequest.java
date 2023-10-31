package moviebookingsystem.contract.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookingRequest {
    private long movieId;
    private long seatNumber;
    private long showTimeId;
    private long memberId;

    public BookingRequest(){}

}
