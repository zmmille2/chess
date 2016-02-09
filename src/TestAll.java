import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import Tests.BishopTest;
import Tests.BoardTest;
import Tests.CamelTest;
import Tests.ChessTest;
import Tests.KingTest;
import Tests.KnightTest;
import Tests.PawnTest;
import Tests.PieceTest;
import Tests.QueenTest;
import Tests.RookTest;
import Tests.SquirrelTest;


@RunWith(Suite.class)
@SuiteClasses({ BoardTest.class, KingTest.class, KnightTest.class, PawnTest.class, QueenTest.class,
		BishopTest.class, RookTest.class, PieceTest.class, ChessTest.class, CamelTest.class, SquirrelTest.class})
public class TestAll {
}