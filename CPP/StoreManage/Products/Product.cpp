#include "../InputInformation.cpp"
#include "../IOFILE.cpp"
#include <sstream>
#include <iomanip>
#include <vector>

class Product{
    public:
        //class attribute
        int product_id;
        std::string product_name;
        std::string product_color;
        std::string product_size;
        float product_price;
        int product_quantity;
        int product_brand_id;
        Brand brands;

        Product() = default;

        Product(const Product& other)
                :product_id(other.product_id), product_name(other.product_name), product_color(other.product_color),
                product_size(other.product_size), product_price(other.product_price), product_quantity(other.product_quantity),
                product_brand_id(other.product_brand_id){
        }
        //object IOFILE
        InputOutputFile input_output_file;
        //Name file
        const std::string name_file = "../DataFile/products.txt";
        std::fstream file;
        std::fstream fileBrand;


        void display_product(){
            //Open file brands.txt to read
            input_output_file.open_file(file, name_file, std::ios::in);
            //Check file opened successfully
            if(file.is_open()){
                std::cout << std::setw(10) << std::right << "Product id";
                std::cout << std::setw(20) << std::right << "Product name";
                std::cout << std::setw(15) << std::right << "Product size";
                std::cout << std::setw(15) << std::right << "Product color";
                std::cout << std::setw(15) << std::right << "Product price";
                std::cout << std::setw(20) << std::right << "Product quantity";
                std::cout << std::setw(17) << std::right << "Product brand" << std::endl;
                std::string line;
                //Read 1 line
                while(std::getline(file, line)){
                    std::stringstream temp(line);

                    std::string id;
                    std::string name;
                    std::string color;
                    std::string size;
                    std::string price;
                    std::string quantity;
                    std::string brand_id;
                    std::string brand_name;
                    //split line
                    std::getline(temp, id, ',');
                    std::getline(temp, name, ',');
                    std::getline(temp, color, ',');
                    std::getline(temp, size, ',');
                    std::getline(temp, price, ',');
                    std::getline(temp, quantity, ',');
                    std::getline(temp, brand_id);
                    input_output_file.open_file(fileBrand, "../DataFile/brands.txt", std::ios::in);
                    //Check file opened successfully
                    if(fileBrand.is_open()){
                        std::string line;
                        //Read 1 line
                        while(std::getline(fileBrand, line)){
                            std::stringstream temp(line);

                            std::string id;
                            std::string name;
                            //split line
                            std::getline(temp, id, ',');
                            std::getline(temp, name);
                            if(std::stoi(id) == std::stoi(brand_id)){
                                brand_name = name;
                            }
                        }
                    } else {
                        std::cout << "Can not open file" << std::endl;
                    }
                    //close brands.txt
                    input_output_file.close_file(fileBrand);
                    //Display
                    std::cout << std::setw(10) << std::right << id;
                    std::cout << std::setw(20) << std::right << name;
                    std::cout << std::setw(15) << std::right << color;
                    std::cout << std::setw(15) << std::right << size;
                    std::cout << std::setw(15) << std::right << std::fixed << std::setprecision(2) << std::stof(price);
                    std::cout << std::setw(20) << std::right << quantity;
                    std::cout << std::setw(17) << std::right << brand_name << std::endl;
                }
            } else {
                std::cout << "Can not open file" << std::endl;
            }
            input_output_file.close_file(file);
        }


        //function insert product
        void add_product(Input input){
            int count = 0;
            //insert product id
            std::cout << "Add product id: ";
            this->product_id = input.input_id();
            //insert product name
            std::cout << "Add product name: ";
            std::cin.ignore();
            this->product_name = input.input_detail();
            //insert product size
            std::cout << "Add product size: ";
            this->product_size = input.input_detail();
            //insert product color
            std::cout << "Add product color: ";
            this->product_color = input.input_detail();
            //insert product price
            std::cout << "Add product price: ";
            this->product_price = input.input_float_attr_val();
            //insert product quantity
            std::cout << "Add product quantity: ";
            this->product_quantity = input.input_id();
            //insert product brand_id
            std::cout << "Choose product brand id: ";
            //Get all brand
            brands.display_brand();
            std::cout << "Choose product brand id from brand list: " << std::endl;
            this->product_brand_id = input.input_id();
            input_output_file.open_file(file, "../DataFile/brands.txt", std::ios::in);
            if(file.is_open()){
                std::string line;
                //Read 1 line
                while(std::getline(file, line)){
                    std::stringstream temp(line);
                    std::string id;
                    //split line
                    std::getline(temp, id, ',');
                    if(this->product_brand_id == std::stoi(id)){
                        count++;
                    }
                }
            }
            input_output_file.close_file(file);

//            std::cout << "Add product size: ";
//            this->product_quantity = input.input_id();

            if(count != 0){
                //Open file products.txt
                input_output_file.open_file(file, name_file, std::ios::out | std::ios::app);
                //Write brand information to file
                file << this->product_id << "," << this->product_name << "," << this->product_size << "," << this->product_color << "," << this->product_price << "," << this->product_quantity << "," << this->product_brand_id << std::endl;
                //Close file
                input_output_file.close_file(file);
                std::cout << "Insert successfully" << std::endl;
            } else {
                std::cout << "Insert failed" << std::endl;
            }
        }

