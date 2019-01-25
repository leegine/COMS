// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   CreateTableParser.java

package com.fitechlabs.dbind.gen.parser;

import java.io.*;
import java.util.*;

// Referenced classes of package com.fitechlabs.dbind.gen.parser:
//            ParseException, SimpleCharStream, CreateTableParserTokenManager, Token, 
//            CreateTableParserConstants

public class CreateTableParser
    implements CreateTableParserConstants
{
    public static class FK
    {

        public String toString()
        {
            String s = "fk (";
            for(Iterator it = columns.iterator(); it.hasNext();)
                s = s + it.next() + ",";

            s = s + ") ";
            s = s + parentName + " (";
            for(Iterator it = parentColumns.iterator(); it.hasNext();)
                s = s + it.next() + ",";

            s = s + ")";
            return s;
        }

        public String constraintName;
        public String parentName;
        public List columns;
        public List parentColumns;

        public FK()
        {
            constraintName = null;
            parentName = null;
            columns = new ArrayList();
            parentColumns = new ArrayList();
        }
    }

    public static class Column
    {

        public String toString()
        {
            return name + " " + type + "(" + precision + "," + scale + ") " + (hasDefault ? " DEFAULT xxx " : " ") + (nullable != Boolean.FALSE ? " NULL" : " NOT NULL");
        }

        public String name;
        public String type;
        public Boolean nullable;
        public Integer precision;
        public Integer scale;
        public boolean hasDefault;

        public Column()
        {
            name = null;
            type = null;
            nullable = null;
            precision = null;
            scale = null;
            hasDefault = false;
        }
    }

    public static class Table
    {

        public String toString()
        {
            String s = "create table " + name + "(\n";
            for(Iterator it = columns.iterator(); it.hasNext();)
                s = s + "\t" + it.next() + "\n";

            s = s + ")\n";
            s = s + "PK (";
            for(Iterator it = pkColumns.iterator(); it.hasNext();)
                s = s + it.next() + ",";

            s = s + ")\n";
            for(Iterator it = fks.iterator(); it.hasNext();)
                s = s + it.next() + "\n";

            return s;
        }

        public String name;
        public List pkColumns;
        public List columns;
        public List fks;

        public Table()
        {
            name = null;
            pkColumns = new ArrayList();
            columns = new ArrayList();
            fks = new ArrayList();
        }
    }


    public static Table handle(String statement)
        throws ParseException
    {
        CreateTableParser parser = new CreateTableParser(new StringReader(statement));
        return parser.relationalTable();
    }

    public final Table relationalTable()
        throws ParseException
    {
        Table table = new Table();
        jj_consume_token(5);
        jj_consume_token(6);
        Token tName = jj_consume_token(16);
        jj_consume_token(20);
        relationalProperties(table);
        jj_consume_token(21);
        table.name = tName.image;
        return table;
    }

    public final void relationalProperties(Table table)
        throws ParseException
    {
        relationalProperty(table);
label0:
        do
            switch(jj_ntk != -1 ? jj_ntk : jj_ntk())
            {
            default:
                jj_la1[0] = jj_gen;
                break label0;

            case 22: // '\026'
                jj_consume_token(22);
                relationalProperty(table);
                break;
            }
        while(true);
    }

    public final void relationalProperty(Table table)
        throws ParseException
    {
        switch(jj_ntk != -1 ? jj_ntk : jj_ntk())
        {
        case 16: // '\020'
            columnDefination(table);
            break;

        case 9: // '\t'
            tableOrViewConstraint(table);
            break;

        default:
            jj_la1[1] = jj_gen;
            jj_consume_token(-1);
            throw new ParseException();
        }
    }

    public final void columnDefination(Table table)
        throws ParseException
    {
        Column col = new Column();
        Token tName = jj_consume_token(16);
        dataType(col);
        switch(jj_ntk != -1 ? jj_ntk : jj_ntk())
        {
        case 12: // '\f'
            columnDefault(col);
            break;

        default:
            jj_la1[2] = jj_gen;
            break;
        }
        switch(jj_ntk != -1 ? jj_ntk : jj_ntk())
        {
        case 7: // '\007'
        case 8: // '\b'
            columnConstraint(col);
            break;

        default:
            jj_la1[3] = jj_gen;
            break;
        }
        col.name = tName.image;
        table.columns.add(col);
    }

    public final void dataType(Column col)
        throws ParseException
    {
        Token tPrecision = null;
        Token tScale = null;
        sqlType(col);
        switch(jj_ntk != -1 ? jj_ntk : jj_ntk())
        {
        case 20: // '\024'
            jj_consume_token(20);
            tPrecision = jj_consume_token(17);
            switch(jj_ntk != -1 ? jj_ntk : jj_ntk())
            {
            case 22: // '\026'
                jj_consume_token(22);
                tScale = jj_consume_token(17);
                break;

            default:
                jj_la1[4] = jj_gen;
                break;
            }
            jj_consume_token(21);
            break;

        default:
            jj_la1[5] = jj_gen;
            break;
        }
        if(tPrecision != null)
        {
            col.precision = new Integer(tPrecision.image);
            if(tScale != null)
                col.scale = new Integer(tScale.image);
        }
    }

    public final void sqlType(Column col)
        throws ParseException
    {
        Token tLong = null;
        switch(jj_ntk != -1 ? jj_ntk : jj_ntk())
        {
        case 15: // '\017'
            tLong = jj_consume_token(15);
            break;

        default:
            jj_la1[6] = jj_gen;
            break;
        }
        Token tType = jj_consume_token(16);
        if(tLong != null)
            col.type = tLong.image + " " + tType.image;
        else
            col.type = tType.image;
    }

    public final void columnDefault(Column col)
        throws ParseException
    {
        jj_consume_token(12);
        switch(jj_ntk != -1 ? jj_ntk : jj_ntk())
        {
        case 8: // '\b'
            jj_consume_token(8);
            break;

        case 16: // '\020'
        case 17: // '\021'
        case 18: // '\022'
        case 19: // '\023'
            expression();
            break;

        case 9: // '\t'
        case 10: // '\n'
        case 11: // '\013'
        case 12: // '\f'
        case 13: // '\r'
        case 14: // '\016'
        case 15: // '\017'
        default:
            jj_la1[7] = jj_gen;
            jj_consume_token(-1);
            throw new ParseException();
        }
        col.hasDefault = true;
    }

    public final void expression()
        throws ParseException
    {
        switch(jj_ntk != -1 ? jj_ntk : jj_ntk())
        {
        case 17: // '\021'
            jj_consume_token(17);
            break;

        case 18: // '\022'
            jj_consume_token(18);
            break;

        case 19: // '\023'
            jj_consume_token(19);
            break;

        case 16: // '\020'
            function();
            break;

        default:
            jj_la1[8] = jj_gen;
            jj_consume_token(-1);
            throw new ParseException();
        }
    }

    public final void function()
        throws ParseException
    {
        Token t = jj_consume_token(16);
        switch(jj_ntk != -1 ? jj_ntk : jj_ntk())
        {
        case 20: // '\024'
            jj_consume_token(20);
            expression();
label0:
            do
                switch(jj_ntk != -1 ? jj_ntk : jj_ntk())
                {
                default:
                    jj_la1[9] = jj_gen;
                    break label0;

                case 22: // '\026'
                    jj_consume_token(22);
                    expression();
                    break;
                }
            while(true);
            jj_consume_token(21);
            break;

        default:
            jj_la1[10] = jj_gen;
            break;
        }
    }

    public final void columnConstraint(Column col)
        throws ParseException
    {
        Token tNot = null;
        switch(jj_ntk != -1 ? jj_ntk : jj_ntk())
        {
        case 7: // '\007'
            tNot = jj_consume_token(7);
            break;

        default:
            jj_la1[11] = jj_gen;
            break;
        }
        jj_consume_token(8);
        if(tNot != null)
            col.nullable = Boolean.FALSE;
        else
            col.nullable = Boolean.TRUE;
    }

    public final void tableOrViewConstraint(Table table)
        throws ParseException
    {
        jj_consume_token(9);
        Token sName = jj_consume_token(16);
        switch(jj_ntk != -1 ? jj_ntk : jj_ntk())
        {
        case 10: // '\n'
            uniqueConstraint();
            break;

        case 11: // '\013'
            primaryKeyConstraint(table);
            break;

        case 13: // '\r'
            foreignKeyConstraint(table, sName);
            break;

        case 12: // '\f'
        default:
            jj_la1[12] = jj_gen;
            jj_consume_token(-1);
            throw new ParseException();
        }
    }

    public final void uniqueConstraint()
        throws ParseException
    {
        jj_consume_token(10);
        jj_consume_token(20);
        jj_consume_token(16);
label0:
        do
            switch(jj_ntk != -1 ? jj_ntk : jj_ntk())
            {
            default:
                jj_la1[13] = jj_gen;
                break label0;

            case 22: // '\026'
                jj_consume_token(22);
                jj_consume_token(16);
                break;
            }
        while(true);
        jj_consume_token(21);
    }

    public final void primaryKeyConstraint(Table table)
        throws ParseException
    {
        jj_consume_token(11);
        jj_consume_token(16);
        jj_consume_token(20);
        pkColumn(table);
label0:
        do
            switch(jj_ntk != -1 ? jj_ntk : jj_ntk())
            {
            default:
                jj_la1[14] = jj_gen;
                break label0;

            case 22: // '\026'
                jj_consume_token(22);
                pkColumn(table);
                break;
            }
        while(true);
        jj_consume_token(21);
    }

    public final void pkColumn(Table table)
        throws ParseException
    {
        Token sName = jj_consume_token(16);
        table.pkColumns.add(sName.image);
    }

    public final void foreignKeyConstraint(Table table, Token sName)
        throws ParseException
    {
        FK fk = new FK();
        fk.constraintName = sName.image;
        jj_consume_token(13);
        jj_consume_token(16);
        jj_consume_token(20);
        fkColumn(fk);
label0:
        do
            switch(jj_ntk != -1 ? jj_ntk : jj_ntk())
            {
            default:
                jj_la1[15] = jj_gen;
                break label0;

            case 22: // '\026'
                jj_consume_token(22);
                fkColumn(fk);
                break;
            }
        while(true);
        jj_consume_token(21);
        reference(fk);
        table.fks.add(fk);
    }

    public final void fkColumn(FK fk)
        throws ParseException
    {
        Token sName = jj_consume_token(16);
        fk.columns.add(sName.image);
    }

    public final void reference(FK fk)
        throws ParseException
    {
        jj_consume_token(14);
        Token sName = jj_consume_token(16);
        switch(jj_ntk != -1 ? jj_ntk : jj_ntk())
        {
        case 20: // '\024'
            jj_consume_token(20);
            fkParentColumn(fk);
label0:
            do
                switch(jj_ntk != -1 ? jj_ntk : jj_ntk())
                {
                default:
                    jj_la1[16] = jj_gen;
                    break label0;

                case 22: // '\026'
                    jj_consume_token(22);
                    fkParentColumn(fk);
                    break;
                }
            while(true);
            jj_consume_token(21);
            break;

        default:
            jj_la1[17] = jj_gen;
            break;
        }
        fk.parentName = sName.image;
    }

    public final void fkParentColumn(FK fk)
        throws ParseException
    {
        Token sName = jj_consume_token(16);
        fk.parentColumns.add(sName.image);
    }

    private static void jj_la1_0()
    {
        jj_la1_0 = (new int[] {
            0x400000, 0x10200, 4096, 384, 0x400000, 0x100000, 32768, 0xf0100, 0xf0000, 0x400000, 
            0x100000, 128, 11264, 0x400000, 0x400000, 0x400000, 0x400000, 0x100000
        });
    }

    public CreateTableParser(InputStream stream)
    {
        jj_la1 = new int[18];
        jj_expentries = new Vector();
        jj_kind = -1;
        jj_input_stream = new SimpleCharStream(stream, 1, 1);
        token_source = new CreateTableParserTokenManager(jj_input_stream);
        token = new Token();
        jj_ntk = -1;
        jj_gen = 0;
        for(int i = 0; i < 18; i++)
            jj_la1[i] = -1;

    }

    public void ReInit(InputStream stream)
    {
        jj_input_stream.ReInit(stream, 1, 1);
        token_source.ReInit(jj_input_stream);
        token = new Token();
        jj_ntk = -1;
        jj_gen = 0;
        for(int i = 0; i < 18; i++)
            jj_la1[i] = -1;

    }

    public CreateTableParser(Reader stream)
    {
        jj_la1 = new int[18];
        jj_expentries = new Vector();
        jj_kind = -1;
        jj_input_stream = new SimpleCharStream(stream, 1, 1);
        token_source = new CreateTableParserTokenManager(jj_input_stream);
        token = new Token();
        jj_ntk = -1;
        jj_gen = 0;
        for(int i = 0; i < 18; i++)
            jj_la1[i] = -1;

    }

    public void ReInit(Reader stream)
    {
        jj_input_stream.ReInit(stream, 1, 1);
        token_source.ReInit(jj_input_stream);
        token = new Token();
        jj_ntk = -1;
        jj_gen = 0;
        for(int i = 0; i < 18; i++)
            jj_la1[i] = -1;

    }

    public CreateTableParser(CreateTableParserTokenManager tm)
    {
        jj_la1 = new int[18];
        jj_expentries = new Vector();
        jj_kind = -1;
        token_source = tm;
        token = new Token();
        jj_ntk = -1;
        jj_gen = 0;
        for(int i = 0; i < 18; i++)
            jj_la1[i] = -1;

    }

    public void ReInit(CreateTableParserTokenManager tm)
    {
        token_source = tm;
        token = new Token();
        jj_ntk = -1;
        jj_gen = 0;
        for(int i = 0; i < 18; i++)
            jj_la1[i] = -1;

    }

    private final Token jj_consume_token(int kind)
        throws ParseException
    {
        Token oldToken;
        if((oldToken = token).next != null)
            token = token.next;
        else
            token = token.next = token_source.getNextToken();
        jj_ntk = -1;
        if(token.kind == kind)
        {
            jj_gen++;
            return token;
        } else
        {
            token = oldToken;
            jj_kind = kind;
            throw generateParseException();
        }
    }

    public final Token getNextToken()
    {
        if(token.next != null)
            token = token.next;
        else
            token = token.next = token_source.getNextToken();
        jj_ntk = -1;
        jj_gen++;
        return token;
    }

    public final Token getToken(int index)
    {
        Token t = token;
        for(int i = 0; i < index; i++)
            if(t.next != null)
                t = t.next;
            else
                t = t.next = token_source.getNextToken();

        return t;
    }

    private final int jj_ntk()
    {
        if((jj_nt = token.next) == null)
            return jj_ntk = (token.next = token_source.getNextToken()).kind;
        else
            return jj_ntk = jj_nt.kind;
    }

    public ParseException generateParseException()
    {
        jj_expentries.removeAllElements();
        boolean la1tokens[] = new boolean[23];
        for(int i = 0; i < 23; i++)
            la1tokens[i] = false;

        if(jj_kind >= 0)
        {
            la1tokens[jj_kind] = true;
            jj_kind = -1;
        }
        for(int i = 0; i < 18; i++)
        {
            if(jj_la1[i] != jj_gen)
                continue;
            for(int j = 0; j < 32; j++)
                if((jj_la1_0[i] & 1 << j) != 0)
                    la1tokens[j] = true;

        }

        for(int i = 0; i < 23; i++)
            if(la1tokens[i])
            {
                jj_expentry = new int[1];
                jj_expentry[0] = i;
                jj_expentries.addElement(jj_expentry);
            }

        int exptokseq[][] = new int[jj_expentries.size()][];
        for(int i = 0; i < jj_expentries.size(); i++)
            exptokseq[i] = (int[])jj_expentries.elementAt(i);

        return new ParseException(token, exptokseq, CreateTableParserConstants.tokenImage);
    }

    public final void enable_tracing()
    {
    }

    public final void disable_tracing()
    {
    }

    public CreateTableParserTokenManager token_source;
    SimpleCharStream jj_input_stream;
    public Token token;
    public Token jj_nt;
    private int jj_ntk;
    private int jj_gen;
    private final int jj_la1[];
    private static int jj_la1_0[];
    private Vector jj_expentries;
    private int jj_expentry[];
    private int jj_kind;

    static 
    {
        jj_la1_0();
    }
}
