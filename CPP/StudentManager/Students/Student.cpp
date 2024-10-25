#include "../Input/InputInformation.cpp"
#include "../Input/InputOutputFile.cpp"
#include <sstream>
#include <iomanip>
#include <vector>
#include <fstream>

class Student{
    public:
        //student attributes
        //student id
        std::string studentId;
        //student name
        std::string studentName;
        //student birth year
        std::string studentBirthYear;
        //student gender
        std::string studentGender;
        //student address
        std::string studentAddress;
        //student class
        std::string studentClass;
        //student major
        std::string studentMajor;
        //student school year
        std::string studentSchoolYear;
        //student start year
        int studentStartYear;
        //student end year
        int studentEndYear;
        //student average mark
        float studentAverageMark;
        //student classification
        std::string studentClassification;

        //file txt information
        const std::string fileName = "../DataFile/students.txt";
        std::fstream file;
        InputOutputFile inputOutputFile;

        //const
        const int precision = 1;
        //constructor
        Student() = default;
        //function copy object
        Student(const Student& other):
            studentId(other.studentId), studentName(other.studentName),
            studentBirthYear(other.studentBirthYear), studentGender(other.studentGender),
            studentAddress(other.studentAddress), studentClass(other.studentClass),
            studentMajor(other.studentMajor), studentSchoolYear(other.studentSchoolYear),
            studentStartYear(other.studentStartYear), studentEndYear(other.studentEndYear),
            studentAverageMark(other.studentAverageMark), studentClassification(other.studentClassification){
        };

        //function display student list
        void displayStudent(){
            //Open file students.txt
            inputOutputFile.openFile(file, fileName, std::ios::in);
            //Check file opened successfully
            if(file.is_open()){
                std::cout << std::setw(10) << std::right << "Id";
                std::cout << " | " << std::setw(17) << std::left << "Name";
                std::cout << " | " << std::setw(10) << std::left << "Birth year";
                std::cout << " | " << std::setw(6) << std::left << "Gender";
                std::cout << " | " << std::setw(14) << std::left << "Address";
                std::cout << " | " << std::setw(8) << std::left << "Class";
                std::cout << " | " << std::setw(15) << std::left << "Major";
                std::cout << " | " << std::setw(10) << std::left << "School year";
                std::cout << " | " << std::setw(10) << std::left << "Start year";
                std::cout << " | " << std::setw(10) << std::left << "End year";
                std::cout << " | " << std::setw(4) << std::left << "Mark";
                std::cout << " | " << std::setw(10) << std::left << "Rating" << std::endl;
                std::string line;
                //Read 1 line
                while(std::getline(file, line)){
                    Student student = splitLine(line);
                    displayStudentInfor(student);
                }
            } else {
                std::cout << "Can not open file" << std::endl;
            }
            inputOutputFile.closeFile(file);
        }

