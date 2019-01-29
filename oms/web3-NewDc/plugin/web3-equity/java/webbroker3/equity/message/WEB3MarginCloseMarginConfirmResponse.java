head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.45.56;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3MarginCloseMarginConfirmResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        :  信用取引返済注文確認レスポンスクラス(WEB3MarginCloseMarginConfirmResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/9/16 李松峰 (中訊) 新規作成
Revesion History : 2004/12/13 桑原 (SRA) 修正
Revesion History : 2007/06/13 武波 (中訊) モデルNo.1167
*/
package webbroker3.equity.message;

import java.util.Date;

/**
 * （信用取引返済注文確認レスポンス）。<br>
 * <br>
 * 信用取引返済注文確認レスポンスクラス
 * @@version 1.0
 */
public class WEB3MarginCloseMarginConfirmResponse extends WEB3MarginConfirmCommonResponse 
{
    /**
     * (PTYPE)<BR>
     */
    public static final String PTYPE = "margin_closeMarginConfirm";

    /**
     * (SerialVersionUID)<BR>
     */
    public static final long serialVersionUID = 200409101645L;        
    /**
     * (建株明細一覧)<BR>
     */
    public WEB3MarginContractUnit[] contractUnits;
    
    /**
     * (信用取引手数料情報)<BR>
     */
    public WEB3MarginCommissionInfoUnit commissionInfo;
    
    /**
     * (確認時単価)<BR>
     * <BR>
     * 完了リクエストで送信する値。<BR>
     */
    public String checkPrice;
    
    /**
     * (注文ID)<BR>
     */
    public String orderId;
    
	/**
	 * (インサイダー警告表示フラグ)<BR>
	 * true：警告表示要　@　@　@false：警告表示不要
	 */
	public boolean insiderWarningFlag; 
    
    /**
     * (返済時注意文言)<BR>
     * <BR>
     * 返済時注意文言<BR>
     */
    public WEB3MarginCloseMarginAttentionUnit closeMarginAttention; 

    /**
     * (注文有効期限)<BR>
     * 注文有効期限
     */
    public Date expirationDate;

    /**
     * @@roseuid 414047D9002F
     */
    public WEB3MarginCloseMarginConfirmResponse() 
    {
     
    }
    
    /**
     * (コンストラクタ。)<BR>
     * 引数で与えられたリクエストオブジェクトを基に<BR>
     * レスポンスオブジェクトを生成する。<BR>
     *<BR>
     * @@param l_request リクエストオブジェクト
     */
    public WEB3MarginCloseMarginConfirmResponse(WEB3MarginCloseMarginConfirmRequest l_request)
    {
        super(l_request);
    }
}
@
