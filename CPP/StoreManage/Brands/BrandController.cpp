#include "Brand.cpp"

class BrandController{
    public:
        //object input, brand
        Input input;
        Brand brand;
        //function display menu
        void menu() {
            //Display menu
            std::cout << "1. Display list brand" << std::endl;
            std::cout << "2. Add a brand" << std::endl;
            std::cout << "3. Update a brand" << std::endl;
            std::cout << "4. Delete a brand" << std::endl;
            std::cout << "Select: ";
            //call function input_choice from object input
            int selected = input.input_choice();
            //check selected to call function
            switch(selected){
                //if selected = 1
                case 1:
                    //call function display_brand
                    brand.display_brand();
                    break;
                    //if selected = 2
                case 2:
                    //call function add_brand
                    brand.add_brand(input);
                    break;
                    //if selected = 3
                case 3:
                    //call function update_brand
                    brand.update_brand(input);
                    break;
                    //if selected = 4
                case 4:
                    //call function delete_brand
                    brand.delete_brand(input);
                    break;
                    //if selected not in {1, 2, 3, 4}
                default:
                    std::cout << "Unknown choice" << std::endl;
                    break;
            }
        }
};