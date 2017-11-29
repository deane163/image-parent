package com.xiaoshu.model;

import java.io.Serializable;

/**
 * 
 * @author administrator
 *
 */
public class ServerConfig implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int id;

	private String ip;

	private int weight;

	private int startWeight;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

	public int getStartWeight() {
		return startWeight;
	}

	public void setStartWeight(int startWeight) {
		this.startWeight = startWeight;
	}

}
