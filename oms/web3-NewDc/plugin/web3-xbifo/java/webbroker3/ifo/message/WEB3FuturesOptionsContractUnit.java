head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.19.11;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3FuturesOptionsContractUnit.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 建玉明細(WEB3FuturesOptionsContractUnit.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/11 李頴淵 (中訊) 新規作成
              001: 2004/08/05 王暁傑 (中訊) 対応バグ U00021
Revesion History : 2007/06/08 孫洪江 (中訊) 仕様変更モデルNo.640
*/

package webbroker3.ifo.message;

import com.fitechlabs.xtrade.kernel.message.Message;


/**
 * (建玉明細)<BR>
 * 建玉の明細を表すクラス<BR>
 * @@author 李頴淵
 * @@version 1.0
 */
public class WEB3FuturesOptionsContractUnit extends Message
{
    
    /**
     * 建玉ＩＤ
     */
    public String id;
    
    /**
     * (建日)
     */
    public java.util.Date openDate;
    
    /**
     * (建玉数)
     */
    public String contractQuantity;
    
    /**
     * (建単価)
     */
    public String contractPrice;
    
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
     * (損益)
     */
    public String income;
    
    /**
     * (損益(諸経費込))
     */
    public String incomeCost;
    
    /**
     * (返済数量)
     */
    public String contractOrderQuantity;
    
    /**
     * (決済順位)
     */
    public String settlePriority;

    /**
     * (返済約定数量)
     */
    public String contractExecQuantity;

    /**
     * (立会区分)<BR>
     * 1：夕場（夕場実施する会社の夕場時間帯のみ設定）　@NULL：その他<BR>
     */
    public String sessionType;

    /**
     * (建玉明細)
     * コンストラクタ
     * @@roseuid 407F6E8C008C
     */
    public WEB3FuturesOptionsContractUnit() 
    {
        
    }
}
@
