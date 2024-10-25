cmake_minimum_required(VERSION 3.23)
project(StudentManager)

set(CMAKE_CXX_STANDARD 23)

add_executable(StudentManager main.cpp)
set(CMAKE_EXE_LINKER_FLAGS "-static")
