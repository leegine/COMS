head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.15.18;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3OptionsCommonResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 株価指数オプション共通入力レスポンス(WEB3OptionsCommonResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/10 張威 (中訊) 新規作成
              001: 2006/07/12 張騰宇　@(中訊)　@仕様変更　@467
Revesion History : 2007/06/08 張騰宇(中訊) モデル 639
Revesion History : 2007/06/21 孟亜南(中訊) モデル 711
*/

package webbroker3.ifo.message;

import java.util.Date;

import webbroker3.common.message.WEB3GenResponse;
import webbroker3.common.message.WEB3GenRequest;

/**
 * (株価指数オプション共通入力レスポンス)<BR>
 * 株価指数オプション共通入力レスポンスクラス<BR>                                                
 * @@author 張威
 * @@version 1.0
 */
public class WEB3OptionsCommonResponse extends WEB3GenResponse 
{
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "options_common";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200406111708L;
    
    /**
     * (注文単価区分一覧)<BR>
     * 0：成行　@　@1：指値<BR>
     */
    public String[] orderPriceDivList;
    
    /**
     * (執行条件一覧)<BR>
     * 1：無条件 3:寄付　@4:引け　@7:不出来引け成行<BR>
     */
    public String[] execCondList;
    
    /**
     * (注文期限区分一覧)<BR>
     * 1：当日限り　@2：出来るまで注文　@3：夕場まで注文<BR>
     */
    public String[] expirationDateTypeList;
    
    /**
     * (有効期限開始日)<BR>
     * 出来るまで注文に指定できる最初の日<BR>
     * <BR>
     * 注文期限区分に「出来るまで注文」がある場合設定<BR>
     */
    public Date expirationStartDate;
    
    /**
     * (有効期限最終日)<BR>
     * 出来るまで注文に指定できる最後の日<BR>
     * <BR>
     * 注文期限区分に「出来るまで注文」がある場合設定<BR>
     */
    public Date expirationEndDate;
    
    /**
     * (有効期限内祝日一覧)<BR>
     * 出来るまで注文に指定できる期間中の祝日一覧<BR>
     * <BR>
     * 注文期限区分に「出来るまで注文」がある場合でかつ、期間中に祝日のある場合に設定<BR>
     */
    public Date[] holidayList;
    
    /**
     * (発注条件区分一覧)<BR>
     * 0：指定なし　@1：逆指値　@2：W指値<BR>
     */
    public String[] orderCondTypeList;
    
    /**
     * (W指値用執行条件一覧)<BR>
     * 1：無条件 3:寄付　@4:引け　@7:不出来引け成行<BR>
     */
    public String[] wlimitExecCondList;

    /**
     * (立会区分)<BR>
     * 1：夕場（夕場実施する会社の夕場時間帯のみ設定）　@NULL：その他<BR>
     */
    public String sessionType;

    /**
     * デフォルトコンストラクタ
     */
    public WEB3OptionsCommonResponse()
    {
        
    }
    
    /**
     * コンストラクタ。引数で与えられたリクエストオブジェクトを基に<BR>
     * レスポンスオブジェクトを生成する。<BR>
     *<BR>
     * @@param l_request リクエストオブジェクト
     */
    protected WEB3OptionsCommonResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
}
@
