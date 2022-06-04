PROGRAM Example2();

PROCEDURE f;
BEGIN
  Writeln ('First params', p1);
  Writeln ('Second params', p2, p3)
END

PROCEDURE g;
BEGIN
  Writeln ('cats')
END

BEGIN
    s := 0;
    g;
    WHILE (s < 10)
    DO
        BEGIN
            REPEAT s := s AND (6 * 7);
            UNTIL (s > 0)
        END
END.
