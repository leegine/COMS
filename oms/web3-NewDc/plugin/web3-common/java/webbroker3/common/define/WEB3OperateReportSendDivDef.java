head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.04.50.00;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3OperateReportSendDivDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 定数定義インタフェイス(WEB3OperateReportSendDivDef)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/07/22 li-yingyuan(sinocom)　@新規作成
*/
package webbroker3.common.define;

/**
 * 運用報告書発送区分　@定数定義インタフェイス
 *                                                                      
 * @@author li-yingyuan
 * @@version 1.0
 */
public interface WEB3OperateReportSendDivDef
{
    /**
     * 0 : 銘柄ﾏｽﾀｰ決算月
     */
    public static final String PRODUCT_YEARLY_BOOKS_CLOSING_MONTH = "0";

    /**
     * 1 : 指定月
     */
    public static final String DESIGNATE_MONTH = "1";
    
    /**
     * 2 : GPのみ指定月
     */
    public static final String GP_DESIGNATE_MONTH = "2";

}@
