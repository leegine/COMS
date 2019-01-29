head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.05.35.09;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6f44d7efa104045;
filename	WEB3IPOLotResultUnit.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ���I���ʖ��׃��b�Z�[�W�f�[�^(WEB3IPOLotResultUnit.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/10 ���� (���u) �V�K�쐬
Revesion History : 2005/12/20 �A���� (���u) �d�l�ύXNo.105�C��
*/

package webbroker3.ipo.message;

import java.util.Date;

import com.fitechlabs.xtrade.kernel.message.Message;

/**
 * ���I���ʖ��׃��b�Z�[�W�f�[�^�N���X
 *                                                                
 * @@author ����
 * @@version 1.0
 */
public class WEB3IPOLotResultUnit extends Message
{
    
    /**
     * ���X�R�[�h
     */
    public String branchCode;
    
    /**
     * �ڋq�R�[�h
     */
    public String accountCode;
    
    /**
     * �ڋq��
     */
    public String accountName;
    
    /**
     * ���I���ʋ敪<BR>
     * <BR>
     * 1�F�@@���I<BR>
     * 2�F�@@�⌇<BR>
     * 3�F�@@���I<BR>
     * 21�F�@@�⌇���I<BR>
     * 23�F�@@�⌇���I
     */
    public String lotResultDiv;
    
    /**
     * ���I����
     */
    public String prizeQuantity;
    
    /**
     * �w���\������
     */
    public String offerQuantity;
    
    /**
     * �w���\�����ޓ���
     */
    public Date offerCancelDate;
    
    /**
     * �w���\���󋵋敪<BR>
     * <BR>
     * 1�F�@@�w���\��<BR>
     * 2�F�@@����
     */
    public String offerStateDiv;
    
    /**
     * ��t��ԋ敪<BR>
     * <BR>
     * 0�F�@@DEFAULT�i----�j<BR>
     * 1�F�@@SONAR���M�ρi��t���j<BR>
     * 2�F�@@SONAR���f�ρi��t�ρj
     */
    public String receiveStateDiv;
    
    /**
     * �D�揇��
     */
    public String priority;
    
    /**
     * ���҃R�[�h
     */
    public String traderCode;

    /**
     * ���J���i
     */
    public String publicOfferingPrice;
    
    /**
     * �M�p�敪
     */
    public String marginDiv;
    
    /**
     * ���I�ԍ�
     */
    public String lotNumber;
    
    /**
     * �f�t�H���g�R���X�g���N�^
     * @@roseuid 40EE26B50009
     */
    public WEB3IPOLotResultUnit() 
    {
     
    }
}
@
