#include <iostream>
#include <string>
#include <vector>

#include "Animal.h"
using namespace std;

// enum COLOR { Green, Blue, White, Black, Brown } ;
// const string pColor[] = {"Green", "Blue", "White", "Black", "Brown"};
// COLOR convert(const std::string& str)
// {
//     if(str == "Green") return Green;
//     else if(str == "Blue") return Blue;
//     else if(str == "White") return White;
//     else if(str == "Black") return Black;
//     else if(str == "Brown") return Brown;
// }

const string pColor[] = {"Green", "Blue", "White", "Black", "Brown"};

Animal::Animal() : _name("unknown") {
    cout << "constructing Animal object " << _name << endl;
}

Animal::Animal(string n, COLOR c) : _name(n), _color(c) {
    cout << "name: " << _name << endl;
    cout << "Color: " << pColor[_color] << endl;
}

Animal::~Animal() {
    cout << "destructing Animal object " << _name << endl;
}

void Animal::speak() const {
    cout << "Animal speaks " << endl;
}

string Animal::getName() const {
    return _name;
}

COLOR Animal::getColor() {
    return _color;
}

Mammal::Mammal() {};

Mammal::Mammal(string n, COLOR c) : Animal(n, c) {

};
Mammal::~Mammal() = default;
void Mammal::eat() const {
    cout << "Mammal " << Animal::getName() << " eat " << endl;
}
void Mammal::move() const {
    cout << "Mammal " << Animal::getName() << " moves " << endl;
}
// class Animal {
//     public :
//     Animal() : _name("unknown") {
//         cout << "constructing Animal object "<< _name << endl ;
//     }
//     Animal(string n, COLOR c) : _name(n), _color(c) {
//         cout << "name: " << _name << endl;
//         cout << "Color: " << pColor[_color] << endl;
//     }
//     ~Animal() {
//         cout << "destructing Animal object "<< _name << endl ;
//     }

//     virtual void speak() const {
//         cout << "Animal speaks "<< endl ;
//     }

//     virtual void move() const =0;

//     string getName () const {
//         return _name;
//     }

//     COLOR getColor(){
//         return _color;
//     }
//     private :
//         string _name;
//         COLOR _color ;
// };

// class Mammal : public Animal{
//     public : 
//     Mammal(string n, COLOR c) : Animal(n,c){

//     }
//     ~Mammal() {

//     }
//     void eat() const {
//         cout << "Mammal "<< Animal::getName() <<" eat " << endl ;
//     }
//     void move() const{
//         cout << "Mammal "<< Animal::getName() <<" moves "<< endl ;
//     }
// };

// class Dog : public Mammal{
//     public : 
//     Dog(string n, COLOR c, string o) : Mammal(n,c), _owner(o){
//         cout << "owner: " << _owner << endl;
//     }
//     ~Dog() {

//     }

//     void speak() const {
//         cout << "Dog "<< Animal::getName() <<" Woof "<< endl ;
//     }

//     void move() const{
//         cout << "Dog "<< Animal::getName() <<" moves "<< endl ;
//     }

//     private :
//         string _owner;

// };

// class Cat : public Mammal{
//     public : 
//     Cat(string n, COLOR c) : Mammal(n,c){

//     }
//     ~Cat() {

//     }

//     void speak() const {
//         cout << "Cat "<< Animal::getName() <<" meow"<< endl ;
//     }

//     void move() const{
//         cout << "Cat "<< Animal::getName() <<" moves "<< endl ;
//     }

// };

// class Lion : public Mammal{
//     public : 
//     Lion(string n, COLOR c) : Mammal(n,c){

//     }
//     ~Lion() {

//     }

//     void speak() const {
//         cout << "Lion "<< Animal::getName() <<" Roar"<< endl ;
//     }

//     void move() const{
//         cout << "Lion "<< Animal::getName() <<" moves "<< endl ;
//     }

// };

// int main() {
//     // Animal a("Tevin", Brown);
//     // a.speak() ;


//     // Mammal m("tevin", Green);
//     // m.speak();

//     // Dog d("TEVIN", White, "Noel");
//     // d.speak();
//     // d.move();



//     // Dog dogi("Lassie", White, "Andy");
//     // Mammal *aniPtr = &dogi ;
//     // Mammal &aniRef = dogi ;
//     // Mammal aniVal = dogi ;
//     // aniPtr->speak() ;
//     // aniRef.speak() ;
//     // aniVal.speak() ;

//     int x;
//     vector<Mammal *> zoo;
//     do{
//         cout << "Select the animal to send to Zoo: " << endl;
//         cout << "(1)Dog (2)Cat (3)Lion (4)Move all animals (5)Quit" << endl;
//         cout << "Enter your option: ";
//         cin >> x;
//         string name;
//         string color;
//         Mammal *m;
//         COLOR c;
//         switch (x)
//         {
//             case 1:
//                 cout << "Enter dog's name: ";
//                 cin >> name;
//                 cout << "Enter dog's color: ";
//                 cin >> color;
//                 c = convert(color);
//                 // Dog dog(name, c, "Zoo");
//                 m = new Dog(name, c, "Zoo") ;
//                 zoo.push_back(m);

//                 break;
//             case 2:
//                 cout << "Enter cat's name: ";
//                 cin >> name;
//                 cout << "Enter cat's color: ";
//                 cin >> color;
//                 c = convert(color);
//                 // Dog dog(name, c, "Zoo");
//                 m = new Cat(name, c) ;
//                 zoo.push_back(m);
//                 break;
//             case 3:
//                 cout << "Enter Lion's name: ";
//                 cin >> name;
//                 cout << "Enter Lion's color: ";
//                 cin >> color;
//                 c = convert(color);
//                 // Dog dog(name, c, "Zoo");
//                 m = new Lion(name, c) ;
//                 zoo.push_back(m);
//                 break;
//             case 4:
//                 for(int i = 0; i < zoo.size() ; i++){
//                     zoo[i]->move();
//                     zoo[i]->speak();
//                     zoo[i]->eat();
//                 }
//                 x = 5;
//                 break;
//             case 5:
//                 cout << "quit sending" << endl;
//                 break;
//             default:
//                 cout << "pls enter correct option" << endl;
//                 break;
//         }
//         if (x == 5){
//             break;
//         }

//     } while (true);

//     cout << "Program exiting..."<< endl ;
//     return 0;
// }
