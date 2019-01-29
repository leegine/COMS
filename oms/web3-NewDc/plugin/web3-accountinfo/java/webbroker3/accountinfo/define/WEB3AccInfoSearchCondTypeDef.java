head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.02.25.50;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	WEB3AccInfoSearchCondTypeDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 検索条件区分(WEB3AccInfoSearchCondTypeDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/9/22 呉艶飛(sinocom) 新規作成
*/
package webbroker3.accountinfo.define;

/**
 * 検索条件区分 定数定義インタフェイス<BR>
 * <BR>
 * @@author 呉艶飛<BR>
 * @@version 1.0<BR>
 */
public interface WEB3AccInfoSearchCondTypeDef 
{
    /**
     * 0：　@0：全て（ALL）　@　@
     */
    public final static String ALL = "0";
    
    /**
     * 1：　@1：Y客（YACCOUNT）　@　@
     */
    public final static String YACCOUNT = "1";
    
    /**
     * 2：　@2：管理ロック（ADMINLOCK）　@　@
     */
    public final static String ADMINLOCK = "2";
    
    /**
     * 3：　@3：支店ロック（BRANCHLOCK）　@　@
     */
    public final static String BRANCHLOCK = "3";
}
@
