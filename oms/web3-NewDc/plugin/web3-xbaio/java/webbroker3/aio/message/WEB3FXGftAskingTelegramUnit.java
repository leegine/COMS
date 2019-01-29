head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.16.19;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3FXGftAskingTelegramUnit.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : GFT�˗��d������(WEB3FXGftAskingTelegramUnit)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/1/20 ���z (���u) �V�K�쐬   
                 : 2006/04/26 ������ (���u) �d�l�ύX�E���f��533
                 : 2008/05/27 �O�� (SCS) 
Revesion History : 2008/09/22 ���g (���u) �d�l�ύX�E���f��1013,1040
Revesion History : 2009/03/20 �Ԑi (���u) �d�l�ύX�E���f��1129
Revesion History : 2009/05/31 �đo�g (���u) �d�l�ύX�E���f��1164
Revesion History : 2009/06/25 �����F (���u) �d�l�ύX�E���f��1167
Revesion History : 2009/10/27 �����F (���u) �d�l�ύX�E���f��1243
*/

package webbroker3.aio.message;

import com.fitechlabs.xtrade.kernel.message.Message;

/**
 * (GFT�˗��d������) <BR>
 * GFT�˗��d���̖��� <BR>
 * 
 * @@author ���z(���u)
 * @@version 1.0
 */
public class WEB3FXGftAskingTelegramUnit extends Message
{
    
    /**
     * (��n��) <BR>
     * ��n���iYYYYYMMDD�j<BR>
     */
    public String deliveryDate;
    
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
     * �����敪��04(�o��)�܂���02(����)�̏ꍇ�A�ݒ�<BR>
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
     * (���ʃR�[�h) <BR>
     * ���ʃR�[�h <BR>
     * �iWEB3�ɂēƎ��ɐݒ肵�Ă��鍀�ځj <BR>
     */
    public String requestNumber;

    /**
     * (���O�i���j) <BR>
     * ���O(��) <BR>
     * <BR>
     * �����敪��01(�����J��)�̏ꍇ�A�ݒ� <BR>
     */
    public String fxLastName;

    /**
     * (���O�i���j) <BR>
     * ���O(��) <BR>
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
     * (�Z���P)<BR>
     * �Z���P<BR>
     */
    public String address1;

    /**
     * (�Z���Q)<BR>
     * �Z���Q<BR>
     */
    public String address2;

    /**
     * (�Z���R)<BR>
     * �Z���R<BR>
     */
    public String address3;

    /**
     * (FX�Ïؔԍ��Q)<BR>
     * FX�Ïؔԍ��Q<BR>
     */
    public String fxPassword2;

    /**
     * (GFT�˗��d������) <BR>
     * �R���X�g���N�^�B <BR>
     * 
     * @@return webbroker3.aio.message.WEB3FXGftAskingTelegramUnit
     * @@roseuid 41B0391D0398
     */
    public WEB3FXGftAskingTelegramUnit()
    {
    }
}@
