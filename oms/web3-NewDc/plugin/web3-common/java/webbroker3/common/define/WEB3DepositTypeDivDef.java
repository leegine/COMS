head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.04.48.25;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3DepositTypeDivDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 預り区分  定数定義インタフェイス(WEB3DepositTypeDivDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/09/23　@彭巍 (SRA) 新規作成
*/
package webbroker3.common.define;

/**
 * 預り区分　@定数定義インタフェイス
 *
 * @@author 彭巍(SRA)
 * @@version 1.0
 */
public interface WEB3DepositTypeDivDef
{

    /**
     *  01:預り金 
     */
    public static final String FROM_DEPOSIT = "01";

    /**
     *  04:保証金  
     */
    public static final String GUARANTEE = "04";
    
    /**
     *  05:株先証拠金　@
     */
    public static final String STOCK_FUTURES_MARGIN = "05";
    
    /**
     *  86：為替保証金　@
     */
    public static final String  EXCHANGE_GUARANTEE = "86";
 
   
     
}
@
