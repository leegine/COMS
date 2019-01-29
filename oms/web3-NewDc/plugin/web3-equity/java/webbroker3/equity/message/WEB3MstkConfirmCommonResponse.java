head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.45.52;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3MstkConfirmCommonResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 株式ミニ投資確認共通レスポンス(WEB3MstkConfirmCommonResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/12 彭巍(中訊) 新規作成
                   2004/12/09 桑原(SRA) 残案件対応 No.281
                   2005/01/05 岡村(SRA) JavaDoc修正
*/

package webbroker3.equity.message;

import java.util.Date;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * （株式ミニ投資確認共通レスポンス）。<BR>
 * <BR>
 * 株式ミニ投資確認共通レスポンスクラス
 * @@author 彭巍
 * @@version 1.0
 */
public class WEB3MstkConfirmCommonResponse extends WEB3GenResponse 
{
    
    /**
     * （PTYPE）。
     */
    public final static  String PTYPE = "mstk_confirmCommon";
        
    /**
     * （SerialVersionUID）。
     */
    public final static long serialVersionUID = 200410101054L;    
    
    /**
     * （概算受渡代金）。
     */
    public String estimatedPrice;
    
    /**
     * （手数料情報）。
     */
    public WEB3MstkCommissionInfoUnit commissionInfo;
    
    /**
     * (時価区分)<BR>
     * 1:現在値　@　@2:売気配値　@　@3:買気配値　@　@4:前日終値<BR>
     */
    public String currentPriceDiv;
    
    /**
     * （インサイダー警告表示フラグ）。<BR>
     * <BR>
     * true：警告表示要<BR>
     * false：警告表示不要
     */
    public boolean insiderWarningFlag;
        
    /**
     * （時価(現在値)）。
     */
    public String currentPrice;
    
    /**
     * （前日比）。
     */
    public String comparedPreviousDay;
    
    /**
     * （取引時間(時価発表時間)）。
     */
    public Date currentPriceTime;
    
    /**
     * （取引終了警告）。<BR>
     * <BR>
     * true：警告文を表示する<BR>
     * false：警告文を表示しない
     */
    public boolean messageSuspensionFlag;
    
    /**
     * （確認時単価）。
     */
    public String checkPrice;
    
    /**
     * （確認時発注日）。<BR>
     * <BR>
     * ※注文執行日
     */
    public Date checkDate;
    
    /**
     * （注文ID）。
     */
    public String orderId;
    
    /**
     * （株式ミニ投資確認共通レスポンス）。<BR>
     * <BR>
     * デフォルトコンストラクタ
     */
    public WEB3MstkConfirmCommonResponse() 
    {
     
    }
    
    /**
     * （株式ミニ投資確認共通レスポンス）。<BR>
     * <BR>
     * コンストラクタ
     * @@param l_request 株式ミニ投資確認共通リクエスト
     */
    public WEB3MstkConfirmCommonResponse(WEB3GenRequest l_request) 
    {
        super(l_request); 
    }
}
@
