head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.04.33.45;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3CodeTypeDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : コード種別定数定義インタフェイス(WEB3CodeTypeDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/10/18 栄イ(中訊) 新規作成
*/
package webbroker3.common.define;

/**
 * コード翻訳テーブルのコード種別定数定義インタフェイス
 *
 * @@author 栄イ(中訊)
 * @@version 1.0
 */
public interface WEB3CodeTypeDef
{
    /**
     * th_repdv：取引履歴一覧ダウンロード(弁済区分)
     */
    public final static String TH_REPDV = "th_repdv";

    /**
     * th_cmcd:取引履歴一覧ダウンロード(商品コード)
     */
    public final static String TH_CMCD = "th_cmcd";

    /**
     * pls_comdv:損益明細照会ダウンロード((商品)適用)
     */
    public final static String PLS_COMDV = "pls_comdv";

    /**
     * pls_terdv:損益明細照会ダウンロード(長短)
     */
    public final static String PLS_TERDV = "pls_terdv";

    /**
     * pls_retdv:損益明細照会ダウンロード（銘柄名）
     */
    public final static String PLS_RETDV = "pls_retdv";
}
@
