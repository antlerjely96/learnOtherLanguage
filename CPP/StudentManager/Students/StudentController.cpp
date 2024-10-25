#include "Student.cpp"

class StudentController{
    public:
        //object input, student
        Input input;
        Student student;

        //function display menu
        void menu(){
            //Display menu
            std::cout << "1. Display Student list" << std::endl;
            std::cout << "2. Add new Student" << std::endl;
            std::cout << "3. Update Student" << std::endl;
            std::cout << "4. Delete Student" << std::endl;
            std::cout << "5. Search Student by name" << std::endl;
            std::cout << "6. Sort Student by Average Mark Descending" << std::endl;
            std::cout << "7. Exit" << std::endl;
            int selection;
            do{
                std::cout << "Select in menu: ";
                //Call function to input selection
                selection = input.inputTypeIntVal();
                std::cout << std::endl;
                //check selection to call function in model
                switch(selection){
                    case 1:
                        //call function display students list
                        student.displayStudent();
                        break;
                    case 2:
                        //call function add a student
                        student.insertStudent(input);
                        break;
                    case 3:
                        //call function update students
                        student.updateStudent(input);
                        break;
                    case 4:
                        //call function delete students
                        student.removeStudent(input);
                        break;
                    case 5:
                        //call function search students
                        student.searchStudent(input);
                        break;
                    case 6:
                        //call function sort students
                        student.sortStudent();
                        break;
                    case 7:
                        //exit
                        break;
                    default:
                        //display error message
                        std::cout << "Unknown selection" << std::endl;
                        break;
                }
            } while (selection != 7);
        }
};