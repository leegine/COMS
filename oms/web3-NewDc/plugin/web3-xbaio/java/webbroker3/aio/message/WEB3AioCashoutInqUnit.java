head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.10.37;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AioCashoutInqUnit.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/** 
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �o���\���⍇������(WEB3AioCashoutInqUnit)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/10 ���� (���u) �V�K�쐬
                   2004/10/27 ���E(���u) ���r���[
                   2004/12/10 ���E (���u) �c�Ή�
                   2006/06/14 ��O�� (���u) ���f��No.593
                   2006/07/31 ����� (���u) ����̍X ���f��604
                   2006/09/04 �Ԑi (���u) ����̍X ���f��No.629
*/

package webbroker3.aio.message;

import java.util.Date;
import com.fitechlabs.xtrade.kernel.message.Message;

/**
 * (�o���\���⍇������)<BR>
 * �o���\���⍇�����׃N���X<BR>
 * 
 * @@author ����(���u)
 * @@version 1.0 
 */
public class WEB3AioCashoutInqUnit extends Message
{    
    /**
     * (����ID)
     */
    public String orderId;
    
    /**
     * (���X�R�[�h)
     */
    public String branchCode;
    
    /**
     * (�ڋq�R�[�h)
     */
    public String accountCode;
    
    /**
     * (�ڋq��)�i�����j
     */
    public String accountName;
    
    /**
     * (�����o�H�敪�j<BR>
     * <BR>
     * 1�F �R�[���Z���^�[<BR>
     * 2�F PC<BR>
     * 3�F �X�����O�V���b�g<BR>
     * 4�F i-mode<BR>
     * 5�F Vodafone<BR>
     * 6�F AU<BR>
     * 9�F HOST<BR>
     * A�F �Ǘ���<BR>
     * B�F �ۏ؋������U�փo�b�`<BR>
     * <BR>
     */
    public String orderRootDiv;
    
    /**
     * (���҃R�[�h�j
     */
    public String traderCode;
    
    /**
     * (��������)
     */
    public Date orderDate;
    
    /**
     * (��n��)
     */
    public Date deliveryDate;
    
    /**
     * (�o���z)
     */
    public String cashoutAmt;
    
    //============ remain zhou-yong NO.1  begin ==============
    
    /**
     * (������t�敪)<BR>
     * <BR>
     * 0�F ��t����<BR>
     * 1�F ��t��<BR>
     * 2�F ��t�G���[<BR>
     * 4�F ��t�� <BR>
     * <BR>
     */
    public String orderDiv;
    
    //============ remain zhou-yong NO.1  end ==============    
    
    /**
     * (����敪)<BR>
     * <BR>
     * 0�F �����l<BR>
     * 1�F �����<BR>
     * 2�F �����<BR>
     * 3�F ������s<BR>
     * 4�F �G���[<BR>
     * 5�F ����s��<BR>
     */
    public String cancelDiv;
    
    /**
     * (�������)
     */
    public Date cancelDate;
    
    /**
     * (�o���]��)
     */
    public String paymentPower;
    
    /**
     * (���Z�@@�փR�[�h)
     */
    public String financialInstitutionCode;
    
    /**
     * (�o���`�F�b�N�A����`�F�b�N�̌���)<BR>
     * <BR>
     * 0�F OK<BR>
     * 1�F NG<BR>
     */
    public String checkResult;
    
    /**
     * (�U����x�X�R�[�h)<BR>
     * �U����x�X�R�[�h<BR>
     */
    public String transferBranchCode;
    
    /**
     * (�a���敪)<BR>
     * �a���敪<BR>
     * <BR>
     * 1:���ʗa��<BR>
     * 2:�����a��<BR>
     * 3:���̑�<BR>
     * 4:���~����<BR>
     */
    public String transferAccountDiv;
    
    /**
     * (�U��������ԍ�)<BR>
     * �U��������ԍ�<BR>
     */
    public String transferAccountNumber;
    
    /**
     * (�U����������`�l)<BR>
     * �U����������`�l<BR>
     */
    public String transferAccountName;

    /**
     * (�ʉ݃R�[�h)<BR>
     * �ʉ݃R�[�h
     */
    public String currencyCode;    
    
    /**
     * (�o���\���⍇������)<BR>
     * �R���X�g���N�^<BR>
     * @@return webbroker3.aio.message.WEB3AioCashoutInqUnit
     * @@roseuid 40E52E17006F
     */
    public WEB3AioCashoutInqUnit() 
    {
     
    }
}
@
