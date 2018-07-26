package com.ljcs.cxwl.util;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.ljcs.cxwl.entity.ProvinceJson;
import com.ljcs.cxwl.entity.ProvinceList;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

/**
 * @author xlei
 * @Date 2018/7/26.
 */

public class GsonUtils {
    private static Gson gson = null;

    static {
        if (gson == null) {
            gson = new Gson();
        }
    }

    private GsonUtils() {
    }

    /**
     * 转成json
     *
     * @param object
     * @return
     */
    public static String GsonString(Object object) {
        String gsonString = null;
        if (gson != null) {
            gsonString = gson.toJson(object);
        }
        return gsonString;
    }

    /**
     * 转成bean
     *
     * @param gsonString
     * @param cls
     * @return
     */
    public static <T> T GsonToBean(String gsonString, Class<T> cls) {
        T t = null;
        if (gson != null) {
            t = gson.fromJson(gsonString, cls);
        }
        return t;
    }

    /**
     * 转成list
     *
     * @param gsonString
     * @param cls
     * @return
     */
    public static <T> List<T> GsonToList(String gsonString, Class<T> cls) {
        List<T> list = null;
        if (gson != null) {
            list = gson.fromJson(gsonString, new TypeToken<List<T>>() {
            }.getType());
        }
        return list;
    }

    /**
     * 转成list中有map的
     *
     * @param gsonString
     * @return
     */
    public static <T> List<Map<String, T>> GsonToListMaps(String gsonString) {
        List<Map<String, T>> list = null;
        if (gson != null) {
            list = gson.fromJson(gsonString, new TypeToken<List<Map<String, T>>>() {
            }.getType());
        }
        return list;
    }

    /**
     * 转成map的
     *
     * @param gsonString
     * @return
     */
    public static <T> Map<String, T> GsonToMaps(String gsonString) {
        Map<String, T> map = null;
        if (gson != null) {
            map = gson.fromJson(gsonString, new TypeToken<Map<String, T>>() {
            }.getType());
        }
        return map;
    }

    /**
     * 获取省份数据
     *
     * @param consumer
     * @param context
     * @param <S>
     * @return
     */

    public  static <S> Disposable getData(DisposableObserver<S> consumer, final Context context) {
        return Observable.create(new ObservableOnSubscribe<S>() {
            @Override
            public void subscribe(ObservableEmitter<S> e) throws Exception {
                ProvinceList data = new ProvinceList();
                InputStream is = context.getAssets().open("province.json");
                String text = FileUtil.readTextFromFile(is);
                List<ProvinceJson> provinceJsons = GsonToList(text, ProvinceJson.class);
                data.setOptions1Items(provinceJsons);
                List<List<String>> list2 = new ArrayList<>();
                List<List<List<String>>> list3 = new ArrayList<>();
                for (int i = 0; i < provinceJsons.size(); i++) {//遍历省份
                   List<String> CityList = new ArrayList<>();//该省的城市列表（第二级）
                    List<List<String>> Province_AreaList = new ArrayList<>();//该省的所有地区列表（第三极）

                    for (int c = 0; c < provinceJsons.get(i).getCity().size(); c++) {//遍历该省份的所有城市
                        String CityName = provinceJsons.get(i).getCity().get(c).getName();
                        CityList.add(CityName);//添加城市
                        ArrayList<String> City_AreaList = new ArrayList<>();//该城市的所有地区列表

                        //如果无地区数据，建议添加空字符串，防止数据为null 导致三个选项长度不匹配造成崩溃
                        if (provinceJsons.get(i).getCity().get(c).getArea() == null || provinceJsons.get(i).getCity().get(c).getArea().size() == 0) {
                            City_AreaList.add("");
                        } else {
                            City_AreaList.addAll(provinceJsons.get(i).getCity().get(c).getArea());
                        }
                        Province_AreaList.add(City_AreaList);//添加该省所有地区数据
                    }
                    list2.add(CityList);
                    list3.add(Province_AreaList);
                }
                data.setOptions2Items(list2);
                data.setOptions3Items(list3);
                e.onNext((S) data);
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribeWith(consumer);
    }
}
