head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.05.41.13;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6f44d7efa104045;
filename	WEB3IPODemandOfferProductUnit.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : IPO�\���w���\���������׃��b�Z�[�W(WEB3IPODemandOfferProductUnit.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/10 ���� (���u) �V�K�쐬
Revesion History : 2005/01/05 ���(SRA) �c�Ή�>>>048
Revesion History : 2005/01/06 ���(SRA) �c�Č��Ή�>>>055
*/

package webbroker3.ipo.message;

import java.util.Date;

import com.fitechlabs.xtrade.kernel.message.Message;

/**
 * IPO�\���w���\���������׃��b�Z�[�W�N���X
 *                                                                
 * @@author ����
 * @@version 1.0
 */
public class WEB3IPODemandOfferProductUnit extends Message
{
    
    /**
     * IPO�\���h�c
     */
    public String id;
    
    /**
     * (����ID)<BR>
     * IPO����ID
     */
    public String ipoProductId;
    
    /**
     * �����R�[�h
     */
    public String productCode;
    
    /**
     * ������
     */
    public String productName;
    
    /**
     * �\������
     */
    public String demandQuantity;
    
    /**
     * �\�����i�敪<BR>
     * �@@0�F���s<BR>
     * �@@1�F�w�l
     */
    public String demandPriceDiv;
    
    /**
     * �\�����i
     */
    public String demandPrice;
    
    /**
     * ���J���i
     */
    public String publicOfferingPrice;
    
    /**
     * ���J���i�f�B�X�J�E���g��
     */
    public String publicOfferingDiscountRate;
    
    /**
     * ���J���i�����
     */
    public WEB3IPOTermUnit publicOfferingPriceDetermDate;
    
    /**
     * ���I����
     */
    public String prizeQuantity;
    
    /**
     * �w���\������
     */
    public String offerQuantity;
    
    /**
     * �\�����ޓ���
     */
    public Date offerCancelDate;
    
    /**
     * �\�������z
     */
    public String demandEquivalentPrice;
    
    /**
     * �w���\�����
     */
    public String offerPrice;
    
    /**
     * �u�b�N�r���f�B���O�J�n����
     */
    public Date bookBuildingStartDate;
    
    /**
     * �u�b�N�r���f�B���O�I������
     */
    public Date bookBuildingEndDate;
    
    /**
     * ���茈��敪<BR>
     * <BR>
     * �O�F �X�P�W���[������<BR>
     * �P�F �X�P�W���[������<BR>
     */
    public String undecideDecideDiv;
    
    /**
     * �w���\�����ԁi���Ўw��j
     */
    public WEB3IPOTermUnit appointOfferTerm;
    
    /**
     * IPO�X�P�W���[��<BR>
     * <BR>
     * 1�F�@@�u�b�N�r���f�B���O�J�n�O<BR>
     * 2�F�@@�u�b�N�r���f�B���O���Ԓ�<BR>
     * 3�F�@@�u�b�N�r���f�B���O�I��<BR>
     * 4�F�@@���J���i����<BR>
     * 5�F�@@���I�I��<BR>
     * 6�F�@@�w���\�����Ԓ�<BR>
     * 7�F�@@�w���\���I��<BR>
     * 8�F�@@���J
     */
    public String ipoScheduleDiv;
    
    /**
     * �\���\���󋵋敪<BR>
     * <BR>
     * �@@01�F�@@�u�b�N�r���f�B���O�\����<BR>
     * �@@02�F�@@�u�b�N�r���f�B���O�L�����Z��<BR>
     * �@@03�F�@@���I<BR>
     * �@@04�F�@@���I�L�����Z��<BR>
     * �@@05�F�@@�\����<BR>
     * �@@06�F�@@����<BR>
     * �@@07�F�@@�⌇<BR>
     * �@@08�F�@@�⌇�L�����Z��<BR>
     * �@@09�F�@@�⌇�\����<BR>
     * �@@10�F�@@�⌇����<BR>
     * �@@11�F�@@�⌇���I<BR>
     * �@@12�F�@@���I<BR>
     */
    public String demandOfferStateDiv;
    
    /**
     * �����敪<BR>
     * <BR>
     * �@@0�F�@@���<BR>
     * �@@1�F�@@����
     */
    public String taxType;
    
    /**
     * ����\�R�[�h�ꗗ<BR>
     * <BR>
     * �@@1�F�@@�\��<BR>
     * �@@2�F�@@�����E���<BR>
     * �@@3�F�@@�w���\��<BR>
     * �@@4�F�@@����
     */
    public String[] controlPossibleCodeList;
    
    /**
     * �\���p�P�ʋ敪<BR>
     * <BR>
     * �P�F �����i���j<BR>
     * �Q�F �����i���j
     */
    public String displayUnitDiv;
    
    /**
     * �w���\�����ʕύX�\�t���O<BR>
     * <BR>
     * true:�w���\�����ʓ��͉\(�\��)<BR>
     * false�F�w���\�����ʂ𓖑I���ʂɌŒ�(��\��)
     */
    public boolean offerQuantityFlag;
    
    /**
     * �������敪<BR>
     * <BR>
     * 1:�~<BR>
     * 2:��
     */
    public String temporaryConditionDiv;
    
    /**
     * IPO��~<BR>
     * <BR>
     * 0�F�戵��<BR>
     * 1�F�戵��~��<BR>�@@
     * 2�F���~
     */
    public String ipoStopDiv;
    
    /**
     * �f�t�H���g�R���X�g���N�^
     * @@roseuid 40DC03550073
     */
    public WEB3IPODemandOfferProductUnit() 
    {
     
    }
}
@
