#include <vector>
#include <string>
#include "Restaurant.h"
#include "Dish.h"
#include "Table.h"
#include "Action.h"

//test
Restaurant::Restaurant() : open(true), tables(), menu(), actionsLog() {}

Restaurant::Restaurant(const std::string &configFilePath) {}

void Restaurant::start() {
}

int Restaurant::getNumOfTables() const {}

Table *Restaurant::getTable(int ind) {}

const std::vector<BaseAction *> &Restaurant::getActionsLog() const {} // Return a reference to the history of actions

std::vector<Dish> &Restaurant::getMenu() {}