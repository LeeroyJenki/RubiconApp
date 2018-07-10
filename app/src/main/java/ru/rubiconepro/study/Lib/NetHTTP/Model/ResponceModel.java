package ru.rubiconepro.study.Lib.NetHTTP.Model;

import org.json.JSONException;
import org.json.JSONObject;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

import okhttp3.Request;
import okhttp3.Response;

public class ResponceModel {
    public int index;
    public Request request;
    public Response responce;
    public byte[] responceBody;

    public ResponceModel(int index, Request request) {
        this.index = index;
        this.request = request;
    }

    public ResponceModel(int index, Request request, Response responce) {
        this.index = index;
        this.request = request;
        this.responce = responce;
    }

    public ResponceModel(int index, Request request, Response responce, byte[] body) {
        this.index = index;
        this.request = request;
        this.responce = responce;
        this.responceBody = body;
    }

    public String asString() {
        return new String(responceBody, StandardCharsets.UTF_8);
    }

    public JSONObject asJSONObject() throws JSONException {
        return new JSONObject(asString());
    }
}
