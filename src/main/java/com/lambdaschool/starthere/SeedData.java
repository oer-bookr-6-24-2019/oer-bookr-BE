package com.lambdaschool.starthere;

import com.lambdaschool.starthere.models.*;
import com.lambdaschool.starthere.services.BookService;
import com.lambdaschool.starthere.services.ReviewService;
import com.lambdaschool.starthere.services.RoleService;
import com.lambdaschool.starthere.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;

@Transactional
@Component
public class SeedData implements CommandLineRunner
{
    @Autowired
    RoleService roleService;

    @Autowired
    UserService userService;

    @Autowired
    ReviewService reviewService;


    @Autowired
    BookService bookService;


    @Override
    public void run(String[] args) throws Exception
    {
        Role r1 = new Role("admin");
        Role r2 = new Role("user");
        Role r3 = new Role("data");

        roleService.save(r1);
        roleService.save(r2);
        roleService.save(r3);

        // admin, data & user
        ArrayList<UserRoles> admins = new ArrayList<>();
        admins.add(new UserRoles(new User(), r1));
        admins.add(new UserRoles(new User(), r2));
        admins.add(new UserRoles(new User(), r3));
        User u1 = new User("admin", "password", admins);
        userService.save(u1);

        // data & user
        ArrayList<UserRoles> datas = new ArrayList<>();
        datas.add(new UserRoles(new User(), r3));
        datas.add(new UserRoles(new User(), r2));
        User u2 = new User("datamanager", "password", datas);
        userService.save(u2);

        // user
        ArrayList<UserRoles> users = new ArrayList<>();
        users.add(new UserRoles(new User(), r2));
        User u3 = new User("user", "password!", users);
        User u4 = new User("bob", "password", users);

        userService.save(u3);
        userService.save(u4);

        users = new ArrayList<>();
        users.add(new UserRoles(new User(), r2));
        User u5 = new User("Jane", "password", users);
        userService.save(u5);

        Review review1 = new Review("This book rocks!", 5, "Gary Indiana", null);
        reviewService.save(review1);


        Book b1 = new Book("Flatterland", "Cool Publishing", "www.test.com", "images.googles.com/test", "general license", "Betsy Midler", new ArrayList<Review>(Arrays.asList(review1)) );
        Book b2 = new Book("Flat Earth Land", "Cooler Publishing", "www.test2.com", "images.googles.com/test2", "general licenses", "Betsy Midler the 2nd", null);

        review1.setBook(b1);
        bookService.save(b1);
        bookService.save(b2);
    }
}
