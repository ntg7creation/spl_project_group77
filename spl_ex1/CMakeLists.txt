cmake_minimum_required(VERSION 3.12)
project(spl_ex1)

set(CMAKE_CXX_STANDARD 11)

add_executable(spl_ex1 main.cpp Restaurant.h Table.h Customer.h Dish.h Action.h Customer.cpp Restaurant.cpp)