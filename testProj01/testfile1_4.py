#test
t1, t2 = input('Skriv in tva tal: ').split()
t1 = int(t1)
t2 = int(t2)
opr = input('Skriv in operator *,+,- : ')

if opr == "+":
    print("resultat: {} + {} = {}".format(t1,t2,t1+t2))

elif opr == "*":
    print("resultat: {} * {} = {}".format(t1,t2,t1*t2))

elif opr == "-":
    print("resultat: {} - {} = {}".format(t1, t2, t1-t2))
else:
    print("wrong... use *, + or - next time")
