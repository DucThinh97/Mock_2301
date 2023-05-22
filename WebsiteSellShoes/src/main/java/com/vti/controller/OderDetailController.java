package com.vti.controller;

import com.vti.dto.oderDetail.OderDetailDTO;
import com.vti.entity.OderDetail;
import com.vti.form.creating.CatalogFormForCreating;
import com.vti.service.implement.IOderDetailService;
import io.swagger.v3.oas.annotations.Parameter;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping(value = "/api/v1/oderDetails")
public class OderDetailController {

    @Autowired
    private IOderDetailService service;

    @Autowired
    private ModelMapper modelMapper;


//    @GetMapping()
//    public ResponseEntity<?> getOderDetailByOderId(Pageable pageable, @Parameter(name = "oderId") int oderId) {
//
//        Page<OderDetail> oderDetails = service.createOderDetailByOderId(pageable, oderId);
//
//        // convert entities --> dtos
//        List<OderDetailDTO> dtos = modelMapper.map(oderDetails.getContent(), new TypeToken<List<OderDetailDTO>>() {
//        }.getType());
//
//        Page<OderDetailDTO> dtoPages = new PageImpl<>(dtos, pageable, oderDetails.getTotalElements());
//
//        return new ResponseEntity<>(dtoPages, HttpStatus.OK);
//    }

    @PostMapping()
    public ResponseEntity<?> createOderDetailByOderId( @Parameter(name = "oderId") int oderId) {
        service.createOderDetailByOderId(oderId);
        return new ResponseEntity<String>("Create successfully!", HttpStatus.OK);
    }


}
