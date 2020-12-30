package studio.demo.service;

import studio.demo.model.service.CommentServiceModel;
import studio.demo.model.view.CommentViewModel;


import java.util.List;

public interface CommentService {



    void addComment(CommentServiceModel commentServiceModel);

    List<CommentViewModel> findAllItems();

    CommentViewModel findById(String id);

    void delete(String id);
}
