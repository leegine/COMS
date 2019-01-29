head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.25;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3FeqCommonMessageCreatedService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 外国株式共通メッセージ作成サービス(WEB3FeqCommonMessageCreatedService)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/07/13  艾興(中訊) 新規作成
*/
package webbroker3.feq.service.delegate;

import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqFinTransactionParams;

import webbroker3.common.WEB3BaseException;
import webbroker3.feq.WEB3FeqOrderExecution;
import webbroker3.feq.WEB3FeqOrderUnit;
import webbroker3.feq.message.WEB3AdminFeqExecuteGroup;
import webbroker3.feq.message.WEB3FeqExecDetailInfoUnit;
import webbroker3.feq.message.WEB3FeqOrderAndExecutionUnit;
import webbroker3.feq.message.WEB3FeqOrderCommonUnit;

/**
 * (外国株式共通メッセージ作成サービス) <BR>
 * 外国株式共通メッセージ作成サービスインタフェイス
 * @@author 艾興
 * @@version 1.0 
 */
public interface WEB3FeqCommonMessageCreatedService
{
    
    /**
     * (create外国株式注文共通明細) <BR>
     * 注文単位の内容で、外国株式注文共通明細メッセージ <BR>
     * オブジェクトプロパティに値をセットする。 <BR>
     * @@param l_feqOrderCommonUnit - (外国株式注文共通明細) <BR>
     * 外国株式注文共通明細メッセージオブジェクト
     * 
     * @@param l_feqOrderUnit - (注文単位)
     * @@throws WEB3BaseException
     * @@roseuid 429448520095
     */
    public void createFeqOrderCommonUnit(WEB3FeqOrderCommonUnit l_feqOrderCommonUnit, 
        WEB3FeqOrderUnit l_feqOrderUnit) throws WEB3BaseException;
    
    /**
     * (create外国株式約定詳細（管理者）) <BR>
     * 約定，トランザクション（取引勘定明細）の内容で、 <BR>
     * 外国株式約定詳細（管理者）メッセージオブジェクトプロパティに値をセットする。 <BR>
     * @@param l_adminFeqExecDetailInfoUnit - (外国株式約定詳細（管理者）) <BR>
     * 外国株式約定詳細（管理者）メッセージオブジェクト
     * @@param l_feqExecute - (約定)
     * @@param l_feqFinTransactionParams - (トランザクション（取引勘定明細）行) <BR>
     * トランザクション（取引勘定明細）行オブジェクト
     * @@throws WEB3BaseException
     * @@roseuid 42A0461403C7
     */
    public void createAdminFeqExecDetailInfoUnit(WEB3FeqExecDetailInfoUnit l_adminFeqExecDetailInfoUnit, 
        WEB3FeqOrderExecution l_feqExecute, FeqFinTransactionParams l_feqFinTransactionParams) 
        throws WEB3BaseException;
    
    /**
     * (create外国株式注文明細（管理者）) <BR>
     * 約定，トランザクション（取引勘定明細）の内容で、 <BR>
     * 外国株式注文明細（管理者）メッセージオブジェクトプロパティに値をセットする。 <BR>
     * @@param l_adminFeqExecuteGroup - (外国株式注文明細（管理者）) <BR>
     * 外国株式注文明細（管理者）メッセージオブジェクト
     * @@param l_feqOrderUnit - (注文単位)
     * @@throws WEB3BaseException
     * @@roseuid 4294485200A4
     */
    public void createAdminFeqExecuteGroup(WEB3AdminFeqExecuteGroup l_adminFeqExecuteGroup, 
        WEB3FeqOrderUnit l_feqOrderUnit) throws WEB3BaseException;
    
    /**
     * (create外国株式約定入力情報) <BR>
     * 注文単位，約定，トランザクション（取引勘定明細）の内容で、 <BR>
     * 外国株式注文明細（管理者）メッセージオブジェクト <BR>
     * プロパティに値をセットする。 <BR>
     * @@param l_feqOrderAndExecutionUnit - (外国株式約定入力情報) <BR>
     * 外国株式約定入力情報メッセージ
     * @@param l_feqOrderUnit - (注文単位)
     * @@param l_feqExecute - (約定)
     * @@param l_feqFinTransactionParams - (トランザクション（取引勘定明細）行) <BR>
     * トランザクション（取引勘定明細）行オブジェクト
     * @@throws WEB3BaseException
     * @@roseuid 4294485200B4
     */
    public void createFeqOrderAndExecutionUnit(WEB3FeqOrderAndExecutionUnit l_feqOrderAndExecutionUnit, 
            WEB3FeqOrderUnit l_feqOrderUnit, WEB3FeqOrderExecution l_feqExecute, 
            FeqFinTransactionParams l_feqFinTransactionParams) throws WEB3BaseException;
}
@
