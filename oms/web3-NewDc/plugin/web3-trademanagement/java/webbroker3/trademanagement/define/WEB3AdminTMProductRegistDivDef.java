head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.24.08;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6f44d7ebe647d68;
filename	WEB3AdminTMProductRegistDivDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : Trademanagement Product Regist Div Def(WEB3AdminTMProductRegistDivDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/05 張宝楠 (中訊) 新規作成
*/
package webbroker3.trademanagement.define;

/**
 * Trademanagement Product Regist Div Def<BR>
 * WEB3AdminTMProductRegistDivDef interface<BR>
 * @@author Prabhu
 * @@version 1.0
 */
public interface WEB3AdminTMProductRegistDivDef
{

    /**
     *  0：取扱可能
     */
    public final static String NORMAL  = "0";

    /**
     * 1：バッチ処理中
     */
    public final static String BATCH = "1";

    /**
     * 2：停止
     */
    public final static String SCRAM = "2";
}@
