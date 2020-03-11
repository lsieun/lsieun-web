package lsieun.net.http.handler;

import lsieun.net.Resource;
import lsieun.net.http.bean.HttpHeader;

import java.util.Optional;

public abstract class ResourceHandler {
    public abstract Resource getResource(String uri_path, HttpHeader header);
}
