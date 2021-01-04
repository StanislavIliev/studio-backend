package studio.demo.service.impl;


import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import studio.demo.exception.CommentNullException;
import studio.demo.exception.CommentWithThisNameDoesNotExist;
import studio.demo.exception.CommentWithThisTopicExist;
import studio.demo.exception.UserNullException;
import studio.demo.model.entity.Comment;
import studio.demo.model.entity.User;
import studio.demo.model.service.CommentServiceModel;
import studio.demo.model.view.CommentViewModel;
import studio.demo.repository.CommentRepository;
import studio.demo.repository.UserRepository;
import studio.demo.service.CommentService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService {
    private final CommentRepository commentRepository;
    private final ModelMapper modelMapper;
    private  final UserRepository userRepository;

    public CommentServiceImpl(CommentRepository commentRepository, ModelMapper modelMapper, UserRepository userRepository) {
        this.commentRepository = commentRepository;
        this.modelMapper = modelMapper;
        this.userRepository = userRepository;
    }

    @Override
    public CommentServiceModel addComment(CommentServiceModel commentServiceModel) throws CommentNullException, CommentWithThisTopicExist, UserNullException {

        String username = commentServiceModel.getUser().getUsername();
        User user = this.userRepository.findByUsername(username).orElse(null);
        if(user == null){
            throw new UserNullException("User is null");
        }
        user.setUsername(commentServiceModel.getUser().getUsername());
        user.setEmail(commentServiceModel.getUser().getEmail());
        user.setPhoneNumber(commentServiceModel.getUser().getPhoneNumber());

        this.userRepository.saveAndFlush(user);

        if (commentServiceModel.getTopic().trim().isEmpty()) {
            throw new CommentNullException("Comment is null");
        }
        if (commentRepository.findById(commentServiceModel.getTopic()).isPresent()) {
            throw new CommentWithThisTopicExist("Comment with this topic exists!");
        }
        Comment comment = this.modelMapper.map(commentServiceModel, Comment.class);
        comment.setUser(user);
        this.commentRepository.saveAndFlush(comment);


        return commentServiceModel;
    }

    @Override
    public List<CommentViewModel> findAllItems() {
        return this.commentRepository.findAll().stream()
                .map(comment -> {
                    CommentViewModel commentViewModel = this.modelMapper
                            .map(comment, CommentViewModel.class);
                    commentViewModel.setImgUrl(String.format("/img/%s-%s.jpg"
                            , comment.getTopic(), comment.getDescription()));
                    return commentViewModel;
                }).collect(Collectors.toList());
    }

    @Override
    public CommentViewModel findById(String id) {
        return this.commentRepository.findById(id)
                .map(comment -> {
                    CommentViewModel commentViewModel = this.modelMapper
                            .map(comment, CommentViewModel.class);
                    commentViewModel.setImgUrl(String.format("/img/%s-%s.jpg"
                            , comment.getTopic(), comment.getDescription()));
                    return commentViewModel;
                }).orElse(null);
    }

    @Override
    public boolean delete(String id) {
        if (this.commentRepository.findById(id).isEmpty()) {
            return false;
        }
        this.commentRepository.deleteById(id);
        return true;
    }

    @Override
    public CommentServiceModel update(CommentViewModel comment) throws CommentWithThisNameDoesNotExist {

        Comment c = this.commentRepository.
                findById(comment.getId()).orElse(null);
        this.checkCommentExist(c);
        if (!comment.getDescription().trim().isEmpty()) {
            c.setDescription(comment.getDescription());
        }
        if (!comment.getTopic().trim().isEmpty()) {
            c.setTopic(comment.getTopic());
        }

        return this.modelMapper.map(this.commentRepository.saveAndFlush(c),
                CommentServiceModel.class);

    }

    private void checkCommentExist(Comment c) throws CommentWithThisNameDoesNotExist {
        if (c == null) {
            throw new CommentWithThisNameDoesNotExist("Comment with this name does not exist!");
        }
    }

}
