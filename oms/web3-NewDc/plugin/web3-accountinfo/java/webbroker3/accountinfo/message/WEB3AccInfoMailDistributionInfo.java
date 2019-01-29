head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.02.08.37;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	WEB3AccInfoMailDistributionInfo.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 案内メール配信指示情報(WEB3AccInfoMailDistributionInfo)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/13 彭巍 (中訊) 新規作成
*/

package webbroker3.accountinfo.message;

import java.util.Date;

import com.fitechlabs.xtrade.kernel.message.Message;

/**
 * (案内メール配信指示情報)<BR>
 * 案内メール配信指示情報<BR>
 */
public class WEB3AccInfoMailDistributionInfo extends Message 
{    
    /**
     * (ID)<BR>
	 *	案内メール配信指示ID<BR>
     */
    public String id;
    
    /**
     * (配信指示日時)<BR>
     */
    public Date distributionRequestDate;
    
    /**
     * (配信顧客数)<BR>
     */
    public String distributionAccountNumber;
    
    /**
     * (全顧客フラグ)<BR>
     * true：　@全顧客に配信<BR>
     * false：　@案内メール希望客のみに配信<BR>
     */
    public boolean allAccountFlag;
    
    /**
     * (件名)<BR>
     *<BR>
     *  ※ encodeした値<BR>
     */
    public String mailSubject;
    
    /**
     * (更新者コード)<BR>
     *<BR>
     *  ※　@配信指示者<BR>
     */
    public String updaterCode;
    
    /**
     * (案内メール配信指示情報)<BR>
     * デフォルトコンストラクタ<BR>
     * @@return webbroker3.accountinfo.message.WEB3AccInfoMailDistributionInfo
     * @@roseuid 415BCF700005
     */
    public WEB3AccInfoMailDistributionInfo() 
    {
     
    }
}
@
