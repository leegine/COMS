head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.19;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3FPTForceLogoutInfoUnit.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.docadmin.message;

import java.util.Date;

import com.fitechlabs.xtrade.kernel.message.Message;

/**
 * �Ǘ��� ���ʖ����� �������O�A�E�g���ʏƉ�Unit
 */
public class WEB3FPTForceLogoutInfoUnit extends Message
{
    
    /**
     * ��������
     * 0:������
     * 1:������
     * 5:������
     * 9:�G���[
     */
    public String transactionResult;
    
    /**
     * �����J�n����
     */
    public Date transactionStartDate;
    
    /**
     * �����I������
     */
    public Date transactionEndDate;
    
    /**
     * ��������
     */
    public String endCount;
    
    /**
     * �X�V�҃R�[�h
     */
    public String updaterCode;
    
    /**
     * ���ʋ敪�Ǘ����ꗗ
     */
    public WEB3FPTDocumentDivAdminInfoUnit[] documentDivList;
    
    /**
     * @@roseuid 47DF467702E6
     */
    public WEB3FPTForceLogoutInfoUnit() 
    {
     
    }
}
@
