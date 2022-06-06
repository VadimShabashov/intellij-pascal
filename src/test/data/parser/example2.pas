PROGRAM Example2();

VAR
s : INTEGER;

PROCEDURE f;
BEGIN
  Writeln ('First');
  Writeln ('Second')
END;

PROCEDURE g;
BEGIN
  Writeln ('cats')
END;

BEGIN
    s := 10;
    g;

    WHILE (s <= 10)
    DO
        f;
        BEGIN
            REPEAT s -= 1 AND (6 * 7)
            UNTIL (s > 0)
        END
END.
