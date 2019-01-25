// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   CreateTableParserTokenManager.java

package com.fitechlabs.dbind.gen.parser;

import java.io.IOException;
import java.io.PrintStream;

// Referenced classes of package com.fitechlabs.dbind.gen.parser:
//            TokenMgrError, CreateTableParserConstants, SimpleCharStream, Token

public class CreateTableParserTokenManager
    implements CreateTableParserConstants
{

    public void setDebugStream(PrintStream ds)
    {
        debugStream = ds;
    }

    private final int jjStopStringLiteralDfa_0(int pos, long active0)
    {
        switch(pos)
        {
        case 0: // '\0'
            if((active0 & 65504L) != 0L)
            {
                jjmatchedKind = 16;
                return 1;
            } else
            {
                return -1;
            }

        case 1: // '\001'
            if((active0 & 65504L) != 0L)
            {
                jjmatchedKind = 16;
                jjmatchedPos = 1;
                return 1;
            } else
            {
                return -1;
            }

        case 2: // '\002'
            if((active0 & 65376L) != 0L)
            {
                jjmatchedKind = 16;
                jjmatchedPos = 2;
                return 1;
            }
            return (active0 & 128L) == 0L ? -1 : 1;

        case 3: // '\003'
            if((active0 & 32352L) != 0L)
            {
                jjmatchedKind = 16;
                jjmatchedPos = 3;
                return 1;
            }
            return (active0 & 33024L) == 0L ? -1 : 1;

        case 4: // '\004'
            if((active0 & 32288L) != 0L)
            {
                jjmatchedKind = 16;
                jjmatchedPos = 4;
                return 1;
            }
            return (active0 & 64L) == 0L ? -1 : 1;

        case 5: // '\005'
            if((active0 & 31232L) != 0L)
            {
                jjmatchedKind = 16;
                jjmatchedPos = 5;
                return 1;
            }
            return (active0 & 1056L) == 0L ? -1 : 1;

        case 6: // '\006'
            if((active0 & 16896L) != 0L)
            {
                jjmatchedKind = 16;
                jjmatchedPos = 6;
                return 1;
            }
            return (active0 & 14336L) == 0L ? -1 : 1;

        case 7: // '\007'
            if((active0 & 16896L) != 0L)
            {
                jjmatchedKind = 16;
                jjmatchedPos = 7;
                return 1;
            } else
            {
                return -1;
            }

        case 8: // '\b'
            if((active0 & 16896L) != 0L)
            {
                jjmatchedKind = 16;
                jjmatchedPos = 8;
                return 1;
            } else
            {
                return -1;
            }
        }
        return -1;
    }

    private final int jjStartNfa_0(int pos, long active0)
    {
        return jjMoveNfa_0(jjStopStringLiteralDfa_0(pos, active0), pos + 1);
    }

    private final int jjStopAtPos(int pos, int kind)
    {
        jjmatchedKind = kind;
        jjmatchedPos = pos;
        return pos + 1;
    }

    private final int jjStartNfaWithStates_0(int pos, int kind, int state)
    {
        jjmatchedKind = kind;
        jjmatchedPos = pos;
        try
        {
            curChar = input_stream.readChar();
        }
        catch(IOException e)
        {
            return pos + 1;
        }
        return jjMoveNfa_0(state, pos + 1);
    }

    private final int jjMoveStringLiteralDfa0_0()
    {
        switch(curChar)
        {
        case 40: // '('
            return jjStopAtPos(0, 20);

        case 41: // ')'
            return jjStopAtPos(0, 21);

        case 44: // ','
            return jjStopAtPos(0, 22);

        case 67: // 'C'
        case 99: // 'c'
            return jjMoveStringLiteralDfa1_0(544L);

        case 68: // 'D'
        case 100: // 'd'
            return jjMoveStringLiteralDfa1_0(4096L);

        case 70: // 'F'
        case 102: // 'f'
            return jjMoveStringLiteralDfa1_0(8192L);

        case 76: // 'L'
        case 108: // 'l'
            return jjMoveStringLiteralDfa1_0(32768L);

        case 78: // 'N'
        case 110: // 'n'
            return jjMoveStringLiteralDfa1_0(384L);

        case 80: // 'P'
        case 112: // 'p'
            return jjMoveStringLiteralDfa1_0(2048L);

        case 82: // 'R'
        case 114: // 'r'
            return jjMoveStringLiteralDfa1_0(16384L);

        case 84: // 'T'
        case 116: // 't'
            return jjMoveStringLiteralDfa1_0(64L);

        case 85: // 'U'
        case 117: // 'u'
            return jjMoveStringLiteralDfa1_0(1024L);

        case 42: // '*'
        case 43: // '+'
        case 45: // '-'
        case 46: // '.'
        case 47: // '/'
        case 48: // '0'
        case 49: // '1'
        case 50: // '2'
        case 51: // '3'
        case 52: // '4'
        case 53: // '5'
        case 54: // '6'
        case 55: // '7'
        case 56: // '8'
        case 57: // '9'
        case 58: // ':'
        case 59: // ';'
        case 60: // '<'
        case 61: // '='
        case 62: // '>'
        case 63: // '?'
        case 64: // '@'
        case 65: // 'A'
        case 66: // 'B'
        case 69: // 'E'
        case 71: // 'G'
        case 72: // 'H'
        case 73: // 'I'
        case 74: // 'J'
        case 75: // 'K'
        case 77: // 'M'
        case 79: // 'O'
        case 81: // 'Q'
        case 83: // 'S'
        case 86: // 'V'
        case 87: // 'W'
        case 88: // 'X'
        case 89: // 'Y'
        case 90: // 'Z'
        case 91: // '['
        case 92: // '\\'
        case 93: // ']'
        case 94: // '^'
        case 95: // '_'
        case 96: // '`'
        case 97: // 'a'
        case 98: // 'b'
        case 101: // 'e'
        case 103: // 'g'
        case 104: // 'h'
        case 105: // 'i'
        case 106: // 'j'
        case 107: // 'k'
        case 109: // 'm'
        case 111: // 'o'
        case 113: // 'q'
        case 115: // 's'
        default:
            return jjMoveNfa_0(0, 0);
        }
    }

    private final int jjMoveStringLiteralDfa1_0(long active0)
    {
        try
        {
            curChar = input_stream.readChar();
        }
        catch(IOException e)
        {
            jjStopStringLiteralDfa_0(0, active0);
            return 1;
        }
        switch(curChar)
        {
        case 65: // 'A'
        case 97: // 'a'
            return jjMoveStringLiteralDfa2_0(active0, 64L);

        case 69: // 'E'
        case 101: // 'e'
            return jjMoveStringLiteralDfa2_0(active0, 20480L);

        case 78: // 'N'
        case 110: // 'n'
            return jjMoveStringLiteralDfa2_0(active0, 1024L);

        case 79: // 'O'
        case 111: // 'o'
            return jjMoveStringLiteralDfa2_0(active0, 41600L);

        case 82: // 'R'
        case 114: // 'r'
            return jjMoveStringLiteralDfa2_0(active0, 2080L);

        case 85: // 'U'
        case 117: // 'u'
            return jjMoveStringLiteralDfa2_0(active0, 256L);
        }
        return jjStartNfa_0(0, active0);
    }

    private final int jjMoveStringLiteralDfa2_0(long old0, long active0)
    {
        if((active0 &= old0) == 0L)
            return jjStartNfa_0(0, old0);
        try
        {
            curChar = input_stream.readChar();
        }
        catch(IOException e)
        {
            jjStopStringLiteralDfa_0(1, active0);
            return 2;
        }
        switch(curChar)
        {
        case 66: // 'B'
        case 98: // 'b'
            return jjMoveStringLiteralDfa3_0(active0, 64L);

        case 69: // 'E'
        case 101: // 'e'
            return jjMoveStringLiteralDfa3_0(active0, 32L);

        case 70: // 'F'
        case 102: // 'f'
            return jjMoveStringLiteralDfa3_0(active0, 20480L);

        case 73: // 'I'
        case 105: // 'i'
            return jjMoveStringLiteralDfa3_0(active0, 3072L);

        case 76: // 'L'
        case 108: // 'l'
            return jjMoveStringLiteralDfa3_0(active0, 256L);

        case 78: // 'N'
        case 110: // 'n'
            return jjMoveStringLiteralDfa3_0(active0, 33280L);

        case 82: // 'R'
        case 114: // 'r'
            return jjMoveStringLiteralDfa3_0(active0, 8192L);

        case 84: // 'T'
        case 116: // 't'
            if((active0 & 128L) != 0L)
                return jjStartNfaWithStates_0(2, 7, 1);
            // fall through

        case 67: // 'C'
        case 68: // 'D'
        case 71: // 'G'
        case 72: // 'H'
        case 74: // 'J'
        case 75: // 'K'
        case 77: // 'M'
        case 79: // 'O'
        case 80: // 'P'
        case 81: // 'Q'
        case 83: // 'S'
        case 85: // 'U'
        case 86: // 'V'
        case 87: // 'W'
        case 88: // 'X'
        case 89: // 'Y'
        case 90: // 'Z'
        case 91: // '['
        case 92: // '\\'
        case 93: // ']'
        case 94: // '^'
        case 95: // '_'
        case 96: // '`'
        case 97: // 'a'
        case 99: // 'c'
        case 100: // 'd'
        case 103: // 'g'
        case 104: // 'h'
        case 106: // 'j'
        case 107: // 'k'
        case 109: // 'm'
        case 111: // 'o'
        case 112: // 'p'
        case 113: // 'q'
        case 115: // 's'
        default:
            return jjStartNfa_0(1, active0);
        }
    }

    private final int jjMoveStringLiteralDfa3_0(long old0, long active0)
    {
        if((active0 &= old0) == 0L)
            return jjStartNfa_0(1, old0);
        try
        {
            curChar = input_stream.readChar();
        }
        catch(IOException e)
        {
            jjStopStringLiteralDfa_0(2, active0);
            return 3;
        }
        switch(curChar)
        {
        case 66: // 'B'
        case 67: // 'C'
        case 68: // 'D'
        case 70: // 'F'
        case 72: // 'H'
        case 73: // 'I'
        case 74: // 'J'
        case 75: // 'K'
        case 78: // 'N'
        case 79: // 'O'
        case 80: // 'P'
        case 82: // 'R'
        case 84: // 'T'
        case 85: // 'U'
        case 86: // 'V'
        case 87: // 'W'
        case 88: // 'X'
        case 89: // 'Y'
        case 90: // 'Z'
        case 91: // '['
        case 92: // '\\'
        case 93: // ']'
        case 94: // '^'
        case 95: // '_'
        case 96: // '`'
        case 98: // 'b'
        case 99: // 'c'
        case 100: // 'd'
        case 102: // 'f'
        case 104: // 'h'
        case 105: // 'i'
        case 106: // 'j'
        case 107: // 'k'
        case 110: // 'n'
        case 111: // 'o'
        case 112: // 'p'
        case 114: // 'r'
        default:
            break;

        case 65: // 'A'
        case 97: // 'a'
            return jjMoveStringLiteralDfa4_0(active0, 4128L);

        case 69: // 'E'
        case 101: // 'e'
            return jjMoveStringLiteralDfa4_0(active0, 24576L);

        case 71: // 'G'
        case 103: // 'g'
            if((active0 & 32768L) != 0L)
                return jjStartNfaWithStates_0(3, 15, 1);
            break;

        case 76: // 'L'
        case 108: // 'l'
            if((active0 & 256L) != 0L)
                return jjStartNfaWithStates_0(3, 8, 1);
            else
                return jjMoveStringLiteralDfa4_0(active0, 64L);

        case 77: // 'M'
        case 109: // 'm'
            return jjMoveStringLiteralDfa4_0(active0, 2048L);

        case 81: // 'Q'
        case 113: // 'q'
            return jjMoveStringLiteralDfa4_0(active0, 1024L);

        case 83: // 'S'
        case 115: // 's'
            return jjMoveStringLiteralDfa4_0(active0, 512L);
        }
        return jjStartNfa_0(2, active0);
    }

    private final int jjMoveStringLiteralDfa4_0(long old0, long active0)
    {
        if((active0 &= old0) == 0L)
            return jjStartNfa_0(2, old0);
        try
        {
            curChar = input_stream.readChar();
        }
        catch(IOException e)
        {
            jjStopStringLiteralDfa_0(3, active0);
            return 4;
        }
        switch(curChar)
        {
        case 65: // 'A'
        case 97: // 'a'
            return jjMoveStringLiteralDfa5_0(active0, 2048L);

        case 69: // 'E'
        case 101: // 'e'
            if((active0 & 64L) != 0L)
                return jjStartNfaWithStates_0(4, 6, 1);
            break;

        case 73: // 'I'
        case 105: // 'i'
            return jjMoveStringLiteralDfa5_0(active0, 8192L);

        case 82: // 'R'
        case 114: // 'r'
            return jjMoveStringLiteralDfa5_0(active0, 16384L);

        case 84: // 'T'
        case 116: // 't'
            return jjMoveStringLiteralDfa5_0(active0, 544L);

        case 85: // 'U'
        case 117: // 'u'
            return jjMoveStringLiteralDfa5_0(active0, 5120L);
        }
        return jjStartNfa_0(3, active0);
    }

    private final int jjMoveStringLiteralDfa5_0(long old0, long active0)
    {
        if((active0 &= old0) == 0L)
            return jjStartNfa_0(3, old0);
        try
        {
            curChar = input_stream.readChar();
        }
        catch(IOException e)
        {
            jjStopStringLiteralDfa_0(4, active0);
            return 5;
        }
        switch(curChar)
        {
        case 69: // 'E'
        case 101: // 'e'
            if((active0 & 32L) != 0L)
                return jjStartNfaWithStates_0(5, 5, 1);
            if((active0 & 1024L) != 0L)
                return jjStartNfaWithStates_0(5, 10, 1);
            else
                return jjMoveStringLiteralDfa6_0(active0, 16384L);

        case 71: // 'G'
        case 103: // 'g'
            return jjMoveStringLiteralDfa6_0(active0, 8192L);

        case 76: // 'L'
        case 108: // 'l'
            return jjMoveStringLiteralDfa6_0(active0, 4096L);

        case 82: // 'R'
        case 114: // 'r'
            return jjMoveStringLiteralDfa6_0(active0, 2560L);
        }
        return jjStartNfa_0(4, active0);
    }

    private final int jjMoveStringLiteralDfa6_0(long old0, long active0)
    {
        if((active0 &= old0) == 0L)
            return jjStartNfa_0(4, old0);
        try
        {
            curChar = input_stream.readChar();
        }
        catch(IOException e)
        {
            jjStopStringLiteralDfa_0(5, active0);
            return 6;
        }
        switch(curChar)
        {
        default:
            break;

        case 65: // 'A'
        case 97: // 'a'
            return jjMoveStringLiteralDfa7_0(active0, 512L);

        case 78: // 'N'
        case 110: // 'n'
            if((active0 & 8192L) != 0L)
                return jjStartNfaWithStates_0(6, 13, 1);
            else
                return jjMoveStringLiteralDfa7_0(active0, 16384L);

        case 84: // 'T'
        case 116: // 't'
            if((active0 & 4096L) != 0L)
                return jjStartNfaWithStates_0(6, 12, 1);
            break;

        case 89: // 'Y'
        case 121: // 'y'
            if((active0 & 2048L) != 0L)
                return jjStartNfaWithStates_0(6, 11, 1);
            break;
        }
        return jjStartNfa_0(5, active0);
    }

    private final int jjMoveStringLiteralDfa7_0(long old0, long active0)
    {
        if((active0 &= old0) == 0L)
            return jjStartNfa_0(5, old0);
        try
        {
            curChar = input_stream.readChar();
        }
        catch(IOException e)
        {
            jjStopStringLiteralDfa_0(6, active0);
            return 7;
        }
        switch(curChar)
        {
        case 67: // 'C'
        case 99: // 'c'
            return jjMoveStringLiteralDfa8_0(active0, 16384L);

        case 73: // 'I'
        case 105: // 'i'
            return jjMoveStringLiteralDfa8_0(active0, 512L);
        }
        return jjStartNfa_0(6, active0);
    }

    private final int jjMoveStringLiteralDfa8_0(long old0, long active0)
    {
        if((active0 &= old0) == 0L)
            return jjStartNfa_0(6, old0);
        try
        {
            curChar = input_stream.readChar();
        }
        catch(IOException e)
        {
            jjStopStringLiteralDfa_0(7, active0);
            return 8;
        }
        switch(curChar)
        {
        case 69: // 'E'
        case 101: // 'e'
            return jjMoveStringLiteralDfa9_0(active0, 16384L);

        case 78: // 'N'
        case 110: // 'n'
            return jjMoveStringLiteralDfa9_0(active0, 512L);
        }
        return jjStartNfa_0(7, active0);
    }

    private final int jjMoveStringLiteralDfa9_0(long old0, long active0)
    {
        if((active0 &= old0) == 0L)
            return jjStartNfa_0(7, old0);
        try
        {
            curChar = input_stream.readChar();
        }
        catch(IOException e)
        {
            jjStopStringLiteralDfa_0(8, active0);
            return 9;
        }
        switch(curChar)
        {
        default:
            break;

        case 83: // 'S'
        case 115: // 's'
            if((active0 & 16384L) != 0L)
                return jjStartNfaWithStates_0(9, 14, 1);
            break;

        case 84: // 'T'
        case 116: // 't'
            if((active0 & 512L) != 0L)
                return jjStartNfaWithStates_0(9, 9, 1);
            break;
        }
        return jjStartNfa_0(8, active0);
    }

    private final void jjCheckNAdd(int state)
    {
        if(jjrounds[state] != jjround)
        {
            jjstateSet[jjnewStateCnt++] = state;
            jjrounds[state] = jjround;
        }
    }

    private final void jjAddStates(int start, int end)
    {
        do
            jjstateSet[jjnewStateCnt++] = jjnextStates[start];
        while(start++ != end);
    }

    private final void jjCheckNAddTwoStates(int state1, int state2)
    {
        jjCheckNAdd(state1);
        jjCheckNAdd(state2);
    }

    private final void jjCheckNAddStates(int start, int end)
    {
        do
            jjCheckNAdd(jjnextStates[start]);
        while(start++ != end);
    }

    private final void jjCheckNAddStates(int start)
    {
        jjCheckNAdd(jjnextStates[start]);
        jjCheckNAdd(jjnextStates[start + 1]);
    }

    private final int jjMoveNfa_0(int startState, int curPos)
    {
        int startsAt = 0;
        jjnewStateCnt = 11;
        int i = 1;
        jjstateSet[0] = startState;
        int kind = 0x7fffffff;
        do
        {
            if(++jjround == 0x7fffffff)
                ReInitRounds();
            if(curChar < '@')
            {
                long l = 1L << curChar;
                do
                    switch(jjstateSet[--i])
                    {
                    case 0: // '\0'
                        if((0x3fe000000000000L & l) != 0L)
                        {
                            if(kind > 17)
                                kind = 17;
                            jjCheckNAddStates(0, 2);
                        } else
                        if(curChar == '0')
                        {
                            if(kind > 17)
                                kind = 17;
                            jjCheckNAdd(6);
                        } else
                        if(curChar == '\'')
                            jjCheckNAddTwoStates(3, 4);
                        break;

                    case 1: // '\001'
                        if((0x3ff000000000000L & l) != 0L)
                        {
                            if(kind > 16)
                                kind = 16;
                            jjstateSet[jjnewStateCnt++] = 1;
                        }
                        break;

                    case 2: // '\002'
                        if(curChar == '\'')
                            jjCheckNAddTwoStates(3, 4);
                        break;

                    case 3: // '\003'
                        if((0xffffff7fffffffffL & l) != 0L)
                            jjCheckNAddTwoStates(3, 4);
                        break;

                    case 4: // '\004'
                        if(curChar == '\'' && kind > 19)
                            kind = 19;
                        break;

                    case 5: // '\005'
                        if(curChar == '0')
                        {
                            if(kind > 17)
                                kind = 17;
                            jjCheckNAdd(6);
                        }
                        break;

                    case 6: // '\006'
                        if(curChar == '.')
                            jjCheckNAdd(7);
                        break;

                    case 7: // '\007'
                        if((0x3ff000000000000L & l) != 0L)
                        {
                            if(kind > 18)
                                kind = 18;
                            jjCheckNAdd(7);
                        }
                        break;

                    case 8: // '\b'
                        if((0x3fe000000000000L & l) != 0L)
                        {
                            if(kind > 17)
                                kind = 17;
                            jjCheckNAddStates(0, 2);
                        }
                        break;

                    case 9: // '\t'
                        if((0x3ff000000000000L & l) != 0L)
                        {
                            if(kind > 17)
                                kind = 17;
                            jjCheckNAdd(9);
                        }
                        break;

                    case 10: // '\n'
                        if((0x3ff000000000000L & l) != 0L)
                            jjCheckNAddTwoStates(10, 6);
                        break;
                    }
                while(i != startsAt);
            } else
            if(curChar < '\200')
            {
                long l = 1L << (curChar & 0x3f);
                do
                    switch(jjstateSet[--i])
                    {
                    case 0: // '\0'
                    case 1: // '\001'
                        if((0x7fffffe87fffffeL & l) != 0L)
                        {
                            if(kind > 16)
                                kind = 16;
                            jjCheckNAdd(1);
                        }
                        break;

                    case 3: // '\003'
                        jjAddStates(3, 4);
                        break;
                    }
                while(i != startsAt);
            } else
            {
                int i2 = (curChar & 0xff) >> 6;
                long l2 = 1L << (curChar & 0x3f);
                do
                    switch(jjstateSet[--i])
                    {
                    case 3: // '\003'
                        if((jjbitVec0[i2] & l2) != 0L)
                            jjAddStates(3, 4);
                        break;
                    }
                while(i != startsAt);
            }
            if(kind != 0x7fffffff)
            {
                jjmatchedKind = kind;
                jjmatchedPos = curPos;
                kind = 0x7fffffff;
            }
            curPos++;
            if((i = jjnewStateCnt) == (startsAt = 11 - (jjnewStateCnt = startsAt)))
                return curPos;
            try
            {
                curChar = input_stream.readChar();
            }
            catch(IOException e)
            {
                return curPos;
            }
        } while(true);
    }

    public CreateTableParserTokenManager(SimpleCharStream stream)
    {
        debugStream = System.out;
        jjrounds = new int[11];
        jjstateSet = new int[22];
        curLexState = 0;
        defaultLexState = 0;
        input_stream = stream;
    }

    public CreateTableParserTokenManager(SimpleCharStream stream, int lexState)
    {
        this(stream);
        SwitchTo(lexState);
    }

    public void ReInit(SimpleCharStream stream)
    {
        jjmatchedPos = jjnewStateCnt = 0;
        curLexState = defaultLexState;
        input_stream = stream;
        ReInitRounds();
    }

    private final void ReInitRounds()
    {
        jjround = 0x80000001;
        for(int i = 11; i-- > 0;)
            jjrounds[i] = 0x80000000;

    }

    public void ReInit(SimpleCharStream stream, int lexState)
    {
        ReInit(stream);
        SwitchTo(lexState);
    }

    public void SwitchTo(int lexState)
    {
        if(lexState >= 1 || lexState < 0)
        {
            throw new TokenMgrError("Error: Ignoring invalid lexical state : " + lexState + ". State unchanged.", 2);
        } else
        {
            curLexState = lexState;
            return;
        }
    }

    protected Token jjFillToken()
    {
        Token t = Token.newToken(jjmatchedKind);
        t.kind = jjmatchedKind;
        String im = jjstrLiteralImages[jjmatchedKind];
        t.image = im != null ? im : input_stream.GetImage();
        t.beginLine = input_stream.getBeginLine();
        t.beginColumn = input_stream.getBeginColumn();
        t.endLine = input_stream.getEndLine();
        t.endColumn = input_stream.getEndColumn();
        return t;
    }

    public Token getNextToken()
    {
        Token specialToken = null;
        int curPos = 0;
        do
        {
            do
            {
                try
                {
                    curChar = input_stream.BeginToken();
                }
                catch(IOException e)
                {
                    jjmatchedKind = 0;
                    Token matchedToken = jjFillToken();
                    return matchedToken;
                }
                try
                {
                    input_stream.backup(0);
                    for(; curChar <= ' ' && (0x100002600L & 1L << curChar) != 0L; curChar = input_stream.BeginToken());
                    break;
                }
                catch(IOException e1) { }
            } while(true);
            jjmatchedKind = 0x7fffffff;
            jjmatchedPos = 0;
            curPos = jjMoveStringLiteralDfa0_0();
            if(jjmatchedKind != 0x7fffffff)
            {
                if(jjmatchedPos + 1 < curPos)
                    input_stream.backup(curPos - jjmatchedPos - 1);
                if((jjtoToken[jjmatchedKind >> 6] & 1L << (jjmatchedKind & 0x3f)) != 0L)
                {
                    Token matchedToken = jjFillToken();
                    return matchedToken;
                }
            } else
            {
                int error_line = input_stream.getEndLine();
                int error_column = input_stream.getEndColumn();
                String error_after = null;
                boolean EOFSeen = false;
                try
                {
                    input_stream.readChar();
                    input_stream.backup(1);
                }
                catch(IOException e1)
                {
                    EOFSeen = true;
                    error_after = curPos > 1 ? input_stream.GetImage() : "";
                    if(curChar == '\n' || curChar == '\r')
                    {
                        error_line++;
                        error_column = 0;
                    } else
                    {
                        error_column++;
                    }
                }
                if(!EOFSeen)
                {
                    input_stream.backup(1);
                    error_after = curPos > 1 ? input_stream.GetImage() : "";
                }
                throw new TokenMgrError(EOFSeen, curLexState, error_line, error_column, error_after, curChar, 0);
            }
        } while(true);
    }

    public PrintStream debugStream;
    static final long jjbitVec0[] = {
        0L, 0L, -1L, -1L
    };
    static final int jjnextStates[] = {
        9, 10, 6, 3, 4
    };
    public static final String jjstrLiteralImages[] = {
        "", null, null, null, null, null, null, null, null, null, 
        null, null, null, null, null, null, null, null, null, null, 
        "(", ")", ","
    };
    public static final String lexStateNames[] = {
        "DEFAULT"
    };
    static final long jjtoToken[] = {
        0x7fffe1L
    };
    static final long jjtoSkip[] = {
        30L
    };
    protected SimpleCharStream input_stream;
    private final int jjrounds[];
    private final int jjstateSet[];
    protected char curChar;
    int curLexState;
    int defaultLexState;
    int jjnewStateCnt;
    int jjround;
    int jjmatchedPos;
    int jjmatchedKind;

}
