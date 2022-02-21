#include <iostream>
#include <string>
#include <vector>

#include "Animal.h"
#include "childAnimal.h"

COLOR convert(const std::string& str)
{
    if (str == "Green") return COLOR(Green);
    else if (str == "Blue") return COLOR(Blue);
    else if (str == "White") return COLOR(White);
    else if (str == "Black") return COLOR(Black);
    else if (str == "Brown") return COLOR(Brown);
}

int main() {
    // Animal a("Tevin", Brown);
    // a.speak() ;


   /* Mammal m("tevin", Green);
    m.speak();*/

    // Dog d("TEVIN", White, "Noel");
    // d.speak();
    // d.move();



    // Dog dogi("Lassie", White, "Andy");
    // Mammal *aniPtr = &dogi ;
    // Mammal &aniRef = dogi ;
    // Mammal aniVal = dogi ;
    // aniPtr->speak() ;
    // aniRef.speak() ;
    // aniVal.speak() ;

    int x;
    vector<Mammal*> zoo;
    do {
        cout << "Select the animal to send to Zoo: " << endl;
        cout << "(1)Dog (2)Cat (3)Lion (4)Move all animals (5)Quit" << endl;
        cout << "Enter your option: ";
        cin >> x;
        string name;
        string color;
        Mammal* m;
        COLOR c;
        switch (x)
        {
        case 1:
            cout << "Enter dog's name: ";
            cin >> name;
            cout << "Enter dog's color: ";
            cin >> color;
            c = convert(color);
            // Dog dog(name, c, "Zoo");
            m = new Dog(name, c, "Zoo");
            zoo.push_back(m);

            break;
        case 2:
            cout << "Enter cat's name: ";
            cin >> name;
            cout << "Enter cat's color: ";
            cin >> color;
            c = convert(color);
            // Dog dog(name, c, "Zoo");
            m = new Cat(name, c);
            zoo.push_back(m);
            break;
        case 3:
            cout << "Enter Lion's name: ";
            cin >> name;
            cout << "Enter Lion's color: ";
            cin >> color;
            c = convert(color);
            // Dog dog(name, c, "Zoo");
            m = new Lion(name, c);
            zoo.push_back(m);
            break;
        case 4:
            for (int i = 0; i < zoo.size(); i++) {
                zoo[i]->move();
                zoo[i]->speak();
                zoo[i]->eat();
            }
            break;
        case 5:
            cout << "quit sending" << endl;
            break;
        default:
            cout << "pls enter correct option" << endl;
            break;
        }
        if (x == 5) {
            break;
        }

    } while (true);

    cout << "Program exiting..." << endl;
    return 0;
}