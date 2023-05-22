package com.vti.service;

import com.vti.entity.Cart;
import com.vti.entity.Comment;
import com.vti.form.creating.CartFormForCreating;
import com.vti.form.creating.CommentFormForCreating;
import com.vti.repository.ICommentRepository;
import com.vti.service.implement.ICommentService;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.modelmapper.TypeMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CommentService implements ICommentService {

    @Autowired
    private ICommentRepository repository;

    @Autowired
    private ModelMapper modelMapper;


    @Override
    public Page<Comment> getCommentByProductId(Pageable pageable, int id) {
        return repository.findByProductId(pageable, id);
    }

    @Override
    public void createComment(CommentFormForCreating form) {

        TypeMap<CommentFormForCreating, Comment> typeMap = modelMapper.getTypeMap(CommentFormForCreating.class, Comment.class);
        if (typeMap == null) { // if not already added
            // skip field
            modelMapper.addMappings(new PropertyMap<CommentFormForCreating, Comment>() {
                @Override
                protected void configure() {
                    skip(destination.getId());
                }
            });
        }
        // convert form to entity
        Comment comment = modelMapper.map(form, Comment.class);

        repository.save(comment);
    }

    @Override
    public void deleteCommentByUserIdAndProductId(int userId, int productId) {
        repository.deleteCommentByUserIdAndProductId(userId, productId);
    }


}
