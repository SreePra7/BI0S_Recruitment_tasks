def find_xor_pairs(arr, target):
    seen = set()
    pairs = set()
    
    for num in arr:
        complement = num ^ target
        if complement in seen:
            # Sort the pair to ensure (a, b) and (b, a) are treated as the same
            pair = tuple(sorted((num, complement)))
            pairs.add(pair)
        seen.add(num)
        
    return list(pairs)


l= list(eval(input("enter the list of numbers")))
target = int(input("enter the target"))
print(find_xor_pairs(l, target)) 