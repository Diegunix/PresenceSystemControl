package com.presencesystem.commons.enums;

public enum ActionEnum {

    CLOCK_IN("Entrada", 0, true), CLOCK_OUT("Salida", 1,false);

    private String description;

    private Integer code;
    
    private Boolean result;

    ActionEnum(String description, Integer code, Boolean result) {
        this.description = description;
        this.code = code;
        this.result = result;
    }

    public String getDescription() {
        return description;
    }

    public Integer getCode() {
        return code;
    }
    
    public Boolean getResult() {
        return result;
    }

    public static ActionEnum get(Integer code) {
        for (ActionEnum action : ActionEnum.values()) {
            if (action.getCode().equals(code)) {
                return action;
            }
        }
        return null;
    }

    public static ActionEnum get(String description) {
        for (ActionEnum action : ActionEnum.values()) {
            if (action.getDescription().equals(description)) {
                return action;
            }
        }
        return null;
    }

    public static ActionEnum get(Boolean result) {
        for (ActionEnum action : ActionEnum.values()) {
            if (action.getResult().equals(result)) {
                return action;
            }
        }
        return null;
    }

}
