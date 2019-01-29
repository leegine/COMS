head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.11.01;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3OptionsCloseMarginContractGroup.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 株価指数オプション返済建玉一覧明細行クラス(WEB3OptionsCloseMarginContractGroup.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/10 呉艶飛 新規作成
              001: 2004/07/28 王暁傑 (中訊) 対応 詳細設計チェック指摘事項 (日本側) 
                   com.fitechlabs.xtrade.kernel.message.Messageを継承。
Revesion History : 2007/06/08   孫洪江 (中訊) 仕様変更モデルNo.640
*/

package webbroker3.ifo.message;

import java.util.Date;

import com.fitechlabs.xtrade.kernel.message.Message;


/**
 * (株価指数オプション返済建玉一覧明細行)<BR>
 * 株価指数オプション返済建玉一覧明細行クラス<BR>
 * @@author 呉艶飛
 * @@version 1.0
 */
public class WEB3OptionsCloseMarginContractGroup extends Message
{
      
    /**
     * (建単価)<BR>
     */
    public String contractPrice;
    
    /**
     * (建日)<BR>
     */
    public Date openDate;
    
    /**
     * (返済数量（注文部分数量）)<BR>
     */
    public String contractOrderQuantity;

    /**
     * (注文単価区分)<BR>
     */
    public String orderPriceDiv;
    
    /**
     * (注文単価)<BR>
     */
    public String limitPrice;

    /**
     * (約定数量)<BR>
     */
    public String execQuantity;
    
    /**
     * (約定単価)<BR>
     */
    public String execPrice;
    
    /**
     * (建手数料)<BR>
     */
    public String contractCommission;
    
    /**
     * (建手数料消費税)<BR>
     */
    public String contractCommissionConsumptionTax;
    
    /**
     * (建約定金額)<BR>
     */
    public String contractExecTotalPrice;
    
    /**
     * (損益)<BR>
     */
    public String income;
    
    /**
     * (建順位)<BR>
     */
    public String closeMarginOrderNumber;

    /**
     * (立会区分)<BR>
     * 1：夕場（夕場実施する会社の夕場時間帯のみ設定）　@NULL：その他<BR>
     */
    public String sessionType;
}
@
