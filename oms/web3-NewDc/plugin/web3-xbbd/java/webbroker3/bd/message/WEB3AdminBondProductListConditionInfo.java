head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.02.48.43;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3AdminBondProductListConditionInfo.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��ҍ������ꗗ��������(WEB3AdminBondProductListConditionInfo.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/08/17 ��іQ (���u) �V�K�쐬
*/

package webbroker3.bd.message;

import java.util.Date;

import com.fitechlabs.xtrade.kernel.message.Message;

/**
 * (�Ǘ��ҍ������ꗗ��������)<BR>
 * �����ꗗ����������Ƃ��̏���
 * 
 * @@author ��іQ
 * @@version 1.0
 */
public class WEB3AdminBondProductListConditionInfo extends Message
{
    
    /**
     * (���^�C�v)<BR>
     * ���^�C�v<BR>
     * <BR>
     * 4:�O�����@@10:������
     */
    public String bondType;
    
    /**
     * (��ʃR�[�h)<BR>
     * ��ʃR�[�h
     */
    public String bondCategCode;
    
    /**
     * (�����R�[�h(WEB3))<BR>
     * �����R�[�h(WEB3)
     */
    public String productCode;
    
    /**
     * (���s��)<BR>
     * ���s��
     */
    public Date issueDate;
    
    /**
     * (���ғ�)<BR>
     * ���ғ�
     */
    public Date maturityDate;
    
    /**
     * (������)<BR>
     * ������
     */
    public String interestPaymentDay;
    
    /**
     * (�ʉ݃R�[�h)<BR>
     * �ʉ݃R�[�h
     */
    public String currencyCode;
    
    /**
     * (�戵�敪)<BR>
     * �戵�敪<BR>
     * <BR>
     * 0�F�s��  1�F�Ǘ��ҁ@@2�F�Ǘ���/�ڋq
     */
    public String tradeHandleDiv;
    
    /**
     * @@roseuid 44E3363B00EA
     */
    public WEB3AdminBondProductListConditionInfo() 
    {
     
    }
}
@
