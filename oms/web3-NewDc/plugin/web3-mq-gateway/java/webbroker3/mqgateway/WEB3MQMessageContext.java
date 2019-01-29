head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.07.47.51;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7944d8853e14978;
filename	WEB3MQMessageContext.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/*
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : WEB3MQMessageContext�N���X(WEB3MQMessageContext.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/02/08 �R�c�@@��i(FLJ) �V�K�쐬
                 : 2005/03/07 �R�c�@@��i(FLJ) �ڋq�R�[�h�i�����j��long����Long�ɕύX
 */
package webbroker3.mqgateway;

import java.util.Date;

/**
 * ���[�U�[�f�[�^���ɒǉ����鋤�ʂ̃R���e�L�X�g���
 *
 * @@author Takuji Yamada (FLJ)
 * @@version 1.0
 */
public class WEB3MQMessageContext
{
    
    /**
     * SONAR��ЃR�[�h
     */
    private final String institutionCode;
    
    /**
     * Oracle SID
     */
    private final String sid;
    
    /**
     * �ڋq�R�[�h�i���j
     */
    private final Long accountIdStart;
    
    /**
     * �ڋq�R�[�h�i���j
     */
    private final Long accountIdEnd;
    
    /**
     * �c�Ɠ�
     */
    private final Date bizDate;
    
    /**
     * �R���X�g���N�^
     * 
     * @@param l_strInstitutionCode ��ЃR�[�h
     * @@param l_strSid OracleSID
     * @@param l_lngAccountIdStart �ڋq�R�[�h�i���j
     * @@param l_lngAccountIdEnd �ڋq�R�[�h�i���j
     * @@param l_datBizDate �c�Ɠ�
     */
    public WEB3MQMessageContext(
            String l_strInstitutionCode,
            String l_strSid,
            Long l_lngAccountIdStart,
            Long l_lngAccountIdEnd,
            Date l_datBizDate)
    {
        this.institutionCode = l_strInstitutionCode;
        this.sid = l_strSid;
        this.accountIdStart = l_lngAccountIdStart;
        this.accountIdEnd = l_lngAccountIdEnd;
        this.bizDate = l_datBizDate;
    }

    /**
     * �R���X�g���N�^
     * 
     * @@param l_strInstitutionCode ��ЃR�[�h
     * @@param l_strSid OracleSID
     * @@param l_lngAccountIdStart �ڋq�R�[�h�i���j
     * @@param l_lngAccountIdEnd �ڋq�R�[�h�i���j
     * @@param l_datBizDate �c�Ɠ�
     * @@deprecated �ڋq�R�[�h�i���j�A�ڋq�R�[�h�i���j��Long�Őݒ肷��R���X�g���N�^���g�p�B
     */
    public WEB3MQMessageContext(
            String l_strInstitutionCode, 
            String l_strSid,
            long l_lngAccountIdStart, 
            long l_lngAccountIdEnd, 
            Date l_datBizDate)
    {
        this(l_strInstitutionCode, 
            l_strSid, 
            new Long(l_lngAccountIdStart),
            new Long(l_lngAccountIdEnd), 
            l_datBizDate);
    }
    
    /**
     * �ڋq�R�[�h�i��)���擾����B �ڋq�R�[�h�i��)��null�̏ꍇ�A0��Ԃ��B
     * 
     * @@return accountIdStart ���擾����B
     */
    public long getAccountIdStart()
    {
        return !getAccountIdStartIsNull() ? accountIdStart.longValue() : 0L;
    }
    
    /**
     * �ڋq�R�[�h�i��)���擾����B
     * �ڋq�R�[�h�i��)��null�̏ꍇ�A0��Ԃ��B
     * 
     * @@return accountIdEnd ���擾����B
     */
    public long getAccountIdEnd()
    {
        return !getAccountIdEndIsNull() ? accountIdEnd.longValue() : 0L;
    }
    
    /**
     * @@return bizDate ���擾����B
     */
    public Date getBizDate()
    {
        return bizDate;
    }
    
    /**
     * @@return institutionCode ���擾����B
     */
    public String getInstitutionCode()
    {
        return institutionCode;
    }
    
    /**
     * @@return sid ���擾����B
     */
    public String getSid()
    {
        return sid;
    }
    
    /**
     * �ڋq�R�[�h�i��)��null�̏ꍇtrue��Ԃ��B
     */
    public boolean getAccountIdStartIsNull()
    {
        return accountIdStart == null;
    }
    
    /**
     * �ڋq�R�[�h�i��)��null�̏ꍇtrue��Ԃ��B
     */
    public boolean getAccountIdEndIsNull()
    {
        return accountIdEnd == null;
    }
    
}
@
