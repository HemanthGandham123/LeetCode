package backtracking;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Medium {
    public List<String> generateParenthesis(int n) {
        List<List<String>> strList = new ArrayList<>();
        List<String> tmp = new ArrayList<>();
        tmp.add("()");
        strList.add(tmp);
        for (int i = 1; i < n; i++) {
            Set<String> parSet = new HashSet<>();
            for (String par : strList.get(i - 1)) {
                parSet.add("(" + par + ")");
                parSet.add("()" + par);
                parSet.add(par + "()");
            }
            strList.add(parSet.stream().sorted().collect(Collectors.toList()));
        }
        return strList.get(n - 1);
    }
}
