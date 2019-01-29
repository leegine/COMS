head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.04.48.38;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AioHostTransferPaymentTransferFlagDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 定数定義インタフェイス(WEB3AioHostTransferPaymentTransferFlagDef)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/10/05 黄建 (中訊) 新規作成
*/

package webbroker3.aio.define;

/**
 * 振替請求伝票キューテーブルの出入区分　@定数定義インタフェイス
 *                                                                     
 * @@author 黄建
 * @@version 1.0
 */

public class WEB3AioHostTransferPaymentTransferFlagDef
{
    /**
     *  1：振替出庫　@
     */
    public static final String TRANSFER_STORAGE_OUT = "1";
    
    /**
     *  2：振替入庫
     */
    public static final String TRANSFER_STORAGE_IN = "2";
}
@
