head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.04.50.49;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AioInputNumberDivDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 定数定義インタフェイス(WEB3AioInputPasswordDivDef)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/01/18 黄建 (中訊) 新規作成
*/

package webbroker3.aio.define;

/**
 * 管理者・FX口座情報検索リクエストの入力番号区分　@定数定義インタフェイス
 *                                                                     
 * @@author 黄建
 * @@version 1.0
 */

public interface WEB3AioInputNumberDivDef
{
    /**
     * 1：FXログインID
     */
    public static final String FX_LOGINID = "1";
   
    /**
     * 2：FX口座番号
     */
    public static final String FX_ACCOUNT_CODE = "2";
    
    /**
     * 3：顧客コード
     */
    public static final String ACCOUNT_CODE = "3";
}

@