        //function insert student
        void insertStudent(Input input){
            int count = 0;
            //insert student id
            std::cout << "Insert student id: ";
            std::cin.ignore();
            this->studentId = input.inputTypeStringVal();
            //insert student name
            std::cout << "Insert student name: ";
            this->studentName = input.inputTypeStringVal();
            //insert student birth year
            std::cout << "Insert student birth year: ";
            this->studentBirthYear = input.inputTypeStringVal();
            //insert student student gender
            std::cout << "Insert student gender: ";
            this->studentGender = input.inputTypeStringVal();
            //insert student address
            std::cout << "Insert student address: ";
            this->studentAddress = input.inputTypeStringVal();
            //insert student class
            std::cout << "Insert student class: ";
            this->studentClass = input.inputTypeStringVal();
            //insert student major
            std::cout << "Insert student major: ";
            this->studentMajor = input.inputTypeStringVal();
            //insert student school year
            std::cout << "Insert student school year: ";
            this->studentSchoolYear = input.inputTypeStringVal();
            //insert student start year
            std::cout << "Insert student start year: ";
            this->studentStartYear = input.inputTypeIntVal();
            //insert student end year
            std::cout << "Insert student end year: ";
            this->studentEndYear = input.inputTypeIntVal();
            do {
                //insert student average mark
                std::cout << "Insert student average mark: ";
                this->studentAverageMark = input.inputTypeFloatVal();
            } while (this->studentAverageMark < 0 || this->studentAverageMark > 10);
            //insert student classification
            if(this->studentAverageMark >= 0 && this->studentAverageMark < 5){
                this->studentClassification = "Yeu";
            } else if(this->studentAverageMark >= 5 && this->studentAverageMark < 6.5){
                this->studentClassification = "Trung Binh";
            } else if(this->studentAverageMark >= 6.5 && this->studentAverageMark < 8){
                this->studentClassification = "Kha";
            } else {
                this->studentClassification = "Gioi";
            }
            //Open file
            inputOutputFile.openFile(file, fileName, std::ios::in);
            //Check input id exists or not
            if(file.is_open()){
                std::string line;
                while(std::getline(file, line)){
                    std::stringstream temp(line);
                    std::string id;
                    std::getline(temp, id, ',');
                    if(this->studentId == id){
                        count++;
                    }
                }
            }
            inputOutputFile.closeFile(file);
            if(count == 0){
                inputOutputFile.openFile(file, fileName,  std::ios::out | std::ios::app);
                //Write student information to file
                file << this->studentId << "," << this->studentName << ","
                << this->studentBirthYear << "," << this->studentGender << ","
                << this->studentAddress << "," << this->studentClass << ","
                << this->studentMajor << "," << this->studentSchoolYear << ","
                << this->studentStartYear << "," << this->studentEndYear << ","
                << this->studentAverageMark << "," << this->studentClassification << std::endl;
                std::cout << "Insert successfully" << std::endl;
                //Close file
                inputOutputFile.closeFile(file);
            } else {
                std::cout << "Insert failed because id already exist" << std::endl;
            }
        }

        //function update student
        void updateStudent(Input input){
            //List student
            std::vector<std::string> students;
            //call display_product
            std::cout << "List students" << std::endl;
            displayStudent();
            //Choose student to update
            std::cout << "Select student to update: ";
            std::cin.ignore();
            std::string findId = input.inputTypeStringVal();
            //Opem file
            inputOutputFile.openFile(file, fileName, std::ios::in);
            //check can open file
            if(file.is_open()){
                //declare line
                std::string line;
                //Read line in file
                while (std::getline(file, line)){
                    Student student = splitLine(line);
                    //Check id student needs to be updated
                    if(student.studentId == findId){
                        //input update student name
                        std::cout << "Input update name: ";
                        std::string name = input.inputTypeStringVal();
                        updateStudentInformation(line, student.studentName, name);
                        //input update student birth year
                        std::cout << "Input update birth year: ";
                        std::string birthYear = input.inputTypeStringVal();
                        updateStudentInformation(line, student.studentBirthYear, birthYear);
                        //input update student gender
                        std::cout << "Input update gender: ";
                        std::string gender = input.inputTypeStringVal();
                        updateStudentInformation(line, student.studentGender, gender);
                        //input update student address
                        std::cout << "Input update address: ";
                        std::string address = input.inputTypeStringVal();
                        updateStudentInformation(line, student.studentAddress, address);
                        //input update student class
                        std::cout << "Input update class: ";
                        std::string classStudent = input.inputTypeStringVal();
                        updateStudentInformation(line, student.studentClass, classStudent);
                        //input update student major
                        std::cout << "Input update major: ";
                        std::string major = input.inputTypeStringVal();
                        updateStudentInformation(line, student.studentMajor, major);
                        //input update student school year
                        std::cout << "Input update school year: ";
                        std::string schoolYear = input.inputTypeStringVal();
                        updateStudentInformation(line, student.studentSchoolYear, schoolYear);
                        //input update student start year
                        std::cout << "Input update start year: ";
                        int startYear = input.inputTypeIntVal();
                        updateStudentInformation(line, std::to_string(student.studentStartYear), std::to_string(startYear));
                        //input update student end year
                        std::cout << "Input update end year: ";
                        int endYear = input.inputTypeIntVal();
                        updateStudentInformation(line, std::to_string(student.studentEndYear), std::to_string(endYear));
                        //input update student average mark
                        std::cout << "Input update average mark: ";
                        float tempMark;
                        do {
                            tempMark = input.inputTypeFloatVal();
                        } while (tempMark < 0 || tempMark > 10);
                        std::string updateMark = std::to_string(tempMark);
                        updateMark = updateMark.substr(0, updateMark.find('.') + precision + 1);
                        std::string averageMark = std::to_string(student.studentAverageMark);
                        averageMark = averageMark.substr(0, averageMark.find('.') + precision + 1);
                        updateStudentInformation(line, averageMark, updateMark);
                        //update student classification
                        std::string classification;
                        if(tempMark >= 0 && tempMark < 5){
                            classification = "Yeu";
                        } else if(tempMark >= 5 && tempMark < 6.5){
                            classification = "Trung Binh";
                        } else if(tempMark >= 6.5 && tempMark < 8){
                            classification = "Kha";
                        } else {
                            classification = "Gioi";
                        }
                        updateStudentInformation(line, student.studentClassification, classification);
                        //add student to list
                        students.push_back(line);
                    } else {
                        students.push_back(line);
                    }
                }
            }
            //clode file
            inputOutputFile.closeFile(file);
            //open and clear file
            inputOutputFile.openFile(file, fileName, std::ios::in | std::ios::trunc);
            //close file
            inputOutputFile.closeFile(file);
            //open file to input list student after update
            inputOutputFile.openFile(file, fileName, std::ios::out);
//            insert to file
            for(const std::string studentAfterUpdate: students){
                file << studentAfterUpdate << std::endl;
            }
            std::cout << "Update successfully" << std::endl;
            //close file
            inputOutputFile.closeFile(file);
        }

