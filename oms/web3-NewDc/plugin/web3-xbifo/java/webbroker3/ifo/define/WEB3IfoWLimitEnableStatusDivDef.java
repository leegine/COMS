head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.34;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3IfoWLimitEnableStatusDivDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : W指値用有効状態区分(WEB3IfoWLimitEnableStatusDivDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/07/13  徐宏偉 (中訊) 新規作成
*/
package webbroker3.ifo.define;

/**
 * W指値用有効状態区分<BR>
 * 
 * @@author 徐宏偉
 * @@version 1.0
 */
public interface WEB3IfoWLimitEnableStatusDivDef 
{
    /**
     * 0：リミット注文有効　@
     */
    public static final String LIMIT_ENABLE = "0";
    
    /**
     * 1：ストップ注文有効 
     */
    public static final String STOP_ENABLE  = "1";
    
    /**
     * 2：ストップ注文失効済
     */
    public static final String STOP_UN_ENABLE = "2";
}
@
