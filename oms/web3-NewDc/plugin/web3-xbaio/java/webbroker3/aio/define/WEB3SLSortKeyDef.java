head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.04.50.07;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3SLSortKeyDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 定数定義インタフェイス(WEB3SLSortKeyDef.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/09/18 金傑（中訊）新規作成
*/
package webbroker3.aio.define;

/**
 * (証券担保ローン用ソートキー区分)<BR>
 * 証券担保ローン用ソートキー区分<BR>
 *
 * @@author 金傑(中訊)
 * @@version 1.0
 */
public interface WEB3SLSortKeyDef
{
    /**
     * accountCode： 顧客コード<BR>
     */
    public static final String ACCOUNT_CODE = "accountCode";

    /**
     * producttype： 銘柄タイプ<BR>
     */
    public static final String PRODUCT_TYPE = "productType";

    /**
     * productcode： 銘柄コード<BR>
     */
    public static final String PRODUCT_CODE = "productCode";

    /**
     * targetPeriodFrom： 適用期間from<BR>
     */
    public static final String TARGET_PERIOD_FORM = "targetPeriodFrom";
}
@
