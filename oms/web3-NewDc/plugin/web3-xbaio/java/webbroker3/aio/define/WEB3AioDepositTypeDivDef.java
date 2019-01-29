head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.04.46.05;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AioDepositTypeDivDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 定数定義インタフェイス(WEB3AioDepositTypeDivDef)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/06 王蘭芬(中訊) 新規作成
*/
package webbroker3.aio.define;

/**
 * 「証券振替一覧リクエスト」「証券振替共通リクエスト」<BR>
 * 「預り証券明細」の「預り区分」定数定義インタフェイス
 *                                                                     
 * @@author 王蘭芬
 * @@version 1.0
 */
public interface WEB3AioDepositTypeDivDef {
    
    /**
     *  0：指定無し
     */
    public static final String DEFAULT = "0";
    
    /**
     *  1：保護（保護から代用への振替）
     */
    public static final String  SAFE_DEPOSIT = "1";
    
    /**
     *  2：代用（代用から保護への振替）
     */
    public static final String COLLATERAL_SECURITY = "2";
}
@
