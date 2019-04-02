"""
Algorithm by Neapolitan
ch 2-8
step-by-step analysis
"""
#from copy import deepcopy

def merge2(low, mid, high):
    i = low  # for the left/small
    j = mid + 1  # for the right/large   
    k = low  # the index for U[] 
    print("merge2")
    print("      ", "low =", low, "; mid =", mid, "; high =", high)
    print("      ", "  i =", i, ";   j =", j, ";    k =", k)
    U = S[:]  # the temp list to hold the merged result
    print("                                           hard-copy  U = S[:]")
    print("    U =", U)
    print("    S =", S)
    # I have think carefully. We don't need deepcopy. Linked-list is fine.
    while i <= mid and j <= high:
        if S[i] < S[j]:
            print('                                           S[ i ] < S[ j ]')
            print('                                           S[',i,'] < S[',j,']')
            print('                                             ',S[i],'<',S[j])
            print('                                           U[ k ] is replaced by S[ i ]')
            print('                                           U[',k,'] is replaced by S[',i,']')
            U[k] = S[i]
            print("    U =", U)
            print("    S =", S)
            i += 1
        else:
            print('                                           S[ i ] >= S[ j ]')
            print('                                           S[',i,'] >= S[',j,']')
            print('                                             ',S[i],'>=',S[j])
            print('                                           U[ k ] is replaced by S[ j ]')
            print('                                           U[',k,'] is replaced by S[',j,']')
            U[k] = S[j]
            print("    U =", U)
            print("    S =", S)
            j += 1
        k += 1
    
    # If one of the U[] or K[] is used up, we don't comparison anymore. 
    # Merge the residual to the sorted part directly
    if i > mid:  # the left part is used up
        print("")
        print('i =',i,'>',mid,'= mid')
        print('                    The left is used up')
        print("                                           U[k:high+1] is replaced by S[j:high+1]")
        print('                                           U[',k,':',high+1,']  is replaced by S[',j,':',high+1,']')
        print('                                           ',U[k:high+1],'                     ',S[j:high+1])
        U[k:high+1] = S[j:high+1]  # Connect the right part onto the tail of U[]
        print("    U =", U)
        print("    S =", S)
    else:  # the right part is used up
        print("")
        print('j =',j,'>',high,'= high')
        print('                    The right is used up')
        print("                                           U[k:high+1] is replaced by S[i:mid+1]")
        print('                                           U[',k,':',high+1,']  is replaced by S[',i,':',mid+1,']')
        print('                                           ',U[k:high+1],'                     ',S[i:mid+1])
        U[k:high+1] = S[i:mid+1]
        print("    U =", U)
        print("    S =", S)

    S[low:high+1] = U[low:high+1]  # overwirte the yet-merged S[] with the merged U[]
    print("                                           Put U into S")
    print("    U =", U)
    print("    S =", S)

def mergesort2(low, high):
    print("mergesort2")
    print("          low =",low, "; high =",high)
    if high > low:
        mid  = (low + high) // 2  # ===floor( (low + high) // 2 )
        print("                             mid =",mid)
        mergesort2(low, mid)
        mergesort2(mid + 1, high)
        merge2(low, mid, high)
    else:
        print("                             X")

global S
S = [123, 34, 189, 56, 150, 12, 9, 240]
print("Input")
print(S)
mergesort2(0,len(S)-1) 
print("Output")
print(S)

