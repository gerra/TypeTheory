#include <iostream>
#include <fstream>
#include <string>
#include "../LambdaParser.h"
#include "../Substitution.h"
#include "../Normalization.h"

using namespace std;


int main() {
    string s;
    getline(cin, s);
    s = getStringWithoutSpaces(s);
    Node *v = parseStringToFormula(s);
    cout << v->getAsString() << "\n";
    cout << normalize(v)->getAsString() << "\n";
    //normalize(v);
    return 0;
    /*
    // 3 HW:
    Node *expr, *sub;
    Variable *var;
    parseCondition(s, expr, var, sub);
    cout << substitute(expr, var->name, sub)->getAsString() << "\n";
    return 0;
    */

    s = getStringWithoutSpaces(s);
//    Node *v = parseStringToFormula(s);
    cout << v->getAsString() << "\n";
    cout << "Empty vars:\n";
    for (auto it : getEmptyVars(v)) {
        cout << it->name << "\n";
    }
    return 0;
}
