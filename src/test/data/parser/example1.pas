PROGRAM Example1(i1, i2);

VAR
a : BOOLEAN = TRUE;
b : INTEGER = 2;
c : INTEGER;

BEGIN
    IF (a = 0)
    THEN c := (5 + a) * b
    ELSE IF b < 0
         THEN c := 8 * 6 + (5 + 3) * (6 AND 9)
         ELSE c := 7 OR (6 + 8)
END.
