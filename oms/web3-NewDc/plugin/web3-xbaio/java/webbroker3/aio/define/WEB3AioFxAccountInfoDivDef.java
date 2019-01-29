head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.04.46.01;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AioFxAccountInfoDivDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 定数定義インタフェイス(WEB3AioFxAccountInfoDivDef)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/01/18 黄建 (中訊) 新規作成
*/

package webbroker3.aio.define;

/**
 * 管理者・FX口座情報変更共通リクエスト の処理区分　@定数定義インタフェイス
 *                                                                     
 * @@author 黄建
 * @@version 1.0
 */

public interface WEB3AioFxAccountInfoDivDef
{
    /**
     * 0：口座情報変更
     */
    public static final String ACCOUNT_INFO = "0";
   
    /**
     * 1：口座開設状況変更
     */
    public static final String ACCOUNT_OPEN_STATUS = "1";
}

@
