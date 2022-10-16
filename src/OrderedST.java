import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class OrderedST<Key extends Comparable<Key>, Value> {
    private Key[] keys; //키값
    private Value[] values; //index값
    private int N; //총 데이터 사이즈 측정

    public Iterable<Key> keys(Key lo, Key hi) {
        Queue<Key> q = new LinkedList<Key>();
        for (int i = rank(lo); i < rank(hi); i++) {
            q.add(keys[i]);
            if (q.contains(hi)) {
                q.add(keys[rank(hi)]);
            }

        }
        return q;
    }

    public OrderedST(int symbol) {
        keys = (Key[]) new Comparable[symbol];//키값 생성
        values = (Value[]) new Object[symbol]; // 벨류값 생성
    }

    public int size() {
        return N;
    }

    public Value get(Key key) {

        if (isEmpty()) return null;
        int i = rank(key);
        if (i < N && keys[i].compareTo(key) == 0)
            return values[i];//벨류 배열 값에 넣어준다.
        else return null;

    }

    boolean isEmpty() {
        return true;
    }
    public void put(Key key, Value value) {
        int i = rank(key);
        if (i < N && keys[i].compareTo(key) == 0) {
            values[i] = value;
            return;
        }
        for (int j = N; j > i; j--) {
            keys[j] = keys[j - 1];
            values[j] = values[j - 1];
        }
        keys[i] = key;
        values[i] = value;
        N++;
    }
    public Key celling(Key key) { //키값보다 크거나 같은 가장 작은 키
        int i = rank(key);
        return keys[i];
    }

    public Key floor(Key key) { //키값보다 작거나 같은 가장 큰 키
        int i = rank(key);
        return keys[i - 1];
    }
    public int rank(Key key) {
        int lo = 0, hi = N - 1;
        while (lo < hi) {
            int mid = lo + (hi - lo) / 2;
            int cmp = key.compareTo(keys[mid]);
            if (cmp < 0) hi = mid = hi - 1;// 현재 들어간 값이 중간값보다 작을 떄
            else if (cmp > 0) lo = mid + 1;// 현재 들어간 값이 중간값보다 클때
            else return mid; //현재 들어간 값과 중간값이 같을 떄
        }
        return lo;
    }






    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("몇개의 데이터를 넣을지 입력하시오");
        int n = scan.nextInt();
        String[] key = new String[n];
        String keys;
        OrderedST<String,Integer> ost = new OrderedST<String, Integer>(n);
        while (n!=0)
        {
            int i = 0;
            String k = scan.next();
            ost.put(k,i);
            i++;
            n--;

        }
        System.out.println("Rank의 값은:"+ost.rank("A"));
        System.out.println("Celling의 값은:"+ost.celling("B"));
        System.out.println("floor의 값은:"+ost.floor("T"));

    }
}