head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.08.05.19;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7444d8857e2568b;
filename	WEB3PvInfoSettleContractUnit.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 決済期限間近の建玉情報(WEB3PvInfoSettleContractUnit.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/18 王亞洲(中訊) 新規作成
*/
package webbroker3.pvinfo.message;

import java.util.Date;

import com.fitechlabs.xtrade.kernel.message.Message;


/**
 * (決済期限間近の建玉情報)<BR>
 * 決済期限間近の建玉情報クラス<BR>
 * @@author 王亞洲
 * @@version 1.00
 */
public class WEB3PvInfoSettleContractUnit extends Message 
{
    
    /**
     * (期日の1営業日前)<BR>
     * 期日の1営業日前<BR>
     */
    public Date beforeBizDate;
    
    /**
     * (銘柄コード)<BR>
     * 銘柄コード<BR>
     */
    public String productCode;
    
    /**
     * (銘柄名)<BR>
     * 銘柄名<BR>
     */
    public String productName;
    
    /**
     * (決済期限間近の建玉情報)<BR>
     * コンストラクタ。<BR>
     * @@return webbroker3.pvinfo.message.WEB3PvInfoSettleContractUnit
     * @@roseuid 4147AA4100A8
     */
    public WEB3PvInfoSettleContractUnit() 
    {
     
    }
}
@
