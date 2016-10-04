kr = input("Skriv in belopp: " )
kr = float(kr)
procent = input("skriv in braknad okning i procent, ex:20: ")
procent = "1.{}".format(procent)
procent = float(procent)
antal_ar = input("antal ar på bank: ")
antal_ar = int(antal_ar)

print(procent)
for i in range(1,antal_ar):
    kr = kr*procent
    print("år {}: {}".format(i, kr))

