head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.07.00;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AioSettleReportUnit.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ���ϘA�g���|�[�g���׃N���X(WEB3AioSettleReportUnit)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/12 ��O�� (���u) �V�K�쐬
                   2004/10/27 ���E(���u) ���r���[
                   2006/04/14 ������ �d�l�ύX�E���f�� 531
*/

package webbroker3.aio.message;

import java.util.Date;
import com.fitechlabs.xtrade.kernel.message.Message;

/**
 * (���ϘA�g���|�[�g����)<BR>
 * ���ϘA�g���|�[�g���׃N���X
 * 
 * @@author ��O��(���u)
 * @@version 1.0 
 */
public class WEB3AioSettleReportUnit extends Message
{    
    /**
     * (�ڋq�R�[�h)<BR>
     * �ڋq�̌ڋq�R�[�h
     */
    public String accountCode;
    
    /**
     * (�ڋq��)<BR>
     * �ڋq�̖��́i�����j
     */
    public String accountName;
    
    /**
     * (.com�f�r�b�g���ώ���ԍ�)<BR>
     * ���ώ���ԍ�
     */
    public String comDebitNumber;
    
    /**
     * (�����X�����ԍ�)<BR>
     * �����X�̒����ԍ�
     * �i��ЃR�[�h�{���X�R�[�h�{���ʃR�[�h�j
     */
    public String shopOrderId;
    
    /**
     * (��t����)<BR>
     * �����̎�t����
     */
    public Date receptionDate;
    
    /**
     * (�߂����)<BR>
     * �����̖߂����
     */
    public Date returnDate;
    
    /**
     * (��n��)<BR>
     * �����̎�n��
     */
    public Date deliveryDate;
    
    /**
     * (�U���\���)<BR>
     * �����̐U���\���
     */
    public Date transScheduledDate;
    
    /**
     * (���z)<BR>
     * �����z
     */
    public String amount;
    
    /**
     * (�������)<BR>
     * <BR>
     * �O�F������<BR>
     * �P�F���ϊ���<BR>
     * �Q�F���ϒ��~<BR>
     * �R�F�G���[<BR>
     */
    public String transactionStatus;
    
    /**
     * (���b�Z�[�W�R�[�h)<BR>
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
    public String messageCode;
    
    /**
     * (���͋敪)<BR>
     * ���͋敪<BR>
     * null�F�@@PC<BR>
     * 1�F�@@�R�[���Z���^�[ <BR>
     * 2�F�@@i-mode<BR>
     * 3�F�@@�X�����O�V���b�g<BR>
     * 4�F�@@vodafone<BR>
     * 5�F�@@au <BR>
     * 9�F�@@HOST<BR>
     */
    public String inputDiv;
    
    /**
     * (���ϘA�g���|�[�g����)<BR>
     * �R���X�g���N�^
     * @@return webbroker3.aio.message.WEB3AioSettleReportUnit
     * @@roseuid 40E533FD014A
     */
    public WEB3AioSettleReportUnit()
    {
     
    }
}
@
