PROGRAM Example3(cats, dogs);

USES math, manyCats IN './manyCats.pas';

VAR
age : INTEGER;
cat : CHAR = 'c';
dog : BOOLEAN = 1;
length : REAL;

FUNCTION f(a : INTEGER) : CHAR;
BEGIN
   IF a = 6 THEN Writeln(cat)
END;

FUNCTION h(a : INTEGER; b : REAL) : CHAR;
BEGIN
   f(9);
   IF a < 10 THEN f(b) ELSE f(b)
END;

BEGIN
    f(8);

    FOR i := 1 TO 10
    DO BEGIN
            Writeln(i);
            i /= 5
       END;

    FOR i := 10 DOWNTO 1
    DO REPEAT
            h(age, length);
            dog := NOT dog
       UNTIL g <= 1
END.
