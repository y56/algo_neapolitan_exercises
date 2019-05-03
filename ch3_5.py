# We first map the index in the textbook  1~7 to 0~6.
# We represent infinity as -1.

W = [[ 0,  4, -1, -1, -1, 10, -1],
     [ 3,  0, -1, 18, -1, -1, -1],
     [-1,  6,  0, -1, -1, -1, -1],
     [-1,  5, 15,  0,  2, 19,  5],
     [-1, -1, 12,  1,  0, -1, -1],
     [-1, -1, -1, -1, -1,  0, 10],
     [-1, -1, -1,  8, -1, -1,  0]]

print("At the beginning, W is")
for _ in range(len(W)): print(W[_])
print("\n")

fullIndexList = [0] * len(W)
for k in range(len(W)):
    fullIndexList[k] = k

for skip in range(0,len(W)):      # n loops

    indexList = fullIndexList[:]  # hard-copy
    indexList.pop(skip)           # eliminate the "skip" 

    print("Skiping", skip, ". We are working on", indexList)
    
    for i in indexList:      # (n-1) loops
        for j in indexList:  # (n-1) loops
            print("Woring on W[ %d ][ %d ];  %2d + %2d vs %2d" %(i, j, W[skip][j], W[i][skip], W[i][j]), end=';  ')
            if W[skip][j] != -1 and W[i][skip] != -1:  # they are normal numbers
                if W[i][j] == -1: # originally an Inf
                    W[i][j] = W[skip][j] + W[skip][j]
                    print("choose W[ %d ][ %d ] =  %2d + %2d" %(i, j, W[skip][j], W[i][skip]))
                else:  # they all are normal numbers, we have to compare  
                    if W[skip][j] + W[i][skip] < W[i][j]:    
                        W[i][j] = W[skip][j] + W[skip][j]
                        print("choose W[ %d ][ %d ] =  %2d + %2d" %(i, j, W[skip][j], W[i][skip]))
                    else:
                        print("choose W[ %d ][ %d ] =  %2d" %(i, j, W[i][j]))
            else: # there are at least one Inf; impossiple for a shorter path
                print("choose W[ %d ][ %d ] =  %2d" %(i, j, W[i][j]))
    print("\nNow, W is")
    for _ in range(len(W)): print(W[_])
    print("\n")

