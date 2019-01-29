head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.12.18;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3OptionsIndividualCloseMarginListResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 株価指数オプション個別返済一覧画面表示レスポンス
(WEB3OptionsＩndividualCloseMarginListResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/11 張威 (中訊) 新規作成
*/

package webbroker3.ifo.message;

import webbroker3.common.message.WEB3GenResponse;
import webbroker3.common.message.WEB3GenRequest;

/**
 * (株価指数オプション個別返済一覧画面表示レスポンス)<BR>
 * 株価指数オプション個別返済一覧画面表示レスポンスクラス<BR>                                                                    
 * @@author 張威
 * @@version 1.0
 */
public class WEB3OptionsIndividualCloseMarginListResponse extends WEB3GenResponse 
{
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "options_individualCloseMarginList";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200406111735L;
    
    /**
     * (銘柄名)<BR>
     */
    public String opProductName;
    
    /**
     * (建区分)<BR>
     * 1：買建　@2：売建
     */
    public String contractType;
    
    /**
     * (取引市場)<BR>
     * 1：東京　@2：大阪
     */
    public String marketCode;
    
    /**
     * (現在値)<BR>
     */
    public String currentPrice;
    
    /**
     * (建玉明細)<BR>
     */
    public WEB3FuturesOptionsContractUnit[] contractUnits;
    
    /**
     * (取引終了警告文言)<BR>
     * 閉局間近の市場があれば、そのコードを格納
     */
    public String[] messageSuspension;    
    
    /**
     * デフォルトコンストラクタ
     */
    public WEB3OptionsIndividualCloseMarginListResponse()
    {
        
    }
    
    /**
     * コンストラクタ。引数で与えられたリクエストオブジェクトを基に<BR>
     * レスポンスオブジェクトを生成する。<BR>
     *<BR>
     * @@param l_request リクエストオブジェクト
     */
    protected WEB3OptionsIndividualCloseMarginListResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
}
@
