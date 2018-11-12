#include <vector>
#include <string>
#include <fstream>
#include <sstream>
#include "Restaurant.h"
#include "Dish.h"
#include "Table.h"
#include "Action.h"

using namespace std;
//test
Restaurant::Restaurant() : open(false), tables(), menu(), actionsLog() {}

Restaurant::Restaurant(const string &configFilePath): open(false),tables(), menu(), actionsLog()  {

    cout<< "Restaurant load" << endl;
    ifstream infile(configFilePath);

    string line;
    int tablenumber =0;
    while (getline(infile, line)) {
        istringstream data(line);
        if (!line.compare("#number of tables\r")) {
            cout << line << endl;//delet later
            getline(infile, line);
            istringstream data(line);
            data >> tablenumber;
            cout << tablenumber << endl; // delet later
        }
        if (!line.compare("#tables description\r")) {
            vector<int> tablessize;
            string token = ",";
            cout << line << endl;//delet later
            getline(infile, line);
            istringstream data(line);
            string size;
            while (getline(data, size, ',')) {
                int temp = stoi(size);
                tablessize.push_back(temp);//get each table size here
            }
            for (auto i = tablessize.begin(); i != tablessize.end(); ++i) {
                cout << *i << " ";//init each table here
                //Table* newTable = new Table(*i);
            }
            cout << endl;
        }
        if(!line.compare("#Menu\r"))
        {
            cout<<line<<endl;// delet later
            int count = 0;
            while(getline(infile,line)) {
                DishType Etype;
                string name;
                string type;
                int cost;
                string info;
                istringstream data(line);
                getline(data, info, ',');
                name = info;
                getline(data, info, ',');
                type = info;
                if (!type.compare("VEG"))
                    Etype = DishType::VEG;
                if (!type.compare("SPC"))
                    Etype = DishType::SPC;
                if (!type.compare("BVG"))
                    Etype = DishType::BVG;
                if (!type.compare("ALC"))
                    Etype = DishType::ALC;
                getline(data, info, ',');
                cost = stoi(info);
                cout << name << "_" << Etype << "_" << cost << endl;//change to init dishs
                //Dish *newDish = new Dish(count, name, cost,Etype);
            }

        }
    }
    //deal with the file and split it into parts
}

void Restaurant::start() {
    open = true;
    cout << "Restaurant is now open!" << endl;
    //start action loop
    string input;
    string command;

    getline(cin, input);
    istringstream data(input);

    while (getline(data, command, ' ')) {
        cout << "you entered the word:" << command << endl;
    }
}

int Restaurant::getNumOfTables() const {
    return  tables.size();
}

Table *Restaurant::getTable(int ind) {
    return  tables[ind];
}

const vector<BaseAction *>& Restaurant::getActionsLog() const {
    return  actionsLog;
} // Return a reference to the history of actions

vector<Dish>& Restaurant::getMenu() {
    return  menu;

}