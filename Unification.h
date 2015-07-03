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


void makeGeneral(Term *&term, map<string, Term *> &res) {
    if (typeid(*term) == typeid(TermVariable)) {
        TermVariable *var = static_cast<TermVariable *>(term);
        term = res[var->name];
    } else if (typeid(*term) == typeid(Function)) {
        Function *f = static_cast<Function *>(term);
        for (auto &arg : f->args) {
            makeGeneral(arg, res);
        }
    }
}

void substituteInRes(map<string, Term *> &res) {
    for (auto &it : res) {
        makeGeneral(it.second, res);
    }
}

void unify(Term *s, Term *t, map<string, Term *> &res) {
    makeGeneral(s, res);
    makeGeneral(t, res);
    if (typeid(*s) == typeid(TermVariable)) {
        TermVariable *ss = static_cast<TermVariable *>(s);
        s = res[ss->name];
    }
    if (typeid(*t) == typeid(TermVariable)) {
        TermVariable *tt = static_cast<TermVariable *>(t);
        t = res[tt->name];
    }
    if (typeid(*s) == typeid(TermVariable) && checkTermsAreEqual(s, t)) return;
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
    } else if (typeid(*s) != typeid(TermVariable)) {
        unify(t, s, res);
    } else {
        TermVariable *ss = static_cast<TermVariable *>(s);
        if (t->TermVariableOccurs(ss->name)) {
            throw UnifyException("There are no solution: TermVariable "
                                    + ss->name + " occurs in " + t->getAsString());
        } else {
            res[ss->name] = t;
        }
    }
    substituteInRes(res);
}

map<string, Term *> solve(vector<Equation> equations) {
    map<string, Term *> res;
    for (auto &equation : equations) {
        set<string> equationTermVariables = equation.getAllTermVariables();
        for (auto &var : equationTermVariables) {
            if (res.find(var) == res.end()) {
                res[var] = new TermVariable(var);
            }
        }
    }
    for (auto &equation : equations) {
        unify(equation.left, equation.right, res);
    }
    substituteInRes(res);

    // delete id substitution (like "x = x")
    set<string> ids;
    for (auto &it : res) {
        if (typeid(*(it.second)) == typeid(TermVariable)) {
            TermVariable *var = static_cast<TermVariable *>(it.second);
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
