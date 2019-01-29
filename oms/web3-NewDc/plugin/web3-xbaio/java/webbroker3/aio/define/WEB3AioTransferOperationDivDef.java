head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.04.45.48;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AioTransferOperationDivDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 定数定義インタフェイス(WEB3AioTransferOperationDivDef)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/01/18 黄建 (中訊) 新規作成
                 : 2006/08/04 鈴木 (SCS)モデル　@595対応
*/

package webbroker3.aio.define;

/**
 * FX振替明細 の振替区分　@定数定義インタフェイス
 *                                                                     
 * @@author 黄建
 * @@version 1.0
 */

public interface WEB3AioTransferOperationDivDef
{
    /**
     * 1：入金（FX） 
     */
    public static final String CASH_IN = "1";
    
    /**
     * 2：出金（FX）
     */
    public static final String CASH_OUT = "2";

    /**
     * 3：出金（先OP）
     */
    public static final String FUOP_OUT = "3";

    /**
     * 4：入金（先OP）
     */
    public static final String FUOP_IN = "4";
}
@
