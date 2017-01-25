#include <iostream>
#include <fstream>
#include <string>
#include "../LambdaParser.h"

using namespace std;

int main() {
    try {
        ifstream cin("task1.in");
        ofstream cout("task1.out");
        string s;
        getline(cin, s);
        Node *v = parseStringToFormula(s);
        cout << v->getAsString() << "\n";
    } catch (const char *e) {
        cout << e << "\n";
    }
    return 0;
}
