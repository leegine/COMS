head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.04.54.04;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3ForeignSummaryInfo.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 外貨サマリ情報(WEB3ForeignSummaryInfo)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/8/22 車進 (中訊) 新規作成
*/
package webbroker3.aio.message;

import com.fitechlabs.xtrade.kernel.message.Message;

/**
 * (外貨サマリ情報)<BR>
 * 外貨サマリ情報クラス<BR>
 *
 * @@author 車進(中訊)
 * @@version 1.0
 */

public class WEB3ForeignSummaryInfo extends Message
{

    /**
     * (外貨コード)<BR>
     * 外貨コード<BR>
     */
    public String foreignCurrencyCode;

    /**
     * (入金件数)<BR>
     * 入金件数<BR>
     */
    public String cashinNumber;

    /**
     * (入金合計金額)<BR>
     * 入金合計金額<BR>
     */
    public String cashinTotalPrice;


    /**
     * @@roseuid 44EBB20E0177
     */
    public WEB3ForeignSummaryInfo() 
    {
     
    }
    
    /**
     * (外貨サマリ情報）<BR>
     * コンストラクタ<BR>
     * <BR>
     * １）　@引数:通貨コードをセットする。<BR>
     * @@param l_strForeignCurrencyCode - (通貨コード)<BR>
     * 通貨コード<BR>
     * @@roseuid 44E43FB7018C
     */
    public WEB3ForeignSummaryInfo(String l_strCurrencyCode) 
    {
        this.foreignCurrencyCode =  l_strCurrencyCode;
    }
    
}
@
