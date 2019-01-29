head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.03.51;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3AdminMutualConditionsInputResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 投資信託銘柄条件登録入力レスポンス(WEB3AdminMutualConditionsInputResponse)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/13 黄建  (中訊) 新規作成
Revesion History : 2004/08/23 于美麗 (中訊) レビュー 
Revesion History : 2004/12/09 于美麗 (中訊) 残対応
Revesion History : 2005/10/18 韋念瓊 (中訊) フィデリティ対応
Revesion History : 2006/04/09 張騰宇 (中訊)　@モデル548
*/
package webbroker3.mf.message;
import java.util.Date;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * 投資信託銘柄条件登録入力レスポンス<BR>
 * 
 * @@author 黄建(中訊)
 * @@version 1.0 
 */

public class WEB3AdminMutualConditionsInputResponse extends WEB3GenResponse
{
    /**
     * PTYPE
     */
    public static final String PTYPE = "admin_mutual_conditions_input";
    
    /**
     * SerialVersionUID<BR>
     */
    public final static long serialVersionUID = 200408131500L;    
    
    /**
     * 投信協会銘柄コード<BR>
     */
    public String mutualAssocProductCode;
    
    /**
     * 最終更新者コード<BR>
     */
    public String lastUpdaterCode;
    
    /**
     * 最終更新日時<BR>
     */
    public Date lastUpdateTime;
    
    /**
     * 設定日<BR>
     * <BR>
     */
    public Date settingDate;
    
    /**
     * 償還日<BR>
     * <BR>
     */
    public Date refundDate;
    
    /**
     * 解約解禁日<BR>
     * <BR>
     */
    public Date removalOfNotSell;
    
    /**
     * 指定方法@一覧（買付）<BR>
     * <BR>
     * 0:選択指定　@3:金額指定　@4:口数指定<BR>
     */
    public String[] buySelectableList;

    /**
     * 指定方法@一覧（解約）<BR>
     * <BR>
     * 0:選択指定　@3:金額指定　@4:口数指定<BR>
     */
    public String[] sellSelectableList;

    /**
     * 指定方法@一覧（乗換）<BR>
     * <BR>
     * 0:選択指定　@3:金額指定　@4:口数指定<BR>
     */
    public String[] switchingSelectableList;
    
    /**
     * 指定方法@一覧（募集）<BR>
     * <BR>
     * 0:選択指定　@3:金額指定　@4:口数指定<BR>
     */
    public String[] applySelectableList;
    
    /**
     * 現在日からの発注日<BR>
     */
    public Date curBizDate;
    
    /**
     * 現在日からの翌営業日<BR>
     */
    public Date nextBizDate;

    /**
     * (外貨MMFフラグ)<BR>
     * 外貨MMFフラグ<BR> 
     * <BR>
     * true:銘柄が外貨MMF <BR>
     * false:銘柄が外貨MMFでない<BR>
     */
    public boolean frgnMmfFlag;

    /**
     * (通貨コード)<BR>
     * 通貨コード <BR>
     * <BR>
     * A0:US$　@A1:C$　@A2:A$　@A3:HK$　@A4:S$ <BR>
     * A5:NZ$　@D0:￡　@D1:Irish ￡　@F0:Fr <BR>
     * F1:SFr　@I0:DM　@J0:G　@K0:Lit　@L0:AS <BR>
     * M0:DKr　@M1:NKr　@M2:SKr　@N0:Pts　@T0:円 <BR>
     * T3:FIM　@U1:Bath　@Z3:ECU　@Z4:EUR<BR>
     */
    public String currencyCode;

    /**
     * 投信銘柄条件登録共通情報<BR>
     */
    public WEB3MutualProductConditionsCommonInfo mutualProductInfo;
    
    /**
     * (投信銘柄条件登録入力レスポンス)<BR>
     * デフォルトコンストラクタ<BR>
     * @@roseuid 40DF7B7B01AC
     */
    public WEB3AdminMutualConditionsInputResponse()
    {
    }
    
    /**
     * コンストラクタ。引数で与えられたリクエストオブジェクトを基に<BR>
     * レスポンスオブジェクトを生成する。<BR>
     *<BR>
     * @@param l_request リクエストオブジェクト
     */
    protected WEB3AdminMutualConditionsInputResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
}
@
