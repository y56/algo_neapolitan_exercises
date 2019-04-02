"""
Algorithm by Neapolitan
ch 2-8
"""

def merge2(low, mid, high):
    i = low  # for the left/small
    j = mid + 1  # for the right/large   
    k = low  # the index for U[] 
    U = S[:]  # the temp list to hold the merged result
    # I have think carefully. We don't need deepcopy. Linked-list is fine.
    while i <= mid and j <= high:
        if S[i] < S[j]:
            U[k] = S[i]
            i += 1
        else:
            U[k] = S[j]
            j += 1
        k += 1
    
    # If one of the U[] or K[] is used up, we don't comparison anymore. 
    # Merge the residual to the sorted part directly
    if i > mid:  # the left part is used up
        U[k:high+1] = S[j:high+1]  # Connect the right part onto the tail of U[]
    else:  # the right part is used up
        U[k:high+1] = S[i:mid+1]
        
    S[low:high+1] = U[low:high+1]  # overwirte the yet-merged S[] with the merged U[]

def mergesort2(low, high):
    if high > low:
        mid  = (low + high) // 2  # ===floor( (low + high) // 2 )
        mergesort2(low, mid)
        mergesort2(mid + 1, high)
        merge2(low, mid, high)

global S
S = [123, 34, 189, 56, 150, 12, 9, 240]
print("Input")
print(S)
mergesort2(0,len(S)-1) 
print("Output")
print(S)

