package com.anasajimuhammed.newurl.dto;

import com.anasajimuhammed.newurl.models.URLModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class URLDataResponseModel {

    List<URLModel> urlData;
    String BaseURL;
}
