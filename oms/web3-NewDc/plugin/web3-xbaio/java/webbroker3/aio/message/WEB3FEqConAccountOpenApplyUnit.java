head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.03.30;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3FEqConAccountOpenApplyUnit.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �O�������J�ݐ\������(WEB3FEqConAccountOpenApplyUnit)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/03/18 ���E(���u) �V�K�쐬
                   2006/02/08 ����(���u) �d�l�ύX�E���f��481
*/

package webbroker3.aio.message;

import com.fitechlabs.xtrade.kernel.message.Message;
import java.util.Date;

/**
 * (�O�������J�ݐ\������)<BR>
 * �O�������J�ݐ\�����׃N���X
 * 
 * @@author ���E(���u)
 * @@version 1.0
 */
public class WEB3FEqConAccountOpenApplyUnit extends Message 
{
    
    /**
     * (���ʃR�[�h)<BR>
     * ���ʃR�[�h
     */
    public String requestNumber;
    
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
     * (�ڋq��)<BR>
     * �ڋq��
     */
    public String accountName;
    
    /**
     * (�\������)<BR>
     * YYYYMMDDHHMMSS
     */
    public Date applyDate;
    
    /**
     * (�J�ݓ���)<BR>
     * YYYYMMDDHHMMSS
     */
    public Date openDate;
    
    /**
     * (�X�e�[�^�X�敪)<BR>
     * 0�F�����J�ݒ�<BR>
     * 1�F�����J�݊���<BR>
     * 2�F�����J�݃G���[<BR>
     * 9�F�폜
     */
    public String statusDiv;
    
    /**
     * (����M�敪)<BR>
     * 0�F���M����<BR>
     * 1�F���M��
     */
    public String sendRcvDiv;
    
    /**
     * (���O�i���j)<BR>
     * �O����������p�̖��O�i���j
     */
    public String familyName;
    
    /**
     * (���O�i���j)<BR>
     * �O����������p�̖��O�i���j
     */
    public String name;
    
    /**
     * (�O�������ԍ�)<BR>
     * �O����������p�̌����ԍ�<BR>
     * <BR>
     * �����J�ݏ󋵋敪��0(�����J�ݒ�)�܂���2(�����J�݃G���[�j�̏ꍇ��null
     */
    public String fstkAccountCode;
    
    /**
     * (���[���A�h���X)<BR>
     * ���[���A�h���X
     */
    public String mailAddress;
    
    /**
     * (���l)<BR>
     * 10000000�F�����J�ݎ�t�� <BR>
     * 20000000�F�����J�ݒ� <BR>
     * 90000000�F����� <BR>
     * 99999999�F�V�X�e���G���[ <BR>
     * 00000000�F�����J�݊��� <BR>
     * 90000009:��������
     */
    public String biko;
    
    /**
     * (������ꗗ)<BR>
     * �O�������J�ݎ�����̈ꗗ
     */
    public WEB3FEqConAccountOpenQuestionInfo[] questionInfoList;
    
    /**
     * (�X�V�\�t���O)<BR>
     * �X�V�\�t���O<BR>
     * <BR>
     * �X�V�\�F true<BR>
     * �X�V�s�F false
     */
    public boolean updateFlag;
    
    /**
     * (�O�������J�ݐ\������)<BR>
     * �R���X�g���N�^
     * @@roseuid 41CFACF201DC
     */
    public WEB3FEqConAccountOpenApplyUnit() 
    {
     
    }
}
@
