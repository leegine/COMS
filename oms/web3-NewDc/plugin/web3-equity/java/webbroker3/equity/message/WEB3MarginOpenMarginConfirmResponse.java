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
filename	WEB3MarginOpenMarginConfirmResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 信用取引新規建注文確認レスポンス(WEB3MarginOpenMarginConfirmResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/09/17 凌建平 (中訊) 新規作成
Revesion History : 2004/12/10 桑原 (SRA) 修正
Revesion History : 2006/12/25 張騰宇 (中訊) モデル 1085
Revesion History : 2007/06/13 武波 (中訊) モデルNo.1167
*/

package webbroker3.equity.message;

import java.util.Date;

/**
 * （信用取引新規建注文確認レスポンス）。<br>
 * <br>
 * 信用取引新規建注文確認レスポンスクラス
 * @@author 凌建平
 * @@version 1.0
 */
public class WEB3MarginOpenMarginConfirmResponse extends WEB3MarginConfirmCommonResponse 
{
    /**
     * (PTYPE)<BR>
     */
    public static final String PTYPE = "margin_openMarginConfirm";

    /**
     * (SerialVersionUID)<BR>
     */
    public static final long serialVersionUID = 200409101617L;
    
    /**
     * (銘柄名)
     */
    public String productName;
    
    /**
     * (手数料情報)<BR>
     * 信用取引手数料情報
     */
    public WEB3MarginCommissionInfoUnit commissionInfo;
    
    /**
     * (金利)<BR>
     * <BR>
     * 信用取引区分が「一般信用」の時設定<BR>
     */
    public String interestRates;
    
    /**
     * (清算期間)
     */
    public String clearUpTerm;
    
    /**
     * (確認時単価)<BR>
     * <BR>
     * 完了リクエストで送信する値。<BR>
     */
    public String checkPrice;
    
    /**
     * (金販法@同意)<BR>
     * true：同意済orｼｽﾃﾑ化未対応　@ false：非同意
     */
    //項目削除
    //public boolean finSalesAgreement;
    
    /**
     * (注文ID)
     */
    public String orderId;
      
    /**
     * (インサイダー警告表示フラグ)<BR>
     * true：警告表示要　@　@　@false：警告表示不要 
     */
    public boolean insiderWarningFlag;
    
    /**
     * (注意文言表示区分)<BR>
     * 注意文言表示区分。<BR>
     * null：注意文言非表示　@　@2：増担保規制注意文言表示
     */
    public String attentionObjectionType;

    /**
     * (市場コード)<BR>
     * 市場コード
     */
    public String marketCode;

    /**
     * (注文有効期限)<BR>
     * 注文有効期限
     */
    public Date expirationDate;

    /**
     * @@roseuid 4140477D03B2
     */
    public WEB3MarginOpenMarginConfirmResponse() 
    {
     
    }
    
    /**
     * (コンストラクタ。)<BR>
     * 引数で与えられたリクエストオブジェクトを基に<BR>
     * レスポンスオブジェクトを生成する。<BR>
     *<BR>
     * @@param l_request リクエストオブジェクト
     */
    public WEB3MarginOpenMarginConfirmResponse(WEB3MarginOpenMarginConfirmRequest l_request)
    {
        super(l_request);
    }
}
@
