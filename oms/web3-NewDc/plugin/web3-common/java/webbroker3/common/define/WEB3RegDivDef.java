head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.04.57.26;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3RegDivDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 定数定義インタフェイス(WEB3RegDivDef)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/07/22 li-yingyuan(sinocom)　@新規作成
*/
package webbroker3.common.define;

/**
 * 登録区分　@定数定義インタフェイス
 *                                                                      
 * @@author li-yingyuan
 * @@version 1.0
 */
public interface WEB3RegDivDef
{
    /**
     * 1 : 新規
     */
    public static final String NEW = "1";

    /**
     * 2 : 変更
     */
    public static final String CHANGE = "2";
    
    /**
     * 3 : 抹消
     */
    public static final String OBLITERATE = "3";
    
    /**
     * 4 : 抹消取消
     */
    public static final String OBLITERATE_CANCEL = "4";

}
@
