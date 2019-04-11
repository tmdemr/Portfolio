#include <iostream>
#include <vector>

int main(int args, char *argv[]){
  using Matrix = std::vector<std::vector<double>>;
  void print(const Matrix& m) {
    for (const std::vector<double>& row : m) { // For each row​
      for (int elem : row) // For each element in a row​
        std::cout << std::setw(5) << elem;
      std::cout << '\n';
    }

}
