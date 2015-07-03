#include <iostream>
#include <fstream>
#include <string>
#include "../LambdaParser.h"
#include "../Normalization.h"

using namespace std;

int main() {
    try {
        ifstream cin("task4.in");
        ofstream cout("task4.out");
        string s;
        getline(cin, s);
        Node *v = parseStringToFormula(s);
        cout << normalize(v)->getAsString() << "\n";
    } catch (const char *e) {
        cerr << e << "\n";
    }
    return 0;
}
