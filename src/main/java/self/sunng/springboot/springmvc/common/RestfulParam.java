package self.sunng.springboot.springmvc.common;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

public class RestfulParam extends LinkedHashMap<String, String> {

    private static final long serialVersionUID = 4718933253896426291L;
    private HttpServletRequest request;
    private HttpServletResponse response;

    public RestfulParam(HttpServletRequest request, HttpServletResponse response, Map<String, String> m) {
        super(m);
        this.request = request;
        this.response = response;
    }

    public RestfulParam(Map<String, String> m) {
        super(m);
    }

    public Short getShort(String key) {
        String value = get(key);
        if (value == null) {
            return null;
        }
        return Short.parseShort(value);
    }

    public short getShort(String key, short defaultValue) {
        String value = get(key);
        if (value == null) {
            return defaultValue;
        }
        return Short.parseShort(get(key));
    }

    public Integer getInteger(String key) {
        String value = get(key);
        if (value == null) {
            return null;
        }
        return Integer.parseInt(value);
    }

    public int getInteger(String key, int defaultValue) {
        String value = get(key);
        if (value == null) {
            return defaultValue;
        }
        return Integer.parseInt(value);
    }

    public Long getLong(String key) {
        String value = get(key);
        if (value == null) {
            return null;
        }
        return Long.parseLong(value);
    }

    public long getLong(String key, long defaultValue) {
        String value = get(key);
        if (value == null) {
            return defaultValue;
        }
        return Long.parseLong(value);
    }

    public String getString(String key) {
        return get(key);
    }

    public String getString(String key, String defaultValue) {
        String value = get(key);
        if (value == null) {
            return defaultValue;
        }
        return value;
    }

    public Boolean getBoolean(String key) {
        String value = get(key);
        if (value == null) {
            return null;
        }
        boolean result;
        if ("true".equals(value)) {
            result = true;
        } else if ("false".equals(value)) {
            result = false;
        } else if ("0".equals(value)) {
            result = false;
        } else if ("1".equals(value)) {
            result = true;
        } else {
            throw new IllegalArgumentException("value must be 'true'/'false' or '0'/'1'");
        }
        return result;
    }

    public boolean getBoolean(String key, boolean defaultValue) {
        String value = get(key);
        if (value == null) {
            return defaultValue;
        }
        boolean result;
        if ("true".equals(value)) {
            result = true;
        } else if ("false".equals(value)) {
            result = false;
        } else if ("0".equals(value)) {
            result = false;
        } else if ("1".equals(value)) {
            result = true;
        } else {
            throw new IllegalArgumentException("value must be 'true'/'false' or '0'/'1'");
        }
        return result;
    }

    public List<Integer> getIntegerList(String key) {
        String value = get(key);
        if (value == null) {
            return null;
        }
        ArrayList<Integer> list = new ArrayList<Integer>();
        String[] valueArr = value.split(",");
        for (String elementValue : valueArr) {
            list.add(Integer.parseInt(elementValue));
        }
        return list;
    }

    public List<Long> getLongList(String key) {
        String value = get(key);
        if (value == null) {
            return null;
        }
        ArrayList<Long> list = new ArrayList<Long>();
        String[] valueArr = value.split(",");
        for (String elementValue : valueArr) {
            list.add(Long.parseLong(elementValue));
        }
        return list;
    }

    public List<String> getStringList(String key) {
        String value = get(key);
        if (value == null) {
            return null;
        }
        ArrayList<String> list = new ArrayList<String>();
        String[] valueArr = value.split(",");
        for (String elementValue : valueArr) {
            list.add(elementValue);
        }
        return list;
    }

    public HttpServletRequest getRequest() {
        return request;
    }

    public HttpServletResponse getResponse() {
        return response;
    }


    public Map<String, Object> getMap() {
        return new HashMap<String, Object>(this);
    }
}
