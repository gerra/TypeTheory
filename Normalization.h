#ifndef NORMALIZATION_H
#define NORMALIZATION_H

#include "LambdaParser.h"
#include "Substitution.h"
#include <map>
#include <unordered_map>
#include <string>
#include <functional>

struct HashExpr {
    size_t operator()(const Node *v) const {
        return v->hash;
    }
};

struct EqualExpr {
    bool operator()(Node *v1, Node *v2) const {
        return checkNodesAreEqual(v1, v2);
    }
};

//unordered_map<Node *, Node *, decltype(&getHash), decltype(&checkNodesAreEqual)> cache(1000, getHash, checkNodesAreEqual);
unordered_map<Node *, Node *, HashExpr, EqualExpr> cache;

Node *normalize(Node *v) {
    if (cache.find(v) != cache.end()) {
        return cache[v];
    }
    if (v->getClass() == "Lambda") {
        Lambda *vv = static_cast<Lambda *>(v);
        cache[v] = new Lambda(vv->var, normalize(vv->v));
    } else if (v->getClass() == "Variable") {
        cache[v] = v;
    } else if (v->getClass() == "Apply") {
        Apply *vv = static_cast<Apply *>(v);
        Node *ln = normalize(vv->l);
        if (ln->getClass() == "Lambda") {
            Lambda *ll = static_cast<Lambda *>(ln);
            cache[v] = normalize(substitute(ll->v, ll->var->name, normalize(vv->r)));
        } else {
            cache[v] = new Apply(ln, normalize(vv->r));
        }
    }
    return cache[v];
}

#endif
