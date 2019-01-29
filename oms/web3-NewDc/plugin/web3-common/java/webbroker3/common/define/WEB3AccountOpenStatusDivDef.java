head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.04.50.41;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AccountOpenStatusDivDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 定数定義インタフェイス(WEB3AccountOpenStatusDivDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/1/21 屈陽 (中訊) 新規作成
Revesion History : 2006/02/16 凌建平 (中訊) 入出金仕様変更管理台帳No.80(ＤＢレイアウト)
*/

package webbroker3.common.define;

/**
 * 口座開設状況区分　@定数定義インタフェイス
 *                                                                     
 * @@author 屈陽
 * @@version 1.0
 */
public interface WEB3AccountOpenStatusDivDef
{
    /**
     * 0：口座開設中　@　@
     */
    public static final String ACCOUNT_OPENING = "0";
    
    /**
     * 1：口座開設完了
     */
    public static final String ACCOUNT_OPEN_COMPLETE = "1";
    
    /**
     * 2：口座開設エラー
     */
    public static final String ACCOUNT_OPEN_ERROR = "2";

    /**
     * 3:ダウンロード済
     */
    public static final String DOWNLOAD_COMPLETE = "3";
    
    /**
     * 9：削除
     */
    public static final String DELETE = "9";
}
@
