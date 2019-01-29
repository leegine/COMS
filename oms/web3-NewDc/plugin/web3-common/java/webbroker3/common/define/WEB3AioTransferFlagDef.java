head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.04.38.18;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AioTransferFlagDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 定数定義インタフェイス(WEB3TransferFlagDef)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/06 周勇 (中訊) 新規作成
*/
package webbroker3.common.define;

/**
 * 代用振替請求キューテーブルの振替区分　@定数定義インタフェイス
 *                                                                     
 * @@author 周勇
 * @@version 1.0
 */
public interface WEB3AioTransferFlagDef
{
    /**
     * 02：保護→代用
     */
    public static final String SAFE_DEPOSIT = "02";
    
    /**
     *  01：代用→保護
     */
    public static final String COLLATERAL_SECURITY = "01";
}
@
