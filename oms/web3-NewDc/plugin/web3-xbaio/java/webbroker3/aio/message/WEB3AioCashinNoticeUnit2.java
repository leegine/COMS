head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.15.57;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AioCashinNoticeUnit2.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 入金通知明細(WEB3AioCashinNoticeUnit2)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/1/21 李俊 (中訊) 新規作成
                 : 2006/8/23 車進(中訊) 仕様変更 モデル 614
                 : 2006/11/09 徐宏偉(中訊) 仕様変更 モデル 682
*/
package webbroker3.aio.message;

import java.util.Date;
import com.fitechlabs.xtrade.kernel.message.Message;

/**
 * (入金通知明細)<BR>
 * 入金通知明細クラス<BR>
 *
 * @@author 李俊(中訊)
 * @@version 1.0
 */

public class WEB3AioCashinNoticeUnit2 extends Message
{

    /**
     * (入金通知テーブルID)<BR>
     * データ取込区分 + 入金通知テーブルID<BR>
     */
    public String cashinNoticeTableId;

    /**
     * (部店コード)<BR>
     */
    public String branchCode;

    /**
     * (顧客コード)<BR>
     */
    public String accountCode;

    /**
     * (振込依頼人コード)<BR>
     */
    public String clientCode;

    /**
     * (振込依頼人名)<BR>
     */
    public String clientName;

    /**
     * (顧客名)<BR>
     */
    public String accountName;

    /**
     * (金額)<BR>
     */
    public String price;

    /**
     * (通貨コード)<BR>
     */
    public String currencyCode;

    /**
     * (勘定日)<BR>
     */
    public Date settlementDate;

    /**
     * (銀行コード)<BR>
     */
    public String financialInstitutionCode;

    /**
     * (銀行名)<BR>
     */
    public String financialInstitutionName;

    /**
     * (支店コード)<BR>
     */
    public String financialBranchCode;

    /**
     * (支店名)<BR>
     */
    public String  financialBranchName;
    
    
    /**
     * (振込銀行名)<BR>
     */
    public String transferFinancialInstitutionName;

    /**
     * (振込支店名)<BR>
     */
    public String  transferFinancialBranchName;    

    /**
     * (備考)<BR>
     */
    public String remark;

    /**
     * (処理区分)<BR>
     */
    public String transactionDiv;

    /**
     * (処理日時)<BR>
     */
    public Date transactionDate;

    /**
     * (入払区分)<BR>
     */
    public String cashinoutDiv;

    /**
     * (取引区分)<BR>
     */
    public String ioTradingType;


    /**
     * (入金通知明細)<BR>
     * デフォルトコンストラクタ<BR>
     *
     * @@return webbroker3.aio.message.WEB3AioCashinNoticeUnit2
     * @@roseuid 40E284450344
     */
    public WEB3AioCashinNoticeUnit2()
    {

    }
}
@
