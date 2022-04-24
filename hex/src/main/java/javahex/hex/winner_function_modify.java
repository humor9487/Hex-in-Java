public static boolean winner(int n) {
    boolean[][] visited = new boolean[BOARD_SIZE + 2][BOARD_SIZE + 2];
    Stack<int[]> stack;
    stack = new Stack<>();
    int x, y, t;
    // 將n的處理一般化
    t = (n==0)?1:-1;
    for (int z = 1; z <= BOARD_SIZE; z++) { //原本沒判定邊最後一排
        x = (n==0)?1:z;
        y = (n==0)?z:1;
        if (board[x][y] == t) {
            visited[x][y] = true;
            stack.push(new int[]{x, y});
        }
    }
    if (stack.empty()) return false;
    int[] curr = new int[]{stack.peek()[0], stack.peek()[1]};
    // check the presence of neighbors and DFS
    do {
        // return true if having reached the bottom row
        if (curr[n] == BOARD_SIZE) return true; // 0, 1替換成n
        boolean hasNeighbor = false;

        for (int i = 0; i < 6; i++) {
            int A = (i == 0 || i == 1) ? -1 : (i == 2 || i == 5) ? 0 : 1;
            int B = (i == 4 || i == 5) ? -1 : (i == 0 || i == 3) ? 0 : 1;
            if (!visited[curr[0] + A][curr[1] + B] && board[curr[0] + A][curr[1] + B] == t) {
                hasNeighbor = true;
                visited[curr[0] + A][curr[1] + B] = true;
                stack.push(new int[]{curr[0] + A, curr[1] + B});
                break;
            }
        }
        if (!hasNeighbor) {
            if (stack.size() <= 1) return false;
            stack.pop();
        }
        curr = new int[]{stack.peek()[0], stack.peek()[1]};
    } while (!stack.empty());
    return false;
}
