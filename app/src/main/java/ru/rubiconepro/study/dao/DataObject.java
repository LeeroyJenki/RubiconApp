package ru.rubiconepro.study.dao;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public abstract class DataObject {
    protected final int id;
}
