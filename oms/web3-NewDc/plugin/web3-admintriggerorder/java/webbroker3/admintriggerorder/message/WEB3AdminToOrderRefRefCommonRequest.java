head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.45.57;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminToOrderRefRefCommonRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �g���K�[�����Ǘ��ҁE�����Ɖ�ʃ��N�G�X�g(WEB3AdminToOrderRefRefCommonRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/02/16�@@�]�V�q(���u) �V�K�쐬
                 : 2006/08/23�@@�юu��(���u) �d�l�ύX���f��No.066,071
                 : 2006/10/18  ������(���u) �d�l�ύX���f��No.094
                 : 2006/11/30 ����(���u) �d�l�ύX���f��No.117
*/

package webbroker3.admintriggerorder.message;

import java.util.Date;

import webbroker3.admintriggerorder.define.WEB3AdminToTickMatchDivDef;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3CommodityDivDef;
import webbroker3.common.define.WEB3MarketCodeDef;
import webbroker3.common.define.WEB3TriggerOrderStatusDef;
import webbroker3.common.define.WEB3TriggerOrderTypeDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (�g���K�[�����Ǘ��ҁE�����Ɖ�ʃ��N�G�X�g)<BR>
 * 
 * @@author �]�V�q
 * @@version 1.0
 */
public class WEB3AdminToOrderRefRefCommonRequest extends WEB3GenRequest
{
    
    /**
     * ���O�o�̓��[�e�B���e�B�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminToOrderRefRefCommonRequest.class);

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_to_order_ref_ref_common";
    
    /**
     * serialVersionUID<BR>
     */
    public static final long serialVersionUID = 200602141850L;
        
    /**
     * (����ID)<BR>
     * ����ID<BR>
     */
    public String orderId = null;
    
    /**
     * (���X�R�[�h)<BR>
     * ���X�R�[�h�̔z�� <BR>
     * <BR>
     * �����X�R�[�h�����͎��́APR�w�ŕێ����Ă��� <BR>
     * �@@�戵�\���X�R�[�h�ꗗ���Z�b�g�����B<BR>
     */
    public String[] branchCode;
    
    /**
     * (�ڋq�R�[�h)<BR>
     * �ڋq�R�[�h<BR>
     */
    public String accountCode = null;
    
    /**
     * (�s��R�[�h)<BR>
     * �s��R�[�h<BR>
     */
    public String marketCode = null;
    
    /**
     * (���i�敪)<BR>
     * ���i�敪 <BR>
     * <BR>
     * 1�F�@@�������� <BR>
     * 2�F�@@�M�p��� <BR>
     * 3�F�@@�敨 <BR>
     * 4�F�@@�I�v�V���� <BR>
     */
    public String productDiv = null;
    
    /**
     * (�����������)<BR>
     * �����������<BR>
     * <BR>
     * 1�F�@@�A������<BR>
     * 2�F�@@OCO����<BR>
     * 3�F�@@IFD����<BR>
     * 4�F�@@�t�w�l����<BR>
     * 5�F�@@W�w�l����<BR>
     */
    public String triggerOrderType;
    
    /**
     * (�����󋵋敪)<BR>
     * �����󋵋敪<BR>
     * <BR>
     * 1�F�@@�ҋ@@��<BR>
     * 2�F�@@������ <BR>
     * 3�F�@@��������<BR>
     * 8�F�@@�����R���G���[<BR>
     * 9�F�@@�����x���G���[<BR>
     * 13�F�@@�X�g�b�v��������<BR>
     */
    public String triggerOrderState = null;
    
    /**
     * (������)<BR>
     * ������<BR>
     */
    public Date orderBizDate = null;
    
    /**
     * (��������M����From)<BR>
     * ��������M����From<BR>
     * (YYYYMMDDhhmmss)<BR>
     */
    public String currentPriceInfoAcceptTimeFrom = null;
    
    /**
     * (��������M����To)<BR>
     * ��������M����To<BR>
     * (YYYYMMDDhhmmss)<BR>
     */
    public String currentPriceInfoAcceptTimeTo = null;
    
    /**
     * (�g���K�[�N������From)<BR>
     * �g���K�[�N������From<BR>
     * (YYYYMMDDhhmmss)<BR>
     */
    public String triggerStartTimeFrom = null;
    
    /**
     * (�g���K�[�N������To)<BR>
     * �g���K�[�N������To<BR>
     * (YYYYMMDDhhmmss)<BR>
     */
    public String triggerStartTimeTo = null;
    
    /**
     * (������������From)<BR>
     * ������������From<BR>
     * (YYYYMMDDhhmmss)<BR>
     */
    public String orderCompleteTimeFrom = null;
    
    /**
     * (������������To)<BR>
     * ������������To<BR>
     * (YYYYMMDDhhmmss)<BR>
     */
    public String orderCompleteTimeTo = null;
    
    /**
     * (�ݒl�ƍ�����From)<BR>
     * ���ݒl�ƍ�����From<BR> 
     * (YYYYMMDDhhmmss)<BR>
     */
    public String tickMatchTimeFrom = null;
    
    /**
     * (���ݒl�ƍ�����To)<BR>
     * ���ݒl�ƍ�����To <BR>
     * (YYYYMMDDhhmmss)<BR>
     */
    public String tickMatchTimeTo = null;

    /**
     * (���ݒl�ƍ��敪)<BR>
     * ���ݒl�ƍ��敪 <BR>
     * <BR>
     * 1�F�@@�������^�� <BR>
     * 2�F�@@�s�������^��<BR>
     * 3�F�@@�����x���^��<BR>
     * 9�F�@@�S�ẴG���[ <BR>
     * <BR>
     * ���u0�F�@@����v�͐ݒ肳��Ȃ�<BR>
     */
    public String tickMatchDiv = null;

    /**
     * (�v���y�[�W�ԍ�)<BR>
     * �v���y�[�W�ԍ� <BR>
     * <BR>
     * �\�����������y�[�W�ʒu���w��@@���擪�y�[�W��"1"�Ƃ���<BR>
     */
    public String pageIndex;
    
    /**
     * (�y�[�W���\���s��)<BR>
     * �y�[�W���\���s�� <BR>
     * <BR>
     * �P�y�[�W���ɕ\�����������s�����w��<BR>
     */
    public String pageSize;
    
    /**
     * (�\�[�g�L�[)<BR>
     */
    public WEB3AdminToOrderRefSortKey[] sortKeys;
    
    /**
     * (�������Ԉꗗ)<BR>
     */
    public WEB3AdminToDifferentTimeUnit[] differentTimeList;
    
    /**
     * �R���X�g���N�^<BR>
     * @@roseuid 43F1B3C70399
     */
    public WEB3AdminToOrderRefRefCommonRequest() 
    {
     
    }
    
    /**
     * �����N�G�X�g�f�[�^�̐������`�F�b�N���s���B <BR>
     * �i�������A���N���X���Ŋ�������ȈՃ`�F�b�N�݂̂Ƃ���B�j <BR>
     * <BR>
     * �P�j�@@����ID�`�F�b�N <BR>
     * �@@this.����ID��null�̏ꍇ�A�ȉ��̃`�F�b�N���s���B <BR>
     * �@@�P�|�P�jthis.����ID�������̏ꍇ�A <BR>
     * �@@�@@�@@�@@�@@�u����ID�������ȊO�v�̗�O���X���[����B <BR>
     * class: WEB3BusinessLayerException<BR>
     * tag  : BUSINESS_ERROR_01476<BR>
     * <BR>
     * �Q�j�@@���X�R�[�h�`�F�b�N <BR>
     * �@@�Q�|�P�jthis.���X�R�[�h��null�̏ꍇ�A <BR>
     * �@@�@@�@@�@@�@@�u���X�R�[�h��null�v�̗�O���X���[����B <BR>
     * class: WEB3BusinessLayerException<BR>
     * tag  : BUSINESS_ERROR_02174<BR>
     * <BR>
     * �@@�Q�|�Q�jthis.���X�R�[�h.length��0�̏ꍇ�A <BR>
     * �@@�@@�@@�@@�@@�u���X�R�[�h�̗v�f����0�v�̗�O���X���[����B <BR>
     * class: WEB3BusinessLayerException<BR>
     * tag  : BUSINESS_ERROR_02175<BR>
     * <BR>
     * �@@�Q�|�R�jthis.���X�R�[�h�̗v�f�����ȉ��̏������s���B <BR>
     * �@@�@@�Q�|�R�|�P�jthis.���X�R�[�h���ȉ��̏����ɊY������ꍇ�A <BR>
     * �@@�@@�@@�@@�@@�@@�@@�u���X�R�[�h�G���[�v�̗�O���X���[����B <BR>
     * �@@�@@�@@�@@�@@�@@�@@�E���X�R�[�h������ <BR>
     * �@@�@@�@@�@@�@@�@@�@@�E���X�R�[�h.length��3 <BR>
     * class: WEB3BusinessLayerException<BR>
     * tag  : BUSINESS_ERROR_00779<BR>
     * <BR>
     * �R�j�s��R�[�h�`�F�b�N<BR>
     * �@@this.�s��R�[�h != null�̏ꍇ�A�ȉ��̃`�F�b�N���s���B<BR>
     * �@@�R�|�P�jthis.�s��R�[�h�ɉ��L�̍��ڈȊO���ݒ肳��Ă�����A<BR>
     * �@@�@@�@@�@@�u�s��R�[�h������`�̒l�v�̗�O���X���[����B<BR>
     * �@@�@@�@@�@@�E"����"<BR>
     * �@@�@@�@@�@@�E"���"<BR>
     * �@@�@@�@@�@@�E"���É�"<BR>
     * �@@�@@�@@�@@�E"����"<BR>
     * �@@�@@�@@�@@�E"�D�y"<BR>
     * �@@�@@�@@�@@�E"NNM"<BR>
     * �@@�@@�@@�@@�E"JASDAQ" <BR>
     * <BR>
     * �S�j�@@�ڋq�R�[�h�`�F�b�N <BR>
     * �@@this.�ڋq�R�[�h��null�̏ꍇ�A�ȉ��̃`�F�b�N���s���B <BR>
     * �@@�S�|�P�jthis.�ڋq�R�[�h���ȉ��̏����ɊY������ꍇ�A <BR>
     * �@@�@@�@@�@@�u�ڋq�R�[�h�G���[�v�̗�O���X���[����B <BR>
     * �@@�@@�@@�@@�E�ڋq�R�[�h������ <BR>
     * �@@�@@�@@�@@�E�ڋq�R�[�h.length��6 <BR>
     * class: WEB3BusinessLayerException<BR>
     * tag  : BUSINESS_ERROR_00780<BR>
     * <BR>
     * �T�j�@@���i�敪�`�F�b�N <BR>
     * �@@this.���i�敪��null�̏ꍇ�A�ȉ��̃`�F�b�N���s���B <BR>
     * �@@�T�|�P�jthis.���i�敪�ɉ��L�̍��ڈȊO���ݒ肳��Ă�����A <BR>
     * �@@�@@�@@�@@�u���i�敪������`�̒l�v�̗�O���X���[����B <BR>
     * �@@�@@�@@�@@�E"��������" <BR>
     * �@@�@@�@@�@@�E"�M�p���" <BR>
     * �@@�@@�@@�@@�E"�敨" <BR>
     * �@@�@@�@@�@@�E"�I�v�V����" <BR>
     * class: WEB3BusinessLayerException<BR>
     * tag  : BUSINESS_ERROR_01068<BR>
     * <BR>
     * �U�j�@@����������ʃ`�F�b�N<BR>
     * �@@�U�|�P�j this.����������ʁ�null�̏ꍇ�A<BR>
     * �@@�@@�@@�@@�u����������ʂ�null�v�̗�O���X���[����B<BR>
     * class: WEB3BusinessLayerException<BR>
     * tag  : BUSINESS_ERROR_02396<BR>
     * <BR>
     * �@@�U�|�Q�jthis.����������ʂɉ��L�̍��ڈȊO���ݒ肳��Ă����ꍇ�A<BR>
     * �@@�@@�@@�@@�u����������ʂ�����`�̒l�v�̗�O���X���[����B <BR>
     * �@@�@@�@@�@@�E"�A������" <BR>
     * �@@�@@�@@�@@�E"OCO����"<BR>
     * �@@�@@�@@�@@�E"IFD����"<BR>
     * �@@�@@�@@�@@�E"�t�w�l����" <BR>
     * �@@�@@�@@�@@�E"W�w�l����"<BR>
     * class: WEB3BusinessLayerException<BR>
     * tag  : BUSINESS_ERROR_02397<BR>
     * <BR>
     * �V�j�@@�����󋵋敪�`�F�b�N<BR>
     * �@@this.�����󋵋敪��null�̏ꍇ�A�ȉ��̃`�F�b�N���s���B <BR>
     * �@@�V�|�P�jthis.�����󋵂ɉ��L�̍��ڈȊO���ݒ肳��Ă�����A <BR>
     * �@@�@@�@@�@@�u�����󋵂�����`�̒l�v�̗�O���X���[����B <BR>
     * �@@�@@�@@�@@�E"�ҋ@@��" <BR>
     * �@@�@@�@@�@@�E"������" <BR>�@@
     * �@@�@@�@@�@@�E"��������" <BR>
     * �@@�@@�@@�@@�E"�����R���G���["<BR>
     * �@@�@@�@@�@@�E"�����x���G���["<BR>
     * �@@�@@�@@�@@�E"�X�g�b�v��������"<BR>
     * class: WEB3BusinessLayerException<BR>
     * tag  : BUSINESS_ERROR_02352<BR>
     * <BR>
     * �@@�V�|�Q�jthis.�����󋵋敪��"�X�g�b�v��������" ����<BR> 
     * �@@�@@�@@�@@�@@this.����������ʁ�"W�w�l����"�̏ꍇ�A<BR> 
     * �@@�@@�@@�u�����󋵎w�肪�s���v�̗�O���X���[����B<BR> 
     * class: WEB3BusinessLayerException<BR>
     * tag  : BUSINESS_ERROR_02626<BR>
     * <BR>
     * �@@�V�|�R�jthis.�����󋵋敪��"�����R���G���[" ���� <BR>
     * �@@�@@�@@�@@�@@this.����������ʁ�"W�w�l����"�̏ꍇ�A <BR>
     * �@@�@@�@@�uW�w�l�����͔����󋵋敪�F�����R���G���[�w��s�v�� <BR>
     * �@@�@@�@@��O���X���[����B<BR>
     * class: WEB3BusinessLayerException<BR>
     * tag  : BUSINESS_ERROR_02701<BR>
     * <BR>
     * �W�j�@@��������M����From�`�F�b�N <BR>
     * �@@this.��������M����From��null�̏ꍇ�A�ȉ��̃`�F�b�N���s���B <BR>
     * �@@�W�|�P�jthis.��������M����From��Date�^�ɕϊ��ł��Ȃ������ꍇ�A <BR>
     * �@@�@@�@@�@@�@@�u���͎��ԃG���[(��������M����From)�v�̗�O���X���[����B <BR>
     * class: WEB3BusinessLayerException<BR>
     * tag  : BUSINESS_ERROR_02354<BR>
     * <BR>
     * �X�j�@@��������M����To�`�F�b�N <BR>
     * �@@this.��������M����To��null�̏ꍇ�A�ȉ��̃`�F�b�N���s���B <BR>
     * �@@�X�|�P�jthis.��������M����To��Date�^�ɕϊ��ł��Ȃ������ꍇ�A <BR>
     * �@@�@@�@@�@@�@@�u���͎��ԃG���[(��������M����To)�v�̗�O���X���[����B <BR>
     * class: WEB3BusinessLayerException<BR>
     * tag  : BUSINESS_ERROR_02355<BR>
     * <BR>
     * �P�O�j�@@��������M����From/To�������`�F�b�N <BR>
     * �@@this.��������M����From��null ���� <BR>
     * �@@this.��������M����To��null�̏ꍇ�A�ȉ��̃`�F�b�N���s���B <BR>
     * �@@�P�O�|�P�jthis.��������M����From > this.��������M����To�̏ꍇ�A <BR>
     * �@@�@@�@@�@@�@@�u���͎��Ԑ������G���[�v�̗�O���X���[����B <BR>
     * class: WEB3BusinessLayerException<BR>
     * tag  : BUSINESS_ERROR_01481<BR>
     * <BR>
     * �P�P�j�@@�g���K�[�N������From�`�F�b�N <BR>
     * �@@this.�g���K�[�N������From��null�̏ꍇ�A�ȉ��̃`�F�b�N���s���B <BR>
     * �@@�P�P�|�P�jthis.�g���K�[�N������From��Date�^�ɕϊ��ł��Ȃ������ꍇ�A <BR>
     * �@@�@@�@@�@@�@@�u���͎��ԃG���[(�g���K�[�N������From)�v�̗�O���X���[����B <BR>
     * class: WEB3BusinessLayerException<BR>
     * tag  : BUSINESS_ERROR_02356<BR>
     * <BR>
     * �P�Q�j�@@�g���K�[�N������To�`�F�b�N <BR>
     * �@@this.�g���K�[�N������To��null�̏ꍇ�A�ȉ��̃`�F�b�N���s���B <BR>
     * �@@�P�Q�|�P�jthis.�g���K�[�N������To��Date�^�ɕϊ��ł��Ȃ������ꍇ�A <BR>
     * �@@�@@�@@�@@�@@�u���͎��ԃG���[(�g���K�[�N������To)�v�̗�O���X���[����B <BR>
     * class: WEB3BusinessLayerException<BR>
     * tag  : BUSINESS_ERROR_02357<BR>
     * <BR>
     * �P�R�j�@@�g���K�[�N������From/To�������`�F�b�N <BR>
     * �@@this.�g���K�[�N������From��null ���� <BR>
     * �@@this.�g���K�[�N������To��null�̏ꍇ�A�ȉ��̃`�F�b�N���s���B <BR>
     * �@@�P�R�|�P�jthis.�g���K�[�N������From > this.�g���K�[�N������To�̏ꍇ�A <BR>
     * �@@�@@�@@�@@�@@�u���͎��Ԑ������G���[�v�̗�O���X���[����B <BR>
     * class: WEB3BusinessLayerException<BR>
     * tag  : BUSINESS_ERROR_01481<BR>
     * <BR>
     * �P�S�j�@@������������From�`�F�b�N <BR>
     * �@@this.������������From��null�̏ꍇ�A�ȉ��̃`�F�b�N���s���B <BR>
     * �@@�P�S�|�P�jthis.������������From��Date�^�ɕϊ��ł��Ȃ������ꍇ�A <BR>
     * �@@�@@�@@�@@�@@�u���͎��ԃG���[(������������From)�v�̗�O���X���[����B <BR>
     * class: WEB3BusinessLayerException<BR>
     * tag  : BUSINESS_ERROR_02358<BR>
     * <BR>
     * �P�T�j�@@������������To�`�F�b�N <BR>
     * �@@this.������������To��null�̏ꍇ�A�ȉ��̃`�F�b�N���s���B <BR>
     * �@@�P�T�|�P�jthis.������������To��Date�^�ɕϊ��ł��Ȃ������ꍇ�A <BR>
     * �@@�@@�@@�@@�@@�u���͎��ԃG���[(������������To)�v�̗�O���X���[����B <BR>
     * class: WEB3BusinessLayerException<BR>
     * tag  : BUSINESS_ERROR_02359<BR>
     * <BR>
     * �P�U�j�@@������������From/To�������`�F�b�N <BR>
     * �@@this.������������From��null ���� <BR>
     * �@@this.������������To��null�̏ꍇ�A�ȉ��̃`�F�b�N���s���B <BR>
     * �@@�P�U�|�P�jthis.������������From > this.������������To�̏ꍇ�A <BR>
     * �@@�@@�@@�@@�@@�u���͎��Ԑ������G���[�v�̗�O���X���[����B <BR>
     * class: WEB3BusinessLayerException<BR>
     * tag  : BUSINESS_ERROR_01481<BR>
     * <BR>
     * �P�V�j�@@���ݒl�ƍ�����From�`�F�b�N<BR>  
     * �@@this.���ݒl�ƍ�����From��null�̏ꍇ�A�ȉ��̃`�F�b�N���s���B<BR>  
     * �@@�P�V�|�P�jthis.���ݒl�ƍ�����From��Date�^�ɕϊ��ł��Ȃ������ꍇ�A<BR>  
     * �@@�@@�@@�@@�@@�u���͎��ԃG���[(���ݒl�ƍ�����From)�v�̗�O���X���[����B<BR>
     * class: WEB3BusinessLayerException<BR>
     * tag  : BUSINESS_ERROR_02627<BR>
     * <BR>
     * �P�W�j�@@���ݒl�ƍ�����To�`�F�b�N  <BR>
     * �@@this.���ݒl�ƍ�����To��null�̏ꍇ�A�ȉ��̃`�F�b�N���s���B<BR>  
     * �@@�P�W�|�P�jthis.���ݒl�ƍ�����To��Date�^�ɕϊ��ł��Ȃ������ꍇ�A<BR>  
     * �@@�@@�@@�@@�@@�u���͎��ԃG���[(���ݒl�ƍ�����To)�v�̗�O���X���[����B<BR> 
     * class: WEB3BusinessLayerException<BR>
     * tag  : BUSINESS_ERROR_02628<BR> 
     * <BR>
     * �P�X�j�@@���ݒl�ƍ�����From/To�������`�F�b�N  <BR>
     * �@@this.���ݒl�ƍ�����From��null ����  <BR>
     * �@@this.���ݒl�ƍ�����To��null�̏ꍇ�A�ȉ��̃`�F�b�N���s���B<BR>  
     * �@@�P�X�|�P�jthis.���ݒl�ƍ�����From > this.���ݒl�ƍ�����To�̏ꍇ�A<BR>  
     * �@@�@@�@@�@@�@@�u���͎��Ԑ������G���[�v�̗�O���X���[����B<BR>
     * class: WEB3BusinessLayerException<BR>
     * tag  : BUSINESS_ERROR_01481<BR>  
     * <BR>
     * �Q�O�j�@@���ݒl�ƍ��敪�`�F�b�N <BR>
     * �@@this.���ݒl�ƍ��敪��null�̏ꍇ�A�ȉ��̃`�F�b�N���s���B<BR>  
     * �@@�Q�O�|�P�jthis.���ݒl�ƍ��敪�ɉ��L�̍��ڈȊO���ݒ肳��Ă�����A<BR>  
     * �@@�@@�@@�@@�u���ݒl�ƍ��敪������`�̒l�v�̗�O���X���[����B<BR>  
     * �@@�@@�@@�@@�E"�s�������^��" <BR>
     * �@@�@@�@@�@@�E"�������^��"  <BR>
     * �@@�@@�@@�@@�E"�����x���^��" <BR>
     * �@@�@@�@@�@@�E"�S�ẴG���[" <BR>
     * class: WEB3BusinessLayerException<BR>
     * tag  : BUSINESS_ERROR_02629<BR>
     * <BR>
     * �Q�P�j�@@�������Ԉꗗ�`�F�b�N<BR>
     * �@@this.�������Ԉꗗ��null�̏ꍇ�A<BR>
     * �@@�������Ԉꗗ�̑S�v�f�ɑ΂��ĉ��L�̃`�F�b�N���s���B <BR>
     * �@@�@@�Q�P�|�P�j��������.validate()���\�b�h���R�[������B<BR>
     * class: WEB3BusinessLayerException<BR>
     * tag  : BUSINESS_ERROR_02627<BR>
     * <BR>
     * �Q�Q�j�@@�\�[�g�L�[�`�F�b�N <BR>
     * �@@�Q�Q�|�P�jthis.�\�[�g�L�[��null�ł������ꍇ <BR>
     * �@@�@@�@@�@@�u�\�[�g�L�[��null�v�̗�O���X���[����B <BR>
     * class: WEB3BusinessLayerException<BR>
     * tag  : BUSINESS_ERROR_00231<BR>
     * <BR>
     * �@@�Q�Q�|�Q�jthis.�\�[�g�L�[.length��0�������ꍇ <BR>
     * �@@�@@�@@�@@�u�\�[�g�L�[.�v�f����0�v�̗�O���X���[����B <BR>
     * class: WEB3BusinessLayerException<BR>
     * tag  : BUSINESS_ERROR_00232<BR>
     * <BR>
     * �@@�Q�Q�|�R�jthis.�\�[�g�L�[�̑S�v�f�ɑ΂��� <BR>
     * �@@�@@�@@�@@���L�̃`�F�b�N���s���B <BR>
     * �@@�@@�Q�Q�|�R�|�P�j�\�[�g�L�[.validate()���R�[������B <BR>
     * <BR>
     * �Q�R�j�v���y�[�W�ԍ��`�F�b�N <BR>
     * �@@�Q�R�|�P�jthis.�v���y�[�W�ԍ���null�ł������ꍇ�A <BR>
     * �@@�@@�@@�@@�u�v���y�[�W�ԍ���null�v�̗�O���X���[����B <BR>
     * class: WEB3BusinessLayerException<BR>
     * tag  : BUSINESS_ERROR_00089<BR>
     * <BR>
     * �@@�Q�R�|�Q�jthis.�v���y�[�W�ԍ��������̏ꍇ�A <BR>
     * �@@�@@�@@�@@�u�v���y�[�W�ԍ��������ȊO�v�̗�O���X���[����B <BR>
     * class: WEB3BusinessLayerException<BR>
     * tag  : BUSINESS_ERROR_00090<BR>
     * <BR>
     * �@@�Q�R�|�R�jthis.�v���y�[�W�ԍ� <= 0�ł������ꍇ�A <BR>
     * �@@�@@�@@�@@�u�v���y�[�W�ԍ���0�ȉ��v�̗�O���X���[����B <BR>
     * class: WEB3BusinessLayerException<BR>
     * tag  : BUSINESS_ERROR_00616<BR>
     * <BR>
     * �Q�S�j�y�[�W���\���s���`�F�b�N <BR>
     * �@@�Q�S�|�P�jthis.�y�[�W���\���s����null�ł������ꍇ�A <BR>
     * �@@�@@�@@�@@�u�y�[�W���\���s����null�v�̗�O���X���[����B <BR>
     * class: WEB3BusinessLayerException<BR>
     * tag  : BUSINESS_ERROR_02224<BR>
     * <BR>
     * �@@�Q�S�|�Q�jthis.�y�[�W���\���s���������̏ꍇ�A <BR>
     * �@@�@@�@@�@@�u�y�[�W���\���s���������ȊO�v�̗�O���X���[����B <BR>
     * class: WEB3BusinessLayerException<BR>
     * tag  : BUSINESS_ERROR_00092<BR>
     * �@@ <BR>
     * �@@�Q�S�|�R�jthis.�y�[�W���\���s�� <= 0�ł������ꍇ�A <BR>
     * �@@�@@�@@�@@�u�y�[�W���\���s����0�ȉ��v�̗�O���X���[����B <BR>
     * class: WEB3BusinessLayerException<BR>
     * tag  : BUSINESS_ERROR_00617<BR>
     * @@throws WEB3BaseException
     * @@roseuid 43DF145E0370
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " validate()";
        log.entering(STR_METHOD_NAME);
        
        //�P�j�@@����ID�`�F�b�N
        //�@@this.����ID��null�̏ꍇ�A�ȉ��̃`�F�b�N���s���B
        if (this.orderId != null)
        {
            //�@@�P�|�P�jthis.����ID�������̏ꍇ�A
            //�@@�@@�@@�@@�@@�u����ID�������ȊO�v�̗�O���X���[����B
            if (!WEB3StringTypeUtility.isInteger(this.orderId))
            {
                log.debug("����ID�������ȊO�ł��B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01476,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "����ID�������ȊO�ł��B");
            }
        }
        
        //�Q�j�@@���X�R�[�h�`�F�b�N
        //�@@�Q�|�P�jthis.���X�R�[�h��null�̏ꍇ�A
        //�@@�@@�@@�@@�@@�u���X�R�[�h��null�v�̗�O���X���[����B 
        if (this.branchCode == null)
        {
            log.debug("���X�R�[�h��null�ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02174,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "���X�R�[�h��null�ł��B");
        }
        
        //�@@�Q�|�Q�jthis.���X�R�[�h.length��0�̏ꍇ�A
        //�@@�@@�@@�@@�@@�u���X�R�[�h�̗v�f����0�v�̗�O���X���[����B
        if (this.branchCode.length == 0)
        {
            log.debug("���X�R�[�h�̗v�f����0�ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02175,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "���X�R�[�h�̗v�f����0�ł��B");
        }
        
        //�@@�Q�|�R�jthis.���X�R�[�h�̗v�f�����ȉ��̏������s���B
        //�@@�@@�Q�|�R�|�P�jthis.���X�R�[�h���ȉ��̏����ɊY������ꍇ�A
        //�@@�@@�@@�@@�@@�@@�@@�u���X�R�[�h�G���[�v�̗�O���X���[����B
        //�@@�@@�@@�@@�@@�@@�@@�E���X�R�[�h������
        //�@@�@@�@@�@@�@@�@@�@@�E���X�R�[�h.length��3 
        int l_intLen = this.branchCode.length;
        for (int i = 0; i < l_intLen; i++)
        {
            if (!WEB3StringTypeUtility.isInteger(this.branchCode[i])
                || this.branchCode[i].length() != 3)
            {
                log.debug("���X�R�[�h�̓��͂��s���ł��B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00779,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "���X�R�[�h�̓��͂��s���ł��B");
            }
        }

        //�@@�R�j�s��R�[�h�`�F�b�N  
        //�@@�@@this.�s��R�[�h != null�̏ꍇ�A�ȉ��̃`�F�b�N���s���B  
        //�@@�@@�R�|�P�jthis.�s��R�[�h�ɉ��L�̍��ڈȊO���ݒ肳��Ă�����A  
        //�@@�@@�@@�@@�@@�u�s��R�[�h������`�̒l�v�̗�O���X���[����B  
        //�@@�@@�@@�@@�@@�E"����"  
        //�@@�@@�@@�@@�@@�E"���"  
        //�@@�@@�@@�@@�@@�E"���É�"  
        //�@@�@@�@@�@@�@@�E"����"  
        //�@@�@@�@@�@@�@@�E"�D�y"  
        //�@@�@@�@@�@@�@@�E"NNM"  
        //�@@�@@�@@�@@�@@�E"JASDAQ" 
        if (this.marketCode != null
            && !WEB3MarketCodeDef.TOKYO.equals(this.marketCode)
            && !WEB3MarketCodeDef.OSAKA.equals(this.marketCode)
            && !WEB3MarketCodeDef.NAGOYA.equals(this.marketCode)
            && !WEB3MarketCodeDef.FUKUOKA.equals(this.marketCode)
            && !WEB3MarketCodeDef.SAPPORO.equals(this.marketCode)
            && !WEB3MarketCodeDef.NNM.equals(this.marketCode)
            && !WEB3MarketCodeDef.JASDAQ.equals(this.marketCode))
        {
            log.debug("�s��R�[�h�����݂��Ȃ��R�[�h�l�ł��B");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00608,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�s��R�[�h�����݂��Ȃ��R�[�h�l�ł��B");
        }

        //�S�j�@@�ڋq�R�[�h�`�F�b�N
        //�@@this.�ڋq�R�[�h��null�̏ꍇ�A�ȉ��̃`�F�b�N���s���B
        if (this.accountCode != null)
        {
            //�S�|�P�jthis.�ڋq�R�[�h���ȉ��̏����ɊY������ꍇ�A
            //�@@�@@�@@�u�ڋq�R�[�h�G���[�v�̗�O���X���[����B
            //�@@�@@�@@�E�ڋq�R�[�h������
            //�@@�@@�@@�E�ڋq�R�[�h.length��6
            if (!WEB3StringTypeUtility.isInteger(this.accountCode)
                || this.accountCode.length() != 6)
            {
                log.debug("�ڋq�R�[�h�̓��͂��s���ł��B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00780,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "�ڋq�R�[�h�̓��͂��s���ł��B");
            }
        }
        
        //�T�j�@@���i�敪�`�F�b�N
        //�@@this.���i�敪��null�̏ꍇ�A�ȉ��̃`�F�b�N���s���B
        if (this.productDiv != null)
        {
            //�T�|�P�jthis.���i�敪�ɉ��L�̍��ڈȊO���ݒ肳��Ă�����A
            //�@@�@@�@@�u���i�敪������`�̒l�v�̗�O���X���[����B
            //�@@�@@�@@�E"��������"
            //�@@�@@�@@�E"�M�p���"
            //�@@�@@�@@�E"�敨"
            //�@@�@@�@@�E"�I�v�V����"
            if (!(WEB3CommodityDivDef.EQUITY.equals(this.productDiv)
                || WEB3CommodityDivDef.MARGIN.equals(this.productDiv)
                || WEB3CommodityDivDef.FUTURE.equals(this.productDiv)
                || WEB3CommodityDivDef.OPTION.equals(this.productDiv)))
            {
                log.debug("���i�敪�����݂��Ȃ��R�[�h�l�ł��B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01068,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "���i�敪�����݂��Ȃ��R�[�h�l�ł��B");
            }
        }
        
        //�U�j�@@����������ʃ`�F�b�N
        //�U�|�P�j this.����������ʁ�null�̏ꍇ�A
        //�@@�@@�@@�u����������ʂ�null�v�̗�O���X���[����B
        if (this.triggerOrderType == null)
        {
            log.debug("����������ʂ����w��ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02396,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "����������ʂ����w��ł��B");
        }
        
        //�U�|�Q�jthis.����������ʂɉ��L�̍��ڈȊO���ݒ肳��Ă����ꍇ�A
        //�@@�@@�@@�u����������ʂ�����`�̒l�v�̗�O���X���[����B
        //�@@�@@�@@�E"�A������" 
        //�@@�@@�@@�E"OCO����"
        //�@@�@@�@@�E"IFD����"
        //�@@�@@�@@�E"�t�w�l����"
        //�@@�@@�@@�E"W�w�l����"
        if (!(WEB3TriggerOrderTypeDef.SUCC.equals(this.triggerOrderType)
            || WEB3TriggerOrderTypeDef.OCO.equals(this.triggerOrderType)
            || WEB3TriggerOrderTypeDef.IFD.equals(this.triggerOrderType)
            || WEB3TriggerOrderTypeDef.STOP.equals(this.triggerOrderType)
            || WEB3TriggerOrderTypeDef.W_LlIMIT.equals(this.triggerOrderType)))
        {
            log.debug("����������ʂ�����`�̒l�ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02397,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "����������ʂ�����`�̒l�ł��B");
        }
        
        //�V�j�@@�����󋵋敪�`�F�b�N
        //�@@this.�����󋵋敪��null�̏ꍇ�A�ȉ��̃`�F�b�N���s���B
        if (this.triggerOrderState != null)
        {
            //�V�|�P�jthis.�����󋵂ɉ��L�̍��ڈȊO���ݒ肳��Ă�����A
            //�@@�@@�@@�u�����󋵂�����`�̒l�v�̗�O���X���[����B 
            //�@@�@@�@@�E"�ҋ@@��" 
            //�@@�@@�@@�E"������" 
            //�@@�@@�@@�E"��������" 
            //�@@�@@�@@�E"�����R���G���["
            //�@@�@@�@@�E"�����x���G���["
            //�@@�@@�@@�E"�X�g�b�v��������" 
            if (!(WEB3TriggerOrderStatusDef.ORDER_WAITING.equals(this.triggerOrderState)
                || WEB3TriggerOrderStatusDef.ORDERING.equals(this.triggerOrderState)
                || WEB3TriggerOrderStatusDef.ORDER_COMPLETE.equals(this.triggerOrderState)
                || WEB3TriggerOrderStatusDef.ORDER_VALIDATE_ERROR.equals(this.triggerOrderState)
                || WEB3TriggerOrderStatusDef.ORDER_DELAY_ERROR.equals(this.triggerOrderState)
                || WEB3TriggerOrderStatusDef.STOP_ORDER_INVALIDATION.equals(this.triggerOrderState)))
            {
                log.debug("�����󋵂̒l�����݂��Ȃ��R�[�h�l�ł��B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02352,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "�����󋵂̒l�����݂��Ȃ��R�[�h�l�ł��B");
            }
            
            //�@@�V�|�Q�jthis.�����󋵋敪��"�X�g�b�v��������" ���� 
            //�@@�@@�@@�@@�@@this.����������ʁ�"W�w�l����"�̏ꍇ�A 
            //�@@�@@�@@�u�����󋵎w�肪�s���v�̗�O���X���[����B
            if (WEB3TriggerOrderStatusDef.STOP_ORDER_INVALIDATION.equals(this.triggerOrderState) 
                && !WEB3TriggerOrderTypeDef.W_LlIMIT.equals(this.triggerOrderType))
            {
                log.debug("�����󋵎w�肪�s���B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02626,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "�����󋵎w�肪�s���B");
            }
            
            //�V�|�R�jthis.�����󋵋敪��"�����R���G���[" ���� 
            //   this.����������ʁ�"W�w�l����"�̏ꍇ�A 
            // �uW�w�l�����͔����󋵋敪�F�����R���G���[�w��s�v�� 
            // ��O���X���[����B 
            if (WEB3TriggerOrderStatusDef.ORDER_VALIDATE_ERROR.equals(this.triggerOrderState) 
                && WEB3TriggerOrderTypeDef.W_LlIMIT.equals(this.triggerOrderType))
            {
                log.debug("W�w�l�����͔����󋵋敪�F�����R���G���[�w��s��");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02701,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "W�w�l�����͔����󋵋敪�F�����R���G���[�w��s��");
            }
        }
        
        //�W�j�@@��������M����From�`�F�b�N
        //�@@this.��������M����From��null�̏ꍇ�A�ȉ��̃`�F�b�N���s���B
        if (this.currentPriceInfoAcceptTimeFrom != null)
        {
            //�W�|�P�jthis.��������M����From��Date�^�ɕϊ��ł��Ȃ������ꍇ�A 
            //�@@�@@�@@�@@�u���͎��ԃG���[(��������M����From)�v�̗�O���X���[����B
            if (!WEB3StringTypeUtility.isDateStr(this.currentPriceInfoAcceptTimeFrom, "yyyyMMddHHmmss"))
            {
                log.debug("���͎��ԃG���[(��������M����From)�B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02354,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "���͎��ԃG���[(��������M����From)�B");
            }
        }

        //�X�j�@@��������M����To�`�F�b�N 
        //�@@this.��������M����To��null�̏ꍇ�A�ȉ��̃`�F�b�N���s���B 
        if (this.currentPriceInfoAcceptTimeTo != null)
        {
            //�X�|�P�jthis.��������M����To��Date�^�ɕϊ��ł��Ȃ������ꍇ�A 
            //�@@�@@�@@�@@�u���͎��ԃG���[(��������M����To)�v�̗�O���X���[����B 
            if (!WEB3StringTypeUtility.isDateStr(this.currentPriceInfoAcceptTimeTo, "yyyyMMddHHmmss"))
            {
                log.debug("���͎��ԃG���[(��������M����To)�B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02355,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "���͎��ԃG���[(��������M����To)�B");
            }
        }
        
        //�P�O�j�@@��������M����From/To�������`�F�b�N 
        //�@@this.��������M����From��null ���� 
        //�@@this.��������M����To��null�̏ꍇ�A�ȉ��̃`�F�b�N���s���B 
        if (this.currentPriceInfoAcceptTimeFrom != null && this.currentPriceInfoAcceptTimeTo != null)
        {
            //�P�O�|�P�jthis.��������M����From > this.��������M����To�̏ꍇ�A
            //�@@�@@�@@�@@�u���͎��Ԑ������G���[�v�̗�O���X���[����B 
            if (this.currentPriceInfoAcceptTimeFrom.compareTo(
                this.currentPriceInfoAcceptTimeTo) > 0)
            {
                log.debug("���͎��Ԑ������G���[�B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01481,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "���͎��Ԑ������G���[�B");
            }
        }
        
        //�P�P�j�@@�g���K�[�N������From�`�F�b�N
        //�@@this.�g���K�[�N������From��null�̏ꍇ�A�ȉ��̃`�F�b�N���s���B
        if (this.triggerStartTimeFrom != null)
        {
            //�@@�P�P�|�P�jthis.�g���K�[�N������From��Date�^�ɕϊ��ł��Ȃ������ꍇ�A
            //�@@�@@�@@�@@�@@�u���͎��ԃG���[(�g���K�[�N������From)�v�̗�O���X���[����B
            if (!WEB3StringTypeUtility.isDateStr(this.triggerStartTimeFrom, "yyyyMMddHHmmss"))
            {
                log.debug("���͎��ԃG���[(�g���K�[�N������From)�B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02356,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "���͎��ԃG���[(�g���K�[�N������From)�B");
            }
        }
        
        //�P�Q�j�@@�g���K�[�N������To�`�F�b�N
        //�@@this.�g���K�[�N������To��null�̏ꍇ�A�ȉ��̃`�F�b�N���s���B 
        if (this.triggerStartTimeTo != null)
        {
            //�P�Q�|�P�jthis.�g���K�[�N������To��Date�^�ɕϊ��ł��Ȃ������ꍇ�A
            //�@@�@@�@@�@@�u���͎��ԃG���[(�g���K�[�N������To)�v�̗�O���X���[����B
            if (!WEB3StringTypeUtility.isDateStr(this.triggerStartTimeTo, "yyyyMMddHHmmss"))
            {
                log.debug("���͎��ԃG���[(�g���K�[�N������To)�B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02357,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "���͎��ԃG���[(�g���K�[�N������To)�B");
            }
        }
        
        //�P�R�j�@@�g���K�[�N������From/To�������`�F�b�N
        //�@@this.�g���K�[�N������From��null ����
        //�@@this.�g���K�[�N������To��null�̏ꍇ�A�ȉ��̃`�F�b�N���s���B
        if (this.triggerStartTimeFrom != null && this.triggerStartTimeTo != null)
        {
            //�P�R�|�P�jthis.�g���K�[�N������From > this.�g���K�[�N������To�̏ꍇ�A
            //�@@�@@�@@�@@�u���͎��Ԑ������G���[�v�̗�O���X���[����B
            if (this.triggerStartTimeFrom.compareTo(this.triggerStartTimeTo) > 0)
            {
                log.debug("���͎��Ԑ������G���[�B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01481,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "���͎��Ԑ������G���[�B");
            }
        }

        //�P�S�j�@@������������From�`�F�b�N>
        //�@@this.������������From��null�̏ꍇ�A�ȉ��̃`�F�b�N���s���B
        if (this.orderCompleteTimeFrom != null)
        {
            //�P�S�|�P�jthis.������������From��Date�^�ɕϊ��ł��Ȃ������ꍇ�A
            //�@@�@@�@@�@@�u���͎��ԃG���[(������������From)�v�̗�O���X���[����B   
            if (!WEB3StringTypeUtility.isDateStr(this.orderCompleteTimeFrom, "yyyyMMddHHmmss"))
            {
                log.debug("���͎��ԃG���[(������������From)�B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02358,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "���͎��ԃG���[(������������From)�B");
            }
        }
        
        //�P�T�j�@@������������To�`�F�b�N
        //�@@this.������������To��null�̏ꍇ�A�ȉ��̃`�F�b�N���s���B
        if (this.orderCompleteTimeTo != null)
        {
            //�P�T�|�P�jthis.������������To��Date�^�ɕϊ��ł��Ȃ������ꍇ�A
            //�@@�@@�@@�@@�u���͎��ԃG���[(������������To)�v�̗�O���X���[����B
            if (!WEB3StringTypeUtility.isDateStr(this.orderCompleteTimeTo, "yyyyMMddHHmmss"))
            {
                log.debug("���͎��ԃG���[(������������To)�B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02359,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "���͎��ԃG���[(������������To)�B");
            }
        }

        //�P�U�j�@@������������From/To�������`�F�b�N
        //�@@this.������������From��null ����
        //�@@this.������������To��null�̏ꍇ�A�ȉ��̃`�F�b�N���s���B
        if (this.orderCompleteTimeFrom != null && this.orderCompleteTimeTo != null)
        {
            //�P�U�|�P�jthis.������������From > this.������������To�̏ꍇ�A 
            //�@@�@@�@@�@@�u���͎��Ԑ������G���[�v�̗�O���X���[����B
            if (this.orderCompleteTimeFrom.compareTo(this.orderCompleteTimeTo) > 0)
            {
                log.debug("���͎��Ԑ������G���[�B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01481,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "���͎��Ԑ������G���[�B");
            }
        }

        //�P�V�j�@@���ݒl�ƍ�����From�`�F�b�N 
        //�@@this.���ݒl�ƍ�����From��null�̏ꍇ�A�ȉ��̃`�F�b�N���s���B  
        //�@@�P�V�|�P�jthis.���ݒl�ƍ�����From��Date�^�ɕϊ��ł��Ȃ������ꍇ�A  
        //�@@�@@�@@�@@�@@�u���͎��ԃG���[(���ݒl�ƍ�����From)�v�̗�O���X���[����B  
        if (this.tickMatchTimeFrom != null && 
            !WEB3StringTypeUtility.isDateStr(this.tickMatchTimeFrom, "yyyyMMddHHmmss"))
        {
            log.debug("���͎��ԃG���[(���ݒl�ƍ�����From)");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02627,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "���͎��ԃG���[(���ݒl�ƍ�����From)");
        }
        
        //�P�W�j�@@���ݒl�ƍ�����To�`�F�b�N  
        //�@@this.���ݒl�ƍ�����To��null�̏ꍇ�A�ȉ��̃`�F�b�N���s���B  
        //�@@�P�W�|�P�jthis.���ݒl�ƍ�����To��Date�^�ɕϊ��ł��Ȃ������ꍇ�A  
        //�@@�@@�@@�@@�@@�u���͎��ԃG���[(���ݒl�ƍ�����To)�v�̗�O���X���[����B  
        if (this.tickMatchTimeTo != null 
            && !WEB3StringTypeUtility.isDateStr(this.tickMatchTimeTo, "yyyyMMddHHmmss"))
        {
            log.debug("���͎��ԃG���[(���ݒl�ƍ�����To)�B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02628,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "���͎��ԃG���[(���ݒl�ƍ�����To)�B");
        }
        
        //�P�X�j�@@���ݒl�ƍ�����From/To�������`�F�b�N  
        //�@@this.���ݒl�ƍ�����From��null ����  
        //�@@this.���ݒl�ƍ�����To��null�̏ꍇ�A�ȉ��̃`�F�b�N���s���B  
        //�@@�P�X�|�P�jthis.���ݒl�ƍ�����From > this.���ݒl�ƍ�����To�̏ꍇ�A  
        //�@@�@@�@@�@@�@@�u���͎��Ԑ������G���[�v�̗�O���X���[����B  
        if (this.tickMatchTimeFrom != null && this.tickMatchTimeTo != null) 
        {
            if (this.tickMatchTimeFrom.compareTo(this.tickMatchTimeTo) > 0)
            {
                log.debug("���͎��Ԑ������G���[�B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01481,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "���͎��Ԑ������G���[�B");
            }
        }
        
        //�Q�O�j�@@���ݒl�ƍ��敪�`�F�b�N 
        //�@@this.���ݒl�ƍ��敪��null�̏ꍇ�A�ȉ��̃`�F�b�N���s���B  
        //�@@�Q�O�|�P�jthis.���ݒl�ƍ��敪�ɉ��L�̍��ڈȊO���ݒ肳��Ă�����A  
        //�@@�@@�@@�@@�u���ݒl�ƍ��敪������`�̒l�v�̗�O���X���[����B  
        //�@@�@@�@@�@@�E"�s�������^��" �@@�@@ 
        //�@@�@@�@@�@@�E"�������^��" 
        //        �E"�����x���^��"
        //�@@�@@�@@�@@�E"�S�ẴG���["      
        if (this.tickMatchDiv != null)
        {
            if (!WEB3AdminToTickMatchDivDef.ERROR_ORDER_SUSPICION.equals(this.tickMatchDiv)
                && !WEB3AdminToTickMatchDivDef.NOT_ORDER_SUSPICION.equals(this.tickMatchDiv)
                && !WEB3AdminToTickMatchDivDef.ORDER_DELAY_SUSPICION.equals(this.tickMatchDiv)
                && !WEB3AdminToTickMatchDivDef.ALL_ERROR.equals(this.tickMatchDiv))
            {
                log.debug("���ݒl�ƍ��敪������`�̒l�B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02629,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "���ݒl�ƍ��敪������`�̒l�B");
            }
        }
        
        //�Q�P�j�@@�������Ԉꗗ�`�F�b�N
        //�@@this.�������Ԉꗗ��null�̏ꍇ�A
        //�@@�������Ԉꗗ�̑S�v�f�ɑ΂��ĉ��L�̃`�F�b�N���s���B
        //�@@�@@�Q�P�|�P�j��������.validate()���\�b�h���R�[������B
        if (this.differentTimeList != null)
        {
            l_intLen = this.differentTimeList.length;
            for (int i = 0; i < l_intLen; i++)
            {
                this.differentTimeList[i].validate();
            }
        }

        //�Q�Q�j�@@�\�[�g�L�[�`�F�b�N
        //�Q�Q�|�P�jthis.�\�[�g�L�[��null�ł������ꍇ
        //�@@�@@�@@�u�\�[�g�L�[��null�v�̗�O���X���[����B
        if (this.sortKeys == null)
        {
            log.debug("�\�[�g�L�[�����w��ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00231,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�\�[�g�L�[�����w��ł��B");
        }
        
        //�Q�Q�|�Q�jthis.�\�[�g�L�[.length��0�������ꍇ
        //�@@�@@�@@�u�\�[�g�L�[.�v�f����0�v�̗�O���X���[����B
        if (this.sortKeys.length == 0)
        {
            log.debug("�\�[�g�L�[�̗v�f�����O�ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00232,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�\�[�g�L�[�̗v�f�����O�ł��B");
        }
        
        //�Q�Q�|�R�jthis.�\�[�g�L�[�̑S�v�f�ɑ΂���
        //�@@�@@�@@���L�̃`�F�b�N���s���B
        //�@@�Q�Q�|�R�|�P�j�\�[�g�L�[.validate()���R�[������B
        l_intLen = this.sortKeys.length;
        for (int i = 0; i < l_intLen; i++)
        {
            this.sortKeys[i].validate();
        }
        
        //�Q�R�j�v���y�[�W�ԍ��`�F�b�N
        //�Q�R�|�P�jthis.�v���y�[�W�ԍ���null�ł������ꍇ�A
        //�@@�@@�@@�u�v���y�[�W�ԍ���null�v�̗�O���X���[����B
        if (this.pageIndex == null)
        {
            log.debug("�v���y�[�W�ԍ������w��ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00089,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�v���y�[�W�ԍ������w��ł��B");
        }
        
        //�Q�R�|�Q�jthis.�v���y�[�W�ԍ��������̏ꍇ�A
        //�@@�@@�@@�u�v���y�[�W�ԍ��������ȊO�v�̗�O���X���[����B
        if (!WEB3StringTypeUtility.isInteger(this.pageIndex))
        {
            log.debug("�v���y�[�W�ԍ��������ȊO�̒l�ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00090,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�v���y�[�W�ԍ��������ȊO�̒l�ł��B");
        }
        
        //�Q�R�|�R�jthis.�v���y�[�W�ԍ� <= 0�ł������ꍇ�A
        //�@@�@@�@@�u�v���y�[�W�ԍ���0�ȉ��v�̗�O���X���[����B
        if (Integer.parseInt(this.pageIndex) <= 0)
        {
            log.debug("�v���y�[�W�ԍ��̒l��0�ȉ��ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00616,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�v���y�[�W�ԍ��̒l��0�ȉ��ł��B");
        }
        
        //�Q�S�j�y�[�W���\���s���`�F�b�N
        //�Q�S�|�P�jthis.�y�[�W���\���s����null�ł������ꍇ�A
        //�@@�@@�@@�u�y�[�W���\���s����null�v�̗�O���X���[����B
        if (this.pageSize == null)
        {
            log.debug("�y�[�W���\���s���������͂ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02224,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�y�[�W���\���s���������͂ł��B");
        }
        
        //�Q�S�|�Q�jthis.�y�[�W���\���s���������̏ꍇ�A
        //�@@�@@�@@�u�y�[�W���\���s���������ȊO�v�̗�O���X���[����B
        if (!WEB3StringTypeUtility.isInteger(this.pageSize))
        {
            log.debug("�y�[�W���\���s���������ȊO�̒l�ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00092,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�y�[�W���\���s���������ȊO�̒l�ł��B");
        }
        
        //�Q�S�|�R�jthis.�y�[�W���\���s�� <= 0�ł������ꍇ�A
        //�@@�@@�@@�u�y�[�W���\���s����0�ȉ��v�̗�O���X���[����B
        if (Integer.parseInt(this.pageSize) <= 0)
        {
            log.debug("�y�[�W���\���s���̒l��0�ȉ��ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00617,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�y�[�W���\���s���̒l��0�ȉ��ł��B");
        }

        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (createResponse�̎���)<BR>
     * @@return WEB3GenResponse
     * @@roseuid 4158EB6402B2
     */
    public WEB3GenResponse createResponse() 
    {
        return null;
    }
}
@
