def anagrams(str1, str2):
    if len(str1) != len(str2):
        return False
        
    count1 = {}
    count2 = {}
    
    #Counting the characters in the first string
    for char in str1:
        if char in count1:
            count1[char] += 1
        else:
            count1[char] = 1
            
    #Counting the characters in the second string
    for char in str2:
        if char in count2:
            count2[char] += 1
        else:
            count2[char] = 1
    return count1 == count2
w1 = input("enter the first word")
w2 = input("enter the second word")
print(anagrams(w1,w2)) 
