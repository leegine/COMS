head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.04.37.32;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3SoapConnectDivDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 接続区分  定数定義インタフェイス(WEB3SoapConnectDivDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/03/01 凌建平(中訊) 新規作成
*/
package webbroker3.common.define;

/**
 * 接続区分 定数定義インタフェイス
 *
 * @@author 凌建平(中訊)
 * @@version 1.0
 */
public interface WEB3SoapConnectDivDef                
{
    /**
     *  01： （FX）東京金融先物取引所 振替登録 
     */
    public static final String  FX_TOKYO_TRANSFER_SUBMIT = "01";
}
@
