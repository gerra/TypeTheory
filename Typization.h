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

Term *getTypes(Node *v, map<string, Term *> &types, vector<Equation> &equations) {
    if (typeid(*v) == typeid(Variable)) {
        Variable *vv = static_cast<Variable *>(v);
        if (types.find(vv->getAsString()) == types.end()) {
            Term *curType = new TermVariable(getNewVariable());
            types[vv->getAsString()] = curType;
            equations.push_back(Equation(new TermVariable(vv->name), curType));
        }
    } else if (typeid(*v) == typeid(Apply)) {
        Apply *vv = static_cast<Apply *>(v);

        Term *leftType = getTypes(vv->l, types, equations);
        Term *rightType = getTypes(vv->r, types, equations);

        TermVariable *newVar = new TermVariable(getNewVariable());
        Function *arrow = new Function("f");
        //arrow->addArg(types[vv->r->getAsString()]);
        arrow->addArg(rightType);
        arrow->addArg(newVar);

        types[vv->getAsString()] = newVar;
        //equations.push_back(Equation(types[vv->l->getAsString()], arrow));
        equations.push_back(Equation(leftType, arrow));
    } else if (typeid(*v) == typeid(Lambda)) {
        Lambda *vv = static_cast<Lambda *>(v);

        Term *prev = NULL;
        if (types.find(vv->var->name) != types.end()) {
            prev = types[vv->var->name];
        }
        //getTypes(vv->var, types, equations);
        types[vv->var->name] = new TermVariable(getNewVariable());
        Term *vType = getTypes(vv->v, types, equations);

        Function *arrow = new Function("f");
        arrow->addArg(types[vv->var->getAsString()]);
        //arrow->addArg(types[vv->v->getAsString()]);
        arrow->addArg(vType);

        types[vv->getAsString()] = arrow;

        TermVariable *newVar = new TermVariable(getNewVariable());
        equations.push_back(Equation(newVar, arrow));

        if (prev) {
            types[vv->var->name] = prev;
        } else {
            types.erase(vv->var->name);
        }
    }
    return types[v->getAsString()];
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
    //for (auto &eq : equations) {
    //    cout << eq.left->getAsString() << " = " << eq.right->getAsString() << "\n";
    //}
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
