package studio.demo.service;

import studio.demo.exception.CommentNullException;
import studio.demo.exception.CommentWithThisNameDoesNotExist;
import studio.demo.exception.CommentWithThisTopicExist;
import studio.demo.exception.UserNullException;
import studio.demo.model.service.CommentServiceModel;
import studio.demo.model.view.CommentViewModel;


import java.util.List;

public interface CommentService {



    CommentServiceModel addComment(CommentServiceModel commentServiceModel) throws CommentNullException, CommentWithThisTopicExist, UserNullException;

    List<CommentViewModel> findAllItems();

    CommentViewModel findById(String id);

    boolean delete(String id);

    CommentServiceModel update (CommentViewModel comment) throws CommentWithThisNameDoesNotExist;
}
