#include <iostream>
#include <string>
#include <vector>

#include "Animal.h"
#include "childAnimal.h"
using namespace std;

Dog::Dog() : Mammal() {};

Dog::Dog(string n, COLOR c, string o) : Mammal(n, c), _owner(o) {
    cout << "owner: " << _owner << endl;
};

Dog::~Dog() = default;

void Dog::speak() const {
    cout << "Dog " << Animal::getName() << " Woof " << endl;
}

void Dog::move() const {
    cout << "Dog " << Animal::getName() << " moves " << endl;
}

Cat::Cat() {};

Cat::Cat(string n, COLOR c) : Mammal(n, c) {

};

Cat::~Cat() = default;

void Cat::speak() const {
    cout << "Cat " << Animal::getName() << " meow" << endl;
}

void Cat::move() const {
    cout << "Cat " << Animal::getName() << " moves " << endl;
}

Lion::Lion() : Mammal() {};

Lion::Lion(string n, COLOR c) : Mammal(n, c) {

};

Lion::~Lion() = default;

void Lion::speak() const {
    cout << "Lion " << Animal::getName() << " Roar" << endl;
}

void Lion::move() const {
    cout << "Lion " << Animal::getName() << " moves " << endl;
}
