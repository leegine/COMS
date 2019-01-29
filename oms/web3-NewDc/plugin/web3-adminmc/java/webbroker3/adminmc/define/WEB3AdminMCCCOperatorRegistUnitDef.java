head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.50.56;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AdminMCCCOperatorRegistUnitDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 定数定義インタフェイス(WEB3AdminMCCCOperatorRegistUnitDef)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/18  範慧琴(sinocom)　@新規作成
*/
package webbroker3.adminmc.define;

/**
 * CCオペレータ登録情報 定数定義インタフェイス
 *                                                                     
 * @@author 範慧琴
 * @@version 1.0
 */
public interface WEB3AdminMCCCOperatorRegistUnitDef
{
    /**
     * branchCode: 部店コード 
     */
    public static final String BRANCH_CODE = "branchCode";
    
    /**
     * operatorCode: オペレータコード
     */
    public static final String OPERATOR_CODE = "operatorCode";
    
    
    /**
     * accountOrderDiv: 代行注文可能区分
     */
    public static final String ACCOUNT_ORDER_DIV = "accountOrderDiv";
    
    /**
     * departmentCode: 所属コード
     */
    public static final String DEPARTMENT_CODE = "departmentCode";
    
    /**
     * errorCount:エラー回数
     */
    public static final String ERROR_COUNT = "errorCount";
    
    /**
     * updateTimeStamp: 更新日時
     */
    public static final String UPDATE_TIME_STAMP = "updateTimeStamp";
    
    /**
     * updaterCode: 更新者コード
     */
    public static final String UPDATER_CODE = "updaterCode";   
  
}
@
