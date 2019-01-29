head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.06.36.24;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7984d7db5d91d86;
filename	WEB3SrvRegiNoLotteryGroup.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : サービス利用抽選無サービス明細情報一覧行(WEB3SrvRegiNoLotteryGroup.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/18 鄭海良(中訊) 新規作成
Revesion History : 2007/06/05 孫洪江(中訊) 仕様変更モデルNo.248
*/

package webbroker3.srvregi.message;

import java.util.Date;

/**
 * (サービス利用抽選無サービス明細情報一覧行)<BR>
 * サービス利用抽選無サービス明細情報一覧行データクラス<BR>
 *  
 * @@author 鄭海良
 * @@version 1.0  
 */
public class WEB3SrvRegiNoLotteryGroup extends WEB3AdminSrvRegiDetailCommon 
{
   
    /**
     * (サービス説明URL)
     */
    public String explainURL;
    
    /**
     * (利用期間料金情報)
     */
    public WEB3SrvRegiChargeInfo[] chargeInfo;
    
    /**
     * (試用期間単位区分)<BR>
     * 1:年　@2:月　@3:日<BR>
     * （試用期間が無い場合、null）<BR>
     */
    public String trialDiv;
    
    /**
     * (試用期間)<BR>
     * （試用期間が無い場合、null）<BR>
     */
    public String trialPeriod;
    
    /**
     * (登録日)
     */
    public Date registDate;
    
    /**
     * (利用期限)
     */
    public Date useLimitDate;
    
    /**
     * (電子鳩設定区分)<BR>
     * true:設定有　@false:設定無<BR>
     */
    public boolean elePigeonDiv;
    
    /**
     * (申込可能区分)<BR>
     * true:可能　@false:不可<BR>
     */
    public boolean applyAbleDiv;
    
    /**
     * (継続申込可能区分)<BR>
     * true:可能　@false:不可<BR>
     */
    public boolean continuationDiv;
    
    /**
     * (サービス利用可能区分)<BR>
     * true:可能　@false:不可<BR>
     */
    public boolean useAbleDiv;

    /**
     * (無料申込可能区分)<BR>
     * true:可能　@false:不可<BR>
     */
    public boolean noChargeAbleDiv;

    /**
     * (申込属性区分)<BR>
     * 1:無料対象、2:申込不可<BR>
     */
    public String applyAttributeDiv;

    /**
     * (申込属性期間From)<BR>
     * 申込属性期間From(YYYYMMDD)<BR>
     */
    public Date applyAttributePeriodFrom;

    /**
     * (申込属性期間To)<BR>
     * 申込属性期間To(YYYYMMDD)<BR>
     */
    public Date applyAttributePeriodTo;

    /**
     * (無料属性申込区分)<BR>
     * null or '0' ：通常申込　@'1'：無料属性申込<BR>
     */
    public String freeAttributeApplyDiv;

    /**
     * (サービス利用抽選無サービス明細情報一覧行)<BR>
     * デフォルトコンストラクタ<BR>
     * @@roseuid 40EE320300F0
     */
    public WEB3SrvRegiNoLotteryGroup() 
    {
     
    }
}
@
