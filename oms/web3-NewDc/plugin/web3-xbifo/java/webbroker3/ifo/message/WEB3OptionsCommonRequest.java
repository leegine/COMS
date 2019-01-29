head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.20.55;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3OptionsCommonRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����w���I�v�V�������ʓ��̓��N�G�X�g(WEB3OptionsCommonRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/10 ���� (���u) �V�K�쐬
              001: 2004/07/22 ���Ō� (���u) WEB3OrderPriceDivDef��WEB3IfoOrderPriceDivDef�������ւ���
              002: 2004/07/22 ���Ō� (���u) WEB3ExecutionConditionDef��WEB3IfoExecCondTypeDef�������ւ���
              003: 2004/07/22 ���Ō� (���u) WEB3OrderExpirationDateTypeDef��WEB3IfoExpirationDateTypeDef�������ւ���
              004: 2004/07/22 ���Ō� (���u) WEB3OrderingConditionDef��WEB3OrderingConditionDef�������ւ���
              006: 2004/07/22 ���Ō� (���u) WEB3OrderingConditionDef��WEB3IfoStopOrderCondOperatorDef��WEB3IfoWlimitOrderCondOperatorDef�������ւ���
              007: 2004/07/23 ���Ō� (���u) WEB3OrderConditionOperatorDef��WEB3IfoStopOrderCondOperatorDef��WEB3IfoWlimitOrderCondOperatorDef�������ւ���
              008: 2004/07/23 ���Ō� (���u) WEB3OrderPriceDivDef��WEB3IfoWLimitOrderPriceDivDef�������ւ���
              009: 2006/07/12 �����F (���u) �Ή��d�l�ύX�@@454,462,470,517
              010: 2006/07/12 �����F (���u) �Ή��d�l�ύX�@@533
              011: 2006/08/03 �����F (���u) �Ή��d�l�ύX�@@538
              012: 2006/08/10 �s�p(���u)�@@���f�� 541
Revesion History : 2007/06/08 �����F(���u) ���f�� 639�A644
Revesion History : 2008/03/11 ����(���u) ���f�� 826
*/

package webbroker3.ifo.message;

import java.util.Date;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3ExecutionConditionDef;
import webbroker3.common.define.WEB3OrderConditionOperatorDef;
import webbroker3.common.define.WEB3OrderExpirationDateTypeDef;
import webbroker3.common.define.WEB3OrderPriceDivDef;
import webbroker3.common.define.WEB3OrderingConditionDef;
import webbroker3.common.define.WEB3StopBasePriceTypeDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.ifo.define.WEB3IfoWLimitEnableStatusDivDef;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;


/**
 * (�����w���I�v�V�������ʓ��̓��N�G�X�g)<BR>
 * �����w���I�v�V�������ʓ��̓��N�G�X�g�N���X<BR>
 * @@author ����
 * @@version 1.0
 */
public class WEB3OptionsCommonRequest extends WEB3GenRequest
{
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3OptionsCommonRequest.class);

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "options_common";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200406111702L;

    /**
     * (��������)<BR>
     * �ꊇ�ԍώ��Ɂu�����_�����[�h�v�̏ꍇ�͐ݒ肳��Ȃ�<BR>
     */
    public String opOrderQuantity;

    /**
     * (�����P���敪)<BR>
     * 0�F���s�@@1�F�w�l<BR>
     */
    public String orderPriceDiv;

    /**
     * (�����P��)<BR>
     * �����P���敪���u�w�l�v�̏ꍇ�ɐݒ�<BR>
     */
    public String limitPrice;

    /**
     * (���s����)<BR>
     * 1�F������ 3�F��t 4�F���� 7�F�s�o���������s<BR>
     */
    public String execCondType;

    /**
     * (���������敪)<BR>
     * 1�F��������@@2�F�o����܂Œ����@@3�F�[��܂Œ���<BR>
     */
    public String expirationDateType;

    /**
     * (�����L������)<BR>
     * ���������敪���u�o����܂Œ����v�̏ꍇ�ɐݒ�<BR>
     */
    public Date expirationDate;

    /**
     * (���������敪)<BR>
     * 0�F�w��Ȃ��@@1�F�t�w�l�@@2�FW�w�l<BR>
     */
    public String orderCondType;

    /**
     * (�t�w�l�p�v���~�A���^�����Y���i)<BR>
     * 0�FDEFAULT�i�����j�i*�j�敨�n�o�̏ꍇ�͌����Y����<BR>
     * 1�F�v���~�A��<BR>
     * ���������敪���A�u1�F�t�w�l�v�̏ꍇ�ݒ肳���<BR>
     */
    public String stopPremium_underlyingAssets;

    /**
     * (�t�w�l�p���������P��)<BR>
     * ���������敪���A�u1�F�t�w�l�v�̏ꍇ�ݒ肳���<BR>
     */
    public String stopOrderCondPrice;

    /**
     * (�t�w�l�p�����������Z�q)<BR>
     * 1�F�ȏ�@@2�F�ȉ�<BR>
     * ���������敪���A�u1�F�t�w�l�v�̏ꍇ�ݒ肳���<BR>
     */
    public String stopOrderCondOperator;

    /**
     * (�v�w�l�p�v���~�A���^�����Y���i)<BR>
     * 0�FDEFAULT�i�����j�i*�j�敨�n�o�̏ꍇ�͌����Y����<BR>
     * 1�F�v���~�A��<BR>
     * ���������敪���A�u2�FW�w�l�v�̏ꍇ�ݒ肳���<BR>
     */
    public String wlimitPremium_underlyingAssets;

    /**
     * (�v�w�l�p���������P��)<BR>
     * ���������敪���A�u2�FW�w�l�v�̏ꍇ�ݒ肳���<BR>
     */
    public String wlimitOrderCondPrice;

    /**
     * (�v�w�l�p�����������Z�q)<BR>
     * 1�F�ȏ�@@2�F�ȉ�<BR>
     * ���������敪���A�u2�FW�w�l�v�̏ꍇ�ݒ肳���<BR>
     */
    public String wlimitOrderCondOperator;

    /**
     * (�v�w�l�p�����P���敪)<BR>
     * 0�F���s�@@1�F�w�l<BR>
     * ���������敪���A�u2�FW�w�l�v�̏ꍇ�ݒ肳���<BR>
     */
    public String wLimitOrderPriceDiv;

    /**
     * (�v�w�l�p�����P��)<BR>
     * �v�w�l�p�����P���敪���A�u1�F�w�l�v�̏ꍇ�ݒ肳���<BR>
     */
    public String wLimitPrice;
    
    /**
     * (W�w�l�p���s����)<BR>
     * <BR>
     * 1�F������ 3�F��t 4�F���� 7�F�s�o���������s<BR>
     * ���������敪���A�u2�FW�w�l�v�̏ꍇ�ݒ肳���<BR>
     */
    public String wlimitExecCondType;
    
    /**
     * (W�w�l�p�L����ԋ敪)<BR>
     * <BR>
     * 0�F���~�b�g�����L���@@1�F�X�g�b�v�����L��<BR>
     * 2�F�X�g�b�v����������<BR>
     * <BR>
     * ���������̂݃Z�b�g�B<BR>
     */
    public String wlimitEnableStatusDiv;

    /**
     * @@roseuid 40C0A60F02DE
     */
    public WEB3OptionsCommonRequest()
    {

    }

    /**
     * �����N�G�X�g�f�[�^�̐������`�F�b�N���s���B<BR>
     * �i�������A���N���X���Ŋ�������ȈՃ`�F�b�N�݂̂Ƃ���B�j<BR>
     * <BR>
     * �P�j�@@�����P���敪�`�F�b�N<BR>
     * �@@�P�|�P�jthis.�����P���敪��null�̒l�ł���Η�O���X���[����B<BR>
     *     class: WEB3BusinessLayerException<BR>
     *     tag:   BUSINESS_ERROR_00184<BR>
     * �@@�P�|�Q�jthis.�����P���敪���ȉ��̒l�ȊO�̏ꍇ��O���X���[����B<BR>
     * �@@�@@�@@�@@�E�h0�F���s�h<BR>
     * �@@�@@�@@�@@�E�h1�F�w�l�h<BR>
     *     class: WEB3BusinessLayerException<BR>
     *     tag:   BUSINESS_ERROR_00185<BR>
     * <BR>
     * �Q�j�@@�����P���`�F�b�N<BR>
     * �@@�Q�|�P�jthis.�����P���敪���h0�F���s�h�ł��Athis.�����P����<BR>
     * �@@�@@�@@�@@null�ȊO�̒l�ł���Η�O���X���[����B<BR>
     *     class: WEB3BusinessLayerException<BR>
     *     tag:   BUSINESS_ERROR_00116<BR>
     * �@@�Q�|�Q�jthis.�����P���敪���h1�F�w�l�h�ł��Athis.�����P����<BR>
     * �@@�@@�@@�@@null�̒l�ł���Η�O���X���[����B<BR>
     *     class: WEB3BusinessLayerException<BR>
     *     tag:   BUSINESS_ERROR_00187<BR>
     * �@@�Q�|�R�jthis.�����P���敪���h1�F�w�l�h�ł��Athis.�����P����<BR>
     * �@@�@@�@@�@@�����ȊO�̒l�ł���Η�O���X���[����B<BR>
     *     class: WEB3BusinessLayerException<BR>
     *     tag:   BUSINESS_ERROR_00188<BR>
     * �@@�Q�|�S�jthis.�����P���敪���h1�F�w�l�h�ł��Athis.�����P����<BR>
     * �@@�@@�@@�@@�O�ȉ��̒l�ł���Η�O���X���[����B<BR>
     *     class: WEB3BusinessLayerException<BR>
     *     tag:   BUSINESS_ERROR_00189<BR>
     * �@@�Q�|�T�jthis.�����P���敪���h1�F�w�l�h�ł��Athis.�����P����<BR>
     * �@@�@@�@@�@@�V���𒴂���l�ł���Η�O���X���[����B<BR>
     *     class: WEB3BusinessLayerException<BR>
     *     tag:   BUSINESS_ERROR_00190<BR>
     * <BR>
     * �R�j�@@���s�����`�F�b�N<BR>
     * �@@�R�|�P�jthis.���s������null�̒l�ł���Η�O���X���[����B<BR>
     *     class: WEB3BusinessLayerException<BR>
     *     tag:   BUSINESS_ERROR_00197<BR>
     * �@@�R�|�Q�jthis.���s�������ȉ��̒l�ȊO�̏ꍇ��O���X���[����B<BR>
     * �@@�@@�@@�@@�E�h1�F�������h<BR>
     * �@@�@@�@@�@@�E�h3:��t�h<BR>
     * �@@�@@�@@�@@�E�h4:�����h<BR>
     * �@@�@@�@@�@@�E�h7:�s�o���������s�h<BR>
     *     class: WEB3BusinessLayerException<BR>
     *     tag:   BUSINESS_ERROR_00127<BR>
     * <BR>
     * �S�j�@@���s�����E�����P���敪�`�F�b�N<BR>
     * �@@this.���s�������h7:�s�o���������s�h�ł��A<BR>
     * �@@this.�����P���敪���h1�F�w�l�h�ȊO�̒l�ł���Η�O���X���[����B<BR>
     *     class: WEB3BusinessLayerException<BR>
     *     tag:   BUSINESS_ERROR_00198<BR>
     * <BR>
     * �T�j�@@���������敪�`�F�b�N<BR>
     * �@@�T�|�P�jthis.���������敪��null�̒l�ł���Η�O���X���[����B<BR>
     *       class: WEB3BusinessLayerException<BR>
     *       tag:   BUSINESS_ERROR_00208<BR>
     * �@@�T�|�Q�jthis.���������敪���ȉ��̒l�ȊO�̏ꍇ��O���X���[����B<BR>
     * �@@�@@�@@�@@�E�h1�F��������h<BR>
     * �@@�@@�@@�@@�E�h2�F�o����܂Œ����h<BR>
     * �@@�@@�@@�@@�E�h3�F�[��܂Œ����h <BR>
     *       class: WEB3BusinessLayerException<BR>
     *       tag:   BUSINESS_ERROR_00209<BR>
     * <BR>
     * �U�j�@@���������敪�E���s�����`�F�b�N<BR>
     * �@@�U�|�P�jthis.���������敪���h2�F�o����܂Œ����h�ł��A <BR>
     * �@@�@@�@@�@@�@@this.���s�������h1�F�������h�ȊO�̒l�ł���Η�O���X���[����B<BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag:   BUSINESS_ERROR_00125<BR>
     * �@@�U�|�Q�jthis.���������敪���h3�F�[��܂Œ����h�ł��A <BR>
     * �@@�@@�@@�@@�@@this.���s�������h1�F�������h�ȊO�̒l�ł���Η�O���X���[����B<BR>
     * �@@class: WEB3BusinessLayerException<BR>
     * �@@tag:   BUSINESS_ERROR_02817<BR>
     * <BR>
     * �V�j�@@�����L�������`�F�b�N<BR>
     * �@@�V�|�P�jthis.���������敪���h1�F��������h�ł��A<BR>
     *�@@�@@�@@�@@�@@ this.�����L��������null�ł���Η�O���X���[����B<BR>
     *           class: WEB3BusinessLayerException<BR>
     *           tag:   BUSINESS_ERROR_00117<BR>
     * �@@�V�|�Q�jthis.���������敪���h2�F�o����܂Œ����h�ł��A<BR>
     * �@@�@@�@@�@@�@@this.�����L��������null�̒l�ł���Η�O���X���[����B<BR>
     * �@@�@@�@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@�@@tag:   BUSINESS_ERROR_00210<BR>
     * �@@�V�|�R�jthis.���������敪���h3�F�[��܂Œ����h�ł��A<BR>
     * �@@�@@�@@�@@�@@this.�����L��������null�ł���Η�O���X���[����<BR>
     * �@@�@@�@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@�@@tag:   BUSINESS_ERROR_02818<BR>
     * <BR>
     * �W�j�@@���������敪�`�F�b�N<BR>
     * �@@�W�|�P�jthis.���������敪��null�̒l�ł���Η�O���X���[����B<BR>
     *     class: WEB3BusinessLayerException<BR>
     *     tag:   BUSINESS_ERROR_00211<BR>
     * �@@�W�|�Q�jthis.���������敪���ȉ��̒l�ȊO�̏ꍇ��O���X���[����B<BR>
     * �@@�@@�@@�@@�E�h0�F�w��Ȃ��h<BR>
     * �@@�@@�@@�@@�E�h1�F�t�w�l�h<BR>
     * �@@�@@�@@�@@�E�h2�FW�w�l�h<BR>
     *       class: WEB3BusinessLayerException<BR>
     *       tag:   BUSINESS_ERROR_00212<BR>
     * <BR>
     * �X�j�@@�t�w�l�p�v���~�A���^�����Y���i�`�F�b�N<BR>
     * �@@�X�|�P�jthis.���������敪���h1�F�t�w�l�h�̒l�ł��A<BR>
     * �@@�@@�@@�@@this.�t�w�l�p�v���~�A���^�����Y���i��null�̒l�ł����<BR>
     * �@@�@@�@@�@@��O���X���[����B<BR>
     *       class: WEB3BusinessLayerException<BR>
     *       tag:   BUSINESS_ERROR_00213<BR>
     * �@@�X�|�Q�jthis.���������敪���h1�F�t�w�l�h�̒l�ł��A<BR>
     * �@@�@@�@@�@@this.�t�w�l�p�v���~�A���^�����Y���i��<BR>
     * �@@�@@�@@�@@�ȉ��̒l�ȊO�̏ꍇ��O���X���[����B<BR>
     * �@@�@@�@@�@@�E�h0�F�����Y���i�h<BR>
     * �@@�@@�@@�@@�E�h1�F�v���~�A���h<BR>
     *       class: WEB3BusinessLayerException<BR>
     *       tag:   BUSINESS_ERROR_00214<BR>
     * �@@�X�|�R�jthis.���������敪���h0�F�w��Ȃ��h�̒l�ł��A<BR>
     *�@@�@@�@@�@@this.�t�w�l�p�v���~�A���^�����Y���i��null�ł����<BR>
     *�@@�@@�@@�@@��O���X���[����B<BR>
     *           class: WEB3BusinessLayerException<BR>
     *           tag:   BUSINESS_ERROR_01922<BR>
     *�@@�X�|�S�jthis.���������敪���h2�FW�w�l�h�̒l�ł��A<BR>
     *�@@�@@�@@�@@this.�t�w�l�p�v���~�A���^�����Y���i��null�ł����<BR>
     *�@@�@@�@@�@@��O���X���[����B<BR>
     *           class: WEB3BusinessLayerException<BR>
     *           tag:   BUSINESS_ERROR_01923<BR>
     * <BR>
     * �P�O�j�@@�t�w�l�p���������P���`�F�b�N<BR>
     * �@@�P�O�|�P�jthis.���������敪���h1�F�t�w�l�h�̒l�ł��A<BR>
     * �@@�@@�@@�@@this.�t�w�l�p���������P����null�̒l��<BR>
     * �@@�@@�@@�@@����Η�O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00215<BR>
     * �@@�P�O�|�Q�jthis.���������敪���h1�F�t�w�l�h�̒l�ł��A<BR>
     * �@@�@@�@@�@@this.�t�w�l�p���������P���������ȊO�̒l��<BR>
     * �@@�@@�@@�@@����Η�O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00216<BR>
     * �@@�P�O�|�R�jthis.���������敪���h1�F�t�w�l�h�̒l�ł��A<BR>
     * �@@�@@�@@�@@this.�t�w�l�p���������P�����O�ȉ��̒l��<BR>
     * �@@�@@�@@�@@����Η�O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00216<BR>
     * �@@�P�O�|�S�jthis.���������敪���h1�F�t�w�l�h�̒l�ł��A<BR>
     * �@@�@@�@@�@@this.�t�w�l�p���������P�����V���𒴂���l��<BR>
     * �@@�@@�@@�@@����Η�O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00216<BR>
     * �@@�P�O�|�T�jthis.���������敪���h0�F�w��Ȃ��h�̒l�ł��A<BR>
     *�@@�@@�@@�@@this.�t�w�l�p���������P����(null�܂���0)��<BR>
     *�@@�@@�@@�@@����Η�O���X���[����B<BR>
     *           class: WEB3BusinessLayerException<BR>
     *           tag:   BUSINESS_ERROR_01872<BR>
     * �@@�P�O�|�U�jthis.���������敪���h2�FW�w�l�h�̒l�ł��A<BR>
     *�@@�@@�@@�@@this.�t�w�l�p���������P����(null�܂���0)��<BR>
     *�@@�@@�@@�@@����Η�O���X���[����B<BR>
     *           class: WEB3BusinessLayerException<BR>
     *           tag:   BUSINESS_ERROR_01882<BR>
     * <BR>
     * �P�P�j�@@�t�w�l�p�����������Z�q�`�F�b�N<BR>
     * �@@�P�P�|�P�jthis.���������敪���h1�F�t�w�l�h�̒l�ł��A<BR>
     * �@@�@@�@@�@@this.�t�w�l�p�����������Z�q��null�̒l��<BR>
     * �@@�@@�@@�@@����Η�O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00219<BR>
     * �@@�P�P�|�Q�jthis.���������敪���h1�F�t�w�l�h�̒l�ł��A<BR>
     * �@@�@@�@@�@@this.�t�w�l�p�����������Z�q���ȉ��̒l�ȊO�̏ꍇ<BR>
     * �@@�@@�@@�@@��O���X���[����B<BR>
     * �@@�@@�@@�@@�E�h1�F�ȏ�h<BR>
     * �@@�@@�@@�@@�E�h2�F�ȉ��h<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00220<BR>
     * �@@�P�P�|�R�jthis.���������敪���h0�F�w��Ȃ��h�̒l�ł��A<BR>
     *�@@�@@�@@�@@this.�t�w�l�p�����������Z�q��null��<BR>
     *�@@�@@�@@�@@����Η�O���X���[����B<BR>
     *           class: WEB3BusinessLayerException<BR>
     *           tag:   BUSINESS_ERROR_01873<BR>
     *�@@ �P�P�|�S�jthis.���������敪���h2�FW�w�l�h�̒l�ł��A<BR>
     *�@@�@@�@@�@@this.�t�w�l�p�����������Z�q��null��<BR>
     *�@@�@@�@@�@@����Η�O���X���[����B<BR>
     *           class: WEB3BusinessLayerException<BR>
     *           tag:   BUSINESS_ERROR_01883<BR>
     * <BR>
     * �P�Q�j�@@�v�w�l�p�v���~�A���^�����Y���i�`�F�b�N<BR>
     * �@@�P�Q�|�P�jthis.���������敪���h2�FW�w�l�h�̒l�ł��A<BR>
     * �@@�@@�@@�@@this.�v�w�l�p�v���~�A���^�����Y���i��null�̒l�ł����<BR>
     * �@@�@@�@@�@@��O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00221<BR>
     * �@@�P�Q�|�Q�jthis.���������敪���h2�FW�w�l�h�̒l�ł��A<BR>
     * �@@�@@�@@�@@this.�v�w�l�p�v���~�A���^�����Y���i��<BR>
     * �@@�@@�@@�@@�ȉ��̒l�ȊO�̏ꍇ��O���X���[����B<BR>
     * �@@�@@�@@�@@�E�h0�F�����Y���i�h<BR>
     * �@@�@@�@@�@@�E�h1�F�v���~�A���h<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00222<BR>
     * �@@�P�Q�|�R�jthis.���������敪���h0�F�w��Ȃ��h�̒l�ł��A<BR>
     *�@@�@@�@@�@@this.�v�w�l�p�v���~�A���^�����Y���i��null�ł����<BR>
     *�@@�@@�@@�@@��O���X���[����B<BR>
     *           class: WEB3BusinessLayerException<BR>
     *           tag:   BUSINESS_ERROR_01924<BR>
     * �@@�P�Q�|�S�jthis.���������敪���h1�F�t�w�l�h�̒l�ł��A<BR>
     *�@@�@@�@@�@@this.�v�w�l�p�v���~�A���^�����Y���i��null�ł����<BR>
     *�@@�@@�@@�@@��O���X���[����B<BR>
     *           class: WEB3BusinessLayerException<BR>
     *           tag:   BUSINESS_ERROR_01925<BR>
     * <BR>
     * �P�R�j�@@�v�w�l�p���������P���`�F�b�N<BR>
     * �@@�P�R�|�P�jthis.���������敪���h2�FW�w�l�h�̒l�ł��A<BR>
     * �@@�@@�@@�@@this.�v�w�l�p���������P����null�̒l�ł����<BR>
     * �@@�@@�@@�@@��O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00223<BR>
     * �@@�P�R�|�Q�jthis.���������敪���h2�FW�w�l�h�̒l�ł��A<BR>
     * �@@�@@�@@�@@this.�v�w�l�p���������P���������ȊO�̒l�ł����<BR>
     * �@@�@@�@@�@@��O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00224<BR>
     * �@@�P�R�|�R�jthis.���������敪���h2�FW�w�l�h�̒l�ł��A<BR>
     * �@@�@@�@@�@@this.�v�w�l�p���������P�����O�ȉ��̒l�ł����<BR>
     * �@@�@@�@@�@@��O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00224<BR>
     * �@@�P�R�|�S�jthis.���������敪���h2�FW�w�l�h�̒l�ł��A<BR>
     * �@@�@@�@@�@@this.�v�w�l�p���������P�����V���𒴂���l�ł����<BR>
     * �@@�@@�@@�@@��O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00224<BR>
     * �@@�P�R�|�T�jthis.���������敪���h0�F�w��Ȃ��h�̒l�ł��A<BR>
     *�@@�@@�@@�@@this.�v�w�l�p���������P����(null�܂���0)�ł����<BR>
     *�@@�@@�@@�@@��O���X���[����B<BR>
     *           class: WEB3BusinessLayerException<BR>
     *           tag:   BUSINESS_ERROR_01874<BR>
     * �@@�P�R�|�U�jthis.���������敪���h1�F�t�w�l�h�̒l�ł��A<BR>
     *�@@�@@�@@�@@this.�v�w�l�p���������P����(null�܂���0)�ł����<BR>
     *�@@�@@�@@�@@��O���X���[����B<BR>
     *           class: WEB3BusinessLayerException<BR>
     *           tag:   BUSINESS_ERROR_01878<BR>
     * <BR>
     * �P�S�j�@@�v�w�l�p�����������Z�q�`�F�b�N<BR>
     * �@@�P�S�|�P�jthis.���������敪���h2�FW�w�l�h�̒l�ł��A<BR>
     * �@@�@@�@@�@@this.�v�w�l�p�����������Z�q��null�̒l�ł����<BR>
     * �@@�@@�@@�@@��O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00227<BR>
     * �@@�P�S�|�Q�jthis.���������敪���h2�FW�w�l�h�̒l�ł��A<BR>
     * �@@�@@�@@�@@this.�v�w�l�p�����������Z�q���ȉ��̒l�ȊO�̏ꍇ<BR>
     * �@@�@@�@@�@@��O���X���[����B<BR>
     * �@@�@@�@@�@@�E�h1�F�ȏ�h<BR>
     * �@@�@@�@@�@@�E�h2�F�ȉ��h<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00228<BR>
     * �@@�P�S�|�R�jthis.���������敪���h0�F�w��Ȃ��h�̒l�ł��A<BR>
     *�@@�@@�@@�@@this.�v�w�l�p�����������Z�q��null�ł����<BR>
     *�@@�@@�@@�@@��O���X���[����B<BR>
     *           class: WEB3BusinessLayerException<BR>
     *           tag:   BUSINESS_ERROR_01875<BR>
     *�@@ �P�S�|�S�jthis.���������敪���h1�F�t�w�l�h�̒l�ł��A<BR>
     *�@@�@@�@@�@@this.�v�w�l�p�����������Z�q��null�ł����<BR>
     *�@@�@@�@@�@@��O���X���[����B<BR>
     *           class: WEB3BusinessLayerException<BR>
     *           tag:   BUSINESS_ERROR_01879<BR>
     * <BR>
     * �P�T�j�@@�v�w�l�p�����P���敪�`�F�b�N<BR>
     * �@@�P�T�|�P�jthis.���������敪���h2�FW�w�l�h�̒l�ł��A<BR>
     * �@@�@@�@@�@@this.�v�w�l�p�����P���敪��null�̒l�ł����<BR>
     * �@@�@@�@@�@@��O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00229<BR>
     * �@@�P�T�|�Q�jthis.���������敪���h2�FW�w�l�h�̒l�ł��A<BR>
     * �@@�@@�@@�@@this.�v�w�l�p�����P���敪���ȉ��̒l�ȊO�̏ꍇ<BR>
     * �@@�@@�@@�@@��O���X���[����B<BR>
     * �@@�@@�@@�@@�E�h0�F���s�h<BR>
     * �@@�@@�@@�@@�E�h1�F�w�l�h<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00230<BR>
     * �@@�P�T�|�R�jthis.���������敪���h0�F�w��Ȃ��h�̒l�ł��A<BR>
     *�@@�@@�@@�@@this.�v�w�l�p�����P���敪��null�ł����<BR>
     *�@@�@@�@@�@@��O���X���[����B<BR>
     *           class: WEB3BusinessLayerException<BR>
     *           tag:   BUSINESS_ERROR_01876<BR>
     * �@@�P�T�|�S�jthis.���������敪���h1�F�t�w�l�h�̒l�ł��A<BR>
     *�@@�@@�@@�@@this.�v�w�l�p�����P���敪��null�ł����<BR>
     *�@@�@@�@@�@@��O���X���[����B<BR>
     *           class: WEB3BusinessLayerException<BR>
     *           tag:   BUSINESS_ERROR_01880<BR>
     * <BR>
     * �P�U�j�@@�v�w�l�p�����P���`�F�b�N<BR>
     * �@@�P�U�|�P�jthis.���������敪���h2�FW�w�l�h�̒l�ł��A<BR>
     * �@@�@@�@@�@@this.�v�w�l�p�����P���敪���h0�F���s�h�̒l�ł���<BR>
     * �@@�@@�@@�@@this.�v�w�l�p�����P����null�ȊO�̒l�ł����<BR>
     * �@@�@@�@@�@@��O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00124<BR>
     * �@@�P�U�|�Q�jthis.���������敪���h2�FW�w�l�h�̒l�ł��A<BR>
     * �@@�@@�@@�@@this.�v�w�l�p�����P���敪���h1�F�w�l�h�̒l�ł���<BR>
     * �@@�@@�@@�@@this.�v�w�l�p�����P����null�̒l�ł����<BR>
     * �@@�@@�@@�@@��O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00313<BR>
     * �@@�P�U�|�R�jthis.���������敪���h2�FW�w�l�h�̒l�ł��A<BR>
     * �@@�@@�@@�@@this.�v�w�l�p�����P���敪���h1�F�w�l�h�̒l�ł���<BR>
     * �@@�@@�@@�@@this.�v�w�l�p�����P���������ȊO�̒l�ł����<BR>
     * �@@�@@�@@�@@��O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00314<BR>
     * �@@�P�U�|�S�jthis.���������敪���h2�FW�w�l�h�̒l�ł��A<BR>
     * �@@�@@�@@�@@this.�v�w�l�p�����P���敪���h1�F�w�l�h�̒l�ł���<BR>
     * �@@�@@�@@�@@this.�v�w�l�p�����P�����O�ȉ��̒l�ł����<BR>
     * �@@�@@�@@�@@��O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00314<BR>
     * �@@�P�U�|�T�jthis.���������敪���h2�FW�w�l�h�̒l�ł��A<BR>
     * �@@�@@�@@�@@this.�v�w�l�p�����P���敪���h1�F�w�l�h�̒l�ł���<BR>
     * �@@�@@�@@�@@this.�v�w�l�p�����P�����V���𒴂���l�ł����<BR>
     * �@@�@@�@@�@@��O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00314<BR>
     *   �P�U�|�U�jthis.���������敪���h0�F�w��Ȃ��h�̒l�ł��A<BR>
     *�@@�@@�@@�@@this.�v�w�l�p�����P����(null�܂���0)�ł����<BR>
     *�@@�@@�@@�@@��O���X���[����B<BR>
     *           class: WEB3BusinessLayerException<BR>
     *           tag:   BUSINESS_ERROR_01877<BR>
     *�@@ �P�U�|�V�jthis.���������敪���h1�F�t�w�l�h�̒l�ł��A<BR>
     *�@@�@@�@@�@@this.�v�w�l�p�����P����(null�܂���0)�ł����<BR>
     *�@@�@@�@@�@@��O���X���[����B<BR>
     *�@@�@@�@@�@@�@@�@@class: WEB3BusinessLayerException<BR>
     *�@@�@@�@@�@@�@@�@@tag:   BUSINESS_ERROR_01881<BR>
     * <BR>
     * �P�V�j�@@���������E���s�����̃`�F�b�N <BR>
     * �@@�P7�|�P�jthis.����������"1�F�t�w�l"�̏ꍇ�A <BR>
     * �@@�@@�@@�@@this.���s�������u1�F�������v�ł���Η�O���X���[����B<BR>
     * �@@�@@�@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@�@@tag:  BUSINESS_ERROR_02473 <BR>
     * �@@�P7�|�Q�j�@@this.����������"2�FW�w�l"�̏ꍇ�A <BR>
     * �@@�@@�@@�@@this.���s�������ȉ��̒l�ȊO�̏ꍇ�A��O���X���[����B <BR>
     * �@@�@@�@@�@@�E�h1�F�������h <BR>
     * �@@�@@�@@�@@�E�h7:�s�o���������s�h<BR>
     * �@@�@@�@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@�@@tag:  BUSINESS_ERROR_02500 <BR>
     *<BR>
     * �P8�j�@@�v�w�l�p���s�����`�F�b�N<BR>                      
     * �@@�P8�|�P�jthis.���������敪���h2�FW�w�l�h�̒l�ł��A<BR> 
     * �@@�@@�@@�@@this.�v�w�l�p���s������null�̒l�ł���Η�O���X���[����B<BR> 
     * �@@�@@�@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@�@@tag:   BUSINESS_ERROR_02499<BR>
     * �@@�P8�|�Q�jthis.���������敪���h2�FW�w�l�h�̒l�ł��A<BR> 
     * �@@�@@�@@�@@this.�v�w�l�p���s�������ȉ��̒l�ȊO�̏ꍇ�A��O���X���[����B<BR> 
     * �@@�@@�@@�@@�E�h1�F�������h<BR>                               
     * �@@�@@�@@�@@�E�h7:�s�o���������s�h<BR>   
     * �@@�@@�@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@�@@tag:  BUSINESS_ERROR_02500<BR>   
     * �@@�P�W�|�R�jthis.���������敪���h0�F�w��Ȃ��h�̒l�ł��A<BR>
     * �@@�@@�@@�@@this.�v�w�l�p���s������null�ł����<BR>
     * �@@�@@�@@�@@��O���X���[����B<BR>
     * �@@�@@�@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@�@@tag:  BUSINESS_ERROR_02525<BR>
     * �@@�P�W�|�S�jthis.���������敪���h1�F�t�w�l�h�̒l�ł��A<BR>
     * �@@�@@�@@�@@this.�v�w�l�p���s������null�ł����<BR>
     * �@@�@@�@@�@@��O���X���[����B<BR>                  
     * �@@�@@�@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@�@@tag:  BUSINESS_ERROR_02526<BR>
     * <BR>                                                    
     * �P9�j�@@�v�w�l�p���s�����E�����P���敪�`�F�b�N<BR>        
     * �@@�@@�@@�@@this.�v�w�l�p���s�������h7:�s�o���������s�h�ł��A<BR> 
     * �@@�@@�@@�@@this.�v�w�l�p�����P���敪���h1�F�w�l�h�ȊO�̒l�ł���Η�O���X���[����B<BR> 
     * �@@�@@�@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@�@@tag:  BUSINESS_ERROR_02501 <BR>
     *<BR>                                                      
     * 20�j�@@�v�w�l�p�L����ԋ敪�`�F�b�N<BR>                  
     * �@@�@@�@@�@@this.�v�w�l�p�L����ԋ敪��null�ł���΁A<BR>     
     * �@@�@@�@@�@@�ȉ��̏������s���B<BR>                            
     * �@@20�|�P�j�@@this.�v�w�l�L����ԋ敪���ȉ��̒l�ȊO�̏ꍇ�A<BR> 
     * �@@�@@�@@�@@��O���X���[����B<BR>                            
     * �@@�@@�@@�@@�E�h0�F���~�b�g�����L���h<BR>                     
     * �@@�@@�@@�@@�E�h1�F�X�g�b�v�����L���h<BR>                     
     * �@@�@@�@@�@@�E�h2�F�X�g�b�v���������ρh<BR>                   
     * �@@�@@�@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@�@@tag:   BUSINESS_ERROR_02502<BR>
     * <BR>
     * �Q�P�j�@@�v�w�l�p���s�����E�����L�������`�F�b�N<BR>
     * �@@�Q�P�|�P�jthis.���������敪���h2�FW�w�l�h�̒l�ł��A<BR>
     * �@@�@@�@@�@@�@@�@@this.���������敪���h2�F�o����܂Œ����h�ł��A<BR>
     * �@@�@@�@@�@@�@@�@@this.�v�w�l�p���s�������h1�F�������h�ȊO�̒l<BR>
     * �@@�@@�@@�@@�@@�@@�ł���Η�O���X���[����B<BR>
     * �@@�@@�@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@�@@tag:   BUSINESS_ERROR_02503<BR>
     * �@@�Q�P�|�Q�jthis.���������敪���h2�FW�w�l�h�̒l�ł��A<BR>
     * �@@�@@�@@�@@�@@�@@this.���������敪���h3�F�[��܂Œ����h�ł��A<BR>
     * �@@�@@�@@�@@�@@�@@this.�v�w�l�p���s�������h1�F�������h�ȊO�̒l<BR>
     * �@@�@@�@@�@@�@@�@@�ł���Η�O���X���[����B<BR>
     * �@@�@@�@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@�@@tag:   BUSINESS_ERROR_02503<BR>
     * @@throws WEB3BaseException
     * @@roseuid 4067D9D30270
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validate()";
        log.entering(STR_METHOD_NAME);

        //�P�j�@@�����P���敪�`�F�b�N
        //�@@�P�|�P�jthis.�����P���敪��null�̒l�ł���Η�O���X���[����B
        if (this.orderPriceDiv == null)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00184,
                getClass().getName() + "validate",
                "�����P���敪��null�̒l�ł���");
        }

        //�@@�P�|�Q�jthis.�����P���敪���ȉ��̒l�ȊO�̏ꍇ��O���X���[����B
        // �@@�@@�@@�@@�E�h0�F���s�h
        // �@@�@@�@@�@@�E�h1�F�w�l�h
        else if (!WEB3OrderPriceDivDef.MARKET_PRICE.equals(this.orderPriceDiv)
            && !WEB3OrderPriceDivDef.LIMIT_PRICE.equals(this.orderPriceDiv))
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00185,
                getClass().getName() + "validate",
                "�����P���敪���h0�F���s�h�܂��́A�h1�F�w�l�h�ȊO�ł���");
        }

        //�Q�j�@@�����P���`�F�b�N
        //�@@�Q�|�P�jthis.�����P���敪���h0�F���s�h�ł��Athis.�����P����
        //null�ȊO�̒l�ł���Η�O���X���[����B
        if (WEB3OrderPriceDivDef.MARKET_PRICE.equals(this.orderPriceDiv)
            && WEB3StringTypeUtility.isNotEmpty(this.limitPrice))
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00116,
                getClass().getName() + "validate",
                "�����P���敪���h0�F���s�h�̏ꍇ�A�����P�������͕s�ł�");
        }

        //�@@�Q�|�Q�jthis.�����P���敪���h1�F�w�l�h�ł��Athis.�����P����
        //null�̒l�ł���Η�O���X���[����B
        else if (WEB3OrderPriceDivDef.LIMIT_PRICE.equals(this.orderPriceDiv)
            && WEB3StringTypeUtility.isEmpty(this.limitPrice))
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00187,
                getClass().getName() + "validate",
                "�w�l�����̏ꍇ�A�w�l�����͕K�{�ł�");
        }

        //�@@�Q�|�R�jthis.�����P���敪���h1�F�w�l�h�ł��Athis.�����P����
        //�����ȊO�̒l�ł���Η�O���X���[����B
        else if (WEB3OrderPriceDivDef.LIMIT_PRICE.equals(this.orderPriceDiv)
            && !WEB3StringTypeUtility.isNumber(this.limitPrice))
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00188,
                getClass().getName() + "validate",
                "�����P���敪���h1�F�w�l�h�̏ꍇ�A�����P���������ȊO�ł���");
        }

        //�@@�Q�|�S�jthis.�����P���敪���h1�F�w�l�h�ł��Athis.�����P����
        //�O�ȉ��̒l�ł���Η�O���X���[����B
        else if (WEB3OrderPriceDivDef.LIMIT_PRICE.equals(this.orderPriceDiv)
            && Long.parseLong(this.limitPrice) <= 0)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00189,
                getClass().getName() + "validate",
                "�����P���敪���h1�F�w�l�h�̏ꍇ�A�����P�����O�ȉ��̒l�ł���");
        }

        //�@@�Q�|�T�jthis.�����P���敪���h1�F�w�l�h�ł��Athis.�����P����
        //�V���𒴂���l�ł���Η�O���X���[����B
        else if (WEB3OrderPriceDivDef.LIMIT_PRICE.equals(this.orderPriceDiv)
            && WEB3StringTypeUtility.getByteLength(this.limitPrice) > 7)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00190,
                getClass().getName() + "validate",
                "�����P���敪���h1�F�w�l�h�̏ꍇ�A�����P�����V���𒴂��܂����B");
        }

        //�R�j�@@���s�����`�F�b�N
        //�@@�R�|�P�jthis.���s������null�̒l�ł���Η�O���X���[����B
        if (WEB3StringTypeUtility.isEmpty(this.execCondType))
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00197,
                getClass().getName() + "validate",
                "���s������I�����Ă�������");
        }
        //�@@�R�|�Q�jthis.���s�������ȉ��̒l�ȊO�̏ꍇ��O���X���[����B
        //�h1�F�������h
        //�h3:��t�h
        //�h4:�����h
        //�h7:�s�o���������s�h
        else if (!WEB3ExecutionConditionDef.NO_CONDITION.equals(this.execCondType)
            && !WEB3ExecutionConditionDef.AT_MARKET_OPEN.equals(this.execCondType)
            && !WEB3ExecutionConditionDef.AT_MARKET_CLOSE.equals(this.execCondType)
            && !WEB3ExecutionConditionDef.AT_MARKET_CLOSE_NOT_EXECUTED.equals(this.execCondType))
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00127,
                getClass().getName() + "validate",
                "���s�����������Ȃ��A���A�����A�s�o���������s�ȊO�̏ꍇ�G���[�B");
        }

        //�S�j�@@���s�����E�����P���敪�`�F�b�N
        //this.���s�������h7:�s�o���������s�h�ł��A
        //this.�����P���敪���h1�F�w�l�h�ȊO�̒l�ł���Η�O���X���[����B
        if (WEB3ExecutionConditionDef.AT_MARKET_CLOSE_NOT_EXECUTED.equals(this.execCondType)
            && !WEB3OrderPriceDivDef.LIMIT_PRICE.equals(this.orderPriceDiv))
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00114,
                getClass().getName() + "validate",
                "���s�������h7:�s�o���������s�h�̏ꍇ�A�����P���敪���h1�F�w�l�h��ݒ肵�Ă��������B");
        }

        //�T�j�@@���������敪�`�F�b�N
        //�@@�T�|�P�jthis.���������敪��null�̒l�ł���Η�O���X���[����B
        if (WEB3StringTypeUtility.isEmpty(this.expirationDateType))
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00208,
                getClass().getName() + "validate",
                "���������敪���I�����Ă�������");
        }
        //�@@�T�|�Q�jthis.���������敪���ȉ��̒l�ȊO�̏ꍇ��O���X���[����B
        //�h1�F��������h
        //�h2�F�o����܂Œ����h
        //�h3�F�[��܂Œ����h
        else if (!WEB3OrderExpirationDateTypeDef.DAY_LIMIT.equals(this.expirationDateType)
            && !WEB3OrderExpirationDateTypeDef.CARRIED_ORDER.equals(this.expirationDateType)
            && !WEB3OrderExpirationDateTypeDef.EVENING_SESSION_ORDER.equals(this.expirationDateType))
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00209,
                getClass().getName() + "validate",
                "���������敪�̒l�����݂��Ȃ��R�[�h�l�ł��B");
        }

        //�U�j�@@���������敪�E���s�����`�F�b�N
        //�U�|�P�jthis.���������敪���h2�F�o����܂Œ����h�ł��A
        //        this.���s�������h1�F�������h�ȊO�̒l�ł���Η�O���X���[����B
        if (WEB3OrderExpirationDateTypeDef.CARRIED_ORDER.equals(this.expirationDateType)
            && !WEB3ExecutionConditionDef.NO_CONDITION.equals(this.execCondType))
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00125,
                getClass().getName() + "validate",
                "���������敪���h2�F�o����܂Œ����h�̏ꍇ�A���s�������u1�F�������v�̏ꍇ�G���[�B");
        }

        // �@@�U�|�Q�jthis.���������敪���h3�F�[��܂Œ����h�ł��A
        // �@@�@@�@@�@@�@@this.���s�������h1�F�������h�ȊO�̒l�ł���Η�O���X���[����B
        if (WEB3OrderExpirationDateTypeDef.EVENING_SESSION_ORDER.equals(this.expirationDateType)
            && !WEB3ExecutionConditionDef.NO_CONDITION.equals(this.execCondType))
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02817,
                getClass().getName() + "validate",
                "���������敪���g3�F�[��܂Œ����h�̏ꍇ�́A���s�����Ɂg1�F�������h��ݒ肵�ĉ������B");
        }

        //�V�j�@@�����L�������`�F�b�N
        //�V�|�P�jthis.���������敪���h1�F��������h�ł��A
        // this.�����L��������null�ł���Η�O���X���[����B
        if (WEB3OrderExpirationDateTypeDef.DAY_LIMIT.equals(this.expirationDateType)
            && this.expirationDate != null)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00117,
                getClass().getName() + "validate",
                "���������敪���g1�F��������h�̏ꍇ�́A�����L�������w��s�ł��B");
        }

        //�V�|�Q�jthis.���������敪���h2�F�o����܂Œ����h�ł��A
        // this.�����L��������null�̒l�ł���Η�O���X���[����B
        else if (WEB3OrderExpirationDateTypeDef.CARRIED_ORDER.equals(this.expirationDateType)
            && this.expirationDate == null)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00210,
                getClass().getName() + "validate",
                "���������敪���h2�F�o����܂Œ����h�̏ꍇ�A�����L�����������͂��Ă�������");
        }

        //�V�|�R�jthis.���������敪���h3�F�[��܂Œ����h�ł��A
        // this.�����L��������null�ł���Η�O���X���[����
        else if (WEB3OrderExpirationDateTypeDef.EVENING_SESSION_ORDER.equals(this.expirationDateType)
            && this.expirationDate != null)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02818,
                getClass().getName() + "validate",
                "���������敪���g3�F�[��܂Œ����h�̏ꍇ�́A�����L�������w��s�ł��B");
        }

        //�W�j�@@���������敪�`�F�b�N
        //�@@�W�|�P�jthis.���������敪��null�̒l�ł���Η�O���X���[����B
        if (WEB3StringTypeUtility.isEmpty(this.orderCondType))
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00211,
                getClass().getName() + "validate",
                "���������敪���I�����Ă�������");
        }
        //�@@�W�|�Q�jthis.���������敪���ȉ��̒l�ȊO�̏ꍇ��O���X���[����B
        //�h0�F�w��Ȃ��h
        //�h1�F�t�w�l�h
        //�h2�FW�w�l�h
        else if (!WEB3OrderingConditionDef.DEFAULT.equals(this.orderCondType)
            && !WEB3OrderingConditionDef.STOP_LIMIT_PRICE.equals(this.orderCondType)
            && !WEB3OrderingConditionDef.W_LIMIT_PRICE.equals(this.orderCondType))
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00212,
                getClass().getName() + "validate",
                "���������h0�F�w��Ȃ��h�A�h1�F�t�w�l�h�A�h2�FW�w�l�h�ȊO�̏ꍇ�G���[�B");
        }

        //�X�j�@@�t�w�l�p�v���~�A���^�����Y���i�`�F�b�N
        //�@@�X�|�P�jthis.���������敪���h1�F�t�w�l�h�̒l�ł��A
        //this.�t�w�l�p�v���~�A���^�����Y���i��null�̒l�ł����
        //��O���X���[����B
        if (WEB3OrderingConditionDef.STOP_LIMIT_PRICE.equals(this.orderCondType)
            && WEB3StringTypeUtility.isEmpty(this.stopPremium_underlyingAssets))
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00213,
                getClass().getName() + "validate",
                "�t�w�l�p�v���~�A���^�����Y���i��null�̒l�ł���B");
        }

        //�@@�X�|�Q�jthis.���������敪���h1�F�t�w�l�h�̒l�ł��A
        //this.�t�w�l�p�v���~�A���^�����Y���i��
        //�ȉ��̒l�ȊO�̏ꍇ��O���X���[����B
        //�h0�F�����Y���i�h
        //�h1�F�v���~�A���h
        else if (WEB3OrderingConditionDef.STOP_LIMIT_PRICE.equals(this.orderCondType)
            && !WEB3StopBasePriceTypeDef.DEFAULT.equals(this.stopPremium_underlyingAssets)
            && !WEB3StopBasePriceTypeDef.PREMIUM.equals(this.stopPremium_underlyingAssets))
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00214,
                getClass().getName() + "validate",
                "�t�w�l�p�v���~�A���^�����Y���i���h0�F�����Y���i�h�A�h1�F�v���~�A���h�ȊO�̏ꍇ�G���[");
        }

        // �X�|�R�jthis.���������敪���h0�F�w��Ȃ��h�̒l�ł��A
        //     this.�t�w�l�p�v���~�A���^�����Y���i��null�ł����
        //     ��O���X���[����B
        else if (WEB3OrderingConditionDef.DEFAULT.equals(this.orderCondType)
            && this.stopPremium_underlyingAssets != null)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01922,
                getClass().getName() + "validate",
                "���������敪���g0�F�w��Ȃ��h�̏ꍇ�́A�t�w�l�p�v���~�A���^�����Y���i���w��s�ł��B");
        }

        // �X�|�S�jthis.���������敪���h2�FW�w�l�h�̒l�ł��A
        //�@@   this.�t�w�l�p�v���~�A���^�����Y���i��null�ł����
        //�@@   ��O���X���[����B
        else if (WEB3OrderingConditionDef.W_LIMIT_PRICE.equals(this.orderCondType)
            && this.stopPremium_underlyingAssets != null)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01923,
                getClass().getName() + "validate",
                "���������敪���g2�FW�w�l�h�̏ꍇ�́A�t�w�l�p�v���~�A���^�����Y���i���w��s�ł��B");
        }

        //�P�O�j�@@�t�w�l�p���������P���`�F�b�N
        //�@@�P�O�|�P�jthis.���������敪���h1�F�t�w�l�h�̒l�ł��A
        //this.�t�w�l�p���������P����null�̒l��
        //����Η�O���X���[����B
        if (WEB3OrderingConditionDef.STOP_LIMIT_PRICE.equals(this.orderCondType)
            && WEB3StringTypeUtility.isEmpty(this.stopOrderCondPrice))
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00215,
                getClass().getName() + "validate",
                "���������敪���h1�F�t�w�l�h�̏ꍇ�A�t�w�l�p���������P����null�̒l�ł���");
        }

        //�@@�P�O�|�Q�jthis.���������敪���h1�F�t�w�l�h�̒l�ł��A
        //this.�t�w�l�p���������P���������ȊO�̒l��
        //����Η�O���X���[����B
        else if (
            WEB3OrderingConditionDef.STOP_LIMIT_PRICE.equals(this.orderCondType)
                && !WEB3StringTypeUtility.isNumber(this.stopOrderCondPrice))
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00216,
                getClass().getName() + "validate",
                "���������敪���h1�F�t�w�l�h�̏ꍇ�A�t�w�l�p���������P���������ȊO�̒l�ł���");
        }

        //�@@�P�O�|�R�jthis.���������敪���h1�F�t�w�l�h�̒l�ł��A
        //this.�t�w�l�p���������P�����O�ȉ��̒l��
        //����Η�O���X���[����B
        else if (
            WEB3OrderingConditionDef.STOP_LIMIT_PRICE.equals(this.orderCondType)
                && Long.parseLong(this.stopOrderCondPrice) <= 0)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00216,
                getClass().getName() + "validate",
                "���������敪���h1�F�t�w�l�h�̏ꍇ�A�t�w�l�p���������P�����O�ȉ��̒l�ł���");
        }

        //�@@�P�O�|�S�jthis.���������敪���h1�F�t�w�l�h�̒l�ł��A
        //this.�t�w�l�p���������P�����V���𒴂���l��
        //����Η�O���X���[����B
        else if (WEB3OrderingConditionDef.STOP_LIMIT_PRICE.equals(this.orderCondType)
            && WEB3StringTypeUtility.getByteLength(this.stopOrderCondPrice) > 7)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00216,
                getClass().getName() + "validate",
                "���������敪���h1�F�t�w�l�h�̏ꍇ�A�t�w�l�p���������P�����V���𒴂��܂����B");
        }

        //  �P�O�|�T�jthis.���������敪���h0�F�w��Ȃ��h�̒l�ł��A
        //this.�t�w�l�p���������P����(null�܂���0)��
        //����Η�O���X���[����B
        else if (WEB3OrderingConditionDef.DEFAULT.equals(this.orderCondType)
            && this.stopOrderCondPrice != null
            && Double.parseDouble(this.stopOrderCondPrice) != 0)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01872,
                getClass().getName() + "validate",
                "���������敪���g0�F�w��Ȃ��h�̏ꍇ�́A�t�w�l�p���������P�����w��s�ł��B");
        }

        //�@@�P�O�|�U�jthis.���������敪���h2�FW�w�l�h�̒l�ł��A
        //this.�t�w�l�p���������P����(null�܂���0)��
        //����Η�O���X���[����B
        else if (WEB3OrderingConditionDef.W_LIMIT_PRICE.equals(this.orderCondType)
            && this.stopOrderCondPrice != null
            && Double.parseDouble(this.stopOrderCondPrice) != 0)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01882,
                getClass().getName() + "validate",
                "���������敪���g2�FW�w�l�h�̏ꍇ�́A�t�w�l�p���������P�����w��s�ł��B");
        }

        //�P�P�j�@@�t�w�l�p�����������Z�q�`�F�b�N
        //�@@�P�P�|�P�jthis.���������敪���h1�F�t�w�l�h�̒l�ł��A
        //this.�t�w�l�p�����������Z�q��null�̒l��
        //����Η�O���X���[����B
        if (WEB3OrderingConditionDef.STOP_LIMIT_PRICE.equals(this.orderCondType)
            && WEB3StringTypeUtility.isEmpty(this.stopOrderCondOperator))
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00219,
                getClass().getName() + "validate",
                "���������敪���h1�F�t�w�l�h�̏ꍇ�A�t�w�l�p�����������Z�q��null�̒l�ł���");
        }

        //�@@�P�P�|�Q�jthis.���������敪���h1�F�t�w�l�h�̒l�ł��A
        //this.�t�w�l�p�����������Z�q���ȉ��̒l�ȊO�̏ꍇ
        //��O���X���[����B
        //�h1�F�ȏ�h
        //�h2�F�ȉ��h
        else if (WEB3OrderingConditionDef.STOP_LIMIT_PRICE.equals(this.orderCondType)
            && !WEB3OrderConditionOperatorDef.ABOVE_BASE_PRICE.equals(this.stopOrderCondOperator)
            && !WEB3OrderConditionOperatorDef.BELOW_BASE_PRICE.equals(this.stopOrderCondOperator))
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00220,
                getClass().getName() + "validate",
                "���������敪���h1�F�t�w�l�h�̏ꍇ�́A�t�w�l�p�����������Z�q���h1�F�ȏ�h���́h2�F�ȉ��h��ݒ肵�ĉ������B");
        }

        //�@@�P�P�|�R�jthis.���������敪���h0�F�w��Ȃ��h�̒l�ł��A
        //this.�t�w�l�p�����������Z�q��null��
        //����Η�O���X���[����B
        else if (WEB3OrderingConditionDef.DEFAULT.equals(this.orderCondType)
            && this.stopOrderCondOperator != null)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01873,
                getClass().getName() + "validate",
                "���������敪���g0�F�w��Ȃ��h�̏ꍇ�́A�t�w�l�p�����������Z�q���w��s�ł��B");
        }

        //�@@�P�P�|�S�jthis.���������敪���h2�FW�w�l�h�̒l�ł��A
        //this.�t�w�l�p�����������Z�q��null��
        //����Η�O���X���[����B
        else if (WEB3OrderingConditionDef.W_LIMIT_PRICE.equals(this.orderCondType)
            && this.stopOrderCondOperator != null)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01883,
                getClass().getName() + "validate",
                "���������敪���g2�FW�w�l�h�̏ꍇ�́A�t�w�l�p�����������Z�q���w��s�ł��B");
        }

        //�P�Q�j�@@�v�w�l�p�v���~�A���^�����Y���i�`�F�b�N
        //�@@�P�Q�|�P�jthis.���������敪���h2�FW�w�l�h�̒l�ł��A
        //this.�v�w�l�p�v���~�A���^�����Y���i��null�̒l�ł����
        //��O���X���[����B
        if (WEB3OrderingConditionDef.W_LIMIT_PRICE.equals(this.orderCondType)
            && WEB3StringTypeUtility.isEmpty(this.wlimitPremium_underlyingAssets))
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00221,
                getClass().getName() + "validate",
                "���������敪���h2�FW�w�l�h�̏ꍇ�A�v�w�l�p�v���~�A���^�����Y���i��null�̒l�ł���");
        }

        //�@@�P�Q�|�Q�jthis.���������敪���h2�FW�w�l�h�̒l�ł��A
        //this.�v�w�l�p�v���~�A���^�����Y���i��
        //�ȉ��̒l�ȊO�̏ꍇ��O���X���[����B
        //�h0�F�����Y���i�h
        //�h1�F�v���~�A���h
        else if (WEB3OrderingConditionDef.W_LIMIT_PRICE.equals(this.orderCondType)
            && !WEB3StopBasePriceTypeDef.DEFAULT.equals(this.wlimitPremium_underlyingAssets)
            && !WEB3StopBasePriceTypeDef.PREMIUM.equals(this.wlimitPremium_underlyingAssets))
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00222,
                getClass().getName() + "validate",
                "���������敪���h1�F�t�w�l�h�̏ꍇ�A�v�w�l�p�v���~�A���^�����Y���i���h0�F�����Y���i�h�A�h1�F�v���~�A���h�̒l�ȊO�ł���");
        }

        //�@@�P�Q�|�R�jthis.���������敪���h0�F�w��Ȃ��h�̒l�ł��A
        //this.�v�w�l�p�v���~�A���^�����Y���i��null�ł����
        // ��O���X���[����B
        else if (WEB3OrderingConditionDef.DEFAULT.equals(this.orderCondType)
            && this.wlimitPremium_underlyingAssets != null)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01924,
                getClass().getName() + "validate",
                "���������敪���g0�F�w��Ȃ��h�̏ꍇ�́A�v�w�l�p�v���~�A���^�����Y���i���w��s�ł��B");
        }

        //�@@�P�Q�|�S�jthis.���������敪���h1�F�t�w�l�h�̒l�ł��A
        //�@@�@@�@@�@@this.�v�w�l�p�v���~�A���^�����Y���i��null�ł����
        //�@@�@@�@@�@@��O���X���[����B
        else if (WEB3OrderingConditionDef.STOP_LIMIT_PRICE.equals(this.orderCondType)
            && this.wlimitPremium_underlyingAssets != null)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01925,
                getClass().getName() + "validate",
                "���������敪���g1�F�t�w�l�h�̏ꍇ�́A�v�w�l�p�v���~�A���^�����Y���i���w��s�ł��B");
        }

        //�P�R�j�@@�v�w�l�p���������P���`�F�b�N
        //�@@�P�R�|�P�jthis.���������敪���h2�FW�w�l�h�̒l�ł��A
        //this.�v�w�l�p���������P����null�̒l�ł����
        //��O���X���[����B
        if (WEB3OrderingConditionDef.W_LIMIT_PRICE.equals(this.orderCondType)
            && WEB3StringTypeUtility.isEmpty(this.wlimitOrderCondPrice))
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00223,
                getClass().getName() + "validate",
                "���������敪���h�v�w��h�̏ꍇ�́AW�w�l�p���������P������`���K�v�ł��B");
        }

        //�@@�P�R�|�Q�jthis.���������敪���h2�FW�w�l�h�̒l�ł��A
        //this.�v�w�l�p���������P���������ȊO�̒l�ł����
        //��O���X���[����B
        else if (WEB3OrderingConditionDef.W_LIMIT_PRICE.equals(this.orderCondType)
            && !WEB3StringTypeUtility.isNumber(this.wlimitOrderCondPrice))
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00224,
                getClass().getName() + "validate",
                "���������敪���h2�FW�w�l�h�̏ꍇ�A�v�w�l�p���������P���������ȊO�̒l�ł���B");
        }

        //�@@�P�R�|�R�jthis.���������敪���h2�FW�w�l�h�̒l�ł��A
        //this.�v�w�l�p���������P�����O�ȉ��̒l�ł����
        //��O���X���[����B
        else if (WEB3OrderingConditionDef.W_LIMIT_PRICE.equals(this.orderCondType)
            && Long.parseLong(this.wlimitOrderCondPrice) <= 0)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00224,
                getClass().getName() + "validate",
                "���������敪���h2�FW�w�l�h�̏ꍇ�A�v�w�l�p���������P�����O�ȉ��̒l�ł���B");
        }

        //�@@�P�R�|�S�jthis.���������敪���h2�FW�w�l�h�̒l�ł��A
        //this.�v�w�l�p���������P�����V���𒴂���l�ł����
        //��O���X���[����B
        else if (WEB3OrderingConditionDef.W_LIMIT_PRICE.equals(this.orderCondType)
            && WEB3StringTypeUtility.getByteLength(this.wlimitOrderCondPrice) > 7)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00224,
                getClass().getName() + "validate",
                "���������敪���h2�FW�w�l�h�̏ꍇ�A�v�w�l�p���������P�����V���𒴂��܂����B");
        }

        //�@@�P�R�|�T�jthis.���������敪���h0�F�w��Ȃ��h�̒l�ł��A
        //this.�v�w�l�p���������P����(null�܂���0)�ł����
        //��O���X���[����B
        else if (WEB3OrderingConditionDef.DEFAULT.equals(this.orderCondType)
            && this.wlimitOrderCondPrice != null
            && Double.parseDouble(this.wlimitOrderCondPrice) != 0)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01874,
                getClass().getName() + "validate",
                "���������敪���g0�F�w��Ȃ��h�̏ꍇ�́AW�w�l�p���������P�����w��s�ł��B");
        }

        //�@@�P�R�|�U�jthis.���������敪���h1�F�t�w�l�h�̒l�ł��A
        //this.�v�w�l�p���������P����(null�܂���0)�ł����
        //��O���X���[����B
        else if (WEB3OrderingConditionDef.STOP_LIMIT_PRICE.equals(this.orderCondType)
            && this.wlimitOrderCondPrice != null
            && Double.parseDouble(this.wlimitOrderCondPrice) != 0)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01878,
                getClass().getName() + "validate",
                "���������敪���g1�F�t�w�l�h�̏ꍇ�́AW�w�l�p���������P�����w��s�ł��B");
        }

        //�P�S�j�@@�v�w�l�p�����������Z�q�`�F�b�N
        //�@@�P�S�|�P�jthis.���������敪���h2�FW�w�l�h�̒l�ł��A
        //this.�v�w�l�p�����������Z�q��null�̒l�ł����
        //��O���X���[����B
        if (WEB3OrderingConditionDef.W_LIMIT_PRICE.equals(this.orderCondType)
            && WEB3StringTypeUtility.isEmpty(this.wlimitOrderCondOperator))
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00227,
                getClass().getName() + "validate",
                "���������敪���hW�w�l�h�̏ꍇ�́AW�w�l�p�����������Z�q���`���K�v�ł��B");
        }
        //�@@�P�S�|�Q�jthis.���������敪���h2�FW�w�l�h�̒l�ł��A
        //this.�v�w�l�p�����������Z�q���ȉ��̒l�ȊO�̏ꍇ
        //��O���X���[����B
        //�h1�F�ȏ�h
        //�h2�F�ȉ��h
        else if (WEB3OrderingConditionDef.W_LIMIT_PRICE.equals(this.orderCondType)
            && !WEB3OrderConditionOperatorDef.ABOVE_BASE_PRICE.equals(this.wlimitOrderCondOperator)
            && !WEB3OrderConditionOperatorDef.BELOW_BASE_PRICE.equals(this.wlimitOrderCondOperator))
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00228,
                getClass().getName() + "validate",
                "���������敪���hW�w�l�h�̏ꍇ�́AW�w�l�p�����������Z�q�Ɂh1�F�ȏ�h���́h2�F�ȉ��h��ݒ肵�ĉ������B");
        }

        //�@@�P�S�|�R�jthis.���������敪���h0�F�w��Ȃ��h�̒l�ł��A
        //this.�v�w�l�p�����������Z�q��null�ł����
        //��O���X���[����B
        else if (WEB3OrderingConditionDef.DEFAULT.equals(this.orderCondType)
            && this.wlimitOrderCondOperator != null)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01875,
                getClass().getName() + "validate",
                "���������敪���g0�F�w��Ȃ��h�̏ꍇ�́AW�w�l�p�����������Z�q���w��s�ł��B");
        }

        //�@@�P�S�|�S�jthis.���������敪���h1�F�t�w�l�h�̒l�ł��A
        //this.�v�w�l�p�����������Z�q��null�ł����
        //��O���X���[����B
        else if (WEB3OrderingConditionDef.STOP_LIMIT_PRICE.equals(this.orderCondType)
            && this.wlimitOrderCondOperator != null)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01879,
                getClass().getName() + "validate",
                "���������敪���g1�F�t�w�l�h�̏ꍇ�́AW�w�l�p�����������Z�q���w��s�ł��B");
        }

        //�P�T�j�@@�v�w�l�p�����P���敪�`�F�b�N
        //�@@�P�T�|�P�jthis.���������敪���h2�FW�w�l�h�̒l�ł��A
        //this.�v�w�l�p�����P���敪��null�̒l�ł����
        //��O���X���[����B
        if (WEB3OrderingConditionDef.W_LIMIT_PRICE.equals(this.orderCondType)
            && WEB3StringTypeUtility.isEmpty(this.wLimitOrderPriceDiv))
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00229,
                getClass().getName() + "validate",
                "���������敪���h2�FW�w�l�h�̏ꍇ�A�v�w�l�p�����P���敪��null�̒l�ł���B");
        }
        //�@@�P�T�|�Q�jthis.���������敪���h2�FW�w�l�h�̒l�ł��A
        //this.�v�w�l�p�����P���敪���ȉ��̒l�ȊO�̏ꍇ
        //��O���X���[����B
        //�h0�F���s�h
        //�h1�F�w�l�h
        else if (WEB3OrderingConditionDef.W_LIMIT_PRICE.equals(this.orderCondType)
            && !WEB3OrderPriceDivDef.MARKET_PRICE.equals(this.wLimitOrderPriceDiv)
            && !WEB3OrderPriceDivDef.LIMIT_PRICE.equals(this.wLimitOrderPriceDiv))
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00230,
                getClass().getName() + "validate",
                "���������敪���h2�FW�w�l�h�̏ꍇ�A�v�w�l�p�����P���敪���h0�F���s�h�A�h1�F�w�l�h�ȊO�̏ꍇ�װ�B");
        }

        //�@@�P�T�|�R�jthis.���������敪���h0�F�w��Ȃ��h�̒l�ł��A
        //this.�v�w�l�p�����P���敪��null�ł����
        //��O���X���[����B
        else if (WEB3OrderingConditionDef.DEFAULT.equals(this.orderCondType)
            && this.wLimitOrderPriceDiv != null)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01876,
                getClass().getName() + "validate",
                "���������敪���g0�F�w��Ȃ��h�̏ꍇ�́AW�w�l�p�����P���敪���w��s�ł��B");
        }

        //�@@�P�T�|�S�jthis.���������敪���h1�F�t�w�l�h�̒l�ł��A
        //this.�v�w�l�p�����P���敪��null�ł����
        //��O���X���[����B
        else if (WEB3OrderingConditionDef.STOP_LIMIT_PRICE.equals(this.orderCondType)
            && this.wLimitOrderPriceDiv != null)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01880,
                getClass().getName() + "validate",
                "���������敪���g1�F�t�w�l�h�̏ꍇ�́AW�w�l�p�����P���敪���w��s�ł��B");
        }

        //�P�U�j�v�w�l�p�����P���`�F�b�N
        //�@@�P�U�|�P�jthis.���������敪���h2�FW�w�l�h�̒l�ł��A
        //this.�v�w�l�p�����P���敪���h0�F���s�h�̒l�ł���
        //this.�v�w�l�p�����P����null�ȊO�̒l�ł����
        //��O���X���[����B
        if (WEB3OrderingConditionDef.W_LIMIT_PRICE.equals(this.orderCondType)
            && WEB3OrderPriceDivDef.MARKET_PRICE.equals(this.wLimitOrderPriceDiv)
            && this.wLimitPrice != null)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00124,
                getClass().getName() + "validate",
                "���������敪���h�Q�FW�w�l�h�@@���@@W�w�l�p�����P���敪���h�O�F���s���h�̏ꍇ�AW�w�l�p�����P����null�ȊO�ł���΃G���[�B");
        }

        //�@@�P�U�|�Q�jthis.���������敪���h2�FW�w�l�h�̒l�ł��A
        //this.�v�w�l�p�����P���敪���h1�F�w�l�h�̒l�ł���
        //this.�v�w�l�p�����P����null�̒l�ł����
        //��O���X���[����B
        else if (WEB3OrderingConditionDef.W_LIMIT_PRICE.equals(this.orderCondType)
            && WEB3OrderPriceDivDef.LIMIT_PRICE.equals(this.wLimitOrderPriceDiv)
            && WEB3StringTypeUtility.isEmpty(this.wLimitPrice))
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00313,
                getClass().getName() + "validate",
                "���������敪���h�v�w��h�̏ꍇ�́AW�w�l�p�����P���̒�`���K�v�ł��B");
        }
        //�@@�P�U�|�R�jthis.���������敪���h2�FW�w�l�h�̒l�ł��A
        //this.�v�w�l�p�����P���敪���h1�F�w�l�h�̒l�ł���
        //this.�v�w�l�p�����P���������ȊO�̒l�ł����
        //��O���X���[����B
        else if (WEB3OrderingConditionDef.W_LIMIT_PRICE.equals(this.orderCondType)
            && WEB3OrderPriceDivDef.LIMIT_PRICE.equals(this.wLimitOrderPriceDiv)
            && !WEB3StringTypeUtility.isNumber(this.wLimitPrice))
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00314,
                getClass().getName() + "validate",
                "���������敪���h2�FW�w�l�h�̏ꍇ�A�v�w�l�p�����P���������ȊO�̒l�ł���B");
        }
        //�@@�P�U�|�S�jthis.���������敪���h2�FW�w�l�h�̒l�ł��A
        //this.�v�w�l�p�����P���敪���h1�F�w�l�h�̒l�ł���
        //this.�v�w�l�p�����P�����O�ȉ��̒l�ł����
        //��O���X���[����B
        else if (WEB3OrderingConditionDef.W_LIMIT_PRICE.equals(this.orderCondType)
            && WEB3OrderPriceDivDef.LIMIT_PRICE.equals(this.wLimitOrderPriceDiv)
            && Long.parseLong(this.wLimitPrice) <= 0)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00314,
                getClass().getName() + "validate",
                "���������敪���h2�FW�w�l�h�̏ꍇ�A�v�w�l�p�����P�����O�ȉ��̒l�ł���B");
        }
        //�@@�P�U�|�T) this.���������敪���h2�FW�w�l�h�̒l�ł��A
        //this.�v�w�l�p�����P���敪���h1�F�w�l�h�̒l�ł���
        //this.�v�w�l�p�����P�����V���𒴂���l�ł����
        //��O���X���[����B
        else if (WEB3OrderingConditionDef.W_LIMIT_PRICE.equals(this.orderCondType)
            && WEB3OrderPriceDivDef.LIMIT_PRICE.equals(this.wLimitOrderPriceDiv)
            && WEB3StringTypeUtility.getByteLength(this.wLimitPrice) > 7)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00314,
                getClass().getName() + "validate",
                "���������敪���h2�FW�w�l�h�̏ꍇ�A�v�w�l�p�����P�����V���𒴂��܂����B");
        }

        //  �P�U�|�U�jthis.���������敪���h0�F�w��Ȃ��h�̒l�ł��A
        //this.�v�w�l�p�����P����(null�܂���0)�ł����
        //��O���X���[����B
        else if (WEB3OrderingConditionDef.DEFAULT.equals(this.orderCondType)
            && this.wLimitPrice != null
            && Double.parseDouble(this.wLimitPrice) != 0)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01877,
                getClass().getName() + "validate",
                "���������敪���g0�F�w��Ȃ��h�̏ꍇ�́AW�w�l�p�����P�����w��s�ł��B");
        }

        //�@@�P�U�|�V�jthis.���������敪���h1�F�t�w�l�h�̒l�ł��A
        //this.�v�w�l�p�����P����(null�܂���0)�ł����
        //��O���X���[����B
        else if (WEB3OrderingConditionDef.STOP_LIMIT_PRICE.equals(this.orderCondType)
            && this.wLimitPrice != null
            && Double.parseDouble(this.wLimitPrice) != 0)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01881,
                getClass().getName() + "validate",
                "���������敪���g1�F�t�w�l�h�̏ꍇ�́AW�w�l�p�����P�����w��s�ł��B");
        }
        
        // �P7�j�@@���������E���s�����̃`�F�b�N
        //�@@�P7�|�P�j�@@this.����������"1�F�t�w�l"�̏ꍇ�A 
        //   this.���s�������u1�F�������v�ł���Η�O���X���[����B
        if (WEB3OrderingConditionDef.STOP_LIMIT_PRICE.equals(this.orderCondType)
            && !WEB3ExecutionConditionDef.NO_CONDITION.equals(this.execCondType))
        {
            log.debug("�v�w�l�p���s���������w��ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02473,
                this.getClass().getName() + STR_METHOD_NAME,
                "���������敪���t�w�l�̏ꍇ�́A���s�����ɖ��������w�肵�Ă��������B");
        }
        
        //�@@�P7�|�Q�j�@@this.����������"2�FW�w�l"�̏ꍇ�A 
        //�@@�@@�@@�@@this.���s�������ȉ��̒l�ȊO�̏ꍇ�A��O���X���[����B 
        //�@@�@@�@@�@@�E�h1�F�������h 
        //�@@�@@�@@�@@�E�h7:�s�o���������s�h
        else if(WEB3OrderingConditionDef.W_LIMIT_PRICE.equals(this.orderCondType)
            && !(WEB3ExecutionConditionDef.NO_CONDITION.equals(this.execCondType)
            || WEB3ExecutionConditionDef.AT_MARKET_CLOSE_NOT_EXECUTED.equals(this.execCondType)))
        {
            log.debug("W�w�l�����̎��s�����́g�������h�A�g�s�o���������s�h�ȊO�w��s�B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02500,
                this.getClass().getName() + STR_METHOD_NAME,
                "W�w�l�����̎��s�����́g�������h�A�g�s�o���������s�h�ȊO�w��s�B"); 
        }
        
        // �P8�j�@@�v�w�l�p���s�����`�F�b�N                     
        // �@@�P8�|�P�jthis.���������敪���h2�FW�w�l�h�̒l�ł��A
        // �@@�@@�@@�@@this.�v�w�l�p���s������null�̒l�ł���Η�O���X���[����B
        if (WEB3OrderingConditionDef.W_LIMIT_PRICE.equals(this.orderCondType)
            && this.wlimitExecCondType == null)
        {
            log.debug("�v�w�l�p���s���������w��ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02499,
                this.getClass().getName() + STR_METHOD_NAME,
                "�v�w�l�p���s���������w��ł��B");
        }
       
        //�P�W�|�Q�jthis.���������敪���h2�FW�w�l�h�̒l�ł��A 
        //      this.�v�w�l�p���s�������ȉ��̒l�ȊO�̏ꍇ�A��O���X���[����B 
        //      �E�h1�F�������h 
        //      �E�h7:�s�o���������s�h 
        else if (WEB3OrderingConditionDef.W_LIMIT_PRICE.equals(this.orderCondType)
            && !(WEB3ExecutionConditionDef.NO_CONDITION.equals(this.wlimitExecCondType)
            || WEB3ExecutionConditionDef.AT_MARKET_CLOSE_NOT_EXECUTED.equals(this.wlimitExecCondType)))
        {
            log.debug("W�w�l�����̎��s�����́g�������h�A�g�s�o���������s�h�ȊO�w��s�B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02500,
                this.getClass().getName() + STR_METHOD_NAME,
                "W�w�l�����̎��s�����́g�������h�A�g�s�o���������s�h�ȊO�w��s�B");
        }
        
        //�@@�P�W�|�R�jthis.���������敪���h0�F�w��Ȃ��h�̒l�ł��A 
        //�@@�@@�@@�@@this.�v�w�l�p���s������null�ł���� 
        if (WEB3OrderingConditionDef.DEFAULT.equals(this.orderCondType) &&
            this.wlimitExecCondType != null)
        {
            log.debug("���������敪���g�w��Ȃ��h�̏ꍇ�́A�v�w�l�p���s�������w��s�ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02525,
                this.getClass().getName() + STR_METHOD_NAME,
                "���������敪���g�w��Ȃ��h�̏ꍇ�́A�v�w�l�p���s�������w��s�ł��B");
        }
                
        //�@@�P�W�|�S�jthis.���������敪���h1�F�t�w�l�h�̒l�ł��A 
        //�@@�@@�@@�@@this.�v�w�l�p���s������null�ł���� 
        //�@@�@@�@@�@@��O���X���[����B 
        if (WEB3OrderingConditionDef.STOP_LIMIT_PRICE.equals(this.orderCondType) &&
            this.wlimitExecCondType != null)
        {
            log.debug("���������敪���g�t�w�l�h�̏ꍇ�́A�v�w�l�p���s�������w��s�ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02526,
                this.getClass().getName() + STR_METHOD_NAME,
                "���������敪���g�t�w�l�h�̏ꍇ�́A�v�w�l�p���s�������w��s�ł��B");
        }
       
        //�P�X�j�@@�v�w�l�p���s�����E�����P���敪�`�F�b�N 
        //�@@�@@�@@�@@this.�v�w�l�p���s�������h7:�s�o���������s�h�ł��A 
        //�@@�@@�@@�@@this.�v�w�l�p�����P���敪���h1�F�w�l�h�ȊO�̒l�ł���Η�O���X���[����B 
        //                                  
        if(WEB3ExecutionConditionDef.AT_MARKET_CLOSE_NOT_EXECUTED.equals(this.wlimitExecCondType)
            && !WEB3OrderPriceDivDef.LIMIT_PRICE.equals(this.wLimitOrderPriceDiv))
        {
            log.debug("�v�w�l�p�����P���敪���h�w�l�h�ȊO�̒l�ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02501,
                this.getClass().getName() + STR_METHOD_NAME,
                "�v�w�l�p�����P���敪���h�w�l�h�ȊO�̒l�ł��B");
        }
       
        //�Q�O�j�@@�v�w�l�p�L����ԋ敪�`�F�b�N 
        //�@@�@@�@@�@@this.�v�w�l�p�L����ԋ敪��null�ł���΁A 
        //�@@�@@�@@�@@�ȉ��̏������s���B        
        //�@@�Q�O�|�P�j�@@this.�v�w�l�L����ԋ敪���ȉ��̒l�ȊO�̏ꍇ�A         
        //�@@�@@�@@�@@��O���X���[����B        
        //�@@�@@�@@�@@�E�h0�F���~�b�g�����L���h 
        //�@@�@@�@@�@@�E�h1�F�X�g�b�v�����L���h 
        //�@@�@@�@@�@@�E�h2�F�X�g�b�v���������ρh 
        if(this.wlimitEnableStatusDiv != null 
            && !(WEB3IfoWLimitEnableStatusDivDef.LIMIT_ENABLE.equals(this.wlimitEnableStatusDiv)
            || WEB3IfoWLimitEnableStatusDivDef.STOP_ENABLE.equals(this.wlimitEnableStatusDiv)
            || WEB3IfoWLimitEnableStatusDivDef.STOP_UN_ENABLE.equals(this.wlimitEnableStatusDiv)))
        {
            log.debug("�v�w�l�L����ԋ敪�̒l�����݂��Ȃ��R�[�h�l�ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02502,
                this.getClass().getName() + STR_METHOD_NAME,
                "�v�w�l�L����ԋ敪�̒l�����݂��Ȃ��R�[�h�l�ł��B");
        }
                                 
        //�Q�P�j�@@�v�w�l�p���s�����E�����L�������`�F�b�N 
        //    �@@�@@this.���������敪���h2�FW�w�l�h�̒l�ł��A
        //�@@�@@�@@�@@this.���������敪���h2�F�o����܂Œ����h�ł��A 
        //�@@�@@�@@�@@this.�v�w�l�p���s�������h1�F�������h�ȊO�̒l 
        //�@@�@@�@@�@@�ł���Η�O���X���[����B     
        if(WEB3OrderingConditionDef.W_LIMIT_PRICE.equals(this.orderCondType)
            && WEB3OrderExpirationDateTypeDef.CARRIED_ORDER.equals(this.expirationDateType)
            && !WEB3ExecutionConditionDef.NO_CONDITION.equals(this.wlimitExecCondType))
        {
            log.debug("�v�w�l�p���s�������h�������h�ȊO�̒l�ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02503,
                this.getClass().getName() + STR_METHOD_NAME,
                "�v�w�l�p���s�������h�������h�ȊO�̒l�ł��B");
        }

        //�@@�Q�P�|�Q�jthis.���������敪���h2�FW�w�l�h�̒l�ł��A
        //�@@�@@�@@�@@�@@�@@this.���������敪���h3�F�[��܂Œ����h�ł��A
        //�@@�@@�@@�@@�@@�@@this.�v�w�l�p���s�������h1�F�������h�ȊO�̒l
        //�@@�@@�@@�@@�@@�@@�ł���Η�O���X���[����B
        if(WEB3OrderingConditionDef.W_LIMIT_PRICE.equals(this.orderCondType)
            && WEB3OrderExpirationDateTypeDef.EVENING_SESSION_ORDER.equals(this.expirationDateType)
            && !WEB3ExecutionConditionDef.NO_CONDITION.equals(this.wlimitExecCondType))
        {
            log.debug("�v�w�l�p���s�������h�������h�ȊO�̒l�ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02503,
                this.getClass().getName() + STR_METHOD_NAME,
                "�v�w�l�p���s�������h�������h�ȊO�̒l�ł��B");
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (validate�A������)<BR>
     * �����N�G�X�g�f�[�^�̐������`�F�b�N���s���B<BR>
     *�i�������A���N���X���Ŋ�������ȈՃ`�F�b�N�݂̂Ƃ���B�j<BR>
     * <BR>
     * �@@�P�j�@@���s�����`�F�b�N<BR>
     * �@@�@@���s������"������"�̏ꍇ�́A<BR>
     *�@@ �u�A�������͎��s�����w��s�v�̗�O��throw����B<BR>
     * �@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@tag�@@:   BUSINESS_ERROR_02235<BR>
     * <BR>
     * �@@�Q�j�@@���������`�F�b�N<BR>
     * �@@�@@���������敪��"�w��Ȃ�"�̏ꍇ�́A<BR>
     * �@@�u�A�������͔��������w��s�v�̗�O��throw����B<BR>
     * �@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@tag�@@:   BUSINESS_ERROR_02236<BR>
     * 
     * @@throws WEB3BaseException
     * 
     */
    public void validateSuccOrder() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validateSuccOrder()";
        log.entering(STR_METHOD_NAME);
        
        // �P�j�@@���s�����`�F�b�N
        // ���s������"������"�̏ꍇ�́A
        if (!WEB3ExecutionConditionDef.NO_CONDITION.equals(this.execCondType))
        {
            // �u�A�������͎��s�����w��s�v�̗�O��throw����B
            log.debug("�A�������͎��s�����w��s�B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02235,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�A�������͎��s�����w��s�B");
        }
        
        // �Q�j�@@���������`�F�b�N
        // ���������敪��"�w��Ȃ�"�̏ꍇ��
        if (!WEB3OrderingConditionDef.DEFAULT.equals(this.orderCondType))
        {
            // �u�A�������͔��������w��s�v�̗�O��throw����B
            log.debug("�A�������͔��������w��s�B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02236,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�A�������͔��������w��s�B");
        }
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * ���Y���N�G�X�g�ɑΉ����郌�X�|���X�I�u�W�F�N�g��Ԃ��B<BR>
     *<BR>
     * @@return ���X�|���X�I�u�W�F�N�g
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3OptionsCommonResponse(this);
    }
}
@
