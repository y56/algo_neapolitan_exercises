#include <iostream>

void partition(int S[], int low, int high, int pivotpoint)
{
    int i, j, pivotitem, temp; 
    
    j = low;
    pivotitem = S[low];
    for (i = low + 1; i <= high; i++)
    {
        if (S[i] < pivotitem)
        {
            j++;
            temp = S[i]; S[i] = S[j]; S[j] = temp;
        }
    }
    pivotpoint = j;
    temp = S[low]; S[low] = S[pivotpoint]; S[pivotpoint] = temp;
}
void quicksort(int S[], int low, int high)
{
    int pivotpoint = low;

    if (high > low)
    {
        partition(S, low, high, pivotpoint);
        quicksort(S, low, pivotpoint - 1);
        quicksort(S, pivotpoint + 1, high);
    }
}
int main() 
{
    int S[] = {123, 34, 189, 56, 150, 12, 9, 240};
    int size = sizeof(S) / sizeof(S[0]);
    printf("\nThe size is %d\n\n", size);
    int i; for (i = 0; i < size; i++)
        printf("%d, ",S[i]);
    printf("\n\n");
    
    quicksort(S, 0, size-1); 
    
    for (i = 0; i < size; i++)
        printf("%d, ",S[i]);
    printf("\n");
}
