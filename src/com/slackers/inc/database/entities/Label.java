/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.slackers.inc.database.entities;

import com.slackers.inc.database.IEntity;
import java.io.Serializable;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author John Stegeman <j.stegeman@labyrinth-tech.com>
 */
public class Label implements IEntity{
    
    private static final String TABLE = "LABELS";
    
    public static enum BeverageSource
    {
        DOMESTIC,
        IMPORTED;
    }    
    public static enum BeverageType
    {
        WINE,
        BEER,
        DISTILLED;
    }
    
    private long labelId;
    private double alchoholContent;
    private boolean isAccepted;
    
    private String representativeIdNumber;
    private String plantNumber;
    private BeverageSource productSource;
    private BeverageType productType;
    private String brandName;
    
    public Label()
    {
        this.alchoholContent = -1;
        this.labelId = 0;
        this.isAccepted = false;
        this.plantNumber = null;
        this.productSource = null;
        this.productType = null;
        this.representativeIdNumber = null;
        this.brandName = null;
    }

    public double getAlchoholContent() {
        return alchoholContent;
    }

    public void setAlchoholContent(double alchoholContent) {
        this.alchoholContent = alchoholContent;
    }

    public long getLabelId() {
        return labelId;
    }

    public void setLabelId(long labelId) {
        this.labelId = labelId;
    }

    public boolean isIsAccepted() {
        return isAccepted;
    }

    public void setIsAccepted(boolean isAccepted) {
        this.isAccepted = isAccepted;
    }

    public String getRepresentativeIdNumber() {
        return representativeIdNumber;
    }

    public void setRepresentativeIdNumber(String representativeIdNumber) {
        this.representativeIdNumber = representativeIdNumber;
    }

    public String getPlantNumber() {
        return plantNumber;
    }

    public void setPlantNumber(String plantNumber) {
        this.plantNumber = plantNumber;
    }

    public BeverageSource getProductSource() {
        return productSource;
    }

    public void setProductSource(BeverageSource productSource) {
        this.productSource = productSource;
    }

    public BeverageType getProductType() {
        return productType;
    }

    public void setProductType(BeverageType productType) {
        this.productType = productType;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    @Override
    public String getTableName() {
        return TABLE;
    }

    @Override
    public Map<String, Object> getEntityValues() {
        Map<String,Object> values = new HashMap<>();  
        values.put("labelId", this.labelId);
        values.put("isAccepted", this.isAccepted);
        values.put("alchoholContent", this.alchoholContent);
        values.put("representativeIdNumber", this.representativeIdNumber);
        values.put("plantNumber", this.plantNumber);        
        values.put("productSource", this.productSource);
        values.put("productType", this.productType);
        values.put("brandName", this.brandName);
        return values;
    }

    @Override
    public Map<String, Object> getUpdatableEntityValues() {
        Map<String,Object> values = new HashMap<>();  
        values.put("isAccepted", this.isAccepted);
        values.put("alchoholContent", this.alchoholContent);
        values.put("representativeIdNumber", this.representativeIdNumber);
        values.put("plantNumber", this.plantNumber);        
        values.put("productSource", this.productSource);
        values.put("productType", this.productType);
        values.put("brandName", this.brandName);
        return values;
    }

    @Override
    public void setEntityValues(Map<String, Object> values) {
        
        if (values.containsKey("labelId"))
        {
            this.labelId = (long) values.get("labelId");
        }
        if (values.containsKey("isAccepted"))
        {
            this.isAccepted = (Boolean) values.get("isAccepted");
        }
        if (values.containsKey("alchoholContent"))
        {
            this.alchoholContent = (double) values.get("alchoholContent");
        }
        
        if (values.containsKey("representativeIdNumber"))
        {
            this.representativeIdNumber = (String) values.get("representativeIdNumber");
        }
        if (values.containsKey("plantNumber"))
        {
            this.plantNumber = (String) values.get("plantNumber");
        }
        if (values.containsKey("productSource"))
        {
            this.productSource = (BeverageSource) values.get("productSource");
        }
        if (values.containsKey("productType"))
        {
            this.productType = (BeverageType) values.get("productType");
        }
        if (values.containsKey("brandName"))
        {
            this.brandName = (String) values.get("brandName");
        }
    }

    @Override
    public Map<String, Class> getEntityNameTypePairs() {
        Map<String,Class> pairs = new HashMap<>();
        pairs.put("labelId", Long.class);
        pairs.put("isAccepted", Boolean.class);
        pairs.put("alchoholContent", Double.class);
        pairs.put("representativeIdNumber", String.class);
        pairs.put("plantNumber", String.class);        
        pairs.put("productSource", Serializable.class);
        pairs.put("productType", Serializable.class);
        pairs.put("brandName", String.class);        
        return pairs;
    }

    @Override
    public List<String> tableColumnCreationSettings() {
        List<String> cols = new LinkedList<>();
        cols.add("labelId bigint PRIMARY KEY GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1)");
        cols.add("isAccepted boolean");
        cols.add("alchoholContent float");        
        cols.add("representativeIdNumber varchar(128)");
        cols.add("plantNumber varchar(128)");
        cols.add("productSource varchar(512)");
        cols.add("productType varchar(512)");
        cols.add("brandName varchar(512)");
        return cols;
    }

    @Override
    public String getPrimaryKeyName() {
        return "labelId";
    }

    @Override
    public Serializable getPrimaryKeyValue() {
        return this.labelId;
    }

    @Override
    public void setPrimaryKeyValue(Serializable value) {
        this.labelId = (long) value;
    }
    
    @Override
    public Label deepCopy() {
        Label label = new Label();
        label.setEntityValues(this.getEntityValues());
        return label;
    }
}
