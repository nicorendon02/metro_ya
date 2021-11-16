package com.umanizales.metro_ya.controller.dto;

import com.umanizales.metro_ya.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BoyWithParentDTO {
    private User boy;
    private int parentId;
}