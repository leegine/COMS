head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.45.53;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3MarginCommonRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �M�p������ʃ��N�G�X�g(WEB3MarginCommonRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/09/16 ������ (���u) �V�K�쐬
                   2006/11/02 �����F(���u) ���f�� 948,1000
*/

package webbroker3.equity.message;

import java.util.Date;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3OrderPriceDivDef;
import webbroker3.common.define.WEB3ExecutionConditionDef;
import webbroker3.common.define.WEB3OrderExpirationDateTypeDef;
import webbroker3.common.define.WEB3OrderingConditionDef;
import webbroker3.common.define.WEB3OrderConditionOperatorDef;
import webbroker3.common.define.WEB3PriceConditionDef;
import webbroker3.equity.define.WEB3EquityWlimitEnableStatusDivDef;

import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * �i�M�p������ʃ��N�G�X�g�j�B<br>
 * <br>
 * �M�p������ʃ��N�G�X�g�N���X
 * @@author ������
 * @@version 1.0
 */
public class WEB3MarginCommonRequest extends WEB3GenRequest 
{

    /**
     * <p>�i���O�o�̓��[�e�B���e�B�j�B</p>
     */
    private static WEB3LogUtility log =
            WEB3LogUtility.getInstance(WEB3MarginCommonRequest.class);
    
    /**
     * <p>�iPTYPE�j�B</p>
     */
    public static final String PTYPE = "margin_common";

    /**
     * <p>�iSerialVersionUID�j�B</p>
     */
    public static final long serialVersionUID = 200409101800L;
    
    /**
     * <p>�i���������j�B</p>
     * <p>��������</p>
     */
    public String orderQuantity;
    
    /**
     * <p>�i�����P���敪�j�B</p>
     * <p>0�F���s�@@1�F�w�l</p>
     */
    public String orderPriceDiv;
    
    /**
     * <p>�i�����P���j�B</p>
     * <p>�����P��<br>
     * <br>
     * �����P���敪���u�w�l�v�̏ꍇ�ɐݒ�</p>
     */
    public String limitPrice;
    
    /**
     * <p>�i�l�i�����j�B</p>
     * <p>0:�w��Ȃ��@@1:���ݒl�w�l�@@3:�D��w�l�@@5:���s�c���w�l�@@7:���s�c�����</p>
     */
    public String priceCondType;
    
    /**
     * <p>�i���s�����j�B</p>
     * <p>1�F������ 3�F��t 4�F���� 7�F�s�o���������s</p>
     */
    public String execCondType;
    
    /**
     * <p>�i���������敪�j�B</p>
     * <p>1�F��������@@2�F�o����܂Œ���</p>
     */
    public String expirationDateType;
    
    /**
     * <p>�i�����L�������j�B</p>
     * <p>���������敪���u�o����܂Œ����v�̏ꍇ�ɐݒ�</p>
     */
    public Date expirationDate;
    
    /**
     * <p>�i���������敪�j�B</p>
     * <p>0�F�w��Ȃ��@@1�F�t�w�l�@@2�FW�w�l</p>
     */
    public String orderCondType;
    
    /**
     * <p>�i�t�w�l�p���������P���j�B</p>
     * <p>���������敪���A�u�t�w�l�v�̏ꍇ�ݒ肳���</p>
     */
    public String stopOrderCondPrice;
    
    /**
     * <p>�i�t�w�l�p�����������Z�q�j�B</p>
     * <p>1�F�ȏ�@@2�F�ȉ�<br>
     * <br>
     * ���������敪���A�u�t�w�l�v�̏ꍇ�ݒ肳���</p>
     */
    public String stopOrderCondOperator;
    
    /**
     * <p>�i�v�w�l�p���������P���j�B</p>
     * <p>���������敪���A�uW�w�l�v�̏ꍇ�ݒ肳���</p>
     */
    public String wlimitOrderCondPrice;
    
    /**
     * <p>�i�v�w�l�p�����������Z�q�j�B</p>
     * <p>1�F�ȏ�@@2�F�ȉ�<br>
     * <br>
     * ���������敪���A�uW�w�l�v�̏ꍇ�ݒ肳���</p>
     */
    public String wlimitOrderCondOperator;
    
    /**
     * <p>�i�v�w�l�p�����P���敪�j�B</p>
     * <p>0�F���s�@@1�F�w�l<br>
     * <br>
     * ���������敪���A�uW�w�l�v�̏ꍇ�ݒ肳���</p>
     */
    public String wLimitOrderPriceDiv;
    
    /**
     * <p>�i�v�w�l�p�����P���j�B</p>
     * <p>�v�w�l�p�����P���敪���A�u�w�l�v�̏ꍇ�ݒ肳���</p>
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
     * 0�F���~�b�g�����L���@@1�F�X�g�b�v�����L�� <BR>
     * 2�F�X�g�b�v���������� <BR>
     * ���������敪���A�u2�FW�w�l�v�̏ꍇ�ݒ肳���B <BR>
     * ���������̂݃Z�b�g�B<BR>
     */
    public String wlimitEnableStatusDiv;

    /**
     * <p>�i�M�p������ʃ��N�G�X�g�j�B</p>
     * <p>�R���X�g���N�^�B</p>
     */
    public WEB3MarginCommonRequest() 
    {
    }
    
    /**
     * <p>�ivalidate�j�B</p>
     * <p>�i�����N�G�X�g�f�[�^�̐������`�F�b�N���s���B<br>
     * �i�������A���N���X���Ŋ�������ȈՃ`�F�b�N�݂̂Ƃ���B�j<br>
     * <br>
     * �P�j�@@�����P���敪�`�F�b�N<br>
     * �@@�P�|�P�jthis.�����P���敪��null�̏ꍇ�A<br>
     * �@@�@@�@@�@@�@@�u�����P���敪��null�v�̗�O���X���[����B<br>
     * �@@�@@�@@�@@�@@class: WEB3BusinessLayerException<br>
     * �@@�@@�@@�@@�@@tag:   BUSINESS_ERROR_00184<br>
     * <br>
     * �@@�P�|�Q�jthis.�����P���敪���ȉ��̒l�ȊO�̏ꍇ�A<br>
     * �@@�@@�@@�@@�@@�u�����P���敪������`�̒l�v�̗�O���X���[����B<br>
     * <br>
     * �@@�@@�@@�@@�E�h0�F���s�h<br>
     * �@@�@@�@@�@@�E�h1�F�w�l�h<br>
     * �@@�@@�@@�@@�@@class: WEB3BusinessLayerException<br>
     * �@@�@@�@@�@@�@@tag:   BUSINESS_ERROR_00185<br>
     * <br>
     * �Q�j�@@�����P���`�F�b�N<br>
     * �@@�Q�|�P�jthis.�����P���敪���h0�F���s�h�ł��Athis.�����P����null<br>
     * �@@�@@�@@�@@�@@�̏ꍇ�́A��O���X���[����B<br>
     * �@@�@@�@@�@@�@@class: WEB3BusinessLayerException<br>
     * �@@�@@�@@�@@�@@tag:   BUSINESS_ERROR_00116<br>
     * <br>
     * �@@�Q�|�Q�jthis.�����P���敪���h1�F�w�l�h�ł��A<br>
     * �@@�@@�@@�@@�@@�ȉ��̂����ꂩ�ɊY������ꍇ�́A�ȉ��̗�O���X���[����B<br>
     * <br>
     * �@@�@@�@@�@@�Ethis.�����P����null�@@(�u�����P����null�v�̗�O���X���[�B)<br>
     * �@@�@@�@@�@@�@@class: WEB3BusinessLayerException<br>
     * �@@�@@�@@�@@�@@tag:   BUSINESS_ERROR_00313<br>
     * �@@�@@�@@�@@�Ethis.�����P�������� (�u�����P���������ȊO�v�̗�O���X���[�B)<br>
     * �@@�@@�@@�@@�@@class: WEB3BusinessLayerException<br>
     * �@@�@@�@@�@@�@@tag:   BUSINESS_ERROR_00314<br>
     * �@@�@@�@@�@@�Ethis.�����P�����O�@@�@@ (�u�����P����0�ȉ��v�̗�O���X���[�B)<br>
     * �@@�@@�@@�@@�@@class: WEB3BusinessLayerException<br>
     * �@@�@@�@@�@@�@@tag:   BUSINESS_ERROR_00314<br>
     * �@@�@@�@@�@@�Ethis.�����P����8���𒴂��鐔��<br>
     * �@@�@@�@@�@@�@@�@@(�u�����P���̌�����8���𒴉߁v�̗�O���X���[�B)<br>
     * �@@�@@�@@�@@�@@class: WEB3BusinessLayerException<br>
     * �@@�@@�@@�@@�@@tag:   BUSINESS_ERROR_00314<br>
     * <br>
     * �R�j�@@�l�i�����`�F�b�N<br>
     * �@@�R�|�P�jthis.�l�i������null�̏ꍇ�A<br>
     * �@@�@@�@@�@@�@@�u�l�i������null�v�̗�O���X���[����B<br>
     * �@@�@@�@@�@@�@@class: WEB3BusinessLayerException<br>
     * �@@�@@�@@�@@�@@tag:   BUSINESS_ERROR_01344<br>
     * <br>
     * �@@�R�|�Q�jthis.�l�i�������ȉ��̒l�ȊO�̏ꍇ�A<br>
     * �@@�@@�@@�@@�@@�u�l�i����������`�̒l�v�̗�O���X���[����B<br>
     * <br>
     * �@@�@@�@@�@@�E�h0�F�w��Ȃ��h<br>
     * �@@�@@�@@�@@�E�h1�F���ݒl�w�l�h<br>
     * �@@�@@�@@�@@�E�h3�F�D��w�l�h<br>
     * �@@�@@�@@�@@�E�h5�F���s�c���w�l�h<br>
     * �@@�@@�@@�@@�E�h7�F���s�c������h<br>
     * �@@�@@�@@�@@�@@class: WEB3BusinessLayerException<br>
     * �@@�@@�@@�@@�@@tag:   BUSINESS_ERROR_00314<br>
     * <br>
     * �S�j�@@���s�����`�F�b�N<br>
     * �@@�S�|�P�jthis.���s������null�̏ꍇ�A<br>
     * �@@�@@�@@�@@�@@�u���s������null�v�̗�O���X���[����B<br>
     * �@@�@@�@@�@@�@@class: WEB3BusinessLayerException<br>
     * �@@�@@�@@�@@�@@tag:   BUSINESS_ERROR_00197<br>
     * <br>
     * �@@�S�|�Q�jthis.���s�������ȉ��̒l�ȊO�̏ꍇ�A<br>
     * �@@�@@�@@�@@�@@�u���s����������`�̒l�v�̗�O���X���[����B<br>
     * <br>
     * �@@�@@�@@�@@�E�h1�F�������h<br>
     * �@@�@@�@@�@@�E�h3�F��t�h<br>
     * �@@�@@�@@�@@�E�h4�F�����h<br>
     * �@@�@@�@@�@@�E�h7�F�s�o���������s�h<br>
     * �@@�@@�@@�@@�@@class: WEB3BusinessLayerException<br>
     * �@@�@@�@@�@@�@@tag:   BUSINESS_ERROR_00127<br>
     * <br>
     * �T�j�@@���s�����E�����P���敪�`�F�b�N<br>
     * �@@this.���s�������h7�F�s�o���������s�h�ł��A<br>
     * �@@this.�l�i�������i�h1�F���ݒl�w�l�h�A�h3�F�D��w�l�h�j ���A<BR>
     * �@@this.�����P���敪���h1�F�w�l�h�̏ꍇ�A<br>
     * �@@�u�s�o���������s���͐��s�s�v�̗�O���X���[����B<br>
     * �@@���h1�F���ݒl�w�l�h�A�h3�F�D��w�l�h�����̏ꍇ�A�s�ꓞ����ɂ����ɒl���t���̂ŁA<BR> 
     * �@@���s�o���������s�ł����Ă��h0�F���s�h�w��Ƃ���B<BR>
     * �@@�@@�@@�@@�@@class: WEB3BusinessLayerException<br>
     * �@@�@@�@@�@@�@@tag:   BUSINESS_ERROR_00114<br>
     * <br>
     * �U�j�@@���������敪�`�F�b�N<br>
     * �@@�U�|�P�jthis.���������敪��null�̏ꍇ�A<br>
     * �@@�@@�@@�@@�@@�u���������敪��null�v�̗�O���X���[����B<br>
     * �@@�@@�@@�@@�@@class: WEB3BusinessLayerException<br>
     * �@@�@@�@@�@@�@@tag:   BUSINESS_ERROR_00208<br>
     * <br>
     * �@@�U�|�Q�jthis.���������敪���ȉ��̒l�ȊO�̏ꍇ�A<br>
     * �@@�@@�@@�@@�@@�u���������敪������`�̒l�v�̗�O���X���[����B<br>
     * �@@�@@�@@�@@�@@class: WEB3BusinessLayerException<br>
     * �@@�@@�@@�@@�@@tag:   BUSINESS_ERROR_00209<br>
     * <br>
     * �@@�@@�@@�@@�E�h1�F��������h<br>
     * �@@�@@�@@�@@�E�h2�F�o����܂Œ����h<br>
     * <br>
     * �V�j�@@���������敪�E���s�����`�F�b�N<br>
     * �@@this.���������敪���h2�F�o����܂Œ����h�ł��A<br>
     * �@@this.���s�������h1�F�������h�̏ꍇ�A<br>
     * �@@�u�o����܂Œ������͎��s�����w��s�v�̗�O���X���[����B<br>
     * �@@�@@�@@�@@�@@class: WEB3BusinessLayerException<br>
     * �@@�@@�@@�@@�@@tag:   BUSINESS_ERROR_00125<br>
     * <br>
     * �W�j�@@�����L�������`�F�b�N<br>
     * �@@�W�|�P�j�@@this.���������敪���h1�F��������h�ł��A<br>
     * �@@this.�����L��������null�̏ꍇ�A<br>
     * �@@�u�����������͒����L�������w��s�v�̗�O���X���[����B<br>
     * �@@�@@�@@�@@�@@class: WEB3BusinessLayerException<br>
     * �@@�@@�@@�@@�@@tag:   BUSINESS_ERROR_00117<br>
     * <br>
     * �@@�W�|�Q�j�@@this.���������敪���h2�F�o����܂Œ����h�ł��A<br>
     * �@@this.�����L��������null�̏ꍇ�A<br>
     * �@@�u�����L��������null�v�̗�O���X���[����B<br>
     * �@@�@@�@@�@@�@@class: WEB3BusinessLayerException<br>
     * �@@�@@�@@�@@�@@tag:   BUSINESS_ERROR_00210<br>
     * <br>
     * �X�j�@@���������敪�`�F�b�N<br>
     * �@@�X�|�P�jthis.���������敪��null�̏ꍇ�A<br>
     * �@@�@@�@@�@@�@@�u���������敪��null�v�̗�O���X���[����B<br>
     * �@@�@@�@@�@@�@@class: WEB3BusinessLayerException<br>
     * �@@�@@�@@�@@�@@tag:   BUSINESS_ERROR_00211<br>
     * <br>
     * �@@�X�|�Q�jthis.���������敪���ȉ��̒l�ȊO�̏ꍇ�A<br>
     * �@@�@@�@@�@@�@@�u���������敪������`�̒l�v�̗�O���X���[����B<br>
     * <br>
     * �@@�@@�@@�@@�E�h0�F�w��Ȃ��h<br>
     * �@@�@@�@@�@@�E�h1�F�t�w�l�h<br>
     * �@@�@@�@@�@@�E�h2�FW�w�l�h<br>
     * �@@�@@�@@�@@�@@class: WEB3BusinessLayerException<br>
     * �@@�@@�@@�@@�@@tag:   BUSINESS_ERROR_00212<br>
     * <br>
     * �P�O�j�@@���������̃`�F�b�N�P�i�h0�F�w��Ȃ��h�j<br>
     * �@@this.���������敪���h0�F�w��Ȃ��h�ł��A�@@<br>
     *   �ȉ��̂����ꂩ�ɊY������ꍇ�A�ȉ��̗�O���X���[����B<br>
     * <br>
     * �@@�@@�Ethis.�t�w�l�p���������P�����inull�܂��́h�O�h�j<br>
     * �@@�@@�@@�@@�u�t�w�l�p���������P���̎w��s�v�̗�O���X���[�B<br>
     * �@@�@@�@@�@@�@@class: WEB3BusinessLayerException<br>
     * �@@�@@�@@�@@�@@tag:   BUSINESS_ERROR_01872<br>
     * �@@�@@�Ethis.�t�w�l�p�����������Z�q��null<br>
     * �@@�@@�@@�@@�u�t�w�l�����������Z�q�̎w��s�v�̗�O���X���[�B<br>
     * �@@�@@�@@�@@�@@class: WEB3BusinessLayerException<br>
     * �@@�@@�@@�@@�@@tag:   BUSINESS_ERROR_01873<br>
     * �@@�@@�Ethis.W�w�l�p���������P�����inull�܂��́h�O�h�j<br>
     * �@@�@@�@@�@@�uW�w�l�p���������P���̎w��s�v�̗�O���X���[�B<br>
     * �@@�@@�@@�@@�@@class: WEB3BusinessLayerException<br>
     * �@@�@@�@@�@@�@@tag:   BUSINESS_ERROR_01874<br>
     * �@@�@@�Ethis.W�w�l�p�����������Z�q��null<br>
     * �@@�@@�@@�@@�uW�w�l�p�����������Z�q�̎w��s�v�̗�O���X���[�B<br>
     * �@@�@@�@@�@@�@@class: WEB3BusinessLayerException<br>
     * �@@�@@�@@�@@�@@tag:   BUSINESS_ERROR_01875<br>
     * �@@�@@�Ethis.W�w�l�p�����P���敪��null<br>
     * �@@�@@�@@�@@�uW�w�l�p�����P���敪�̎w��s�v�̗�O���X���[�B<br>
     * �@@�@@�@@�@@�@@class: WEB3BusinessLayerException<br>
     * �@@�@@�@@�@@�@@tag:   BUSINESS_ERROR_01876<br>
     * �@@�@@�Ethis.W�w�l�p�����P�����inull�܂��́h�O�h�j<br>
     * �@@�@@�@@�@@�uW�w�l�p�����P���̎w��s�v�̗�O���X���[�B<br>
     * �@@�@@�@@�@@�@@class: WEB3BusinessLayerException<br>
     * �@@�@@�@@�@@�@@tag:   BUSINESS_ERROR_01877<br>
     * �@@�@@�Ethis.W�w�l�p���s������null <BR>
     *�@@�@@�@@�@@�u���������敪���g�w��Ȃ��h�̏ꍇ�́A<BR>
     * �@@�@@�@@�@@�v�w�l�p���s�������w��s�v�̗�O���X���[�B<BR>
     * �@@�@@�@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@�@@tag:   BUSINESS_ERROR_02525<BR>
     * <br>
     * �P�P�j�@@���������̃`�F�b�N�Q�i�h1�F�t�w�l�h�j<br>
     * �@@�P�P�|�P�jthis.���������敪���h1�F�t�w�l�h�ł��A<br>
     * �@@�@@�@@�@@�@@�ȉ��̂����ꂩ�ɊY������ꍇ�A��O���X���[����B<br>
     * <br>
     * �@@�@@�Ethis.W�w�l�p���������P�����inull�܂��́h�O�h�j<br>
     * �@@�@@�@@�@@�uW�w�l�p���������P���̎w��s�v�̗�O���X���[�B<br>
     * �@@�@@�@@�@@�@@class: WEB3BusinessLayerException<br>
     * �@@�@@�@@�@@�@@tag:   BUSINESS_ERROR_01878<br>
     * �@@�@@�Ethis.W�w�l�p�����������Z�q��null<br>
     * �@@�@@�@@�@@�uW�w�l�p�����������Z�q�̎w��s�v�̗�O���X���[�B<br>
     * �@@�@@�@@�@@�@@class: WEB3BusinessLayerException<br>
     * �@@�@@�@@�@@�@@tag:   BUSINESS_ERROR_01879<br>
     * �@@�@@�Ethis.W�w�l�p�����P���敪��null<br>
     * �@@�@@�@@�@@�uW�w�l�p�����P���敪�̎w��s�v�̗�O���X���[�B<br>
     * �@@�@@�@@�@@�@@class: WEB3BusinessLayerException<br>
     * �@@�@@�@@�@@�@@tag:   BUSINESS_ERROR_01880<br>
     * �@@�@@�Ethis.W�w�l�p�����P�����inull�܂��́h�O�h�j<br>
     * �@@�@@�@@�@@�uW�w�l�p�����P���̎w��s�v�̗�O���X���[�B<br>
     * �@@�@@�@@�@@�@@class: WEB3BusinessLayerException<br>
     * �@@�@@�@@�@@�@@tag:   BUSINESS_ERROR_01881<br>
     *�@@�@@�Ethis.W�w�l�p���s������null <BR>
     *�@@�@@�@@�@@�u���������敪���g�t�w�l�h�̏ꍇ�́A<BR>
     * �@@�@@�@@�@@�v�w�l�p���s�������w��s�v�̗�O���X���[�B<BR> 
     * �@@�@@�@@�@@�@@class : WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@�@@tag   : BUSINESS_ERROR_02526<BR>
     * <br>
     * �@@�P�P�|�Q�jthis.���������敪���h1�F�t�w�l�h�ł��A<br>
     * �@@�@@�@@�@@�@@�ȉ��̂����ꂩ�ɊY������ꍇ�A��O���X���[����B<br>
     * <br>
     * �@@�@@�Ethis.�t�w�l�p���������P����null<br>
     * �@@�@@�@@�@@�u�t�w�l�p���������P����null�v�̗�O���X���[�B<br>
     * �@@�@@�@@�@@�@@class: WEB3BusinessLayerException<br>
     * �@@�@@�@@�@@�@@tag:   BUSINESS_ERROR_00215<br>
     * �@@�@@�Ethis.�t�w�l�p���������P��������<br>
     * �@@�@@�@@�@@�u�t�w�l�p���������P���������ȊO�v�̗�O���X���[�B<br>
     * �@@�@@�@@�@@�@@class: WEB3BusinessLayerException<br>
     * �@@�@@�@@�@@�@@tag:   BUSINESS_ERROR_00216<br>
     * �@@�@@�Ethis.�t�w�l�p���������P�����O<br>
     * �@@�@@�@@�@@�u�t�w�l�p���������P����0�ȉ��v�̗�O���X���[�B<br>
     * �@@�@@�@@�@@�@@class: WEB3BusinessLayerException<br>
     * �@@�@@�@@�@@�@@tag:   BUSINESS_ERROR_00216<br>
     * �@@�@@�Ethis.�t�w�l�p���������P����8���𒴂��鐔��<br>
     * �@@�@@�@@�@@�u�t�w�l�p���������P���̌�����8���𒴉߁v�̗�O���X���[�B<br>
     * �@@�@@�@@�@@�@@class: WEB3BusinessLayerException<br>
     * �@@�@@�@@�@@�@@tag:   BUSINESS_ERROR_00216<br>
     * �@@�@@�Ethis.�t�w�l�p�����������Z�q��null<br>
     * �@@�@@�@@�@@�u�t�w�l�p�����������Z�q��null�v�̗�O���X���[�B<br>
     * �@@�@@�@@�@@�@@class: WEB3BusinessLayerException<br>
     * �@@�@@�@@�@@�@@tag:   BUSINESS_ERROR_00219<br>
     * �@@�@@�Ethis.�t�w�l�p�����������Z�q��(�h1�F�ȏ�h�܂��́h2�F�ȉ��h)<br>
     * �@@�@@�@@�@@�u�t�w�l�p�����������Z�q������`�̒l�v�̗�O���X���[�B<br>
     * �@@�@@�@@�@@�@@class: WEB3BusinessLayerException<br>
     * �@@�@@�@@�@@�@@tag:   BUSINESS_ERROR_00220<br>
     * <br>
     * �P�Q�j�@@���������̃`�F�b�N�R�i�h2�FW�w�l�h�j<br>
     * �@@�P�Q�|�P�jthis.���������敪���h2�FW�w�l�h�ł��A<br>
     * �@@�@@�@@�@@�@@�ȉ��̂����ꂩ�ɊY������ꍇ�A�ȉ��̗�O���X���[����B<br>
     * <br>
     * �@@�@@�Ethis.�t�w�l�p���������P�����inull�܂��́h�O�h�j<br>
     * �@@�@@�@@�@@�u�t�w�l�p���������P���̎w��s�v�̗�O���X���[�B<br>
     * �@@�@@�@@�@@�@@class: WEB3BusinessLayerException<br>
     * �@@�@@�@@�@@�@@tag:   BUSINESS_ERROR_01882<br>
     * �@@�@@�Ethis.�t�w�l�p�����������Z�q��null<br>
     * �@@�@@�@@�@@�u�t�w�l�����������Z�q�̎w��s�v�̗�O���X���[�B<br>
     * �@@�@@�@@�@@�@@class: WEB3BusinessLayerException<br>
     * �@@�@@�@@�@@�@@tag:   BUSINESS_ERROR_01883<br>
     * <br>
     * �@@�P�Q�|�Q�jthis.���������敪���h2�FW�w�l�h�̒l�ł��A<br>
     * �@@�@@�@@�@@�@@�ȉ��̂����ꂩ�ɊY������ꍇ�A�ȉ��̗�O���X���[����B<br>
     * <br>
     * �@@�@@�Ethis.�v�w�l�p���������P����null<br>
     * �@@�@@�@@�@@�uW�w�l�p���������P����null�v�̗�O���X���[�B<br>
     * �@@�@@�@@�@@�@@class: WEB3BusinessLayerException<br>
     * �@@�@@�@@�@@�@@tag:   BUSINESS_ERROR_00223<br>
     * �@@�@@�Ethis.�v�w�l�p���������P��������<br>
     * �@@�@@�@@�@@�uW�w�l�p���������P���������ȊO�v�̗�O���X���[�B<br>
     * �@@�@@�@@�@@�@@class: WEB3BusinessLayerException<br>
     * �@@�@@�@@�@@�@@tag:   BUSINESS_ERROR_00224<br>
     * �@@�@@�Ethis.�v�w�l�p���������P�����O<br>
     * �@@�@@�@@�@@�uW�w�l�p���������P����0�ȉ��v�̗�O���X���[�B<br>
     * �@@�@@�@@�@@�@@class: WEB3BusinessLayerException<br>
     * �@@�@@�@@�@@�@@tag:   BUSINESS_ERROR_00224<br>
     * �@@�@@�Ethis.�v�w�l�p���������P����8���𒴂��鐔��<br>
     * �@@�@@�@@�@@�uW�w�l�p���������P���̌�����8���𒴉߁v�̗�O���X���[�B<br>
     * �@@�@@�@@�@@�@@class: WEB3BusinessLayerException<br>
     * �@@�@@�@@�@@�@@tag:   BUSINESS_ERROR_00224<br>
     * �@@�@@�Ethis.�v�w�l�p�����������Z�q��null<br>
     * �@@�@@�@@�@@�uW�w�l�p�����������Z�q��null�v�̗�O���X���[�B<br>
     * �@@�@@�@@�@@�@@class: WEB3BusinessLayerException<br>
     * �@@�@@�@@�@@�@@tag:   BUSINESS_ERROR_00227<br>
     * �@@�@@�Ethis.�v�w�l�p�����������Z�q���i�h1�F�ȏ�h�܂��́h2�F�ȉ��h�j<br>
     * �@@�@@�@@�@@�uW�w�l�p�����������Z�q������`�̒l�v�̗�O���X���[�B<br>
     * �@@�@@�@@�@@�@@class: WEB3BusinessLayerException<br>
     * �@@�@@�@@�@@�@@tag:   BUSINESS_ERROR_00228<br>
     * �@@�@@�Ethis.�v�w�l�p�����P���敪��null<br>
     * �@@�@@�@@�@@�uW�w�l�p�����P���敪��null�v�̗�O���X���[�B<br>
     * �@@�@@�@@�@@�@@class: WEB3BusinessLayerException<br>
     * �@@�@@�@@�@@�@@tag:   BUSINESS_ERROR_00229<br>
     * �@@�@@�Ethis.�v�w�l�p�����P���敪���i�h0�F���s�h�܂��́h1�F�w�l�h�j<br>
     * �@@�@@�@@�@@�uW�w�l�p�����P���敪������`�̒l�v�̗�O���X���[�B<br>
     * �@@�@@�@@�@@�@@class: WEB3BusinessLayerException<br>
     * �@@�@@�@@�@@�@@tag:   BUSINESS_ERROR_00230<br>
     *�@@�@@�Ethis�D�v�w�l�p���s������null <BR>
     *�@@�@@�@@�@@�u�v�w�l�p���s���������w��v�̗�O���X���[�B<BR>
     * �@@�@@�@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@�@@tag:   BUSINESS_ERROR_02499<BR>
     *�@@�@@�Ethis�D�v�w�l�p���s������null <BR>
     *�@@�@@�@@�����L�̒l�ȊO�̏ꍇ�A�uW�w�l�����̎��s�����́g�������h�A<BR>
     * �@@�@@�@@�g�s�o���������s�h�ȊO�w��s�B�v�̗�O���X���[�B <BR>
     * <BR>
     *�@@�@@�@@�@@�@@1�F������ <BR>
     *�@@�@@�@@�@@�@@7�F�s�o���������s<BR>
     * �@@�@@�@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@�@@tag:   BUSINESS_ERROR_02500<BR>
     * <br>
     * �P�R�j�@@�v�w�l�p�����P���`�F�b�N<br>
     * �@@�P�R�|�P�jthis.���������敪���h2�FW�w�l�h�ł��A<br>
     * �@@�@@�@@�@@this.�v�w�l�p�����P���敪���h0�F���s�h�ł���<br>
     * �@@�@@�@@�@@this.�v�w�l�p�����P����null�̏ꍇ�A<br>
     * �@@�@@�@@�@@�uW�w�l(���s)����W�w�l�p�����P���̎w��s�v��<br>
     * �@@�@@�@@�@@��O���X���[����B<br>
     * �@@�@@�@@�@@�@@class: WEB3BusinessLayerException<br>
     * �@@�@@�@@�@@�@@tag:   BUSINESS_ERROR_00124<br>
     * <br>
     * �@@�P�R�|�Q�jthis.���������敪���h2�FW�w�l�h�ł��A<br>
     * �@@�@@�@@�@@this.�v�w�l�p�����P���敪���h1�F�w�l�h�ł��A<br>
     * �@@�@@�@@�@@�ȉ��̂����ꂩ�ɊY������ꍇ�́A�ȉ��̗�O���X���[����B<br>
     * <br>
     * �@@�@@�@@�@@�Ethis.�v�w�l�p�����P����null<br>
     * �@@�@@�@@�@@�@@�@@�uW�w�l�p�����P����null�v�̗�O���X���[�B<br>
     * �@@�@@�@@�@@�@@class: WEB3BusinessLayerException<br>
     * �@@�@@�@@�@@�@@tag:   BUSINESS_ERROR_00313<br>
     * �@@�@@�@@�@@�Ethis.�v�w�l�p�����P��������<br>
     * �@@�@@�@@�@@�@@�@@�uW�w�l�p�����P���������ȊO�v�̗�O���X���[�B<br>
     * �@@�@@�@@�@@�@@class: WEB3BusinessLayerException<br>
     * �@@�@@�@@�@@�@@tag:   BUSINESS_ERROR_00314<br>
     * �@@�@@�@@�@@�Ethis.�v�w�l�p�����P�����O<br>
     * �@@�@@�@@�@@�@@�@@�uW�w�l�p�����P����0�ȉ��v�̗�O�X���[�B<br>
     * �@@�@@�@@�@@�@@class: WEB3BusinessLayerException<br>
     * �@@�@@�@@�@@�@@tag:   BUSINESS_ERROR_00314<br>
     * �@@�@@�@@�@@�Ethis.�v�w�l�p�����P����8���𒴂��鐔��<br>
     * �@@�@@�@@�@@�@@�@@�uW�w�l�p�����P���̌�����8���𒴉߁v�̗�O���X���[�B<br>
     * �@@�@@�@@�@@�@@class: WEB3BusinessLayerException<br>
     * �@@�@@�@@�@@�@@tag:   BUSINESS_ERROR_00314<br>
     * <br>
     * �P�S�j�@@���������`�F�b�N<br>
     * �@@�@@�@@�@@this.����������null�ł��A�ȉ��̂����ꂩ�ɊY������ꍇ�́A<br>
     * �@@�@@�@@�@@�ȉ��̗�O���X���[����B<br>
     * <br>
     * �@@�@@�@@�Ethis.��������������<br>
     * �@@�@@�@@�@@�@@�@@�u���������������ȊO�v�̗�O���X���[�B<br>
     * �@@�@@�@@�@@�@@class: WEB3BusinessLayerException<br>
     * �@@�@@�@@�@@�@@tag:   BUSINESS_ERROR_00075<br>
     * �@@�@@�@@�Ethis.�����������O<br>
     * �@@�@@�@@�@@�@@�@@�u����������0�ȉ��v�̗�O���X���[�B<br>
     * �@@�@@�@@�@@�@@class: WEB3BusinessLayerException<br>
     * �@@�@@�@@�@@�@@tag:   BUSINESS_ERROR_00076<br>
     * �@@�@@�@@�Ethis.����������8���𒴂��鐔��<br>
     * �@@�@@�@@�@@�@@�@@�u���������̌�����8���𒴉߁v�̗�O���X���[�B<br>
     * �@@�@@�@@�@@�@@class: WEB3BusinessLayerException<br>
     * �@@�@@�@@�@@�@@tag:   BUSINESS_ERROR_00077<br>
     * <br>
     * �P�T�j�@@�l�i�����E�����P���敪�̃`�F�b�N<br>
     * �@@this.�l�i������"0�F�w��Ȃ�"�̏ꍇ�A<br>
     * �@@this.�����P���敪���u0�F���s�v�ł���Η�O���X���[����B<br>
     * �@@�@@�@@�@@�@@class: WEB3BusinessLayerException<br>
     * �@@�@@�@@�@@�@@tag:   BUSINESS_ERROR_01348<br>
     * <br>
     * �P�U�j�@@�l�i�����E���s�����̃`�F�b�N<br>
     * �@@this.�l�i�������i"5�F���s�c���w�l����"�A"7�F���s�c���������"�j�̂����ꂩ�̏ꍇ�A<br>
     * �@@this.���s�������u1�F�������v�ł���Η�O���X���[����B<br>
     * �@@�@@�@@�@@�@@class: WEB3BusinessLayerException<br>
     * �@@�@@�@@�@@�@@tag:   BUSINESS_ERROR_01349<br>
     * <br>
     * �P�V�j�@@�l�i�����E���������敪�̃`�F�b�N<br>
     * �@@this.�l�i������"0�F�w��Ȃ�"�̏ꍇ�A<br>
     * �@@this.���������敪���u1�F��������v�ł���Η�O���X���[����B<br>
     * �@@�@@�@@�@@�@@class: WEB3BusinessLayerException<br>
     * �@@�@@�@@�@@�@@tag:   BUSINESS_ERROR_01350<br>
     * <br>
     * �P�W�j�@@�l�i�����E���������敪�̃`�F�b�N<br>
     * �@@this.�l�i������"0�F�w��Ȃ�"�̏ꍇ�A<br>
     * �@@this.���������敪���u0�F�w��Ȃ��v�ł���Η�O���X���[����B<br>
     * �@@�@@�@@�@@�@@class: WEB3BusinessLayerException<br>
     * �@@�@@�@@�@@�@@tag:   BUSINESS_ERROR_01351</p>
     * <br>
     * �P�X�j�@@���������E���s�����̃`�F�b�N <BR>
     * �@@�P�X�|�P�j�@@�@@this.����������"1�F�t�w�l"�̏ꍇ�A <BR>
     * �@@�@@this.���s�������u1�F�������v�ł���Η�O���X���[����B <BR>
     *  �@@�@@�@@�@@�@@class: WEB3BusinessLayerException<BR>
     *  �@@�@@�@@�@@�@@tag:   BUSINESS_ERROR_02473<BR>
     * <BR>
     * �@@�P�X�|�Q�j�@@this.����������"2�FW�w�l"�̏ꍇ�A <BR>
     * �@@�@@this.���s�������ȉ��̒l�ȊO�̏ꍇ�A��O���X���[����B <BR>
     * <BR>
     * �@@�@@�@@�E1�F������ <BR>
     * �@@�@@�@@�E7�F�s�o���������s <BR>
     *  �@@�@@�@@�@@�@@class: WEB3BusinessLayerException<BR>
     *  �@@�@@�@@�@@�@@tag:   BUSINESS_ERROR_02500<BR>
     * <BR>
     * �Q�O�j�@@�v�w�l�p���s�����E�����P���敪�`�F�b�N <BR>
     * �@@�@@�@@�@@this.�v�w�l�p���s�������h7:�s�o���������s�h�ł��A <BR>
     * �@@�@@�@@�@@this.�v�w�l�p�����P���敪���h1�F�w�l�h�̏ꍇ�A��O���X���[����B<BR>
     *  �@@�@@�@@�@@�@@class: WEB3BusinessLayerException<BR>
     *  �@@�@@�@@�@@�@@tag:   BUSINESS_ERROR_02501<BR>
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
     *  �@@�@@�@@�@@�@@class: WEB3BusinessLayerException<BR>
     *  �@@�@@�@@�@@�@@tag:   BUSINESS_ERROR_02502<BR>
     * <BR>
     * �Q�Q�j�@@�v�w�l�p���s�����E�����L�������`�F�b�N <BR>
     * �@@�@@�@@�@@this.���������敪���h2�FW�w�l�h�ł��A <BR>
     * �@@�@@�@@�@@this.���������敪���h2�F�o����܂Œ����h�ł��A <BR>
     * �@@�@@�@@�@@this.�v�w�l�p���s�������h1�F�������h�̏ꍇ�A <BR>
     * �@@�@@�@@�@@��O���X���[����B<BR>
     *  �@@�@@�@@�@@�@@class: WEB3BusinessLayerException<BR>
     *  �@@�@@�@@�@@�@@tag:   BUSINESS_ERROR_02503<BR>
     * @@throws WEB3BaseException
     */
    public void validate() throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = "WEB3MarginCommonRequest: validate()";
        log.entering(STR_METHOD_NAME);
        
        // �P�j�@@�����P���敪�`�F�b�N
        if (this.orderPriceDiv == null)
        {
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00184,
            this.getClass().getName() + "validate");
        }

        if (!WEB3OrderPriceDivDef.MARKET_PRICE.equals(this.orderPriceDiv)
                 && !WEB3OrderPriceDivDef.LIMIT_PRICE.equals(this.orderPriceDiv))
        {
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00185,
            this.getClass().getName() + "validate");
        }

        // �Q�j�@@�����P���`�F�b�N
        if (WEB3OrderPriceDivDef.MARKET_PRICE.equals(this.orderPriceDiv) && this.limitPrice != null)
        {
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00116,
            this.getClass().getName() + "validate");
        }

		if (WEB3OrderPriceDivDef.LIMIT_PRICE.equals(this.orderPriceDiv))
		{
			if (this.limitPrice == null)
			{
				throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00187,
				this.getClass().getName() + "validate");
			}

			if (!WEB3StringTypeUtility.isNumber(this.limitPrice))
			{
				throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00188,
				this.getClass().getName() + "validate");
			}

			if (Long.parseLong(this.limitPrice) <= 0 )
			{
				throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00189,
				this.getClass().getName() + "validate");
			}

			if (this.limitPrice.length() > 8)
			{
				throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00190,
				this.getClass().getName() + "validate");
			}
		}
       
        // �R�j�@@�l�i�����`�F�b�N
        if(this.priceCondType == null)
        {
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_01343,
            this.getClass().getName() + "validate");
        }

        if(!(WEB3PriceConditionDef.DEFAULT.equals(this.priceCondType)
            || WEB3PriceConditionDef.PRESENT_VALUE_LIMIT_PRICE_ORDER.equals(this.priceCondType)
            || WEB3PriceConditionDef.PRIORITY_LIMIT_PRICE_ORDER.equals(this.priceCondType)
            || WEB3PriceConditionDef.PARTIALLY_LIMIT_PRICE_ORDER.equals(this.priceCondType)
            || WEB3PriceConditionDef.PARTIALLY_CANCEL_ORDER.equals(this.priceCondType)))
        {
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_01344,
            this.getClass().getName() + "validate");
        }

        if (this.execCondType == null)
        {
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00197,
            this.getClass().getName() + "validate");
        }

        if (!WEB3ExecutionConditionDef.NO_CONDITION.equals(this.execCondType)
                 && !WEB3ExecutionConditionDef.AT_MARKET_OPEN.equals(this.execCondType)
                 && !WEB3ExecutionConditionDef.AT_MARKET_CLOSE.equals(this.execCondType)
                 && !WEB3ExecutionConditionDef.AT_MARKET_CLOSE_NOT_EXECUTED.equals(this.execCondType))
        {
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00127,
            this.getClass().getName() + "validate");
        }

        // �T�j�@@���s�����E�����P���敪�`�F�b�N
        // this.���s�������h7�F�s�o���������s�h ���A 
        // this.�l�i�������i�h1�F���ݒl�w�l�h�A�h3�F�D��w�l�h�j ���A 
        // this.�����P���敪���h1�F�w�l�h�̏ꍇ�A 
        // �u�s�o���������s���͐��s�s�v�̗�O���X���[����B 
		// ���h1�F���ݒl�w�l�h�A�h3�F�D��w�l�h�����̏ꍇ�A�s�ꓞ����ɂ����ɒl���t���̂ŁA 
		// ���s�o���������s�ł����Ă��h0�F���s�h�w��Ƃ���B 
        if (WEB3ExecutionConditionDef.AT_MARKET_CLOSE_NOT_EXECUTED.equals(this.execCondType)
            && !(WEB3PriceConditionDef.PRESENT_VALUE_LIMIT_PRICE_ORDER.equals(this.priceCondType)
                || WEB3PriceConditionDef.PRIORITY_LIMIT_PRICE_ORDER.equals(this.priceCondType))
            && !WEB3OrderPriceDivDef.LIMIT_PRICE.equals(this.orderPriceDiv))
        {
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00114,
            this.getClass().getName() + "validate");
        }

        // �U�j�@@���������敪�`�F�b�N
        if (this.expirationDateType == null)
        {
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00208,
            this.getClass().getName() + "validate");
        }

        if (!WEB3OrderExpirationDateTypeDef.DAY_LIMIT.equals(this.expirationDateType)
                && !WEB3OrderExpirationDateTypeDef.CARRIED_ORDER.equals(this.expirationDateType))
        {
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00209,
            this.getClass().getName() + "validate");
        }

        // �V�j�@@���������敪�E���s�����`�F�b�N
        if (WEB3OrderExpirationDateTypeDef.CARRIED_ORDER.equals(this.expirationDateType)
                && !WEB3ExecutionConditionDef.NO_CONDITION.equals(this.execCondType))
        {
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00125,
            this.getClass().getName() + "validate");
        }

        // �W�j�@@�����L�������`�F�b�N
        if (WEB3OrderExpirationDateTypeDef.DAY_LIMIT.equals(this.expirationDateType)
                && this.expirationDate != null)
        {
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00117,
            this.getClass().getName() + "validate");
        }

        if (WEB3OrderExpirationDateTypeDef.CARRIED_ORDER.equals(this.expirationDateType)
                && this.expirationDate == null)
        {
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00210,
            this.getClass().getName() + "validate");
        }

        // �X�j�@@���������敪�`�F�b�N
        if (this.orderCondType == null)
        {
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00211,
            this.getClass().getName() + "validate");
        }

        if (!WEB3OrderingConditionDef.DEFAULT.equals(this.orderCondType)
                && !WEB3OrderingConditionDef.STOP_LIMIT_PRICE.equals(this.orderCondType)
                && !WEB3OrderingConditionDef.W_LIMIT_PRICE.equals(this.orderCondType))
        {
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00212,
            this.getClass().getName() + "validate");
        }

        // �P�O�j�@@���������̃`�F�b�N�P�i�h0�F�w��Ȃ��h�j
        if (WEB3OrderingConditionDef.DEFAULT.equals(this.orderCondType))
        {
            if (this.stopOrderCondPrice != null && !"0".equals(this.stopOrderCondPrice))
            {
                throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_01872,
                this.getClass().getName() + "validate");
            }

            if (this.stopOrderCondOperator != null)
            {
                throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_01873,
                this.getClass().getName() + "validate");
            }

            if (this.wlimitOrderCondPrice != null && !"0".equals(this.wlimitOrderCondPrice))
            {
                throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_01874,
                this.getClass().getName() + "validate");
            }

            if (this.wlimitOrderCondOperator != null)
            {
                throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_01875,
                this.getClass().getName() + "validate");
            }

            if (this.wLimitOrderPriceDiv != null)
            {
                throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_01876,
                this.getClass().getName() + "validate");
            }

            if (this.wLimitPrice != null && !"0".equals(this.wLimitPrice))
            {
                throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_01877,
                this.getClass().getName() + "validate");
            }
            
            //�Ethis.W�w�l�p���s������null 
            //�@@�@@�u���������敪���g�w��Ȃ��h�̏ꍇ�́A�v�w�l�p���s�������w��s�v�̗�O���X���[�B 
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
        if (WEB3OrderingConditionDef.STOP_LIMIT_PRICE.equals(this.orderCondType))
        {
            if (this.wlimitOrderCondPrice != null && !"0".equals(this.wlimitOrderCondPrice))
            {
                throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_01878,
                this.getClass().getName() + "validate");
            }

            if (this.wlimitOrderCondOperator != null)
            {
                throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_01879,
                this.getClass().getName() + "validate");
            }

            if (this.wLimitOrderPriceDiv != null)
            {
                throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_01880,
                this.getClass().getName() + "validate");
            }

            if (this.wLimitPrice != null && !"0".equals(this.wLimitPrice))
            {
                throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_01881,
                this.getClass().getName() + "validate");
            }
            
            //�Ethis.W�w�l�p���s������null 
            //�@@�@@�u���������敪���g�t�w�l�h�̏ꍇ�́A�v�w�l�p���s�������w��s�v�̗�O���X���[�B 
            if (this.wlimitExecCondType != null)
            {
                log.debug("���������敪���g�t�w�l�h�̏ꍇ�́A�v�w�l�p���s�������w��s�ł��B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02526,
                    this.getClass().getName() + "." +  STR_METHOD_NAME,
                    "���������敪���g�t�w�l�h�̏ꍇ�́A�v�w�l�p���s�������w��s�ł��B");
            }
        }

        // �@@�P�P�|�Q�jthis.���������敪���h1�F�t�w�l�h
        if (WEB3OrderingConditionDef.STOP_LIMIT_PRICE.equals(this.orderCondType))
        {
            if (this.stopOrderCondPrice == null)
            {
                throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00215,
                this.getClass().getName() + "validate");
            }

            if (!WEB3StringTypeUtility.isNumber(this.stopOrderCondPrice))
            {
                throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00216,
                this.getClass().getName() + "validate");
            }

            if (Long.parseLong(this.stopOrderCondPrice) <= 0)
            {
                throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00216,
                this.getClass().getName() + "validate");
            }

            if (this.stopOrderCondPrice.length() > 8)
            {
                throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00216,
                this.getClass().getName() + "validate");
            }

            if (this.stopOrderCondOperator == null)
            {
                throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00219,
                this.getClass().getName() + "validate");
            }

            if (!WEB3OrderConditionOperatorDef.ABOVE_BASE_PRICE.equals(this.stopOrderCondOperator)
                    && !WEB3OrderConditionOperatorDef.BELOW_BASE_PRICE.equals(this.stopOrderCondOperator))
            {
                throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00220,
                this.getClass().getName() + "validate");
            }
        }

        // �P�Q�j�@@���������̃`�F�b�N�R�i�h2�FW�w�l�h�j
        if (WEB3OrderingConditionDef.W_LIMIT_PRICE.equals(this.orderCondType))
        {
            if (this.stopOrderCondPrice != null && !"0".equals(this.stopOrderCondPrice))
            {
                throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_01882,
                this.getClass().getName() + "validate");
            }

            if (this.stopOrderCondOperator != null)
            {
                throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_01883,
                this.getClass().getName() + "validate");
            }
        }

        if (WEB3OrderingConditionDef.W_LIMIT_PRICE.equals(this.orderCondType))
        {
            if (this.wlimitOrderCondPrice == null)
            {
                throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00223,
                this.getClass().getName() + "validate");
            }

            if (!WEB3StringTypeUtility.isNumber(this.wlimitOrderCondPrice))
            {
                throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00224,
                this.getClass().getName() + "validate");
            }

            if (Long.parseLong(this.wlimitOrderCondPrice) <= 0)
            {
                throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00224,
                this.getClass().getName() + "validate");
            }

            if (this.wlimitOrderCondPrice.length() > 8)
            {
                throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00224,
                this.getClass().getName() + "validate");
            }

            if (this.wlimitOrderCondOperator == null)
            {
                throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00227,
                this.getClass().getName() + "validate");
            }

            if (!WEB3OrderConditionOperatorDef.ABOVE_BASE_PRICE.equals(this.wlimitOrderCondOperator)
                    && !WEB3OrderConditionOperatorDef.BELOW_BASE_PRICE.equals(this.wlimitOrderCondOperator))
            {
                throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00228,
                this.getClass().getName() + "validate");
            }

            if (this.wLimitOrderPriceDiv == null)
            {
                throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00229,
                this.getClass().getName() + "validate");
            }

            if (!WEB3OrderPriceDivDef.MARKET_PRICE.equals(this.wLimitOrderPriceDiv)
                    && !WEB3OrderPriceDivDef.LIMIT_PRICE.equals(this.wLimitOrderPriceDiv))
            {
                throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00230,
                this.getClass().getName() + "validate");
            }
            
            //�@@�@@�Ethis�D�v�w�l�p���s������null 
            //�@@�@@�@@�@@�u�v�w�l�p���s���������w��v�̗�O���X���[�B
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
            //�@@�@@�@@�����L�̒l�ȊO�̏ꍇ�A�uW�w�l�����̎��s�����́g�������h�A
            //�g�s�o���������s�h�ȊO�w��s�B�v�̗�O���X���[�B 
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

		// �P�R�j�@@�v�w�l�p�����P���`�F�b�N
		if (WEB3OrderingConditionDef.W_LIMIT_PRICE.equals(this.orderCondType)
				&& WEB3OrderPriceDivDef.MARKET_PRICE.equals(this.wLimitOrderPriceDiv)
				&& this.wLimitPrice != null)
		{
			throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00124,
			this.getClass().getName() + "validate");
		}

		if (WEB3OrderingConditionDef.W_LIMIT_PRICE.equals(this.orderCondType)
				&& WEB3OrderPriceDivDef.LIMIT_PRICE.equals(this.wLimitOrderPriceDiv))
		{
			if (this.wLimitPrice == null)
			{
				throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00313,
				this.getClass().getName() + "validate");
			}
    
			if (!WEB3StringTypeUtility.isNumber(this.wLimitPrice))
			{
				throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00314,
				this.getClass().getName() + "validate");
			}
    
			if (Long.parseLong(this.wLimitPrice) <= 0)
			{
				throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00314,
				this.getClass().getName() + "validate");
			}    

			if (this.wLimitPrice.length() > 8)
			{
				throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00314,
				this.getClass().getName() + "validate");
			}    
		}

        // �P�S�j�@@���������`�F�b�N
        if (this.orderQuantity != null)
        {
            if (!WEB3StringTypeUtility.isNumber(this.orderQuantity))
            {
                throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00075,
                this.getClass().getName() + "validate");
            }

            if (Long.parseLong(this.orderQuantity) <= 0)
            {

                throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00076,
                this.getClass().getName() + "validate");
            }    

            if (this.orderQuantity.length() > 8)
            {
                throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00077,
                this.getClass().getName() + "validate");
            }   
        }
        
        // �P�T�j�@@�l�i�����E�����P���敪�̃`�F�b�N 
        if(!WEB3PriceConditionDef.DEFAULT.equals(this.priceCondType)
            && !WEB3OrderPriceDivDef.MARKET_PRICE.equals(this.orderPriceDiv))
        {
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_01348,
            this.getClass().getName() + "validate");
        }

        // �P�U�j�@@�l�i�����E���s�����̃`�F�b�N 
        if(WEB3PriceConditionDef.PARTIALLY_LIMIT_PRICE_ORDER.equals(this.priceCondType)
            || WEB3PriceConditionDef.PARTIALLY_CANCEL_ORDER.equals(this.priceCondType))
        {
            if(!WEB3ExecutionConditionDef.NO_CONDITION.equals(this.execCondType))
            {
                throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_01349,
                this.getClass().getName() + "validate");
            }
        }

        // �P�V�j�@@�l�i�����E���������敪�̃`�F�b�N 
        if(!WEB3PriceConditionDef.DEFAULT.equals(this.priceCondType)
            && !WEB3OrderExpirationDateTypeDef.DAY_LIMIT.equals(this.expirationDateType))
        {
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_01350,
            this.getClass().getName() + "validate");
        }

        // �P�W�j�@@�l�i�����E���������敪�̃`�F�b�N 
        if(!WEB3PriceConditionDef.DEFAULT.equals(this.priceCondType)
            && !WEB3OrderingConditionDef.DEFAULT.equals(this.orderCondType))
        {
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_01351,
            this.getClass().getName() + "validate");
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
        //�@@�@@this.���s�������ȉ��̒l�ȊO�̏ꍇ�A��O���X���[����B 
        //�@@�@@�@@�E1�F������ 
        //�@@�@@�@@�E7�F�s�o���������s 
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
     * <p>�icreate���X�|���X�j�B</p>
     * <p>null��Ԃ��B</p>
     * @@return null
     */
    public WEB3GenResponse createResponse() 
    {
        return null;
    }
}
@
