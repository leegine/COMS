head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.06;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminEquityTableNameDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 設定対象テーブル名定数定義インタフェイス(WEB3AdminEquityTableNameDef.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/12/30 張少傑(中訊) 新規作成 モデルNo.219
*/
package webbroker3.eqtypeadmin.define;

/**
 * 設定対象テーブル名 定数定義インタフェイス
 * <BR>
 * @@author 張少傑
 * @@version 1.0
 */
public interface WEB3AdminEquityTableNameDef
{
    /**
     * 株式取引銘柄マスター
     */
    public final static String EQTYPE_TRADED_PRODUCT = "eqtype_traded_product";
}
@
