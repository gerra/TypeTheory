#ifndef TERM_PARSER_H
#define TERM_PARSER_H

#include <vector>
#include <string>
#include <set>
#include "Utils.h"
#include <typeinfo>

using namespace std;

struct Term {
private:
    bool allVariablesWereComputed = false;
    set<string> allVariables;
public:
    string name;

    virtual string getAsString() = 0;
    virtual set<string> _getAllVariables() = 0;
    virtual bool variableOccurs(const string &name) = 0;

    set<string> getAllVariables() {
        if (!allVariablesWereComputed) {
            allVariablesWereComputed = true;
            allVariables = _getAllVariables();
        }
        return allVariables;
    }
};

struct Function : Term {
    vector<Term *> args;

    Function(const string &s) {
        name = s;
    }

    void addArg(Term *arg) {
        args.push_back(arg);
    }

    string getAsString() {
        string res = "";
        res += name;
        res += "(";
        for (int i = 0; i < args.size(); i++) {
            res += args[i]->getAsString();
            if (i != args.size() - 1) res += ", ";
        }
        res += ")";
        return res;
    }

    set<string> _getAllVariables() {
        set<string> res;
        for (auto arg : args) {
            set<string> argVariables = arg->getAllVariables();
            res.insert(argVariables.begin(), argVariables.end());
        }
        return res;
    }

    bool variableOccurs(const string &name) {
        for (auto arg : args) {
            if (arg->variableOccurs(name)) return true;
        }
        return false;
    }
};

struct Variable : Term {
    Variable(const string &s) {
        name = s;
    }

    string getAsString() {
        return name;
    }

    set<string> _getAllVariables() {
        set<string> res;
        res.insert(name);
        return res;
    }

    bool variableOccurs(const string &name) {
        return name == this->name;
    }
};

struct Equation {
    Term *left, *right;

    Equation(Term *left, Term *right) : left(left), right(right) {}

    string getAsString() {
        return left->getAsString() + " = " + right->getAsString();
    }

    set<string> getAllVariables() {
        set<string> res = left->getAllVariables();
        set<string> rr = right->getAllVariables();
        res.insert(rr.begin(), rr.end());
        return res;
    }
};

Term *parseTerm(const string &s, int &pos) {
    skipSpaces(s, pos);
    string termName = "";
    while (pos < s.length() && islower(s[pos]) || isdigit(s[pos]) || s[pos] == '\'') {
        termName += s[pos++];
    }
    if (termName[0] <= 'h') {
        skipSpaces(s, pos);
        if (s[pos] != '(') {
            string err = "";
            err.append("Bad format, \'(\' expected (at postion ")
                .append(to_string(pos))
                .append(")");
            throw err.c_str();
        }

        Function *f = new Function(termName);
        for (; pos < s.length();) {
            pos++;
            skipSpaces(s, pos);
            Term *arg = parseTerm(s, pos);
            f->addArg(arg);
            skipSpaces(s, pos);
            if (s[pos] == ',') {
                skipSpaces(s, pos);
                continue;
            } else if (s[pos] == ')') {
                pos++;
                break;
            }
        }
        return f;
    } else {
        return new Variable(termName);
    }
}

Term *parseTerm(const string &s) {
    int pos = 0;
    return parseTerm(s, pos);
}

Equation parseEquation(const string &s) {
    for (int i = 0; i < s.length(); i++) {
        if (s[i] == '=') {
            return Equation(parseTerm(s.substr(0, i)),
                            parseTerm(s.substr(i + 1)));
        }
    }
}

bool checkTermsAreEqual(Term *a, Term *b) {
    if (!a && !b) return true;
    if (!a || !b) return false;
    if (a == b) return true;
    if (typeid(*a) != typeid(*b)) return false;
    if (typeid(*a) == typeid(Variable)) {
        Variable *aa = static_cast<Variable *>(a);
        Variable *bb = static_cast<Variable *>(b);
        return aa->name == bb->name;
    } else if (typeid(*a) == typeid(Function)) {
        Function *aa = static_cast<Function *>(a);
        Function *bb = static_cast<Function *>(b);
        if (aa->args.size() != bb->args.size()) return false;
        if (aa->name != bb->name) return false;
        //for (int i = 0; i < aa->args.size(); i++) {
        //    if (!checkTermsAreEqual(aa->args[i], bb->args[i])) return false;
        //}
        return true;
    }
}

#endif // TERM_PARSER_H
