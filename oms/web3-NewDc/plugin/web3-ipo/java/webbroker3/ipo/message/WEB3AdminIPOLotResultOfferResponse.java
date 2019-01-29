head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.05.37.23;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6f44d7efa104045;
filename	WEB3AdminIPOLotResultOfferResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者IPO抽選結果購入申込状況ﾚｽﾎﾟﾝｽ(WEB3AdminIPOLotResultOfferResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/12 斉麟 (中訊) 新規作成
Revesion History : 2005/12/20 譚漢江 (中訊) 仕様変更No.105修正
*/

package webbroker3.ipo.message;

import java.util.Date;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * 管理者IPO抽選結果購入申込状況ﾚｽﾎﾟﾝｽクラス
 *                                                               
 * @@author 斉麟
 * @@version 1.0
 */
public class WEB3AdminIPOLotResultOfferResponse extends WEB3GenResponse 
{
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_IPO_lotResultOffer";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200408121118L;
    
    /**
     * 顧客名
     */
    public String accountName;
    
    /**
     * 抽選結果区分<BR>
     * <BR>
     * 1：　@当選<BR>
     * 2：　@補欠<BR>
     * 3：　@落選<BR>
     * 21：　@補欠当選<BR>
     * 23：　@補欠落選<BR>
     * <BR>
     */
    public String lotResultDiv;
    
    /**
     * 当選数量
     */
    public String prizeQuantity;
    
    /**
     * 購入申込数量
     */
    public String offerQuantity;
    
    /**
     * 購入申込辞退日時
     */
    public Date offerCancelDate;
    
    /**
     * 購入申込状況区分<BR>
     * <BR>
     * 1：　@購入申込<BR>
     * 2：　@辞退<BR>
     */
    public String offerStateDiv;
    
    /**
     * 受付状態区分<BR>
     * <BR>
     * 0：　@DEFAULT（----）<BR>
     * 1：　@SONAR送信済（受付中）<BR>
     * 2：　@SONAR反映済（受付済）<BR>
     */
    public String receiveStateDiv;
    
    /**
     * 優先順位
     */
    public String priority;
    
    /**
     * 購入申込数量変更可能フラグ<BR>
     * <BR>
     * 　@true：　@購入申込数量入力可能（表示）<BR>
     * 　@false：　@購入申込数量を当選数量に固定（非表示）<BR>
     */
    public boolean offerQuantityFlag;
    
    /**
     * 表示用単位区分<BR>
     * <BR>
     * １： 株数（株）<BR>
     * ２： 口数（口）<BR>
     */
    public String displayUnitDiv;
    
    /**
     * 扱者コード
     */
    public String traderCode;

    /**
     * 公開価格
     */
    public String publicOfferingPrice;
    
    /**
     * 信用区分
     */
    public String marginDiv;
    
    /**
     * 抽選番号
     */
    public String lotNumber;

    /**
     * @@roseuid 4112DAD40297
     */
    public WEB3AdminIPOLotResultOfferResponse() 
    {
     
    }
    
    /**
     * コンストラクタ。<BR>
     * 指定されたリクエストオブジェクトに対するレスポンスオブジェクトを生成する。<BR>
     * @@param l_request - リクエストオブジェクト
     * @@roseuid 40E124A00197
     */
    public WEB3AdminIPOLotResultOfferResponse(WEB3GenRequest l_request) 
    {
        super(l_request);
    }
}
@
