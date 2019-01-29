head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.02.03.42;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	WEB3AccInfoBatoInfo.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 電子鳩情報メッセージ(WEB3AccInfoBatoInfo.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/12 李海波 (中訊) 新規作成
*/

package webbroker3.accountinfo.message;

import com.fitechlabs.xtrade.kernel.message.Message;


/**
 * (電子鳩情報)<BR>
 * 電子鳩情報メッセージ<BR>
 * 
 * @@author 李海波
 * @@version 1.0
 */
public class WEB3AccInfoBatoInfo extends Message 
{
    
    /**
     * (取引報告書交付状態区分)<BR>
     * 取引報告書交付状態区分<BR>
     * <BR>
     * 0：　@郵便配布<BR>
     * 1：　@電子配布<BR>
     */
    public String tradingReportStateDiv;
    
    /**
     * (取引残高報告書交付状態区分)<BR>
     * 取引残高報告書交付状態区分<BR>
     * <BR>
     * 0：　@郵便配布<BR>
     * 1：　@郵便配布（受渡都度作成）<BR>
     * 9：　@電子配布<BR>
     */
    public String positionReportStateDiv;
    
    /**
     * (取引残高報告書作成周期区分)<BR>
     * 取引残高報告書作成周期区分<BR>
     * <BR>
     * 1：　@毎月<BR>
     * 3：　@3ヶ月<BR>
     */
    public String positionReportCycleDiv;
    
    /**
     * (取引残高報告書預り証作成状態区分)<BR>
     * 取引残高報告書預り証作成状態区分<BR>
     * <BR>
     * 0：　@不作成<BR>
     * 1：　@作成<BR>
     * <BR>
     */
    public String certificateDepositStateDiv;
    
    /**
     * (取引残高報告書計算書作成状態区分)<BR>
     * 取引残高報告書計算書作成状態区分<BR>
     * <BR>
     * 0：　@不作成<BR>
     * 1：　@作成<BR>
     */
    public String accountStatementStateDiv;
    
    /**
     * @@roseuid 418F39EE0271
     */
    public WEB3AccInfoBatoInfo() 
    {
     
    }
}
@
