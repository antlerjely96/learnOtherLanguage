#ifndef INPUTINFORMATION_CPP
#define INPUTINFORMATION_CPP

#include <iostream>

class Input{
    public:
        //Input choice to excute menu
        int input_choice(){
            int choice;
            std::cin >> choice;
            return choice;
        }

        //Input attribute id
        int input_id(){
            int id;
            std::cin >> id;
            return id;
        }

        //Input other attribute
        std::string input_detail(){
            std::string detail;
            std::getline(std::cin, detail);
            return detail;
        }

        //input attr float value
        float input_float_attr_val(){
            float value;
            std::cin >> value;
            return value;
        }
};

#endif