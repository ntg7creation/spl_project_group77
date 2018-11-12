cmake_minimum_required(VERSION 3.12)
project(spl_ex1)

set(CMAKE_CXX_STANDARD 11)

add_executable(spl_ex1 files/main.cpp files/Restaurant.h files/Table.h files/Customer.h files/Dish.h files/Action.h files/Customer.cpp files/Restaurant.cpp)