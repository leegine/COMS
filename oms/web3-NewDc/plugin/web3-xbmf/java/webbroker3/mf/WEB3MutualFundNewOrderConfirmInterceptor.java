head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.20;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3MutualFundNewOrderConfirmInterceptor.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ���M�V�K�����m��C���^�Z�v�^(WEB3MutualFundNewOrderConfirmInterceptor)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/08 ���� (���u) �V�K�쐬
Revesion History : 2004/12/06 ������ (���u) �c�Ή�
Revesion History : 2006/10/11 �����F (���u) ���f��504
Revesion History : 2007/03/09 �����F (���u) ���f��549
Revesion History : 2007/03/26 ��іQ (���u) ���f�� No.550
*/
package webbroker3.mf;

import java.sql.Timestamp;
import java.util.Date;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManagerPersistenceContext;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManagerPersistenceType;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingSystem;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountRow;
import com.fitechlabs.xtrade.plugin.tc.xbmf.MutualFundTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.xbmf.data.MutualFundOrderActionParams;
import com.fitechlabs.xtrade.plugin.tc.xbmf.data.MutualFundOrderUnitParams;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3HostReqOrderNumberManageService;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

/**
 * ���M�V�K�����m��C���^�Z�v�^<BR>
 *
 * @@author ����(���u)
 * @@version 1.0
 */
