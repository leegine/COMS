head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.02.59.26;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AccOpenInspectInfo.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : ()åa¤ Ø\[VVXeæñ
File Name        : ûÀJÝR¸îñ(WEB3AccOpenInspectInfo.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/06/16 £Hn (u) VKì¬
*/

package webbroker3.accountopen.message;

import java.util.Date;

import com.fitechlabs.xtrade.kernel.message.Message;

/**
 * (ûÀJÝR¸îñ)<BR>
 * ûÀJÝR¸îñ
 * 
 * @@author £Hn
 * @@version 1.0
 */
public class WEB3AccOpenInspectInfo extends Message
{
    /**
     * (XR[h)<BR>
     * XR[h
     */
    public String branchCode;
    
    /**
     * (ÚqR[h)<BR>
     * ÚqR[h
     */
    public String accountCode;
    
    /**
     * (¯ÊR[h)<BR>
     * ¯ÊR[h
     */
    public String requestNumber;
    
    /**
     * (Úq¼iJij)<BR>
     * Úq¼iJij
     */
    public String accountNameKana;
    
    /**
     * (¶NúN)<BR>
     * ¶NúN<BR>
     * <BR>
     * 1F¾¡@@2Få³@@3Fºa@@4F½¬@@9Fs¾
     */
    public String eraBorn;
    
    /**
     * (¶Nú)<BR>
     * ¶Nú<BR>
     * <BR>
     * aïiYYMMDDjF6
     */
    public String bornDate;
    
    /**
     * (dbÔ)<BR>
     * dbÔ<BR>
     * <BR>
     * ¦@@"-"ðÜÞ¶ñ
     */
    public String telephone;
    
    /**
     * (gÑÔ)<BR>
     * gÑÔ<BR>
     * <BR>
     * ¦@@"-"ðÜÞ¶ñ
     */
    public String mobileTelephone;
    
    /**
     * ([AhX)<BR>
     * [AhX
     */
    public String mailAddress;
    
    /**
     * (ZP)<BR>
     * ZP
     */
    public String address1;
    
    /**
     * (ZQ)<BR>
     * ZQ
     */
    public String address2;
    
    /**
     * (ZR)<BR>
     * ZR
     */
    public String address3;
    
    /**
     * (­¶ú)<BR>
     * ­¶ú
     */
    public Date occurredDate;
    
    /**
     * (R¸íÊ)<BR>
     * R¸íÊ<BR>
     * <BR>
     * 1F¯êÚq`FbN@@2F¯êÚqi©j`FbN@@3FYq`FbN
     */
    public String reviewCode;
    
    /**
     * (R¸æª)<BR>
     * R¸æª<BR>
     * <BR>
     * 0FR¸Ò¿@@1FFÂ@@2FÛF
     */
    public String checkDiv;
    
    /**
     * (R¸ú)<BR>
     * R¸ú
     */
    public Date checkDate;
    
    /**
     * (R¸SÒ)<BR>
     * R¸SÒ
     */
    public String checkerCode;
    
    /**
     * (d¡æª)<BR>
     * d¡æª
     */
    public String duplicateDiv;
    
    /**
     * (d¡XR[h)<BR>
     * d¡XR[h
     */
    public String dupliBranchCode;
    
    /**
     * (d¡ÚqR[h)<BR>
     * d¡ÚqR[h
     */
    public String dupliAccountCode;
    
    /**
     * (d¡Úq¼iJij)<BR>
     * d¡Úq¼iJij
     */
    public String dupliAccountNameKana;
    
    /**
     * (d¡¶NúN)<BR>
     * d¡¶NúN<BR>
     * <BR>
     * 1F¾¡@@2Få³@@3Fºa@@4F½¬@@9Fs¾
     */
    public String dupliEraBorn;
    
    /**
     * (d¡¶Nú)<BR>
     * d¡¶Nú<BR>
     * <BR>
     * aïiYYMMDDjF6
     */
    public String dupliBornDate;
    
    /**
     * (d¡dbÔ)<BR>
     * d¡dbÔ<BR>
     * <BR>
     * ¦@@"-"ðÜÞ¶ñ
     */
    public String dupliTelephone;
    
    /**
     * (d¡gÑÔ)<BR>
     * d¡gÑÔ<BR>
     * <BR>
     * ¦@@"-"ðÜÞ¶ñ
     */
    public String dupliMobileTelephone;
    
    /**
     * (d¡[AhX)<BR>
     * d¡[AhX
     */
    public String dupliMailAddress;
    
    /**
     * (d¡ZP)<BR>
     * d¡ZP
     */
    public String dupliAddress1;
    
    /**
     * (d¡ZQ)<BR>
     * d¡ZQ
     */
    public String dupliAddress2;
    
    /**
     * (d¡ZR)<BR>
     * d¡ZR
     */
    public String dupliAddress3;
    
    /**
     * (YqID)<BR>
     * YqID
     */
    public String yellowAccountId;
    
    /**
     * (YqÇXR[h)<BR>
     * YqÇXR[h
     */
    public String yAccountBranchCode;
    
    /**
     * (YqÆ±æª)<BR>
     * YqÆ±æª
     */
    public String yAccountBusinessDiv;
    
    /**
     * (YqÇNo)<BR>
     * YqÇNo
     */
    public String yAccountMngNo;
    
    /**
     * @@roseuid 44912C0E03A9
     */
    public WEB3AccOpenInspectInfo() 
    {
    }
}
@
