head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.04.46.43;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3HostTransferPaymentStatusDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 定数定義インタフェイス(WEB3HostTransferPaymentStatusDef)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/10/05 黄建 (中訊) 新規作成
*/

package webbroker3.aio.define;

/**
 * 振替請求伝票キューテーブルの処理区分　@定数定義インタフェイス
 *                                                                     
 * @@author 黄建
 * @@version 1.0
 */
public class WEB3HostTransferPaymentStatusDef
{
    /**
     *  0：未処理
     */
    public static final String NOT_DEAL = "0";   
    
    /**
     *  1：処理済
     */
    public static final String DEALT = "1";
    
    /**
     *  2：処理対象外
     */
    public static final String DEAL_EXCEPT_OBJECT = "2";
    
    /**
     *  3：保有資産なし
     */
    public static final String NO_ASSET = "3";
    
    /**
     *  9：エラー
     */
    public static final String DATA_ERROR = "9";
}
@
