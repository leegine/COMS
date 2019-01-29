head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.05.37.39;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6f44d7efa104045;
filename	WEB3IPODemandCommonResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : IPO�\�����ʃ��X�|���X(WEB3IPODemandCommonResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/10 ���� (���u) �V�K�쐬
Revesion History : 2010/09/23 �Ԑi (���u) ���f��No.181
*/

package webbroker3.ipo.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * IPO�\�����ʃ��X�|���X�N���X
 *                                                                
 * @@author ����
 * @@version 1.0
 */
public class WEB3IPODemandCommonResponse extends WEB3GenResponse 
{
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "IPO_demandCommon";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200408102004L;
    
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
     * �������敪<BR>
     * <BR>
     * �P�F�����i�i�~�j<BR>
     * �Q�F�f�B�X�J�E���g���i���j
     */
    public String temporaryConditionDiv;
    
    /**
     * �����������l
     */
    public String temporaryConditionLower;
    
    /**
     * ����������l
     */
    public String temporaryConditionUpper;
    
    /**
     * ����
     */
    public String tickValue;
    
    /**
     * �\���p�P�ʋ敪<BR>
     * <BR>
     * �P�F �����i���j<BR>
     * �Q�F �����i���j
     */
    public String displayUnitDiv;
    
    /**
     * ���s�\<BR>
     * <BR>
     * �O�F ���s�\�i�w�l�^���s�j<BR>
     * �P�F ���s�s�i�w�l�̂݁j
     */
    public String marketOrderFlag;
    
    /**
     * @@roseuid 4112E79E00B4
     */
    public WEB3IPODemandCommonResponse() 
    {
     
    }

    /**
     * �R���X�g���N�^�B�����ŗ^����ꂽ���N�G�X�g�I�u�W�F�N�g�����<BR>
     * ���X�|���X�I�u�W�F�N�g�𐶐�����B<BR>
     *<BR>
     * @@param l_request ���N�G�X�g�I�u�W�F�N�g
     */
    protected WEB3IPODemandCommonResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
}
@
