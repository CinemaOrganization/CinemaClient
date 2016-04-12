package cinema.client.web;

import org.springframework.stereotype.Controller;

@Controller
//@RequestMapping(value = "/comment")
public class CommentController {
//
//
//    CommentService service;
//
//    @Autowired
//    public CommentController(CommentService service) {
//        this.service = service;
//    }
//
//    @RequestMapping(value = "/comment", method = RequestMethod.GET)
//    public String showRegistrationForm(Model model) {
//        Comment comment = new Comment();
//        model.addAttribute(comment);
//        return "comment";
//    }
//
//    @RequestMapping(value = "/add", method = POST)
//    public String registerUserAccount(@ModelAttribute("comment") @Valid Comment comment,
//                                      BindingResult result, Model model) {
//        if (!result.hasErrors()) {
//            createComment(comment, result);
//        }
//        model.addAttribute(comment);
//        return "session";
//    }
//
//    private void createComment(Comment comment, BindingResult result) {
//        service.addNewComment(comment);
//
//    }
}
