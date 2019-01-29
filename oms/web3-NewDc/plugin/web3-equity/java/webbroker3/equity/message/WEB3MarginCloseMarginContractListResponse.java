head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.45.53;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3MarginCloseMarginContractListResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 信用取引決済建株一覧レスポンス(WEB3MarginCloseMarginContractListResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/09/16 凌建平 (中訊) 新規作成
Revesion History : 2004/12/10 桑原 (SRA) 修正
*/

package webbroker3.equity.message;

import webbroker3.common.message.WEB3GenResponse;

/**
 * （信用取引決済建株一覧レスポンス）。<br>
 * <br>
 * 信用取引決済建株一覧レスポンス
 * @@author 凌建平
 * @@version 1.0
 */
public class WEB3MarginCloseMarginContractListResponse extends WEB3GenResponse 
{
    /**
     * (PTYPE)<BR>
     */
    public static final String PTYPE = "margin_closeMarginContractList";

    /**
     * (SerialVersionUID)<BR>
     */
    public static final long serialVersionUID = 200409101648L;         
    /**
     * (銘柄コード)
     */
    public String productCode;
    
    /**
     * (銘柄名)
     */
    public String productName;
    
    /**
     * (市場コード)
     */
    public String marketCode;
    
    /**
     * (口座区分)<BR>
     * 0：一般　@1：特定
     */
    public String taxType;
    
    /**
     * (建区分)<BR>
     * 1：買建　@2：売建
     */
    public String contractType;
    
    /**
     * (弁済)<BR>
     * 1：一般信用 2：制度信用
     */
    public WEB3MarginRepaymentUnit repayment;
    
    /**
     * (決済順序区分)<BR>
     * 0：ランダム　@1：単価益順　@2：単価損順　@3：建日順
     */
    public String closingOrder;
    
    /**
     * (決済建株一覧明細行)<BR>
     * 信用取引決済建株明細行
     */
    public WEB3MarginCloseMarginContractGroup[] closeMarginContractGroups;
    
    /**
     * @@roseuid 414032D002B6
     */
    public WEB3MarginCloseMarginContractListResponse() 
    {
     
    }
    
    /**
     * (コンストラクタ。)<BR>
     * 引数で与えられたリクエストオブジェクトを基に<BR>
     * レスポンスオブジェクトを生成する。<BR>
     *<BR>
     * @@param l_request リクエストオブジェクト
     */
    public WEB3MarginCloseMarginContractListResponse(WEB3MarginCloseMarginContractListRequest l_request)
    {
        super(l_request);
    }
}
@
