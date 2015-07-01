#ifndef SUBSTITUTION_H
#define SUBSTITUTION_H

void getEmptyVars(Node *v, map<string, int> &boundVars, set<Variable *> &res) {
    if (v->getClass() == "Variable") {
        Variable *vv = static_cast<Variable *>(v);
        if (boundVars[vv->name] == 0) {
            res.insert(vv);
        }
        return;
    } else if (v->getClass() == "Apply") {
        Apply *vv = static_cast<Apply *>(v);
        getEmptyVars(vv->l, boundVars, res);
        getEmptyVars(vv->r, boundVars, res);
    } else if (v->getClass() == "Lambda") {
        Lambda *vv = static_cast<Lambda *>(v);
        boundVars[vv->var->name]++;
        getEmptyVars(vv->v, boundVars, res);
        boundVars[vv->var->name]--;
    }
}

Node *substitute(Node *v, map<string, int> &boundVars, string &var, Node *value) {
    if (v->getClass() == "Variable") {
        Variable *vv = static_cast<Variable *>(v);
        if (var == vv->name && boundVars[vv->name] == 0) {
            return value;
        }
        return vv;
    } else if (v->getClass() == "Apply") {
        Apply *vv = static_cast<Apply *>(v);

        Node *newL = substitute(vv->l, boundVars, var, value);
        Node *newR = substitute(vv->r, boundVars, var, value);

        return new Apply(newL, newR);
    } else if (v->getClass() == "Lambda") {
        Lambda *vv = static_cast<Lambda *>(v);
        boundVars[vv->var->name]++;
        Node *newV = substitute(vv->v, boundVars, var, value);
        boundVars[vv->var->name]--;
        return new Lambda(vv->var, newV);
    }
}

set<Variable *> getEmptyVars(Node *v) {
    set<Variable *> res;
    map<string, int> boundVars;
    getEmptyVars(v, boundVars, res);
    return res;
}

void parseCondition(const string &s, Node *&expr, Variable *&var, Node *&exprForSubstitution) {
    string exprStr, varStr, exprForSubstitutionStr;
    int i;
    for (i = 0; i < s.length(); i++) {
        if (s[i] == '[') {
            exprStr = s.substr(0, i);
            while (s[i] != ']') i++;
            string substitution = s.substr(exprStr.length() + 1, i - exprStr.length() - 1);
            for (i = 0; i < substitution.length(); i++) {
                if (substitution[i] == '=') {
                    varStr = substitution.substr(0, i - 1);
                    exprForSubstitutionStr = substitution.substr(i + 1);
                    break;
                }
            }
            break;
        }
    }
    expr = parseStringToFormula(exprStr);
    var = static_cast<Variable *>(parseStringToFormula(varStr));
    exprForSubstitution = parseStringToFormula(exprForSubstitutionStr);
}

Node *substitute(Node *v, string &var, Node *value) {
    map<string, int> boundVars;
    return substitute(v, boundVars, var, value);
}

#endif
