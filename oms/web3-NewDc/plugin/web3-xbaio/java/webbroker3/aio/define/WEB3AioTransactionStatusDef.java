head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.04.46.10;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AioTransactionStatusDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 定数定義インタフェイス(WEB3AioTransactionStatusDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/19 韋念瓊 (中訊) 新規作成
*/
package webbroker3.aio.define;

/**
 * 決済連携レポート一覧の処理状態　@定数定義インタフェイス
 *                                                                     
 * @@author 韋念瓊
 * @@version 1.0
 */
public interface WEB3AioTransactionStatusDef
{      
    /**
     * 0 : 未処理
     */
    public static final String NOT_TRANSACTION = "0";

    /**
     * 1 : 決済完了
     */
    public static final String SETTLE_COMPLETE = "1";    
    
    /**
     * 2 : 決済中止
     */
    public static final String SETTLE_STOP = "2";
    
    /**
     * 3 : エラー
     */
    public static final String ERROR = "3";  
    
    /**
     * 4 : 全て
     */
    public static final String ALL = "4";   
    
}
@