public class WEB3MutualFundNewOrderConfirmInterceptor
    extends WEB3DefaultMutualFundOrderConfirmInterceptor
{

    /**
     *  ���O���[�e�B���e�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3MutualFundNewOrderConfirmInterceptor.class);

    /**
     * �����`���l��<BR>
     * <BR>
     * 0�F�c�ƓX�@@1�F�C���^�[�l�b�g�@@2�F�R�[���Z���^�@@3�F���o�C�� <BR>
     */
    protected String orderChannel;

    /**
     * �v�Z����z<BR>
     */
    protected double constantValue;

    /**
     * �v�Z����z�i�抷��j<BR>
     */
    protected double switchingConstantValue;

    /**
     * �T�Z��n���<BR>
     */
    protected double estimatedPrice;

    /**
     * �T�Z��������<BR>
     */
    protected double estimatedQty;

    /**
     * ��n���@@<BR>
     */
    protected String deliveryDiv;

    /**
     * ���M�^�C�v<BR>
     * <BR>
     * 0�F���̑��@@1�F�����@@2�F���O�@@3�F�O��MMF<BR>
     */
    protected String mutualFundType;

    /**
     * ����<BR>
     */
    protected Timestamp executionTimestamp;

    /**
     * ���ϋ敪<BR>
     * <BR>
     * 1�F�~�݁@@2�F�O��<BR>
     */
    protected String settlementType;

    /**
     * ���萔���敪<BR>
     * <BR>
     * �󔒁F���֌W�@@5�F�抷�D���@@9�F���萔��<BR>
     */
    protected String noCommissionDivision;

    /**
     * �����R�[�h�i�抷��j<BR>
     */
    protected String switchingSubjectMutualProductCode;

    /**
     * �����敪<BR>
     * <BR>
     * 0�F���@@1�F����<BR>
     */
    protected String requestDivision;

    /**
     * �����o�H�敪<BR>
     * <BR>
     * 1�F�R�[���Z���^�[�@@2�F�o�b�@@3:�X�����O�V���b�g <BR>
     * 4�Fi-mode�@@5�FVodafone�@@6�FAU�@@9�FHOST <BR>
     * �i�R�[���Z���^�[�̎��̂ݎg�p�j<BR>
     */
    protected String orderChannelDivision;

    /**
     * ��n��<BR>
     */
    protected Timestamp deliveryDate;

    /**
     * ���M���敪<BR>
     */
    protected String mutualFundSellDiv;

    /**
     * ����z�K�p��<BR>
     */
    protected Timestamp constantValueAppDate;

    /**
     * �T�Z���t�����i�抷��j<BR>
     */
    protected double switchingEstimatedQty;

    /**
     * �ŋ敪�i�抷��j<BR>
     */
    protected TaxTypeEnum switchingSubjectTaxDivision;

    /**
     * �o���������ʃR�[�h<BR>
     */
    protected String paymentOrderReqNumber;

    /**
     * (�ꊇ�敪)<BR>
     * �ꊇ�敪<BR>
     */
    protected boolean  norealDiv;

    /**
     * (�����I����)<BR>
     * �����I����<BR>
     */
    protected Date orderEndDate;

    /**
     * (���M�V�K�����m��C���^�Z�v�^)<BR>
     * �f�t�H���g�R���X�g���N�^<BR>
     * @@roseuid 40AD8E0E03B4
     */
    public WEB3MutualFundNewOrderConfirmInterceptor()
    {
    }

    /**
     * (�X�V�l�ݒ�)<BR>
     * �imutate�̎����j<BR>
     * <BR>
     * �����E��������̒��ŁA���M���������f�[�^�̍쐬�E�X�V���ɌĂ΂��B<BR>
     * �����ŗ^����ꂽ���M��������Params�ɒl��ݒ肵�A���M��������Params��Ԃ��B <BR>
     * <BR>
     * �P�j�@@�����G���[���R�R�[�h�̐ݒ���s���B <BR>
     * �@@���M��������Params.set�����G���[���R�R�[�h()���R�[�����A�����G���[���R�R�[�h�̐ݒ���s���B <BR>
     * �@@�mset�����G���[���R�R�[�h�ɓn���p�����^�n <BR>
     * �@@�@@�����G���[���R�R�[�h�F null <BR>
     * <BR>
     * �Q�j�@@�����ŗ^����ꂽ���M��������Params��Ԃ��B <BR>
     * @@param l_orderManagerPersistenceType - (�i�����^�C�v)<BR>
     * �f�[�^�̍X�V�܂��͑}�����ɌĂԂ��ǂ����w��<BR>
     * @@param l_orderManagerPersistenceContext - (�i�����R���e�L�X�g)<BR>
     * �Ăяo�����̃R���e�L�X�g���w��B�Ⴆ�Ό����������ȂǁB<BR>
     * @@param l_mutualFundOrderActionParams - (���M��������Params)<BR>
     * �i�����O�̓��M��������Params<BR>
     * @@return MutualFundOrderActionParams
     * @@roseuid 40AD8E93025C
     */
    public MutualFundOrderActionParams mutate(
        OrderManagerPersistenceType l_orderManagerPersistenceType,
        OrderManagerPersistenceContext l_orderManagerPersistenceContext,
        MutualFundOrderActionParams l_mutualFundOrderActionParams)
    {
        final String STR_METHOD_NAME =
            "mutate(OrderManagerPersistenceType l_orderManagerPersistenceType," +
            "OrderManagerPersistenceContext l_orderManagerPersistenceContext, " +
            "MutualFundOrderActionParams l_mutualFundOrderActionParams) )";
        log.entering(STR_METHOD_NAME);

        if  (l_mutualFundOrderActionParams == null)
        {
            log.debug(" �p�����[�^Null�o���Ȃ��BWith " +
                "(�i�����O�̓��M��������Params)l_mutualFundOrderActionParams" +
                    l_mutualFundOrderActionParams);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�p�����[�^��Null�ł���");
        }
        // �P�j�@@�����G���[���R�R�[�h�̐ݒ���s���B
        l_mutualFundOrderActionParams.setErrorReasonCode(null);

        //�Q�j�@@�����ŗ^����ꂽ���M��������Params��Ԃ��B
        log.exiting(STR_METHOD_NAME);
        return l_mutualFundOrderActionParams;
    }

    /**
     * (�X�V�l�ݒ�)<BR>
     * �imutate�̎����j<BR>
     * <BR>
     * �����E��������̒��ŁA���M�����P�ʃf�[�^�̍쐬�E�X�V���ɌĂ΂��B<BR>
     * �����ŗ^����ꂽ���M�����P��Params�ɒl��ݒ肵�A���M�����P��Params��Ԃ��B<BR>
     * <BR>
     * �P�j�@@�⏕�����I�u�W�F�N�g�̎擾<BR>
     * �@@�|�g���A�J�E���g�}�l�[�W��.getSubAccount()���R�[�����āA�⏕�����I�u�W�F�N�g<BR>
     * �@@�@@���擾����B<BR>
     * �@@�@@�mgetSubAccount�ɓn���p�����^�n<BR>
     * �@@�@@�@@����ID�F ���M�����P��Params.getAccountId()�̖߂�l<BR>
     * �@@�@@�@@�⏕����ID�F ���M�����P��Params.getSubAccountId()�̖߂�l<BR>
     * <BR>
     * �Q�j�@@�󒍓����̐ݒ���s���B<BR>
     * �@@�|ThreadLocalSystemAttributesRegistry.getAttribute( )���R�[�����A<BR>
     * �����������擾����B <BR>
     * �@@�@@�mgetAttribute�ɓn���p�����^�n<BR>
     * �@@�@@�@@�ݒ�L�[�F �hxblocks.gtl.attributes.systemtimestamp�h<BR>
     * �@@�|���M�����P��Params.set�󒍓���()���R�[�����A�󒍓����̐ݒ���s���B<BR>
     * �@@�@@�mset�󒍓����ɓn���p�����^�n<BR>
     * �@@�@@�@@�󒍓����F �擾������������<BR>
     * <BR>
     * �R�j�@@���҃R�[�h(SONAR)�̐ݒ���s���B<BR>
     * �@@�|���M�����P��Params.set���҃R�[�h�iSONAR�j()���R�[�����A<BR>
     * ���҃R�[�h(SONAR)�̐ݒ���s���B <BR>
     * �@@�@@�mset���҃R�[�h�iSONAR�j�ɓn���p�����^�n<BR>
     * �@@�@@�@@���҃R�[�h(SONAR)�F <BR>
     * �擾�����⏕����.getMainAccount().getDataSourceObject().get���҃R�[�h�iSONAR�j()�̖߂�l<BR>
     * <BR>
     * �S�j�@@���ʃR�[�h�̐ݒ���s���B<BR>
     * �@@�|�������ʃR�[�h�̔ԃT�[�r�X.get�V�K���ʃR�[�h()���R�[������B <BR>
     * �@@�@@[get�V�K���ʃR�[�h�ɓn���p�����^] <BR>
     * �@@�@@�@@�،���ЃR�[�h�F �擾�����⏕����.getInstitution().getInstitutionCode() �̖߂�l <BR>
     * �@@�@@�@@���X�R�[�h�F �擾�����⏕����.getMainAccount().getBranch().getBranchCode()�̖߂�l <BR>
     * �@@�@@�@@�����^�C�v�F ProductTypeEnum.�����M�� <BR>
     * <BR>
     * �@@�|���M�����P��Params.set���ʃR�[�h()���R�[�����A���ʃR�[�h�̐ݒ���s���B<BR>
     * �@@�@@�mset���ʃR�[�h�ɓn���p�����^�n<BR>
     * �@@�@@�@@���ʃR�[�h�F �擾�������ʃR�[�h<BR>
     * <BR>
     * �T�j�@@�������̐ݒ���s���B  <BR>
     *�@@�|���M�����P��Params.set������()���R�[�����A�������̐ݒ���s���B <BR>
     *�@@�@@�mset�������ɓn���p�����^�n <BR>
     *�@@�@@�@@this.�ꊇ�敪��true�Ȃ�A <BR>
     *�@@�@@�@@�@@this.�����I������ݒ肷��B <BR>
     *�@@�@@�@@this.�ꊇ�敪��false�Ȃ�A <BR>
     *�@@�@@�@@�@@���M������ԊǗ�.get���M������(��������)�̖߂�l��ݒ肷��B<BR>
     * <BR>
     * �U�j�@@�v�Z����z�i�抷��j�̐ݒ���s���B<BR>
     * �@@�|���M�����P��Params.set�v�Z����z�i�抷��j()���R�[�����A<BR>
     * �v�Z����z�i�抷��j�̐ݒ���s���B<BR>
     * �@@�@@�mset�v�Z����z�i�抷��j�ɓn���p�����^�n<BR>
     * �@@�@@�@@�@@(*) Double.isNan(this.get�v�Z����z�i�抷��j())��true��Ԃ��ꍇ�́A<BR>
     * �@@�@@�@@�@@�@@�@@(Double)null��ݒ肷��B<BR>
     * �@@�@@�@@�@@(*) Double.isNan(this.get�v�Z����z�i�抷��j())��false��Ԃ��ꍇ�́A<BR>
     * �@@�@@�@@�@@�@@�@@this.get�v�Z����z�i�抷��j()�̖߂�l��ݒ肷��B<BR>
     * <BR>
     * �V�j�@@�T�Z���t�����i�抷��j�̐ݒ���s���B<BR>
     * �@@�|���M�����P��Params.set�T�Z���t�����i�抷��j()���R�[�����A<BR>
     * �T�Z���t�����i�抷��j�̐ݒ���s���B<BR>
     * �@@�@@�mset�T�Z���t�����i�抷��j�ɓn���p�����^�n<BR>
     * �@@�@@�@@�@@(*) Double.isNan(this.get�T�Z���t�����i�抷��j())��true��Ԃ��ꍇ�́A<BR>
     * �@@�@@�@@�@@�@@�@@(Double)null��ݒ肷��B<BR>
     * �@@�@@�@@�@@(*) Double.isNan(this.get�T�Z���t�����i�抷��j())��false��Ԃ��ꍇ�́A<BR>
     * �@@�@@�@@�@@�@@�@@this.get�T�Z���t�����i�抷��j()�̖߂�l��ݒ肷��B<BR>
     * <BR>
     * �W�j�@@���M�����P��Params�Ɉȉ��̐ݒ���s���B<BR>
     * <BR>
     * �@@�@@���M�����P��Params.set��n��(this.get��n��( ))<BR>
     * �@@�@@���M�����P��Params.set���񒍕��̒����`���l��(this.get�����`���l��( ))<BR>
     * �@@�@@���M�����P��Params.set�v�Z����z(this.get�v�Z����z( ))<BR>
     * �@@�@@���M�����P��Params.set����z�K�p��(this.get����z�K�p��( ))<BR>
     * �@@�@@���M�����P��Params.set�T�Z��n���(this.get�T�Z��n���( ))<BR>
     * �@@�@@���M�����P��Params.set�T�Z��������(this.get�T�Z��������( ))<BR>
     * �@@�@@���M�����P��Params.set�ŋ敪�i�抷��j(this.get�ŋ敪�i�抷��j( ))<BR>
     * �@@�@@���M�����P��Params.set��n���@@(this.get��n���@@( ))<BR>
     * �@@�@@���M�����P��Params.set���M�^�C�v(this.get���M�^�C�v( ))<BR>
     * �@@�@@���M�����P��Params.set���M���敪(this.get���M���敪())<BR>
     * �@@�@@���M�����P��Params.set����(this.get����( ))<BR>
     * �@@�@@���M�����P��Params.set�����(null)<BR>
     * �@@�@@���M�����P��Params.set���ϋ敪(this.get���ϋ敪( ))<BR>
     * �@@�@@���M�����P��Params.set���萔���敪(this.get���萔���敪( ))<BR>
     * �@@�@@���M�����P��Params.set�����R�[�h�i�抷��j(this.get�����R�[�h�i�抷��j( ))<BR>
     * �@@�@@���M�����P��Params.set�����敪(this.get�����敪( ))<BR>
     * �@@�@@���M�����P��Params.set�����o�H�敪(this.get�����o�H�敪( ))<BR>
     * �@@�@@���M�����P��Params.set�����G���[���R�R�[�h(null)<BR>
     *     ���M�����P��Params.set�o���������ʃR�[�h(this.get�o���������ʃR�[�h( ))
     * <BR>
     * �X�j�@@�����ŗ^����ꂽ���M�����P��Params��Ԃ��B<BR>
     * @@param l_orderManagerPersistenceType - (�i�����^�C�v)<BR>
     * �f�[�^�̍X�V�܂��͑}�����ɌĂԂ��ǂ����w��<BR>
     * @@param l_orderManagerPersistenceContext - (�i�����R���e�L�X�g)<BR>
     * �Ăяo�����̃R���e�L�X�g���w��B�Ⴆ�Ό����������ȂǁB<BR>
     * @@param l_mutualFundOrderUnitParams - (���M�����P��Params)<BR>
     * �i�����O�̓��M�����P��Params<BR>
     * @@return MutualFundOrderUnitParams
     * @@roseuid 40AD8E93026C
     */
    public MutualFundOrderUnitParams mutate(
        OrderManagerPersistenceType l_orderManagerPersistenceType,
        OrderManagerPersistenceContext l_orderManagerPersistenceContext,
        MutualFundOrderUnitParams l_mutualFundOrderUnitParams)
    {
        final String STR_METHOD_NAME =
            "mutate(OrderManagerPersistenceType l_orderManagerPersistenceType," +
            "OrderManagerPersistenceContext l_orderManagerPersistenceContext," +
            "MutualFundOrderUnitParams l_mutualFundOrderUnitParams)";
        log.entering(STR_METHOD_NAME);

        if(l_mutualFundOrderUnitParams == null)
        {
            log.debug(" �p�����[�^Null�o���Ȃ��BWith " +
                "(�i�����O�̓��M�����P��Params)l_mutualFundOrderUnitParams =" +
                    l_mutualFundOrderUnitParams);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�p�����[�^��Null�ł���");
        }

        //�P�j�@@�⏕�����I�u�W�F�N�g�̎擾

        //�⏕����
        SubAccount l_subAccount = null;

        //�g���A�J�E���g�}�l�[�W���擾
        FinApp l_finApp =
            (FinApp) Services.getService(FinApp.class);
        WEB3GentradeAccountManager l_gentradeAccountManaer =
            (WEB3GentradeAccountManager) l_finApp.getAccountManager();
        try
        {
            //�|�g���A�J�E���g�}�l�[�W��.getSubAccount()���R�[�����āA
             //�⏕�����I�u�W�F�N�g���擾����B
            l_subAccount =
                l_gentradeAccountManaer.getSubAccount(
                    l_mutualFundOrderUnitParams.getAccountId(),
                    l_mutualFundOrderUnitParams.getSubAccountId());
            log.debug("getAccountId" + l_mutualFundOrderUnitParams.getAccountId());
            log.debug("getSubAccountId" + l_mutualFundOrderUnitParams.getSubAccountId());
            log.debug("l_subAccount" + l_subAccount);

            // �Q�j�@@�󒍓����̐ݒ���s���B

            //�|ThreadLocalSystemAttributesRegistry.getAttribute( )
             //���R�[�����A�����������擾����B
            Date l_datDeliveryDate =
                (Date) ThreadLocalSystemAttributesRegistry.getAttribute(
                    TradingSystem.SYSTEM_TIMESTAMP_ATTRIBUTE_NAME);

            //�|���M�����P��Params.set�󒍓���()���R�[�����A�󒍓����̐ݒ���s���B
            l_mutualFundOrderUnitParams.setReceivedDateTime(l_datDeliveryDate);

            //�R�j�@@���҃R�[�h(SONAR)�̐ݒ���s���B

            //�|���M�����P��Params.set���҃R�[�h�iSONAR�j()���R�[�����A
             //���҃R�[�h(SONAR)��

            MainAccountRow l_mainAccountRow =
                (MainAccountRow) l_subAccount.getMainAccount().getDataSourceObject();
            String l_strSonarTraderCode =
                l_mainAccountRow.getSonarTraderCode();
            l_mutualFundOrderUnitParams.setSonarTraderCode(l_strSonarTraderCode);

            //�S�j�@@���ʃR�[�h�̐ݒ���s���B

            //�������ʃR�[�h�̔ԃT�[�r�X
            WEB3HostReqOrderNumberManageService l_hostReqOrderNumberManageService =
                (WEB3HostReqOrderNumberManageService)Services.getService(WEB3HostReqOrderNumberManageService.class);

            //�������ʃR�[�h�̔ԃT�[�r�X.get�V�K���ʃR�[�h()���R�[������B
            String l_strOrderRequestNumber =
                l_hostReqOrderNumberManageService.getNewNumber(
                    l_subAccount.getInstitution().getInstitutionCode(),
                    l_subAccount.getMainAccount().getBranch().getBranchCode(),
                    ProductTypeEnum.MUTUAL_FUND);

            //�|���M�����P��Params.set���ʃR�[�h()���R�[�����A���ʃR�[�h�̐ݒ���s���B
            l_mutualFundOrderUnitParams.setOrderRequestNumber(
                    l_strOrderRequestNumber);

           // �T�j�@@�������̐ݒ���s���B
           //�@@�|���M�����P��Params.set������()���R�[�����A�������̐ݒ���s���B
           //�@@�@@�mset�������ɓn���p�����^�n
           //�@@�@@�@@this.�ꊇ�敪��true�Ȃ�A
           //�@@�@@�@@�@@this.�����I������ݒ肷��B
           //�@@�@@�@@this.�ꊇ�敪��false�Ȃ�A
           //�@@�@@�@@�@@���M������ԊǗ�.get���M������(��������)�̖߂�l��ݒ肷��B
            String l_strBizDate = "";
            if (this.norealDiv)
            {
                l_strBizDate = WEB3DateUtility.formatDate(this.orderEndDate,"yyyyMMdd");
            }
            else
            {
                l_strBizDate =
                    WEB3DateUtility.formatDate(
                        WEB3MutualFundTradingTimeManagement.getMutualOrderBizDate(),
                        "yyyyMMdd");
            }
            l_mutualFundOrderUnitParams.setBizDate(l_strBizDate);
        }
        catch (NotFoundException l_ex)
        {
            log.error("�Y���⏕�����I�u�W�F�N�g�̕s����  with " +
                "(����ID)l_lngAccountId =" +
                    l_mutualFundOrderUnitParams.getAccountId() +
                " and (�⏕����ID)l_lngSubAccountId =" +
                    l_mutualFundOrderUnitParams.getSubAccountId());
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch(WEB3BaseException l_ex)
        {
            log.error(" �e�[�u���ɊY������f�[�^������܂���B With " +
                "(�،���ЃR�[�h)l_strInstitutionCode =" +
                    l_subAccount.getInstitution().getInstitutionCode() +
                " and (���X�R�[�h)l_strBranchCode =" +
                    l_subAccount.getMainAccount().getBranch().getBranchCode());
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        //�U�j�@@�v�Z����z�i�抷��j�̐ݒ���s���B
         /*  (*) Double.isNan(this.get�v�Z����z�i�抷��j())��true��Ԃ��ꍇ��
            �A(Double)null��ݒ肷��B
             (*) Double.isNan(this.get�v�Z����z�i�抷��j())��false��Ԃ��ꍇ�́A
             this.get�v�Z����z�i�抷��j()�̖߂�l��ݒ肷��B*/
        if (Double.isNaN(this.getSwitchingConstantValue()) == true)
        {
            l_mutualFundOrderUnitParams.setSwtCalcConstantValue(null);
        }
        else
        {
            l_mutualFundOrderUnitParams.setSwtCalcConstantValue(
                this.getSwitchingConstantValue());
        }

        //�V�j�@@�T�Z���t�����i�抷��j�̐ݒ���s���B
         /*  (*) Double.isNan(this.get�T�Z���t�����i�抷��j())��true��Ԃ��ꍇ��
            (Double)null��ݒ肷��
             (*) Double.isNan(this.get�T�Z���t�����i�抷��j())��false��Ԃ��ꍇ��
                  this.get�T�Z���t�����i�抷��j()�̖߂�l��ݒ肷��B */
        if (Double.isNaN(this.getSwitchingEstimatedQty()) == true)
        {
            l_mutualFundOrderUnitParams.setSwtEstimateDealingQty(null);
        }
        else
        {
            l_mutualFundOrderUnitParams.setSwtEstimateDealingQty(
                (long) this.getSwitchingEstimatedQty());
        }

        //�W�j�@@���M�����P��Params�Ɉȉ��̐ݒ���s���B

        //���M�����P��Params.set��n��(this.get��n��( ))
        l_mutualFundOrderUnitParams.setDeliveryDate(
            this.getDeliveryDate());

        //���M�����P��Params.set���񒍕��̒����`���l��(this.get�����`���l��( ))
        l_mutualFundOrderUnitParams.setOrderChanel(
            this.getOrderChannel());

        //���M�����P��Params.set�v�Z����z(this.get�v�Z����z( ))
        if (Double.isNaN(this.getConstantValue()) == true)
        {
            l_mutualFundOrderUnitParams.setCalcConstantValue(null);
        }
        else
        {
            l_mutualFundOrderUnitParams.setCalcConstantValue(
                this.getConstantValue());
        }

        //���M�����P��Params.set����z�K�p��(this.get����z�K�p��( ))
        l_mutualFundOrderUnitParams.setConstantValueAppDate(
            this.getConstantValueAppDate());

        //���M�����P��Params.set�T�Z��n���(this.get�T�Z��n���( ))
        l_mutualFundOrderUnitParams.setEstimatedPrice(
            this.getEstimatedPrice());

        //���M�����P��Params.set�T�Z��������(this.get�T�Z��������( ))
        l_mutualFundOrderUnitParams.setEstimateDealingQty(
            (long) this.getEstimatedQty());

        //���M�����P��Params.set�ŋ敪�i�抷��j(this.get�ŋ敪�i�抷��j( ))
        l_mutualFundOrderUnitParams.setSwtTaxType(
            this.getSwitchingSubjectTaxDivision());

        // ���M�����P��Params.set��n���@@(this.get��n���@@( ))
        l_mutualFundOrderUnitParams.setPaymentMethod(this.getDeliveryDiv());

        //���M�����P��Params.set���M�^�C�v(this.get���M�^�C�v( ))

        //** The other mutual fund besides domestic and foreign mutual fund product. */
        if (MutualFundTypeEnum.OTHER.intValue() ==
            Integer.parseInt(this.getMutualFundType()))
        {
            l_mutualFundOrderUnitParams.setFundType(MutualFundTypeEnum.OTHER);
        }

        //** Domestic mutual fund product. */
        else if (MutualFundTypeEnum.DOMESTIC.intValue() ==
            Integer.parseInt(this.getMutualFundType()))
        {
            l_mutualFundOrderUnitParams.setFundType(MutualFundTypeEnum.DOMESTIC);
        }

        //** Foreign mutual fund product. */
        else if (MutualFundTypeEnum.FOREIGN.intValue() ==
            Integer.parseInt(this.getMutualFundType()))
        {
            l_mutualFundOrderUnitParams.setFundType(MutualFundTypeEnum.FOREIGN);
        }
        //FOREIGN_MMF
        else if (MutualFundTypeEnum.FOREIGN_MMF.intValue() ==
            Integer.parseInt(this.getMutualFundType()))
        {
            l_mutualFundOrderUnitParams.setFundType(MutualFundTypeEnum.FOREIGN_MMF);
        }

        //���M�����P��Params.set���M���敪(this.get���M���敪())
        l_mutualFundOrderUnitParams.setFundSellDiv(
            this.getMutualFundSellDiv());

        //���M�����P��Params.set����(this.get����( ))
        l_mutualFundOrderUnitParams.setExecDate(
            this.getExecutionTimestamp());

        // ���M�����P��Params.set�����(null)
        l_mutualFundOrderUnitParams.setExecStatus(null);

        //���M�����P��Params.set���ϋ敪(this.get���ϋ敪( ))
        l_mutualFundOrderUnitParams.setSettlementDiv(
            this.getSettlementType());

        //���M�����P��Params.set���萔���敪(this.get���萔���敪( ))
        l_mutualFundOrderUnitParams.setNoContractCommissionDiv(
            this.getNoCommissionDivision());

        //���M�����P��Params.set�����R�[�h�i�抷��j(this.get�����R�[�h�i�抷��j( ))
        l_mutualFundOrderUnitParams.setSwtProductCode(
            this.getSwitchingSubjectMutualProductCode());

        //���M�����P��Params.set�����敪(this.get�����敪( ))
        l_mutualFundOrderUnitParams.setRequestDiv(
            this.getRequestDivision());

        //���M�����P��Params.set�����o�H�敪(this.get�����o�H�敪( ))
        l_mutualFundOrderUnitParams.setOrderRootDiv(
            this.getOrderChannelDivision());

        // ���M�����P��Params.set�����G���[���R�R�[�h(null)
        l_mutualFundOrderUnitParams.setErrorReasonCode(null);

        //���M�����P��Params.set�o���������ʃR�[�h(this.get�o���������ʃR�[�h( ))
        l_mutualFundOrderUnitParams.setPaymentOrderReqNumber(
            this.getPaymentOrderReqNumber());

        //�X�j�@@�����ŗ^����ꂽ���M�����P��Params��Ԃ��B
        log.exiting(STR_METHOD_NAME);
        return l_mutualFundOrderUnitParams;
    }

    /**
     * �����`���l���̐ݒ���s���B<BR>
     * @@param l_strOrderChannel - �����`���l��<BR>
     * @@roseuid 40AD92050133
     */
    public void setOrderChannel(String l_strOrderChannel)
    {
        this.orderChannel = l_strOrderChannel;
    }

    /**
     * this.�����`���l����Ԃ��B<BR>
     * @@return String
     * @@roseuid 40AD91EC00E5
     */
    public String getOrderChannel()
    {
        return orderChannel;
    }

    /**
     * �v�Z����z�̐ݒ���s���B<BR>
     * @@param l_dblConstantValue - ����z<BR>
     * @@roseuid 40AD92AE0114
     */
    public void setConstantValue(double l_dblConstantValue)
    {
        this.constantValue = l_dblConstantValue;
    }

    /**
     * this.�v�Z����z��Ԃ��B<BR>
     * @@return double
     * @@roseuid 40AD92B5021D
     */
    public double getConstantValue()
    {
        return constantValue;
    }

    /**
     * �v�Z����z�i�抷��j�̐ݒ���s���B<BR>
     * @@param l_dblSwitchingConstantValue - ����z�i�抷��j<BR>
     * @@roseuid 40D7D3B20221
     */
    public void setSwitchingConstantValue(double l_dblSwitchingConstantValue)
    {
        this.switchingConstantValue = l_dblSwitchingConstantValue;
    }

    /**
     * this.�v�Z����z�i�抷��j��Ԃ��B<BR>
     * @@return double
     * @@roseuid 40D7D3B20230
     */
    public double getSwitchingConstantValue()
    {
        return switchingConstantValue;
    }

    /**
     * �T�Z��n����̐ݒ���s���B<BR>
     * @@param l_dblEstimatedPrice - �T�Z��n���<BR>
     * @@roseuid 40AD92B702AA
     */
    public void setEstimatedPrice(double l_dblEstimatedPrice)
    {
        this.estimatedPrice = l_dblEstimatedPrice;
    }

    /**
     * this.�T�Z��n�����Ԃ��B<BR>
     * @@return double
     * @@roseuid 40AD92C30152
     */
    public double getEstimatedPrice()
    {
        return estimatedPrice;
    }

    /**
     * �T�Z���������̐ݒ���s���B<BR>
     * @@param l_dblEstimatedQty - �T�Z��������<BR>
     * @@roseuid 40AD92C7027B
     */
    public void setEstimatedQty(double l_dblEstimatedQty)
    {
        this.estimatedQty = l_dblEstimatedQty;
    }

    /**
     * this�T�Z����������Ԃ��B<BR>
     * @@return double
     * @@roseuid 40AD92D303D3
     */
    public double getEstimatedQty()
    {
        return estimatedQty;
    }

    /**
     * ��n���@@�̐ݒ���s���B<BR>
     * @@param l_strDeliveryDiv - ��n���@@<BR>
     * @@roseuid 40AD92D60366
     */
    public void setDeliveryDiv(String l_strDeliveryDiv)
    {
        this.deliveryDiv = l_strDeliveryDiv;
    }

    /**
     * this.��n���@@��Ԃ��B<BR>
     * @@return String
     * @@roseuid 40AD92DD026C
     */
    public String getDeliveryDiv()
    {
        return deliveryDiv;
    }

    /**
     * ���M�^�C�v�̐ݒ���s���B<BR>
     * @@param l_strMutualFundType - ���M�^�C�v<BR>
     * @@roseuid 40AD92E3000A
     */
    public void setMutualFundType(String l_strMutualFundType)
    {
        this.mutualFundType = l_strMutualFundType;
    }

    /**
     * this.���M�^�C�v��Ԃ��B<BR>
     * @@return String
     * @@roseuid 40AD92ED0308
     */
    public String getMutualFundType()
    {
        return mutualFundType;
    }

    /**
     * �����̐ݒ���s���B<BR>
     * @@param l_tsExecutionTimestamp - ����<BR>
     * @@roseuid 40AD92F70104
     */
    public void setExecutionTimestamp(Timestamp l_tsExecutionTimestamp)
    {
        this.executionTimestamp = l_tsExecutionTimestamp;
    }

    /**
     * this.������Ԃ��B<BR>
     * @@return Timestamp
     * @@roseuid 40AD9301023D
     */
    public Timestamp getExecutionTimestamp()
    {
        return executionTimestamp;
    }

    /**
     * ���ϋ敪�̐ݒ���s���B<BR>
     * @@param l_strSettlementType - ���ϋ敪<BR>
     * @@roseuid 40AD9312028B
     */
    public void setSettlementType(String l_strSettlementType)
    {
        this.settlementType = l_strSettlementType;
    }

    /**
     * this.���ϋ敪��Ԃ��B<BR>
     * @@return String
     * @@roseuid 40AD93190029
     */
    public String getSettlementType()
    {
        return settlementType;
    }

    /**
     * ���萔���敪�̐ݒ���s���B<BR>
     * @@param l_strNoCommissionDivision - ���萔���敪<BR>
     * @@roseuid 40AD931D0366
     */
    public void setNoCommissionDivision(String l_strNoCommissionDivision)
    {
        this.noCommissionDivision = l_strNoCommissionDivision;
    }

    /**
     * this.���萔���敪��Ԃ��B<BR>
     * @@return String
     * @@roseuid 40AD9327000A
     */
    public String getNoCommissionDivision()
    {
        return noCommissionDivision;
    }

    /**
     * �����R�[�h�i�抷��j�̐ݒ���s���B<BR>
     * @@param l_strSwitchingSubjectMutualProductCode - �����R�[�h�i�抷��j<BR>
     * @@roseuid 40AD936502D9
     */
    public void setSwitchingSubjectMutualProductCode(
        String l_strSwitchingSubjectMutualProductCode)
    {
        this.switchingSubjectMutualProductCode =
            l_strSwitchingSubjectMutualProductCode;
    }

    /**
     * this.�����R�[�h�i�抷��j��Ԃ��B<BR>
     * @@return String
     * @@roseuid 40AD93750058
     */
    public String getSwitchingSubjectMutualProductCode()
    {
        return switchingSubjectMutualProductCode;
    }

    /**
     * �����敪�̐ݒ���s���B<BR>
     * @@param l_strRequestDivision - �����敪<BR>
     * @@roseuid 40AD937A0087
     */
    public void setRequestDivision(String l_strRequestDivision)
    {
        this.requestDivision = l_strRequestDivision;
    }

    /**
     * this.�����敪��Ԃ��B<BR>
     * @@return String
     * @@roseuid 40AD9380028B
     */
    public String getRequestDivision()
    {
        return requestDivision;
    }

    /**
     * �����o�H�敪�̐ݒ���s���B<BR>
     * @@param l_strOrderChannelDivision - �����o�H�敪<BR>
     * @@roseuid 40AD93830068
     */
    public void setOrderChannelDivision(String l_strOrderChannelDivision)
    {
        this.orderChannelDivision = l_strOrderChannelDivision;
    }

    /**
     * this.�����o�H�敪��Ԃ��B<BR>
     * @@return String
     * @@roseuid 40AD938B00F5
     */
    public String getOrderChannelDivision()
    {
        return orderChannelDivision;
    }

    /**
     * ��n���̐ݒ���s���B<BR>
     * @@param l_tsDeliveryDate - ��n��<BR>
     * @@roseuid 40B338FB03A9
     */
    public void setDeliveryDate(Timestamp l_tsDeliveryDate)
    {
        this.deliveryDate = l_tsDeliveryDate;
    }

    /**
     * this.��n����Ԃ��B<BR>
     * @@return Timestamp
     * @@roseuid 40B339350232
     */
    public Timestamp getDeliveryDate()
    {
        return deliveryDate;
    }

    /**
     * ���M���敪�̐ݒ���s���B<BR>
     * @@param l_strMutualFundSellDiv - ���M���敪<BR>
     * @@roseuid 40B4558D02FD
     */
    public void setMutualFundSellDiv(String l_strMutualFundSellDiv)
    {
        this.mutualFundSellDiv = l_strMutualFundSellDiv;
    }

    /**
     * this.���M���敪��Ԃ��B<BR>
     * @@return String
     * @@roseuid 40B4558D030D
     */
    public String getMutualFundSellDiv()
    {
        return mutualFundSellDiv;
    }

    /**
     * ����z�K�p���̐ݒ���s���B<BR>
     * @@param l_tsConstantValueAppDate - ����z�K�p��<BR>
     * @@roseuid 40CFE5A500DA
     */
    public void setConstantValueAppDate(Timestamp l_tsConstantValueAppDate)
    {
        this.constantValueAppDate = l_tsConstantValueAppDate;
    }

    /**
     * this.����z�K�p����Ԃ��B<BR>
     * @@return Timestamp
     * @@roseuid 40CFE5A500DC
     */
    public Timestamp getConstantValueAppDate()
    {
        return constantValueAppDate;
    }

    /**
     * �T�Z���t�����i�抷��j�̐ݒ���s���B<BR>
     * @@param l_dblSwitchingEstimatedQty - �抷��T�Z���t����<BR>
     * @@roseuid 40D2BB550253
     */
    public void setSwitchingEstimatedQty(double l_dblSwitchingEstimatedQty)
    {
        this.switchingEstimatedQty = l_dblSwitchingEstimatedQty;
    }

    /**
     * this.�T�Z���t�����i�抷��j��Ԃ��B<BR>
     * @@return double
     * @@roseuid 40D2BB550255
     */
    public double getSwitchingEstimatedQty()
    {
        return switchingEstimatedQty;
    }

    /**
     * �ŋ敪�i�抷��j�̐ݒ���s���B<BR>
     * @@param l_switchingSubjectTaxDivision - �ŋ敪�i�抷��j<BR>
     * @@roseuid 40D2BBB5007E
     */
    public void setSwitchingSubjectTaxDivision(
        TaxTypeEnum l_switchingSubjectTaxDivision)
    {
        this.switchingSubjectTaxDivision = l_switchingSubjectTaxDivision;
    }

    /**
     * this.�ŋ敪�i�抷��j��Ԃ��B<BR>
     * @@return com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum
     * @@roseuid 40D2BBB50080
     */
    public TaxTypeEnum getSwitchingSubjectTaxDivision()
    {
        return switchingSubjectTaxDivision;
    }

    /**
     * �o���������ʃR�[�h�̐ݒ���s���B<BR>
     * @@param l_strPaymentOrderReqNumber - �o���������ʃR�[�h<BR>
     * @@roseuid 40D2BBB5007E
     */
    public void setPaymentOrderReqNumber(
        String l_strPaymentOrderReqNumber)
    {
        this.paymentOrderReqNumber = l_strPaymentOrderReqNumber;
    }

    /**
     * this.�o���������ʃR�[�h��Ԃ��B<BR>
     * @@return String
     * @@roseuid 40D2BBB50080
     */
    public String getPaymentOrderReqNumber()
    {
        return paymentOrderReqNumber;
    }

    /**
     * (set�ꊇ�敪)<BR>
     * �ꊇ�敪�̐ݒ���s���B<BR>
     * @@param l_blnNorealDiv - (�ꊇ�敪)<BR>
     */
    public void setNorealDiv(boolean l_blnNorealDiv)
    {
        this.norealDiv = l_blnNorealDiv;
    }

    /**
     * (get�ꊇ�敪)<BR>
     * this.�ꊇ�敪��Ԃ��B<BR>
     * @@return boolean
     */
    public boolean getNorealDiv()
    {
        return norealDiv;
    }

    /**
     * (set�����I���� )<BR>
     * �����I�����̐ݒ���s���B<BR>
     * @@param l_datOrderEndDiv - (�����I����)<BR>
     */
    public void setOrderEndDate(Date l_datOrderEndDiv)
    {
        this.orderEndDate= l_datOrderEndDiv;
    }

    /**
     * (get�����I����)<BR>
     * this.�����I������Ԃ��B<BR>
     * @@return Date
     */
    public Date getOrderEndDate()
    {
        return orderEndDate;
    }
}
@
