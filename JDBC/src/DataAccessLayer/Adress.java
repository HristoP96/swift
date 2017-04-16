/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataAccessLayer;

/**
 *
 * @author ickoto
 */
public class Adress {
    
    
    private String _country;
    private String _city;
    private String _municipality;
    private String _postalCode;
    private String _street;
    private String _number;
    private Integer _floor;
    private Integer _apartmentNo;

    public Adress(String _country, String _city, String _municipality, String _postalCode, String _street, String _number, Integer _floor, Integer _apartmentNo) {
        this._country = _country;
        this._city = _city;
        this._municipality = _municipality;
        this._postalCode = _postalCode;
        this._street = _street;
        this._number = _number;
        this._floor = _floor;
        this._apartmentNo = _apartmentNo;
    }

    public String getCountry() {
        return _country;
    }

    public String getCity() {
        return _city;
    }

    public String getMunicipality() {
        return _municipality;
    }

    public String getPostalCode() {
        return _postalCode;
    }

    public String getStreet() {
        return _street;
    }

    public Integer getFloor() {
        return _floor;
    }

    public Integer getApartmentNo() {
        return _apartmentNo;
    }

    public String getNumber() {
        return _number;
    }

    @Override
    public String toString() {
        String result = String.format("%s %s Street%n", _number, _street);
        if (_floor != null && _apartmentNo != null) {
            result += String.format("fl. %d, ap. %d%n", _floor, _apartmentNo);
        }
        result += String.format("%s %s%n", _postalCode, _municipality);
        result += String.format("%s, %s", _city, _country);

        return result;
    }

    public Adress() {
    }

}
