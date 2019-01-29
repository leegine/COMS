head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.04.30.37;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3ErrorCommentDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : エラーコメント定数定義インタフェイス(WEB3ErrorCommentDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/01/19 凌建平(sinocom) 新規作成
*/
package webbroker3.common.define;

/**
 * エラーコメント定数定義インタフェイス
 *
 * @@author 凌建平
 * @@version 1.0
 */
public interface WEB3ErrorCommentDef
{

    /**
     * 名寄せエラー：入金エラーコメント　@
     */
    public final static String DEPOSIT_ERROR_COMMENT = "名寄せエラー";  
} @
