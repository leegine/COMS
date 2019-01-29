head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.04.59.18;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3FundTypeDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 商品区分  定数定義インタフェイス(WEB3FundTypeDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/09/22　@彭巍 (SRA) 新規作成
*/
package webbroker3.common.define;

/**
 * 商品区分　@定数定義インタフェイス
 *
 * @@author 彭巍(SRA)
 * @@version 1.0
 */
public interface WEB3FundTypeDef
{

    /**
     *  3：中国F 
     */
    public static final String MIDIUM_TERM_GOV_FUND = "3";

    /**
     *    4：MMF  
     */
    public static final String MMF_SET = "4";
    
    /**
     *  5：出金
     */
    public static final String PAYMENT = "5";
 
     
}
@
