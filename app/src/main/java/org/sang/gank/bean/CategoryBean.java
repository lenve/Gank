package org.sang.gank.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by 王松 on 2016/9/16.
 */
@Entity
public class CategoryBean {
    @Id
    private Long id;
    @Property
    private String name;
    @Property
    private String category;
    @Property
    private boolean isSelected;
    @Generated(hash = 781692867)
    public CategoryBean(Long id, String name, String category, boolean isSelected) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.isSelected = isSelected;
    }
    @Generated(hash = 1870435730)
    public CategoryBean() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getCategory() {
        return this.category;
    }
    public void setCategory(String category) {
        this.category = category;
    }
    public boolean getIsSelected() {
        return this.isSelected;
    }
    public void setIsSelected(boolean isSelected) {
        this.isSelected = isSelected;
    }
}
