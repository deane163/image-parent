package com.xiaoshu.entity;import com.baomidou.mybatisplus.activerecord.Model;import com.baomidou.mybatisplus.annotations.TableField;import java.util.Date;import com.baomidou.mybatisplus.annotations.TableName;import java.io.Serializable;/** * <p> *  * </p> * * @author administrator * @since 2017-12-05 */@TableName("user_token")public class UserToken extends Model<UserToken> {    private static final long serialVersionUID = 1L;	private Integer id;	private String name;	private String password;	private String token;	@TableField("expired_time")	private Long expiredTime;	@TableField("create_time")	private Date createTime;	public Integer getId() {		return id;	}	public void setId(Integer id) {		this.id = id;	}	public String getName() {		return name;	}	public void setName(String name) {		this.name = name;	}	public String getPassword() {		return password;	}	public void setPassword(String password) {		this.password = password;	}	public String getToken() {		return token;	}	public void setToken(String token) {		this.token = token;	}	public Long getExpiredTime() {		return expiredTime;	}	public void setExpiredTime(Long expiredTime) {		this.expiredTime = expiredTime;	}	public Date getCreateTime() {		return createTime;	}	public void setCreateTime(Date createTime) {		this.createTime = createTime;	}	@Override	protected Serializable pkVal() {		return this.id;	}}