head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.01.59.03;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	WEB3AccInfoMobileOfficeChangeAccount.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : ()åa¤ Ø\[VVXeæñ
File Name        : gÑÔEÎ±æîñÏX\Úq(WEB3AccInfoMobileOfficeChangeAccount.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/10 JN°V (u) VKì¬
Revesion History : 2006/12/14 ü· (u) fNo.153
Revesion History : 2007/01/17 ½¶q (u) fNo.160
*/

package webbroker3.accountinfo.message;

import java.util.Date;

import com.fitechlabs.xtrade.kernel.message.Message;


/**
 * (gÑÔEÎ±æîñÏX\Úq)<BR>
 * gÑÔEÎ±æîñÏX\ÚqbZ[W<BR>
 * @@author JN°V
 * @@version 1.0
 */
public class WEB3AccInfoMobileOfficeChangeAccount extends Message 
{
    
    /**
     * (\ú)<BR>
     * \ú<BR>
     */
    public Date applyDate;
    
    /**
     * (XR[h)<BR>
     * XR[h<BR>
     */
    public String branchCode;
    
    /**
     * (ÚqR[h)<BR>
     * ÚqR[h<BR>
     */
    public String accountCode;
    
    /**
     * (Úq¼)<BR>
     * Úq¼<BR>
     */
    public String accountName;
    
    /**
     * (\ÒR[h)<BR>
     * \ÒR[h<BR>
     */
    public String updaterCode;
    
    /**
     * (»èú)<BR>
     * »èú<BR>
     */
    public Date judgementDate;
    
    /**
     * (»èÒR[h)<BR>
     * »èÒR[h<BR>
     */
    public String judgeCode;
    
    /**
     * (\óµæª)<BR>
     * \óµæª<BR>
     * <BR>
     * 0F@@»èÒ¿<BR>
     * 1F@@»èÒ¿imFj<BR>
     * 2F@@»èÏÝ<BR>
     */
    public String applyStateDiv;
    
    /**
     * (»èÊ)<BR>
     * »èÊ<BR>
     * <BR>
     * 1F³F<BR>
     * 2FsÂ<BR>
     */
    public String judgmentResultDiv;

    /**
     * (ûÀæª)<BR>
     * ûÀæª<BR>
     * <BR>
     * 0FÂlûÀ<BR>
     * 1F@@lûÀ<BR>
     */
    public String accountType;

    /**
     * (gÑdbEÎ±æîñ)<BR>
     */
    public WEB3AccInfoMobileOfficeInfo mobileOfficeInfo;

    /**
     * (ótÊ)<BR>
     * ótÊ<BR>
     * <BR>
     * 1Fót®¹<BR>
     * 9FG[<BR>
     */
    public String acceptedResult;

    /**
     * (gÑÔEÎ±æîñÏX\Úq)<BR>
     * gÑÔEÎ±æîñÏX\Úq<BR>
     * RXgN^<BR>
     * @@return webbroker3.accountinfo.message.WEB3AccInfoMobileOfficeChangeAccount
     * @@roseuid 414982D80319
     */
    public WEB3AccInfoMobileOfficeChangeAccount() 
    {
     
    }
}
@
