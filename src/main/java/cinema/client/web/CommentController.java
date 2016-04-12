package cinema.client.web;

import cinema.client.entity.Comment;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
@RequestMapping(value = "/comment")
public class CommentController {

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ModelAndView registerUserAccount(@ModelAttribute("comment") @Valid Comment comment,
                                            BindingResult result) {
        if (!result.hasErrors()) {
            createComment(comment, result);
        }

        return new ModelAndView("session?film_id="+comment.getFilm().getId(), "comment", comment);
    }

    private void createComment(Comment comment, BindingResult result) {
    }
}
