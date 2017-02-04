#include <iostream>
#include <fstream>
#include <string>
#include "../LambdaParser.h"
#include "../Typization.h"

using namespace std;

int main() {
    try {
        ifstream cin("task6.in");
       // ofstream cout("task6.out");
        string s;
        getline(cin, s);
        Node *v = parseStringToFormula(s);
        map<string, Term *> types = getTypes(v);

        cout << toArrow(types[v->getAsString()]) << "\n\n";
        for (auto &it : v->getAllVars()) {
            if (types.find(it) != types.end()) {
                cout << it << ": " << toArrow(types[it]) << "\n";
            }
        }
    } catch (const char *e) {
        cout << e << "\n";
    } catch (UnifyException &e) {
		cout << "Выражение не имеет типа\n";
		cerr << e.msg << "\n";
	}
    return 0;
}