        void update_product(Input input){
            int count = 0;
            //Create vector to store product list
            std::vector<std::string> products;
            //call display_product
            std::cout << "List Products" << std::endl;
            display_product();
            //Choose product to update
            std::cout << "Select product to update: ";
            int findId = input.input_id();
            std::cin.ignore();

            //Opem file
            input_output_file.open_file(file, name_file, std::ios::in);
            if(file.is_open()){
                //declare line
                std::string line;
                //Read 1 line in file
                while(std::getline(file, line)){
                    //declare temp
                    std::stringstream temp(line);

                    std::string id;
                    std::string name;
                    std::string color;
                    std::string size;
                    std::string price;
                    std::string quantity;
                    std::string brand_id;
                    //split line
                    std::getline(temp, id, ',');
                    std::getline(temp, name, ',');
                    std::getline(temp, size, ',');
                    std::getline(temp, color, ',');
                    std::getline(temp, price, ',');
                    std::getline(temp, quantity, ',');
                    std::getline(temp, brand_id);
                    //Check if findId equals the product's id
                    if(std::stoi(id) == findId){

                        //Input updated product name
                        std::cout << "Replace name: ";
                        std::string replace_name = input.input_detail();
                        //find Position product name need to updated
                        size_t positionName = line.find(name);
                        //replace product name
                        line.replace(positionName, name.length() , replace_name);

                        //Input updated product size
                        std::cout << "Replace size: ";
                        std::string replace_size = input.input_detail();
                        //find Position product size need to updated
                        size_t positionSize = line.rfind(size);
                        //replace product size
                        line.replace(positionSize, size.length() , replace_size);

                        //Input updated product color
                        std::cout << "Replace color: ";
                        std::string replace_color = input.input_detail();
                        //find Position product color need to updated
                        size_t positionColor = line.rfind(color);
                        //replace product color
                        line.replace(positionColor, color.length() , replace_color);

                        //Input updated product quantity
                        std::cout << "Replace quantity: ";
                        std::string replace_quantity = std::to_string(input.input_id());
                        //find Position product quantity need to updated
                        size_t positionQuantity = line.rfind(quantity);
                        //replace product name
                        line.replace(positionQuantity, quantity.length() , replace_quantity);

                        //Input updated product price
                        std::cout << "Replace price: ";
                        std::string replace_price = std::to_string(input.input_float_attr_val());
                        //find Position product name need to updated
                        size_t positionPrice = line.rfind(price);
                        //replace product name
                        line.replace(positionPrice, price.length() , replace_price);

                        //Input updated product brand id
                        std::cout << "Choose product brand id from brand list: " << std::endl;
                        this->product_brand_id = input.input_id();
                        std::cout << "Replace brand id: ";
                        std::string replace_brand_id = std::to_string(input.input_id());
                        input_output_file.open_file(fileBrand, "../DataFile/brands.txt", std::ios::in);
                        if(fileBrand.is_open()){
                            std::string lineBrand;
                            //Read 1 line
                            while(std::getline(fileBrand, lineBrand)){
                                std::stringstream tempBrand(lineBrand);
                                std::string idBrand;
                                //split line
                                std::getline(tempBrand, idBrand, ',');
                                if(std::stoi(replace_brand_id) == std::stoi(idBrand)){
                                    count++;
                                }
                            }
                        }
                        input_output_file.close_file(fileBrand);

                        if(count != 0){
                            //find Position product brand_id need to updated
                            size_t positionBrandId = line.rfind(brand_id);
                            //replace product name
                            line.replace(positionBrandId, brand_id.length() , replace_brand_id);
                        }

                        //push line to vector brands
                        products.push_back(line);
                    }
                    else {
                        //push line to vector brands
                        products.push_back(line);
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
            for(const std::string product: products){
                file << product << std::endl;
            }
            std::cout << "Update successfully" << std::endl;
            //close file
            input_output_file.close_file(file);
//            std::cout << "Update Product" << std::endl;
        }

        void delete_product(Input input){
            //Create vector to store list products
            std::vector<std::string> products;
            //call display_product
            std::cout << "List Products" << std::endl;
            display_product();
            //Choose product to delete
            std::cout << "Select product to delete: ";
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
                        products.push_back(line);
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
            for(const std::string product: products){
                file << product << std::endl;
            }
            std::cout << "Delete successfully" << std::endl;
            //close file
            input_output_file.close_file(file);
        }
};