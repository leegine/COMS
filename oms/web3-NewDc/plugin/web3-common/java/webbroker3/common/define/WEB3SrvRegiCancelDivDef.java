head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.04.27.48;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3SrvRegiCancelDivDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 取消区分(WEB3SrvRegiCancelDivDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/11 郭英(sinocom) 新規作成
*/
package webbroker3.common.define;

/**
 * サービス申込登録テーブル.取消区分
 *
 * @@author 郭英
 * @@version 1.0
 */
public interface WEB3SrvRegiCancelDivDef
{

    /**
     * 0:通常（default）　@　@
     */
    public final static String USUAL_DEFAULT = "0";

    /**
     * 1:取消　@
     */
    public final static String CANCEL = "1";
    
}@
