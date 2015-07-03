#include <iostream>
#include <fstream>
#include <string>
#include "../LambdaParser.h"
#include "../Normalization.h"
#include "../Substitution.h"

using namespace std;

int main() {
    try {
        ifstream cin("task3.in");
        ofstream cout("task3.out");
        string s;
        getline(cin, s);

        Node *expr;
        Variable *var;
        Node *value;
        parseCondition(s, expr, var, value);
        Node *subst = substitute(expr, var->name, value);
        if (checkNodesAreEqual(expr, subst) && expr->hasVar(var->name)) {
            cout << "Нет свободы для подстановки переменной " << var->name << "\n";
        } else {
            cout << subst->getAsString() << "\n";
        }
    } catch (const char *e) {
        cerr << e << "\n";
    }
    return 0;
}
