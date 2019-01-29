head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.02;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3BondReferenceTypeDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 照会区分定数定義インタフェイス(WEB3BondReferenceTypeDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/9/5 郭英(中訊) 新規作成
*/
package webbroker3.bd.define;

/**
 * 照会区分定数定義インタフェイス
 * 
 * @@author 郭英
 * @@version 1.0 
 */
public interface WEB3BondReferenceTypeDef
{
    /**
     * 応募一覧
     */
    public static final String RECRUIT_LIST = "1";
    
    /**
     * 買付一覧
     */
    public static final String BUY_LIST = "2";
    
    /**
     * 応募/買付一覧
     */
    public static final String RECRUIT_BUY_LIST = "3";
    
    /**
     * 0：注文約定照会
     */
    public static final String EXECUTE_REFERENCE = "0";
    
    /**
     * 1：取消一覧
     */
    public static final String CANCEL_LIST = "1";
}
@
