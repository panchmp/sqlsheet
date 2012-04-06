package net.pcal.sqlsheet;

import java.io.Serializable;
import java.util.Date;

public class ResultMock implements Serializable {

    private static final long serialVersionUID = 1L;

	private Integer id;
	private String name;
	private Date date;

	public ResultMock(){
	    id = 0;
	    name="";
	    date = new Date();
	}

    public ResultMock(Integer id,String name, Date date){
        this.id = id;
        this.name = name;
        this.date = date;
    }

	@Override
	public String toString() {
		return "CustomBean [ID=" + id + ", Name=" + name + ", calDate="
		+ date + "]";
	}

	public Integer getID() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
}
