package com.testdb.knexel.test.Controller;

import com.testdb.knexel.test.Entity.User;
import com.testdb.knexel.test.Rrepository.UserRepository;
import com.testdb.knexel.test.Services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Controller
public class CRUDController {

    @Autowired
    UserServices userServices;
    @Autowired
    UserRepository userRepository;
    @RequestMapping(value = "/accounts", method = RequestMethod.GET)
    public String getUser(Model model) {

        model.addAttribute("users", new User());
        List<User> users = userServices.findAll();
        model.addAttribute("accounts", users);

        return "users";
    }

    @RequestMapping(value = "/accounts", method = RequestMethod.POST)
    public String getUser( @ModelAttribute("user") User user, BindingResult bindingResult,
                           Model model) {




        String status=user.getOperationType();
        List<User> users=null;
        model.addAttribute("users", new User());

            //Deleting user by id form database
            if(status.equals("remove") ){
               userServices.deleteById(user.getId());
            }


            //Save and update user to database
            if (status.equals("save") ) {
                userServices.save(user);
            }


            List <User> userfor=null;
            //Search Block

        if (status.equals("search") ) {
         //   System.out.println(user.getBirthday().toString().isEmpty());

            Boolean isUserName=!user.getName().isEmpty();
            Boolean isUserLastName=!user.getLastName().isEmpty();
            Boolean isUserParent=!user.getParentname().isEmpty();
            Boolean isUserNumber=!user.getNumber().isEmpty();
            Boolean isUserAdress=!user.getAddress().isEmpty();



                //Search for ID
            if (user.getId() != null) {
                User userid;
                userid = userServices.getById(user.getId());
                model.addAttribute("accounts", userid);
                return "users";
                // end of search fot ID

            } else {


                List <User> userleve1=new ArrayList<>();
                if (isUserName){
                    users=userServices.getByName(user.getName());

                }//IsUsername

                    if (isUserLastName){
                        if (users==null)
                            users=userServices.getBySurname(user.getLastName());
                        for (User user1 : users ){
                            if( user1.getLastName().equals( user.getLastName() ) ){
                                userleve1.add(user1);
                            }
                        }
                        users.clear();
                        users=new ArrayList<>(userleve1);
                        userleve1.clear();
                    } //end of last name fillter

                    // start to fillter for Parent
                    if (isUserParent){
                        if (users==null)
                            users=userRepository.findAllByParentname(user.getParentname());
                            for (User user1 : users ){
                                if(user1.getParentname().equals( user.getParentname() ) ){
                                    userleve1.add(user1);
                                }
                            }
                        users.clear();
                        users=new ArrayList<>(userleve1);
                        userleve1.clear();
                }//end of Parent

                        //start birthday
                    if (user.getBirthday()!=null){
                        if (users==null)
                            users=userRepository.findAllByBirthday( user.getBirthday() );
                        for (User user1 : users ){
                            if( user1.getBirthday().toString().equals( user.getBirthday().toString() ) ){
                                userleve1.add(user1);
                            }
                        }
                        users.clear();
                        users=new ArrayList<>(userleve1);
                        userleve1.clear();
                    } //end of Birthday fillter

                    if (isUserNumber){
                        if (users==null)
                            users=userRepository.findAllByNumber(user.getNumber());
                        for (User user1 : users ){
                            if( user1.getNumber().equals( user.getNumber() ) ){
                                userleve1.add(user1);
                            }
                        }
                        users.clear();
                        users=new ArrayList<>(userleve1);
                        userleve1.clear();
                    } //end of number


                    if (isUserAdress){
                        if (users==null)
                            users=userRepository.findAllByAddress(user.getAddress());
                        for (User user1 : users ){
                            if( user1.getAddress().equals( user.getAddress() ) ){
                                userleve1.add(user1);
                            }
                        }
                        users.clear();
                        users=new ArrayList<>(userleve1);
                        userleve1.clear();
                    } //end of address fillter




                model.addAttribute("accounts", users);
                return "users";
        }


    }//go main page
        return "redirect:/accounts";
    }

    @RequestMapping(value="/edit", method=RequestMethod.POST)
    public String getEditAccount(@RequestParam("id") int id,
                                Model model) {

        User user =userServices.getById(id);
        model.addAttribute("users", user);
        List<User> users = userServices.findAll();
        model.addAttribute("accounts", users);

        return "users";

    }

    @RequestMapping(value="/delete", method=RequestMethod.POST)
    public String getRemoveAccount(@RequestParam("id") int id,Model model) {

         userServices.deleteById(id);
        List<User> users = userServices.findAll();
        model.addAttribute("accounts", users);

        return "redirect:/accounts";

    }

}
