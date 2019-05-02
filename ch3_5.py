# We first map the index in the textbook  1~7 to 0~6.
# We represent infinity as -1.

W = [[ 0,  4, -1, -1, -1, 10, -1],
     [ 3,  0, -1, 18, -1, -1, -1],
     [-1,  6,  0, -1, -1, -1, -1],
     [-1,  5, 15,  0,  2, 19,  5],
     [-1, -1, 12,  1,  0, -1, -1],
     [-1, -1, -1, -1, -1,  0, 10],
     [-1, -1, -1,  8, -1, -1,  0]]

fullIndexList = [0] * len(W)
for k in range(len(W)):
    fullIndexList[k] = k

for skip in range(0,len(W)):      # n loops
    indexList = fullIndexList[:]  # hard-copy
    indexList.pop(skip)           # eliminate the "skip" 
    print(indexList)
    for i in indexList:      # (n-1) loops
        for j in indexList:  # (n-1) loops
            print(W[skip][j], "+",  W[i][skip], "vs",  W[i][j])
            if W[skip][j] != -1 and W[i][skip] != -1:  # they are normal numbers
                if W[i][j] == -1: # originally an Inf
                    W[i][j] = W[skip][j] + W[skip][j]
                    print("choose", W[skip][j], "+", W[skip][j])
                else:  # they all are normal numbers, we have to compare  
                    if W[skip][j] + W[i][skip] < W[i][j]:    
                        W[i][j] = W[skip][j] + W[skip][j]
                        print("choose", W[skip][j], "+", W[skip][j])
                    else:
                        print("choos", W[i][j])
            else: # there are at least one Inf; impossiple for a shorter path
                print("choose", W[i][j])

    for _ in range(len(W)): print(W[_])
