def find_second_extremes(arr):
    if len(arr) < 2:
        return "Array must have at least two elements"
    
    # Initialize using the first element of the array
    first_max = arr[0]
    second_max = None
    
    first_min = arr[0]
    second_min = None
    
    for num in arr:
        #Finding the maximum
        if num > first_max:
            second_max = first_max  
            first_max = num         
        elif num != first_max and (second_max is None or num > second_max):
            second_max = num
            
        #Finding the minimum 
        if num < first_min:
            second_min = first_min  
            first_min = num         
        elif num != first_min and (second_min is None or num < second_min):
            second_min = num
            
    return {
        "second_largest": second_max,
        "second_smallest": second_min
    }

l= list(eval(input("enter the list of numbers")))
result = find_second_extremes(l)
print("Result:", result)
