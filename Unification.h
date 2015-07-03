#ifndef UNIFICATION_H
#define UNIFICATION_H

#include <map>
#include <set>
#include "TermParser.h"
#include <typeinfo>

using namespace std;

struct UnifyException {
    string msg;
    UnifyException(const string &msg) : msg(msg) {}
};

void unify(Term *s, Term *t, map<string, Term *> &res) {
    if (typeid(*s) == typeid(Variable)) {
        Variable *ss = static_cast<Variable *>(s);
        s = res[ss->name];
    }
    if (typeid(*t) == typeid(Variable)) {
        Variable *tt = static_cast<Variable *>(t);
        t = res[tt->name];
    }
    if (typeid(*s) == typeid(Variable) && checkTermsAreEqual(s, t)) return;
    if (typeid(*s) == typeid(Function) && typeid(*t) == typeid(Function)) {
        Function *ss = static_cast<Function *>(s);
        Function *tt = static_cast<Function *>(t);
        if (checkTermsAreEqual(ss, tt)) {
            for (int i = 0; i < ss->args.size(); i++) {
                unify(ss->args[i], tt->args[i], res);
            }
        } else {
            throw UnifyException("There are no solution: "
                                    + ss->name + "(" + to_string(ss->args.size()) + " args) != "
                                    + tt->name + "(" + to_string(tt->args.size()) + " args)");
        }
    } else if (typeid(*s) != typeid(Variable)) {
        unify(t, s, res);
    } else {
        Variable *ss = static_cast<Variable *>(s);
        Function *tt = static_cast<Function *>(t);
        if (tt->variableOccurs(ss->name)) {
            throw UnifyException("There are no solution: variable "
                                    + ss->name + " occurs in " + tt->getAsString());
        } else {
            res[ss->name] = tt;
        }
    }
}

void makeGeneral(Term *&term, map<string, Term *> res) {
    if (typeid(*term) == typeid(Variable)) {
        Variable *var = static_cast<Variable *>(term);
        term = res[var->name];
    } else if (typeid(*term) == typeid(Function)) {
        Function *f = static_cast<Function *>(term);
        for (auto &arg : f->args) {
            makeGeneral(arg, res);
        }
    }
}

map<string, Term *> solve(vector<Equation> equations) {
    map<string, Term *> res;
    for (auto &equation : equations) {
        set<string> equationVariables = equation.getAllVariables();
        for (auto &var : equationVariables) {
            if (res.find(var) == res.end()) {
                res[var] = new Variable(var);
            }
        }
    }
    for (auto &equation : equations) {
        unify(equation.left, equation.right, res);
    }
    for (auto &it : res) {
        makeGeneral(it.second, res);
    }

    // delete id substitution (like "x = x")
    set<string> ids;
    for (auto &it : res) {
        if (typeid(*(it.second)) == typeid(Variable)) {
            Variable *var = static_cast<Variable *>(it.second);
            if (var->name == it.first) {
                ids.insert(it.first);
            }
        }
    }
    for (auto &it : ids) {
        res.erase(it);
    }
    return res;
}

#endif // UNIFICATION_H
