package dev.sara.implementations;

import java.util.List;

public interface IGenericService<T> {
    
    public List<T> getEntities();
}
