#ifndef INPUTINFORMATION_CPP
#define INPUTINFORMATION_CPP

//import library: iostream
#include <iostream>

//Declare class Input
class Input {
    public:
        //funtion to input variable has type int
        int inputTypeIntVal(){
            int variable;
            std::cin >> variable;
            return variable;
        }
        //funtion to input variable has type string
        std::string inputTypeStringVal(){
            std::string variable;
            getline(std::cin,variable);
            return variable;
        }
        //funtion to input variable has type float
        float inputTypeFloatVal(){
            float variable;
            std::cin >> variable;
            return variable;
        }

};
#endif