head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.06.30.40;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7984d7db5d91d86;
filename	WEB3AdminSrvRegiNoLotteryStateGroup.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : サービス利用顧客申込状況一覧抽選無サービス一覧行(WEB3AdminSrvRegiNoLotteryStateGroup.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/20 鄭海良(中訊) 新規作成
*/

package webbroker3.srvregi.message;


/**
 * (サービス利用顧客申込状況一覧抽選無サービス一覧行)<BR>
 * サービス利用顧客申込状況一覧抽選無サービス一覧行データクラス<BR>
 *  
 * @@author 鄭海良
 * @@version 1.0  
 */
public class WEB3AdminSrvRegiNoLotteryStateGroup extends WEB3AdminSrvRegiCustomerInfoCommon 
{

    /**
     * (登録可能区分)<BR>
     * true:可能　@false:不可<BR>
     */
    public boolean registAbleDiv;
    
    /**
     * (変更可能区分)<BR>
     * true:可能　@false:不可<BR>
     */
    public boolean changeAbleDiv;
    
    /**
     * (サービス利用顧客申込状況一覧抽選無サービス一覧行)<BR>
     * デフォルトコンストラクタ<BR>
     * @@roseuid 40EE53C902E4
     */
    public WEB3AdminSrvRegiNoLotteryStateGroup() 
    {
     
    }
}
@
