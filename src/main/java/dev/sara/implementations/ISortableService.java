package dev.sara.implementations;

import java.util.List;

public interface ISortableService<T, S> extends IGenericService<T, S> {
    List<T> getEntitiesSortedByDate();
}

