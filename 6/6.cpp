#include <iostream>
#include <fstream>
#include <string>
#include "../LambdaParser.h"
#include "../Typization.h"

using namespace std;

int main() {
    try {
        ifstream cin("task6.in");
        ofstream cout("task6.out");
        string s;
        getline(cin, s);
        Node *v = parseStringToFormula(s);
        map<string, Term *> types = getTypes(v);

        cout << toArrow(types[v->getAsString()]) << "\n\n";
        for (auto &it : v->getAllVars()) {
            cout << it << ": " << toArrow(types[it]) << "\n";
        }
        //cout << types[v->getAsString()]->getAsString() << "\n";
        //map<string, Term *> solution = solve(equations);
        //cout << types[v->getAsString()]->getAsString() << "\n\n";
        //for (auto &it : types) {
        //    cout << it.first << " = " << it.second->getAsString() << "\n";
        //}
        //cout << v->getAsString() << "\n";
    } catch (const char *e) {
        cout << e << "\n";
    }
    return 0;
}
