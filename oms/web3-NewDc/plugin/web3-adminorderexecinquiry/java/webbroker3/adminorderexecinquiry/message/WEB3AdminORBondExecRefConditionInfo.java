head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.02.44.19;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AdminORBondExecRefConditionInfo.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ���Ǘ��Ғ������Ɖ���������(WEB3AdminORBondExecRefConditionInfo)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/08/17 �����q(���u) �V�K�쐬  
Revesion History : 2007/07/10 ������(���u) �d�l�ύX���f��No.100
*/

package webbroker3.adminorderexecinquiry.message;

import java.util.Date;

import com.fitechlabs.xtrade.kernel.message.Message;


/**
 * (���Ǘ��Ғ������Ɖ���������)<BR>
 * �������Ɖ������Ƃ��̏���<BR>
 * 
 * @@author �����q(���u)
 * @@version 1.0
 */
public class WEB3AdminORBondExecRefConditionInfo extends Message
{
    
    /**
     * (����ID)<BR>
     * ����ID
     */
    public String id;
    
    /**
     * (���X�R�[�h)<BR>
     * ���X�R�[�h <BR>
     * <BR>
     * �����X�R�[�h�����͎��́APR�w�ŕێ����Ă���<BR>  
     * �戵�\���X�R�[�h�ꗗ���Z�b�g�����B<BR>
     */
    public String[] branchCode;
    
    /**
     * (�����R�[�h(WEB3))<BR>
     * �����R�[�h(WEB3)
     */
    public String productCode;
    
    /**
     * (�ڋq�R�[�h)<BR>
     * �ڋq�R�[�h
     */
    public String accountCode;
    
    /**
     * (������)<BR>
     * ������
     */
    public Date orderBizDate;
    
    /**
     * (����)<BR>
     * ����
     */
    public Date executionUpdateDate;
    
    /**
     * (�������)<BR>
     * �������<BR>
     * <BR>
     * 401�F���t�@@402�F���p�@@404�F����
     */
    public String tradingType;
    
    /**
     * (�������敪)<BR>
     * �������敪<BR>
     * <BR>
     * 0�F�����@@1�F���ρ@@2�F�����
     */
    public String executionState;
    
    /**
     * (���ϋ敪)<BR>
     * ���ϋ敪<BR>
     * <BR>
     * 1�F�~�݁@@2�F�O��
     */
    public String settleDiv;
    
    /**
     * (�ʉ݃R�[�h)<BR>
     * �ʉ݃R�[�h
     */
    public String currencyCode;
    
    /**
     * (�I�y���[�^�R�[�h)<BR>
     * �I�y���[�^�R�[�h
     */
    public String operatorCode;
    
    /**
     * (���҃R�[�h�iSONAR�j)<BR>.
     * ���҃R�[�h�iSONAR�j
     */
    public String sonarTraderCode;
    
    /**
     * (�Ǘ��҃R�[�h)<BR>
     * �Ǘ��҃R�[�h
     */
    public String administratorCode;
    
    /**
     * (���^�C�v)<BR>
     * ���^�C�v<BR>
     * 4�F�O�����@@11�F�l�������� 12�F�Ѝ�
     */
    public String bondType;

    /**
     * @@roseuid 44E335A700AB
     */
    public WEB3AdminORBondExecRefConditionInfo() 
    {
     
    }
}
@
