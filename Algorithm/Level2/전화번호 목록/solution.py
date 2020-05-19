def solution(phone_book)
    answer = True
    phone_book.sort()
    for i in range(1, len(phone_book))
        len_text = len(phone_book[i-1])
        print(phone_book[i][0:len_text])
        if phone_book[i-1] == phone_book[i][0:len_text]
            return False
    return answer