head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.18.04;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3FuturesOptionsDetailUnit.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 株価指数先物オプション残高照会明細クラス(WEB3FuturesOptionsDetailUnit.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/29 呉艶飛 新規作成         
Revesion History : 2007/06/08 孫洪江 (中訊) 仕様変更モデルNo.640
*/
package webbroker3.ifo.message;

import java.util.Date;

import com.fitechlabs.xtrade.kernel.message.Message;


/**
 * (株価指数先物オプション残高照会明細)<BR>
 * 株価指数先物オプション残高照会明細クラス
 * @@author 呉艶飛
 * @@version 1.0
 */
public class WEB3FuturesOptionsDetailUnit extends Message
{
   
    /**
     * (ＩＤ)<BR>
     * 建玉ＩＤ<BR>
     */
    public String id;
    
    /**
     * (銘柄コード)<BR>
     * 先物OP銘柄コード<BR>
     */
    public String productCode;
    
    /**
     * (銘柄名)<BR>
     * 先物OP銘柄名称<BR>
     */
    public String productName;
    
    /**
     * (指数種別)<BR>
     * 0005：TOPIX　@0018：日経225　@0016：日経300　@0019：ミニ日経225<BR>
     */
    public String targetProductCode;
    
    /**
     * (限月)<BR>
     * YYYYMM形式<BR>
     */
    public String delivaryMonth;
    
    /**
     * (オプション商品区分)<BR>
     * F：先物  P：プットオプション  C：コールオプション<BR>
     */
    public String opProductType;
 
    /**
     * (行使価格)<BR>
     */    
    public String strikePrice;
    
    /**
     * (取引市場)<BR>
     * 1：東京　@2：大阪
     */
    public String marketCode;
    
    /**
     * (建区分)<BR>
     * 1：買建　@2：売建
     */
    public String contractType;
    
    /**
     * (建日)<BR>
     */
    public Date openDate;
    
    /**
     * (建数量)<BR>
     */
    public String contractOrderQuantity;
    
    /**
     * (建単価)<BR>
     */
    public String contractPrice;
    
    /**
     * (決済状態区分)<BR>
     * 1：未決済　@2：決済中<BR>
     */
    public String settlementState;
    
    /**
     * (建約定金額)<BR>
     */
    public String contractExecPrice;
    
    /**
     * (建手数料)<BR>
     * 建手数料(消費税込)<BR>
     */
    public String contractCommission;
    
    /**
     * (取引最終日)<BR>
     */
    public Date lastTradingDate;
    
    /**
     * (時価)<BR>
     */
    public String currentPrice = null;
    
    /**
     * (前日比)<BR>
     */
    public String comparedPreviousDay = null;
    
    /**
     * (時価取得時間)<BR>
     */
    public String currentPriceTime = null;
    
    /**
     * (損益)<BR>
     */
    public String income = null;
    
    /**
     * (損益(諸経費込))<BR>
     */
    public String incomeCost = null;

    /**
     * (立会区分)<BR>
     * 1：夕場（夕場実施する会社の夕場時間帯のみ設定）　@NULL：その他<BR>
     */
    public String sessionType;
}
@
