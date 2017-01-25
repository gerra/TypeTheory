#ifndef NORMALIZATION_H
#define NORMALIZATION_H

#include "LambdaParser.h"
#include "Substitution.h"
#include <map>
#include <unordered_map>
#include <string>
#include <functional>
#include <typeinfo>

struct HashExpr {
    size_t operator()(Node *v) const {
        return v->getHash();
    }
};

struct EqualExpr {
    bool operator()(Node *v1, Node *v2) const {
        return checkNodesAreEqual(v1, v2);
    }
};

unordered_map<Node *, Node *, HashExpr, EqualExpr> cache;
unordered_map<Node *, Node *, HashExpr, EqualExpr> cache_soft;

Node *substituteNormalized(Node *v, const string &var, Node *value) {
    if (typeid(*v) == typeid(Variable)) {
        Variable *vv = static_cast<Variable *>(v);
        if (var == vv->name) {
            return value;
        }
        return vv;
    } else if (typeid(*v) == typeid(Apply)) {
        Apply *vv = static_cast<Apply *>(v);

        Node *newL = substituteNormalized(vv->l, var, value);
        Node *newR = substituteNormalized(vv->r, var, value);

        return new Apply(newL, newR);
    } else if (typeid(*v) == typeid(Lambda)) {
        Lambda *vv = static_cast<Lambda *>(v);
        set<string> lambdaEmptyVars = vv->v->getEmptyVars();
        if (vv->var->name == var || lambdaEmptyVars.find(var) == lambdaEmptyVars.end()) {
            return vv;
        }

        set<string> valueEmptyVars = value->getEmptyVars();
        if (valueEmptyVars.find(vv->var->name) == valueEmptyVars.end()) {
            return new Lambda(vv->var, substituteNormalized(vv->v, var, value));
        }

        set<string> allVars(valueEmptyVars);
        allVars.insert(lambdaEmptyVars.begin(), lambdaEmptyVars.end());

        string newVar = "a";
        while (allVars.find(newVar) != allVars.end()) {
            newVar += "'";
        }
        Node *newV = substituteNormalized(vv->v, var, value);
        return new Lambda(new Variable(newVar), newV);
    }
}

Node *softNormalize(Node *v) {
    if (!v) return NULL;
    if (cache_soft.find(v) != cache_soft.end()) {
//        cout << ">> " << v->getAsString() << " = " << cache_soft[v]->getAsString() << "\n";
        return cache_soft[v];
    }
   // cache_soft[v] = v;
    if (typeid(*v) == typeid(Lambda)) {
        Lambda *vv = static_cast<Lambda *>(v);
        cache_soft[v] = new Lambda(vv->var, softNormalize(vv->v));
    } else if (typeid(*v) == typeid(Variable)) {
        cache_soft[v] = v;
    } else if (typeid(*v) == typeid(Apply)) {
        Apply *vv = static_cast<Apply *>(v);
        Node *ln = softNormalize(vv->l);
        if (typeid(*ln) == typeid(Lambda)) {
            Lambda *ll = static_cast<Lambda *>(ln);
            cache_soft[v] = softNormalize(substituteNormalized(ll->v, ll->var->name, vv->r));
        } else {
            cache_soft[v] = new Apply(ln, vv->r);
        }
    }
//    cout << ">> " << v->getAsString() << " = " << cache_soft[v]->getAsString() << "\n";
    return cache_soft[v];
}

Node *normalize(Node *v) {
    if (!v) return NULL;
    if (cache.find(v) != cache.end()) {
       // cout << v->getAsString() << " = " << cache[v]->getAsString() << "\n";
        return cache[v];
    }
   // cache[v] = v;
    if (typeid(*v) == typeid(Lambda)) {
        Lambda *vv = static_cast<Lambda *>(v);
        cache[v] = new Lambda(vv->var, normalize(vv->v));
    } else if (typeid(*v) == typeid(Variable)) {
        cache[v] = v;
    } else if (typeid(*v) == typeid(Apply)) {
        Apply *vv = static_cast<Apply *>(v);
        Node *ln = softNormalize(vv->l);
        if (typeid(*ln) == typeid(Lambda)) {
            Lambda *ll = static_cast<Lambda *>(ln);
            cache[v] = normalize(substituteNormalized(ll->v, ll->var->name, vv->r));
        } else {
            cache[v] = new Apply(normalize(ln), normalize(vv->r));
        }
    }
  //  cout << v->getAsString() << " = " << cache[v]->getAsString() << "\n";
    return cache[v];
}

#endif
