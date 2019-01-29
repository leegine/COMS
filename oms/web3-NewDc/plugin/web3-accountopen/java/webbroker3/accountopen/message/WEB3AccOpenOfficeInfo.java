head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.02.30;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AccOpenOfficeInfo.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 勤務先情報(WEB3AccOpenOfficeInfo.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/17 鄭海良(中訊) 新規作成
*/

package webbroker3.accountopen.message;

import com.fitechlabs.xtrade.kernel.message.Message;


/**
 * (勤務先情報)<BR>
 * 勤務先情報<BR>
 *   
 * @@author 鄭海良
 * @@version 1.0
 */
public class WEB3AccOpenOfficeInfo extends Message 
{
    
    /**
     * (職業区分)<BR>
     * 職業区分<BR>
     * <BR>
     * （※ 項目値については各社カスタマイズあり）<BR>
     */
    public String occupationDiv;
    
    /**
     * (勤務先名称)<BR>
     * 勤務先名称<BR>
     */
    public String officeName;
    
    /**
     * (勤務先郵便番号)<BR>
     * 勤務先郵便番号<BR>
     */
    public String officeZipCode;
    
    /**
     * (勤務先住所)<BR>
     * 勤務先住所<BR>
     */
    public String officeAdress;
    
    /**
     * (勤務先電話番号)<BR>
     * 勤務先電話番号<BR>
     * <BR>
     * ※　@"-"を含む文字列<BR>
     */
    public String officeTelephone;
    
    /**
     * (勤務先ＦＡＸ番号)<BR>
     * 勤務先ＦＡＸ番号<BR>
     * <BR>
     * ※　@"-"を含む文字列<BR>
     */
    public String officeFaxTelephone;
    
    /**
     * (所属部署)<BR>
     * 所属部署<BR>
     */
    public String department;
    
    /**
     * (役職名)<BR>
     * 役職名<BR>
     */
    public String position;
    
    /**
     * @@roseuid 41B45E790157
     */
    public WEB3AccOpenOfficeInfo() 
    {
     
    }
}
@
