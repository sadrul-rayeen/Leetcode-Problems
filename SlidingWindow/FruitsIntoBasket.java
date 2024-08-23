package SlidingWindow;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/*
 * Problem - Fruit Into Baskets
 * 
 *  You are visiting a farm that has a single row of fruit trees arranged from left to right. The trees are represented by an integer array fruits where fruits[i] is the type of fruit the ith tree produces.

*   You want to collect as much fruit as possible. However, the owner has some strict rules that you must follow:
*    You only have two baskets, and each basket can only hold a single type of fruit. There is no limit on the amount of fruit each basket can hold.
*   Starting from any tree of your choice, you must pick exactly one fruit from every tree (including the start tree) while moving to the right. The picked fruits must fit in one of your baskets.
*   Once you reach a tree with fruit that cannot fit in your baskets, you must stop.
*   Given the integer array fruits, return the maximum number of fruits you can pick.
*   

    Example 1:

    Input: fruits = [1,2,1]
    Output: 3
    Explanation: We can pick from all 3 trees.

    Example 2:

    Input: fruits = [0,1,2,2]
    Output: 3
    Explanation: We can pick from trees [1,2,2].
    If we had started at the first tree, we would only pick from trees [0,1].

    Example 3:

    Input: fruits = [1,2,3,2,2]
    Output: 4
    Explanation: We can pick from trees [2,3,2,2].
    If we had started at the first tree, we would only pick from trees [1,2].

    Constraints:

        1 <= fruits.length <= 105
        0 <= fruits[i] < fruits.length
 */

public class FruitsIntoBasket {

    // time - O(n * n)
    // space - O(3) --> storing value 3 values
    public static int findMaxValueBruteForce(int[] fruits) {
        int n = fruits.length;
        int maxLength = 0;
        for (int i = 0; i < n; i++) {
            Set<Integer> s = new HashSet<>();
            for (int j = i; j < n; j++) {
                s.add(fruits[j]);

                if (s.size() <= 2) {
                    maxLength = Math.max(maxLength, j - i + 1);
                } else {
                    break;
                }
            }
        }

        return maxLength;

    }
    // time -> O(n)
    // space -> O(n) --> for hashmap
    public static int findMaxValueOptimal(int[] fruits) {
        int n = fruits.length;
        int left = 0;
        int right = 0;
        int maxLength = 0;

        HashMap<Integer, Integer> map = new HashMap<>();

        while (right < n) {
            int current = fruits[right];
            int frequency = map.getOrDefault(current, 0);
            map.put(current, frequency + 1);

            if (map.size() <= 2) {
                maxLength = Math.max(maxLength, right - left + 1);
            }

            if (map.size() > 2) {
                while (map.size() > 2) {
                    int currentIthValue = fruits[left];
                    int currentFrequency = map.get(currentIthValue);
                    map.put(currentIthValue, currentFrequency - 1);

                    if (map.get(currentIthValue) == 0) {
                        map.remove(currentIthValue);
                    }

                    left++;
                }
            }

            right++;
        }

        return maxLength;
    }
    public static void main(String[] args) {
        int[] fruits = { 1,2,1 };

        // int result = findMaxValueBruteForce(fruits);
        int result2 = findMaxValueOptimal(fruits);

        System.out.println(result2);
    }
}
