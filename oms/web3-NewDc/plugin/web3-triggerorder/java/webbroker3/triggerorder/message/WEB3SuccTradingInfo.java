head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.41.50;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8704d7ec30c0c9d;
filename	WEB3SuccTradingInfo.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 連続注文取引情報(WEB3SuccTradingInfo.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/10/17 呉艶飛(中訊) 新規作成
*/

package webbroker3.triggerorder.message;

import com.fitechlabs.xtrade.kernel.message.Message;


/**
 * (連続注文取引情報)<BR>
 * 連続注文取引情報<BR>
 * @@author 呉艶飛
 * @@version 1.0
 */
public class WEB3SuccTradingInfo extends Message 
{
    
    /**
     * (商品区分)<BR>
     * 商品区分<BR>
     * <BR>
     * 1：　@現物株式<BR>
     * 2：　@信用取引<BR>
     * 3：　@先物<BR>
     * 4：　@オプション<BR>
     */
    public String commodityType;
    
    /**
     * (連続注文取引区分一覧)<BR>
     * 連続注文取引区分一覧<BR>
     * <BR>
     * 01：　@買付（前提注文）<BR>
     * 02：　@買付<BR>
     * 03：　@売付（前提注文）<BR>
     * 04：　@売付（既存残）<BR>
     * 05：　@信用新規建（前提注文）<BR>
     * 06：　@信用新規建<BR>
     * 07：　@信用返済（前提注文）<BR>
     * 08：　@信用返済（既存残）<BR>
     * 09：　@現引現渡（前提注文）<BR>
     * 10：　@現引現渡（既存残）<BR>
     * 11：　@先物新規建（前提注文）<BR>
     * 12：　@先物新規建<BR>
     * 13：　@先物返済（前提注文）<BR>
     * 14：　@先物返済（既存残）<BR>
     * 15：　@OP新規建（前提注文）<BR>
     * 16：　@OP新規建<BR>
     * 17：　@OP返済（前提注文）<BR>
     * 18：　@OP返済（既存残）<BR>
     */
    public String[] succTradingTypeList;
    
    /**
     * (連続注文取引情報)<BR>
     * コンストラクタ<BR>
     * @@roseuid 431F849400FE
     */
    public WEB3SuccTradingInfo() 
    {
     
    }
}
@
