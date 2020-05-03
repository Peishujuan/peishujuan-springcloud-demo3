package com.peishujuan.springcloud.jpa.tests.service;

import com.peishujuan.springcloud.jpa.tests.dao.AreaRepository;
import com.peishujuan.springcloud.jpa.tests.entity.AreaEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AreaService {
    @Autowired
    private AreaRepository areaRepository;

    public List<AreaEntity> getAreaList(){
        //return areaRepository.findByPid(1);
        //返回的结果
        List<AreaEntity> provinceList = new ArrayList<>();
        //一次从数据查询的所有数据
        List<AreaEntity> all = areaRepository.findAll();
        //从all里查询省的数据
        all.forEach(province ->{
            if(province.getPid()==1){
                provinceList.add(province);
            }
        });
        //遍历省 查找市的数据
        provinceList.forEach(province->{
            //查省下的所有市
            List<AreaEntity> cityList = new ArrayList<>();
            all.forEach(city->{
                if(city.getPid().intValue()==province.getId().intValue()){
                    cityList.add(city);
                    //查市下的所有区
                    List<AreaEntity> areaList = new ArrayList<>();
                    all.forEach(area->{
                        if(area.getPid().intValue()==city.getId().intValue()){
                            areaList.add(area);
                        }
                    });
                    //设置区
                    city.setAreaList(areaList);
                }
            });
            //设置市
            province.setAreaList(cityList);
        });
        return provinceList;
    }
}
