#test mindre eller större än
age = eval(input("Enter age: "))

if(age>=1) and (age<18):
    print("ej bolaget eller köra bil")

elif(age>=18) and (age < 20):
    print("köra bil, ej bolaget")

elif(age>20) and (age <= 50):
    print("köra bil och får vara full")

elif(age>50):
    print("nykter, och rullator")

else:
    print("skriv in rätt ålder")