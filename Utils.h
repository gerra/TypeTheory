#ifndef UTILS_H
#define UTILS_H

#include <string>
#include <cctype>

using namespace std;

string getStringWithoutSpaces(const string &s) {
    string res = "";
    unsigned int i = 0;
    while (i < s.length() && isspace(s[i])) i++;
    for (; i < s.length(); i++) {
        char c = s[i];
        if (!isspace(c) || !isspace(res[res.length() - 1])) {
            res += c;
        }
    }
    return res;
}

void skipSpaces(const string &s, int &pos) {
    while (pos < s.length() && isspace(s[pos])) pos++;
}

#endif
