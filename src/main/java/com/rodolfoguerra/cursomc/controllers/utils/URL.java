package com.rodolfoguerra.cursomc.controllers.utils;

import java.net.URLDecoder;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class URL {

    public static List<Long> decoderList(String s) {
        return Arrays.stream(s.split(",")).map(Long::valueOf).collect(Collectors.toList());
    }

    public static String decodeParam(String s){
        return URLDecoder.decode(s, Charset.defaultCharset());
    }
}
