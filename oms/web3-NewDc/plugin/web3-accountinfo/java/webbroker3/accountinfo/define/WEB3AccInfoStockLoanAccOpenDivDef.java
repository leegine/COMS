head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.02.26.52;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	WEB3AccInfoStockLoanAccOpenDivDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 開設状況 定数定義インタフェイス(WEB3AccInfoStockLoanAccOpenDivDef.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/09/07 武波 (中訊) 新規作成 仕様変更・モデルNo.223
*/
package webbroker3.accountinfo.define;

/**
 * (開設状況 定数定義インタフェイス)<BR>
 * 開設状況 定数定義インタフェイス<BR>
 * <BR>
 * @@author 武波
 * @@version 1.0
 */
public interface WEB3AccInfoStockLoanAccOpenDivDef
{
    /**
     * 0：　@未開設<BR>
     */
    public final static String NOT_OPEN = "0";

    /**
     * 1：　@開設済<BR>
     */
    public final static String OPEN = "1";
}
@
