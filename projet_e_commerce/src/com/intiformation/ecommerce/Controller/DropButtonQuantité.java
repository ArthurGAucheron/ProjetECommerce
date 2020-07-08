package com.intiformation.ecommerce.Controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
/**
 * Managed Bean pour le DropButton
 * @author arthu
 *
 */

@ManagedBean(name="dropButton")
@SessionScoped

public class DropButtonQuantité implements Serializable{

	private String items;
	

	public List<String> completeText(String query) {
        List<String> results = new ArrayList<String>();
        for(int i = 0; i < 10; i++) {
            results.add(query + i);
        }
         
        return results;
    }


	public String getItems() {
		return items;
	}


	public void setItems(String items) {
		this.items = items;
	}
	
	

}
