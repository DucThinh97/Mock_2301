package com.vti.controller;

import com.vti.dto.CatalogDTO;
import com.vti.dto.OderListDTO;
import com.vti.entity.Catalog;
import com.vti.entity.OderList;
import com.vti.form.creating.CartFormForCreating;
import com.vti.form.creating.CatalogFormForCreating;
import com.vti.form.creating.OderListFormForCreating;
import com.vti.form.updating.CatalogFormForUpdating;
import com.vti.service.implement.ICatalogService;
import com.vti.service.implement.IOderListService;
import io.swagger.v3.oas.annotations.Parameter;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping(value = "/api/v1/oderLists")
public class OderListController {

    @Autowired
    private IOderListService service;

    @Autowired
    private ModelMapper modelMapper;


    @GetMapping(value = "/username")
    public ResponseEntity<?> getOderListByUsername(Pageable pageable) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) auth.getPrincipal();

        Page<OderList> oderLists = service.getOderListByUsername(pageable, userDetails.getUsername());

        List<OderListDTO> dtos = modelMapper.map(oderLists.getContent(), new TypeToken<List<OderListDTO>>() {
        }.getType());

        Page<OderListDTO> dtoPages = new PageImpl<>(dtos, pageable, oderLists.getTotalElements());

        return new ResponseEntity<>(dtoPages, HttpStatus.OK);
    }

    @GetMapping(value = "/username/status")
    public ResponseEntity<?> getOderListByUsernameAndStatus(Pageable pageable, @Parameter(name = "status") OderList.Status status) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) auth.getPrincipal();

        Page<OderList> oderLists = service.getOderListByUsernameAndStatus(pageable, userDetails.getUsername(), status);

        List<OderListDTO> dtos = modelMapper.map(oderLists.getContent(), new TypeToken<List<OderListDTO>>() {
        }.getType());

        Page<OderListDTO> dtoPages = new PageImpl<>(dtos, pageable, oderLists.getTotalElements());

        return new ResponseEntity<>(dtoPages, HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<?> createOderList(@Parameter(name = "userId") int userId, @RequestBody OderListFormForCreating form) {
        service.createOderList(userId, form);
        return new ResponseEntity<String>("Create successfully!", HttpStatus.OK);
    }


}
