head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.05.36.20;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6f44d7efa104045;
filename	WEB3IPOProductInfo.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : IPO������񃁃b�Z�[�W�f�[�^(WEB3IPOProductInfo.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/10 ���� (���u) �V�K�쐬
                 : 2006/11/22 �����q (���u) ���f�� No.166
Revesion History : 2008/08/22 ���u�� (���u) ���f�� No.178
Revesion History : 2010/09/23 �Ԑi (���u) ���f�� No.181
*/

package webbroker3.ipo.message;

import java.util.Date;

import com.fitechlabs.xtrade.kernel.message.Message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3DocReadingDivDef;
import webbroker3.common.define.WEB3EnableMarketOrderDef;
import webbroker3.common.define.WEB3IpoRegistDetailDivDef;
import webbroker3.common.define.WEB3IpoRegistDivDef;
import webbroker3.common.define.WEB3IpoUnitDivDef;
import webbroker3.common.define.WEB3ProvisionalValueDivDef;
import webbroker3.ipo.define.WEB3UndecideDecideDivDef;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;


/**
 * IPO������񃁃b�Z�[�W�f�[�^�N���X
 *                                                                
 * @@author ����
 * @@version 1.0
 */
public class WEB3IPOProductInfo extends Message
{
    /**         
     * ���O�o�̓��[�e�B���e�B�B         
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3IPOProductInfo.class);
        
    /**
     * IPO�o�^�敪<BR>
     * <BR>
     * �P�F�V�K���<BR>
     * �Q�F�����<BR>
     */
    public String ipoRegistDiv;
    
    /**
     * IPO�o�^�敪�ڍ�<BR>
     * <BR>
     * �P�F��W<BR>
     * �Q�F���o��<BR>
     * �R�F����<BR>
     * �S�F��W�E���o��<BR>
     * �T�F��W�̎戵��<BR>
     * �U�F���o���̎戵��<BR>
     * �V�F����̎戵��<BR>
     * �W�F��W�E���o���̎戵��<BR>
     */
    public String ipoRegistDetailDiv;
    
    /**
     * �����R�[�h
     */
    public String productCode;
    
    /**
     * ������
     */
    public String productName;
    
    /**
     * ���茈��敪<BR>
     * <BR>
     * �O�F �X�P�W���[������<BR>
     * �P�F �X�P�W���[������
     */
    public String undecideDecideDiv;
    
    /**
     * ���J��
     */
    public WEB3IPOTermUnit publicOfferingDate;
    
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
     * �������񎦓�
     */
    public Date temporaryConditionPresentationDate;
    
    /**
     * ���吔��
     */
    public String issuedQuantity;
    
    /**
     * ���o����
     */
    public String offeringQuantity;
    
    /**
     * ���Ў戵����
     */
    public String handlingQuantity;
    
    /**
     * �w���\���P��
     */
    public String offerUnit;
    
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
     * �劲���،���Ж�
     */
    public String leadManagingUnderwriter;
    
    /**
     * �u�b�N�r���f�B���O�J�n����
     */
    public Date bookBuildingStartDate;
    
    /**
     * �u�b�N�r���f�B���O�I������
     */
    public Date bookBuildingEndDate;
    
    /**
     * ���J���i�����
     */
    public WEB3IPOTermUnit publicOfferingPriceDetermDate;
    
    /**
     * ���J���i
     */
    public String publicOfferingPrice;
    
    /**
     * ���J���i�f�B�X�J�E���g��
     */
    public String publicOfferingDiscountRate;
    
    /**
     * ���I��
     */
    public WEB3IPOTermUnit lotDate;
    
    /**
     * �w���\�����ԁi�ژ_�����j
     */
    public WEB3IPOTermUnit prospectusOfferTerm;
    
    /**
     * �w���\�����ԁi���Ўw��j
     */
    public WEB3IPOTermUnit appointOfferTerm;
    
    /**
     * ���s��Ѓ��S�t�@@�C��URL
     */
    public String issuerLogoFileURL;
    
    /**
     * ���s��ЃE�F�u�T�C�gURL
     */
    public String issuerWebSiteURL;
    
    /**
     * ���s��ЊT�v
     */
    public String issuerCorporateOutline;
    
    /**
     * ���l
     */
    public String note;
    
    /**
     * (�ژ_�����{���敪)<BR>
     * �ژ_�����{���敪 <BR>
     * <BR>
     * 0�F�{���v<BR>
     * 1�F���v�\�����{���s�v<BR>
     */
    public String prospectusReadDiv;

    /**
     * �f�t�H���g�R���X�g���N�^
     * @@roseuid 40C6723801F0
     */
    public WEB3IPOProductInfo() 
    {
     
    }
    
    /**
     * ���N�G�X�g�f�[�^�̐������`�F�b�N���s���B <BR>
     * <BR>
     * �P�j�@@this.IPO�o�^�敪�̃`�F�b�N<BR>
     * �@@�P�|�P�j�@@�����͂̏ꍇ�A��O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00434<BR>
     * �@@�P�|�Q�j�@@�s���ȃR�[�h�l�̏ꍇ�A��O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00436<BR>
     * <BR>
     * �Q�j�@@this.IPO�o�^�敪�ڍׂ̃`�F�b�N<BR>
     * �@@�Q�|�P�j�@@�����͂̏ꍇ�A��O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00435<BR>
     * �@@�Q�|�Q�j�@@�s���ȃR�[�h�l�̏ꍇ�A��O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00437<BR>
     * <BR>
     * �R�j�@@this.�����R�[�h�̃`�F�b�N<BR>
     * �@@�R�|�P�j�@@�����͂̏ꍇ�A��O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00438<BR>
     * �@@�R�|�Q�j�@@�T�C�Y��5byte�ȊO�̏ꍇ�A��O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00439<BR>
     * <BR>
     * �S�j�@@this.�������̃`�F�b�N<BR>
     * �@@�S�|�P�j<BR>
     * �@@�@@���@@this.IPO�o�^�敪 == �h�V�K���h�̏ꍇ<BR>
     * �@@�@@�@@�����͂̏ꍇ�ł���΁A��O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00440<BR>
     * <BR>
     * �@@�� ���͂�����ꍇ�A�ȉ��̃`�F�b�N���s���B<BR>
     * �@@�S�|�Q�j�@@�T�C�Y��50byte���傫���ꍇ�A��O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00441<BR>
     * <BR>
     * �T�j�@@this.���J���̃`�F�b�N<BR>
     * �@@�� this.���茈��敪 == �h����h�̏ꍇ�A�ȉ��̃`�F�b�N���s���B<BR>
     * �@@�T�|�P�j�@@this.���J��.�J�n�����������͂̏ꍇ�A��O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00442<BR>
     * �@@�T�|�Q�j�@@this.���J��.validate()���R�[������B<BR>
     * <BR>
     * �U�j�@@this.�s��R�[�h�̃`�F�b�N<BR>
     * �@@�U�|�P�j�@@�����͂̏ꍇ�A��O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00443<BR>
     * <BR>
     * �V�j�@@this.�������敪�̃`�F�b�N<BR>
     * �@@�V�|�P�j�@@�����͂̏ꍇ�A��O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00444<BR>
     * �@@�V�|�Q�j�@@�s���ȃR�[�h�l�̏ꍇ�A��O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00445<BR>
     * <BR>
     * �W�j�@@this.�����������l�̃`�F�b�N<BR>
     * �@@�� ���͂�����ꍇ�A�ȉ��̃`�F�b�N���s���B<BR>
     * �@@�W�|�P�j�@@���l�łȂ��ꍇ�A��O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00446<BR>
     * �@@�W�|�Q�j�@@�����`�F�b�N<BR>
     * �@@�@@�� this.�������敪 == �h�����i�i�~�j�h�̏ꍇ<BR>
     * �@@�@@�@@�|�T�C�Y��9byte���傫���ꍇ�A��O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00447<BR>
     * �@@�@@�@@�|�����l�łȂ��ꍇ�A��O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00488<BR>
     * �@@�@@�� this.�������敪 == �h�f�B�X�J�E���g���i���j�h�̏ꍇ<BR>
     * �@@�@@�@@�|�T�C�Y��������2���^������2���ȓ��̃����W�łȂ��ꍇ�A��O��<BR>
     * �X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00448<BR>
     * <BR>
     * �X�j�@@this.����������l�̃`�F�b�N<BR>
     * �@@�� ���͂�����ꍇ�A�ȉ��̃`�F�b�N���s���B<BR>
     * �@@�X�|�P�j�@@���l�łȂ��ꍇ�A��O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00449<BR>
     * �@@�X�|�Q�j�@@�����`�F�b�N<BR>
     * �@@�@@�� this.�������敪 == �h�����i�i�~�j�h�̏ꍇ<BR>
     * �@@�@@�@@�|�T�C�Y��9byte���傫���ꍇ�A��O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00450<BR>
     * �@@�@@�@@�|�����l�łȂ��ꍇ�A��O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00489<BR>
     * �@@�@@�� this.�������敪 == �h�f�B�X�J�E���g���i���j�h�̏ꍇ<BR>
     * �@@�@@�@@�|�T�C�Y��������2���^������2���ȓ��͈̔͊O�̏ꍇ�A��O��
     * <BR>
     * �X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00451<BR>
     * <BR>
     * �P�O�j�@@this.�������񎦓��̃`�F�b�N<BR>
     * �@@�P�O�|�P�j�@@�����͂̏ꍇ�A��O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00452<BR>
     * <BR>
     * �P�P�j�@@this.���吔�ʂ̃`�F�b�N<BR>
     * �@@�� ���͂�����ꍇ�A�ȉ��̃`�F�b�N���s���B<BR>
     * �@@�P�P�|�P�j�@@���l�łȂ��ꍇ�A��O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00453<BR>
     * �@@�P�P�|�Q�j�@@�T�C�Y��9byte���傫���ꍇ�A��O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00454<BR>
     * <BR>
     * �P�Q�j�@@this.���o���ʂ̃`�F�b�N<BR>
     * �@@�� ���͂�����ꍇ�A�ȉ��̃`�F�b�N���s���B<BR>
     * �@@�P�Q�|�P�j�@@���l�łȂ��ꍇ�A��O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00455<BR>
     * �@@�P�Q�|�Q�j�@@�T�C�Y��9byte���傫���ꍇ�A��O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00456<BR>
     * <BR>
     * �P�R�j�@@this.���Ў戵���ʂ̃`�F�b�N<BR>
     * �@@�� ���͂�����ꍇ�A�ȉ��̃`�F�b�N���s���B<BR>
     * �@@�P�R�|�P�j�@@���l�łȂ��ꍇ�A��O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00457<BR>
     * �@@�P�R�|�Q�j�@@�T�C�Y��9byte���傫���ꍇ�A��O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00458<BR>
     * <BR>
     * �P�S�j�@@this.�w���\���P�ʂ̃`�F�b�N<BR>
     * �@@�� ���͂�����ꍇ�A�ȉ��̃`�F�b�N���s���B<BR>
     * �@@�P�S�|�P�j�@@���l�łȂ��ꍇ�A��O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00459<BR>
     * �@@�P�S�|�Q�j�@@�T�C�Y��9byte���傫���ꍇ�A��O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00460<BR>
     * �@@�P�S�|�R�j�@@<BR>
     * �@@�@@�� this.���Ў戵���ʁAthis.�w���\���P�ʂ̗����ɓ��͂�����ꍇ<BR>
     * �@@�@@�@@�|�ithis.�w���\���P�� > this.���Ў戵���ʁj�̏ꍇ�A��O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00461<BR>
     * �@@�@@�@@�|�ithis.���Ў戵���� % this.�w���\���P�� != 0�j�̏ꍇ�A��O��
     * <BR>
     * �X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00462<BR>
     * �@@�@@�� this.���o���ʁAthis.�w���\���P�ʂ̗����ɓ��͂�����ꍇ<BR>
     * �@@�@@�@@�|�ithis.���o���� % this.�w���\���P�� != 0�j�̏ꍇ�A��O��
     * <BR>
     * �X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00463<BR>
     * �@@�@@�� this.���吔�ʁAthis.�w���\���P�ʂ̗����ɓ��͂�����ꍇ<BR>
     * �@@�@@�@@�|�ithis.���吔�� % this.�w���\���P�� != 0�j�̏ꍇ�A��O��
     * <BR>
     * �X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00464<BR>
     * <BR>
     * �P�T�j�@@this.���݂̃`�F�b�N<BR>
     * �@@�� ���͂�����ꍇ�A�ȉ��̃`�F�b�N���s���B<BR>
     * �@@�P�T�|�P�j�@@���l�łȂ��ꍇ�A��O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00465<BR>
     * �@@�P�T�|�Q�j�@@�����`�F�b�N�B<BR>
     * �@@�@@�� this.�������敪 == �h�����i�i�~�j�h�̏ꍇ<BR>
     * �@@�@@�@@�|�T�C�Y��6byte���傫���ꍇ�A��O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00466<BR>
     * �@@�@@�@@�|�����l�łȂ��ꍇ�A��O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00467<BR>
     * �@@�@@�� this.�������敪 == �h�f�B�X�J�E���g���i���j�h�̏ꍇ<BR>
     * �@@�@@�@@�|�T�C�Y��������2���^������2���ȓ��͈̔͊O�̏ꍇ�A��O��
     * <BR>
     * �X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00468<BR>
     * �@@�P�T�|�S�j�@@�[���`�F�b�N�B
     * �@@�@@�@@�|�[�������͂��ꂽ��A��O���X���[����B
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00465<BR>
     * <BR>
     * �P�U�j�@@this.�\���p�P�ʋ敪�̃`�F�b�N<BR>
     * �@@�P�U�|�P�j�@@�����͂̏ꍇ�A��O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00469<BR>
     * �@@�P�U�|�Q�j�@@�s���ȃR�[�h�l�̏ꍇ�A��O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00470<BR>
     * <BR>
     * �P�V�j�@@this.�劲���،���Ж��̃`�F�b�N<BR>
     * �@@�P�V�|�P�j�@@�����͂̏ꍇ�A��O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00471<BR>
     * �@@�P�V�|�Q�j�@@�T�C�Y��80byte���傫���ꍇ�A��O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00472<BR>
     * <BR>
     * �P�W�j�@@this.�u�b�N�r���f�B���O�J�n�����̃`�F�b�N<BR>
     * �@@�P�W�|�P�j�@@�����͂̏ꍇ�A��O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00473<BR>
     * <BR>
     * �P�X�j�@@this.�u�b�N�r���f�B���O�I�������̃`�F�b�N<BR>
     * �@@�P�X�|�P�j�@@�����͂̏ꍇ�A��O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00474<BR>
     * <BR>
     * �Q�O�j�@@this.���J���i������̃`�F�b�N<BR>
     * �@@�� this.���茈��敪 == �h����h�̏ꍇ�A�ȉ��̃`�F�b�N���s���B<BR>
     * �@@�Q�O�|�P�j�@@this.���J���i�����.�J�n�����������͂̏ꍇ�A��O��
     * <BR>
     * �X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00475<BR>
     * �@@�Q�O�|�Q�j�@@this.���J���i�����.validate()���R�[������B<BR>
     * <BR>
     * �Q�P�j�@@this.���J���i�̃`�F�b�N<BR>
     * �@@�� ���͂�����ꍇ�A�ȉ��̃`�F�b�N���s���B<BR>
     * �@@�Q�P�|�P�j�@@���l�łȂ��ꍇ�A��O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00476<BR>
     * �@@�Q�P�|�Q�j�@@�T�C�Y��9byte���傫���ꍇ�A��O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00477<BR>
     * <BR>
     * �Q�Q�j�@@this.���J���i�f�B�X�J�E���g���̃`�F�b�N<BR>
     * �@@�� ���͂�����ꍇ�A�ȉ��̃`�F�b�N���s���B<BR>
     * �@@�Q�Q�|�P�j�@@�T�C�Y��������2���^������2���ȓ��͈̔͊O�̏ꍇ�A��O��
     * <BR>
     * �X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00478<BR>
     * <BR>
     * �Q�R�j�@@this.���I���̃`�F�b�N<BR>
     * �@@�� this.���茈��敪 == �h����h�̏ꍇ�A�ȉ��̃`�F�b�N���s���B<BR>
     * �@@�Q�R�|�P�j�@@this.���I��.�J�n�����������͂̏ꍇ�A��O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00479<BR>
     * �@@�Q�R�|�Q�j�@@this.���I��.validate()���R�[������B<BR>
     * <BR>
     * �Q�S�j�@@this.�w���\�����ԁi�ژ_�����j�̃`�F�b�N<BR>
     * �@@�� this.���茈��敪 == �h����h�̏ꍇ�A�ȉ��̃`�F�b�N���s���B<BR>
     * �@@�Q�S�|�P�j�@@this.�w���\�����ԁi�ژ_�����j.�J�n�����������͂̏ꍇ�A
     * <BR>
     * ��O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00480<BR>
     * �@@�Q�S�|�Q�j�@@this.�w���\�����ԁi�ژ_�����j.�I�������������͂̏ꍇ�A<BR>
     * ��O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00481<BR>
     * �@@�Q�S�|�R�j�@@this.�w���\�����ԁi�ژ_�����j.validate()���R�[������B<BR>
     * <BR>
     * �Q�T�j�@@this.�w���\�����ԁi���Ўw��j�̃`�F�b�N<BR>
     * �@@�� this.���茈��敪 == �h����h�̏ꍇ�A�ȉ��̃`�F�b�N���s���B<BR>
     * �@@�Q�T�|�P�j�@@this.�w���\�����ԁi���Ўw��j.�J�n�����������͂̏ꍇ�A<BR>
     * ��O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00482<BR>
     * 
     * �@@�Q�T�|�Q�j�@@this.�w���\�����ԁi���Ўw��j.�I�������������͂̏ꍇ�A<BR>
     * ��O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00483<BR>
     * 
     * �@@�Q�T�|�R�j�@@this.�w���\�����ԁi���Ўw��j.validate()���R�[������B<BR>
     * <BR>
     * �Q�U�j�@@this.���s��Ѓ��S�t�@@�C��URL�̃`�F�b�N<BR>
     * �@@�� ���͂�����ꍇ�A�ȉ��̃`�F�b�N���s���B<BR>
     * �@@�Q�U�|�P�j�@@�T�C�Y��80byte���傫���ꍇ�A��O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00484<BR>
     * 
     * <BR>
     * �Q�V�j�@@this.���s��ЃE�F�u�T�C�gURL�̃`�F�b�N<BR>
     * �@@�� ���͂�����ꍇ�A�ȉ��̃`�F�b�N���s���B<BR>
     * �@@�Q�V�|�P�j�@@�T�C�Y��80byte���傫���ꍇ�A��O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00485<BR>
     * 
     * <BR>
     * �Q�W�j�@@this.���s��ЊT�v�̃`�F�b�N<BR>
     * �@@�� ���͂�����ꍇ�A�ȉ��̃`�F�b�N���s���B<BR>
     * �@@�Q�W�|�P�j�@@�T�C�Y��400byte���傫���ꍇ�A��O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00486<BR>
     * 
     * <BR>
     * �Q�X�j�@@this.���l�̃`�F�b�N<BR>
     * �@@�� ���͂�����ꍇ�A�ȉ��̃`�F�b�N���s���B<BR>
     * �@@�Q�X�|�P�j�@@�T�C�Y��400byte���傫���ꍇ�A��O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00487<BR>
     * <BR>
     * �R�O�j�@@this.���s�\�̃`�F�b�N <BR>
     * �R�O�|�P�j�@@this.���s�\�������͂̏ꍇ�A��O���X���[����B<BR> 
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00591<BR>
�@@   * �R�O�|�Q�j�@@�s���ȃR�[�h�l�̏ꍇ�A��O���X���[����B <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00592<BR>
     *<BR>
     * �R�P�j�@@this.���茈��敪�̃`�F�b�N <BR>
     * �R�P�|�P�j�@@this.���茈��敪�������͂̏ꍇ�A��O���X���[����B <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00593<BR>
     * �R�P�|�Q�j�@@�s���ȃR�[�h�l�̏ꍇ�A��O���X���[����B <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00594<BR>
     * <BR>
     * �R�Q�j�@@this.�ژ_�����{���敪�̃`�F�b�N<BR>
     * �@@�R�Q�|�P�j�@@this.�ژ_�����{���敪 != null �̏ꍇ�A�ȉ����������s���B<BR>
     * <BR>
     * �@@�@@�R�Q�|�P�|�P�j�@@�s���ȃR�[�h�l�̏ꍇ�A��O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_02694<BR>
     * <BR>
     * @@roseuid 40C4103B00BB
     */
    public void validate() throws WEB3BaseException
    {

        final String STR_METHOD_NAME = " validate()";
        
        log.entering(STR_METHOD_NAME);
        
        //IPO�o�^�敪�̃`�F�b�N
        if(this.ipoRegistDiv == null)
        {
            throw new WEB3BusinessLayerException(
                            WEB3ErrorCatalog.BUSINESS_ERROR_00434,
                            this.getClass().getName() + STR_METHOD_NAME);
        }
        else if(!WEB3IpoRegistDivDef.OPEN_LISTING.equals(this.ipoRegistDiv) && !WEB3IpoRegistDivDef.LISTED.equals(this.ipoRegistDiv))
        {
            throw new WEB3BusinessLayerException(
                                        WEB3ErrorCatalog.BUSINESS_ERROR_00436,
                                        this.getClass().getName() + STR_METHOD_NAME);
        }
        
        //this.IPO�o�^�敪�ڍׂ̃`�F�b�N
        if(this.ipoRegistDetailDiv == null)
        {
            throw new WEB3BusinessLayerException(
                            WEB3ErrorCatalog.BUSINESS_ERROR_00435,
                            this.getClass().getName() + STR_METHOD_NAME);
        }
        else if(!WEB3IpoRegistDetailDivDef.RECRUIT.equals(this.ipoRegistDetailDiv) 
            && !WEB3IpoRegistDetailDivDef.SALES.equals(this.ipoRegistDetailDiv) 
            && !WEB3IpoRegistDetailDivDef.PRIVATE_RECRUIT.equals(this.ipoRegistDetailDiv)
            && !WEB3IpoRegistDetailDivDef.RECRUIT_AND_SALES.equals(this.ipoRegistDetailDiv)
            && !WEB3IpoRegistDetailDivDef.RECRUIT_HANDING.equals(this.ipoRegistDetailDiv)
            && !WEB3IpoRegistDetailDivDef.SALES_HANDING.equals(this.ipoRegistDetailDiv)
            && !WEB3IpoRegistDetailDivDef.PRIVATE_RECRUIT_HANDING.equals(this.ipoRegistDetailDiv)
            && !WEB3IpoRegistDetailDivDef.RECRUIT_AND_SALES_HANDING.equals(this.ipoRegistDetailDiv))
        {
            throw new WEB3BusinessLayerException(
                                        WEB3ErrorCatalog.BUSINESS_ERROR_00437,
                                        this.getClass().getName() + STR_METHOD_NAME);
        }        
        
        //�R�j�@@this.�����R�[�h�̃`�F�b�N
        
        if(this.productCode == null)
        {
            throw new WEB3BusinessLayerException(
                            WEB3ErrorCatalog.BUSINESS_ERROR_00079,
                            this.getClass().getName() + STR_METHOD_NAME);
        }
        
        int l_intLength = WEB3StringTypeUtility.getByteLength(this.productCode);
        
        if(l_intLength != 5)
        {
            throw new WEB3BusinessLayerException(
                                        WEB3ErrorCatalog.BUSINESS_ERROR_00439,
                                        this.getClass().getName() + STR_METHOD_NAME);
        }  
              
        // �S�j�@@this.�������̃`�F�b�N
        int l_intNameLength = WEB3StringTypeUtility.getByteLength(this.productName);
        if(WEB3IpoRegistDivDef.OPEN_LISTING.equals(this.ipoRegistDiv))
        {
            if(this.productName == null || "".equals(this.productName))
            {
                throw new WEB3BusinessLayerException(
                            WEB3ErrorCatalog.BUSINESS_ERROR_00440,
                            this.getClass().getName() + STR_METHOD_NAME);
            }            
        }
        
        if(this.productName != null || !"".equals(this.productName)) //this.���������͂�����ꍇ
        {
            if(l_intNameLength > 50)
            {
                //�S�|�Q�j�@@�T�C�Y��50byte���傫���ꍇ
                throw new WEB3BusinessLayerException(
                            WEB3ErrorCatalog.BUSINESS_ERROR_00441,
                            this.getClass().getName() + STR_METHOD_NAME);           
            }
        }
        //�T�j�@@this.���J���̃`�F�b�N
        
        if(WEB3UndecideDecideDivDef.SCHEDULE_DECIDED.equals(this.undecideDecideDiv))
        {
            if(this.publicOfferingDate.startDate == null)
            {
                throw new WEB3BusinessLayerException(
                                                WEB3ErrorCatalog.BUSINESS_ERROR_00442,
                                                this.getClass().getName() + STR_METHOD_NAME);
            }
            
            this.publicOfferingDate.validate();
        }
        
        //     * �U�j�@@this.�s��R�[�h�̃`�F�b�N<BR>

        if(this.publicOfferingMarketCode == null)
        {
            throw new WEB3BusinessLayerException(
                            WEB3ErrorCatalog.BUSINESS_ERROR_00443,
                            this.getClass().getName() + STR_METHOD_NAME);
        }
        
        
        // �V�j�@@this.�������敪�̃`�F�b�N<BR>

        if(this.temporaryConditionDiv == null)
        {
            throw new WEB3BusinessLayerException(
                            WEB3ErrorCatalog.BUSINESS_ERROR_00444,
                            this.getClass().getName() + STR_METHOD_NAME);
        }
        if(!WEB3ProvisionalValueDivDef.TRUE_VALUE.equals(this.temporaryConditionDiv) && !WEB3ProvisionalValueDivDef.DISCOUNT_RATIO.equals(this.temporaryConditionDiv))
        {
            throw new WEB3BusinessLayerException(
                            WEB3ErrorCatalog.BUSINESS_ERROR_00445,
                            this.getClass().getName() + STR_METHOD_NAME);
        }
        
        //     * �W�j�@@this.�����������l�̃`�F�b�N<BR>
        if( !(this.temporaryConditionLower == null) )
        {        
            int l_intLowerLength = WEB3StringTypeUtility.getByteLength(this.temporaryConditionLower);
            if( !(WEB3StringTypeUtility.isNumber(this.temporaryConditionLower)) )
            {
                throw new WEB3BusinessLayerException(
                                WEB3ErrorCatalog.BUSINESS_ERROR_00446,
                                this.getClass().getName() + STR_METHOD_NAME);                
            }
            if(WEB3ProvisionalValueDivDef.TRUE_VALUE.equals(this.temporaryConditionDiv))
            {
                if(l_intLowerLength > 9)
                {
                    throw new WEB3BusinessLayerException(
                                    WEB3ErrorCatalog.BUSINESS_ERROR_00447,
                                    this.getClass().getName() + STR_METHOD_NAME);                    
                }
                else if( !(WEB3StringTypeUtility.isInteger(this.temporaryConditionLower)) )
                {
                    throw new WEB3BusinessLayerException(
                                    WEB3ErrorCatalog.BUSINESS_ERROR_00488,
                                    this.getClass().getName() + STR_METHOD_NAME);                          
                }
            }
            else if(WEB3ProvisionalValueDivDef.DISCOUNT_RATIO.equals(this.temporaryConditionDiv))
            {
                if( (WEB3StringTypeUtility.getIntegerDigits(this.temporaryConditionLower) > 2) 
                    || WEB3StringTypeUtility.getFractionDigits(this.temporaryConditionLower) > 2)
                {
                    throw new WEB3BusinessLayerException(
                                    WEB3ErrorCatalog.BUSINESS_ERROR_00448,
                                    this.getClass().getName() + STR_METHOD_NAME);                     
                }
            }
			double l_temporaryConditionLowerD = Double.parseDouble(this.temporaryConditionLower);
			if (l_temporaryConditionLowerD == 0)
			{
				throw new WEB3BusinessLayerException(
								WEB3ErrorCatalog.BUSINESS_ERROR_00446,
								this.getClass().getName() + STR_METHOD_NAME);   
			}
        }
        //�X�j�@@this.����������l�̃`�F�b�N<BR>
        if( !(this.temporaryConditionUpper == null) )
        {
            int l_intUpperLength = WEB3StringTypeUtility.getByteLength(this.temporaryConditionUpper);
            
            if( !(WEB3StringTypeUtility.isNumber(this.temporaryConditionUpper)) )
            {
                throw new WEB3BusinessLayerException(
                                WEB3ErrorCatalog.BUSINESS_ERROR_00449,
                                this.getClass().getName() + STR_METHOD_NAME);                
            }
            if(WEB3ProvisionalValueDivDef.TRUE_VALUE.equals(this.temporaryConditionDiv))
            {
                if(l_intUpperLength > 9)
                {
                    throw new WEB3BusinessLayerException(
                                    WEB3ErrorCatalog.BUSINESS_ERROR_00450,
                                    this.getClass().getName() + STR_METHOD_NAME);                    
                }
                else if( !(WEB3StringTypeUtility.isInteger(this.temporaryConditionUpper)) )
                {
                    throw new WEB3BusinessLayerException(
                                    WEB3ErrorCatalog.BUSINESS_ERROR_00489,
                                    this.getClass().getName() + STR_METHOD_NAME);                          
                }
            }
            else if(WEB3ProvisionalValueDivDef.DISCOUNT_RATIO.equals(this.temporaryConditionDiv))
            {
                if( (WEB3StringTypeUtility.getIntegerDigits(this.temporaryConditionUpper) > 2) 
                    || WEB3StringTypeUtility.getFractionDigits(this.temporaryConditionUpper) > 2)
                {
                    throw new WEB3BusinessLayerException(
                                    WEB3ErrorCatalog.BUSINESS_ERROR_00451,
                                    this.getClass().getName() + STR_METHOD_NAME);                     
                }
            }
			double l_temporaryConditionUpperD = Double.parseDouble(this.temporaryConditionUpper);
			if (l_temporaryConditionUpperD == 0)
			{
				throw new WEB3BusinessLayerException(
								WEB3ErrorCatalog.BUSINESS_ERROR_00449,
								this.getClass().getName() + STR_METHOD_NAME);   
			}
        }

        //�P�O�j�@@this.�������񎦓��̃`�F�b�N<BR>
        
        if(this.temporaryConditionPresentationDate == null)
        {
            throw new WEB3BusinessLayerException(
                                            WEB3ErrorCatalog.BUSINESS_ERROR_00452,
                                            this.getClass().getName() + STR_METHOD_NAME);
        }
        
        
        //�P�P�j�@@this.���吔�ʂ̃`�F�b�N<BR>
        if( !(this.issuedQuantity == null) )
        {
            int l_intQuantityLength = WEB3StringTypeUtility.getByteLength(this.issuedQuantity);
            
            if( !(WEB3StringTypeUtility.isNumber(this.issuedQuantity)) )
            {
                throw new WEB3BusinessLayerException(
                                                WEB3ErrorCatalog.BUSINESS_ERROR_00453,
                                                this.getClass().getName() + STR_METHOD_NAME);             
            }
            else if(l_intQuantityLength > 9)
            {
                throw new WEB3BusinessLayerException(
                                                WEB3ErrorCatalog.BUSINESS_ERROR_00454,
                                                this.getClass().getName() + STR_METHOD_NAME);                
            }
        }
        //�P�Q�j�@@this.���o���ʂ̃`�F�b�N<BR>
        if( !(this.offeringQuantity == null) )
        {
            int l_intOfferingLength = WEB3StringTypeUtility.getByteLength(this.offeringQuantity);
            
            if( !(WEB3StringTypeUtility.isNumber(this.offeringQuantity)) )
            {
                throw new WEB3BusinessLayerException(
                                                WEB3ErrorCatalog.BUSINESS_ERROR_00455,
                                                this.getClass().getName() + STR_METHOD_NAME);             
            }
            else if(l_intOfferingLength > 9)
            {
                throw new WEB3BusinessLayerException(
                                                WEB3ErrorCatalog.BUSINESS_ERROR_00456,
                                                this.getClass().getName() + STR_METHOD_NAME);                
            }
        }        
        //�P�R�j�@@this.���Ў戵���ʂ̃`�F�b�N<BR>
        if( !(this.handlingQuantity == null) )
        {
            int l_intHandlingLength = WEB3StringTypeUtility.getByteLength(this.handlingQuantity);
            
            if( !(WEB3StringTypeUtility.isNumber(this.handlingQuantity)) )
            {
                throw new WEB3BusinessLayerException(
                                                WEB3ErrorCatalog.BUSINESS_ERROR_00457,
                                                this.getClass().getName() + STR_METHOD_NAME);             
            }
            else if(l_intHandlingLength > 9)
            {
                throw new WEB3BusinessLayerException(
                                                WEB3ErrorCatalog.BUSINESS_ERROR_00458,
                                                this.getClass().getName() + STR_METHOD_NAME);                
            }
        }          
        
        //�P�S�j�@@this.�w���\���P�ʂ̃`�F�b�N<BR>
        
        if(this.offerUnit != null)
        {
            int l_intOfferLength = WEB3StringTypeUtility.getByteLength(this.offerUnit);
            if( !(WEB3StringTypeUtility.isNumber(this.offerUnit)) )
            {
                throw new WEB3BusinessLayerException(
                                                WEB3ErrorCatalog.BUSINESS_ERROR_00459,
                                                this.getClass().getName() + STR_METHOD_NAME);             
            }
            
            if(l_intOfferLength > 9)
            {
                throw new WEB3BusinessLayerException(
                                                WEB3ErrorCatalog.BUSINESS_ERROR_00460,
                                                this.getClass().getName() + STR_METHOD_NAME);                
            }
            double l_dblUnit = Double.parseDouble(this.offerUnit); 
			if (l_dblUnit == 0)
			{
				throw new WEB3BusinessLayerException(
								WEB3ErrorCatalog.BUSINESS_ERROR_00459,
								this.getClass().getName() + STR_METHOD_NAME);  
			}
            if(this.handlingQuantity != null)
            {
                double l_dblQuantity = Double.parseDouble(this.handlingQuantity);
                if(l_dblUnit > l_dblQuantity)
                {
                    throw new WEB3BusinessLayerException(
                                                    WEB3ErrorCatalog.BUSINESS_ERROR_00461,
                                                    this.getClass().getName() + STR_METHOD_NAME);                    
                }
                
                else if( (l_dblQuantity % l_dblUnit) != 0)
                {
                    throw new WEB3BusinessLayerException(
                                                    WEB3ErrorCatalog.BUSINESS_ERROR_00462,
                                                    this.getClass().getName() + STR_METHOD_NAME);                    
                }
            }
            
            if(this.offeringQuantity != null)
            {
                double l_dblOfferingQuantity = Double.parseDouble(this.offeringQuantity);
                if( (l_dblOfferingQuantity % l_dblUnit) != 0)
                {
                    throw new WEB3BusinessLayerException(
                                                    WEB3ErrorCatalog.BUSINESS_ERROR_00463,
                                                    this.getClass().getName() + STR_METHOD_NAME);                    
                }
            }
            
            if(this.issuedQuantity != null)
            {
                double l_dblIssuedQuantity = Double.parseDouble(this.issuedQuantity);
                if( (l_dblIssuedQuantity % l_dblUnit) != 0)
                {
                    throw new WEB3BusinessLayerException(
                                                    WEB3ErrorCatalog.BUSINESS_ERROR_00464,
                                                    this.getClass().getName() + STR_METHOD_NAME);                    
                }
            }         
        }  
        
        //�P�T�j�@@this.���݂̃`�F�b�N<BR>
        
        if( !(this.tickValue == null) )
        {
            int l_intTickValue = WEB3StringTypeUtility.getByteLength(this.tickValue);
            if( !(WEB3StringTypeUtility.isNumber(this.tickValue)) )
            {
                throw new WEB3BusinessLayerException(
                                WEB3ErrorCatalog.BUSINESS_ERROR_00465,
                                this.getClass().getName() + STR_METHOD_NAME);                
            }
            if(WEB3ProvisionalValueDivDef.TRUE_VALUE.equals(this.temporaryConditionDiv))
            {
                if(l_intTickValue > 6)
                {
                    throw new WEB3BusinessLayerException(
                                    WEB3ErrorCatalog.BUSINESS_ERROR_00466,
                                    this.getClass().getName() + STR_METHOD_NAME);                    
                }
                else if( !(WEB3StringTypeUtility.isInteger(this.tickValue)) )
                {
                    throw new WEB3BusinessLayerException(
                                    WEB3ErrorCatalog.BUSINESS_ERROR_00467,
                                    this.getClass().getName() + STR_METHOD_NAME);                          
                }
            }
            else if(WEB3ProvisionalValueDivDef.DISCOUNT_RATIO.equals(this.temporaryConditionDiv))
            {
                if( (WEB3StringTypeUtility.getIntegerDigits(this.tickValue) > 2) 
                    || WEB3StringTypeUtility.getFractionDigits(this.tickValue) > 2)
                {
                    throw new WEB3BusinessLayerException(
                                    WEB3ErrorCatalog.BUSINESS_ERROR_00468,
                                    this.getClass().getName() + STR_METHOD_NAME);                     
                }
            }
			double l_intTickD = Double.parseDouble(this.tickValue);
			if (l_intTickD == 0)
			{
				throw new WEB3BusinessLayerException(
								WEB3ErrorCatalog.BUSINESS_ERROR_00465,
								this.getClass().getName() + STR_METHOD_NAME);   
			}
        }

        //�P�U�j�@@this.�\���p�P�ʋ敪�̃`�F�b�N<BR>
        
        if(this.displayUnitDiv == null)
        {
            throw new WEB3BusinessLayerException(
                                            WEB3ErrorCatalog.BUSINESS_ERROR_00469,
                                            this.getClass().getName() + STR_METHOD_NAME);                        
        }
        else if(!WEB3IpoUnitDivDef.STOCK_QUANTITY.equals(this.displayUnitDiv) && !WEB3IpoUnitDivDef.QUANTITY.equals(this.displayUnitDiv))
        {
            throw new WEB3BusinessLayerException(
                                            WEB3ErrorCatalog.BUSINESS_ERROR_00470,
                                            this.getClass().getName() + STR_METHOD_NAME);            
        }
        //�P�V�j�@@this.�劲���،���Ж��̃`�F�b�N<BR>

        if(this.leadManagingUnderwriter == null)
        {
            throw new WEB3BusinessLayerException(
                                            WEB3ErrorCatalog.BUSINESS_ERROR_00471,
                                            this.getClass().getName() + STR_METHOD_NAME);            
        }

        int l_intLeadLength = WEB3StringTypeUtility.getByteLength(this.leadManagingUnderwriter);
        if(l_intLeadLength > 80)
        {
            throw new WEB3BusinessLayerException(
                                            WEB3ErrorCatalog.BUSINESS_ERROR_00472,
                                            this.getClass().getName() + STR_METHOD_NAME);            
        }
        //�P�W�j�@@this.�u�b�N�r���f�B���O�J�n�����̃`�F�b�N<BR>

        if(this.bookBuildingStartDate == null)
        {
            throw new WEB3BusinessLayerException(
                                            WEB3ErrorCatalog.BUSINESS_ERROR_00473,
                                            this.getClass().getName() + STR_METHOD_NAME);            
        }
        
        //�P�X�j�@@this.�u�b�N�r���f�B���O�I�������̃`�F�b�N<BR>

        if(this.bookBuildingEndDate == null)
        {
            throw new WEB3BusinessLayerException(
                                            WEB3ErrorCatalog.BUSINESS_ERROR_00474,
                                            this.getClass().getName() + STR_METHOD_NAME);            
        }
        
        //�Q�O�j�@@this.���J���i������̃`�F�b�N<BR>

        if(WEB3UndecideDecideDivDef.SCHEDULE_DECIDED.equals(this.undecideDecideDiv))
        {
            if(this.publicOfferingPriceDetermDate.startDate == null)
            {
                throw new WEB3BusinessLayerException(
                                                WEB3ErrorCatalog.BUSINESS_ERROR_00475,
                                                this.getClass().getName() + STR_METHOD_NAME);                  
            }
            this.publicOfferingPriceDetermDate.validate();
        }
        //�Q�P�j�@@this.���J���i�̃`�F�b�N<BR>

        if(this.publicOfferingPrice != null)
        {
            int l_intPriceLength = WEB3StringTypeUtility.getByteLength(this.publicOfferingPrice);
            if( !(WEB3StringTypeUtility.isNumber(this.publicOfferingPrice)) )
            {
                throw new WEB3BusinessLayerException(
                                                WEB3ErrorCatalog.BUSINESS_ERROR_00476,
                                                this.getClass().getName() + STR_METHOD_NAME);             
            }
            else if(l_intPriceLength > 9)
            {
                throw new WEB3BusinessLayerException(
                                                WEB3ErrorCatalog.BUSINESS_ERROR_00477,
                                                this.getClass().getName() + STR_METHOD_NAME);                
            }
        }        
        
        // �Q�Q�j�@@this.���J���i�f�B�X�J�E���g���̃`�F�b�N<BR>
         
        if( !(this.publicOfferingDiscountRate == null))
        {
            if( (WEB3StringTypeUtility.getIntegerDigits(this.publicOfferingDiscountRate) > 2) 
                || WEB3StringTypeUtility.getFractionDigits(this.publicOfferingDiscountRate) > 2)
            {
                throw new WEB3BusinessLayerException(
                                WEB3ErrorCatalog.BUSINESS_ERROR_00478,
                                this.getClass().getName() + STR_METHOD_NAME);                     
            }
        }
        
        //�Q�R�j�@@this.���I���̃`�F�b�N<BR>
        
        if(WEB3UndecideDecideDivDef.SCHEDULE_DECIDED.equals(this.undecideDecideDiv))
        {
            if(this.lotDate.startDate == null)
            {
                throw new WEB3BusinessLayerException(
                                                WEB3ErrorCatalog.BUSINESS_ERROR_00479,
                                                this.getClass().getName() + STR_METHOD_NAME);                  
            }
            this.lotDate.validate();
        }
        // �Q�S�j�@@this.�w���\�����ԁi�ژ_�����j�̃`�F�b�N<BR>
        
        if(WEB3UndecideDecideDivDef.SCHEDULE_DECIDED.equals(this.undecideDecideDiv))
        {
            if(this.prospectusOfferTerm.startDate == null)
            {
                throw new WEB3BusinessLayerException(
                                                WEB3ErrorCatalog.BUSINESS_ERROR_00480,
                                                this.getClass().getName() + STR_METHOD_NAME);                  
            }
            else if(this.prospectusOfferTerm.endDate == null)
            {
                throw new WEB3BusinessLayerException(
                                                WEB3ErrorCatalog.BUSINESS_ERROR_00480,
                                                this.getClass().getName() + STR_METHOD_NAME);                
            }
            this.prospectusOfferTerm.validate();
        }
        //�Q�T�j�@@this.�w���\�����ԁi���Ўw��j�̃`�F�b�N<BR>

        if(WEB3UndecideDecideDivDef.SCHEDULE_DECIDED.equals(this.undecideDecideDiv))
        {
            if(this.appointOfferTerm.startDate == null)
            {
                throw new WEB3BusinessLayerException(
                                                WEB3ErrorCatalog.BUSINESS_ERROR_00482,
                                                this.getClass().getName() + STR_METHOD_NAME);                  
            }
            else if(this.appointOfferTerm.endDate ==null)
            {
                throw new WEB3BusinessLayerException(
                                                WEB3ErrorCatalog.BUSINESS_ERROR_00482,
                                                this.getClass().getName() + STR_METHOD_NAME);                
            }
            this.appointOfferTerm.validate();
        }        
        
        //�Q�U�j�@@this.���s��Ѓ��S�t�@@�C��URL�̃`�F�b�N<BR>

        if(this.issuerLogoFileURL != null)
        {
            int l_intLogoLength = WEB3StringTypeUtility.getByteLength(this.issuerLogoFileURL);
            if(l_intLogoLength > 80)
            {
                throw new WEB3BusinessLayerException(
                                                WEB3ErrorCatalog.BUSINESS_ERROR_00484,
                                                this.getClass().getName() + STR_METHOD_NAME);                
            }
        }        
        // �Q�V�j�@@this.���s��ЃE�F�u�T�C�gURL�̃`�F�b�N<BR>

        int l_intWebLength = WEB3StringTypeUtility.getByteLength(this.issuerWebSiteURL);
        if(this.issuerWebSiteURL != null)
        {
            if(l_intWebLength > 80)
            {
                throw new WEB3BusinessLayerException(
                                                WEB3ErrorCatalog.BUSINESS_ERROR_00485,
                                                this.getClass().getName() + STR_METHOD_NAME);                
            }
        }    
        //�Q�W�j�@@this.���s��ЊT�v�̃`�F�b�N<BR>

        int l_intOutlineLength = WEB3StringTypeUtility.getByteLength(this.issuerCorporateOutline);
        if( !(this.issuerCorporateOutline == null) )
        {
            if(l_intOutlineLength > 400)
            {
                throw new WEB3BusinessLayerException(
                                                WEB3ErrorCatalog.BUSINESS_ERROR_00486,
                                                this.getClass().getName() + STR_METHOD_NAME);                
            }
        }    
        // �Q�X�j�@@this.���l�̃`�F�b�N<BR>

        int l_intNoteLength = WEB3StringTypeUtility.getByteLength(this.note);
        if( !(this.note == null) )
        {
            if(l_intNoteLength > 400)
            {
                throw new WEB3BusinessLayerException(
                                                WEB3ErrorCatalog.BUSINESS_ERROR_00487,
                                                this.getClass().getName() + STR_METHOD_NAME);                
            }
        }    
        //�R�O�j�@@this.���s�\�̃`�F�b�N <BR>

        if(this.marketOrderFlag == null)
        {
            throw new WEB3BusinessLayerException(
                                            WEB3ErrorCatalog.BUSINESS_ERROR_00591,
                                            this.getClass().getName() + STR_METHOD_NAME);            
        }
        else if(!WEB3EnableMarketOrderDef.ENABLE_MARKET_ORDER.equals(this.marketOrderFlag) 
                            && !WEB3EnableMarketOrderDef.DISABLE_MARKET_ORDER.equals(this.marketOrderFlag))
        {
            throw new WEB3BusinessLayerException(
                                            WEB3ErrorCatalog.BUSINESS_ERROR_00592,
                                            this.getClass().getName() + STR_METHOD_NAME);                                
        }
        
        // �R�P�j�@@this.���茈��敪�̃`�F�b�N <BR>

        if(this.undecideDecideDiv == null)
        {
            throw new WEB3BusinessLayerException(
                                            WEB3ErrorCatalog.BUSINESS_ERROR_00593,
                                            this.getClass().getName() + STR_METHOD_NAME);            
        }
        else if(!WEB3UndecideDecideDivDef.SCHEDULE_DECIDED.equals(this.undecideDecideDiv) 
                            && !WEB3UndecideDecideDivDef.SCHEDULE_UNDECIDED.equals(this.undecideDecideDiv))
        {
            throw new WEB3BusinessLayerException(
                                            WEB3ErrorCatalog.BUSINESS_ERROR_00594,
                                            this.getClass().getName() + STR_METHOD_NAME);                                
        }
        
        // �R�Q�j�@@this.�ژ_�����{���敪�̃`�F�b�N
        // �@@�R�Q�|�P�j�@@this.�ژ_�����{���敪 != null �̏ꍇ�A�ȉ����������s���B
        // �@@�@@�R�Q�|�P�|�P�j�@@�s���ȃR�[�h�l�̏ꍇ�A��O���X���[����B
        if (this.prospectusReadDiv != null)
        {
            if (!WEB3DocReadingDivDef.DEFAULT.equals(this.prospectusReadDiv)
                && !WEB3DocReadingDivDef.NO_READ.equals(this.prospectusReadDiv))
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02694,
                    this.getClass().getName() + STR_METHOD_NAME,
                    "�ژ_�����{���敪���s���ȃR�[�h�l�ł��B");
            }
        }
        log.exiting(STR_METHOD_NAME);
    }
}
@
