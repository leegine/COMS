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
filename	WEB3EquityCommonRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����������ʃ��N�G�X�g(WEB3EquityCommonRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/05/17 ���_�� (���u) �V�K�쐬
                   2006/11/01 �����F(���u) ���f�� 948,1000
*/
package webbroker3.equity.message;

import java.util.Date;

import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3ExecutionConditionDef;
import webbroker3.common.define.WEB3OrderingConditionDef;
import webbroker3.common.define.WEB3OrderExpirationDateTypeDef;
import webbroker3.common.define.WEB3OrderPriceDivDef;
import webbroker3.common.define.WEB3PriceConditionDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;
import webbroker3.common.define.WEB3OrderConditionOperatorDef;
import webbroker3.equity.define.WEB3EquityWlimitEnableStatusDivDef;

/**
 * �i�����������ʃ��N�G�X�g�j�B<br>
 * <br>
 * �����������ʗv���@@���N�G�X�g�f�[�^�N���X
 * @@version 1.0
 */
public class WEB3EquityCommonRequest extends WEB3GenRequest
{

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 20040521001L;

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "equity_common";

    /**
     * ��������<BR>
     */
    public String orderQuantity;

    /**
     * (�����P���敪)<BR>
     * <BR>
     * 0:���s�@@1:�w�l<BR>
     */
    public String orderPriceDiv;

    /**
     * (�����P��)<BR>
     * <BR>
     * �����P���敪���u�w�l�v�̏ꍇ�ɐݒ�<BR>
     */
    public String limitPrice;
    
    /**
     * (�l�i����)<BR>
     * <BR>
     * 0:�w��Ȃ��@@1:���ݒl�w�l�@@3:�D��w�l�@@5:���s�c���w�l�@@7:���s�c�����<BR>
     */
    public String priceCondType;

    /**
     * (���s����)<BR>
     * <BR>
     * 1�F������ 3:��t�@@4:�����@@7:�s�o���������s<BR>
     */
    public String execCondType;

    /**
     * (���������敪)<BR>
     * <BR>
     * 1�F��������@@2�F�o����܂Œ���<BR>
     */
    public String expirationDateType;

    /**
     * (�����L������)<BR>
     * <BR>
     * ���������敪���u�o����܂Œ����v�̏ꍇ�ɐݒ�<BR>
     */
    public Date expirationDate;

    /**
     * (���������敪)<BR>
     * <BR>
     * 0�F�w��Ȃ��@@1�F�t�w�l�@@2�FW�w�l<BR>
     */
    public String orderCondType;

    /**
     * (�t�w�l�p���������P��)<BR>
     * <BR>
     * ���������敪���u1�F�t�w�l�v�̏ꍇ�ݒ肳���<BR>
     */
    public String stopOrderCondPrice;

    /**
     * (�t�w�l�p�����������Z�q)<BR>
     * <BR>
     * ���������敪���u1�F�t�w�l�v�̏ꍇ�ݒ肳���<BR>
     * 1�F�ȏ�@@2�F�ȉ�<BR>
     */
    public String stopOrderCondOperator;

    /**
     * (�v�w�l�p���������P��)<BR>
     * <BR>
     * ���������敪���u2�FW�w�l�v�̏ꍇ�ݒ肳���<BR>
     */
    public String wlimitOrderCondPrice;

    /**
     * (�v�w�l�p�����������Z�q)<BR>
     * <BR>
     * ���������敪���u2�FW�w�l�v�̏ꍇ�ݒ肳���<BR>
     * 1�F�ȏ�@@2�F�ȉ�<BR>
     */
    public String wlimitOrderCondOperator;

    /**
     * (�v�w�l�p�����P���敪)<BR>
     * <BR>
     * 0�F���s�@@1�F�w�l<BR>
     * ���������敪���A�u2�FW�w�l�v�̏ꍇ�ݒ肳���<BR>
     * ���ɁA�ǂ�����I������Ȃ������ꍇ�Anull�Ƃ����B<BR>
     */
    public String wLimitOrderPriceDiv;

    /**
     * (W�w�l�p�����P��)<BR>
     * <BR>
     * �v�w�l�p�����P���敪���A�u1�F�w�l�v�̏ꍇ�ݒ肳���<BR>
     */
    public String wLimitPrice;
    
    /**
     * (�v�w�l�p���s����)<BR>
     * 1�F������ 3�F��t 4�F���� 7�F�s�o���������s<BR>
     * ���������敪���A�u2�FW�w�l�v�̏ꍇ�ݒ肳���B<BR>
     */
    public String wlimitExecCondType;
    
    /**
     * (�v�w�l�p�L����ԋ敪)<BR>
     * 0�F���~�b�g�����L���@@1�F�X�g�b�v�����L��<BR>
     * 2�F�X�g�b�v����������<BR>
     * ���������敪���A�u2�FW�w�l�v�̏ꍇ�ݒ肳���B<BR>
     * ���������̂݃Z�b�g�B<BR>
     */
    public String wlimitEnableStatusDiv;

    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3EquityCommonRequest.class);

    /**
     * �R���X�g���N�^<BR>
     * @@roseuid 40611B320200
     */
    public WEB3EquityCommonRequest()
    {

    }

    /**
     * �ivalidate�j<BR>
     * <BR>
     * �����N�G�X�g�f�[�^�̐������`�F�b�N���s���B<BR>
     * �i�������A���N���X���Ŋ�������ȈՃ`�F�b�N�݂̂Ƃ���B�j<BR>
     * <BR>
     * �P�j�@@���������̃`�F�b�N<BR>
     * �@@this�D���������̒l���ȉ��̂����ꂩ�ɊY������ꍇ�́A<BR>
     * �@@��O���X���[����B<BR>
     * <BR>
     * �@@  �Enull<BR>
     * �@@�@@�@@class : WEB3BusinessLayerException<BR>
     * �@@�@@�@@tag   : BUSINESS_ERROR_00126<BR>
     * �@@�@@�E�����ȊO<BR>
     * �@@�@@�@@class : WEB3BusinessLayerException<BR>
     * �@@�@@�@@tag   : BUSINESS_ERROR_00901<BR>
     * �@@�@@�E�O�ȉ��̐���<BR>
     * �@@�@@�@@class : WEB3BusinessLayerException<BR>
     * �@@�@@�@@tag   : BUSINESS_ERROR_00902<BR>
     * �@@�@@�E�W���𒴂��鐔��<BR>
     * �@@�@@�@@class : WEB3BusinessLayerException<BR>
     * �@@�@@�@@tag   : BUSINESS_ERROR_00903<BR>
     * <BR>
     * �Q�j�@@�����P���敪�`�F�b�N<BR>
     * �@@�Q�|�P�jthis.�����P���敪��null�̏ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�u�����P���敪��null�v�̗�O���X���[����B<BR>
     * �@@�@@�@@class : WEB3BusinessLayerException<BR>
     * �@@�@@�@@tag   : BUSINESS_ERROR_00184<BR>
     * <BR>
     * �@@�Q�|�Q�jthis.�����P���敪��null�A<BR>
     * �@@�@@�@@�@@�@@�����L�̒l�ȊO�̏ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�u�����P���敪������`�̒l�v�̗�O���X���[����B<BR>
     * �@@�@@�@@�@@�E0�F���s<BR>
     * �@@�@@�@@�@@�E1�F�w�l<BR>
     * �@@�@@�@@class : WEB3BusinessLayerException<BR>
     * �@@�@@�@@tag   : BUSINESS_ERROR_00185<BR>
     * <BR>
     * �R�j�@@�l�i�����`�F�b�N<BR>
     * �@@�R�|�P�jthis.�l�i������null�̏ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�u�l�i������null�v�̗�O���X���[����B<BR>
     * �@@�@@�@@class : WEB3BusinessLayerException<BR>
     * �@@�@@�@@tag   : BUSINESS_ERROR_01343<BR>
     * <BR>
     * �@@�R�|�Q�jthis.�l�i������null�A<BR>
     * �@@�@@�@@�@@�@@�����L�̒l�ȊO�̏ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�u�l�i����������`�̒l�v�̗�O���X���[����B<BR>
     * �@@�@@�@@�@@�E0�F�w��Ȃ�<BR>
     * �@@�@@�@@�@@�E1�F���ݒl�w�l����<BR>
     * �@@�@@�@@�@@�E3�F�D��w�l����<BR>
     * �@@�@@�@@�@@�E5�F���s�c���w�l����<BR>
     * �@@�@@�@@�@@�E7�F���s�c���������<BR>
     * �@@�@@�@@class : WEB3BusinessLayerException<BR>
     * �@@�@@�@@tag   : BUSINESS_ERROR_01344<BR>
     * <BR>
     * �S�j�@@���s�����`�F�b�N<BR>
     * �@@�S�|�P�jthis.���s������null�̏ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�u���s������null�v�̗�O���X���[����B<BR>
     * �@@�@@�@@class : WEB3BusinessLayerException<BR>
     * �@@�@@�@@tag   : BUSINESS_ERROR_00197<BR>
     * <BR>
     * �@@�S�|�Q�jthis.���s������null�A<BR>
     * �@@�@@�@@�@@�@@�����L�̒l�ȊO�̏ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�u���s����������`�̒l�v�̗�O���X���[����B<BR>
     * �@@�@@�@@�@@�E1�F������<BR>
     * �@@�@@�@@�@@�E3�F��t<BR>
     * �@@�@@�@@�@@�E4�F����<BR>
     * �@@�@@�@@�@@�E7�F�s�o���������s<BR>
     * �@@�@@�@@class : WEB3BusinessLayerException<BR>
     * �@@�@@�@@tag   : BUSINESS_ERROR_00150<BR>
     * <BR>
     * �T�j�@@���������敪�`�F�b�N<BR>
     * �@@�T�|�P�jthis.���������敪��null�̏ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�u���������敪��null�v�̗�O���X���[����B<BR>
     * �@@�@@�@@class : WEB3BusinessLayerException<BR>
     * �@@�@@�@@tag   : BUSINESS_ERROR_00208<BR>
     * <BR>
     * �@@�T�|�Q�jthis.���������敪��null�A<BR>
     * �@@�@@�@@�@@�@@�����L�̒l�ȊO�̏ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�u���������敪������`�̒l�v�̗�O���X���[����B<BR>
     * �@@�@@�@@�@@�E1�F��������<BR>
     * �@@�@@�@@�@@�E2�F�o����܂Œ���<BR>
     * �@@�@@�@@class : WEB3BusinessLayerException<BR>
     * �@@�@@�@@tag   : BUSINESS_ERROR_00209<BR>
     * <BR>
     * �U�j�@@���������敪�`�F�b�N<BR>
     * �@@�U�|�P�jthis.���������敪��null�̏ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�u���������敪��null�v�̗�O���X���[����B<BR>
     * �@@�@@�@@class : WEB3BusinessLayerException<BR>
     * �@@�@@�@@tag   : BUSINESS_ERROR_00211<BR>
     * <BR>
     * �@@�U�|�Q�jthis.���������敪��null�A<BR>
     * �@@�@@�@@�@@�@@�����L�̒l�ȊO�̏ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�u���������敪������`�̒l�v�̗�O���X���[����B<BR>
     * �@@�@@�@@�@@�E0�F�w��Ȃ�<BR>
     * �@@�@@�@@�@@�E1�F�t�w�l<BR>
     * �@@�@@�@@�@@�E2�FW�w�l<BR>
     * �@@�@@�@@class : WEB3BusinessLayerException<BR>
     * �@@�@@�@@tag   : BUSINESS_ERROR_00212<BR>
     * <BR>
     * �V�j�@@�����P���E���s�����̃`�F�b�N<BR>
     * �@@this�D���s�������h7�F�s�o���������s�h���A<BR>
     * �@@this�D�l�i�������i�h1�F���ݒl�w�l�h�A�h3�F�D��w�l�h�j���A<BR>
     * �@@this�D�����P���敪���h1�F�w�l�h�ł���Η�O���X���[����B<BR>
     * �@@���h1�F���ݒl�w�l�h�A�h3�F�D��w�l�h�����̏ꍇ�A�s�ꓞ����ɂ����ɒl���t���̂ŁA<BR>
     * �@@���s�o���������s�ł����Ă��h0�F���s�h�w��Ƃ���B<BR>
     * �@@�@@�@@class : WEB3BusinessLayerException<BR>
     * �@@�@@�@@tag   : BUSINESS_ERROR_00114<BR>
     * <BR>
     * �W�j�@@�����P���敪�E�P�� �̐������`�F�b�N<BR>
     * �@@�W�|�P)�@@this�D�����P���敪���h1�F�w�l�h���A<BR>
     * �@@�@@this�D�����P���̒l���ȉ��̂����ꂩ�ɊY������ꍇ�́A<BR>
     * �@@�@@��O���X���[����<BR>
     * <BR>
     * �@@�@@  �Enull<BR>
     * �@@�@@�@@class : WEB3BusinessLayerException<BR>
     * �@@�@@�@@tag   : BUSINESS_ERROR_00187<BR>
     * �@@�@@  �E�����ȊO<BR>
     * �@@�@@�@@class : WEB3BusinessLayerException<BR>
     * �@@�@@�@@tag   : BUSINESS_ERROR_00188<BR>
     * �@@�@@  �E�O�ȉ��̐���<BR>
     * �@@�@@�@@class : WEB3BusinessLayerException<BR>
     * �@@�@@�@@tag   : BUSINESS_ERROR_00189<BR>
     * �@@  �@@�E�W���𒴂��鐔��<BR>
     * �@@�@@�@@class : WEB3BusinessLayerException<BR>
     * �@@�@@�@@tag   : BUSINESS_ERROR_00190<BR>
     * <BR>
     * �@@�W�|�Q)�@@this�D�����P���敪���h0�F���s�h���A<BR>
     * �@@�@@this�D�����P�����inull�܂��́h�O�h�j�ł���Η�O���X���[����B<BR>
     * �@@�@@�@@class : WEB3BusinessLayerException<BR>
     * �@@�@@�@@tag   : BUSINESS_ERROR_00116<BR>
     * <BR>
     * �X�j�@@�����̃`�F�b�N<BR>
     * �@@�X�|�P)�@@�@@�@@this�D���������敪���h1�F��������h���A<BR>
     * �@@this�D�����L��������null�ł���Η�O���X���[����B<BR>
     * �@@�@@�@@class : WEB3BusinessLayerException<BR>
     * �@@�@@�@@tag   : BUSINESS_ERROR_00117<BR>
     * �@@�X�|�Q)�@@�@@this�D���������敪���h2�F�o����܂Œ����h���A<BR> 
     * �@@this�D�����L��������null�ł���Η�O���X���[����B <BR>
     * �@@�@@�@@class : WEB3BusinessLayerException<BR>
     * �@@�@@�@@tag   : BUSINESS_ERROR_00210<BR>
     * <BR>
     * �P�O�j�@@���������̃`�F�b�N�P�i�h0�F�w��Ȃ��h�j<BR>
     * �@@this�D���������敪���h0�F�w��Ȃ��h���A<BR>
     *   �ȉ��̂����ꂩ�ɊY������ꍇ�͗�O���X���[����B<BR>
     * <BR>
     * �@@�@@�Ethis�D�t�w�l�p���������P�����inull�܂��́h�O�h�j <BR>
     * �@@�@@�@@class : WEB3BusinessLayerException<BR>
     * �@@�@@�@@tag   : BUSINESS_ERROR_01872<BR>
     * �@@�@@�Ethis�D�t�w�l�p�����������Z�q��null<BR>
     * �@@�@@�@@class : WEB3BusinessLayerException<BR>
     * �@@�@@�@@tag   : BUSINESS_ERROR_01873<BR>
     * �@@�@@�Ethis�DW�w�l�p���������P�����inull�܂��́h�O�h�j<BR>
     * �@@�@@�@@class : WEB3BusinessLayerException<BR>
     * �@@�@@�@@tag   : BUSINESS_ERROR_01874<BR>
     * �@@�@@�Ethis�DW�w�l�p�����������Z�q��null<BR>
     * �@@�@@�@@class : WEB3BusinessLayerException<BR>
     * �@@�@@�@@tag   : BUSINESS_ERROR_01875<BR>
     * �@@�@@�Ethis�DW�w�l�p�����P���敪��null<BR>
     * �@@�@@�@@class : WEB3BusinessLayerException<BR>
     * �@@�@@�@@tag   : BUSINESS_ERROR_01876<BR>
     * �@@�@@�Ethis�DW�w�l�p�����P�����inull�܂��́h�O�h�j<BR>
     * �@@�@@�@@class : WEB3BusinessLayerException<BR>
     * �@@�@@�@@tag   : BUSINESS_ERROR_01877<BR>
     * �@@�@@�Ethis�D�v�w�l�p���s������null<BR>
     * �@@�@@�@@class : WEB3BusinessLayerException<BR>
     * �@@�@@�@@tag   : BUSINESS_ERROR_02525<BR> 
     * <BR>
     * �P�P�j�@@���������̃`�F�b�N�Q�i�h1�F�t�w�l�h�j<BR>
     * �@@�P�P�|�P)�@@this�D���������敪���h1�F�t�w�l�h���A<BR>
     * �@@�@@�ȉ��̂����ꂩ�ɊY������ꍇ�́A��O���X���[����B<BR>
     * <BR>
     * �@@�@@�Ethis�DW�w�l�p���������P�����inull�܂��́h�O�h�j<BR>
     * �@@�@@�@@class : WEB3BusinessLayerException<BR>
     * �@@�@@�@@tag   : BUSINESS_ERROR_01878<BR>
     * �@@�@@�Ethis�DW�w�l�p�����������Z�q��null<BR>
     * �@@�@@�@@class : WEB3BusinessLayerException<BR>
     * �@@�@@�@@tag   : BUSINESS_ERROR_01879<BR>
     * �@@�@@�Ethis�DW�w�l�p�����P���敪��null<BR>
     * �@@�@@�@@class : WEB3BusinessLayerException<BR>
     * �@@�@@�@@tag   : BUSINESS_ERROR_01880<BR>
     * �@@�@@�Ethis�DW�w�l�p�����P�����inull�܂��́h�O�h�j<BR>
     * �@@�@@�@@class : WEB3BusinessLayerException<BR>
     * �@@�@@�@@tag   : BUSINESS_ERROR_01881<BR>
     * �@@�@@�Ethis�D�v�w�l�p���s������null <BR>
     * �@@�@@�@@class : WEB3BusinessLayerException<BR>
     * �@@�@@�@@tag   : BUSINESS_ERROR_02526<BR>
     * <BR>
     * �@@�P�P�|�Q)�@@this�D���������敪���h1�F�t�w�l�h���A<BR>
     * �@@�@@�ȉ��̂����ꂩ�ɊY������ꍇ�́A��O���X���[����B<BR>
     * <BR>
     * �@@�@@�Ethis�D�t�w�l�p���������P���̒l�����L�̂����ꂩ��<BR>
     * �@@�@@ �Y������<BR>
     * �@@�@@�@@�Enull<BR>
     * �@@�@@�@@class : WEB3BusinessLayerException<BR>
     * �@@�@@�@@tag   : BUSINESS_ERROR_00215<BR>
     * �@@�@@  �E�����ȊO<BR>
     * �@@�@@�@@class : WEB3BusinessLayerException<BR>
     * �@@�@@�@@tag   : BUSINESS_ERROR_00216<BR>
     * �@@�@@  �E�O�ȉ��̐���<BR>
     * �@@�@@�@@class : WEB3BusinessLayerException<BR>
     * �@@�@@�@@tag   : BUSINESS_ERROR_00217<BR>
     * �@@  �@@�E�W���𒴂��鐔��<BR>
     * �@@�@@�@@class : WEB3BusinessLayerException<BR>
     * �@@�@@�@@tag   : BUSINESS_ERROR_00218<BR>
     * <BR>
     * �@@�@@�Ethis�D�t�w�l�p�����������Z�q��null<BR>
     * �@@�@@�@@class : WEB3BusinessLayerException<BR>
     * �@@�@@�@@tag   : BUSINESS_ERROR_00219<BR>
     * �@@�@@�Ethis�D�t�w�l�p�����������Z�q��null<BR>
     * �@@�@@�@@�����L�̒l�ȊO�̏ꍇ�A<BR>
     * �@@�@@�@@class : WEB3BusinessLayerException<BR>
     * �@@�@@�@@tag   : BUSINESS_ERROR_00220<BR>
     * <BR>
     * �P�Q�j�@@���������̃`�F�b�N�R�i�h2�FW�w�l�h�j<BR>
     * �@@�P�Q�|�P)�@@this�D���������敪���h2�FW�w�l�h���A<BR>
     * �@@�@@�ȉ��̂����ꂩ�ɊY������ꍇ�́A��O���X���[����B<BR>
     * <BR>
     * �@@�@@�Ethis�D�t�w�l�p���������P�����inull�܂��́h�O�h�j<BR>
     * �@@�@@�@@class : WEB3BusinessLayerException<BR>
     * �@@�@@�@@tag   : BUSINESS_ERROR_01882<BR>
     * �@@�@@�Ethis�D�t�w�l�p�����������Z�q��null<BR>
     * �@@�@@�@@class : WEB3BusinessLayerException<BR>
     * �@@�@@�@@tag   : BUSINESS_ERROR_01883<BR>
     * <BR>
     * �@@�P�Q�|�Q)�@@this�D���������敪���h2�FW�w�l�h���A<BR>
     * �@@�@@�ȉ��̂����ꂩ�ɊY������ꍇ�́A��O���X���[����B<BR>
     * <BR>
     * �@@�@@�Ethis�DW�w�l�p�����������Z�q��null<BR>
     * �@@�@@�@@class : WEB3BusinessLayerException<BR>
     * �@@�@@�@@tag   : BUSINESS_ERROR_00227<BR>
     * �@@�@@�Ethis�DW�w�l�p�����������Z�q��null<BR>
     * �@@�@@�@@�����L�̒l�ȊO�̏ꍇ�A<BR>
     * �@@�@@�@@�@@1�F�ȏ�<BR>
     * �@@�@@�@@�@@2�F�ȉ�<BR>
     * �@@�@@�@@class : WEB3BusinessLayerException<BR>
     * �@@�@@�@@tag   : BUSINESS_ERROR_00228<BR>
     * �@@�@@�Ethis�DW�w�l�p�����P���敪��null<BR>
     * �@@�@@�@@class : WEB3BusinessLayerException<BR>
     * �@@�@@�@@tag   : BUSINESS_ERROR_01358<BR>
     * �@@�@@�Ethis�DW�w�l�p�����P���敪��null<BR>
     * �@@�@@�@@�����L�̒l�ȊO�̏ꍇ�A<BR>
     * �@@�@@�@@�@@�E0�F���s<BR>
     * �@@�@@�@@�@@�E1�F�w�l<BR>
     * �@@�@@�@@class : WEB3BusinessLayerException<BR>
     * �@@�@@�@@tag   : BUSINESS_ERROR_01359<BR>
     * �@@�@@�Ethis�DW�w�l�p���������P���̒l���ȉ��̂����ꂩ<BR>
     * �@@�@@�@@�@@�@@null<BR>
     * �@@�@@�@@class : WEB3BusinessLayerException<BR>
     * �@@�@@�@@tag   : BUSINESS_ERROR_00223<BR>
     * �@@�@@�@@�@@�@@�����ȊO<BR>
     * �@@�@@�@@class : WEB3BusinessLayerException<BR>
     * �@@�@@�@@tag   : BUSINESS_ERROR_01361<BR>
     *  �@@�@@�@@�@@�O�ȉ��̐���<BR>
     * �@@�@@�@@class : WEB3BusinessLayerException<BR>
     * �@@�@@�@@tag   : BUSINESS_ERROR_01360<BR>
     * �@@�@@�@@�@@�@@�W���𒴂��鐔��<BR>
     * �@@�@@�@@class : WEB3BusinessLayerException<BR>
     * �@@�@@�@@tag   : BUSINESS_ERROR_01362<BR>
     * �@@�@@�Ethis�D�v�w�l�p���s������null <BR>
     * �@@�@@�@@class : WEB3BusinessLayerException<BR>
     * �@@�@@�@@tag   : BUSINESS_ERROR_02499<BR>
     * �@@�@@�Ethis�D�v�w�l�p���s������null <BR>
     * �@@�@@�@@�����L�̒l�ȊO�̏ꍇ�A<BR>
     * �@@�@@�@@�@@�@@1�F������<BR>
     * �@@�@@�@@�@@�@@7�F�s�o���������s<BR>
     * �@@�@@�@@class : WEB3BusinessLayerException<BR>
     * �@@�@@�@@tag   : BUSINESS_ERROR_02500<BR>
     * <BR>
     * �P�R�j�@@W�w�l�p�����P���敪�EW�w�l�p�����P�� �̐������`�F�b�N<BR>
     * �@@�P�R�|�P)�@@this�DW�w�l�p�����P���敪���h1�F�w�l�h���A<BR>
     * �@@�@@this�DW�w�l�p�����P���̒l���ȉ��̂����ꂩ�ɊY������<BR>
     * �@@ �ꍇ�́A��O���X���[����B<BR>
     * <BR>
     * �@@�@@�Enull
     * �@@�@@�@@class : WEB3BusinessLayerException<BR>
     * �@@�@@�@@tag   : BUSINESS_ERROR_00313<BR>
     * �@@�@@�E�����ȊO<BR>
     * �@@�@@�@@class : WEB3BusinessLayerException<BR>
     * �@@�@@�@@tag   : BUSINESS_ERROR_00314<BR>
     * �@@�@@�E�O�ȉ��̐���<BR>
     * �@@�@@�@@class : WEB3BusinessLayerException<BR>
     * �@@�@@�@@tag   : BUSINESS_ERROR_00315<BR>
     * �@@�@@�E�W���𒴂��鐔��<BR>
     * �@@�@@�@@class : WEB3BusinessLayerException<BR>
     * �@@�@@�@@tag   : BUSINESS_ERROR_00316<BR>
     * <BR>
     * �@@�P�R�|�Q)�@@this�DW�w�l�p�����P���敪���h0�F���s�h���A<BR>
     * �@@�@@this�DW�w�l�p�����P�����inull�܂��́h�O�h�j�ł���΁A<BR>
     * �@@�@@��O���X���[����B<BR>
     * �@@�@@�@@class : WEB3BusinessLayerException<BR>
     * �@@�@@�@@tag   : BUSINESS_ERROR_00124<BR>
     * <BR>
     * �P�S�j�@@���������E���s�����̃`�F�b�N<BR>
     * �@@this�D���������敪���h2�F�o����܂Œ����h�̏ꍇ�A<BR>
     * �@@this�D���s�������u1�F�������v�ł���Η�O���X���[����B<BR>
     * �@@�@@�@@class : WEB3BusinessLayerException<BR>
     * �@@�@@�@@tag   : BUSINESS_ERROR_00125<BR>
     * <BR>
     * �P�T�j�@@�l�i�����E�����P���敪�̃`�F�b�N<BR>
     * �@@this.�l�i������"0�F�w��Ȃ�"�̏ꍇ�A<BR>
     * �@@this.�����P���敪���u0�F���s�v�ł���Η�O���X���[����B<BR>
     * �@@�@@�@@class : WEB3BusinessLayerException<BR>
     * �@@�@@�@@tag   : BUSINESS_ERROR_01348<BR>
     * <BR>
     * �P�U�j�@@�l�i�����E���s�����̃`�F�b�N<BR>
     * �@@this.�l�i�������i"5�F���s�c���w�l����"�A"7�F���s�c���������"�j�̂����ꂩ�̏ꍇ�A<BR>
     * �@@this.���s�������u1�F�������v�ł���Η�O���X���[����B<BR>
     * �@@�@@�@@class : WEB3BusinessLayerException<BR>
     * �@@�@@�@@tag   : BUSINESS_ERROR_01349<BR>
     * <BR>
     * �P�V�j�@@�l�i�����E���������敪�̃`�F�b�N<BR>
     * �@@this.�l�i������"0�F�w��Ȃ�"�̏ꍇ�A<BR>
     * �@@this.���������敪���u1�F��������v�ł���Η�O���X���[����B<BR>
     * �@@�@@�@@class : WEB3BusinessLayerException<BR>
     * �@@�@@�@@tag   : BUSINESS_ERROR_01350<BR>
     * <BR>
     * �P�W�j�@@�l�i�����E���������敪�̃`�F�b�N<BR>
     * �@@this.�l�i������"0�F�w��Ȃ�"�̏ꍇ�A<BR>
     * �@@this.���������敪���u0�F�w��Ȃ��v�ł���Η�O���X���[����B<BR>
     * �@@�@@�@@class : WEB3BusinessLayerException<BR>
     * �@@�@@�@@tag   : BUSINESS_ERROR_01351<BR>
     * <BR>
     * �P�X�j�@@���������E���s�����̃`�F�b�N <BR>
     * �@@�P�X�|�P�j�@@this.����������"1�F�t�w�l"�̏ꍇ�A <BR>
     * �@@�@@this.���s�������u1�F�������v�ł���Η�O���X���[����B <BR>
     *  �@@�@@�@@class : WEB3BusinessLayerException<BR>
     *  �@@�@@�@@tag   : BUSINESS_ERROR_02473<BR>
     * <BR>
     * �@@�P�X�|�Q�j�@@this.����������"2�FW�w�l"�̏ꍇ�A <BR>
     * �@@�@@this.���s�������ȉ��̒l�ȊO�̏ꍇ�A��O���X���[����B <BR>
     * <BR>
     * �@@�@@�@@�E1�F������ <BR>
     * �@@�@@�@@�E7�F�s�o���������s<BR>
     *  �@@�@@�@@class : WEB3BusinessLayerException<BR>
     *  �@@�@@�@@tag   : BUSINESS_ERROR_02500<BR>
     * <BR>
     * �Q�O�j�@@�v�w�l�p���s�����E�����P���敪�`�F�b�N <BR>
     * �@@�@@�@@�@@this.�v�w�l�p���s�������h7:�s�o���������s�h�ł��A <BR>
     * �@@�@@�@@�@@this.�v�w�l�p�����P���敪���h1�F�w�l�h�̏ꍇ�A��O���X���[����B<BR>
     *  �@@�@@�@@class : WEB3BusinessLayerException<BR>
     *  �@@�@@�@@tag   : BUSINESS_ERROR_02501<BR>
     * <BR>
     * �Q�P�j�@@�v�w�l�p�L����ԋ敪�`�F�b�N <BR>
     * �@@�@@�@@�@@this.�v�w�l�p�L����ԋ敪��null�ł���΁A <BR>
     * �@@�@@�@@�@@�ȉ��̏������s���B <BR>
     * �@@�Q�P�|�P�j�@@this.�v�w�l�L����ԋ敪���ȉ��̒l�ȊO�̏ꍇ�A <BR>
     * �@@�@@�@@�@@��O���X���[����B <BR>
     * <BR>
     * �@@�@@�@@�@@�@@�E0�F���~�b�g�����L�� <BR>
     * �@@�@@�@@�@@�@@�E1�F�X�g�b�v�����L�� <BR>
     * �@@�@@�@@�@@�@@�E2�F�X�g�b�v���������� <BR>
     *  �@@�@@�@@class : WEB3BusinessLayerException<BR>
     *  �@@�@@�@@tag   : BUSINESS_ERROR_02502<BR>
     * <BR>
     * �Q�Q�j�@@�v�w�l�p���s�����E�����L�������`�F�b�N <BR>
     * �@@�@@�@@�@@this.���������敪���h2�FW�w�l�h�ł��A <BR>
     * �@@�@@�@@�@@this.���������敪���h2�F�o����܂Œ����h�ł��A <BR>
     * �@@�@@�@@�@@this.�v�w�l�p���s�������h1�F�������h�̏ꍇ�A <BR>
     * �@@�@@�@@�@@��O���X���[����B<BR>
     *  �@@�@@�@@class : WEB3BusinessLayerException<BR>
     *  �@@�@@�@@tag   : BUSINESS_ERROR_02503<BR>
     * @@throws webbroker3.common.WEB3BusinessLayerException
     * @@roseuid 40600E47029F
     */
    public void validate() throws WEB3BusinessLayerException
    {
        final String STR_METHOD_NAME ="validate()";

        log.entering(STR_METHOD_NAME);

        // �P�j�@@���������̃`�F�b�N
        if(this.orderQuantity == null)
        {
            // ����������null�̏ꍇ
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00126,
                this.getClass().getName() + ".validate()");
        }

        try
        {
            int l_intOrderQuantity = Integer.parseInt(this.orderQuantity);
            if(l_intOrderQuantity <= 0)
            {
                // �����������O�ȉ��̏ꍇ
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00902,
                    this.getClass().getName() + ".validate()");
            }
            
            if(99999999 < l_intOrderQuantity)
            {
                // �����������W���𒴂���
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00903,
                    this.getClass().getName() + ".validate()");
            }
            
            
        } catch(NumberFormatException e)
        {
            // �������������l�ȊO�̏ꍇ
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00901,
                this.getClass().getName() + ".validate()");
        }

        // �Q�j�@@�����P���敪�`�F�b�N
        if(this.orderPriceDiv == null)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00184,
                this.getClass().getName() + ".validate()");
        }
        if(!WEB3OrderPriceDivDef.MARKET_PRICE.equals(this.orderPriceDiv)
            && !WEB3OrderPriceDivDef.LIMIT_PRICE.equals(this.orderPriceDiv))
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00185,
                this.getClass().getName() + ".validate()");
        }

        // �R�j�@@�l�i�����`�F�b�N
        if(this.priceCondType == null)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01343,
                this.getClass().getName() + ".validate()");
        }
        if(!(WEB3PriceConditionDef.DEFAULT.equals(this.priceCondType)
            || WEB3PriceConditionDef.PRESENT_VALUE_LIMIT_PRICE_ORDER.equals(this.priceCondType)
            || WEB3PriceConditionDef.PRIORITY_LIMIT_PRICE_ORDER.equals(this.priceCondType)
            || WEB3PriceConditionDef.PARTIALLY_LIMIT_PRICE_ORDER.equals(this.priceCondType)
            || WEB3PriceConditionDef.PARTIALLY_CANCEL_ORDER.equals(this.priceCondType)))
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01344,
                this.getClass().getName() + ".validate()");
        }

        // �S�j�@@���s�����`�F�b�N
        if(this.execCondType == null)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00197,
                this.getClass().getName() + ".validate()");
        }
        if(!(WEB3ExecutionConditionDef.NO_CONDITION.equals(this.execCondType)
            || WEB3ExecutionConditionDef.AT_MARKET_OPEN.equals(this.execCondType)
            || WEB3ExecutionConditionDef.AT_MARKET_CLOSE.equals(this.execCondType)
            || WEB3ExecutionConditionDef.AT_MARKET_CLOSE_NOT_EXECUTED.equals(this.execCondType)))
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00150,
                this.getClass().getName() + ".validate()");
        }

        // �T�j�@@���������敪�`�F�b�N
        if(this.expirationDateType == null)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00208,
                this.getClass().getName() + ".validate()");
        }
        if(!(WEB3OrderExpirationDateTypeDef.DAY_LIMIT.equals(this.expirationDateType)
            || WEB3OrderExpirationDateTypeDef.CARRIED_ORDER.equals(this.expirationDateType)))
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00209,
                this.getClass().getName() + ".validate()");
        }
        
        // �U�j�@@���������敪�`�F�b�N
        if(this.orderCondType == null)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00211,
                this.getClass().getName() + ".validate()");
        }
        if(!(WEB3OrderingConditionDef.DEFAULT.equals(this.orderCondType)
            || WEB3OrderingConditionDef.STOP_LIMIT_PRICE.equals(this.orderCondType)
            || WEB3OrderingConditionDef.W_LIMIT_PRICE.equals(this.orderCondType)))
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00212,
                this.getClass().getName() + ".validate()");
        }

        //  �V�j�@@�����P���E���s�����̃`�F�b�N<BR>
        if (WEB3ExecutionConditionDef.AT_MARKET_CLOSE_NOT_EXECUTED.equals(this.execCondType) &&
            !WEB3PriceConditionDef.PRESENT_VALUE_LIMIT_PRICE_ORDER.equals(this.priceCondType) &&
            !WEB3PriceConditionDef.PRIORITY_LIMIT_PRICE_ORDER.equals(this.priceCondType) &&
            !WEB3OrderPriceDivDef.LIMIT_PRICE.equals(this.orderPriceDiv))
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00114,
                this.getClass().getName() + ".validate()");
        }

        // �W�j�@@�����P���敪�E�P�� �̐������`�F�b�N
        if(WEB3OrderPriceDivDef.LIMIT_PRICE.equals(this.orderPriceDiv))
        {
            if(this.limitPrice == null)
            {
                // �����P����null�̏ꍇ
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00187,
                    this.getClass().getName() + ".validate()");
            }
            
            try
            {
                int l_intLimitPrice = Integer.parseInt(this.limitPrice);
                if(l_intLimitPrice <= 0)
                {
                    // �����P�����O�ȉ��̏ꍇ
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_00189,
                        this.getClass().getName() + ".validate()");
                }
                if(99999999 < l_intLimitPrice)
                {
                    // �����P�����W���𒴂���ꍇ
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_00190,
                        this.getClass().getName() + ".validate()");
                }
            } catch(NumberFormatException e)
            {
                // �����P�������l�ȊO�̏ꍇ
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00188,
                    this.getClass().getName() + ".validate()");
            }
        } else if(WEB3OrderPriceDivDef.MARKET_PRICE.equals(this.orderPriceDiv))
        {
            if(this.limitPrice != null && !this.limitPrice.equals("0"))
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00116,
                    this.getClass().getName() + ".validate()");
            }
        }

        // �X�j�@@�����̃`�F�b�N
        if(WEB3OrderExpirationDateTypeDef.DAY_LIMIT.equals(this.expirationDateType)
            && this.expirationDate != null)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00117,
                this.getClass().getName() + ".validate()");
        }
        
        if(WEB3OrderExpirationDateTypeDef.CARRIED_ORDER.equals(this.expirationDateType)
        	&& this.expirationDate == null)
        {
        	throw new WEB3BusinessLayerException(
        		WEB3ErrorCatalog.BUSINESS_ERROR_00210,
        		this.getClass().getName() + ".validate()");
        }
        

        // �P�O�j�@@���������̃`�F�b�N�P�i�h0�F�w��Ȃ��h�j
        if(WEB3OrderingConditionDef.DEFAULT.equals(this.orderCondType))
        {
            if(!(this.stopOrderCondPrice == null || this.stopOrderCondPrice.equals("0")))
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01872,
                    this.getClass().getName() + ".validate()");
            }
            
            if(this.stopOrderCondOperator != null)
            {
                throw new WEB3BusinessLayerException(
				WEB3ErrorCatalog.BUSINESS_ERROR_01873,
                    this.getClass().getName() + ".validate()");
            }
            
            if(!(this.wlimitOrderCondPrice == null || this.wlimitOrderCondPrice.equals("0")))
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01874,
                    this.getClass().getName() + ".validate()");
            }
            
            if(this.wlimitOrderCondOperator != null)
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01875,
                    this.getClass().getName() + ".validate()");
            }
            
			if(this.wLimitOrderPriceDiv != null)
			{
				throw new WEB3BusinessLayerException(
					WEB3ErrorCatalog.BUSINESS_ERROR_01876,
					this.getClass().getName() + ".validate()");
			}
			
            if(!(this.wLimitPrice == null || this.wLimitPrice.equals("0")))
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01877,
                    this.getClass().getName() + ".validate()");
            }
            
            //�Ethis�D�v�w�l�p���s������null 
            if (this.wlimitExecCondType != null)
            {
                log.debug("���������敪���g�w��Ȃ��h�̏ꍇ�́A�v�w�l�p���s�������w��s�ł��B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02525,
                    this.getClass().getName() + "." +  STR_METHOD_NAME,
                    "���������敪���g�w��Ȃ��h�̏ꍇ�́A�v�w�l�p���s�������w��s�ł��B");
            }
        }

        // �P�P�j�@@���������̃`�F�b�N�Q�i�h1�F�t�w�l�h�j
        if(WEB3OrderingConditionDef.STOP_LIMIT_PRICE.equals(this.orderCondType))
        {
            if(!(this.wlimitOrderCondPrice == null || this.wlimitOrderCondPrice.equals("0")))
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01878,
                    this.getClass().getName() + ".validate()");
            }
            
            if(this.wlimitOrderCondOperator != null)
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01879,
                    this.getClass().getName() + ".validate()");
            }
            
			if(this.wLimitOrderPriceDiv != null)
			{
				throw new WEB3BusinessLayerException(
					WEB3ErrorCatalog.BUSINESS_ERROR_01880,
					this.getClass().getName() + ".validate()");
			}
			
            if(!(this.wLimitPrice == null || this.wLimitPrice.equals("0")))
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01881,
                    this.getClass().getName() + ".validate()");
            }
            
            //�Ethis�D�v�w�l�p���s������null 
            if (this.wlimitExecCondType != null)
            {
                log.debug("���������敪���g�t�w�l�h�̏ꍇ�́A�v�w�l�p���s�������w��s�ł��B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02526,
                    this.getClass().getName() + "." +  STR_METHOD_NAME,
                    "���������敪���g�t�w�l�h�̏ꍇ�́A�v�w�l�p���s�������w��s�ł��B");
            }
            
            if(this.stopOrderCondPrice == null) {
                // �t�w�l�p���������P����null�̏ꍇ
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00215,
                    this.getClass().getName() + ".validate()");
            }
            try
            {
                int l_intStopOrderCondPrice = Integer.parseInt(this.stopOrderCondPrice);
                
                if(l_intStopOrderCondPrice <= 0)
                {
                    // �t�w�l�p���������P�����O�ȉ��̏ꍇ
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_00216,
                        this.getClass().getName() + ".validate()");
                }
                
                if(99999999 < l_intStopOrderCondPrice)
                {
                    // �t�w�l�p���������P�����W���𒴂���ꍇ
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_00216,
                        this.getClass().getName() + ".validate()");
                }
                
            } catch(NumberFormatException e)
            {
                // �t�w�l�p���������P�������l�ȊO�̏ꍇ
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00216,
                    this.getClass().getName() + ".validate()");
            }
            
            if(this.stopOrderCondOperator == null)
            {
                // �t�w�l�p�����������Z�q��null�̏ꍇ
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00219,
                    this.getClass().getName() + ".validate()");
            }
            
            if(!WEB3OrderConditionOperatorDef.ABOVE_BASE_PRICE.equals(this.stopOrderCondOperator)
                && !WEB3OrderConditionOperatorDef.BELOW_BASE_PRICE.equals(this.stopOrderCondOperator))
            {
                // �t�w�l�p�����������Z�q����`�l�ȊO�̏ꍇ
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00220,
                    this.getClass().getName() + ".validate()");
            }
            
        }

        // �P�Q�j�@@���������̃`�F�b�N�R�i�h2�FW�w�l�h�j
        if(WEB3OrderingConditionDef.W_LIMIT_PRICE.equals(this.orderCondType))
        {
            if(!(this.stopOrderCondPrice == null || this.stopOrderCondPrice.equals("0")))
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01882,
                    this.getClass().getName() + ".validate()");
            }
            
            if(this.stopOrderCondOperator != null)
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01883,
                    this.getClass().getName() + ".validate()");
            }
            
            if(this.wlimitOrderCondOperator == null)
            {
                // W�w�l�p�����������Z�q��null�̏ꍇ
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00227,
                    this.getClass().getName() + ".validate()");
            }
            
            if(!WEB3OrderConditionOperatorDef.ABOVE_BASE_PRICE.equals(this.wlimitOrderCondOperator)
                && !WEB3OrderConditionOperatorDef.BELOW_BASE_PRICE.equals(this.wlimitOrderCondOperator))
            {
                // W�w�l�p�����������Z�q����`�l�ȊO�̏ꍇ
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00228,
                    this.getClass().getName() + ".validate()");
            }
            
            if(this.wLimitOrderPriceDiv == null)
            {
                // W�w�l�p�����P���敪��null�̏ꍇ
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01358,
                    this.getClass().getName() + ".validate()");
            }
            
            if(!WEB3OrderPriceDivDef.LIMIT_PRICE.equals(this.wLimitOrderPriceDiv)
                && !WEB3OrderPriceDivDef.MARKET_PRICE.equals(this.wLimitOrderPriceDiv))
            {
                // W�w�l�p�����P���敪����`�l�ȊO�̏ꍇ
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01359,
                    this.getClass().getName() + ".validate()");
            }
            
            if(this.wlimitOrderCondPrice == null)
            {
                // W�w�l�p���������P����null�̏ꍇ
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00223,
                    this.getClass().getName() + ".validate()");
            }
            
            try
            {
                int l_intWlimitOrderCondPrice = Integer.parseInt(this.wlimitOrderCondPrice);
                if(l_intWlimitOrderCondPrice <= 0)
                {
                    // W�w�l�p���������P�����O�ȉ��̏ꍇ
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_01360,
                        this.getClass().getName() + ".validate()");
                }
                
                if(99999999 < l_intWlimitOrderCondPrice)
                {
                    // W�w�l�p���������P����8���𒴂����ꍇ
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_01361,
                        this.getClass().getName() + ".validate()");
                }
            } catch(NumberFormatException e)
            {
                // W�w�l�p���������P���������ȊO�̏ꍇ
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01362,
                    this.getClass().getName() + ".validate()");
            }
            
            //�Ethis�D�v�w�l�p���s������null
            if (this.wlimitExecCondType == null)
            {
                log.debug("�v�w�l�p���s���������w��ł��B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02499,
                    this.getClass().getName() + "." +  STR_METHOD_NAME,
                    "�v�w�l�p���s���������w��ł��B");
            }
            
            //�@@�@@�Ethis�D�v�w�l�p���s������null 
            //�@@�@@�@@�����L�̒l�ȊO�̏ꍇ�A 
            //�@@�@@�@@�@@�@@1�F������ 
            //�@@�@@�@@�@@�@@7�F�s�o���������s 
            else if (!(WEB3ExecutionConditionDef.NO_CONDITION.equals(this.wlimitExecCondType)
                || WEB3ExecutionConditionDef.AT_MARKET_CLOSE_NOT_EXECUTED.equals(this.wlimitExecCondType)))
            {
                log.debug("W�w�l�����̎��s�����́g�������h�A�g�s�o���������s�h�ȊO�w��s�B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02500,
                    this.getClass().getName() + "." +  STR_METHOD_NAME,
                    "W�w�l�����̎��s�����́g�������h�A�g�s�o���������s�h�ȊO�w��s�B");
            }
        }

        // �P�R�j�@@W�w�l�p�����P���敪�EW�w�l�p�����P�� �̐������`�F�b�N
        if(WEB3OrderPriceDivDef.LIMIT_PRICE.equals(this.wLimitOrderPriceDiv))
        {
            if(this.wLimitPrice == null)
            {
                // W�w�l�p�����P����Null�̏ꍇ
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00313,
                    this.getClass().getName() + ".validate()");
            }
            
            try
            {
                int l_intWlimitPrice = Integer.parseInt(this.wLimitPrice);
                
                if(l_intWlimitPrice <= 0)
                {
                    // W�w�l�p�����P�����O�ȉ��̏ꍇ
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_00314,
                        this.getClass().getName() + ".validate()");
                }
                
                if(99999999 < l_intWlimitPrice)
                {
                    // W�w�l�p�����P�����W���𒴂���ꍇ
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_00314,
                        this.getClass().getName() + ".validate()");
                }
            } catch(NumberFormatException e)
            {
                // W�w�l�p�����P���������ȊO�̏ꍇ
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00314,
                    this.getClass().getName() + ".validate()");
            }
        } else if(WEB3OrderPriceDivDef.MARKET_PRICE.equals(this.wLimitOrderPriceDiv))
        {
            if(this.wLimitPrice != null && !this.wLimitPrice.equals("0"))
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00124,
                    this.getClass().getName() + ".validate()");
            }
        }

        // �P�S�j�@@���������E���s�����̃`�F�b�N
        if(WEB3OrderExpirationDateTypeDef.CARRIED_ORDER.equals(this.expirationDateType))
        {
            if(!WEB3ExecutionConditionDef.NO_CONDITION.equals(this.execCondType))
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00125,
                    this.getClass().getName() + ".validate()");
            }
        }

        // �P�T�j�@@�l�i�����E�����P���敪�̃`�F�b�N
        if(!WEB3PriceConditionDef.DEFAULT.equals(this.priceCondType))
        {
            if(!WEB3OrderPriceDivDef.MARKET_PRICE.equals(this.orderPriceDiv))
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01348,
                    this.getClass().getName() + ".validate()");
            }
        }

        // �P�U�j�@@�l�i�����E���s�����̃`�F�b�N
        if(WEB3PriceConditionDef.PARTIALLY_LIMIT_PRICE_ORDER.equals(this.priceCondType)
            || WEB3PriceConditionDef.PARTIALLY_CANCEL_ORDER.equals(this.priceCondType))
        {
            if(!WEB3ExecutionConditionDef.NO_CONDITION.equals(this.execCondType))
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01349,
                    this.getClass().getName() + ".validate()");
            }
        }

        // �P�V�j�@@�l�i�����E���������敪�̃`�F�b�N
        if(!WEB3PriceConditionDef.DEFAULT.equals(this.priceCondType))
        {
            if(!WEB3OrderExpirationDateTypeDef.DAY_LIMIT.equals(this.expirationDateType))
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01350,
                    this.getClass().getName() + ".validate()");
            }
        }

        // �P�W�j�@@�l�i�����E���������敪�̃`�F�b�N
        if(!WEB3PriceConditionDef.DEFAULT.equals(this.priceCondType))
        {
            if(!WEB3OrderingConditionDef.DEFAULT.equals(this.orderCondType))
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01351,
                    this.getClass().getName() + ".validate()");
            }
        }
        
        // �P�X�j�@@���������E���s�����̃`�F�b�N
        if (WEB3OrderingConditionDef.STOP_LIMIT_PRICE.equals(this.orderCondType)
        	&& !WEB3ExecutionConditionDef.NO_CONDITION.equals(this.execCondType))
        {
			throw new WEB3BusinessLayerException(
				WEB3ErrorCatalog.BUSINESS_ERROR_02473,
				this.getClass().getName() + ".validate()");
        }

        //�@@�P�X�|�Q�j�@@this.����������"2�FW�w�l"�̏ꍇ�A 
        //�@@this.���s�������ȉ��̒l�ȊO�̏ꍇ�A��O���X���[����B
        //�@@�E1�F������ 
        //�@@�E7�F�s�o���������s
        if (WEB3OrderingConditionDef.W_LIMIT_PRICE.equals(this.orderCondType)
            && !(WEB3ExecutionConditionDef.NO_CONDITION.equals(this.execCondType)
            || WEB3ExecutionConditionDef.AT_MARKET_CLOSE_NOT_EXECUTED.equals(this.execCondType)))
        {
            log.debug("W�w�l�����̎��s�����́g�������h�A�g�s�o���������s�h�ȊO�w��s�B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02500,
                this.getClass().getName() + "." +  STR_METHOD_NAME,
                "W�w�l�����̎��s�����́g�������h�A�g�s�o���������s�h�ȊO�w��s�B");
        }

        //�Q�O�j�@@�v�w�l�p���s�����E�����P���敪�`�F�b�N 
        //�@@�@@this.�v�w�l�p���s�������h7:�s�o���������s�h�ł��A 
        //�@@�@@this.�v�w�l�p�����P���敪���h1�F�w�l�h�̏ꍇ�A��O���X���[����B
        if (WEB3ExecutionConditionDef.AT_MARKET_CLOSE_NOT_EXECUTED.equals(this.wlimitExecCondType)
            && !WEB3OrderPriceDivDef.LIMIT_PRICE.equals(this.wLimitOrderPriceDiv))
        {
            log.debug("�v�w�l�p�����P���敪���h�w�l�h�ȊO�̒l�ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02501,
                this.getClass().getName() + "." +  STR_METHOD_NAME,
                "�v�w�l�p�����P���敪���h�w�l�h�ȊO�̒l�ł��B");
        }

        //�Q�P�j�@@�v�w�l�p�L����ԋ敪�`�F�b�N 
        //�@@�@@this.�v�w�l�p�L����ԋ敪��null�ł���΁A 
        //�@@�@@�ȉ��̏������s���B
        //�Q�P�|�P�j�@@this.�v�w�l�L����ԋ敪���ȉ��̒l�ȊO�̏ꍇ�A
        //�@@�@@��O���X���[����B
        //�@@�@@�@@�E0�F���~�b�g�����L��
        //�@@�@@�@@�E1�F�X�g�b�v�����L��
        //�@@�@@�@@�E2�F�X�g�b�v����������
        if (this.wlimitEnableStatusDiv != null 
            && !(WEB3EquityWlimitEnableStatusDivDef.LIMIT_ENABLE.equals(this.wlimitEnableStatusDiv)
            || WEB3EquityWlimitEnableStatusDivDef.STOP_ENABLE.equals(this.wlimitEnableStatusDiv)
            || WEB3EquityWlimitEnableStatusDivDef.STOP_UN_ENABLE.equals(this.wlimitEnableStatusDiv)))
        {
            log.debug("�v�w�l�L����ԋ敪�̒l�����݂��Ȃ��R�[�h�l�ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02502,
                this.getClass().getName() + "." +  STR_METHOD_NAME,
                "�v�w�l�L����ԋ敪�̒l�����݂��Ȃ��R�[�h�l�ł��B");
        }

        // �Q�Q�j�@@�v�w�l�p���s�����E�����L�������`�F�b�N 
        //�@@�@@this.���������敪���h2�FW�w�l�h�ł��A 
        //�@@�@@�@@this.���������敪���h2�F�o����܂Œ����h�ł��A 
        //�@@�@@�@@this.�v�w�l�p���s�������h1�F�������h�̏ꍇ�A 
        //�@@�@@�@@��O���X���[����B
        if (WEB3OrderingConditionDef.W_LIMIT_PRICE.equals(this.orderCondType)
            && WEB3OrderExpirationDateTypeDef.CARRIED_ORDER.equals(this.expirationDateType)
            && !WEB3ExecutionConditionDef.NO_CONDITION.equals(this.wlimitExecCondType))
        {
            log.debug("�v�w�l�p���s�������h�������h�ȊO�̒l�ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02503,
                this.getClass().getName() + "." +  STR_METHOD_NAME,
                "�v�w�l�p���s�������h�������h�ȊO�̒l�ł��B");
        }

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (validate�A������)<BR>
     * �����N�G�X�g�f�[�^�̐������`�F�b�N�i�A�������p�j���s���B<BR>
     * �i�������A���N���X���Ŋ�������ȈՃ`�F�b�N�݂̂Ƃ���B�j<BR>
     * <BR>
     * �P�j�@@�l�i�����`�F�b�N<BR>
     * �@@�l�i������"�w��Ȃ�"�̏ꍇ�́A<BR>
     * �@@�u�A�������͒l�i�����w��s�v�̗�O��throw����B<BR>
     * <BR>
     * �Q�j�@@���s�����`�F�b�N<BR>
     * �@@���s������"������"�̏ꍇ�́A<BR>
     * �@@�u�A�������͎��s�����w��s�v�̗�O��throw����B<BR>
     * <BR>
     * �R�j�@@���������`�F�b�N<BR>
     * �@@���������敪��"�w��Ȃ�"�̏ꍇ�́A<BR>
     * �@@�u�A�������͔��������w��s�v�̗�O��throw����B<BR>
     * @@throws WEB3BusinessLayerException
     */
    public void validateSuccOrder() throws WEB3BusinessLayerException
    {
        final String STR_METHOD_NAME = "validateSuccOrder()";
        log.entering(STR_METHOD_NAME);
        
        if (!WEB3PriceConditionDef.DEFAULT.equals(this.priceCondType))
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02234,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        if (!WEB3ExecutionConditionDef.NO_CONDITION.equals(this.execCondType))
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02235,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        if (!WEB3OrderingConditionDef.DEFAULT.equals(this.orderCondType))
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02236,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * @@return webbroker3.common.message.WEB3GenResponse
     * @@roseuid 409EFF3C0270
     */
    public WEB3GenResponse createResponse()
    {
        return null;
    }
}
@
