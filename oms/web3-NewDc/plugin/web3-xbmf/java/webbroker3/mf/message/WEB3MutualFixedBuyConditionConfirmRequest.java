head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.01.59;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3MutualFixedBuyConditionConfirmRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ���M�莞��z���t���������o�^�m�F���N�G�X�g(WEB3MutualFixedBuyConditionConfirmRequest.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/07/10 ���z(���u) �V�K�쐬 ���f��605
Revision History : 2008/07/31 ���g(���u) �d�l�ύX ���f��622
*/

package webbroker3.mf.message;

import java.util.Date;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;


/**
 * (���M�莞��z���t���������o�^�m�F���N�G�X�g)<BR>
 * ���M�莞��z���t���������o�^�m�F���N�G�X�g<BR>
 * <BR>
 * @@author ���z
 * @@version 1.0
 */
public class WEB3MutualFixedBuyConditionConfirmRequest extends WEB3GenRequest
{
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "mutual_fixed_buy_condition_confirm";

    /**
     * SerialVersionUID<BR>
     */
    public final static long serialVersionUID = 200807101426L;

    /**
     * ���O���[�e�B���e�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3MutualFixedBuyConditionConfirmRequest.class);

    /**
     * (���M�莞��z���t�ϗ��o�^���e)<BR>
     * ���M�莞��z���t�ϗ��o�^���e<BR>
     */
    public WEB3MutualFixedBuyCommonUnit[] conditionList;

    /**
     * (���M�莞��z���t�V�K�ǉ����e)<BR>
     * ���M�莞��z���t�V�K�ǉ�<BR>
     */
    public WEB3MutualFixedBuyCommonUnit[] addConditionList;

    /**
     * (���M�莞��z���������o�^�m�F���N�G�X�g)<BR>
     * �f�t�H���g�R���X�g���N�^<BR>
     * @@roseuid 4848EE9302CD
     */
    public WEB3MutualFixedBuyConditionConfirmRequest()
    {

    }

    /**
     * (create���X�|���X)<BR>
     * �icreateResponse�̎����j<BR>
     * <BR>
     * ���M�莞��z���t���������o�^�m�F���X�|���X�I�u�W�F�N�g�𐶐����ĕԂ��B<BR>
     * @@return WEB3GenResponse
     * @@roseuid 4848EF5A017C
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3MutualFixedBuyConditionConfirmResponse(this);
    }

    /**
     * �����N�G�X�g�f�[�^�̐������`�F�b�N���s���B<BR>
     * �i�������A���N���X���Ŋ�������ȈՃ`�F�b�N�݂̂Ƃ���B�j<BR>
     * <BR>
     * �P�j���M�莞��z���t�ϗ��o�^���e�̃`�F�b�N<BR>
     * <BR>
     * �@@�@@�P�|�P)�@@���M�莞��z���t�ϗ��o�^���e�̗v�f�����J��Ԃ��ă`�F�b�N���s���B<BR>
     * <BR>
     * �@@�@@�@@�@@�P�|�P�|�P�j�����R�[�h == null �̏ꍇ�A��O���X���[����B<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@(�����R�[�h���w��G���[�j<BR>
     * �@@�@@�@@�@@�@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@�@@�@@�@@tag:   BUSINESS_ERROR_00079<BR>
     * <BR>
     * �@@�@@�@@�@@1�|�P�|�Q�j���t���z�i���X�j �� null �̏ꍇ�A�ȉ��̃`�F�b�N���s�Ȃ��B<BR>
     * <BR>
     * �@@�@@�@@�@@�P�|�P�|�Q�|�P�j���t���z�i���X�j�������ȊO�̏ꍇ�A��O���X���[����B<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�i�����`�F�b�N�G���[�j<BR>
     * �@@�@@�@@�@@�@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@�@@�@@�@@tag:   BUSINESS_ERROR_02476<BR>
     * <BR>
     * �@@�@@�@@�@@�P�|�P�|�Q�|�Q�j���t���z�i���X�j �� 8���̏ꍇ�A��O���X���[����B<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�i���t���z�i���X�j�����G���[�j<BR>
     * �@@�@@�@@�@@�@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@�@@�@@�@@tag:   BUSINESS_ERROR_02477<BR>
     * <BR>
     * �@@�@@�@@�@@1�|�P�|�R�j���t���z�i�ςݑ����j �� null �̏ꍇ�A�ȉ��̃`�F�b�N���s�Ȃ��B<BR>
     * <BR>
     * �@@�@@�@@�@@�@@�@@1�|�P�|�R�|�P�j���t���z�i�ςݑ����j�������ȊO�̏ꍇ�A��O���X���[����B<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�i�����`�F�b�N�G���[�j<BR>
     * �@@�@@�@@�@@�@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@�@@�@@�@@tag:   BUSINESS_ERROR_02478<BR>
     * <BR>
     * �@@�@@�@@�@@�@@�@@1�|�P�|�R�|�Q�j���t���z�i�ςݑ����j �� 9���̏ꍇ�A��O���X���[����B<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�i���t���z�i�ςݑ����j�����G���[�j<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�����N�G�X�g�f�[�^�̐������`�F�b�N���s���B <BR>
     * �@@�@@�@@�@@�@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@�@@�@@�@@tag:   BUSINESS_ERROR_02479<BR>
     * <BR>
     * �@@�@@�@@�@@�P�|�P�|�S�j�ύX�敪 == null �̏ꍇ�A��O���X���[����B<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@(�ύX�敪���w��G���[�j<BR>
     * �@@�@@�@@�@@�@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@�@@�@@�@@tag:   BUSINESS_ERROR_03102<BR>
     * <BR>
     * �@@�@@�@@�@@�P�|�P�|�T�j�K�p�J�n�N�� == null �̏ꍇ�A��O���X���[����B<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@(�K�p�J�n�N�����w��G���[�j<BR>
     * �@@�@@�@@�@@�@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@�@@�@@�@@tag:   BUSINESS_ERROR_03103<BR>
     * <BR>
     * <BR>
     * �Q�j���M�莞��z���t�V�K�ǉ����e�̃`�F�b�N<BR>
     * <BR>
     * �@@�@@�Q�|�P)�@@���M�莞��z���t�V�K�ǉ����e�̗v�f�����J��Ԃ��ă`�F�b�N���s���B<BR>
     * <BR>
     * �@@�@@�@@�@@�Q�|�P�|�P�j�����R�[�h == null �̏ꍇ�A��O���X���[����B<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@(�����R�[�h���w��G���[�j<BR>
     * �@@�@@�@@�@@�@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@�@@�@@�@@tag:   BUSINESS_ERROR_00079<BR>
     * <BR>
     * �@@�@@�@@�@@�Q�|�P�|�Q�j���t���z�i���X�j == null �̏ꍇ�A��O���X���[����B<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�i���t���z�i���X�j�����̓G���[�j<BR>
     * �@@�@@�@@�@@�@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@�@@�@@�@@tag:   BUSINESS_ERROR_02475<BR>
     * <BR>
     * �@@�@@�@@�@@�Q�|�P�|�R�j���t���z�i���X�j�������ȊO�̏ꍇ�A��O���X���[����B<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�i�����`�F�b�N�G���[�j<BR>
     * �@@�@@�@@�@@�@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@�@@�@@�@@tag:   BUSINESS_ERROR_02476<BR>
     * <BR>
     * �@@�@@�@@�@@�Q�|�P�|�S�j���t���z�i���X�j �� 8���̏ꍇ�A��O���X���[����B<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�i���t���z�i���X�j�����G���[�j<BR>
     * �@@�@@�@@�@@�@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@�@@�@@�@@tag:   BUSINESS_ERROR_02477<BR>
     * <BR>
     * �@@�@@�@@�@@�Q�|�P�|�T�j���t���z�i�ςݑ����j �� null �̏ꍇ�A�ȉ��̃`�F�b�N���s�Ȃ��B<BR>
     * <BR>
     * �@@�@@�@@�@@�@@�@@�Q�|�P�|�T�|�P�j���t���z�i�ςݑ����j�������ȊO�̏ꍇ�A��O���X���[����B<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�i�����`�F�b�N�G���[�j<BR>
     * �@@�@@�@@�@@�@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@�@@�@@�@@tag:   BUSINESS_ERROR_02478<BR>
     * <BR>
     * �@@�@@�@@�@@�@@�@@�Q�|�P�|�T�|�Q�j���t���z�i�ςݑ����j �� 9���̏ꍇ�A��O���X���[����B<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�i���t���z�i�ςݑ����j�����G���[�j<BR>
     * �@@�@@�@@�@@�@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@�@@�@@�@@tag:   BUSINESS_ERROR_02479<BR>
     * <BR>
     * �@@�@@�@@�@@�Q�|�P�|�U�j�ύX�敪 == null �̏ꍇ�A��O���X���[����B<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@(�ύX�敪���w��G���[�j<BR>
     * �@@�@@�@@�@@�@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@�@@�@@�@@tag:   BUSINESS_ERROR_03102<BR>
     * <BR>
     * �@@�@@�@@�@@�Q�|�P�|�V�j�K�p�J�n�N�� == null �̏ꍇ�A��O���X���[����B<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@(�K�p�J�n�N�����w��G���[�j<BR>
     * �@@�@@�@@�@@�@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@�@@�@@�@@tag:   BUSINESS_ERROR_03103<BR>
     * <BR>
     * @@throws WEB3BaseException
     * @@roseuid 4858ACEC022E
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validate()";
        log.entering(STR_METHOD_NAME);

        //�P�j���M�莞��z���t�ϗ��o�^���e�̃`�F�b�N
        if (this.conditionList != null)
        {
            //�@@�P�|�P)�@@���M�莞��z���t�ϗ��o�^���e�̗v�f�����J��Ԃ��ă`�F�b�N���s���B
            int l_intConditionListLength = this.conditionList.length;
            for (int i = 0; i < l_intConditionListLength; i++)
            {
                //�P�|�P�|�P�j�����R�[�h == null �̏ꍇ�A��O���X���[����B
                String l_strMutualProductCode = this.conditionList[i].mutualProductCode;
                if (l_strMutualProductCode == null)
                {
                    log.debug("�����R�[�h�����w��ł��B");
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_00079,
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        "�����R�[�h�����w��ł��B");
                }
    
                //1�|�P�|�Q�j���t���z�i���X�j �� null �̏ꍇ�A�ȉ��̃`�F�b�N���s�Ȃ��B
                String l_strMonthlyBuyAmount = this.conditionList[i].monthlyBuyAmount;
                if (!WEB3StringTypeUtility.isEmpty(l_strMonthlyBuyAmount))
                {
                    //�P�|�P�|�Q�|�P�j���t���z�i���X�j�������ȊO�̏ꍇ�A��O���X���[����B
                    if (!WEB3StringTypeUtility.isDigit(l_strMonthlyBuyAmount))
                    {
                        log.debug("���t���z�i���X�j�������ȊO�̒l�ł��B");
                        log.exiting(STR_METHOD_NAME);
                        throw new WEB3BusinessLayerException(
                            WEB3ErrorCatalog.BUSINESS_ERROR_02476,
                            this.getClass().getName() + "." + STR_METHOD_NAME,
                            "���t���z�i���X�j�������ȊO�̒l�ł��B");
                    }
    
                    //�P�|�P�|�Q�|�Q�j���t���z�i���X�j �� 8���̏ꍇ�A��O���X���[����B
                    int l_intMonthlyBuyAmoutLength = l_strMonthlyBuyAmount.length();
                    if (l_intMonthlyBuyAmoutLength >= 8)
                    {
                        log.debug("���t���z�i���X�j�����G���[�B");
                        log.exiting(STR_METHOD_NAME);
                        throw new WEB3BusinessLayerException(
                            WEB3ErrorCatalog.BUSINESS_ERROR_02477,
                            this.getClass().getName() + "." + STR_METHOD_NAME,
                            "���t���z�i���X�j�����G���[�B");
                    }
                }
    
                //1�|�P�|�R�j���t���z�i�ςݑ����j �� null �̏ꍇ�A�ȉ��̃`�F�b�N���s�Ȃ��B
                String l_strIncreaseBuyAmount = this.conditionList[i].increaseBuyAmount;
                if (!WEB3StringTypeUtility.isEmpty(l_strIncreaseBuyAmount))
                {
                    //1�|�P�|�R�|�P�j���t���z�i�ςݑ����j�������ȊO�̏ꍇ�A��O���X���[����B
                    if (!WEB3StringTypeUtility.isDigit(l_strIncreaseBuyAmount))
                    {
                        log.debug("���t���z�i�ςݑ����j�������ȊO�̒l�ł��B");
                        log.exiting(STR_METHOD_NAME);
                        throw new WEB3BusinessLayerException(
                            WEB3ErrorCatalog.BUSINESS_ERROR_02478,
                            this.getClass().getName() + "." + STR_METHOD_NAME,
                            "���t���z�i�ςݑ����j�������ȊO�̒l�ł��B");
                    }
    
                    //1�|�P�|�R�|�Q�j���t���z�i�ςݑ����j �� 9���̏ꍇ�A��O���X���[����B
                    int l_intIncreaseBuyAmountLength = l_strIncreaseBuyAmount.length();
                    if (l_intIncreaseBuyAmountLength >= 9)
                    {
                        log.debug("���t���z�i�ςݑ����j�����G���[�B");
                        log.exiting(STR_METHOD_NAME);
                        throw new WEB3BusinessLayerException(
                            WEB3ErrorCatalog.BUSINESS_ERROR_02479,
                            this.getClass().getName() + "." + STR_METHOD_NAME,
                            "���t���z�i�ςݑ����j�����G���[�B");
                    }
                }
    
                //�P�|�P�|�S�j�ύX�敪 == null �̏ꍇ�A��O���X���[����B
                String l_strChangeDiv = this.conditionList[i].changeDiv;
                if (l_strChangeDiv == null)
                {
                    log.debug("�ύX�敪���w��G���[�B");
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_03102,
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        "�ύX�敪���w��G���[�B");
                }
    
                //�P�|�P�|�T�j�K�p�J�n�N�� == null �̏ꍇ�A��O���X���[����B
                Date l_datValidStartDate = this.conditionList[i].validStartDate;
                if (l_datValidStartDate == null)
                {
                    log.debug("�K�p�J�n�N�����w��G���[�B");
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_03103,
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        "�K�p�J�n�N�����w��G���[�B");
                }
    
            }
        }

        //�Q�j���M�莞��z���t�V�K�ǉ����e�̃`�F�b�N
        if (this.addConditionList != null)
        {
            //�@@�Q�|�P)�@@���M�莞��z���t�V�K�ǉ����e�̗v�f�����J��Ԃ��ă`�F�b�N���s��
            int l_intAddConditionListLength = this.addConditionList.length;
            for (int i = 0; i < l_intAddConditionListLength; i++)
            {
                //�Q�|�P�|�P�j�����R�[�h == null �̏ꍇ�A��O���X���[����B
                String l_strMutualProductCode = this.addConditionList[i].mutualProductCode;
                if (l_strMutualProductCode == null)
                {
                    log.debug("�����R�[�h�����w��ł��B");
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_00079,
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        "�����R�[�h�����w��ł��B");
                }
    
                //�Q�|�P�|�Q�j���t���z�i���X�j == null �̏ꍇ�A��O���X���[����B
                String l_strMonthlyBuyAmount = this.addConditionList[i].monthlyBuyAmount;
                if (l_strMonthlyBuyAmount == null)
                {
                    log.debug("���t���z���̓G���[�B");
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_02475,
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        "���t���z���̓G���[�B");
                }
    
                //�Q�|�P�|�R�j���t���z�i���X�j�������ȊO�̏ꍇ�A��O���X���[����B
                if (!WEB3StringTypeUtility.isDigit(l_strMonthlyBuyAmount))
                {
                    log.debug("���t���z�i���X�j�������ȊO�̒l�ł��B");
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_02476,
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        "���t���z�i���X�j�������ȊO�̒l�ł��B");
                }
    
                //�Q�|�P�|�S�j���t���z�i���X�j �� 8���̏ꍇ�A��O���X���[����B
                int l_intMonthlyBuyAmoutLength = l_strMonthlyBuyAmount.length();
                if (l_intMonthlyBuyAmoutLength >= 8)
                {
                    log.debug("���t���z�i���X�j�����G���[�B");
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_02477,
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        "���t���z�i���X�j�����G���[�B");
                }
    
                //�Q�|�P�|�T�j���t���z�i�ςݑ����j �� null �̏ꍇ�A�ȉ��̃`�F�b�N���s�Ȃ��B
                String l_strIncreaseBuyAmount = this.addConditionList[i].increaseBuyAmount;
                if (!WEB3StringTypeUtility.isEmpty(l_strIncreaseBuyAmount))
                {
                    //�Q�|�P�|�T�|�P�j���t���z�i�ςݑ����j�������ȊO�̏ꍇ�A��O���X���[����B
                    if (!WEB3StringTypeUtility.isDigit(l_strIncreaseBuyAmount))
                    {
                        log.debug("���t���z�i�ςݑ����j�������ȊO�̒l�ł��B");
                        log.exiting(STR_METHOD_NAME);
                        throw new WEB3BusinessLayerException(
                            WEB3ErrorCatalog.BUSINESS_ERROR_02478,
                            this.getClass().getName() + "." + STR_METHOD_NAME,
                            "���t���z�i�ςݑ����j�������ȊO�̒l�ł��B");
                    }
    
                    //�Q�|�P�|�T�|�Q�j���t���z�i�ςݑ����j �� 9���̏ꍇ�A��O���X���[����B
                    int l_intIncreaseBuyAmountLength = l_strIncreaseBuyAmount.length();
                    if (l_intIncreaseBuyAmountLength >= 9)
                    {
                        log.debug("���t���z�i�ςݑ����j�����G���[�B");
                        log.exiting(STR_METHOD_NAME);
                        throw new WEB3BusinessLayerException(
                            WEB3ErrorCatalog.BUSINESS_ERROR_02479,
                            this.getClass().getName() + "." + STR_METHOD_NAME,
                            "���t���z�i�ςݑ����j�����G���[�B");
                    }
                }
    
                //�Q�|�P�|�U�j�ύX�敪 == null �̏ꍇ�A��O���X���[����B
                String l_strChangeDiv = this.addConditionList[i].changeDiv;
                if (l_strChangeDiv == null)
                {
                    log.debug("�ύX�敪���w��G���[�B");
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_03102,
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        "�ύX�敪���w��G���[�B");
                }
    
                //�Q�|�P�|�V�j�K�p�J�n�N�� == null �̏ꍇ�A��O���X���[����B
                Date l_datValidStartDate = this.addConditionList[i].validStartDate;
                if (l_datValidStartDate == null)
                {
                    log.debug("�K�p�J�n�N�����w��G���[�B");
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_03103,
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        "�K�p�J�n�N�����w��G���[�B");
                }
            }
        }

        log.exiting(STR_METHOD_NAME);
    }
}
@
