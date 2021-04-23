package com.gyh.common.dto;

import com.gyh.common.tools.StringUtils;
import com.gyh.system.sys.utils.IpUtils;

import java.io.Serializable;

/**
 * 阿里云 IP 地理位置查询
 * 接收 实体类
 * @author gyh
 * @Date 2021/4/9 10:37
 */
public class IPDto implements Serializable {
    private String city;
    private String cityEn;
    private String country;
    private String countryCode;
    private String countryEn;
    private String county;
    private String ip;
    private String isp;
    private String latitude;
    private String longitude;
    private String province;
    private String provinceEn;
    private String requestId;

    private String address;

    public IPDto(String ip) {
        this.ip = ip;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCityEn() {
        return cityEn;
    }

    public void setCityEn(String cityEn) {
        this.cityEn = cityEn;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getCountryEn() {
        return countryEn;
    }

    public void setCountryEn(String countryEn) {
        this.countryEn = countryEn;
    }

    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getIsp() {
        return isp;
    }

    public void setIsp(String isp) {
        this.isp = isp;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getProvinceEn() {
        return provinceEn;
    }

    public void setProvinceEn(String provinceEn) {
        this.provinceEn = provinceEn;
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public String getAddress() {
        if (StringUtils.isNotEmpty(address)) {
            return address;
        } else {
            if (IpUtils.LOCALHOST.equals(ip)) {
                return "本机访问";
            } else {
                return country + "," + province + "," + city;
            }
        }
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
