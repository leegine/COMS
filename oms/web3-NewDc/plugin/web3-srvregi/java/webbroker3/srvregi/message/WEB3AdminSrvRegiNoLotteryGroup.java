head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.06.34.16;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7984d7db5d91d86;
filename	WEB3AdminSrvRegiNoLotteryGroup.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : サービス利用管理者抽選無サービス明細情報一覧行(WEB3AdminSrvRegiNoLotteryGroup.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/20 鄭海良(中訊) 新規作成
*/

package webbroker3.srvregi.message;

/**
 * (サービス利用管理者抽選無サービス明細情報一覧行)<BR>
 * サービス利用管理者抽選無サービス明細情報一覧行データクラス<BR>
 *  
 * @@author 鄭海良
 * @@version 1.0  
 */
public class WEB3AdminSrvRegiNoLotteryGroup extends WEB3AdminSrvRegiDetailCommon 
{

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
     * (サービス利用管理者抽選無サービス明細情報一覧行)<BR>
     * デフォルトコンストラクタ<BR>
     * @@roseuid 40F2206303D8
     */
    public WEB3AdminSrvRegiNoLotteryGroup() 
    {
     
    }
}
@
