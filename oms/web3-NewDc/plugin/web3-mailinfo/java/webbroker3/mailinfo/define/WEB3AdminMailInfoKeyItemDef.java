head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.05.16.49;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7844d7ef4f02f89;
filename	WEB3AdminMailInfoKeyItemDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : キー項目定義インタフェイス(WEB3AdminMailInfoKeyItemDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/15  魏馨(中訊) 新規作成
*/
package webbroker3.mailinfo.define;

/* 
 * @@author 魏馨
 * @@version 1.0.1
 */
public interface WEB3AdminMailInfoKeyItemDef
{
    /*
     * プログラムID          
     */
    public static final String SENDMAIL_DIV = "sendMailDiv";

    /*
     * 識別ID      
     */
    public static final String DISCERN_ID = "discernId";

}
@
