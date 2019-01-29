head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.04.45.36;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AioFeqConOperationDivDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 定数定義インタフェイス(WEB3AioFeqConOperationDivDef)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/03/23 周勇 (中訊) 新規作成
*/
package webbroker3.aio.define;

/**
 * 外株口座への振替取消サービスImplのget処理区分の戻り値　@定数定義インタフェイス
 *                                                                     
 * @@author 周勇
 * @@version 1.0
 */
public interface WEB3AioFeqConOperationDivDef
{
    /**
     * 0 : 振替中
     */
    public final static String TRANSFERING = "0";
    
    /**
     * 1 : UWG決済中
     */
    public final static String UWG_TRANSFERING = "1";
    
    /**
     * 2 : UWG決済完了
     */
    public final static String UWG_TRANSFER_COMPLETE = "2";

    /**
     * 3 : UWG決済エラー
     */
    public final static String UWG_TRANSFER_ERROE = "3";
    
    /**
     * 4 : 取消済
     */
    public final static String CANCELED = "4";

}
@
