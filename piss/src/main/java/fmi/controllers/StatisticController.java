package fmi.controllers;

import fmi.hibernate.model.StudentList;
import fmi.hibernate.model.User;
import fmi.hibernate.model.UserRole;
import fmi.repository.*;
import fmi.specification.UserSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specifications;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: dpavlov
 * Date: 14-1-6
 * Time: 11:08
 * To change this template use File | Settings | File Templates.
 */
@Controller
@RequestMapping("/statistic")
public class StatisticController {

    @Autowired
    ThesisCommitteeRepository thesisCommitteeRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    ThesisProposalRepository thesisProposalRepository;

    @Autowired
    StudentListRepository studentListRepository;

    @RequestMapping(value = "/head", method = RequestMethod.GET)
    public String getStatisticForHead(String startDate, String endDate, String type, String date, String tId, ModelMap model){
        model.put("headList", userRepository.findAll(Specifications.where(UserSpecification.archiveIs(new Boolean(false)))
                .and(UserSpecification.roleIs(UserRole.TEACHER))
                .and(UserSpecification.validatedIs(false))));
        if(type != null){
            SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
            Calendar c1 = Calendar.getInstance();
            Calendar c2 = Calendar.getInstance();
            if(type.equals("1")){
                try{
                    Date start = format.parse(startDate);
                    Date end = format.parse(endDate);

                    c1.setTime(end);
                    c1.add(Calendar.DATE, 1);

                    c2.setTime(start);
                    c2.add(Calendar.MINUTE, -1);
                    model.put("proposalList", thesisProposalRepository.findByTutorAndUserGraduatedOnBetween(userRepository.findOne(Long.valueOf(tId)), c2.getTime(), c1.getTime()));
                } catch (ParseException e){
                    e.printStackTrace();
                }
            } else if(type.equals("2")) {
                try{
                    Date start = format.parse(date);

                    c1.setTime(start);
                    c1.add(Calendar.DATE, 1);

                    c2.setTime(start);
                    c2.add(Calendar.MINUTE, -1);
                    model.put("proposalList", thesisProposalRepository.findByTutorAndUserGraduatedOnBetween(userRepository.findOne(Long.valueOf(tId)), c2.getTime(), c1.getTime()));
                } catch (ParseException e){
                    e.printStackTrace();
                }

            }
        }
        return "statistics/head";
    }

    @RequestMapping(value = "/teacher", method = RequestMethod.GET)
    public String getStatisticForTeacher(String startDate, String endDate, String type, String date, ModelMap model){
        if(type != null){
            SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
            Calendar c1 = Calendar.getInstance();
            Calendar c2 = Calendar.getInstance();
            if(type.equals("1")){
                try {
                    Date start = format.parse(startDate);
                    Date end = format.parse(endDate);

                    c1.setTime(end);
                    c1.add(Calendar.DATE, 1);

                    c2.setTime(start);
                    c2.add(Calendar.MINUTE, -1);

                    model.put("committeeList", thesisCommitteeRepository.findByDateBetween(c2.getTime(), c1.getTime()));
                } catch (ParseException e){
                    e.printStackTrace();
                }
            } else if(type.equals("2")){
                try {
                    Date start = format.parse(date);

                    c1.setTime(start);
                    c1.add(Calendar.DATE, 1);

                    c2.setTime(start);
                    c2.add(Calendar.MINUTE, -1);

                    model.put("committeeList", thesisCommitteeRepository.findByDateBetween(c2.getTime(), c1.getTime()));
                } catch (ParseException e){
                    e.printStackTrace();
                }
            }
        }
        return "statistics/teacher";
    }

    @RequestMapping(value = "/graduated", method = RequestMethod.GET)
    public String graduatedBetween(String startDate, String endDate, String type, String date, ModelMap model){

        if(type != null){
            SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
            Calendar c1 = Calendar.getInstance();
            Calendar c2 = Calendar.getInstance();
            if(type.equals("1")){
                try {
                    Date start = format.parse(startDate);
                    Date end = format.parse(endDate);

                    c1.setTime(end);
                    c1.add(Calendar.DATE, 1);

                    c2.setTime(start);
                    c2.add(Calendar.MINUTE, -1);

                    model.put("userList", userRepository.findByGraduatedTrueAndGraduatedOnBetween(c2.getTime(), c1.getTime()));
                } catch (ParseException e){
                    e.printStackTrace();
                }
            } else if(type.equals("2")){
                try {
                    Date start = format.parse(date);

                    c1.setTime(start);
                    c1.add(Calendar.DATE, 1);

                    c2.setTime(start);
                    c2.add(Calendar.MINUTE, -1);

                    model.put("userList", userRepository.findByGraduatedTrueAndGraduatedOnBetween(c2.getTime(), c1.getTime()));
                } catch (ParseException e){
                    e.printStackTrace();
                }
            }
        }
        return "statistics/graduated";
    }

    @RequestMapping(value = "/committee", method = RequestMethod.GET)
    public String finalStatisticICanNotFigureOutANameForThisMethod(String startDate, String endDate, String type, String date, String tId, ModelMap model){
        model.put("headList", userRepository.findAll(Specifications.where(UserSpecification.archiveIs(new Boolean(false)))
                .and(UserSpecification.roleIs(UserRole.TEACHER))
                .and(UserSpecification.validatedIs(false))));

        if(type != null){
            SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
            Calendar c1 = Calendar.getInstance();
            Calendar c2 = Calendar.getInstance();
            if(type.equals("1")){
                try {
                    Date start = format.parse(startDate);
                    Date end = format.parse(endDate);

                    c1.setTime(end);
                    c1.add(Calendar.DATE, 1);

                    c2.setTime(start);
                    c2.add(Calendar.MINUTE, -1);



                } catch (ParseException e){
                    e.printStackTrace();
                }
            } else if(type.equals("2")){
                try {
                    Date start = format.parse(date);

                    c1.setTime(start);
                    c1.add(Calendar.DATE, 1);

                    c2.setTime(start);
                    c2.add(Calendar.MINUTE, -1);


                } catch (ParseException e){
                    e.printStackTrace();
                }
            }

            List<User> returnList = new ArrayList<User>();
            User teacher = userRepository.findOne(Long.valueOf(tId));
            List<StudentList> studentLists = studentListRepository.findByUserGraduatedOnBetween(c2.getTime(), c1.getTime());
            for(StudentList s: studentLists){
                if(s.getCommittee().getCommittee().contains(teacher)){
                    returnList.add(s.getUser());
                }
            }
            model.put("userList", returnList);
        }



        return "statistics/committee";
    }

    @RequestMapping(value = "/phd", method = RequestMethod.GET)
    public String phdStatistic(String startDate, String endDate, ModelMap model){
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        Date start;
        Date end;
        try {

            start = format.parse(startDate);
            end = format.parse(endDate);

        } catch (ParseException e) {
        }


        return "statistics/phd";
    }

}
