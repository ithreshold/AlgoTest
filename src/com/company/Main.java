package com.company;

public class Main {

    public static int maxFlow(int[][] cap, int s, int t) {
        for (int flow = 0;;) {
            int df = findPath(cap, new boolean[cap.length], s, t, Integer.MAX_VALUE);
            if (df == 0)
                return flow;
            flow += df;
        }
    }

    static int findPath(int[][] cap, boolean[] vis, int u, int t, int f) {
        if (u == t)
            return f;
        vis[u] = true;
        for (int v = 0; v < vis.length; v++)
            if (!vis[v] && cap[u][v] > 0) {
                int df = findPath(cap, vis, v, t, Math.min(f, cap[u][v]));
                if (df > 0) {
                    cap[u][v] -= df;
                    cap[v][u] += df;
                    return df;
                }
            }
        return 0;
    }

    // Usage example
    public static void main(String[] args) {
        int[][] capacity = { { 0, 3, 2 }, { 0, 0, 2 }, { 0, 0, 0 } }; // example num1 - кажется, что работает

        //int[][] capacity = { { 0, 1, 16 }, { 0, 2, 13}, {1, 2, 10}, { 1, 3, 12}, { 2, 1, 4}, { 2, 4, 14}, { 3, 2, 9 }, { 3, 5, 20 }, {4, 3, 7}, { 4, 5, 4} };
        //это второй пример, s = 0, t = 5, падает переполнение массива, понятно что в рекурсии в 20ой строке..
        System.out.println(maxFlow(capacity, 0, 2));
    }
}