class Triangle:
    def __init__(self, a: int, b: int, c: int):
        if a <= 0 or b <= 0 or c <= 0:
            raise ValueError("All sides must be at least greater than 0")
        if a + b <= c or a + c <= b or b + c <= a:
            raise ValueError("Any two sides must be greater than the length of the third side.")

        self.a = a
        self.b = b
        self.c = c

    def calculate_perimeter(self) -> int:
        return self.a + self.b + self.c

    def is_right_triangle(self) -> bool:
        if (self.a**2 + self.b**2 == self.c**2 or self.a**2 + self.c**2 == self.b**2 or self.b**2 + self.c**2 == self.a**2) :
            return True
        return False

triangle = Triangle(1, 2, 3)
print(triangle.calculate_perimeter())
print(triangle.is_right_triangle())
