head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.04.41.03;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3SoapConnectTypeDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : SOAP接続形式定数定義インタフェイス(WEB3SoapConnectTypeDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/02/15 凌建平(中訊) 新規作成
*/
package webbroker3.common.define;

/**
 * SOAP接続形式 定数定義インタフェイス
 *
 * @@author 凌建平
 * @@version 1.0
 */
public interface WEB3SoapConnectTypeDef
{
    /**
     * 0：メッセージ形式  　@　@　@　@　@  　@　@
     */
    public final static String DOCMENT = "0";

    /**
     * 1：RPC形式　@
     */
    public final static String RPC = "1";  
} @
