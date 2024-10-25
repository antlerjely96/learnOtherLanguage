#ifndef INPUTOUTPUTFILE_CPP
#define INPUTOUTPUTFILE_CPP

#include <iostream>
#include <fstream>

class InputOutputFile{
public:
    void openFile(std::fstream& file, std::string filename, std::ios_base::openmode mode){
        file.open(filename, mode);
    }

    //Close txt file
    void closeFile(std::fstream& file){
        file.close();
    }
};

#endif