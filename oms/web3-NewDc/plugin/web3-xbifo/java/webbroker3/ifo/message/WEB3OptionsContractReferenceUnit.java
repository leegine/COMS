head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.14.29;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3OptionsContractReferenceUnit.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 株価指数オプション建玉照会明細(WEB3OptionsContractReferenceUnit.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/11 張威 (中訊) 新規作成
Revesion History : 2007/06/08 孟亜南 (中訊) 仕様変更モデルNo.682
*/

package webbroker3.ifo.message;

import java.util.Date;

import com.fitechlabs.xtrade.kernel.message.Message;

/**
 * (株価指数オプション建玉照会明細)<BR>
 * 株価指数オプション建玉照会行クラス<BR>
 * @@author 張威
 * @@version 1.0
 */
public class WEB3OptionsContractReferenceUnit extends Message
{
    
    /**
     * (ＩＤ)<BR>
     * 建玉ＩＤ<BR>
     */
    public String id;
    
    /**
     * (銘柄コード)<BR>
     */
    public String opProductCode;
    
    /**
     * (銘柄名)<BR>
     */
    public String opProductName;
    
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
     * P：プットオプション C：コールオプション<BR>
     */
    public String opProductType;
    
    /**
     * (行使価格)
     */
    public String strikePrice;
    
    /**
     * (取引市場)<BR>
     * 1：東京　@2：大阪<BR>
     */
    public String marketCode;
    
    /**
     * (建区分)<BR>
     * 1：買建　@2：売建<BR>
     */
    public String contractType;
    
    /**
     * (建日)
     */
    public Date openDate;
    
    /**
     * (建数量)
     */
    public String contractOrderQuantity;
    
    /**
     * (建単価)
     */
    public String contractPrice;
    
    /**
     * (決済状態区分)<BR>
     * 0：決済済　@1：未決済　@2：決済中<BR>
     */
    public String settlementState;
    
    /**
     * (建約定金額)
     */
    public String contractExecPrice;
    
    /**
     * (建手数料)
     * 建手数料(消費税込)
     */
    public String contractCommission;
    
    /**
     * (取引最終日)
     */
    public Date lastTradingDate;
    
    /**
     * (損益)
     */
    public String income;
    
    /**
     * (損益(諸経費込))
     */
    public String incomeCost;
    
    /**
     * (立会区分)<BR>
     * 1：夕場（夕場実施する会社の夕場時間帯のみ設定）　@NULL：その他<BR>
     */
    public String sessionType;
    
    /**
     * @@roseuid 40C0754900AB
     */
    public WEB3OptionsContractReferenceUnit() 
    {
     
    }
}
@
