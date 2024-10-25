#ifndef IOFILE_CPP
#define IOFILE_CPP

#include <iostream>
#include <fstream>

class InputOutputFile{
    public:
        void open_file(std::fstream& file, std::string filename, std::ios_base::openmode mode){
            file.open(filename, mode);
        }

        //Close txt file
        void close_file(std::fstream& file){
            file.close();
        }
};

#endif