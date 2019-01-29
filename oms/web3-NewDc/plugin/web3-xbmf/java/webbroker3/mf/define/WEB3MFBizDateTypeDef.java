head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.45;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3MFBizDateTypeDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 営業日区分 定数定義インタフェイス(WEB3MFBizDateTypeDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/16 王蘭芬(中訊)　@新規作成
*/
package webbroker3.mf.define;

/**
 * 営業日区分　@定数定義インタフェイス
 *                                                                     
 * @@author 王蘭芬
 * @@version 1.0
 */
public interface WEB3MFBizDateTypeDef
{
    /**
     * 0:営業日 
     */
    public static final String BIZDATE = "0";

    /**
     * 1:非営業日
     */
    public static final String NOT_BIZDATE = "1";

}
@
