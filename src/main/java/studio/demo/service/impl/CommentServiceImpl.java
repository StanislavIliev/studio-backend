package studio.demo.service.impl;


import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import studio.demo.model.entity.Comment;
import studio.demo.model.service.CommentServiceModel;
import studio.demo.model.view.CommentViewModel;
import studio.demo.repository.CommentRepository;
import studio.demo.service.CommentService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService {
    private final CommentRepository commentRepository;
    private final ModelMapper modelMapper;

    public CommentServiceImpl(CommentRepository commentRepository, ModelMapper modelMapper) {
        this.commentRepository = commentRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void addComment(CommentServiceModel commentServiceModel) {
        Comment comment = this.modelMapper.map(commentServiceModel, Comment.class);
        this.commentRepository.saveAndFlush(comment);
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
    public void delete(String id) {
        this.commentRepository.deleteById(id);
    }
}
