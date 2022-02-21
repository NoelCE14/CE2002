#ifndef ANIMAL_H
#define ANIMAL_H

#include <iostream>
#include <string>
#include <vector>

using namespace std;



enum COLOR { Green, Blue, White, Black, Brown };
//const string pColor[] = {"Green", "Blue", "White", "Black", "Brown"};

class Animal {
public:
    Animal();

    Animal(string n, COLOR c);
    ~Animal();

    virtual void speak() const;

    virtual void move() const = 0;

    string getName() const;

    COLOR getColor();

private:
    string _name;
    COLOR _color;
};

class Mammal : public Animal {
public:
    Mammal();
    Mammal(string n, COLOR c);
    ~Mammal();
    void eat() const;
    void move() const;
};

#endif
