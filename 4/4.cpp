#include <iostream>
#include <fstream>
#include <string>
#include "../LambdaParser.h"
#include "../Normalization.h"

using namespace std;

int main() {
    try {
        ifstream cin("task4.in");
        ofstream cout("task4.out");
        string s;
        getline(cin, s);
        Node *v = parseStringToFormula(s);
        cout << normalize(v)->getAsString() << "\n";
    } catch (const char *e) {
        cerr << e << "\n";
    }

//((\v0.((\v1.((\v2.((\v3.((\v4.((\v5.((\v6.((\v7.((\v8.((\v9.((\v10.((v8 (v9 (\f.(\x.(f (f (f (f x)))))))) ((v8 (v9 (\f.(\x.(f (f (f (f (f x))))))))) (v9 (\f.(\x.(f (f (f (f (f (f x)))))))))))) (\n.((v0 (\s.(\i.(((v1 (v4 i)) (\f.(\x.x))) ((v7 (f i)) (s (v5 i))))))) n)))) (\n.((((v0 (\f.(\i.(\m.(\n.(((v1 (v4 i)) n) (((f (v5 i)) n) ((v7 m) n)))))))) n) (\f.(\x.x))) (\f.(\x.(f x))))))) (\a.(\b.(\s.((s a) b)))))) (\a.(\b.((a v6) b))))) (\n.(\f.(\x.((n f) (f x))))))) (\n.(\f.(\x.(((n (\g.(\h.(h (g f))))) (\u.x)) (\u.u))))))) (\n.((n (\u.v3)) v2)))) (\a.(\b.b)))) (\a.(\b.a)))) (\c.(\t.(\e.((c t) e)))))) (\f.((\x.(f (x x))) (\x.(f (x x))))))

//(\s. ((s (\f. (\a. (f (f (f (f (f (f (\f. (\x. (f (f (f (f (f x)))))))))))))))) (\s. ((s (\f. (\x. (f (f (f (f (f (f (f (f (f (f (f x)))))))))))))) (\f. (\x. (f (f (f (f (f (f (f (f (f (f (f (f (f (f (f x)))))))))))))))))))))

//(\s. ((s (\f. (\z. (f (f (f (f (f z)))))))) (\s. ((s (\f. (\x. (f (f (f (f (f (f (f (f x))))))))))) (\f. (\x. (f (f (f (f (f (f (f (f (f (f (f (f (f x)))))))))))))))))))
    return 0;
}
