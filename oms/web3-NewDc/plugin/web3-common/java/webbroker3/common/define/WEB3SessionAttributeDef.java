head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.04.29.05;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3SessionAttributeDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : セッション属性名定数定義クラス(WEB3SessionAttributeDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/03/31 菊地(SRA) 新規作成
*/
package webbroker3.common.define;

/**
 * セッション属性名定数定義クラス
 *
 * @@author 菊地(SRA)
 * @@version 1.0
 */
public interface WEB3SessionAttributeDef
{
    /**
     * xTradeセッションID
     */
    public static final String SESSION_ID = "sessionID";
    
    /**
     * 注文チャネル
     */
    public static final String ORDER_CHANNEL = "orderChannel";
    
    /**
     * 注文経路区分
     */
    public static final String ORDER_ROOT_DIV = "orderRootDiv";
    
    /**
     * 会社ID
     */
    public static final String INSTITUTION_ID = "institutionID";
    
    /**
     * 部店ID
     */
    public static final String BRANCH_ID = "branchID";
    
    /**
     * CCオペレータID
     */
    public static final String CCOPERATOR_ID = "ccOperatorID";
    
    /**
     * 権限レベル
     */
    public static final String RIGHT_LEVEL = "rightLevel";
    
    /**
     * IPアドレス
     */
    public static final String IP_ADDRESS = "ipAddress";
}@
