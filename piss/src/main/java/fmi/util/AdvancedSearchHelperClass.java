package fmi.util;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: dpavlov
 * Date: 13-10-10
 * Time: 11:29
 * To change this template use File | Settings | File Templates.
 */
public class AdvancedSearchHelperClass {

    private Long areaId;

    private Long hotelTypeId;

    private Long roomTypeId;

    private Long roomsCount;

    private Date startDate;

    private Date endDate;

    private Long stars;

    private List<Long> facilitiesList = new ArrayList<Long>();

    public void setEndDateAsString(String endDate){
        try{
            this.endDate = new SimpleDateFormat("dd/MM/yyyy").parse(endDate);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public String getEndDateAsString(){
        if(this.endDate != null){
            return new SimpleDateFormat("dd/MM/yyyy").format(this.endDate);
        } else {
            return "";
        }

    }

    public void setStartDateAsString(String startDate){
        try{
            this.startDate = new SimpleDateFormat("dd/MM/yyyy").parse(startDate);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public String getStartDateAsString(){
        if(this.startDate != null){
            return new SimpleDateFormat("dd/MM/yyyy").format(this.startDate);
        } else {
            return "";
        }

    }

    public List<Long> getFacilitiesList() {
        return facilitiesList;
    }

    public void setFacilitiesList(List<Long> facilitiesList) {
        this.facilitiesList = facilitiesList;
    }

    public Long getAreaId() {
        return areaId;
    }

    public void setAreaId(Long areaId) {
        this.areaId = areaId;
    }

    public Long getHotelTypeId() {
        return hotelTypeId;
    }

    public void setHotelTypeId(Long hotelTypeId) {
        this.hotelTypeId = hotelTypeId;
    }

    public Long getRoomTypeId() {
        return roomTypeId;
    }

    public void setRoomTypeId(Long roomTypeId) {
        this.roomTypeId = roomTypeId;
    }

    public Long getRoomsCount() {
        return roomsCount;
    }

    public void setRoomsCount(Long roomsCount) {
        this.roomsCount = roomsCount;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Long getStars() {
        return stars;
    }

    public void setStars(Long stars) {
        this.stars = stars;
    }
}
