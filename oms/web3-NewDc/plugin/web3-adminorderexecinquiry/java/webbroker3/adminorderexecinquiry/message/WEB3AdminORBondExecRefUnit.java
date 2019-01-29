head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.02.43.36;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AdminORBondExecRefUnit.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ���Ǘ��Ғ������Ɖ�s(WEB3AdminORBondExecRefUnit)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/08/17 �����q(���u) �V�K�쐬    
Revesion History : 2007/07/9 ������(���u) �d�l�ύX���f��No.100
*/

package webbroker3.adminorderexecinquiry.message;

import java.util.Date;

import com.fitechlabs.xtrade.kernel.message.Message;


/**
 * (���Ǘ��Ғ������Ɖ�s)<BR>
 * ���Ǘ��Ғ������Ɖ�\������Ƃ��̍s�f�[�^��ۑ�
 * @@author �����q(���u)
 * @@version 1.0
 */
public class WEB3AdminORBondExecRefUnit extends Message
{
    
    /**
     * (���b�N�����{�^���敪)<BR>
     * ���b�N�����{�^���敪<BR>
     * <BR>
     * 0�F��\�� 1�F�����{�^�� 2�F���b�N�{�^��
     */
    public String lockDiv;
    
    /**
     * (���ύX�{�^���敪)<BR>
     * ���ύX�{�^���敪<BR>
     * <BR>
     * 0�F��\���@@1�F���{�^���@@2�F�ύX�{�^��
     */
    public String execChgDiv;
    
    /**
     * (����{�^���敪)<BR>
     * ����{�^���敪<BR>
     * <BR>
     * 0�F��\���@@1�F����{�^��
     */
    public String cancelDiv;
    
    /**
     * (����ID)<BR>
     * ����ID
     */
    public String id;
    
    /**
     * (���X�R�[�h)<BR>
     * ���X�R�[�h
     */
    public String branchCode;
    
    /**
     * (�ڋq�R�[�h)<BR>
     * �ڋq�R�[�h
     */
    public String accountCode;
    
    /**
     * (�����R�[�h(WEB3))<BR>
     * �����R�[�h(WEB3)
     */
    public String productCode;
    
    /**
     * (������)<BR>
     * ������
     */
    public String productName;
    
    /**
     * (_�������)<BR>
     * �������<BR>
     * <BR>
     * 401�F���t�@@402�F���p�@@404�F����
     */
    public String tradingType;
    
    /**
     * (���ϋ敪)<BR>
     * ���ϋ敪<BR>
     * <BR>
     * 1�F�~�݁@@2�F�O��
     */
    public String settleDiv;
    
    /**
     * (��������)<BR>
     * ��������
     */
    public String orderQuantity;
    
    /**
     * (��萔��)<BR>
     * ��萔��
     */
    public String execQuantity;
    
    /**
     * (�w�l)<BR>
     * �w�l
     */
    public String limitOrderPrice;
    
    /**
     * (���P��)<BR>
     * ���P��
     */
    public String execPrice;
    
    /**
     * (��������i�~�݁j)<BR>
     * ��������i�~�݁j
     */
    public String yenTradePrice;
    
    /**
     * (�o�ߗ��q�i�~�݁j)<BR>
     * �o�ߗ��q�i�~�݁j
     */
    public String yenAccruedInterest;
    
    /**
     * (��n����i�~�݁j)<BR>
     * ��n����i�~�݁j
     */
    public String yenDeliveryPrice;
    
    /**
     * (�ʉ݃R�[�h)<BR>
     * �ʉ݃R�[�h
     */
    public String currencyCode;
    
    /**
     * (�בփ��[�g)<BR>
     * �בփ��[�g
     */
    public String fxRate;
    
    /**
     * (��������i�O�݁j)<BR>
     * ��������i�O�݁j
     */
    public String foreignTradePrice;
    
    /**
     * (�o�ߗ��q�i�O�݁j)<BR>
     * �o�ߗ��q�i�O�݁j
     */
    public String foreignAccruedInterest;
    
    /**
     * (��n����i�O�݁j)<BR>
     * ��n����i�O�݁j
     */
    public String foreignDeliveryPrice;
    
    /**
     * (�󒍓���)<BR>
     * �󒍓���
     */
    public Date acceptOrderTimeStamp;
    
    /**
     * (������)<BR>
     * ������
     */
    public Date orderBizDate;
    
    /**
     * (����)<BR>
     * ����
     */
    public Date domesticExecutionDate;
    
    /**
     * (���n����)<BR>
     * ���n����
     */
    public Date foreignExecutionDate;
    
    /**
     * (��n��)<BR>
     * ��n��
     */
    public Date domesticDeliveryDate;
    
    /**
     * (���n��n��)<BR>
     * ���n��n��
     */
    public Date foreignDeliveryDate;
    
    /**
     * (�������敪)<BR>
     * �������敪<BR>
     * <BR>
     * 0�F�����i�����ρj�@@1�F���ρ@@2�F�����
     */
    public String executionState;
    
    /**
     * (�����o�H�敪)<BR>
     * �����o�H�敪<BR>
     * <BR>
     * 1�F�R�[���Z���^�[�@@2�F�o�b�@@9�FHOST A�F�Ǘ���
     */
    public String orderRootDiv;
    
    /**
     * (���񒍕��̒����`���l��)<BR>
     * ���񒍕��̒����`���l��<BR>
     * <BR>
     * 0:�c�ƓX�@@1�F�C���^�[�l�b�g�@@2�F�R�[���Z���^�[
     */
    public String orderChannel;
    
    /**
     * (�I�y���[�^�R�[�h)<BR>
     * �I�y���[�^�R�[�h
     */
    public String operatorCode;
    
    /**
     * (���҃R�[�h�iSONAR�j)<BR>
     * ���҃R�[�h�iSONAR�j
     */
    public String sonarTraderCode;
    
    /**
     * (�Љ�X�敪)<BR>
     * �Љ�X�敪
     */
    public String introduceStoreDiv;
    
    /**
     * (�Љ�X�R�[�h)<BR>
     * �Љ�X�R�[�h
     */
    public String introduceStoreCode;
    
    /**
     * (�Ǘ��҃R�[�h)<BR>
     * �Ǘ��҃R�[�h
     */
    public String administratorCode;
    
    /**
     * (���Ǘ��Ғ������Ɖ�s)<BR>
     * �R���X�g���N�^�B
     * @@roseuid 44CFF7FF007D
     */
    public void WEB3AdminBondExecRefUnit() 
    {
     
    }
}
@
