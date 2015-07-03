#include <iostream>
#include <fstream>
#include <map>
#include <string>
#include <set>
#include "../TermParser.h"
#include "../Unification.h"

using namespace std;

int main() {
    string s = "x";
    string t = "z";
    try {
        string s;
        vector<Equation> equations;
        while (getline(cin, s)) {
            equations.push_back(parseEquation(s));
        }

        try {
            map<string, Term *> solution = solve(equations);
            for (auto &it : solution) {
                cout << it.first << " = " << it.second->getAsString() << "\n";
            }
        } catch (const char *e) {
            cerr << e << "\n";
        } catch (UnifyException &e) {
            cerr << e.msg << "\n";
        }
    } catch (char const *c) {
        cout << c << "\n";
    }
    return 0;
}
