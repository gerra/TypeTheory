#include <iostream>
#include <string>
#include "../LambdaParser.h"
#include "../Normalization.h"

using namespace std;

int main() {
    string s;
    getline(cin, s);
    Node *v = parseStringToFormula(s);
    //cout << substituteNormalized(v, "y", parseStringToFormula("f x"))->getAsString() << "\n";
    cout << "\n\n";
    cout << normalize(v)->getAsString() << "\n";

    return 0;
}
