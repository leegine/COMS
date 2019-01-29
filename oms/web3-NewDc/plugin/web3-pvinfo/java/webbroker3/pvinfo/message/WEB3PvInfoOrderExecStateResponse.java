head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.08.06.39;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7444d8857e2568b;
filename	WEB3PvInfoOrderExecStateResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 注文約定状況レスポンス(WEB3PvInfoOrderExecStateResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/18 王亞洲(中訊) 新規作成
Revesion History : 2004/10/20 李弘毅(中訊) 作成
*/
package webbroker3.pvinfo.message;

import java.util.Date;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * (注文約定状況レスポンス)<BR>
 * 注文約定状況レスポンスクラス<BR>
 * @@author 王亞洲
 * @@version 1.00
 */
public class WEB3PvInfoOrderExecStateResponse extends WEB3GenResponse 
{
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "PvInfo_orderExecState";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200410181349L;
    
    /**
     * (営業日の一覧)<BR>
     * 当営業日、翌営業日の順で格納。<BR>
     * ※当日が非営業日の場合は、<BR>
     * 　@翌営業日、翌々営業日の順で格納。<BR>
     */
    public Date[] bizDateList;
    
    /**
     * (信用取引口座開設)<BR>
     * 信用取引口座を開設しているかどうかのフラグ<BR>
     * <BR>
     * false：　@未開設<BR>
     * true：　@開設済<BR>
     */
    public boolean marginAccOpen;
    
    /**
     * (先物取引口座開設)<BR>
     * 先物取引口座を開設しているかどうかのフラグ<BR>
     * <BR>
     * false：　@未開設<BR>
     * true：　@開設済<BR>
     */
    public boolean futuresAccOpen;
    
    /**
     * (オプション取引口座開設)<BR>
     * オプション取引口座を開設しているかどうかのフラグ<BR>
     * <BR>
     * false：　@未開設<BR>
     * true：　@開設済<BR>
     */
    public boolean optionsAccOpen;
    
    /**
     * (当日注文件数)<BR>
     * 当日注文件数<BR>
     */
    public WEB3PvInfoTradeCountUnit orderCountsToday;
    
    /**
     * (当日約定件数)<BR>
     * 当日約定件数<BR>
     */
    public WEB3PvInfoTradeCountUnit execCountsToday;
    
    /**
     * (翌日注文件数)<BR>
     * 翌日注文件数<BR>
     */
    public WEB3PvInfoTradeCountUnit orderCountsTomorrow;
    
    /**
     * (当日売買代金情報)
     * 売買代金情報<BR>
     */
    public WEB3PvInfoTradePriceUnit tradePriceUnit;
    
    /**
     * (IPO当選銘柄情報一覧[])<BR>
     * IPO当選銘柄情報一覧<BR>
     */
    public WEB3PvInfoIpoProductUnit[] ipoProductUnits;
    
    /**
     * デフォルトコンストラクタ 引数で与えられたリクエストオブジェクトを基に<BR>
     * レスポンスオブジェクトを生成する。<BR>
     * <BR>
     * @@param l_request リクエストオブジェクト
     */
     public WEB3PvInfoOrderExecStateResponse(WEB3GenRequest l_request)
     {
         super(l_request);
     }   
}
@
