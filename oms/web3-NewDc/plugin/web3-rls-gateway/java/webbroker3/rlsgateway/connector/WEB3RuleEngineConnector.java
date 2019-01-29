head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.08.22.44;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7944d885c296483;
filename	WEB3RuleEngineConnector.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/*
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : WEB3ルールエンジンコネクタ(WEB3RuleEngineConnector.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/05/15 齋藤 栄三(FLJ) 新規作成
*/
package webbroker3.rlsgateway.connector;

import com.fitechlabs.fin.intellioms.rlsclt.RuleEngineConnector;
import com.fitechlabs.fin.intellioms.rlsclt.RuleEngineConnectorException;
import com.fitechlabs.fin.intellioms.rulesys.CondOrder;
import com.fitechlabs.fin.intellioms.rulesys.CondOrderOperation;

/**
 *
 * WEB3ルールエンジンコネクタ
 * @@author Eizo Saito (FLJ)
 * @@version 1.0
 */
public interface WEB3RuleEngineConnector extends RuleEngineConnector
{
    /**
     * ルールエンジンコネクタを設定する。
     * 
     * @@param RuleEngineConnector - (ルールエンジンコネクタ)
     */
    public void setRuleEngineConnector(RuleEngineConnector ruleEngineConnector);
    
    /**
     * 再接続する。
     */
    public void restart();
    
    /**
     * 接続済みか判断する。
     */
    public boolean isConnected();
    
    /**
     * 接続済み判定フラグを設定する。
     * 
     * @@param boolean - (接続済み判定フラグ)
     */
    public void setConnected(boolean isConnected);
    
    /**
     * リトライ条件付き注文登録。
     * 
     * @@param CondOrder - (条件付き注文)
     */
    public void retryRegisterCondOrder(CondOrder l_condOrder) throws RuleEngineConnectorException;
    
    /**
     * リトライ条件付き注文オペレーション。
     * 
     * @@param CondOrderOperation - (条件付き注文オペレーション)
     */
    public void retrySubmitCondOrderOperation(CondOrderOperation l_operation) throws RuleEngineConnectorException;
}
@
