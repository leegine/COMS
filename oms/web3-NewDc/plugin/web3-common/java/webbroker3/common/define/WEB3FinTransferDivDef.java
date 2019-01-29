head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.04.40.13;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3FinTransferDivDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 定数定義インタフェイス(WEB3MethodTypeDef)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/09 王蘭芬　@新規作成
*/
package webbroker3.common.define;

/**
 * 振込先金融機@関テーブルの振替区分　@定数定義インタフェイス
 *                                                                     
 * @@author 王蘭芬
 * @@version 1.0
 */
public interface WEB3FinTransferDivDef {
    /**
     * 1=銀行振込  
     */
    public static final String BANK_TRANSFER = "1";
    
    /**
     * ５=郵便振込  
     */
    public static final String POST_TRANSFER = "5";
}

@