        //function remove student
        void removeStudent(Input input){
            //List student
            std::vector<std::string> students;
            //call display_product
            std::cout << "List students" << std::endl;
            displayStudent();
            //Choose student to delete
            std::cout << "Select student to remove: ";
            std::cin.ignore();
            std::string findId = input.inputTypeStringVal();
            inputOutputFile.openFile(file, fileName, std::ios::in);
            //Check file open success
            if(file.is_open()){
                //declare line
                std::string line;
                //Read 1 line in file
                while(std::getline(file, line)){
                    Student student = splitLine(line);
                    //Check if findId equals the studentId's id
                    if(student.studentId != findId){
                        //push line to vector students
                        students.push_back(line);
                    }
                }
            }
            //clode file
            inputOutputFile.closeFile(file);
            //open and clear file
            inputOutputFile.openFile(file, fileName, std::ios::in | std::ios::trunc);
            //close file
            inputOutputFile.closeFile(file);
            //open file to input list student after update
            inputOutputFile.openFile(file, fileName, std::ios::out);
            //insert to file
            for(const std::string student: students){
                file << student << std::endl;
            }
            std::cout << "Delete successfully" << std::endl;
            //close file
            inputOutputFile.closeFile(file);
        }

        //function search student
        void searchStudent(Input input){
            //input name to search
            std::cout << "Input name to search: ";
            std::cin.ignore();
            std::string searchName = input.inputTypeStringVal();
            std::cout << std::setw(10) << std::right << "Id";
            std::cout << " | " << std::setw(17) << std::left << "Name";
            std::cout << " | " << std::setw(10) << std::left << "Birth year";
            std::cout << " | " << std::setw(6) << std::left << "Gender";
            std::cout << " | " << std::setw(14) << std::left << "Address";
            std::cout << " | " << std::setw(8) << std::left << "Class";
            std::cout << " | " << std::setw(15) << std::left << "Major";
            std::cout << " | " << std::setw(10) << std::left << "School year";
            std::cout << " | " << std::setw(10) << std::left << "Start year";
            std::cout << " | " << std::setw(10) << std::left << "End year";
            std::cout << " | " << std::setw(4) << std::left << "Mark";
            std::cout << " | " << std::setw(10) << std::left << "Rating" << std::endl;
            inputOutputFile.openFile(file, fileName, std::ios::in);
            //Check file open success
            if(file.is_open()){
                //declare line
                std::string line;
                //Read 1 line in file
                while(std::getline(file, line)){
                    Student student = splitLine(line);
                    //Check if findName equals the student name
                    if(convertNameStudent(student.studentName, searchName) == true){
                        //push line to vector students
                        displayStudentInfor(student);
                    }
                }
            }
            inputOutputFile.closeFile(file);
        }

