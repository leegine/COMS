head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.04.25.43;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3SystemHandlingDivDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : システム取扱区分定数定義インタフェイス(WEB3SystemHandlingDivDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/07/26 劉江涛(sinocom) 新規作成
*/
package webbroker3.common.define;

/**
 * システム取扱区分 定数定義インタフェイス
 *
 * @@author 劉江涛
 * @@version 1.0
 */
public interface WEB3SystemHandlingDivDef
{

    /**
     * 0：WEBBROKERⅢで取り扱わない　@　@  　@　@
     */
    public final static String WEBBROKER3_DONOT_TREAT_IT_IN = "0";

    /**
     * 1：WEBBROKERⅢで取り扱う
     */
    public final static String WEBBROKER3_TREAT_IT_IN = "1";
  
    /**
     * 2:郵送請求のみ (nullの場合、変更無しとする)
     */
    public final static String MAIL_REQUEST_ONLY = "2";
}
@
