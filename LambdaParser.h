#ifndef LAMBDA_PARSER_H
#define LAMBDA_PARSER_H

#include <string>
#include <cctype>
#include <map>
#include <set>

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


struct Node {
    long long hash;
    virtual void print() {
        cout << getAsString();
    }
    virtual string getAsString() = 0;
    virtual bool isAtom() {
        return false;
    }
    virtual string getClass() = 0;
};

struct Variable : Node {
    string name;
    Variable(const string &name) : name(name) {
        for (char c : name) {
            hash = hash * 31 + c;
        }
    }

    string getAsString() {
        return name;
    }

    bool isAtom() {
        return true;
    }

    string getClass() {
        return "Variable";
    }
};

struct Lambda : Node {
    Variable *var;
    Node *v;
    Lambda() : var(NULL), v(NULL) {}
    Lambda(Variable *var, Node *v) : var(var), v(v) {
        hash = (var->hash * 31) ^ (v->hash * 43);
    }

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

    string getClass() {
        return "Lambda";
    }
};

struct Apply : Node {
    Node *l, *r;
    Apply() : l(NULL), r(NULL) {}
    Apply(Node *l, Node *r) : l(l), r(r) {
        hash = (l->hash * 59) & (r->hash * 73);
    }

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

    string getClass() {
        return "Apply";
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
    if (a->hash != b->hash) return false;
    if (a->getClass() != b->getClass()) return false;
    if (a->getClass() == "Variable") {
        auto *aa = static_cast<Variable *>(a);
        auto *bb = static_cast<Variable *>(b);
        return aa->name == bb->name;
    } else if (a->getClass() == "Lambda") {
        auto *aa = static_cast<Lambda *>(a);
        auto *bb = static_cast<Lambda *>(b);
        return aa->var->name == bb->var->name && checkNodesAreEqual(aa->v, bb->v);
    } else if (a->getClass() == "Variable") {
        auto *aa = static_cast<Apply *>(a);
        auto *bb = static_cast<Apply *>(b);
        return checkNodesAreEqual(aa->l, bb->l) && checkNodesAreEqual(aa->r, bb->r);
    }
}

#endif