        //function sort students
        void sortStudent(){
            //declare temp student
            Student tempStudent;
            //Declare vector students
            std::vector<Student> students;
            std::cout << "List of students ordered by student mark descending!" << std::endl;
            std::cout << std::setw(10) << std::right << "Id";
            std::cout << " | " << std::setw(17) << std::left << "Name";
            std::cout << " | " << std::setw(10) << std::left << "Birth year";
            std::cout << " | " << std::setw(6) << std::left << "Gender";
            std::cout << " | " << std::setw(14) << std::left << "Address";
            std::cout << " | " << std::setw(8) << std::left << "Class";
            std::cout << " | " << std::setw(15) << std::left << "Major";
            std::cout << " | " << std::setw(10) << std::left << "School year";
            std::cout << " | " << std::setw(10) << std::left << "Start year";
            std::cout << " | " << std::setw(10) << std::left << "End year";
            std::cout << " | " << std::setw(4) << std::left << "Mark";
            std::cout << " | " << std::setw(10) << std::left << "Rating" << std::endl;
            //open file
            inputOutputFile.openFile(file, fileName, std::ios::in);
            if(file.is_open()){
                std::string line;
                while(std::getline(file, line)){
                    //Get information about a student
                    Student student = splitLine(line);
                    //put student to vector
                    students.push_back(student);
                }
                //close file
                inputOutputFile.openFile(file, fileName, std::ios::in);
            } else {
                std::cout << "Can not open file!" << std::endl;
            }
            //sort students by student mark descending
            for(int i = 0; i < students.size() - 1; i++) {
                for(int j = i + 1; j < students.size(); j++) {
                    if(students[i].studentAverageMark < students[j].studentAverageMark){
                        //tempStudent = students[i]
                        tempStudent.studentId = students[i].studentId;
                        tempStudent.studentName = students[i].studentName;
                        tempStudent.studentBirthYear = students[i].studentBirthYear;
                        tempStudent.studentGender = students[i].studentGender;
                        tempStudent.studentAddress = students[i].studentAddress;
                        tempStudent.studentClass = students[i].studentClass;
                        tempStudent.studentMajor = students[i].studentMajor;
                        tempStudent.studentSchoolYear = students[i].studentSchoolYear;
                        tempStudent.studentStartYear = students[i].studentStartYear;
                        tempStudent.studentEndYear = students[i].studentEndYear;
                        tempStudent.studentAverageMark = students[i].studentAverageMark;
                        tempStudent.studentClassification = students[i].studentClassification;
                        //students[i] = students[j]
                        students[i].studentId = students[j].studentId;
                        students[i].studentName = students[j].studentName;
                        students[i].studentBirthYear = students[j].studentBirthYear;
                        students[i].studentGender = students[j].studentGender;
                        students[i].studentAddress = students[j].studentAddress;
                        students[i].studentClass = students[j].studentClass;
                        students[i].studentMajor = students[j].studentMajor;
                        students[i].studentSchoolYear = students[j].studentSchoolYear;
                        students[i].studentStartYear = students[j].studentStartYear;
                        students[i].studentEndYear = students[j].studentEndYear;
                        students[i].studentAverageMark = students[j].studentAverageMark;
                        students[i].studentClassification = students[j].studentClassification;
                        //students[j] = tempStudent
                        students[j].studentId = tempStudent.studentId;
                        students[j].studentName = tempStudent.studentName;
                        students[j].studentBirthYear = tempStudent.studentBirthYear;
                        students[j].studentGender = tempStudent.studentGender;
                        students[j].studentAddress = tempStudent.studentAddress;
                        students[j].studentClass = tempStudent.studentClass;
                        students[j].studentMajor = tempStudent.studentMajor;
                        students[j].studentSchoolYear = tempStudent.studentSchoolYear;
                        students[j].studentStartYear = tempStudent.studentStartYear;
                        students[j].studentEndYear = tempStudent.studentEndYear;
                        students[j].studentAverageMark = tempStudent.studentAverageMark;
                        students[j].studentClassification = tempStudent.studentClassification;
                    }
                }
            }
            //Display the students after sorting
            for(const Student student : students){
                displayStudentInfor(student);
            }
        }

