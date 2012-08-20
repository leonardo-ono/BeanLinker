package jbean;

import java.math.BigDecimal;
import java.util.Date;

/**
 *
 * @author leonardo
 */
public class ObjA {
    
    private byte varBytePrim = 1;
    private Byte varByteObj = 1;
    
    private short varShortPrim = 2;
    private Short varShortObj = 2;

    private int varIntPrim = 3;
    private Integer varIntObj = 3;

    private long varLongPrim = 4L;
    private Long varLongObj = 4L;

    private float varFloatPrim = 5.0f;
    private Float varFloatObj = 5.0f;
    
    private double varDoublePrim = 6.0d;
    private Double varDoubleObj = 6.0d;

    private boolean varBooleanPrim = true;
    private Boolean varBooleanObj = true;

    private char varCharPrim = 'A';
    private Character varCharObj = 'A';
    
    private Object obj = new Object();
    private BigDecimal varBigDecimalObj = BigDecimal.TEN;
    private Date varDateObj = new Date();
    
    public Boolean getVarBooleanObj() {
        return varBooleanObj;
    }

    public void setVarBooleanObj(Boolean varBooleanObj) {
        this.varBooleanObj = varBooleanObj;
    }

    public boolean isVarBooleanPrim() {
        return varBooleanPrim;
    }

    public void setVarBooleanPrim(boolean varBooleanPrim) {
        this.varBooleanPrim = varBooleanPrim;
    }

    public Byte getVarByteObj() {
        return varByteObj;
    }

    public void setVarByteObj(Byte varByteObj) {
        this.varByteObj = varByteObj;
    }

    public byte getVarBytePrim() {
        return varBytePrim;
    }

    public void setVarBytePrim(byte varBytePrim) {
        this.varBytePrim = varBytePrim;
    }

    public Character getVarCharObj() {
        return varCharObj;
    }

    public void setVarCharObj(Character varCharObj) {
        this.varCharObj = varCharObj;
    }

    public char getVarCharPrim() {
        return varCharPrim;
    }

    public void setVarCharPrim(char varCharPrim) {
        this.varCharPrim = varCharPrim;
    }

    public Double getVarDoubleObj() {
        return varDoubleObj;
    }

    public void setVarDoubleObj(Double varDoubleObj) {
        this.varDoubleObj = varDoubleObj;
    }

    public double getVarDoublePrim() {
        return varDoublePrim;
    }

    public void setVarDoublePrim(double varDoublePrim) {
        this.varDoublePrim = varDoublePrim;
    }

    public Float getVarFloatObj() {
        return varFloatObj;
    }

    public void setVarFloatObj(Float varFloatObj) {
        this.varFloatObj = varFloatObj;
    }

    public float getVarFloatPrim() {
        return varFloatPrim;
    }

    public void setVarFloatPrim(float varFloatPrim) {
        this.varFloatPrim = varFloatPrim;
    }

    public Integer getVarIntObj() {
        return varIntObj;
    }

    public void setVarIntObj(Integer varIntObj) {
        this.varIntObj = varIntObj;
    }

    public int getVarIntPrim() {
        return varIntPrim;
    }

    public void setVarIntPrim(int varIntPrim) {
        this.varIntPrim = varIntPrim;
    }

    public Long getVarLongObj() {
        return varLongObj;
    }

    public void setVarLongObj(Long varLongObj) {
        this.varLongObj = varLongObj;
    }

    public long getVarLongPrim() {
        return varLongPrim;
    }

    public void setVarLongPrim(long varLongPrim) {
        this.varLongPrim = varLongPrim;
    }

    public Short getVarShortObj() {
        return varShortObj;
    }

    public void setVarShortObj(Short varShortObj) {
        this.varShortObj = varShortObj;
    }

    public short getVarShortPrim() {
        return varShortPrim;
    }

    public void setVarShortPrim(short varShortPrim) {
        this.varShortPrim = varShortPrim;
    }

    public Object getObj() {
        return obj;
    }

    public void setObj(Object obj) {
        this.obj = obj;
    }

    public BigDecimal getVarBigDecimalObj() {
        return varBigDecimalObj;
    }

    public void setVarBigDecimalObj(BigDecimal varBigDecimalObj) {
        this.varBigDecimalObj = varBigDecimalObj;
    }

    public Date getVarDateObj() {
        return varDateObj;
    }

    public void setVarDateObj(Date varDateObj) {
        this.varDateObj = varDateObj;
    }

}
