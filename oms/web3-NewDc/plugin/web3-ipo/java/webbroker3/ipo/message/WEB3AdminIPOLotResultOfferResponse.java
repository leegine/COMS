head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.05.37.23;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6f44d7efa104045;
filename	WEB3AdminIPOLotResultOfferResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ���IPO���I���ʍw���\����ڽ��ݽ(WEB3AdminIPOLotResultOfferResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/12 �ė� (���u) �V�K�쐬
Revesion History : 2005/12/20 杊��] (���u) �d�l�ύXNo.105�C��
*/

package webbroker3.ipo.message;

import java.util.Date;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * �Ǘ���IPO���I���ʍw���\����ڽ��ݽ�N���X
 *                                                               
 * @@author �ė�
 * @@version 1.0
 */
public class WEB3AdminIPOLotResultOfferResponse extends WEB3GenResponse 
{
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_IPO_lotResultOffer";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200408121118L;
    
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
     * 23�F�@@�⌇���I<BR>
     * <BR>
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
     * 2�F�@@����<BR>
     */
    public String offerStateDiv;
    
    /**
     * ��t��ԋ敪<BR>
     * <BR>
     * 0�F�@@DEFAULT�i----�j<BR>
     * 1�F�@@SONAR���M�ρi��t���j<BR>
     * 2�F�@@SONAR���f�ρi��t�ρj<BR>
     */
    public String receiveStateDiv;
    
    /**
     * �D�揇��
     */
    public String priority;
    
    /**
     * �w���\�����ʕύX�\�t���O<BR>
     * <BR>
     * �@@true�F�@@�w���\�����ʓ��͉\�i�\���j<BR>
     * �@@false�F�@@�w���\�����ʂ𓖑I���ʂɌŒ�i��\���j<BR>
     */
    public boolean offerQuantityFlag;
    
    /**
     * �\���p�P�ʋ敪<BR>
     * <BR>
     * �P�F �����i���j<BR>
     * �Q�F �����i���j<BR>
     */
    public String displayUnitDiv;
    
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
     * @@roseuid 4112DAD40297
     */
    public WEB3AdminIPOLotResultOfferResponse() 
    {
     
    }
    
    /**
     * �R���X�g���N�^�B<BR>
     * �w�肳�ꂽ���N�G�X�g�I�u�W�F�N�g�ɑ΂��郌�X�|���X�I�u�W�F�N�g�𐶐�����B<BR>
     * @@param l_request - ���N�G�X�g�I�u�W�F�N�g
     * @@roseuid 40E124A00197
     */
    public WEB3AdminIPOLotResultOfferResponse(WEB3GenRequest l_request) 
    {
        super(l_request);
    }
}
@
