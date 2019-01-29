head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.05.13.45;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7844d7ef4f02f89;
filename	WEB3AdminMailInfoGroup.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : メール情報一覧行(WEB3AdminMailInfoGroup.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/15  魏馨(中訊) 新規作成
*/
package webbroker3.mailinfo.message;

import com.fitechlabs.xtrade.kernel.message.Message;


/**
 * (メール情報一覧行)<BR>
 * メール情報一覧行　@データクラス<BR>
 * 
 * @@author 魏馨
 * @@version 1.0
 */
public class WEB3AdminMailInfoGroup extends Message 
{
    
    /**
     * (送信メール区分)<BR>
     */
    public String sendMailDiv;
        
    /**
     * (識別ID)<BR>
     */
    public String discernId;
    
    /**
     * (メール名称)<BR>
     */
    public String mailName;
    
    /**
     * (差出人)<BR>
     */
    public String mailFrom;
    
    /**
     * (件名)<BR>
     */
    public String mailSubject;
    
    /**
     * (メール情報一覧行)<BR>
     * デフォルトコンストラクタ<BR>
     * @@roseuid 413C0E3B038A
     */
    public WEB3AdminMailInfoGroup() 
    {
     
    }
}
@
