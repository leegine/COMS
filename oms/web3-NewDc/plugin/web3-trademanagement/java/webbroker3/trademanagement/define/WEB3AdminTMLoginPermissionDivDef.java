head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.24.12;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6f44d7ebe647d68;
filename	WEB3AdminTMLoginPermissionDivDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : Trademanagement Login Permission Div Def(WEB3AdminTMLoginPermissionDivDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/05 張宝楠 (中訊) 新規作成
*/
package webbroker3.trademanagement.define;

/**
 * Trademanagement Login Permission Div Def<BR>
 * WEB3AdminTMLoginPermissionDivDef<BR>
 * @@author Prabhu
 * @@version 1.0
 */
public interface WEB3AdminTMLoginPermissionDivDef
{
    /**
     * 0：停止
     */
    public final static String DISABLE  = "0";

    /**
     * 1：可能
     */
    public final static String ENABLE = "1";

}@
