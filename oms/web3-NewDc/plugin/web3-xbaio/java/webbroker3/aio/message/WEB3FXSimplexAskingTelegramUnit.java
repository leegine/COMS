head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.04.58.18;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3FXSimplexAskingTelegramUnit.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : Simplex�˗��d������(WEB3FXSimplexAskingTelegramUnit.java)
Author Name      : Daiwa Institute of Research
Revision History : 2009/09/17 �����F (���u) �V�K�쐬�E���f��1201
*/
package webbroker3.aio.message;

import com.fitechlabs.xtrade.kernel.message.Message;

/**
 * (Simplex�˗��d������) <BR>
 * Simplex�˗��d�����׃N���X<BR>
 * <BR>
 * @@author �����F(���u)
 * @@version 1.0
 */
public class WEB3FXSimplexAskingTelegramUnit extends Message
{
    /**
     * (�����敪)<BR>
     * (simplexOperationDiv) <BR>
     * 01�F�����J�� <BR>
     * 02�F���� <BR>
     * 04�F�o�� <BR>
     * 07�F�U�։\�z<BR>
     */
    public String simplexOperationDiv;

    /**
     * (���FX���O�C��ID)<BR>
     * ���FX���O�C��ID <BR>
     * (oseFxLoginId)<BR>
     */
    public String oseFxLoginId;

    /**
     * (�ڋq���J�i)<BR>
     * �ڋq���J�i <BR>
     * (fullNameKana)<BR>
     */
    public String fullNameKana;

    /**
     * (�ڋq��)<BR>
     * �ڋq��<BR>
     * (fullName)<BR>
     */
    public String fullName;

    /**
     * (����)<BR>
     * ����<BR>
     * (genderType)<BR>
     */
    public String genderType;

    /**
     * (�X�֔ԍ�)<BR>
     * �X�֔ԍ�<BR>
     * (zipCode)<BR>
     */
    public String zipCode;

    /**
     * (�Z���P)<BR>
     * �Z���P<BR>
     * (address1)<BR>
     */
    public String address1;

    /**
     * (�Z���Q)<BR>
     * �Z���Q<BR>
     * (address2)<BR>
     */
    public String address2;

    /**
     * (�Z���R)<BR>
     * �Z���R<BR>
     * (address3)<BR>
     */
    public String address3;

    /**
     * (��ꃁ�[���A�h���X)<BR>
     * ��ꃁ�[���A�h���X<BR>
     * (mail1)<BR>
     */
    public String mail1;

    /**
     * (�������O�C���p�X���[�h)<BR>
     * �������O�C���p�X���[�h<BR>
     * (initialLoginPassword)<BR>
     */
    public String initialLoginPassword;

    /**
     * (��������p�X���[�h)<BR>
     * ��������p�X���[�h<BR>
     * (initialTradePassword)<BR>
     */
    public String initialTradePassword;

    /**
     * (������؃p�X���[�h)<BR>
     * ������؃p�X���[�h<BR>
     * (initialOsePassword)<BR>
     */
    public String initialOsePassword;

    /**
     * (�^�C���X�^���v)<BR>
     * �^�C���X�^���v<BR>
     * (timeStamp)<BR>
     */
    public String timeStamp;

    /**
     * (�n�b�V���l)<BR>
     * �n�b�V���l<BR>
     * (hashValue)<BR>
     */
    public String hashValue;

    /**
     * (�U��ID)<BR>
     * �U��ID<BR>
     * (transferId)<BR>
     */
    public String transferId;

    /**
     * (���FX�����ԍ�)<BR>
     * ���FX�����ԍ�<BR>
     * (oseAccountId)<BR>
     */
    public String oseAccountId;

    /**
     * (�U�֓����z)<BR>
     * �U�֓����z<BR>
     * (depositAmount)<BR>
     */
    public String depositAmount;

    /**
     * (�U�֏o���z)<BR>
     * �U�֏o���z<BR>
     * (withdrawalAmount)<BR>
     */
    public String withdrawalAmount;

    /**
     * �R���X�g���N�^�B<BR>
     */
    public WEB3FXSimplexAskingTelegramUnit()
    {

    }
}
@
