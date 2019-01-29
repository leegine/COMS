head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.05.42.20;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6f44d7efa104045;
filename	WEB3IPOOfferCommonResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : IPO�w���\�����ʃ��X�|���X(WEB3IPOOfferCommonResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/10 ���� (���u) �V�K�쐬
Revesion History : 2010/09/23 �Ԑi (���u) ���f��No.181
*/

package webbroker3.ipo.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * IPO�w���\�����ʃ��X�|���X�N���X
 *                                                                
 * @@author ����
 * @@version 1.0
 */
public class WEB3IPOOfferCommonResponse extends WEB3GenResponse 
{
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "IPO_offerCommon";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200408102002L;
    
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
     * 12�F�@@���ؓ񕔁@@<BR>
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
     */
    public String publicOfferingMarketCode;
    
    /**
     * �w���\���P��
     */
    public String offerUnit;
    
    /**
     * ���I����
     */
    public String prizeQuantity;
    
    /**
     * �\���p�P�ʋ敪<BR>
     * <BR>
     * �P�F �����i���j<BR>
     * �Q�F �����i���j
     */
    public String displayUnitDiv;
    
    /**
     * @@roseuid 4112E684014E
     */
    public WEB3IPOOfferCommonResponse() 
    {
     
    }
    
    /**
     * �R���X�g���N�^�B�����ŗ^����ꂽ���N�G�X�g�I�u�W�F�N�g�����<BR>
     * ���X�|���X�I�u�W�F�N�g�𐶐�����B<BR>
     *<BR>
     * @@param l_request ���N�G�X�g�I�u�W�F�N�g
     */
    protected WEB3IPOOfferCommonResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
}
@
