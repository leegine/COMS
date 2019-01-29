head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.07.48.16;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7944d8853e14978;
filename	WEB3MQSendMessageRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/*
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : WEB3MQSendMessageRequest�N���X(WEB3MQSendMessageRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/01/31 �R�c�@@��i(FLJ) �V�K�쐬
 */
package webbroker3.mqgateway.manager.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * 
 *
 * @@author Takuji Yamada (FLJ)
 * @@version 1.0
 */
public class WEB3MQSendMessageRequest extends WEB3GenRequest
{
    
    public static final String PTYPE = "mq_send_message";
    
    public WEB3MQSendMessageRequest()
    {
        super();
    }
    
    /**
     * ��ЃR�[�h
     */
    public String institutionCode;
    
    /**
     * �f�[�^�R�[�h
     */
    public String dataCode;
    
    /**
     * ���[�U�[�f�[�^
     */
    public String userData;
    
    /**
     * OracleSID
     */
    public String oracleSID;
    
    /**
     * �ڋqID�i���j
     */
    public String accountIdStart;
    
    /**
     * �ڋqID�i���j
     */
    public String accountIdEnd;
    
    /**
     * �������iyyyyMMdd�j
     */
    public String bizDate;


    /**
     * @@return dataCode ���擾����B
     */
    public String getDataCode()
    {
        return dataCode;
    }
    /**
     * @@return institutionCode ���擾����B
     */
    public String getInstitutionCode()
    {
        return institutionCode;
    }
    /**
     * @@return userData ���擾����B
     */
    public String getUserData()
    {
        return userData;
    }
    
    /**
     * @@return accountIdEnd ���擾����B
     */
    public String getAccountIdEnd()
    {
        return accountIdEnd;
    }
    /**
     * @@return accountIdStart ���擾����B
     */
    public String getAccountIdStart()
    {
        return accountIdStart;
    }
    /**
     * @@return bizDate ���擾����B
     */
    public String getBizDate()
    {
        return bizDate;
    }
    /**
     * @@return oracleSID ���擾����B
     */
    public String getOracleSID()
    {
        return oracleSID;
    }
    
    /**
     * @@see webbroker3.common.message.WEB3GenRequest#createResponse()
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3MQSendMessageResponse();
    }
    
}
@
