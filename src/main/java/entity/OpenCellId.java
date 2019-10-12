package entity;

import java.sql.Timestamp;

public class OpenCellId {

    private String radio;
    private Integer mcc;
    private Integer net;
    private Integer area;
    private Integer cell;
    private Integer unit;
    private Double lon;
    private Double lat;
    private Integer range;
    private Integer samples;
    private Integer changeable;
    private Timestamp created;
    private Timestamp updated;
    private Integer averageSignal;

    public OpenCellId(String radio, Integer mcc, Integer net, Integer area,
                      Integer cell, Integer unit, Double lon, Double lat,
                      Integer range, Integer samples, Integer changeable,
                      Timestamp created, Timestamp updated, Integer averageSignal) {
        this.radio = radio;
        this.mcc = mcc;
        this.net = net;
        this.area = area;
        this.cell = cell;
        this.unit = unit;
        this.lon = lon;
        this.lat = lat;
        this.range = range;
        this.samples = samples;
        this.changeable = changeable;
        this.created = created;
        this.updated = updated;
        this.averageSignal = averageSignal;
    }

    public OpenCellId() {
    }

       public String getRadio() {
        return radio;
    }

    public void setRadio(String radio) {
        this.radio = radio;
    }

    public Integer getMcc() {
        return mcc;
    }

    public void setMcc(Integer mcc) {
        this.mcc = mcc;
    }

    public Integer getNet() {
        return net;
    }

    public void setNet(Integer net) {
        this.net = net;
    }

    public Integer getArea() {
        return area;
    }

    public void setArea(Integer area) {
        this.area = area;
    }

    public Integer getCell() {
        return cell;
    }

    public void setCell(Integer cell) {
        this.cell = cell;
    }

    public Integer getUnit() {
        return unit;
    }

    public void setUnit(Integer unit) {
        this.unit = unit;
    }

    public Double getLon() {
        return lon;
    }

    public void setLon(Double lon) {
        this.lon = lon;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public Integer getRange() {
        return range;
    }

    public void setRange(Integer range) {
        this.range = range;
    }

    public Integer getSamples() {
        return samples;
    }

    public void setSamples(Integer samples) {
        this.samples = samples;
    }

    public Integer getChangeable() {
        return changeable;
    }

    public void setChangeable(Integer changeable) {
        this.changeable = changeable;
    }

    public Timestamp getCreated() {
        return created;
    }

    public void setCreated(Timestamp created) {
        this.created = created;
    }

    public Timestamp getUpdated() {
        return updated;
    }

    public void setUpdated(Timestamp updated) {
        this.updated = updated;
    }

    public Integer getAverageSignal() {
        return averageSignal;
    }

    public void setAverageSignal(Integer averageSignal) {
        this.averageSignal = averageSignal;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        OpenCellId that = (OpenCellId) o;

        if (getRadio() != null ? !getRadio().equals(that.getRadio()) : that.getRadio() != null)
            return false;
        if (getMcc() != null ? !getMcc().equals(that.getMcc()) : that.getMcc() != null)
            return false;
        if (getNet() != null ? !getNet().equals(that.getNet()) : that.getNet() != null)
            return false;
        if (getArea() != null ? !getArea().equals(that.getArea()) : that.getArea() != null)
            return false;
        if (getCell() != null ? !getCell().equals(that.getCell()) : that.getCell() != null)
            return false;
        if (getUnit() != null ? !getUnit().equals(that.getUnit()) : that.getUnit() != null)
            return false;
        if (getLon() != null ? !getLon().equals(that.getLon()) : that.getLon() != null)
            return false;
        if (getLat() != null ? !getLat().equals(that.getLat()) : that.getLat() != null)
            return false;
        if (getRange() != null ? !getRange().equals(that.getRange()) : that.getRange() != null)
            return false;
        if (getSamples() != null ? !getSamples().equals(that.getSamples()) : that.getSamples() != null)
            return false;
        if (getChangeable() != null ? !getChangeable().equals(that.getChangeable()) : that.getChangeable() != null)
            return false;
        if (getCreated() != null ? !getCreated().equals(that.getCreated()) : that.getCreated() != null)
            return false;
        if (getUpdated() != null ? !getUpdated().equals(that.getUpdated()) : that.getUpdated() != null)
            return false;
        return getAverageSignal() != null ? getAverageSignal().equals(that.getAverageSignal()) : that.getAverageSignal() == null;
    }

    @Override
    public int hashCode() {
        int result = (getRadio() != null ? getRadio().hashCode() : 0);
        result = 31 * result + (getMcc() != null ? getMcc().hashCode() : 0);
        result = 31 * result + (getNet() != null ? getNet().hashCode() : 0);
        result = 31 * result + (getArea() != null ? getArea().hashCode() : 0);
        result = 31 * result + (getCell() != null ? getCell().hashCode() : 0);
        result = 31 * result + (getUnit() != null ? getUnit().hashCode() : 0);
        result = 31 * result + (getLon() != null ? getLon().hashCode() : 0);
        result = 31 * result + (getLat() != null ? getLat().hashCode() : 0);
        result = 31 * result + (getRange() != null ? getRange().hashCode() : 0);
        result = 31 * result + (getSamples() != null ? getSamples().hashCode() : 0);
        result = 31 * result + (getChangeable() != null ? getChangeable().hashCode() : 0);
        result = 31 * result + (getCreated() != null ? getCreated().hashCode() : 0);
        result = 31 * result + (getUpdated() != null ? getUpdated().hashCode() : 0);
        result = 31 * result + (getAverageSignal() != null ? getAverageSignal().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "OpenCellId{" +
                ", radio='" + radio + '\'' +
                ", mcc=" + mcc +
                ", net=" + net +
                ", area=" + area +
                ", cell=" + cell +
                ", unit=" + unit +
                ", lon=" + lon +
                ", lat=" + lat +
                ", range=" + range +
                ", samples=" + samples +
                ", changeable=" + changeable +
                ", created=" + created +
                ", updated=" + updated +
                ", averageSignal=" + averageSignal +
                '}';
    }
}
