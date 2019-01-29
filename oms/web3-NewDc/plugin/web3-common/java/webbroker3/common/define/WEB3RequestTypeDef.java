head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.04.29.09;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3RequestTypeDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 定数定義インタフェイス(WEB3RequestTypeDef)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/01 zhao-lin(sinocom)　@新規作成
Revesion History : 2006/07/12 栄イ (中訊)【先物オプション】仕様変更・ＤＢレイアウトNo.043
Revesion History : 2006/11/28 栄イ (中訊)【株式】仕様変更・ＤＢレイアウトNo.133
Revesion History : 2006/12/11 栄イ (中訊)【先物オプション】仕様変更・ＤＢレイアウトNo.057
*/
package webbroker3.common.define;

/**
 * リクエストタイプ　@定数定義インタフェイス
 *                                                                     
 * @@author zhao-lin
 * @@version 1.0
 */
public interface WEB3RequestTypeDef
{
    /**
     * 0 : DEFAULT(画面/HOST)
     */
    public static final String DEFAULT = "0";

    /**
     * 1 : 時価サーバ
     */
    public static final String QUOTE_SERVER = "1";

    /**
     * 2：切替完了
     */
    public static final String TRANSFERED = "2";

    /**
     * 5：失効
     */
    public static final String INVALIDATE = "5";

    /**
     * 8：発注失敗
     */
    public static final String ORDER_FAILURE = "8";
}
@
