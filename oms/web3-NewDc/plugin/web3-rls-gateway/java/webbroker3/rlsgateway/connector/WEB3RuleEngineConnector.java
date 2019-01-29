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
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : WEB3���[���G���W���R�l�N�^(WEB3RuleEngineConnector.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/05/15 �V�� �h�O(FLJ) �V�K�쐬
*/
package webbroker3.rlsgateway.connector;

import com.fitechlabs.fin.intellioms.rlsclt.RuleEngineConnector;
import com.fitechlabs.fin.intellioms.rlsclt.RuleEngineConnectorException;
import com.fitechlabs.fin.intellioms.rulesys.CondOrder;
import com.fitechlabs.fin.intellioms.rulesys.CondOrderOperation;

/**
 *
 * WEB3���[���G���W���R�l�N�^
 * @@author Eizo Saito (FLJ)
 * @@version 1.0
 */
public interface WEB3RuleEngineConnector extends RuleEngineConnector
{
    /**
     * ���[���G���W���R�l�N�^��ݒ肷��B
     * 
     * @@param RuleEngineConnector - (���[���G���W���R�l�N�^)
     */
    public void setRuleEngineConnector(RuleEngineConnector ruleEngineConnector);
    
    /**
     * �Đڑ�����B
     */
    public void restart();
    
    /**
     * �ڑ��ς݂����f����B
     */
    public boolean isConnected();
    
    /**
     * �ڑ��ςݔ���t���O��ݒ肷��B
     * 
     * @@param boolean - (�ڑ��ςݔ���t���O)
     */
    public void setConnected(boolean isConnected);
    
    /**
     * ���g���C�����t�������o�^�B
     * 
     * @@param CondOrder - (�����t������)
     */
    public void retryRegisterCondOrder(CondOrder l_condOrder) throws RuleEngineConnectorException;
    
    /**
     * ���g���C�����t�������I�y���[�V�����B
     * 
     * @@param CondOrderOperation - (�����t�������I�y���[�V����)
     */
    public void retrySubmitCondOrderOperation(CondOrderOperation l_operation) throws RuleEngineConnectorException;
}
@
