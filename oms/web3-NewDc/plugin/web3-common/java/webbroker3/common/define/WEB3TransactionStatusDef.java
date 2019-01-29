head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.04.49.02;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3TransactionStatusDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 処理区分  定数定義インタフェイス(WEB3TransactionStatusDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/09/24　@彭巍 (SRA) 新規作成
*/
package webbroker3.common.define;

/**
 * 処理区分　@定数定義インタフェイス
 *
 * @@author 彭巍(SRA)
 * @@version 1.0
 */
public interface WEB3TransactionStatusDef
{

    /**
     * ０：未処理　@ 
     */
    public static final String NOT_DEAL = "0";

    /**
     * １：OK　@
     */
    public static final String OK = "1";
    
    /**
     *  ２：NG　@
     */
    public static final String NG = "2";
    
    /**
     * ３：エラー  
     */
    public static final String ERROR = "3"; 
   
}
@
