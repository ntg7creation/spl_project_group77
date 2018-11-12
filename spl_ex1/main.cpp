#include "Restaurant.h"
#include <iostream>

//testing git push pop test1
using namespace std;

Restaurant* backup = nullptr;

int main(int argc, char** argv){

    cout << "starting programe" << endl;
    if(argc!=2){
        cout << "usage: rest <config_path>" << endl;
        return 0;
    }

    string configurationFile = argv[1];
    cout << configurationFile << endl;//to delet
    Restaurant rest(configurationFile);
    rest.start();
    if(backup!=nullptr){
    	delete backup;
    	backup = nullptr;
    }
    return 0;
}