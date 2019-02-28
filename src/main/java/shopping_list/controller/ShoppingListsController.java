package shopping_list.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import shopping_list.dto.ShoppingListDto;
import shopping_list.model.Item;
import shopping_list.model.ShoppingList;
import shopping_list.model.User;
import shopping_list.security.MyUserPrincipal;
import shopping_list.service.ShoppingListService;
import shopping_list.service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
public class ShoppingListsController {

    private final UserService userService;
    private final ShoppingListService shoppingListService;


    @Autowired
    public ShoppingListsController(UserService userService, ShoppingListService shoppingListService) {
        this.userService = userService;
        this.shoppingListService = shoppingListService;
    }

    @CrossOrigin
    @GetMapping(value = "api/getLists")
    public List<ShoppingList> showList(HttpServletRequest request) {
        if (!(SecurityContextHolder.getContext().getAuthentication() instanceof AnonymousAuthenticationToken)) {
            MyUserPrincipal myUserPrincipal = (MyUserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            Optional<User> user = userService.findByUsername(myUserPrincipal.getUsername());
            if (user.isPresent()) {
                return shoppingListService.findItemsByUserId(user.get());
            }
        }
        // TODO better handling
        return new ArrayList(Arrays.asList(new ShoppingList()));
    }

    @CrossOrigin
    @PostMapping(value = "api/saveList")
    public ShoppingList saveList(@RequestBody List<Item> items) {
        if (SecurityContextHolder.getContext().getAuthentication().isAuthenticated()) {
            MyUserPrincipal myUserPrincipal = (MyUserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            Optional<User> user = userService.findByUsername(myUserPrincipal.getUsername());
            if (user.isPresent()) {
                return shoppingListService.createList(user.get(), items);
            }
        }
        return new ShoppingList();
    }

    @CrossOrigin
    @PostMapping(value = "api/updateList")
    public ShoppingList updateList(@RequestBody ShoppingListDto shoppingList) {
        if (SecurityContextHolder.getContext().getAuthentication().isAuthenticated()) {
            MyUserPrincipal myUserPrincipal = (MyUserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            Optional<User> user = userService.findByUsername(myUserPrincipal.getUsername());
            if (user.isPresent()) {
                return shoppingListService.updateList(shoppingList, user.get());
            }
        }
        return new ShoppingList();
    }

    @CrossOrigin
    @DeleteMapping(value = "api/deleteList")
    public ShoppingList deleteItem(@RequestBody ShoppingList list) {
        shoppingListService.removeList(list);
        return list;
    }

}
