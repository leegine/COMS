head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.05.38.09;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6f44d7efa104045;
filename	WEB3AdminIPOLotResultOfferDownloadResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者IPO抽選結果購入申込状況ﾀﾞｳﾝﾛｰﾄﾞﾚｽﾎﾟﾝｽ(WEB3AdminIPOLotResultOfferDownloadResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/12 斉麟 (中訊) 新規作成
*/

package webbroker3.ipo.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * 管理者IPO抽選結果購入申込状況ﾀﾞｳﾝﾛｰﾄﾞﾚｽﾎﾟﾝｽクラス
 *                                                               
 * @@author 斉麟
 * @@version 1.0
 */
public class WEB3AdminIPOLotResultOfferDownloadResponse extends WEB3GenResponse 
{
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_IPO_lotResultOfferDownload";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200408121114L;
    
    /**
     * 銘柄コード
     */
    public String productCode;
    
    /**
     * 銘柄名
     */
    public String productName;
    
    /**
     * アップロード履歴
     */
    public WEB3IPOUploadHistoryUnit uploadHistory;
    
    /**
     * 当社取扱数量
     */
    public String handlingQuantity;
    
    /**
     * 割当確定数量
     */
    public String allotQuantity;
    
    /**
     * 繰上可能数量
     */
    public String advanceQuantity;
    
    /**
     * 繰上待ち数量
     */
    public String advanceWaitQuantity;
    
    /**
     * 当選者購入申込概況
     */
    public WEB3IPOOfferConditionUnit prizedOfferCondition;
    
    /**
     * 補欠者購入申込概況
     */
    public WEB3IPOOfferConditionUnit waitingOfferCondition;
    
    /**
     * 落選者購入申込概況
     */
    public WEB3IPOOfferConditionUnit rejectedOfferCondition;
    
    /**
     * 表示用単位区分<BR>
     * <BR>
     * １： 株数（株）<BR>
     * ２： 口数（口）<BR>
     */
    public String displayUnitDiv;
    
    /**
     * @@roseuid 4112DAD50298
     */
    public WEB3AdminIPOLotResultOfferDownloadResponse() 
    {
     
    }
    
    /**
     * コンストラクタ。<BR>
     * 指定されたリクエストオブジェクトに対するレスポンスオブジェクトを生成する。<BR>
     * @@param l_request - リクエストオブジェクト
     * @@roseuid 40E12495005F
     */
    public WEB3AdminIPOLotResultOfferDownloadResponse(WEB3GenRequest l_request) 
    {
        super(l_request);
    }
}
@
