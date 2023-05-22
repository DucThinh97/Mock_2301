package com.vti.service;

import ch.qos.logback.core.joran.util.beans.BeanUtil;
import com.vti.entity.OderDetail;
import com.vti.entity.OderDetailDBConvert;
import com.vti.form.creating.OderDetailFormForCreating;
import com.vti.repository.*;
import com.vti.service.implement.IOderDetailService;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.modelmapper.TypeMap;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.constraints.Null;
import java.util.List;

@Service


@Transactional
public class OderDetailService implements IOderDetailService {

    @Autowired
    private IOderDetailRepository oderDetailRepository;

    @Autowired
    private  IOderDetailConvertRepository oderDetailConvertRepository;

    @Autowired
    private ModelMapper modelMapper;



    @Override
    public void createOderDetailByOderId(int oderId) {
        // set oderId
      List<OderDetailDBConvert> oderDetailConvertLists = oderDetailConvertRepository.getOderDetailByOderId(oderId);
        for (OderDetailDBConvert detailDBConvert : oderDetailConvertLists
             ) {

//            TypeMap<OderDetailFormForCreating, OderDetail> typeMap = modelMapper.getTypeMap(OderDetailFormForCreating.class, OderDetail.class);
//        if (typeMap == null) { // if not already added
//            // skip field
//            modelMapper.addMappings(new PropertyMap<OderDetailFormForCreating, OderDetail>() {
//                @Override
//                protected void configure() {
//                    skip(destination.getId());
//                }
//            });
//        }
//            OderDetail oderDetail = modelMapper.map(oderDetailFormForCreating, OderDetail.class);
            OderDetail oderDetail = new OderDetail();
            BeanUtils.copyProperties(detailDBConvert,oderDetail,"idConvert","id");


            oderDetailRepository.save(oderDetail);
        }
    }


//    @Override
//    public Page<OderDetail> getOderDetailByOderId(Pageable pageable, int oderId) {
//
//// set oderId
//        OderDetailFormForCreating oderDetailFormForCreating = new OderDetailFormForCreating();
//        oderDetailFormForCreating.setOderListId(oderId);
//
//        // set productName and salePrice
//        List<Product> products = productRepository.getNameAndPriceByOderId(oderId);
//
//        for (Product form: products) {
//
//            oderDetailFormForCreating.setProductName(form.getName());
//            oderDetailFormForCreating.setPrice(form.getPrice());
//        }
//
//        // set Quantity
//        List<Integer> quantityByOderId = cartRepository.getQuantityByOderId(oderId);
//        for (Integer integer : quantityByOderId){
//            oderDetailFormForCreating.setQuantity(integer);
//        }
//
//        // set Total
//        List<Integer> totalByOderId = oderDetailRepository.getTotalByOderId(oderId);
//        for (Integer integer : totalByOderId){
//            oderDetailFormForCreating.setTotal(integer);
//
//        }
//
//        // omit id field
//        TypeMap<OderDetailFormForCreating, OderDetail> typeMap = modelMapper.getTypeMap(OderDetailFormForCreating.class, OderDetail.class);
//        if (typeMap == null) { // if not already added
//            // skip field
//            modelMapper.addMappings(new PropertyMap<OderDetailFormForCreating, OderDetail>() {
//                @Override
//                protected void configure() {
//                    skip(destination.getId());
//                }
//            });
//        }
//        // convert form to entity
//        OderDetail oderDetail = modelMapper.map(oderDetailFormForCreating, OderDetail.class);
//
//        oderDetailRepository.save(oderDetail);
//
//        return oderDetailRepository.findAllByOderListId(pageable,oderId);
//    }
}