    private:
        //function convert name students to search
        bool convertNameStudent(const std::string& name, const std::string& findName){
            std::string copyName = name;
            std::string copyFindName = findName;
            //Convert to lower
            std::transform(copyName.begin(), copyName.end(), copyName.begin(), [](unsigned char c) {
                return std::tolower(c);
            });
            //Convert to lower
            std::transform(copyFindName.begin(), copyFindName.end(), copyFindName.begin(), [](unsigned char c) {
                return std::tolower(c);
            });
            if(copyName == copyFindName){
                return true;
            } else {
                return false;
            }
        }

        Student splitLine(const std::string& line){
            //Create student object
            Student student;
            std::stringstream temp(line);
            std::string id;
            std::string name;
            std::string birthYear;
            std::string gender;
            std::string address;
            std::string classStudent;
            std::string major;
            std::string schoolYear;
            std::string startYear;
            std::string endYear;
            std::string mark;
            std::string rating;
            //split line
            std::getline(temp, id, ',');
            std::getline(temp, name, ',');
            std::getline(temp, birthYear, ',');
            std::getline(temp, gender, ',');
            std::getline(temp, address, ',');
            std::getline(temp, classStudent, ',');
            std::getline(temp, major, ',');
            std::getline(temp, schoolYear, ',');
            std::getline(temp, startYear, ',');
            std::getline(temp, endYear, ',');
            std::getline(temp, mark, ',');
            std::getline(temp, rating);
            //
            student.studentId = id;
            student.studentName = name;
            student.studentBirthYear = birthYear;
            student.studentGender = gender;
            student.studentAddress = address;
            student.studentClass = classStudent;
            student.studentMajor = major;
            student.studentSchoolYear = schoolYear;
            student.studentStartYear = std::stoi(startYear);
            student.studentEndYear = std::stoi(endYear);
            student.studentAverageMark = std::stof(mark);
            student.studentClassification = rating;
            //
            return student;
        }
        //function display student information
        void displayStudentInfor(Student student){
            std::cout << std::setw(10) << std::right << student.studentId;
            std::cout << " | " << std::setw(17) << std::left << student.studentName;
            std::cout << " | " << std::setw(10) << std::left << student.studentBirthYear;
            std::cout << " | " << std::setw(6) << std::left << student.studentGender;
            std::cout << " | " << std::setw(14) << std::left << student.studentAddress;
            std::cout << " | " << std::setw(8) << std::left << student.studentClass;
            std::cout << " | " << std::setw(15) << std::left << student.studentMajor;
            std::cout << " | " << std::setw(11) << std::left << student.studentSchoolYear;
            std::cout << " | " << std::setw(10) << std::left << student.studentStartYear;
            std::cout << " | " << std::setw(10) << std::left << student.studentEndYear;
            std::cout << " | " << std::setw(4) << std::left <<std::fixed << std::setprecision(2) << student.studentAverageMark;
            std::cout << " | " << std::setw(10) << std::left << student.studentClassification << std::endl;
        }
        //function update information about student
        void updateStudentInformation(std::string& line, std::string findValue, std::string updateValue) {
            //find attribute want update
            size_t position = line.find(findValue);
            //replace attribute
            line.replace(position, findValue.length(), updateValue);
        }
};