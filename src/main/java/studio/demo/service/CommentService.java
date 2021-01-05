package studio.demo.service;

import studio.demo.exception.*;
import studio.demo.model.binding.CommentAddBindingModel;
import studio.demo.model.entity.Comment;
import studio.demo.model.service.CommentServiceModel;
import studio.demo.model.service.OrderServiceModel;
import studio.demo.model.view.CommentViewModel;
import studio.demo.model.view.OrderViewModel;


import java.util.List;
import java.util.Optional;

public interface CommentService {


    CommentServiceModel addComment(CommentServiceModel commentServiceModel) throws CommentNullException, CommentWithThisTopicExist, UserNullException;

    List<CommentViewModel> findAllItems();

    CommentViewModel findById(String id);

    boolean delete(String id) throws CommentWithThisIdDoesNotExist;

    CommentServiceModel update (CommentAddBindingModel comment) throws CommentWithThisNameDoesNotExist;

    Optional<Comment> findByTopic(String topic);

}
