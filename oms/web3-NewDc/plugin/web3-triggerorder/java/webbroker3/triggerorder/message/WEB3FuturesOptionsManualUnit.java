head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.40.35;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8704d7ec30c0c9d;
filename	WEB3FuturesOptionsManualUnit.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 先物OP手動発注Unit(WEB3FuturesOptionsManualUnit.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/02/17　@余新敏(中訊) 新規作成
                 : 2006/08/24  唐性峰(中訊) モデルNo.163 對應
*/

package webbroker3.triggerorder.message;

import webbroker3.ifo.message.WEB3FuturesOptionsContractUnit;

/**
 * (先物OP手動発注Unit)<BR>
 * 先物OP手動発注Unitクラス<BR>
 * 
 * @@author 余新敏
 * @@version 1.0
 */
public class WEB3FuturesOptionsManualUnit extends WEB3ManualCommonUnit 
{
    
    /**
     * (口座区分)<BR>
     * 口座区分<BR>
     * <BR>
     * 0：　@オプション買建口座 <BR>
     * 1：　@先物オプション口座<BR>
     */
    public String taxType;
    
    /**
     * (指数種別)<BR>
     * 指数種別<BR>
     * <BR>
     * 0005：　@TOPIX<BR>
     * 0018：　@日経225<BR>
     * 0016：　@日経300<BR>
     * 0019：　@ミニ日経225<BR>
     */
    public String targetProductCode;
    
    /**
     * (限月)<BR>
     * 限月<BR>
     * (YYYYMM)<BR>
     */
    public String delivaryMonth;
    
    /**
     * (行使価格)<BR>
     * 行使価格<BR>
     */
    public String strikePrice = null;
    
    /**
     * (オプション商品区分)<BR>
     * オプション商品区分<BR>
     * <BR>
     * P：プットオプション<BR>
     * C：コールオプション<BR>
     * <BR>
     * ※先物の場合は、nullをセット。<BR>
     */
    public String opProductType = null;
    
    /**
     * (発注条件単価区分)<BR>
     * 発注条件単価区分<BR>
     * <BR>
     * 0：　@原資産価格<BR>
     * 1：　@プレミアム<BR>
     * <BR>
     * ※条件注文種別が"逆指値注文"または"Ｗ指値注文"の場合設定<BR>
     */
    public String orderCondPriceDiv = null;
    
    /**
     * (取引終了警告文言)<BR>
     * 閉局間近の市場があれば、そのコードを格納。<BR>
     */
    public String[] messageSuspension = null;
    
    /**
     * (建玉明細)<BR>
     * 建玉明細<BR>
     */
    public WEB3FuturesOptionsContractUnit[] contractUnits;
    
    /**
     * (先物OP手動発注Unit)<BR>
     * コンストラクタ<BR>
     * @@roseuid 43F48892038A
     */
    public WEB3FuturesOptionsManualUnit() 
    {
     
    }
}
@
