head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.05.42.16;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6f44d7efa104045;
filename	WEB3AdminIPOPublicOfferingProductUnit.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ���IPO���J�������׃��b�Z�[�W�f�[�^(WEB3AdminIPOPublicOfferingProductUnit.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/10 ���� (���u) �V�K�쐬
Revesion History : 2005/01/06 ���(SRA) �c�Č��Ή�>>>055
Revesion History : 2010/09/23 �Ԑi (���u) ���f�� No.181
*/

package webbroker3.ipo.message;

import java.util.Date;

import com.fitechlabs.xtrade.kernel.message.Message;


/**
 * �Ǘ���IPO���J�������׃��b�Z�[�W�f�[�^�N���X
 *                                                                
 * @@author ����
 * @@version 1.0
 */
public class WEB3AdminIPOPublicOfferingProductUnit extends Message
{
    
    /**
     * IPO�����h�c
     */
    public String id;
    
    /**
     * �����R�[�h
     */
    public String productCode;
    
    /**
     * ������
     */
    public String productName;
    
    /**
     * ���J�s��R�[�h<BR>
     * <BR>
     * 10�F�@@���؁@@<BR>
     * 11�F�@@���؈ꕔ�@@<BR>
     * 12�F�@@���ؓ� <BR>�@@
     * 13�F�@@�}�U�[�Y�@@<BR>
     * 20�F�@@��؁@@<BR>
     * 21�F�@@��؈ꕔ�@@<BR>
     * 22�F�@@��ؓ񕔁@@<BR>
     * 30�F�@@���؁@@<BR>
     * 31�F�@@���؈ꕔ�@@<BR>
     * 32�F�@@���ؓ񕔁@@<BR>
     * 33�F�@@�Z���g���b�N�X<BR>
     * 40�F�@@���؁@@<BR>
     * 41�F�@@Q-Board<BR>
     * 50�F�@@�D�؁@@<BR>
     * 51�F�@@�A���r�V���X<BR>
     * 90�F�@@JASDAQ�i�X�^���_�[�h�j
     * 91�F�@@JASDAQ�i�O���[�X�j
     * <BR>
     */
    public String publicOfferingMarketCode;
    
    /**
     * �u�b�N�r���f�B���O�J�n����
     */
    public Date bookBuildingStartDate;
    
    /**
     * �u�b�N�r���f�B���O�I������
     */
    public Date bookBuildingEndDate;
    
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
    public Date publicOfferingPriceDetermDate;
    
    /**
     * ���茈��敪<BR>
     * <BR>
     * �O�F �X�P�W���[������<BR>
     * �P�F �X�P�W���[������<BR>
     */
    public String undecideDecideDiv;
    
    /**
     * ���I��
     */
    public WEB3IPOTermUnit lotDate;
    
    /**
     * �w���\�����ԁi���Ўw��j
     */
    public WEB3IPOTermUnit appointOfferTerm;
    
    /**
     * ���J��
     */
    public WEB3IPOTermUnit publicOfferingDate;
    
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
     * 8�F�@@���J<BR>
     * <BR>
     */
    public String ipoScheduleDiv;
    
    /**
     * IPO��~<BR>
     * <BR>
     * 0�FDEFAULT�i�J�Ò��j<BR>
     * 1�F�戵��~��<BR>
     * 2�F�戵���~<BR>
     */
    public String ipoStopDiv;
    
    /**
     * �f�t�H���g�R���X�g���N�^
     * @@roseuid 40C6917E0296
     */
    public WEB3AdminIPOPublicOfferingProductUnit() 
    {
     
    }
}
@
