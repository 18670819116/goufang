package com.ljcs.cxwl.entity;

import java.util.List;

/**
 * @author xlei
 * @Date 2018/7/26.
 */

public class ProvinceList {
    private List<ProvinceJson> options1Items;
    private List<List<String>> options2Items;
    private List<List<List<String>>> options3Items;

    public List<ProvinceJson> getOptions1Items() {
        return options1Items;
    }

    public void setOptions1Items(List<ProvinceJson> options1Items) {
        this.options1Items = options1Items;
    }

    public List<List<String>> getOptions2Items() {
        return options2Items;
    }

    public void setOptions2Items(List<List<String>> options2Items) {
        this.options2Items = options2Items;
    }

    public List<List<List<String>>> getOptions3Items() {
        return options3Items;
    }

    public void setOptions3Items(List<List<List<String>>> options3Items) {
        this.options3Items = options3Items;
    }
}
