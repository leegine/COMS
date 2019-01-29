head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.05.40.31;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6f44d7efa104045;
filename	WEB3IPOOfferConditionUnit.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : IPO�w���\���T�����׃��b�Z�[�W�f�[�^(WEB3IPOOfferConditionUnit.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/10 ���� (���u) �V�K�쐬
*/

package webbroker3.ipo.message;

import com.fitechlabs.xtrade.kernel.message.Message;

/**
 * IPO�w���\���T�����׃��b�Z�[�W�f�[�^�N���X
 *                                                                
 * @@author ����
 * @@version 1.0
 */
public class WEB3IPOOfferConditionUnit extends Message
{
    
    /**
     * �w���\������
     */
    public String offerQuantity;
    
    /**
     * �w���\������
     */
    public String offerNumber;
    
    /**
     * �w�����ސ���
     */
    public String declineQuantity;
    
    /**
     * �w�����ތ���
     */
    public String declineNumber;
    
    /**
     * ���萔��
     */
    public String undecideQuantity;
    
    /**
     * ���茏��
     */
    public String undecideNumber;
    
    /**
     * ���v����
     */
    public String totalQuantity;
    
    /**
     * ���v����
     */
    public String totalNumber;
    
    /**
     * �w���m�萔��
     */
    public String decisionQuantity;
    
    /**
     * �w���m�茏��
     */
    public String decisionNumber;
    
    /**
     * ���ޗ��I����
     */
    public String declineRejectedQuantity;
    
    /**
     * ���ޗ��I����
     */
    public String declineRejectedNumber;
    
    /**
     * �f�t�H���g�R���X�g���N�^
     * @@roseuid 40EBAAFA0212
     */
    public WEB3IPOOfferConditionUnit() 
    {
     
    }
}
@
