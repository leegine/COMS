head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.20.51;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3FXAccOpenApplyUnit.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
 File Name        : FX�����J�ݐ\������(WEB3FXAccOpenApplyUnit)
 Author Name      : Daiwa Institute of Research
 Revesion History : 2005/01/20 ����(���u) �V�K�쐬
                    2006/02/08 ����(���u) �d�l�ύX�E���f��481
                    2006/02/09 �]�V�q(���u) �d�l�ύX�E���f��458
                    2006/02/09 �A����(���u) �d�l�ύX�E���f��475
                    2006/02/22 ���(SRA) �d�l�ύX�E���f��500
 Revesion History : 2008/05/19 �đo�g(���u) �d�l�ύX ���f��No.866
 */

package webbroker3.aio.message;

import java.util.Date;
import com.fitechlabs.xtrade.kernel.message.Message;

/**
 * (FX�����J�ݐ\������) <BR>
 * FX�����J�ݐ\������
 * 
 * @@author ����(���u)
 * @@version 1.0
 */

public class WEB3FXAccOpenApplyUnit extends Message
{
    /**
     * (�I���\�t���O) <BR>
     * �I���\�̏ꍇ�Atrue�B�ȊO�Afalse�B
     */
    public boolean selectableFlag;

    /**
     * (���ʃR�[�h) <BR>
     * ���ʃR�[�h
     */
    public String requestNumber;

    /**
     * (���X�R�[�h) <BR>
     * ���X�R�[�h
     */
    public String branchCode;

    /**
     * (�ڋq�R�[�h) <BR>
     * �ڋq�R�[�h
     */
    public String accountCode;

    /**
     * (�ڋq��) <BR>
     * �ڋq��
     */
    public String accountName;

    /**
     * (�\������) <BR>
     * YYYYMMDDHHMMSS
     */
    public Date applyTime;

    /**
     * (�X�e�[�^�X�敪) <BR>
     * 0�F�����J�ݒ� <BR>
     * 1�F�����J�݊��� <BR>
     * 2�F�����J�݃G���[ <BR>
     * 3�F�_�E�����[�h�� <BR>
     * 9�F�폜
     */
    public String statusDiv;

    /**
     * (����M�敪) <BR>
     * 0�F���M���� <BR>
     * 1�F���M��
     */
    public String sendRcvDiv;

    /**
     * (�iFX�j���O�i���j) <BR>
     * �ב֕ۏ؋�����p�̖��O�i���j
     */
    public String fxLastName;

    /**
     * (�iFX�j���O�i���j) <BR>
     * �ב֕ۏ؋�����p�̖��O�i���j
     */
    public String fxFirstName;

    /**
     * (�iFX�j���O�C��ID) <BR>
     * �ב֕ۏ؋�����p�̃��O�C��ID
     */
    public String fxLoginId;

    /**
     * (�iFX�j���[���A�h���X) <BR>
     * �ב֕ۏ؋�����p�̃��[���A�h���X
     */
    public String fxMailAddress;

    /**
     * (FX�������ꗗ) <BR>
     * FX�������̈ꗗ <BR>
     * <BR>
     * �����J�ݏ󋵋敪��0(�����J�ݒ�)�܂���2(�����J�݃G���[�j�̏ꍇ��null
     */
    public WEB3FXAccInformationUnit[] fxAccInformationList;

    /**
     * (���l) <BR>
     * 10000000�F�����J�ݒ� <BR>
     * 90000000�F�폜 <BR>
     * 99999999�F�V�X�e���G���[ <BR>
     * 00000000�F�����J�݊��� <BR>
     * 00000105�FGFT��t���ԊO�G���[ <BR>
     * 00000199�FGFT�V�X�e���N���G���[ <BR>
     * 00000204�F�c���s���G���[ <BR>
     * 00000299�F���[�U�[�N���G���[ <BR>
     * 00000501�F�Y���ۏ؋����������G���[ <BR>
     * 00000502�F���o�����z�G���[ <BR>
     * 00000601�F�d�������G���[�i�K�{���ږ����́j <BR>
     * 00000602�F�d�������G���[�i�s�������g�p�j <BR>
     * 00000603�F�d�������G���[�i�����s���j <BR>
     * 00000609�F�d�������N���G���[ <BR>
     * 00000701�F�����敪�G���[ <BR>
     * 00000801�F2�d�����G���[ <BR>
     * 00000901�FGFT�V�X�e���G���[ <BR>
     * 00000909�F�n�b�V���l�G���[ <BR>
     * 00000910�F�^�C���A�E�g�G���[ <BR>
     * 90000009:��������
     */
    public String fxRemark;

    /**
     * (MRF�c���t���O) <BR>
     * MRF�c������̏ꍇ�Atrue�B�ȊO�Afalse�B
     */
    public boolean mrfBalanceFlag;

    /**
     * (MRF�����t���O) <BR>
     * MRF��������̏ꍇ�Atrue�B�ȊO�Afalse�B
     */
    public boolean mrfAccountFlag;

    /**
     * (FX������ӎ�����ꗗ) <BR>
     * FX������ӎ�����̈ꗗ
     */
    public WEB3FXTradeAgreementUnit[] fxTradeAgreementList;
    
    /**
     * (������敪)<BR>
     * 0�F�����M<BR>
     * 1�F���M��<BR>
     * 2�F��̍�<BR>
     */
    public String agreementDiv;

    /**
     * (FX�V�X�e���R�[�h)<BR>
     * FX�V�X�e���R�[�h
     */
    public String fxSystemCode;

    /**
     * (FX�����J�ݐ\������) <BR>
     * �R���X�g���N�^�B
     * 
     * @@roseuid 41B042CE02BD
     */
    public WEB3FXAccOpenApplyUnit()
    {
    }
}@
