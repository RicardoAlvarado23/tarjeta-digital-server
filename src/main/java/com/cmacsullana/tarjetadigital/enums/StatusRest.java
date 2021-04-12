package com.cmacsullana.tarjetadigital.enums;

/**
 * 
 * @author riap
 *
 */
public enum StatusRest {
    EXITO("00"),
    EXITO2("0"),
    ERROR("99");
    
    private final String code;
    
    StatusRest(String code){
        this.code = code;
    }

    /**
     * @return the valor
     */
    public String getCode() {
        return code;
    }
}