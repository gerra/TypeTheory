#include <iostream>
#include <fstream>
#include <string>
#include "../LambdaParser.h"
#include "../Normalization.h"

using namespace std;

int main() {
    try {
        ifstream cin("task2.in");
        ofstream cout("task2.out");
        string s;
        getline(cin, s);
        Node *v = parseStringToFormula(s);
        for (const string &var : v->getEmptyVars()) {
            cout << var << "\n";
        }
    } catch (const char *e) {
        cerr << e << "\n";
    }
    return 0;
}
