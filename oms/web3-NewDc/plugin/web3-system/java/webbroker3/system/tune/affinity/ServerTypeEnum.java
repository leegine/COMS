head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.06.24.37;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7984d7db4391837;
filename	ServerTypeEnum.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (株)大和総研 証券ソリューションシステム第二部
 File Name        : Serverタイプクラス(ServerTypeEnum.java)
 Author Name      : Daiwa Institute of Research
 Revesion History : 2004/09/08 劉 新規作成
 */
package webbroker3.system.tune.affinity;

import com.fitechlabs.xtrade.kernel.enumerated.*;

/**
 * Serverタイプクラス
 *
 * @@author 劉
 * @@version 1.0
 */
public class ServerTypeEnum
    extends Enumerated
{

    /** Constant for server in terms of app. */
    public static final ServerTypeEnum AP = new ServerTypeEnum(IntValues.AP,
        "AP");

    /** Constant for server in terms of db. */
    public static final ServerTypeEnum DB = new ServerTypeEnum(IntValues.DB,
        "DB");

    /**
     * Mandatory constructor override of superclass constructor
     *
     * @@param i Int value.
     * @@param s String representation.
     */
    public ServerTypeEnum(int i, String s)
    {
        super(i, s);
    }

    /**
     * Optional Inner class to define values of integers used. In this way the
     * class can be easily used in switch statements
     */
    public static class IntValues
    {

        //~ Static fields/initializers ---------------------------------------------

        /** Indicates server is app. */
        public static final int AP = 1;

        /** Indicates server is db. */
        public static final int DB = 2;
    }
}
@
