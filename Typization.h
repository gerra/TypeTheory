#ifndef TYPIZATION_H
#define TYPIZATION_H

#include "LambdaParser.h"
#include "TermParser.h"
#include "Unification.h"
#include <map>
#include <string>

using namespace std;

int varCount = 0;

string getNewVariable() {
    return "t" + to_string(varCount++);
}

void getTypes(Node *v, map<string, Term *> &types, vector<Equation> &equations) {
    if (typeid(*v) == typeid(Variable)) {
        Variable *vv = static_cast<Variable *>(v);
        if (types.find(vv->getAsString()) == types.end()) {
            types[vv->getAsString()] = new TermVariable(getNewVariable());
        }
    } else if (typeid(*v) == typeid(Apply)) {
        Apply *vv = static_cast<Apply *>(v);

        getTypes(vv->l, types, equations);
        getTypes(vv->r, types, equations);

        TermVariable *newVar = new TermVariable(getNewVariable());
        Function *arrow = new Function("f");
        arrow->addArg(types[vv->r->getAsString()]);
        arrow->addArg(newVar);

        types[vv->getAsString()] = newVar;
        equations.push_back(Equation(types[vv->l->getAsString()], arrow));

    } else if (typeid(*v) == typeid(Lambda)) {
        Lambda *vv = static_cast<Lambda *>(v);

        getTypes(vv->var, types, equations);
        getTypes(vv->v, types, equations);

        Function *arrow = new Function("f");
        arrow->addArg(types[vv->var->getAsString()]);
        arrow->addArg(types[vv->v->getAsString()]);
        // arrow = varType -> vType or f(varType, vType)

        types[vv->getAsString()] = arrow;

        TermVariable *newVar = new TermVariable(getNewVariable());
        equations.push_back(Equation(newVar, arrow));
    }
}

void simplify(Term *&v, map<string, Term *> &types, map<string, Term *> &shortTypes) {
    if (typeid(*v) == typeid(TermVariable)) {
        TermVariable *vv = static_cast<TermVariable *>(v);
        if (shortTypes.find(vv->name) != shortTypes.end()) {
            v = shortTypes[vv->name];
        }
    } else if (typeid(*v) == typeid(Function)) {
        Function *vv = static_cast<Function *>(v);
        for (auto arg : vv->args) {
            simplify(arg, types, shortTypes);
        }
    }
}

map<string, Term *> getTypes(Node *v) {
    map<string, Term *> types;
    vector<Equation> equations;
    getTypes(v, types, equations);
    map<string, Term *> shortTypes = solve(equations);
    for (auto &it : types) {
        simplify(it.second, types, shortTypes);
    }
    return types;
}

string toArrow(Term *v) {
    if (typeid(*v) == typeid(TermVariable)) {
        TermVariable *vv = static_cast<TermVariable *>(v);
        return vv->name;
    } else if (typeid(*v) == typeid(Function)) {
        Function *vv = static_cast<Function *>(v);
        return "(" + toArrow(vv->args[0]) + "->" + toArrow(vv->args[1]) + ")";
    }
}

#endif
