package com.ruhua.domain.common.base.context;

public class MobileClientContext extends ClientContext
{
  private String clientVersion;
  private String networkType;
  private String openudid;
  private String pin;
  private String ip;
  private String area;
  private String client;
  private String osVersion;
  private String uuid;
  private String screen;
  private String adid;
  private String partner;

  public String getClientVersion()
  {
    return this.clientVersion;
  }

  public void setClientVersion(String clientVersion) {
    this.clientVersion = clientVersion;
  }

  public String getNetworkType() {
    return this.networkType;
  }

  public String getPin() {
    return this.pin;
  }

  public void setPin(String pin) {
    this.pin = pin;
  }

  public String getIp() {
    return this.ip;
  }

  public void setIp(String ip) {
    this.ip = ip;
  }

  public void setNetworkType(String networkType) {
    this.networkType = networkType;
  }

  public String getOpenudid() {
    return this.openudid;
  }

  public void setOpenudid(String openudid) {
    this.openudid = openudid;
  }

  public String getArea() {
    return this.area;
  }

  public void setArea(String area) {
    this.area = area;
  }

  public String getClient() {
    return this.client;
  }

  public void setClient(String client) {
    this.client = client;
  }

  public String getOsVersion() {
    return this.osVersion;
  }

  public void setOsVersion(String osVersion) {
    this.osVersion = osVersion;
  }

  public String getUuid() {
    return this.uuid;
  }

  public void setUuid(String uuid) {
    this.uuid = uuid;
  }

  public String getScreen() {
    return this.screen;
  }

  public void setScreen(String screen) {
    this.screen = screen;
  }

  public String getAdid() {
    return this.adid;
  }

  public void setAdid(String adid) {
    this.adid = adid;
  }

  public String getPartner() {
    return this.partner;
  }

  public void setPartner(String partner) {
    this.partner = partner;
  }
}