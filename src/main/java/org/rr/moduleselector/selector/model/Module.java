package org.rr.moduleselector.selector.model;

import lombok.Getter;
import lombok.Value;

import java.util.List;


@Value
public class Module {
    String code;
    String name;
    int semester;
    boolean isCompulsory;
    List<Module> alternative;
    Subject subject;
    Universities university;

    public String getFullName() {
        return code + " - " + name;
    }
}