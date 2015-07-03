#ifndef LAMBDA_PARSER_H
#define LAMBDA_PARSER_H

#include <string>
#include <cctype>
#include <map>
#include <set>
#include "Utils.h"
#include <typeinfo>
#include <functional>

using namespace std;

struct Node {
private:
    size_t nodeHash = 0;
    bool hashWasComputed = false;

    set<string> emptyVars;
    bool emptyVarsWereComputed = false;
public:


    virtual void print() {
        cout << getAsString();
    }
    virtual string getAsString() = 0;
    virtual bool isAtom() {
        return false;
    }
    virtual set<string> _getEmptyVars() = 0;

    set<string> getEmptyVars() {
        if (!emptyVarsWereComputed) {
            emptyVarsWereComputed = true;
            emptyVars =     _getEmptyVars();
        }
        return emptyVars;
    }

    size_t getHash() {
        if (!hashWasComputed) {
            hashWasComputed = true;
            nodeHash = hash<string>()(getAsString());
        }
        return nodeHash;
    }
};

struct Variable : Node {
    string name;
    Variable(const string &name) : name(name) {}

    string getAsString() {
        return name;
    }

    bool isAtom() {
        return true;
    }

    set<string> _getEmptyVars() {
        set<string> res;
        res.insert(name);
        return res;
    }
};

struct Lambda : Node {
    Variable *var;
    Node *v;
    Lambda() : var(NULL), v(NULL) {}
    Lambda(Variable *var, Node *v) : var(var), v(v) {}

    string getAsString() {
        string res = "";
        res = "\\";
        res += var->getAsString();
        res += " . ";
        bool isAtom = v->isAtom();
        if (!isAtom) res += "(";
        res += v->getAsString();
        if (!isAtom) res += ")";
        return res;
    }

    set<string> _getEmptyVars() {
        set<string> res = v->getEmptyVars();
        res.erase(var->name);
        return res;
    }
};

struct Apply : Node {
    Node *l, *r;
    Apply() : l(NULL), r(NULL) {}
    Apply(Node *l, Node *r) : l(l), r(r) {}

    string getAsString() {
        string res = "";
        if (l) {
            bool isAtom = l->isAtom();
            if (!isAtom) res += "(";
            res += l->getAsString();
            if (!isAtom) res += ")";
            if (r) res += " ";
        }
        if (r) {
            bool isAtom = r->isAtom();
            if (!isAtom) res += "(";
            res += r->getAsString();
            if (!isAtom) res += ")";
        }
        return res;
    }

    bool isAtom() {
        if (l->isAtom() && !r) return true;
        return false;
    }

    set<string> _getEmptyVars() {
        set<string> ll = l->getEmptyVars();
        set<string> res = r->getEmptyVars();
        res.insert(ll.begin(), ll.end());
        return res;
    }
};

Node *parseExpression(const string &s, int &pos);
Node *parseApply(const string &s, int &pos);
Node *parseAtom(const string &s, int &pos);
Variable *parseVariable(const string &s, int &pos);

Node *parseExpression(const string &s, int &pos) {
    skipSpaces(s, pos);

    Apply *apply = NULL;

    Node *v = NULL;
    if (s[pos] != '\\') {
        v = parseApply(s, pos);
        skipSpaces(s, pos);
    }
    if (pos == s.length() || s[pos] != '\\') {
        return v;
    }
    pos++;
    skipSpaces(s, pos);
    Variable *var = parseVariable(s, pos);
    skipSpaces(s, pos);
    if (pos == s.length() || s[pos] != '.') {
        throw ". expected";
    }
    pos++;
    skipSpaces(s, pos);
    Lambda *lambda = new Lambda(var, parseExpression(s, pos));
    if (v != NULL) {
        return new Apply(v, lambda);
    }
    return lambda;
};

Node *parseApply(const string &s, int &pos) {
    skipSpaces(s, pos);
    Node *res = parseAtom(s, pos);
    skipSpaces(s, pos);
    Node *atom;
    while ((atom = parseAtom(s, pos)) != NULL) {
        res = new Apply(res, atom);
        skipSpaces(s, pos);
    }
    return res;
}

Node *parseAtom(const string &s, int &pos) {
    if (s[pos] == '(') {
        pos++;
        skipSpaces(s, pos);
        Node *expr = parseExpression(s, pos);
        skipSpaces(s, pos);
        if (pos == s.length() || s[pos] != ')') throw ") expected";
        pos++;
        skipSpaces(s, pos);
        return expr;
    }
    return parseVariable(s, pos);
}

Variable *parseVariable(const string &s, int &pos) {
    skipSpaces(s, pos);
    string name = "";
    while (pos < s.length() &&
            ('a' <= s[pos] && s[pos] <= 'z' || '0' <= s[pos] && s[pos] <= '9' || s[pos] == '\'')) {
        name += s[pos];
        pos++;
    }
    if (name.length() == 0) {
        return NULL;
    }
    return new Variable(name);
}

Node *parseStringToFormula(const string &s) {
    int pos = 0;
    return parseExpression(s, pos);
}

bool checkNodesAreEqual(Node *a, Node *b) {
    if (!a && !b) return true;
    if (!a || !b) return false;
    if (a == b) return true;
    if (a->getHash() != b->getHash()) return false;
    if (typeid(*a) != typeid(*b)) return false;
    if (typeid(*a) == typeid(Variable)) {
        auto *aa = static_cast<Variable *>(a);
        auto *bb = static_cast<Variable *>(b);
        return aa->name == bb->name;
    } else if (typeid(*a) == typeid(Lambda)) {
        auto *aa = static_cast<Lambda *>(a);
        auto *bb = static_cast<Lambda *>(b);
        return aa->var->name == bb->var->name && checkNodesAreEqual(aa->v, bb->v);
    } else if (typeid(*a) == typeid(Apply)) {
        auto *aa = static_cast<Apply *>(a);
        auto *bb = static_cast<Apply *>(b);
        return checkNodesAreEqual(aa->l, bb->l) && checkNodesAreEqual(aa->r, bb->r);
    } else {
        throw "Unknown type";
    }
}

#endif
