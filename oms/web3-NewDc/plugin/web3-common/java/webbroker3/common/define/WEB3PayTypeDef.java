head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.04.58.49;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3PayTypeDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 委託手数料枝番登録マスター．弁済区分　@定数定義インタフェイス(WEB3PayTypeDef)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/02/12 髙橋　@良和(SRA) 新規作成
*/
package webbroker3.common.define;

/**
 * 委託手数料枝番登録マスター．弁済区分　@定数定義インタフェイス
 *
 * @@author 髙橋　@良和(SRA)
 * @@version 1.0
 */
public interface WEB3PayTypeDef
{

    /**
     * 制度信用
     */
    public static final String SYS_MARGIN = "1";

    /**
     * 一般信用
     */
    public static final String GEN_MARGIN = "3";

    /**
     * その他
     */
    public static final String OTHER = "00";

}
@
