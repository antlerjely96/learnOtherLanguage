#include "../InputInformation.cpp"
#include "../IOFILE.cpp"
#include <sstream>
#include <iomanip>
#include <vector>

class Brand {
public:
    //Class attributes
    int brand_id;
    std::string brand_name;
    //object IOFILE
    InputOutputFile input_output_file;
    //Name file
    const std::string name_file = "../DataFile/brands.txt";
    std::fstream file;

    Brand() = default; // Hàm xây dựng mặc định

    Brand(const Brand& other) // Hàm xây dựng sao chép
            : brand_id(other.brand_id), brand_name(other.brand_name) {
    }

    //function display brand
    void display_brand() {
        //Open file brands.txt to read
        input_output_file.open_file(file, name_file, std::ios::in);
        //Check file opened successfully
        if(file.is_open()){
            std::cout << std::setw(10) << std::right << "Brand id";
            std::cout << std::setw(20) << std::right << "Brand name" << std::endl;
            std::string line;
            //Read 1 line
            while(std::getline(file, line)){
                std::stringstream temp(line);

                std::string id;
                std::string name;
                //split line
                std::getline(temp, id, ',');
                std::getline(temp, name);
                //Display
                std::cout << std::setw(10) << std::right << id;
                std::cout << std::setw(20) << std::right << name << std::endl;
            }
        } else {
            std::cout << "Can not open file" << std::endl;
        }
        input_output_file.close_file(file);
    }
    //function add brand
    void add_brand(Input input) {
        int count = 0;
        //insert brand id
        std::cout << "Add brand id: ";
        this->brand_id = input.input_id();
        //insert brand name
        std::cout << "Add brand name: ";
        std::cin.ignore();
        this->brand_name = input.input_detail();

        //Open file
        input_output_file.open_file(file, name_file, std::ios::in);
        //Check input id exists or not
        if(file.is_open()){
            std::string line;
            while(std::getline(file, line)){
                std::stringstream temp(line);
                std::string id;
                std::getline(temp, id, ',');
                if(this->brand_id == std::stoi(id)){
                    count++;
                }
            }
        }
        input_output_file.close_file(file);
        if(count == 0){
            input_output_file.open_file(file, name_file,  std::ios::out | std::ios::app);
            //Write brand information to file
            file << this->brand_id << "," << this->brand_name << std::endl;
            std::cout << "Insert successfully" << std::endl;
            //Close file
            input_output_file.close_file(file);
        } else {
            std::cout << "Insert failed because id already exist" << std::endl;
        }
    }

    //function update brand
    void update_brand(Input input) {
        //Create vector to store list brand
        std::vector<std::string> brands;
        //call display_brand
        std::cout << "List brands" << std::endl;
        display_brand();
        //Choose brand to update
        std::cout << "Select brand to update: ";
        int findId = input.input_id();
        std::cin.ignore();
        //Opem file
        input_output_file.open_file(file, name_file, std::ios::in);
        //Check file open success
        if(file.is_open()){
            //declare line
            std::string line;
            //Read 1 line in file
            while(std::getline(file, line)){
                //declare temp, id, name
                std::stringstream temp(line);
                std::string id;
                std::string name;
                //split line
                std::getline(temp, id, ',');
                std::getline(temp, name);
                //Check if findId equals the brand's id
                if(std::stoi(id) == findId){
                    //Input updated brand name
                    std::cout << "Replace name: ";
                    std::string replace_name = input.input_detail();
                    //find Position brand name need to updated
                    size_t position = line.find(name);
                    //replace brand name
                    line.replace(position, name.length() , replace_name);
                    //push line to vector brands
                    brands.push_back(line);
                }
                else {
                    //push line to vector brands
                    brands.push_back(line);
                }
            }
        }
        //clode file
        input_output_file.close_file(file);
        //open and clear file
        input_output_file.open_file(file, name_file, std::ios::in | std::ios::trunc);
        //close file
        input_output_file.close_file(file);
        //open file to input list brand after update
        input_output_file.open_file(file, name_file, std::ios::out);
        //insert to file
        for(const std::string brand: brands){
            file << brand << std::endl;
        }
        std::cout << "Update successfully" << std::endl;
        //close file
        input_output_file.close_file(file);
    }

    //function update brand
    void delete_brand(Input input) {
        //Create vector to store list brand
        std::vector<std::string> brands;
        //call display_brand
        std::cout << "List brands" << std::endl;
        display_brand();
        //Choose brand to update
        std::cout << "Select brand to delete: ";
        int findId = input.input_id();
        std::cin.ignore();
        //Opne file
        input_output_file.open_file(file, name_file, std::ios::in);
        //Check file open success
        if(file.is_open()){
            //declare line
            std::string line;
            //Read 1 line in file
            while(std::getline(file, line)){
                //declare temp, id, name
                std::stringstream temp(line);
                std::string id;
                //split line
                std::getline(temp, id, ',');
                //Check if findId equals the brand's id
                if(std::stoi(id) != findId){
                    //push line to vector brands
                    brands.push_back(line);
                }
            }
        }
        //clode file
        input_output_file.close_file(file);
        //open and clear file
        input_output_file.open_file(file, name_file, std::ios::in | std::ios::trunc);
        //close file
        input_output_file.close_file(file);
        //open file to input list brand after update
        input_output_file.open_file(file, name_file, std::ios::out);
        //insert to file
        for(const std::string brand: brands){
            file << brand << std::endl;
        }
        std::cout << "Delete successfully" << std::endl;
        //close file
        input_output_file.close_file(file);
    }
};