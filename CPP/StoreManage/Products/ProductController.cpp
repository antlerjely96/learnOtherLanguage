#include "Product.cpp"

class ProductController{
    public:
        //Opject input, product
        Input input;
        Product product;

        //function display menu
        void menu(){
            //Display menu
            std::cout << "1. Display product list" << std::endl;
            std::cout << "2. Add a product" << std::endl;
            std::cout << "3. Update a product" << std::endl;
            std::cout << "4. Delete a product" << std::endl;
            std::cout << "Select: ";
            //call function input_choice from object input
            int selected = input.input_choice();
            switch(selected){
                //if selected = 1
                case 1:
                    //call function display_brand
                    product.display_product();
                    break;
                    //if selected = 2
                case 2:
                    //call function add_brand
                    product.add_product(input);
                    break;
                    //if selected = 3
                case 3:
                    //call function update_brand
                    product.update_product(input);
                    break;
                    //if selected = 4
                case 4:
                    //call function delete_brand
                    product.delete_product(input);
                    break;
                    //if selected not in {1, 2, 3, 4}
                default:
                    std::cout << "Unknown choice" << std::endl;
                    break;
            }
        }
};