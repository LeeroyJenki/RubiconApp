package ru.rubiconepro.study.Lib.NetHTTP.Model;

import okhttp3.Request;
import okhttp3.Response;

public class ResponceModel {
    public int index;
    public Request request;
    public Response responce;

    public ResponceModel(int index, Request request) {
        this.index = index;
        this.request = request;
    }

    public ResponceModel(int index, Request request, Response responce) {
        this.index = index;
        this.request = request;
        this.responce = responce;
    }
}
