#ifndef CHILDANIMAL_H
#define CHILDANIMAL_H

#include <iostream>
#include <string>
#include <vector>

#include "Animal.h"
using namespace std;

class Dog : public Mammal {
public:
    Dog();
    Dog(string n, COLOR c, string o);
    ~Dog();

    void speak() const;

    void move() const;

private:
    string _owner;

};

class Cat : public Mammal {
public:
    Cat();
    Cat(string n, COLOR c);
    ~Cat();

    void speak() const;

    void move() const;

};

class Lion : public Mammal {
public:
    Lion();
    Lion(string n, COLOR c);
    ~Lion();

    void speak() const;

    void move() const;

};

#endif
