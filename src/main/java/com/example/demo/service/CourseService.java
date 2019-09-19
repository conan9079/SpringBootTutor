package com.example.demo.service;


import com.example.demo.modal.Course;
import com.example.demo.modal.TwoSum;
import com.example.demo.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Component
public class CourseService {

    @Autowired
    CourseRepository courseRepository;

    public List<Course> findAllCourses(){

        return courseRepository.findAllClasses();
    }

    public List<Course> searchByCourseName(String input){

        return courseRepository.findCourseByName(input);
    }

    public List<Integer> getTwoSumIndex(Integer target) {

        return getTwoSumIndexFromList(target, Arrays.asList(courseRepository.getNumbers()));
    }

    public List<Integer> getTwoSumIndex(TwoSum twoSum) {

        if (twoSum == null || twoSum.getTarget() == null) {
            Integer []res = new Integer[2];
            return Arrays.asList(res);
        }
        return getTwoSumIndexFromList(twoSum.getTarget(), twoSum.getInputList());
    }

    private List<Integer> getTwoSumIndexFromList(Integer target, List<Integer> inputList) {
        Integer []res = new Integer[2];
        if(inputList == null || inputList.size() == 0){
            return Arrays.asList(res);
        }

        Map<Integer, Integer> valMap = new HashMap<>();

        for(int i = 0; i < inputList.size(); i++){
            if(valMap.keySet().contains(target - inputList.get(i))){
                res[0] = valMap.get(target - inputList.get(i));
                res[1] = i;
                return Arrays.asList(res);
            }
            valMap.put(inputList.get(i), i);
        }

        res[0] = -1;
        res[1] = -1;
        return Arrays.asList(res);
    }
}
