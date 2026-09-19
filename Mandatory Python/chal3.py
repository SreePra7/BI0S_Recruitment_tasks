def reverse_alpha(s):
    chars = list(s)
    alphabets = []
    for c in chars:
        if c.isalpha():
            alphabets.append(c)
            
    alphabets.reverse()
    
    alpha_idx = 0
    for i in range(len(chars)):
        if chars[i].isalpha():
            chars[i] = alphabets[alpha_idx]
            alpha_idx += 1
            
    final_string = "".join(chars)
    return final_string

s = input("enter the string")
print(reverse_alpha(s)) 