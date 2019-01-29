head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.00.53;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3TransactionTypeDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 処理区分  定数定義インタフェイス(WEB3TransactionTypeDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/09/23　@彭巍 (SRA) 新規作成
*/
package webbroker3.common.define;

/**
 * 処理区分　@定数定義インタフェイス
 *
 * @@author 彭巍(SRA)
 * @@version 1.0
 */
public interface WEB3TransactionTypeDef
{

    /**
     *  1：売付 
     */
    public static final String SELL = "1";

    /**
     *   2：買付  
     */
    public static final String BUY = "2";
    
    /**
     *  5：その他
     */
    public static final String DEFAULT = "5";
 
     
}
@
