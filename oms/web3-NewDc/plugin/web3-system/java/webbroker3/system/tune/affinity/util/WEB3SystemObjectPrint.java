head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.06.25.27;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7984d7db4391837;
filename	WEB3SystemObjectPrint.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (株)大和総研 証券ソリューションシステム第二部
 File Name        : Object print util クラス(WEB3SystemObjectPrint.java)
 Author Name      : Daiwa Institute of Research
 Revesion History : 2004/09/08 劉 新規作成
 */
package webbroker3.system.tune.affinity.util;

import webbroker3.system.tune.affinity.*;

/**
 * Object print util クラス
 *
 * @@author 劉
 * @@version 1.0
 */
public class WEB3SystemObjectPrint
{

    public static String print(int[] to)
    {
        String s = "[";
        for (int i = 0; i < to.length - 1; i++)
        {
            s += to[i] + ",";
        }
        if (to.length > 0)
        {
            s += to[to.length - 1] + "]";
        }
        else
        {
            s += "]";
        }
        return s;
    }

    public static String printWEB3AcctIdTryOrderMapInfo(WEB3AcctIdTryOrderMapInfo info)
    {

        return
            "accountIdStart=" + info.getAccountIdStart() + "," +
            " accountIdEnd= " + info.getAccountIdEnd() + "," +
            " appServerTryOrder= " +
            print(info.getAppServerTryOrder()) + "\n";
    }

    public static String printWEB3ReqNumTryOrderMapInfo(WEB3ReqNumTryOrderMapInfo info)
    {

        return
            "head2OfOrderRequestNumber=" + info.getHead2OfOrderRequestNumber() + "," +
            " appServerTryOrder= " +
            print(info.getAppServerTryOrder()) + "\n";
    }

}
@
