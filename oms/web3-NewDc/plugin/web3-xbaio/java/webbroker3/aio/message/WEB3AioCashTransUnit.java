head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.12.08;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AioCashTransUnit.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ���o�����׃N���X(WEB3AioCashTransUnit)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/11 ���z (���u) �V�K�쐬
Revesion History : 2004/10/22 ���� (���u) ���r���[
Revesion History : 2005/11/17 ���r (���u) �t�B�f���e�B�Ή�                           
Revesion History : 2007/05/09 �����q (���u) �d�l�ύX No.723
Revesion History : 2008/09/22 ���g (���u) �d�l�ύX�E���f��1043
*/

package webbroker3.aio.message;

import java.util.Date;

import com.fitechlabs.xtrade.kernel.message.Message;

/**
 * (���o������)<BR>
 * ���o�����׃N���X
 * 
 * @@author ���z(���u)
 * @@version 1.0
 */
public class WEB3AioCashTransUnit extends Message
{
    
    /**
     * (��t���t)<BR>
     * �e�����̎�t���� <BR>
     */
    public Date receptionDate;
    
    /**
     * (����̎��)<BR>
     * 0�F �o��<BR>
     * 1�F ���o��<BR>
     * 2�F ��񗿏o��<BR>
     * 3�F ����<BR>
     * 4�F �����A��<BR>
     * 5�F �敨OP�؋����֏o�� <BR>
     * 6�F �敨OP�؋���������� <BR>
     * 7�F �M�p�ۏ؋��֏o�� <BR>
     * 8�F �M�p�ۏ؋�������� <BR>
     * 9�F FX�ۏ؋��֏o�� <BR>
     * 10�F FX�ۏ؋�������� <BR>
     * 11�F �������������֏o�� <BR>
     * 12�F ������������������� <BR>
     * 13�F ���̑��o�� <BR>
     * 14�F ���̑�����<BR>
     * 15�F �S�ۃ��[���ԍ�<BR>
     * 16�F CFD�����֏o��<BR>
     * 17�F CFD�����������<BR>
     * <BR>
     */
    public String tradingType;
    
    /**
     * (���Z�@@�֖�)<BR>
     * �i�I�����C�������̂݁j<BR>
     * ���ϋ@@�ւ̖���
     */
    public String paySchemeName;
    
    /**
     * (���o���̋��z)
     */
    public String cashTransAmt;
    
    /**
     * (��n�\���)<BR>
     * �،������ւ̓��o���� <BR>
     */
    public Date deliveryScheduledDate;
    
    /**
     * �i�I�����C�������̂݁j<BR>
     * .com�f�r�b�g���ώ���ԍ� <BR>
     */
    public String comDebitNumber;
    
    /**
     * �i���l�j<BR>
     * �ȉ��̂悤�ȓ��e <BR>
     * <BR>
     * �P�j�ב֕ۏ؋��U�ցi�����"9"��������"10"�j�̏ꍇ <BR>
     * <BR>
     * �Ώۂ̌����ԍ��敪 <BR>
     * <BR>
     * 1�F1���ʉ݃R�[�X <BR>
     * 2�F10���ʉ݃R�[�X <BR>
     * <BR>
     * �Q�j��񗿏o���E���o���i�����"1"��������"2"�j�̏ꍇ <BR>
     * <BR>
     * �Ώۏ��i�� <BR>
     * <BR>
     * 01�F ���o�e���R��21-624 <BR>
     * 02�F �����ʐM�j���[�X�E���e���`���[�g�E���A��������� <BR>
     * 06�F QUICK IS-Web ���I���b�N�X�،��Ł� <BR>
     * 07�F QUICK ���T�[�`�l�b�g <BR>
     * 09�F ��Ўl�G�� <BR>
     * 10�F Market Viewer �ǂ��ł��g���[�_�[ <BR>
     * 12�F �d�v������� <BR>
     * 13�F �ȒP�e�N�j�J������ <BR>
     * 14�F Market Viewer�ǂ��ł��g���[�_�[Pro. <BR>
     * 33�F �n�C�p�[�{�b�N�X �I�[�N�V���� <BR>
     * 34�F �n�C�p�[�g���[�hPro <BR>
     * 35�F ���A���^�C���g���[�_�[ <BR>
     * 36�F �C���C�g���[�_�[ <BR>
     * 38�F ���A���^�C�������iQUICK�@@Is-Web�j <BR>
     * 40�F �n�C�p�[�g���[�_�[�u�X���[�L�[�E���t�̕����v <BR>
     * <BR>
     * �R�j���̑��̏o���E���̑��̓����i�����"13"��������"14"�j�̏ꍇ <BR>
     * <BR>
     * 02�F�i���j�ϑ��ۏ؋� <BR>
     * 03�F�[������ <BR>
     * 06�F���`������ <BR>
     * 07�F�i�ہj�����Ǘ��� <BR>
     * 08�F�i�O���j�����Ǘ��� <BR>
     * 09�F�i���j�����Ǘ��� <BR>
     * 10�F�i���敨�j�ϑ��ۏ؋� <BR>
     * 11�F�i�����敨�j�ϑ��ۏ؋� <BR>
     * 14�F�i�I�v�V�����j�ϑ��ۏ؋� <BR>
     * 19�F�i�����I�v�V�����j�ϑ��ۏ؋� <BR>
     * 24�F��s�U���萔�� <BR>
     * 42�F�X�[�p�[G�i�p���j���j <BR>
     * 43�F�X�[�p�[G�i�p���j�Ηj <BR>
     * 44�F�X�[�p�[G�i�p���j���j <BR>
     * 45�F�X�[�p�[G�i�p���j�ؗj <BR>
     * 46�F�X�[�p�[G�i�p���j���j <BR>
     * 47�F�����~ 1���� <BR>
     * 48�F�����~ 3���� <BR>
     * 49�F�����~ 6���� <BR>
     * 50�F�����~ 1�N <BR>
     * 52�F�������t�@@���h <BR>
     * 53�F�i�ݓ��j�����Ǘ��� <BR>
     * 54�F�������t�@@���h�L���b�V���O <BR>
     * 55�FMMF�L���b�V���O <BR>
     * 71�F�i�敨�I�v�V���� ���؁j <BR>
     * 73�F�i�敨�I�v�V���� ���؁j <BR>
     * 74�F�i�敨�I�v�V���� ���v���o�j <BR>
     * 93�F���̑��a��� <BR>
     * 99�F���̑� <BR>
     * <BR>
     * �S�j�o���@@"0" �̏ꍇ<BR>
     * <BR>
     * mf�F���M���<BR> 
     * <BR>
     * �T�jCFD�U�ցi�����"16"��������"17"�j�̏ꍇ<BR>
     * <BR>
     * �@@�@@�Ώۂ̌����ԍ��敪<BR>
     * <BR>
     * �@@3�FCFD�R�[�X<BR>
     */
    public String ioRemark;
    
    
    /**
     * (������)<BR>
     * �i�I�����C�������̂݁j<BR>
     * �I�����C�����ς̏�����<BR>
     * <BR>
     * A�F ���ϒ�<BR>
     * B�F �f�[�^�ʐM�G���[�i���Z�@@�֌��ϊJ�n��j<BR>
     * C�F �f�[�^�ʐM�G���[�i���Z�@@�֌��ϊJ�n�O�j<BR>
     * D�F ���ώ�t<BR>
     * E�F ���ϊ���<BR>
     * F�F ���σG���[<BR>
     * G�F ���Z�@@�֌��ϒ��~<BR>
     * H�F ���Z�@@�֌��ό��ʃG���[<BR>
     * I�F ����ς�<BR>
     * J�F ���ώ��s�i�V�X�e���G���[�j<BR>
     * K�F ���ώ�t�i���������t�]�͂ɂ͉��Z���ꂸ�j<BR>
     * L�F ���ϊ����i���������t�]�͂ɂ͉��Z���ꂸ�j<BR>
     * M�F ���σG���[<BR>
     * N�F �Z�b�V�����A�E�g�G���[�i���Z�@@�֌��ϊ����j<BR>
     * O�F �Z�b�V�����A�E�g�G���[�i���Z�@@�֌��ώ��s�j<BR>
     * P�F ���ϒ�<BR>
     * V�F �����<BR>
     * W�F ����s��<BR>
     * X�F ������s<BR>
     * �h �h�F NULL<BR>
     * <BR>
     */
    public String payStatus;

    /**
     * �i�ʉ݃R�[�h�j<BR>
     * �ʉ݃R�[�h <BR>
     */
    public String currencyCode;

    /**
     * (�R���X�g���N�^)
     * @@return webbroker3.aio.message.WEB3AioCashTransUnit
     * @@roseuid 40E27A560140
     */
    public WEB3AioCashTransUnit() 
    {
     
    }
}
@
