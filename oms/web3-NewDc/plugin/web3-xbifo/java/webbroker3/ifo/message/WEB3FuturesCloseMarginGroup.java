head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.18.37;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3FuturesCloseMarginGroup.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 株価指数先物返済一覧行(WEB3FuturesCloseMarginGroup.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/07/19 鄒鋭 (中訊) 新規作成
Revesion History : 2007/06/08 孫洪江 (中訊) 仕様変更モデルNo.640
*/

package webbroker3.ifo.message;

import com.fitechlabs.xtrade.kernel.message.Message;

/**
 * (株価指数先物返済一覧行)<BR>
 * 株価指数先物返済一覧行クラス<BR>
 * @@author 鄒鋭
 * @@version 1.0
 */
public class WEB3FuturesCloseMarginGroup extends Message
{
    /**
     * (銘柄コード)<BR>
     */
    public String futProductCode;

    /**
     * (銘柄名)<BR>
     */
    public String futProductName;

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
     * (建玉数)<BR>
     */
    public String contractQuantity;

    /**
     * (建単価)<BR>
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
     * (建代金)<BR>
     */
    public String contractExecPrice;

    /**
     * (建手数料)<BR>
     * 建手数料(消費税込)<BR>
     */
    public String contractCommission;
    
    /**
     * (現在値)<BR>
     */
    public String currentPrice;

    /**
     * (損益)<BR>
     */
    public String income;
    
    /**
     * (損益(諸経費込))
     */
    public String incomeCost;

    /**
     * (建玉明細)<BR>
     */
    public WEB3FuturesOptionsContractUnit[] contractUnits;

    /**
     * (立会区分)<BR>
     * 1：夕場（夕場実施する会社の夕場時間帯のみ設定）　@NULL：その他<BR>
     */
    public String sessionType;

    /**
     * @@roseuid 40F7AE170109
     */
    public WEB3FuturesCloseMarginGroup()
    {

    }
}
@
