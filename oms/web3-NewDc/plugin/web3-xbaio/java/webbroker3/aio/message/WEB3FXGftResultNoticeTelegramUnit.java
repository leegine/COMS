head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.04.56.49;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3FXGftResultNoticeTelegramUnit.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : GFT���ʒʒm�d������(WEB3FXGftResultNoticeTelegramUnit)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/1/20 ���z (���u) �V�K�쐬   
                 : 2006/04/26 ������ (���u) �d�l�ύX�E���f��533
                 : 2006/07/13 ��O�� (���u) �d�l�ύX�E���f��601
Revesion History : 2008/09/22 ���g (���u) �d�l�ύX�E���f��993,1013,1039,1040
Revesion History : 2009/03/19 �Ԑi (���u) �d�l�ύX�E���f��1129,1154
Revesion History : 2009/06/25 �����F (���u) �d�l�ύX�E���f��1167
*/

package webbroker3.aio.message;

import com.fitechlabs.xtrade.kernel.message.Message;

/**
 * (GFT���ʒʒm�d������) <BR>
 * GFT���ʒʒm�d���̖��� <BR>
 * 
 * @@author ���z(���u)
 * @@version 1.0
 */
public class WEB3FXGftResultNoticeTelegramUnit extends Message
{    
    /**
     * (DIR��GFT���M����) <BR>
     * YYYYMMDDHHMMSS <BR>
     */
    public String dirSendTime;

    /**
     * (�����敪) <BR>
     * <BR>
     * 01�F�����J�� <BR>
     * 02�F���� <BR>
     * 03�F�����ǉ�<BR>
     * 04�F�o�� <BR>
     * 07�F�U�։\�z<BR>
     */
    public String gftOperationDiv;

    /**
     * (�ב֕ۏ؋������ԍ�) <BR>
     * �ב֕ۏ؋������ԍ� <BR>
     * <BR>
     * �����敪��04(�o��)�܂���02(����)�̏ꍇ�A�ݒ� <BR>
     */
    public String fxAccountCode;

    /**
     * (���[���A�h���X) <BR>
     * ���[���A�h���X <BR>
     * <BR>
     * �����敪��01(�����J��)�̏ꍇ�A�ݒ� <BR>
     */
    public String fxMailAddress;

    /**
     * (�������O�C��ID) <BR>
     * �������O�C��ID <BR>
     * �iWEB3�ɂēƎ��ɐݒ肵�Ă��鍀�ځj <BR>
     */
    public String fxFirstLoginId;

    /**
     * (�����p�X���[�h) <BR>
     * �����p�X���[�h <BR>
     * <BR>
     * �����敪��01(�����J��)�̏ꍇ�A�ݒ� <BR>
     */
    public String fxFirstPassword;

    /**
     * (�S���敪) <BR>
     * �S���敪 <BR>
     */
    public String groupName;

    /**
     * (���o���z) <BR>
     * ���o���z <BR>
     * <BR>
     * �����敪��04(�o��)�܂���02(����)�̏ꍇ�A�ݒ� <BR>
     */
    public String cashinoutAmt;

    /**
     * (WOLF�Z�b�V�����L�[) <BR>
     * WOLF�Z�b�V�����L�[ <BR>
     * �iWEB3�ɂēƎ��ɐݒ肵�Ă��鍀�ځj <BR>
     */
    public String wolfSession;

    /**
     * (�A�v���P�[�V����ID) <BR>
     * �A�v���P�[�V����ID <BR>
     * �iWEB3�ɂēƎ��ɐݒ肵�Ă��鍀�ځj <BR>
     */
    public String wolfAid;

    /**
     * (�Đ����T�[�r�XID) <BR>
     * �Đ����T�[�r�XID <BR>
     * �iWEB3�ɂēƎ��ɐݒ肵�Ă��鍀�ځj <BR>
     */
    public String regetServiceId;

    /**
     * (SSID) <BR>
     * SSID <BR>
     * �iWEB3�ɂēƎ��ɐݒ肵�Ă��鍀�ځj <BR>
     */
    public String wolfSsid;

    /**
     * (��ЃR�[�h) <BR>
     * ��ЃR�[�h <BR>
     * �iWEB3�ɂēƎ��ɐݒ肵�Ă��鍀�ځj <BR>
     */
    public String institutionCode;

    /**
     * (���X�R�[�h) <BR>
     * ���X�R�[�h <BR>
     * �iWEB3�ɂēƎ��ɐݒ肵�Ă��鍀�ځj <BR>
     */
    public String branchCode;

    /**
     * (�ڋq�R�[�h) <BR>
     * �ڋq�R�[�h <BR>
     * �iWEB3�ɂēƎ��ɐݒ肵�Ă��鍀�ځj <BR>
     */
    public String accountCode;

    /**
     * (���ʃR�[�h�j <BR>
     * ���ʃR�[�h <BR>
     * �iWEB3�ɂēƎ��ɐݒ肵�Ă��鍀�ځj <BR>
     */
    public String requestNumber;

    /**
     * (��t����) <BR>
     * ��t���� <BR>
     */
    public String resultCode;

    /**
     * (GFT��DIR���M����) <BR>
     * YYYYYMMDDHHMMSS <BR>
     */
    public String gftSendTime;

    /**
     * (�ב֕ۏ؋������ԍ��i1���ʉ݁j) <BR>
     * �ב֕ۏ؋������ԍ��i1���ʉ݁j <BR>
     * <BR>
     * �����敪��01(�����J��)�̏ꍇ�A�ݒ� <BR>
     */
    public String gftAcc1;

    /**
     * (�ב֕ۏ؋������ԍ��i10���ʉ݁j) <BR>
     * �ב֕ۏ؋������ԍ��i10���ʉ݁j <BR>
     * <BR>
     * �����敪��01(�����J��)�̏ꍇ�A�ݒ� <BR>
     */
    public String gftAcc2;

    /**
     * (���O�i���j) <BR>
     * ���O�i���j <BR>
     * <BR>
     * �����敪��01(�����J��)�̏ꍇ�A�ݒ� <BR>
     */
    public String fxLastName;

    /**
     * (���O�i���j) <BR>
     * ���O�i���j <BR>
     * <BR>
     * �����敪��01(�����J��)�̏ꍇ�A�ݒ� <BR>
     */
    public String fxFirstName;

    /**
     * (�n�b�V���l) <BR>
     * �n�b�V���l <BR>
     */
    public String hashValue;
    
    /**
     * (��n��) <BR>
     * ��n���iYYYYYMMDD�j<BR>
     */
    public String deliveryDate;
    
    /**
     * (���o���z2) <BR>
     * ���o���z2<BR>
     */
    public String cashinoutAmt2;

    /**
     * (�ב֕ۏ؋��������ꗗ)<BR>
     * �ב֕ۏ؋��������̈ꗗ<BR>
     */
    public WEB3FXAccInformationUnit[] fxAccInformationList;

    /**
     * (GFT���ʒʒm�d������) <BR>
     * �R���X�g���N�^�B <BR>
     * 
     * @@return webbroker3.aio.message.WEB3FXGftResultNoticeTelegramUnit
     * @@roseuid 41B038EB0156
     */
    public WEB3FXGftResultNoticeTelegramUnit()
    {
    }
}@
