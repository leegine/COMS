head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.04.32.37;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3CategCodeDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 種別コード定数定義インタフェイス(WEB3CategCodeDef.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/12/10 栄イ(中訊) 新規作成
Revision History : 2008/05/19 趙林鵬(中訊) 種別コード設定値の説明変更
*/
package webbroker3.common.define;

/**
 * 種別コード定数定義インタフェイス
 * （書面区分管理テーブルと書面交付管理テーブルと電子鳩銘柄コード管理テーブル
 * の書面区分の参考用）
 *
 * @@author 栄イ(中訊)
 * @@version 1.0
 */
public interface WEB3CategCodeDef
{
    /**
     * 010：契約締結前交付書面
     */
    public final static String DOCUMENT_DELIVERY = "010";
}
@
