head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.00.48.39;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3TPDepositRealTransferEnforceDivDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 「会社部店別余力計算条件」の定数定義クラス（WEB3TPDepositRealTransferEnforceDivDef.java）
Author Name      : Daiwa Institute of Research
Revision History : 2007/10/18 崔遠鵬 (中訊) 新規作成　@仕様変更モデル212
*/
package webbroker3.tradingpower.define;

/**
 * 「保証金リアル振替実施区分」の定数定義クラス<BR>
 * 
 * @@author 崔遠鵬
 * @@version 1.0
 */
public interface WEB3TPDepositRealTransferEnforceDivDef
{
    /**
     * 0：保証金へのリアル振替を行わない
     */
    public final static String DEFAULT = "0";

    /**
     * 1：保証金へのリアル振替を行う（入金受入日のみ考慮）
     */
    public final static String EXECUTE_T0 = "1";

    /**
     * 2：保証金へのリアル振替を行う（入金受入日+1まで考慮）
     */
    public final static String EXECUTE_T1 = "2";    
}
@
