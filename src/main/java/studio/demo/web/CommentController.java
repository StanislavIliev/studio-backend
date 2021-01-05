package studio.demo.web;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import studio.demo.exception.*;
import studio.demo.model.binding.CommentAddBindingModel;
import studio.demo.model.entity.Comment;
import studio.demo.model.service.CommentServiceModel;
import studio.demo.model.view.CommentViewModel;
import studio.demo.service.CommentService;
import javax.validation.Valid;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/comments")
public class CommentController {

    ConcurrentMap<String , Comment> comments = new ConcurrentHashMap<>();

    private final ModelMapper modelMapper;
    private final CommentService commentService;

    public CommentController(ModelMapper modelMapper, CommentService commentService) {
        this.modelMapper = modelMapper;
        this.commentService = commentService;
    }


    @PostMapping(value = "/update", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CommentViewModel> update
            (@RequestBody CommentAddBindingModel comment) throws CommentWithThisNameDoesNotExist {

        CommentServiceModel updatedComment =
                this.commentService.update(this.modelMapper.map(comment, CommentAddBindingModel.class));

        return new ResponseEntity<CommentViewModel>(this.modelMapper.map(updatedComment, CommentViewModel.class),  HttpStatus.OK);

    }


    @GetMapping("/{id}")
    @ApiOperation(value = "Find comment by id",
            notes = "Provide id to look up for a specific comment",
            response = Comment.class
    )
    public  Comment getComment (@ApiParam(value = "Id value for the comment you need to retrieve"
            ,required = true)@PathVariable String id){
        return comments.get(id);
    }


    @PostMapping(value = "/add", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CommentViewModel> addComment
            (@RequestBody CommentAddBindingModel comment) throws
            CommentNullException, CommentWithThisTopicExist, UserNullException {

        CommentServiceModel ccc = this.commentService.addComment(this.modelMapper.map
                (comment, CommentServiceModel.class));

        CommentViewModel comView = this.modelMapper.map(ccc, CommentViewModel.class);

        return new ResponseEntity<>(comView, HttpStatus.OK);
    }


    @GetMapping("/details")
    public ModelAndView details(@RequestParam("id") String id, ModelAndView modelAndView) {
        modelAndView.addObject("comment", this.commentService.findById(id));
        modelAndView.setViewName("details-comment");
        return modelAndView;
    }


    @GetMapping(value = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<CommentViewModel>> allComments() {
        List<CommentViewModel> comments = this.commentService.findAllItems()
                .stream()
                .map(ccc -> this.modelMapper.map(ccc, CommentViewModel.class))
                .collect(Collectors.toList());
        return new ResponseEntity<>(comments, HttpStatus.OK);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Boolean> deleteComment(@Valid @RequestBody CommentAddBindingModel comment) throws CommentWithThisIdDoesNotExist {

        Comment c = this.modelMapper.map(comment,Comment.class);
        boolean isCommentDeleted = this.commentService.delete(c.getId());
        return new ResponseEntity<>(isCommentDeleted, HttpStatus.OK);
    }


}
