head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.14.24;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3OptionsCloseMarginGroup.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 株価指数オプション返済一覧行クラス(WEB3OptionsCloseMarginGroup.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/10 呉艶飛 (中訊) 新規作成
              001: 2004/07/28 王暁傑 (中訊) 対応 詳細設計チェック指摘事項 (日本側) 
                   com.fitechlabs.xtrade.kernel.message.Messageを継承。
Revesion History : 2007/06/08 孫洪江 (中訊) 仕様変更モデルNo.640
*/

package webbroker3.ifo.message;

import com.fitechlabs.xtrade.kernel.message.Message;
/**
 * (株価指数オプション返済一覧行)<BR>
 * 株価指数オプション返済一覧行クラス<BR>
 * @@author 呉艶飛
 * @@version 1.0
 */
public class WEB3OptionsCloseMarginGroup extends Message
{
    
    /**
     * (銘柄コード)
     */
    public String opProductCode;
    
    /**
     * (銘柄名)
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
     * (建玉数)
     */
    public String contractQuantity;
    
    /**
     * (建単価)
     */
    public String contractPrice;
    
    /**
     * (決済状態区分)<BR>
     * 下記のいずれか。<BR>
     * 1：未決済<BR>
     * 2：決済中<BR>
     */
    public String settlementState;
    
    /**
     * (建約定金額)
     */
    public String contractExecPrice;
    
    /**
     * (建手数料)<BR>
     * 建手数料(消費税込)<BR>
     */
    public String contractCommission;
    
    /**
     * (現在値)
     */
    public String currentPrice;
    
    /**
     * (損益)
     */
    public String income;
    
    /**
     * (損益(諸経費込))
     */
    public String incomeCost;
    
    /**
     * (建玉明細)
     */
    public WEB3FuturesOptionsContractUnit[] contractUnits; 
    
    /**
     * (立会区分)<BR>
     * 1：夕場（夕場実施する会社の夕場時間帯のみ設定）　@NULL：その他<BR>
     */
    public String sessionType;
}
@
