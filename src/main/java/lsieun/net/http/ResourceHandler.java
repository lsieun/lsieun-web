package lsieun.net.http;

import lsieun.net.Resource;

import java.util.Optional;

public abstract class ResourceHandler {
    public abstract Resource getResource(String uri_path);
}
